/**
 * 
 */
package com.clinakos.data.model.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author li0008
 *
 */
public class ProcedureResultHistory implements Serializable{

	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -3306120304736575767L;
		private int id;
		private Double result;
		private String comments;
		private String resultStatus;
		
//--------------------data member added after removing procedure_order
		private int providerId;
		private int patientId;
		private Date dateResult;
		private Date dateOrdered;
		private String laboratoryName;
		private ProcedureType procedureType;
		private UserLoginDetail userLoginDetail;
		private String labName;
//----------------- setting value for lab datatable color code
		private int colorCodeIndexForLabHistory;
		

//---------data member for display values
		private String doctorName;
		private String labUnit;
		private String labRange;
		private int labTypeId;
//----------------------setting value for lab datatable color code
		private int colorCodeIndexForCurrentLabResult;
		
		// Set the value of Patient Lab Observation from Web Services HL7 message 
		
		private List<ProcedureType> observationLabList;
		
		private String refrenceRange;
		
		private int doctorId;
		
		
		

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
		 * @return the result
		 */
		public Double getResult() {
			return result;
		}

		/**
		 * @param result the result to set
		 */
		public void setResult(Double result) {
			this.result = result;
		}

		/**
		 * @return the comments
		 */
		public String getComments() {
			return comments;
		}

		/**
		 * @param comments the comments to set
		 */
		public void setComments(String comments) {
			this.comments = comments;
		}

		/**
		 * @return the resultStatus
		 */
		public String getResultStatus() {
			return resultStatus;
		}

		/**
		 * @param resultStatus the resultStatus to set
		 */
		public void setResultStatus(String resultStatus) {
			this.resultStatus = resultStatus;
		}

		/**
		 * @return the dateResult
		 */
		public Date getDateResult() {
			return dateResult;
		}

		/**
		 * @param dateResult the dateResult to set
		 */
		public void setDateResult(Date dateResult) {
			this.dateResult = dateResult;
		}

		/**
		 * @return the providerId
		 */
		public int getProviderId() {
			return providerId;
		}

		/**
		 * @param providerId the providerId to set
		 */
		public void setProviderId(int providerId) {
			this.providerId = providerId;
		}

		/**
		 * @return the dateOrdered
		 */
		public Date getDateOrdered() {
			return dateOrdered;
		}

		/**
		 * @param dateOrdered the dateOrdered to set
		 */
		public void setDateOrdered(Date dateOrdered) {
			this.dateOrdered = dateOrdered;
		}

		/**
		 * @return the laboratoryName
		 */
		public String getLaboratoryName() {
			return laboratoryName;
		}

		/**
		 * @param laboratoryName the laboratoryName to set
		 */
		public void setLaboratoryName(String laboratoryName) {
			this.laboratoryName = laboratoryName;
		}

		/**
		 * @return the colorCodeIndexForLabHistory
		 */
		public int getColorCodeIndexForLabHistory() {
			return colorCodeIndexForLabHistory;
		}

		/**
		 * @param colorCodeIndexForLabHistory the colorCodeIndexForLabHistory to set
		 */
		public void setColorCodeIndexForLabHistory(int colorCodeIndexForLabHistory) {
			this.colorCodeIndexForLabHistory = colorCodeIndexForLabHistory;
		}

		/**
		 * @return the userLoginDetail
		 */
		public UserLoginDetail getUserLoginDetail() {
			return userLoginDetail;
		}

		/**
		 * @param userLoginDetail the userLoginDetail to set
		 */
		public void setUserLoginDetail(UserLoginDetail userLoginDetail) {
			this.userLoginDetail = userLoginDetail;
		}

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
		 * @return the procedureType
		 */
		public ProcedureType getProcedureType() {
			if(procedureType==null){
				procedureType=new ProcedureType();
			}
			return procedureType;
		}

		/**
		 * @param procedureType the procedureType to set
		 */
		public void setProcedureType(ProcedureType procedureType) {
			this.procedureType = procedureType;
		}

		public String getLabName() {
			return labName;
		}

		public void setLabName(String labName) {
			this.labName = labName;
		}

		/**
		 * @return the doctorName
		 */
		public String getDoctorName() {
			return doctorName;
		}

		/**
		 * @param doctorName the doctorName to set
		 */
		public void setDoctorName(String doctorName) {
			this.doctorName = doctorName;
		}

		/**
		 * @return the labUnit
		 */
		public String getLabUnit() {
			return labUnit;
		}

		/**
		 * @param labUnit the labUnit to set
		 */
		public void setLabUnit(String labUnit) {
			this.labUnit = labUnit;
		}

		/**
		 * @return the labRange
		 */
		public String getLabRange() {
			return labRange;
		}

		/**
		 * @param labRange the labRange to set
		 */
		public void setLabRange(String labRange) {
			this.labRange = labRange;
		}

		/**
		 * @return the labTypeId
		 */
		public int getLabTypeId() {
			return labTypeId;
		}

		/**
		 * @param labTypeId the labTypeId to set
		 */
		public void setLabTypeId(int labTypeId) {
			this.labTypeId = labTypeId;
		}

		/**
		 * @return the colorCodeIndexForCurrentLabResult
		 */
		public int getColorCodeIndexForCurrentLabResult() {
			return colorCodeIndexForCurrentLabResult;
		}

		/**
		 * @param colorCodeIndexForCurrentLabResult the colorCodeIndexForCurrentLabResult to set
		 */
		public void setColorCodeIndexForCurrentLabResult(
				int colorCodeIndexForCurrentLabResult) {
			this.colorCodeIndexForCurrentLabResult = colorCodeIndexForCurrentLabResult;
		}

		/**
		 * @return the observationLabList
		 */
		public List<ProcedureType> getObservationLabList() {
			if(observationLabList==null){
				observationLabList=new ArrayList<ProcedureType>();
			}
			return observationLabList;
		}

		/**
		 * @param observationLabList the observationLabList to set
		 */
		public void setObservationLabList(List<ProcedureType> observationLabList) {
			this.observationLabList = observationLabList;
		}

		/**
		 * @return the refrenceRange
		 */
		public String getRefrenceRange() {
			return refrenceRange;
		}

		/**
		 * @param refrenceRange the refrenceRange to set
		 */
		public void setRefrenceRange(String refrenceRange) {
			this.refrenceRange = refrenceRange;
		}

		/**
		 * @return the doctorId
		 */
		public int getDoctorId() {
			return doctorId;
		}

		/**
		 * @param doctorId the doctorId to set
		 */
		public void setDoctorId(int doctorId) {
			this.doctorId = doctorId;
		}

	
}
