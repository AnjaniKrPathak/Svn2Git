package com.clinakos.data.model.patient;

public class WeeklyDose {
	private int id;
	private int patientId;
	private int providerId;
	private int doctorId;
	
	private String medicineName;
	private double drugId;
	private double drugNameId;
	private String strength;
	
	private String sundayDose;
	private String mondayDose;
	private String tuesDay;
	private String wednesdayDose;
	private String thursdayDose;
	private String fridayDose;
	private String saturdayDose;
	private String averageDose;
	
	
	
	public String getAverageDose() {
		return averageDose;
	}
	public void setAverageDose(String averageDose) {
		this.averageDose = averageDose;
	}
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
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getSundayDose() {
		return sundayDose;
	}
	public void setSundayDose(String sundayDose) {
		this.sundayDose = sundayDose;
	}
	public String getMondayDose() {
		return mondayDose;
	}
	public void setMondayDose(String mondayDose) {
		this.mondayDose = mondayDose;
	}
	public String getTuesDay() {
		return tuesDay;
	}
	public void setTuesDay(String tuesDay) {
		this.tuesDay = tuesDay;
	}
	public String getWednesdayDose() {
		return wednesdayDose;
	}
	public void setWednesdayDose(String wednesdayDose) {
		this.wednesdayDose = wednesdayDose;
	}
	public String getThursdayDose() {
		return thursdayDose;
	}
	public void setThursdayDose(String thursdayDose) {
		this.thursdayDose = thursdayDose;
	}
	public String getFridayDose() {
		return fridayDose;
	}
	public void setFridayDose(String fridayDose) {
		this.fridayDose = fridayDose;
	}
	public String getSaturdayDose() {
		return saturdayDose;
	}
	public void setSaturdayDose(String saturdayDose) {
		this.saturdayDose = saturdayDose;
	}
	
	

	

}
