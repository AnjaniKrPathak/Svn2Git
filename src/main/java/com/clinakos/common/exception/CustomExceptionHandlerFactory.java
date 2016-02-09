/**
 * 
 */
package com.clinakos.common.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

/**
 * @author LI0013
 *
 */
public class CustomExceptionHandlerFactory extends ExceptionHandlerFactory {
	
	private ExceptionHandlerFactory exceptionHandlerFactory;
	
	public CustomExceptionHandlerFactory(ExceptionHandlerFactory exceptionHandlerFactory)
	{
		this.exceptionHandlerFactory=exceptionHandlerFactory;
	}

	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerFactory#getExceptionHandler()
	 */
	@Override
	public ExceptionHandler getExceptionHandler() {
		// TODO Auto-generated method stub
		//System.out.println("getExceptionHandler");
		ExceptionHandler exceptionHandler=new CustomExceptionHandler(exceptionHandlerFactory.getExceptionHandler());
		return exceptionHandler;
	}

}
