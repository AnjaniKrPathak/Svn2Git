package com.validic;

import java.util.Date;



public class ValidicWeight {
	
	private int id;
	private String user_id;
	private String clinakosUserId;
	private String _id;
	private String timestamp;
	private String utc_offset;
	private double weight;
	private double height;
	private double free_mass;
	private double fat_percent;
	private double mass_weight;
	private double bmi;
	private String source;
	private String source_name;
	private String last_updated;
	private String dataId;
	private Date startDate;
	private Date endDate;
	private boolean validated;
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getUtc_offset() {
		return utc_offset;
	}
	public void setUtc_offset(String utc_offset) {
		this.utc_offset = utc_offset;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getFree_mass() {
		return free_mass;
	}
	public void setFree_mass(double free_mass) {
		this.free_mass = free_mass;
	}
	public double getFat_percent() {
		return fat_percent;
	}
	public void setFat_percent(double fat_percent) {
		this.fat_percent = fat_percent;
	}
	public double getMass_weight() {
		return mass_weight;
	}
	public void setMass_weight(double mass_weight) {
		this.mass_weight = mass_weight;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSource_name() {
		return source_name;
	}
	public void setSource_name(String source_name) {
		this.source_name = source_name;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
