package com.clinakos.data.model.patient;

public class DrugCategoryFilter {
	
	private String drugCategoryName;
	private double drugId;
	private double drugNameId;
	private String dosageForm;
	
	
	
	public DrugCategoryFilter() {
		super();
	}
	public DrugCategoryFilter(double drugId, double drugNameId,
			String dosageForm) {
		super();
		this.drugId = drugId;
		this.drugNameId = drugNameId;
		this.dosageForm = dosageForm;
	}
	public String getDrugCategoryName() {
		return drugCategoryName;
	}
	public void setDrugCategoryName(String drugCategoryName) {
		this.drugCategoryName = drugCategoryName;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((drugCategoryName == null) ? 0 : drugCategoryName.hashCode());
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
		DrugCategoryFilter other = (DrugCategoryFilter) obj;
		if (drugCategoryName == null) {
			if (other.drugCategoryName != null)
				return false;
		} else if (!drugCategoryName.equalsIgnoreCase(other.drugCategoryName))
			return false;
		return true;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	
	

}
