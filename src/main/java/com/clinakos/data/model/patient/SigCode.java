/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-0011 for Signature Code
 *
 */
public class SigCode {
	
	private int id;
	private String sigCode;
	private String meaning;
	
	private boolean morning;
	private boolean noon;
	private boolean evening;
	private boolean bedTime;
	private boolean specialCase;
	private int newCropId;
	private double sigCodeValue;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSigCode() {
		return sigCode;
	}
	public void setSigCode(String sigCode) {
		this.sigCode = sigCode;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	
	public boolean isMorning() {
		return morning;
	}
	public void setMorning(boolean morning) {
		this.morning = morning;
	}
	public boolean isNoon() {
		return noon;
	}
	public void setNoon(boolean noon) {
		this.noon = noon;
	}
	public boolean isEvening() {
		return evening;
	}
	public void setEvening(boolean evening) {
		this.evening = evening;
	}
	public boolean isBedTime() {
		return bedTime;
	}
	public void setBedTime(boolean bedTime) {
		this.bedTime = bedTime;
	}
	public boolean isSpecialCase() {
		return specialCase;
	}
	public void setSpecialCase(boolean specialCase) {
		this.specialCase = specialCase;
	}
	public int getNewCropId() {
		return newCropId;
	}
	public void setNewCropId(int newCropId) {
		this.newCropId = newCropId;
	}
	public double getSigCodeValue() {
		return sigCodeValue;
	}
	public void setSigCodeValue(double sigCodeValue) {
		this.sigCodeValue = sigCodeValue;
	}
	

}
