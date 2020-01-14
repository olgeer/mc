/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.common.exmapper;

import java.util.List;

import com.ucsmy.mc.common.entity.UserBasic;




/**
 * Description:用户扩展Mapper
 * Time:2015年12月4日下午3:04:50
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public interface ExUserBasicMapper {
	
	
    /**
     * 查询用户实体的基本信息，不全.
     * @param username	：String类型，代表用户用户名
     * @return 返回用户基本信息实体
     */
    UserBasic selectUserBasicByUserAccount(String username);
    
    
    /**
     * 根据用户Id类别,删除对应的用户实体.
     * @param usbaIds 用户Id列表.
     * @return 受影响的行数.
     */
    int batchDeleteUserBasicByUsbaIds(List<String> usbaIds);
    
    /**
     * 根据用户名,查询出该用户实体.
     * @param username.
     * @return 用户实体.
     */
    UserBasic selectUserBasicByUsbaAccount(String username);

    /**
     * 通过id串查找用户列表
     * @param usba_ids
     * @return
     */
    List<UserBasic> selectUserBasicBySomeId(Object param);
    
    
    /**
     * 通过userId和token更新登陆状态
     * @return
     */
    int updateCredentialExpiredByUsbaIdAndToken(UserBasic record);
}
