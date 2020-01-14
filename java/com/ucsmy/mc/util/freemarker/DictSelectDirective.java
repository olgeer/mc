/**
 * Copyright (c) 2016
 * Company:广东网金控股股份有限公司(http://www.ucsmy.com)
 * All rights reserved.
 */
package com.ucsmy.mc.util.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * Description:select下拉框FreeMarker自定义指令
 * Time:2016年1月18日下午4:41:24
 * @version 1.0
 * @since 1.0
 * @author chenchengteng
 */
public class DictSelectDirective implements TemplateDirectiveModel {

	@Override
	public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		SimpleScalar id = (SimpleScalar) params.get("id"); // select id
		SimpleScalar name = (SimpleScalar) params.get("name"); // select name
		SimpleScalar type = (SimpleScalar) params.get("type"); // 数据字典类型
		TemplateModel value = (TemplateModel)params.get("value"); // 数据字典值
		SimpleScalar style = (SimpleScalar) params.get("class"); // select class样式
		TemplateBooleanModel showSelect = (TemplateBooleanModel) params.get("showSelect"); // 是否显示请选择option
		SimpleScalar option = (SimpleScalar) params.get("option"); //默认的第一个显示文本，如果为空，显示"请选择"，否则按此值显示
		SimpleScalar triggerConfig = (SimpleScalar)params.get("triggerConfig");
		Writer out = env.getOut();
		if (type != null) {
			StringBuilder builder = new StringBuilder();
			builder.append("<select id='");
			if (id != null && StringUtils.isNotBlank(id.getAsString())) {
				builder.append(id);
			} else if (name != null && StringUtils.isNotBlank(name.getAsString())) {
				builder.append(name);
			}
			builder.append("'");
			if (name != null && StringUtils.isNotBlank(name.getAsString())) {
				builder.append(" name='").append(name).append("'");
			}
			if (style != null && StringUtils.isNotBlank(style.getAsString())) {
				builder.append(" class='").append(style).append("'");
			}else{
				builder.append(" class='").append("bs-select form-control").append("'");
			}
			if (triggerConfig != null && StringUtils.isNotBlank(triggerConfig.getAsString())) {
				builder.append(" trigger='").append(triggerConfig.getAsString()).append("'");
			}
			builder.append(">");
			if (showSelect == null || showSelect.getAsBoolean() != false) {
				builder.append("<option value=''>");
				// 如果默认显示值不为空，即需要显示特定的文本，则加入option值
				if(option != null && StringUtils.isNotBlank(option.getAsString()))
					builder.append(option);
				else{
					if(style != null && StringUtils.equals("form-control form-filter input-sm", style.getAsString())){
						builder.append("全部");
					}else{
						builder.append("请选择");
					}
				}
				builder.append("</option>");
			}
			
			List<Dict> dictList = DictContext.getInstance().getDict(type.getAsString());
			if (dictList != null) {
				int values = -100;
				if(value != null){
					if(value instanceof SimpleNumber){
						values = ((SimpleNumber)value).getAsNumber().intValue();
					}else if(value instanceof SimpleScalar){
						values = Integer.valueOf(((SimpleScalar)value).getAsString());
					}
				}
				for (Dict dict : dictList) {
					builder.append("<option value='").append(dict.getCode()).append("'");
					if (values == dict.getCode())
						builder.append(" selected");
					builder.append(">").append(dict.getValue()).append("</option>");
				}
			}
			builder.append("</select>");
			out.write(builder.toString());
		}
	}
}
