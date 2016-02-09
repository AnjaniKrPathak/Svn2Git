package com.clinakos.data.model.patient;

import java.util.Date;





public class PatientDiagnosesDetails {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int patientId;
	private String icdId; // Changed Double to String By Anjani
	private String codeDescription;
	private String code;
	private Date date;
	private String icdCodeType;
	private UserLoginDetail userLoginDetail;
	private int doctorId;
	private String docFirstName;
	private String docLastName;
	
	private String docmiddleName;
	
	private boolean statusUI;
	//by vinod
	private boolean checkstatusDatabase;
	private String ExternalDoctorUpin;
	
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
	
	
	public String getCodeDescription() {
		return codeDescription;
	}
	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}
	/*public double getIcdId() {
		return icdId;
	}
	public void setIcdId(double icdId) {
		this.icdId = icdId;
	}*/
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	/**
	 * @return the icdCodeType
	 */
	public String getIcdCodeType() {
		return icdCodeType;
	}
	/**
	 * @param icdCodeType the icdCodeType to set
	 */
	public void setIcdCodeType(String icdCodeType) {
		this.icdCodeType = icdCodeType;
	}
	/**
	 * @return the userLoginDetail
	 */
	public UserLoginDetail getUserLoginDetail() {
		if(userLoginDetail==null){
			userLoginDetail=new UserLoginDetail();
		}
			
		return userLoginDetail;
	}
	/**
	 * @param userLoginDetail the userLoginDetail to set
	 */
	public void setUserLoginDetail(UserLoginDetail userLoginDetail) {
		this.userLoginDetail = userLoginDetail;
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
	/**
	 * @return the docFirstName
	 */
	public String getDocFirstName() {
		return docFirstName;
	}
	/**
	 * @param docFirstName the docFirstName to set
	 */
	public void setDocFirstName(String docFirstName) {
		this.docFirstName = docFirstName;
	}
	/**
	 * @return the docLastName
	 */
	public String getDocLastName() {
		return docLastName;
	}
	/**
	 * @param docLastName the docLastName to set
	 */
	public void setDocLastName(String docLastName) {
		this.docLastName = docLastName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/*@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		PatientDiagnosesDetails pDiagnosesDetails=(PatientDiagnosesDetails) obj;
		if(icdId==pDiagnosesDetails.getIcdId()){
			return true;
		}else{
			return false;
		}
		//return Double.compare(icdId, pDiagnosesDetails.getIcdId())== 0;
	}*/
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/*@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		double hash = 7;
		hash = 31 * hash + Integer.parseInt(icdId);
		return (int) hash;
	}*/
	/**
	 * @return the docmiddleName
	 */
	public String getDocmiddleName() {
		return docmiddleName;
	}
	/**
	 * @param docmiddleName the docmiddleName to set
	 */
	public void setDocmiddleName(String docmiddleName) {
		this.docmiddleName = docmiddleName;
	}
	/**
	 * @return the icdId
	 */
	public String getIcdId() {
		return icdId;
	}
	/**
	 * @param icdId the icdId to set
	 */
	public void setIcdId(String icdId) {
		this.icdId = icdId;
	}
	public boolean isStatusUI() {
		return statusUI;
	}
	public void setStatusUI(boolean statusUI) {
		this.statusUI = statusUI;
	}
	public boolean isCheckstatusDatabase() {
		return checkstatusDatabase;
	}
	public void setCheckstatusDatabase(boolean checkstatusDatabase) {
		this.checkstatusDatabase = checkstatusDatabase;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((icdId == null) ? 0 : icdId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientDiagnosesDetails other = (PatientDiagnosesDetails) obj;
		if (icdId == null) {
			if (other.icdId != null)
				return false;
		} else if (!icdId.equals(other.icdId))
			return false;
		return true;
	}
	public String getExternalDoctorUpin() {
		return ExternalDoctorUpin;
	}
	public void setExternalDoctorUpin(String externalDoctorUpin) {
		ExternalDoctorUpin = externalDoctorUpin;
	}
	
	
	
	
	
}
