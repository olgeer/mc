/**   
 * Copyright © 2016 广东网金控股股份有限公司(http://www.ucsmy.com). All rights reserved.
 */
package com.ucsmy.mc.common.pojo;

import java.util.List;

/**
 * Description:部门树实体.
 * Time:2015年12月4日下午4:20:00
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DepartmentTree {

	/** 部门ID. */
    private String depaId;
                    
    /** 部门编号. */
    private String depaNo;
    
	/** 部门名称. */
    private String depaName;

    /** 部门级别编号. */
    private int gradGradeNo;

	/** 部门级别. */
    private String gradGradeName;
    
    /** 子部门集合. */
    private List<DepartmentTree> childList;
    
    /** 父部门 .*/
    private DepartmentTree parent;
    
    /** 父部门id. */
    private String depaParentId;
    
    /** 父部门编号. */
    private String depDepaNo;
    

	public String getDepaId() {
		return depaId;
	}

	public void setDepaId(String depaId) {
		this.depaId = depaId;
	}

	public String getDepaNo() {
		return depaNo;
	}

	public void setDepaNo(String depaNo) {
		this.depaNo = depaNo;
	}

	public String getDepaName() {
		return depaName;
	}

	public void setDepaName(String depaName) {
		this.depaName = depaName;
	}

	public List<DepartmentTree> getChildList() {
		return childList;
	}

	public void setChildList(List<DepartmentTree> childList) {
		this.childList = childList;
	}

	public DepartmentTree getParent() {
		return parent;
	}

	public void setParent(DepartmentTree parent) {
		this.parent = parent;
	}

	public int getGradGradeNo() {
		return gradGradeNo;
	}

	public void setGradGradeNo(int gradGradeNo) {
		this.gradGradeNo = gradGradeNo;
	}

	public String getGradGradeName() {
		return gradGradeName;
	}

	public void setGradGradeName(String gradGradeName) {
		this.gradGradeName = gradGradeName;
	}

	public String getDepaParentId() {
		return depaParentId;
	}

	public void setDepaParentId(String depaParentId) {
		this.depaParentId = depaParentId;
	}

	public String getDepDepaNo() {
		return depDepaNo;
	}

	public void setDepDepaNo(String depDepaNo) {
		this.depDepaNo = depDepaNo;
	}

	
	
}
