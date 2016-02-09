package com.clinakos.data.model.patient;

import java.util.Date;

public class ACOPatientMeasure {
	

	private int id ;
	private int patientId ;
	private Date  measureDate ;
	private String mesureType ;
	private String exception ;
	private int active ;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public Date getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(Date measureDate) {
		this.measureDate = measureDate;
	}
	public String getMesureType() {
		return mesureType;
	}
	public void setMesureType(String mesureType) {
		this.mesureType = mesureType;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + active;
		result = prime * result
				+ ((mesureType == null) ? 0 : mesureType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ACOPatientMeasure other = (ACOPatientMeasure) obj;

		if(active == other.active 
				&& mesureType.equals(other.mesureType)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
	
	
	
}
