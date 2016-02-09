package com.clinakos.data.model.patient;

import java.util.Date;



public class PatientProviderClinic {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int clinicId;
	private int clinicProviderId;
	private int providerLocationId;
	private int patientId;
	private String clinicName;
	private boolean checkDeleteClinic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicProviderId() {
		return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	public boolean isCheckDeleteClinic() {
		return checkDeleteClinic;
	}
	public void setCheckDeleteClinic(boolean checkDeleteClinic) {
		this.checkDeleteClinic = checkDeleteClinic;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clinicProviderId;
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
		PatientProviderClinic other = (PatientProviderClinic) obj;
		if (clinicProviderId != other.clinicProviderId)
			return false;
		return true;
	}
	public int getProviderLocationId() {
		return providerLocationId;
	}
	public void setProviderLocationId(int providerLocationId) {
		this.providerLocationId = providerLocationId;
	}
	
	
}
