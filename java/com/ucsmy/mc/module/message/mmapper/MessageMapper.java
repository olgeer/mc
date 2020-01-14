package com.ucsmy.mc.module.message.mmapper;

import com.ucsmy.mc.module.message.entity.MessReceive;

import java.util.List;
import java.util.Map;

/**
 * Created by jack on 2016/12/7.
 */
public interface MessageMapper {

    /**
     * 对应条件的消息列表
     * @param map
     * @return
     */
    List<Map<String, Object>> selectMessageList(Map<String, Object> map);

    /**
     * 统计未读消息条数
     * @param userid
     * @return
     */
    int countTodo(String userid);

    /**
     * 显示三条消息
     * @param map
     * @return
     */
    List<Map<String, Object>> selectTop3MessageList(Map<String, Object> map);


    /**
     *
     * @param
     * @return
     */
    List<Map>  selectUsersByRoles(Map  arr);
    
    
    
    /**
     * 根据userId,role查询用户邮箱
     * @param map
     * @return
     */
    List<Map<String, Object>> selectUserMailByRolesAndUserIds(Map<String, Object> map);
    
    
    /**
     * 根据userId查询用户邮箱
     * @param map
     * @return
     */
    String selectUserMailUserId(String userId);

    
    /**
     * 批量消息.
     * @param map
     * @return
     */
    int batchInsertMessage(Map<String, Object> map);
    
    
    //    插入消息
    int InsertMessage(MessReceive ms);
    
    
    /**
     * 已读消息
     * @param id
     * @return
     */
    int updateSts(String id);
    
    
    
    /**
     * 根据消息id查询消息内容和消息跳转路径配置
     * @param id
     * @return
     */
    Map<String, Object> selectMessageJoinGlobObjById(String id);

}
