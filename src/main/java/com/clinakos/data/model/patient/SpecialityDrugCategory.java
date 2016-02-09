package com.clinakos.data.model.patient;

public class SpecialityDrugCategory {

	private int id;
	private int medCountForDrugCategory;
	private String drugCategory;
	private String medName;
	private int patientCountForDrugCategory;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMedCountForDrugCategory() {
		return medCountForDrugCategory;
	}
	public void setMedCountForDrugCategory(int medCountForDrugCategory) {
		this.medCountForDrugCategory = medCountForDrugCategory;
	}
	public String getDrugCategory() {
		return drugCategory;
	}
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public int getPatientCountForDrugCategory() {
		return patientCountForDrugCategory;
	}
	public void setPatientCountForDrugCategory(int patientCountForDrugCategory) {
		this.patientCountForDrugCategory = patientCountForDrugCategory;
	}
	
	
	
}
