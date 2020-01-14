/**   
 * Copyright © 2016 广东网金控股股份有限公司(http://www.ucsmy.com). All rights reserved.
 */
package com.ucsmy.mc.common.pojo;

import java.util.List;
import java.util.Map;

/** 
 * @ClassName: ComboTree 
 * @Description: TODO
 * @author: ucs_chenchengteng
 * @date: 2016年11月28日 下午2:07:32
 * @version: V1.0     
 */
public class ComboTree {

	private String id;
	private String text;// 树节点名称
	private Map<String, Object> attributes;// 其他参数
	private List<ComboTree> children;// 子节点
	private TreeState state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public List<ComboTree> getChildren() {
		return children;
	}
	public void setChildren(List<ComboTree> children) {
		this.children = children;
	}
	public TreeState getState() {
		return state;
	}
	public void setState(TreeState state) {
		this.state = state;
	}
	
	
}
