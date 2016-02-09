package com.clinakos.viewController.controller;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.clinakos.common.util.CommonAuditInfo;
import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.LoginSecurity;



/**
 * When user succefully logout  then set the close the session and redirect to login page  and sinsert in audit table information
 *
 * @author Gopal Krishna jha from Lumbini
 
 */
public class LogOutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
	private IUserDao userDAO;
	public static final Logger logger = Logger.getLogger("LogOutSuccessHandler.class");
	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

  	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
		
  		try{
  			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
  	
		//System.out.println("inside logout succes handler method ");
		//System.out.println("::::::::::::::user name:::"+userDetails.getUsername());
		//int  userId=userDAO.findUserId(userDetails.getUsername(),userDetails.getPassword(),request);
		 LoginSecurity userInfo=userDAO.findUserId(userDetails.getUsername(),userDetails.getPassword());
		//System.out.println("::::::::::"+userId);
        if (authentication != null) {
        	//System.out.println(":::::::::::::::::::::::::::::::::::::??????????????????"+ctx.getLoginId());
            // do something 
        	 AuditInfo auditInfo=new CommonAuditInfo().findDetaiLOFAudit(userInfo.getId(),"Logout",request);
      	     userDAO.saveAuditValue(auditInfo);
      	     logger.info("Log out successful:::::::");
        }
        setDefaultTargetUrl("/page/core/login.jsf");
        super.onLogoutSuccess(request, response, authentication);  
  		}catch(NullPointerException ne)
  		{
  			 setDefaultTargetUrl("/page/core/login.jsf");
  	        super.onLogoutSuccess(request, response, authentication);  
  	        ne.printStackTrace();
  		}
    }

}
