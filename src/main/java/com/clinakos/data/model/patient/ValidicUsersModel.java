package com.clinakos.data.model.patient;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidicUsersModel {

	private int clinakosUserId;
	private String firstName;
	private String lastName;
	private String userAccessToken;
	private String appSourceName;
	private Set<String> appSource;
	private Map<Integer,Set<String>> userAppMap;
	private String validicUserId;
	
	public int getClinakosUserId() {
		return clinakosUserId;
	}
	public void setClinakosUserId(int clinakosUserId) {
		this.clinakosUserId = clinakosUserId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserAccessToken() {
		return userAccessToken;
	}
	public void setUserAccessToken(String userAccessToken) {
		this.userAccessToken = userAccessToken;
	}
	
	public String getAppSourceName() {
		return appSourceName;
	}
	public void setAppSourceName(String appSourceName) {
		this.appSourceName = appSourceName;
	}
	public Set<String> getAppSource() {
		return appSource;
	}
	public void setAppSource(Set<String> appSource) {
		this.appSource = appSource;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + clinakosUserId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValidicUsersModel other = (ValidicUsersModel) obj;
		if (clinakosUserId != other.clinakosUserId)
			return false;
		return true;
	}
	public Map<Integer, Set<String>> getUserAppMap() {
		return userAppMap;
	}
	public void setUserAppMap(Map<Integer, Set<String>> userAppMap) {
		this.userAppMap = userAppMap;
	}
	public String getValidicUserId() {
		return validicUserId;
	}
	public void setValidicUserId(String validicUserId) {
		this.validicUserId = validicUserId;
	}

	
	
}
