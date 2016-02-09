
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with Doctor's Personal Details
 */

package com.clinakos.data.model.patient;

public class CareTeam {

	private int id;
	private int patientId;
	private int doctorId;
	private int providerId;
	private String doctorFirstName;
	private String doctorLastName;
	
	/*private String physicianName;
	private String specialty;
	private String phone;
	private String email;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;*/
	
	private boolean isActive;
	private String specialty;
	private String address;
	private String phoneNumber;
	private String degree;
	private String email;
	private String npi;
	private boolean clinakosUser;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	/*public String getPhysicianName() {
		return physicianName;
	}
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	}*/
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the doctorFirstName
	 */
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	/**
	 * @param doctorFirstName the doctorFirstName to set
	 */
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	/**
	 * @return the doctorLastName
	 */
	public String getDoctorLastName() {
		return doctorLastName;
	}
	/**
	 * @param doctorLastName the doctorLastName to set
	 */
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getNpi() {
		return npi;
	}
	public void setNpi(String npi) {
		this.npi = npi;
	}
	public boolean isClinakosUser() {
		return clinakosUser;
	}
	public void setClinakosUser(boolean clinakosUser) {
		this.clinakosUser = clinakosUser;
	}
	
	

}
