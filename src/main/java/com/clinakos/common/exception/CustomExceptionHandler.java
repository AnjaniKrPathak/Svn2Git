/**
 * 
 */
package com.clinakos.common.exception;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.ejb.spi.HandleDelegate;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.hibernate.mapping.Map;

import com.google.inject.internal.FinalizablePhantomReference;
import com.sun.faces.context.RequestMap;

/**
 * @author LI0013
 *
 */
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private static final Logger log=Logger.getLogger(CustomExceptionHandler.class);
	private ExceptionHandler wrapped;
	
	
	
	
	/**
	 * @param handler
	 */
	public CustomExceptionHandler(ExceptionHandler handler) {
		super();
		this.wrapped = handler;
	}


	/* (non-Javadoc)
	 * @see javax.faces.context.ExceptionHandlerWrapper#getWrapped()
	 */
	@Override
	public ExceptionHandler getWrapped() {
		// TODO Auto-generated method stub
		return wrapped;
	}
	
	public void handle() throws FacesException
	{
	  for(Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator();it.hasNext();){
	   
	    	ExceptionQueuedEvent exceptionQueuedEvent=it.next();
	    	System.out.println("Iterating over ExceptionQueuedEvents.current::::"+exceptionQueuedEvent.toString());
	    	ExceptionQueuedEventContext exceptionQueuedEventContext=(ExceptionQueuedEventContext) exceptionQueuedEvent.getSource();
	    	Throwable throwable=exceptionQueuedEventContext.getException();
	    	if( throwable instanceof Throwable) {
	    		Throwable t=(Throwable)throwable;
	    		FacesContext facesContext=FacesContext.getCurrentInstance();
	    		java.util.Map<String, Object> requestMap=facesContext.getExternalContext().getRequestMap();
	    		NavigationHandler navigationHandler=facesContext.getApplication().getNavigationHandler();
	    		try{
	    			
	    			requestMap.put("currentView", t.getMessage());
	    			
	    			facesContext.getExternalContext().getFlash().put("exceptioniNFO", t.getCause());
	    			
	    			
	    			System.out.println("Throwable cause::::=="+t.getCause());
	    			System.out.println("::::::::::"+t.getMessage());
	    			//t.printStackTrace();
	    			navigationHandler.handleNavigation(facesContext, null, "/error?faces-redirect=true");
	    			String errorPageLocation="/page/core/error.jsf";
	    			facesContext.setViewRoot(facesContext.getApplication().getViewHandler().createView(facesContext, errorPageLocation));
	    			facesContext.getPartialViewContext().setRenderAll(true);
	    			facesContext.renderResponse();
	    		}
	    		catch(Throwable t1)
	    		{
	    			System.out.println("inside catch block:::");
	    			t1.printStackTrace();
	    		}
	    		finally{
	    			it.remove();
	    		}
	    		
	    	}
	    	getWrapped().handle();
	  }
	  
	}
}
	
	
	
				
				
					
					
				
		
			
	    	
				
	  
	
	


