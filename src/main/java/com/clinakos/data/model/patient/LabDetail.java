package com.clinakos.data.model.patient;

public class LabDetail {
	private int id;
	private String labName;
	private String genericMedicineName;;
	private boolean selected;
	private String labfrequencyDetail;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getGenericMedicineName() {
		return genericMedicineName;
	}
	public void setGenericMedicineName(String genericMedicineName) {
		this.genericMedicineName = genericMedicineName;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getLabfrequencyDetail() {
		return labfrequencyDetail;
	}
	public void setLabfrequencyDetail(String labfrequencyDetail) {
		this.labfrequencyDetail = labfrequencyDetail;
	}
	
	
	

}
