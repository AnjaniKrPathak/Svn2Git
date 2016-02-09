package com.clinakos.data.model.patient;

import java.awt.List;
import java.util.ArrayList;
import java.util.Date;

public class MedActionPlan {
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
	private String labFrequency="";
	private String durationOfTherapy;
	private String recommendedDurationOfTherapy;
	private String recommendedTarget;
	private String diet;
	private String regimen;
	private Date actionDate;
	private Date lastUpdateDate;
	private Date nextLabDate;
	private String patientNotes;
	private double lowAcceptableRange;
	private double highAcceptableRange;
	private int value;
	private String unit;
	private String dosageForm;
	private Date dosingStartDate;
	
	private String labUnit;
	private int noOfDaysStartingFromMedActionPlan;
	private double labResult; 
	private Date labDate;
	private boolean showNotesForINRLab;

	
	//private String labFrequencyForPlateletCount;
	//private String labFrequencyForHematocrit;
	//private String labFrequencyForSerumCreatinine;
	//private String labFrequencyForTroughAntiXaLevel;
	///private String labFrequencyForPeakAntiXaLevel;
	
	//private boolean checkLabForPatientWeight;
	//private boolean checkLabForPlateletCount;
	//private boolean checkLabForHematocrit;
	//private boolean checkLabForSerumCreatinine;
	//private boolean checkLabForAntiXaLevel;
	//private ArrayList<String> labList=new ArrayList<String>();
	
	private String confirmDayForMaintenance;
	private double drugId;
	private double drugNameId;
	
	private String showConfirmDayMsg;
	private String valueConfirmDaysMsg;
	
	
	private String medicineStage;
	private String lastupdatedby;
	private String createdby;
	
	private String subdiagonses;
	private Date prescriprtiondate;
	private String prescriptionby;
	private String labImageStatusForINR;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabImageStatusForINR() {
		return labImageStatusForINR;
	}
	public void setLabImageStatusForINR(String labImageStatusForINR) {
		this.labImageStatusForINR = labImageStatusForINR;
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
	public String getDurationOfTherapy() {
		return durationOfTherapy;
	}
	public void setDurationOfTherapy(String durationOfTherapy) {
		this.durationOfTherapy = durationOfTherapy;
	}
	public String getRecommendedDurationOfTherapy() {
		return recommendedDurationOfTherapy;
	}
	public void setRecommendedDurationOfTherapy(String recommendedDurationOfTherapy) {
		this.recommendedDurationOfTherapy = recommendedDurationOfTherapy;
	}
	public String getRecommendedTarget() {
		return recommendedTarget;
	}
	public void setRecommendedTarget(String recommendedTarget) {
		this.recommendedTarget = recommendedTarget;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
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
	public String getConfirmDayForMaintenance() {
		return confirmDayForMaintenance;
	}
	public void setConfirmDayForMaintenance(String confirmDayForMaintenance) {
		this.confirmDayForMaintenance = confirmDayForMaintenance;
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
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public double getLowAcceptableRange() {
		return lowAcceptableRange;
	}
	public void setLowAcceptableRange(double lowAcceptableRange) {
		this.lowAcceptableRange = lowAcceptableRange;
	}
	public double getHighAcceptableRange() {
		return highAcceptableRange;
	}
	public void setHighAcceptableRange(double highAcceptableRange) {
		this.highAcceptableRange = highAcceptableRange;
	}
	
	public String getMedicineStage() {
		return medicineStage;
	}
	public void setMedicineStage(String medicineStage) {
		this.medicineStage = medicineStage;
	}
	public String getShowConfirmDayMsg() {
		return showConfirmDayMsg;
	}
	public void setShowConfirmDayMsg(String showConfirmDayMsg) {
		this.showConfirmDayMsg = showConfirmDayMsg;
	}
	public String getValueConfirmDaysMsg() {
		return valueConfirmDaysMsg;
	}
	public void setValueConfirmDaysMsg(String valueConfirmDaysMsg) {
		this.valueConfirmDaysMsg = valueConfirmDaysMsg;
	}
	/**
	 * @return the lastupdatedby
	 */
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	/**
	 * @param lastupdatedby the lastupdatedby to set
	 */
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}
	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the subdiagonses
	 */
	public String getSubdiagonses() {
		return subdiagonses;
	}
	/**
	 * @param subdiagonses the subdiagonses to set
	 */
	public void setSubdiagonses(String subdiagonses) {
		this.subdiagonses = subdiagonses;
	}
	/**
	 * @return the prescriprtiondate
	 */
	public Date getPrescriprtiondate() {
		return prescriprtiondate;
	}
	/**
	 * @param date the prescriprtiondate to set
	 */
	public void setPrescriprtiondate(Date date) {
		this.prescriprtiondate = date;
	}
	/**
	 * @return the prescriptionby
	 */
	public String getPrescriptionby() {
		return prescriptionby;
	}
	/**
	 * @param prescriptionby the prescriptionby to set
	 */
	public void setPrescriptionby(String prescriptionby) {
		this.prescriptionby = prescriptionby;
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
	public String getLabUnit() {
		return labUnit;
	}
	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((medicineName == null) ? 0 : medicineName.hashCode());
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
		MedActionPlan other = (MedActionPlan) obj;
		if (medicineName == null) {
			if (other.medicineName != null)
				return false;
		} else if (!medicineName.equalsIgnoreCase(other.medicineName))
			return false;
		return true;
	}
	public Date getDosingStartDate() {
		return dosingStartDate;
	}
	public void setDosingStartDate(Date dosingStartDate) {
		this.dosingStartDate = dosingStartDate;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public int getNoOfDaysStartingFromMedActionPlan() {
		return noOfDaysStartingFromMedActionPlan;
	}
	public void setNoOfDaysStartingFromMedActionPlan(
			int noOfDaysStartingFromMedActionPlan) {
		this.noOfDaysStartingFromMedActionPlan = noOfDaysStartingFromMedActionPlan;
	}
	public double getLabResult() {
		return labResult;
	}
	public void setLabResult(double labResult) {
		this.labResult = labResult;
	}
	public Date getLabDate() {
		return labDate;
	}
	public void setLabDate(Date labDate) {
		this.labDate = labDate;
	}
	public boolean isShowNotesForINRLab() {
		return showNotesForINRLab;
	}
	public void setShowNotesForINRLab(boolean showNotesForINRLab) {
		this.showNotesForINRLab = showNotesForINRLab;
	}

	
	

}
