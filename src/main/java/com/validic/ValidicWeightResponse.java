package com.validic;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicWeightResponse {

	@JsonIgnore
	private Summary summary;
	private List<ValidicWeight> weight;
	
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	public List<ValidicWeight> getWeight() {
		return weight;
	}
	public void setWeight(List<ValidicWeight> weight) {
		this.weight = weight;
	}
	
	
}
