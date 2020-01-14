/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
 

package com.ucsmy.mc.common.service;


import java.util.List;
import java.util.Map;

import com.ucsmy.mc.common.entity.Dictionary;

/**
 * Description:
 * 
 * Time:2015年12月2日上午10:12:20
 * @version 1.0
 * @since 1.0
 * @author lidongxu
 */

public interface CommonDictionaryService {
	/**
	 * 
	  * @Description: 根据map查询（map：严格按照Dictionary属性对应）
	  * @return List<Dictionary>   
	  * @throws
	 */
    public List<Dictionary> getValueByMap(Map<String, Object> map);
    /**
     * 
      * @Description: 根据字典属性Kind进行该字典查询
      * @return List<Dictionary>   
      * @throws
     */
    public List<Dictionary> getDictionaryByKind(String kind);
    /**
     * 
      * @Description: 根据Kind和Value获取code值
      * @return String   
      * @throws
     */
    public String getCodeByKindAndValue(String kind ,String value);
    /**
     * 
      * @Description: 根据Kind和Code获取Value值
      * @return String   
      * @throws
     */
    public String getValueByKindAndCode(String kind ,String code);
    /**
     * 
      * @Description: 主键查询
      * @return Dictionary   
      * @throws
     */
    public Dictionary getDictionaryById(int id);
    /**
     * 
      * @Description: 更新实体
      * @return int   
      * @throws
     */
    public int updateDictionary(Dictionary dic);
    /**
     * 插入实体
      * @Description: TODO
      * @return int   
      * @throws
     */
    public int insertDictionary(Dictionary dic);
    /**
     * 
      * @Description: 删除实体（根据实体主键）
      * @return int   
      * @throws
     */
    public int deleteDictionary(Dictionary dic);
}
