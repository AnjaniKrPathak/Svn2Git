package com.clinakos.common.util;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


public class ContextUtil {
	 private int loginId;
	 private String loginUserName;
	 private int patientId;
	 private String loggedUsertimeZone;
	// private String patientLoginId;
	 private int providerId;
	 private String providerName;
	 private int clinicId;
	 private String clinicName;
	 private int clinicProviderId;
	 private int insuranceId;
	 private String loggerFirstName;
	 private String loggerLastName;
	private String loggedUserMiddleName;
//----------------------added by saurabh for role admin/superAdmin
	private Map<String,Boolean> rolePrivilegeMap;//= new linkedha
	private int providerLocationId;
	private String role;
	
	
	 public int getLoginId() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = 
		     (HttpSession) context.getExternalContext().getSession(true);
		    
		    return (Integer) session.getAttribute("userId");
		
		//return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	public String getLoginUserName() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = 
		     (HttpSession) context.getExternalContext().getSession(true);
		    
		    return (String) session.getAttribute("userName");
		
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	public int getPatientId() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session =(HttpSession) context.getExternalContext().getSession(true);
		    return (Integer) session.getAttribute("patientId");
		
		//return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/*public String getPatientLoginId() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session =(HttpSession) context.getExternalContext().getSession(true);
	    return (String) session.getAttribute("PatientLoginId");
		//return patientLoginId;
	}
	public void setPatientName(String patientName) {
		this.patientLoginId = patientName;
	}*/
	public int getProviderId() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = 
		     (HttpSession) context.getExternalContext().getSession(true);
		    
		    return (Integer) session.getAttribute("providerId");
		//return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public int getClinicId() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = 
		     (HttpSession) context.getExternalContext().getSession(true);
		    
		    return (Integer) session.getAttribute("clinicId");
		//return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public int getClinicProviderId() {
		 FacesContext context = FacesContext.getCurrentInstance();
		    HttpSession session = 
		     (HttpSession) context.getExternalContext().getSession(true);
		    
		    return (Integer) session.getAttribute("clinicproviderId");
		//return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public int getInsuranceId() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    Integer insuranceiD=(Integer) session.getAttribute("insuranceId");
	    try{
		    if((insuranceiD==null))
		    {
		    	insuranceiD=0;
		    }
	    }
	    catch (NullPointerException e) {
			e.getMessage();
			insuranceiD=0;
		}
	    return insuranceiD;
		//return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getLoggerFirstName() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    
	    return (String) session.getAttribute("loggedUserFirstName");
		//return loggerFirstName;
	}
	public void setLoggerFirstName(String loggerFirstName) {
		this.loggerFirstName = loggerFirstName;
	}
	public String getLoggerLastName() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    
	    return (String) session.getAttribute("loggedUserLastName");
		//return loggerLastName;
	}
	public void setLoggerLastName(String loggerLastName) {
		this.loggerLastName = loggerLastName;
	}
	public String getLoggedUserMiddleName() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    
	    return (String) session.getAttribute("loggedUserMiddleName");
		//return loggedUserMiddleName;
	}
	public void setLoggedUserMiddleName(String loggedUserMiddleName) {
		this.loggedUserMiddleName = loggedUserMiddleName;
	}
	public String getLoggedUsertimeZone() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    
	   // return (Integer) session.getAttribute("userId");
	return (String) session.getAttribute("loggedUsertimeZone");
	}
	public void setLoggedUsertimeZone(String loggedUsertimeZone) {
		this.loggedUsertimeZone = loggedUsertimeZone;
	}
	public Map<String, Boolean> getRolePrivilegeMap() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    
		return (Map<String, Boolean>) session.getAttribute("rolePrivilegeMap");
	}
	public void setRolePrivilegeMap(Map<String, Boolean> rolePrivilegeMap) {
		this.rolePrivilegeMap = rolePrivilegeMap;
	}
	public int getProviderLocationId() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
		return (Integer) session.getAttribute("providerLocationId");
	}
	public void setProviderLocationId(int providerLocationId) {
		this.providerLocationId = providerLocationId;
	}
	public String getRole() {
		FacesContext context = FacesContext.getCurrentInstance();
	    HttpSession session = 
	     (HttpSession) context.getExternalContext().getSession(true);
	    return (String) session.getAttribute("role");
	}
	public void setRole(String role) {
		this.role = role;
	}

	 
	 
	 
	 
}
