/**
 * 
 */
package com.clinakos.viewController.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.primefaces.component.datatable.DataTable;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.patient.ParentMedActionPlan;
import com.clinakos.data.model.patient.ReconcileInfo;
import com.clinakos.data.model.rules.html.Page;
import com.clinakos.service.IPatientMedicineService;

/**
 * @author IDC-0004
 *
 *	CreatedBy:Nagaraj
 *	This bean will be injected/or used into PatientMedicineManageBean
 */
public class AuditManageBean implements Serializable{

	
	private static final long serialVersionUID = -3358258051304711731L;
	
	
	/**
	 * reconcileInfo being used in UI -kayak page or overviewFilter page and medication
	 * JSF pages
	 * 
	 */
	private ReconcileInfo reconcileInfo;
	
	private List<ParentMedActionPlan> inactiveMedsWithActiveMAP; //List for MedPlan which has been discontinued 
	
	private DataTable orphanedMedPlansDataTable;
	
	IPatientMedicineService patientMedicineService;
	
	private ParentMedActionPlan parentMedActionPlan;
	/**
	 * overViewFilter.jsf
	 * 	medication.jsf
	 * 
	 * @return  ReconcileInfo object 
	 */
	public ReconcileInfo getReconcileInfo() {
		if(reconcileInfo==null){
			reconcileInfo=new ReconcileInfo();
			int patientId=new ContextUtil().getPatientId();
			reconcileInfo=patientMedicineService.getLatestReconcileInfo(patientId); // Get Latest Reconcile Info based on patient id 
		}
		return reconcileInfo;
	}

	public void setReconcileInfo(ReconcileInfo reconcileInfo) {
		this.reconcileInfo = reconcileInfo;
	}


	public void setPatientMedicineService(
			IPatientMedicineService patientMedicineService) {
		this.patientMedicineService = patientMedicineService;
	}
   /**
    * used in genralmedactionplan.jsf page 
    * @return List of Discontinued  ParentMedActionPlan 
    */
	public List<ParentMedActionPlan> getInactiveMedsWithActiveMAP() {
		if(inactiveMedsWithActiveMAP==null){
			inactiveMedsWithActiveMAP=new ArrayList<ParentMedActionPlan>();
			inactiveMedsWithActiveMAP=patientMedicineService.getInactiveMedsWithActiveMAP(new ContextUtil().getPatientId()); // Get ParentMedActionPlan which has been  discontinued 
		}
		return inactiveMedsWithActiveMAP;
	}

	public void setInactiveMedsWithActiveMAP(
			List<ParentMedActionPlan> inactiveMedsWithActiveMAP) {
		this.inactiveMedsWithActiveMAP = inactiveMedsWithActiveMAP;
	}
	/**
	 * used in genralmedactionplan.jsf
	 * For Removing  Discontinued ParentMedActionPlan Select Row Object Data from DataTable 
	 */
	public void removeOrphanedMedActionPlan(){
		parentMedActionPlan=new ParentMedActionPlan();
		parentMedActionPlan=(ParentMedActionPlan) getOrphanedMedPlansDataTable().getRowData(); // Get Object Row Data of ParentMedActionPlan
	}
    /**
     * It will delete selected ParentMedActionPlan which has been discontinued 
     */
	public void deleteOrphanedMedActionPlan(){
		patientMedicineService.deleteGeneralMAPonSelectedDrug(parentMedActionPlan); // Delete selected ParentMedActionPlan 
		resetOrphanedMAP(); //Reset Data of Orphaned map 
	}
	
	private void resetOrphanedMAP() {
		inactiveMedsWithActiveMAP=null;
	}

	public DataTable getOrphanedMedPlansDataTable() {
		return orphanedMedPlansDataTable;
	}

	public void setOrphanedMedPlansDataTable(DataTable orphanedMedPlansDataTable) {
		this.orphanedMedPlansDataTable = orphanedMedPlansDataTable;
	}
	public ParentMedActionPlan getParentMedActionPlan() {
		return parentMedActionPlan;
	}

	public void setParentMedActionPlan(ParentMedActionPlan parentMedActionPlan) {
		this.parentMedActionPlan = parentMedActionPlan;
	}
}
