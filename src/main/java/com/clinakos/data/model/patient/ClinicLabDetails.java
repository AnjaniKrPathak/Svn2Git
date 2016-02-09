
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with ClinicLabDetails 
 */

package com.clinakos.data.model.patient;

public class ClinicLabDetails {

	private int id;
	private int clinicDiagnosisLabId;
	private String labRange;
	private String labNotes;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicDiagnosisLabId() {
		return clinicDiagnosisLabId;
	}
	public void setClinicDiagnosisLabId(int clinicDiagnosisLabId) {
		this.clinicDiagnosisLabId = clinicDiagnosisLabId;
	}
	public String getLabRange() {
		return labRange;
	}
	public void setLabRange(String labRange) {
		this.labRange = labRange;
	}
	public String getLabNotes() {
		return labNotes;
	}
	public void setLabNotes(String labNotes) {
		this.labNotes = labNotes;
	}
	
	
}
