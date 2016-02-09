/**
 * 
 */
package com.clinakos.data.model.core;

/**
 * @author IDC-0022
 *
 */
public class DiagnosisUploadData {

	private int id;
	private String externalId;
	private String clinakosId;
	private String icdCode;
	private String icdCodeType;
	private String dateOfDiagnosis;
	private String externalIdAvailable;
	private int adminId;
	private String doctorNPI;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getClinakosId() {
		return clinakosId;
	}
	public void setClinakosId(String clinakosId) {
		this.clinakosId = clinakosId;
	}
	public String getIcdCode() {
		return icdCode;
	}
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}
	public String getIcdCodeType() {
		return icdCodeType;
	}
	public void setIcdCodeType(String icdCodeType) {
		this.icdCodeType = icdCodeType;
	}
	public String getDateOfDiagnosis() {
		return dateOfDiagnosis;
	}
	public void setDateOfDiagnosis(String dateOfDiagnosis) {
		this.dateOfDiagnosis = dateOfDiagnosis;
	}
	public String getExternalIdAvailable() {
		return externalIdAvailable;
	}
	public void setExternalIdAvailable(String externalIdAvailable) {
		this.externalIdAvailable = externalIdAvailable;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getDoctorNPI() {
		return doctorNPI;
	}
	public void setDoctorNPI(String doctorNPI) {
		this.doctorNPI = doctorNPI;
	}

}
