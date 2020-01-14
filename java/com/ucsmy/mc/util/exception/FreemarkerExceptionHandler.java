package com.ucsmy.mc.util.exception;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
	private Logger log = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);
	
    public void handleTemplateException(TemplateException te, Environment env,
                                        Writer out) throws TemplateException {
    	
            log.error("[Freemarker Error: " + te.getMessage() + "]");
            throw new RuntimeException("freemarker error", te);
    }
}