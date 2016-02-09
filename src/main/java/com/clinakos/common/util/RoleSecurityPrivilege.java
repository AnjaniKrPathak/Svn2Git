/**
 * 
 */
package com.clinakos.common.util;

/**
 * @author IDC-0004
 *
 */
public enum RoleSecurityPrivilege {

	ROLE_ADMIN(1),
	ROLE_DOCTOR(2),
	ROLE_PATIENT(3),
	ROLE_NURSE(4),
	ROLE_MEDICAL_ASSISTANT(7),
	ROLE_SUPER_ADMIN(10),
	ROLE_PHARMA(11);

	private int value;

	public int getValue() {
		return value;
	}

	private RoleSecurityPrivilege(int value) {
		this.value = value;
	}


	
	
}
