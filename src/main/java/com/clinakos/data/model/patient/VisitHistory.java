package com.clinakos.data.model.patient;

import java.util.Date;

public class VisitHistory {
	private int id;
	private int patientId;
	private int doctorID;
	private int clinicProviderID;
	private Date visitDate;
	private String notes;
	private String patientEducation;
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
	public int getDoctorID() {
		return doctorID;
	}
	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	public int getClinicProviderID() {
		return clinicProviderID;
	}
	public void setClinicProviderID(int clinicProviderID) {
		this.clinicProviderID = clinicProviderID;
	}
	public Date getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getPatientEducation() {
		return patientEducation;
	}
	public void setPatientEducation(String patientEducation) {
		this.patientEducation = patientEducation;
	}
	
	

}
