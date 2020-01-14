package com.ucsmy.mc.module.activiti.entity;

public class BpmPosition {
	private String bppoId;

	private String gradId;

	private String bppoName;

	private String bppoNo;

	private String bppoParentId;

	public String getBppoId() {
		return bppoId;
	}

	public void setBppoId(String bppoId) {
		this.bppoId = bppoId;
	}

	public String getGradId() {
		return gradId;
	}

	public void setGradId(String gradId) {
		this.gradId = gradId;
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
}