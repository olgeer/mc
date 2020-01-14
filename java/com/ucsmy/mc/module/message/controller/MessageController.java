package com.ucsmy.mc.module.message.controller;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucsmy.mc.module.message.entity.MessReceive;
import com.ucsmy.mc.module.message.mmapper.MessageMapper;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.MailUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.UUIDUtil;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;
import com.ucsmy.mc.util.session.SessionPropertyUtil;

import sun.misc.BASE64Decoder;

@Controller(value = "MessageController")
@RequestMapping("/message/messct")
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    
    public static class MsgType {
    	public static final String UNREAD = "1";
    	public static final String READ = "2";
    }

    @Autowired
    MessageMapper messageMapper;
    
    
    /**
     * 消息列表
     * @param request
     * @return
     * @Description: 消息列表列表页面的freemark
     */
    @RequestMapping("/toMessList")
    public String toMessList(Model model, HttpServletRequest request) {
        model.addAttribute("sts", request.getParameter("sts"));
        model.addAttribute("uuid", UUIDUtil.creatUUID());
        return "/message/message_list";
    }
    
    
   /**
    * 消息详情页面
    * @param model
    * @param request
    * @return
    */
    @RequestMapping("/toView")
    public String toView(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        List<Map<String, Object>> list = messageMapper.selectMessageList(map);
        
        if (list.size() >= 0) {
        	Map<String, Object> msg = list.get(0);
			model.addAttribute("msg", msg);
			
			/**
			 * 未读消息设置成已读
			 */
			if (MsgType.UNREAD.equals(msg.get("sts"))) {
				messageMapper.updateSts(id);
			}
        }
        
        return "/message/message_view";
    }
    
    
    
    /**
     * 跳转到消息配置路径
     * @param model
     * @param request
     * @return
     * @throws IOException 
     */
     @RequestMapping("/toMsgUrl")
     public void toMsgUrl(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
         String id = request.getParameter("id");
         Map<String, Object> msg = messageMapper.selectMessageJoinGlobObjById(id);
         String revUserId = (String) msg.get("rev_user_id");
         
         if (SessionPropertyUtil.getUsbaId().equals(revUserId)) {
         
	         /**
	          * 未读消息设置成已读
	          */
	         if (MsgType.UNREAD.equals(msg.get("sts"))) {
	        	 messageMapper.updateSts(id);
	         }
	         
	         String url = (String) msg.get("glob_detail_url");
	         String relationDomainId = (String) msg.get("relation_domain_id");
	         
	         response.sendRedirect(request.getContextPath() + "/" + url + relationDomainId);
         } else {
        	 response.sendRedirect(request.getContextPath());
         }
     }


    /**
     * 获取消息列表json
     * @return
     */
    @RequestMapping("/messageListView")
    @ResponseBody
    public Map<String, Object> messageListView(HttpServletRequest request, String sts) {

        //存储DataTable数据Map
        Map<String, Object> dataTableMap = new HashMap<String, Object>();
        //存储Request数据Map
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //查询过滤添加Map
        Map<String, Object> filterMap = new HashMap<String, Object>();
        parameterMap.putAll(DataTableUtil.getParameterMap());
        // DataTable操作类型
        //String sAction = (String) parameterMap.get(DTConstants.ACTION);
//        if (DTConstants.FILTER_ACTION.equals(sAction)) {
        System.out.println(" DTConstants.FILTER_ACTION.equals(sAction)  ");
        filterMap.putAll(parameterMap);
        filterMap.put("rev_user_id", SessionPropertyUtil.getUsbaId());
//        }
        // DataTable操作类型
        PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
        List<Map<String, Object>> list = messageMapper.selectMessageList(filterMap);
        DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
        dataTableMap.put(DTConstants.DATA, list);
        logger.debug(" messageListView JSON parms :  " + JSON.toJSONString(parameterMap));
        return dataTableMap;
    }


    /**
     * 接收服务那边传过来的消息
     *
     * @param request
     * @return
     */
    @RequestMapping("/reviceMes")
    @ResponseBody
    public String reviceMes(HttpServletRequest request) {
        String data = request.getParameter("data");
        //System.out.println(data);
        
        try {
        	if (StringUtils.isBlank(data)) {
        		return "data参数不能为空";
        	}
        	
            data = URLDecoder.decode(data, "UTF-8");
            //String data = "ZXlKa1lYUmhJanBiZXlKa1pYUmhhV3dpT2lMbG41L2xrSTNsaUxEbW5KL21qNURwaHBJc0lPV2ZuK1dRalZzeVhTRGx0N0xvdjRmbW5KL3Z2SXpvcnI3bHJwcm5tb1RvdjRmbW5KL21sN2JwbDdUa3VMb3lNREUzTFRBeExUQXhMT2l2dCtXUGl1YVh0dVdraE9lUWh1KzhnU0lzSW5KbGJHRjBhVzl1WDJSdmJXRnBiaUk2SWpFaUxDSnlaV3hoZEdsdmJsOWtiMjFoYVc1ZmFXUWlPaUl5SWl3aWMyVnVaRjkxYzJWeVgybGtJam9pTWlJc0luTmxibVIwYVcxbElqb2lNakF4Tnkwd01pMHdPRlF4TlRveE56b3pPUzQxTlRnaUxDSjBhWFJzWlNJNkl1V2ZuK1dRamVXSXNPYWNuK2FQa09tR2tpSjlYU3dpYlhOMGFYQWlPaUl6SWl3aWNtOXNaWE1pT2xzaU1qVWlMQ0l5TlNKZExDSjBaV3R2YmlJNklqRXlNelExTmlJc0luVnpaWEpwWkhNaU9sc2lORFlpTENJME5pSmRmUT09";

            //解析组ID
        	//String JsomMessage = decodeStr(data);
            String JsomMessage = Base64ToStr(Base64ToStr(data));
            JSONObject jsonObject = null;
            try {
            	jsonObject =JSON.parseObject(JsomMessage);
            } catch(Exception ex) {
            	ex.printStackTrace();
            	return "json 数据解析错误: " + ex.getMessage();
            }
            
            
            /**
             * 站内用户
             */
            Set<String> userIds = new HashSet<>();
            JSONObject smsUser = (JSONObject) jsonObject.get("sms");
            if (smsUser != null) {
            	JSONArray smsRoles = (JSONArray)smsUser.get("roles");
            	JSONArray smsuserIds = (JSONArray)smsUser.get("userids");
            	
            	if (smsRoles != null) {
	            	Map<String, Object> smsParams = new HashMap<>();
	            	List<String> arr = new ArrayList<>();
	            	for (int i = 0; i < smsRoles.size(); i++) {
	                 arr.add(smsRoles.get(i).toString());
	            	}
	            	smsParams.put("ids", arr);
	            	List<Map> list = messageMapper.selectUsersByRoles(smsParams);
	            	for (Map<String, Object> o: list) {
	            		userIds.add((String)o.get("usba_id"));
	            	}
            	}
            	
            	if (smsuserIds != null) {
	            	for (int i = 0; i < smsuserIds.size(); i++) {
	            		userIds.add(smsuserIds.get(i).toString());
	               	}
            	}
            }
            
            
            /**
             * 发送邮件的用户
             */
            Map<String, String> userIdToMail = new HashMap<>();
            JSONObject mailUser = (JSONObject) jsonObject.get("mail");
            if (mailUser != null) {
            	JSONArray mailRoles = (JSONArray)mailUser.get("roles");
            	JSONArray mailuserIds = (JSONArray)mailUser.get("userids");
            	if (mailRoles != null || mailuserIds != null) {
            		List<String> mailRoleArr = new ArrayList<>();
	            	for (int i = 0; i < mailRoles.size(); i++) {
	            		mailRoleArr.add(mailRoles.get(i).toString());
	            	}
	            	
	            	List<String> mailUserArr = new ArrayList<>();
	            	for (int i = 0; i < mailuserIds.size(); i++) {
	            		mailUserArr.add(mailuserIds.get(i).toString());
	            	}
            		
	            	Map<String, Object> mailParams = new HashMap<>();
	            	mailParams.put("roleIds", mailRoleArr);
	            	mailParams.put("userIds", mailUserArr);
	            	List<Map<String, Object>> rs = messageMapper.selectUserMailByRolesAndUserIds(mailParams);
	            	for (Map<String, Object> o: rs) {
	            		String mail = (String) o.get("usba_mail");
	            		if (StringUtils.isNotBlank(mail)) {
	            			userIdToMail.put((String)o.get("usba_id"), mail);
	            		}
	            	}
            	}
            }
            
            
            JSONArray arraydata = (JSONArray) jsonObject.get("data");
            String mstip = jsonObject.getString("mstip");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //当前消息
            for (int j = 0; j < arraydata.size(); j++) {
                JSONObject o = (JSONObject) arraydata.get(j);
                MessReceive r = new MessReceive();
                r.setId(UUIDUtil.creatUUID());
                r.setTitle(o.getString("title"));
                r.setDetail(o.getString("detail"));
                r.setRelation_domain_id(o.getString("relation_domain_id"));
                r.setSend_user_id(o.getString("send_user_id"));
                r.setSts(MsgType.UNREAD);
                r.setMstip(Integer.valueOf(mstip));
                try {
                    Date d = sdf.parse(o.getString("sendtime"));
                    r.setSend_time(d);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                r.setRelation_domain(o.getString("relation_domain"));
                
                
                for (String userId: userIds) {
                	r.setRev_user_id(userId);
                	messageMapper.InsertMessage(r);
                }
                
                for (String mail: userIdToMail.values()) {
                	MailUtil.sendMail(mail, r.getTitle(), r.getDetail());
                }
            }
            
            return "1";
	        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "2";
    }
    

    public static String Base64ToStr(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static String decodeStr(String encodeStr) {
        Base64 base64 = new Base64();
        String a = "";
        try {
            a = new String(base64.decode(base64.decode(encodeStr.getBytes("UTF-8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return a;
    }
    
    
    /**
     * 获取右上角的代办
     *
     * @return
     */
    @RequestMapping("/messageToDoList")
    @ResponseBody
    public Map<String, Object> messageToDoList(HttpServletRequest request) {

        //存储DataTable数据Map
        Map<String, Object> dataTableMap = new HashMap<String, Object>();
        //存储Request数据Map
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //查询过滤添加Map
        Map<String, Object> filterMap = new HashMap<String, Object>();
        parameterMap.putAll(DataTableUtil.getParameterMap());
        // DataTable操作类型
        PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
        String usbaId = SessionPropertyUtil.getUsbaId();
        Integer count = messageMapper.countTodo(usbaId);//条数
        dataTableMap.put("todo_count", count);
        filterMap.put("rev_user_id", usbaId); //用户放入
        List<Map<String, Object>> list = messageMapper.selectTop3MessageList(filterMap);
        DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
        dataTableMap.put(DTConstants.DATA, list);
        //logger.debug(" messageToDoList JSON parms :  " + JSON.toJSONString(parameterMap));
        //logger.debug(" messageToDoList usbaId parms :  " + usbaId);
        return dataTableMap;
    }
    
}
