/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-0004
 * Created By:Nagaraj
 * Created On:13/Feb/2015
 */
public class PaediatricPrecaution {
	
	private Double drugId;
	private Double drugSubID1;
	private String drugTypeID;
	private String drugInfo;
	private String precautionSeverityLevel;
	private String precautionDescription;
	private String PrecautionSeverityValue;
	private Double minimumAgeInDays;
	private Double maximumAgeInDays;
	
	public Double getDrugId() {
		return drugId;
	}
	public void setDrugId(Double drugId) {
		this.drugId = drugId;
	}
	public Double getDrugSubID1() {
		return drugSubID1;
	}
	public void setDrugSubID1(Double drugSubID1) {
		this.drugSubID1 = drugSubID1;
	}
	public String getDrugTypeID() {
		return drugTypeID;
	}
	public void setDrugTypeID(String drugTypeID) {
		this.drugTypeID = drugTypeID;
	}
	public String getDrugInfo() {
		return drugInfo;
	}
	public void setDrugInfo(String drugInfo) {
		this.drugInfo = drugInfo;
	}
	public String getPrecautionSeverityLevel() {
		return precautionSeverityLevel;
	}
	public void setPrecautionSeverityLevel(String precautionSeverityLevel) {
		this.precautionSeverityLevel = precautionSeverityLevel;
	}
	public String getPrecautionDescription() {
		return precautionDescription;
	}
	public void setPrecautionDescription(String precautionDescription) {
		this.precautionDescription = precautionDescription;
	}
	public String getPrecautionSeverityValue() {
		return PrecautionSeverityValue;
	}
	public void setPrecautionSeverityValue(String precautionSeverityValue) {
		PrecautionSeverityValue = precautionSeverityValue;
	}
	public Double getMinimumAgeInDays() {
		return minimumAgeInDays;
	}
	public void setMinimumAgeInDays(Double minimumAgeInDays) {
		this.minimumAgeInDays = minimumAgeInDays;
	}
	public Double getMaximumAgeInDays() {
		return maximumAgeInDays;
	}
	public void setMaximumAgeInDays(Double maximumAgeInDays) {
		this.maximumAgeInDays = maximumAgeInDays;
	}
	
	

}
