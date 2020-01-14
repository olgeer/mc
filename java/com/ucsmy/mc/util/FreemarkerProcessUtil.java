/**
 * 
 */
package com.ucsmy.mc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author Administrator
 *
 */
public class FreemarkerProcessUtil {
	/**
	 * @param templatePath 模板文件存放路径
	 * @param templateName 模板文件名称
	 * @param fileName 生成的文件名称
	 * @param root
	 */
    private static boolean analysisTemplate(String templatePath,String templateName,String fileName,Map<?,?>root){
        boolean ret  =true;
    	try {
            Configuration config=new Configuration();
            //设置要解析的模板所在的目录，并加载模板文件
            config.setDirectoryForTemplateLoading(new File(templatePath));
            //设置包装器，并将对象包装为数据模型
            config.setObjectWrapper(new DefaultObjectWrapper());
            //获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
            //否则会出现乱码
            Template template=config.getTemplate(templateName,"UTF-8");
            //合并数据模型与模板
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer out = new OutputStreamWriter(fos,"UTF-8");
            template.process(root, out);
            out.flush();
            out.close();
        } catch (IOException e) {
        	ret=false;
            e.printStackTrace();
        }catch (TemplateException e) {
        	ret=false;
            e.printStackTrace();
        }
    	return ret;
    }
    /**
     * 生成xml文件
     * @param map
     * @return
     */
    public static String processCheckTemplet(Map<String, Object> map){
        Map<String,Object> root=new HashMap<String, Object>();
        root.put("retMap", map);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //下面是你定义模板的路径
        String templatesPath=request.getServletContext().getRealPath("/");
        //下面是模板的名称
        String templateFile=SpringContextUtil.getPropertiesBean().getProperty("export.createXML.cbmsCheckTemplet");
        //下面是xml页面输出路径
        String htmlFile=templatesPath+SpringContextUtil.getPropertiesBean().getProperty("export.createXML.cbmsCheckXMLName")+ DateUtil.formatDate("yyyyMMddHHmmss",new Date())+".xml";
        //根据模板生成静态页面
       boolean ret = analysisTemplate(templatesPath,templateFile,htmlFile,root);
       if(ret ){
    	   return htmlFile;
       }else{
    	   return null;
       }
    }
    
    public static boolean deleteFile(String filePath){
    	File file=new File(filePath);//输入要删除的文件位置;
    	if(file.exists()){
    		file.delete();
    		return true;
    	}else{
    		return false;
    	}
    }
}
