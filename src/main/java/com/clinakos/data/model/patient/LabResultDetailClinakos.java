package com.clinakos.data.model.patient;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.joda.time.DateTime;



public class LabResultDetailClinakos {
	private int id;
	private String resultGuid;
	private String hl7Message;
	private String htmlMessage;
	private String doctorLastName;
	private String doctorMidName;
	private String doctorFirstName;
	private Date resultDateTime;
	private String patientLastName;
	private String patientFirstName;
	private String patientMiddleName;
	private String patientDOB;
	
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
	 * @return the resultGuid
	 */
	public String getResultGuid() {
		return resultGuid;
	}
	/**
	 * @param resultGuid the resultGuid to set
	 */
	public void setResultGuid(String resultGuid) {
		this.resultGuid = resultGuid;
	}
	/**
	 * @return the hl7Message
	 */
	public String getHl7Message() {
		return hl7Message;
	}
	/**
	 * @param hl7Message the hl7Message to set
	 */
	public void setHl7Message(String hl7Message) {
		this.hl7Message = hl7Message;
	}
	/**
	 * @return the htmlMessage
	 */
	public String getHtmlMessage() {
		return htmlMessage;
	}
	/**
	 * @param htmlMessage the htmlMessage to set
	 */
	public void setHtmlMessage(String htmlMessage) {
		this.htmlMessage = htmlMessage;
	}
	/**
	 * @return the doctorLastName
	 */
	public String getDoctorLastName() {
		return doctorLastName;
	}
	/**
	 * @param doctorLastName the doctorLastName to set
	 */
	public void setDoctorLastName(String doctorLastName) {
		this.doctorLastName = doctorLastName;
	}
	/**
	 * @return the doctorMidName
	 */
	public String getDoctorMidName() {
		return doctorMidName;
	}
	/**
	 * @param doctorMidName the doctorMidName to set
	 */
	public void setDoctorMidName(String doctorMidName) {
		this.doctorMidName = doctorMidName;
	}
	/**
	 * @return the doctorFirstName
	 */
	public String getDoctorFirstName() {
		return doctorFirstName;
	}
	/**
	 * @param doctorFirstName the doctorFirstName to set
	 */
	public void setDoctorFirstName(String doctorFirstName) {
		this.doctorFirstName = doctorFirstName;
	}
	
	/**
	 * @return the patientLastName
	 */
	public String getPatientLastName() {
		return patientLastName;
	}
	/**
	 * @param patientLastName the patientLastName to set
	 */
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	/**
	 * @return the patientFirstName
	 */
	public String getPatientFirstName() {
		return patientFirstName;
	}
	/**
	 * @param patientFirstName the patientFirstName to set
	 */
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	/**
	 * @return the patientMiddleName
	 */
	public String getPatientMiddleName() {
		return patientMiddleName;
	}
	/**
	 * @param patientMiddleName the patientMiddleName to set
	 */
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}
	/**
	 * @return the patientDOB
	 */
	public String getPatientDOB() {
		return patientDOB;
	}
	/**
	 * @param patientDOB the patientDOB to set
	 */
	public void setPatientDOB(String patientDOB) {
		this.patientDOB = patientDOB;
	}
	/**
	 * @return the resultDateTime
	 */
	public Date getResultDateTime() {
		return resultDateTime;
	}
	/**
	 * @param resultDateTime the resultDateTime to set
	 */
	public void setResultDateTime(Date resultDateTime) {
		this.resultDateTime = resultDateTime;
	}
	
}
