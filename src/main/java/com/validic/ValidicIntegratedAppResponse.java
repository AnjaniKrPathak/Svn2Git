package com.validic;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ValidicIntegratedAppResponse {
	
	private List<App> apps;
	@JsonIgnore
    private Summary summary;
    
	
	
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
	 * @return the apps
	 */
	public List<App> getApps() {
		return apps;
	}
	/**
	 * @param apps the apps to set
	 */
	public void setApps(List<App> apps) {
		this.apps = apps;
	}
	
	
	

}
