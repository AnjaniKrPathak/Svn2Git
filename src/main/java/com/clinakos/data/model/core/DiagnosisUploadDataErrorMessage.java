/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author IDC-0022
 *
 */
public class DiagnosisUploadDataErrorMessage {

	private int id;
	private String externalId;
	private String errorMessage;
	private int uploadedBy;
	private Date uploadedDate;
	private String clinakosId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(int uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public String getClinakosId() {
		return clinakosId;
	}
	public void setClinakosId(String clinakosId) {
		this.clinakosId = clinakosId;
	}
	
}
