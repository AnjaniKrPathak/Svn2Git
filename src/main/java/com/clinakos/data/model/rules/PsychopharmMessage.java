package com.clinakos.data.model.rules;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PsychopharmMessage extends Message {
	
	private boolean drugScreenSelected;
	private boolean pregnancySelected;
	private List<String>contraindicatedMeds;
	private List<String>contraindicatedDiagnosis;
	private Double phq9Scale;
	private Double altmanScale;
	private Double CrCl_Value;
	private String monitoring_paramater;
	private String paramter_value;
	private String paramter_unit;
	private Double monitoring_parameter_value;
	private Map<String,PsychopharmMessage> psychopharmPayload = new HashMap<String,PsychopharmMessage>();
	private String gender;
	private String labDate;
	
	private Double lithiumLevel;
	private String patientAnswer;
	private List<String>responseList;
	private boolean contraindicatedMedicines;
	private boolean contraindicatedDiaognosis;
	private boolean recommedationSystemMessage;
	private String next_step;
	private String prev_step;
	private String current_step;
	private List<MedPlan>medPlanDefaultSettings;
	private Map<String,String>responeLinkedMapList=new LinkedHashMap<String, String>();
	private String selectedValue;
	private boolean phq_9;
	private boolean optionsRendered;
	private Integer patientAge;
	private String question;
	private boolean optionsSelected;
	private boolean responseSuccess;
	
	private String label;
	private String toolTip;
	
	public PsychopharmMessage(){
		
	}
	
	public PsychopharmMessage(String paramter_value, String paramter_unit,String label,String toolTip,String labDate) {
		super();
		this.paramter_value = paramter_value;
		this.paramter_unit = paramter_unit;
		this.label=label;
		this.toolTip=toolTip;
		this.labDate=labDate;
	}
	
	
	
	

	

	public PsychopharmMessage(String selectedValue,Map<String, String> responeLinkedMapList
			) {
		super();
		this.responeLinkedMapList = responeLinkedMapList;
		this.selectedValue = selectedValue;
	}
	
	

	public PsychopharmMessage(String selectedValue,List<String> responseList,
			Map<String, String> responeLinkedMapList,boolean optionsRendered) {
		super();
		this.responseList = responseList;
		this.responeLinkedMapList = responeLinkedMapList;
		this.selectedValue = selectedValue;
		this.optionsRendered=optionsRendered;
	}

	public String getMonitoring_paramater() {
		return monitoring_paramater;
	}
	public void setMonitoring_paramater(String monitoring_paramater) {
		this.monitoring_paramater = monitoring_paramater;
	}
	
	public String getParamter_unit() {
		return paramter_unit;
	}
	public void setParamter_unit(String paramter_unit) {
		this.paramter_unit = paramter_unit;
	}
	public boolean isDrugScreenSelected() {
		return drugScreenSelected;
	}
	public void setDrugScreenSelected(boolean drugScreenSelected) {
		this.drugScreenSelected = drugScreenSelected;
	}
	public boolean isPregnancySelected() {
		return pregnancySelected;
	}
	public void setPregnancySelected(boolean pregnancySelected) {
		this.pregnancySelected = pregnancySelected;
	}
	public List<String> getContraindicatedMeds() {
		return contraindicatedMeds;
	}
	public void setContraindicatedMeds(List<String> contraindicatedMeds) {
		this.contraindicatedMeds = contraindicatedMeds;
	}
	public List<String> getContraindicatedDiagnosis() {
		return contraindicatedDiagnosis;
	}
	public void setContraindicatedDiagnosis(List<String> contraindicatedDiagnosis) {
		this.contraindicatedDiagnosis = contraindicatedDiagnosis;
	}
	public Double getPhq9Scale() {
		return phq9Scale;
	}
	public void setPhq9Scale(Double phq9Scale) {
		this.phq9Scale = phq9Scale;
	}
	public Double getAltmanScale() {
		return altmanScale;
	}
	public void setAltmanScale(Double altmanScale) {
		this.altmanScale = altmanScale;
	}
	public Double getCrCl_Value() {
		return CrCl_Value;
	}
	public void setCrCl_Value(Double crCl_Value) {
		CrCl_Value = crCl_Value;
	}
	public String getParamter_value() {
		return paramter_value;
	}
	public void setParamter_value(String paramter_value) {
		this.paramter_value = paramter_value;
	}

	public Map<String, PsychopharmMessage> getPsychopharmPayload() {
		return psychopharmPayload;
	}

	public void setPsychopharmPayload(
			Map<String, PsychopharmMessage> psychopharmPayload) {
		this.psychopharmPayload = psychopharmPayload;
	}

	public Double getMonitoring_parameter_value() {
		return monitoring_parameter_value;
	}

	public void setMonitoring_parameter_value(Double monitoring_parameter_value) {
		this.monitoring_parameter_value = monitoring_parameter_value;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getLithiumLevel() {
		return lithiumLevel;
	}

	public void setLithiumLevel(Double lithiumLevel) {
		this.lithiumLevel = lithiumLevel;
	}

	public String getPatientAnswer() {
		return patientAnswer;
	}

	public void setPatientAnswer(String patientAnswer) {
		this.patientAnswer = patientAnswer;
	}

	public List<String> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<String> responseList) {
		this.responseList = responseList;
	}

	public boolean isContraindicatedMedicines() {
		return contraindicatedMedicines;
	}

	public void setContraindicatedMedicines(boolean contraindicatedMedicines) {
		this.contraindicatedMedicines = contraindicatedMedicines;
	}

	public boolean isContraindicatedDiaognosis() {
		return contraindicatedDiaognosis;
	}

	public void setContraindicatedDiaognosis(boolean contraindicatedDiaognosis) {
		this.contraindicatedDiaognosis = contraindicatedDiaognosis;
	}

	public boolean isRecommedationSystemMessage() {
		return recommedationSystemMessage;
	}

	public void setRecommedationSystemMessage(boolean recommedationSystemMessage) {
		this.recommedationSystemMessage = recommedationSystemMessage;
	}

	public String getNext_step() {
		return next_step;
	}

	public void setNext_step(String next_step) {
		this.next_step = next_step;
	}

	public String getPrev_step() {
		return prev_step;
	}

	public void setPrev_step(String prev_step) {
		this.prev_step = prev_step;
	}

	public String getCurrent_step() {
		return current_step;
	}

	public void setCurrent_step(String current_step) {
		this.current_step = current_step;
	}

	public List<MedPlan> getMedPlanDefaultSettings() {
		return medPlanDefaultSettings;
	}

	public void setMedPlanDefaultSettings(List<MedPlan> medPlanDefaultSettings) {
		this.medPlanDefaultSettings = medPlanDefaultSettings;
	}

	public Map<String, String> getResponeLinkedMapList() {
		return responeLinkedMapList;
	}

	public void setResponeLinkedMapList(Map<String, String> responeLinkedMapList) {
		this.responeLinkedMapList = responeLinkedMapList;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	public boolean isPhq_9() {
		return phq_9;
	}

	public void setPhq_9(boolean phq_9) {
		this.phq_9 = phq_9;
	}

	public boolean isOptionsRendered() {
		return optionsRendered;
	}

	public void setOptionsRendered(boolean optionsRendered) {
		this.optionsRendered = optionsRendered;
	}

	public Integer getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(Integer patientAge) {
		this.patientAge = patientAge;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public boolean isOptionsSelected() {
		return optionsSelected;
	}

	public void setOptionsSelected(boolean optionsSelected) {
		this.optionsSelected = optionsSelected;
	}

	public boolean isResponseSuccess() {
		return responseSuccess;
	}

	public void setResponseSuccess(boolean responseSuccess) {
		this.responseSuccess = responseSuccess;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getToolTip() {
		return toolTip;
	}

	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}

	public String getLabDate() {
		return labDate;
	}

	public void setLabDate(String labDate) {
		this.labDate = labDate;
	}

	

	
	

}
