/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ucsmy.mc.util.constants.Constants;

/** 
 * @ClassName: FileDownLoadUtil 
 * @Description: TODO 
 * @author: ucs_chenchengteng
 * @date: 2017年2月13日 下午5:36:55
 * @version: V1.0     
 */
public class FileDownLoadUtil {
	
	private static Logger log = LoggerFactory.getLogger(FileDownLoadUtil.class);

	// 保存输入流对应的文件名（在流关闭失败的时候，重新读一次文件，把新建的流关掉，以释放被占用的文件）
	private static Map<InputStream, String> inputStreamMap = new HashMap<InputStream, String>();
	
	/** 
	 * @Title: download 
	 * @Description: TODO
	 * @param request
	 * @param response
	 * @param Url
	 * @return
	 * @return: boolean
	 */
	public static boolean download(HttpServletRequest request, HttpServletResponse response, String url) {  
		//防止修改路径
		if(url.contains("..")){
			return false;
		}
		String fileName=url.substring(url.lastIndexOf("/")+1);
        StringBuilder downLoadPath = new StringBuilder();
        downLoadPath.append(Constants.FILE_PATH+File.separator);
        downLoadPath.append(url.replace("/", File.separator));

        long fileLength = new File(downLoadPath.toString()).length();  
        response.setHeader("Content-Length", String.valueOf(fileLength));  
        //清空response
        response.reset();
        response.setContentType("application/octet-stream"); 
        //设置GB2312编码解决IE浏览器读取文件名属性乱码问题
        try {
			response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
        	return false;
		}  
        BufferedInputStream bis = null;  
        BufferedOutputStream bos = null;
        try {
	        
	        bis = new BufferedInputStream(new FileInputStream(downLoadPath.toString()));
			inputStreamMap.put(bis, downLoadPath.toString());
	        bos = new BufferedOutputStream(response.getOutputStream());
	        byte[] buff = new byte[Constants.READ_MAXIMUM];  
	        int bytesRead;  
	        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	        	bos.write(buff, 0, bytesRead); 
	        }
	        bos.flush();
	        
        } catch (Exception e) {
        	log.error("文件下载失败", e);
        	
        } finally {
        	try {
        		if(bos != null)
        			bos.close();
			} catch (IOException e) {
				log.error("关闭IO流失败,文件名称："+fileName, e);
				bos = null;
				System.gc();
				return false;
			}
        	try {
        		if(bis != null)
        			bis.close();
			} catch (IOException e) {
				log.error("关闭IO流失败,文件名称："+fileName, e);
				bis=null;
				System.gc();
				return false;
			}
        }
		return true;
    }
}
