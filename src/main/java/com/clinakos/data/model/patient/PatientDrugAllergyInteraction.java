/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class PatientDrugAllergyInteraction {
	
	private int id;
	private MasterDrugAllergyInteraction masterDrugAllergyInteraction;
	private int patientID;
	private boolean status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MasterDrugAllergyInteraction getMasterDrugAllergyInteraction() {
		return masterDrugAllergyInteraction;
	}
	public void setMasterDrugAllergyInteraction(
			MasterDrugAllergyInteraction masterDrugAllergyInteraction) {
		this.masterDrugAllergyInteraction = masterDrugAllergyInteraction;
	}
	public int getPatientID() {
		return patientID;
	}
	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
