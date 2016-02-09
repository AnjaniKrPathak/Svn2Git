package com.clinakos.data.model.patient;

import java.util.Date;

public class ClinicPatientDetail {
	private int id;
	private int totalPatient;
	private ClinicProvider clinicProvider;
	private String clinicName;
	private Date date;
	
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
	 * @return the totalPatient
	 */
	public int getTotalPatient() {
		return totalPatient;
	}
	/**
	 * @param totalPatient the totalPatient to set
	 */
	public void setTotalPatient(int totalPatient) {
		this.totalPatient = totalPatient;
	}
	
	/**
	 * @return the clinicProvider
	 */
	public ClinicProvider getClinicProvider() {
		return clinicProvider;
	}
	/**
	 * @param clinicProvider the clinicProvider to set
	 */
	public void setClinicProvider(ClinicProvider clinicProvider) {
		this.clinicProvider = clinicProvider;
	}
	/**
	 * @return the clinicName
	 */
	public String getClinicName() {
		return clinicName;
	}
	/**
	 * @param clinicName the clinicName to set
	 */
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
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
	

}
