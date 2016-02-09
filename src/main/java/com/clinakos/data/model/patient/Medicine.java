package com.clinakos.data.model.patient;

public class Medicine {
	private int id;
	private String medicinName;
	private String clinicName;
	private boolean flagForMedActionPlan;
	private boolean flagForDoseCalculator;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicinName() {
		return medicinName;
	}
	public void setMedicinName(String medicinName) {
		this.medicinName = medicinName;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public boolean isFlagForMedActionPlan() {
		return flagForMedActionPlan;
	}
	public void setFlagForMedActionPlan(boolean flagForMedActionPlan) {
		this.flagForMedActionPlan = flagForMedActionPlan;
	}
	public boolean isFlagForDoseCalculator() {
		return flagForDoseCalculator;
	}
	public void setFlagForDoseCalculator(boolean flagForDoseCalculator) {
		this.flagForDoseCalculator = flagForDoseCalculator;
	}
	
	
	

}
