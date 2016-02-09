package com.validic;

import java.util.List;

public class ValidicUserProfile {
	private int id;
	private String gender;
	private String location;
	private String birth_year;
	private String country;
	private Double height;
	private Double weight;
	private List<String> applications;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	/**
	 * @return the height
	 */
	public Double getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(Double height) {
		this.height = height;
	}
	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	/**
	 * @return the applications
	 */
	public List<String> getApplications() {
		return applications;
	}
	/**
	 * @param applications the applications to set
	 */
	public void setApplications(List<String> applications) {
		this.applications = applications;
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
	 * @return the birth_year
	 */
	public String getBirth_year() {
		return birth_year;
	}
	/**
	 * @param birth_year the birth_year to set
	 */
	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}
	
	

}
