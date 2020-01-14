package com.ucsmy.mc.util.paging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientCallbackInfo{

	private static final Logger logger = LoggerFactory.getLogger(ClientCallbackInfo.class);
	
	protected int callbackType;
	
	protected String openURL;

	public int getCallbackType()
	{
		return callbackType;
	}

	public void setCallbackType(int callbackType)
	{
		this.callbackType = callbackType;
	}

	public String getOpenURL()
	{
		return openURL;
	}

	public void setOpenURL(String openURL)
	{
		this.openURL = openURL;
	}
}
