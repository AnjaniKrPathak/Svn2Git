package com.validic;

import java.util.Date;

public class ValidicRoutineActivity {
	
	private int id;
	private String user_id;
	private String clinakosUserId;
	private String _id;
	private String utc_offset;
	private String  timestamp;
	private double  distance;
	private double  floors;
	private String  elevation;
	private double  steps;
	private double  calories_burned;
	private String last_updated;
	private String activityId;
	private String source;
	private String source_name;
	private Date startDate;
	private Date endDate;
	private boolean validated;
	private String water;
	
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getUtc_offset() {
		return utc_offset;
	}
	public void setUtc_offset(String utc_offset) {
		this.utc_offset = utc_offset;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getFloors() {
		return floors;
	}
	public void setFloors(double floors) {
		this.floors = floors;
	}
	public double getSteps() {
		return steps;
	}
	public void setSteps(double steps) {
		this.steps = steps;
	}
	public double getCalories_burned() {
		return calories_burned;
	}
	public void setCalories_burned(double calories_burned) {
		this.calories_burned = calories_burned;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	public String getElevation() {
		return elevation;
	}
	public void setElevation(String elevation) {
		this.elevation = elevation;
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
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	
}
