package com.clinakos.data.model.patient;

public class DrugCategoryDetail {
    
	private int id;
	private String medicineCategory;
	private int drugCountPerCategory;
	
	public String getMedicineCategory() {
		return medicineCategory;
	}
	public void setMedicineCategory(String medicineCategory) {
		this.medicineCategory = medicineCategory;
	}
	
	public int getDrugCountPerCategory() {
		return drugCountPerCategory;
	}
	public void setDrugCountPerCategory(int drugCountPerCategory) {
		this.drugCountPerCategory = drugCountPerCategory;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
