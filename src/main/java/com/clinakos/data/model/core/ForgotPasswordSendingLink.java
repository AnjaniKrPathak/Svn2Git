/**
 * 
 */
package com.clinakos.data.model.core;

import java.util.Date;

/**
 * @author Saurabh
 *
 */
public class ForgotPasswordSendingLink {
	private int id;
	private int userId;
	private String sendingLink;
	private Date createdDate;
	private Date modifiedDate;
	private boolean active;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getSendingLink() {
		return sendingLink;
	}
	public void setSendingLink(String sendingLink) {
		this.sendingLink = sendingLink;
	}
	

}
