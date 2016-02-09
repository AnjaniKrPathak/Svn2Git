
package com.clinakos.data.model.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.annotations.Type;



/**
 * @author Nagaraj
 *
 */
@Entity
@Table(name="general_med_action_plan")
@Audited
public class ParentMedActionPlan implements Serializable{


	private static final long serialVersionUID = 5409287800482252850L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="med_Action_PlanId")
	private int id;
	
	@Column(name="drugNameId")
	private double drugNameId;
	
	@Column(name="drugId")
	private double drugId;
	
	@Column(name="patientId")
	private int patientId;
	
/*	@ManyToOne
	@JoinColumn(name="id")
	private PatientDiagnosesDetails patientDiagnosesDetails;*/
	
	@Column(name="dosingStartDate")
	private Date dosingStartDate;
	
	@Column(name="systemMedPlanId")
	private int systemMedPlanId;
	
	/*@ElementCollection
	@JoinTable(name="general_med_action_plan_parameters",
    joinColumns=@JoinColumn(name="medActionPlanId"))*/
	/*@JoinTable(name="general_med_action_plan_parameters",
    joinColumns=@JoinColumn(name="medActionPlanId"))*/
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, targetEntity=ChildMedActionPlanParameter.class)
	@JoinColumn(name="medActionPlanId",referencedColumnName="med_Action_PlanId")
	@Fetch(FetchMode.SUBSELECT)
	private List<ChildMedActionPlanParameter>childLabParameters=new ArrayList<ChildMedActionPlanParameter>();
	
	@Column(name="createdBy")
	private int createdBy;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedBy")
	private int updatedBy;
	
	@Column(name="updatedDate")
	private Date updatedDate;
	
	@Column(name="clinicProviderId")
	private int clinicProviderId;
	
	@Column(name="prescriptionDate")
	private Date prescriptionDate;
	
	@Column(name="strength")
	private String strength;
	
	@Column(name="regimen")
	private String regimen;
	
	@Column(name="prescribedBy")
	private String prescribedBy;
	
	@Column(name="dosageForm")
	private String dosageForm;
	
	@Column(name="patientDiagnosisId")
	private String patientDiagnosisDetailsId;
	
	@Column(name="patientDiagnosisName")
	private String patientDiagnosisName;
	
	@Column(name="drugName")
	private String drugName;
	
	@Column(name="unit")
	private String unit;
	
	@Transient
	private String createdByName;
	
	@Transient
	private String updatedByName;
	
	@Transient
	private double goalRangeValue;
	
	@Transient
	private double goalRangeEndValue;
	
	@Transient
	private String selectedLabUI;
	
	@Transient
	private PatientDiagnosesDetails patientDiagnosisDetailsObj;
	
	@Column(name="providerId")
	private int providerId;
	
	@Column(name="isDrugDiscontinued")
	private boolean isDrugDiscontinued;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public Date getDosingStartDate() {
		return dosingStartDate;
	}
	public void setDosingStartDate(Date dosingStartDate) {
		this.dosingStartDate = dosingStartDate;
	}
	public int getSystemMedPlanId() {
		return systemMedPlanId;
	}
	public void setSystemMedPlanId(int systemMedPlanId) {
		this.systemMedPlanId = systemMedPlanId;
	}
	

	
/*public Set<ChildMedActionPlanParameter> getChildLabParameters() {
		return childLabParameters;
	}
	public void setChildLabParameters(
			Set<ChildMedActionPlanParameter> childLabParameters) {
		this.childLabParameters = childLabParameters;
	}*/
	/*	public PatientDiagnosesDetails getPatientDiagnosesDetails() {
		return patientDiagnosesDetails;
	}
	public void setPatientDiagnosesDetails(
			PatientDiagnosesDetails patientDiagnosesDetails) {
		this.patientDiagnosesDetails = patientDiagnosesDetails;
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
	public int getClinicProviderId() {
		return clinicProviderId;
	}
	public void setClinicProviderId(int clinicProviderId) {
		this.clinicProviderId = clinicProviderId;
	}
	public Date getPrescriptionDate() {
		return prescriptionDate;
	}
	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getRegimen() {
		return regimen;
	}
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	public String getPrescribedBy() {
		return prescribedBy;
	}
	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	
	public String getPatientDiagnosisName() {
		return patientDiagnosisName;
	}
	public void setPatientDiagnosisName(String patientDiagnosisName) {
		this.patientDiagnosisName = patientDiagnosisName;
	}
	public List<ChildMedActionPlanParameter> getChildLabParameters() {
		return childLabParameters;
	}
	public void setChildLabParameters(
			List<ChildMedActionPlanParameter> childLabParameters) {
		this.childLabParameters = childLabParameters;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getUpdatedByName() {
		return updatedByName;
	}
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}
	public double getGoalRangeValue() {
		return goalRangeValue;
	}
	public void setGoalRangeValue(double goalRangeValue) {
		this.goalRangeValue = goalRangeValue;
	}
	public double getGoalRangeEndValue() {
		return goalRangeEndValue;
	}
	public void setGoalRangeEndValue(double goalRangeEndValue) {
		this.goalRangeEndValue = goalRangeEndValue;
	}
	public String getSelectedLabUI() {
		return selectedLabUI;
	}
	public void setSelectedLabUI(String selectedLabUI) {
		this.selectedLabUI = selectedLabUI;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getPatientDiagnosisDetailsId() {
		return patientDiagnosisDetailsId;
	}
	public void setPatientDiagnosisDetailsId(String patientDiagnosisDetailsId) {
		this.patientDiagnosisDetailsId = patientDiagnosisDetailsId;
	}
	public PatientDiagnosesDetails getPatientDiagnosisDetailsObj() {
		return patientDiagnosisDetailsObj;
	}
	public void setPatientDiagnosisDetailsObj(
			PatientDiagnosesDetails patientDiagnosisDetailsObj) {
		this.patientDiagnosisDetailsObj = patientDiagnosisDetailsObj;
	}
	public boolean isDrugDiscontinued() {
		return isDrugDiscontinued;
	}
	public void setDrugDiscontinued(boolean isDrugDiscontinued) {
		this.isDrugDiscontinued = isDrugDiscontinued;
	}
	
}
