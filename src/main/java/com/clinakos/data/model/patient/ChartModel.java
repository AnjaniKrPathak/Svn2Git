/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-0004
 *
 */
public class ChartModel {

	private int countValue;
	private String rangeName;
	private String lowRangeVal;
	private String highRangeVal;
	
	private double drugId;
	private double drugNameId;
	private String patients;
	
	public int getCountValue() {
		return countValue;
	}
	public void setCountValue(int countValue) {
		this.countValue = countValue;
	}
	public String getRangeName() {
		return rangeName;
	}
	public void setRangeName(String rangeName) {
		this.rangeName = rangeName;
	}
	public String getLowRangeVal() {
		return lowRangeVal;
	}
	public void setLowRangeVal(String lowRangeVal) {
		this.lowRangeVal = lowRangeVal;
	}
	public String getHighRangeVal() {
		return highRangeVal;
	}
	public void setHighRangeVal(String highRangeVal) {
		this.highRangeVal = highRangeVal;
	}
	
	
	
	@Override
	public String toString() {
		return "ChartModel [countValue=" + countValue + ", rangeName="
				+ rangeName + "]";
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public String getPatients() {
		return patients;
	}
	public void setPatients(String patients) {
		this.patients = patients;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	
	

	
	
}
