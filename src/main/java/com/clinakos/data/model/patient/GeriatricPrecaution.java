/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-0004
 * Created By:Nagaraj
 * Created On:13/Feb/2015
 */
public class GeriatricPrecaution {
	
	private Double drugId;
	private String drugTypeId;
	private String drugInfo;
	private Double drugSubID1;
	private String precautionSeverityLevel;
	private String precautionDescription;
	
	public Double getDrugId() {
		return drugId;
	}
	public void setDrugId(Double drugId) {
		this.drugId = drugId;
	}
	public String getDrugTypeId() {
		return drugTypeId;
	}
	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
	public String getDrugInfo() {
		return drugInfo;
	}
	public void setDrugInfo(String drugInfo) {
		this.drugInfo = drugInfo;
	}
	public Double getDrugSubID1() {
		return drugSubID1;
	}
	public void setDrugSubID1(Double drugSubID1) {
		this.drugSubID1 = drugSubID1;
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
	
	

}
