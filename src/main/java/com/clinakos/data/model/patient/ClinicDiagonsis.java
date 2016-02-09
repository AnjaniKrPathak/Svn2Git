
/**
 * @author Lumbini Innovations Pvt Ltd
 *Pojo for Interacting with DiagonsisClinic Name
 */

package com.clinakos.data.model.patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClinicDiagonsis {

	private int id;
	private int clinicId;
	private String diagnosisName;
	private int icdId;
	private String medicineName;
	private List<ClinicDiagonsisLab>clinicDiagonsisLabList=new ArrayList<ClinicDiagonsisLab>();
	//private Set<LOVCode> clinicDiagonsisLabList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClinicId() {
		return clinicId;
	}
	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}
	
	public int getIcdId() {
		return icdId;
	}
	public void setIcdId(int icdId) {
		this.icdId = icdId;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public List<ClinicDiagonsisLab> getClinicDiagonsisLabList() {
		return clinicDiagonsisLabList;
	}
	public void setClinicDiagonsisLabList(List<ClinicDiagonsisLab> clinicDiagonsisLabList) {
		this.clinicDiagonsisLabList = clinicDiagonsisLabList;
	}
	/*public Set<LOVCode> getClinicDiagonsisLabList() {
		return clinicDiagonsisLabList;
	}
	public void setClinicDiagonsisLabList(Set<LOVCode> clinicDiagonsisLabList) {
		this.clinicDiagonsisLabList = clinicDiagonsisLabList;
	}*/
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	
	

}
