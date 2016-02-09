package com.clinakos.data.model.patient;
import java.util.Set;

public class LOVType {
	private int id;
	private String text;
	private String textDescription;
	private Set<LOVCode> lovCodeDetail;
	//private List<LOVCode> lovCodeDetail = new ArrayList<LOVCode>();  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTextDescription() {
		return textDescription;
	}
	public void setTextDescription(String textDescription) {
		this.textDescription = textDescription;
	}
	public Set<LOVCode> getLovCodeDetail() {
		return lovCodeDetail;
	}
	public void setLovCodeDetail(Set<LOVCode> lovCodeDetail) {
		this.lovCodeDetail = lovCodeDetail;
	}
	
	/*public List<LOVCode> getLovCodeDetail() {
		return lovCodeDetail;
	}
	public void setLovCodeDetail(List<LOVCode> lovCodeDetail) {
		this.lovCodeDetail = lovCodeDetail;
	}*/
	
	
	

}
