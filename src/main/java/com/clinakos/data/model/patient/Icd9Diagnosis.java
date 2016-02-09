/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class Icd9Diagnosis {
	
	private int id;
	private String dxCode;
	private String formatedCode;
	private String shortDescription;
	private String longDiscription;
	private int active;
	private int revision;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDxCode() {
		return dxCode;
	}
	public void setDxCode(String dxCode) {
		this.dxCode = dxCode;
	}
	public String getFormatedCode() {
		return formatedCode;
	}
	public void setFormatedCode(String formatedCode) {
		this.formatedCode = formatedCode;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDiscription() {
		return longDiscription;
	}
	public void setLongDiscription(String longDiscription) {
		this.longDiscription = longDiscription;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getRevision() {
		return revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	
	

}
