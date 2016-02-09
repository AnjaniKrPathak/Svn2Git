package com.clinakos.data.model.patient;

import java.util.ArrayList;

import com.clinakos.data.model.core.InsuranceCompanies;

public class EmdeonLabOrders {
	
	private int patientId;
	private ArrayList<LabOrderCommonSegment> labOrderCommonSegmentList;
	private ArrayList<LabOrderObservationDetail> labOrderObservationDetailList;
	private ArrayList<LabOrderObservationRequestDetail> labOrderObservationRequestDetailList;
	private String labOrderHL7Message;
	private String labOrderHtmlMessage;
	private ArrayList<PatientDiagnosesDetails> diagnosesDetailList;
	private ArrayList<InsuranceCompanies> patientInsuranceDetailList;
	private ArrayList<PatientGuarantor> patientGurantorList;

	
	
	
	
	
	
	/**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}
	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	/**
	 * @return the labOrderCommonSegmentList
	 */
	public ArrayList<LabOrderCommonSegment> getLabOrderCommonSegmentList() {
		return labOrderCommonSegmentList;
	}
	/**
	 * @param labOrderCommonSegmentList the labOrderCommonSegmentList to set
	 */
	public void setLabOrderCommonSegmentList(
			ArrayList<LabOrderCommonSegment> labOrderCommonSegmentList) {
		this.labOrderCommonSegmentList = labOrderCommonSegmentList;
	}
	/**
	 * @return the labOrderObservationDetailList
	 */
	public ArrayList<LabOrderObservationDetail> getLabOrderObservationDetailList() {
		return labOrderObservationDetailList;
	}
	/**
	 * @param labOrderObservationDetailList the labOrderObservationDetailList to set
	 */
	public void setLabOrderObservationDetailList(
			ArrayList<LabOrderObservationDetail> labOrderObservationDetailList) {
		this.labOrderObservationDetailList = labOrderObservationDetailList;
	}
	/**
	 * @return the labOrderObservationRequestDetailList
	 */
	public ArrayList<LabOrderObservationRequestDetail> getLabOrderObservationRequestDetailList() {
		return labOrderObservationRequestDetailList;
	}
	/**
	 * @param labOrderObservationRequestDetailList the labOrderObservationRequestDetailList to set
	 */
	public void setLabOrderObservationRequestDetailList(
			ArrayList<LabOrderObservationRequestDetail> labOrderObservationRequestDetailList) {
		this.labOrderObservationRequestDetailList = labOrderObservationRequestDetailList;
	}
	/**
	 * @return the labOrderHL7Message
	 */
	public String getLabOrderHL7Message() {
		return labOrderHL7Message;
	}
	/**
	 * @param labOrderHL7Message the labOrderHL7Message to set
	 */
	public void setLabOrderHL7Message(String labOrderHL7Message) {
		this.labOrderHL7Message = labOrderHL7Message;
	}
	/**
	 * @return the labOrderHtmlMessage
	 */
	public String getLabOrderHtmlMessage() {
		return labOrderHtmlMessage;
	}
	/**
	 * @param labOrderHtmlMessage the labOrderHtmlMessage to set
	 */
	public void setLabOrderHtmlMessage(String labOrderHtmlMessage) {
		this.labOrderHtmlMessage = labOrderHtmlMessage;
	}
	/**
	 * @return the diagnosesDetailList
	 */
	public ArrayList<PatientDiagnosesDetails> getDiagnosesDetailList() {
		return diagnosesDetailList;
	}
	/**
	 * @param diagnosesDetailList the diagnosesDetailList to set
	 */
	public void setDiagnosesDetailList(
			ArrayList<PatientDiagnosesDetails> diagnosesDetailList) {
		this.diagnosesDetailList = diagnosesDetailList;
	}
	/**
	 * @return the patientInsuranceDetailList
	 */
	public ArrayList<InsuranceCompanies> getPatientInsuranceDetailList() {
		return patientInsuranceDetailList;
	}
	/**
	 * @param patientInsuranceDetailList the patientInsuranceDetailList to set
	 */
	public void setPatientInsuranceDetailList(
			ArrayList<InsuranceCompanies> patientInsuranceDetailList) {
		this.patientInsuranceDetailList = patientInsuranceDetailList;
	}
	/**
	 * @return the patientGurantorList
	 */
	public ArrayList<PatientGuarantor> getPatientGurantorList() {
		return patientGurantorList;
	}
	/**
	 * @param patientGurantorList the patientGurantorList to set
	 */
	public void setPatientGurantorList(
			ArrayList<PatientGuarantor> patientGurantorList) {
		this.patientGurantorList = patientGurantorList;
	}
	
	

}
