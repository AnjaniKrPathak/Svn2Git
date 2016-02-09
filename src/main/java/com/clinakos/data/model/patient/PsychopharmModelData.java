package com.clinakos.data.model.patient;

import java.util.Date;

public class PsychopharmModelData {
	
	private String bmiValue;
	private String hdlValue;
	private String fbsValue;
	private String tgValue;
	private String systolicBP_Value;
	private String diastolicBP_Value;
	private String bunValue;
	private String creatinineValue;
	private String crclValue;
	private String wbcValue;
	private String tshValue;
	private String ekgValue;
	
	private String altmanManiaRatingScale;
	private String phq9Scale;
	
	private boolean drugScreenOption;
	private boolean pregnancyOption;
	
	private boolean dosing_step_1_disabled;
	private boolean dosing_step_2_disabled;
	private boolean dosing_step_3_disabled;
	private boolean dosing_step_4_disabled;
	
	
	private String activeIndexDefault;
	
	private String recommendedDose;
	private String strengths;
	private String selectedPhase;
	private boolean formFilled=false;//This field used for validation for PHQ-9 in exceptional case
	private Date phq9Date;
	private Date altmanManiaDate;
	
	
	public String getBmiValue() {
		return bmiValue;
	}
	public void setBmiValue(String bmiValue) {
		this.bmiValue = bmiValue;
	}
	public String getHdlValue() {
		return hdlValue;
	}
	public void setHdlValue(String hdlValue) {
		this.hdlValue = hdlValue;
	}
	public String getFbsValue() {
		return fbsValue;
	}
	public void setFbsValue(String fbsValue) {
		this.fbsValue = fbsValue;
	}
	public String getTgValue() {
		return tgValue;
	}
	public void setTgValue(String tgValue) {
		this.tgValue = tgValue;
	}
	public String getSystolicBP_Value() {
		return systolicBP_Value;
	}
	public void setSystolicBP_Value(String systolicBP_Value) {
		this.systolicBP_Value = systolicBP_Value;
	}
	public String getDiastolicBP_Value() {
		return diastolicBP_Value;
	}
	public void setDiastolicBP_Value(String diastolicBP_Value) {
		this.diastolicBP_Value = diastolicBP_Value;
	}
	public String getBunValue() {
		return bunValue;
	}
	public void setBunValue(String bunValue) {
		this.bunValue = bunValue;
	}
	public String getCreatinineValue() {
		return creatinineValue;
	}
	public void setCreatinineValue(String creatinineValue) {
		this.creatinineValue = creatinineValue;
	}
	public String getCrclValue() {
		return crclValue;
	}
	public void setCrclValue(String crclValue) {
		this.crclValue = crclValue;
	}
	public String getWbcValue() {
		return wbcValue;
	}
	public void setWbcValue(String wbcValue) {
		this.wbcValue = wbcValue;
	}
	public String getTshValue() {
		return tshValue;
	}
	public void setTshValue(String tshValue) {
		this.tshValue = tshValue;
	}
	public String getEkgValue() {
		return ekgValue;
	}
	public void setEkgValue(String ekgValue) {
		this.ekgValue = ekgValue;
	}
	public String getAltmanManiaRatingScale() {
		return altmanManiaRatingScale;
	}
	public void setAltmanManiaRatingScale(String altmanManiaRatingScale) {
		this.altmanManiaRatingScale = altmanManiaRatingScale;
	}
	public String getPhq9Scale() {
		return phq9Scale;
	}
	public void setPhq9Scale(String phq9Scale) {
		this.phq9Scale = phq9Scale;
	}
	
	
	
	public boolean isDrugScreenOption() {
		return drugScreenOption;
	}
	public void setDrugScreenOption(boolean drugScreenOption) {
		this.drugScreenOption = drugScreenOption;
	}
	public boolean isPregnancyOption() {
		return pregnancyOption;
	}
	public void setPregnancyOption(boolean pregnancyOption) {
		this.pregnancyOption = pregnancyOption;
	}
	
	
	
	
	public boolean isDosing_step_1_disabled() {
		return dosing_step_1_disabled;
	}
	public void setDosing_step_1_disabled(boolean dosing_step_1_disabled) {
		this.dosing_step_1_disabled = dosing_step_1_disabled;
	}
	public boolean isDosing_step_2_disabled() {
		return dosing_step_2_disabled;
	}
	public void setDosing_step_2_disabled(boolean dosing_step_2_disabled) {
		this.dosing_step_2_disabled = dosing_step_2_disabled;
	}
	public boolean isDosing_step_3_disabled() {
		return dosing_step_3_disabled;
	}
	public void setDosing_step_3_disabled(boolean dosing_step_3_disabled) {
		this.dosing_step_3_disabled = dosing_step_3_disabled;
	}
	public boolean isDosing_step_4_disabled() {
		return dosing_step_4_disabled;
	}
	public void setDosing_step_4_disabled(boolean dosing_step_4_disabled) {
		this.dosing_step_4_disabled = dosing_step_4_disabled;
	}
	@Override
	public String toString() {
		return "PsychopharmModelData [bmiValue=" + bmiValue + ", hdlValue="
				+ hdlValue + ", fbsValue=" + fbsValue + ", tgValue=" + tgValue
				+ ", systolicBP_Value=" + systolicBP_Value
				+ ", diastolicBP_Value=" + diastolicBP_Value + ", bunValue="
				+ bunValue + ", creatinineValue=" + creatinineValue
				+ ", crclValue=" + crclValue + ", wbcValue=" + wbcValue
				+ ", tshValue=" + tshValue + ", ekgValue=" + ekgValue
				+ ", altmanManiaRatingScale=" + altmanManiaRatingScale
				+ ", phq9Scale=" + phq9Scale + "]";
	}
	public String getRecommendedDose() {
		return recommendedDose;
	}
	public void setRecommendedDose(String recommendedDose) {
		this.recommendedDose = recommendedDose;
	}
	public String getStrengths() {
		return strengths;
	}
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	public String getActiveIndexDefault() {
		return activeIndexDefault;
	}
	public void setActiveIndexDefault(String activeIndexDefault) {
		this.activeIndexDefault = activeIndexDefault;
	}
	public String getSelectedPhase() {
		return selectedPhase;
	}
	public void setSelectedPhase(String selectedPhase) {
		this.selectedPhase = selectedPhase;
	}
	public boolean isFormFilled() {
		return formFilled;
	}
	public void setFormFilled(boolean formFilled) {
		this.formFilled = formFilled;
	}
	public Date getPhq9Date() {
		return phq9Date;
	}
	public void setPhq9Date(Date phq9Date) {
		this.phq9Date = phq9Date;
	}
	public Date getAltmanManiaDate() {
		return altmanManiaDate;
	}
	public void setAltmanManiaDate(Date altmanManiaDate) {
		this.altmanManiaDate = altmanManiaDate;
	}
	

	
	
	

}
