/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;

/**
 * @author li0008
 *
 */
public class PatientMedicineNotes {

	private int id;
	private int patientId;
	private String drugName;
	private String notes;
	private Date date;
	private String patientEducation;
	
	
	
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
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}
	/**
	 * @param drugName the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/*Added by venu to clear the data while clicking reset button in notes section in patient portal */
	public void clear()
	{
		notes="";
	}
	public void setPatientEducation(String patientEducation) {
		this.patientEducation = patientEducation;
	}
	public String getPatientEducation() {
		return patientEducation;
	}
	
	
	
	}
