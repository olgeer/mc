package com.ucsmy.mc.util.paging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("serial")
public class HandlerResult implements java.io.Serializable
{
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(HandlerResult.class);

	ResultState resultState;

	protected Object resultObj;

	protected Page page;

	protected ClientCallbackInfo clientCallbackInfo;

	public ResultState getResultState() {
		return resultState;
	}

	public void setResultState(ResultState resultState) {
		this.resultState = resultState;
	}

	public HandlerResult(){}

	public ClientCallbackInfo getClientCallbackInfo()
	{
		return clientCallbackInfo;
	}

	public void setClientCallbackInfo(ClientCallbackInfo clientCallbackInfo)
	{
		this.clientCallbackInfo = clientCallbackInfo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Object getResultObj() {
		return resultObj;
	}

	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}

}
