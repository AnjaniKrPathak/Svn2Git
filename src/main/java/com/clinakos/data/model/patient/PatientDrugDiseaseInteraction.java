/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class PatientDrugDiseaseInteraction {
	
	private int id;
	private MasterDrugDiseaseInteraction masterDrugDiseaseInteraction;
	private int patientId;
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MasterDrugDiseaseInteraction getMasterDrugDiseaseInteraction() {
		return masterDrugDiseaseInteraction;
	}
	public void setMasterDrugDiseaseInteraction(
			MasterDrugDiseaseInteraction masterDrugDiseaseInteraction) {
		this.masterDrugDiseaseInteraction = masterDrugDiseaseInteraction;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
