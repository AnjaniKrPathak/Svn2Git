package com.validic;

import java.util.Date;

public class ValidicNutrition {
	
	private int id;
	private String _id;
	private String timestamp;
	private String utc_offset;
	private double calories;
	private double carbohydrates;
	private double fat;
	private double fiber;
	private double protein;
    private double sodium;
    private double water;
    private String meal;
    private String source;
    private String lastUpdated;
    private String entryId;
    private String source_name;
    private String last_updated;
    private String user_id;
    private String clinakosUserId;
    private Date startDate;
    private Date endDate;
    private boolean validated;
    
	
    
    
    
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
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the utc_offset
	 */
	public String getUtc_offset() {
		return utc_offset;
	}
	/**
	 * @param utc_offset the utc_offset to set
	 */
	public void setUtc_offset(String utc_offset) {
		this.utc_offset = utc_offset;
	}
	/**
	 * @return the calories
	 */
	public double getCalories() {
		return calories;
	}
	/**
	 * @param calories the calories to set
	 */
	public void setCalories(double calories) {
		this.calories = calories;
	}
	/**
	 * @return the carbohydrates
	 */
	public double getCarbohydrates() {
		return carbohydrates;
	}
	/**
	 * @param carbohydrates the carbohydrates to set
	 */
	public void setCarbohydrates(double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}
	/**
	 * @return the fat
	 */
	public double getFat() {
		return fat;
	}
	/**
	 * @param fat the fat to set
	 */
	public void setFat(double fat) {
		this.fat = fat;
	}
	/**
	 * @return the fiber
	 */
	public double getFiber() {
		return fiber;
	}
	/**
	 * @param fiber the fiber to set
	 */
	public void setFiber(double fiber) {
		this.fiber = fiber;
	}
	/**
	 * @return the protein
	 */
	public double getProtein() {
		return protein;
	}
	/**
	 * @param protein the protein to set
	 */
	public void setProtein(double protein) {
		this.protein = protein;
	}
	/**
	 * @return the sodium
	 */
	public double getSodium() {
		return sodium;
	}
	/**
	 * @param sodium the sodium to set
	 */
	public void setSodium(double sodium) {
		this.sodium = sodium;
	}
	/**
	 * @return the water
	 */
	public double getWater() {
		return water;
	}
	/**
	 * @param water the water to set
	 */
	public void setWater(double water) {
		this.water = water;
	}
	
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the lastUpdated
	 */
	public String getLastUpdated() {
		return lastUpdated;
	}
	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/**
	 * @return the entryId
	 */
	public String getEntryId() {
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	/**
	 * @return the source_name
	 */
	public String getSource_name() {
		return source_name;
	}
	/**
	 * @param source_name the source_name to set
	 */
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	/**
	 * @return the last_updated
	 */
	public String getLast_updated() {
		return last_updated;
	}
	/**
	 * @param last_updated the last_updated to set
	 */
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the meal
	 */
	public String getMeal() {
		return meal;
	}
	/**
	 * @param meal the meal to set
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}
	public String getClinakosUserId() {
		return clinakosUserId;
	}
	public void setClinakosUserId(String clinakosUserId) {
		this.clinakosUserId = clinakosUserId;
	}
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
}
