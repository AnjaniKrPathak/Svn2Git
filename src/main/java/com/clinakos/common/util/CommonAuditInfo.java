package com.clinakos.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.LoginSecurity;

public class CommonAuditInfo {
	public static final Logger logger = Logger.getLogger("CommonAuditInfo.class");

	
	/*
	 * user entry save in databse i,e when user login, logout, any update by user 
	 * code developed by Gopal Krishan jha fro LI
	 * (non-Javadoc)
	 */
	public AuditInfo findDetaiLOFAudit(int userId, String message,
			HttpServletRequest request) {
		
		AuditInfo auditInfo=new AuditInfo();
		auditInfo.setCreateTime(new Date());
		auditInfo.setUserId(userId);
		auditInfo.setComment(message);
		
			String ipAddress = request.getHeader("X-FORWARDED-FOR");  
			//System.out.println(":1:::::::::::::::::::??????? ipAddress"+ipAddress+"request.getRemoteHost()"+request.getPathInfo());
		
			if (ipAddress == null) {  
				ipAddress = request.getRemoteAddr();  
							}
			
			//System.out.println("::::::::::::::::::::??????? ipAddress"+ipAddress+"request.getRemoteHost()"+request.getRemoteHost());
			if(ipAddress.equals("0:0:0:0:0:0:0:1") || ipAddress.equals("127.0.0.1"))
			{
				try {
					InetAddress ownIP=InetAddress.getLocalHost();
					//System.out.println(":::::::::::ownIP =="+ownIP+"::ownIP.getHostAddress()"+ownIP.getHostAddress()+"getHostName:::"+ownIP.getHostName()+"::"+ownIP.getCanonicalHostName());
					ipAddress=ownIP.getHostAddress();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					logger.error("unknown host", e);
					e.printStackTrace();
				}
			}
			
			try {
				auditInfo.setMachineName(InetAddress.getByName(ipAddress).getHostName());
				//System.out.println("::::::::::::::host name:::::"+auditInfo.getMachineName());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			auditInfo.setIpAddress(ipAddress);
		return auditInfo;
	}


	public void findUSerID(LoginSecurity userInfo, HttpServletRequest request) {
		if (userInfo.getRole()==3) {
			//System.out.println("hi i m patient:::::::"+userId);
			//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			HttpSession session = request.getSession();
			session.setAttribute("patientId",userInfo.getId());
		//}
		
	}
	
	
	}
}
