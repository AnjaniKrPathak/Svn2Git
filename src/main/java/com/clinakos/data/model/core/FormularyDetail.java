package com.clinakos.data.model.core;

import org.primefaces.model.SelectableDataModel;


public class FormularyDetail implements SelectableDataModel<FormularyDetail>{
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int medicineId;
	private int insuranceId;
	private String formulayTier;
	private String patientCopay;
	private String medicineName;
	private String isFormulary;
	private String therapyType;
	private String alternateMedicine;
	private String saving;
	private String medStrength;
	private String pharmacyTypeDesc;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFormulayTier() {
		return formulayTier;
	}
	public void setFormulayTier(String formulayTier) {
		this.formulayTier = formulayTier;
	}
	public String getPatientCopay() {
		return patientCopay;
	}
	public void setPatientCopay(String patientCopay) {
		this.patientCopay = patientCopay;
	}
	public int getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	
	
	public String getIsFormulary() {
		return isFormulary;
	}
	public void setIsFormulary(String isFormulary) {
		this.isFormulary = isFormulary;
	}
	
	public FormularyDetail getRowData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Object getRowKey(FormularyDetail arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getTherapyType() {
		return therapyType;
	}
	public void setTherapyType(String therapyType) {
		this.therapyType = therapyType;
	}
	public String getAlternateMedicine() {
		return alternateMedicine;
	}
	public void setAlternateMedicine(String alternateMedicine) {
		this.alternateMedicine = alternateMedicine;
	}
	public String getSaving() {
		return saving;
	}
	public void setSaving(String saving) {
		this.saving = saving;
	}
	/**
	 * @return the medStrength
	 */
	public String getMedStrength() {
		return medStrength;
	}
	/**
	 * @param medStrength the medStrength to set
	 */
	public void setMedStrength(String medStrength) {
		this.medStrength = medStrength;
	}
	/**
	 * @return the pharmacyTypeDesc
	 */
	public String getPharmacyTypeDesc() {
		return pharmacyTypeDesc;
	}
	/**
	 * @param pharmacyTypeDesc the pharmacyTypeDesc to set
	 */
	public void setPharmacyTypeDesc(String pharmacyTypeDesc) {
		this.pharmacyTypeDesc = pharmacyTypeDesc;
	}
	
	

}
