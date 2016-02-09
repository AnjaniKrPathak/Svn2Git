package com.clinakos.data.model.rules;

public class ModelMessage {
	
	private String alertLevel;
	private String recomendationMessage;
	private String questions;
	private String message;
	private double value;
	
	public String getAlertLevel() {
		return alertLevel;
	}
	public void setAlertLevel(String alertLevel) {
		this.alertLevel = alertLevel;
	}
	public String getRecomendationMessage() {
		return recomendationMessage;
	}
	public void setRecomendationMessage(String recomendationMessage) {
		this.recomendationMessage = recomendationMessage;
	}
	@Override
	public String toString() {
		return "ModelMessage [alertLevel=" + alertLevel
				+ ", recomendationMessage=" + recomendationMessage + "]";
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
	

}
