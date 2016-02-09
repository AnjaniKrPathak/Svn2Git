package com.validic;

import java.util.ArrayList;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicBiometricsMeasurementResponse {
	@JsonIgnore
	private Summary summary;
	private ArrayList<ValidicBiometricmeasurement> biometrics;
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
	 * @return the biometrics
	 */
	public ArrayList<ValidicBiometricmeasurement> getBiometrics() {
		if(biometrics==null){
			biometrics=new ArrayList<ValidicBiometricmeasurement>();
		}
		return biometrics;
	}
	/**
	 * @param biometrics the biometrics to set
	 */
	public void setBiometrics(ArrayList<ValidicBiometricmeasurement> biometrics) {
		this.biometrics = biometrics;
	}
	
	
	
	

}
