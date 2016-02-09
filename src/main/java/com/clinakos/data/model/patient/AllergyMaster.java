package com.clinakos.data.model.patient;

public class AllergyMaster {
	private int id;
	private String allergyName;
	private String status;
	private String allergyType;
	private String compositeAllergyId;
	private String allergySourceId;
	private String allergyConceptId;
	private String conceptType;
	//private String source;
	

	
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
	 * @return the allergyName
	 */
	public String getAllergyName() {
		return allergyName;
	}
	/**
	 * @param allergyName the allergyName to set
	 */
	public void setAllergyName(String allergyName) {
		this.allergyName = allergyName;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the allergyType
	 */
	public String getAllergyType() {
		return allergyType;
	}
	/**
	 * @param allergyType the allergyType to set
	 */
	public void setAllergyType(String allergyType) {
		this.allergyType = allergyType;
	}
	/**
	 * @return the compositeAllergyId
	 */
	public String getCompositeAllergyId() {
		return compositeAllergyId;
	}
	/**
	 * @param compositeAllergyId the compositeAllergyId to set
	 */
	public void setCompositeAllergyId(String compositeAllergyId) {
		this.compositeAllergyId = compositeAllergyId;
	}
	/**
	 * @return the allergySourceId
	 */
	public String getAllergySourceId() {
		return allergySourceId;
	}
	/**
	 * @param allergySourceId the allergySourceId to set
	 */
	public void setAllergySourceId(String allergySourceId) {
		this.allergySourceId = allergySourceId;
	}
	/**
	 * @return the allergyConceptId
	 */
	public String getAllergyConceptId() {
		return allergyConceptId;
	}
	/**
	 * @param allergyConceptId the allergyConceptId to set
	 */
	public void setAllergyConceptId(String allergyConceptId) {
		this.allergyConceptId = allergyConceptId;
	}
	/**
	 * @return the conceptType
	 */
	public String getConceptType() {
		return conceptType;
	}
	/**
	 * @param conceptType the conceptType to set
	 */
	public void setConceptType(String conceptType) {
		this.conceptType = conceptType;
	}
	

}
