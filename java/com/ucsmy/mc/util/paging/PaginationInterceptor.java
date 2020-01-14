package com.ucsmy.mc.util.paging;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ucsmy.mc.util.SortUtil;

//只拦截select部分
@Intercepts({@Signature(type=Executor.class,method="query",args={ MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class })})
public class PaginationInterceptor implements Interceptor{
	
	private final static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);   
	
	Dialect dialect = new MySql5Dialect();
	 
	public Object intercept(Invocation invocation) throws Throwable {
		//分页参数--上下文传参
		Page page = null;
		PageContext context=PageContext.getContext();
		if(!context.isPagination())	{
			return invocation.proceed();
		}
		
		MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];		
		Object parameter = invocation.getArgs()[1]; 
		BoundSql boundSql = mappedStatement.getBoundSql(parameter); 
		if(boundSql==null || boundSql.getSql()==null || "".equals(boundSql.getSql()))
			return null;
		
		String originalSql = boundSql.getSql().trim();
		RowBounds rowBounds = (RowBounds)invocation.getArgs()[2];
		Object parameterObject = boundSql.getParameterObject();
		
		
		//map传参每次都将currentPage重置,先判读map再判断context
		if(parameterObject!=null)
			page = (Page)ReflectHelper.isPage(parameterObject,"page");
		
		//分页参数--context参数里的Page传参
		if(page==null && context.isPagination()==true)
		{
			page = context;
		}
		
		//后面用到了context的东东
		if(page!=null && page.isPagination()==true) 			
		{
			int totpage=page.getOriginalTotalRows();	
			//得到总记录数
			if (totpage==0)
			{
				//originalSql = originalSql.toLowerCase();
				String lowerCaseSql = originalSql.toLowerCase();
				StringBuffer countSql = null;
				if(lowerCaseSql.contains("group by") || lowerCaseSql.contains("distinct")) {
					countSql = new StringBuffer(originalSql.length()+30 );
					countSql.append("select count(*) from (");
					countSql.append(originalSql);
					countSql.append(") as xxx");
				} else {
					/*countSql  = new StringBuffer(originalSql.length()+15 );
					int start = lowerCaseSql.indexOf("select") + "select".length();
					int end = lowerCaseSql.indexOf(" from");//" from"带空格的
					//如果不存在from的话则不改变
					countSql.append(originalSql);
					if(end!=-1) {
						countSql.replace(start, end, " count(*) ");
					}else{
						end = lowerCaseSql.indexOf("\tfrom");//\t
						if(end!=-1){
							countSql.replace(start, end, " count(*) ");
						}
					}*/
					countSql = new StringBuffer(originalSql.length()+30 );
					countSql.append("select count(*) from (");
					countSql.append(originalSql);
					countSql.append(") as xxx");
				}
				Connection connection=mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection()  ;          
                String countSqlStr = countSql.toString();
                log.debug(countSqlStr);
				PreparedStatement countStmt = connection.prepareStatement(countSqlStr);  
                BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),countSql.toString(),boundSql.getParameterMappings(),parameterObject);  
                setParameters(countStmt,mappedStatement,countBS,parameterObject);  
                ResultSet rs = countStmt.executeQuery();  
                if (rs.next()) {
                	totpage = rs.getInt(1);  
                }  
                log.debug("totalPage {}", totpage);
                rs.close();  
                countStmt.close();  
                connection.close();
			}
		  
		    //分页计算
	        page.init(totpage,page.getPageSize(),page.getCurrentPage());
		  
			if(rowBounds == null || rowBounds == RowBounds.DEFAULT){
				rowBounds= new RowBounds(page.getPageSize()*(page.getCurrentPage()-1),page.getPageSize());
			}

			//分页查询 本地化对象 修改数据库注意修改实现
		    String pagesql=dialect.getLimitString(originalSql, rowBounds.getOffset(), rowBounds.getLimit());
		    String tempsql=SortUtil.getSortSQL(pagesql);
		    if(StringUtils.isNotEmpty(tempsql)){
		    	pagesql=tempsql;
		    }
		    invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);   
		    BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pagesql,boundSql.getParameterMappings(),boundSql.getParameterObject());   
		    MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));  
		    invocation.getArgs()[0]= newMs;
		}
			
			
		   return invocation.proceed();
		
	}
	public static class BoundSqlSqlSource implements SqlSource {  
        BoundSql boundSql;  
  
        public BoundSqlSqlSource(BoundSql boundSql) {  
            this.boundSql = boundSql;  
        }  
  
        public BoundSql getBoundSql(Object parameterObject) {  
            return boundSql;  
        }  
    }  
	public Object plugin(Object arg0) {
		 return Plugin.wrap(arg0, this);
	}
	public void setProperties(Properties arg0) {
	        
	}
    
    /** 
     * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler 
     * @param ps 
     * @param mappedStatement 
     * @param boundSql 
     * @param parameterObject 
     * @throws SQLException 
     */  
    private void setParameters(PreparedStatement ps,MappedStatement mappedStatement,BoundSql boundSql,Object parameterObject) throws SQLException {  
        ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());  
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();  
        if (parameterMappings != null) {
            Configuration configuration = mappedStatement.getConfiguration();  
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();  
            MetaObject metaObject = parameterObject == null ? null: configuration.newMetaObject(parameterObject);  
            for (int i = 0; i < parameterMappings.size(); i++) {  
                ParameterMapping parameterMapping = parameterMappings.get(i);  
                if (parameterMapping.getMode() != ParameterMode.OUT) {  
                    Object value;  
                    String propertyName = parameterMapping.getProperty();  
                    PropertyTokenizer prop = new PropertyTokenizer(propertyName);  
                    if (parameterObject == null) {  
                        value = null;  
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {  
                        value = parameterObject;  
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {  
                        value = boundSql.getAdditionalParameter(propertyName);  
                    } else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)&& boundSql.hasAdditionalParameter(prop.getName())) {  
                        value = boundSql.getAdditionalParameter(prop.getName());  
                        if (value != null) {  
                            value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));  
                        }
                    } else {
                        value = metaObject == null ? null : metaObject.getValue(propertyName);  
                    }
                    TypeHandler typeHandler = parameterMapping.getTypeHandler();  
                    if (typeHandler == null) {  
                        throw new ExecutorException("There was no TypeHandler found for parameter "+ propertyName + " of statement "+ mappedStatement.getId());  
                    }
                    typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());  
                }
            }
        }
    }  
    
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {   
    		Builder builder = new MappedStatement.Builder(ms.getConfiguration(),   
    		ms.getId(), newSqlSource, ms.getSqlCommandType());   
    		builder.resource(ms.getResource());   
    		builder.fetchSize(ms.getFetchSize());   
    		builder.statementType(ms.getStatementType());   
    		builder.keyGenerator(ms.getKeyGenerator()); 
    		//builder.keyProperty(ms.getKeyProperty());   
    		builder.timeout(ms.getTimeout());   
    		builder.parameterMap(ms.getParameterMap());   
    		builder.resultMaps(ms.getResultMaps());   
    		builder.cache(ms.getCache());   
    		MappedStatement newMs = builder.build();   
    		return newMs;   
    }

}