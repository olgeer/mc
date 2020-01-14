/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * Description:数据字典根据key显示value
 * Time:2016年1月18日下午4:42:14
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DictDisplayDirective implements TemplateDirectiveModel {

	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		TemplateModel value = (TemplateModel) params.get("value");
		SimpleScalar type = (SimpleScalar) params.get("type");
		SimpleScalar style = (SimpleScalar) params.get("class"); // label class样式
		Writer out = env.getOut();
		StringBuilder builder = new StringBuilder();
		builder.append("<label ");
		if (style != null && StringUtils.isNotBlank(style.getAsString())) {
			builder.append(" class='").append(style).append("'>");
		}else{
			builder.append(" class='").append("col-md-12 form-control").append("'>");
		}
		if (value != null){
			builder.append( DictContext.getInstance().getDictValue(type.getAsString(), Byte.parseByte(value.toString().trim())));
		}
		builder.append("</label>");
		out.write(builder.toString());
	}
}