package com.clinakos.data.model.patient;

import java.util.Date;

import org.primefaces.model.SelectableDataModel;

public class FormularyChart implements SelectableDataModel<FormularyChart> {
	
	
	private int id;
	private int medicineId;
	
	private String formulayTier;	
	private String patientId;
	private String fName;
	private String lName;
	private String mName;
	private String dob;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getFormulayTier() {
		return formulayTier;
	}
	public void setFormulayTier(String formulayTier) {
		this.formulayTier = formulayTier;
	}
	
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public Object getRowKey(FormularyChart object) {
		// TODO Auto-generated method stub
		return null;
	}

	public FormularyChart getRowData(String rowKey) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	

}
