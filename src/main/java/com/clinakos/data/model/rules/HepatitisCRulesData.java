package com.clinakos.data.model.rules;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clinakos.data.model.rules.Message;

public class HepatitisCRulesData extends Message implements Serializable  {

	private static final long serialVersionUID = -4021415213906697560L;
	/**
	 * 
	 */
	
	
	private int age ;
	private String encephalopathy ;
	private String ascites ;
	private int serumBillirubin ;
	private int serumAlbumin ;
	private int inr ;
	private int score ;
	private String interpretation ;
	
	private String genoType;
	private String subGenotype;
	private String polymorphism ;
	private String ifnEligibility ;
	private String hcvTreatment ;
	private String proteaseInhibitor ;
	private boolean genoTypeEnable ;
	private boolean polymorphismEnable;
	
	
	private String recommendation ;
	private String durationTreatment ;
	private String altRecommendation ;
	private String altRecDuration ;
	private String recommendationLevel ;
	private String source ;
	
	private List<String>responseList;
	private List<String>monitorParameterList;
	private String monitorParameter ;
	private List<MedPlan>medPlanDefaultSettings;
	
	
	
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
	public int getSerumBillirubin() {
		return serumBillirubin;
	}
	public void setSerumBillirubin(int serumBillirubin) {
		this.serumBillirubin = serumBillirubin;
	}
	public int getSerumAlbumin() {
		return serumAlbumin;
	}
	public void setSerumAlbumin(int serumAlbumin) {
		this.serumAlbumin = serumAlbumin;
	}
	public int getInr() {
		return inr;
	}
	public void setInr(int inr) {
		this.inr = inr;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
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
	public List<String> getMonitorParameterList() {
		return monitorParameterList;
	}
	public void setMonitorParameterList(List<String> monitorParameterList) {
		this.monitorParameterList = monitorParameterList;
	}
	public String getMonitorParameter() {
		return monitorParameter;
	}
	public void setMonitorParameter(String monitorParameter) {
		this.monitorParameter = monitorParameter;
	}
	public List<String> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<String> responseList) {
		this.responseList = responseList;
	}
	public List<MedPlan> getMedPlanDefaultSettings() {
		return medPlanDefaultSettings;
	}
	public void setMedPlanDefaultSettings(List<MedPlan> medPlanDefaultSettings) {
		this.medPlanDefaultSettings = medPlanDefaultSettings;
	}
	
	
	
	
	

	
}
