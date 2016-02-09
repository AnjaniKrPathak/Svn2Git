/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author SAURABH
 *
 */
public class ProviderUser {

	private int id;
	private int userId;
	private int providerId;
	private int providerLocationId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getProviderLocationId() {
		return providerLocationId;
	}
	public void setProviderLocationId(int providerLocationId) {
		this.providerLocationId = providerLocationId;
	}
	
	
}
