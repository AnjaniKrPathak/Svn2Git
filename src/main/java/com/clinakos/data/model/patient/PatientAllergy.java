/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;

/**
 * @author li0008
 *
 */
public class PatientAllergy {
	
	private int id;
	private int patientId;
	private int allergytype;
	private String allergyName;
	private String allergyNotes;
	private String  allergySeverity;
	private String allergySource;
	private int compositeAllergyId;
	private int alergyConceptId;
	private String allergyConceptType;
	private int allergyConceptTypeId;
	private Date startDate;
	private Date modifyDate;
	private Date onSetDate;
	private boolean checkDbStatus; 
//------field to compare clinakos date time to new crop
	private String dateTimeForcompareAllergy;
	
	
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
	public int getAllergytype() {
		return allergytype;
	}
	public void setAllergytype(int allergytype) {
		this.allergytype = allergytype;
	}
	public String getAllergyName() {
		return allergyName;
	}
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	public String getAllergyNotes() {
		return allergyNotes;
	}
	public void setAllergyNotes(String allergyNotes) {
		this.allergyNotes = allergyNotes;
	}
	public String getAllergySeverity() {
		return allergySeverity;
	}
	public void setAllergySeverity(String allergySeverity) {
		this.allergySeverity = allergySeverity;
	}
	public String getAllergySource() {
		return allergySource;
	}
	public void setAllergySource(String allergySource) {
		this.allergySource = allergySource;
	}
	public int getCompositeAllergyId() {
		return compositeAllergyId;
	}
	public void setCompositeAllergyId(int compositeAllergyId) {
		this.compositeAllergyId = compositeAllergyId;
	}
	public int getAlergyConceptId() {
		return alergyConceptId;
	}
	public void setAlergyConceptId(int alergyConceptId) {
		this.alergyConceptId = alergyConceptId;
	}
	public String getAllergyConceptType() {
		return allergyConceptType;
	}
	public void setAllergyConceptType(String allergyConceptType) {
		this.allergyConceptType = allergyConceptType;
	}
	public int getAllergyConceptTypeId() {
		return allergyConceptTypeId;
	}
	public void setAllergyConceptTypeId(int allergyConceptTypeId) {
		this.allergyConceptTypeId = allergyConceptTypeId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Date getOnSetDate() {
		return onSetDate;
	}
	public void setOnSetDate(Date onSetDate) {
		this.onSetDate = onSetDate;
	}
	/**
	 * @return the dateTimeForcompareAllergy
	 */
	public String getDateTimeForcompareAllergy() {
		return dateTimeForcompareAllergy;
	}
	/**
	 * @param dateTimeForcompareAllergy the dateTimeForcompareAllergy to set
	 */
	public void setDateTimeForcompareAllergy(String dateTimeForcompareAllergy) {
		this.dateTimeForcompareAllergy = dateTimeForcompareAllergy;
	}
	public boolean isCheckDbStatus() {
		return checkDbStatus;
	}
	public void setCheckDbStatus(boolean checkDbStatus) {
		this.checkDbStatus = checkDbStatus;
	}
	
	
	
	
	
	
	//private String allergy;
	//private String allergyDescription="";
	//private String allergyId;
	
	
	

}
