/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author IDC-
 *
 */
public class ContraindicatedMedItems {
	
	private int id;
	private ContraindicatedMeds parentMedObj;
	private String childMedName;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ContraindicatedMeds getParentMedObj() {
		return parentMedObj;
	}
	public void setParentMedObj(ContraindicatedMeds parentMedObj) {
		this.parentMedObj = parentMedObj;
	}
	public String getChildMedName() {
		return childMedName;
	}
	public void setChildMedName(String childMedName) {
		this.childMedName = childMedName;
	}
	
}
