package com.clinakos.service.serviceImpl;

import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageDetail;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.HtmlUtils;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.common.util.RulesUtil;
import com.clinakos.data.dao.ILabDAO;
import com.clinakos.data.dao.IPatientDAO;
import com.clinakos.data.dao.IPatientMedicineDAO;
import com.clinakos.data.model.core.BatchInteractionAnalytic;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.ACOPatientMeasure;
import com.clinakos.data.model.patient.AlertGenericMedActionLab;
import com.clinakos.data.model.patient.AllergyMaster;
import com.clinakos.data.model.patient.ChildMedActionPlanParameter;
import com.clinakos.data.model.patient.ChildMonitoringParameters;
import com.clinakos.data.model.patient.ClinicDiagonsis;
import com.clinakos.data.model.patient.ClinicQuestionnaire;
import com.clinakos.data.model.patient.ClinicSubdiagnosis;
import com.clinakos.data.model.patient.ContraindicatedMedItems;
import com.clinakos.data.model.patient.ContraindicatedMeds;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.DosageFrom;
import com.clinakos.data.model.patient.DrugDrugInteractionData;
import com.clinakos.data.model.patient.DrugInteractionForWarfarin;
import com.clinakos.data.model.patient.DrugInteractionOverview;
import com.clinakos.data.model.patient.EncounterSummary;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.GeriatricPrecaution;
import com.clinakos.data.model.patient.LabDetail;
import com.clinakos.data.model.patient.MasterDrugAllergyInteraction;
import com.clinakos.data.model.patient.MasterDrugDiseaseInteraction;
import com.clinakos.data.model.patient.MasterLOINCData;
import com.clinakos.data.model.patient.MasterMonitorParameters;
import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedUnitSummary;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.Medicine;
import com.clinakos.data.model.patient.PaediatricPrecaution;
import com.clinakos.data.model.patient.ParentMedActionPlan;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientDrugAllergyInteraction;
import com.clinakos.data.model.patient.PatientDrugDiseaseInteraction;
import com.clinakos.data.model.patient.PatientDrugMapping;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PatientMedicineNotes;
import com.clinakos.data.model.patient.PatientPharmacogenomicsCurrentMedicineData;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.PharmacogenomicsRecomendations;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.PsychopharmModelData;
import com.clinakos.data.model.patient.ReconcileInfo;
import com.clinakos.data.model.patient.RouteDetails;
import com.clinakos.data.model.patient.SendMessageEditRx;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.data.model.patient.UnitDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.VisitHistory;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.data.model.patient.WeeklyDose;
import com.clinakos.data.model.rules.MedPlan;
import com.clinakos.data.model.rules.Message;
import com.clinakos.data.model.rules.ModelMessage;
import com.clinakos.data.model.rules.PsychopharmMessage;
import com.clinakos.data.model.rules.Request;
import com.clinakos.service.IPatientMedicineService;
import com.clinakos.viewController.bean.UserManageBean;
import com.clinakos.viewController.webservicMangedBean.NcFormulary1WSBean;
import com.google.gson.Gson;

import static com.clinakos.common.util.ClinakosConstant.*;

public class PatientMedicineServiceImpl implements IPatientMedicineService {
	IPatientMedicineDAO patientMedicineDAO;
	public static final Logger logger = Logger.getLogger("PatientMedicineServiceImpl.class");
	private ILabDAO labDAO;
	@Autowired
	private NcFormulary1WSBean formulary1WsBean;
	
	
	private IPatientDAO patientDAO;
	
	DecimalFormat decimalFormat=new DecimalFormat();
		
	public void setPatientMedicineDAO(IPatientMedicineDAO patientMedicineDAO) {
		this.patientMedicineDAO = patientMedicineDAO;
	}

	private List<String> currentImpactedMedicineList=new ArrayList<String>();
	
	
	/**
	 * @return the currentImpactedMedicineList
	 */
	public List<String> getCurrentImpactedMedicineList() {
		return currentImpactedMedicineList;
	}

	/**
	 * @param currentImpactedMedicineList the currentImpactedMedicineList to set
	 */
	public void setCurrentImpactedMedicineList(
			List<String> currentImpactedMedicineList) {
		this.currentImpactedMedicineList = currentImpactedMedicineList;
	}

	/*l
	 * find medication detailList and pharmacy detail list then calculate compliance according to particular medicine for particular patient
	 * @author : gopal krishna jha from lumbini
	 * (non-Javadoc)
	 * @return List Of Patient medication Data 
	 * @see com.clinakos.service.IPatientMedicineService#findPatientMedicationDataList(int)
	 */
	public List<PatientMedicationData> findPatientMedicationDataList(
			int patientId,List<SigCode> sigCodeList) {
		List<PatientMedicationData>patientMedicationDataList=new ArrayList<PatientMedicationData>();
		//find medication data list 
		patientMedicationDataList=patientMedicineDAO.findPatientMedicationDataList(patientId); // Get Medication Data based on Patient Id from database 
		
		int insuranceId=new ContextUtil().getInsuranceId();
		List<PharmacyDetail>pharmacyDetailList=new ArrayList<PharmacyDetail>();
		pharmacyDetailList=patientMedicineDAO.findPharmacyDetailListAccordingToPatirnt(patientId); // get Patient Pharmacy Detail Data based on Patient Id 
		List<FormularyDetail>formularyDetailList=new ArrayList<FormularyDetail>();
		formularyDetailList=patientMedicineDAO.findFormularyDetailList(); // Find Formulary Detail Data 
		try { 
			patientMedicationDataList=findComplianceDetail(patientMedicationDataList,pharmacyDetailList,formularyDetailList,insuranceId,sigCodeList); // get Complince Detail Data 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//updating compliance percentage and formulary tier
		patientMedicineDAO.updatpatientMedicationForComplianceAndFormulary(patientMedicationDataList); // update compiaance data in patient medication database table  
		return patientMedicationDataList;
	}
	
public List<ACOPatientMeasure> getACOPatientMeasure(int pat_id){
	return patientMedicineDAO.getACOPatientMeasure(pat_id);
		
	}
public List<ACOPatientMeasure> getACOPatientMeasureHistory(int pat_id){
	return patientMedicineDAO.getACOPatientMeasureHistory(pat_id);	
	}

public void CalPatientmesureCount(int pat_id){
	patientMedicineDAO.CalPatientmesureCount(pat_id);
		
	}
	/**
	 * Get Compliance Detail Data based parameter 
	 * @param patientMedicationDataList
	 * @param pharmacyDetailList
	 * @param formularyDetailList
	 * @param insuranceId
	 * @param sigCodeList
	 * @return List of Patient Medication Data 
	 * @throws Exception
	 * 
	 */
	private List<PatientMedicationData> findComplianceDetail(
			List<PatientMedicationData> patientMedicationDataList,
			List<PharmacyDetail> pharmacyDetailList, List<FormularyDetail> formularyDetailList, int insuranceId,List<SigCode> sigCodeList) throws Exception {
		List<PatientMedicationData>medicineDataList=new ArrayList<PatientMedicationData>();
		for(PatientMedicationData patMedicationData:patientMedicationDataList)
		{
			PatientMedicationData patMedData=new PatientMedicationData();
			patMedData.setId(patMedicationData.getId());
			patMedData.setPatientId(patMedicationData.getPatientId());
			patMedData.setProviderId(patMedicationData.getProviderId());
			//patMedData.setFormula(patMedicationData.getFormula());
			patMedData.setSignature(patMedicationData.getSignature());
			patMedData.setNotes(patMedicationData.getNotes());
			patMedData.setDoctorId(patMedicationData.getDoctorId());
			patMedData.setStartDate(patMedicationData.getStartDate());
			patMedData.setDrugsNotes(patMedicationData.getDrugsNotes());
			patMedData.setDrugs(patMedicationData.getDrugs());
			patMedData.setStrengths(patMedicationData.getStrengths());
			patMedData.setDirections(patMedicationData.getDirections());
			patMedData.setQuantity(patMedicationData.getQuantity());
			patMedData.setLastFillDate(patMedicationData.getLastFillDate());
			patMedData.setReffils(patMedicationData.getReffils());
			patMedData.setImageName(patMedicationData.getImageName());
			patMedData.setDrugId(patMedicationData.getDrugId());
			patMedData.setDrugNameId(patMedicationData.getDrugNameId());
			patMedData.setDataProvider(patMedicationData.getDataProvider());
			patMedData.setUnit(patMedicationData.getUnit());
			patMedData.setUnitDetail(patMedicationData.getUnitDetail());
			patMedData.setByRoute(patMedicationData.getByRoute());
			patMedData.setDosageForm(patMedicationData.getDosageForm());
			patMedData.setPrn(patMedicationData.isPrn());
			patMedData.setAllowSubstitution(patMedicationData.isAllowSubstitution());
			patMedData.setPrescriptionGuid(patMedicationData.getPrescriptionGuid());
			patMedData.setPrescriberName(patMedicationData.getPrescriberName());
			patMedData.setPharmacyName(patMedicationData.getPharmacyName());
			patMedData.setPrescriptionStatus(patMedicationData.getPrescriptionStatus());
			patMedData.setPrescriptionSubStatus(patMedicationData.getPrescriptionSubStatus());
			patMedData.setCauseOfAddMeds(patMedicationData.getCauseOfAddMeds());
			patMedData.setFlagForVisit(patMedicationData.isFlagForVisit());
			patMedData.setExternalPrescriptionId(patMedicationData.getExternalPrescriptionId());
			patMedData.setDateWithTimeZoneForCompare(patMedicationData.getDateWithTimeZoneForCompare());
			patMedData.setGenericName(patMedicationData.getGenericName());
			patMedData.setDaySupply(patMedicationData.getDaySupply());
			patMedData.setDaw_dns(patMedicationData.isDaw_dns());
			patMedData.setOne_Time(patMedicationData.isOne_Time());
			//patMedData.setPharmacyType(patMedicationData.getPharmacyType());
			//patMedData.setPharmacyDetailsType(patMedicationData.getPharmacyDetailsType());
			//patMedData.setFinalDestinationType(patMedicationData.getFinalDestinationType());
			//patMedData.setFinalStatusType(patMedicationData.getFinalStatusType());
			//patMedData.setMedicineStage(patMedicationData.getMedicineStage());
			if(patMedicationData.getTherapeuticCategory()==null||patMedicationData.getDrugCategory()==null)
			{
				WSDrug wsDrug=new WSDrug();
				wsDrug=patientMedicineDAO.getWSdrugObj(patMedicationData.getDataProvider(),patMedicationData.getDrugId(),patMedicationData.getDrugNameId());
				patMedData.setTherapeuticCategory(wsDrug.getTherapeuticCategory());
				patMedData.setDrugCategory(wsDrug.getDrugCategory());
			}
			else{
				patMedData.setTherapeuticCategory(patMedicationData.getTherapeuticCategory());
				patMedData.setDrugCategory(patMedicationData.getDrugCategory());
			}
			patMedData.setFlagForMedActionPlan(patMedicationData.isFlagForMedActionPlan());

			patMedData.setAdditionalSig(patMedicationData.getAdditionalSig());
			

			//patMedData.setMedicineStatus(patMedicationData.getMedicineStatus());
			//causeOfAddMeds
			//patMedData.setCauseOfAddMeds(patMedicationData.getCauseOfAddMeds());
			patMedData.setCheckForMaintenance(patMedicationData.isCheckForMaintenance());
			//List<Object> compliancePercentageAndPharmacyName=findComplianceSymbol(patMedData.getId(),patMedData.getQuantity(),patMedData.getDirections(),patMedData.getReffils(),patMedData.getStartDate(),pharmacyDetailList);
			//patMedData.setComplianceSymbol(compliance);
			
			/**
			 * Added By Nagaraj as per new compliance calculation as per 03/Feb/2015
			 * 
			 */
		
			
			//commented by nagaraj on 03/feb/2015 as new compliance calculation needs to be implemented as per #1078
		/*	List<Object> compliancePercentageAndPharmacyName=findComplianceSymbol(patMedData.getDrugId(),patMedData.getQuantity(),patMedData.getDirections(),
					patMedData.getReffils(),patMedData.getStartDate(),pharmacyDetailList,sigCodeList);*/
			//end of Nagaraj comments
			List<Object> compliancePercentageAndPharmacyName=complianceCalculationForDrug(patMedData.getDrugId(),pharmacyDetailList);
			
			if(!compliancePercentageAndPharmacyName.isEmpty())
			{
				
			patMedData.setCompliancePercentage((Integer) compliancePercentageAndPharmacyName.get(0));
			
			patMedData.setLastFillDate((Date) compliancePercentageAndPharmacyName.get(1));
			}
			else{
				
				patMedData.setCompliancePercentage(50);
				
			}
			
			
			//find formularydetail sysmbol...
			if(patMedicationData.getFormularySymbol()==null || patMedicationData.getFormularySymbol().isEmpty() || patMedicationData.getFormularySymbol().equals(""))
			{
				Double drugIdForCompliance=patMedicationData.getDrugId();
				Integer drugId=(int) drugIdForCompliance.doubleValue();
				logger.info("pat Med Service Impl in double form"+drugIdForCompliance +" drug id in int form "+drugId);
				String formularyStatus=findFormularySymbol(insuranceId,Integer.toString(drugId));
				logger.info("Formulary Status ::::::::In Patient Medication "+formularyStatus+"  Drug Id:::::::in int "+drugId);
				
				patMedData.setFormularySymbol(formularyStatus);
				
			}
			else
				patMedData.setFormularySymbol(patMedicationData.getFormularySymbol());
			
			
			//
		
			//String prescriberName=patientMedicineDAO.findDoctorNameAccordingToId(patMedData.getDoctorId());
			//patMedData.setPrescriberName(prescriberName);
			System.out.println(patMedData.getPharmacyName()+":::::::::compliance symbol:::"+patMedData.getComplianceSymbol()+"formularyDetails symbol:::"+patMedData.getFormularySymbol());
			//Added by anjani
			//For Pharmecogenomics attention rating by saurabh 
			PharmacogenomicsRecomendations pharmacogenomicsRecomendations=patientMedicineDAO.findAttentionRatingImpactedMedication(new ContextUtil().getPatientId(),patMedData.getDrugNameId(),patMedData.getDrugs());
			System.out.println( "drug name"+patMedData.getDrugs());
		    patMedData.setAttentionRating(pharmacogenomicsRecomendations.getAttentionRating());
		    patMedData.setRecomendation(pharmacogenomicsRecomendations.getRecommendation());
		    patMedData.setOvaleMessage(pharmacogenomicsRecomendations.getOvaleMessage());
		    patMedData.setReason(pharmacogenomicsRecomendations.getImplications());
		    patMedData.setPhenoType(pharmacogenomicsRecomendations.getPhenotype());
		    patMedData.setImpactingGene(pharmacogenomicsRecomendations.getImpactingGene());
		    System.out.println("patMedData.getRecomendation() in patient med services"+patMedData.getRecomendation()+"Attention rating ="+
		    		patMedData.getAttentionRating()+"phenotype::"+patMedData.getPhenoType());			
		    medicineDataList.add(patMedData);
		}
		
		return medicineDataList;
	}

	
	/**
	 * 
	 * Created By:Nagaraj
	 * Date:03/Feb/2015
	 * 
	 * AS per 1078 assembla ticket compliance calculation needs to modified 
	 * 
	 */
	
	public List<Object> complianceCalculationForDrug(double drugId,List<PharmacyDetail> pharmacyDetailList){
		logger.info("{--}{--}{--} compliance calculation for Particular Drug started {--}{--}{--}");
		List<Object> complianceListObj=new ArrayList<Object>();
		/**
		 * Step 1:check pharmacy details for Particular drug.
		 * Step 2:if pharmacyDetailsList size is 0 or empty then compliance Percentage is zero then return it
		 * Step 3:else do the compliance calculation
		 * 
		 * 
		 */
		
		/**
		 * In compliance calculation
		 * 
		 * Step 1: Numerator- Sum of “days supply” for all the fills in an interval from pharmacyDetailsList 
		 * Step 2: get Interval start date and last filled date from pharmacyDetailsList
		 * Step 3: if lastFilledDate + daysOfSupply goes beyond todayDate then Interval end date= lastFilledDate + daysOfSupply
		 * Step 4: else if  lastFilledDate + daysOfSupply less than todayDate then  Interval end date=today date
		 * Step 5: Denominator =subtract and get days(IntervalEndDate - IntervalStartDate)
		 * Step 6: compliance %=( Numerator/Denominator)*100
		 * Step 7: As per compliance document,if compliance percentage > 100 then cap it to 100 else set calculated compliance percentage.
		 * 
		 */
		Date lastFilledDateObjValue=null;
		int compliancePercentageValue=0;
		try{
			logger.info("drugId == > "+drugId+" it's pharmacy details size "+pharmacyDetailList.size());
			if(pharmacyDetailList.size()==0 || pharmacyDetailList.isEmpty()){
				logger.info("Inside pharmacyDetailList size zero or Empty ");
				
				complianceListObj.add(compliancePercentageValue);
				complianceListObj.add(lastFilledDateObjValue);
				
				//return last filled date and compliance percentange
				
			}else{
				logger.info("pharmacyDetailList size is greater than Zero and it is not empty");
				int totalDaysOfSupply=0;
				LocalDate lastFilledDate=null;
				LocalDate intervalStartDate=null;
				int lastDaysOfSupply=0;
				boolean isFirstDateFilled=true;//for getting IntervalStartDate as pharmacyDetailList is ascending order
				if(Double.valueOf(drugId)!=null){
					for(PharmacyDetail pharmacyDetail:pharmacyDetailList){
						if(Double.valueOf(pharmacyDetail.getDrugId())!=null && pharmacyDetail.getDrugId()==drugId){
						if(StringUtils.isNotBlank(pharmacyDetail.getDaysOfSupply())){
							totalDaysOfSupply=totalDaysOfSupply+Integer.valueOf(pharmacyDetail.getDaysOfSupply());
						}
						if(isFirstDateFilled){
							intervalStartDate=new DateTime(pharmacyDetail.getDateOfPurchase()).toLocalDate();
							isFirstDateFilled=false;
						}
						if(pharmacyDetail.getDateOfPurchase()!=null){
						lastFilledDate=new DateTime(pharmacyDetail.getDateOfPurchase()).toLocalDate();
						if(StringUtils.isNotBlank(pharmacyDetail.getDaysOfSupply())){
						lastDaysOfSupply=Integer.valueOf(pharmacyDetail.getDaysOfSupply());
						}
						}
						}
					}
					logger.info("Total Days of Supply "+totalDaysOfSupply +" Interval start Date  "+intervalStartDate +"Last Days of supply "+lastDaysOfSupply+" last Filled date "+lastFilledDate);
					
					if(lastFilledDate!=null){
						//format the date pattern
						LocalDate todayDateObj=new LocalDate();
						DateTimeFormatter dateTimeFormatter=DateTimeFormat.forPattern("yyyy-MM-dd");
						DateTime todayDateTimeObj=dateTimeFormatter.parseDateTime(dateTimeFormatter.print(todayDateObj));
						DateTime lastFilledDateTimeObj=dateTimeFormatter.parseDateTime(dateTimeFormatter.print(lastFilledDate));
						
						LocalDate todayDate=new DateTime(todayDateTimeObj).toLocalDate();
						LocalDate lastFilledDateObj=new DateTime(lastFilledDateTimeObj).toLocalDate();
						
						logger.info("Today Date "+todayDate +" last filled date "+lastFilledDateObj+" in yyyy-MM-dd pattern");
						if(lastFilledDateObj.plusDays(lastDaysOfSupply).isAfter(todayDate)){
							logger.info("Inside if last Filled Date + lastDaysOfSupply is after todayDate ");
							LocalDate intervalEndDate=lastFilledDateObj.plusDays(lastDaysOfSupply);
							logger.info("Interval end date after adding lastDaysOfSupply to last Filled Date "+intervalEndDate);
							Days daysElapsed=Days.daysBetween(intervalStartDate, intervalEndDate);
							logger.info(" Days elapsed between IntervalEndDate and IntervalStartDate "+daysElapsed.getDays());
							int compliancePercentage=(totalDaysOfSupply*100/daysElapsed.getDays());
							logger.info("Calculated Compliance Percentage "+compliancePercentage +" with total days of supply "+totalDaysOfSupply +" Days elapsed "+daysElapsed.getDays());
						/*	if(compliancePercentage>100){
								//set compliance percentage as 100
								complianceListObj.add(100);
							}else{*/
								//set calculated compliance percentage.
								complianceListObj.add(compliancePercentage);
						//	}
							
							complianceListObj.add(lastFilledDateObj.toDate());
							
						}else{
							logger.info("Inside if last Filled Date + lastDaysOfSupply is before todayDate ");
							LocalDate intervalEndDate=todayDate;
							logger.info("Interval End Date "+intervalEndDate);
							Days daysElapsed=Days.daysBetween(intervalStartDate, intervalEndDate);
							logger.info(" Days elapsed between IntervalEndDate and IntervalStartDate "+daysElapsed.getDays());
							int compliancePercentage=(totalDaysOfSupply*100/daysElapsed.getDays());
							logger.info("Calculated Compliance Percentage "+compliancePercentage +" with total days of supply "+totalDaysOfSupply +" Days elapsed "+daysElapsed.getDays());
							/*if(compliancePercentage>100){
								complianceListObj.add(100);
								//set compliance percentage as 100
							}else{*/
								complianceListObj.add(compliancePercentage);
								//set calculated compliance percentage.
							//}
						//	complianceListObj.add(compliancePercentage);
							complianceListObj.add(lastFilledDateObj.toDate());
							
						}
				
						
					}
					
				}
			}
		
		}catch(Exception e){
			e.printStackTrace();
			complianceListObj=new ArrayList<Object>();
			complianceListObj.add(compliancePercentageValue);
			complianceListObj.add(lastFilledDateObjValue);
			 
		}
		return complianceListObj;
	}
	
	
	
	
	/**
	 * Commented By Nagaraj on 03/Feb/2015 as new compliance calculation done as per the #1078 document
	 * 
	 */
	
	/*
	 * find formulary symbol per medicine using insurance id and formulary detail
	 * @author: Gopal krishna jha from lumbini..
	 */
	/*
			private List<Object> findComplianceSymbol(double drugId, String quantity,
					String directions, int reffils, Date startDate,
					List<PharmacyDetail> pharmacyDetailList, List<SigCode> sigCodeList) {
				
				logger.info(drugId+"findComplianceSymbolForTest method::::::::::"+directions+"::::startDate"+startDate);
				
				
				
				startDate=new DateUtil().changeDateTimeToDate(startDate,"yyyy-MM-dd");
				//startDate=new DateUtil().AddDate(startDate,0);
				logger.info("start date:::"+startDate);
				List<Object> listOfObject=new ArrayList<Object>();
				//String   pharmacyName= "";
				Date lastfilldate = null;
				for(PharmacyDetail pharmacyDetail:pharmacyDetailList)
				{
					if(pharmacyDetail.getDrugId()==drugId)
					{
					lastfilldate=pharmacyDetail.getDateOfPurchase();
					//break;
					}
				}
				
				boolean checkMeds=false;
				int percentage = 0;
				if(pharmacyDetailList.isEmpty())
				{
				
						percentage=0;
				}
				else
				{
					if(!(quantity==null || quantity.length()==0))
									
						{
									
											int noOfMedicineGivenByDoctor=0,usingDirection;
											noOfMedicineGivenByDoctor=(int)( (new Date().getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24) );
											if(!(directions==null))
											{
											//usingDirection=Integer.parseInt(directions.substring(0, directions.indexOf(" ")));
											usingDirection=findHowManyTimeTakeMedicine(directions, sigCodeList);
											}
											else
											{
												usingDirection=1;
											}
											Date endDateOfMedicine=null,expectedEndDateOfMedicine=startDate;
											//System.out.println("::::::::::usingDirection:::"+usingDirection);
											int noOfMedicinePurchase = 0;
											//System.out.println("?<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+quantity);
											if(!(quantity==null))
												{
												quantity=quantity.replaceAll("[^\\d.]", "");
												noOfMedicineGivenByDoctor=Integer.parseInt(quantity);
												}
										//	numberOfDaysMedicine=noOfMedicineGivenByDoctor/usingDirection;
											//endDateOfMedicine=new DateUtil().AddDate(startDate, numberOfDaysMedicine);
											if(!(reffils==0))
											{
												noOfMedicineGivenByDoctor=noOfMedicineGivenByDoctor*reffils;
											}
											//commented due to deepti changes date taking today date
											//endDateOfMedicine=new DateUtil().AddDate(startDate, noOfMedicineGivenByDoctor/usingDirection);
											endDateOfMedicine=new Date();
											System.out.println(noOfMedicineGivenByDoctor+"::::::endDateOfMedicine:"+endDateOfMedicine);
									Date dateOfPurchase=new Date();
									int noOfMedicinePurchaseLastTime = 0,noOfDaysMiss=0;
									for(PharmacyDetail pharmacyDetail:pharmacyDetailList)
									{
										
										
										if(pharmacyDetail.getDrugId()==drugId)
										{
											System.out.println("startDate::::::"+startDate+":::::pharmacyDetail.getDateOfPurchase()::::::"+pharmacyDetail.getDateOfPurchase()+"endDateOfMedicine:::"+endDateOfMedicine);
											System.out.println(pharmacyDetail.getDateOfPurchase().before(endDateOfMedicine));
											System.out.println(pharmacyDetail.getDateOfPurchase().equals(startDate));
											System.out.println(startDate.getTime()==pharmacyDetail.getDateOfPurchase().getTime());
											System.out.println(pharmacyDetail.getDateOfPurchase().after(startDate) || pharmacyDetail.getDateOfPurchase().equals(startDate));
											
											if(((pharmacyDetail.getDateOfPurchase().after(startDate) || startDate.getTime()==pharmacyDetail.getDateOfPurchase().getTime()) &&
													(pharmacyDetail.getDateOfPurchase().before(endDateOfMedicine))))
										
												{
												int quantityOfPurchase=pharmacyDetail.getQuantity();
												 dateOfPurchase=pharmacyDetail.getDateOfPurchase();
												 checkMeds=true;
												System.out.println(dateOfPurchase+":::::::quantityOfPurchase:::"+quantityOfPurchase);
												System.out.println("::::::expectedEndDateOfMedicine:::"+expectedEndDateOfMedicine+"::::::>>>>>>>>dateOfPurchase::"+dateOfPurchase);
												//noOfMedicinePurchaseLastTime=Integer.parseInt(quantityOfPurchase);
												//noOfMedicinePurchase=noOfMedicinePurchase+Integer.parseInt(quantityOfPurchase);
												//pharmacyName=pharmacyDetail.getPharmacyName();
												int differenceOfDate=0;
												if(dateOfPurchase.after(expectedEndDateOfMedicine) || dateOfPurchase.equals(expectedEndDateOfMedicine) )
													
												{
												
												System.out.println(":::!!!!!!dateOfPurchase:::"+dateOfPurchase+"::::expectedEndDateOfMedicine:::"+expectedEndDateOfMedicine);
												
												 differenceOfDate=(int)( (dateOfPurchase.getTime() - expectedEndDateOfMedicine.getTime()) / (1000 * 60 * 60 * 24) );
												System.out.println(":::::::::::::::11differenceOfDate::"+differenceOfDate);
												noOfDaysMiss=noOfDaysMiss+differenceOfDate;
												}
												System.out.println("*********::::::::noOfDaysMiss=="+noOfDaysMiss);
												if(differenceOfDate >0)
												{
													int noOfdays=quantityOfPurchase/usingDirection;
													System.out.println("::::::::::::::::::noOfdays:::"+noOfdays);
													expectedEndDateOfMedicine=new DateUtil().AddDate(dateOfPurchase,noOfdays );
													System.out.println(usingDirection+":::::::>>>>>dateOfPurchase:"+dateOfPurchase+"::expectedEndDateOfMedicine:"+expectedEndDateOfMedicine);
												}
												else
												{
													expectedEndDateOfMedicine=new DateUtil().AddDate(expectedEndDateOfMedicine, quantityOfPurchase/usingDirection);
												}
												
												if(endDateOfMedicine.after(new Date()))
												{
													expectedEndDateOfMedicine=new Date();
													endDateOfMedicine=new Date();
													System.out
															.println("inside this condition...."+expectedEndDateOfMedicine);
													noOfMedicineGivenByDoctor=(int)( (new Date().getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24) );
												}
												
												System.out.println("endDateOfMedicine::"+endDateOfMedicine+"::::::::expectedEndDateOfMedicine=="+expectedEndDateOfMedicine+":::::::noOfDaysMiss:::"+noOfDaysMiss);
														
												}
											}
										}
									
									if(checkMeds)
									{
									
									int noOfRemainingDay=(int)( (endDateOfMedicine.getTime() - expectedEndDateOfMedicine.getTime()) / (1000 * 60 * 60 * 24) );
											
									if(noOfRemainingDay>=1)
									{
										noOfDaysMiss=noOfDaysMiss+noOfRemainingDay;
									}
									System.out.println("::::::noOfRemainingDay:::"+noOfRemainingDay+":::noOfDaysMiss:"+noOfDaysMiss);
									if(noOfDaysMiss>=1)
									{
										//noOfMedicineGivenByDoctor=noOfMedicineGivenByDoctor-noOfDaysMiss*usingDirection;
										System.out.println(":::noOfMedicineGivenByDoctor::"+noOfMedicineGivenByDoctor+":::"+(noOfMedicineGivenByDoctor-noOfDaysMiss*usingDirection));
										percentage=(noOfMedicineGivenByDoctor-noOfDaysMiss*usingDirection)*100/noOfMedicineGivenByDoctor;
									}
									
									else 
										percentage=100;
									}
									else
										percentage=0;
									
									
							}
				
						
					}
				System.out.println("******::::::::::::percentage:::"+percentage);
				listOfObject.add(lastfilldate);
				listOfObject.add(percentage);
				
				return listOfObject;
				}

			
			 * find how many time take medicine.....
			 * @author GOpal Krishna jha
			 
	public int findHowManyTimeTakeMedicine(String directions,List<SigCode> sigCodeList) {
		int noOfTime=0;
		System.out.println("::::::::directions:::"+directions);
			
		for(SigCode sigCode:sigCodeList)
		{
			if(sigCode.getSigCode().equals(directions))
				{
				if(sigCode.isMorning())
					noOfTime++;
				if(sigCode.isNoon())
					noOfTime++;
				if(sigCode.isEvening())
					noOfTime++;
				if(sigCode.isBedTime())
					noOfTime++;
				if(sigCode.isSpecialCase())
					noOfTime++;
				}
		}
		if(noOfTime==0)
			noOfTime=1;
		return noOfTime;
	}*/

	/*
	 * find formulary symbol per medicine using insurance id and formulary detail
	 * @author: Gopal krishna jha from lumbini..
	 */
	public String findFormularySymbol(int insuranceId,
			 String medicineName) {
		
		logger.info("findFormularySymbol method::::insuranceId=="+insuranceId);
		//List<FormularyDetail> formularyCoverageDetailList=new ArrayList<FormularyDetail>();
		List<FormularyCoverageDetail> formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>();
		String formularyTier="";
		
		FacesContext context = FacesContext	.getCurrentInstance();
		UserManageBean userManageBean=(UserManageBean)context.getApplication().getELResolver().getValue(context.getELContext(), null,"userManageBean");
		
	    try {
	    	
			formularyCoverageDetailList=formulary1WsBean.getFormularyCoverageForComplianceSymbol(insuranceId, medicineName,userManageBean.getProviderLocation(),userManageBean.getRoleSecurity());
	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 logger.info("Patient Medication service Impl formularyCoverageDetailList::::::::::::;;"+formularyCoverageDetailList.size());
		for(FormularyCoverageDetail formularyCoverageDetail:formularyCoverageDetailList)
		{
				 
				formularyTier=	formularyCoverageDetail.getFormularyStatus();
			logger.info("Patient Medication service Impl "+formularyTier);
			
		}
		
		return formularyTier;
	}

	//Method to fetch Medication History of particular Medicine for particular patient
	public List<PatientMedicationHistory> fetchMedicationHistoryList(
			int patientId, String medicineName) {
		return patientMedicineDAO.fetchMedicationHistory(patientId,medicineName);
	}


	public List<PatientMedicationData> getPatientMedicationeData(int patientId) {
		return patientMedicineDAO.showPatientMedicationeData(patientId);
	}

	public List<PatientMedicationHistory> getPatientMedicationeHistoryData(
			String drugs) {
		return patientMedicineDAO.showPatientMedicationeHistoryData(drugs);
	}

	public void savePatientMedicationNotes(PatientMedicineNotes patientMedicineNotes) {
		
		patientMedicineDAO.savePatientMedicationNotes(patientMedicineNotes);
	}

	public List<PatientMedicineNotes> getMedicineNotes() {

		return patientMedicineDAO.getMedicineNotes();
	}

	//Method to fetch Medicine list for Drug Price optimizer Modified by Gopal Krishna jha at Lumbini Innovations
	public List<FormularyDetail> fetchFormularyMedList(List<String> medicineList, int insuranceId) {
		return patientMedicineDAO.fetchForumlarMedList(medicineList, insuranceId);
	}

	//Method to fetch Medicine list for My formulary Look up by LI-0022 at Lumbini Innovations
	public List<FormularyDetail> fetchMyFormularyMedList(int patientId) {
		return patientMedicineDAO.fetchMyFormularyMedList(patientId);
	}

	/*Method to Optimize Drug Price for Doctor Profile based on selected member and medicine
	by LI-0011 at Lumbini Innovations*/
	public List<FormularyDetail> optimizeDrugPrice(int patientId, int insuranceId, FormularyDetail[] selectedFDetel,String otherMed) {
		return patientMedicineDAO.optimizeDrugPrice(patientId,insuranceId,selectedFDetel,otherMed);
	}

	//Method to look up my formulary for looged in patient by LI-0011 at Lumbini Innovations
	/*public List<FormularyDetail> lookUpMyFormulary(int loginId,	FormularyDetail[] selectedFormulary,String otherMed) {
		return patientMedicineDAO.lookUpMyFormulary(loginId,selectedFormulary,otherMed);
	}*/

	public List<PatientMedicationData> findpatientMedicationSummaryList(int patientId) {
		return patientMedicineDAO.findpatientMedicationSummaryList(patientId);
	}

	public List<PharmacogenomicsRecomendations> getRecommendedFutureMedicine(int patientId) {
		logger.info("=========inside getRecommendedFutureMedicine=in patientMedicine service============");
		List<PharmacogenomicsRecomendations> impectedFutureMedicineList=new ArrayList<PharmacogenomicsRecomendations>();
		List<PharmacogenomicsRecomendations> impectedFutureMedicineList2=new ArrayList<PharmacogenomicsRecomendations>();
		impectedFutureMedicineList= patientMedicineDAO.showRecommendedFutureMedicine(patientId);
		if (currentImpactedMedicineList.size()>0) {
		for (PharmacogenomicsRecomendations pr : impectedFutureMedicineList) {
			logger.info("inside impectedFutureMedicineList====drug==="+pr.getDrugName());
			for (String drug : currentImpactedMedicineList) {
				logger.info("inside currentImpactedMedicineList====drug==="+pr.getDrugName()+"::/::"+pr.getDrugName().trim()+"="+drug);
				if((drug).equalsIgnoreCase(pr.getDrugName().trim()))
				{
					logger.info("inside if block====="+pr.getDrugName());
					impectedFutureMedicineList2.add(pr);
					break;
				}
			}
		  }
		}
		impectedFutureMedicineList.removeAll(impectedFutureMedicineList2);
		logger.info(":::::::::::size after:::"+impectedFutureMedicineList.size());
		return impectedFutureMedicineList;
	}


	public List<String> findAlternativeMedicine(String drugs, int insuranceId) {
		return patientMedicineDAO.findAlternativeMedicine(drugs,insuranceId);
	}
	

/*
 * ********************************method to find impacted medicine for Pharmacogenomics
 * @see com.clinakos.service.IPatientMedicineService#findImpactedMedication(int)
 */
	public List<PharmacogenomicsRecomendations> findImpactedMedication(int patientId) {
		currentImpactedMedicineList=new ArrayList<String>();
		logger.info("findImpactedMedication in service layer:::::::::"+currentImpactedMedicineList.size());
		List<PharmacogenomicsRecomendations> impectedCurrentMedicineList=new ArrayList<PharmacogenomicsRecomendations>();
		impectedCurrentMedicineList= patientMedicineDAO.findImpactedMedication(patientId);
		for (PharmacogenomicsRecomendations pr : impectedCurrentMedicineList) {
			logger.info("impectedCurrentMedicineList======"+pr.getDrugForCompareWithFutureImpactedMedicine());
			currentImpactedMedicineList.add(pr.getDrugForCompareWithFutureImpactedMedicine().trim());
		}
		return impectedCurrentMedicineList;
	}

	public List<ClinicQuestionnaire> findClinicQuestionList(int clinicProviderId) {
		return patientMedicineDAO.findClinicQuestionList(clinicProviderId);
	}

	public List<ClinicDiagonsis> findClinicDiagnosisList(String MedicineNAme) {
		return patientMedicineDAO.findClinicDiagnosisList(MedicineNAme);
	}

	public List<String> findLabFrequencyRange(String labName) {
		return patientMedicineDAO.findLabFrequencyRange(labName);
	}


	public List<String> findRegimenList() {
		return patientMedicineDAO.findRegimenList();
	}

	
	public List<PatientMedicationHistory> findMedicineHistoryForAnticoag(
			int patientId) {
		return patientMedicineDAO.findMedicineHistoryForAnticoag(patientId);
	}

	public void saveMedDActionPlan(List<MedActionPlan> medicalActionPlanList, VisitHistory visitHistory, 
			List<PatientMedicationData> addNewmedicationListForPatient,List<PatientMedicationData> removeDuplicateModifyMedicationListForPatient,
			List<Integer> careTeamMemberListForReconcile) {
		patientMedicineDAO.saveMedDActionPlan(medicalActionPlanList,visitHistory,addNewmedicationListForPatient,
				removeDuplicateModifyMedicationListForPatient,  careTeamMemberListForReconcile);
	}

	/**
	 * @return the labDAO
	 */
	public ILabDAO getLabDAO() {
		return labDAO;
	}

	/**
	 * @param labDAO the labDAO to set
	 */
	public void setLabDAO(ILabDAO labDAO) {
		this.labDAO = labDAO;
	}

	public List<Medicine> findAnticoagMedList(String clinicName) {
		return patientMedicineDAO.findAnticoagMedList(clinicName);
	}

	public VisitHistory findVisitHistory(int patientId, int clinicProviderId,
			int loginId) {
		return patientMedicineDAO.findVisitHistory(patientId,clinicProviderId,loginId);
	}

	public List<Medicine> findMedicineList() {
		return patientMedicineDAO.findMedicineList();
	}

/*
 * **************************method to find medicine reminder for patient
 * @see com.clinakos.service.IPatientMedicineService#findRemindersForMedicine()
 * Modified By Anjani 
 */
	public List<PatientMedicationData> findRemindersForMedicine(int loginId, List<SigCode> sigCodeList,
			List<PatientMedicationData> medicationListForPatient) 
		{
			List<PatientMedicationData>patientMedicineReminderList=new ArrayList<PatientMedicationData>();
			List<PharmacyDetail>pharmacyDetailList=new ArrayList<PharmacyDetail>();
			pharmacyDetailList=patientMedicineDAO.findPharmacyDetailListAccordingToPatirnt(loginId);
			int sigCodeValue = 0;
			try 
			{
				for(PatientMedicationData pat:medicationListForPatient)
				{
					logger.info("::::pat.id"+pat.getId());
				if(pat.getReffils()>=1 )
				{
					if(pharmacyDetailList.size()>0){
						for(PharmacyDetail pharmacyDetail:pharmacyDetailList)
						{
							//commented by gopal
							/*if(true)*/
							if(pat.getDrugId()==pharmacyDetail.getDrugId())
							//if(pat.getId()==pharmacyDetail.getPatientMedicationId())
							{
								if(!(pharmacyDetail.getDateOfPurchase()==null)){
									pat.setDrugPurchaseDate(pharmacyDetail.getDateOfPurchase());
								}
								else {
									pat.setDrugPurchaseDate(pat.getStartDate());
								}
								
								for (SigCode sig : sigCodeList) 
									{
										if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && sig.isSpecialCase()==false) 
										 {
												sigCodeValue=(sig.isMorning()? 1:0)+(sig.isNoon()? 1:0)+(sig.isEvening()? 1:0)+(sig.isBedTime()? 1:0);
												pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
												logger.info("inside SpecialCase false block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
										 }
										if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && pat.getDirections().equals("qH")) 
										 {
												sigCodeValue=24;
												pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
												logger.info("inside SpecialCase qH block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
											}
										if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && pat.getDirections().equals("q4-6H prn")) 
										 {
												sigCodeValue=6;
												pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
												logger.info("inside SpecialCase q4-6H prn block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
											}
											
										 }
								pat.setDateOfNextReffil(new DateUtil().AddDate(pat.getDrugPurchaseDate(), pat.getDaysInOneReffil()));
								logger.info("value of setDaysInOneReffil::::"+pat.getDaysInOneReffil()+"value of setDateOfNextReffil:::"+pat.getDateOfNextReffil());
						
							}
							else
							{
								logger.info("::::pharmacyDetailList has no column match pharmacyDetail.id::::"+pharmacyDetail.getId());
							}
						 }
					}
					else{
						pat.setDrugPurchaseDate(pat.getStartDate());
						for (SigCode sig : sigCodeList) 
						{
							if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && sig.isSpecialCase()==false) 
							 {
									sigCodeValue=(sig.isMorning()? 1:0)+(sig.isNoon()? 1:0)+(sig.isEvening()? 1:0)+(sig.isBedTime()? 1:0);
									pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
									logger.info("inside SpecialCase false block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
							 }
							if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && pat.getDirections().equals("qH")) 
							 {
									sigCodeValue=24;
									pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
									logger.info("inside SpecialCase qH block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
								}
							if (pat.getDirections().equalsIgnoreCase(sig.getSigCode()) && pat.getDirections().equals("q4-6H prn")) 
							 {
									sigCodeValue=6;
									pat.setDaysInOneReffil(Integer.valueOf(pat.getQuantity())/sigCodeValue);
									logger.info("inside SpecialCase q4-6H prn block:::"+sigCodeValue+"::pat. DaysInOneReffil::"+pat.getDaysInOneReffil());
								}
								
							 }
					pat.setDateOfNextReffil(new DateUtil().AddDate(pat.getDrugPurchaseDate(), pat.getDaysInOneReffil()));
					logger.info("In else condition  without pharmacy detail value of setDaysInOneReffil::::"+pat.getDaysInOneReffil()+"value of setDateOfNextReffil:::"+pat.getDateOfNextReffil());
						
					}
					
					/*if(pharmacyDetailList.isEmpty())
					{
						logger.info("::::pharmacyDetailList is empty");
						pat.setDateOfNextReffil(new Date());
						patientMedicineReminderList.add(pat);
						
					}*/
					patientMedicineReminderList.add(pat);
				
				  }
				}
			} 
			catch (Exception e) {
			  logger.error("Exception in findRemindersForMedicine @ Service layer::::", e);
			  e.printStackTrace();
			}
	
		return patientMedicineReminderList;
	}
	
	

/*
 * *******Method to find My Medication Goal
 * @see com.clinakos.service.IPatientMedicineService#findMyMedicationGoal(int)
 */
		public List<MedActionPlan> findMyMedicationGoal(int loginId) {

			return patientMedicineDAO.findMyMedicationGoal(loginId);
		}
       /**
        * @return List Sig Code 
        * @see com.clinakos.service.IPatientMedicineService#findSigCodeList()
        */
		
		public List<SigCode> findSigCodeList() {
			return patientMedicineDAO.findSigCodeList();
		}

		public PatientVital findPatientDetail(int patientId) {
			return patientMedicineDAO.findPatientDetail(patientId);
		}

		public List<PharmacogenomicsUserSummary> getPharmacogenomicsUserSummaryList(
				int patientId) {
			return patientMedicineDAO.getPharmacogenomicsUserSummaryList(patientId);
		}

		
		public List<FormularyDetail> findDrugsPriceOPtimizer(
				FormularyDetail[] selectedFDetel, int insuranceId) {
			return patientMedicineDAO.findDrugsPriceOPtimizer(selectedFDetel,insuranceId);
		}

		public List<FormularyDetail> findDrugsPriceOPtimizerForOtherMeds(
				String otherMed, int insuranceId) {
			return patientMedicineDAO.findDrugsPriceOPtimizerForOtherMeds(otherMed,insuranceId);
		}
      /**
       * @param patientId
       * @param providerId
       * @return List of MedActionPlan
       */
		public List<MedActionPlan> findMedActionPlanForPatient(int patientId,
				int providerId) {
			return patientMedicineDAO.findMedActionPlanForPatient(patientId,providerId);
		}
		
		public List<MedActionPlan> findMedActionPlanForPatientforantiocag(int patientId,
				int providerId) {
			return patientMedicineDAO.findMedActionPlanForPatientforanticaog(patientId,providerId);
		}
		
		public List<MedandGenricmed> findgenricandnongenricMedActionPlanForPatient(int patientId,
				int providerId) {
			return patientMedicineDAO.findgenricandnongenricMedActionPlanForPatient(patientId,providerId);
		}

		public void updateSelectedImage(
				PatientMedicationData selectedRowIdOfPatientMedicationData) {
			patientMedicineDAO.updateSelectedImage(selectedRowIdOfPatientMedicationData);
			
		}

		
		public List<PatientMedicationHistory> findIndiviualDose(int patientId,
				int providerId) {
			return patientMedicineDAO.findIndiviualDose(patientId,providerId);
		}

		public Date findAnticoagClinicStartDate(List<Medicine> anicoagMedList,
				int patientId, int providerId) {
			return patientMedicineDAO.findAnticoagClinicStartDate(anicoagMedList,patientId,providerId);
		}

		public Date findAnticoagDate(List<Medicine> anicoagMedList,
				int patientId, int providerId) {
			return patientMedicineDAO.findAnticoagDate(anicoagMedList,patientId);
		}
		/*
		 * find last reconcile date of particular patient.....
		 * (non-Javadoc)
		 * @see com.clinakos.service.IPatientMedicineService#findLastReconcileDate(int, int)
		 */
		public List<String> findLastReconcileDate(int patientId, int providerId) {
			return patientMedicineDAO.findLastReconcileDate(patientId,providerId);
		}

		public UserLoginDetail findDoctorDetail(int loginId) {
			return patientMedicineDAO.findDoctorDetail(loginId);
		}

		
		public List<WSDrug> findSearchMedicineDetailIst(String drugs) {
			return patientMedicineDAO.findSearchMedicineDetailIst(drugs);
		}

		public List<WSDrug> findDoseListOfParticularMeds(Double drugNameId,String dosageForm) {
			
			return patientMedicineDAO.findDoseListOfParticularMeds(drugNameId, dosageForm);
		}

		public List<ClinicSubdiagnosis> findClinicSubDiagnosisList(
				int clinicDiagnosisId) {
			return patientMedicineDAO.findClinicSubDiagnosisList(clinicDiagnosisId);
		}

		/**
		 * Find Allergy From Master Allergy Data 
		 * @param allergyNameForAddingNew passed value for Search Allergy 
		 * @return Allergy Master Data List 
		 * @see com.clinakos.service.IPatientMedicineService#findMasterAllergyData(String)
		 */
		public List<AllergyMaster> findMasterAllergyData(String allergyNameForAddingNew ) {
			return patientMedicineDAO.findMasterAllergyData(allergyNameForAddingNew);
			
			
		}

		/**
		 * @param Patient Id 
		 * @return Patient Allergy Data List 
		 * @see com.clinakos.service.IPatientMedicineService#getAllPatientAllergy(int)
		 */
		public List<PatientAllergy> getAllPatientAllergy(int patientId) {
			return patientMedicineDAO.getAllPatientAllergy(patientId);
		}

		
		public List<RouteDetails> findRouteNameDetailList() {
			return patientMedicineDAO.findRouteNameDetailList();
		}

		
		public List<UnitDetails> findUnitNameDetailsList() {
			return patientMedicineDAO.findUnitNameDetailsList();
		}

		
		public List<DosageFrom> findDosageNameDetailsList() {
			return patientMedicineDAO.findDosageNameDetailsList();
		}

		/**
		 * Save Patient Allergy Data 
		 * @param patientAllergy
		 * @see com.clinakos.service.IPatientMedicineService#savePatientAllergy(PatientAllergy)
		 */
		public void savePatientAllergy(PatientAllergy patientAllergy) {
			
			patientMedicineDAO.savePatientAllergy(patientAllergy);
		}

		/**
		 * Delete APtient Allergy Based on Selection 
		 * @param Patient Allergy 
		 * @see com.clinakos.service.IPatientMedicineService#deleteAllergyDetails(PatientAllergy)
		 */
		public void deleteAllergyDetails(PatientAllergy patientAllergy) {
			patientMedicineDAO.deleteAllergyDetails(patientAllergy);
		}

		
		public void allergyIntegrateFromNewCropToClinakos(
				List<PatientAllergy> patientAllergyListForIntegrate,int patientId) {
			patientMedicineDAO.allergyIntegrateFromNewCropToClinakos(patientAllergyListForIntegrate,patientId);
			
		}

	
		public void saveValueForIntegration(int patientId, boolean checkIntegration) {
			patientMedicineDAO.saveValueForIntegration(patientId,  checkIntegration);
			
		}

		public boolean findStatusOfIntegration(int patientId) {
			return patientMedicineDAO.findStatusOfIntegration(patientId);
		}

		
		public void medicineIntegrateFromNewCropToClinakos(
				List<PatientMedicationData> patientMedicationDataForIntegrate,
				int patientId,List<PatientMedicationData> patientMedicationDataList,List<Medicine> medicineListForCheckingMedActionPlan,
				List<MedActionPlan> medActionPlanList,List<ParentMedActionPlan> allGeneralMedPlans) {
			patientMedicineDAO.medicineIntegrateFromNewCropToClinakos(patientMedicationDataForIntegrate,patientId, patientMedicationDataList,
					medicineListForCheckingMedActionPlan, medActionPlanList,  allGeneralMedPlans);
			
		}

	
		public Double getDrugIdByDrugName(String medicineName) {
			return patientMedicineDAO.getDrugIdByDrugName(medicineName);
		}

		/**
		 * @return the formulary1WsBean
		 */
		public NcFormulary1WSBean getFormulary1WsBean() {
			return formulary1WsBean;
		}

		/**
		 * @param formulary1WsBean the formulary1WsBean to set
		 */
		public void setFormulary1WsBean(NcFormulary1WSBean formulary1WsBean) {
			this.formulary1WsBean = formulary1WsBean;
		}

		
		public List<MedUnitSummary> findUnitSummaryList() {
			return patientMedicineDAO.findUnitSummaryList();
		}

		public List<LabDetail> findLabDetailListAccordingToGenericname(
				String genericDrugs) {
			return patientMedicineDAO.findLabDetailListAccordingToGenericname(genericDrugs);
		}
        /**
         * 
         * @param PatientAllergy Data 
         * @param Allergy Detail Data For Edit 
         * @see com.clinakos.service.IPatientMedicineService#editAllergyDetails(PatientAllergy, String)
         */
		public void editAllergyDetails(PatientAllergy patientAllergy,String allergyDetailForEdit) {
			
			patientMedicineDAO. editAllergyDetails(patientAllergy,allergyDetailForEdit);
		}

		public void updatePharmacyDetailOfParticularUser(
				List<PharmacyDetail> pharmacyDetailListForUpdate,int patientId) {
			patientMedicineDAO.updatePharmacyDetailOfParticularUser(pharmacyDetailListForUpdate,patientId);
		}
		
		
		public void deleteMedicineChanges(
				PatientMedicationData changePatientMedicineBackUpdata) {
			patientMedicineDAO.deleteMedicineChanges(changePatientMedicineBackUpdata);
		}

		public void integrateDrugInteractionToDatabase(
				List<DrugInteraction> drugInteractionListData) {
			patientMedicineDAO.integrateDrugInteractionToDatabase(drugInteractionListData);
		}

		
		public List<PatientDrugMapping> getAllUniqueDrugRecords() {
			return patientMedicineDAO.getAllUniqueDrugRecords();
		}

		
		public void ignoreSelectedDrugInDatabase(
				DrugDrugInteractionData drugDrugInteractionData) {
			patientMedicineDAO.ignoreSelectedDrugInDatabase(drugDrugInteractionData); 	
		}

		
		public void integrateDrugAllergyInteractionToDatabase(
				List<DrugAllergyDetail> drugAllergyDetailList) {
			patientMedicineDAO.integrateDrugAllergyInteractionToDatabase(drugAllergyDetailList);
		}

		
		public List<PatientDrugAllergyInteraction> getAllUniquePatientDrugAllergyRecords() {			
			return patientMedicineDAO.getAllUniquePatientDrugAllergyRecords();
		}


	
		public void saveMedicineDetail(
				PatientMedicationData patientMedicationData) {
			 patientMedicineDAO.saveMedicineDetail(patientMedicationData);
			
		}
		public void deleteParticularMedactionPlan(MedActionPlan medActionPlan) {
			patientMedicineDAO.deleteParticularMedactionPlan(medActionPlan);			
		}


		public void modifyMedicineChange(
				PatientMedicationData CurrentpatientMedicationData,
				PatientMedicationData changePatientMedicineBackUpdata,String deleteMedActionPlanOrUpdate) {
			patientMedicineDAO.modifyMedicineChange(CurrentpatientMedicationData,changePatientMedicineBackUpdata,deleteMedActionPlanOrUpdate);
		}

		
		
		public void saveMedActionPlan(
				List<MedActionPlan> medactionplanListForParticularMedicine) {
			patientMedicineDAO.saveMedActionPlan(medactionplanListForParticularMedicine);
		}
		
	
		public void ignoreSelectedDrugAllergy(
				MasterDrugAllergyInteraction masterDrugAllergyInteraction) {
			patientMedicineDAO.ignoreSelectedDrugAllergy(masterDrugAllergyInteraction);
		}

		
		public List<PatientDrugDiseaseInteraction> getAllUniquePatientDrugDiseaseRecords() {
			
			return patientMedicineDAO.getAllUniquePatientDrugDiseaseRecords();
		}

		
		public void integrateDrugDiseaseInteractionToDatabase(
				List<DrugDiseaseDetail> drugDiseaseListData) {
			patientMedicineDAO.integrateDrugDiseaseInteractionToDatabase(drugDiseaseListData);
			
		}

		
		public void ignoreSelectedDrugDisease(
				MasterDrugDiseaseInteraction masterDrugDiseaseInteraction){
			patientMedicineDAO.ignoreSelectedDrugDisease(masterDrugDiseaseInteraction);
			
		}




		
		public void integratePharmcogenomicsCurrentMedicineToDb(
				List<PharmacogenomicsRecomendations> pharmacogenomicsRecomendationsListForImpactedMedicine) {
			patientMedicineDAO.integratePharmcogenomicsCurrentMedicineToDb(pharmacogenomicsRecomendationsListForImpactedMedicine);
		}

	
		public List<PatientPharmacogenomicsCurrentMedicineData> getAllRecordsFromPharmcogenics(boolean status) {
			
			return patientMedicineDAO.getAllRecordsFromPharmcogenics(status);
		}


		public WSDrug findFullDrugDetailAccordingToThereName(
				String drugDetail) {
			
			return patientMedicineDAO.findFullDrugDetailAccordingToThereName(drugDetail);
		}

		
		public void ignoreSelectedPharmcogenomics(
				PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCurrentMedicineData) {
			patientMedicineDAO.ignoreSelectedPharmcogenomics(patientPharmacogenomicsCurrentMedicineData);
		}

		public List<PatientMedicationData> findpatientDiscontinueMedicineListOfParticularVisit(
				int patientId, int providerId,Date selectedEncounterDateForPrint) {
			return patientMedicineDAO.findpatientDiscontinueMedicineListOfParticularVisit(patientId,providerId,selectedEncounterDateForPrint);
		}

		public void endingVisitOfParticularPatient(int patientId, int providerId) {
			patientMedicineDAO.endingVisitOfParticularPatient(patientId,providerId);
		}
		
		public List<WSDrug> findDoseListOfParticularMedsNotStrength(
				double drugNameId, String dosageForm) {
			// TODO Auto-generated method stub
			return patientMedicineDAO.findDoseListOfParticularMedsNotStrength(drugNameId,dosageForm);
		}


		
		public List<DrugDrugInteractionData> filterDrugInteractions(String s) {
			return patientMedicineDAO.filterDrugInteractions(s);
		}

		public WSDrug findDrugDetailAccordingToDrugId(double drugId) {
			// TODO Auto-generated method stub
			return patientMedicineDAO.findDrugDetailAccordingToDrugId(drugId);
		}

		public List<PatientMedicationHistory> findFullPatientMedicationHistoryData(
				int patientId, int providerId) {
			// TODO Auto-generated method stub
			return patientMedicineDAO.findFullPatientMedicationHistoryData(patientId,providerId);
		}

		public void updatMedActionPlanOfParticularPatient(
				MedActionPlan medActionPlan) {
			patientMedicineDAO.updatMedActionPlanOfParticularPatient(medActionPlan);
			
		}

		public void updateMedActionPlan(
				PatientMedicationData patientMedicationData) {
			patientMedicineDAO.updateMedActionPlan(patientMedicationData);
			
		}

		
		public List<MasterLOINCData> searchLabInLoincMaster(String loincNumber) {
			return patientMedicineDAO.searchLabInLoincMaster(loincNumber);
		}

		
		public boolean saveGenericMedActionPlan(int patientId,
				List<GenericMedActionPlan> genericMedActionPlanList, List<GenericMedActionPlan> temporaryGenericMedPlanList,Date dosingStartDate) {
			return patientMedicineDAO.saveGenericMedActionPlan(patientId,genericMedActionPlanList,temporaryGenericMedPlanList,dosingStartDate);	
		}

		
		public List<ParentMedActionPlan> getAllGenericMedActionPlan(
				int patientId) { 
			return patientMedicineDAO.getAllGenericMedActionPlan(patientId);
		}


		public void updateTakeActionPlan(MedActionPlan medActionPlan,
				WeeklyDose weeklyDose) {
			patientMedicineDAO.updateTakeActionPlan(medActionPlan,weeklyDose);
			
		}

		public WeeklyDose findWeeklyDoseOfParticularPatient(int patientId,
				int doctorId, double drugId) {
			// TODO Auto-generated method stub
			return patientMedicineDAO.findWeeklyDoseOfParticularPatient(patientId,doctorId,  drugId);
		}

		public List<WeeklyDose> findWeeklyDoseOfParticularPatientforanticoagmedicine(int loginid ,int providerid) {
			// TODO Auto-generated method stub
			return patientMedicineDAO.findWeeklyDoseOfParticularPatientforanticoagmedicine(loginid,providerid);
		}
		
		public void updateGenericMedActionPlan(
				GenericMedActionPlan genericMedActionPlan) {
		
			patientMedicineDAO.updateGenericMedActionPlan(genericMedActionPlan);
		}
	
		public void deleteGenericMedActionPlan(
				GenericMedActionPlan genericMedActionPlan) {
			patientMedicineDAO.deleteGenericMedActionPlan(genericMedActionPlan);
		}


		public List<PharmacogenomicsRecomendations> findPharmacogenomicsInteractionDuringAddMedicine(
				double drugNameId, String drugs, int patientId) {
			return patientMedicineDAO.findPharmacogenomicsInteractionDuringAddMedicine(drugNameId,drugs,patientId);
		}

		public void deletePatientMedicationData(double drugId, int patientId,
				int providerId,double drugNameId) {
			patientMedicineDAO.deletePatientMedicationData(drugId,patientId,providerId,drugNameId);		
		}

		
		public List<GenericMedActionPlan> allMedPlansForPatient(
				PatientMedicationData patientMedicationData, int patientID) {
			return patientMedicineDAO.allMedPlansForPatient(patientMedicationData,patientID);
		}

		/**
		 * Data of Alert Generic Med Action Plan Data 
		 * @param patientID
		 * @return List Of GenericMedActionPlan
		 * @see com.clinakos.service.IPatientMedicineService
		 */
		public List<AlertGenericMedActionLab> alertMedActionPlans(int patientID) {
			
			return patientMedicineDAO.alertMedActionPlans(patientID);
		}

		
		public void saveMessageDetials(SendMessageEditRx sendMessageEditRx) {
			// TODO Auto-generated method stub
			patientMedicineDAO.saveMessageDetials(sendMessageEditRx);
		}

		
		public List<SendMessageEditRx> getsendMessageEditRxList(int patientId) {
			
			return patientMedicineDAO.getsendMessageEditRxList(patientId);
		}

		
		public List<MasterMonitorParameters> getAllSubParameters(
				String monitoringParameter) {
			
			return patientMedicineDAO.getAllSubParameters(monitoringParameter);
		}

	
		public List<MedandGenricmed> getAllClinicMedPlans(int patientId) {
			
			return patientMedicineDAO.getAllClinicMedPlans(patientId);
		}
		
    public List<MedandGenricmed> fetchGenericList(int patientId) {
			
			return patientMedicineDAO.fetchGenericList(patientId);
		}
		 
   

	public List<String> findLabTypeList() {
	
		return patientMedicineDAO.findLabTypeList();
	}

	
	public ProcedureType getLabUnitBasedOnParameter(String childParameter) {
		
		return patientMedicineDAO.getLabUnitBasedOnParameter(childParameter);
	}

	public WSDrug getWSdrugObj(String dataProvider, double drugId,double drugNameIdVal) {
		return patientMedicineDAO.getWSdrugObj(dataProvider,drugId,drugNameIdVal);
	}


	public List<SendMessageEditRx> getSendMessageCurrentEditRxList(int patientId, Date todayDate) {
		
		return patientMedicineDAO.getSendMessageCurrentEditRxList(patientId,todayDate);
	}

	public List<PatientMedicationHistory> findDiscontinueMedicineListOfParticularVisit(
			int patientId, int providerId) {
		return patientMedicineDAO.findDiscontinueMedicineListOfParticularVisit(patientId,providerId);
	}

	
	public List<PatientMedicationData> findPatientMedicationDataListForPatient(
			int patientId) {
		return patientMedicineDAO. findPatientMedicationDataListForPatient(patientId);
	}


	public List<MedandGenricmed> findgenricandnongenricMedActionPlanPatient(
			int patientId, int providerId) {
		
	
			return patientMedicineDAO. findgenricandnongenricMedActionPlanPatient(patientId,providerId);
		}
	
	
	public List<PatientMedicationData> findMedicineForPatient(int patientId) {
	
		return  patientMedicineDAO.findMedicineForPatient(patientId);
	}
	
	
	
	
	public void saveMedicineDetailforEducation(
			PatientMedicineNotes patientMedicineNotes,EncounterSummary encounterSummary) {
		 patientMedicineDAO.saveMedicineDetailforEducation(patientMedicineNotes,encounterSummary);
		
	}

	public List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugId, int patientId) {
		return patientMedicineDAO.findPharmacyDetailHistoryListAccordingToParticularDrugId(drugId,patientId);
	}

	
	public List<PharmacyDetail> getPharmacyDetailData(int timePeriod,
			int patientId) {
		// TODO Auto-generated method stub
		return patientMedicineDAO.getPharmacyDetailData(timePeriod,patientId);
	}

/**
 * @see IPatientMedicineService#checkSpecialtyDrug(String)
 */
	public boolean checkSpecialtyDrug(String drugs) {
		
		return patientMedicineDAO.checkSpecialtyDrug(drugs);
	}

	@Override
	public String getPatientGender(int patientId) {
		return patientMedicineDAO.getPatientGender(patientId);
	}
	
	@Override
	public List<Entry<String, PsychopharmMessage>> getPsychopharm_step_1_data_UI(PatientVital patientVital) {
		logger.info("---------------------Firing for dosing guideline UI data and name from drools file -------------");
		decimalFormat.setDecimalSeparatorAlwaysShown(false);
		List<Map.Entry<String,PsychopharmMessage>>psychopharm_parameters;
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.MASTER_DATA);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		Map<String,PsychopharmMessage>psychopharm_parameters_data=new HashMap<String, PsychopharmMessage>();
		psychopharm_parameters_data=psychopharmMessage.getPsychopharmPayload();
		logger.info("drools response" +psychopharm_parameters_data.toString());
		
		//Get latest lab results and prepopulate according in psychopharm dosing guideline wizard Assembla #885. 
		List<ProcedureResultHistory> procedureResults = new ArrayList<ProcedureResultHistory>();
		procedureResults=labDAO.showProcedureResultData();
		
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_parameters_data.entrySet()) {
				for(ProcedureResultHistory procedureResultHistoryObj:procedureResults){
					if(StringUtils.lowerCase(entry.getKey()).equalsIgnoreCase(procedureResultHistoryObj.getLabName().toLowerCase())||
							StringUtils.lowerCase(procedureResultHistoryObj.getLabName()).contains(StringUtils.lowerCase(entry.getKey()))){
						
						
						SimpleDateFormat sm = new SimpleDateFormat("MM/dd/yyyy");
					    String strDate = sm.format(procedureResultHistoryObj.getDateResult());
					    
						entry.getValue().setLabDate(String.valueOf(strDate));;
						entry.getValue().setParamter_value(String.valueOf(decimalFormat.format(procedureResultHistoryObj.getResult())));
						break;
					}
				}
			/*logger.info("Key  "+entry.getKey());
			logger.info("parameter value "+entry.getValue().getParamter_value()+"Unit "+ entry.getValue().getParamter_unit());*/
		}
		
		if(patientVital.getBodyMassIndex()>0){
		
		psychopharm_parameters=loadBMIData(psychopharm_parameters_data,patientVital);
		}else{
		psychopharm_parameters=new ArrayList<Map.Entry<String,PsychopharmMessage>>(psychopharm_parameters_data.entrySet());
		}
			return psychopharm_parameters;
	}
	
	private List<Entry<String, PsychopharmMessage>> loadBMIData(
			Map<String, PsychopharmMessage> psychopharm_parameters_data,
			PatientVital patientVital) {
		logger.info("{--}{--}{--}Loading BMI data method fired{--}{--}{--}");
		List<Map.Entry<String,PsychopharmMessage>>psychopharmParametersData;
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.BMI);
		psychopharmMessage.setMonitoring_parameter_value(patientVital.getBodyMassIndex());
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		String parameterName=psychopharmMessage.getResponse();
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_parameters_data.entrySet()) {
				if(StringUtils.lowerCase(entry.getKey()).equalsIgnoreCase(parameterName.toLowerCase())){
					entry.getValue().setParamter_value(String.valueOf(patientVital.getBodyMassIndex()));
					break;
				}
	}
		psychopharmParametersData=new ArrayList<Map.Entry<String,PsychopharmMessage>>( psychopharm_parameters_data.entrySet());
		return psychopharmParametersData;
	}

	public PsychopharmMessage prepareRequestToDrools(){
		PsychopharmMessage psychopharmMessage=new PsychopharmMessage();
		psychopharmMessage.setMedicine(LITHIUM_MED_NAME);
		return psychopharmMessage;
	}

	@Override
	public List<ModelMessage> psychopharm_validateStep1(
			List<Entry<String, PsychopharmMessage>> psychopharm_parameters,String gender) {
		List<Message>messageList=new ArrayList<Message>();
		List<ModelMessage>modelMessageList=new ArrayList<ModelMessage>();
		List<String>responseList=new ArrayList<String>();
		try{
		for(Map.Entry<String, PsychopharmMessage>mapData:psychopharm_parameters) {
			if(StringUtils.isNotBlank(mapData.getValue().getParamter_value())){
				PsychopharmMessage prepareObject=prepareRequestToDrools();
				prepareObject.setRequest(Request.VALIDATE);
				prepareObject.setMonitoring_parameter_value(Double.valueOf(mapData.getValue().getParamter_value()));
				prepareObject.setMonitoring_paramater(mapData.getKey());
				prepareObject.setGender(gender.toLowerCase());
				messageList.add(prepareObject);
				RulesUtil.process(prepareObject);
			}	
		}
		RulesUtil.fireRuleToDroolEngine();
		for(Message mes:messageList){
			String response = mes.getResponse();
			responseList.add(response);
		}
		for(String response:responseList){
			if(!response.isEmpty()){
				ModelMessage message=new Gson().fromJson(response, ModelMessage.class);
				logger.info("Model message List size "+message.toString());
				modelMessageList.add(message);
				}	
		}	
		}catch(NumberFormatException nfe){
			logger.error("Error in converting string to double ");
			nfe.printStackTrace();
		}
		logger.info("Validated messages size shown to user "+modelMessageList.size());
		return modelMessageList;
	}

	@Override
	public List<ModelMessage> psychopharm_validateStep2(
			List<Entry<String, PsychopharmMessage>> psychopharm_parameters,
			PsychopharmModelData psychopharmModelDataObj,
			List<String> selectedContraindicatedMeds,
			List<String> selectedContraindicatedDiagnosis) {
		List<String>responseList=new ArrayList<String>();
		List<Message>messageList=new ArrayList<Message>();
		List<ModelMessage>modelMessageList=new ArrayList<ModelMessage>();
		try{
			if(psychopharmModelDataObj.isDrugScreenOption()){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setDrugScreenSelected(psychopharmModelDataObj.isDrugScreenOption());
				RulesUtil.process(message);
				messageList.add(message);
				}
			
			if(psychopharmModelDataObj.isPregnancyOption()){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setPregnancySelected(psychopharmModelDataObj.isPregnancyOption());
				RulesUtil.process(message);
				messageList.add(message);
				}
			if(selectedContraindicatedDiagnosis.size()>0){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setContraindicatedDiagnosis(selectedContraindicatedDiagnosis);
				message.setCurrent_step("2");
			
				RulesUtil.process(message);
				messageList.add(message);
			}
			if(selectedContraindicatedMeds.size()>0){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setContraindicatedMeds(selectedContraindicatedMeds);
				message.setCurrent_step("2");
				RulesUtil.process(message);
				messageList.add(message);
			}
			if(StringUtils.isNotBlank(psychopharmModelDataObj.getPhq9Scale())){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setPhq9Scale(Double.valueOf(psychopharmModelDataObj.getPhq9Scale()));
				RulesUtil.process(message);
				messageList.add(message);
			}
			if(StringUtils.isNotBlank(psychopharmModelDataObj.getAltmanManiaRatingScale())){
				PsychopharmMessage message=prepareRequestToDrools();
				message.setAltmanScale(Double.valueOf(psychopharmModelDataObj.getAltmanManiaRatingScale()));
				RulesUtil.process(message);
				messageList.add(message);
			}
			
				RulesUtil.fireRuleToDroolEngine();
				for(Message mes:messageList){
					String response = mes.getResponse();
					responseList.add(response);
				}
				for(String response:responseList){
					if(!response.isEmpty()){
						ModelMessage message=new Gson().fromJson(response, ModelMessage.class);
						logger.info("Model message List size "+message.toString());
						modelMessageList.add(message);
						}
					
				}
		}catch(NumberFormatException nfe){
			logger.error("Error in converting string to double ");
			nfe.printStackTrace();
		}
		return modelMessageList;
	}

	@Override
	public Map<String, Object> getDialogProperties() {
		 Map<String,Object> options = new HashMap<String, Object>();
	        options.put("modal", true);
	        options.put("draggable", true);
	        options.put("resizable", true);
	        options.put("contentHeight", "'100%'");
	        options.put("contentWidth", "'100%'");
	        options.put("height", "400");
	        options.put("width", "1000");
	        return options;
	}

	@Override
	public List<String> getContraindicatedMeds() {
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setContraindicatedMedicines(true);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		List<String>contraindicatedMedsList=new ArrayList<String>();
		contraindicatedMedsList=psychopharmMessage.getResponseList();
		return contraindicatedMedsList;
	}

	@Override
	public List<String> getContraindicatedDiagnosis() {
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setContraindicatedDiaognosis(true);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		List<String>contraindicatedDiagnosisList=new ArrayList<String>();
		contraindicatedDiagnosisList=psychopharmMessage.getResponseList();
		return contraindicatedDiagnosisList;
	}

	@Override
	public PsychopharmModelData getPsychopharmRecomendations(
			List<String> selectedContraindicatedDiagnosis,
			List<Entry<String, PsychopharmMessage>> psychopharm_parameters,Integer age) {
		String recomendationResponse=new String();
		PsychopharmModelData psychopharmModelData=new PsychopharmModelData();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setContraindicatedDiagnosis(selectedContraindicatedDiagnosis);
		psychopharmMessage.setRecommedationSystemMessage(true);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		String recommendedMessageResponse=psychopharmMessage.getResponse();
		logger.info(" recommendedMessageResponse for "+recommendedMessageResponse);
		if(StringUtils.isNotBlank(recommendedMessageResponse)){
			psychopharmModelData.setRecommendedDose(recommendedMessageResponse);
		}else{
			for(Map.Entry<String, PsychopharmMessage>mapData:psychopharm_parameters) {
				if(StringUtils.isNotBlank(mapData.getValue().getParamter_value()) && mapData.getKey().equalsIgnoreCase("CrCl")){
					PsychopharmMessage prepareObject=prepareRequestToDrools();
					prepareObject.setMonitoring_parameter_value(Double.valueOf(mapData.getValue().getParamter_value()));
					prepareObject.setMonitoring_paramater(mapData.getKey());
					prepareObject.setPatientAge(age);
					/*prepareObject.setRecommedationSystemMessage(true);*/
					prepareObject.setNext_step("3");
					RulesUtil.process(prepareObject);
					RulesUtil.fireRuleToDroolEngine();
					recomendationResponse=prepareObject.getResponse();
					break;
				}else if(StringUtils.isBlank(mapData.getValue().getParamter_value()) && mapData.getKey().equalsIgnoreCase("CrCl")){
					PsychopharmMessage prepareObject=prepareRequestToDrools();
					prepareObject.setMonitoring_parameter_value(0.0);
					prepareObject.setMonitoring_paramater(mapData.getKey());
					prepareObject.setPatientAge(age);
					/*prepareObject.setRecommedationSystemMessage(true);*/
					prepareObject.setNext_step("3");
					RulesUtil.process(prepareObject);
					RulesUtil.fireRuleToDroolEngine();
					recomendationResponse=prepareObject.getResponse();
					break;
				}
		}
			
			ModelMessage message=new Gson().fromJson(recomendationResponse, ModelMessage.class);
			if(message!=null){
			try{
			psychopharmModelData.setRecommendedDose((StringUtils.split(message.getRecomendationMessage(), ":"))[0]);
			psychopharmModelData.setStrengths((StringUtils.split(message.getRecomendationMessage(), ":"))[1]);
			}catch(IndexOutOfBoundsException e){
				logger.error("Error in spilt string in service while getting recomendation response from Drool Rules");
				e.printStackTrace();
			}
			}
		}
		return psychopharmModelData;
	}

	@Override
	public List<String> getPhase() {
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.PHASE);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		List<String>phaseList=psychopharmMessage.getResponseList();
		return phaseList;
	}

	
	/*
	 * check drug drug interaction for warfarin
	 * @author: Gopal
	 * (non-Javadoc)
	 * @param patientMedicationDataList
	 * @return List of DrugInteractionForWarfrain
	 * @see com.clinakos.service.IPatientMedicineService#findDrugInteractionForWarfarinList(java.util.List)
	 */
	public List<DrugInteractionForWarfarin> findDrugInteractionForWarfarinList(
			List<PatientMedicationData> patientMedicationDataList) {
		// TODO Auto-generated method stub
		return patientMedicineDAO.findDrugInteractionForWarfarinList(patientMedicationDataList);
	}
	@Override
	public List<GenericMedActionPlan> getDefaultParameters(String lithiumMedName,String genderOfPatient) {
		List<GenericMedActionPlan>defaultResponseList=new ArrayList<GenericMedActionPlan>();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.ACTION_PLAN);
		psychopharmMessage.setGender(genderOfPatient.toLowerCase());
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		List<MedPlan>respnseList=psychopharmMessage.getMedPlanDefaultSettings();
		for(MedPlan medPlan:respnseList){
			GenericMedActionPlan genericMedActionPlan=new GenericMedActionPlan();
			genericMedActionPlan.setMonitoringParameter(medPlan.getMonitoringParameter());
			genericMedActionPlan.setParameterShortName(medPlan.getParameterShortName());
			genericMedActionPlan.setLabUnit(medPlan.getLabUnit());
			genericMedActionPlan.setGoalRangeEndSymbol(medPlan.getGoalRangeEndSymbol());
			genericMedActionPlan.setGoalRangeEndValue(medPlan.getGoalRangeEndValue());
			genericMedActionPlan.setGoalRangeSymbol(medPlan.getGoalRangeSymbol());
			genericMedActionPlan.setGoalRangeValue(medPlan.getGoalRangeValue());
			genericMedActionPlan.setAlertMediumHighRangeSymbol(medPlan.getAlertMediumHighRangeSymbol());
			genericMedActionPlan.setAlertMediumHighRangeValue(medPlan.getAlertMediumHighRangeValue());
			genericMedActionPlan.setAlertMediumLowRangeSymbol(medPlan.getAlertMediumLowRangeSymbol());
			genericMedActionPlan.setAlertMediumLowRangeValue(medPlan.getAlertMediumLowRangeValue());
			genericMedActionPlan.setAlertSevereHighRangeSymbol(medPlan.getAlertSevereHighRangeSymbol());
			genericMedActionPlan.setAlertSevereHighRangeValue(medPlan.getAlertSevereHighRangeValue());
			genericMedActionPlan.setAlertSevereLowRangeSymbol(medPlan.getAlertSevereLowRangeSymbol());
			genericMedActionPlan.setAlertSevereLowRangeValue(medPlan.getAlertSevereLowRangeValue());
			defaultResponseList.add(genericMedActionPlan);
		}
		return defaultResponseList;
	}


/*
 * ***********method for calculating compliance for DEMO DB
 * @see com.clinakos.service.IPatientMedicineService#patientMedicineService()
 */
	public void complianceCalculationForDemo(List<SigCode> sigCodeList) {
		logger.info("===========complianceCalculationForDemo inside patientMedicineService=======================");
		List<Object> complianceResultList=new ArrayList<Object>();
		List<PharmacyDetail> pharmacyDetailListForComplianceCalculationInDemo= new ArrayList<PharmacyDetail>();
		List<PatientMedicationData> patientMedicationDataListForComplianceCalculationInDemo = new ArrayList<PatientMedicationData>();
		try {
			 patientMedicationDataListForComplianceCalculationInDemo=patientMedicineDAO.fetchAllPatientMedicationDataRecords();
			 double drugId = 0;
			 String quantity = null, directions = null;
			 int reffils = 0,patientId = 0,compliancePercentage;
			 Date startDate = null;
			 for (PatientMedicationData pmd : patientMedicationDataListForComplianceCalculationInDemo) {
				 logger.info("===========inside for each loop=======================");
					drugId=pmd.getDrugId();
					quantity=pmd.getQuantity();
					directions=pmd.getDirections();
					reffils=pmd.getReffils();
					startDate=pmd.getStartDate();
					
					if (patientId!=pmd.getPatientId()) { 
						patientId=pmd.getPatientId();
						logger.info("===========calling findPharmacyDetailListAccordingToPatirnt inside patientMedicineService======================="+patientId);
						pharmacyDetailListForComplianceCalculationInDemo=patientMedicineDAO.findPharmacyDetailListAccordingToPatirnt(patientId);
					}
					logger.info("===========calling findComplianceSymbol inside patientMedicineService=======================");
				/*    complianceResultList=findComplianceSymbol( drugId,  quantity,
						 directions,  reffils,  startDate, pharmacyDetailListForComplianceCalculationInDemo, sigCodeList);*/
					complianceResultList=complianceCalculationForDrug(drugId, pharmacyDetailListForComplianceCalculationInDemo);
					   if(!complianceResultList.isEmpty())
						{
						    compliancePercentage=(Integer) complianceResultList.get(0);
						   logger.info("===========inside IF block when complianceResultList is not empty======================="+compliancePercentage);
						}
						else{
							 compliancePercentage=50;
							logger.info("===========inside ELSE block when complianceResultList is empty======================="+compliancePercentage);
						}
					   patientMedicineDAO.updateCompliancePercentageForDemo(compliancePercentage,pmd.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("===========EXCEPTION IN complianceCalculationForDemo INSIDE PATIENTMEDICINESERVICE============",e);
		}
		
	}

	
	public List<GenericMedActionPlan> getPsychoPharmClinicMedActionPlanData(
			int patientId) {
		
		return patientMedicineDAO.getPsychoPharmClinicMedActionPlanData(patientId);
	}
	

	@Override
	public List<Entry<String, PsychopharmMessage>> getPsychopharm_Altman_mania_questions() {
		List<Map.Entry<String,PsychopharmMessage>>psychopharm_altman;
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.QUESTIONS);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		Map<String,PsychopharmMessage>psychopharm_altman_data =new LinkedHashMap<String,PsychopharmMessage>();;
		psychopharm_altman_data=psychopharmMessage.getPsychopharmPayload();
	/*	for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_altman_data.entrySet()) {
			
		logger.info("Key  "+entry.getKey());
		logger.info("Question List size "+entry.getValue().getResponeLinkedMapList());
		
		for(Map.Entry<String, String>entryQuestion:entry.getValue().getResponeLinkedMapList().entrySet()){
			logger.info("key inside object pair "+entryQuestion.getKey());
			logger.info("value inside object pair "+entryQuestion.getValue());
		}
	}*/
		psychopharm_altman=new ArrayList<Map.Entry<String,PsychopharmMessage>>(psychopharm_altman_data.entrySet());
		return psychopharm_altman;
	}



	public List<GenericMedActionPlan> getPsychoPharmMedActionChartDrugNameData(
			int patientId) {
		// TODO Auto-generated method stub
		return patientMedicineDAO.getPsychoPharmMedActionChartDrugNameData(patientId);
	}


	public List<Entry<String, PsychopharmMessage>> getPsychopharm_PHQ_9_questions() {
		List<Map.Entry<String,PsychopharmMessage>>psychopharm_phq9;
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.QUESTIONS);
		psychopharmMessage.setPhq_9(true);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		Map<String,PsychopharmMessage>psychopharm_altman_data =new LinkedHashMap<String,PsychopharmMessage>();;
		psychopharm_altman_data=psychopharmMessage.getPsychopharmPayload();
		psychopharm_phq9=new ArrayList<Map.Entry<String,PsychopharmMessage>>(psychopharm_altman_data.entrySet());
		return psychopharm_phq9;
	}

	public List<GenericMedActionPlan> getPsychoPharmMonitoringParameterChartValue(
			int patientId) {
		
		return patientMedicineDAO.getPsychoPharmMonitoringParameterChartValue(patientId);
	}
	
	public List<Entry<String, PsychopharmMessage>> getPyschoPharmQuestionsBasedOnLithiumLevel(Double psychopharmLabResult, Double creatnineClearnceValue){
		List<Map.Entry<String,PsychopharmMessage>>psychopharm_questionData;
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		//As it has been hardcoded But it will come from lab result for Lithium and CrCl value.
		//For testing purpose I have setted only lithium level
		psychopharmMessage.setLithiumLevel(psychopharmLabResult);
		psychopharmMessage.setCrCl_Value(creatnineClearnceValue);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		Map<String,PsychopharmMessage>psychopharm_question_data =new LinkedHashMap<String,PsychopharmMessage>();;
		psychopharm_question_data=psychopharmMessage.getPsychopharmPayload();
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_question_data.entrySet()) {
			
			logger.info("Key  "+entry.getKey());
			logger.info("Question response "+entry.getValue().getResponseList().size());
			logger.info("Option List size "+entry.getValue().getResponeLinkedMapList().size());
			
			for(Map.Entry<String, String>entryQuestion:entry.getValue().getResponeLinkedMapList().entrySet()){
				logger.info("Option key inside object pair "+entryQuestion.getKey());
				logger.info("value inside object pair "+entryQuestion.getValue());
			}
			
			for(String question:entry.getValue().getResponseList()){
				logger.info("question from drool "+question);
			}
		}
		psychopharm_questionData=new ArrayList<Map.Entry<String,PsychopharmMessage>>(psychopharm_question_data.entrySet());
		return psychopharm_questionData;
		
	}


	
	public List<ParentMedActionPlan> getHepatitisCMedActionPlanData(
			int patientId) {
		
		return patientMedicineDAO.getHepatitisCMedActionPlanData(patientId);
	}

	@Override
	public List<PatientMedicationData> getCurrentPsychopharmMeds(int patientId,
			String psychopharmClinicName) {
		return patientMedicineDAO.getCurrentPsychopharmMeds(patientId,psychopharmClinicName);
	}


	
	public List<PatientMedicationData> getCurrentHepatitsCMedData(
			int patientId, String hepatitisCClinicName) {
		
		return patientMedicineDAO.getCurrentHepatitsCMedData(patientId,hepatitisCClinicName);
	}

	@Override
	public ProcedureResultHistory getLatestLabValue(int patientId,
			int providerId, int loginId, String parameterShortName) {
		
		return labDAO.findCurrentLabValue(patientId, providerId, loginId, parameterShortName);
	}


	@Override
	public List<String> getPsychopharmDefaultLabs(String lithiumMedName) {
		List<String>psychopharmDefaultLabs=new ArrayList<String>();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.LABS);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		psychopharmDefaultLabs=psychopharmMessage.getResponseList();
		logger.info("Default labs for Psychopharm size "+psychopharmDefaultLabs.size());
		return psychopharmDefaultLabs;
	}


	@Override
	public ModelMessage psychopharmRecomendationsBasedOnAnswer(
			List<Entry<String, PsychopharmMessage>> psychopharmQuestions,
			Double psychopharmLabResult,Double creatnineClearnceValue) {
		ModelMessage messageObj=new ModelMessage();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setLithiumLevel(psychopharmLabResult);
		psychopharmMessage.setCrCl_Value(creatnineClearnceValue);
		String patientAnswer=new String();
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharmQuestions) {
			logger.info("Patient Answer To the question "+entry.getValue().getSelectedValue());
			patientAnswer=entry.getValue().getSelectedValue();
			break;
			
		}
		psychopharmMessage.setPatientAnswer(patientAnswer);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		String recomendationResponse=psychopharmMessage.getResponse();
		messageObj=new Gson().fromJson(recomendationResponse, ModelMessage.class);
		return messageObj;
	}
	
	public void saveNewCropDiscontinueMedsData(PatientMedicationHistory patHis) {
		// TODO Auto-generated method stub
		patientMedicineDAO.saveNewCropDiscontinueMedsData(patHis);
	}

	
	@Override
	public List<GenericMedActionPlan> getPsychopharmLabParameters(
			GenericMedActionPlan genericMedActionPlan) {
		List<GenericMedActionPlan>labParametersList=new ArrayList<GenericMedActionPlan>();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setRequest(Request.LABS);
		psychopharmMessage.setMonitoring_paramater(genericMedActionPlan.getParameterShortName());
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		List<MedPlan>respnseList=psychopharmMessage.getMedPlanDefaultSettings();
		for(MedPlan medPlanObj:respnseList){
			GenericMedActionPlan generActionPlanObj=new GenericMedActionPlan();
			generActionPlanObj.setParameterShortName(medPlanObj.getParameterShortName());
			generActionPlanObj.setMonitoringParameter(medPlanObj.getMonitoringParameter());
			labParametersList.add(generActionPlanObj);
		}
		return labParametersList;
	}

	
	public List<GenericMedActionPlan> getHepCChartLabMonitoringParameterData(
			int patientId, String hepatitisCClinicName) {
		
		return patientMedicineDAO.getHepCChartLabMonitoringParameterData(patientId,hepatitisCClinicName);
	}

	
	public List<String> getAllPsychoPharmClinicMedicineList(
			String psychopharmClinicName) {
		
		return patientMedicineDAO.getAllPsychoPharmClinicMedicineList(PSYCHOPHARM_CLINIC_NAME);
	}

	
	public List<String> getHepatitisCClinicMedicineList(
			String hepatitisCClinicName) {
		
		return patientMedicineDAO.getHepatitisCClinicMedicineList(hepatitisCClinicName);
	}

	@Override
	public String getUI_CrVal(
			List<Entry<String, PsychopharmMessage>> psychopharm_parameters) {
		String creatnineValue=new String();
		logger.info("================>Inside CrCl val from drools<=======================");
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_parameters) {
			logger.info("Key  "+entry.getKey());
			logger.info("parameter value "+entry.getValue().getParamter_value()+"Unit "+ entry.getValue().getParamter_unit());
			if(StringUtils.isNotBlank(entry.getValue().getParamter_value())){
			PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
			psychopharmMessage.setRequest(Request.LABS);
			//psychopharmMessage.setRequest(Request.);
			psychopharmMessage.setOptionsSelected(true);
			psychopharmMessage.setMonitoring_paramater(entry.getKey());
			psychopharmMessage.setMonitoring_parameter_value(Double.valueOf(entry.getValue().getParamter_value()));
			RulesUtil.process(psychopharmMessage);
			RulesUtil.fireRuleToDroolEngine();
			String responseValue=psychopharmMessage.getResponse();
			if(StringUtils.isNotBlank(responseValue)){
				creatnineValue=responseValue;
				break;
			}
			
			}
			
	}
		return creatnineValue;
	}

	@Override
	public List<Entry<String, PsychopharmMessage>> loadCalculatedCrClVal(
			double crClval,
			List<Entry<String, PsychopharmMessage>> psychopharm_parameters) {
		List<Map.Entry<String,PsychopharmMessage>>psychopharmUpdatedValues;
		for (Map.Entry<String,PsychopharmMessage> entry : psychopharm_parameters) {
			PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
			psychopharmMessage.setRequest(Request.CrCl_LABS);
			psychopharmMessage.setMonitoring_paramater(entry.getKey());
			RulesUtil.process(psychopharmMessage);
			RulesUtil.fireRuleToDroolEngine();
			boolean response=psychopharmMessage.isResponseSuccess();
			logger.info("response from drools for CrCl"+response);
			if(response){
				logger.info("inside crcl chk"+crClval);
				entry.getValue().setParamter_value(String.valueOf(crClval));
			}
			logger.info("Key  "+entry.getKey());
			logger.info("parameter value "+entry.getValue().getParamter_value());
			}
		
		psychopharmUpdatedValues=new ArrayList<Map.Entry<String,PsychopharmMessage>>(psychopharm_parameters);
		return psychopharmUpdatedValues;
	}

	@Override
	public ModelMessage psychopharmRecomendations(Double psychopharmLabResult) {
		ModelMessage messageObj=new ModelMessage();
		PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
		psychopharmMessage.setLithiumLevel(psychopharmLabResult);
		psychopharmMessage.setRequest(Request.ACTION);
		RulesUtil.process(psychopharmMessage);
		RulesUtil.fireRuleToDroolEngine();
		String recomendationResponse=psychopharmMessage.getResponse();
		messageObj=new Gson().fromJson(recomendationResponse, ModelMessage.class);
		return messageObj;
	}

	//This will be initialized once only
	private List<ContraindicatedMeds>ContraindicatedMedsList=new ArrayList<ContraindicatedMeds>();
	
	
	@Override
	public List<String> checkContraindicatedMeds(
			List<String> contraindicatedMedsForPsychopharmClinic,
			List<PatientMedicationData> patientMedicationDataList) {
		logger.info("=======checkContraindicatedMeds method fired============");
		List<String>contraindicatedChildMeds=new ArrayList<String>();
		try{
			List<ContraindicatedMeds>contraindicatedMeds =getContraindicatedMedsList();
			logger.info(" contraindicated Meds size from Db "+contraindicatedMeds.size());
			contraindicatedChildMeds=contraindicatedChildMeds(contraindicatedMeds,patientMedicationDataList);
			
		}catch(Exception e){
			logger.error("Error in checking contraindicated meds ");
			e.printStackTrace();
		}
		
		
		return contraindicatedChildMeds;
	}

	private List<String> contraindicatedChildMeds(
			List<ContraindicatedMeds> contraindicatedMeds, List<PatientMedicationData> patientMedicationDataList) {
		List<String>contraindicatedChildMeds=new ArrayList<String>();
		Map<String,List<ContraindicatedMedItems>>contraindicatedMedsMap=new LinkedHashMap<String, List<ContraindicatedMedItems>>();
		for(ContraindicatedMeds contraindicatedMedsObj:contraindicatedMeds){
			contraindicatedMedsMap.put(contraindicatedMedsObj.getParentMedName(), contraindicatedMedsObj.getChildMeds());
		}
		//Map<String,Boolean>contrainicatedExists=new LinkedHashMap<String,Boolean>();
		for(PatientMedicationData patientMedicationData:patientMedicationDataList){
			for(Map.Entry<String,List<ContraindicatedMedItems>> entry : contraindicatedMedsMap.entrySet()){
				for(ContraindicatedMedItems contraindicatedMedItems:entry.getValue()){
					if((patientMedicationData.getDrugs().toLowerCase()).contains(contraindicatedMedItems.getChildMedName().toLowerCase())){
					//	contrainicatedExists.put(entry.getKey(), true);
						contraindicatedChildMeds.add(entry.getKey());
						break;
					}
				}
			}
		}
		
		return contraindicatedChildMeds;
	}

	public List<ContraindicatedMeds> getContraindicatedMedsList() {
		if(ContraindicatedMedsList.isEmpty()){
			ContraindicatedMedsList=new ArrayList<ContraindicatedMeds>();
			ContraindicatedMedsList=patientMedicineDAO.getMasterContraindicatedMeds();
		}
		return ContraindicatedMedsList;
	}

	public void setContraindicatedMedsList(
			List<ContraindicatedMeds> contraindicatedMedsList) {
		ContraindicatedMedsList = contraindicatedMedsList;
	}

	
	@Override
	public List<String> getLabParams(
			PsychopharmModelData psychopharmModelDataObj) {
		List<String>psychopharmLabParams=new ArrayList<String>();
		try{
			logger.info("current step value "+psychopharmModelDataObj.getActiveIndexDefault());
			PsychopharmMessage psychopharmMessage=prepareRequestToDrools();
			psychopharmMessage.setRequest(Request.QUESTIONAIRRE_LABS);
		//	psychopharmMessage.setCurrent_step(String.valueOf(Integer.parseInt(psychopharmModelDataObj.getActiveIndexDefault()+1)));
			psychopharmMessage.setPhq_9(true);
			RulesUtil.process(psychopharmMessage);
			RulesUtil.fireRuleToDroolEngine();
			psychopharmLabParams=psychopharmMessage.getResponseList();
			for(String s:psychopharmLabParams){
				logger.info("PHQ-9 and Altman Mania scale==> "+s);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return psychopharmLabParams;
	}

	@Override
	public List<MasterMonitorParameters> getGeneralMedPlanLabs(
			String generalMapView) {
		
		return patientMedicineDAO.getGeneralMedPlanLabs(generalMapView);
	}

	@Override
	public ParentMedActionPlan getAllMedPlansForPatient(
			PatientMedicationData patientMedicationData, int patientID) {
		
		return patientMedicineDAO.getAllMedPlansForPatient(patientMedicationData,patientID);
	}

	@Override
	public List<ChildMedActionPlanParameter> convertToChildMedActionPlanParams(
			List<GenericMedActionPlan> psychopharmDefaultMedPlan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChildMedActionPlanParameter convertToProcedureTypeObj(
			MasterLOINCData selectedLoincData) {
		ProcedureType labTypeObj=new ProcedureType();
		labTypeObj.setId(selectedLoincData.getId());
		labTypeObj.setLabType(selectedLoincData.getShortName());
		labTypeObj.setLoincCode(selectedLoincData.getLoincNumber());
		
		ChildMedActionPlanParameter childMedActionPlanParameter=new ChildMedActionPlanParameter();
		childMedActionPlanParameter.setLabParameterObj(labTypeObj);
		return childMedActionPlanParameter;
	}

	@Override
	public boolean saveNewGeneralMedActionPlan(int patientId,
			ParentMedActionPlan parentMedActionPlan,
			List<ChildMedActionPlanParameter> temporaryChildMedActionPlansParams,Date dosingStartDate) {
		List<PatientDiagnosesDetails> patientDiagnosisList = new ArrayList<PatientDiagnosesDetails>();
		patientDiagnosisList=patientDAO.findPatientDiognosisDetails(new ContextUtil().getPatientId());
		return patientMedicineDAO.saveNewGeneralMedActionPlan(patientId,parentMedActionPlan,temporaryChildMedActionPlansParams,dosingStartDate,patientDiagnosisList);
	}

	@Override
	public List<GenericMedActionPlan> convertToOldGenericMedActionList(
			ParentMedActionPlan parentMedActionPlan2) {
		List<GenericMedActionPlan>oldGenericMedActionList=new ArrayList<GenericMedActionPlan>();
		for(ChildMedActionPlanParameter childMedActionPlanParameter:parentMedActionPlan2.getChildLabParameters()){
			GenericMedActionPlan oldGenericMedActionPlanObj=new GenericMedActionPlan();
			oldGenericMedActionPlanObj.setMonitoringParameter(childMedActionPlanParameter.getLabParameterObj().getLabType());
			oldGenericMedActionPlanObj.setGoalRangeSymbol(childMedActionPlanParameter.getGoalLowRangeSymbol());
			oldGenericMedActionPlanObj.setGoalRangeValue(childMedActionPlanParameter.getGoalLowRangeValue());
			oldGenericMedActionPlanObj.setGoalRangeEndSymbol(childMedActionPlanParameter.getGoalHighRangeSymbol());
			oldGenericMedActionPlanObj.setGoalRangeEndValue(childMedActionPlanParameter.getGoalHighRangeValue());
			
			oldGenericMedActionPlanObj.setAlertMediumLowRangeSymbol(childMedActionPlanParameter.getAlertMediumLowRangeSymbol());
			oldGenericMedActionPlanObj.setAlertMediumLowRangeValue(childMedActionPlanParameter.getAlertMediumLowRangeValue());
			oldGenericMedActionPlanObj.setAlertMediumHighRangeSymbol(childMedActionPlanParameter.getAlertMediumHighRangeSymbol());
			oldGenericMedActionPlanObj.setAlertMediumHighRangeValue(childMedActionPlanParameter.getAlertMediumHighRangeValue());
			
			oldGenericMedActionPlanObj.setAlertSevereLowRangeSymbol(childMedActionPlanParameter.getAlertSevereLowRangeSymbol());
			oldGenericMedActionPlanObj.setAlertSevereLowRangeValue(childMedActionPlanParameter.getAlertSevereLowRangeValue());
			oldGenericMedActionPlanObj.setAlertSevereHighRangeSymbol(childMedActionPlanParameter.getAlertSevereHighRangeSymbol());
			oldGenericMedActionPlanObj.setAlertSevereHighRangeValue(childMedActionPlanParameter.getAlertSevereHighRangeValue());
			
			oldGenericMedActionList.add(oldGenericMedActionPlanObj);
		}
		return oldGenericMedActionList;
	}

	/**
	 * @param patientId
	 * @return List of  ParentMedActionPlan
	 * @see com.clinakos.service.IPatientMedicineService#generalMedPlansForParticularPatient(int)
	 */
	public List<ParentMedActionPlan> generalMedPlansForParticularPatient(
			int patientId) {
		
		return patientMedicineDAO.generalMedPlansForParticularPatient(patientId);
	}

	/**
	 * It will used for Delete ParentMedActionPlan 
	 * @param parentMedActionPlan
	 * @see com.clinakos.service.IPatientMedicineService#deleteGeneralMAPonSelectedDrug(ParentMedActionPlan)
	 *  
	 */
	public void deleteGeneralMAPonSelectedDrug(
			ParentMedActionPlan parentMedActionPlan) {
		patientMedicineDAO.deleteGeneralMAPonSelectedDrug(parentMedActionPlan);
		
	}

	@Override
	public void updateGeneralMAP(ParentMedActionPlan parentMedActionPlan) {
		patientMedicineDAO.updateGeneralMAP(parentMedActionPlan);
		
	}

	@Override
	public MedandGenricmed validateMAPlabParameter(List<MedandGenricmed> allClinicMAP,
			MasterLOINCData selectedLoincData) {
		logger.info("Validating lab parameter with existing MAP method fired");
		MedandGenricmed medActionPlanObj=null;
		Map<Double,List<MedandGenricmed>>medPlanMAP=new LinkedHashMap<Double, List<MedandGenricmed>>();
		try {
			for (MedandGenricmed medPlanObj : allClinicMAP) {
				if (selectedLoincData.getShortName() != null
						&& medPlanObj.getLab() != null) {
					if (selectedLoincData.getShortName().equalsIgnoreCase(
							medPlanObj.getLab())) {
					return medPlanObj;
						/*if(existMedPlanMAP.containsKey(medPlanObj.getDrugId())){
							logger.info("inside if contains key method ");
							List<MedandGenricmed>medActionPlanList=new ArrayList<MedandGenricmed>();
							medActionPlanList=existMedPlanMAP.get(medPlanObj.getDrugId());
							logger.info("existing med plan size "+medActionPlanList.size());
							medActionPlanList.add(medPlanObj);
						//	existMedPlanMAP.remove(Double.valueOf(medPlanObj.getDrugId()));
							existMedPlanMAP.put(Double.valueOf(medPlanObj.getDrugId()), medActionPlanList);
							medPlanMAP.put(Double.valueOf(medPlanObj.getDrugId()), medActionPlanList);
							
						}else{
							List<MedandGenricmed>medActionPlanList=new ArrayList<MedandGenricmed>();
							medActionPlanList.add(medPlanObj);
							medPlanMAP.put(Double.valueOf(medPlanObj.getDrugId()), medActionPlanList);
							
						}*/
						
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	for(Map.Entry<Double,List<MedandGenricmed>> entry : medPlanMAP.entrySet()){
			logger.info("Entry Key ====> "+entry.getKey());
			for(MedandGenricmed medObj:entry.getValue()){
				logger.info("drug name "+medObj.getMedicinename());
				logger.info("Lab Paramter "+medObj.getLab());
			}
		}*/
		return null;
	}

	@Override
	public ParentMedActionPlan prepareMedDetailObj(
			PatientMedicationData patientMedicationData) {
		ParentMedActionPlan parentMedActionPlan=new ParentMedActionPlan();
		parentMedActionPlan.setDosageForm(patientMedicationData.getDosageForm());
		parentMedActionPlan.setDrugId(patientMedicationData.getDrugId());
		parentMedActionPlan.setDrugNameId(patientMedicationData.getDrugNameId());
		parentMedActionPlan.setPrescribedBy(patientMedicationData.getPrescriberName());
		parentMedActionPlan.setPrescriptionDate(patientMedicationData.getStartDate());
		parentMedActionPlan.setRegimen(patientMedicationData.getDirections());
		parentMedActionPlan.setStrength(patientMedicationData.getStrengths());
		parentMedActionPlan.setDrugName(patientMedicationData.getDrugs());
		parentMedActionPlan.setUnit(patientMedicationData.getUnit());
		
		return parentMedActionPlan;
	}

	/**
	 * @param parentMedActionPlan
	 * @return Object of  ParentMedActionPlan
	 * @see com.clinakos.service.IPatientMedicineService#preparePatientMedObj(ParentMedActionPlan)
	 */
	public PatientMedicationData preparePatientMedObj(
			ParentMedActionPlan parentMedActionPlan) {
		PatientMedicationData patientMedicationData=new PatientMedicationData();
		patientMedicationData.setDrugs(parentMedActionPlan.getDrugName());
		patientMedicationData.setStrengths(parentMedActionPlan.getStrength());
		patientMedicationData.setUnit(parentMedActionPlan.getUnit());
		patientMedicationData.setDosageForm(parentMedActionPlan.getDosageForm());
		patientMedicationData.setDirections(parentMedActionPlan.getRegimen());
		patientMedicationData.setDrugId(parentMedActionPlan.getDrugId());
		patientMedicationData.setDrugNameId(parentMedActionPlan.getDrugNameId());
		return patientMedicationData;
	}

	@Override
	public List<ParentMedActionPlan> getPsychopharmClinicMedPlans(
			int patientId, String psychopharmClinicName) {
		// TODO Auto-generated method stub
		return patientMedicineDAO.getPsychopharmClinicMedPlans(patientId,psychopharmClinicName);
	}

	@Override
	public ChildMedActionPlanParameter getChildMedPlanObj(
			List<String> psychopharmLabParamters,
			ParentMedActionPlan parentMedActionPlan) {
		ChildMedActionPlanParameter cMAPobj=new ChildMedActionPlanParameter();
		for(String pLabParameter:psychopharmLabParamters){
			for(ChildMedActionPlanParameter cMAPparameter:parentMedActionPlan.getChildLabParameters()){
				if(StringUtils.lowerCase(pLabParameter).equalsIgnoreCase(cMAPparameter.getLabParameterObj().getLabType())){
					cMAPobj=cMAPparameter;
					break;
				}
			}
		}
		return cMAPobj;
	}

	@Override
	public List<ParentMedActionPlan> convertObjToMasterDetail(
			MedandGenricmed medPlanMAP) {
		List<ParentMedActionPlan>medPlanMasterDetails=new ArrayList<ParentMedActionPlan>();
		ParentMedActionPlan pTypeObj=new ParentMedActionPlan();
		pTypeObj.setDrugName(medPlanMAP.getMedicinename());
		pTypeObj.setDosingStartDate(medPlanMAP.getDosingStartDate());
		pTypeObj.setDrugName(medPlanMAP.getMedicinename());
		pTypeObj.setDrugNameId(medPlanMAP.getDrugNameId());
		pTypeObj.setDosageForm(medPlanMAP.getDose());
		pTypeObj.setDrugId(Double.valueOf(medPlanMAP.getDrugId()));
		 ChildMedActionPlanParameter childMedActionPlanParameter=new ChildMedActionPlanParameter();
		 childMedActionPlanParameter.setGoalHighRangeSymbol(medPlanMAP.getGoalHighRangeSymbol());
		 childMedActionPlanParameter.setGoalHighRangeValue(Double.valueOf(medPlanMAP.getGoalHighRangeValue()));
		 childMedActionPlanParameter.setGoalLowRangeSymbol(medPlanMAP.getGoalLowRangeSymbol());
		 childMedActionPlanParameter.setGoalLowRangeValue(Double.valueOf(medPlanMAP.getGoalLowRangeValue()));
		 childMedActionPlanParameter.setAlertSevereHighRangeSymbol(medPlanMAP.getAlertSevereHighRangeSymbol());
		 childMedActionPlanParameter.setAlertSevereHighRangeValue(Double.valueOf(medPlanMAP.getAlertSevereHighRangeValue()));
		 childMedActionPlanParameter.setAlertSevereLowRangeSymbol(medPlanMAP.getAlertSevereLowRangeSymbol());
		 childMedActionPlanParameter.setAlertSevereLowRangeValue(Double.valueOf(medPlanMAP.getAlertSevereLowRangeValue()));
		 childMedActionPlanParameter.setAlertMediumHighRangeSymbol(medPlanMAP.getAlertMediumHighRangeSymbol());
		 childMedActionPlanParameter.setAlertMediumHighRangeValue(Double.valueOf(medPlanMAP.getAlertMediumHighRangeValue()));
		 childMedActionPlanParameter.setAlertMediumLowRangeSymbol(medPlanMAP.getAlertMediumLowRangeSymbol());
		 childMedActionPlanParameter.setAlertMediumLowRangeValue(Double.valueOf(medPlanMAP.getAlertMediumLowRangeValue()));
		 childMedActionPlanParameter.setLabType(medPlanMAP.getLab());
		 ProcedureType procedureType=new ProcedureType();
		 procedureType.setId(medPlanMAP.getLabParameterId());
		 procedureType.setLabType(medPlanMAP.getLab());
		 procedureType.setUnits(medPlanMAP.getLabUnit());
		 childMedActionPlanParameter.setLabParameterObj(procedureType);
		 childMedActionPlanParameter.setGoalRange(medPlanMAP.getAcceptablerange());
		 pTypeObj.getChildLabParameters().add(childMedActionPlanParameter); 
		 medPlanMasterDetails.add(pTypeObj);
		return medPlanMasterDetails;
/*	List<ParentMedActionPlan>medPlanMasterDetails=new ArrayList<ParentMedActionPlan>();
	 for (Map.Entry<Double,List<MedandGenricmed>> entry : medPlanMAP.entrySet()) {
			ParentMedActionPlan masterDetailObj=new ParentMedActionPlan();
			masterDetailObj.setDrugId(entry.getKey());
		  logger.info("Key :: "+entry.getKey());
		  int i=0;
		  for(MedandGenricmed medPlanObj:entry.getValue()){
			 ChildMedActionPlanParameter childMedActionPlanParameter=new ChildMedActionPlanParameter();
			 childMedActionPlanParameter.setLabType(medPlanObj.getLab());
			 childMedActionPlanParameter.setGoalRange(medPlanObj.getAcceptablerange());
			 masterDetailObj.getChildLabParameters().add(childMedActionPlanParameter); 
			 if(i==entry.getValue().size()-1){
				 masterDetailObj.setDosingStartDate(medPlanObj.getDosingStartDate());
				 masterDetailObj.setDrugName(medPlanObj.getMedicinename());
				 masterDetailObj.setDrugNameId(medPlanObj.getDrugNameId());
				 masterDetailObj.setDosageForm(medPlanObj.getDose());
			 }
			 i++;
			 
		  }
		  medPlanMasterDetails.add(masterDetailObj);
	 }
		return medPlanMasterDetails;*/
	}

	@Override
	public List<ParentMedActionPlan> convertObjToMasterDetailType(
			List<ParentMedActionPlan> medPlanList) {
		List<ParentMedActionPlan>parentMedActionPlanList=new ArrayList<ParentMedActionPlan>();
		Map<Double,ParentMedActionPlan>masterDetailMAP=new LinkedHashMap<Double, ParentMedActionPlan>();
		masterDetailMAP=prepareMasterDetailMAP(medPlanList);
		for(Map.Entry<Double, ParentMedActionPlan>entry:masterDetailMAP.entrySet()){
			parentMedActionPlanList.add(entry.getValue());
		}
		
		
		return parentMedActionPlanList;
	}

	private Map<Double, ParentMedActionPlan> prepareMasterDetailMAP(
			List<ParentMedActionPlan> medPlanList) {
		Map<Double,ParentMedActionPlan>masterDetailMAP=new LinkedHashMap<Double, ParentMedActionPlan>();
		for(ParentMedActionPlan pMasterTypeObj:medPlanList){
			if(masterDetailMAP.size()>0){
				logger.info("inside contains key ");
			for(Map.Entry<Double,ParentMedActionPlan>entry:masterDetailMAP.entrySet()){
				if(entry.getKey()==pMasterTypeObj.getDrugId()){
					ParentMedActionPlan parentMedActionPlanObj=entry.getValue();
					parentMedActionPlanObj.getChildLabParameters().addAll(pMasterTypeObj.getChildLabParameters());
					masterDetailMAP.put(pMasterTypeObj.getDrugId(), parentMedActionPlanObj);
				}else{
					masterDetailMAP.put(pMasterTypeObj.getDrugId(), pMasterTypeObj);
				}
			}
				
			}else{
				masterDetailMAP.put(pMasterTypeObj.getDrugId(), pMasterTypeObj);
			}
		}
		logger.info("master detail MAP size "+masterDetailMAP.size());
		
		return masterDetailMAP;
	}

	@Override
	public ParentMedActionPlan convertGeneralMAPMasterDetailObj(
			PatientMedicationData patientMedicationData,
			List<GenericMedActionPlan> psychopharmDefaultMedPlanParameters) {
		ParentMedActionPlan parentMedActionPlan=new ParentMedActionPlan();
		parentMedActionPlan=prepareMedDetailObj(patientMedicationData);
		parentMedActionPlan.setChildLabParameters(convertToChildMAPobj(psychopharmDefaultMedPlanParameters));
		return parentMedActionPlan;
	}

	private List<ChildMedActionPlanParameter> convertToChildMAPobj(
			List<GenericMedActionPlan> psychopharmDefaultMedPlanParameters) {
		List<ChildMedActionPlanParameter>childMAPlist=new ArrayList<ChildMedActionPlanParameter>();
		for(GenericMedActionPlan genericMedActionPlanObj:psychopharmDefaultMedPlanParameters){
			ChildMedActionPlanParameter cMAPobj=new ChildMedActionPlanParameter();
			/*cMAPobj.setGoalHighRangeSymbol(goalHighRangeSymbol);
			cMAPobj.setGoalHighRangeValue(goalHighRangeValue);
			cMAPobj.setGoalLowRangeSymbol(goalLowRangeSymbol);
			cMAPobj.setGoalLowRangeValue(goalLowRangeValue);
			cMAPobj.setAlertSevereHighRangeSymbol(alertSevereHighRangeSymbol);
			cMAPobj.setAlertSevereHighRangeValue(alertSevereHighRangeValue);
			cMAPobj.setAlertSevereLowRangeSymbol(alertSevereLowRangeSymbol);
			cMAPobj.setAlertSevereLowRangeValue(alertSevereLowRangeValue);
			cMAPobj.setAlertMediumHighRangeSymbol(alertMediumHighRangeSymbol);
			cMAPobj.setAlertMediumHighRangeValue(alertMediumHighRangeValue);
			cMAPobj.setAlertMediumLowRangeSymbol(alertMediumLowRangeSymbol);
			cMAPobj.setAlertMediumLowRangeValue(alertMediumLowRangeValue);
			ProcedureType labProcedureType=new ProcedureType();
			labProcedureType.setId(id);
			cMAPobj.setLabParameterObj(labProcedureType);*/
			childMAPlist.add(cMAPobj);
		}
		return childMAPlist;
	}

	@Override
	public List<ChildMedActionPlanParameter> getPsychopharmDefaultLabParameters(
			String psychopharmMapView, String genderOfPatient) {
		List<MasterMonitorParameters>psychopharmDefaultLabs=patientMedicineDAO.getPsychopharmDefaultLabParameters(psychopharmMapView,genderOfPatient);
		List<ChildMedActionPlanParameter>psychopharmChildMedPlans=new ArrayList<ChildMedActionPlanParameter>();
		psychopharmChildMedPlans=convertToChildMedPlans(psychopharmDefaultLabs);
		return psychopharmChildMedPlans;
	}

	private List<ChildMedActionPlanParameter> convertToChildMedPlans(
			List<MasterMonitorParameters> psychopharmDefaultLabs) {
		List<ChildMedActionPlanParameter>psychopharmChildMedPlans=new ArrayList<ChildMedActionPlanParameter>();
		for(MasterMonitorParameters parentParmeters:psychopharmDefaultLabs){
			for(ChildMonitoringParameters childMonitoringParameters:parentParmeters.getChildMonitoringParameters()){
			ChildMedActionPlanParameter cMAPobj=new ChildMedActionPlanParameter();
			cMAPobj.setGoalHighRangeSymbol(childMonitoringParameters.getGoalHighRangeSymbol());
			cMAPobj.setGoalHighRangeValue(childMonitoringParameters.getGoalHighRangeValue());
			cMAPobj.setGoalLowRangeSymbol(childMonitoringParameters.getGoalLowRangeSymbol());
			cMAPobj.setGoalLowRangeValue(childMonitoringParameters.getGoalLowRangeValue());
			cMAPobj.setAlertSevereHighRangeSymbol(childMonitoringParameters.getAlertSevereHighRangeSymbol());
			cMAPobj.setAlertSevereHighRangeValue(childMonitoringParameters.getAlertSevereHighRangeValue());
			cMAPobj.setAlertSevereLowRangeSymbol(childMonitoringParameters.getAlertSevereLowRangeSymbol());
			cMAPobj.setAlertSevereLowRangeValue(childMonitoringParameters.getAlertSevereLowRangeValue());
			cMAPobj.setAlertMediumHighRangeSymbol(childMonitoringParameters.getAlertMediumHighRangeSymbol());
			cMAPobj.setAlertMediumHighRangeValue(childMonitoringParameters.getAlertMediumHighRangeValue());
			cMAPobj.setAlertMediumLowRangeSymbol(childMonitoringParameters.getAlertMediumLowRangeSymbol());
			cMAPobj.setAlertMediumLowRangeValue(childMonitoringParameters.getAlertMediumLowRangeValue());
			/*ProcedureType labProcedureType=new ProcedureType();
			labProcedureType.setId(childMonitoringParameters.get);*/
			cMAPobj.setLabParameterObj(childMonitoringParameters.getLoincObj());
			psychopharmChildMedPlans.add(cMAPobj);
			}
		}
		return psychopharmChildMedPlans;
	}

	/**
	 * Get Reconcile  Info Data based on Patient Id 
	 * @param patientId
	 * @return ReconcileInfo object 
	 * @see com.clinakos.service.IPatientMedicineService#getLatestReconcileInfo(int)
	 */
	public ReconcileInfo getLatestReconcileInfo(int patientId) {
		return patientMedicineDAO.getLatestReconcileInfo(patientId);
	}

	@Override
	public void updateReconcileInfoonDeleteMed(
			PatientMedicationData changePatientMedicineBackUpdata, boolean status) {
		// TODO Auto-generated method stub
		patientMedicineDAO.updateReconcileInfoonDeleteMed(changePatientMedicineBackUpdata,status);
	}

	@Override
	public Map<String, String> formularyMAPtoOldText() {
		Map<String,String>formularyMAP=new HashMap<String, String>();
		try{
			formularyMAP=parsePropertyEntryDropdown(FORMULARY_MAP_TO_OLD_TEXT);
		}catch(Exception e){
			e.printStackTrace();
		}
		return formularyMAP;
	}

	public Map<String, String> parsePropertyEntryDropdown(String propKey)  {
		Map<String, String> checkBoxMonitoringItems = new LinkedHashMap<String, String>();
		try{
			Properties properties = new Properties();
			properties.load(this.getClass().getClassLoader().getResourceAsStream(CLINAKOS_MESSAGES_LOCATION));	
			String propVal = properties.getProperty(propKey);
			logger.info("property value "+propVal);
			StringTokenizer propElements = new StringTokenizer(propVal, PROP_VAL_DELIM);
			String eachElement = "";	
			if ( propElements !=null) {
				for (; propElements.hasMoreElements();) {
					eachElement = HtmlUtils.htmlUnescape(propElements.nextElement().toString());
					checkBoxMonitoringItems.put(eachElement.substring(0, eachElement.indexOf(":")), eachElement.substring(eachElement.indexOf(":")+1, eachElement.length()));
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return checkBoxMonitoringItems;
	}

	@Override
	public List<GeriatricPrecaution> getGeriatricPrecautions(
			double selectedDrugId) {
		
		return patientMedicineDAO.getGeriatricPrecautions(selectedDrugId);
	}

	@Override
	public List<PaediatricPrecaution> getPaediatricPrecautions(
			double selectedDrugId, Integer patientAgeInDaysVal) {
		List<PaediatricPrecaution>paediatricPrecautions=new ArrayList<PaediatricPrecaution>();
		List<PaediatricPrecaution>paediatricPrecautionsList=patientMedicineDAO.getPaediatricPrecautions(selectedDrugId,patientAgeInDaysVal);
		for(PaediatricPrecaution paediatricPrecaution:paediatricPrecautionsList){
			if(patientAgeInDaysVal >= paediatricPrecaution.getMinimumAgeInDays() && patientAgeInDaysVal <= paediatricPrecaution.getMaximumAgeInDays()){
				paediatricPrecautions.add(paediatricPrecaution);
			}
		}
		return paediatricPrecautions;
	}

	@Override
	public List<DrugInteractionOverview> convertGeriatricPrecautionToDrugInteractionOverviewObj(
			List<GeriatricPrecaution> geriatricPrecautions,
			double selectedDrugId) {
		List<DrugInteractionOverview> geriatricPrecautionsList = new ArrayList<DrugInteractionOverview>();
		for (GeriatricPrecaution geriatricPrecaution : geriatricPrecautions) {
			DrugInteractionOverview drugInteractionOverview = new DrugInteractionOverview();
			drugInteractionOverview.setIssueType(GERIATRIC_PRECAUTIONS);
			drugInteractionOverview.setDrugId(String
					.valueOf((int) selectedDrugId));
			drugInteractionOverview.setDrugIdVal(selectedDrugId);
			drugInteractionOverview.setClinicalEffects("");
			drugInteractionOverview.setPatientManagement(geriatricPrecaution
					.getPrecautionDescription());
			if (geriatricPrecaution.getPrecautionSeverityLevel().equals(
					GERIATRIC_HIGH_SEVERITY)) {
				drugInteractionOverview.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				drugInteractionOverview
						.setSeverityLevelToolTip(geriatricPrecaution
								.getPrecautionSeverityLevel());
			} else {
				drugInteractionOverview.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				drugInteractionOverview
						.setSeverityLevelToolTip(geriatricPrecaution
								.getPrecautionSeverityLevel());
			}

			geriatricPrecautionsList.add(drugInteractionOverview);
		}
		return geriatricPrecautionsList;
	}

	@Override
	public List<DrugInteractionOverview> convertPaediatricPrecautionToDrugInteractionOverviewObj(
			List<PaediatricPrecaution> paediatricPrecautions,
			double selectedDrugId) {
		List<DrugInteractionOverview> paediatricPrecautionsList = new ArrayList<DrugInteractionOverview>();
		for (PaediatricPrecaution paediatricPrecaution : paediatricPrecautions) {
			DrugInteractionOverview drugInteractionOverview = new DrugInteractionOverview();
			drugInteractionOverview.setIssueType(PAEDIATRIC_PRECAUTIONS);
			drugInteractionOverview.setDrugId(String
					.valueOf((int) selectedDrugId));
			drugInteractionOverview.setDrugIdVal(selectedDrugId);
			drugInteractionOverview.setClinicalEffects("");
			drugInteractionOverview.setPatientManagement(paediatricPrecaution
					.getPrecautionDescription());

			if (StringUtils.isNotBlank(paediatricPrecaution
					.getPrecautionSeverityValue())) {
				try {
					if (Integer.valueOf(paediatricPrecaution
							.getPrecautionSeverityValue()) == 1) {
						drugInteractionOverview
								.setSeverityLevel(HIGH_SEVERITY_LEVEL);
						drugInteractionOverview
								.setSeverityLevelToolTip(paediatricPrecaution
										.getPrecautionSeverityLevel());
					} else if (Integer.valueOf(paediatricPrecaution
							.getPrecautionSeverityValue()) == 2
							|| Integer.valueOf(paediatricPrecaution
									.getPrecautionSeverityValue()) == 3) {
						drugInteractionOverview
								.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
						drugInteractionOverview
								.setSeverityLevelToolTip(paediatricPrecaution
										.getPrecautionSeverityLevel());
					}

				} catch (NumberFormatException ne) {
					ne.printStackTrace();
				}
				paediatricPrecautionsList.add(drugInteractionOverview);
			}

		}
		return paediatricPrecautionsList;
	}

	@Override
	public void saveReconcileInfo(
			PatientMedicationData patientMedicationDataForReconcile)  throws HibernateException, Exception{
		patientMedicineDAO.saveReconcileInfoObj(patientMedicationDataForReconcile);
		
	}

	public IPatientDAO getPatientDAO() {
		return patientDAO;
	}

	public void setPatientDAO(IPatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}

	@Override
	public void deleteSelectedRxDrug(
			PatientMedicationData changePatientMedicineBackUpdata) {
		
		patientMedicineDAO.deleteSelectedRxDrug(changePatientMedicineBackUpdata);
	}

	@Override
	public void updateMAPmessage(
			PatientMedicationData changePatientMedicineBackUpdata) {
		patientMedicineDAO.updateMAPmessage(changePatientMedicineBackUpdata);
		
	}


	public List<EncounterSummary> getPatientEncounterSummaryHistoryData(
			int patientId) {
		
		return patientMedicineDAO.getPatientEncounterSummaryHistoryData(patientId);
	}

	
	public List<EncounterSummary> getEncounterSummaryHistoryData(int patientId,
			Date startDate, Date endDate) {
		
		return patientMedicineDAO.getEncounterSummaryHistoryData(patientId,startDate,endDate);
	}

	@Override
	public void updateMedAactionPlanStatus(
			PatientMedicationData changePatientMedicineBackUpdata) {
		patientMedicineDAO.updateMedAactionPlanStatus(changePatientMedicineBackUpdata);
		
	}

	/**
	 * Get the list of ParentMedAction paln which has been discontinued 
	 * @param patientId
	 * @return List of ParentMedActionPlan
	 */
	public List<ParentMedActionPlan> getInactiveMedsWithActiveMAP(int patientId) {
		
		return patientMedicineDAO.getInactiveMedsWithActiveMAP(patientId);
	}

	
	
	public EncounterSummary getSelectedEncounterSummaryData(int patientId,
			int providerId, Date selectedEncounterDateForPrint) {
		
		return patientMedicineDAO.getSelectedEncounterSummaryData(patientId,providerId,selectedEncounterDateForPrint);
	}

	
	public List<MedandGenricmed> getMedAndGenrticMedPlanDataForSelectedDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint) {
		
		return patientMedicineDAO.getMedAndGenrticMedPlanDataForSelectedDate(patientId,providerId,selectedEncounterDateForPrint);
	}

	
	public List<SendMessageEditRx> getSendMessageEditRDataForSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint) {
		
		return patientMedicineDAO.getSendMessageEditRDataForSelectedEncounterDate(patientId,providerId,selectedEncounterDateForPrint);
	}


	public List<PatientMedicationData> getAddedOrChangeMedDataOnSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint) {
		
		return patientMedicineDAO.getAddedOrChangeMedDataOnSelectedEncounterDate(patientId,providerId,selectedEncounterDateForPrint);
	}

	
	public List<PatientMedicationData> getPatientMedicationDataForSelectedEncounterDate(
			int patientId, int providerId, Date selectedEncounterDateForPrint) {
		
		return patientMedicineDAO.getPatientMedicationDataForSelectedEncounterDate(patientId,providerId,selectedEncounterDateForPrint);
	}

	@Override
	public Map<Integer, List<PatientMedicationData>> getPatientMedicationData(
			int providerId, UserLoginDetail[] selectedUserLoginDetail) {
		
		return patientMedicineDAO.getPatientMedicationDataMap(providerId,selectedUserLoginDetail);
	}

	@Override
	public List<HashMap<Integer, List<PharmacyDetail>>> getPharmacyHistoryData(
			UserLoginDetail[] selectedUserLoginDetail, LocalDate todayDate,
			LocalDate endDate) {
		
		return patientMedicineDAO.getPharmacyHistoryData(selectedUserLoginDetail,todayDate,endDate);
	}

	@Override
	public void saveAutoFillReconcileMeds(
			Map<Integer, List<PatientMedicationData>> pharmacyToPatientMedicationObjMaps) {
		 patientMedicineDAO.saveAutoFillReconcileMeds(pharmacyToPatientMedicationObjMaps);
		
	}

	@Override
	public Map<Integer, List<PharmacyDetail>> getPharmacyOldHistory(
			UserLoginDetail[] selectedUserLoginDetail) {
		List<Integer>selectedUserIds=new ArrayList<Integer>();
		for(UserLoginDetail userDetail:selectedUserLoginDetail){
			selectedUserIds.add(userDetail.getUserId());
		}
		return patientMedicineDAO.getPharmacyOldHistory(selectedUserIds);
	}

	@Override
	public void saveInteractionAnalytics(
			List<BatchInteractionAnalytic> interactionAnalytics) {
		patientMedicineDAO.saveInteractionAnalytics(interactionAnalytics);
		
	}

	/**
	 * Intearction history data 
	 * @param patientId
	 * @param providerId
	 * @return Batch Intearction Analytics List 
	 * @see com.clinakos.service.IPatientMedicineService#getInteractionHistoryData(int, int)
	 */
	public List<BatchInteractionAnalytic> getInteractionHistoryData(
			int patientId, int providerId) {
		
		return patientMedicineDAO.getInteractionHistoryData(patientId,providerId);
	}

	
/******@author SAURABH
 * ****TO FIND OLD MEDICATION DATA
*/	
	@Override
	public List<PharmacyDetail> getOldMedicationDataList(int patientId,
			List<PatientMedicationData> patientMedicationDataList) {
		List<PharmacyDetail> pOldMed=new ArrayList<PharmacyDetail>();
		List<PharmacyDetail> pOldMed2=new ArrayList<PharmacyDetail>();
		pOldMed=patientMedicineDAO.getOldMedicationDataList(patientId,patientMedicationDataList);
		logger.info("::::pOldMed.size= "+pOldMed.size()+" :::::patientMedicationDataList.size= "+patientMedicationDataList.size());
		if (patientMedicationDataList.size()>0) {
			for (PatientMedicationData pmd : patientMedicationDataList) {
				logger.info("getOldMedicationDataList ::: pmd.drug_id= "+ pmd.getDrugId());
			for (PharmacyDetail pd : pOldMed) {
				if (pmd.getDrugId()==pd.getDrugId()) {
					logger.info("!!!!!!!!!find common drugId in pmd and pd !!!!!!pd.drug_id= "+ pd.getDrugId());
					pOldMed2.add(pd);
				}
			  }
			}
		}
		pOldMed.removeAll(pOldMed2);
		return pOldMed;
	}

/**
 * Formulary Tier for Patient
 * 
 */
@Override
public String findFormularySymbolForPatient(int insuranceId, String drugName,
		Integer patientId, int providerId,ProviderLocation providerLocationObj,RoleSecurity roleSecurityObj,DoctorDetail doctorDetailForMultithreading) {
		logger.info("findFormularySymbol method::::insuranceId==" + insuranceId);

		List<FormularyCoverageDetail> formularyCoverageDetailList = new ArrayList<FormularyCoverageDetail>();
		String formularyTier = "";

		/*FacesContext context = FacesContext.getCurrentInstance();
		UserManageBean userManageBean = (UserManageBean) context hg
				.getApplication().getELResolver()
				.getValue(context.getELContext(), null, "userManageBean");*/

		try {

			formularyCoverageDetailList = formulary1WsBean
					.getFormularyCoverageForComplianceSymbolForPatientForMultithreading(insuranceId,
							drugName, providerLocationObj,roleSecurityObj,patientId,providerId,doctorDetailForMultithreading);

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Patient Medication service Impl formularyCoverageDetailList::::::::::::;;"
				+ formularyCoverageDetailList.size());
		for (FormularyCoverageDetail formularyCoverageDetail : formularyCoverageDetailList) {

			formularyTier = formularyCoverageDetail.getFormularyStatus();
			logger.info("Patient Medication service Impl " + formularyTier);

		}

		return formularyTier;
}

@Override
public List<PatientProvider> getPatientList(int providerId) {
	
	return patientMedicineDAO.getPatientList(providerId);
}

	


	
}

	

