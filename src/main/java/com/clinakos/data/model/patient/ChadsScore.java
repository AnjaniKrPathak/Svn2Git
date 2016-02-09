package com.clinakos.data.model.patient;

public class ChadsScore {
	private  boolean heartFailure;
	private boolean hyperTension;
	private boolean age;
	private boolean diabetes;
	private boolean stroke;
	private int score;
	private boolean highSensitive;
	private boolean lowSensitive;
	private String sensitiveValue;
	public boolean isHeartFailure() {
		return heartFailure;
	}
	public void setHeartFailure(boolean heartFailure) {
		this.heartFailure = heartFailure;
	}
	public boolean isHyperTension() {
		return hyperTension;
	}
	public void setHyperTension(boolean hyperTension) {
		this.hyperTension = hyperTension;
	}
	public boolean isAge() {
		return age;
	}
	public void setAge(boolean age) {
		this.age = age;
	}
	public boolean isDiabetes() {
		return diabetes;
	}
	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}
	public boolean isStroke() {
		return stroke;
	}
	public void setStroke(boolean stroke) {
		this.stroke = stroke;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public boolean isHighSensitive() {
		return highSensitive;
	}
	public void setHighSensitive(boolean highSensitive) {
		this.highSensitive = highSensitive;
	}
	public boolean isLowSensitive() {
		return lowSensitive;
	}
	public void setLowSensitive(boolean lowSensitive) {
		this.lowSensitive = lowSensitive;
	}
	public String getSensitiveValue() {
		return sensitiveValue;
	}
	public void setSensitiveValue(String sensitiveValue) {
		this.sensitiveValue = sensitiveValue;
	}
	
	
	

}
