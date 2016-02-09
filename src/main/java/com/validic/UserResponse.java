package com.validic;

public class UserResponse {
	private int id;
	private String _id;
	private String uid;
	private String access_token;
	/*private String profile;*/
	private String organizationId;
	private String organizationAccessToken;
	private ValidicUserProfile profile=null;
	
	
	//for clinakos purpose
	private int patientId;//For saving patient Id wrt clinakos
	private int providerId;//For saving providerId wrt clinakos
	
	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * @return the access_token
	 */
	public String getAccess_token() {
		return access_token;
	}
	/**
	 * @param access_token the access_token to set
	 */
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
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
	 * @return the profile
	 */
	public ValidicUserProfile getProfile() {
		if(profile==null){
			profile=new ValidicUserProfile();
		}
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ValidicUserProfile profile) {
		this.profile = profile;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	
	
	
	

}
