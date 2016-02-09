package com.clinakos.data.model.patient;

public class LOVCode {
	private int lOVCodeId;
	//private int lovTypeId;
	private String label;
	private String description;
	private LOVType lOVType;
	
/*	public int getLovTypeId() {
		return lovTypeId;
	}
	public void setLovTypeId(int lovTypeId) {
		this.lovTypeId = lovTypeId;
	}*/
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getlOVCodeId() {
		return lOVCodeId;
	}
	public void setlOVCodeId(int lOVCodeId) {
		this.lOVCodeId = lOVCodeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LOVType getlOVType() {
		return lOVType;
	}
	public void setlOVType(LOVType lOVType) {
		this.lOVType = lOVType;
	}
	

}
