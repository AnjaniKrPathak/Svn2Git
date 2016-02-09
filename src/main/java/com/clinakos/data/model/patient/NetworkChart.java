package com.clinakos.data.model.patient;

public class NetworkChart {
	
	private int id;	
	private int patient_id;
	private int doctor_id;
	private String doctor_name;
	private int doctor_prescriptions;
	private int total_connection;
	public String path4Net;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public int getDoctor_prescriptions() {
		return doctor_prescriptions;
	}
	public void setDoctor_prescriptions(int doctor_prescriptions) {
		this.doctor_prescriptions = doctor_prescriptions;
	}
	
	public int getTotal_connection() {
		return total_connection;
	}
	public void setTotal_connection(int total_connection) {
		this.total_connection = total_connection;
	}	

	public String getPath4Net() {
		return path4Net;
	}



	public void setPath4Net(String path4Net) {
		this.path4Net = path4Net;
	}

}
