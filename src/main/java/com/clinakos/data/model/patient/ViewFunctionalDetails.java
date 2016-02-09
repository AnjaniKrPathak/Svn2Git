package com.clinakos.data.model.patient;

import java.util.Date;



public class ViewFunctionalDetails {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private String name;
	private String unit;
	private double result;
	private int procedureTypeId;
	private int patientId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public int getProcedureTypeId() {
		return procedureTypeId;
	}
	public void setProcedureTypeId(int procedureTypeId) {
		this.procedureTypeId = procedureTypeId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	
}
