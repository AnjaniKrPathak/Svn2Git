package com.validic;

import java.util.Date;

public class ValidicBiometricmeasurement {
	
	private int id;
	private String clinakosUserId;
	private String _id;
	private String timestamp;
	private String utc_offset;
	private double blood_calcium;
	private double blood_chromium;
	private double blood_folic_acid;
	private double blood_magnesium;
	private double blood_potassium;
	private double blood_sodium;
	private double blood_vitamin_b12;
	private double blood_zinc;
	private double creatine_kinase;
	private double crp;
	private double diastolic;
	private double systolic;
	private double ferritin;
	private double hdl;
	private double hscrp;
	private double il6;
	private double ldl;
	private double resting_heartrate;
	private double testosterone;
	private double total_cholesterol;
	private double tsh;
	
	private double vitamin_d;
	private double white_cell_count;
	private double spo2;
	private double temperature;
	private String source;
	private String source_name;
	private String last_updated;
	private String dataId;
	private String user_id;
	private Double uric_acid;
	
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
	 * @return the blood_calcium
	 */
	public double getBlood_calcium() {
		return blood_calcium;
	}
	/**
	 * @param blood_calcium the blood_calcium to set
	 */
	public void setBlood_calcium(double blood_calcium) {
		this.blood_calcium = blood_calcium;
	}
	/**
	 * @return the blood_chromium
	 */
	public double getBlood_chromium() {
		return blood_chromium;
	}
	/**
	 * @param blood_chromium the blood_chromium to set
	 */
	public void setBlood_chromium(double blood_chromium) {
		this.blood_chromium = blood_chromium;
	}
	/**
	 * @return the blood_folic_acid
	 */
	public double getBlood_folic_acid() {
		return blood_folic_acid;
	}
	/**
	 * @param blood_folic_acid the blood_folic_acid to set
	 */
	public void setBlood_folic_acid(double blood_folic_acid) {
		this.blood_folic_acid = blood_folic_acid;
	}
	/**
	 * @return the blood_magnesium
	 */
	public double getBlood_magnesium() {
		return blood_magnesium;
	}
	/**
	 * @param blood_magnesium the blood_magnesium to set
	 */
	public void setBlood_magnesium(double blood_magnesium) {
		this.blood_magnesium = blood_magnesium;
	}
	/**
	 * @return the blood_potassium
	 */
	public double getBlood_potassium() {
		return blood_potassium;
	}
	/**
	 * @param blood_potassium the blood_potassium to set
	 */
	public void setBlood_potassium(double blood_potassium) {
		this.blood_potassium = blood_potassium;
	}
	/**
	 * @return the blood_sodium
	 */
	public double getBlood_sodium() {
		return blood_sodium;
	}
	/**
	 * @param blood_sodium the blood_sodium to set
	 */
	public void setBlood_sodium(double blood_sodium) {
		this.blood_sodium = blood_sodium;
	}
	/**
	 * @return the blood_vitamin_b12
	 */
	public double getBlood_vitamin_b12() {
		return blood_vitamin_b12;
	}
	/**
	 * @param blood_vitamin_b12 the blood_vitamin_b12 to set
	 */
	public void setBlood_vitamin_b12(double blood_vitamin_b12) {
		this.blood_vitamin_b12 = blood_vitamin_b12;
	}
	/**
	 * @return the blood_zinc
	 */
	public double getBlood_zinc() {
		return blood_zinc;
	}
	/**
	 * @param blood_zinc the blood_zinc to set
	 */
	public void setBlood_zinc(double blood_zinc) {
		this.blood_zinc = blood_zinc;
	}
	/**
	 * @return the creatine_kinase
	 */
	public double getCreatine_kinase() {
		return creatine_kinase;
	}
	/**
	 * @param creatine_kinase the creatine_kinase to set
	 */
	public void setCreatine_kinase(double creatine_kinase) {
		this.creatine_kinase = creatine_kinase;
	}
	/**
	 * @return the crp
	 */
	public double getCrp() {
		return crp;
	}
	/**
	 * @param crp the crp to set
	 */
	public void setCrp(double crp) {
		this.crp = crp;
	}
	/**
	 * @return the diastolic
	 */
	public double getDiastolic() {
		return diastolic;
	}
	/**
	 * @param diastolic the diastolic to set
	 */
	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
	/**
	 * @return the systolic
	 */
	public double getSystolic() {
		return systolic;
	}
	/**
	 * @param systolic the systolic to set
	 */
	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}
	/**
	 * @return the ferritin
	 */
	public double getFerritin() {
		return ferritin;
	}
	/**
	 * @param ferritin the ferritin to set
	 */
	public void setFerritin(double ferritin) {
		this.ferritin = ferritin;
	}
	/**
	 * @return the hdl
	 */
	public double getHdl() {
		return hdl;
	}
	/**
	 * @param hdl the hdl to set
	 */
	public void setHdl(double hdl) {
		this.hdl = hdl;
	}
	/**
	 * @return the hscrp
	 */
	public double getHscrp() {
		return hscrp;
	}
	/**
	 * @param hscrp the hscrp to set
	 */
	public void setHscrp(double hscrp) {
		this.hscrp = hscrp;
	}
	/**
	 * @return the il6
	 */
	public double getIl6() {
		return il6;
	}
	/**
	 * @param il6 the il6 to set
	 */
	public void setIl6(double il6) {
		this.il6 = il6;
	}
		/**
	 * @return the resting_heartrate
	 */
	public double getResting_heartrate() {
		return resting_heartrate;
	}
	/**
	 * @param resting_heartrate the resting_heartrate to set
	 */
	public void setResting_heartrate(double resting_heartrate) {
		this.resting_heartrate = resting_heartrate;
	}
	/**
	 * @return the testosterone
	 */
	public double getTestosterone() {
		return testosterone;
	}
	/**
	 * @param testosterone the testosterone to set
	 */
	public void setTestosterone(double testosterone) {
		this.testosterone = testosterone;
	}
	/**
	 * @return the total_cholesterol
	 */
	public double getTotal_cholesterol() {
		return total_cholesterol;
	}
	/**
	 * @param total_cholesterol the total_cholesterol to set
	 */
	public void setTotal_cholesterol(double total_cholesterol) {
		this.total_cholesterol = total_cholesterol;
	}
	/**
	 * @return the tsh
	 */
	public double getTsh() {
		return tsh;
	}
	/**
	 * @param tsh the tsh to set
	 */
	public void setTsh(double tsh) {
		this.tsh = tsh;
	}
	
	/**
	 * @return the vitamin_d
	 */
	public double getVitamin_d() {
		return vitamin_d;
	}
	/**
	 * @param vitamin_d the vitamin_d to set
	 */
	public void setVitamin_d(double vitamin_d) {
		this.vitamin_d = vitamin_d;
	}
	/**
	 * @return the white_cell_count
	 */
	public double getWhite_cell_count() {
		return white_cell_count;
	}
	/**
	 * @param white_cell_count the white_cell_count to set
	 */
	public void setWhite_cell_count(double white_cell_count) {
		this.white_cell_count = white_cell_count;
	}
	/**
	 * @return the spo2
	 */
	public double getSpo2() {
		return spo2;
	}
	/**
	 * @param spo2 the spo2 to set
	 */
	public void setSpo2(double spo2) {
		this.spo2 = spo2;
	}
	/**
	 * @return the temperature
	 */
	public double getTemperature() {
		return temperature;
	}
	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(double temperature) {
		this.temperature = temperature;
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
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}
	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
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
	 * @return the ldl
	 */
	public double getLdl() {
		return ldl;
	}
	/**
	 * @param ldl the ldl to set
	 */
	public void setLdl(double ldl) {
		this.ldl = ldl;
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
	 * @return the uric_acid
	 */
	public Double getUric_acid() {
		return uric_acid;
	}
	/**
	 * @param uric_acid the uric_acid to set
	 */
	public void setUric_acid(Double uric_acid) {
		this.uric_acid = uric_acid;
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