package com.clinakos.data.model.patient;

import java.util.Date;

import javax.persistence.Column;

public class MedandGenricmed {
	private int id;
	private int patientId;
	private int doctorid;
	private int clinicproviderid;
	private String medicinename;
	private String medicinenameforgraph;
	private String lastupdateby;
	private String createdby;
	private Date lastupdatedate;
	private Date createdate;
	private String labfrequency;
	private String acceptablerange;
	private Date nextLabDate;
	private String drugId;
	private String lab;
	private String dose;
	private Double drugNameId;
	private Date dosingStartDate;
	private int labParameterId;
	
	private String frequencyUnit;
	
	private int frequencyNumber;
	
	
	private String goalLowRangeSymbol;
	
	
	private String goalLowRangeValue;
	
	
	private String goalHighRangeSymbol;
	
	
	private String goalHighRangeValue;
	
	
	private String alertSevereLowRangeSymbol;
	
	
	private String alertSevereLowRangeValue;
	
	
	private String alertSevereHighRangeSymbol;
	

	private String alertSevereHighRangeValue;
	
	
	private String alertMediumLowRangeSymbol;
	
	
	private String alertMediumLowRangeValue;
	
	
	private String alertMediumHighRangeSymbol;
	
	
	private String alertMediumHighRangeValue;
	
	private String labUnit;
	
	public int getDoctorid() {
		return doctorid;
	}
	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}
	public int getClinicproviderid() {
		return clinicproviderid;
	}
	public void setClinicproviderid(int clinicproviderid) {
		this.clinicproviderid = clinicproviderid;
	}
	public String getMedicinename() {
		return medicinename;
	}
	public void setMedicinename(String medicinename) {
		this.medicinename = medicinename;
	}
	public String getLastupdateby() {
		return lastupdateby;
	}
	public void setLastupdateby(String lastupdateby) {
		this.lastupdateby = lastupdateby;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public Date getLastupdatedate() {
		return lastupdatedate;
	}
	public void setLastupdatedate(Date lastupdatedate) {
		this.lastupdatedate = lastupdatedate;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getLabfrequency() {
		return labfrequency;
	}
	public void setLabfrequency(String labfrequency) {
		this.labfrequency = labfrequency;
	}
	public String getAcceptablerange() {
		return acceptablerange;
	}
	public void setAcceptablerange(String acceptablerange) {
		this.acceptablerange = acceptablerange;
	}
	/**
	 * @return the patientId
	 */
	
	public Date getNextLabDate() {
		return nextLabDate;
	}
	/**
	 * @param nextLabDate the nextLabDate to set
	 */
	public void setNextLabDate(Date nextLabDate) {
		this.nextLabDate = nextLabDate;
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
	 * @return the patientid
	 */
	

	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
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
	 * @return the lab
	 */
	public String getLab() {
		return lab;
	}
	/**
	 * @param lab the lab to set
	 */
	public void setLab(String lab) {
		this.lab = lab;
	}
	
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getDose() {
		return dose;
	}
	public String getLabUnit() {
		return labUnit;
	}
	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}
	/**
	 * @return the medicinenameforgraph
	 */
	public String getMedicinenameforgraph() {
		return medicinenameforgraph;
	}
	/**
	 * @param medicinenameforgraph the medicinenameforgraph to set
	 */
	public void setMedicinenameforgraph(String medicinenameforgraph) {
		this.medicinenameforgraph = medicinenameforgraph;
	}
	/**
	 * @return the drugNameId
	 */
	public Double getDrugNameId() {
		return drugNameId;
	}
	/**
	 * @param drugNameId the drugNameId to set
	 */
	public void setDrugNameId(Double drugNameId) {
		this.drugNameId = drugNameId;
	}
	/**
	 * @return the dosingStartDate
	 */
	public Date getDosingStartDate() {
		return dosingStartDate;
	}
	/**
	 * @param dosingStartDate the dosingStartDate to set
	 */
	public void setDosingStartDate(Date dosingStartDate) {
		this.dosingStartDate = dosingStartDate;
	}
	public int getLabParameterId() {
		return labParameterId;
	}
	public void setLabParameterId(int labParameterId) {
		this.labParameterId = labParameterId;
	}
	public String getFrequencyUnit() {
		return frequencyUnit;
	}
	public void setFrequencyUnit(String frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}
	public int getFrequencyNumber() {
		return frequencyNumber;
	}
	public void setFrequencyNumber(int frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
	}
	public String getGoalLowRangeSymbol() {
		return goalLowRangeSymbol;
	}
	public void setGoalLowRangeSymbol(String goalLowRangeSymbol) {
		this.goalLowRangeSymbol = goalLowRangeSymbol;
	}
	public String getGoalLowRangeValue() {
		return goalLowRangeValue;
	}
	public void setGoalLowRangeValue(String goalLowRangeValue) {
		this.goalLowRangeValue = goalLowRangeValue;
	}
	public String getGoalHighRangeSymbol() {
		return goalHighRangeSymbol;
	}
	public void setGoalHighRangeSymbol(String goalHighRangeSymbol) {
		this.goalHighRangeSymbol = goalHighRangeSymbol;
	}
	public String getGoalHighRangeValue() {
		return goalHighRangeValue;
	}
	public void setGoalHighRangeValue(String goalHighRangeValue) {
		this.goalHighRangeValue = goalHighRangeValue;
	}
	public String getAlertSevereLowRangeSymbol() {
		return alertSevereLowRangeSymbol;
	}
	public void setAlertSevereLowRangeSymbol(String alertSevereLowRangeSymbol) {
		this.alertSevereLowRangeSymbol = alertSevereLowRangeSymbol;
	}
	public String getAlertSevereLowRangeValue() {
		return alertSevereLowRangeValue;
	}
	public void setAlertSevereLowRangeValue(String alertSevereLowRangeValue) {
		this.alertSevereLowRangeValue = alertSevereLowRangeValue;
	}
	public String getAlertSevereHighRangeSymbol() {
		return alertSevereHighRangeSymbol;
	}
	public void setAlertSevereHighRangeSymbol(String alertSevereHighRangeSymbol) {
		this.alertSevereHighRangeSymbol = alertSevereHighRangeSymbol;
	}
	public String getAlertSevereHighRangeValue() {
		return alertSevereHighRangeValue;
	}
	public void setAlertSevereHighRangeValue(String alertSevereHighRangeValue) {
		this.alertSevereHighRangeValue = alertSevereHighRangeValue;
	}
	public String getAlertMediumLowRangeSymbol() {
		return alertMediumLowRangeSymbol;
	}
	public void setAlertMediumLowRangeSymbol(String alertMediumLowRangeSymbol) {
		this.alertMediumLowRangeSymbol = alertMediumLowRangeSymbol;
	}
	public String getAlertMediumLowRangeValue() {
		return alertMediumLowRangeValue;
	}
	public void setAlertMediumLowRangeValue(String alertMediumLowRangeValue) {
		this.alertMediumLowRangeValue = alertMediumLowRangeValue;
	}
	public String getAlertMediumHighRangeSymbol() {
		return alertMediumHighRangeSymbol;
	}
	public void setAlertMediumHighRangeSymbol(String alertMediumHighRangeSymbol) {
		this.alertMediumHighRangeSymbol = alertMediumHighRangeSymbol;
	}
	public String getAlertMediumHighRangeValue() {
		return alertMediumHighRangeValue;
	}
	public void setAlertMediumHighRangeValue(String alertMediumHighRangeValue) {
		this.alertMediumHighRangeValue = alertMediumHighRangeValue;
	}
	


}
