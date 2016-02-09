
package com.clinakos.viewController.bean;

import static com.clinakos.common.util.ClinakosConstant.PRESCRIPITION_STATUS_CURRENT;
import static com.clinakos.common.util.ClinakosConstant.RECONCILE_MEDS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.service.IPatientMedicineService;
import com.clinakos.service.serviceImpl.PatientMedicineServiceImpl;

/**
 * BatchReconcileProcess is triggered from Admin UI for selected patients.
 * Batching Records : Auto-Filling into Current Med List from past one year.
 * Assembla Ticket :#1154
 * 
 * @author Nagaraj
 *
 */

public class BatchReconcileProcess implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = Logger.getLogger("BatchReconcileProcess.class");
	
	@Autowired
	 IPatientMedicineService patientMedicineService;
	
	@Autowired
	 IUserDao userDAO;
	
	/**
	 * 
	 * 
	 * @param selectedUserLoginDetail
	 */
	
	public void autoFillCurrentMedsFromPharmacyMeds(int patientId,int timePeriod,int providerIdForMultiThreading
			,int loginIdForMultiThreading,ProviderLocation providerLocationObj,RoleSecurity roleSecurityObj,DoctorDetail docterDetailForMultithreading){
		UserLoginDetail  selectedUserLoginDetail[] = new UserLoginDetail[1];
		UserLoginDetail userObj = new UserLoginDetail();
		userObj.setUserId(patientId);
		
		selectedUserLoginDetail[0] = userObj;
		
		LocalDate todayDate=new LocalDate();
		LocalDate endDate=todayDate.minusYears(1);
		int providerId=providerIdForMultiThreading;
		/**
		 * First Get Pharmacy details history for selected Patients from past one year with key/Value pair
		 * 
		 */
		
		
		//Map<Integer,List<PharmacyDetail>>pharmacyHistoryMap=patientMedicineService.getPharmacyHistoryData(selectedUserLoginDetail,todayDate,endDate);
		
		List<HashMap<Integer, List<PharmacyDetail>>>pharmacyHistoryList=patientMedicineService.getPharmacyHistoryData(selectedUserLoginDetail,todayDate,endDate);
	//	Map<Integer,List<PatientMedicationData>>medicationMap=patientMedicineService.getPatientMedicationData(providerId,selectedUserLoginDetail);
		
		/**
		 * Pharmacy history with latest records for last filled Date
		 * 
		 */
		Map<Integer,List<PharmacyDetail>>pharmacyLatestHistory=new HashMap<Integer, List<PharmacyDetail>>();
		
		/**
		 *  Pharmacy history with old records for First Filled date
		 * 
		 */
		Map<Integer,List<PharmacyDetail>>pharmacyOldHistory=new HashMap<Integer, List<PharmacyDetail>>();
		pharmacyOldHistory=patientMedicineService.getPharmacyOldHistory(selectedUserLoginDetail);
		if(pharmacyHistoryList!=null && pharmacyHistoryList.size()>0){
			pharmacyLatestHistory=pharmacyHistoryList.get(0);
			//pharmacyOldHistory=pharmacyHistoryList.get(1);
		}
		
		Map<Integer,List<PatientMedicationData>>pharmacyToPatientMedicationObjMaps=convertPharmacyObjToPatientMedicationObj(selectedUserLoginDetail,pharmacyLatestHistory,
				pharmacyOldHistory,providerId,providerLocationObj,roleSecurityObj,docterDetailForMultithreading);
		
		//Now compare both the lists based on drug name Id,drug Id and last filled date
		
		//As I can use already existing methods to save the reconcile process
		if(pharmacyToPatientMedicationObjMaps!=null && pharmacyToPatientMedicationObjMaps.size()>0){
		patientMedicineService.saveAutoFillReconcileMeds(pharmacyToPatientMedicationObjMaps);
		}
		
		
	}
 
	private Map<Integer, List<PatientMedicationData>> convertPharmacyObjToPatientMedicationObj(
			UserLoginDetail[] selectedUserLoginDetail,Map<Integer, List<PharmacyDetail>> pharmacyHistoryMap, 
			Map<Integer, List<PharmacyDetail>> pharmacyOldHistory, int loginProviderId,ProviderLocation providerLocationObj,RoleSecurity roleSecurityObj,DoctorDetail docterDetailForMultithreading) {
		int providerId=loginProviderId;
		Map<Integer,List<PatientMedicationData>>pharmacyToPatientMedicationObjMaps=new HashMap<Integer, List<PatientMedicationData>>();
		for(UserLoginDetail userLoginDetail:selectedUserLoginDetail){
			int patientId=userLoginDetail.getUserId();
			List<PharmacyDetail>patientPharmacyList=pharmacyHistoryMap.get(patientId);
			List<PharmacyDetail>patientPharmacyOldHistory=pharmacyOldHistory.get(patientId);
			int insuranceId=0;
			try{
			insuranceId=userDAO.findUserInsuranceID(patientId);
			}catch(Exception e){
				insuranceId=0;
				e.printStackTrace();
			}
			if((patientPharmacyList!=null && patientPharmacyList.size()>0) &&(patientPharmacyOldHistory!=null && patientPharmacyOldHistory.size()>0)){
			List<PatientMedicationData>patientMedications=preparePatientMedicationData(patientPharmacyList,patientId,providerId,patientPharmacyOldHistory,
					providerLocationObj,roleSecurityObj,insuranceId,docterDetailForMultithreading);
			pharmacyToPatientMedicationObjMaps.put(patientId, patientMedications);
			}
		}
		return pharmacyToPatientMedicationObjMaps;
	}

	private List<PatientMedicationData> preparePatientMedicationData(
			List<PharmacyDetail> patientPharmacyList, Integer patientId, int providerId, List<PharmacyDetail> patientPharmacyOldHistory, 
			ProviderLocation providerLocationObj, RoleSecurity roleSecurityObj, int insuranceId, DoctorDetail docterDetailForMultithreading) {
		List<PatientMedicationData>patientMedicationData=new ArrayList<PatientMedicationData>();
		for(PharmacyDetail patientDrugDetail5:patientPharmacyList){
		PatientMedicationData	patientMedicationDataForReconcile=new PatientMedicationData();
		
		patientMedicationDataForReconcile.setDrugs(patientDrugDetail5.getDrugInfo());
		
		if(!(((patientDrugDetail5.getDoctorName()==null)) || (patientDrugDetail5.getDoctorName().isEmpty())))
		{
			String firstprescribename;
			String lastprescribename;
			char first;
			char second;
			String prescribe_name1 = new String();
			String prescriber_name2= new String();
			String prescribename=patientDrugDetail5.getDoctorName();
			String[] split = prescribename.split(" ");
			if(split.length>0){
				if (((StringUtils.isNotBlank(split[0]))))
				{
					 firstprescribename=split[0];
					 first=firstprescribename.charAt(0);
					 prescribe_name1=first+firstprescribename.substring(1).toLowerCase();
				}
			}
			if(split.length>=2){
				if ((StringUtils.isNotBlank(split[1])))
				{
					 lastprescribename=split[1];
					 second=lastprescribename.charAt(0);
					 prescriber_name2=second+lastprescribename.substring(1).toLowerCase();
				}
				}
			String prescriber_Name=prescribe_name1+" "+prescriber_name2;
			patientMedicationDataForReconcile.setPrescriberName(prescriber_Name);
		}
		else
		{
			patientMedicationDataForReconcile.setPrescriberName("N/A");	
		}
		//patMedData.setDirections("qPM");
		
			patientMedicationDataForReconcile.setDrugId((patientDrugDetail5.getDrugId()));
		/*System.out.println("patientDrugDetail5.getDrugId():::::"+patientDrugDetail5.getDrugId()+patientDrugDetail5.getDrugInfo());*/
		patientMedicationDataForReconcile.setQuantity(Integer.toString(patientDrugDetail5.getQuantity()));
		/*patMedData.setStartDate(new DateUtil().convertStringToDateFormat(patientDrugDetail5.getFillDate(),DATE_PATTERN));*/ //Commented By Anjani
		logger.info("{--}{--}{--}patient  {--}{--}{--}" + "Current: - Drug Id "+patientDrugDetail5.getDrugId()+" Drug Name Id "+patientDrugDetail5.getDrugNameId() +" Drug Name "+patientDrugDetail5.getDrugInfo());
		for(PharmacyDetail oldPharmacyDetail:patientPharmacyOldHistory){
			if(oldPharmacyDetail!=null){
				logger.info("Inside Old Pharmacy History "+" Drug Id :- "+oldPharmacyDetail.getDrugId() +" Drug Name Id "+oldPharmacyDetail.getDrugNameId() +" Drug Name "+oldPharmacyDetail.getDrugInfo());
				if(oldPharmacyDetail.getDrugId() == patientDrugDetail5.getDrugId() ){
					logger.info("Inside Matching condition ");
					patientMedicationDataForReconcile.setStartDate(oldPharmacyDetail.getDateOfPurchase());
					break;
				}
			}
		}
		
		patientMedicationDataForReconcile.setLastFillDate(patientDrugDetail5.getDateOfPurchase());
		List<Object>complianceVal=patientMedicineService.complianceCalculationForDrug(patientMedicationDataForReconcile.getDrugId(),patientPharmacyOldHistory);
		if(complianceVal!=null){
			logger.info("Calculated Compliance percentage val "+ complianceVal.get(0));
			patientMedicationDataForReconcile.setCompliancePercentage((Integer) complianceVal.get(0));
		}else{
		patientMedicationDataForReconcile.setCompliancePercentage(0);
		}
		patientMedicationDataForReconcile.setStrengths(patientDrugDetail5.getDosage());
		patientMedicationDataForReconcile.setDaySupply(patientDrugDetail5.getDaysOfSupply());
		
		/*patientMedicationDataForReconcile.setStartDate(patientDrugDetail5.getDateOfPurchase());*/
		patientMedicationDataForReconcile.setModifyDate(new DateUtil().getTodayDate());
		
		patientMedicationDataForReconcile.setCauseOfAddMeds(RECONCILE_MEDS);
		patientMedicationDataForReconcile.setFlagForVisit(true);
		
		patientMedicationDataForReconcile.setCheckReconcileMedToCurrent(true); 
		
		patientMedicationDataForReconcile.setChangeMedId(1);
		
		patientMedicationDataForReconcile.setCheckMedsForEprescribe(false);
		patientMedicationDataForReconcile.setPatientId(patientId);
		patientMedicationDataForReconcile.setProviderId(providerId);
		patientMedicationDataForReconcile.setPrescriptionStatus(PRESCRIPITION_STATUS_CURRENT);
		patientMedicationDataForReconcile.setDataProvider("FDB");
		patientMedicationDataForReconcile.setDrugNameId(patientDrugDetail5.getDrugNameId());
		if(StringUtils.isBlank(patientMedicationDataForReconcile.getFormularySymbol()))
		{
			Double drugIdForCompliance=patientMedicationDataForReconcile.getDrugId();
			Integer drugId=(int) drugIdForCompliance.doubleValue();
			logger.info("pat Med Service Impl in double form"+drugIdForCompliance +" drug id in int form "+drugId);
			String formularyStatus=patientMedicineService.findFormularySymbolForPatient(insuranceId,Integer.toString(drugId),patientId,providerId,
					providerLocationObj,roleSecurityObj,docterDetailForMultithreading);
			logger.info("Formulary Status ::::::::In Patient Medication "+formularyStatus+"  Drug Id:::::::in int "+drugId);
			
			patientMedicationDataForReconcile.setFormularySymbol(formularyStatus);
			
		}
		
		WSDrug wsdrug=new WSDrug();
		wsdrug=patientMedicineService.getWSdrugObj(patientMedicationDataForReconcile.getDataProvider(),patientMedicationDataForReconcile.getDrugId(),patientDrugDetail5.getDrugNameId());
		try{
		if(wsdrug!=null ){
		
		if(wsdrug.getGenericName()==null)
			patientMedicationDataForReconcile.setGenericName(wsdrug.getDrugName());
		else
			patientMedicationDataForReconcile.setGenericName(wsdrug.getGenericName());
		
		patientMedicationDataForReconcile.setDosageForm(wsdrug.getDosageForm());
		patientMedicationDataForReconcile.setByRoute(wsdrug.getRoute());
		patientMedicationDataForReconcile.setTherapeuticCategory(wsdrug.getTherapeuticCategory());
		patientMedicationDataForReconcile.setDrugCategory(wsdrug.getDrugCategory());
		
		}
		}catch(NullPointerException nfe){
			nfe.printStackTrace();
		}
		patientMedicationData.add(patientMedicationDataForReconcile);
		}
		return patientMedicationData;
	}

	/*public  IPatientMedicineService getPatientMedicineService() {
		if(patientMedicineService==null){
			patientMedicineService=new PatientMedicineServiceImpl();
		}
		return patientMedicineService;
	}*/

	public  void setPatientMedicineService(
			IPatientMedicineService patientMedicineService) {
		this.patientMedicineService = patientMedicineService;
	}

	public void setUserDAO(IUserDao userDAO) {
		this.userDAO = userDAO;
	}
	
	
	

}
