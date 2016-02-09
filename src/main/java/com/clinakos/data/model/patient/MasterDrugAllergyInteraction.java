/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class MasterDrugAllergyInteraction {
	
	private int id;
	private String interactionText;
	private String drugID;
	private String drugName;
	private String compositeAllergyID;
	private String conceptType;
	private String drugType;
	private String source;
	private String conceptId;
	private String description;
	private String performance;
	//for ignore purpose
	private int patientDrugAllergyDbId;
	private String severityLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInteractionText() {
		return interactionText;
	}
	public void setInteractionText(String interactionText) {
		this.interactionText = interactionText;
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
	public String getCompositeAllergyID() {
		return compositeAllergyID;
	}
	public void setCompositeAllergyID(String compositeAllergyID) {
		this.compositeAllergyID = compositeAllergyID;
	}
	public String getConceptType() {
		return conceptType;
	}
	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}
	public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getConceptId() {
		return conceptId;
	}
	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        MasterDrugAllergyInteraction guest = (MasterDrugAllergyInteraction) obj;
        if(interactionText.equals(guest.getInteractionText())){
        	return true;
        }else{
        	return false;
        }
	}
	public int getPatientDrugAllergyDbId() {
		return patientDrugAllergyDbId;
	}
	public void setPatientDrugAllergyDbId(int patientDrugAllergyDbId) {
		this.patientDrugAllergyDbId = patientDrugAllergyDbId;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	
	

}
