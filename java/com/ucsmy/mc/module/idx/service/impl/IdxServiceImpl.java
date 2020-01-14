/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.idx.service.impl;

import com.ucsmy.mc.common.entity.Department;
import com.ucsmy.mc.common.entity.Mctype;
import com.ucsmy.mc.common.exmapper.ExDepartmentMapper;
import com.ucsmy.mc.common.mapper.DepartmentMapper;
import com.ucsmy.mc.module.admin.mmapper.AdminDepartmentMapper;
import com.ucsmy.mc.module.idx.mmapper.IdxMapper;
import com.ucsmy.mc.module.idx.service.IdxService;
import com.ucsmy.mc.util.DepartmentTreeUtil;
import com.ucsmy.mc.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description:部门管理
 * Time:2015年12月4日下午12:23:57
 *
 * @author chenchengteng
 * @version 1.0
 * @since 1.0
 */
@Service
public class IdxServiceImpl implements IdxService {

    @Resource
    private IdxMapper idxMapper;

    @Override
    public List<Map<String, Object>> getIdxList(Map<String, Object> map) {
        return idxMapper.getIdxList(map);
    }

    @Override
    public List<Mctype> getMctypeList(Map<String, Object> map) {

        return null;
    }

    @Override
    public void addIdx(Mctype mctype) {
        idxMapper.insertIdxSelective(mctype);
    }

    @Override
    public Mctype getIdxById(String id) {
        return idxMapper.selectIdxByPrimaryKey(id);
    }

    @Override
    public void updateIdx(Mctype mctype) {
        idxMapper.updateIdx(mctype);
    }

    @Override
    public int batchDeleteIdx(List<String> depaIds) {
        idxMapper.batchDeleteIdxByIds(depaIds);
        return 0;
    }
}
