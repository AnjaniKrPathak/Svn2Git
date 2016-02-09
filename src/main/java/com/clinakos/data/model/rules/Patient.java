package com.clinakos.data.model.rules;

public class Patient {
	
	private String id;
	private String bloodGroup;
	private int haemoglobinLevel;
	private double inr;
	
	public Patient() {
		
	}
	
	public Patient(String id) {
		this.id = id;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public int getHaemoglobinLevel() {
		return haemoglobinLevel;
	}

	public void setHaemoglobinLevel(int haemoglobinLevel) {
		this.haemoglobinLevel = haemoglobinLevel;
	}

	public double getInr() {
		return inr;
	}

	public void setInr(double inr) {
		this.inr = inr;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}


}
