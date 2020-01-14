package com.ucsmy.mc.util.log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ucsmy.mc.bg.api.RedisUtil;
import com.ucsmy.mc.common.entity.SystemLog;
import com.ucsmy.mc.common.entity.SystemLogExtend;
import com.ucsmy.mc.common.service.SystemLogExtendService;
import com.ucsmy.mc.common.service.SystemLogService;
import com.ucsmy.mc.util.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * 存取全局日志的控制器
 * @author ucs_xuxiling
 *
 */
@Component
public class SystemLogRedisController {
	@Resource(name="systemLogServiceImpl")
	private  SystemLogService systemLogService;
	@Resource(name="systemLogExtendServiceImpl")
	private SystemLogExtendService systemLogExtendService;
	
	
	public static List<String> hmget(String key,String methodName){
		Jedis jedis=null;
		 List<String> listSystemLogExtend=null;
		try{
			jedis=RedisUtil.getJedis();
			listSystemLogExtend=jedis.hmget(key,methodName);
			 if(listSystemLogExtend.size()==0){
					return null;
				}else{
				
				return listSystemLogExtend;
				}
		}catch(Exception e){
			
		}finally {
			RedisUtil.close(jedis);
		}
		return listSystemLogExtend; 
	}
	public List<SystemLog> getBytes(){
		
        List<SystemLog>systemLogs=new ArrayList<>();
       for(int i=0;i<100;i++){
         byte[] bytes = RedisUtil.lpop("systemLog".getBytes());
     	   SystemLog systemLog=(SystemLog)SerializeUtil.unserialize(bytes);
     	   if(systemLog==null){
     		   break;
     	   }else{
           systemLogs.add(systemLog);
     	   }
           
           
          
       }
       
        return systemLogs;
	}
	public void getSystemLogAndInsertSystemLogExtend(){
	    //取出全局日志
		 List<SystemLog> systemLoglist =getBytes(); 
		 if(!systemLoglist.isEmpty()){
		  for (SystemLog systemLog : systemLoglist) {
			  if(systemLog!=null){
				  systemLogService.insert(systemLog);
			//取出全局日志扩展    
			  List<String> listSystemLogExtends= hmget("systemLogExtend",systemLog.getSyloMethodName());
			  //JSONArray SystemLogExtendArray= JSONArray.fromObject(listSystemLogExtends);
			  List<SystemLogExtend> systemLogExtendList =  Collections.emptyList();//(List)JSONArray.toCollection(SystemLogExtendArray,SystemLogExtend.class);
			  //用于删除已经存在的数据
			  Iterator<SystemLogExtend> iter = systemLogExtendList.iterator();  
			  //处理逻辑如果数据存在
				List<SystemLogExtend> sysExLists=	systemLogExtendService.getAllSystemLogExtend();
			   while(iter.hasNext()){  
			   SystemLogExtend s = iter.next(); 
			    if(sysExLists.size()>0){
			    for (SystemLogExtend systemLogEx : sysExLists) {
			    	//添加方法描述和model描述
			    	if(systemLogEx.getMethodName().equals(systemLog.getSyloMethodName())){
			    		systemLog.setSyloMethodDescription(systemLogEx.getMethodDescription());
			    		systemLog.setSyloMethodName(systemLogEx.getClassDescription());
			    	}
			    	//删除后来添加(已经存在)的数据
					if(s.getMethodName().equals(systemLogEx.getMethodName())){  
							          iter.remove();  
					 }  
				   }
			    }
			   }	
			   //批量插入全局日志扩展操作
			if(systemLogExtendList.size()>0){
			  systemLogExtendService.bathInsertSystemLogExtend(systemLogExtendList);
			  }
					
			}
		
		  }
		  //批量插入全局日志
		  Iterator<SystemLog> SysLogIter = systemLoglist.iterator();
		  while(SysLogIter.hasNext()){  
			   SystemLog sys = SysLogIter.next(); 
			   if(sys!=null&&sys.getSyloUsroName()==null){
				   SysLogIter.remove();
			   }
			}
		  if(!systemLoglist.isEmpty()){
		 //systemLogService.bathInsertSystemLog(systemLoglist);
		  }
		  
		}else{
			System.out.println("没有日志");
		}
	
	
	}
}
