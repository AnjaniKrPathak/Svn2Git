/**
 * 
 */
package com.validic;

/**
 * @author IDC-008
 *
 */
public class Organization {
	private String users;

    private String _id;

    private String[] organizations;

    private String name;

    private String users_provisioned;

    private String connections;

    private String activities;
    
    

	/**
	 * @return the users
	 */
	public String getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(String users) {
		this.users = users;
	}

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
	 * @return the organizations
	 */
	public String[] getOrganizations() {
		return organizations;
	}

	/**
	 * @param organizations the organizations to set
	 */
	public void setOrganizations(String[] organizations) {
		this.organizations = organizations;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the users_provisioned
	 */
	public String getUsers_provisioned() {
		return users_provisioned;
	}

	/**
	 * @param users_provisioned the users_provisioned to set
	 */
	public void setUsers_provisioned(String users_provisioned) {
		this.users_provisioned = users_provisioned;
	}

	/**
	 * @return the connections
	 */
	public String getConnections() {
		return connections;
	}

	/**
	 * @param connections the connections to set
	 */
	public void setConnections(String connections) {
		this.connections = connections;
	}

	/**
	 * @return the activities
	 */
	public String getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(String activities) {
		this.activities = activities;
	}

}
