/**
 * 
 */
package com.clinakos.data.dao;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.clinakos.data.model.core.SecurityRolePrivilege;

/**
 * @author SAURABH
 *
 */
public interface IRoleBasedAuthenticationDAO {

	public static final Logger logger = Logger.getLogger("IRoleBasedAuthenticationDAO.class");

	void selectRoleBasedMenu(String role);

	void selectRoleBasedFunction(String role);
	
	Map<String, Boolean> selectRoleBasedPrivilegesMap(int roleId);
	
}
