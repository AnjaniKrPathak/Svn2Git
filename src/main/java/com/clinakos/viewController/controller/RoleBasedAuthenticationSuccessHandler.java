package com.clinakos.viewController.controller;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.clinakos.common.util.CommonAuditInfo;
import com.clinakos.data.dao.IRoleBasedAuthenticationDAO;
import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.LoginSecurity;
import com.clinakos.data.model.core.ProviderUser;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.service.IRoleBasedAuthenticationService;
import com.clinakos.viewController.bean.LabManageBean;
import com.clinakos.viewController.bean.PatientMedicineManageBean;
import com.clinakos.viewController.bean.UserManageBean;



/**
 * When user succesfully login then set the role and set the userid and name in session
 *
 * @author Gopal Krishna jha from Lumbini
 
 */

public class RoleBasedAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private Map<String, String> roleUrlMap;
    private IUserDao userDAO;
    private IRoleBasedAuthenticationService roleAuthService;
    public static final Logger logger = Logger.getLogger("RoleBasedAuthenticationSuccessHandler.class");
    public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}

	public void setRoleAuthService(IRoleBasedAuthenticationService roleAuthService) {
		this.roleAuthService = roleAuthService;
	}
	
	public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
		 int userId=0;
		 HttpSession session=request.getSession();
        if (authentication.getPrincipal() instanceof UserDetails) {
        	logger.info("login  successful:::::");
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().isEmpty() ? null : userDetails.getAuthorities().toArray()[0].toString();          
            
            response.sendRedirect(request.getContextPath() + roleUrlMap.get(role));
            System.out.println("::::::::::::::::::::::::::::::::role::"+role);
            //find the userid from database
            LoginSecurity userInfo=userDAO.findUserId(userDetails.getUsername(),userDetails.getPassword());
            new CommonAuditInfo().findUSerID(userInfo,request);
           //setting the user id and user name in session 
            session.setAttribute("userId", userInfo.getId());
            session.setAttribute("userName",userDetails.getUsername());
            session.setAttribute("role", role); 
            userId=userInfo.getId();
          //selecting role based function and menu by saurabh
            Map<String,Boolean>rolePrivilegeMap=new LinkedHashMap<String, Boolean>();
            try {
            	rolePrivilegeMap=roleAuthService.selectRoleBasedPrivilegesMap(userInfo.getRole());
            	System.out.println(userInfo.getRole()+":::roleAuthService.selectRoleBasedPrivilegesMap:::"+rolePrivilegeMap.size());
            	FacesContext context = FacesContext	.getCurrentInstance();
            	session.setAttribute("rolePrivilegeMap", rolePrivilegeMap);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
            
            //setting the insurance id for login user
            System.out.println("role id:::>>>>"+userInfo.getRole()+"userId::"+userId);
           // userDAO.selectRoleBasedFunction(userInfo.getRole());
            if(userInfo.getRole()==3)
            {
            	int insuranceId=userDAO.findUserInsuranceID(userInfo.getId());
            	session.setAttribute("insuranceId", insuranceId);
            	//System.out.println(":::::insuranceId::"+insuranceId);
            	
            	
            	UserLoginDetail userLoginDetails=userDAO.findUserLoginDetail(userInfo.getId());
            	//System.out.println(":::first name::"+userLoginDetails.getFirstName()+"::"+userLoginDetails.getLastName());
            	FacesContext context = FacesContext	.getCurrentInstance();
            	 session.setAttribute("loggedUserFirstName", userLoginDetails.getFirstName());
                 session.setAttribute("loggedUserLastName",userLoginDetails.getLastName());
                 session.setAttribute("loggedUserMiddleName",userLoginDetails.getMiddleName());
                 session.setAttribute("loggedUsertimeZone", userLoginDetails.getLoginTimeZone());
                 System.out.println("loggedUsertimeZone:::::"+userLoginDetails.getLoginTimeZone());
            }
            if(userInfo.getRole()==1 || userInfo.getRole()==2 ||userInfo.getRole()==4||userInfo.getRole()==11 || userInfo.getRole()==6 || userInfo.getRole()==7 || userInfo.getRole()==10)
            {
            	//find first name and last name of logged user..
            	UserLoginDetail userLoginDetails=userDAO.findUserLoginDetail(userInfo.getId());
            	ProviderUser providerUser= userDAO.findProviderIdForAdmin(userInfo.getId());
            	//System.out.println(":::first name::"+userLoginDetails.getFirstName()+"::"+userLoginDetails.getLastName());
            	FacesContext context = FacesContext	.getCurrentInstance();
            	 session.setAttribute("loggedUserFirstName", userLoginDetails.getFirstName());
                 session.setAttribute("loggedUserLastName",userLoginDetails.getLastName());
                 session.setAttribute("loggedUserMiddleName",userLoginDetails.getMiddleName());
                 session.setAttribute("loggedUsertimeZone", userLoginDetails.getLoginTimeZone());
                 session.setAttribute("providerId", providerUser.getProviderId());
     			 session.setAttribute("providerLocationId", providerUser.getProviderLocationId());
                 System.out.println("loggedUsertimeZone:::::"+userLoginDetails.getLoginTimeZone()+":::"+providerUser.getProviderId()+":::"+providerUser.getProviderLocationId());
                 
    			//UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
    			//userManageBean.setLogedFirstName(userLoginDetails.getFirstName());
    			//userManageBean.setLogedLastName(userLoginDetails.getLastName());
            }
            
        }
       // save the user information when user  login and there ip addrresss
    	  
    	   AuditInfo auditInfo=new CommonAuditInfo().findDetaiLOFAudit(userId,"Login",request);
    	   userDAO.saveAuditValue(auditInfo);
    	   
    }
 
    public void setRoleUrlMap(Map<String, String> roleUrlMap) {
        this.roleUrlMap = roleUrlMap;
    }
    
   
}

