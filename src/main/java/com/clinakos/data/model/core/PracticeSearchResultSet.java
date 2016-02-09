/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author IDC-0004
 *
 */
public class PracticeSearchResultSet {
	
	
	private String firstName;//Patient First Name
	private String lastName;//Patient Last Name
	private Integer patientId;
	private String providerName;
	private Date date;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	@Override
	public String toString() {
		return "PracticeSearchResultSet [firstName=" + firstName
				+ ", lastName=" + lastName + ", patientId=" + patientId
				+ ", providerName=" + providerName + "]";
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
