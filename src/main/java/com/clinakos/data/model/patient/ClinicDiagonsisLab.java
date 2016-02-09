
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with ClinicDiagonsisLab records
 */

package com.clinakos.data.model.patient;

public class ClinicDiagonsisLab {

	private int id;
	private int clinicDiagnosisId;
	private String labName;
	private ClinicDiagonsis clinicDiagonsis;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicDiagnosisId() {
		return clinicDiagnosisId;
	}
	public void setClinicDiagnosisId(int clinicDiagnosisId) {
		this.clinicDiagnosisId = clinicDiagnosisId;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public ClinicDiagonsis getClinicDiagonsis() {
		return clinicDiagonsis;
	}
	public void setClinicDiagonsis(ClinicDiagonsis clinicDiagonsis) {
		this.clinicDiagonsis = clinicDiagonsis;
	}
	
	


}
