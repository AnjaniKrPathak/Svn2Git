/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class LoincKeyMaster {

	private int procedureTypeId;
	private int patientId;
	private String name;
	
	public int getProcedureTypeId() {
		return procedureTypeId;
	}
	public void setProcedureTypeId(int procedureTypeId) {
		this.procedureTypeId = procedureTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}
