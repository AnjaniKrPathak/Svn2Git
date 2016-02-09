/**
 * 
 */
package com.clinakos.data.model.patient;

import java.util.Comparator;
import java.util.Date;

/**
 * @author LI0002
 *
 */
public class LabLineBarChart  implements Comparable<LabLineBarChart>{
	//private Long dateResult;
	private String result;
	private Date startDate;
	private String strength;
	private Long longStartDate;
	private String direction;
	private double directionvalue;
	private double dose;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	/**
	 * @return the strength
	 */
	public String getStrength() {
		return strength;
	}
	/**
	 * @param strength the strength to set
	 */
	public void setStrength(String strength) {
		this.strength = strength;
	}
	/**
	 * @return the dateResult
	 */
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	

	  public int compareTo(LabLineBarChart o) {
	    return getStartDate().compareTo(o.getStartDate());
	  }
	/**
	 * @return the longStartDate
	 */
	public Long getLongStartDate() {
		return longStartDate;
	}
	/**
	 * @param longStartDate the longStartDate to set
	 */
	public void setLongStartDate(Long longStartDate) {
		this.longStartDate = longStartDate;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the directionvalue
	 */
	public double getDirectionvalue() {
		return directionvalue;
	}
	/**
	 * @param directionvalue the directionvalue to set
	 */
	public void setDirectionvalue(double directionvalue) {
		this.directionvalue = directionvalue;
	}
	/**
	 * @return the dose
	 */
	public double getDose() {
		return dose;
	}
	/**
	 * @param dose the dose to set
	 */
	public void setDose(double dose) {
		this.dose = dose;
	}

	
}
	
	
