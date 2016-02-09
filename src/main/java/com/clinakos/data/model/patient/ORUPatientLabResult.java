package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.mapping.Array;

import com.clinakos.data.model.core.EmdeonLabResult;

public class ORUPatientLabResult {
	
	private int id;
	private String comment;
	private String patientId;
	
	
	private List<ProcedureType>  orderObservationList; 
	private List<EmdeonLabResult> emdeonLabResultDetailList;
	 
	
	private String patFirstName;
	private String patMidName;
	private String patLastName;
	private String hl7Message;
	private String requisitionNo;
	private String accessionNo;
	private Date collectionDateNTime;
	private Date reportDateNTime;
	private Date requestReportDate;
	private String htmlMessage;


	
	
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
	
	
	
	/**
	 * @return the patFirstName
	 */
	public String getPatFirstName() {
		return patFirstName;
	}
	/**
	 * @param patFirstName the patFirstName to set
	 */
	public void setPatFirstName(String patFirstName) {
		this.patFirstName = patFirstName;
	}
	/**
	 * @return the patMidName
	 */
	public String getPatMidName() {
		return patMidName;
	}
	/**
	 * @param patMidName the patMidName to set
	 */
	public void setPatMidName(String patMidName) {
		this.patMidName = patMidName;
	}
	/**
	 * @return the patLastName
	 */
	public String getPatLastName() {
		return patLastName;
	}
	/**
	 * @param patLastName the patLastName to set
	 */
	public void setPatLastName(String patLastName) {
		this.patLastName = patLastName;
	}
	/**
	 * @return the orderObservationList
	 */
	public List<ProcedureType> getOrderObservationList() {
		if(orderObservationList==null){
			orderObservationList=new ArrayList<ProcedureType>();
		}
		return orderObservationList;
	}
	/**
	 * @param orderObservationList the orderObservationList to set
	 */
	public void setOrderObservationList(List<ProcedureType> orderObservationList) {
		this.orderObservationList = orderObservationList;
	}
	/**
	 * @return the EmdeonLabResultDetailList
	 */
	public List<EmdeonLabResult> getEmdeonLabResultDetailList() {
		if(emdeonLabResultDetailList==null){
			emdeonLabResultDetailList=new ArrayList<EmdeonLabResult>();
		}
		return emdeonLabResultDetailList;
	}
	/**
	 * @param EmdeonLabResultDetailList the EmdeonLabResultDetailList to set
	 */
	public void setEmdeonLabResultDetailList(
			List<EmdeonLabResult> emdeonLabResultDetailList) {
		this.emdeonLabResultDetailList = emdeonLabResultDetailList;
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
	 * @return the requisitionNo
	 */
	public String getRequisitionNo() {
		return requisitionNo;
	}
	/**
	 * @param requisitionNo the requisitionNo to set
	 */
	public void setRequisitionNo(String requisitionNo) {
		this.requisitionNo = requisitionNo;
	}
	/**
	 * @return the accessionNo
	 */
	public String getAccessionNo() {
		return accessionNo;
	}
	/**
	 * @param accessionNo the accessionNo to set
	 */
	public void setAccessionNo(String accessionNo) {
		this.accessionNo = accessionNo;
	}
	
	/**
	 * @return the requestReportDate
	 */
	public Date getRequestReportDate() {
		return requestReportDate;
	}
	/**
	 * @param requestReportDate the requestReportDate to set
	 */
	public void setRequestReportDate(Date requestReportDate) {
		this.requestReportDate = requestReportDate;
	}
	/**
	 * @return the collectionDateNTime
	 */
	public Date getCollectionDateNTime() {
		return collectionDateNTime;
	}
	/**
	 * @param collectionDateNTime the collectionDateNTime to set
	 */
	public void setCollectionDateNTime(Date collectionDateNTime) {
		this.collectionDateNTime = collectionDateNTime;
	}
	/**
	 * @return the reportDateNTime
	 */
	public Date getReportDateNTime() {
		return reportDateNTime;
	}
	/**
	 * @param reportDateNTime the reportDateNTime to set
	 */
	public void setReportDateNTime(Date reportDateNTime) {
		this.reportDateNTime = reportDateNTime;
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
	
	

}
