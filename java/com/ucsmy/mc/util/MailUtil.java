package com.ucsmy.mc.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
	private static Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	private static String host = null;
	private static String username = null;
	private static String password = null;
	private static String sendfrom = null;
	private static Session session = null;
	
	static {
		Resource resource = new ClassPathResource("/application.properties");
		try {
			Properties appProp = PropertiesLoaderUtils.loadProperties(resource);
			host = appProp.getProperty("email.host");
			username = appProp.getProperty("email.username");
			password = appProp.getProperty("email.password");
			sendfrom = appProp.getProperty("email.sendfrom");
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.debug(ex.getMessage());
		}
		
		
		Properties props = new Properties();
		//props.put("mail.debug", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.transport.protocol", "smtp");
		
		try {
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.socketFactory", sf);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		
		
		// Get session
		session = Session.getDefaultInstance(props, new Authenticator() {
			@Override  
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }  
		});
	}
	
	
	public static void sendMail(String toUser, String title, String content) {
		// Define message
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(sendfrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			
			message.setSubject(title);
			message.setText(content);
			
			// Send message
			Transport.send(message);
		} catch(Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
		}
	}
	
	
	
	public static void main(String[] args) {
		String to = "ma.wenzhong@ucsmy.com";
		String title = "Hello JavaMail";
		String content = "Welcome to JavaMail";
		
		sendMail(to, title, content);
	}
	
}
