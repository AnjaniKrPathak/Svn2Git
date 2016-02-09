/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class PatientDrugMapping {
	
	private int id;
	//private PatientDrugDrugInteraction patientDrugInteractionId;
	private MasterDrugDrugInteraction masterDrugDrugInteractionId;
	/*private MasterClinicalEffectsDrug clinicalEffectsId;
	private MasterServerityLevelDrug severityLevelId;*/
	private int patientID;
	private boolean status;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the masterDrugDrugInteractionId
	 */
	public MasterDrugDrugInteraction getMasterDrugDrugInteractionId() {
		if(masterDrugDrugInteractionId==null){
			masterDrugDrugInteractionId=new MasterDrugDrugInteraction();
		}
		return masterDrugDrugInteractionId;
	}
	/**
	 * @param masterDrugDrugInteractionId the masterDrugDrugInteractionId to set
	 */
	public void setMasterDrugDrugInteractionId(
			MasterDrugDrugInteraction masterDrugDrugInteractionId) {
		this.masterDrugDrugInteractionId = masterDrugDrugInteractionId;
	}
	/**
	 * @return the clinicalEffectsId
	 *//*
	public MasterClinicalEffectsDrug getClinicalEffectsId() {
		return clinicalEffectsId;
	}
	*//**
	 * @param clinicalEffectsId the clinicalEffectsId to set
	 *//*
	public void setClinicalEffectsId(MasterClinicalEffectsDrug clinicalEffectsId) {
		this.clinicalEffectsId = clinicalEffectsId;
	}
	*//**
	 * @return the severityLevelId
	 *//*
	public MasterServerityLevelDrug getSeverityLevelId() {
		return severityLevelId;
	}
	*//**
	 * @param severityLevelId the severityLevelId to set
	 *//*
	public void setSeverityLevelId(MasterServerityLevelDrug severityLevelId) {
		this.severityLevelId = severityLevelId;
	}*/
	/**
	 * @return the patientID
	 */
	public int getPatientID() {
		return patientID;
	}
	/**
	 * @param patientID the patientID to set
	 */
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
