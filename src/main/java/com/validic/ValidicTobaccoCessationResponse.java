package com.validic;


import java.util.List;

public class ValidicTobaccoCessationResponse {
	
	private Summary summary;
	private List<ValidicTobaccoCessation> tobacco_cessation;
	
	
	/**
	 * @return the summary
	 */
	public Summary getSummary() {
		return summary;
	}
	/**
	 * @param summary the summary to set
	 */
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	/**
	 * @return the tobacco_cessation
	 */
	public List<ValidicTobaccoCessation> getTobacco_cessation() {
		return tobacco_cessation;
	}
	/**
	 * @param tobacco_cessation the tobacco_cessation to set
	 */
	public void setTobacco_cessation(List<ValidicTobaccoCessation> tobacco_cessation) {
		this.tobacco_cessation = tobacco_cessation;
	}
	
}
