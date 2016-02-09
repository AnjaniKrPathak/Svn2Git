/**
 * 
 */
package com.clinakos.data.model.patient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author IDC-0004
 *
 */
@Entity
@Table(name="child_monitoring_parameters")
public class ChildMonitoringParameters {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="childParameterName")
	private String childParameterName;
	
	@ManyToOne
	@JoinColumn(name="procedure_type_id")
	private ProcedureType loincObj;
	
	
	@Column(name="goalLowRangeSymbol")
	private String goalLowRangeSymbol;
	
	@Column(name="goalLowRangeValue")
	private double goalLowRangeValue;
	
	@Column(name="goalHighRangeSymbol")
	private String goalHighRangeSymbol;
	
	@Column(name="goalHighRangeValue")
	private double goalHighRangeValue;
	
	@Column(name="alertSevereLowRangeSymbol")
	private String alertSevereLowRangeSymbol;
	
	@Column(name="alertSevereLowRangeValue")
	private double alertSevereLowRangeValue;
	
	@Column(name="alertSevereHighRangeSymbol")
	private String alertSevereHighRangeSymbol;
	
	@Column(name="alertSevereHighRangeValue")
	private double alertSevereHighRangeValue;
	
	@Column(name="alertMediumLowRangeSymbol")
	private String alertMediumLowRangeSymbol;
	
	@Column(name="alertMediumLowRangeValue")
	private double alertMediumLowRangeValue;
	
	@Column(name="alertMediumHighRangeSymbol")
	private String alertMediumHighRangeSymbol;
	
	@Column(name="alertMediumHighRangeValue")
	private double alertMediumHighRangeValue;
	
	public String getChildParameterName() {
		return childParameterName;
	}
	public void setChildParameterName(String childParameterName) {
		this.childParameterName = childParameterName;
	}
	public ProcedureType getLoincObj() {
		return loincObj;
	}
	public void setLoincObj(ProcedureType loincObj) {
		this.loincObj = loincObj;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGoalLowRangeSymbol() {
		return goalLowRangeSymbol;
	}
	public void setGoalLowRangeSymbol(String goalLowRangeSymbol) {
		this.goalLowRangeSymbol = goalLowRangeSymbol;
	}
	public double getGoalLowRangeValue() {
		return goalLowRangeValue;
	}
	public void setGoalLowRangeValue(double goalLowRangeValue) {
		this.goalLowRangeValue = goalLowRangeValue;
	}
	public String getGoalHighRangeSymbol() {
		return goalHighRangeSymbol;
	}
	public void setGoalHighRangeSymbol(String goalHighRangeSymbol) {
		this.goalHighRangeSymbol = goalHighRangeSymbol;
	}
	public double getGoalHighRangeValue() {
		return goalHighRangeValue;
	}
	public void setGoalHighRangeValue(double goalHighRangeValue) {
		this.goalHighRangeValue = goalHighRangeValue;
	}
	public String getAlertSevereLowRangeSymbol() {
		return alertSevereLowRangeSymbol;
	}
	public void setAlertSevereLowRangeSymbol(String alertSevereLowRangeSymbol) {
		this.alertSevereLowRangeSymbol = alertSevereLowRangeSymbol;
	}
	public double getAlertSevereLowRangeValue() {
		return alertSevereLowRangeValue;
	}
	public void setAlertSevereLowRangeValue(double alertSevereLowRangeValue) {
		this.alertSevereLowRangeValue = alertSevereLowRangeValue;
	}
	public String getAlertSevereHighRangeSymbol() {
		return alertSevereHighRangeSymbol;
	}
	public void setAlertSevereHighRangeSymbol(String alertSevereHighRangeSymbol) {
		this.alertSevereHighRangeSymbol = alertSevereHighRangeSymbol;
	}
	public double getAlertSevereHighRangeValue() {
		return alertSevereHighRangeValue;
	}
	public void setAlertSevereHighRangeValue(double alertSevereHighRangeValue) {
		this.alertSevereHighRangeValue = alertSevereHighRangeValue;
	}
	public String getAlertMediumLowRangeSymbol() {
		return alertMediumLowRangeSymbol;
	}
	public void setAlertMediumLowRangeSymbol(String alertMediumLowRangeSymbol) {
		this.alertMediumLowRangeSymbol = alertMediumLowRangeSymbol;
	}
	public double getAlertMediumLowRangeValue() {
		return alertMediumLowRangeValue;
	}
	public void setAlertMediumLowRangeValue(double alertMediumLowRangeValue) {
		this.alertMediumLowRangeValue = alertMediumLowRangeValue;
	}
	public String getAlertMediumHighRangeSymbol() {
		return alertMediumHighRangeSymbol;
	}
	public void setAlertMediumHighRangeSymbol(String alertMediumHighRangeSymbol) {
		this.alertMediumHighRangeSymbol = alertMediumHighRangeSymbol;
	}
	public double getAlertMediumHighRangeValue() {
		return alertMediumHighRangeValue;
	}
	public void setAlertMediumHighRangeValue(double alertMediumHighRangeValue) {
		this.alertMediumHighRangeValue = alertMediumHighRangeValue;
	}


}
