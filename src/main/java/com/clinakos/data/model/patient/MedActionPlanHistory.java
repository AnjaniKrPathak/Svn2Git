package com.clinakos.data.model.patient;

import java.util.Date;

public class MedActionPlanHistory {
	private int id;
	private int patientId;
	private int doctorId;
	private int clinicProviderID;
	private String medicineName;
	private String dose;
	private String notes;
	private String diagnosis;
	private String lab;
	private String acceptableRange;
	private String labFrequency;
	private String diet;
	private String regimen;
	private Date actionDate;
	private Date nextLabDate;
	private String patientNotes;
	

	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}
	public String getAcceptableRange() {
		return acceptableRange;
	}
	public void setAcceptableRange(String acceptableRange) {
		this.acceptableRange = acceptableRange;
	}
	public String getLabFrequency() {
		return labFrequency;
	}
	public void setLabFrequency(String labFrequency) {
		this.labFrequency = labFrequency;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getClinicProviderID() {
		return clinicProviderID;
	}
	public void setClinicProviderID(int clinicProviderID) {
		this.clinicProviderID = clinicProviderID;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public Date getNextLabDate() {
		return nextLabDate;
	}
	public void setNextLabDate(Date nextLabDate) {
		this.nextLabDate = nextLabDate;
	}
	public String getPatientNotes() {
		return patientNotes;
	}
	public void setPatientNotes(String patientNotes) {
		this.patientNotes = patientNotes;
	}
	

}
