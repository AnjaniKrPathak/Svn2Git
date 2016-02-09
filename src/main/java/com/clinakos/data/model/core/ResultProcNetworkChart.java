/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author SAURABH
 *
 */
public class ResultProcNetworkChart {

	private Integer id;
	private Integer userId;
	private Integer doctorPrescriptions;
	private Integer sharedDoctorId;
	private String doctorFirstName;
	private String doctorLastName;
	private Integer doctorId;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDoctorPrescriptions() {
		return doctorPrescriptions;
	}
	public void setDoctorPrescriptions(Integer doctorPrescriptions) {
		this.doctorPrescriptions = doctorPrescriptions;
	}
	public Integer getSharedDoctorId() {
		return sharedDoctorId;
	}
	public void setSharedDoctorId(Integer sharedDoctorId) {
		this.sharedDoctorId = sharedDoctorId;
	}
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	public String getDoctorLastName() {
		return doctorLastName;
	}
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
}
