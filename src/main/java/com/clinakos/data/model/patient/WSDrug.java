package com.clinakos.data.model.patient;

public class WSDrug {
	private int Id;
	private String drugName;
	private String genericName;
	private Double drugNameId;
	//private String drug;
	private Double drugId;
	private String dose;
	private String dataProvider;
	private String dosageForm;
	private String dosageFormoverride;
	private String route;
	private String drugDetail;

	private String therapeuticCategory;//Added By Nagaraj
	private String drugCategory;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public Double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(Double drugNameId) {
		this.drugNameId = drugNameId;
	}
	/*public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}*/
	public Double getDrugId() {
		return drugId;
	}
	public void setDrugId(Double drugId) {
		this.drugId = drugId;
	}
	public String getDose() {
		return dose;
	}
	public void setDose(String dose) {
		this.dose = dose;
	}
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getDrugDetail() {
		return drugDetail;
	}
	public void setDrugDetail(String drugDetail) {
		this.drugDetail = drugDetail;
	}
	public String getTherapeuticCategory() {
		return therapeuticCategory;
	}
	public void setTherapeuticCategory(String therapeuticCategory) {
		this.therapeuticCategory = therapeuticCategory;
	}
	public String getDrugCategory() {
		return drugCategory;
	}
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}
	public String getDosageFormoverride() {
		return dosageFormoverride;
	}
	public void setDosageFormoverride(String dosageFormoverride) {
		this.dosageFormoverride = dosageFormoverride;
	}
	
	
	

}
