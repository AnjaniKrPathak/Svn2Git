/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;
import java.util.List;

/**
 * @author IDC-0004
 * BatchInteractionAnalytic will capture details of patient ,records who pulled and date of pull from webservices
 *
 */
public class BatchInteractionAnalytic {
	
	private int id;
	private int patientId;
	private int providerId;
	private int userId;//User who Pulled the records .
	private Date batchPullDate;//on which date records are pulled.
	//private List<BatchInteractionData> batchInteractionData;

	
	private List<BatchPatientMedsHistory>batchPatientMedsHistories;
	
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
	public Date getBatchPullDate() {
		return batchPullDate;
	}
	public void setBatchPullDate(Date batchPullDate) {
		this.batchPullDate = batchPullDate;
	}
	public List<BatchPatientMedsHistory> getBatchPatientMedsHistories() {
		return batchPatientMedsHistories;
	}
	public void setBatchPatientMedsHistories(
			List<BatchPatientMedsHistory> batchPatientMedsHistories) {
		this.batchPatientMedsHistories = batchPatientMedsHistories;
	}
	
	

}
