package com.validic;

public class UserRestResponse {
	private UserResponse user;
	private String code;
	private String message;
	
	
	
	/**
	 * @return the user
	 */
	
	public UserResponse getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(UserResponse user) {
		this.user = user;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
