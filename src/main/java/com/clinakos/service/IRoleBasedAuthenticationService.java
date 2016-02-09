/**
 * 
 */
package com.clinakos.service;

import java.util.Map;

/**
 * @author SAURABH
 *
 */
public interface IRoleBasedAuthenticationService {

	
	Map<String, Boolean> selectRoleBasedPrivilegesMap(int roleId);
}
