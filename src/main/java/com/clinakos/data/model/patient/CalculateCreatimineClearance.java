package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalculateCreatimineClearance {
	private int age;
	private int height;
	private String gender;
	private double serumCreatinieValue;
	private double weight;
	private double simpleWeight;
	private Double calculateCreatimineClearanceValue=null;
	private String findWeightUnit;
	private String weightUnit;
	private String serumCreatinieValueInString;
	private String labDate;
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getSerumCreatinieValue() {
		return serumCreatinieValue;
	}
	public void setSerumCreatinieValue(double serumCreatinieValue) {
		this.serumCreatinieValue = serumCreatinieValue;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getSimpleWeight() {
		return simpleWeight;
	}
	public void setSimpleWeight(double simpleWeight) {
		this.simpleWeight = simpleWeight;
	}
	public Double getCalculateCreatimineClearanceValue() {
		return calculateCreatimineClearanceValue;
	}
	public void setCalculateCreatimineClearanceValue(
			Double calculateCreatimineClearanceValue) {
		this.calculateCreatimineClearanceValue = calculateCreatimineClearanceValue;
	}
	public String getFindWeightUnit() {
		return findWeightUnit;
	}
	public void setFindWeightUnit(String findWeightUnit) {
		this.findWeightUnit = findWeightUnit;
	}
	public String getWeightUnit() {
		return weightUnit;
	}
	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}
	public String getSerumCreatinieValueInString() {
		return serumCreatinieValueInString;
	}
	public void setSerumCreatinieValueInString(String serumCreatinieValueInString) {
		this.serumCreatinieValueInString = serumCreatinieValueInString;
	}
	@Override
	public String toString() {
		return "CalculateCreatimineClearance [age=" + age + ", height="
				+ height + ", gender=" + gender + ", serumCreatinieValue="
				+ serumCreatinieValue + ", weight=" + weight
				+ ", simpleWeight=" + simpleWeight + "]";
	}
	public String getLabDate() {
		return labDate;
	}
	public void setLabDate(String labDate) {
		this.labDate = labDate;
	}
	
	
	
	
	
	
	
}
