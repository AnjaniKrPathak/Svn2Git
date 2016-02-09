package com.validic;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicFitnessActivityResponse {
	@JsonIgnore
	private Summary summary;
	private List<ValidicFitnessActivity> fitness=null;
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
	 * @return the fitness
	 */
	public List<ValidicFitnessActivity> getFitness() {
		return fitness;
	}
	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(List<ValidicFitnessActivity> fitness) {
		this.fitness = fitness;
	}
	

}
