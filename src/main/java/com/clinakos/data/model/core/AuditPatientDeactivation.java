/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * To record which user deactivated which patient on that day
 * @author IDC-0004
 *
 */
public class AuditPatientDeactivation {
	
	private int id;
	private int patientId;
	private int providerId;
	private int userId;//User who deactivated the patient Id
	private Date deactivatedDate;
	
	private String patientFirstName;
	private String patientLastName;
	private String adminFirstName;
	private String adminLastName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Date deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public String getAdminFirstName() {
		return adminFirstName;
	}
	public void setAdminFirstName(String adminFirstName) {
		this.adminFirstName = adminFirstName;
	}
	public String getAdminLastName() {
		return adminLastName;
	}
	public void setAdminLastName(String adminLastName) {
		this.adminLastName = adminLastName;
	}
	
	

}
