

/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with Doctor's Personal Details
 */
package com.clinakos.data.model.patient;

import java.io.Serializable;

public class DoctorDetail implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2096710675652521614L;
	private int id;
	private Integer userId;
	private String fName;
	private String lName;
	private String speciality;
	private String gender;
	private String doorNo;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private String phoneNumber;
	private String emailId;
	private String degree;
	private String midName;

	private boolean isActive;
	
	private String dea;
	private String upin;
	private String npi;
	private String docLicenseNumber;
	private String docLicenseState;
	private boolean clinakosUser;
	private String address;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the dea
	 */
	public String getDea() {
		return dea;
	}

	/**
	 * @param dea the dea to set
	 */
	public void setDea(String dea) {
		this.dea = dea;
	}

	/**
	 * @return the upin
	 */
	public String getUpin() {
		return upin;
	}

	/**
	 * @param upin the upin to set
	 */
	public void setUpin(String upin) {
		this.upin = upin;
	}

	/**
	 * @return the npi
	 */
	public String getNpi() {
		return npi;
	}

	/**
	 * @param npi the npi to set
	 */
	public void setNpi(String npi) {
		this.npi = npi;
	}

	/**
	 * @return the docLicenseNumber
	 */
	public String getDocLicenseNumber() {
		return docLicenseNumber;
	}

	/**
	 * @param docLicenseNumber the docLicenseNumber to set
	 */
	public void setDocLicenseNumber(String docLicenseNumber) {
		this.docLicenseNumber = docLicenseNumber;
	}

	/**
	 * @return the docLicenseState
	 */
	public String getDocLicenseState() {
		return docLicenseState;
	}

	/**
	 * @param docLicenseState the docLicenseState to set
	 */
	public void setDocLicenseState(String docLicenseState) {
		this.docLicenseState = docLicenseState;
	}

	/**
	 * @return the midName
	 */
	public String getMidName() {
		return midName;
	}

	/**
	 * @param midName the midName to set
	 */
	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isClinakosUser() {
		return clinakosUser;
	}

	public void setClinakosUser(boolean clinakosUser) {
		this.clinakosUser = clinakosUser;
	}

	
	
	

	
}
