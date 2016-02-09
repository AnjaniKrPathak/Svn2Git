package com.clinakos.service.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.event.SaveOrUpdateEventListener;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.dao.ILabDAO;
import com.clinakos.data.model.patient.EmdeonLabOrders;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.LabResultDetailClinakos;
import com.clinakos.data.model.patient.LoincKeyMaster;
import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.ORUPatientLabResult;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.service.ILabService;

public class LabServiceImpl implements ILabService {
	public static final Logger logger = Logger.getLogger("LabServiceImpl.class");
	private ILabDAO labDAO;

	/**
	 * @return the labDAO
	 */
	public ILabDAO getLabDAO() {
		return labDAO;
	}

	/**
	 * @param labDAO
	 *            the labDAO to set
	 */
	public void setLabDAO(ILabDAO labDAO) {
		this.labDAO = labDAO;
	}

	/*
	 * Get Current Lab Result Data 
	 * (non-Javadoc)
	 * @return List of ProcedureResultHistory
	 * @see com.clinakos.doctor.service.IDoctorService#getAllProcedureResult()
	 */
	
	public List<ProcedureResultHistory> getAllProcedureResult() {

		List<ProcedureResultHistory> procedureResults = new ArrayList<ProcedureResultHistory>();
		procedureResults=labDAO.showProcedureResultData();
//-------code to find result is in reference range or not for current lab data
		/*for (ProcedureResultHistory p : procedureResults) {
			if (!(p.getLabRange().equals(null))) { 
				String[] labRangeParts = p.getLabRange().split("-");
				String labRangePart1 = labRangeParts[0]; // 004
				String labRangePart2 = labRangeParts[1]; // after '-'. i.e. calculating max value of range
				Double doubleValueOflabRangePart1 = Double.parseDouble(labRangePart1.toString());
				Double doubleValueOflabRangePart2 = Double.parseDouble(labRangePart2.toString());
				if ((p.getResult() > doubleValueOflabRangePart2) || (p.getResult() < doubleValueOflabRangePart1)) {
					p.setColorCodeIndexForCurrentLabResult(1);
				}
			}
		}*/
		for (ProcedureResultHistory pr : procedureResults) {
			logger.info("getAllProcedureResult inside labservice:::"+pr.getLabName()+pr.getLabRange());
		}
		return procedureResults;
	
	}


	public List<ProcedureResultHistory> labResultValueForChart(Date periodDate,
			String labName,int labId) {
		// TODO Auto-generated method stub
		return labDAO.labResultValueForChart(periodDate, labName,labId);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.clinakos.doctor.service.IDoctorService#getProcedureResultHistory()
	 */
	
	public List<ProcedureResultHistory> getProcedureResultHistory(
			String procudreType) {

		List<ProcedureResultHistory> procedureResultsHistory = new ArrayList<ProcedureResultHistory>();
		procedureResultsHistory=labDAO.showProcedureResultHistoryData(procudreType);
//-------code to find result is in reference range or not for lab history data
		/*for (ProcedureResultHistory p : procedureResultsHistory) {
			System.out.println("inside service getProcedureResultListhistory:::labName:"+p.getProcedureType().getLaboratoryName()+":range::"+p.getProcedureType().getRange());
			if (!(p.getProcedureType().getRange().equals(null))) {
					String[] labRangeParts = p.getProcedureType().getRange().split("-");
					String labRangePart1 = labRangeParts[0]; // before '-'. i.e. calculating min value of range
					String labRangePart2 = labRangeParts[1]; // after '-'. i.e. calculating max value of range
					Double doubleValueOflabRangePart1 = Double.parseDouble(labRangePart1.toString());
					Double doubleValueOflabRangePart2 = Double.parseDouble(labRangePart2.toString());
					if ((p.getResult() > doubleValueOflabRangePart2)|| (p.getResult() < doubleValueOflabRangePart1)) {
						p.setColorCodeIndexForLabHistory(1);
					}
			}
		}*/
		return procedureResultsHistory;
	}

	public List<LoincKeyMaster> getloincMasterList() {
		// TODO Auto-generated method stub
		return labDAO.getloincMasterList();
	}

	public String findlabTestMaxValue(int chartTypeID) {
		// TODO Auto-generated method stub
		return labDAO.findlabTestMaxValue(chartTypeID);
	}

	public List<PharmacogenomicsUserSummary> findPharmacogenomicsUserSummaryList(
			int patientId) {
		// TODO Auto-generated method stub
		return labDAO.findPharmacogenomicsUserSummaryList(patientId);
	}

	public List<ProcedureResultHistory> getProcedureResultForPatient() {
		return labDAO.showProcedureResultDataForPatient();
	}


	public List<PharmacogenomicsUserSummary> getPharmacogenomicsSummaryBasedOnMedicine() {
		// TODO Auto-generated method stub
		return labDAO.findPharmacogenomicsSummaryBasedOnMedicine();
	}

/*
 * ***********************-Method to find Lab Reminders for patient
 * @see com.clinakos.service.ILabService#findRemindersForLab(int)
 */
	public List<MedandGenricmed> findRemindersForLab(int loginId,Date nextDate) {
		logger.info("Method findRemindersForLab called in labServiceImpl:::::");
		List<MedandGenricmed> medPlanList=new ArrayList<MedandGenricmed>();
		medPlanList=labDAO.findRemindersForLab(loginId,nextDate);
		try{
		if (medPlanList!=null) {
			for (MedandGenricmed medActionPlan : medPlanList) {
				logger.info("value of medActionPlan list:::"+medActionPlan.getLab()+"::"+medActionPlan.getLabfrequency()+"::"+medActionPlan.getCreatedate());
				int reminderDays = 0;
				if(medActionPlan.getLabfrequency().matches("\\d.*")) {
					/*int reminderDays=Integer.valueOf(medActionPlan.getLabfrequency().substring(0,medActionPlan.getLabfrequency().indexOf(" ")));*/ // Commented By Anjani
					logger.info("medActionPlan.getLabfrequency().:::: "+ medActionPlan.getLabfrequency().replaceAll("[0-9]", ""));
					if(medActionPlan.getLabfrequency().replaceAll("[0-9\\s+]", "").equalsIgnoreCase("Days")){
						 reminderDays=Integer.parseInt(medActionPlan.getLabfrequency().replaceAll("[\\D]", ""));
					}
					else if (medActionPlan.getLabfrequency().replaceAll("[0-9\\s+]", "").equalsIgnoreCase("Weeks")) {
						reminderDays=Integer.parseInt(medActionPlan.getLabfrequency().replaceAll("[\\D]", ""))*7;
					}
					else if (medActionPlan.getLabfrequency().replaceAll("[0-9\\s+]", "").equalsIgnoreCase("Months")) {
						reminderDays=Integer.parseInt(medActionPlan.getLabfrequency().replaceAll("[\\D]", ""))*30;
					}
					else if (medActionPlan.getLabfrequency().replaceAll("[0-9\\s+]", "").equalsIgnoreCase("Years")) {
						reminderDays=Integer.parseInt(medActionPlan.getLabfrequency().replaceAll("[\\D]", ""))*365;
					}
					if(!(medActionPlan.getDosingStartDate()==null)){
						medActionPlan.setNextLabDate(new DateUtil().AddDate(medActionPlan.getDosingStartDate(), reminderDays));
						logger.info("reminderDays ::::::::::::::"+reminderDays+"Dosing Start Date :::"+medActionPlan.getDosingStartDate()+"Lab Trype "+medActionPlan.getLab());
					}
					
				}
			
			}
		  }
		}
		catch(NullPointerException ne){
			logger.error("Null Pointer exception in findRemindersForLab in LabServiceIMPL:::::", ne);
			ne.printStackTrace();
		}
		catch(Exception e){
			logger.error("exception in findRemindersForLab in LabServiceIMPL:::::", e);
			e.printStackTrace();
		}
		return medPlanList;
	}

	/**
	 * Save Lab Result Data 
	 * @param resultDate
	 * @param result
	 * @param labTypeId
	 * @param labType
	 * @param loincCode
	 * @see com.clinakos.service.ILabService#saveLabDetailValue(Date, String, int, String, String)
	 * 
	 * 
	 */
	public void saveLabDetailValue(Date resultDate,String result, int labTypeId,String labType, String loincCode) {
		labDAO.saveLabDetailValue(resultDate,result,labTypeId,labType,loincCode);
		
	}

	
	public List<ProcedureType> getLabTypeMaster() {
	
		return labDAO.getLabTypeMaster();
	}
	
    /**
     * used for delete Lab Details Data 
     * @param rowIdnumber
     * @see com.clinakos.data.dao.ILabDAO#deleteLabDetails(int)
     */
	public void deleteLabDetails( int rowIdnumber)
	{
labDAO.deleteLabDetails(rowIdnumber);
}
	/**
	 * Edit selected lab Data 
	 * @param selectedCurrentLabIdForEdit current Lab Id 
	 * @param selectedCurrentLabResultForEdit
	 * @param selectedCurrentLabNameForEdit
	 * 
	 */
	public  void editLabResult(int selectedCurrentLabIdForEdit,double selectedCurrentLabResultForEdit,String selectedCurrentLabNameForEdit){
		labDAO.editLabResult(selectedCurrentLabIdForEdit,selectedCurrentLabResultForEdit,selectedCurrentLabNameForEdit);
	}
	/**
	 * It is used for editLab History data 
	 * @param rowIdOfLabHistory idOfSelected Lab History data 
	 * @param labHistoryResult 
	 * @see com.clinakos.service.ILabService#editHistory(int, double)
	 */
	public void editHistory(int rowIdOfLabHistory,double labHistoryResult){
		labDAO.editHistory(rowIdOfLabHistory, labHistoryResult);
	}

/*
 * ********method to show llab history on row expension
 * *****@uthor: saurabh
 */
	public List<ProcedureResultHistory> getprocedureResultHistoryListOnRowExpension(String selectedLabName, int rowId) {
		
		List<ProcedureResultHistory> procedureResultsHistory = new ArrayList<ProcedureResultHistory>();
		procedureResultsHistory=labDAO.getprocedureResultHistoryListOnRowExpension(selectedLabName,rowId);
//--------------code to remove lab result which is in current lab
		/*int count=0;
		if (procedureResultsHistory.size()>=1) {
				
			for (ProcedureResultHistory p : procedureResultsHistory) {
				logger.info("outside if block of row expension method:::"+p.getId());
				 count++;
				if (p.getProcedureType().getId()==selectedLabName && p.getId()==rowId) {
					 logger.info("inside if block of row expension method:::"+p.getId());
					 break;
				}
			}
			System.out.println(count+":::::::::::size before:::"+procedureResultsHistory.size());
			procedureResultsHistory.remove(count-1);
			System.out.println(count+":::::::::::size after:::"+procedureResultsHistory.size());*/
		
		
//-------code to find result is in reference range or not for lab history data
			/*for (ProcedureResultHistory p : procedureResultsHistory) {
				logger.info("inside service getProcedureResultListhistory:::labName:"+p.getProcedureType().getLaboratoryName()+":range::"+p.getProcedureType().getRange());
				String[] labRangeParts = p.getProcedureType().getRange().split("-");
				String labRangePart1 = labRangeParts[0]; // before '-'. i.e. calculating min value of range
				String labRangePart2 = labRangeParts[1]; // after '-'. i.e. calculating max value of range
				Double doubleValueOflabRangePart1 = Double.parseDouble(labRangePart1.toString());
				Double doubleValueOflabRangePart2 = Double.parseDouble(labRangePart2.toString());
				if ((p.getResult() > doubleValueOflabRangePart2)|| (p.getResult() < doubleValueOflabRangePart1)) {
					
					p.setColorCodeIndexForLabHistory(1);
				}
			}*/
		//}
		return procedureResultsHistory;
	}


	
	public void saveLabResltDetailDataFromNewCrop(
			List<LabResultDetailClinakos> labDetailClinakosList) {
            labDAO.saveLabResltDetailDataFromNewCrop(labDetailClinakosList);
		
	}


	public List<ProcedureResultHistory> labResultValueForLineChart(
		) {
		// TODO Auto-generated method stub
		return labDAO.showProcedureHistoryData();
	}

	/*public List<PatientMedicationHistory> fetchMedicationHistoryList(
			int patientId, String string) {
		return labDAO.fetchMedicationHistory(patientId, string);
	}*/

	
	public void saveOruPatientLabResultFromNewCropToClinakos(
			List<ORUPatientLabResult> wsPatientLabResultList,Date reportDate) {
		// TODO Auto-generated method stub
		labDAO.saveOruPatientLabResultFromNewCropToClinakos(wsPatientLabResultList,reportDate);
		
	}

	
	public List<ORUPatientLabResult> getAllWebServicesPatientLabResult() {
		// TODO Auto-generated method stub
		return labDAO.getAllWebServicesPatientLabResult();
	}

	public ProcedureResultHistory findCurrentLabValue(int patientId,
			int providerId, int loginId,  String labName) {
		
		return labDAO.findCurrentLabValue(patientId,providerId,loginId,  labName);
	}

	//use for high chart at medaction plan
	public List<GenericMedActionPlan> getAllGenericMedActionPlan(
			int  id ) { 
		return labDAO.getAllGenericMedActionPlan(id);
	}

	public List<GenericMedActionPlan> getAllGenericMedActionPlan1(int patientId) {
		// TODO Auto-generated method stub
		return labDAO.getAllGenericMedActionPlan1(patientId);
	}
	//use for medicine in med action plan chart
	  public List<MedandGenricmed> fetchGenericList(int patientId) {
			
			return labDAO.fetchGenericList(patientId);

	  }
//use for lab displaying at high chart...
	public List<ProcedureResultHistory> getProcedureHistoryListForChart(String lab, int patientId,Date periodDate) {
		return labDAO.getProcedureHistoryListForChart(lab, patientId,periodDate);
	}
	/**
	 * Get the lab name based on patientId and lab 
	 * @param patientId
	 * @param lab
	 * @return List of  MedandGenricmed 
	 * @see com.clinakos.service.ILabService#fetchGenericListforLab(int, String)
	 */
	public List<MedandGenricmed> fetchGenericListforLab(int patientId, String lab) {
		
		return labDAO.fetchGenericListforLab(patientId,lab);

  }

	public List<PatientMedicationHistory> fetchGenericListforDisplay(int patientId,
			String medicinename, Date periodDate) {
		// TODO Auto-generated method stub
		return labDAO.fetchGenericListforDisplay(patientId,medicinename,periodDate);
	}

/*
 * used method for search Lab name for adding 
 * @param searchLabOption type of Search 
 * @param inputForLabSearch input data for Lab Search 
 * (non-Javadoc)
 * @see com.clinakos.service.ILabService#getProcedureTypeAferSearch(int, java.lang.String)
 */
	public List<ProcedureType> getProcedureTypeAferSearch(int searchLabOption,
			String inputForLabSearch) {

		return labDAO.getProcedureTypeAferSearch(searchLabOption,inputForLabSearch);
	}

	public List<SigCode> fetchsigcodelist() {

		return labDAO.fetchsigcodelistforgraph();
	}
	
	public List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugId, int patientId,Date periodDate) {
		return labDAO.findPharmacyDetailHistoryListAccordingToParticularDrugId(drugId,patientId,periodDate);
	}
     public List<PatientMedicationData> fetchpatientmedicationlist(int patientId,String medicinename,Date periodDate)
     {
    	 return labDAO.fetchpatientmedicationlist(patientId,medicinename,periodDate);
     }


	public void integrateEmedonLabDataInClinakos(Date reportDate) {
		labDAO.integrateEmedonLabDataInClinakos(reportDate);
		
	}

	
	public List<ProcedureResultHistory> getPsychoPharmLabResultData(
			int patientId,String psychoPharmClinicName) {
		
		return labDAO.getPsychoPharmLabResultData(patientId,psychoPharmClinicName);
	}

	
	public List<ProcedureResultHistory> getHepCLabResultData(int patientId,
			String hepatitisCClinicName) {
		// TODO Auto-generated method stub
		
		return labDAO.getHepCLabResultData(patientId,hepatitisCClinicName);
	}


	public ORUPatientLabResult getEmedonLabResult(String medRecordId) {
		
		return labDAO.getEmedonLabResult(medRecordId);
	}

	@Override
	public List<ProcedureType> getPsychopharmProcedureTypes(
			List<String> labParameters) {
		
		return labDAO.getPsychopharmProcedureTypes(labParameters);
	}

	
	public void saveEmdeonLabOrderData(
			ArrayList<EmdeonLabOrders> emdeonLabOrdersList) {
		
		labDAO.saveEmdeonLabOrderData(emdeonLabOrdersList);
	}


	}