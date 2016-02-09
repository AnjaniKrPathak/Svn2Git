package com.validic;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.mapping.Array;

public class ValidicSleepResponse {
	@JsonIgnore
	private Summary summary;
	private List<ValidicSleep> sleep=null;
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
	 * @return the sleep
	 */
	public List<ValidicSleep> getSleep() {
		if(sleep==null){
			sleep=new ArrayList<ValidicSleep>();
		}
		return sleep;
	}
	/**
	 * @param sleep the sleep to set
	 */
	public void setSleep(List<ValidicSleep> sleep) {
		this.sleep = sleep;
	}
	
	

}
