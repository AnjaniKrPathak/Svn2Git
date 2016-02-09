
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with Clinic Questionnaire Details
 */

package com.clinakos.data.model.patient;

public class ClinicQuestionnaire {

	private int id;
	private int clinicProviderId;
	private String questionDetails;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicProviderId() {
		return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public String getQuestionDetails() {
		return questionDetails;
	}
	public void setQuestionDetails(String questionDetails) {
		this.questionDetails = questionDetails;
	}
	
	
	
	
	

}
