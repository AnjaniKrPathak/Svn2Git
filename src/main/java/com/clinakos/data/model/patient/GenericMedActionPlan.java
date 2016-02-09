package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenericMedActionPlan {
	
	private int id;
	private int patientId;
	private int clinicProviderId;
	private String monitoringParameter;
	private String alertSevereLowRangeSymbol;
	private String alertSevereHighRangeSymbol;
	private double alertSevereLowRangeValue;
	private double alertSevereHighRangeValue;
	private String alertMediumLowRangeSymbol;
	private String alertMediumHighRangeSymbol;
	private double alertMediumLowRangeValue;
	private double alertMediumHighRangeValue;
	private String goalRangeSymbol;
	private double goalRangeValue;
	private String frequencyValue;
	private String frequencyNo;
	private int doctorId;
	private String drugId;
	private String drugName;
	private String parameterShortName;
	private String goalRangeSymValue;
	private String alertSevereLowRangeSymValue;
	private String alertSevereHighRangeSymValue;
	private String alertMediumLowRangeSymValue;
	private String alertMediumHighRangeSymValue;
	private Date createdDate;
	private Date LastUpdatedDate;
	private String strength;
	private String regimen;
	private String goalRangeEndSymbol;
	private double goalRangeEndValue;
	private String goalRangeEndSymValue;
	//for UI purpose
	private boolean databaseCheck;
	private int createdBy;
	private int lastUpdatedBy;
	private String createdByName;
	private String lastUpdatedByName;
	private PatientDiagnosesDetails patientDiagnosesDetails;
	private String diagnosisName;
	private int patientDiagnosesDetailsId;
	private Date prescriptionDate;
	private String prescribedBy;
	
	private String unit;
	private String dosageForm;
	private double drugNameId;
	
	//-------------------------view member
	
	private String labUnit;
	
	//Added By Anjani 
		private Date dosingStartDate;
		private String frequencyUnit;
	
		private Date nextLabDate;
		
	
	public Date getDosingStartDate() {
		
			return dosingStartDate;
		}
		public void setDosingStartDate(Date dosingStartDate) {
			this.dosingStartDate = dosingStartDate;
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
	public int getClinicProviderId() {
		return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public String getMonitoringParameter() {
		return monitoringParameter;
	}
	public void setMonitoringParameter(String monitoringParameter) {
		this.monitoringParameter = monitoringParameter;
	}
	public String getAlertSevereLowRangeSymbol() {
		return alertSevereLowRangeSymbol;
	}
	public void setAlertSevereLowRangeSymbol(String alertSevereLowRangeSymbol) {
		this.alertSevereLowRangeSymbol = alertSevereLowRangeSymbol;
	}
	public String getAlertSevereHighRangeSymbol() {
		return alertSevereHighRangeSymbol;
	}
	public void setAlertSevereHighRangeSymbol(String alertSevereHighRangeSymbol) {
		this.alertSevereHighRangeSymbol = alertSevereHighRangeSymbol;
	}
	public double getAlertSevereLowRangeValue() {
		return alertSevereLowRangeValue;
	}
	public void setAlertSevereLowRangeValue(double alertSevereLowRangeValue) {
		this.alertSevereLowRangeValue = alertSevereLowRangeValue;
	}
	public double getAlertSevereHighRangeValue() {
		return alertSevereHighRangeValue;
	}
	public void setAlertSevereHighRangeValue(double alertSevereHighRangeValue) {
		this.alertSevereHighRangeValue = alertSevereHighRangeValue;
	}
	public String getAlertMediumLowRangeSymbol() {
		return alertMediumLowRangeSymbol;
	}
	public void setAlertMediumLowRangeSymbol(String alertMediumLowRangeSymbol) {
		this.alertMediumLowRangeSymbol = alertMediumLowRangeSymbol;
	}
	public String getAlertMediumHighRangeSymbol() {
		return alertMediumHighRangeSymbol;
	}
	public void setAlertMediumHighRangeSymbol(String alertMediumHighRangeSymbol) {
		this.alertMediumHighRangeSymbol = alertMediumHighRangeSymbol;
	}
	public double getAlertMediumLowRangeValue() {
		return alertMediumLowRangeValue;
	}
	public void setAlertMediumLowRangeValue(double alertMediumLowRangeValue) {
		this.alertMediumLowRangeValue = alertMediumLowRangeValue;
	}
	public double getAlertMediumHighRangeValue() {
		return alertMediumHighRangeValue;
	}
	public void setAlertMediumHighRangeValue(double alertMediumHighRangeValue) {
		this.alertMediumHighRangeValue = alertMediumHighRangeValue;
	}
	public String getGoalRangeSymbol() {
		return goalRangeSymbol;
	}
	public void setGoalRangeSymbol(String goalRangeSymbol) {
		this.goalRangeSymbol = goalRangeSymbol;
	}
	public double getGoalRangeValue() {
		return goalRangeValue;
	}
	public void setGoalRangeValue(double goalRangeValue) {
		this.goalRangeValue = goalRangeValue;
	}
	public String getFrequencyValue() {
		return frequencyValue;
	}
	public void setFrequencyValue(String frequencyValue) {
		this.frequencyValue = frequencyValue;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getParameterShortName() {
		return parameterShortName;
	}
	public void setParameterShortName(String parameterShortName) {
		this.parameterShortName = parameterShortName;
	}
	public String getGoalRangeSymValue() {
		return goalRangeSymValue;
	}
	public void setGoalRangeSymValue(String goalRangeSymValue) {
		this.goalRangeSymValue = goalRangeSymValue;
	}
	public String getAlertSevereLowRangeSymValue() {
		return alertSevereLowRangeSymValue;
	}
	public void setAlertSevereLowRangeSymValue(String alertSevereLowRangeSymValue) {
		this.alertSevereLowRangeSymValue = alertSevereLowRangeSymValue;
	}
	public String getAlertSevereHighRangeSymValue() {
		return alertSevereHighRangeSymValue;
	}
	public void setAlertSevereHighRangeSymValue(String alertSevereHighRangeSymValue) {
		this.alertSevereHighRangeSymValue = alertSevereHighRangeSymValue;
	}
	public String getAlertMediumLowRangeSymValue() {
		return alertMediumLowRangeSymValue;
	}
	public void setAlertMediumLowRangeSymValue(String alertMediumLowRangeSymValue) {
		this.alertMediumLowRangeSymValue = alertMediumLowRangeSymValue;
	}
	public String getAlertMediumHighRangeSymValue() {
		return alertMediumHighRangeSymValue;
	}
	public void setAlertMediumHighRangeSymValue(String alertMediumHighRangeSymValue) {
		this.alertMediumHighRangeSymValue = alertMediumHighRangeSymValue;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastUpdatedDate() {
		return LastUpdatedDate;
	}
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		LastUpdatedDate = lastUpdatedDate;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getGoalRangeEndSymbol() {
		return goalRangeEndSymbol;
	}
	public void setGoalRangeEndSymbol(String goalRangeEndSymbol) {
		this.goalRangeEndSymbol = goalRangeEndSymbol;
	}
	public double getGoalRangeEndValue() {
		return goalRangeEndValue;
	}
	public void setGoalRangeEndValue(double goalRangeEndValue) {
		this.goalRangeEndValue = goalRangeEndValue;
	}
	public String getGoalRangeEndSymValue() {
		return goalRangeEndSymValue;
	}
	public void setGoalRangeEndSymValue(String goalRangeEndSymValue) {
		this.goalRangeEndSymValue = goalRangeEndSymValue;
	}
	public boolean isDatabaseCheck() {
		return databaseCheck;
	}
	public void setDatabaseCheck(boolean databaseCheck) {
		this.databaseCheck = databaseCheck;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public int getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(int lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}
	public void setLastUpdatedByName(String lastUpdatedByName) {
		this.lastUpdatedByName = lastUpdatedByName;
	}
	
	
	public PatientDiagnosesDetails getPatientDiagnosesDetails() {
		return patientDiagnosesDetails;
	}
	public void setPatientDiagnosesDetails(
			PatientDiagnosesDetails patientDiagnosesDetails) {
		this.patientDiagnosesDetails = patientDiagnosesDetails;
	}
	
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	
	public int getPatientDiagnosesDetailsId() {
		return patientDiagnosesDetailsId;
	}
	public void setPatientDiagnosesDetailsId(int patientDiagnosesDetailsId) {
		this.patientDiagnosesDetailsId = patientDiagnosesDetailsId;
	}
	
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getPrescribedBy() {
		return prescribedBy;
	}
	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((monitoringParameter == null) ? 0 : monitoringParameter
						.hashCode());
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
		GenericMedActionPlan other = (GenericMedActionPlan) obj;
		if (monitoringParameter == null) {
			if (other.monitoringParameter != null)
				return false;
		} else if (!monitoringParameter.equals(other.monitoringParameter))
			return false;
		return true;
	}
	public String getLabUnit() {
		return labUnit;
	}
	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}
	public String getFrequencyNo() {
		return frequencyNo;
	}
	public void setFrequencyNo(String frequencyNo) {
		this.frequencyNo = frequencyNo;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	/**
	 * @return the frequencyUnit
	 */
	public String getFrequencyUnit() {
		return frequencyUnit;
	}
	/**
	 * @param frequencyUnit the frequencyUnit to set
	 */
	public void setFrequencyUnit(String frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}
	@Override
	public String toString() {
		return "GenericMedActionPlan [id=" + id + ", patientId=" + patientId
				+ ", clinicProviderId=" + clinicProviderId
				+ ", monitoringParameter=" + monitoringParameter
				+ ", alertSevereLowRangeSymbol=" + alertSevereLowRangeSymbol
				+ ", alertSevereHighRangeSymbol=" + alertSevereHighRangeSymbol
				+ ", alertSevereLowRangeValue=" + alertSevereLowRangeValue
				+ ", alertSevereHighRangeValue=" + alertSevereHighRangeValue
				+ ", alertMediumLowRangeSymbol=" + alertMediumLowRangeSymbol
				+ ", alertMediumHighRangeSymbol=" + alertMediumHighRangeSymbol
				+ ", alertMediumLowRangeValue=" + alertMediumLowRangeValue
				+ ", alertMediumHighRangeValue=" + alertMediumHighRangeValue
				+ ", goalRangeSymbol=" + goalRangeSymbol + ", goalRangeValue="
				+ goalRangeValue + ", frequencyValue=" + frequencyValue
				+ ", frequencyNo=" + frequencyNo + ", doctorId=" + doctorId
				+ ", drugId=" + drugId + ", drugName=" + drugName
				+ ", parameterShortName=" + parameterShortName
				+ ", goalRangeSymValue=" + goalRangeSymValue
				+ ", alertSevereLowRangeSymValue="
				+ alertSevereLowRangeSymValue
				+ ", alertSevereHighRangeSymValue="
				+ alertSevereHighRangeSymValue
				+ ", alertMediumLowRangeSymValue="
				+ alertMediumLowRangeSymValue
				+ ", alertMediumHighRangeSymValue="
				+ alertMediumHighRangeSymValue + ", createdDate=" + createdDate
				+ ", LastUpdatedDate=" + LastUpdatedDate + ", strength="
				+ strength + ", regimen=" + regimen + ", goalRangeEndSymbol="
				+ goalRangeEndSymbol + ", goalRangeEndValue="
				+ goalRangeEndValue + ", goalRangeEndSymValue="
				+ goalRangeEndSymValue + ", databaseCheck=" + databaseCheck
				+ ", createdBy=" + createdBy + ", lastUpdatedBy="
				+ lastUpdatedBy + ", createdByName=" + createdByName
				+ ", lastUpdatedByName=" + lastUpdatedByName
				+ ", patientDiagnosesDetails=" + patientDiagnosesDetails
				+ ", diagnosisName=" + diagnosisName
				+ ", patientDiagnosesDetailsId=" + patientDiagnosesDetailsId
				+ ", prescriptionDate=" + prescriptionDate + ", prescribedBy="
				+ prescribedBy + ", unit=" + unit + ", dosageForm="
				+ dosageForm + ", drugNameId=" + drugNameId + ", labUnit="
				+ labUnit + ", dosingStartDate=" + dosingStartDate
				+ ", frequencyUnit=" + frequencyUnit + "]";
	}
	public Date getNextLabDate() {
		return nextLabDate;
	}
	public void setNextLabDate(Date nextLabDate) {
		this.nextLabDate = nextLabDate;
	}
	
}
