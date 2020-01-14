package com.ucsmy.mc.module.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ucsmy.mc.util.SpringContextUtil;

/**
 * 
 * 
 * Description:文件相关的配置信息读取
 * 
 * Time:2015年12月11日下午3:46:53
 * @version 1.0
 * @since 1.0
 * @author yangdd
 */
public class FileUploadUtil {
	private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
	
	private static Properties properties = SpringContextUtil.getPropertiesBean();

	/**
	 * 获取文件保存的位置 是直接保存在硬盘还是保存到ftp上面
	 * @return
	 */
	public static String getToWhere(){
		return properties.getProperty("upload.file.store.where");
	}
	
	/**
	 * 获取硬盘的基本路径
	 * @return
	 */
	public static String getFileBaseRealPath() {
		String fileBaseRealPath = null;
		
		String baseRealPath = properties.getProperty("upload.file.baseRealPath");
		if(StringUtils.isNotBlank(baseRealPath)){
			fileBaseRealPath = baseRealPath;
		}
		
		return fileBaseRealPath;
	}

	
	/**
	 * 获取相对于硬盘或ftp服务器共享目录的相对路径
	 * @return
	 */
	public static String getFileRelativePath() {
		String fileRelativePath = null;
		
		String relativePath = properties.getProperty("upload.file.relativePath");
		
		if(StringUtils.isNotBlank(relativePath)){
			fileRelativePath = relativePath;
		}
		return fileRelativePath;
	}
	
	/**
	 * 根据模块获取相对路径的方法
	 * @param module
	 * @return
	 */
	public static String getRelativePathByFileType(String module){
		String relativePath="upload.file.relativePath";
		if(StringUtils.isNotEmpty(module)){
			relativePath=relativePath.concat(".").concat(module);
		}
		String fileRelativePath = properties.getProperty(relativePath);
		return fileRelativePath;
	}
	
	/**
	 * 取出文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getExt(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		return suffix;
	}
	
	/**
	 * 获取文件名
	 * @param path
	 * @return
	 */
	public static String getFileNameByPath(String path){
		String result = "";
		result = path.substring(path.indexOf("/")+1,path.length());
		return result;
	}
	
	/**
	 * 检测是否允许上传
	 * @param fileData 原文件
	 * @return 如果为空则通过,否则返回不通过原因
	 */
	public static String checkImage(MultipartFile fileData){
		Long imageMaxSize = getImageMaxSize();
		List<String> imageAllowType = getImageAllowType();
		List<String> imageNotAllowType = getImageNotAllowType();
		String suffix = getExt(fileData.getOriginalFilename());
		if(suffix.length()<=1)return "文件类型不支持!";
		String type = suffix.substring(1,suffix.length()-1);
		if(imageMaxSize!=null && imageMaxSize<fileData.getSize()){;
			return "文件过大!";
		}
		if((imageAllowType!=null && !imageAllowType.contains(type)) 
				|| (imageNotAllowType!=null && imageNotAllowType.contains(type))){
			return "文件类型不支持!";
		}
		return null;
	}
	
	/**
	 * 检测是否允许上传
	 * @param fileData 原文件
	 * @return 如果为空则通过,否则返回不通过原因
	 */
	public static String checkFile(MultipartFile fileData){
		Long fileMaxSize = getFileMaxSize();
		List<String> fileAllowType = getFileAllowType();
		List<String> fileNotAllowType = getFileNotAllowType();
		String suffix = getExt(fileData.getOriginalFilename());
		if(suffix.length()<=1)return "文件类型不支持!";
		String type = suffix.substring(1,suffix.length());
		if(fileMaxSize!=null && fileMaxSize<fileData.getSize()){;
			return "文件过大!";
		}
		if((fileAllowType!=null && !fileAllowType.contains(type)) 
				|| (fileNotAllowType!=null && fileNotAllowType.contains(type))){
			return "文件类型不支持!";
		}
		return null;
	}
		
	/**
	 * 获取最大允许图片上传的大小
	 * @return
	 */
	public static Long getImageMaxSize() {
		Long imageMaxSize = null;
		String imageMaxSizeStr = properties.getProperty("upload.image.size");		
		try{
			imageMaxSize = Long.parseLong(imageMaxSizeStr);
		}catch(Exception e){
			log.error("getImageMaxSize : 属性upload.image.size转换成Long出错");
		}
		return imageMaxSize;
	}

	/**
	 * 获取允许上传的图片后缀名
	 * @return
	 */
	public static List<String> getImageAllowType() {
		List<String> imageAllowType = null;
		String imageTypes = properties.getProperty("upload.image.types");
		if(StringUtils.isNotBlank(imageTypes) && "*".equals(StringUtils.trim(imageTypes))){
			String[] typeArray = imageTypes.split(",");
			for(int i=0;i<typeArray.length;i++){
				String type = typeArray[i];
				type = StringUtils.trim(type);
				if(StringUtils.isBlank(type))continue;
				if(!"!".equals(type.substring(0,1))){
					if(imageAllowType==null)imageAllowType = new ArrayList<String>();
					imageAllowType.add(type);
				}
			}
		}
		return imageAllowType;
	}

	/**
	 * 获取不允许上传的图片后缀名
	 * @return
	 */
	public static List<String> getImageNotAllowType() {
		List<String> imageNotAllowType = null;
		String imageTypes = properties.getProperty("upload.image.types");
		if(StringUtils.isNotBlank(imageTypes) && "*".equals(StringUtils.trim(imageTypes))){
			String[] typeArray = imageTypes.split(",");
			for(int i=0;i<typeArray.length;i++){
				String type = typeArray[i];
				type = StringUtils.trim(type);
				if(StringUtils.isBlank(type))continue;
				if("!".equals(type.substring(0,1))){
					if(imageNotAllowType==null)imageNotAllowType = new ArrayList<String>();
					imageNotAllowType.add(type.substring(0,1));
				}
			}
		}
		return imageNotAllowType;
	}

	/**
	 * 获取文件上传的最大容量
	 * @return
	 */
	public static Long getFileMaxSize() {
		Long fileMaxSize = null;
		String fileMaxSizeStr = properties.getProperty("upload.file.size");
		try{
			fileMaxSize = Long.parseLong(fileMaxSizeStr);
		}catch(Exception e){
			log.error("getFileMaxSize : 属性upload.file.size转换成Long出错");
		}
		return fileMaxSize;
	}

	/**
	 * 获取允许上传的文件后缀名
	 * @return
	 */
	public static List<String> getFileAllowType() {
		List<String> fileAllowType = null;
		String fileTypes = properties.getProperty("upload.file.types");
		if(StringUtils.isNotBlank(fileTypes) && !"*".equals(StringUtils.trim(fileTypes))){
			String[] typeArray = fileTypes.split(",");
			for(int i=0;i<typeArray.length;i++){
				String type = typeArray[i];
				type = StringUtils.trim(type);
				if(StringUtils.isBlank(type))continue;
				if(!"!".equals(type.substring(0,1))){
					if(fileAllowType==null)fileAllowType = new ArrayList<String>();
					fileAllowType.add(type.substring(0,1));
				}
				
			}
		}
		return fileAllowType;
	}
	
	/**
	 * 获取不允许上传的文件后缀名
	 * @return
	 */
	public static List<String> getFileNotAllowType() {
		List<String> fileNotAllowType = null;
		String fileTypes = properties.getProperty("upload.file.types");
		if(StringUtils.isNotBlank(fileTypes) && !"*".equals(StringUtils.trim(fileTypes))){
			String[] typeArray = fileTypes.split(",");
			for(int i=0;i<typeArray.length;i++){
				String type = typeArray[i];
				type = StringUtils.trim(type);
				if(StringUtils.isBlank(type))continue;
				if("!".equals(type.substring(0,1))){
					if(fileNotAllowType==null)fileNotAllowType = new ArrayList<String>();
					fileNotAllowType.add(type);
				}
			}
		}
		return fileNotAllowType;
	}
	
}
