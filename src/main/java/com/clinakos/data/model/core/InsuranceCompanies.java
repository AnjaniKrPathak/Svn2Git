package com.clinakos.data.model.core;



public class InsuranceCompanies {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private String companyName;
	private int healPlanDetailID;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String country;
	private String zip;
	private String zip4;
	private String phone;
	
	
	private int companyValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyValue(int companyValue) {
		this.companyValue = companyValue;
	}
	public int getCompanyValue() {
		return companyValue;
	}
	public int getHealPlanDetailID() {
		return healPlanDetailID;
	}
	public void setHealPlanDetailID(int healPlanDetailID) {
		this.healPlanDetailID = healPlanDetailID;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getZip4() {
		return zip4;
	}
	public void setZip4(String zip4) {
		this.zip4 = zip4;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
	

}
