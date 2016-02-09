package com.validic;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicNutritionResponse {
	@JsonIgnore
	private Summary summary;
	private List<ValidicNutrition> nutrition;
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
	 * @return the nutrition
	 */
	public List<ValidicNutrition> getNutrition() {
		return nutrition;
	}
	/**
	 * @param nutrition the nutrition to set
	 */
	public void setNutrition(List<ValidicNutrition> nutrition) {
		this.nutrition = nutrition;
	}
	
	

}
