package com.clinakos.data.model.core;

public class AnticoagProviderLocation {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int patientid;
	private String result;
	private int procedureType ;
	private int providerId;
	private String medicinestage;
	private String date;
	private String first_name;
	private String middle_name;
	private String last_name;
	private String date_of_birth;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getProcedureType() {
		return procedureType;
	}
	public void setProcedureType(int procedureType) {
		this.procedureType = procedureType;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	/**
	 * @return the medicinestage
	 */
	public String getMedicinestage() {
		return medicinestage;
	}
	/**
	 * @param medicinestage the medicinestage to set
	 */
	public void setMedicinestage(String medicinestage) {
		this.medicinestage = medicinestage;
	}
	/**
	 * @return the patientid
	 */
	public int getPatientid() {
		return patientid;
	}
	/**
	 * @param patientid the patientid to set
	 */
	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + patientid;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnticoagProviderLocation other = (AnticoagProviderLocation) obj;
		if (patientid != other.patientid)
			return false;
		return true;
	}
	
	
	

}
