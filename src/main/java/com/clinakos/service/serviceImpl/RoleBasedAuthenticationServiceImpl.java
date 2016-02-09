/**
 * 
 */
package com.clinakos.service.serviceImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.clinakos.common.util.RolePrivillegeUtil;
import com.clinakos.data.dao.IRoleBasedAuthenticationDAO;
import com.clinakos.data.model.core.SecurityRolePrivilege;
import com.clinakos.service.IRoleBasedAuthenticationService;

/**
 * @author SAURABH
 *
 */
public class RoleBasedAuthenticationServiceImpl implements IRoleBasedAuthenticationService , Serializable {

	private static final long serialVersionUID = -3583677223847684893L;
	
	private IRoleBasedAuthenticationDAO roleAuthDAO;
	RolePrivillegeUtil roleUtil=new RolePrivillegeUtil();
	
	public RolePrivillegeUtil getRoleUtil() {
		return roleUtil;
	}

	public void setRoleUtil(RolePrivillegeUtil roleUtil) {
		this.roleUtil = roleUtil;
	}

	public IRoleBasedAuthenticationDAO getRoleAuthDAO() {
		return roleAuthDAO;
	}

	public void setRoleAuthDAO(IRoleBasedAuthenticationDAO roleAuthDAO) {
		this.roleAuthDAO = roleAuthDAO;
	}
	
	
	//Map<String, Boolean> roleBasedPrivilegeMap=new HashMap<String,Boolean>();
	
	
/**
	 * @return the roleBasedPrivilegeMap
	 */
	/*public Map<String, Boolean> getRoleBasedPrivilegeMap() {
		return roleBasedPrivilegeMap;
	}*/

	/**
	 * @param roleBasedPrivilegeMap the roleBasedPrivilegeMap to set
	 * @return 
	 */
	/*public void setRoleBasedPrivilegeMap(Map<String, Boolean> roleBasedPrivilegeMap) {
		this.roleBasedPrivilegeMap = roleBasedPrivilegeMap;
	}*/


	
/*
 * added by saurabh
 * (non-Javadoc)
 * @see com.clinakos.service.IDashBoardService#selectRoleBasedPrivilegesMap()
 */
				public Map<String, Boolean> selectRoleBasedPrivilegesMap(int roleId) {
					System.out.println("::::::selectRoleBasedPrivilegesMap called in service:::::");
					Map<String,Boolean>rolePrivilegeMap=new LinkedHashMap<String, Boolean>();
					rolePrivilegeMap=roleAuthDAO.selectRoleBasedPrivilegesMap(roleId);
					return rolePrivilegeMap;
				}

}
