package com.clinakos.data.model.patient;

public class ClinicSubdiagnosis {
	private int id;
	private int clinicDiagnosisId;
	private String subDiagnosisName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicDiagnosisId() {
		return clinicDiagnosisId;
	}
	public void setClinicDiagnosisId(int clinicDiagnosisId) {
		this.clinicDiagnosisId = clinicDiagnosisId;
	}
	public String getSubDiagnosisName() {
		return subDiagnosisName;
	}
	public void setSubDiagnosisName(String subDiagnosisName) {
		this.subDiagnosisName = subDiagnosisName;
	}
	
	
	

}
