package com.ucsmy.mc.util.log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.bg.api.RedisUtil;
import com.ucsmy.mc.common.entity.SystemLog;
import com.ucsmy.mc.common.entity.SystemLogExtend;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.DateUtil;
import com.ucsmy.mc.util.SerializeUtil;
import com.ucsmy.mc.util.StringUtils;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.Constants;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.constants.LogConstants;
import com.ucsmy.mc.util.session.SessionPropertyUtil;

import redis.clients.jedis.Jedis;

/**
 * Description:controller类日志记录切面 Time:2015年12月7日下午1:19:41
 * 
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
@Component
public class ControllerLogAspect {
	private Logger logger = LoggerFactory.getLogger(ControllerLogAspect.class);

	private  LogConstants[] allLogConstants = LogConstants.values();

	public void doBefore(JoinPoint joinPoint) {
		SystemLog systemLog = new SystemLog();
	try {
		//获取连接点所在的目标对象类权限定名
		String targetName = joinPoint.getTarget().getClass().getName();
		//获取连接点的方法签名对象
		String methodName = joinPoint.getSignature().getName();
		//获取连接点方法运行时传入参数列表
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
					getMethodType(systemLog,methodName);
					systemLog.setSyloId(UUIDUtil.creatUUID());
					systemLog.setSyloCreateDate(DateUtil.getCurrentTime());
					systemLog.setSyloIp(SessionPropertyUtil.getIP());
					systemLog.setSyloRoleName(SessionPropertyUtil.getRoleName());
					systemLog.setSyloMethodName(
							targetName + "." + joinPoint.getSignature().getName() + "()");
							if (SessionPropertyUtil.getUserAccount() != null) {
								systemLog.setSyloUsroName(SessionPropertyUtil.getUserAccount());
								//管理员 类型日志
								systemLog.setSyloOwn(Constants.SIGN_0);
							} else {
								systemLog.setSyloUsroName(SessionPropertyUtil.getAdmiAccount());
								//管理员 类型日志
								systemLog.setSyloOwn(Constants.SIGN_1);
							}
							if (SessionPropertyUtil.getUsroId() != null) {
								systemLog.setSyloUsroId(SessionPropertyUtil.getUsroId());
							} else {
								systemLog.setSyloUsroId(SessionPropertyUtil.getAdroId());
							}
							systemLog.setSyloRoleName(SessionPropertyUtil.getRoleName());
							if(methodName.endsWith("Datatable")) {
								getParameters(systemLog, joinPoint, true);
							} else {
								getParameters(systemLog, joinPoint,false);
							}
							//存日志全局日志
							RedisUtil.lpush("systemLog".getBytes(),SerializeUtil.serialize(systemLog));
							//存全局日志的扩展
							SystemLogExtend systemLogExtend=new SystemLogExtend();
							systemLogExtend.setMethodName(systemLog.getSyloMethodName());
							systemLogExtend.setSleId(UUIDUtil.creatUUID());
							systemLogExtend.setClassName(targetName);
							hmset(systemLog,systemLogExtend);
						
			//getAnnotationInfo(joinPoint, systemLog);
	} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 将全局日志的扩张写进redis中,使用map,确保唯一性
	 * @param systemLog
	 * @param systemLogExtend
	 */
	public void hmset(SystemLog systemLog,SystemLogExtend systemLogExtend){
		Jedis jedis=null;
		try{
			jedis=	RedisUtil.getJedis();
			Map<String,String> systemLogExtendMap =  new HashMap<String,String>(); 
			systemLogExtendMap.put(systemLog.getSyloMethodName(), JSONObject.toJSONString(systemLogExtend));
			jedis.hmset("systemLogExtend",systemLogExtendMap);
			}catch(Exception e){
				
			}finally{
				RedisUtil.returnResource(jedis);
			}
		
		
	}

	/**
	 * @Description：获取注解中对方法的描述信息 用于Controller层注解
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
/*	public  void getAnnotationInfo(JoinPoint joinPoint, SystemLog systemLog) throws Exception {
		//获取连接点所在的目标对象
		String targetName = joinPoint.getTarget().getClass().getName();
		//获取连接点的方法签名对象
		String methodName = joinPoint.getSignature().getName();
		//获取连接点方法运行时的入参列表
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);

		ClassLogAnnotation classLogAnnotation = targetClass.getAnnotation(ClassLogAnnotation.class);
		// 获取Class注解信息
		if (classLogAnnotation != null) {
			systemLog.setSyloModuleDescription(classLogAnnotation.description());
			systemLog.setSyloModuleName(classLogAnnotation.moduleName());
		}else{
			return;
		}
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					MethodLogAnnotation methodLogAnnotation = method.getAnnotation(MethodLogAnnotation.class);
					if(methodLogAnnotation!=null){
						systemLog.setSyloCreateDate(DateUtil.getCurrentTime());
						systemLog.setSyloIp(SessionPropertyUtil.getIP());
						systemLog.setSyloRoleName(SessionPropertyUtil.getRoleName());
						systemLog.setSyloMethodName(
								joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
						if (SessionPropertyUtil.getUserAccount() != null) {
							systemLog.setSyloUsroName(SessionPropertyUtil.getUserAccount());
							//管理员 类型日志
							systemLog.setSyloOwn(Constants.SIGN_0);
						} else {
							systemLog.setSyloUsroName(SessionPropertyUtil.getAdmiAccount());
							//管理员 类型日志
							systemLog.setSyloOwn(Constants.SIGN_1);
						}
						if (SessionPropertyUtil.getUsroId() != null) {
							systemLog.setSyloUsroId(SessionPropertyUtil.getUsroId());
						} else {
							systemLog.setSyloUsroId(SessionPropertyUtil.getAdroId());
						}
						systemLog.setSyloRoleName(SessionPropertyUtil.getRoleName());
						systemLog.setSyloMethodDescription(methodLogAnnotation.description());
						systemLog.setSyloMethodType(methodLogAnnotation.type().getZhName());
						
						if(methodName.endsWith("Datatable")) {
							getParams(systemLog, joinPoint,methodLogAnnotation, true);
						} else {
							getParams(systemLog, joinPoint,methodLogAnnotation,false);
						}
					systemLogMapper.insertSelective(systemLog);
						break;
					}else{
						return;
					}
				}
			}
		}
	}*/
	/**
	 * @Description: 通过方法名前缀，判断操作类型
	 * @param systemLog
	 * @param methodName
	 */
	public  void getMethodType(SystemLog systemLog, String methodName) {
		for (LogConstants LogConstants : allLogConstants) {
			if (methodName.startsWith(LogConstants.name())) {
				systemLog.setSyloMethodType(LogConstants.getZhName());
				break;
			}
		}
	}

	/**
	 * @Description: 判断Datatable操作类型
	 * @param sGroupActionName
	 * @return
	 */
	public  String getDatatableMethodType(String sGroupActionName) {
		for (LogConstants logConstants : allLogConstants) {
			if (sGroupActionName.equals(logConstants.name())) {
				return logConstants.getZhName();
			}
		}
		return "";
	}

	/**
	  * @Description: 根据ModuleName参数获取模块中文名称
	  * @param moduleName
	  * @return
	  */
	/*public  String getModuleZhName(String moduleName) {
		for (ModuleConstants moduleConstants : allModuleConstants) {
			if (moduleName.equals(moduleConstants.name())) {
				return moduleConstants.getZhName();
			}
		}
		return "";
	}*/
	/**
	 * 获取请求参数,区别是没有methodLogAnnotation注解
	 * @param systemLog
	 * @param joinPoint
	 * @param isDataTable
	 */
	public  void getParameters(SystemLog systemLog, JoinPoint joinPoint,boolean isDataTable){
		StringBuilder stringBuilder = new StringBuilder();
		Object[] objects = joinPoint.getArgs();
		if (isDataTable) {
			stringBuilder.append(JSON.toJSON(DataTableUtil.getParameterMap()));
			String sAction = (String) DataTableUtil.getParameterMap().get(DTConstants.ACTION);
			String sGroupActionName = (String) DataTableUtil.getParameterMap().get(DTConstants.GROUP_ACTION_NAME);
			if (StringUtils.equals(DTConstants.GROUP_ACTION, sAction)) {
				systemLog.setSyloMethodType(getDatatableMethodType(sGroupActionName));
			} else {
				systemLog.setSyloMethodType(LogConstants.query.getZhName());
			}
		} else {
			if (objects != null && objects.length > 0) {
				for (Object object : objects) {
					if (object instanceof HttpServletRequest) {
						Map<String, String[]> requestParams=((ServletRequest) object).getParameterMap();
						//判断方法是否为公共的,如果为公共的，则parameter不为空
						stringBuilder.append(JSON.toJSON(requestParams));
					} 
				}
			}
		}
		systemLog.setSyloParams(stringBuilder.toString());
	}
	/**
	 * @Description: 获取请求参数信息
	 * @param systemLog
	 * @param joinPoint
	 */
	/*public  void getParams(SystemLog systemLog, JoinPoint joinPoint,MethodLogAnnotation methodLogAnnotation, boolean isDataTable) {
		StringBuilder stringBuilder = new StringBuilder();
		Object[] objects = joinPoint.getArgs();
		if (isDataTable) {
			stringBuilder.append(JSON.toJSON(DataTableUtil.getParameterMap()));
			String sAction = (String) DataTableUtil.getParameterMap().get(DTConstants.ACTION);
			String sGroupActionName = (String) DataTableUtil.getParameterMap().get(DTConstants.GROUP_ACTION_NAME);
			if (StringUtils.equals(DTConstants.GROUP_ACTION, sAction)) {
				systemLog.setSyloMethodType(getDatatableMethodType(sGroupActionName));
			} else {
				systemLog.setSyloMethodType(LogConstants.query.getZhName());
			}
		} else {
			if (objects != null && objects.length > 0) {
				for (Object object : objects) {
					if (object instanceof HttpServletRequest) {
						Map<String, String[]> requestParams=((ServletRequest) object).getParameterMap();
						//判断方法是否为公共的,如果为公共的，则parameter不为空
						if(StringUtils.isNotBlank(methodLogAnnotation.parameter())){
							String[] moduleName=requestParams.get(methodLogAnnotation.parameter().trim());
							if(moduleName!=null&&moduleName.length>0){
								//更新调用该方法的模块名称日志
								systemLog.setSyloModuleName(getModuleZhName(moduleName[0]));
							}
						}
						stringBuilder.append(JSON.toJSON(requestParams));
					} 
				}
			}
		}
		systemLog.setSyloParams(stringBuilder.toString());
	}*/

}