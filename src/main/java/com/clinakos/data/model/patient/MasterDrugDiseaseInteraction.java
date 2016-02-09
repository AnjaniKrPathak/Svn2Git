/**
 * 
 */
package com.clinakos.data.model.patient;



/**
 * @author LI-M-0004
 *
 */
public class MasterDrugDiseaseInteraction {
	
	private int id;
	private String drugID;
	private String drugName;
	private String directCondition;
	private String severityLevelText;
	private String diseaseRelation;
	private String icd9;
	private String relatedCondition;
	private String severityLevel;
	private String severityLevelShortText;
	private String diseaseRelationText;
	//for ignore button purpose
	private int patientDrugDiseaseDbId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrugID() {
		return drugID;
	}
	public void setDrugID(String drugID) {
		this.drugID = drugID;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDirectCondition() {
		return directCondition;
	}
	public void setDirectCondition(String directCondition) {
		this.directCondition = directCondition;
	}
	public String getSeverityLevelText() {
		return severityLevelText;
	}
	public void setSeverityLevelText(String severityLevelText) {
		this.severityLevelText = severityLevelText;
	}
	public String getDiseaseRelation() {
		return diseaseRelation;
	}
	public void setDiseaseRelation(String diseaseRelation) {
		this.diseaseRelation = diseaseRelation;
	}
	public String getIcd9() {
		return icd9;
	}
	public void setIcd9(String icd9) {
		this.icd9 = icd9;
	}
	public String getRelatedCondition() {
		return relatedCondition;
	}
	public void setRelatedCondition(String relatedCondition) {
		this.relatedCondition = relatedCondition;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getSeverityLevelShortText() {
		return severityLevelShortText;
	}
	public void setSeverityLevelShortText(String severityLevelShortText) {
		this.severityLevelShortText = severityLevelShortText;
	}
	public String getDiseaseRelationText() {
		return diseaseRelationText;
	}
	public void setDiseaseRelationText(String diseaseRelationText) {
		this.diseaseRelationText = diseaseRelationText;
	}
	public int getPatientDrugDiseaseDbId() {
		return patientDrugDiseaseDbId;
	}
	public void setPatientDrugDiseaseDbId(int patientDrugDiseaseDbId) {
		this.patientDrugDiseaseDbId = patientDrugDiseaseDbId;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        MasterDrugDiseaseInteraction guest = (MasterDrugDiseaseInteraction) obj;
        if(drugName.equals(guest.getDrugName())&&directCondition.equals(guest.getDirectCondition())
        		&&icd9.equals(guest.getIcd9())&&diseaseRelation.equals(guest.getDiseaseRelation())){
        	return true;
        }else{
        	return false;
        }
	}
	
	

}
