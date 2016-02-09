/**
 * 
 */
package com.clinakos.data.model.patient;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

/**
 * @author Nagaraj
 *
 */
@Entity
@Table(name="general_med_action_plan_parameters")
@Audited
public class ChildMedActionPlanParameter implements Serializable{

	private static final long serialVersionUID = -7517398197578519204L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="child_parameter_id")
	private int id;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=ProcedureType.class)
	@JoinColumn(name="procedure_type_id" ,referencedColumnName="procedure_type_id")
	@Fetch(FetchMode.JOIN)
	@Audited(targetAuditMode =RelationTargetAuditMode.NOT_AUDITED)
	private ProcedureType labParameterObj;
	
	/*@OneToOne(fetch=FetchType.LAZY)
	@Audited(targetAuditMode =RelationTargetAuditMode.NOT_AUDITED)
	private ProcedureType labParameterObj;*/
	/*@Transient
	private ProcedureType labParameterObj=new ProcedureType();*/
	
	@Column(name="frequencyUnit")
	private String frequencyUnit;
	
	@Column(name="frequencyNumber")
	private int frequencyNumber;
	
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
	
	@Column(name="createdBy")
	private int createdBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedBy")
	private int updatedBy;
	
	@Column(name="updatedDate")
	private Date updatedDate;
	

	
	//for UI purpose
	@Transient
	private boolean databaseCheck;
	
	@Transient
	private String labType;
	
	@Transient
	private String goalRange;
	
	@Transient
	private Date nextLabDate;
	
	@Transient
	private boolean isMedPlanUpdateRequired;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ProcedureType getLabParameterObj() {
		return labParameterObj;
	}
	public void setLabParameterObj(ProcedureType labParameterObj) {
		this.labParameterObj = labParameterObj;
	}

	public String getFrequencyUnit() {
		return frequencyUnit;
	}
	public void setFrequencyUnit(String frequencyUnit) {
		this.frequencyUnit = frequencyUnit;
	}
	public int getFrequencyNumber() {
		return frequencyNumber;
	}
	public void setFrequencyNumber(int frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
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
	/*public Date getNextLabDate() {
		return nextLabDate;
	}
	public void setNextLabDate(Date nextLabDate) {
		this.nextLabDate = nextLabDate;
	}*/
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
/*	public ParentMedActionPlan getParentMedActionPlan() {
		return parentMedActionPlan;
	}
	public void setParentMedActionPlan(ParentMedActionPlan parentMedActionPlan) {
		this.parentMedActionPlan = parentMedActionPlan;
	}*/
	public boolean isDatabaseCheck() {
		return databaseCheck;
	}
	public void setDatabaseCheck(boolean databaseCheck) {
		this.databaseCheck = databaseCheck;
	}
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getGoalRange() {
		return goalRange;
	}
	public void setGoalRange(String goalRange) {
		this.goalRange = goalRange;
	}
	public Date getNextLabDate() {
		return nextLabDate;
	}
	public void setNextLabDate(Date nextLabDate) {
		this.nextLabDate = nextLabDate;
	}
	public boolean isMedPlanUpdateRequired() {
		return isMedPlanUpdateRequired;
	}
	public void setMedPlanUpdateRequired(boolean isMedPlanUpdateRequired) {
		this.isMedPlanUpdateRequired = isMedPlanUpdateRequired;
	}
	
	
	
	
}
