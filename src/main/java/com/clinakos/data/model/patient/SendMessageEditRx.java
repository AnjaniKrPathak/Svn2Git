
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with Doctor's Personal Details
 */

package com.clinakos.data.model.patient;

import java.util.Date;

public class SendMessageEditRx {

	private int id;
	private int patientId;
	private int loginDoctorId;
	private double medId;
	private String sendMsgToDocName;
	private String sendMsgFromDocname;
	
	private String drugsNotes;
	private Date messageSentDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getLoginDoctorId() {
		return loginDoctorId;
	}
	public void setLoginDoctorId(int loginDoctorId) {
		this.loginDoctorId = loginDoctorId;
	}
	
	public String getSendMsgToDocName() {
		return sendMsgToDocName;
	}
	public void setSendMsgToDocName(String sendMsgToDocName) {
		this.sendMsgToDocName = sendMsgToDocName;
	}
	public double getMedId() {
		return medId;
	}
	public void setMedId(double medId) {
		this.medId = medId;
	}
	public Date getMessageSentDate() {
		return messageSentDate;
	}
	public void setMessageSentDate(Date messageSentDate) {
		this.messageSentDate = messageSentDate;
	}
	public String getSendMsgFromDocname() {
		return sendMsgFromDocname;
	}
	public void setSendMsgFromDocname(String sendMsgFromDocname) {
		this.sendMsgFromDocname = sendMsgFromDocname;
	}
	public String getDrugsNotes() {
		return drugsNotes;
	}
	public void setDrugsNotes(String string) {
		this.drugsNotes = string;
	}
	
	
	

}
