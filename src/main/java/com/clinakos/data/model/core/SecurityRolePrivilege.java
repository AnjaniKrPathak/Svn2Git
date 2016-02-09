/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author SAURABH	
 *
 */
public class SecurityRolePrivilege {
  private int id;
  //private int securityPrivillegeId;
  private int roleId;
  private boolean permission;
  private MasterFunctionMenu masterFunctionMenu;
  
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getRoleId() {
	return roleId;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public boolean isPermission() {
	return permission;
}
public void setPermission(boolean permission) {
	this.permission = permission;
}
public MasterFunctionMenu getMasterFunctionMenu() {
	return masterFunctionMenu;
}
public void setMasterFunctionMenu(MasterFunctionMenu masterFunctionMenu) {
	this.masterFunctionMenu = masterFunctionMenu;
}

	
}
