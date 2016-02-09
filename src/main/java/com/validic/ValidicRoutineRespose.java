package com.validic;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicRoutineRespose {
	@JsonIgnore
	private Summary summary;
	private List<ValidicRoutineActivity> routine;
	
	public Summary getSummary() {
		return summary;
	}
	public void setSummary(Summary summary) {
		this.summary = summary;
	}
	public List<ValidicRoutineActivity> getRoutine() {
		return routine;
	}
	public void setRoutine(List<ValidicRoutineActivity> routine) {
		this.routine = routine;
	}
	
}
