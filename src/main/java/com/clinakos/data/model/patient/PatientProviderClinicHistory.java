package com.clinakos.data.model.patient;

import java.util.Date;



public class PatientProviderClinicHistory {

	private int id;
	private int clinicProviderId;
	private int patientId;
	private Date deletedate;
	private String prescibername;
	private int providerid;
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
	public String getPrescibername() {
		return prescibername;
	}
	public void setPrescibername(String prescibername) {
		this.prescibername = prescibername;
	}
	public Date getDeletedate() {
		return deletedate;
	}
	public void setDeletedate(Date deletedate) {
		this.deletedate = deletedate;
	}
	public int getProviderid() {
		return providerid;
	}
	public void setProviderid(int providerid) {
		this.providerid = providerid;
	}
	
	
	
}
