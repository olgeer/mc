package com.ucsmy.mc.module.common.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * 
 * 
 * Description:MyBatis Sql 工具类
 * 
 * Time:2015年12月23日下午3:51:40
 * @version 1.0
 * @since 1.0
 * @author yangdd
 */
public class MyBatisSqlUtils {

	/**
	 * 运行期获取MyBatis执行的SQL及参数
	 * 
	 * @param id
	 *            Mapper xml 文件里的select Id
	 * @param parameterMap
	 *            参数
	 * @param sqlSessionFactory
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static MyBatisSql getMyBatisSql(String id, Map<String, Object> parameterMap,
			SqlSessionFactory sqlSessionFactory) {
		MyBatisSql ibatisSql = new MyBatisSql();
		MappedStatement ms = sqlSessionFactory.getConfiguration().getMappedStatement(id);
		BoundSql boundSql = ms.getBoundSql(parameterMap);
		ibatisSql.setSql(boundSql.getSql());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

		if (parameterMappings != null) {
			Object[] parameterArray = new Object[parameterMappings.size()];
			ParameterMapping parameterMapping = null;
			Object value = null;
			Object parameterObject = null;
			MetaObject metaObject = null;
			PropertyTokenizer prop = null;
			String propertyName = null;
			String[] names = null;
			for (int i = 0; i < parameterMappings.size(); i++) {
				parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					propertyName = parameterMapping.getProperty();
					names = propertyName.split("\\.");
					if (propertyName.indexOf(".") != -1 && names.length == 2) {
						parameterObject = parameterMap.get(names[0]);
						propertyName = names[1];
					} else if (propertyName.indexOf(".") != -1 && names.length == 3) {
						parameterObject = parameterMap.get(names[0]); // map
						if (parameterObject instanceof Map) {
							parameterObject = ((Map) parameterObject).get(names[1]);
						}
						propertyName = names[2];
					} else {
						parameterObject = parameterMap == null ? null :parameterMap.get(propertyName);
					}
					// metaObject = parameterMap == null ? null : MetaObject.forObject(parameterObject);
					metaObject = parameterMap == null ? null : MetaObject.forObject(parameterObject, new DefaultObjectFactory(),
									new DefaultObjectWrapperFactory());
					prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (ms.getConfiguration().getTypeHandlerRegistry()
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
						if (parameterObject.getClass().toString().equals("class java.lang.String")){
							value = "'"+parameterObject+"'";
						}
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = MetaObject
									.forObject(value, new DefaultObjectFactory(), new DefaultObjectWrapperFactory())
									.getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					parameterArray[i] = value;
				}
			}
			ibatisSql.setParameters(parameterArray);
		}
		return ibatisSql;
	}

}
