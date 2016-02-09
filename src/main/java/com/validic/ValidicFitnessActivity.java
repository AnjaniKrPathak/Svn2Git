package com.validic;

import java.util.Date;

public class ValidicFitnessActivity {
	
	
	private int id;
	private String clinakosUserId;
	private String _id;
	private String timestamp;
	private String utc_offset;
	private String type;
	private String intensity;
	private String start_time;
	private double distance;
	private double duration;
	private double calories;
	private String source;
	private String source_name;
	private String last_updated;
	private String activityId;
	private String user_id;
	private Date startDate;
	private Date endDate;
	private String activity_category;
	private boolean validated;
	
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIntensity() {
		return intensity;
	}
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
	 * @return the start_time
	 */
	public String getStart_time() {
		return start_time;
	}
	/**
	 * @param start_time the start_time to set
	 */
	public void setStart_time(String start_time) {
		this.start_time = start_time;
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
	public String getActivity_category() {
		return activity_category;
	}
	public void setActivity_category(String activity_category) {
		this.activity_category = activity_category;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
	
}
