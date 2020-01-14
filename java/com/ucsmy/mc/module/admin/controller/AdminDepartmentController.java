/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.module.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.common.entity.Department;
import com.ucsmy.mc.common.entity.Grade;
import com.ucsmy.mc.common.exmapper.ExDepartmentMapper;
import com.ucsmy.mc.module.admin.service.AdminDepartmentService;
import com.ucsmy.mc.module.admin.service.AdminGradeService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;


/** 
 * @ClassName: AdminDepartmentController 
 * @Description: 部门管理
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午2:27:52
 * @version: V1.0     
 */
@Controller
@RequestMapping("/admin/department")
public class AdminDepartmentController {
	@Resource
	private ExDepartmentMapper exDepartmentMapper;
	
	@Resource(name="adminGradeServiceImpl")
	private AdminGradeService adminGradeService;
	
	@Resource(name="adminDepartmentServiceImpl")
	private AdminDepartmentService adminDepartmentService;
	
	@RequestMapping("/toDepartmentList")
	public String toDepartmentList(Model model,HttpServletRequest request){
		return "admin/department/department_list";
	}
	
	/** 
	 * @Title: toAddOrEidtDepartment 
	 * @Description: TODO 新建或者编辑部门界面
	 * @return: String 新建或者编辑部门界面路径
	 */
	@RequestMapping("/toAddOrEidtDepartment")
	public String toAddOrEidtDepartment(Model model,HttpServletRequest request) {
	
		Map<String, Object> map=new HashMap<String, Object>();
		List<Grade> gradeList =adminGradeService.getGradeList(map,(byte)0);
		String depaId=request.getParameter("depaId");
		if(StringUtils.isNotBlank(depaId)){
			Department department=adminDepartmentService.getDepartmentByDepaId(depaId);
			model.addAttribute("department",department);
		}
		List<Map<String, Object>> departmentMenuList =adminDepartmentService.getDepartmentMenu();
		model.addAttribute("gradeList",gradeList);
		model.addAttribute("departmentMenuList",departmentMenuList);
		return "admin/department/department_add_edit";
		
	}
	
	/** 
	 * @Title: saveDepartment 
	 * @Description: TODO 保存新增或者编辑操作数据
	 * @return: String 操作结果
	 */
	@RequestMapping("/saveDepartment")
	@ResponseBody
	public String saveDepartment(HttpServletRequest request){
		Department department=new Department();
		String result ;
		String grade=request.getParameter("grade").trim();
		String depaId=request.getParameter("depa_id");
		String depaNo=request.getParameter("depaNo").trim();
		String depaName=request.getParameter("depaName").trim();
		String depaDescription=request.getParameter("depaDescription").trim();
		
		//级别Id
		int gradId=Integer.parseInt(grade.split("%")[0]);
		//级别编号
		int gradNo=Integer.parseInt(grade.split("%")[1]);
		department.setDepaNo(depaNo);
		department.setDepaName(depaName);
		department.setDepaDescription(depaDescription);
		department.setGradId(gradId);
		//类别
		
		if(gradNo>1){
			String parentIdStr=request.getParameter(""+(gradNo-1));
			//判断是否存在父部门
			if(StringUtils.isNotBlank(parentIdStr)){
				department.setDepaParentId(parentIdStr.trim());
			}else{
				result="父部门不为空";
				return result;
			}
		}else {
			
			Department topDepartment=adminDepartmentService.getTopDepartment();
			if(topDepartment!=null){//判断是否已经存在顶级部门
				if(StringUtils.isNotBlank(depaId)){
					if(depaId.equals(topDepartment.getDepaId())){//判断是否是新建顶级部门
						result="不能存在多个顶级部门";
						return result;
					}
				}
			}
		}
		if(StringUtils.isNotBlank(depaId)){
			//更新操作
			try {
				department.setDepaId(depaId.trim());
				adminDepartmentService.updateDepartment(department);
				return "success";
			} catch (Exception e) {
				return "部门编号重复";
			}
		}else{
			//插入操作
			try {
				adminDepartmentService.addDepartment(department);
				return "success";
			} catch (Exception e) {
				return "部门编号重复";
			}
		}

	}
	
	
	/** 
	 * @Title: getDepartmentDatatable 
	 * @Description: TODO 获取部门列表数据
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/getDepartmentList")
	@ResponseBody
	public Map<String, Object> getDepartmentDatatable() {
		
        //存储DataTable数据Map
		Map<String, Object> dataTableMap = new HashMap<String, Object>();
		//存储Request数据Map
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		//查询过滤添加Map
		Map<String, Object> filterMap = new HashMap<String, Object>();
		parameterMap.putAll(DataTableUtil.getParameterMap());
		// DataTable操作类型
		String sAction=(String)parameterMap.get(DTConstants.ACTION);
	    if(DTConstants.FILTER_ACTION.equals(sAction)){
	    	
			filterMap.putAll(parameterMap);
		}else if(DTConstants.GROUP_ACTION.equals(sAction)){
			List<String> depaIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (depaIds != null) {
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				if (sGroupActionName.equals("delete")) {
					int upNum = adminDepartmentService.batchDeleteDepartment(depaIds);
					if(upNum > 0) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", upNum + "条记录已被删除");
					}
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminDepartmentService.getDepartmentList(filterMap);
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}
}
