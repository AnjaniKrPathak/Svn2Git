package com.clinakos.data.model.core;

public class ProviderLocation { 
	
	private int id;
	private int providerId;
	
	private String addressLine1;
	private String addressLine2;
	private String zipCode;
	private String city;
	private String state;
	private String country;
	private String primaryPhoneNumber;
	private String primaryContactNumber;
	private String faxNumber;
	private String location;
	private String accountId;
	private String siteId;
	private int zip4Code;
	
	private ProviderDetail providerDetail;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}
	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	/**
	 * @return the zip4Code
	 */
	public int getZip4Code() {
		return zip4Code;
	}
	/**
	 * @param zip4Code the zip4Code to set
	 */
	public void setZip4Code(int zip4Code) {
		this.zip4Code = zip4Code;
	}
	
	public ProviderDetail getProviderDetail() {
		return providerDetail;
	}
	public void setProviderDetail(ProviderDetail providerDetail) {
		this.providerDetail = providerDetail;
	}
	
	
}
