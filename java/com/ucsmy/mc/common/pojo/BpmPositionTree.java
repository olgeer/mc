/**   
 * Copyright © 2016 广东网金控股股份有限公司(http://www.ucsmy.com). All rights reserved.
 */
package com.ucsmy.mc.common.pojo;

import java.util.List;

/**
 * Description:崗位树实体.
 * Time:2016年12月28日下午14:13:00
 * @version 1.0
 * @since 1.0
 * @author xuxiling
 */
public class BpmPositionTree {
	 /** 崗位ID */
	private String bppoId;

	 /** 崗位名稱 */
	private String bppoName;
	 /** 部门编号. */
	private String bppoNo;

	private String bppoParentId;
	  /** 岗位级别编号. */
    private int gradGradeNo;
	/** 岗位级别名稱 */
    private String gradGradeName;
    /** 子岗位集合. */
    private List<BpmPositionTree> childList;
    /** 父岗位.*/
    private BpmPositionTree parent;
    /** 父岗位id. */
    private String bpoParentId;
    
    /** 父岗位编号. */
    private String bpoBpoNo;

	public String getBppoId() {
		return bppoId;
	}

	public void setBppoId(String bppoId) {
		this.bppoId = bppoId;
	}

	public String getBppoName() {
		return bppoName;
	}

	public void setBppoName(String bppoName) {
		this.bppoName = bppoName;
	}

	public String getBppoNo() {
		return bppoNo;
	}

	public void setBppoNo(String bppoNo) {
		this.bppoNo = bppoNo;
	}

	public String getBppoParentId() {
		return bppoParentId;
	}

	public void setBppoParentId(String bppoParentId) {
		this.bppoParentId = bppoParentId;
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

	public List<BpmPositionTree> getChildList() {
		return childList;
	}

	public void setChildList(List<BpmPositionTree> childList) {
		this.childList = childList;
	}

	public BpmPositionTree getParent() {
		return parent;
	}

	public void setParent(BpmPositionTree parent) {
		this.parent = parent;
	}

	public String getBpoParentId() {
		return bpoParentId;
	}

	public void setBpoParentId(String bpoParentId) {
		this.bpoParentId = bpoParentId;
	}

	public String getBpoBpoNo() {
		return bpoBpoNo;
	}

	public void setBpoBpoNo(String bpoBpoNo) {
		this.bpoBpoNo = bpoBpoNo;
	}


}