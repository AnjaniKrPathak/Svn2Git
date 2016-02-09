package com.clinakos.data.model.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProviderDetail {
	
	private int id;
	private String name;
	private boolean isActive;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String primaryPhoneNumber;
	private String primaryFaxNumber;
	private String accountId;
	private String siteId;
	private String locationName;
	private String organizationType;
	private Set<ProviderLocation> providerLocation = new HashSet<ProviderLocation>(0);
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the primaryPhoneNumber
	 */
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	/**
	 * @param primaryPhoneNumber the primaryPhoneNumber to set
	 */
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	/**
	 * @return the primaryFaxNumber
	 */
	public String getPrimaryFaxNumber() {
		return primaryFaxNumber;
	}
	/**
	 * @param primaryFaxNumber the primaryFaxNumber to set
	 */
	public void setPrimaryFaxNumber(String primaryFaxNumber) {
		this.primaryFaxNumber = primaryFaxNumber;
	}
	/**
	 * @return the accountId
	 */
	public String getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	/**
	 * @return the siteId
	 */
	public String getSiteId() {
		return siteId;
	}
	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
	public Set<ProviderLocation> getProviderLocation() {
		return providerLocation;
	}
	public void setProviderLocation(Set<ProviderLocation> providerLocation) {
		this.providerLocation = providerLocation;
	}
	
	
	
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(String organizationType) {
		this.organizationType = organizationType;
	}
	
	
	
	

}
