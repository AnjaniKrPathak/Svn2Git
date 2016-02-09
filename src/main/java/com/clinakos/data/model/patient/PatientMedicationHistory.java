/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;

/**
 * @author LI-0011
 *
 */
public class PatientMedicationHistory {
	
	private int id;
	private int patientId;
	private int providerId;
	private String drugs;
	//private String fomula;
	//private String signature;
	private String quantity;
	private String notes;
	private int reffils;
	private Date startDate;
	private Date lastFillDate;
	private String pharmacyName;
	private String medicineStatus;
	
	private String strength;
	private String direction;
	private int prescriberID;
	private String prescriberName;
	
	private double drugId;
	private String dataProvider;
	private double drugNameId;
	
	private String unit;
	private String dosageForm;
	private String byRoute;
	private String unitDetail;
	
	private String prescriptionGuid;
	private String prescriptionStatus;
	private String prescriptionSubStatus;
	private boolean flagForVisit;
	private Date actionDate;
	private String dateTimeZoneForCompare;
	
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
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getReffils() {
		return reffils;
	}
	public void setReffils(int reffils) {
		this.reffils = reffils;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getLastFillDate() {
		return lastFillDate;
	}
	public void setLastFillDate(Date lastFillDate) {
		this.lastFillDate = lastFillDate;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getMedicineStatus() {
		return medicineStatus;
	}
	public void setMedicineStatus(String medicineStatus) {
		this.medicineStatus = medicineStatus;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public int getPrescriberID() {
		return prescriberID;
	}
	public void setPrescriberID(int prescriberID) {
		this.prescriberID = prescriberID;
	}
	public String getPrescriberName() {
		return prescriberName;
	}
	public void setPrescriberName(String prescriberName) {
		this.prescriberName = prescriberName;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getByRoute() {
		return byRoute;
	}
	public void setByRoute(String byRoute) {
		this.byRoute = byRoute;
	}
	public String getUnitDetail() {
		return unitDetail;
	}
	public void setUnitDetail(String unitDetail) {
		this.unitDetail = unitDetail;
	}
	public String getPrescriptionGuid() {
		return prescriptionGuid;
	}
	public void setPrescriptionGuid(String prescriptionGuid) {
		this.prescriptionGuid = prescriptionGuid;
	}
	public String getPrescriptionStatus() {
		return prescriptionStatus;
	}
	public void setPrescriptionStatus(String prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}
	public String getPrescriptionSubStatus() {
		return prescriptionSubStatus;
	}
	public void setPrescriptionSubStatus(String prescriptionSubStatus) {
		this.prescriptionSubStatus = prescriptionSubStatus;
	}
	public boolean isFlagForVisit() {
		return flagForVisit;
	}
	public void setFlagForVisit(boolean flagForVisit) {
		this.flagForVisit = flagForVisit;
	}
	public Date getActionDate() {
		return actionDate;
	}
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	public String getDateTimeZoneForCompare() {
		return dateTimeZoneForCompare;
	}
	public void setDateTimeZoneForCompare(String dateTimeZoneForCompare) {
		this.dateTimeZoneForCompare = dateTimeZoneForCompare;
	}
	

	
	
}
