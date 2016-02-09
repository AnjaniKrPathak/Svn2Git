package com.validic;

import org.codehaus.jackson.annotate.JsonIgnore;

public class ValidicAppResponse {

	
	private Summary summary;
	
	@JsonIgnore
	private DeviceApplications apps;

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

	public DeviceApplications getApps() {
		return apps;
	}

	public void setApps(DeviceApplications apps) {
		this.apps = apps;
	}

	
	
	

}
