/**
 * 
 */
package com.clinakos.viewController.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;


/**
 * @author SAURABH
 *
 */

@Component
public class LoginFilter implements Filter{

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException 
	 {

	  String url = ((HttpServletRequest)request).getRequestURL().toString();
	  String queryString = ((HttpServletRequest)request).getQueryString();
	  String result = java.net.URLDecoder.decode(url, "UTF-8");
	  HttpServletRequest httpReq = (HttpServletRequest) request;
	  HttpServletResponse httpResp = (HttpServletResponse) response;
	  
	  /*if (httpReq.isRequestedSessionIdValid())
	  {*/
	  
	     HttpSession session = httpReq.getSession();
	     //System.out.println("::::: url for invalidate session:::: "+url);
	     if(url.endsWith("clinakos/") || url.endsWith("login.jsf"))
	     {
	      System.out.println("inside if url checking for clinakos/::: login filter");
	      session.invalidate();//invalidate session
	     }
	     
	     filter.doFilter(request, response);
	  /*}*/
	   
	 }

	 public void init(FilterConfig arg0){
	 }
	    
	 public void destroy() {
	 }
}
