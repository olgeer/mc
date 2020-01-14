/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.idx.controller;

import com.ucsmy.mc.common.entity.Mctype;
import com.ucsmy.mc.module.idx.service.IdxService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.StringUtils;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/idx")
public class Kc_IdxController {

    @Resource
    public IdxService idxService;

    @RequestMapping("/toIdxList")
    public String toAddOrEidtDepartment(Model model, HttpServletRequest request) {
        return "admin/idx/idx_list";
    }

    @RequestMapping("/getIdxList")
    @ResponseBody
    public Map<String, Object> getIdxList() {

        //存储DataTable数据Map
        Map<String, Object> dataTableMap = new HashMap<String, Object>();
        //存储Request数据Map
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        //查询过滤添加Map
        Map<String, Object> filterMap = new HashMap<String, Object>();
        parameterMap.putAll(DataTableUtil.getParameterMap());
        // DataTable操作类型
        String sAction = (String) parameterMap.get(DTConstants.ACTION);
        if (DTConstants.FILTER_ACTION.equals(sAction)) {

            filterMap.putAll(parameterMap);
        } else if (DTConstants.GROUP_ACTION.equals(sAction)) {
            List<String> depaIds = DataTableUtil.getDataTableListIds(parameterMap);

            if (depaIds != null) {
                String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
                if (sGroupActionName.equals("delete")) {
                    int upNum = idxService.batchDeleteIdx(depaIds);
                    if(upNum > 0) {
                        dataTableMap.put("sStatus", "OK");
                        dataTableMap.put("sMessage", upNum + "条记录已被删除");
                    }
                }
            }
        }
        PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
        List<Map<String, Object>> list = idxService.getIdxList(filterMap);
        DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
        dataTableMap.put(DTConstants.DATA, list);
        return dataTableMap;
    }


    /**
     * @Title: toAddOrEidtDepartment
     * @Description: TODO 新建或者编辑部门界面
     * @return: String 新建或者编辑部门界面路径
     */
    @RequestMapping("/toAddOrEidtIdx")
    public String toAddOrEidtIdx(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)) {
            Mctype mctype = idxService.getIdxById(id);
            model.addAttribute("mctype", mctype);
        }
        return "admin/idx/idx_add_edit";

    }


    /**
     * @Title: saveDepartment
     * @Description: TODO 保存新增或者编辑操作数据
     * @return: String 操作结果
     */
    @RequestMapping("/saveIdx")
    @ResponseBody
    public String saveDepartment(HttpServletRequest request) {
        Mctype mctype = new Mctype();
        String result;
        String iname = request.getParameter("iname").trim();


        String id = request.getParameter("id");
        if (StringUtils.isNotBlank(id)){
            id.trim();
        }
        String imark = request.getParameter("imark");
        String status = request.getParameter("status").trim();
        String ilevel = request.getParameter("ilevel").trim();
        //VO传值
        mctype.setIlevel(ilevel);
        mctype.setImark(imark);
        mctype.setIname(iname);
        mctype.setStatus(status);

        if (StringUtils.isNotBlank(id)) {
            //更新指标信息
            mctype.setId(id);
            idxService.updateIdx(mctype);
        } else {
            //保存

            idxService.addIdx(mctype);  //保存指标信息
        }
        return "success";

    }


}
