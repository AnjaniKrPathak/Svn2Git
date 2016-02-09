package com.clinakos.data.model.patient;

import java.util.Date;

public class AlertGenericMedActionLab {
	
	private int id;
	private int patientId;
	private int doctorId;
	private String drugId;
	private String drugName;
	private Date labDate;
	private double labResultValue;
	private double acceptableRange;
	private String labType;
	private String severityLevel;
	private String lowRangeSymbol;
	private double lowValue;
	private String highRangeSymbol;
	private double highValue;
	private String labDateResult;
	private boolean status;
	//for UI purpose
	private boolean labTypeCheckMedPlan;
	
	private String alertSevereLowRangeSymbol;
	private String alertSevereHighRangeSymbol;
	private double alertSevereLowRangeValue;
	private double alertSevereHighRangeValue;
	private String alertMediumLowRangeSymbol;
	private String alertMediumHighRangeSymbol;
	private double alertMediumLowRangeValue;
	private double alertMediumHighRangeValue;
	
	// convert double to String
	//private String lowValueInString;
	//private String highValueInString;
	//private String labResultValueInString;
	
	
	 private String labUnit;
	
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
	public double getLabResultValue() {
		return labResultValue;
	}
	public void setLabResultValue(double labResultValue) {
		this.labResultValue = labResultValue;
	}
	public double getAcceptableRange() {
		return acceptableRange;
	}
	public void setAcceptableRange(double acceptableRange) {
		this.acceptableRange = acceptableRange;
	}
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	public String getLowRangeSymbol() {
		return lowRangeSymbol;
	}
	public void setLowRangeSymbol(String lowRangeSymbol) {
		this.lowRangeSymbol = lowRangeSymbol;
	}
	public double getLowValue() {
		return lowValue;
	}
	public void setLowValue(double lowValue) {
		this.lowValue = lowValue;
	}
	public String getHighRangeSymbol() {
		return highRangeSymbol;
	}
	public void setHighRangeSymbol(String highRangeSymbol) {
		this.highRangeSymbol = highRangeSymbol;
	}
	public double getHighValue() {
		return highValue;
	}
	public void setHighValue(double highValue) {
		this.highValue = highValue;
	}
	public String getLabDateResult() {
		return labDateResult;
	}
	public void setLabDateResult(String labDateResult) {
		this.labDateResult = labDateResult;
	}
	public Date getLabDate() {
		return labDate;
	}
	public void setLabDate(Date labDate) {
		this.labDate = labDate;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean isLabTypeCheckMedPlan() {
		return labTypeCheckMedPlan;
	}
	public void setLabTypeCheckMedPlan(boolean labTypeCheckMedPlan) {
		this.labTypeCheckMedPlan = labTypeCheckMedPlan;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((drugName == null) ? 0 : drugName.hashCode());
		result = prime * result
				+ ((severityLevel == null) ? 0 : severityLevel.hashCode());
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
		AlertGenericMedActionLab other = (AlertGenericMedActionLab) obj;
		/*if (drugName == null) {
			if (other.drugName != null)
				return false;
		} else */
			if (drugName.equalsIgnoreCase(other.drugName) && severityLevel.equalsIgnoreCase(other.severityLevel) && labType.equalsIgnoreCase(other.labType))
			{
				return true;
			}
			else{
				return false;
			}
		/*if (severityLevel == null) {
			if (other.severityLevel != null)
				return false;
		} else if (!severityLevel.equals(other.severityLevel))
			return false;*/
		
	}
	public String getLabUnit() {
		return labUnit;
	}
	public void setLabUnit(String labUnit) {
		this.labUnit = labUnit;
	}
	
}
