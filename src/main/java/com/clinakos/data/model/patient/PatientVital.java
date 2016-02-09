/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Date;



/**
 * @author li0008
 *
 */
public class PatientVital {
	private int id;
	private Double weight;
	private double weightInKG;
	private String height;
	private double bodyMassIndex;
	private String bodyMassIndexStatus;
	private String idealBodyWeight;
	private int patientIdForVital;
	private String bmiMessage;
	private String bmiRange;
	private double cmHeight;
	private String race;
	private String weightUnits; 
	private Double convertWeight;
	private String updateUnites;
	private String heightinfeet;
	private String heightininches;
	private int pulse;
	private double temperature;
	
	private Date measurementDate;
	private int systolic;
	private int diastolic;
	private int respiration;
	private int feet;
	private int inch;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public double getBodyMassIndex() {
		return bodyMassIndex;
	}
	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}
	public String getBodyMassIndexStatus() {
		return bodyMassIndexStatus;
	}
	public void setBodyMassIndexStatus(String bodyMassIndexStatus) {
		this.bodyMassIndexStatus = bodyMassIndexStatus;
	}
	public String getIdealBodyWeight() {
		return idealBodyWeight;
	}
	public void setIdealBodyWeight(String idealBodyWeight) {
		this.idealBodyWeight = idealBodyWeight;
	}
	public int getPatientIdForVital() {
		return patientIdForVital;
	}
	public void setPatientIdForVital(int patientIdForVital) {
		this.patientIdForVital = patientIdForVital;
	}
	public String getBmiMessage() {
		return bmiMessage;
	}
	public void setBmiMessage(String bmiMessage) {
		this.bmiMessage = bmiMessage;
	}
	public String getBmiRange() {
		return bmiRange;
	}
	public void setBmiRange(String bmiRange) {
		this.bmiRange = bmiRange;
	}
	public double getCmHeight() {
		return cmHeight;
	}
	public void setCmHeight(double cmHeight) {
		this.cmHeight = cmHeight;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public double getWeightInKG() {
		return weightInKG;
	}
	public void setWeightInKG(double weightInKG) {
		this.weightInKG = weightInKG;
	}
	public String getWeightUnits() {
		return weightUnits;
	}
	public void setWeightUnits(String weightUnits) {
		this.weightUnits = weightUnits;
	}
	public Double getConvertWeight() {
		return convertWeight;
	}
	public void setConvertWeight(Double convertWeight) {
		this.convertWeight = convertWeight;
	}
	public String getUpdateUnites() {
		return updateUnites;
	}
	public void setUpdateUnites(String updateUnites) {
		this.updateUnites = updateUnites;
	}
	public String getHeightininches() {
		return heightininches;
	}
	public void setHeightininches(String heightininches) {
		this.heightininches = heightininches;
	}
	public String getHeightinfeet() {
		return heightinfeet;
	}
	public void setHeightinfeet(String heightinfeet) {
		this.heightinfeet = heightinfeet;
	}
	
	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * @return the measurementDate
	 */
	public Date getMeasurementDate() {
		return measurementDate;
	}
	/**
	 * @param measurementDate the measurementDate to set
	 */
	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}
	/**
	 * @return the systolic
	 */
	public int getSystolic() {
		return systolic;
	}
	/**
	 * @param systolic the systolic to set
	 */
	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}
	/**
	 * @return the diastolic
	 */
	public int getDiastolic() {
		return diastolic;
	}
	/**
	 * @param diastolic the diastolic to set
	 */
	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}
	/**
	 * @return the respiration
	 */
	public int getRespiration() {
		return respiration;
	}
	/**
	 * @param respiration the respiration to set
	 */
	public void setRespiration(int respiration) {
		this.respiration = respiration;
	}
	/**
	 * @return the pulse
	 */
	public int getPulse() {
		return pulse;
	}
	/**
	 * @param pulse the pulse to set
	 */
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	/**
	 * @return the feet
	 */
	public int getFeet() {
		return feet;
	}
	/**
	 * @param feet the feet to set
	 */
	public void setFeet(int feet) {
		this.feet = feet;
	}
	/**
	 * @return the inch
	 */
	public int getInch() {
		return inch;
	}
	/**
	 * @param inch the inch to set
	 */
	public void setInch(int inch) {
		this.inch = inch;
	}
	
	
	//private UserLoginDetail patientId;
	
	
	

}
