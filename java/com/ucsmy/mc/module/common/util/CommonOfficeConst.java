/**
 * 
 */
package com.ucsmy.mc.module.common.util;


/**
 * @author dong
 *
 */
public class CommonOfficeConst
{
/*	public final static String OFFICEMODULESUBKIND = "9";//模板类型 数据字典
	public final static String OFFICEFILEFORMAT = "10";//文件格式
*/
	public final static String TEMPLATEOPENTYPE_ADD = "0"; //新建
	public final static String TEMPLATEOPENTYPE_EDIT = "1"; //新建
	public final static String NOTDELETEFLAG="0";//未删除
    
	//文件存放处 ftp：0 硬盘为其他值
    public  final static String TOWHERE = FileUploadUtil.getToWhere();
    //获取硬盘的基本路径
    public final static String BASEREALPATH = FileUploadUtil.getFileBaseRealPath();
}
