package com.validic;

public class ValidicUserCredential {
	private int id;
	private int userId;
	private String organizationId;
	private String organizationAccessToken;
	private String customerAuthenticationToken;
	private String validicUserId;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the organizationId
	 */
	public String getOrganizationId() {
		return organizationId;
	}
	/**
	 * @param organizationId the organizationId to set
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
	/**
	 * @return the organizationAccessToken
	 */
	public String getOrganizationAccessToken() {
		return organizationAccessToken;
	}
	/**
	 * @param organizationAccessToken the organizationAccessToken to set
	 */
	public void setOrganizationAccessToken(String organizationAccessToken) {
		this.organizationAccessToken = organizationAccessToken;
	}
	/**
	 * @return the customerAuthenticationToken
	 */
	public String getCustomerAuthenticationToken() {
		return customerAuthenticationToken;
	}
	/**
	 * @param customerAuthenticationToken the customerAuthenticationToken to set
	 */
	public void setCustomerAuthenticationToken(String customerAuthenticationToken) {
		this.customerAuthenticationToken = customerAuthenticationToken;
	}
	/**
	 * @return the validicUserId
	 */
	public String getValidicUserId() {
		return validicUserId;
	}
	/**
	 * @param validicUserId the validicUserId to set
	 */
	public void setValidicUserId(String validicUserId) {
		this.validicUserId = validicUserId;
	}

}
