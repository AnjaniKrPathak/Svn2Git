/**
 * 
 */
package com.clinakos.common.util;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author SAURABH
 *
 */
public class RolePrivillegeUtil {

/*	private Map<String, Boolean> rolePrivilegesMap = new LinkedHashMap<String, Boolean>();  


	public Map<String, Boolean> getRolePrivilegesMap() {
		return rolePrivilegesMap;
	}


	public void setRolePrivilegesMap(Map<String, Boolean> rolePrivilegesMap) {
		this.rolePrivilegesMap = rolePrivilegesMap;
	}*/


	/*
	 * *********added by saurabh for role privileges
	 */
	public boolean isEnable(String checkFunction) {
		//System.out.println(":::inside isPermitted method::::" + checkFunction);
		boolean enable = true;
		try {
			Map<String, Boolean> rolePrivilegesMap = new LinkedHashMap<String, Boolean>();
			rolePrivilegesMap=new ContextUtil().getRolePrivilegeMap();
			if (rolePrivilegesMap.containsKey(checkFunction)) {
				enable = rolePrivilegesMap.get(checkFunction);
				/*System.out.println(":inside if::rolePrivilegesMap.getfunctionName="
						+ rolePrivilegesMap.get(checkFunction));*/
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("=========EXCEPTION IN isEnable METHOD IN class RolePrivillegeUtil================");
		}
		
		
		//System.out.println(":::is "+checkFunction+"  enable for login user= " + enable);
		return enable;
	}
	

}
