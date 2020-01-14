/*
 * Copyright (c) 2016 
 * 广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ucsmy.mc.common.pojo.BpmPositionTree;
import com.ucsmy.mc.module.activiti.exmapper.ExBpmPositionMapper;

/**
 * Description:岗位树工具类.
 * Time:2016年12月29日上午9:21:56
 * @version 1.0
 * @since 1.0
 * @author xuxiling
 */
@Component
public class BpmPositionTreeUtil {
	
	/** 岗位树Mapper. */
	@Resource
	private ExBpmPositionMapper exBpmPositionMapper ;
	
	/** 整个岗位树 */
	private static List<BpmPositionTree> bpmpositionTrees= new  ArrayList<>();
	private static BpmPositionTree bpmpositionTree=new BpmPositionTree();
	
	/** 父岗位IdList. */
	private static List<String> parentIdList = new ArrayList<String>();
	
	/**
	 * 初始化岗位树.
	 */
	@PostConstruct
	public void init() {
		reloadBpmPosition(exBpmPositionMapper);
	}

	/**
	 * reload岗位树.
	 * @param exBpmPositionMapper 岗位树Mapper
	 */
	public static void reloadBpmPosition(ExBpmPositionMapper exBpmPositionMapper) {
		bpmpositionTrees=exBpmPositionMapper.selectBpmPositionTree();
	}
	
	/**根据岗位Id查询目标级别BpmPositionTreeList.
	 * 如果目标级别就是当前岗位Id级别，则返回List中只有当前岗位.
	 * 如果目标级别小于当前岗位Id级别,则返回List中只有对应级别的父岗位.
	 * 如果目标级别大于当前岗位Id级别，则返回List中有所有对应级别的子岗位.
	 * @param currentBppoId 当前岗位Id
	 * @param targetGradeNo 目标级别
	 * @return
	 */
	public static List<BpmPositionTree> getGradeBpmPositionTrees(String currentBppoId, int targetGradeNo){
		List<BpmPositionTree> bpmPositionTreeList = new ArrayList<BpmPositionTree>();
		BpmPositionTree currentPositionTree = getBpmPositionTreeByBppoId(currentBppoId, BpmPositionTreeUtil.bpmpositionTree);
		int currentGradeNo = currentPositionTree.getGradGradeNo();
		if(currentGradeNo == targetGradeNo) {
			bpmPositionTreeList.add(currentPositionTree);
			return bpmPositionTreeList;
		} else if (currentGradeNo > targetGradeNo) {
			bpmPositionTreeList.add(getParent(currentBppoId, targetGradeNo));
			return bpmPositionTreeList;
		} else {
			bpmPositionTreeList=getBpmPositionTreeByBppoId(currentPositionTree, targetGradeNo);
			return bpmPositionTreeList;
		}
	}
	/**
	 * 根据岗位Id,查找直属父岗位Id.
	 * @param bppoId 岗位Id
	 * @return 直属父岗位Id
	 */
	public static String getParentId(String bppoId) {
		return getParentId(bppoId, -1);
	}
	/**
	 * 根据子岗位Id和级别,查找对应级别的父岗位Id.
	 * @param bppoId 岗位Id
	 * @param parentGradeNo 级别编号
	 * @return 对应级别的父岗位Id
	 */
	public static String getParentId(String bppoId, int parentGradeNo) {
		BpmPositionTree parentBpmPositionTree = getParent(bppoId, parentGradeNo);
		if (parentBpmPositionTree != null) {
			return parentBpmPositionTree.getBppoId();
		} else {
			return null;
		}
	}
	/**
	 * 根据岗位Id和级别,查找对应级别的父岗位Id集合,集合按照父岗位->父岗位->父岗位 排序.
	 * @param depaId 岗位Id
	 * @param parentGradeNo 级别编号
	 * @return 对应级别的父岗位Id集合
	 */
	public static List<String> getParentIds(String bppoId, int parentGradeNo) {
		getParent(bppoId, parentGradeNo);
		return parentIdList;
	}
	
	
	/**
	 * 根据岗位Id,查找直属父岗位实体.
	 * @param bppoId 岗位Id
	 * @return 直属父岗位
	 */
	public static BpmPositionTree getParent(String bppoId) {
		return getParent(bppoId, -1);
	}
	
	/**
	 * 根据岗位Id和级别,查找对应级别的父岗位实体.
	 * @param depaId 岗位Id
	 * @param parentGradeNo 级别编号
	 * @return 对应级别的父岗位实体
	 */
	public static BpmPositionTree getParent(String bppoId, int parentGradeNo) {
		BpmPositionTree bpmPositionTree = getBpmPositionTreeByBppoId(bppoId, BpmPositionTreeUtil.bpmpositionTree);
		if (bpmPositionTree != null) {
			int gradGradeNo = bpmPositionTree.getGradGradeNo();
			if (parentGradeNo == -1) {
				return getParentBpmPositionTree(bpmPositionTree);
			} else if (parentGradeNo < gradGradeNo) {
				parentIdList.clear();
				for (int i = 0; i < gradGradeNo - parentGradeNo; i++) {
					bpmPositionTree = getParentBpmPositionTree(bpmPositionTree);
					parentIdList.add(bpmPositionTree.getBppoId());
				}
				return bpmPositionTree;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	
	/**
	 * 根据岗位Id,查找所有父岗位实体.
	 * @param bppoId 岗位Id
	 * @return 所有父岗位实体List
	 */
	public static List<BpmPositionTree> getAllParent(String bppoId) {
		BpmPositionTree bpmPositionTree = getBpmPositionTreeByBppoId(bppoId, BpmPositionTreeUtil.bpmpositionTree);
		List<BpmPositionTree> bpmPositionTrees=new ArrayList<BpmPositionTree>();
		int gradGradeNo = bpmPositionTree.getGradGradeNo();
		bpmPositionTrees.add(bpmPositionTree);
		BpmPositionTree temp=bpmPositionTree;
		for (int i = 0; i < gradGradeNo - 1; i++) {
			temp = getParentBpmPositionTree(temp);
			bpmPositionTrees.add(temp);
		}
		return bpmPositionTrees;
	}
	
	
	/**根据岗位Id，返回由父岗位节点拼接成的Path,
	 * @param depaId 岗位Id
	 * @return
	 */
	public static String getPathByBppoId(String bppoId){
		StringBuilder stringBuilder=new StringBuilder();
		List<BpmPositionTree> list=getAllParent(bppoId);
		//倒序List
		Collections.reverse(list);
		for (Iterator<BpmPositionTree> iterator = list.iterator(); iterator.hasNext();) {
			BpmPositionTree bpmPositionTree = (BpmPositionTree) iterator.next();
			stringBuilder.append(File.separator).append(bpmPositionTree.getBppoNo());
		}
		return stringBuilder.toString();
	}
	
	
	
	/**
	 * 根据岗位Id,查找子岗位集合.
	 * @param depaId 岗位Id
	 * @return 子岗位集合
	 */
	public static List<BpmPositionTree> getChildBpmPositionTrees(String bppoId){
		BpmPositionTree bpmPositionTree=getBpmPositionTreeByBppoId(bppoId,BpmPositionTreeUtil.bpmpositionTree);
		if (bpmPositionTree != null) {
			return getChildBpmPositionTree(bpmPositionTree);
		} else {
			return null;
		}
	}
	
	/**
	 * @param depaId 当前岗位Id
	 * @return 当前岗位的子岗位depaId
	 */
	public static List<String> getChildBpmPositionTreeBppoIds(String bppoId) {
		List<String> bppoIds=new ArrayList<String>();
		List<BpmPositionTree> chlidBpmPositionTrees=getChildBpmPositionTrees(bppoId);
		for (BpmPositionTree bpmPositionTree : chlidBpmPositionTrees) {
			bppoIds.add(bpmPositionTree.getBppoId());
		}
		return bppoIds;
	}
	
	/**
	 * 获取当前岗位树的所有节点
	 * @param departmentTree
	 * @return 岗位ID List
	 */
	public static List<String> getAllChildBpmPositionTreeBppoIds(BpmPositionTree bpmPositionTree) {
		
		List<String> bppoIds=new ArrayList<String>();
		List<BpmPositionTree> childList = bpmPositionTree.getChildList();
		if(childList.size()>0){
			for (BpmPositionTree tempBpmPositionTree : childList) {
				List<String> childDepaIdList=getAllChildBpmPositionTreeBppoIds(tempBpmPositionTree);
				if(childDepaIdList.size()>0){
					bppoIds.addAll(childDepaIdList);
				}
				bppoIds.add(tempBpmPositionTree.getBppoId());
			}
		} 
		return bppoIds;
		
	}
	/**
	 * 根据子岗位Id，在整个岗位中递归查找子岗位.
	 * @param depaId  岗位Id
	 * @param departmentTree 整个岗位
	 * @return 子岗位实体
	 */
	public static BpmPositionTree getBpmPositionTreeByBppoId(String bppoId,BpmPositionTree bpmPositionTree){
		BpmPositionTree targetBpmPositionTree = null;
		if (bpmPositionTree.getBppoId() == bppoId) {
			targetBpmPositionTree = bpmPositionTree;
			return targetBpmPositionTree;
		} else {
			 List<BpmPositionTree> childList = bpmPositionTree.getChildList();
			 if (childList.size() > 0) {
				for (BpmPositionTree childBpmPositionTree : childList) {
					targetBpmPositionTree = getBpmPositionTreeByBppoId(bppoId, childBpmPositionTree);
					if (targetBpmPositionTree != null) {
						break;
					}
				}
				return targetBpmPositionTree;
			}
			return null;
		}
	}
	
	/**
	 * 根据岗位Id,获取当前岗位Tree
	 * @param bppoId
	 * @return 岗位树
	 */
	public static BpmPositionTree getBpmPositionTreeByBppoId(String bppoId) {
		return getBpmPositionTreeByBppoId(bppoId,bpmpositionTree);
	}
	


	/**
	 * 根据父岗位Tree，查找对应级别的所有子岗位Tree.
	 * @param departmentTree 父岗位Tree
	 * @param targetGradeNo	子岗位级别
	 * @return
	 */
	public static List<BpmPositionTree> getBpmPositionTreeByBppoId(BpmPositionTree bpmPositionTree, int targetGradeNo) {
		List<BpmPositionTree> childDepartmentTreeList = new ArrayList<BpmPositionTree>();
		List<BpmPositionTree> currentChildDepartmentTreeList= bpmPositionTree.getChildList();
		for (BpmPositionTree childDepartmentTree : currentChildDepartmentTreeList) {
			if (childDepartmentTree.getGradGradeNo() == targetGradeNo) {
				childDepartmentTreeList.addAll(currentChildDepartmentTreeList);
				return childDepartmentTreeList;
			} else {
				childDepartmentTreeList.addAll(getBpmPositionTreeByBppoId(childDepartmentTree, targetGradeNo));
			}
		}
		return  childDepartmentTreeList;
	}
	/**
	 * 获取父岗位实体.
	 * @param departmentTree 岗位实体
	 * @return 父岗位实体
	 */
	private static BpmPositionTree getParentBpmPositionTree(BpmPositionTree bpmPositionTree) {
		return bpmPositionTree.getParent();
	}
	/**
	 *获取子岗位集合.
	 * @param departmentTree 岗位实体
	 * @return 子岗位集合
	 */
	private static List<BpmPositionTree> getChildBpmPositionTree(BpmPositionTree bpmPositionTree) {
		return bpmPositionTree.getChildList();
	}

	public ExBpmPositionMapper getCommonBpmPositionMapper() {
		return exBpmPositionMapper;
	}

	public void setCommonBpmPositionMapper(
			ExBpmPositionMapper exBpmPositionMapper) {
		this.exBpmPositionMapper = exBpmPositionMapper;
	}

	public static BpmPositionTree getBpmPositionTree() {
		return bpmpositionTree;
	}

	public static void setBpmPositionTreeMap(
			BpmPositionTree bpmpositionTree) {
		BpmPositionTreeUtil.bpmpositionTree = bpmpositionTree;
	}
	
	/**
	 * 根据当前岗位id，查找出该岗位在所在年份对应的岗位id.
	 * @param currentDepaId 当前岗位id
	 * @return 所在年份对应的岗位id
	 */
	public static String getBpmPositionIdByYear(short year, String currentBppoId){
		BpmPositionTree currentBpmPositionTree = getBpmPositionTreeByBppoId(currentBppoId);
		BpmPositionTree targetDepartmentTree =bpmpositionTree;
		
		if(currentBpmPositionTree != null && targetDepartmentTree != null){
			BpmPositionTree bpmPositionTree = getBpmPositionTreeByBppoNo(currentBpmPositionTree.getBppoNo(), targetDepartmentTree);
			if(bpmPositionTree != null){
				return bpmPositionTree.getBppoId();
			} 
		}
		
		return"";
		
	}
	
	/**
	 * 根据子岗位编号，在整个岗位中递归查找子岗位.
	 * @param depaNo  岗位编号
	 * @param departmentTree 整个岗位
	 * @return 子岗位实体
	 */
	public static BpmPositionTree getBpmPositionTreeByBppoNo(String bppoNo, BpmPositionTree departmentTree){
		BpmPositionTree targetDepartmentTree = null;
		if (departmentTree.getBppoNo().equals(bppoNo)) {
			targetDepartmentTree = departmentTree;
			return targetDepartmentTree;
		} else {
			 List<BpmPositionTree> childList = departmentTree.getChildList();
			if (childList.size() > 0) {
				for (BpmPositionTree childDepartmentTree : childList) {
					targetDepartmentTree = getBpmPositionTreeByBppoNo(bppoNo, childDepartmentTree);
					if (targetDepartmentTree != null) {
						break;
					}
				}
				return targetDepartmentTree;
			}
			return null;
		}
	}
	
}
