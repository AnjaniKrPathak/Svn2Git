package com.validic;

import java.util.Date;

public class ValidicSleep {

	private int id;
	private String _id;
	private String clinakosUserId;
	private String timestamp;
	private String utc_offset;
	private double total_sleep;
	private double awake;
	private double deep;
	private double light;
	private double rem;
	private double times_woken;
	private String source;
	private String source_name;
	private String last_updated;
	private String activityId;
	private String user_id;
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
	 * @return the total_sleep
	 */
	public double getTotal_sleep() {
		return total_sleep;
	}
	/**
	 * @param total_sleep the total_sleep to set
	 */
	public void setTotal_sleep(double total_sleep) {
		this.total_sleep = total_sleep;
	}
	/**
	 * @return the awake
	 */
	public double getAwake() {
		return awake;
	}
	/**
	 * @param awake the awake to set
	 */
	public void setAwake(double awake) {
		this.awake = awake;
	}
	/**
	 * @return the deep
	 */
	public double getDeep() {
		return deep;
	}
	/**
	 * @param deep the deep to set
	 */
	public void setDeep(double deep) {
		this.deep = deep;
	}
	/**
	 * @return the light
	 */
	public double getLight() {
		return light;
	}
	/**
	 * @param light the light to set
	 */
	public void setLight(double light) {
		this.light = light;
	}
	/**
	 * @return the rem
	 */
	public double getRem() {
		return rem;
	}
	/**
	 * @param rem the rem to set
	 */
	public void setRem(double rem) {
		this.rem = rem;
	}
	/**
	 * @return the times_woken
	 */
	public double getTimes_woken() {
		return times_woken;
	}
	/**
	 * @param times_woken the times_woken to set
	 */
	public void setTimes_woken(double times_woken) {
		this.times_woken = times_woken;
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
	 * @return the activityId
	 */
	public String getActivityId() {
		return activityId;
	}
	/**
	 * @param activityId the activityId to set
	 */
	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
