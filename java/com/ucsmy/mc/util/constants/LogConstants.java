/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.constants;

/**
 * Description:操作日志枚举 常量  
 * Time:2016年2月16日下午4:26:38
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public enum LogConstants {
	update("更新"),add("新增"),delete("删除"),query("查询"),submit("提交"),view("查看详情"),
	//启用，关闭
	open("启用"),close("关闭"),
	//档案相关的一些操作
	archived("归档"),destory("销毁"),ineffective("失效"),
	browse_y("可浏览"),browse_n("不可浏览"),download_y("可下载"),download_n("不可下载"),
	modify_y("可修改"),modify_n("不可修改"),borrow_y("可借阅"),borrow_n("不可借阅"),
	//生效
	effective("生效"),
	//其他
	uploadFile("上传文件"),deleteFile("删除文件"),exportFile("导出文件"),importFile("导入文件")
	;
	private String zhName;
	private LogConstants(String zhName){
		this.zhName=zhName;
	}
	public String getZhName() {
		return zhName;
	}
	public void setZhName(String zhName) {
		this.zhName = zhName;
	}
}
