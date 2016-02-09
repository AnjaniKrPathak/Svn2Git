package com.clinakos.data.model.patient;

import java.io.Serializable;

public class HepatitisC  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9063720763479096895L;
	
	private int age ;
	private String encephalopathy ;
	private String ascites ;
	private float serumBillirubin ;
	private float serumAlbumin ;
	private float inr ;
	private float score ;
	private String interpretation ;
	
	private String genoType;
	private String subGenotype;
	private String polymorphism ;
	private String ifnEligibility ;
	private boolean ifnRequired ;
	private String hcvTreatment ;
	private String proteaseInhibitor ;
	private boolean genoTypeEnable ;
	private boolean polymorphismEnable;
	private boolean hcvTreatementEnable; 
	
	private String recommendation ;
	private String durationTreatment ;
	private String altRecommendation ;
	private String altRecDuration ;
	private String recommendationLevel ;
	private String source ;
	
	private String monitorParameter;
	private String selectedFrequency;
	private String dateOfNextLab ;
	
	private boolean dosing_step_1_disabled;
	private boolean dosing_step_2_disabled;
	private boolean dosing_step_3_disabled;
	private boolean dosing_step_4_disabled;
	
	private String activeIndexDefault;
	
	
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEncephalopathy() {
		return encephalopathy;
	}
	public void setEncephalopathy(String encephalopathy) {
		this.encephalopathy = encephalopathy;
	}
	public String getAscites() {
		return ascites;
	}
	public void setAscites(String ascites) {
		this.ascites = ascites;
	}
	public float getSerumBillirubin() {
		return serumBillirubin;
	}
	public void setSerumBillirubin(float serumBillirubin) {
		this.serumBillirubin = serumBillirubin;
	}
	public float getSerumAlbumin() {
		return serumAlbumin;
	}
	public void setSerumAlbumin(float serumAlbumin) {
		this.serumAlbumin = serumAlbumin;
	}
	public float getInr() {
		return inr;
	}
	public void setInr(float inr) {
		this.inr = inr;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getInterpretation() {
		return interpretation;
	}
	public void setInterpretation(String interpretation) {
		this.interpretation = interpretation;
	}
	public String getGenoType() {
		return genoType;
	}
	public void setGenoType(String genoType) {
		this.genoType = genoType;
	}
	public String getSubGenotype() {
		return subGenotype;
	}
	public void setSubGenotype(String subGenotype) {
		this.subGenotype = subGenotype;
	}
	public String getPolymorphism() {
		return polymorphism;
	}
	public void setPolymorphism(String polymorphism) {
		this.polymorphism = polymorphism;
	}
	public String getIfnEligibility() {
		return ifnEligibility;
	}
	public void setIfnEligibility(String ifnEligibility) {
		this.ifnEligibility = ifnEligibility;
	}
	public boolean isGenoTypeEnable() {
		return genoTypeEnable;
	}
	public void setGenoTypeEnable(boolean genoTypeEnable) {
		this.genoTypeEnable = genoTypeEnable;
	}
	
	public boolean isPolymorphismEnable() {
		return polymorphismEnable;
	}
	public void setPolymorphismEnable(boolean polymorphismEnable) {
		this.polymorphismEnable = polymorphismEnable;
	}
	
	public boolean isHcvTreatementEnable() {
		return hcvTreatementEnable;
	}
	public void setHcvTreatementEnable(boolean hcvTreatementEnable) {
		this.hcvTreatementEnable = hcvTreatementEnable;
	}
	public String getHcvTreatment() {
		return hcvTreatment;
	}
	public void setHcvTreatment(String hcvTreatment) {
		this.hcvTreatment = hcvTreatment;
	}
	public String getProteaseInhibitor() {
		return proteaseInhibitor;
	}
	public void setProteaseInhibitor(String proteaseInhibitor) {
		this.proteaseInhibitor = proteaseInhibitor;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getDurationTreatment() {
		return durationTreatment;
	}
	public void setDurationTreatment(String durationTreatment) {
		this.durationTreatment = durationTreatment;
	}
	public String getAltRecommendation() {
		return altRecommendation;
	}
	public void setAltRecommendation(String altRecommendation) {
		this.altRecommendation = altRecommendation;
	}
	public String getAltRecDuration() {
		return altRecDuration;
	}
	public void setAltRecDuration(String altRecDuration) {
		this.altRecDuration = altRecDuration;
	}
	public String getRecommendationLevel() {
		return recommendationLevel;
	}
	public void setRecommendationLevel(String recommendationLevel) {
		this.recommendationLevel = recommendationLevel;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMonitorParameter() {
		return monitorParameter;
	}
	public void setMonitorParameter(String monitorParameter) {
		this.monitorParameter = monitorParameter;
	}
	public String getSelectedFrequency() {
		return selectedFrequency;
	}
	public void setSelectedFrequency(String selectedFrequency) {
		this.selectedFrequency = selectedFrequency;
	}
	public String getDateOfNextLab() {
		return dateOfNextLab;
	}
	public void setDateOfNextLab(String dateOfNextLab) {
		this.dateOfNextLab = dateOfNextLab;
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
	public String getActiveIndexDefault() {
		return activeIndexDefault;
	}
	public void setActiveIndexDefault(String activeIndexDefault) {
		this.activeIndexDefault = activeIndexDefault;
	}
	public boolean isIfnRequired() {
		return ifnRequired;
	}
	public void setIfnRequired(boolean ifnRequired) {
		this.ifnRequired = ifnRequired;
	}
	
	
	
	

	
}
