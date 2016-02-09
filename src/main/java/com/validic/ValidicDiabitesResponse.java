package com.validic;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicDiabitesResponse {
	@JsonIgnore
	private Summary summary;
	
	private List<ValidicDiabetesMeasurements> diabetes=new ArrayList<ValidicDiabetesMeasurements>();
	
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
	 * @return the diabetes
	 */
	public List<ValidicDiabetesMeasurements> getDiabetes() {
		return diabetes;
	}
	/**
	 * @param diabetes the diabetes to set
	 */
	public void setDiabetes(List<ValidicDiabetesMeasurements> diabetes) {
		this.diabetes = diabetes;
	}
	
	
	

}
