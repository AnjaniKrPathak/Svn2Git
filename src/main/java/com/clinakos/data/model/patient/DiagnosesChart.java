package com.clinakos.data.model.patient;

public class DiagnosesChart {
	
	private int id;
	private String icd_id;
	private String icd_code_type;
	private String diagnosesName;
	private int noofpatients;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIcd_id() {
		return icd_id;
	}
	public void setIcd_id(String icd_id) {
		this.icd_id = icd_id;
	}
	public String getIcd_code_type() {
		return icd_code_type;
	}
	public void setIcd_code_type(String icd_code_type) {
		this.icd_code_type = icd_code_type;
	}
	public String getDiagnosesName() {
		return diagnosesName;
	}
	public void setDiagnosesName(String diagnosesName) {
		this.diagnosesName = diagnosesName;
	}
	public int getNoofpatients() {
		return noofpatients;
	}
	public void setNoofpatients(int noofpatients) {
		this.noofpatients = noofpatients;
	}
	
	

}
