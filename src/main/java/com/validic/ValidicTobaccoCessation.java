package com.validic;

import java.util.Date;

public class ValidicTobaccoCessation {
	
	private int id;
	private String _id;
	private String clinakosUserId;
	private String timestamp;
	private double utc_offset;
	private double cigarettes_allowed;
	private double cigarettes_smoked;
	private double cravings;
	private String last_smoked;
	private String source;
	private String source_name;
	private String last_updated;
	private String user_id;
	private Date startDate;
	private Date endDate;
	
	
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
	public double getUtc_offset() {
		return utc_offset;
	}
	/**
	 * @param utc_offset the utc_offset to set
	 */
	public void setUtc_offset(double utc_offset) {
		this.utc_offset = utc_offset;
	}
	/**
	 * @return the cigarettes_allowed
	 */
	public double getCigarettes_allowed() {
		return cigarettes_allowed;
	}
	/**
	 * @param cigarettes_allowed the cigarettes_allowed to set
	 */
	public void setCigarettes_allowed(double cigarettes_allowed) {
		this.cigarettes_allowed = cigarettes_allowed;
	}
	/**
	 * @return the cigarettes_smoked
	 */
	public double getCigarettes_smoked() {
		return cigarettes_smoked;
	}
	/**
	 * @param cigarettes_smoked the cigarettes_smoked to set
	 */
	public void setCigarettes_smoked(double cigarettes_smoked) {
		this.cigarettes_smoked = cigarettes_smoked;
	}
	
	/**
	 * @return the last_smoked
	 */
	public String getLast_smoked() {
		return last_smoked;
	}
	/**
	 * @param last_smoked the last_smoked to set
	 */
	public void setLast_smoked(String last_smoked) {
		this.last_smoked = last_smoked;
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
	 * @return the cravings
	 */
	public double getCravings() {
		return cravings;
	}
	/**
	 * @param cravings the cravings to set
	 */
	public void setCravings(double cravings) {
		this.cravings = cravings;
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
	
	
}
