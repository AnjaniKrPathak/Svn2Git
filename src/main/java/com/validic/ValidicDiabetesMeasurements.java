package com.validic;




public class ValidicDiabetesMeasurements {
	
	private int id;
	private String clinakosUserId;
    private String _id;
    private String timestamp;
    private String utc_offset;
    private double c_peptide;
    private double fasting_plasma_glucose_test;
    private double hba1c;
    private double insulin;
    private double oral_glucose_tolerance_test;
    private double random_plasma_glucose_test;
    private double triglyceride;
    private double blood_glucose;
    private String source;
    private String source_name;
    private String activityId;
    private String last_updated;
    private String user_id;
    private boolean validated;
    
    
	
	public double getHba1c() {
		return hba1c;
	}
	public void setHba1c(double hba1c) {
		this.hba1c = hba1c;
	}
	public double getInsulin() {
		return insulin;
	}
	public void setInsulin(double insulin) {
		this.insulin = insulin;
	}
	
	
	public double getTriglyceride() {
		return triglyceride;
	}
	public void setTriglyceride(double triglyceride) {
		this.triglyceride = triglyceride;
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
	 * @return the fasting_plasma_glucose_test
	 */
	public double getFasting_plasma_glucose_test() {
		return fasting_plasma_glucose_test;
	}
	/**
	 * @param fasting_plasma_glucose_test the fasting_plasma_glucose_test to set
	 */
	public void setFasting_plasma_glucose_test(double fasting_plasma_glucose_test) {
		this.fasting_plasma_glucose_test = fasting_plasma_glucose_test;
	}
	/**
	 * @return the oral_glucose_tolerance_test
	 */
	public double getOral_glucose_tolerance_test() {
		return oral_glucose_tolerance_test;
	}
	/**
	 * @param oral_glucose_tolerance_test the oral_glucose_tolerance_test to set
	 */
	public void setOral_glucose_tolerance_test(double oral_glucose_tolerance_test) {
		this.oral_glucose_tolerance_test = oral_glucose_tolerance_test;
	}
	/**
	 * @return the random_plasma_glucose_test
	 */
	public double getRandom_plasma_glucose_test() {
		return random_plasma_glucose_test;
	}
	/**
	 * @param random_plasma_glucose_test the random_plasma_glucose_test to set
	 */
	public void setRandom_plasma_glucose_test(double random_plasma_glucose_test) {
		this.random_plasma_glucose_test = random_plasma_glucose_test;
	}
	/**
	 * @return the blood_glucose
	 */
	public double getBlood_glucose() {
		return blood_glucose;
	}
	/**
	 * @param blood_glucose the blood_glucose to set
	 */
	public void setBlood_glucose(double blood_glucose) {
		this.blood_glucose = blood_glucose;
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
	 * @return the c_peptide
	 */
	public double getC_peptide() {
		return c_peptide;
	}
	/**
	 * @param c_peptide the c_peptide to set
	 */
	public void setC_peptide(double c_peptide) {
		this.c_peptide = c_peptide;
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
	public String getClinakosUserId() {
		return clinakosUserId;
	}
	public void setClinakosUserId(String clinakosUserId) {
		this.clinakosUserId = clinakosUserId;
	}
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
}
