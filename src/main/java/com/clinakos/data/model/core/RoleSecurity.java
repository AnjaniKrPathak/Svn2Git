package com.clinakos.data.model.core;

public class RoleSecurity {
	
	private int id;
	private String roleName;
	private boolean active;
	private String newCropRole;
	private String roleType;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getNewCropRole() {
		return newCropRole;
	}
	public void setNewCropRole(String newCropRole) {
		this.newCropRole = newCropRole;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	
	
	
}
