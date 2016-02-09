package com.clinakos.service.serviceImpl;

import org.springframework.ws.client.core.WebServiceTemplate;

public class WebServiceClientImpl<T> {
	
	/**
	 * Default Constructor required for Spring bean instantiation
	 * 
	 */
	public WebServiceClientImpl() {

	}

	/**
	 * Generic Method to send and receive response 
	 * @param t
	 * @param webServiceTemplate
	 * @return
	 * @throws Exception
	 */
	public Object sendRequestAndReceiveResponse(
			T t, WebServiceTemplate webServiceTemplate)
			throws Exception {
		return webServiceTemplate.marshalSendAndReceive(t);
	}

	

}
