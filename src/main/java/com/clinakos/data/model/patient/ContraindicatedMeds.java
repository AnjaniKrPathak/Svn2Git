/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.List;
import java.util.Set;

/**
 * @author IDC-
 *
 */
public class ContraindicatedMeds {
	
	private int id;
	private String parentMedName;
	private String clinicName;
	private boolean status;
	
	private List<ContraindicatedMedItems>childMeds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getParentMedName() {
		return parentMedName;
	}
	public void setParentMedName(String parentMedName) {
		this.parentMedName = parentMedName;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public List<ContraindicatedMedItems> getChildMeds() {
		return childMeds;
	}
	public void setChildMeds(List<ContraindicatedMedItems> childMeds) {
		this.childMeds = childMeds;
	}
	
	

}
