package com.clinakos.service;

import org.springframework.ws.client.core.WebServiceTemplate;

public interface WebServiceClient<T> {
	
	/**
	 * Send manageMemberRequest to server, this method should only take a entire
	 * Request Object.
	 * 
	 * @param manageMemberCardRequest
	 * @return
	 */
	public Object sendManageMemberCardRequestAndReceiveResponse(
			T t,
			WebServiceTemplate webServiceTemplate) throws Exception;

	

}
