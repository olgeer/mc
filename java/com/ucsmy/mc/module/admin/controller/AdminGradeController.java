package com.ucsmy.mc.module.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ucsmy.mc.common.entity.Grade;
import com.ucsmy.mc.module.admin.service.AdminGradeService;
import com.ucsmy.mc.util.DataTableUtil;
import com.ucsmy.mc.util.PageUtil;
import org.apache.commons.lang3.StringUtils;
import com.ucsmy.mc.util.constants.DTConstants;
import com.ucsmy.mc.util.paging.PageContext;


/**
 * 级别管理控制器
 * 
 * @author ucs_xuxiling
 *
 */
@Controller
@RequestMapping("/admin/grade")
public class AdminGradeController {
	@Resource(name="adminGradeServiceImpl")
	private AdminGradeService adminGradeService;
	
	@RequestMapping("/toGradeList")
	public String togradeList(Model model){
		return "admin/grade/grade_list";
	}
	
	/** 
	 * @Title: toAddOrEidtgrade 
	 * @Description: TODO 新建或者编辑级别界面
	 * @return: String 新建或者编辑级别界面路径
	 */
	@RequestMapping("/toAddOrEidtGrade")
	public String toAddOrEidtGrade(Model model,HttpServletRequest request) {
	
		/*Map<String, Object> map=new HashMap<String, Object>();
		List<Grade> gradeList =adminGradeService.selectGradeList(map);*/
		String gradId=request.getParameter("gradId");
		if(StringUtils.isNotBlank(gradId)){
			Grade grade=adminGradeService.selectByPrimaryKey(gradId);
			model.addAttribute("grade",grade);
		}
		
		return "admin/grade/grade_add_edit";
		
	}
	
	/** 
	 * @Title: savegrade 
	 * @Description: TODO 保存新增或者编辑操作数据
	 * @return: String 操作结果
	 */
	@RequestMapping("/saveGrade")
	@ResponseBody
	public String savegrade(HttpServletRequest request){
		Grade grade=new Grade();
		String gradId=request.getParameter("gradId");
		String gradGradeNo=request.getParameter("gradGradeNo");
		String gradGradeName=request.getParameter("gradGradeName");
		String gradType=request.getParameter("gradType");
		grade.setGradGradeName(gradGradeName);
		grade.setGradGradeNo(gradGradeNo);
		if(StringUtils.isNotBlank(gradType)){
		grade.setGradType(Byte.valueOf(gradType));
		
		}
		if(StringUtils.isNotBlank(gradId)){
			//更新操作
			try {
				grade.setGradId(gradId.trim());
				adminGradeService.updateByPrimaryKey(grade);
				return "success";
			} catch (Exception e) {
				return "级别编号重复";
			}
		}else{
			//插入操作
			try {
				adminGradeService.insert(grade);
				return "success";
			} catch (Exception e) {
				return "级别编号重复";
			}
		}

	}
	
	
	/** 
	 * @Title: getgradeDatatable 
	 * @Description: TODO 获取级别列表数据
	 * @return: Map<String,Object>
	 */
	@RequestMapping("/getGradeList")
	@ResponseBody
	public Map<String, Object> getGradeDatatable() {
		
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
			List<String> gradeIds = DataTableUtil.getDataTableListIds(parameterMap);
			
			if (gradeIds != null) {
				String sGroupActionName=(String)parameterMap.get(DTConstants.GROUP_ACTION_NAME);
				if (sGroupActionName.equals("delete")) {
					int upNum = adminGradeService.batchDeleteGrade(gradeIds);
					if(upNum > 0) {
						dataTableMap.put("sStatus", "OK");
						dataTableMap.put("sMessage", upNum + "条记录已被删除");
					}
				}
			}
		}
		PageContext pageContext = PageUtil.setPageArgs(parameterMap);// 分页
		List<Map<String, Object>> list = adminGradeService.selectGradeList(filterMap);
		DataTableUtil.setPageArgs(pageContext, dataTableMap, parameterMap);
		dataTableMap.put(DTConstants.DATA, list);
		return dataTableMap;
	}

}
