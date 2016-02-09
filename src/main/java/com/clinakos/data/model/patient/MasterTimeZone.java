package com.clinakos.data.model.patient;

import java.io.Serializable;

public class MasterTimeZone implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7189974195835182583L;
	
 private int id;
 private String timeZone;
 private String description;
 private String relativeToGMT;
 
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTimeZone() {
	return timeZone;
}
public void setTimeZone(String timeZone) {
	this.timeZone = timeZone;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getRelativeToGMT() {
	return relativeToGMT;
}
public void setRelativeToGMT(String relativeToGMT) {
	this.relativeToGMT = relativeToGMT;
}

 
}
