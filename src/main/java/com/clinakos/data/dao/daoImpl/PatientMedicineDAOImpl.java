package com.clinakos.data.dao.daoImpl;

import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import net.sf.cglib.transform.impl.AddDelegateTransformer;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.weaver.patterns.ParserException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.RootEntityResultTransformer;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.HtmlUtils;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.dao.IPatientMedicineDAO;
import com.clinakos.data.model.core.BatchInteractionAnalytic;
import com.clinakos.data.model.core.BatchPatientMedsHistory;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.patient.ACOPatientMeasure;
import com.clinakos.data.model.patient.AlertGenericMedActionLab;
import com.clinakos.data.model.patient.AllergyMaster;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ChildMedActionPlanParameter;
import com.clinakos.data.model.patient.ClinicDiagonsis;
import com.clinakos.data.model.patient.ClinicDiagonsisLab;
import com.clinakos.data.model.patient.ClinicLabDetails;
import com.clinakos.data.model.patient.ClinicQuestionnaire;
import com.clinakos.data.model.patient.ClinicSubdiagnosis;
import com.clinakos.data.model.patient.ContraindicatedMeds;
import com.clinakos.data.model.patient.DosageFrom;
import com.clinakos.data.model.patient.DrugDrugInteractionData;
import com.clinakos.data.model.patient.DrugInteractionForWarfarin;
import com.clinakos.data.model.patient.EncounterSummary;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.GenericMedicine;
import com.clinakos.data.model.patient.GeriatricPrecaution;
import com.clinakos.data.model.patient.LabDetail;
import com.clinakos.data.model.patient.MasterDrugAllergyInteraction;
import com.clinakos.data.model.patient.MasterDrugDiseaseInteraction;
import com.clinakos.data.model.patient.MasterDrugDrugInteraction;
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
import com.clinakos.data.model.patient.ReconcileInfo;
import com.clinakos.data.model.patient.RouteDetails;
import com.clinakos.data.model.patient.SendMessageEditRx;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.data.model.patient.UnitDetails;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.VisitHistory;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.data.model.patient.WeeklyDose;
import com.clinakos.data.model.rules.PsychopharmMessage;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.TimeUtil;

import static com.clinakos.common.util.ClinakosConstant.*;

@SuppressWarnings("unchecked")

public class PatientMedicineDAOImpl extends BaseDaoImpl implements IPatientMedicineDAO {
	public static final Logger logger = Logger.getLogger("PatientMedicineDAOImpl.class");
	
	//Method to fetch All Medicine detail taken by any particular Patient
	
	//@SuppressWarnings("unchecked")
	/**
	 * Get All medication data based on patient  
	 * @return List of Patient Medication data 
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findPatientMedicationDataList(int)
	 */
	public List<PatientMedicationData> findPatientMedicationDataList( int patientId) {
		logger.debug("findPatientMedicationDataList.................."+patientId);
		return getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class)
				.add(Restrictions.eq("patientId", patientId)).list();
		
	}
	

	public List<ACOPatientMeasure> getACOPatientMeasure(int pat_id){
		List<ACOPatientMeasure> pteintList= new ArrayList<ACOPatientMeasure>();
		
	
		try{
		
			SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery("select f.id,f.patient_id,f.measure_type, f.active,f.exception, f.measure_date  "
					+"from ( "
					  +" select measure_type, max(measure_date) as maxdate "
					   +"from aco_patient_measure group by measure_type "
					+ ") as x inner join aco_patient_measure as f on f.measure_type = x.measure_type and f.measure_date = x.maxdate where f.patient_id =:patient_id");
						         sqlQuery.addEntity(ACOPatientMeasure.class);
						         sqlQuery.setInteger("patient_id", pat_id);
						         pteintList =sqlQuery.list();
		}
		
		catch(HibernateException he){
			he.printStackTrace();
		}
			
		return pteintList ;
		}
	
	public List<ACOPatientMeasure> getACOPatientMeasureHistory(int pat_id){

		List<ACOPatientMeasure> pteintList= new ArrayList<ACOPatientMeasure>();
		
		try{
			
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ACOPatientMeasure.class);
			criteria.add(Restrictions.eq("patientId", pat_id));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.distinct(Projections.property("mesureType")),"mesureType")
					.add(Projections.property("active"),"active")
					.add(Projections.property("exception"),"exception")
					.add(Projections.property("measureDate"),"measureDate")
					
					);
			criteria.setResultTransformer(Transformers.aliasToBean(ACOPatientMeasure.class));
			pteintList=criteria.list();
			logger.info("Aco patient measure history list size "+pteintList.size());
			
			
			
		}
		
		catch(HibernateException he){
			he.printStackTrace();
		}
			
		return pteintList ;
		
		
	}
	/**
	 * Call procedure For All ACO Count Value 
	 * @param pat_id
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#CalPatientmesureCount(int)	
	 */
	
	public void CalPatientmesureCount(int pat_id){

		try{
			
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_aco_measure_poplulationLevel("+pat_id+")}");
	        statement.executeQuery();
			
			
		}
		catch(SQLException sql){
			sql.printStackTrace();
			logger.info("SQL exception in getACOPatientMeasure======="+sql);
		}
		
		catch(HibernateException he){
			he.printStackTrace();
		}
			
		
		}
	
	
	
	
	//Method to fetch perticular Medicine history taken by any particular Patient by Anand S Jha
	public List<PatientMedicationHistory> fetchMedicationHistory(int patientId,String medicineName) {
		List<PatientMedicationHistory> medicationHistoryList = null;
		try
		{
			Criteria criteria4MedHistory = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
			criteria4MedHistory.add(Restrictions.eq("patientId", patientId));
			criteria4MedHistory.add(Restrictions.eq("drugs",medicineName));
			criteria4MedHistory.addOrder(Order.asc("startDate"));
			medicationHistoryList=criteria4MedHistory.list();
			//prescriberName
			for(PatientMedicationHistory patHistory:medicationHistoryList)
			{
				patHistory.setPrescriberName(findDoctorNameAccordingToId(patHistory.getPrescriberID()));
			}
		}
		catch(Exception e)
		{
			logger.debug(e.toString());
		}
		return medicationHistoryList;
	}


	public List<PatientMedicationData> showPatientMedicationeData(int patientId) {
		List<PatientMedicationData> medicationDataList=new ArrayList<PatientMedicationData>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			medicationDataList=criteria.list();
			
			for (PatientMedicationData patientMedicationData : medicationDataList) {
				
				patientMedicationData.setPrescriberName(findDoctorNameAccordingToId(patientMedicationData.getDoctorId()));
				
				//System.out.println("doctor name for patient profile medication data table???????");
				CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_get_med_image_id('"+patientMedicationData.getDrugId()+"')}");
				ResultSet resultSet=statement.executeQuery();
				while (resultSet.next()) {
					
					String medicineName=resultSet.getString("medicine_name");
				if(!(resultSet.getString("image_id").equals("\\")) && !(resultSet.getString("image_id").equals("\\N"))){
						String imageId=resultSet.getString("image_id");
						System.out.println("medicineName:::::::::::::    "+medicineName    +"imageId::::::::   "+imageId+"Orignal MedicineName:::::::::   "+patientMedicationData.getDrugs());
						System.out.println("resultSet.getFetchSize()"+resultSet.getFetchSize()+"rs.getRow    "+resultSet.getRow()+"imageId.length()   "+imageId.length());
						
							
							patientMedicationData.setMedicineImageId(imageId);
							System.out.println("patientMedicationData.getMedicineImageId()::::::::"+patientMedicationData.getMedicineImageId());
						}
					
				}
					
					
				}
			
			
			
			
			
			
		} catch (Exception e) {
			
		e.printStackTrace();
		}
		return medicationDataList;
	}


	/*
	 * find pahrmacy detail list based on Patient Id  
	 * @author gopal krishna jha from lumbini
	 * (non-Javadoc)
	 * @return List of Pharmacy Detail Data  
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findPharmacyDetailListAccordingToPatirnt(int)
	 */
	public List<PharmacyDetail> findPharmacyDetailListAccordingToPatirnt(
			int patientId) {
		logger.info("findPharmacyDetailListAccordingToPatirnt method startted in patientmedicinedao impl ");
		return getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class).add(Restrictions.eq("patientId", patientId))
				.addOrder(Order.asc("dateOfPurchase")).list();
	}


	public List<PatientMedicationHistory> showPatientMedicationeHistoryData(String drugs) {
		List<PatientMedicationHistory> patMedicationHistories=new ArrayList<PatientMedicationHistory>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
			criteria.add(Restrictions.eq("drugs", drugs));
			criteria.add(Restrictions.eq("patientId", new ContextUtil().getLoginId()));
			patMedicationHistories=criteria.list();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patMedicationHistories;
	}
	
	/*
	 * find formulary Detail list
	 * @author: Gopal Krishan jha
	 * (non-Javadoc)
	 * @return List of Formulary Detail Data 
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findFormularyDetailList()
	 */
	
	public List<FormularyDetail> findFormularyDetailList() {
		return getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class).list();
	}


	/*
	 * find current drugs for drugs optimizer..
	 * @author: GOpal Krishna jha 
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#fetchForumlarMedList(java.util.List, int)
	 */
	public List<FormularyDetail> fetchForumlarMedList(
			List<String> medicineList, int insuranceId) {
		logger.info("fetchForumlarMedList inside dao:::"+insuranceId);
		List<FormularyDetail>formularyDetaiList=new ArrayList<FormularyDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
							criteria.add(Restrictions.eq("insuranceId", insuranceId));
							criteria.add(Restrictions.in("medicineName", medicineList));
		formularyDetaiList=criteria.list();
		for(FormularyDetail fdetail:formularyDetaiList){
			fdetail.setIsFormulary(NO);
		    fdetail.setPatientCopay("Not Available");
		}
			
		return formularyDetaiList;
	}

	//Method to fetch Medicine List for Drug Price Optimizer, Author: LI-0011 in Lumbini Innovations
	public List<FormularyDetail> fetchForumlarMedList(int patientId, int insuranceId) {
		List<PatientMedicationData> medIdList;
		List<FormularyDetail> formularyMedList = new ArrayList<FormularyDetail>();
		//List<FormularyDetail> formularyMedicineList = new ArrayList<FormularyDetail>();
		List<FormularyDetail> medList = new ArrayList<FormularyDetail>();
		//FormularyDetail fdetel = new FormularyDetail();
		try
		{				
		Criteria criteria4Med = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria4Med.add(Restrictions.eq("patientId",patientId));
		medIdList= criteria4Med.list();
		for(PatientMedicationData pMedId:medIdList)
		{
			//String Query4MedList = "SELECT * FROM clinakos.ext_formulary  order by medicine_name";
			//SQLQuery query4Med = getSessionFactory().getCurrentSession().createSQLQuery(Query4MedList);
			Criteria criteria4ForumlaryMed = getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
			criteria4ForumlaryMed.add(Restrictions.eq("medicineName", pMedId.getDrugs()));
			criteria4ForumlaryMed.add(Restrictions.eq("insuranceId", insuranceId));
			medList=criteria4ForumlaryMed.list();	
			
			//System.out.println("Distinct Row are::"+medList.size());
			if(medList.size()>0)
			{
				FormularyDetail medFormularyDetail = medList.get(0);
				medFormularyDetail.setIsFormulary(YES);
				formularyMedList.add(medFormularyDetail);
			}
			else
			{
				FormularyDetail medFormularyDetail = new FormularyDetail();
				medFormularyDetail.setMedicineName(pMedId.getDrugs());
				medFormularyDetail.setIsFormulary(NO);
				formularyMedList.add(medFormularyDetail);
			}
		}
		}
		catch(Exception e)
		{
		   logger.debug("Exception came as::"+e.toString());		
		}
		return formularyMedList;
	}


	/*
	 * *******************************************************************method to fetch patient's medicine notes
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#getMedicineNotes()
	 */

		public List<PatientMedicineNotes> getMedicineNotes() {
			
			List<PatientMedicineNotes> patMedicineNotes= new ArrayList<PatientMedicineNotes>();
			try {
				/*String hql="FROM PatientMedicineNotes pmn , PatientMedicationData pmd where  pmd.drugs=pmn.drugName and pmn.patientId= :patientId";
				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				query.setParameter("patientId", new ContextUtil().getLoginId());
				patMedicineNotes=query.list();*/
				
				SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery("select pmn.drug_name,pmn.notes,pmn.date,pmn.id,pmn.user_id,pmn.patient_education from patient_medicine_notes pmn inner join patient_medication_data pmd on pmn.drug_name=pmd.drugs and pmn.user_id=pmd.user_id where pmn.user_id=:patientId");
						         sqlQuery.addEntity(PatientMedicineNotes.class);
						         sqlQuery.setInteger("patientId", new ContextUtil().getLoginId());
			   patMedicineNotes =sqlQuery.list();
				System.out.println("medication notes:::::::size============"+patMedicineNotes.size());
			} 
			
			catch (Exception e) 
			{
				logger.error("exception in getMedicineNotes patientMedicineDao:::::", e);
				e.printStackTrace();
			}
			
			return patMedicineNotes;
		}

		

		
/*
 * *************************************************************************method to save patient notes
 * @see com.clinakos.data.dao.IPatientMedicineDAO#savePatientMedicationNotes()
 */

	public void savePatientMedicationNotes(PatientMedicineNotes patientMedicineNotes) {
		logger.debug("method savePatientMedicationNotes starts in daoImpl::::::");
		save(patientMedicineNotes);		
	}


	/*
	 *find Doctor name according to doctor id::
	 *@author:gopal krishna jha.. 
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findDoctorNameAccordingToId(int)
	 */
	public String findDoctorNameAccordingToId(int doctorId) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
		
		criteria.setProjection(Projections.projectionList().
		         add(Projections.property("firstName") ). add(Projections.property("lastName") ));
                 criteria.add(Restrictions.eq("userId",doctorId));
        //  UserLoginDetail doctorDetail=new UserLoginDetail();
          //doctorDetail=(UserLoginDetail) criteria.list().get(0);
          String name = null;
          List<Object> doctorDetailList = criteria.list();
  		for(Object obj: doctorDetailList){
  		  Object[] row = (Object[]) obj;
  		
  		name=(String)row[0]+" "+(String)row[1];
  		  
  		  
  		}
          
		return name;
	}

	
		/*
		 * update patient medication table value formulary tier and compliance percentage
		 * @author: Gopal krishna jha..
		 * (non-Javadoc)
		 * @param patientMedicationDataList  
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#updatpatientMedicationForComplianceAndFormulary(java.util.List)
		 */
	public void updatpatientMedicationForComplianceAndFormulary(
			List<PatientMedicationData> patientMedicationDataList) {
		logger.info("inside this method updatpatientMedicationForComplianceAndFormulary()"+patientMedicationDataList.size());
		for(PatientMedicationData patMedData:patientMedicationDataList)
			update(patMedData);
		
	}


	//Method to fetch Medicine List for My formulary Look up by LI-0011 at Lumbini Innovations
	public List<FormularyDetail> fetchMyFormularyMedList(int patientId) {
		int insuranceId=0;
		try
		{
			Criteria criteria4insurance = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class) ;
			criteria4insurance.add(Restrictions.eq("userId",patientId));
			criteria4insurance.add(Restrictions.eq("primaryInsurance",YES));
			UserInsuranceDetails fdetel = new UserInsuranceDetails();
			fdetel= (UserInsuranceDetails) criteria4insurance.list().get(0);
			insuranceId=fdetel.getInsuranceId();
		}
		catch(Exception e)
		{
			logger.debug("Exception Came as"+e.toString());
		}
		return fetchForumlarMedList(patientId, insuranceId);
	}

	/*  Method to Optimize Drug Price for Doctor Profile based on selected member and medicine 
	by LI-0011 at Lumbini Innovations   */
	public List<FormularyDetail> optimizeDrugPrice(int patientId,
			int insuranceId, FormularyDetail[] selectedFDetel,String otherMed) {
		List<FormularyDetail> FormularyMedicine =  new ArrayList<FormularyDetail>();
		//FormularyDetail fdetel = new FormularyDetail();
		
		int i=0;
		int len=selectedFDetel.length;
		String MedName;
		String patientCopay;
		
		try
		{
		 if(otherMed!="")
		  {
			len=len+1;
		  }
		 for(i=0;i<len;i++)
		  {
		    if(i==selectedFDetel.length)
		    {
			  MedName=otherMed;
			  Criteria cr4Copay=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
			  cr4Copay.add(Restrictions.eq("medicineName",MedName));
			  cr4Copay.add(Restrictions.eq("insuranceId",insuranceId));
			  if(cr4Copay.list().size()>0)
			  {
			    FormularyDetail fdtl=(FormularyDetail) cr4Copay.list().get(0);
			    patientCopay=fdtl.getPatientCopay();
			  }
			  else
			  {
			    patientCopay="";
			  }
		    }
		   else
		    {
			  MedName=selectedFDetel[i].getMedicineName();
			  patientCopay=selectedFDetel[i].getPatientCopay();
		    }
			
			Criteria criteria4Therapy= getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
			criteria4Therapy.add(Restrictions.eq("medicineName", MedName));
			criteria4Therapy.add(Restrictions.eq("insuranceId", insuranceId));
			List<FormularyDetail> formTherapy = criteria4Therapy.list();
			String copay;
			int saving;
			FormularyDetail formDetel = new FormularyDetail();
			if(formTherapy.size()>0)
			  {
				formDetel= formTherapy.get(0);
				//System.out.println("Therapy is ::: "+formDetel.getTherapyType());
				
				Criteria criteria4MedList = getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
				criteria4MedList.add(Restrictions.eq("insuranceId", insuranceId));
				criteria4MedList.add(Restrictions.eq("therapyType", formDetel.getTherapyType()));
				criteria4MedList.add(Restrictions.ne("medicineName", MedName));	
				
				List<FormularyDetail> resultList = criteria4MedList.list();		
				
						if(resultList.size()>0)
						{
							for(FormularyDetail fdet:resultList)
							{
							  FormularyDetail formDetail = new FormularyDetail();
							  formDetail.setMedicineName(MedName);
							  formDetail.setPatientCopay(patientCopay);						  
							  formDetail.setAlternateMedicine(fdet.getMedicineName());		
							  int lastIndex=fdet.getPatientCopay().length();
							  char prefix= fdet.getPatientCopay().charAt(0);
							  String newcopay = fdet.getPatientCopay().substring(1,lastIndex);
							  copay= patientCopay.substring(1,patientCopay.length());
							  int copayVal=Integer.parseInt(copay) ;
							  int newCopayVal= Integer.parseInt(newcopay);
							  saving= copayVal - newCopayVal ;
							  
							  String save= Integer.toString(saving);
							  char prefixNew=save.charAt(0);
							  if(String.valueOf(prefixNew).equals("-"))
							  {
								  save=save.replace(prefixNew, prefix);
								  save=prefixNew+save;
							  }
							  else
							  {
								  save=prefix+save;
							  }
							  formDetail.setSaving(save);
					            
							  FormularyMedicine.add(formDetail);
							}					  
						}
						else
						{
							FormularyDetail medFormularyDetail = new FormularyDetail();
							medFormularyDetail.setMedicineName(MedName);
							medFormularyDetail.setPatientCopay(patientCopay);	
							medFormularyDetail.setAlternateMedicine(" ");
							medFormularyDetail.setSaving(" ");
							FormularyMedicine.add(medFormularyDetail);
						}
					
			   }
			
			  else
			   {
				 FormularyDetail medFormularyDetail = new FormularyDetail();
				 medFormularyDetail.setMedicineName(MedName);
				 medFormularyDetail.setPatientCopay(patientCopay);	
				 medFormularyDetail.setAlternateMedicine(" ");
				 FormularyMedicine.add(medFormularyDetail);
			   }
					
		   }
		}
		catch(Exception e)
		{
			logger.debug("Exception came while Optimizing Drugh:"+e.toString());
		}
		return FormularyMedicine;
	}

	public List<PatientMedicationData> findpatientMedicationSummaryList(int patientId) {
		List<PatientMedicationData> medicationDataList=new ArrayList<PatientMedicationData>();
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class)
				.add(Restrictions.eq("patientId", patientId));
		medicationDataList= criteria.list();
		for (PatientMedicationData patientMedicationData : medicationDataList) {
			patientMedicationData.setPrescriberName(findDoctorNameAccordingToId(patientMedicationData.getDoctorId()));
			patientMedicationData.setGenericMedicine(findMedicineImage(patientMedicationData.getDrugs()));
			System.out.println("::::"+patientMedicationData.getGenericMedicine().toString()+"size of generic medicine list:::"+patientMedicationData.getGenericMedicine().size());
		}
		
		return medicationDataList;
	}
	/*
	 * *****method to find generic medicine image
	 */
	public List<String> findMedicineImage(String drugs) {
				logger.info("inside findMedicineImage method::::::::");
				List<GenericMedicine> gmList=new ArrayList<GenericMedicine>();
				List<String> medicineNameForImage= new ArrayList<String>();
				Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedicine.class);
				criteria.add(Restrictions.eq("genericMedicine", drugs));
				
				ProjectionList projectionList = Projections.projectionList();
				projectionList.add(Projections.property("medicineImageName"), "medicineImageName");
				
				criteria.setProjection(projectionList);
				gmList = criteria.setResultTransformer(
						Transformers.aliasToBean(GenericMedicine.class)).list();
				gmList = criteria.list();
				for (GenericMedicine gm : gmList) {
					System.out.println("findMedicineImageNew :::::::"+gm.getMedicineImageName()+"::"+gm.getGenericMedicine());
					medicineNameForImage.add(gm.getMedicineImageName());
				}
				
				
				
				return medicineNameForImage;
			}
	
	
	
/**
 * Get Future Impacted Medicine for Pharmecgenomic Profile 
 */
	public List<PharmacogenomicsRecomendations> showRecommendedFutureMedicine(int patientId) {
		List<PharmacogenomicsRecomendations> pharmecogenomicRecomandationList=new ArrayList<PharmacogenomicsRecomendations>();
		try {
			logger.info( "showRecommendedFutureMedicine()" );
			/*Criteria crtForPhenoGenoType=getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsUserSummary.class);
			                             crtForPhenoGenoType.add(Restrictions.eq("userId", new ContextUtil().getPatientId()));
			List<PharmacogenomicsUserSummary> pharmcoSummaryList=crtForPhenoGenoType.list();  
			List<String> geneSymbolList=new ArrayList<String>();
			List<String> genoTypeList=new ArrayList<String>();
			List<String> phenoTypeList=new ArrayList<String>();
			for(PharmacogenomicsUserSummary pharmcoSummary:pharmcoSummaryList){
				geneSymbolList.add(pharmcoSummary.getGeneSymbol());
				genoTypeList.add(pharmcoSummary.getGenoType());
				phenoTypeList.add(pharmcoSummary.getPhenoType());
			}
		    Criteria patMedCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		                            patMedCriteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
		    List<PatientMedicationData> patMedicineList=patMedCriteria.list();
		    List<String> patMedList=new ArrayList<String>();
		    for(PatientMedicationData patMedData:patMedicineList){
		    	patMedList.add(patMedData.getDrugs());
		    }
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsRecomendations.class);
			                 criteria.createAlias("pharmacogenomicsId", "pharmaco");
		    	             criteria.add(Restrictions.in("pharmaco.geneSymbol", geneSymbolList));
		    	             criteria.add(Restrictions.in("pharmaco.phenoType", phenoTypeList));
		   
		    Disjunction disjunction=Restrictions.disjunction(); 	             
		    	  for(String genoType:genoTypeList){
		             disjunction.add(Restrictions.like("genoType", genoType, MatchMode.ANYWHERE));
		    	  }
		    	  for(String patMed:patMedList){
		    		 criteria.add(Restrictions.ne("drugName",patMed));
		    	  }
		    	 
			pharmecogenomicRecomandationList=criteria.list();*/
			
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getFutureImpcatedMedicine("+patientId+")}");
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				PharmacogenomicsRecomendations recomendations=new PharmacogenomicsRecomendations();
				String gene=resultSet.getString("gene");
				String drugName=resultSet.getString("drugName");
				String drugClass=resultSet.getString("drugClass");
				String recommendation=resultSet.getString("recommendation");
				String impact=resultSet.getString("impact");
				String implications=resultSet.getString("implications");
				recomendations.setDrugName(drugName);
				recomendations.setDrugClass(drugClass);
				recomendations.setImpact(impact);
				recomendations.setImplications(implications);
				recomendations.setRecommendation(recommendation);
				recomendations.setImpactingGene(gene);
				//System.out.println("impactingGene::::proc_curentImpactedMedicine="+gene+":::"+recomendations.getImpactingGene());
				pharmecogenomicRecomandationList.add(recomendations);
				
			}
			
			System.out.println("pharmecogenomicRecomandationList.size()"+pharmecogenomicRecomandationList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pharmecogenomicRecomandationList;
	}

	/*
	 * find alternative medicine using formulary according to medicine
	 * @author: Gopal krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findAlternativeMedicine(java.lang.String, int)
	 */
	public List<String> findAlternativeMedicine(String drugs, int insuranceId) {
		logger.info("findAlternativeMedicine method in PatientMedicineDaoiMpl:::"+drugs+"::"+insuranceId);
		 DetachedCriteria bolgsEntries = DetachedCriteria.forClass(FormularyDetail.class)  
		            .setProjection( Property.forName("therapyType"));
		 bolgsEntries.add(Restrictions.eq("insuranceId", insuranceId));
		 bolgsEntries.add(Restrictions.eq("medicineName", drugs));
		
		 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class) ;  
		 criteria.add(Restrictions.ne("medicineName", drugs) ); 
		 criteria.add(Restrictions.eq("insuranceId", insuranceId) ); 
        // .add( Subqueries.geAll("formulayTier", bolgsEntries) ) ; 
		 criteria.add( Property.forName("therapyType").eq(bolgsEntries) ); 
        // .list();  
		List<String>alternativeMedicineList=new ArrayList<String>(); 
		 List<FormularyDetail>formularyDetailList=new ArrayList<FormularyDetail>();
		 formularyDetailList=criteria.list();
		 //System.out.println("::::::::size:::"+formularyDetailList.size());
		 for(FormularyDetail formDetail:formularyDetailList)
		 {
			// System.out.println(":::medicine name::"+formDetail.getMedicineName());
			 alternativeMedicineList.add(formDetail.getMedicineName());
		 }
		
		return alternativeMedicineList;
	}	
	
	
/*
 * *********************************method to find impacted medicine for Pharmacogenomics
 * @see com.clinakos.data.dao.IPatientMedicineDAO#findImpactedMedication(int)
 */
	public List<PharmacogenomicsRecomendations> findImpactedMedication(int patientId) {
		logger.info("findImpactedMedication starts in dao layer::::::::");
		List<PharmacogenomicsRecomendations> impactMedicineList=new ArrayList<PharmacogenomicsRecomendations>();
		try{
		
		/*List<String> phenoTypeList= new ArrayList<String>(); //-------list to save phenoType of patient
		List<String> geneSymbolList= new ArrayList<String>(); //---------list to save gene of patient
		List<String> genoTypeList= new ArrayList<String>(); //---------list to save genoType
		String doseValue="dose"; //---------String passed in method findDoseOrRegimen
		String regimenValue="regimen"; //---------String passed in method findDoseOrRegimen
		
		
			
	//---------------- To find out gene and phenotype of particular patient
		List<PharmacogenomicsUserSummary> pharmacogenomicsList= new ArrayList<PharmacogenomicsUserSummary>();
		Criteria criteria1=getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsUserSummary.class,"pharmoc");
		criteria1.add(Restrictions.eq("pharmoc.userId", patientId));
		pharmacogenomicsList=criteria1.list();
		for (PharmacogenomicsUserSummary pharmacogenomicsUserSummary : pharmacogenomicsList) {
			phenoTypeList.add(pharmacogenomicsUserSummary.getPhenoType());//-----------adding value to phenoTypeList
			geneSymbolList.add(pharmacogenomicsUserSummary.getGeneSymbol());//----------------adding value to geneSymbolList
			genoTypeList.add(pharmacogenomicsUserSummary.getGenoType());//----------------adding value to genoTypeList
		}
		
		logger.info("medicationNameList::::"+medicationNameList.toString());
		logger.info("geneSymbolList::::::"+geneSymbolList.toString());
		logger.info("phenotype:::::::"+phenoTypeList.toString());
			
		Criteria criteria2=getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsRecomendations.class);
		criteria2.createAlias("pharmacogenomicsId", "pharmacogenomicsId");
		criteria2.add(Restrictions.in("drugName", medicationNameList));
		criteria2.add(Restrictions.in("pharmacogenomicsId.geneSymbol", geneSymbolList));
		criteria2.add(Restrictions.in("pharmacogenomicsId.phenoType", phenoTypeList));
		
		Disjunction disjunction=Restrictions.disjunction();
		for(String genoType:genoTypeList){
			disjunction.add(Restrictions.like("pharmacogenomicsId.genoType", genoType, MatchMode.ANYWHERE));
    	  }
		
		impactMedicineList=criteria2.list();
		
		for (PharmacogenomicsRecomendations ph : impactMedicineList) {
			ph.setDose(findDoseOrRegimen(ph.getDrugName(), patientId,doseValue));
			ph.setRegimen(findDoseOrRegimen(ph.getDrugName(), patientId,regimenValue)); 
			logger.info("value of dose="+ph.getDose()+"::reigmen="+ph.getRegimen());
		}*/
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getCurrentImpactedMedicine("+patientId+")}");
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				PharmacogenomicsRecomendations pharmacogenomicsRecomendations=new PharmacogenomicsRecomendations();
				pharmacogenomicsRecomendations.setDrugName(resultSet.getString("drugs"));
				pharmacogenomicsRecomendations.setImpact(resultSet.getString("impact"));
				pharmacogenomicsRecomendations.setImplications(resultSet.getString("implications"));
				pharmacogenomicsRecomendations.setRecommendation(resultSet.getString("recommendation"));
				pharmacogenomicsRecomendations.setDose(resultSet.getString("strengths"));
				pharmacogenomicsRecomendations.setRegimen(resultSet.getString("directions"));
				pharmacogenomicsRecomendations.setImpactingGene(resultSet.getString("gene1"));
				pharmacogenomicsRecomendations.setAttentionRating(resultSet.getString("attention_rating"));
				pharmacogenomicsRecomendations.setOvaleMessage(resultSet.getString("message"));
				pharmacogenomicsRecomendations.setPhenotype(resultSet.getString("phenotype1"));
				pharmacogenomicsRecomendations.setPatientMedicationDataId(Integer.parseInt(resultSet.getString("pmd_id")));
				
				pharmacogenomicsRecomendations.setDrugForCompareWithFutureImpactedMedicine(resultSet.getString("pgi_drugs"));
				
				impactMedicineList.add(pharmacogenomicsRecomendations);
			}
		
		logger.info(":sizee:::::::::"+impactMedicineList.size());
		
		}
		catch(Exception e){
			logger.error("exception in findMedicationListForPharmacogenomics at dao layer::::::::", e);
			e.printStackTrace();
		}
		return impactMedicineList;
	}

	
	public List<ClinicQuestionnaire> findClinicQuestionList(int clinicProviderId) {
		
		return getSessionFactory().getCurrentSession().createCriteria(ClinicQuestionnaire.class)
				.add(Restrictions.eq("clinicProviderId", clinicProviderId)).list();
	}

	/*
	 * find clinic diagnosis list according to clinic
	 * @author:Gopal Krishna Jha
	 */
	public List<ClinicDiagonsis> findClinicDiagnosisList(String  medicineName) {
		logger.info("findClinicDiagnosisList::::::::"+medicineName);
		List<ClinicDiagonsis> cininicDiagnosisList=new ArrayList<ClinicDiagonsis>();
			//List cininicDiagnosisList = getSessionFactory().getCurrentSession().createQuery("FROM ClinicDiagonsis").list(); 
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicDiagonsis.class);
					criteria.add(Restrictions.eq("medicineName", medicineName));
		 cininicDiagnosisList=criteria.list();
			//System.out.println(":::::size:::"+cininicDiagnosisList.size());
		 for (Iterator iterator = cininicDiagnosisList.iterator(); iterator.hasNext();){
			 ClinicDiagonsis clinicDiagnosis = (ClinicDiagonsis) iterator.next();
			 // System.out.println(":::::::name:"+clinicDiagnosis.getDiagnosisName());
			  List labDetail = clinicDiagnosis.getClinicDiagonsisLabList();
			  	
			  for (Iterator iterator2 =labDetail.iterator(); iterator2.hasNext();){
				  ClinicDiagonsisLab labType = (ClinicDiagonsisLab) iterator2.next(); 
				  //System.out.println(":::::::::heloo "+labType.getId()+":::"+labType.getLabName());
			       
			  }
			}
		 
		//System.out.println("::::::::"+cininicDiagnosisList.size());
		return cininicDiagnosisList;
	}
	
	

	/*
	 * find Acceptable range  according to lab
	 * @author:Gopal Krishna Jha
	 */
	public List<String> findLabFrequencyRange(String labName ) {
		logger.info("findLabFrequencyRange::::::::"+labName);
		List<Integer>clinicDiagnosisIdList=new ArrayList<Integer>();
		List<String>labFrequencyList=new ArrayList<String>();
		List<ClinicDiagonsisLab>ClinicDiagonsisLabLabList=getSessionFactory().getCurrentSession().createCriteria(ClinicDiagonsisLab.class)
				.add(Restrictions.eq("labName", labName)).list();
				//.setProjection( Projections.projectionList().
				//add( Projections.property("clinicDiagonsis") )).
				for(ClinicDiagonsisLab cl:ClinicDiagonsisLabLabList)
				{
					//System.out.println("::::::::::::"+cl.getId()+":::"+cl.getClinicDiagnosisId()+":::class::"+cl.getClinicDiagonsis().getId());
					clinicDiagnosisIdList.add(cl.getClinicDiagonsis().getId());
				}
		
		//System.out.println("::::::::::::::::::::::"+clinicDiagnosisIdList);
				if(!clinicDiagnosisIdList.isEmpty())
				{
				labFrequencyList= getSessionFactory().getCurrentSession().createCriteria(ClinicLabDetails.class)
				.add(Restrictions.in("clinicDiagnosisLabId", clinicDiagnosisIdList))
				.setProjection(Projections.distinct(Projections.property("labRange")))
				       
				.list();
				}
		
		return labFrequencyList;
	}


	/*
	 * find regimen list
	 * @author: gopal krishna jha
	 */
	
	public List<String> findRegimenList() {
		logger.info("inside findRegimenList in dao impl");
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(SigCode.class);
		
		criteria.setProjection(Projections.projectionList().
		         add(Projections.property("sigCode") ));
                 
		List<String>regimenList=criteria.list();
		System.out.println(":::::::regimenList::"+regimenList.size());
		return regimenList;
	}
	
	/*
	 * find medicine history for anticoag clinic
	 * @author: Gopal Kirshna JHA ..
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findMedicineHistoryForAnticoag(int)
	 */
	
	public List<PatientMedicationHistory> findMedicineHistoryForAnticoag(
			int patientId) {
		logger.info("inside findMedicineHistoryForAnticoag method::::"+patientId);
		
		List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
		List<String>medicineList=new ArrayList<String>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(Medicine.class);
		criteria.setProjection(Projections.projectionList().
		         add(Projections.property("medicinName") ));
		criteria.add(Restrictions.eq("clinicName", ANTICOAG));
		medicineList=criteria.list();
		//System.out.println(":::::::::::"+medicineList.size()+":::"+medicineList);
		if(medicineList.size()>=1)
		{
			patientMedicationHistoryList=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class)
										.add(Restrictions.in("drugs", medicineList)).setMaxResults(NUMBER_OF_ROW_IN_MED_HISTORY)
										.add(Restrictions.eq("patientId", patientId))
										.addOrder(Order.desc("lastFillDate")).list();
		}
                 
		return patientMedicationHistoryList;
	}
	
	/*
	 * modify medicine by user save it to patient medication data and delete previous medicine and insert it into history
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#modifyMedicineChange(com.clinakos.data.model.patient.PatientMedicationData, com.clinakos.data.model.patient.PatientMedicationData)
	 */
	public void modifyMedicineChange(
			PatientMedicationData currentpatientMedicationData,
			PatientMedicationData changePatientMedicineBackUpdata,String deleteMedActionPlanOrUpdate) {
		logger.info(changePatientMedicineBackUpdata.getDrugId()+"modifyMedicineChange method in dao impl::::::"+currentpatientMedicationData.getDrugId());
		System.out.println(currentpatientMedicationData.getStrengths()+":::::::::"+changePatientMedicineBackUpdata.getStrengths());
		try{
			if(deleteMedActionPlanOrUpdate.equalsIgnoreCase("Yes"))
			{
				Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan   where drugId =:drugId and patientId =:patientId");
				//query.setString("dose", currentpatientMedicationData.getStrengths());
				//query.setDate("lastUpdateDate", new Date());
				//query.setDouble("latestdrugId", currentpatientMedicationData.getDrugId());
				
				query.setDouble("drugId", changePatientMedicineBackUpdata.getDrugId());
				query.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
				int result = query.executeUpdate();
				System.out.println(":::::::::::result:::"+result);
				
				Query genericMedPlanQuery =getSessionFactory().getCurrentSession().createQuery("delete ParentMedActionPlan where drugId =:drugId and patientId =:patientId");
				/*genericMedPlanQuery.setString("drugId", String.valueOf((int)changePatientMedicineBackUpdata.getDrugId()));*/
				genericMedPlanQuery.setDouble("drugId", changePatientMedicineBackUpdata.getDrugId());
				genericMedPlanQuery.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
				int genericMedPlanResult = genericMedPlanQuery.executeUpdate();
				logger.info("genericMedPlanResult after deleting med action plan "+genericMedPlanResult);
				
				Query overviewupdate = getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData  set directions = :latestDirections,strengths=:latestStrength,unit=:latestUnit where drugId=:drugId and patientId=:patientId");
				overviewupdate.setString("latestDirections", currentpatientMedicationData.getDirections());
				overviewupdate.setString("latestStrength", currentpatientMedicationData.getStrengths());
				overviewupdate.setString("latestUnit", currentpatientMedicationData.getUnit());
				overviewupdate.setDouble("drugId", changePatientMedicineBackUpdata.getDrugId());
				overviewupdate.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
				int overviewupdateResult  = overviewupdate.executeUpdate();
				logger.info("after updating :::overview patientMedicationdata list::::::::"+overviewupdateResult);
			}
			else
			{
			Query query =getSessionFactory().getCurrentSession().createQuery("update MedActionPlan  set dose =:dose, regimen =:regimen,unit =:unit,lastUpdateDate=:lastUpdateDate ,drugId=:latestdrugId " +
					"where drugId =:drugId and patientId =:patientId");
			query.setString("dose", currentpatientMedicationData.getStrengths());
			query.setString("regimen", currentpatientMedicationData.getDirections());
			query.setParameter("lastUpdateDate", new Date());
			
			System.out.println("lastUpdateDate:::::::"+new Date());
			query.setDouble("latestdrugId", currentpatientMedicationData.getDrugId());
			query.setString("unit", currentpatientMedicationData.getUnit());
			query.setDouble("drugId", changePatientMedicineBackUpdata.getDrugId());
			query.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
			int result = query.executeUpdate();
			System.out.println(":::::::::::result:::"+result);
			//For updating Generic Med Action Plan 
			Query generalMedPlanQuery=getSessionFactory().getCurrentSession().createQuery("update ParentMedActionPlan set strength=:modifiedStrength,regimen=:modifiedRegimen, unit=:latestunit,drugId=:latestDrugId where drugId=:drugid and patientId=:patientId");
			generalMedPlanQuery.setParameter("modifiedStrength", currentpatientMedicationData.getStrengths());
			generalMedPlanQuery.setParameter("modifiedRegimen", currentpatientMedicationData.getDirections());
			generalMedPlanQuery.setParameter("latestunit", currentpatientMedicationData.getUnit());
			generalMedPlanQuery.setParameter("latestDrugId",currentpatientMedicationData.getDrugId());
			generalMedPlanQuery.setParameter("drugid",changePatientMedicineBackUpdata.getDrugId());
			generalMedPlanQuery.setParameter("patientId", changePatientMedicineBackUpdata.getPatientId());
			int genralMedPlanUpdateQuery=generalMedPlanQuery.executeUpdate();
			logger.info("after updating genral med action plan query "+genralMedPlanUpdateQuery);
			if(result!=0)
				currentpatientMedicationData.setFlagForMedActionPlan(false);
			}	
			
		savePatientMedHistoryAndDeletePatientMedicationData(changePatientMedicineBackUpdata);
		getSessionFactory().getCurrentSession().save(currentpatientMedicationData);
		
		//System.out.println(":::::::::::drug id::"+changePatientMedicineBackUpdata.getDrugId());
		//logger.info("changed reqgimen and strength "+currentpatientMedicationData.getStrengths() +" regimen "+currentpatientMedicationData.getDirections());
		
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
	/*
	 * delete medicine changes from patient medicine data and insert in patient medicine history and delete also med action plan if available on particular meds
	 * @authr: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#deleteMedicineChanges(com.clinakos.data.model.patient.PatientMedicationData)
	 */
	
	public void deleteMedicineChanges(
			PatientMedicationData changePatientMedicineBackUpdata) {
		logger.info("deleteMedicineChanges on dao:::::"+changePatientMedicineBackUpdata.getId());
		PatientMedicationData modifiedMedicationData=changePatientMedicineBackUpdata;
		
		try{
		
		//changePatientMedicineBackUpdata.setDateWithTimeZoneForCompare(new DateUtil().convertDateFormatUsingFormat(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));

		savePatientMedHistoryAndDeletePatientMedicationData(changePatientMedicineBackUpdata);
	
		
		
		//delete med action plan if avalable..
		System.out.println(":::::::::::drug id::"+modifiedMedicationData.getDrugId());
		
		Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan where drugId =:drugId and patientId =:patientId");
		query.setDouble("drugId", modifiedMedicationData.getDrugId());
		query.setInteger("patientId", modifiedMedicationData.getPatientId());
		int result = query.executeUpdate();
		System.out.println(":::::::::::result:::"+result);
		
		Query genericMedPlanQuery =getSessionFactory().getCurrentSession().createQuery("delete ParentMedActionPlan where drugId =:drugId and patientId =:patientId");
		genericMedPlanQuery.setDouble("drugId", modifiedMedicationData.getDrugId());
		genericMedPlanQuery.setInteger("patientId", modifiedMedicationData.getPatientId());
		int genericMedPlanResult = genericMedPlanQuery.executeUpdate();
		logger.info("genericMedPlanResult after deleting med action plan "+genericMedPlanResult);
		
		Query alertMedActionLab =getSessionFactory().getCurrentSession().createQuery("delete AlertGenericMedActionLab where drugId =:drugId and patientId =:patientId");
		alertMedActionLab.setString("drugId", String.valueOf((int)modifiedMedicationData.getDrugId()));
		alertMedActionLab.setInteger("patientId", modifiedMedicationData.getPatientId());
		int alertMessage = alertMedActionLab.executeUpdate();
		System.out.println("alertMedActionLab:::::::::::::"+alertMessage);
		
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	/*
	 * delete patient medication data and insert into history
	 * @author: Gopal Krishna jha
	 */
	private void savePatientMedHistoryAndDeletePatientMedicationData(PatientMedicationData changePatientMedicineBackUpdata) 
		{
		logger.info("savePatientMedHistoryAndDeletePatientMedicationData:::::::::::::");
		PatientMedicationHistory patientMedicineHistory=new PatientMedicationHistory();
		patientMedicineHistory.setPatientId(changePatientMedicineBackUpdata.getPatientId());
		patientMedicineHistory.setStrength(changePatientMedicineBackUpdata.getStrengths());
		patientMedicineHistory.setProviderId(changePatientMedicineBackUpdata.getProviderId());
		patientMedicineHistory.setDrugs(changePatientMedicineBackUpdata.getDrugs());
		patientMedicineHistory.setQuantity(changePatientMedicineBackUpdata.getQuantity());
		patientMedicineHistory.setReffils(changePatientMedicineBackUpdata.getReffils());
		patientMedicineHistory.setStartDate(changePatientMedicineBackUpdata.getStartDate());
		patientMedicineHistory.setMedicineStatus(DISCONTINUE_MEDS);
		patientMedicineHistory.setDirection(changePatientMedicineBackUpdata.getDirections());
		patientMedicineHistory.setPrescriberID(changePatientMedicineBackUpdata.getDoctorId());
		patientMedicineHistory.setDrugId(changePatientMedicineBackUpdata.getDrugId());
		patientMedicineHistory.setDrugNameId(changePatientMedicineBackUpdata.getDrugNameId());
		patientMedicineHistory.setDataProvider(changePatientMedicineBackUpdata.getDataProvider());
		patientMedicineHistory.setNotes(changePatientMedicineBackUpdata.getNotes());
		patientMedicineHistory.setUnit(changePatientMedicineBackUpdata.getUnit());
		patientMedicineHistory.setUnitDetail(changePatientMedicineBackUpdata.getUnitDetail());
		patientMedicineHistory.setByRoute(changePatientMedicineBackUpdata.getByRoute());
		patientMedicineHistory.setDosageForm(changePatientMedicineBackUpdata.getDosageForm());
		patientMedicineHistory.setPrescriptionGuid(changePatientMedicineBackUpdata.getPrescriptionGuid());
		patientMedicineHistory.setPrescriberName(changePatientMedicineBackUpdata.getPrescriberName());
		patientMedicineHistory.setPrescriptionStatus(changePatientMedicineBackUpdata.getPrescriptionStatus());
		patientMedicineHistory.setPrescriptionSubStatus(changePatientMedicineBackUpdata.getPrescriptionSubStatus());
		patientMedicineHistory.setFlagForVisit(true);
		patientMedicineHistory.setActionDate(new Date());
		//patientMedicineHistory.setDateWithTimeZoneForCompare(new DateUtil().convertDateFormatUsingFormat(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		patientMedicineHistory.setDateTimeZoneForCompare(new DateUtil().convertDateFormatUsingFormat(new Date(), "yyyy-MM-dd'T'HH:mm:ss.SSSZ"));
		
		getSessionFactory().getCurrentSession().save(patientMedicineHistory);
		
		//delete patient medicine data
		getSessionFactory().getCurrentSession().delete(changePatientMedicineBackUpdata);
		
	}
	
	
	public void saveNewCropDiscontinueMedsData(PatientMedicationHistory patHis) {
		// TODO Auto-generated method stub
		System.out.println("drug_ID:::::::::"+patHis.getDrugId());
		getSessionFactory().getCurrentSession().save(patHis);
	}


	/*
	 * save med action plan 
	 * @author:Gopal Krishna jha
	 */
	public void saveMedActionPlan(List<MedActionPlan> medicalActionPlanList)
	{
		logger.info("saveMedActionPlan method start in dao");
		
		try{
			List<Double>drugIdList=new ArrayList<Double>();
			
			String todayDate=new DateUtil().DateInMySqlPattern(new Date());
			for(MedActionPlan med:medicalActionPlanList)
				{
				System.out.println("MedActionPlan:::list::"+med.getDrugId()+"LLL"+med.getMedicineName());
				
				drugIdList.add((med.getDrugId()));
				System.out.println("::::::::"+med.getDrugId());
				}
			if(!drugIdList.isEmpty())
				{
				//Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan where drugId not in (:drugId) and patientId =:patientId");
				Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan where drugId  in (:drugId) and patientId =:patientId");
				query.setParameterList("drugId", drugIdList);
				query.setInteger("patientId", new ContextUtil().getPatientId());
				int result = query.executeUpdate();
				System.out.println(":::::::::::result:::"+result);
				}
			
			for(MedActionPlan medactionplan:medicalActionPlanList)
			{
				getSessionFactory().getCurrentSession().save(medactionplan);
				/*int result=0;
				Query query =getSessionFactory().getCurrentSession().createQuery("update MedActionPlan " +
						"set medicineName =:medicineName,dose=:dose,diagnosis=:diagnosis,lab=:lab,acceptableRange=:acceptableRange,labFrequency=:labFrequency, " +
						"regimen=:regimen, lastUpdateDate=:lastUpdateDate  where drugId =:drugId and patientId =:patientId");
				query.setString("medicineName", medactionplan.getMedicineName());
				query.setString("dose", medactionplan.getDose());
				query.setString("diagnosis", medactionplan.getDiagnosis());
				query.setString("lab", medactionplan.getLab());
				
				query.setString("acceptableRange", medactionplan.getAcceptableRange());
				query.setString("labFrequency", medactionplan.getLabFrequency());
				query.setString("regimen", medactionplan.getRegimen());
				query.setDate("lastUpdateDate",new Date());
				query.setDouble("drugId", medactionplan.getDrugId());
				query.setInteger("patientId", medactionplan.getPatientId());
				
				 result = query.executeUpdate();
				System.out.println("211:::::::::::result:::"+result);
				if(result==0)
				{
					String insert_medActionPlan="insert into med_action_plan (patient_id,doctor_id,clinic_provider_id,medicine_name,dose,notes,diagnosis," +
							"lab,acceptable_range,lab_frequency,regimen,action_date,last_update_date,drugId,drugNameId) values " +
							"("+medactionplan.getPatientId()+","+medactionplan.getDoctorId()+","+medactionplan.getClinicProviderID()+",'"+medactionplan.getMedicineName()+"'," +
									"'"+medactionplan.getDose()+"' ,'"+medactionplan.getNotes()+"','"+medactionplan.getDiagnosis()+"','"+medactionplan.getLab()+"'" +
											",'"+medactionplan.getAcceptableRange()+"','"+medactionplan.getLabFrequency()+"','"+medactionplan.getRegimen()+"'," +
													" '"+todayDate+"','"+todayDate+"' ,'"+medactionplan.getDrugId()+"','"+medactionplan.getDrugNameId()+"')";
					Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_medActionPlan);
					sqlQuery1.executeUpdate();
				}
				*/
			}
			
			//saveValueForIntegration(patientId, false);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/*
	 * save medaction plan after clicking on visit summary in db
	 * @author: Gopal Krishna jha::
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#saveMedDActionPlan(com.clinakos.data.model.patient.MedActionPlan)
	 */
	
	public void saveMedDActionPlan(List<MedActionPlan> medicalActionPlanList ,VisitHistory visitHistory, 
			List<PatientMedicationData> addNewmedicationListForPatient, List<PatientMedicationData> removeDuplicateModifyMedicationListForPatient, 
			List<Integer> careTeamMemberListForReconcile) {
		logger.info(addNewmedicationListForPatient.size()+"inside saveMedDActionPlan method in dao impl::::::::::"+removeDuplicateModifyMedicationListForPatient.size());
		
		for(PatientMedicationData visitHistoryDetail:removeDuplicateModifyMedicationListForPatient)
		{
			System.out.println("::::::::::::::><><"+visitHistoryDetail.getDrugs());
		}
		
		//saving or deleting med action plan start patientId
		saveMedActionPlan(medicalActionPlanList);
		//saving or deleting med action plan start
			save(visitHistory);
			
		
			//for checking..
			//removeDuplicateModifyMedicationListForPatient=new ArrayList<PatientMedicationData>();
		for(PatientMedicationData visitHistoryDetail:removeDuplicateModifyMedicationListForPatient)
		{
			//remove all string value from quantity..
			String str = visitHistoryDetail.getQuantity();
			if(!(str==null))
				str = str.replaceAll("[^\\d.]", "");
			visitHistoryDetail.setQuantity(str);
			
			
			//discontinue meds delete and insert..
			visitHistoryDetail.setCauseOfAddMeds(visitHistoryDetail.getMedicineStatus());
			if(visitHistoryDetail.getMedicineStatus().equals(DISCONTINUE_MEDS))
			{
				
				//System.out.println("::::::::::::::::discontMeds::"+visitHistoryDetail.getMedicineStatus());
				PatientMedicationHistory medHistory=new PatientMedicationHistory();
				medHistory=findDetailOFMedHistory(visitHistoryDetail.getDrugs(),visitHistoryDetail.getDrugId(),new ContextUtil().getPatientId(),new ContextUtil().getProviderId());
				//System.out.println("::::::::::::history::::"+medHistory.getDrugs());
				
				save(medHistory);
				System.out.println("inside save dao::"+medHistory.getDrugs()+"::::::::::::::::"+medHistory.getStrength()+":::drug id"+medHistory.getDrugId());
				
				//for deleting med action plan if available...
				/*String hql = "delete from MedActionPlan  where medicineName= :medicineName";
				int result=getSessionFactory().getCurrentSession().createQuery(hql).setString("medicineName", visitHistoryDetail.getDrugs()).executeUpdate();
				System.out.println(":::::::::::no of row deleted:::"+result);*/
				
			}
			//new medicine added 
			else if(visitHistoryDetail.getMedicineStatus().equals("New Added Meds") || visitHistoryDetail.getMedicineStatus().equals("Reconcile Meds")|| visitHistoryDetail.getMedicineStatus().equals(MODIFY_MEDS))
			{
				//System.out.println("::::"+visitHistoryDetail.getDrugs()+":::::");
				/*if(visitHistoryDetail.getReffils()==0)
					visitHistoryDetail.setReffils(1);*/
				System.out.println("1111222:::"+visitHistoryDetail.getDrugs()+"::getQuantity:::"+visitHistoryDetail.getQuantity()+":getDirections::"+
						visitHistoryDetail.getDirections()+"::getStrengths:"+visitHistoryDetail.getStrengths()+":getReffils::"+visitHistoryDetail.getReffils());
				getSessionFactory().getCurrentSession().saveOrUpdate(visitHistoryDetail);
				//save(visitHistoryDetail);
			}
			//other medcicine only updated...
			else
			{
				System.out.println("@@@1111:::::::id::"+visitHistoryDetail.getPatientId()+"::::"+visitHistoryDetail.getProviderId()+"::"+visitHistoryDetail.getDrugsNotes());
				Query query = getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData set strengths = :strengths ,directions=:directions," +
						" quantity=:quantity,drugsNotes=:drugsNotes ,drugId=:drugId" +
	    			" where patientId = :patientId and providerId=:providerId and drugs=:drugs");
					//query.setParameter("strengths", "DIALOG1");
					query.setString("strengths", visitHistoryDetail.getStrengths());
					query.setString("directions", visitHistoryDetail.getDirections());
					query.setString("quantity", (visitHistoryDetail.getQuantity()));
					query.setString("drugsNotes", visitHistoryDetail.getDrugsNotes());
					query.setDouble("drugId", visitHistoryDetail.getDrugId());
					query.setInteger("patientId", visitHistoryDetail.getPatientId());
					query.setInteger("providerId", visitHistoryDetail.getProviderId());
					query.setString("drugs", visitHistoryDetail.getDrugs());
					
					
				int result1 = query.executeUpdate();
				System.out.println(">>>>>>>>>>>>>>"+result1);
			}
			
			//save care team table data..
			if(careTeamMemberListForReconcile.size() >=1)
			{
				for(Integer doctorId:careTeamMemberListForReconcile)
				{
					System.out.println("doctorId:::::::::::::"+doctorId+"::"+new ContextUtil().getPatientId());
					Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
					criteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
					criteria.add(Restrictions.eq("doctorId", doctorId));
					List<CareTeam>careTeamList=new ArrayList<CareTeam>();
					careTeamList=criteria.list();
					if(careTeamList.isEmpty())
					{
						CareTeam careTeam=new CareTeam();
						careTeam.setDoctorId(doctorId);
						careTeam.setPatientId(new ContextUtil().getPatientId());
						save(careTeam);
					}
					
					
					
				}
			}
			
		}
		
		//for saving in care team if particular doctor is avilable then not save if not then save it
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
		criteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
		criteria.add(Restrictions.eq("doctorId", new ContextUtil().getLoginId()));
		List<CareTeam>careTeamList=new ArrayList<CareTeam>();
		careTeamList=criteria.list();
		if(careTeamList.isEmpty())
		{
			CareTeam careTeam=new CareTeam();
			careTeam.setDoctorId(new ContextUtil().getLoginId());
			careTeam.setPatientId(new ContextUtil().getPatientId());
			save(careTeam);
		}
		
		
		/*for(PatientMedicationData pat:addNewmedicationListForPatient)
		{
			System.out.println("::::"+pat.getDrugs()+":::::");
			pat.setReffils(1);
			System.out.println(":::"+pat.getDrugs()+"::getQuantity:::"+pat.getQuantity()+":getDirections::"+pat.getDirections()+"::getStrengths:"+pat.getStrengths()+":getReffils::"+pat.getReffils());
			save(pat);
		}*/
		
		logger.info("inside saveMedDActionPlan method in dao impl::::::::::END::::::::::");
	}
	/*
	 * find dis- continue medicien list to save medicine history table
	 * @author:Gopal Krishna jha
	 */
	private PatientMedicationHistory findDetailOFMedHistory(String discontMeds,
			double drugId, int patientId, int providerId) {
		PatientMedicationHistory patientMedicationHistory=new PatientMedicationHistory();
		List<PatientMedicationData> patientMedicationDataList=new ArrayList<PatientMedicationData>();
		Criteria criteria= getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class)
								.add(Restrictions.eq("drugId", drugId))
								.add(Restrictions.eq("patientId", patientId))
								.add(Restrictions.eq("providerId", providerId));
		
		patientMedicationDataList=criteria.list();
		System.out.println("::::::::::::::::::::::::size in list when we delete"+patientMedicationDataList.size());
		int id=0;
		for(PatientMedicationData pat:patientMedicationDataList)
		{
			//System.out.println("pat pat patpat:::::::::"+pat.getDrugs());
			id=pat.getId();
			patientMedicationHistory.setDrugs(pat.getDrugs());
			patientMedicationHistory.setPatientId(pat.getPatientId());
			patientMedicationHistory.setProviderId(pat.getProviderId());
			patientMedicationHistory.setStrength(pat.getStrengths());
			//patientMedicationHistory.setSignature(pat.getSignature());
			patientMedicationHistory.setQuantity(pat.getQuantity());
			patientMedicationHistory.setReffils(pat.getReffils());
			patientMedicationHistory.setStartDate(pat.getStartDate());
			patientMedicationHistory.setLastFillDate(pat.getLastFillDate());
			patientMedicationHistory.setMedicineStatus(pat.getCauseOfAddMeds());
			patientMedicationHistory.setPrescriberID(pat.getDoctorId());
			patientMedicationHistory.setDirection(pat.getDirections());
			
			patientMedicationHistory.setDrugId(pat.getDrugId());
			patientMedicationHistory.setDrugNameId(pat.getDrugNameId());
			patientMedicationHistory.setDataProvider(pat.getDataProvider());
			
		}
		PatientMedicationData patientMedicationData=new PatientMedicationData();
		patientMedicationData.setId(id);
		System.out.println(":::::::::::::::::::id in delete::"+id);
		delete(patientMedicationData);
		return patientMedicationHistory;
	}
	
	/*
	 * save medicine detailof particular patient 
	 * @author gopal krishna jha
	 */
	
	public void saveMedicineDetail(PatientMedicationData patientMedicationData) {
		try{
		logger.info("saveMedicineDetail in patient medicien dao impl");
		logger.info("patientMedicationData"+patientMedicationData.getDateWithTimeZoneForCompare());
		//delete meds if previous available....
		boolean noDocDuplicate = false;
		System.out.println(":::::::::::drug id::"+patientMedicationData.getDrugId());
		deletePatientMedicationData(patientMedicationData.getDrugId(),patientMedicationData.getPatientId(),patientMedicationData.getProviderId(),patientMedicationData.getDrugNameId());
		/*Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientMedicationData where drugId =:drugId and patientId =:patientId and providerId =:providerId");
		query.setDouble("drugId", patientMedicationData.getDrugId());
		query.setInteger("patientId", patientMedicationData.getPatientId());
		query.setInteger("providerId", patientMedicationData.getProviderId());
		int result = query.executeUpdate();
		System.out.println(":::::::::::result:::"+result);*/
		 List<CareTeam>  careteamlist = new ArrayList<CareTeam>();
		 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
		        criteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
		        criteria.add(Restrictions.eq("doctorId", new ContextUtil().getLoginId()));
		        careteamlist= criteria.list();
		 if(null != careteamlist && careteamlist.size()>0 ){
		 for(CareTeam ct:careteamlist){
			 if(ct.getDoctorId() == patientMedicationData.getDoctorId()){
				 noDocDuplicate = true;
				 break;
			 }
		 	}
		 }
		 if(!noDocDuplicate){
					CareTeam careTeam=new CareTeam();
					careTeam.setDoctorId(new ContextUtil().getLoginId());
					careTeam.setPatientId(new ContextUtil().getPatientId());
				    careTeam.setProviderId(patientMedicationData.getProviderId());
					save(careTeam);
			 /*Query query = getSessionFactory().getCurrentSession().createQuery("insert into CareTeam where user_id =:userid and doctor_id =:doctorid");
			 query.setInteger("userid", patientMedicationData.getPatientId());
			 query.setInteger("doctorid", patientMedicationData.getDoctorId());
			 int result = query.executeUpdate();*/
			 System.out.println("patientId=================="+patientMedicationData.getPatientId()+"and DoctoeId ==============="
			       +patientMedicationData.getDoctorId()+"and providerId============="+patientMedicationData.getProviderId());
					 
		 }
		
		 getSessionFactory().getCurrentSession().save(patientMedicationData);
		 
		
		 
		}catch(HibernateException hfe){
			logger.error("Error in saveMedicineDetail for patient id  "+patientMedicationData.getPatientId());
			hfe.printStackTrace();
		}catch(Exception e){
			logger.error("Error in saveMedicineDetail");
			e.printStackTrace();
		}
	}
	

	/*
	 * Added By Nagaraj
	 * on 14/Jan/2015
	 * This method will save reconcile info to reconcile_info table in database whenever Reconcile med is done
	 * 
	 */
	private void saveReconcileInfo(PatientMedicationData patientMedicationData) throws HibernateException,Exception{
		logger.info("save Reconcile Info method Fired======>");
		ReconcileInfo reconcileInfo=new ReconcileInfo();
		reconcileInfo.setPatientId(new ContextUtil().getPatientId());
		reconcileInfo.setLastReconciledBy(new ContextUtil().getLoginId());
		reconcileInfo.setProviderId(new ContextUtil().getPatientId());
		reconcileInfo.setClinicProviderId(new  ContextUtil().getClinicProviderId());
		reconcileInfo.setLastReconciledDate(new DateUtil().getTodayDate());
		reconcileInfo.setDrugId(patientMedicationData.getDrugId());
		reconcileInfo.setDrugNameId(patientMedicationData.getDrugNameId());
		reconcileInfo.setStatus(true);
		getSessionFactory().getCurrentSession().save(reconcileInfo);
		
	}


	/*
	 * delete patient medicationData 
	 * author: Gopal Krishna jha
	 */
	public void deletePatientMedicationData(
			double drugId, int patientId,
			int providerId, double drugNameId) {
		logger.info(drugNameId+"deletePatientMedicationData particular drugId::"+drugId);
		Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientMedicationData where drugId =:drugId and drugNameId =:drugNameId and  patientId =:patientId " +
				" and providerId =:providerId");
		query.setDouble("drugId", drugId);
		query.setDouble("drugNameId", drugNameId);
		query.setInteger("patientId", patientId);
		query.setInteger("providerId", providerId);
		int result = query.executeUpdate();
		System.out.println(":::::::::::result:::"+result);
		
	}
	
	/*
	 * find anticoag med list...........
	 * @author:Gopal Krishna JHA
	 */

	

	public List<Medicine> findAnticoagMedList(String clinicName) {
		logger.info("inside findAnticoagMedList method in daoimpl");
		List<Medicine>medicineList=new ArrayList<Medicine>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(Medicine.class);
		/*criteria.setProjection(Projections.projectionList().
		         add(Projections.property("medicinName") ));*/
		criteria.add(Restrictions.eq("clinicName", clinicName));
		medicineList=criteria.list();
		//System.out.println("medicineList::::::::>>>>>>>"+medicineList);
		return medicineList;
	}
	
	/*
	 * find visit History of particular patient according to 
	 * @author:Gopal Krishna jha 
	 */
	
	public VisitHistory findVisitHistory(int patientId,
			int clinicProviderId, int loginId) {
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(VisitHistory.class);
				criteria.add(Restrictions.eq("patientId", patientId));
				criteria.add(Restrictions.eq("clinicProviderID", clinicProviderId));
				criteria.add(Restrictions.eq("doctorID", loginId));
				criteria.addOrder(Order.desc("visitDate"));
			List<VisitHistory>list=new ArrayList<VisitHistory>();
		 list=criteria.list();
		VisitHistory visitHistory= new VisitHistory();
		if(list.size()>=1)
		{
			visitHistory=(VisitHistory) list.get(0);
		}
		return visitHistory;
	}

	/*
	 * find medicine list ...
	 * @author:Gopal Krishna JHa..
	 */
	public List<Medicine> findMedicineList() {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(Medicine.class);
		return criteria.list();
	}

/*
 * *****************************Method To Find Dose Or Regimen for PharmacogenomicsProfile
 */

	

	public String findDoseOrRegimen(String drugName,int patientId,String find)
		{
		  logger.info("findDoseOrRegimen starts at dao layer:::");	
		  String doseOrRegimen = null;
			try {
						
			if (find=="dose") {
				String hql="select pt.strengths from PatientMedicationData pt where pt.patientId="+patientId+" and pt.drugs='"+drugName+"'";
				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				doseOrRegimen =(String) query.uniqueResult();
			   }
			if (find=="regimen") {
				String hql="select pt.directions from PatientMedicationData pt where pt.patientId="+patientId+" and pt.drugs='"+drugName+"'";
				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				doseOrRegimen =(String) query.uniqueResult();
			   }
			} 
			catch (Exception e) {
				logger.error("exception in findDoseOrRegimen at dao layer:::", e);
				e.printStackTrace();
			}
			return doseOrRegimen;
		}

	/*
 * *******Method to find My Medication Goal
 * @see com.clinakos.data.dao.IPatientMedicineDAO#findMyMedicationGoal(int)
 */
		public List<MedActionPlan> findMyMedicationGoal(int loginId) {
			logger.info("findMyMedicationGoal called in patientMedicineDAO::::::::");
			List<MedActionPlan> maList = new ArrayList<MedActionPlan>();
			try{
				//String hql="from MedActionPlan where patientId= :patientId and id in (select MAX(id) from MedActionPlan where patientId= :patientId group by medicineName )";
				String hql="from MedActionPlan where patientId= :patientId "; //for displaying all MedActionPlan goals by venu
				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				query.setParameter("patientId", loginId);
				maList=query.list();
			}
			catch(Exception e){
				logger.error("Exception in findMyMedicationGoal @ DAO layer::::", e);
				e.printStackTrace();
			}
			return maList;
	
		}
       /**
        * Get Sig Data for Which is used for get patient  medication data 
        * @return SigCodeList  
        * @see com.clinakos.data.dao.IPatientMedicineDAO#findSigCodeList()
        */
		public List<SigCode> findSigCodeList() {
			return getSessionFactory().getCurrentSession().createCriteria(SigCode.class).list();
		}
		/*
		 * find patient detail for drugs calculation
		 * @author: Gopal Krishna jha 
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findPatientDetail(int)
		 */
		public PatientVital findPatientDetail(int patientId) {
			logger.info("findPatientDetail in dao impl::::"+patientId);
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientVital.class);
			criteria.add(Restrictions.eq("patientIdForVital",patientId));
			PatientVital ptVital=(PatientVital) criteria.list().get(0);
			return ptVital;
		}

		/*
		 * 
		 * Author:Gopal Krishna jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#getPharmacogenomicsUserSummaryList(int)
		 */
		public List<PharmacogenomicsUserSummary> getPharmacogenomicsUserSummaryList(
				int patientId) {
			List<PharmacogenomicsUserSummary> pharmacogenomicsUserSummaryList = new ArrayList<PharmacogenomicsUserSummary>();
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(PharmacogenomicsUserSummary.class);
			criteria.add(Restrictions.eq("userId", patientId));
			pharmacogenomicsUserSummaryList = criteria.list();
			return pharmacogenomicsUserSummaryList;
			
		}

		/*
		 * find selected drugs detail for optimizer in according to price ...
		 * @author: Gopal Krishna jha from li.. 
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findDrugsPriceOPtimizer(java.util.List, int)
		 */
		public List<FormularyDetail> findDrugsPriceOPtimizer(
				FormularyDetail[] selectedFDetel, int insuranceId) {
			logger.info("findDrugsPriceOPtimizer method in PatientMedicineDaoiMpl:::"+insuranceId);
			List<FormularyDetail>formularyDetailListForOPtimize=new ArrayList<FormularyDetail>();
			for(FormularyDetail detail:selectedFDetel)
			{
				String drugs=detail.getMedicineName();
				String copay=detail.getPatientCopay();
			 DetachedCriteria subQuery = DetachedCriteria.forClass(FormularyDetail.class)  
			            .setProjection( Property.forName("therapyType"));
			 subQuery.add(Restrictions.eq("insuranceId", insuranceId));
			 subQuery.add(Restrictions.eq("medicineName", drugs));
			
			 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class) ;  
			 criteria.add(Restrictions.ne("medicineName", drugs) ); 
			 criteria.add(Restrictions.eq("insuranceId", insuranceId) ); 
	        // .add( Subqueries.geAll("formulayTier", bolgsEntries) ) ; 
			 criteria.add( Property.forName("therapyType").eq(subQuery) ); 
	        // .list();  
			//List<String>alternativeMedicineList=new ArrayList<String>(); 
			 List<FormularyDetail>formularyDetailList=new ArrayList<FormularyDetail>();
			 formularyDetailList=criteria.list();
			 //formularyDetailListForOPtimize.addAll(formularyDetailList);
			 for(FormularyDetail formularyDetail:formularyDetailList)
			 {
				 FormularyDetail formDetailObj=new FormularyDetail();
				 formDetailObj.setMedicineName(drugs);
				 formDetailObj.setAlternateMedicine(formularyDetail.getMedicineName());
				 formDetailObj.setPatientCopay(copay);
				 formDetailObj.setSaving(findCopaySaving(formDetailObj.getPatientCopay(),formularyDetail.getPatientCopay()));
				 formularyDetailListForOPtimize.add(formDetailObj);
			 }
			}
			//return alternativeMedicineList;
			System.out.println("::::::::::::size:::"+formularyDetailListForOPtimize.size());
			return formularyDetailListForOPtimize;
		}
		/*
		 * find the copay saving ..
		 * @author: Gopal Krishna jha
		 */

		private String findCopaySaving(String patientCopay, String alternateMedCopay) {
			String symbol="";
			
			symbol=patientCopay.substring(0,1);
			//System.out.println(":symbol::"+symbol);
			int diff=Integer.parseInt(patientCopay.substring(1,patientCopay.length()))-
					Integer.parseInt(alternateMedCopay.substring(1,alternateMedCopay.length()));
			//System.out.println(":::diff::"+diff);
			String finalDiff=Integer.toString(diff);
			finalDiff=symbol+finalDiff;
			//System.out.println(":::finalDiff::"+finalDiff);
			return finalDiff;
		}

		/*
		 * find the copay saving for other medcine... ..
		 * @author: Gopal Krishna jha
		 */
		public List<FormularyDetail> findDrugsPriceOPtimizerForOtherMeds(
				String otherMed, int insuranceId) {
			logger.info("findDrugsPriceOPtimizerForOtherMeds method in PatientMedicineDaoiMpl:::"+otherMed+"::"+insuranceId);
			//finding the detail of particular medicine..
			List<String> medicineList=new ArrayList<String>();
			medicineList.add(otherMed);
			List<FormularyDetail>formularyDetaiListForGivenMeds=fetchForumlarMedList(medicineList,  insuranceId);
			FormularyDetail formularyDetailforgiveMeds=new FormularyDetail();
			for(FormularyDetail fdetail:formularyDetaiListForGivenMeds)
				formularyDetailforgiveMeds=fdetail;
			
			List<FormularyDetail>formularyDetailListForOPtimize=new ArrayList<FormularyDetail>();
		 DetachedCriteria subQuery = DetachedCriteria.forClass(FormularyDetail.class)  
		            .setProjection( Property.forName("therapyType"));
		 subQuery.add(Restrictions.eq("insuranceId", insuranceId));
		 subQuery.add(Restrictions.eq("medicineName", otherMed));
		
		 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class) ;  
		 criteria.add(Restrictions.ne("medicineName", otherMed) ); 
		 criteria.add(Restrictions.eq("insuranceId", insuranceId) ); 
        // .add( Subqueries.geAll("formulayTier", bolgsEntries) ) ; 
		 criteria.add( Property.forName("therapyType").eq(subQuery) ); 
        // .list();  
		//List<String>alternativeMedicineList=new ArrayList<String>(); 
		 List<FormularyDetail>formularyDetailList=new ArrayList<FormularyDetail>();
		 formularyDetailList=criteria.list();
		 //formularyDetailListForOPtimize.addAll(formularyDetailList);
		 for(FormularyDetail formularyDetail:formularyDetailList)
		 {
			 FormularyDetail formDetailObj=new FormularyDetail();
			 formDetailObj.setMedicineName(otherMed);
			 formDetailObj.setAlternateMedicine(formularyDetail.getMedicineName());
			 formDetailObj.setPatientCopay(formularyDetailforgiveMeds.getPatientCopay());
			 formDetailObj.setSaving(findCopaySaving(formDetailObj.getPatientCopay(),formularyDetail.getPatientCopay()));
			 formularyDetailListForOPtimize.add(formDetailObj);
		 }
			return formularyDetailListForOPtimize;
			
		}

		/*
		 * find med action plan of particular patient or particular medicine 
		 * @author: Gopal Krishna jha
		 * (non-Javadoc)
		 * @param patientId
		 * @param proviedId
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findMedActionPlanForPatient(int, int)
		 */
		public List<MedActionPlan> findMedActionPlanForPatient(int patientId,
				int providerId) {
			logger.info(":findMedActionPlanForPatient in dao iml:::"+patientId);
			List<MedActionPlan>medActionPlanList=new ArrayList<MedActionPlan>();
			
			//String queryString=" from MedActionPlan where id in (select max(id) from MedActionPlan where patientId=:patientId group by medicineName)";
			
			String queryString=" from MedActionPlan where  patientId=:patientId";
			
			
			Query query=getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setInteger("patientId", patientId);
			medActionPlanList=query.list();
			
			for (MedActionPlan map : medActionPlanList) {
				map.setLabUnit(getLabUnitForGenericMedactionPlan(map.getLab()));
		}
			
			return medActionPlanList;
		}
		
		public List<MedActionPlan> findMedActionPlanForPatientforanticaog(int patientId,
				int providerId) {
			logger.info(":findMedActionPlanForPatient in dao iml:::"+patientId);
			List<MedActionPlan>medActionPlanListforanticoag=new ArrayList<MedActionPlan>();
			try
			{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedActionPlan.class);
			criteria.add(Restrictions.eq("patientId",patientId));
		     criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("medicineName"), "medicineName").add(Projections.property("drugNameId"), "drugNameId") ))
		.setResultTransformer(Transformers.aliasToBean(MedActionPlan.class));
		     medActionPlanListforanticoag=criteria.list();
			}
		     catch(Exception e){
					e.printStackTrace();
				}
			
			
			
			return medActionPlanListforanticoag;
		}
		
		private String getLabUnitForGenericMedactionPlan(String lab) {
			// TODO Auto-generated method stub
			
				 String labUnit="";
				 String hql="select units FROM ProcedureType where labType= :labType";
					Query query=getSessionFactory().getCurrentSession().createQuery(hql);
					query.setParameter("labType", lab);
					if(!query.list().isEmpty())
					labUnit=(String) query.list().get(0);
					System.out.println("labUnit:::::"+labUnit);
				
			return labUnit;
		}

		public List<MedandGenricmed> findgenricandnongenricMedActionPlanForPatient(int patientId,
				int providerId) {
			logger.info(":findMedActionPlanForPatient in dao iml:::"+patientId);
			List<MedandGenricmed>medActionPlanListforgenericandnongenricmed=new ArrayList<MedandGenricmed>();
			try{
			//String queryString=" from MedActionPlan where id in (select max(id) from MedActionPlan where patientId=:patientId group by medicineName)";
		//	 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class).add(Restrictions.eq("patientId", patientId)) ;
			/*String queryString=" from MedandGenricmed where  patientid=:patientId";
			
			
			Query query=getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setInteger("patientid", patientId);*/
				List<MedandGenricmed>medActionPlanListforPatient=new ArrayList<MedandGenricmed>();
				  String hql="FROM MedandGenricmed where patientId= :patientId";
					Query query=getSessionFactory().getCurrentSession().createQuery(hql);
					query.setParameter("patientId", patientId);
					//query.setParameter("createdate", new DateUtil().getTodayDate());
					medActionPlanListforPatient=query.list();
					
					for(MedandGenricmed medgenericmed:medActionPlanListforPatient)
					{
						MedandGenricmed medgen=new MedandGenricmed();
						medgen.setCreatedate(medgenericmed.getCreatedate());
						medgen.setMedicinename(medgenericmed.getMedicinename());
						medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
						if(StringUtils.isNotBlank(medgenericmed.getLastupdateby()) ){
							if(StringUtils.isAlphaSpace(medgenericmed.getLastupdateby())){
							medgen.setLastupdateby(medgenericmed.getLastupdateby());
						}else{
							medgen.setLastupdateby(getUserNameByUserId(Integer.valueOf(medgenericmed.getLastupdateby())));
						}
						}
						
						medgen.setLabfrequency(medgenericmed.getLabfrequency());
						medgen.setLab(medgenericmed.getLab());
						medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
						medgen.setLastupdatedate(medgenericmed.getLastupdatedate());
					
						medActionPlanListforgenericandnongenricmed.add(medgen);
				    	
					}
				
			
			}catch(HibernateException hfe){
				hfe.printStackTrace();
			}catch(Throwable t){
				t.printStackTrace();
			}
			
			
			return medActionPlanListforgenericandnongenricmed;
		}
		

		/*
		 * delete particular med action plan
		 * @author: Gopal Krishna jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#deleteParticularMedactionPlan(com.clinakos.data.model.patient.MedActionPlan)
		 */
		public void deleteParticularMedactionPlan(MedActionPlan medActionPlan) {
			
			getSessionFactory().getCurrentSession().delete(medActionPlan);
		}

		
		

		/*
		 * update selected image by patient
		 * @author: GOpal Krishna jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#updateSelectedImage(com.clinakos.data.model.patient.PatientMedicationData)
		 */
		public void updateSelectedImage(
				PatientMedicationData selectedRowIdOfPatientMedicationData) {
			logger.info("updateSelectedImage::::::::::");
			update(selectedRowIdOfPatientMedicationData);
		}

		
		public List<PatientMedicationHistory> findIndiviualDose(int patientId,int providerId) {
			List<PatientMedicationHistory> findDrugsFormula = new ArrayList<PatientMedicationHistory>();
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
			criteria.add(Restrictions.eq("patientId", patientId)).add(Restrictions.eq("providerId", providerId))
			.add(Restrictions.eq("drugs", "Warfarin")).setMaxResults(4);
			findDrugsFormula=criteria.list();
			System.out.println("findDrugsFormula::::::::::"+findDrugsFormula.size());
			return findDrugsFormula;
		}

		/*
		 * find anticoag clinic start date of particular user..
		 * @author: Gopal Krisha jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findAnticoagClinicStartDate(java.util.List, int, int)
		 */
		public Date findAnticoagClinicStartDate(List<Medicine> anicoagMedList1,
				int patientId, int providerId) {
			List<String>anicoagMedList=new ArrayList<String>();
			for(Medicine med:anicoagMedList1)
			{
				anicoagMedList.add(med.getMedicinName());
			}
			logger.info("findAnticoagClinicStartDate method start:::"+anicoagMedList.size()+patientId+providerId);
			Date startDate = null;
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
						criteria.add(Restrictions.in("drugs", anicoagMedList));
						criteria.add(Restrictions.eq("patientId", patientId));
						criteria.add(Restrictions.eq("providerId", providerId));
						criteria.add(Restrictions.eq("providerId", providerId));
						criteria.addOrder(Order.asc("startDate"));
			List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
			List<PatientMedicationData>patientMedicationDataList=new ArrayList<PatientMedicationData>();
			patientMedicationHistoryList=criteria.list();
			if(patientMedicationHistoryList.isEmpty())
			{
				Criteria criteria1=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
				criteria1.add(Restrictions.in("drugs", anicoagMedList));
				criteria1.add(Restrictions.eq("patientId", patientId));
				criteria1.add(Restrictions.eq("providerId", providerId));
				criteria1.addOrder(Order.asc("startDate"));
				patientMedicationDataList=criteria1.list();
				if(!(patientMedicationDataList.isEmpty()))
					startDate=patientMedicationDataList.get(0).getStartDate();
					
			}
			else
				startDate=patientMedicationHistoryList.get(0).getStartDate();
				
			return startDate;
		}

		/*
		 * find anticoag  start date of particular user..
		 * @author: Gopal Krisha jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findAnticoagClinicStartDate(java.util.List, int, int)
		 */
		public Date findAnticoagDate(List<Medicine> anicoagMedList1, int patientId) {
			List<String>anicoagMedList=new ArrayList<String>();
			for(Medicine med:anicoagMedList1)
			{
				anicoagMedList.add(med.getMedicinName());
			}
			logger.info("findAnticoagDate method start:::"+anicoagMedList.size()+patientId);
			Date startDate = null;
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
						criteria.add(Restrictions.in("drugs", anicoagMedList));
						criteria.add(Restrictions.eq("patientId", patientId));
						criteria.addOrder(Order.asc("startDate"));
						//criteria.add(Restrictions.eq("providerId", providerId));
			List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
			List<PatientMedicationData>patientMedicationDataList=new ArrayList<PatientMedicationData>();
			patientMedicationHistoryList=criteria.list();
			if(patientMedicationHistoryList.isEmpty())
			{
				Criteria criteria1=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
				criteria1.add(Restrictions.in("drugs", anicoagMedList));
				criteria1.add(Restrictions.eq("patientId", patientId));
				criteria1.addOrder(Order.asc("startDate"));//(Restrictions.eq("providerId", providerId));
				patientMedicationDataList=criteria1.list();
				if(!(patientMedicationDataList.isEmpty()))
					startDate=patientMedicationDataList.get(0).getStartDate();
					
			}
			else
				startDate=patientMedicationHistoryList.get(0).getStartDate();
				
			return startDate;
		}

		/*
		 * find last reconcile date of particular user
		 * @author: Gopal Krishna Jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findLastReconcileDate(int, int)
		 */
		public List<String> findLastReconcileDate(int patientId, int providerId) {
			logger.info("findLastReconcileDate method in dao impl:::"+patientId+"::"+providerId);
			List<String>reconcileInfo=new ArrayList<String>();
			
			List<PatientMedicationData>PatientMedicationDataList=new ArrayList<PatientMedicationData>();
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.add(Restrictions.eq("providerId", providerId));
			criteria.add(Restrictions.eq("causeOfAddMeds", RECONCILE_MEDS));
			criteria.addOrder(Order.desc("startDate"));
			PatientMedicationDataList=criteria.list();
			
			if(!PatientMedicationDataList.isEmpty())
			{
				String prescribeDate=new DateUtil().convertDateFormatUsingFormat(PatientMedicationDataList.get(0).getStartDate(), MM_DD_YYYY_DATE_PATTERN);
				reconcileInfo.add(PatientMedicationDataList.get(0).getPrescriberName());
				reconcileInfo.add(prescribeDate);	
			}
			else
			{
			List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
			 criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.add(Restrictions.eq("providerId", providerId));
			criteria.add(Restrictions.eq("medicineStatus", RECONCILE_MEDS));
			criteria.addOrder(Order.desc("startDate"));
			patientMedicationHistoryList=criteria.list();
			if(!patientMedicationHistoryList.isEmpty())
			{
				//String prescriberName=findDoctorNameAccordingToId(patientMedicationHistoryList.get(0).getPrescriberID());
				String prescribeDate=new DateUtil().convertDateFormatUsingFormat(patientMedicationHistoryList.get(0).getStartDate(), MM_DD_YYYY_DATE_PATTERN);
				System.out.println("::prescriberName:::"+patientMedicationHistoryList.get(0).getPrescriberName()+"::prescribeDate::"+prescribeDate);
				reconcileInfo.add(patientMedicationHistoryList.get(0).getPrescriberName());
				reconcileInfo.add(prescribeDate);
				
			}
			}
			return reconcileInfo;
			/*else
				return patientMedicationHistoryList.get(0).getStartDate();*/
		}

	/*
	 * find medicien list feom medicine table
	 * @author: Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findSearchMedicineDetailIst(java.lang.String)
	 */
		public List<WSDrug> findSearchMedicineDetailIst(String drugs) {
			drugs=drugs+"%";
			logger.info("findSearchMedicineDetailIst in dao start:::"+drugs);
			List<WSDrug> wsdrugList=new ArrayList<WSDrug>();
			List<WSDrug> wsdrugListforsetdosagefrom=new ArrayList<WSDrug>();
			
			//String sql="select  distinct IFNULL(genericName,drugName) as DrugName from msacess_view_ws_drug where drugname like '"+drugs+"' or genericname like '"+drugs+"' ;";
			String sql="select  distinct (drugName) as DrugName from msacess_view_ws_drug where drugname like '"+drugs+"'  ;";

			Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			List<Object>genericName=new ArrayList<Object>();
			genericName=query.list();
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+genericName.size());
			List<String>drugNameList=new ArrayList<String>();
			for(Object obj: genericName){
				  Object row = (Object) obj;
				  String drugName=new String();
				  drugName=(String) row;
				//  System.out.println("::::::::::::::::::::::::::!!!!+"+drugName);
				  drugNameList.add(drugName);
				  //wsdrug.setDataProvider((String)row[3]);
				  //drugDetailList.add(wsdrug);
			}
			
			if(!drugNameList.isEmpty())
			{
				Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
				criteria.add(Restrictions.disjunction()
					//.add(Restrictions.in("genericName", drugNameList))
					.add(Restrictions.in("drugName", drugNameList)));
					criteria.add(Restrictions.isNotNull("drugNameId"));
					criteria.add(Restrictions.eq("dataProvider", "F"));
			
					ProjectionList projList = Projections.projectionList();
					projList.add(Projections.property("drugName"));
					projList.add(Projections.property("drugNameId"));
					projList.add(Projections.property("genericName"));
					projList.add(Projections.property("dosageForm"));
					projList.add(Projections.property("dosageFormoverride"));
					projList.add(Projections.property("route"));
					projList.add(Projections.property("therapeuticCategory"));
					projList.add(Projections.property("drugCategory"));
					projList.add(Projections.groupProperty("drugNameId"));
					criteria.setProjection(Projections.distinct(projList)).addOrder(Order.asc("drugName"));

			
		          List<Object> doctorDetailList = criteria.list();
		  		for(Object obj: doctorDetailList){
		  		  Object[] row = (Object[]) obj;
		  		WSDrug wsdrug=new WSDrug();
		  		wsdrug.setDrugName((String)row[0]);
		  		wsdrug.setDrugNameId((Double)row[1]);
		  		if(row[2]==null)
		  			wsdrug.setGenericName((String)row[0]);
		  		else
		  			wsdrug.setGenericName((String)row[2]);
		  		
		  		wsdrug.setDosageForm((String)row[3]);
		  		if(row[4]==null)
		  			wsdrug.setDosageFormoverride((String)row[3]);
		  		else
		  			wsdrug.setDosageFormoverride((String)row[4]);
		  		wsdrug.setRoute((String)row[5]);
		  		wsdrug.setTherapeuticCategory((String)row[6]);
		  		wsdrug.setDrugCategory((String)row[7]);
		  		wsdrugListforsetdosagefrom.add(wsdrug);
		  		}
		  		for(WSDrug wsdrugs:wsdrugListforsetdosagefrom)
		  		{
		  			if(wsdrugs.getDosageFormoverride().contains("("))
		  			{
		  				wsdrugs.setDosageFormoverride(wsdrugs.getDosageFormoverride().substring(0, wsdrugs.getDosageFormoverride().indexOf("(")));
		  			}
		  			else
		  			{
		  				wsdrugs.setDosageFormoverride(wsdrugs.getDosageFormoverride());
		  			}
		  			wsdrugList.add(wsdrugs);
		  		}
	  		  
	  		}
			
			return wsdrugList;
		}

		
		public UserLoginDetail findDoctorDetail(int loginId) {
			System.out.println("loginId:::::::"+loginId);
			//UserLoginDetail doctorDetails =new UserLoginDetail();
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			criteria.add(Restrictions.eq("userId", loginId));
			UserLoginDetail drDetails=(UserLoginDetail) criteria.list().get(0);
			
			return drDetails;
		}

		
		public WSDrug findFullDrugDetailAccordingToThereName(String drugDetail) {
			logger.info("findFullDrugDetailAccordingToThereName ::::: "+drugDetail);
			WSDrug wsdrug=new WSDrug();
			
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
			criteria.add(Restrictions.eq("drugDetail", drugDetail));
			if(criteria.list().size()!=0)
				wsdrug=(WSDrug) criteria.list().get(0);
			return wsdrug;
		}
		
		
		
		
		/*
		 * find drugid which meds dnt have strength
		 * @author: Gopal Krishna Jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findDoseListOfParticularMedsNotStrength(double, java.lang.String)
		 */
		public List<WSDrug> findDoseListOfParticularMedsNotStrength(
				double drugNameId, String dosageForm) {
			logger.info("findDoseListOfParticularMedsNotStrength method start in patient Dao Impl:::"+drugNameId);
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
			criteria.add(Restrictions.eq("drugNameId", drugNameId));
			criteria.add(Restrictions.isNotNull("dose"));
			List<WSDrug>drugDetailList=new ArrayList<WSDrug>();
			// cast(SUBSTRING_INDEX(dose,' ',1) as SIGNED)
			String sql="SELECT drugId,DrugNameId,dosage,dataProvider,TheraputicCategory,DrugCategory FROM msacess_view_ws_drug where drugNameId='"+drugNameId+"' " ; 
					//"and DosageForm='"+dosageForm+"' and dosage is not null order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";

			if((dosageForm!=null))
			{
				if(!dosageForm.equals("Add'l Sig"))
					sql=sql+" and DosageForm='"+dosageForm+"'";
				
			}
			//sql=sql+"  and dosage is not null order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";
			
			sql=sql+"   order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";
			
			System.out.println("::::::::::::::::::><><>>>>>>>>>>>>:"+sql);
			Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			List<Object>complianceMeterDetailList=new ArrayList<Object>();
			complianceMeterDetailList=query.list();
			for(Object obj: complianceMeterDetailList){
				  Object[] row = (Object[]) obj;
				  WSDrug wsdrug=new WSDrug();
				  wsdrug.setDrugId((Double)row[0]);
				  wsdrug.setDrugNameId((Double)row[1]);
				  wsdrug.setDose((String)row[2]);
				  //System.out.println(wsdrug.getDrugId()+"::::::::::::::::::::::::::!!!!+"+wsdrug.getDose());
				  wsdrug.setDataProvider((String)row[3]);
				  wsdrug.setTherapeuticCategory((String)row[4]);
				  wsdrug.setDrugCategory((String)row[5]);
				  drugDetailList.add(wsdrug);
			}
			//System.out.println("::::::"+drugDetailList.size());
			return drugDetailList;
		}
		/*
		 * find dose detail list according to particular patient
		 * @author:Gopal Krishna Jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findDoseListOfParticularMeds(java.lang.Double)
		 */
		public List<WSDrug> findDoseListOfParticularMeds(Double drugNameId,String dosageForm) {
			logger.info("findDoseListOfParticularMeds method start in patient Dao Impl:::"+drugNameId);
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
			criteria.add(Restrictions.eq("drugNameId", drugNameId));
			criteria.add(Restrictions.isNotNull("dose"));
			List<WSDrug>drugDetailList=new ArrayList<WSDrug>();
			// cast(SUBSTRING_INDEX(dose,' ',1) as SIGNED)
			String sql="SELECT drugId,DrugNameId,dosage,dataProvider , TheraputicCategory,DrugCategory,DosageForm FROM msacess_view_ws_drug where drugNameId='"+drugNameId+"' " ; 
					//"and DosageForm='"+dosageForm+"' and dosage is not null order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";

			if((dosageForm!=null))
			{
				if(!(dosageForm.equals("Add'l Sig")||dosageForm.equals("Add Sig")))
					sql=sql+" and DosageForm='"+dosageForm+"'";
				
			}
			sql=sql+"  and dosage is not null order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";
			
			//sql=sql+"   order by  cast(SUBSTRING_INDEX(dosage,' ',1) as decimal(6,1)) asc";
			
			System.out.println("::::::::::::::::::><><>>>>>>>>>>>>:"+sql);
			Query query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
			List<Object>complianceMeterDetailList=new ArrayList<Object>();
			complianceMeterDetailList=query.list();
			for(Object obj: complianceMeterDetailList){
				  Object[] row = (Object[]) obj;
				  WSDrug wsdrug=new WSDrug();
				  wsdrug.setDrugId((Double)row[0]);
				  wsdrug.setDrugNameId((Double)row[1]);
				  wsdrug.setDose((String)row[2]);
				  //System.out.println(wsdrug.getDrugId()+"::::::::::::::::::::::::::!!!!+"+wsdrug.getDose());
				  wsdrug.setDataProvider((String)row[3]);
				  wsdrug.setTherapeuticCategory((String)row[4]);
				  wsdrug.setDrugCategory((String)row[5]);
				  wsdrug.setDosageForm((String)row[6]);
				  drugDetailList.add(wsdrug);
			}
			//System.out.println("::::::"+drugDetailList.size());
			return drugDetailList;
		}
		/*
		 * find sub diagnosisi list according to clinic diagnosis id...
		 * @author: Gopal Krishna jha
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findClinicSubDiagnosisList(int)
		 */
		public List<ClinicSubdiagnosis> findClinicSubDiagnosisList(
				int clinicDiagnosisId) {
			logger.info("findClinicSubDiagnosisList method start in dao::"+clinicDiagnosisId);
			
			return 	 getSessionFactory().getCurrentSession().createCriteria(ClinicSubdiagnosis.class).
					add(Restrictions.eq("clinicDiagnosisId", clinicDiagnosisId)).
					list();
			
		}

		/**
		 * Find Allergy name based on user Input from Master Allergy table  
		 * @param allergyNameForAddingNew passed input value for get Allergy From master Data  
		 * @return Allergy list 
		 * @see com.clinakos.data.dao.IPatientMedicineDAO#findMasterAllergyData(String)
		 */
		public List<AllergyMaster> findMasterAllergyData(String allergyNameForAddingNew) {
			System.out.println("allergyName::::::"+allergyNameForAddingNew);
			List<AllergyMaster> allergyMasterList=new ArrayList<AllergyMaster>();
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AllergyMaster.class);
			                  criteria.add(Restrictions.eq("status", "A"));
			                  //criteria.add(Restrictions.ilike("allergyName", patientAllergy.getAllergy()));
			              	criteria.add(Restrictions.like("allergyName", allergyNameForAddingNew+"%"));
			 allergyMasterList=criteria.list();   
			 System.out.println("::::::::::::"+allergyMasterList.size());
			 
			return allergyMasterList;
		}

	  /**
	   * Get Patient Allergy On the basis of patient Id 
	   * @param patientId
	   * @return List of Patient Allergy 
	   * @see com.clinakos.data.dao.IPatientMedicineDAO#getAllPatientAllergy(int)
	   * 
	   */
		public List<PatientAllergy> getAllPatientAllergy(int patientId) {
			List<PatientAllergy> patientAllergyList=new ArrayList<PatientAllergy>();
			List<PatientAllergy> pAllergyListAfterTrim = new ArrayList<PatientAllergy>();
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientAllergy.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			patientAllergyList=criteria.list();
			for (PatientAllergy palist : patientAllergyList) {
				logger.info("Allergy length bfr trim::::::::::"+palist.getAllergyName());
				palist.setAllergyName(palist.getAllergyName().trim());
				logger.info("Allergy length after trim::::::::::"+palist.getAllergyName());
				pAllergyListAfterTrim.add(palist);
				
			}
			return pAllergyListAfterTrim;
		}

	
	public List<RouteDetails> findRouteNameDetailList() {
		List<RouteDetails>routeNameDetailList= getSessionFactory().getCurrentSession().createCriteria(RouteDetails.class).list();
		//Criteria criteria =getSessionFactory().getCurrentSession().createCriteria(RouteDetails.class);
		//routeNameDetailList=criteria.list();
		return routeNameDetailList;
	}

	
	public List<UnitDetails> findUnitNameDetailsList() {
		List<UnitDetails>unitNameDetailList= getSessionFactory().getCurrentSession().createCriteria(UnitDetails.class).list();
		return unitNameDetailList;
	}

	
	public List<DosageFrom> findDosageNameDetailsList() {
		List<DosageFrom>dosageNameDetailList=getSessionFactory().getCurrentSession().createCriteria(DosageFrom.class).list();
		
		return dosageNameDetailList;
	}

	/*
	 * save patient allergy.. 
	 * @param patientAllergy Patient Allergy Object data  
	 * @author Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#savePatientAllergy()
	 * 
	 */
	public void savePatientAllergy(PatientAllergy patientAllergy) {
		logger.info("savePatientAllergy method in dao::::::");
		patientAllergy.setAllergyName(patientAllergy.getAllergyName().trim());
		save(patientAllergy);
		//System.out.println("date::123::"+patientAllergy.getOnSetDate());
		
	}

	/**
	 * Delete PAtient Allergy Based on Selection 
	 * @param patientAllergy
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#deleteAllergyDetails(PatientAllergy)
	 */
	public void deleteAllergyDetails(PatientAllergy patientAllergy) {
		System.out.println("patientAllergy:::::::::"+patientAllergy.getCompositeAllergyId()+"patient id ::::"+patientAllergy.getPatientId());
		
		//getSessionFactory().getCurrentSession().dcreateCriteria(PatientAllergy.class);
		Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientAllergy where compositeAllergyId =:compositeAllergyId and patientId =:patientId");
		query.setInteger("patientId", patientAllergy.getPatientId());
		query.setInteger("compositeAllergyId", patientAllergy.getCompositeAllergyId());
		System.out.println("PatientAllergy:::::::::::::");
		 query.executeUpdate();
	}

	/*
	 * integrate necrop to clinakos db...
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#allergyIntegrateFromNewCropToClinakos(java.util.List)
	 */
	public void allergyIntegrateFromNewCropToClinakos(
			List<PatientAllergy> patientAllergyListForIntegrate,int patientId) {
		logger.info("allergyIntegrateFromNewCropToClinakos in dao::::"+patientAllergyListForIntegrate.size());
		List<Integer>allergyIdList=new ArrayList<Integer>();
		for(PatientAllergy patientAllergy:patientAllergyListForIntegrate)
			{
			allergyIdList.add((patientAllergy.getAlergyConceptId()));
			System.out.println("::::::::"+patientAllergy.getAlergyConceptId());
			}
		if(!allergyIdList.isEmpty())
			{
			Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientAllergy where alergyConceptId not in (:alergyConceptId) and patientId =:patientId");
			query.setParameterList("alergyConceptId", allergyIdList);
			query.setInteger("patientId", new ContextUtil().getPatientId());
			int result = query.executeUpdate();
			System.out.println(":::::::::::result:::"+result);
			}
		else
		{
			Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientAllergy where  patientId =:patientId");
			//query.setParameterList("allergyId", allergyIdList);
			query.setInteger("patientId", new ContextUtil().getPatientId());
			int result = query.executeUpdate();
			System.out.println(":::::::::::result:::"+result);
		}
		
		for(PatientAllergy patientAllergy:patientAllergyListForIntegrate)
		{
			int result=0;
			Query query =getSessionFactory().getCurrentSession().createQuery("update PatientAllergy set allergySeverity =:allergySeverity, allergyNotes =:allergyNotes " +
					", modifyDate =:modifyDate, onSetDate=:onSetDate" +
					" where alergyConceptId =:alergyConceptId and patientId =:patientId");
			query.setInteger("alergyConceptId", patientAllergy.getAlergyConceptId());
			query.setInteger("patientId", new ContextUtil().getPatientId());
			query.setString("allergySeverity", patientAllergy.getAllergySeverity());
			query.setString("allergyNotes", patientAllergy.getAllergyNotes());
			query.setDate("modifyDate", new Date());
			query.setDate("onSetDate", patientAllergy.getOnSetDate());
			 result = query.executeUpdate();
			System.out.println("11:::::::::::result:::"+result);
			if(result==0)
			{
				//'"+new DateUtil().DateInMySqlPattern(procedureResult.getProcedureOrder().getDateOrdered())+"' "
				
				//String onsetDate="";
				if(patientAllergy.getOnSetDate()!=null)
				{
					//onsetDate=new DateUtil().DateInMySqlPattern(patientAllergy.getOnSetDate());
					String insert_PatientAllergy="insert into patient_allergy " +
							"(user_id, allergy_type, allergy_name, allergy_notes, allergy_severity, " +
							"allergy_source, composite_allergy_id, alergy_concept_id, allergy_concept_type, allergy_concept_type_id , start_date, modify_date, onSetDate) values " +
							"("+patientAllergy.getPatientId()+","+patientAllergy.getAllergytype()+" ,'"+patientAllergy.getAllergyName()+"' ,'"+patientAllergy.getAllergyNotes()+"'," +
								"'"+patientAllergy.getAllergySeverity()+"','"+patientAllergy.getAllergySource()+"' ,"+patientAllergy.getCompositeAllergyId()+","+patientAllergy.getAlergyConceptId()+"" +
											", '"+patientAllergy.getAllergyConceptType()+"',"+patientAllergy.getAllergyConceptTypeId()+" ," +
										 " '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"', '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"', '"+new DateUtil().DateInMySqlPattern(patientAllergy.getOnSetDate())+"')";
					Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_PatientAllergy);
					sqlQuery1.executeUpdate();
					
				}
				else
				{
					String insert_PatientAllergy="insert into patient_allergy " +
							"(user_id, allergy_type, allergy_name, allergy_notes, allergy_severity, " +
							"allergy_source, composite_allergy_id, alergy_concept_id, allergy_concept_type, allergy_concept_type_id , start_date, modify_date) values " +
							"("+patientAllergy.getPatientId()+","+patientAllergy.getAllergytype()+" ,'"+patientAllergy.getAllergyName()+"' ,'"+patientAllergy.getAllergyNotes()+"'," +
								"'"+patientAllergy.getAllergySeverity()+"','"+patientAllergy.getAllergySource()+"' ,"+patientAllergy.getCompositeAllergyId()+","+patientAllergy.getAlergyConceptId()+"" +
											", '"+patientAllergy.getAllergyConceptType()+"',"+patientAllergy.getAllergyConceptTypeId()+" ," +
										 " '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"', '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"')";
					Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_PatientAllergy);
					sqlQuery1.executeUpdate();
				}
				
				/*new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate());
				String insert_PatientAllergy="insert into patient_allergy " +
						"(user_id, allergy_type, allergy_name, allergy_notes, allergy_severity, " +
						"allergy_source, composite_allergy_id, alergy_concept_id, allergy_concept_type, allergy_concept_type_id , start_date, modify_date, onSetDate) values " +
						"("+patientAllergy.getPatientId()+","+patientAllergy.getAllergytype()+" ,'"+patientAllergy.getAllergyName()+"' ,'"+patientAllergy.getAllergyNotes()+"'," +
							"'"+patientAllergy.getAllergySeverity()+"','"+patientAllergy.getAllergySource()+"' ,"+patientAllergy.getCompositeAllergyId()+","+patientAllergy.getAlergyConceptId()+"" +
										", '"+patientAllergy.getAllergyConceptType()+"',"+patientAllergy.getAllergyConceptTypeId()+" ," +
									 " '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"', '"+new DateUtil().DateInMySqlPattern(patientAllergy.getStartDate())+"', '"+onsetDate+"')";
				Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_PatientAllergy);
				sqlQuery1.executeUpdate();*/
			}
			
		}
		
		//saveValueForIntegration(new ContextUtil().getPatientId(), false);
		
	}
	
	/*
	 * integrate newcrop to clinakos db...
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#medicineIntegrateFromNewCropToClinakos(java.util.List, int)
	 */
	public void medicineIntegrateFromNewCropToClinakos(
			List<PatientMedicationData> patientMedicationDataForIntegrate,
			int patientId,List<PatientMedicationData> patientMedicationDataList,List<Medicine> medicineListForCheckingMedActionPlan,List<MedActionPlan> medActionPlanList,
			List<ParentMedActionPlan> allGenericMedActionPlan) {
		logger.info("medicineIntegrateFromNewCropToClinakos in dao::::"+patientMedicationDataForIntegrate.size()+":::"+patientId);
		//logger.info("deleteMedicineChanges on dao::12345:::"+changePatientMedicineBackUpdata.getId());
		//PatientMedicationData modifiedMedicationData=changePatientMedicineBackUpdata;
		 
		try{
			List<Double>drugIdList=new ArrayList<Double>();
			List<Integer>externalPrescriptionIdList=new ArrayList<Integer>();
			for(PatientMedicationData pat:patientMedicationDataForIntegrate)
				{
				drugIdList.add(pat.getDrugId());
				System.out.println("length ::::::::::::::::::::::::"+pat.getExternalPrescriptionId().length());
				System.out.println(pat.getExternalPrescriptionId().trim().length()<5);
				if(pat.getExternalPrescriptionId().trim().length()!=0 && pat.getExternalPrescriptionId().trim().length()<5)
					externalPrescriptionIdList.add(Integer.parseInt((pat.getExternalPrescriptionId())));
				System.out.println("::::::::"+pat.getExternalPrescriptionId());
				}
			if(!externalPrescriptionIdList.isEmpty())
				{
				//savePatientMedHistoryAndDeletePatientMedicationData(changePatientMedicineBackUpdata);
				
				Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientMedicationData where id not in (:id) and patientId =:patientId");
				query.setParameterList("id", externalPrescriptionIdList);
				query.setInteger("patientId", patientId);
				int result = query.executeUpdate();
				System.out.println("::::::222:::::result:::"+result);
				
				
				}
			if(patientMedicationDataList.size()>0 && externalPrescriptionIdList.isEmpty() )
			{
				Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientMedicationData where providerId=:providerId and patientId =:patientId");
				query.setInteger("providerId", new ContextUtil().getProviderId());
				query.setInteger("patientId", patientId);
				int result = query.executeUpdate();
				System.out.println("::::::333:::::result:::"+result);
			}
			 
			//delete med action plan..
			
			if(!drugIdList.isEmpty())
			{
			//savePatientMedHistoryAndDeletePatientMedicationData(changePatientMedicineBackUpdata);
			
			Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan where drugId not in (:drugId) and patientId =:patientId");
			query.setParameterList("drugId", drugIdList);
			query.setInteger("patientId", patientId);
			int result = query.executeUpdate();
			System.out.println("::::444:::::::result:::"+result);
			}
			else
			{
			Query query =getSessionFactory().getCurrentSession().createQuery("delete MedActionPlan where doctorId=:doctorId and patientId =:patientId");
			query.setInteger("doctorId", new ContextUtil().getLoginId());
			query.setInteger("patientId", patientId);
			int result = query.executeUpdate();
			System.out.println(":::555::::::::result:::"+result);
		}
			
			
			for(PatientMedicationData patMedData:patientMedicationDataForIntegrate)
			{
				System.out.println(patMedData.getExternalPrescriptionId()+"::::::::::::::::::::::::::::::::::::::::+"+patMedData.getPrescriptionGuid());
				if(patMedData.getDosageForm().equalsIgnoreCase("Add'l Sig"))
				{
					//logger.info("string escape utils for sql dosage single forward slash  form"+StringEscapeUtils.escapeSql("Add\'l Sig"));
					//logger.info("string escape utils for sql dosage without slash  form"+StringEscapeUtils.escapeSql("Add'l Sig"));
					patMedData.setDosageForm("Add Sig");
					
					System.out.println("::::::::::::::::::::::::inside if:::"+patMedData.getDosageForm());
				}
				int count=0;
				patMedData.setFlagForVisit(false);
				if(patMedData.getExternalPrescriptionId().trim().length()!=0)
				{
						for(PatientMedicationData patMedDataForCurrentSystem:patientMedicationDataForIntegrate)
						{
							if(patMedData.getExternalPrescriptionId().equals(patMedDataForCurrentSystem.getExternalPrescriptionId()))
							{
								System.out.println("inside get externalprescription id");
								count++;
							}
						}
						logger.info(":::::::::::::count:::::::::::"+count);
						if(count==1)
						{
							//update particular row
							if(patMedData.getPrescriberName().equalsIgnoreCase("NOT SENT"))
								patMedData.setPrescriptionStatus(PRESCRIPITION_STATUS_PENDING);
							
							
							System.out.println(patMedData.getPrescriptionStatus()+":::::::::::inside if count ==1:: "+patMedData.getExternalPrescriptionId());
							/*if((patMedData.getPrescriberName().equalsIgnoreCase("NOT SENT")))
								patMedData.setPrescriptionStatus(PRESCRIPITION_STATUS_PENDING);*/
							if(patMedData.getExternalPrescriptionId().trim().length()>5)
							{
								
								patMedData.setExternalPrescriptionId("0");
								
							}
							//else
								patMedData.setId(Integer.parseInt(patMedData.getExternalPrescriptionId()));
							//update(patMedData);
								
								
							
							System.out.println(patMedData.getExternalPrescriptionId()+":::::::::::::::::::id::::::"+patMedData.getId()+
									"patMedData="+patMedData.getDateWithTimeZoneForCompare()+"patMedData:::"+patMedData.isPrn());
							Query query =getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData " +
									"set quantity =:quantity,directions=:directions, strengths=:strengths, reffils=:reffils , pharmacyName =:pharmacyName ," +
									"dosageForm =:dosageForm , unit =:unit, byRoute =:byRoute ,prescriptionGuid =:prescriptionGuid  , prescriberName =:prescriberName " +
									
									" ,pharmacyType =:pharmacyType , pharmacyDetailsType =:pharmacyDetailsType, finalDestinationType =:finalDestinationType ,finalStatusType =:finalStatusType " +
									
									" ,prescriptionStatus  =:prescriptionStatus , prescriptionSubStatus =:prescriptionSubStatus , additionalSig =:additionalSig ,prn =:prn , allowSubstitution= :allowSubstitution" +
									" ,externalPrescriptionId =:externalPrescriptionId , dateWithTimeZoneForCompare =:dateWithTimeZoneForCompare , daySupply =:daySupply " +
									" where id =:id and patientId =:patientId");
							query.setString("quantity", patMedData.getQuantity());
							query.setString("directions", patMedData.getDirections());
							query.setString("strengths", patMedData.getStrengths());
							query.setInteger("reffils", patMedData.getReffils());
							query.setString("externalPrescriptionId", patMedData.getExternalPrescriptionId());
							query.setInteger("patientId", patMedData.getPatientId());
							query.setInteger("id", Integer.parseInt(patMedData.getExternalPrescriptionId()));
							query.setString("dosageForm", patMedData.getDosageForm());
							query.setString("unit", patMedData.getUnit());
							
							query.setString("byRoute", patMedData.getByRoute());
							query.setString("prescriberName", patMedData.getPrescriberName());
							
							query.setString("pharmacyType", patMedData.getPharmacyType());
							query.setString("pharmacyDetailsType", patMedData.getPharmacyDetailsType());
							query.setString("finalDestinationType", patMedData.getFinalDestinationType());
							query.setString("finalStatusType", patMedData.getFinalStatusType());
							
							
							query.setString("prescriptionGuid", patMedData.getPrescriptionGuid());
							query.setString("pharmacyName", patMedData.getPharmacyName());
							query.setString("prescriptionStatus", patMedData.getPrescriptionStatus());
							query.setString("additionalSig", patMedData.getAdditionalSig());
							query.setBoolean("prn", patMedData.isPrn());
							query.setBoolean("allowSubstitution", patMedData.isAllowSubstitution());
							query.setString("prescriptionSubStatus", patMedData.getPrescriptionSubStatus());
							query.setString("dateWithTimeZoneForCompare", patMedData.getDateWithTimeZoneForCompare());
							query.setString("daySupply", patMedData.getDaySupply());
							int result=0;
							result=  query.executeUpdate();
							System.out.println("11>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> result=="+result);
							
							if(result==0)
							{
								insertIntoPatientMedicationData(patMedData,medicineListForCheckingMedActionPlan, medActionPlanList,  allGenericMedActionPlan);
							}
							
						}
						else
						{
							//update only current meds not insert on doctor not sent
							if(!(patMedData.getPrescriberName().equalsIgnoreCase("NOT SENT")))
							{
								System.out.println("::::11 inside else::::::::::::externalPrescriptionId:::::"+patMedData.getExternalPrescriptionId());
								//update(patMedData);
								//delete
							
								Query query1 =getSessionFactory().getCurrentSession().createQuery("delete PatientMedicationData where id=:id and " +
										" patientId =:patientId");
								query1.setInteger("id",Integer.parseInt(patMedData.getExternalPrescriptionId()));
								query1.setInteger("patientId", patientId);
								int result1 = query1.executeUpdate();
								System.out.println("111:::::::::::result:::"+result1);
								
								
								//save value
								
							//	patMedData.setDrugNameId(findDrugNameIdAcordingToDrugId(patMedData.getDrugId()));
								if(result1==1)
								{
									patMedData.setPrescriberName(new ContextUtil().getLoggerFirstName()+" " +new ContextUtil().getLoggedUserMiddleName() +" "+new ContextUtil().getLoggerLastName());
								insertIntoPatientMedicationData(patMedData,medicineListForCheckingMedActionPlan, medActionPlanList,  allGenericMedActionPlan);
								}
								System.out.println("patMedData="+patMedData.getDateWithTimeZoneForCompare());
								
							}
						}
				}
				
				else
				{
					logger.info("for save which meds add in new crop:::patMedData="+patMedData.getDateWithTimeZoneForCompare());
					insertIntoPatientMedicationData(patMedData,medicineListForCheckingMedActionPlan, medActionPlanList,  allGenericMedActionPlan);
					
				}
			}
			
			saveValueForIntegration(patientId, false);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * insert value into patient medication data
	 * @author: Gopal Krishna Jha
	 */
	private void insertIntoPatientMedicationData(
			PatientMedicationData patMedData, List<Medicine> medicineListForCheckingMedActionPlan, List<MedActionPlan> medActionPlanList, List<ParentMedActionPlan> allGenericMedActionPlan) {
		System.out.println("inside insertIntoPatientMedicationData:::patMedData="+patMedData.getDateWithTimeZoneForCompare());
		WSDrug wsdrug=new WSDrug();
		wsdrug=findDrugDetailAccordingToDrugId(patMedData.getDrugId());
		//patMedData.setDrugNameId(findDrugNameIdAcordingToDrugId(patMedData.getDrugId()));
		if(wsdrug.getDrugNameId()!=null)
		patMedData.setDrugNameId(wsdrug.getDrugNameId());
		if(wsdrug.getGenericName()==null)
			patMedData.setGenericName(wsdrug.getDrugName());
		else
			patMedData.setGenericName(wsdrug.getGenericName());
		
		boolean checkFlagForMedactionPlan=checkMedactionPlanOrNotOfParticularDrugs(medActionPlanList,allGenericMedActionPlan,patMedData.getDrugId());
		if(!checkFlagForMedactionPlan)
		{
			for(Medicine med:medicineListForCheckingMedActionPlan)
			{
				if(med.getMedicinName().equalsIgnoreCase(patMedData.getGenericName()))
					patMedData.setFlagForMedActionPlan(true);
			}
		}
		//String date=new DateUtil().DateInMySqlPattern(patMedData.getStartDate());
		
		String date=new DateUtil().DateAndTimeInMySqlPattern(patMedData.getStartDate());
		String insert_PatientAllergy="insert into patient_medication_data (user_id,provider_id,drugs, pharmacy_name, quantity,directions,strengths,refills, additional_sig , prn, allow_substitution, " +
				"start_date,doctor_id,cause_of_add_meds,drug_id,drug_name_id,data_provider,unit, by_route, dosage_form,prescription_guid, prescriber_name, " +
				" prescription_status , prescription_subStatus , flag_for_visit , generic_name , date_time_zone_for_compare,flag_for_medActionPlan , day_supply ) values " +
				"("+patMedData.getPatientId()+","+patMedData.getProviderId()+",'"+patMedData.getDrugs()+"' ,'"+patMedData.getPharmacyName()+"','"+patMedData.getQuantity()+"'," +
						"'"+patMedData.getDirections()+"' ,'"+patMedData.getStrengths()+"',"+patMedData.getReffils()+",'"+patMedData.getAdditionalSig()+"',"+patMedData.isPrn()+","+patMedData.isAllowSubstitution()+", '"+date+"'" +
						","+patMedData.getDoctorId()+",'"+NEW_ADDED_MEDS+"',"+patMedData.getDrugId()+","+patMedData.getDrugNameId()+
						",'"+patMedData.getDataProvider()+"','"+patMedData.getUnit()+"' ,'"+patMedData.getByRoute()+"' ," +
								"'"+patMedData.getDosageForm()+"', '"+patMedData.getPrescriptionGuid()+"','"+ patMedData.getPrescriberName()+"'," +
						"'"+ patMedData.getPrescriptionStatus()+"','"+patMedData.getPrescriptionSubStatus()+"' , " +
						" "+patMedData.isFlagForVisit()+" ,'"+patMedData.getGenericName()+"' , '"+patMedData.getDateWithTimeZoneForCompare()+"',"
						+patMedData.isFlagForMedActionPlan()+", '"+patMedData.getDaySupply()+"' )";
		Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_PatientAllergy);
		sqlQuery1.executeUpdate();
		
	}
	
	/*
	 * check particular meds having med action plan or not..
	 * @author: Gopal Krishna Jha
	 */
	private boolean checkMedactionPlanOrNotOfParticularDrugs(List<MedActionPlan> medActionPlanList, List<ParentMedActionPlan> allGenericMedActionPlan, double drugId) {
			boolean check = false;
			if(drugId==0)
				check=false;
			// getAllGenericMedActionPlan();
			// getMedicalActionPlanList();
			 
			 for(MedActionPlan medPlan:medActionPlanList)
				{
					if(drugId==medPlan.getDrugId())
					{
						return true;

					}
				}
			// getAllGenericMedActionPlan();
			 for (ParentMedActionPlan  genMedActionPlan : allGenericMedActionPlan) {
				 
				 if(drugId==genMedActionPlan.getDrugId())
					{
						return true;

					}
				}
			 
			
		return check;
	}
	
	/*
	 * update med action plan and weekly dose
	 * @author: Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#updateTakeActionPlan(com.clinakos.data.model.patient.MedActionPlan, com.clinakos.data.model.patient.WeeklyDose)
	 */
	public void updateTakeActionPlan(MedActionPlan medActionPlan,
			WeeklyDose weeklyDose) {
	logger.info("updateTakeActionPlan method start in dao impl:::");
	medActionPlan.setLastUpdateDate(new DateUtil().getTodayDate());
	medActionPlan.setLastupdatedby(new ContextUtil().getLoggerFirstName()+" "+new ContextUtil().getLoggedUserMiddleName()+" "+new ContextUtil().getLoggerLastName());
	update(medActionPlan);
	
	Query query =getSessionFactory().getCurrentSession().createQuery("update WeeklyDose set sundayDose =:sundayDose ,mondayDose =:mondayDose, tuesDay =:tuesDay , " +
			" wednesdayDose =:wednesdayDose , thursdayDose =:thursdayDose, fridayDose =:fridayDose, saturdayDose =:saturdayDose" +
			" where ( patientId =:patientId  and drugId =:drugId ) ");
	
	query.setString("sundayDose", weeklyDose.getSundayDose());
	query.setString("mondayDose", weeklyDose.getMondayDose());
	query.setString("tuesDay", weeklyDose.getTuesDay());
	query.setString("wednesdayDose", weeklyDose.getWednesdayDose());
	query.setString("thursdayDose", weeklyDose.getThursdayDose());
	query.setString("fridayDose", weeklyDose.getFridayDose());
	query.setString("saturdayDose", weeklyDose.getSaturdayDose());
	
	
	query.setInteger("patientId", weeklyDose.getPatientId());
	//query.setInteger("doctorId", weeklyDose.getDoctorId());
	query.setDouble("drugId", weeklyDose.getDrugId());
	
	int result = query.executeUpdate();
	System.out.println("111:::::::::::result:::"+result);
	if(result==0)
	{
		//insert into db..
		String insert_into_weeklyDose="insert into weekly_dose (user_id ,provider_id, doctor_id, medicine_name, drug_id, drug_name_id, strength, " +
				" sunday_dose, monday_dose , tuesday_dose, wednesday_dose, thursday_dose ,friday_dose , saturday_dose ) values " +
				"("+weeklyDose.getPatientId()+" ,"+weeklyDose.getProviderId()+" , "+weeklyDose.getDoctorId()+" , '"+weeklyDose.getMedicineName()+"', " +
				" "+weeklyDose.getDrugId()+"  ,   "+weeklyDose.getDrugNameId()+" , '"+weeklyDose.getStrength()+"' , '"+weeklyDose.getSundayDose()+"' , " +
				" '"+weeklyDose.getMondayDose()+"' , '"+weeklyDose.getTuesDay()+"' , '"+weeklyDose.getWednesdayDose()+"' , '"+weeklyDose.getThursdayDose()+"' , " +
						" '"+weeklyDose.getFridayDose()+"' , '"+weeklyDose.getSaturdayDose()+"' )";
		
		Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_into_weeklyDose);
		sqlQuery1.executeUpdate();
		
		}
	}
	
	/*
	 * find dose detail of particular meds and particular patient
	 * @author: Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findWeeklyDoseOfParticularPatient(int, int)
	 */
	public WeeklyDose findWeeklyDoseOfParticularPatient(int patientId,
			int doctorId, double drugId) {
		WeeklyDose weeklyDose=new WeeklyDose();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WeeklyDose.class);
		criteria.add(Restrictions.eq("patientId",patientId));
		//criteria.add(Restrictions.eq("doctorId",doctorId));
		criteria.add(Restrictions.eq("drugId",drugId));
		if(!criteria.list().isEmpty())
		{
			weeklyDose=(WeeklyDose) criteria.list().get(0);
		}
	
		return weeklyDose;
	}

	public List<WeeklyDose> findWeeklyDoseOfParticularPatientforanticoagmedicine(int loginid,int providerid) {
		System.out.println("login_in="+loginid+"provider_id"+providerid);
		List<WeeklyDose>weeklyDose=new ArrayList<WeeklyDose>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WeeklyDose.class);
		criteria.add(Restrictions.eq("patientId",loginid));
		criteria.add(Restrictions.eq("providerId",providerid));
		weeklyDose=criteria.list();
	
		return weeklyDose;
	}
	
	/*
	 * find full drug detail..
	 */

	public WSDrug findDrugDetailAccordingToDrugId(double drugId) {
		logger.info("drugid of medicine is"+drugId);
		WSDrug wsdrug=new WSDrug();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		
		criteria.add(Restrictions.eq("drugId",drugId));
		criteria.add(Restrictions.eq("dataProvider","F"));
		if(criteria.list().size()!=0)
			wsdrug= (WSDrug) criteria.list().get(0);
		
		System.out.println("::::::::::::::wsdrug:::::::"+wsdrug.getGenericName()+"::::::::::"+wsdrug.getDrugId());
		return wsdrug;
	}

	/*
	 * finding  drug name id according to drug unique id..
	 * @author:Gopal Krishna jha
	 */

	public double findDrugNameIdAcordingToDrugId(double drugId) {
		logger.info("findDrugNameIdAcordingToDrugId::::::"+drugId);
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		
		criteria.add(Restrictions.eq("drugId",drugId));
		criteria.add(Restrictions.eq("dataProvider","F"));
		criteria.setProjection( Projections.projectionList().
				add( Projections.property("drugNameId") ));
		double drugnameId=(Double) criteria.uniqueResult();
		return drugnameId;
	}

	/*
	 * save value in user detail for integration new crop to 
	 * @autthor:Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#saveValueForIntegration(int)
	 */
	public void saveValueForIntegration(int patientId, boolean checkIntegration) {
		logger.info("saveValueForIntegration::"+patientId);
		Query query =getSessionFactory().getCurrentSession().createQuery("update UserLoginDetail set checkIntegration =:checkIntegration where userId =:userId ");
		query.setBoolean("checkIntegration", checkIntegration);
		query.setInteger("userId", patientId);
		
		 query.executeUpdate();
		//System.out.println("11:::::::::::result:::"+result);
		
	}

	/*
	 * find integration status of particular user..
	 * @author: Gopal krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findStatusOfIntegration(int)
	 */
	public boolean findStatusOfIntegration(int patientId) {
		boolean check=false;
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
		//int userId=0,role=0;
		criteria.add(Restrictions.eq("userId",patientId));
		criteria.setProjection( Projections.projectionList().
				add( Projections.property("checkIntegration") ));
		check=(Boolean)criteria.uniqueResult();
		logger.info("findStatusOfIntegration::::::"+check);
		return check;
	}

	/**
	 * Get Patient Medication Drug Id on the basis of Drug Name 
	 */
	public Double getDrugIdByDrugName(String medicineName) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		                  criteria.add(Restrictions.eq("drugs",medicineName));
		 
		 Double drugId=0.0;                 
		 List<PatientMedicationData>  patientMedicineList=criteria.list();
		 //System.out.println("medicineNameeeeee "+medicineName+"Size"+patientMedicineList.size());
		 if(!(patientMedicineList.size()==0)){
			 
			PatientMedicationData patientMedicationData=patientMedicineList.get(0);
			 drugId=patientMedicationData.getDrugId();
			 //System.out.println("druggggId in if condition "+drugId);
		 }
		return drugId;
	}

	
	public List<MedUnitSummary> findUnitSummaryList() {
		// TODO Auto-generated method stub
		List<MedUnitSummary>unitSummaryList=getSessionFactory().getCurrentSession().createCriteria(MedUnitSummary.class).list();
		return unitSummaryList;
	}

	/*
	 * find lab detail list according to generic medicine nema..
	 * @author:Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findLabDetailListAccordingToGenericname(java.lang.String)
	 */
	public List<LabDetail> findLabDetailListAccordingToGenericname(
			String genericDrugs) {
		logger.info("findLabDetailListAccordingToGenericname::::::::::"+genericDrugs);
		List<LabDetail>labDetailList=new ArrayList<LabDetail>();
		labDetailList=getSessionFactory().getCurrentSession().createCriteria(LabDetail.class).
				add(Restrictions.eq("genericMedicineName", genericDrugs)).list();
				
		//System.out.println("::::::::::::::Size:::"+labDetailList.size());
		
		return labDetailList;
	}


/*
 * method to edit allergy
 * @author saurabh
 * @param patientAllergy 
 * @param Allergy Data for Edit 
 * @see com.clinakos.data.dao.IPatientMedicineDAO#editAllergyDetails(com.clinakos.data.model.patient.PatientAllergy)
 */
	public void editAllergyDetails(PatientAllergy patientAllergy, String allergyDetailForEdit) {
		logger.info("editAllergyDetails method start in dao::::"+patientAllergy.getAllergyName());
		update(patientAllergy);
		/*logger.info("editAllergyDetails::::::::::"+patientAllergy.getAllergyDescription()+":::"+patientAllergy.getAllergyId()+":::"+patientAllergy.getPatientId()+":::"+patientAllergy.getAllergyDescription());
		String update_PatientAllergy=("update PatientAllergy set allergyDescription = :allergyDescription" +
				" where allergyId = :allergyId and patientId = :patientId");
		Query hql = getSessionFactory().getCurrentSession().createQuery(update_PatientAllergy);
		hql.setParameter("allergyDescription", allergyDetailForEdit);
		hql.setParameter("allergyId", patientAllergy.getAllergyId());
		hql.setParameter("patientId", patientAllergy.getPatientId());
		hql.executeUpdate();*/
		//logger.info("editAllergyDetails:::::updated");
	}
	/*
	 * save pharmacy detail of particular user..
	 * @author:Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#updatePharmacyDetailOfParticularUser(java.util.List)
	 */

	public void updatePharmacyDetailOfParticularUser(
			List<PharmacyDetail> pharmacyDetailListForUpdate,int patientId) {
		logger.info("updatePharmacyDetailOfParticularUser method start in dao:::"+pharmacyDetailListForUpdate.size());
		String deletePharmacyDetail=("delete PharmacyDetail " +
				" where patientId = :patientId ");
		Query hql = getSessionFactory().getCurrentSession().createQuery(deletePharmacyDetail);
		hql.setParameter("patientId", patientId);
		hql.executeUpdate();
		
		for(PharmacyDetail pharmacyDetail:pharmacyDetailListForUpdate)
			getSessionFactory().getCurrentSession().save(pharmacyDetail);
			//save(pharmacyDetail);
		
		
	}

	
	public void integrateDrugInteractionToDatabase(
			List<DrugInteraction> drugInteractionList) {
		List<PatientDrugMapping>patientDrugMappingList=new ArrayList<PatientDrugMapping>();
		logger.info("************Start Integrating Drug Drug Interaction into Clinakos datasbase**************");
		try{
		int patientIdForDrugMapping=new ContextUtil().getPatientId();	
		Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugMapping.class).add(Restrictions.eq("patientID", patientIdForDrugMapping));
		patientDrugMappingList=patientDrugMappingCriteria.list();
		logger.info("patient drug mapping size from database"+patientDrugMappingList.size());
		logger.info("drug interaction web service respnse  size "+drugInteractionList.size());
		//If Database contains no records for given patientID execute if
		if(patientDrugMappingList.size()==0){
			List<MasterDrugDrugInteraction>uniqueDrugInteractionList=new ArrayList<MasterDrugDrugInteraction>();
			for(DrugInteraction drugInteraction:drugInteractionList){
				MasterDrugDrugInteraction masterDrugDrugInteraction=new MasterDrugDrugInteraction();
				masterDrugDrugInteraction.setDrug1ID(drugInteraction.getDrug1ID());
				masterDrugDrugInteraction.setDrug2ID(drugInteraction.getDrug2ID());
				masterDrugDrugInteraction.setDrug1Name(drugInteraction.getDrug1());
				masterDrugDrugInteraction.setDrug2Name(drugInteraction.getDrug2());
				masterDrugDrugInteraction.setClinicalEffects(drugInteraction.getClinicalEffects());
				masterDrugDrugInteraction.setSeverityLevels(drugInteraction.getSeverityLevel());
				masterDrugDrugInteraction.setDiscussion(drugInteraction.getDiscussion());
				masterDrugDrugInteraction.setDrug1Type(drugInteraction.getDrug1Type());
				masterDrugDrugInteraction.setDrug2Type(drugInteraction.getDrug2Type());
				masterDrugDrugInteraction.setMechanismOfAction(drugInteraction.getMechanismOfAction());
				masterDrugDrugInteraction.setMonographTitle(drugInteraction.getMonographTitle());
				masterDrugDrugInteraction.setPatientManagement(drugInteraction.getPatientManagement());
				masterDrugDrugInteraction.setPerformance(drugInteraction.getPerformance());
				masterDrugDrugInteraction.setPredisposingFactors(drugInteraction.getPredisposingFactors());
				masterDrugDrugInteraction.setReferences(drugInteraction.getReferences());
				if(!uniqueDrugInteractionList.contains(masterDrugDrugInteraction)){
					uniqueDrugInteractionList.add(masterDrugDrugInteraction);
				}
			}
			logger.info("uniqueDrugInteractionList extracted from drug-drug webservice response::::"+uniqueDrugInteractionList.size());
			for(MasterDrugDrugInteraction masterDrugDrugInteraction:uniqueDrugInteractionList){
				MasterDrugDrugInteraction saveToMasterDrugInteraction=saveMasterDrugInteraction(masterDrugDrugInteraction);
				saveToPatientDrugMapping(saveToMasterDrugInteraction);
			}
			
		}
		else{
			//if database contains already records for existing patient
			//first make uniquess in drug webservice response
			List<MasterDrugDrugInteraction>databaseDrugList=new ArrayList<MasterDrugDrugInteraction>();
			List<MasterDrugDrugInteraction>uniqueDrugInteractionList=new ArrayList<MasterDrugDrugInteraction>();
			for(DrugInteraction drugInteraction:drugInteractionList){
				MasterDrugDrugInteraction masterDrugDrugInteraction=new MasterDrugDrugInteraction();
				masterDrugDrugInteraction.setDrug1ID(drugInteraction.getDrug1ID());
				masterDrugDrugInteraction.setDrug2ID(drugInteraction.getDrug2ID());
				masterDrugDrugInteraction.setDrug1Name(drugInteraction.getDrug1());
				masterDrugDrugInteraction.setDrug2Name(drugInteraction.getDrug2());
				masterDrugDrugInteraction.setClinicalEffects(drugInteraction.getClinicalEffects());
				masterDrugDrugInteraction.setSeverityLevels(drugInteraction.getSeverityLevel());
				masterDrugDrugInteraction.setDiscussion(drugInteraction.getDiscussion());
				masterDrugDrugInteraction.setDrug1Type(drugInteraction.getDrug1Type());
				masterDrugDrugInteraction.setDrug2Type(drugInteraction.getDrug2Type());
				masterDrugDrugInteraction.setMechanismOfAction(drugInteraction.getMechanismOfAction());
				masterDrugDrugInteraction.setMonographTitle(drugInteraction.getMonographTitle());
				masterDrugDrugInteraction.setPatientManagement(drugInteraction.getPatientManagement());
				masterDrugDrugInteraction.setPerformance(drugInteraction.getPerformance());
				masterDrugDrugInteraction.setPredisposingFactors(drugInteraction.getPredisposingFactors());
				masterDrugDrugInteraction.setReferences(drugInteraction.getReferences());
				if(!uniqueDrugInteractionList.contains(masterDrugDrugInteraction)){
					uniqueDrugInteractionList.add(masterDrugDrugInteraction);
				}
			}
			logger.info("else uniqueDrugInteractionList from web service response::::"+uniqueDrugInteractionList.size());
			
			for(PatientDrugMapping patientDrugMapping:patientDrugMappingList){
				MasterDrugDrugInteraction masterDrugDrugInteraction=new MasterDrugDrugInteraction();
				masterDrugDrugInteraction.setDrug1ID(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug1ID());
				masterDrugDrugInteraction.setDrug2ID(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug2ID());
				masterDrugDrugInteraction.setDrug1Name(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug1Name());
				masterDrugDrugInteraction.setDrug2Name(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug2Name());
				masterDrugDrugInteraction.setClinicalEffects(patientDrugMapping.getMasterDrugDrugInteractionId().getClinicalEffects());
				masterDrugDrugInteraction.setSeverityLevels(patientDrugMapping.getMasterDrugDrugInteractionId().getSeverityLevels());
				masterDrugDrugInteraction.setDiscussion(patientDrugMapping.getMasterDrugDrugInteractionId().getDiscussion());
				masterDrugDrugInteraction.setDrug1Type(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug1Type());
				masterDrugDrugInteraction.setDrug2Type(patientDrugMapping.getMasterDrugDrugInteractionId().getDrug2Type());
				masterDrugDrugInteraction.setMechanismOfAction(patientDrugMapping.getMasterDrugDrugInteractionId().getMechanismOfAction());
				masterDrugDrugInteraction.setMonographTitle(patientDrugMapping.getMasterDrugDrugInteractionId().getMonographTitle());
				masterDrugDrugInteraction.setPatientManagement(patientDrugMapping.getMasterDrugDrugInteractionId().getPatientManagement());
				masterDrugDrugInteraction.setPerformance(patientDrugMapping.getMasterDrugDrugInteractionId().getPerformance());
				masterDrugDrugInteraction.setPredisposingFactors(patientDrugMapping.getMasterDrugDrugInteractionId().getPredisposingFactors());
				masterDrugDrugInteraction.setReferences(patientDrugMapping.getMasterDrugDrugInteractionId().getReferences());
				if(!databaseDrugList.contains(masterDrugDrugInteraction)){
				databaseDrugList.add(masterDrugDrugInteraction);
				}
			}
			logger.info("unique databaseDrugList size "+databaseDrugList.size());
			List<MasterDrugDrugInteraction>uniqueDrugListMerging=new ArrayList<MasterDrugDrugInteraction>();
			for(MasterDrugDrugInteraction masterDrugDrugInteraction:uniqueDrugInteractionList){
				if(!databaseDrugList.contains(masterDrugDrugInteraction)){
					uniqueDrugListMerging.add(masterDrugDrugInteraction);
				}
			}
			logger.info("after removing dublicates from web service drug drug and database "+uniqueDrugListMerging.size());
			if(uniqueDrugListMerging.size()>0){
				for(MasterDrugDrugInteraction masterDrugDrugInteraction:uniqueDrugListMerging){
					MasterDrugDrugInteraction saveToMasterDrugInteraction=saveMasterDrugInteraction(masterDrugDrugInteraction);
					saveToPatientDrugMapping(saveToMasterDrugInteraction);
				}
			}		
		}
		}catch(HibernateException e){
			logger.error("error in integrating drug drug interaction web service response to database "+e.getMessage());
		}finally{
			logger.info("************end Integrating Drug Drug Interaction into Clinakos datasbase**************");
		}
	}

	private void saveToPatientDrugMapping(
			MasterDrugDrugInteraction saveToMasterDrugInteraction) {
		PatientDrugMapping patientDrugMapping =new PatientDrugMapping();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			patientDrugMapping.setMasterDrugDrugInteractionId(saveToMasterDrugInteraction);
			patientDrugMapping.setPatientID(patientIdForDrugMapping);
			patientDrugMapping.setStatus(true);
			saveOrUpdate(patientDrugMapping);
		}catch(HibernateException e){
			logger.error("error in saving to database:patient drug drug interaction "+e.getMessage());
		}
	}

	private MasterDrugDrugInteraction saveMasterDrugInteraction(
			MasterDrugDrugInteraction masterDrugDrugInteraction) {
		MasterDrugDrugInteraction masterDrugDrugInteractionDb=new MasterDrugDrugInteraction();
		try{
			masterDrugDrugInteractionDb.setDrug1ID(masterDrugDrugInteraction.getDrug1ID());
			masterDrugDrugInteractionDb.setDrug2ID(masterDrugDrugInteraction.getDrug2ID());
			masterDrugDrugInteractionDb.setDrug1Name(masterDrugDrugInteraction.getDrug1Name());
			masterDrugDrugInteractionDb.setDrug2Name(masterDrugDrugInteraction.getDrug2Name());
			masterDrugDrugInteractionDb.setClinicalEffects(masterDrugDrugInteraction.getClinicalEffects());
			masterDrugDrugInteractionDb.setSeverityLevels(masterDrugDrugInteraction.getSeverityLevels());
			masterDrugDrugInteractionDb.setDiscussion(masterDrugDrugInteraction.getDiscussion());
			masterDrugDrugInteractionDb.setDrug1Type(masterDrugDrugInteraction.getDrug1Type());
			masterDrugDrugInteractionDb.setDrug2Type(masterDrugDrugInteraction.getDrug2Type());
			masterDrugDrugInteractionDb.setMechanismOfAction(masterDrugDrugInteraction.getMechanismOfAction());
			masterDrugDrugInteractionDb.setMonographTitle(masterDrugDrugInteraction.getMonographTitle());
			masterDrugDrugInteractionDb.setPatientManagement(masterDrugDrugInteraction.getPatientManagement());
			masterDrugDrugInteractionDb.setPerformance(masterDrugDrugInteraction.getPerformance());
			masterDrugDrugInteractionDb.setPredisposingFactors(masterDrugDrugInteraction.getPredisposingFactors());
			masterDrugDrugInteractionDb.setReferences(masterDrugDrugInteraction.getReferences());
			Hibernate.initialize(masterDrugDrugInteractionDb);
			getSessionFactory().getCurrentSession().save(masterDrugDrugInteractionDb);
			
		}catch(HibernateException e){
			logger.error("error in saving to database:master drug drug interaction "+e.getMessage());
		}
		return masterDrugDrugInteractionDb;
	}
	
	
	public List<PatientDrugMapping> getAllUniqueDrugRecords() {
		List<PatientDrugMapping>patientDrugMappingList=new ArrayList<PatientDrugMapping>();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugMapping.class);
			patientDrugMappingCriteria.add(Restrictions.eq("patientID", patientIdForDrugMapping));
			patientDrugMappingCriteria.add(Restrictions.eq("status", true));
			patientDrugMappingList=patientDrugMappingCriteria.list();
		}catch(HibernateException e){
			logger.error("error in getting all unique drug mapping from database "+e.getMessage());
		}
		return patientDrugMappingList;
	}


	public void ignoreSelectedDrugInDatabase(
			DrugDrugInteractionData drugDrugInteractionData) {
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugDrugCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugMapping.class);
			patientDrugDrugCriteria.add(Restrictions.idEq(drugDrugInteractionData.getId()));
			patientDrugDrugCriteria.add(Restrictions.eq("patientID", patientIdForDrugMapping));
			PatientDrugMapping patientDrugMapping=(PatientDrugMapping) patientDrugDrugCriteria.list().get(0);
			patientDrugMapping.setStatus(false);
			update(patientDrugMapping);
		}catch(HibernateException e){
			logger.error("error in updating drug drug status in database "+e.getMessage());
		}
		
	}

	
	public void integrateDrugAllergyInteractionToDatabase(
			List<DrugAllergyDetail> drugAllergyDetailList) {
		List<PatientDrugAllergyInteraction>patientDrugAllergyList=new ArrayList<PatientDrugAllergyInteraction>();
		logger.info("************Start Integrating Drug Allergy Interaction into Clinakos datasbase**************");
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();	
			Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugAllergyInteraction.class).add(Restrictions.eq("patientID", patientIdForDrugMapping));
			patientDrugAllergyList=patientDrugMappingCriteria.list();
			logger.info("patient drug allergy mapping size from database"+patientDrugAllergyList.size());
			logger.info("drug allergy interaction web service respnse  size "+drugAllergyDetailList.size());
			//If Database contains no records for given patientID execute if
			if(patientDrugAllergyList.size()==0){
				List<MasterDrugAllergyInteraction>uniqueDrugAllergyInteractionList=new ArrayList<MasterDrugAllergyInteraction>();
				for(DrugAllergyDetail drugAllergyDetail:drugAllergyDetailList){
				MasterDrugAllergyInteraction masterDrugDrugInteraction=new MasterDrugAllergyInteraction();
				masterDrugDrugInteraction.setInteractionText(drugAllergyDetail.getInteractionText());
					if(!uniqueDrugAllergyInteractionList.contains(masterDrugDrugInteraction)){
						uniqueDrugAllergyInteractionList.add(masterDrugDrugInteraction);
					}
				}
				logger.info("uniqueDrugInteractionList extracted from drug-allergy webservice response::::"+uniqueDrugAllergyInteractionList.size());
				for(MasterDrugAllergyInteraction masterDrugAllergyInteraction:uniqueDrugAllergyInteractionList){
					MasterDrugAllergyInteraction saveToMasterDrugAllergyInteraction=saveMasterDrugAllergyInteraction(masterDrugAllergyInteraction);
					saveToPatientDrugAllergyMapping(saveToMasterDrugAllergyInteraction);
				}
		}else{
			List<MasterDrugAllergyInteraction>uniqueDrugAllergyWebServiceList=new ArrayList<MasterDrugAllergyInteraction>();
			for(DrugAllergyDetail drugAllergyDetail:drugAllergyDetailList){
				MasterDrugAllergyInteraction masterDrugDrugInteraction=new MasterDrugAllergyInteraction();
				masterDrugDrugInteraction.setInteractionText(drugAllergyDetail.getInteractionText());
					if(!uniqueDrugAllergyWebServiceList.contains(masterDrugDrugInteraction)){
						uniqueDrugAllergyWebServiceList.add(masterDrugDrugInteraction);
					}
				}
			logger.info("unique drug allergy list from web service response "+uniqueDrugAllergyWebServiceList.size());
			List<MasterDrugAllergyInteraction>uniqueDrugAllergyDbList=new ArrayList<MasterDrugAllergyInteraction>();
			for(PatientDrugAllergyInteraction patientDrugAllergyInteraction:patientDrugAllergyList){
				MasterDrugAllergyInteraction masterDrugDrugInteraction=new MasterDrugAllergyInteraction();
				masterDrugDrugInteraction.setInteractionText(patientDrugAllergyInteraction.getMasterDrugAllergyInteraction().getInteractionText());
				if(!uniqueDrugAllergyDbList.contains(masterDrugDrugInteraction)){
					uniqueDrugAllergyDbList.add(masterDrugDrugInteraction);
				}
			}
			logger.info("unique drug allergy list from database "+uniqueDrugAllergyDbList.size());
			List<MasterDrugAllergyInteraction>uniqueDrugAllergyMergeList=new ArrayList<MasterDrugAllergyInteraction>();
			for(MasterDrugAllergyInteraction drugAllergyInteraction:uniqueDrugAllergyMergeList){
				if(!uniqueDrugAllergyDbList.contains(drugAllergyInteraction)){
					uniqueDrugAllergyMergeList.add(drugAllergyInteraction);
				}
			}
			logger.info("after merging & comparing drug allergy web service response and database to check for uniqueness "+uniqueDrugAllergyMergeList.size());
			if(uniqueDrugAllergyMergeList.size()>0){
				for(MasterDrugAllergyInteraction masterDrugAllergyInteraction:uniqueDrugAllergyMergeList){
					MasterDrugAllergyInteraction saveToMasterDrugAllergyInteraction=saveMasterDrugAllergyInteraction(masterDrugAllergyInteraction);
					saveToPatientDrugAllergyMapping(saveToMasterDrugAllergyInteraction);
				}
			}
			
		}
		}catch(HibernateException e){
			logger.error("error in integrating drug allergy interaction web service response to database "+e.getMessage());
		}finally{
			logger.info("************end Integrating Drug Allergy Interaction into Clinakos datasbase**************");
		}
	}

	private void saveToPatientDrugAllergyMapping(
			MasterDrugAllergyInteraction saveToMasterDrugAllergyInteraction) {
		PatientDrugAllergyInteraction patientDrugAllergyInteraction=new PatientDrugAllergyInteraction();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			patientDrugAllergyInteraction.setMasterDrugAllergyInteraction(saveToMasterDrugAllergyInteraction);
			patientDrugAllergyInteraction.setPatientID(patientIdForDrugMapping);
			patientDrugAllergyInteraction.setStatus(true);
			save(patientDrugAllergyInteraction);
		}catch(HibernateException e){
			logger.error("error in saving drug allergy mapping to database "+e.getMessage());
		}
	}

	private MasterDrugAllergyInteraction saveMasterDrugAllergyInteraction(
			MasterDrugAllergyInteraction masterDrugAllergyInteraction) {
		MasterDrugAllergyInteraction drugAllergyInteraction=new MasterDrugAllergyInteraction();
		try{
			drugAllergyInteraction.setInteractionText(masterDrugAllergyInteraction.getInteractionText());
			save(drugAllergyInteraction);
		}catch(HibernateException e){
			logger.error("error in saving master drug allergy interaction to database "+e.getMessage());
		}
		return drugAllergyInteraction;
	}


	public List<PatientDrugAllergyInteraction> getAllUniquePatientDrugAllergyRecords() {
		List<PatientDrugAllergyInteraction>patientDrugAllergyInteractionList=new ArrayList<PatientDrugAllergyInteraction>();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugAllergyInteraction.class);
			patientDrugMappingCriteria.add(Restrictions.eq("patientID", patientIdForDrugMapping));
			patientDrugMappingCriteria.add(Restrictions.eq("status", true));
			patientDrugAllergyInteractionList=patientDrugMappingCriteria.list();
		}catch(HibernateException e){
			logger.error("error in getting unique drug allergy from database "+e.getMessage());
		}
		return patientDrugAllergyInteractionList;
	}


	public void ignoreSelectedDrugAllergy(
			MasterDrugAllergyInteraction masterDrugAllergyInteraction) {
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugDrugCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugAllergyInteraction.class);
			patientDrugDrugCriteria.add(Restrictions.idEq(masterDrugAllergyInteraction.getPatientDrugAllergyDbId()));
			patientDrugDrugCriteria.add(Restrictions.eq("patientID", patientIdForDrugMapping));
			PatientDrugAllergyInteraction patientDrugMapping=(PatientDrugAllergyInteraction) patientDrugDrugCriteria.list().get(0);
			patientDrugMapping.setStatus(false);
			update(patientDrugMapping);
		}catch(HibernateException e){
			logger.error("error in updating status in ignoreSelectedDrugAllergy"+e.getMessage());
		}
	}

	
//	===========================================
	
	public void integrateDrugDiseaseInteractionToDatabase(
			List<DrugDiseaseDetail> drugDiseaseDetailList) {
		List<PatientDrugDiseaseInteraction>patientDrugDiseaseList=new ArrayList<PatientDrugDiseaseInteraction>();
		logger.info("************Start Integrating Drug disease Interaction into Clinakos datasbase**************");
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();	
			Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugDiseaseInteraction.class).add(Restrictions.eq("patientId", patientIdForDrugMapping));
			patientDrugDiseaseList=patientDrugMappingCriteria.list();
			logger.info("patient drug disease mapping size from database"+patientDrugDiseaseList.size());
			logger.info("drug disease interaction web service respnse  size "+drugDiseaseDetailList.size());
			//If Database contains no records for given patientID execute if
			if(patientDrugDiseaseList.size()==0){
				List<MasterDrugDiseaseInteraction>uniqueDrugDiseaseInteractionList=new ArrayList<MasterDrugDiseaseInteraction>();
				for(DrugDiseaseDetail drugDiseaseDetail:drugDiseaseDetailList){
					MasterDrugDiseaseInteraction masterDrugDiseaseInteraction=new MasterDrugDiseaseInteraction();
				masterDrugDiseaseInteraction.setDirectCondition(drugDiseaseDetail.getDirectCondition());
				masterDrugDiseaseInteraction.setDiseaseRelation(drugDiseaseDetail.getDiseaseRelation());
				masterDrugDiseaseInteraction.setDiseaseRelationText(drugDiseaseDetail.getDiseaseRelationText());
				masterDrugDiseaseInteraction.setDrugName(drugDiseaseDetail.getDrugName());
				masterDrugDiseaseInteraction.setIcd9(drugDiseaseDetail.getICD9());
				masterDrugDiseaseInteraction.setRelatedCondition(drugDiseaseDetail.getRelatedCondition());
				masterDrugDiseaseInteraction.setSeverityLevel(drugDiseaseDetail.getSeverityLevel());
				masterDrugDiseaseInteraction.setSeverityLevelShortText(drugDiseaseDetail.getSeverityLevelShortText());
				masterDrugDiseaseInteraction.setSeverityLevelText(drugDiseaseDetail.getSeverityLevelText());
					if(!uniqueDrugDiseaseInteractionList.contains(masterDrugDiseaseInteraction)){
						uniqueDrugDiseaseInteractionList.add(masterDrugDiseaseInteraction);
					}
				}
				logger.info("uniqueDrugInteractionList extracted from drug-disease webservice response::::"+uniqueDrugDiseaseInteractionList.size());
				for(MasterDrugDiseaseInteraction masterDrugDiseaseInteraction:uniqueDrugDiseaseInteractionList){
					MasterDrugDiseaseInteraction saveToMasterDrugDiseaseInteraction=saveMasterDrugDiseaseInteraction(masterDrugDiseaseInteraction);
					saveToPatientDrugDiseaseMapping(saveToMasterDrugDiseaseInteraction);
				}
		}else{
			List<MasterDrugDiseaseInteraction>uniqueDrugDiseaseWebServiceList=new ArrayList<MasterDrugDiseaseInteraction>();
			for(DrugDiseaseDetail drugDiseaseDetail:drugDiseaseDetailList){
				MasterDrugDiseaseInteraction masterDrugDiseaseInteraction=new MasterDrugDiseaseInteraction();
				masterDrugDiseaseInteraction.setDirectCondition(drugDiseaseDetail.getDirectCondition());
				masterDrugDiseaseInteraction.setDiseaseRelation(drugDiseaseDetail.getDiseaseRelation());
				masterDrugDiseaseInteraction.setDiseaseRelationText(drugDiseaseDetail.getDiseaseRelationText());
				masterDrugDiseaseInteraction.setDrugName(drugDiseaseDetail.getDrugName());
				masterDrugDiseaseInteraction.setIcd9(drugDiseaseDetail.getICD9());
				masterDrugDiseaseInteraction.setRelatedCondition(drugDiseaseDetail.getRelatedCondition());
				masterDrugDiseaseInteraction.setSeverityLevel(drugDiseaseDetail.getSeverityLevel());
				masterDrugDiseaseInteraction.setSeverityLevelShortText(drugDiseaseDetail.getSeverityLevelShortText());
				masterDrugDiseaseInteraction.setSeverityLevelText(drugDiseaseDetail.getSeverityLevelText());
				if(!uniqueDrugDiseaseWebServiceList.contains(masterDrugDiseaseInteraction)){
					uniqueDrugDiseaseWebServiceList.add(masterDrugDiseaseInteraction);
				}
				}
			logger.info("unique drug disease list from web service response "+uniqueDrugDiseaseWebServiceList.size());
			List<MasterDrugDiseaseInteraction>uniqueDrugDiseaseDbList=new ArrayList<MasterDrugDiseaseInteraction>();
			for(PatientDrugDiseaseInteraction patientDrugDiseaseInteraction:patientDrugDiseaseList){
				MasterDrugDiseaseInteraction masterDrugDiseaseInteraction=new MasterDrugDiseaseInteraction();
				masterDrugDiseaseInteraction.setDirectCondition(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getDirectCondition());
				masterDrugDiseaseInteraction.setDiseaseRelation(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getDiseaseRelation());
				masterDrugDiseaseInteraction.setDiseaseRelationText(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getDiseaseRelationText());
				masterDrugDiseaseInteraction.setDrugName(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getDrugName());
				masterDrugDiseaseInteraction.setIcd9(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getIcd9());
				masterDrugDiseaseInteraction.setRelatedCondition(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getRelatedCondition());
				masterDrugDiseaseInteraction.setSeverityLevel(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getSeverityLevel());
				masterDrugDiseaseInteraction.setSeverityLevelShortText(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getSeverityLevelShortText());
				masterDrugDiseaseInteraction.setSeverityLevelText(patientDrugDiseaseInteraction.getMasterDrugDiseaseInteraction().getSeverityLevelText());
				if(!uniqueDrugDiseaseDbList.contains(masterDrugDiseaseInteraction)){
					uniqueDrugDiseaseDbList.add(masterDrugDiseaseInteraction);
				}
			}
			logger.info("unique drug disease list from database "+uniqueDrugDiseaseDbList.size());
			List<MasterDrugDiseaseInteraction>uniqueDrugDiseaseMergeList=new ArrayList<MasterDrugDiseaseInteraction>();
			for(MasterDrugDiseaseInteraction drugDiseaseInteraction:uniqueDrugDiseaseWebServiceList){
				if(!uniqueDrugDiseaseDbList.contains(drugDiseaseInteraction)){
					uniqueDrugDiseaseMergeList.add(drugDiseaseInteraction);
				}
			}
			logger.info("after merging & comparing drug disease web service response and database to check for uniqueness "+uniqueDrugDiseaseMergeList.size());
			if(uniqueDrugDiseaseMergeList.size()>0){
				for(MasterDrugDiseaseInteraction masterDrugDiseaseInteraction:uniqueDrugDiseaseMergeList){
					MasterDrugDiseaseInteraction saveToMasterDrugDiseaseInteraction=saveMasterDrugDiseaseInteraction(masterDrugDiseaseInteraction);
					saveToPatientDrugDiseaseMapping(saveToMasterDrugDiseaseInteraction);
				}
			}
			
		}
		}catch(HibernateException e){
			logger.error("error in integrating drug disease interaction web service response to database "+e.getMessage());
		}finally{
			logger.info("************end Integrating Drug disease Interaction into Clinakos datasbase**************");
		}
	}
	private void saveToPatientDrugDiseaseMapping(
			MasterDrugDiseaseInteraction saveToMasterDrugDiseaseInteraction) {
		PatientDrugDiseaseInteraction patientDrugDiseaseInteraction=new PatientDrugDiseaseInteraction();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			patientDrugDiseaseInteraction.setPatientId(patientIdForDrugMapping);
			patientDrugDiseaseInteraction.setMasterDrugDiseaseInteraction(saveToMasterDrugDiseaseInteraction);
			patientDrugDiseaseInteraction.setStatus(true);
			save(patientDrugDiseaseInteraction);
		}catch(HibernateException e){
			logger.error("error in saving drug allergy mapping to database "+e.getMessage());
		}
	}

	private MasterDrugDiseaseInteraction saveMasterDrugDiseaseInteraction(
			MasterDrugDiseaseInteraction masterDrugDiseaseInteraction) {
		MasterDrugDiseaseInteraction drugDiseaseInteraction=new MasterDrugDiseaseInteraction();
		try{
			drugDiseaseInteraction.setDirectCondition(masterDrugDiseaseInteraction.getDirectCondition());
			drugDiseaseInteraction.setDiseaseRelation(masterDrugDiseaseInteraction.getDiseaseRelation());
			drugDiseaseInteraction.setDiseaseRelationText(masterDrugDiseaseInteraction.getDiseaseRelationText());
			drugDiseaseInteraction.setDrugName(masterDrugDiseaseInteraction.getDrugName());
			drugDiseaseInteraction.setIcd9(masterDrugDiseaseInteraction.getIcd9());
			drugDiseaseInteraction.setRelatedCondition(masterDrugDiseaseInteraction.getRelatedCondition());
			drugDiseaseInteraction.setSeverityLevel(masterDrugDiseaseInteraction.getSeverityLevel());
			drugDiseaseInteraction.setSeverityLevelShortText(masterDrugDiseaseInteraction.getSeverityLevelShortText());
			drugDiseaseInteraction.setSeverityLevelText(masterDrugDiseaseInteraction.getSeverityLevelText());
			save(drugDiseaseInteraction);
		}catch(HibernateException e){
			logger.error("error in saving master drug allergy interaction to database "+e.getMessage());
		}
		return drugDiseaseInteraction;
	}

	public List<PatientDrugDiseaseInteraction> getAllUniquePatientDrugDiseaseRecords() {
		List<PatientDrugDiseaseInteraction>patientDrugDiseaseInteractionList=new ArrayList<PatientDrugDiseaseInteraction>();
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugMappingCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugDiseaseInteraction.class);
			patientDrugMappingCriteria.add(Restrictions.eq("patientId", patientIdForDrugMapping));
			patientDrugMappingCriteria.add(Restrictions.eq("status", true));
			patientDrugDiseaseInteractionList=patientDrugMappingCriteria.list();
		}catch(HibernateException e){
			logger.error("error in getting unique drug disease from database "+e.getMessage());
		}
		return patientDrugDiseaseInteractionList;
	}
	

	public void ignoreSelectedDrugDisease(
			MasterDrugDiseaseInteraction masterDrugDiseaseInteraction) {
		try{
			int patientIdForDrugMapping=new ContextUtil().getPatientId();
			Criteria patientDrugDrugCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugDiseaseInteraction.class);
			patientDrugDrugCriteria.add(Restrictions.idEq(masterDrugDiseaseInteraction.getPatientDrugDiseaseDbId()));
			patientDrugDrugCriteria.add(Restrictions.eq("patientId", patientIdForDrugMapping));
			PatientDrugDiseaseInteraction patientDrugMapping=(PatientDrugDiseaseInteraction) patientDrugDrugCriteria.list().get(0);
			patientDrugMapping.setStatus(false);
			update(patientDrugMapping);
		}catch(HibernateException e){
			logger.error("error in updating status in ignoreSelectedDrugDisease"+e.getMessage());
		}
	}


	public void integratePharmcogenomicsCurrentMedicineToDb(
			List<PharmacogenomicsRecomendations> pharmacogenomicsRecomendationsListForImpactedMedicine) {
		List<PatientPharmacogenomicsCurrentMedicineData>pharmacogenomicsCurrentMedicineDataList=new ArrayList<PatientPharmacogenomicsCurrentMedicineData>();
		try{
			int patientId=new ContextUtil().getPatientId();
			Criteria patientPharmacogenomicsCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientPharmacogenomicsCurrentMedicineData.class);
			patientPharmacogenomicsCriteria.add(Restrictions.eq("patientId", patientId));
			pharmacogenomicsCurrentMedicineDataList=patientPharmacogenomicsCriteria.list();
			logger.info("pharmacogenomicsCurrentMedicineDataList size "+pharmacogenomicsCurrentMedicineDataList.size());
			logger.info("pharmacogenomics size obtained from database"+pharmacogenomicsRecomendationsListForImpactedMedicine.size());
			//database contains no records for patient id then execute if 
			if(pharmacogenomicsCurrentMedicineDataList.size()==0){
				//then save all list to database
				logger.info("inside if");
				for(PharmacogenomicsRecomendations pharmacogenomicsRecomendations:pharmacogenomicsRecomendationsListForImpactedMedicine){
					PatientPharmacogenomicsCurrentMedicineData pharmacogenomicsCurrentMedicineData=new PatientPharmacogenomicsCurrentMedicineData();
					pharmacogenomicsCurrentMedicineData.setPatientId(patientId);
					pharmacogenomicsCurrentMedicineData.setStatus(true);
					pharmacogenomicsCurrentMedicineData.setAttentionRating(pharmacogenomicsRecomendations.getAttentionRating());
					pharmacogenomicsCurrentMedicineData.setDirections("");
					pharmacogenomicsCurrentMedicineData.setDrug(pharmacogenomicsRecomendations.getDrugName());
					pharmacogenomicsCurrentMedicineData.setGeneSymbol(pharmacogenomicsRecomendations.getPharmacogenomicsId().getGeneSymbol());
					pharmacogenomicsCurrentMedicineData.setImpact(pharmacogenomicsRecomendations.getImpact());
					pharmacogenomicsCurrentMedicineData.setImplications(pharmacogenomicsRecomendations.getImplications());
					pharmacogenomicsCurrentMedicineData.setRecommendation(pharmacogenomicsRecomendations.getRecommendation());
					pharmacogenomicsCurrentMedicineData.setStrengths("");
					pharmacogenomicsCurrentMedicineData.setImplicationOvaleMessage(pharmacogenomicsRecomendations.getOvaleMessage());
					save(pharmacogenomicsCurrentMedicineData);
				}
			}else{
				logger.info("inside else");
				List<PatientPharmacogenomicsCurrentMedicineData>procedurePharmacogenomicsList=new ArrayList<PatientPharmacogenomicsCurrentMedicineData>();
				for(PharmacogenomicsRecomendations pharmacogenomicsRecomendations:pharmacogenomicsRecomendationsListForImpactedMedicine){
					PatientPharmacogenomicsCurrentMedicineData pharmacogenomicsCurrentMedicineData=new PatientPharmacogenomicsCurrentMedicineData();
					pharmacogenomicsCurrentMedicineData.setPatientId(patientId);
					pharmacogenomicsCurrentMedicineData.setStatus(true);
					pharmacogenomicsCurrentMedicineData.setAttentionRating(pharmacogenomicsRecomendations.getAttentionRating());
					pharmacogenomicsCurrentMedicineData.setDirections("");
					pharmacogenomicsCurrentMedicineData.setDrug(pharmacogenomicsRecomendations.getDrugName());
					pharmacogenomicsCurrentMedicineData.setGeneSymbol(pharmacogenomicsRecomendations.getPharmacogenomicsId().getGeneSymbol());
					pharmacogenomicsCurrentMedicineData.setImpact(pharmacogenomicsRecomendations.getImpact());
					pharmacogenomicsCurrentMedicineData.setImplications(pharmacogenomicsRecomendations.getImplications());
					pharmacogenomicsCurrentMedicineData.setRecommendation(pharmacogenomicsRecomendations.getRecommendation());
					pharmacogenomicsCurrentMedicineData.setStrengths("");
					pharmacogenomicsCurrentMedicineData.setImplicationOvaleMessage(pharmacogenomicsRecomendations.getOvaleMessage());
					procedurePharmacogenomicsList.add(pharmacogenomicsCurrentMedicineData);
				}
				List<PatientPharmacogenomicsCurrentMedicineData>patientPharmcogenicUniqueList=new ArrayList<PatientPharmacogenomicsCurrentMedicineData>();
				for(PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCurrentMedicineData:procedurePharmacogenomicsList){
					if(!pharmacogenomicsCurrentMedicineDataList.contains(patientPharmacogenomicsCurrentMedicineData)){
						patientPharmcogenicUniqueList.add(patientPharmacogenomicsCurrentMedicineData);	
					}
				}
				if(patientPharmcogenicUniqueList.size()>0){
					for(PatientPharmacogenomicsCurrentMedicineData pharmacogenomicsCurrentMedicineData:patientPharmcogenicUniqueList){
						save(pharmacogenomicsCurrentMedicineData);
					}
				}
				
			}
			
		}catch(HibernateException he){
			logger.error("error in saving integratePharmcogenomicsCurrentMedicineToDb"+he.getMessage());
		}
		
	}



	public List<PatientPharmacogenomicsCurrentMedicineData> getAllRecordsFromPharmcogenics(boolean status) {
		List<PatientPharmacogenomicsCurrentMedicineData>pharmacogenomicsCurrentMedicineDataList=new ArrayList<PatientPharmacogenomicsCurrentMedicineData>();
		try{
			int patientId=new ContextUtil().getPatientId();
			Criteria patientPharmacogenomicsCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientPharmacogenomicsCurrentMedicineData.class);
			patientPharmacogenomicsCriteria.add(Restrictions.eq("patientId", patientId));
			patientPharmacogenomicsCriteria.add(Restrictions.eq("status", status));
			pharmacogenomicsCurrentMedicineDataList=patientPharmacogenomicsCriteria.list();
		}catch(HibernateException e){
			logger.error("error in getting getAllRecordsFromPharmcogenics records "+e.getMessage());
		}
		return pharmacogenomicsCurrentMedicineDataList;
	}

	
	public void ignoreSelectedPharmcogenomics(
			PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCurrentMedicineData) {
		try{
			PatientPharmacogenomicsCurrentMedicineData patientPharmacogenomicsCriteria=(PatientPharmacogenomicsCurrentMedicineData) getSessionFactory().getCurrentSession().get(PatientPharmacogenomicsCurrentMedicineData.class,patientPharmacogenomicsCurrentMedicineData.getId());
			patientPharmacogenomicsCriteria.setStatus(false);
			update(patientPharmacogenomicsCriteria);
		}catch(NullPointerException ne){
			ne.printStackTrace();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}
	/*
	 * find patient discontinue medicine list acording t visit
	 * @author:Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findpatientDiscontinueMedicineListOfParticularVisit(int, int)
	 */
	public List<PatientMedicationData> findpatientDiscontinueMedicineListOfParticularVisit(
			int patientId, int providerId,Date selectedEncounterDateForPrint) {
		
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String selectedDateInStringFormat=format.format(selectedEncounterDateForPrint);
		Date minSelectedDate = null;
		
		try {
			minSelectedDate=format.parse(selectedDateInStringFormat);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date maxSelectedDate=new Date(minSelectedDate.getTime()+ TimeUnit.DAYS.toMillis(1));
		List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
		List<PatientMedicationData>patientMedicationDiscontinueList=new ArrayList<PatientMedicationData>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.between("actionDate", minSelectedDate, maxSelectedDate));
		
		
		
		
		/*criteria.add(Restrictions.eq("flagForVisit", true));*/
		patientMedicationHistoryList=criteria.list();
		
		System.out.println("patientMedicationHistoryList ::::::dao impl"+patientMedicationHistoryList.size());
		
		
		
		for(PatientMedicationHistory history:patientMedicationHistoryList)
		{
		/*if(history.getActionDate().equals(selectedDate) )*/
	    	 {
				 PatientMedicationData pat=new PatientMedicationData();
					pat.setModifyDate(history.getActionDate());
					pat.setMedicineStatus(history.getMedicineStatus());
					pat.setDrugs(history.getDrugs());
					pat.setStrengths(history.getStrength());
					pat.setDirections(history.getDirection());
					pat.setQuantity(history.getQuantity());
					pat.setReffils(history.getReffils());
					pat.setUnit(history.getUnit());
					pat.setDosageForm(history.getDosageForm());
					
					patientMedicationDiscontinueList.add(pat);
	    		
	    	 }
		
			
		}
		return patientMedicationDiscontinueList;
	}
	/*
	 * ending visit of particular patient when we exit new crop
	 * @autor: Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#endingVisitOfParticularPatient(int, int)
	 */
	public void endingVisitOfParticularPatient(int patientId, int providerId) {
		logger.info("endingVisitOfParticularPatient method start");
		
		Query query =getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData set flagForVisit =:flagForVisit" +
				" where patientId =:patientId and providerId =:providerId");
		query.setInteger("providerId", providerId);
		query.setInteger("patientId", patientId);
		query.setBoolean("flagForVisit", false);
		 query.executeUpdate();
		
		Query query1 =getSessionFactory().getCurrentSession().createQuery("update PatientMedicationHistory set flagForVisit =:flagForVisit" +
				" where patientId =:patientId and providerId =:providerId");
		query1.setInteger("providerId", providerId);
		query1.setInteger("patientId", patientId);
		query1.setBoolean("flagForVisit", false);
		query1.executeUpdate();
		
	}


	
	public List<DrugDrugInteractionData> filterDrugInteractions(String s) {
		List<DrugDrugInteractionData>drugFilterList=new ArrayList<DrugDrugInteractionData>();
		List<PatientDrugMapping>patientDrugMappings=new ArrayList<PatientDrugMapping>();
		logger.info("inside filterDrugInteractions ");
		
		try{
			int patientId=new ContextUtil().getPatientId();
			Criteria filterCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDrugMapping.class,"patientDrugMapping");
			filterCriteria.createAlias("patientDrugMapping.masterDrugDrugInteractionId", "mastrDrugInteraction");
			filterCriteria.add(Restrictions.eq("patientDrugMapping.patientID", patientId));
			filterCriteria.add(Restrictions.eq("patientDrugMapping.status", true));
			if(s.contains("|")&& StringUtils.split(s,"|") != null){
				logger.info("spilt");
				String []filterValue=StringUtils.split(s,"|");
				logger.info("spilt o value "+filterValue[0]+"1st value "+filterValue[1]);
				filterCriteria.add(Restrictions.disjunction()
						.add(Restrictions.like("mastrDrugInteraction.severityLevels", "%"+filterValue[0]+"%"))
						.add(Restrictions.like("mastrDrugInteraction.severityLevels", "%"+filterValue[1]+"%")));
			}else{
			filterCriteria.add(Restrictions.like("mastrDrugInteraction.severityLevels", "%"+s+"%"));
			}
			//filterCriteria.setProjection(Projections.property("patientDrugMapping.masterDrugDrugInteractionId"));
			logger.info("filterDrugInteractions size "+filterCriteria.list().size());
			patientDrugMappings=filterCriteria.list();
			for(PatientDrugMapping patientDrugMappingObj:patientDrugMappings){
			DrugDrugInteractionData drugDrugInteractionData=new DrugDrugInteractionData();
			drugDrugInteractionData.setStatus(patientDrugMappingObj.isStatus());
			drugDrugInteractionData.setId(patientDrugMappingObj.getId());
			drugDrugInteractionData.setMasterDrugDbId(patientDrugMappingObj.getMasterDrugDrugInteractionId().getId());
			drugDrugInteractionData.setDrug1ID(patientDrugMappingObj.getMasterDrugDrugInteractionId().getDrug1ID());
			drugDrugInteractionData.setDrug2ID(patientDrugMappingObj.getMasterDrugDrugInteractionId().getDrug2ID());
			drugDrugInteractionData.setDrug1(patientDrugMappingObj.getMasterDrugDrugInteractionId().getDrug1Name());
			drugDrugInteractionData.setDrug2(patientDrugMappingObj.getMasterDrugDrugInteractionId().getDrug2Name());
			drugDrugInteractionData.setClinicalEffects(patientDrugMappingObj.getMasterDrugDrugInteractionId().getClinicalEffects());
			if(StringUtils.contains(StringUtils.lowerCase(patientDrugMappingObj.getMasterDrugDrugInteractionId().getSeverityLevels()), HIGH_FILTER)){
				drugDrugInteractionData.setSeverityLevel(HIGH_FILTER);
				}else if(StringUtils.contains(StringUtils.lowerCase(patientDrugMappingObj.getMasterDrugDrugInteractionId().getSeverityLevels()), HIGH_FILTER_CONTRAINDICATED)){
					drugDrugInteractionData.setSeverityLevel(HIGH_FILTER_CONTRAINDICATED);
				}
			else if(StringUtils.contains(StringUtils.lowerCase(patientDrugMappingObj.getMasterDrugDrugInteractionId().getSeverityLevels()), MEDIUM_FILTER)){
					drugDrugInteractionData.setSeverityLevel(MEDIUM_FILTER);
				}else if(StringUtils.contains(StringUtils.lowerCase(patientDrugMappingObj.getMasterDrugDrugInteractionId().getSeverityLevels()), LOW_FILTER)){
					drugDrugInteractionData.setSeverityLevel(LOW_FILTER);
				}else{
					//do nothing
				}
			drugFilterList.add(drugDrugInteractionData);
			}
		}catch(HibernateException he){
			logger.error("error in filtering drug interaction records "+he.getMessage());
		}
		return drugFilterList;
	}
	/*
	 * find medication history of particular patent..
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findFullPatientMedicationHistoryData(int, int)
	 */
	public List<PatientMedicationHistory> findFullPatientMedicationHistoryData(
			int patientId, int providerId) {
		List<PatientMedicationHistory> patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		patientMedicationHistoryList=criteria.list();
		// TODO Auto-generated method stub
		return patientMedicationHistoryList;
	}
	/*
	 * update med action plan of particular meds and particular patient
	 * @author:Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#updatMedActionPlanOfParticularPatient(com.clinakos.data.model.patient.MedActionPlan)
	 */
	public void updatMedActionPlanOfParticularPatient(
			MedActionPlan medActionPlan) {
		logger.info("updatMedActionPlanOfParticularPatient :::::::");
		update(medActionPlan);
		
	}
	/*
	 * udate patient medication data for medaction plan
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#updateMedActionPlan(com.clinakos.data.model.patient.PatientMedicationData)
	 */
	public void updateMedActionPlan(PatientMedicationData patientMedicationData) {
		update(patientMedicationData);
		
	}


	public List<MasterLOINCData> searchLabInLoincMaster(String loincNumber) {
		List<MasterLOINCData>resultSet=new ArrayList<MasterLOINCData>();
		final String STATUS_ACTIVE="ACTIVE";
		try{
			Criteria masterLOINCcriteria=getSessionFactory().getCurrentSession().createCriteria(MasterLOINCData.class,"l");
			masterLOINCcriteria.add(Restrictions.like("l.shortName", loincNumber+"%"));
			masterLOINCcriteria.add(Restrictions.like("l.status", STATUS_ACTIVE));
			masterLOINCcriteria.setProjection(Projections.projectionList()
					.add(Projections.property("l.loincNumber"),"loincNumber")
					.add(Projections.property("l.shortName"),"shortName")
					.add(Projections.property("l.id"),"id")
					.add(Projections.property("l.longCommonName"),"longCommonName")
					)
					.setResultTransformer(Transformers.aliasToBean(MasterLOINCData.class));
			resultSet=masterLOINCcriteria.list();
			int labListSize=resultSet.size();
			if(labListSize==0)
			{
				logger.info("inside if condition");
				Criteria masterLOINCcriteria1=getSessionFactory().getCurrentSession().createCriteria(MasterLOINCData.class,"l");
				masterLOINCcriteria1.add(Restrictions.like("l.longCommonName", loincNumber+"%"));
				masterLOINCcriteria1.add(Restrictions.like("l.status", STATUS_ACTIVE));
				masterLOINCcriteria1.setProjection(Projections.projectionList()
						.add(Projections.property("l.loincNumber"),"loincNumber")
						.add(Projections.property("l.shortName"),"shortName")
						.add(Projections.property("l.id"),"id")
						.add(Projections.property("l.longCommonName"),"longCommonName")
						)
						.setResultTransformer(Transformers.aliasToBean(MasterLOINCData.class));
				resultSet=masterLOINCcriteria1.list();
				
			}
		}catch(HibernateException he){
			logger.error("ERROR IN searchLabInLoincMaster "+he.getMessage());
			he.printStackTrace();
		}finally{
			logger.info("*****search resultset size in searchLabInLoincMaster********"+"for searched query  "
		+loincNumber+"===>"+resultSet.size());
		}
		return resultSet;
	}


	public boolean saveGenericMedActionPlan(int patientId,
			List<GenericMedActionPlan> genericMedActionPlanList, List<GenericMedActionPlan> temporaryGenericMedPlanList,Date dosingStartDate) {
	int doctorId=new ContextUtil().getLoginId();
	String loggedInName=new ContextUtil().getLoggerFirstName()+" "+new ContextUtil().getLoggedUserMiddleName()+" "+new ContextUtil().getLoggerLastName();
	int clinicProviderId=new ContextUtil().getClinicProviderId();
	logger.info("patient id==>"+patientId+" doctor ID ==>"+doctorId+" clinic provider id ==>"+clinicProviderId);
	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
	boolean success=false;
	try{
		if(temporaryGenericMedPlanList.size()>0){
			for(GenericMedActionPlan genericMedActionPlan:temporaryGenericMedPlanList){
				if(genericMedActionPlan.isDatabaseCheck()){
										delete(genericMedActionPlan);
				}
			}
		}
		PatientDiagnosesDetails patientDiagnosesDetails=new PatientDiagnosesDetails();
		if(genericMedActionPlanList.get(0).getPatientDiagnosesDetails()!=null){
		patientDiagnosesDetails=genericMedActionPlanList.get(0).getPatientDiagnosesDetails();
		logger.info("In dao Impl patientDiognosis details object "+patientDiagnosesDetails.getId());
		}
	
		boolean saveOnceDiagnosis=true;
		for(GenericMedActionPlan genericMedActionPlan:genericMedActionPlanList){
		GenericMedActionPlan genericMedActionPlanObj=new GenericMedActionPlan();
		genericMedActionPlanObj=genericMedActionPlan;
		//genericMedActionPlanObj.setPatientDiagnosesDetailsId(patientDiagnosesDetails.getId());
		genericMedActionPlanObj.setPatientId(patientId);
		genericMedActionPlanObj.setDoctorId(doctorId);
		genericMedActionPlanObj.setClinicProviderId(clinicProviderId);
		
		if(genericMedActionPlan.getId()>0){
			if(saveOnceDiagnosis  && StringUtils.isNotBlank(patientDiagnosesDetails.getCode())){
				logger.info("inside if save patient diagnosis details condition while updating med plan");
				patientDiagnosesDetails.setDoctorId(doctorId);
				patientDiagnosesDetails.setDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
				save(patientDiagnosesDetails);
				saveOnceDiagnosis=false;
			}
			genericMedActionPlanObj.setPatientDiagnosesDetailsId(patientDiagnosesDetails.getId());
			genericMedActionPlanObj.setLastUpdatedBy(doctorId);
			genericMedActionPlanObj.setLastUpdatedByName(loggedInName);
			genericMedActionPlanObj.setDiagnosisName(patientDiagnosesDetails.getCode());
			genericMedActionPlanObj.setLastUpdatedDate(new Date());
			String frequency_str=genericMedActionPlanObj.getFrequencyUnit();
			genericMedActionPlanObj.setFrequencyValue(String.valueOf(genericMedActionPlanObj.getFrequencyNo())+" "+frequency_str);
			//System.out.println("find frequency of med action plan:::::"+frequency_str+"::::"+genericMedActionPlanObj.getFrequencyValue()+"::::"+genericMedActionPlanObj.getFrequencyNo());
			genericMedActionPlanObj.setDosingStartDate(dosingStartDate);
			update(genericMedActionPlanObj);
		}else{
			if(saveOnceDiagnosis  && StringUtils.isNotBlank(patientDiagnosesDetails.getCode())){
				logger.info("inside else save patient diagnosis details condition while saving med plan");
				patientDiagnosesDetails.setDoctorId(doctorId);
				patientDiagnosesDetails.setDate(simpleDateFormat.parse(simpleDateFormat.format(new Date())));
				save(patientDiagnosesDetails);
				saveOnceDiagnosis=false;
			}
			genericMedActionPlanObj.setPatientDiagnosesDetailsId(patientDiagnosesDetails.getId());
			genericMedActionPlanObj.setDiagnosisName(patientDiagnosesDetails.getCode());
			genericMedActionPlanObj.setCreatedDate(new Date());
			genericMedActionPlanObj.setCreatedBy(doctorId);
			genericMedActionPlanObj.setCreatedByName(loggedInName);
			genericMedActionPlanObj.setDiagnosisName(patientDiagnosesDetails.getCode());
			genericMedActionPlanObj.setLastUpdatedBy(doctorId);
			genericMedActionPlanObj.setLastUpdatedByName(loggedInName);
			genericMedActionPlanObj.setLastUpdatedDate(new Date());
			String frequency_str=genericMedActionPlanObj.getFrequencyUnit();
			genericMedActionPlanObj.setFrequencyValue(String.valueOf(genericMedActionPlanObj.getFrequencyNo())+" "+frequency_str);
			genericMedActionPlanObj.setDosingStartDate(dosingStartDate);
			save(genericMedActionPlanObj);
			//For testing purpose of Audit record
			/*callToAuditMAP();*/
		}
		if(genericMedActionPlanObj.getId()!=0){
			success=true;
		}else{
			success=false;
		}
		}
	}catch(HibernateException he){
		he.printStackTrace();
	} catch (ParseException e) {
		e.printStackTrace();
	}
		return success;
	}


	private void callToAuditMAP() {
		
		try{
		//prepare Object for Save General Med action Plan in new MAP 
			ParentMedActionPlan parentMedActionPlan=new ParentMedActionPlan();
			parentMedActionPlan.setCreatedBy(2);
			parentMedActionPlan.setCreatedDate(new Date());
			parentMedActionPlan.setUpdatedBy(2);
			parentMedActionPlan.setUpdatedDate(new Date());
			parentMedActionPlan.setId(1);
			parentMedActionPlan.setDosingStartDate(new Date());
			parentMedActionPlan.setDrugId(1212);
			parentMedActionPlan.setDrugNameId(12);
			parentMedActionPlan.setPatientId(72);
			parentMedActionPlan.setSystemMedPlanId(1);
			
			ProcedureType labTypeObj=new ProcedureType();
			labTypeObj.setId(12223);
			labTypeObj.setLabid(3);
			
			
			ChildMedActionPlanParameter parameter=new ChildMedActionPlanParameter();
			parameter.setId(1);
			parameter.setCreatedBy(2);
			parameter.setCreatedDate(new Date());
			parameter.setLabParameterObj(labTypeObj);
			parameter.setUpdatedBy(2);
			parameter.setUpdatedDate(new Date());
			
			parentMedActionPlan.getChildLabParameters().add(parameter);
			save(parentMedActionPlan);
			
			
			
			
		}catch(HibernateException e){
			e.printStackTrace();
		}catch (Exception e) {
		e.printStackTrace();
		}
		
	}


	public PharmacogenomicsRecomendations findAttentionRatingImpactedMedication(
			int patientId, double drugNameId,String drug) {
		logger.info("===============inside findAttentionRatingImpactedMedication in patient medicine DAO==========="+drugNameId);
		PharmacogenomicsRecomendations pRecomendations=new PharmacogenomicsRecomendations();
		try {
			
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getAttentionRatingForParticularMedicine("+patientId+","+drugNameId+",'"+drug+"')}");
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				
				String recomendation=resultSet.getString("recommendation");
				System.out.println("recomendationnnnnnnnnnnnnnnnn"+recomendation);
				String attentionRating=resultSet.getString("attention_rating");
				String ovaleMessage=resultSet.getString("message");
				String implicationsMessage=resultSet.getString("implications");
				String phenotype=resultSet.getString("phenotype1");
				String impactingGene=resultSet.getString("gene1");
				pRecomendations.setAttentionRating(attentionRating);
				pRecomendations.setRecommendation(recomendation);
				pRecomendations.setOvaleMessage(ovaleMessage);
				pRecomendations.setImplications(implicationsMessage);
				pRecomendations.setPhenotype(phenotype);
				pRecomendations.setImpactingGene(impactingGene);
				System.out.println("value in dao hover message::::"+phenotype);
			}	
			
		} 
		catch (HibernateException he) {
			he.printStackTrace();
			logger.error("Hibernate EXCEPTION IN findAttentionRatingImpactedMedication======"+he);
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("EXCEPTION IN findAttentionRatingImpactedMedication======"+e);
		}

		return pRecomendations;
	}

	
	public List<ParentMedActionPlan> getAllGenericMedActionPlan(int patientId) {
		List<ParentMedActionPlan>allGenericMedActionPlans=new ArrayList<ParentMedActionPlan>();
		
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ParentMedActionPlan.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.addOrder(Order.asc("drugName"));
			allGenericMedActionPlans=criteria.list();
			
			/*for (GenericMedActionPlan gmap : allGenericMedActionPlans) {
				gmap.setLabUnit(getLabUnitForGenericMedaction(gmap.getMonitoringParameter()));
			}*/
		
		logger.info("Generic Med action Plans size for patient ID "+patientId+" ===>"+allGenericMedActionPlans.size());
		return allGenericMedActionPlans;
	}


	/*public void rateFromNewCropToClinakos(
			List<PatientMedicationData> patientMedicationDataForIntegrate,
			int patientId, List<PatientMedicationData> patientMedicationDataList) {
		// TODO Auto-generated method stub
		
	}
*/

	
	private String getLabUnitForGenericMedaction(String monitoringParameter) {
		// TODO Auto-generated method stub
		System.out.println("monitoringParameter::::"+monitoringParameter);
		 String labUnit="";
		 String hql="select units FROM ProcedureType where labType= :labType";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("labType", monitoringParameter);
			
			if(!query.list().isEmpty())
				labUnit=(String) query.list().get(0);
			System.out.println("labUnit::1234:::"+labUnit);
			
		return labUnit;
	}

	public void updateGenericMedActionPlan(
			GenericMedActionPlan genericMedActionPlan) {
	//	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		int doctorId=new ContextUtil().getLoginId();
		String loggedInName=new ContextUtil().getLoggerFirstName()+" "+new ContextUtil().getLoggedUserMiddleName()+" "+new ContextUtil().getLoggerLastName();
		System.out.println("setFrequencyNo::::::::"+genericMedActionPlan.getFrequencyNo()+":::::::"+genericMedActionPlan.getFrequencyUnit());
		String frequency = (genericMedActionPlan.getFrequencyNo()+" "+genericMedActionPlan.getFrequencyUnit());
		System.out.println("LLLL:::::::::"+frequency);
	try{
		GenericMedActionPlan genMedActionPlanObj=genericMedActionPlan;
		genMedActionPlanObj.setLastUpdatedDate(new Date());
		/*genMedActionPlanObj.setFrequencyValue(frequency);*/
		genericMedActionPlan.setLastUpdatedBy(doctorId);
		genericMedActionPlan.setLastUpdatedByName(loggedInName);
		update(genMedActionPlanObj);
	}catch(HibernateException he){
		he.printStackTrace();
	}
		
	}
	
/*
 * method find pharmacognomics interaction during add medicine
 * @author:saurabh
 */
	public List<PharmacogenomicsRecomendations> findPharmacogenomicsInteractionDuringAddMedicine(
			double drugNameId, String drugs, int patientId) {
		String impact = "";
		String drugName = "";
		String recommendation="";
		String implications="";
		String attentionRating="";
		String gene1 ="";
		String genericDrugName = "";
		String phenotype = "";
		
		List<PharmacogenomicsRecomendations>pharmacogenomicsInteractionDuringAddMedicineList= new ArrayList<PharmacogenomicsRecomendations>();
		logger.info("inside findPharmacogenomicsInteractionDuringAddMedicine DAO===drugs="+drugs+":drugNameId="+drugNameId);
		try{
		CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getPharmacogenomicsInteractionNotification("+patientId+",'"+drugNameId+"','"+drugs+"')}");
		ResultSet resultSet=statement.executeQuery();
		
		
		
		while (resultSet.next()) {
			drugName=resultSet.getString("in_drug");
			genericDrugName=resultSet.getString("drugs");
			gene1=resultSet.getString("gene1");
			phenotype=resultSet.getString("phenotype");
			impact=resultSet.getString("impact");
			implications=resultSet.getString("implications");
			recommendation=resultSet.getString("recommendation");
			attentionRating=resultSet.getString("attention_rating");
			PharmacogenomicsRecomendations phr = new PharmacogenomicsRecomendations();
			phr.setDrugName(drugName);
			phr.setGenericDrugName(genericDrugName);
			phr.setGene1(gene1);
			phr.setPhenotype(phenotype);
			phr.setImpact(impact);
			phr.setImplications(implications);
			phr.setRecommendation(recommendation);
			phr.setAttentionRating(attentionRating);
			pharmacogenomicsInteractionDuringAddMedicineList.add(phr);
		 }
		
	    }
		catch(SQLException sql){
			sql.printStackTrace();
			logger.info("SQL exception in findPharmacogenomicsInteractionDuringAddMedicine======="+sql);
		}
		catch(HibernateException he){
			he.printStackTrace();
			logger.info("HibernateException in findPharmacogenomicsInteractionDuringAddMedicine======="+he);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("Exception in findPharmacogenomicsInteractionDuringAddMedicine======="+e);
		}
		return pharmacogenomicsInteractionDuringAddMedicineList;


     }

	
	public void deleteGenericMedActionPlan(
			GenericMedActionPlan genericMedActionPlan) {
	try{
		//delete(genericMedActionPlan.getPatientDiagnosesDetails());
	/*	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedActionPlan.class);
		criteria.add(Restrictions.eq("patientId", genericMedActionPlan.getPatientId()));
		criteria.add(Restrictions.eq("drugId", genericMedActionPlan.getDrugId()));
		List<GenericMedActionPlan>genericMedActionPlansList=criteria.list();
		for(GenericMedActionPlan genericMedActionPlanObj:genericMedActionPlansList){*/
		
		Query query =getSessionFactory().getCurrentSession().createQuery("delete GenericMedActionPlan where id=:id and drugId =:drugId and patientId =:patientId");
		query.setString("drugId", genericMedActionPlan.getDrugId());
		query.setInteger("id", genericMedActionPlan.getId());
		query.setInteger("patientId", genericMedActionPlan.getPatientId());
		int result = query.executeUpdate();
		
	   Query updateQuery = getSessionFactory().getCurrentSession().createQuery("update AlertGenericMedActionLab set status=:lateststatus  where drugId=:drugId and patientId=:patientId");
		updateQuery.setBoolean("lateststatus", false);
		updateQuery.setString("drugId",genericMedActionPlan.getDrugId());
		updateQuery.setInteger("patientId", genericMedActionPlan.getPatientId());
		int updateQueryResult = updateQuery.executeUpdate();
		logger.info("result:::::::::::::::::>>>>>>>><<<<<"+updateQueryResult);
	//	delete(genericMedActionPlan);
	//	}
	}catch(HibernateException he){
		logger.info("ERROR:In deleting generic med action plan with database ID "+genericMedActionPlan.getId());
		he.printStackTrace();
	}
	}


	public List<GenericMedActionPlan> allMedPlansForPatient(
			PatientMedicationData patientMedicationData, int patientID) {
		List<GenericMedActionPlan>genericMedActionPlanList=new ArrayList<GenericMedActionPlan>();
		int drugIds=(int)patientMedicationData.getDrugId();
		try{
			logger.info("converting drug id from double to int "+drugIds);
			logger.info("converting drug id from int to String "+String.valueOf(drugIds));
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedActionPlan.class);
			criteria.add(Restrictions.eq("drugId", String.valueOf(drugIds)));
			criteria.add(Restrictions.eq("patientId", patientID));
			genericMedActionPlanList=criteria.list();
			
			for (GenericMedActionPlan gmap : genericMedActionPlanList) {
				gmap.setLabUnit(getLabUnitForGenericMedactionOverview(gmap.getMonitoringParameter()));
			}
			/*for (GenericMedActionPlan gmp : generic
			 * MedActionPlanList) {
				gmp.setLabUnit(getLabUnitForGenericMedactionPlan(gmp.getMonitoringParameter()));
			}*/
		}catch(HibernateException he){
			he.printStackTrace();
		}
		logger.info("Generic Med Aaction Plan size for patientID "+patientID+" ==>Drug ID "+drugIds+
				" size==>"+genericMedActionPlanList.size());
		return genericMedActionPlanList;
	}

	private String getLabUnitForGenericMedactionOverview(String monitoringParameter) {
		// TODO Auto-generated method stub
		
		 String labUnit="";
		 String hql="select units FROM ProcedureType where labType= :labType";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("labType", monitoringParameter);
			if(!query.list().isEmpty())
			labUnit=(String) query.list().get(0);
			System.out.println("labUnit:For:labchart:overview::"+labUnit);
			
		return labUnit;
	}

	/*public String getLabUnitForGenericMedactionPlan(String monitoringParameter) {
		 String labUnit=null;
		 String hql="select units FROM ProcedureType where procedureType= :procedureType";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("procedureType", monitoringParameter);
			labUnit=(String) query.uniqueResult();
			System.out.println("labUnit:::::"+labUnit);
		 return labUnit;
	}*/
	
	/**
	 * Data of Alert Generic Med Action Plan Data based on Patient Id
	 *  modeified sqlQuery as per New MAP:08/dec/2014 
	 * @param patientID
	 * @return List of GenericMedActionPlan
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#alertMedActionPlans(int)
	 *
	 */
	public List<AlertGenericMedActionLab> alertMedActionPlans(int patientID) {
		List<AlertGenericMedActionLab>alertMedActionPlans=new ArrayList<AlertGenericMedActionLab>();
		List<AlertGenericMedActionLab>alertMedActionPlanList=new ArrayList<AlertGenericMedActionLab>();
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
		final String greaterThan=">";
		final String greaterThanOrEqual=">=";
		final String lessThan="<";
		final String lessThanEqual="<=";
		final String equalSign="=";
		
	try{

	/*	String sqlQuery= "select * from alert_med_action_lab as ml where ml.patient_id=:patientIdForAlerts " +
				" and isActive=:activeAttr and ml.lab_type in (select gmp.parameter_short_name "
				+" from generic_med_action_plan as gmp where gmp.patient_id=:patientId)";*/
		String sqlQuery="select * from alert_med_action_lab as ml where ml.patient_id=:patientIdForAlerts "+
" and isActive=:activeAttr and ml.lab_type in (select pt.lab_type "
+ " from general_med_action_plan as gmp inner join  general_med_action_plan_parameters as cp on gmp.med_Action_PlanId = cp.medActionPlanId "+
" inner join  procedure_type pt on pt.procedure_type_id=cp.procedure_type_id "+
" where gmp.patientId=:patientId)";
		Query listQuery= getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		((SQLQuery) listQuery).addEntity(AlertGenericMedActionLab.class);
		listQuery.setParameter("patientId", patientID);
		listQuery.setParameter("activeAttr", 1);
		listQuery.setParameter("patientIdForAlerts", patientID);
		List dbAlertsList=listQuery.list();
		
		  Iterator it=dbAlertsList.iterator();
		  while(it.hasNext()){
			 
			  AlertGenericMedActionLab  alertGenericMedActionLab =(AlertGenericMedActionLab) it.next();
	
			 AlertGenericMedActionLab alertActionLab=new AlertGenericMedActionLab();
				alertActionLab=alertGenericMedActionLab;
				alertActionLab.setLabDateResult(sdf.format(alertActionLab.getLabDate()));
				boolean checkSevereRange=false;
				if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereLowRangeValue()) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereLowRangeValue()) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
					
				}else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereLowRangeValue()) || 
				(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereLowRangeValue()) || 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
					
				}
				else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThan)) ) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThan))) &&(alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereLowRangeValue()||alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThan))  && 
						alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThan)) &&( alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereLowRangeValue()|| alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
					
				}
				
				else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) ) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThan))) && (alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereLowRangeValue()||alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign))) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThan)) )&&(alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereLowRangeValue()||alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
					
				}
				
				else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThan)) ) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign))) &&(alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereLowRangeValue()|| alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}else if((alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThan)) ) && 
						(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign))) &&(alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereLowRangeValue()|| alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereHighRangeValue())){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
					
				}
				
				/*else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThan)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereLowRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThan)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereHighRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}*/
				
				else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereHighRangeValue()==0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereLowRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereHighRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereLowRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);	
				}
				
				else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereHighRangeValue()==0.0 && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(greaterThan)) && alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereLowRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertSevereHighRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereLowRangeSymbol().equals(lessThan)) && alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereLowRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);	
				}
				
				
				else if(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0&& alertGenericMedActionLab.getAlertSevereLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertSevereHighRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0&& alertGenericMedActionLab.getAlertSevereLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertSevereHighRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0&& alertGenericMedActionLab.getAlertSevereLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(greaterThan)) && alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertSevereHighRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertSevereHighRangeValue()>0.0&& alertGenericMedActionLab.getAlertSevereLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertSevereHighRangeSymbol().equals(lessThan)) && alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertSevereHighRangeValue()){
					alertActionLab.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				}
				
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThan)) &&  
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThan))) && (alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumLowRangeValue() || alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumHighRangeValue()))){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0) && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThan))  && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0) && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThan)) &&(alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumLowRangeValue() || alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumLowRangeValue()) && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumLowRangeValue()) && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				//
				/*else if(alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 &&  (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThan)) && alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumLowRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}else if(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThan)) && alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumHighRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}*/
				//
				   
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) ) && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThan))) && (alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumLowRangeValue()||alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) ) && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThan))) && (alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumLowRangeValue()||alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThan)))  && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign))) &&(alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumLowRangeValue()||alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if((alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThan)))  && 
						(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign))) && (alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumLowRangeValue()||alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumHighRangeValue())){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				
				
				
				else if(alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumHighRangeValue()==0.0  && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumLowRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumHighRangeValue()==0.0 &&  (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumLowRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumHighRangeValue()==0.0  && (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(greaterThan)) && alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumLowRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumLowRangeValue()>0.0 && alertGenericMedActionLab.getAlertMediumHighRangeValue()==0.0 &&  (alertGenericMedActionLab.getAlertMediumLowRangeSymbol().equals(lessThan)) && alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumLowRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 &&alertGenericMedActionLab.getAlertMediumLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThan)) && alertGenericMedActionLab.getLabResultValue()>alertGenericMedActionLab.getAlertMediumHighRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 &&alertGenericMedActionLab.getAlertMediumLowRangeValue()==0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThan)) && alertGenericMedActionLab.getLabResultValue()<alertGenericMedActionLab.getAlertMediumHighRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 &&alertGenericMedActionLab.getAlertMediumLowRangeValue()==0.0  && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(greaterThanOrEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()>=alertGenericMedActionLab.getAlertMediumHighRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else if(alertGenericMedActionLab.getAlertMediumHighRangeValue()>0.0 &&alertGenericMedActionLab.getAlertMediumLowRangeValue()==0.0 && (alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(lessThanEqual)||alertGenericMedActionLab.getAlertMediumHighRangeSymbol().equals(equalSign)) && alertGenericMedActionLab.getLabResultValue()<=alertGenericMedActionLab.getAlertMediumHighRangeValue()){
					alertActionLab.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				}
				else{
					alertActionLab.setSeverityLevel(LOW_SEVERITY_LEVEL);
				}
				
				
				alertMedActionPlanList.add(alertActionLab);
			  
		  }
	
	}catch(Exception he){
		he.printStackTrace();
	}
	logger.info("alertMedActionPlanList  "+alertMedActionPlanList.size());
		return alertMedActionPlanList;
	}

	
	public void saveMessageDetials(SendMessageEditRx sendMessageEditRx) {
		logger.debug("method SendMessageEditRxs starts in daoImpl::::::");
		getSessionFactory().getCurrentSession().save(sendMessageEditRx);
	}
   
	/*
	 *@author:vinod
	 * For Fetch EditRx Message on Medmgmt page 
	 */
	public List<SendMessageEditRx> getsendMessageEditRxList(int patientId) {
		List<SendMessageEditRx>  sendMessageEditRxs = new ArrayList<SendMessageEditRx>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(SendMessageEditRx.class);
		criteria.add(Restrictions.eq("patientId",patientId));
		criteria.add(Restrictions.eq("loginDoctorId", new ContextUtil().getLoginId()));
		System.out.println("in editRx =="+new DateUtil().getTodayDate());
		sendMessageEditRxs = criteria.list();
		logger.info("in Rx List size:::"+sendMessageEditRxs.size());
		return sendMessageEditRxs;
	}


	public List<MasterMonitorParameters> getAllSubParameters(
			String monitoringParameter) {
		List<MasterMonitorParameters>parameterList=new ArrayList<MasterMonitorParameters>();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MasterMonitorParameters.class);
			criteria.add(Restrictions.eq("parentParameter", monitoringParameter));
			criteria.add(Restrictions.eq("status", true));
			parameterList=criteria.list();			
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return parameterList;
	}

	
	public List<MedandGenricmed> getAllClinicMedPlans(int patientId) {
	List<MedandGenricmed>allClinicsMedPlans=new ArrayList<MedandGenricmed>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		allClinicsMedPlans=criteria.list();
	}catch(HibernateException he){
		he.printStackTrace();
	}
		return allClinicsMedPlans;
	}
	
	
	/*create this Medthod For fetching the list for HighChart
	 * 
	 * @Author sanket Singh
	*/
	public List<MedandGenricmed> fetchGenericList(int patientId) {
		List<MedandGenricmed>medActionListForChart=new ArrayList<MedandGenricmed>();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			medActionListForChart=criteria.list();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		logger.info("list size==="+medActionListForChart.size());
			return medActionListForChart;
		}

	
	public List<String> findLabTypeList() {
		logger.info("inside findlabType in dao impl");
		List<String>labTypeList=new ArrayList<String>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
		
		criteria.setProjection(Projections.projectionList().
		         add(Projections.property("labType") ));
                 
		labTypeList=criteria.list();
		System.out.println(":::::::labList::"+labTypeList.size());
		return labTypeList;
	}

	
	public ProcedureType getLabUnitBasedOnParameter(String childParameter) {
		ProcedureType procedureType=new ProcedureType();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class,"pt");
			criteria.add(Restrictions.eq("pt.labType", childParameter));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("pt.labType"),"labType")
					.add(Projections.property("pt.units"),"units")
					)
					.setResultTransformer(Transformers.aliasToBean(ProcedureType.class));
			procedureType=(ProcedureType) criteria.list().get(0);
		}catch(IndexOutOfBoundsException ie){
			ie.printStackTrace();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		return procedureType;
	}

	
	public WSDrug getWSdrugObj(String dataProvider, double drugId,double drugNameIdVal) {
		WSDrug wsDrug=new WSDrug();
		List<WSDrug>wsDrugs=new ArrayList<WSDrug>();
	//	String drugID=String.valueOf((int)drugId);
		
		try{
			/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
			criteria.add(Restrictions.eq("drugId", drugId));
		//	criteria.add(Restrictions.eq("dataProvider", "F"));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("therapeuticCategory"))
					)
					.setResultTransformer(Transformers.aliasToBean(WSDrug.class));
			logger.info("criteria size "+criteria.list().size());
			//wsDrug=(WSDrug) criteria.list().get(0);
*/		
			if(drugId>0){
				String hql="from WSDrug where drugId =:drugID and dataProvider=:dataProviderVal";
				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				query.setParameter("drugID", drugId);
				query.setParameter("dataProviderVal", "F");
				wsDrugs=query.list();
				if(wsDrugs.size()>0){
				wsDrug=(WSDrug) wsDrugs.get(0);
				}
			}else{
				//for the drugs without drugId but It will have drugNameId
					    String hql="from WSDrug where drugNameId =:drugNameID and dataProvider=:dataProviderVal";
						Query query=getSessionFactory().getCurrentSession().createQuery(hql);
						query.setParameter("drugNameID", drugNameIdVal);
						query.setParameter("dataProviderVal", "F");
						wsDrugs=query.list();
						if(wsDrugs.size()>0){
							wsDrug=(WSDrug) wsDrugs.get(0);
							}
				
			}
				logger.info("List size "+wsDrugs.size());
				
		}catch(IndexOutOfBoundsException ie){
			ie.printStackTrace();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		return wsDrug;
	}

	
	
	/*Method for fetch the value of Current date EditRx value
	 * @Author Sanket Singh
	 * 
	 * */
	public List<SendMessageEditRx> getSendMessageCurrentEditRxList(int patientId,Date todayDate)throws ParserException {
		logger.info("in dao :::"+patientId+":::todayDate::"+todayDate);
		List<SendMessageEditRx>  sendMessageEditListDisplay = new ArrayList<SendMessageEditRx>();
		List<SendMessageEditRx>  sendMessageEditListDisplayforcurrentvisit = new ArrayList<SendMessageEditRx>();
		List<SendMessageEditRx>  sendMessageEditListDisplayforcurrentvisitfordisplay = new ArrayList<SendMessageEditRx>();
		try{
			
			Date stardate;
	    	 stardate=new DateUtil().AddDate(todayDate, 0);
	
	  /*SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar calendar = Calendar.getInstance();
	calendar.set(Calendar.HOUR_OF_DAY, 0);
	calendar.set(Calendar.MINUTE, 0);
	calendar.set(Calendar.SECOND, 0);
	Date fromDate = calendar.getTime();
	logger.info("fromDate in "+fromDate);

	Date date4 = formatter1.parse(formatter1.format(fromDate));
	
	calendar.set(Calendar.HOUR_OF_DAY, 23);
	calendar.set(Calendar.MINUTE, 59);
	calendar.set(Calendar.SECOND, 59);
	Date toDate = calendar.getTime();

        
        logger.info("After cal set >>>>> todate:::"+date4 +"fromDate->>>>>>>>>>"+fromDate+"dateInString:::"+toDate);*/
	 
     
		//logger.info("date in editRx ::"+formateddate);
		Criteria criterialist=getSessionFactory().getCurrentSession().createCriteria(SendMessageEditRx.class);
		criterialist.add(Restrictions.eq("patientId",patientId));
		criterialist.add(Restrictions.eq("loginDoctorId", new ContextUtil().getLoginId()));
		
	/* criterialist.add(Restrictions.between("messageSentDate",date4,toDate));*/
		sendMessageEditListDisplayforcurrentvisit=criterialist.list();
		
		
		for(SendMessageEditRx sendmessagevisit:sendMessageEditListDisplayforcurrentvisit)
	     {
	    	 System.out.println(stardate+"action_date::::"+sendmessagevisit.getMessageSentDate());
	    	
	    	// stardate=sdf.p
	    	 if(sendmessagevisit.getMessageSentDate().equals(stardate))
	    	 {

		    	
	    		 System.out.println("date in discontinue medicine::::"+sendmessagevisit.getMessageSentDate()+"start date::"+stardate);
	    		 sendMessageEditListDisplayforcurrentvisitfordisplay.add(sendmessagevisit);
	    		 sendMessageEditListDisplay=sendMessageEditListDisplayforcurrentvisitfordisplay;
	    	 }
		
	 	System.out.println("list in display::::"+sendMessageEditListDisplay.size());

	     }
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("list in display1::::"+sendMessageEditListDisplay.size());
		return sendMessageEditListDisplay;
		}




		
	/* Method for discontinue medcine at currentdate at Visit page
	 * @Author Sanket Singh
	 * 
	 * */
	public List<PatientMedicationHistory> findDiscontinueMedicineListOfParticularVisit(
			int patientId, int providerId) {
		Date modifiedDate=null;
		List<PatientMedicationHistory>patientMedicationHistoryList=new ArrayList<PatientMedicationHistory>();
		List<PatientMedicationData>patientMedicationDiscontinueList=new ArrayList<PatientMedicationData>();
		List<PatientMedicationHistory>patientMedicationDiscontinueListForCurrentDate=new ArrayList<PatientMedicationHistory>();
		try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		/*criteria.add(Restrictions.eq("flagForVisit", true));*/
		patientMedicationHistoryList=criteria.list();
		
		Date stardate;
   	 stardate=new DateUtil().AddDate(new Date(), -1);
		
		
		for(PatientMedicationHistory pathistory:patientMedicationHistoryList)
	     {
	    	 System.out.println(stardate+"action_date::::"+pathistory.getActionDate());
	    	
	    	// stardate=sdf.p
	    	 if(pathistory.getActionDate().after(stardate))
	    	 {

		    	
	    		 System.out.println("date in discontinue medicine::::"+pathistory.getActionDate()+"start date::"+stardate);
	    		 patientMedicationDiscontinueListForCurrentDate.add(pathistory);
	    	 }
		
		
		logger.info("disContinueMedicationListForVisit::::"+patientMedicationHistoryList.size());
		
	
	
		}
		
		/*for(PatientMedicationData pdata:patientMedicationDiscontinueList)
		{
			modifiedDate=pdata.getModifyDate();
			
		}
		
		
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date fromDate = calendar.getTime();
			logger.info("fromDate in "+fromDate);

			Date date4 = formatter1.parse(formatter1.format(fromDate));
			logger.info("date4  in "+date4 );
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date toDate = calendar.getTime();
		
		logger.info("modifiedDate date:::"+modifiedDate);
		Criteria criteriaPatient=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
		criteriaPatient.add(Restrictions.eq("patientId", patientId));
		criteriaPatient.add(Restrictions.eq("providerId", providerId));
		criteriaPatient.add(Restrictions.between("actionDate",date4,toDate));
		patientMedicationDiscontinueListForCurrentDate=criteriaPatient.list();*/
		logger.info("patientMedicationDiscontinueListForCurrentDate==="+patientMedicationDiscontinueListForCurrentDate.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		logger.info("patientMedicationDiscontinueListForCurrentDate==="+patientMedicationDiscontinueListForCurrentDate.size());
		return patientMedicationDiscontinueListForCurrentDate;
	}


/*Method for Fetch the value on New ActionPlan at VisitPage
 * @Author Sanket Singh
 * 
 * */
	   public List<PatientMedicationData> findPatientMedicationDataListForPatient(
			int patientId) {
		
		
		logger.info("findPatientMedicationDataListForPatient Dao Method"+patientId);
		List<PatientMedicationData>patientMedicationAddOrChangeList=new ArrayList<PatientMedicationData>();
		try{
	
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date fromDate = calendar.getTime();
			logger.info("fromDate in "+fromDate);
		
			Date startDate = formatter1.parse(formatter1.format(fromDate));
			logger.info("startDate in add change med"+startDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date toDate = calendar.getTime();
		
			Date startDate1=new Date();
			//startDate1
			Date endDate=new DateUtil().AddDate(startDate1,1);
			logger.info(startDate1+"toDate in add change med"+endDate);
			
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		//criteria.add(Restrictions.between("startDate", startDate,toDate));
		criteria.add(Restrictions.le("startDate", endDate));
		criteria.add(Restrictions.ge("startDate", startDate1));
		
		//criteria.add(Restrictions.like("startDate", startDate));
		
		
		patientMedicationAddOrChangeList=criteria.list();
		logger.info("patientMedicationAddOrChangeList size:::"+patientMedicationAddOrChangeList.size());
		
		}catch(ParseException ps)
		{
			ps.printStackTrace();
		}
		
		return patientMedicationAddOrChangeList;
	}


/*method for fetch the value in MedicationGoal at summary page
 * 
 * @Author sanket singh
 * */
	public List<MedandGenricmed> findgenricandnongenricMedActionPlanPatient(
			int patientId, int providerId)  {
		List<MedandGenricmed>medActionPlanListforPatient=new ArrayList<MedandGenricmed>();
		List<MedandGenricmed>medActionPlanListforPatientCurrentMed=new ArrayList<MedandGenricmed>();
		try{
			logger.info("=========inside findgenricandnongenricMedActionPlanPatient method in patientMedicineDAO========");
		    String todayDate=new DateUtil().DateInMySqlPattern(new Date());
		    Date stardate=new DateUtil().AddDate(new Date(), -1);
		    String hql="FROM MedandGenricmed where patientId= :patientId";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("patientId", patientId);
			//query.setParameter("createdate", new DateUtil().getTodayDate());
			medActionPlanListforPatient=query.list();
			
			for(MedandGenricmed medgenericmed:medActionPlanListforPatient)
			{
				MedandGenricmed medgen=new MedandGenricmed();
				medgen.setCreatedate(medgenericmed.getCreatedate());
				medgen.setMedicinename(medgenericmed.getMedicinename());
				medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
				if(StringUtils.isNotBlank(medgenericmed.getLastupdateby()) ){
					if(StringUtils.isAlphaSpace(medgenericmed.getLastupdateby())){
					medgen.setLastupdateby(medgenericmed.getLastupdateby());
				}else{
					medgen.setLastupdateby(getUserNameByUserId(Integer.valueOf(medgenericmed.getLastupdateby())));
				}
				}
				
				medgen.setLabfrequency(medgenericmed.getLabfrequency());
				medgen.setLab(medgenericmed.getLab());
				medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
				medgen.setLastupdatedate(medgenericmed.getLastupdatedate());
				Date date = new Date();
				System.out.println("::::::stardate:::::"+stardate);
				System.out.println(":::::::setLastupdatedate:::::"+medgen.getLastupdatedate()+"::::::::::"+date);
				if((medgen.getCreatedate().after(stardate))||(medgen.getLastupdatedate().after(stardate)))
		    	 {
					 System.out.println("date for med action plan"+medgen.getCreatedate());
					 medActionPlanListforPatientCurrentMed.add(medgen);
		    	 }
			}
			logger.info("medActionPlanListforPatientCurrentMed size in DAO========"+medActionPlanListforPatientCurrentMed.size());
			} 
		catch (Exception e) {
				e.printStackTrace();
			}
		return medActionPlanListforPatientCurrentMed;
	}


	
	public List<PatientMedicationData> findMedicineForPatient(int patientId)  {
		logger.info("in daoImpl medtho::::");
		List<PatientMedicationData>patientMedicationForUpadateList=new ArrayList<PatientMedicationData>();
	
		try{
		
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date fromDate = calendar.getTime();
			logger.info("fromDate in "+fromDate);
			
			Date startDate = formatter.parse(formatter.format(fromDate));
			
			calendar.set(Calendar.HOUR_OF_DAY,23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date mainDate = calendar.getTime();
		
		logger.info("startDate::::"+startDate);
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		/*criteria.add(Restrictions.between("startDate", startDate,mainDate));*/
	
		patientMedicationForUpadateList=criteria.list();
		logger.info("patientMedicationForUpadateList in daoImpl"+patientMedicationForUpadateList.size());
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return patientMedicationForUpadateList;
	}


	/*
	 * 
	 * Save The Encounter Summary Data and encounter Detail 
	 * 
	 * 
	 * */
	public void saveMedicineDetailforEducation(
			PatientMedicineNotes patientMedicineNotes,EncounterSummary encounterSummary) {
		
			logger.info("saveMedicineDetail in patientMedicineNotes dao impl");
			
				Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EncounterSummary.class);
				criteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
				criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
				criteria.setProjection(Projections.max("encounterNo"));
				int encountrNo=0;
				try {
					 encountrNo=  (Integer) criteria.uniqueResult();
				} catch (NullPointerException ne) {
					ne.printStackTrace();
				}
				
				System.out.println("encounterNo:::::::"+encountrNo);
				if(encountrNo==0 || !(StringUtils.isNotBlank(Integer.toString(encountrNo)))){
					encounterSummary.setEncounterNo(1);
				}
				else if (encountrNo!=0  ) {
					 int encounterNo=encountrNo+1;
					encounterSummary.setEncounterNo(encounterNo);
				}
				else {
					encounterSummary.setEncounterNo(1);
				}
				try {
					getSessionFactory().getCurrentSession().save(encounterSummary);	
				} catch (HibernateException he) {
					he.printStackTrace();
				}
				
			
		
		
		
		
	
		
	}

	/*
	 * find pharmacy detail according to drug id particular patient
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findPharmacyDetailHistoryListAccordingToParticularDrugId(double, int)
	 */
	public List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugId, int patientId) {
		List<PharmacyDetail>pharmacyDetailList=new ArrayList<PharmacyDetail>();
		logger.info("findPharmacyDetailHistoryListAccordingToParticularDrugId in dao::"+drugId);
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("drugId",drugId));
		criteria.addOrder(Order.desc("dateOfPurchase"));
		pharmacyDetailList=criteria.list();
		return pharmacyDetailList;
	}


	/**
	 * Get the Value of Pharmacy Detail 
	 */
	public List<PharmacyDetail> getPharmacyDetailData(int timePeriod,
			int patientId) {
		logger.info("getPharmacyDetailData method start....");
		Calendar calendar=Calendar.getInstance();
		Date todayDate=calendar.getTime();
		calendar.add(Calendar.MONTH,-timePeriod);
		
		Date lowerDate=calendar.getTime();
		System.out.println("compareDate"+lowerDate);
		List<PharmacyDetail> pharmacyDetailList=new ArrayList<PharmacyDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.ne("drugNameId", new Double(0)));
		criteria.add(Restrictions.between("dateOfPurchase", lowerDate, todayDate));
		
		pharmacyDetailList=criteria.list();
		System.out.println("size:::::"+pharmacyDetailList.size());
				
		return pharmacyDetailList;
	}


 /**
  * It will check drug is speciality or not 
  *  @see IPatientMedicineDAO#checkSpecialtyDrug(String)	
  *  @return drug is speciality or not 
  */
	public boolean checkSpecialtyDrug(String drugs) {
		boolean specialtyDrugFound=false;
		try{
			String sqlQuery="Select LCASE(PROPRIETARYNAME) from master_speciality_drug_info where LCASE(PROPRIETARYNAME) like :drugName";
			Query listQuery= getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
			listQuery.setParameter("drugName","%"+drugs.toLowerCase()+"%");
			List<String>resultSet=listQuery.list();
			if(resultSet.size()>0){
				specialtyDrugFound=true;
			}else{
				specialtyDrugFound=false;
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		return specialtyDrugFound;
	}


   /**
    * It is used for get the Drug name which has interaction with warfrain 
    * @param patientMedicationDataList
    * @return List of  DrugInteractionForWarfarin
    */
	public List<DrugInteractionForWarfarin> findDrugInteractionForWarfarinList(
			List<PatientMedicationData> patientMedicationDataList) {
		logger.info("findDrugInteractionForWarfarinList method:::");
		List<DrugInteractionForWarfarin>drugInteractionForWarfarinList=new ArrayList<DrugInteractionForWarfarin>();
		List<String>medicineList=new ArrayList<String>();
		for(PatientMedicationData pat:patientMedicationDataList)
		{
			medicineList.add(pat.getDrugs());
			System.out.println("pat.getDrugs()::::"+pat.getDrugs());
		}
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(DrugInteractionForWarfarin.class);
		criteria.add(Restrictions.in("drugName", medicineList));
		drugInteractionForWarfarinList=criteria.list();
		for(DrugInteractionForWarfarin drug:drugInteractionForWarfarinList)
		{
			drug.setInteraction(drug.getInteraction().trim());
		}
		
		System.out.println("size::::"+drugInteractionForWarfarinList.size());
		return drugInteractionForWarfarinList;
	}

	@Override
	public String getPatientGender(int patientId) {
		UserLoginDetail userLoginDetail=new UserLoginDetail();
		try{
			userLoginDetail= (UserLoginDetail) getSessionFactory().getCurrentSession().get(UserLoginDetail.class, patientId);
			/*userLoginDetailCriteria.setProjection(Projections.projectionList()
					.add(Projections.property("gender"),"gender")		
					);
			userLoginDetailCriteria.setResultTransformer(Transformers.aliasToBean(ACOPatientMeasure.class));
			userLoginDetail=(UserLoginDetail) userLoginDetailCriteria.uniqueResult();*/
		}catch(NullPointerException nfe){
			nfe.printStackTrace();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		return userLoginDetail.getGender();
	}



/*
 * *******************method for calculating compliance for DEMO DB
 * @see com.clinakos.data.dao.IPatientMedicineDAO#fetchAllRecords()
 * **********@author: saurabh
 */
	public List<PatientMedicationData> fetchAllPatientMedicationDataRecords() {
		List<PatientMedicationData> pmdList=new ArrayList<PatientMedicationData>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			pmdList=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=============EXCEPTION IN METHOD fetchAllPatientMedicationDataRecords INSIDE PATIENTMEDICINEDAO===========", e);
		}
		return pmdList;
	}

/*
 * **********************updating compliance percentage in pmd for particular rowID
 * @see com.clinakos.data.dao.IPatientMedicineDAO#updateCompliancePercentageForDemo(int, int)
 * @uthor: saurabh******for DEMO
 
	public void updateCompliancePercentageForDemo(int compliancePercentage,
			int id) {
		try {
			logger.info("==========updateCompliancePercentageForDemo called in patientMedicineDAO===============%======="+compliancePercentage+"====rowID==="+id);
			Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData set compliancePercentage= :compliancePercentage where id= :id");
				hqlQuery.setParameter("compliancePercentage", compliancePercentage);
				hqlQuery.setParameter("id", id);
				int updateId = hqlQuery.executeUpdate();
			logger.info("===================UPDATE OVER FOR PMD============================");	
		} catch (Exception e) {
            logger.error("=======EXCEPTION IN updateCompliancePercentageForDemo IN PATIENT MEDICINE DAO=========", e);
		}
	}		*/



/*
 * *******************method for calculating compliance for DEMO DB
 * @see com.clinakos.data.dao.IPatientMedicineDAO#fetchAllRecords()
 * **********@author: saurabh
 */
	/*public List<PatientMedicationData> fetchAllPatientMedicationDataRecords() {
		List<PatientMedicationData> pmdList=new ArrayList<PatientMedicationData>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			pmdList=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("=============EXCEPTION IN METHOD fetchAllPatientMedicationDataRecords INSIDE PATIENTMEDICINEDAO===========", e);
		}
		return pmdList;
	}
*/
/*
 * **********************updating compliance percentage in pmd for particular rowID
 * @see com.clinakos.data.dao.IPatientMedicineDAO#updateCompliancePercentageForDemo(int, int)
 * @uthor: saurabh******for DEMO
 */
	public void updateCompliancePercentageForDemo(int compliancePercentage,
			int id) {
		try {
			logger.info("==========updateCompliancePercentageForDemo called in patientMedicineDAO===============%======="+compliancePercentage+"====rowID==="+id);
			Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("update PatientMedicationData set compliancePercentage= :compliancePercentage where id= :id");
				hqlQuery.setParameter("compliancePercentage", compliancePercentage);
				hqlQuery.setParameter("id", id);
				int updateId = hqlQuery.executeUpdate();
			logger.info("===================UPDATE OVER FOR PMD============================");	
		} catch (Exception e) {
            logger.error("=======EXCEPTION IN updateCompliancePercentageForDemo IN PATIENT MEDICINE DAO=========", e);
		}
	}


/**
 * Get PsychoPharm Clinic Med Action Plan Data 
 * modified for NEW G-MAP:08/dec/2014
 */
	public List<GenericMedActionPlan> getPsychoPharmClinicMedActionPlanData(
			int patientId) {
		List<GenericMedActionPlan> psychoPharamClinicMedActionPlanList=new ArrayList<GenericMedActionPlan>();
		try {
			/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedActionPlan.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.add(Restrictions.like("monitoringParameter","Lithium",MatchMode.START ));
			criteria.addOrder(Order.asc("drugName"));
			psychoPharamClinicMedActionPlanList=criteria.list();*/
			
			String clinicName="Psychopharm";
			String sqlQuery="select gmp.* "
					+ "from generic_med_action_plan gmp inner join  " 
					+"medicine_details  md on md.medicine_name=gmp.drug_name "
					+"where md.clinic_name='"+clinicName+"'  and gmp.patient_id="+patientId+"";
			SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
			sqlQueryCriteria.addEntity(GenericMedActionPlan.class);
	         
			psychoPharamClinicMedActionPlanList =sqlQueryCriteria.list();
			DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			
		   System.out.println("psychoPharamClinicMedActionPlanList.size()  "+psychoPharamClinicMedActionPlanList.size());
		   
		   
		   /*
			 * List<ParentMedActionPlan> psychoPharamClinicMedActionPlanList=new ArrayList<ParentMedActionPlan>();
			String clinicName="Psychopharm";
			String sqlQuery="select gmp.* "
					+ "from general_med_action_plan gmp inner join  " 
					+"medicine_details  md on md.medicine_name=gmp.drugName "
					+"where md.clinic_name='"+clinicName+"'  and gmp.patientId="+patientId+"";
			SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
			sqlQueryCriteria.addEntity(ParentMedActionPlan.class);
		     
			psychoPharamClinicMedActionPlanList =sqlQueryCriteria.list();
			 * 
			 * 
			 */

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("In catch block :::::"+psychoPharamClinicMedActionPlanList.size()) ;
			e.printStackTrace();
			
		}
		
		return psychoPharamClinicMedActionPlanList;
	}


/**
 * Get Pscho Pharm Drug Name Data for Drug dose response curve 
 * @throws SQLException 
 * @throws HibernateException 
 * MOdified On 08/dec/2014 by Nagaraj
 * for NEW G-MAP
 */
  public List<GenericMedActionPlan> getPsychoPharmMedActionChartDrugNameData(
		int patientId) {
	  String clinicName="Psychopharm";
	  List<GenericMedActionPlan> psychoPharmMedActionChartDrugNameList=new ArrayList<GenericMedActionPlan>();
	  try {
		  /*String sqlQuery="select distinct gmp.drug_name,gmp.drug_id "
					+"  from generic_med_action_plan gmp inner join  " 
					+"medicine_details  md on md.medicine_name=gmp.drug_name "
					+"where md.clinic_name='"+clinicName+"'  and gmp.patient_id="+patientId+"";*/
		  String sqlQuery="select distinct gmp.drugName,gmp.drugId "
					+"  from general_med_action_plan gmp inner join  " 
					+"medicine_details  md on md.medicine_name=gmp.drugName "
					+"where md.clinic_name='"+clinicName+"'  and gmp.patientId="+patientId+"";
		  
		     PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
		     ResultSet resultSet=statement.executeQuery();
		     while (resultSet.next()) {
		    	 GenericMedActionPlan medActionPlan=new GenericMedActionPlan();
		    	 medActionPlan.setDrugName(resultSet.getString("drugName"));
		    	// medActionPlan.setDrugNameId(resultSet.getInt("drug_name_id"));
		    	 medActionPlan.setDrugId(resultSet.getString("drugId"));
		    	 psychoPharmMedActionChartDrugNameList.add(medActionPlan);
				
			}
		     
		     /**
		      *  String sqlQuery="select distinct p.drug_name,gmp.drug_id "
					+"  from general_med_action_plan gmp inner join  " 
					+"medicine_details  md on md.medicine_name=gmp.drug_name "
					+"where md.clinic_name='"+clinicName+"'  and gmp.patient_id="+patientId+"";
		      * 
		      * 
		      * 
		      */
		     
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	 
	return psychoPharmMedActionChartDrugNameList;
  }

/**
 * Get PsychoPharm Med Action Plan Related Monitoring Parameter Chart Value Data 
 * MOdified ON 08/dec/2014
 * new G-MAP by Nagaraj
 */

public List<GenericMedActionPlan> getPsychoPharmMonitoringParameterChartValue(
		int patientId) {
	List<GenericMedActionPlan> psychoPharmMonitoringParameterValueList=new ArrayList<GenericMedActionPlan>();
	try {
		String clinicName="Psychopharm";
		/*String sqlQuery="select distinct gmp.monitoring_parameter,gmp.lab_unit from generic_med_action_plan "
				+" gmp inner join medicine_details md on gmp.drug_name=md.medicine_name where "
				+" md.clinic_name='"+clinicName+"' and gmp.patient_id="+patientId+" ";*/
		String sqlQuery="select distinct pt.lab_type,pt.units from general_med_action_plan "
				+" gmp inner join general_med_action_plan_parameters cp on cp.medActionPlanId=gmp.med_Action_PlanId "
		 		+" inner join procedure_type pt on pt.procedure_type_id=cp.procedure_type_id "
				+" inner join medicine_details md on gmp.drugName=md.medicine_name where "
				+" LCASE(md.clinic_name)='"+clinicName+"' and gmp.patientId="+patientId+" ";
		PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
		ResultSet resultSet=statement.executeQuery();
		while (resultSet.next()) {
			GenericMedActionPlan medActionPlan=new GenericMedActionPlan();
			medActionPlan.setLabUnit(resultSet.getString("units"));
			medActionPlan.setMonitoringParameter(resultSet.getString("lab_type"));
			
			psychoPharmMonitoringParameterValueList.add(medActionPlan);
		}
	
		
		 
		
				
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}

	return psychoPharmMonitoringParameterValueList;
}



/*
 * Get Hepatitis C Patient Med Action Plan Data 
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IPatientMedicineDAO#getHepatitisCMedActionPlanData(int)
 */
/**
 * modified on 08/dec/2014 for new general MAP
 * 
 */
public List<ParentMedActionPlan> getHepatitisCMedActionPlanData(int patientId) {
	// TODO Auto-generated method stub
	List<ParentMedActionPlan> hepatitisCMedActionPlanList=new ArrayList<ParentMedActionPlan>();
	String clinicName="HepatitisC";
	/*String sqlQuery="select gmp.* "
			+ "from generic_med_action_plan gmp inner join  " 
			+"medicine_details  md on md.medicine_name=gmp.drug_name "
			+"where md.clinic_name='"+clinicName+"'  and gmp.patient_id="+patientId+"";*/
	String sqlQuery="select gmp.* "
			+ "from general_med_action_plan gmp inner join  " 
			+"medicine_details  md on md.medicine_name=gmp.drugName "
			+"where LCASE(md.clinic_name)='"+clinicName+"'  and gmp.patientId="+patientId+"";
	SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
	sqlQueryCriteria.addEntity(ParentMedActionPlan.class);
     
	hepatitisCMedActionPlanList =sqlQueryCriteria.list();
	
	for(ParentMedActionPlan pMAP:hepatitisCMedActionPlanList){
		Hibernate.initialize(pMAP.getChildLabParameters());
		String createdUserName=getUserNameByUserId(pMAP.getCreatedBy());
		pMAP.setCreatedByName(createdUserName);
	
		if(pMAP.getCreatedBy()!=pMAP.getUpdatedBy()){
		String updatedUserName=getUserNameByUserId(pMAP.getUpdatedBy());
		pMAP.setUpdatedByName(updatedUserName);
		}else{
			pMAP.setUpdatedByName(createdUserName);
		}
	}
	
	return hepatitisCMedActionPlanList;
}



@Override
public List<PatientMedicationData> getCurrentPsychopharmMeds(int patientId,
		String psychopharmClinicName) {
	List<PatientMedicationData>currentPsychopharmMedsList=new ArrayList<PatientMedicationData>();
	try{
		String sqlQuery="select gmp.* "
				+ "from patient_medication_data gmp inner join  " 
				+"medicine_details  md on md.medicine_name=gmp.drugs "
				+"where LCASE(md.clinic_name)='"+psychopharmClinicName+"'  and gmp.user_id="+patientId+"";
		SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		sqlQueryCriteria.addEntity(PatientMedicationData.class);
		currentPsychopharmMedsList=sqlQueryCriteria.list();
	}catch(HibernateException hfe){
		logger.error("Error in getting psychorpharm meds");
		hfe.printStackTrace();
	}
	return currentPsychopharmMedsList;
}


/**
 * Get all current Hepatitis C Medicine 
 */
public List<PatientMedicationData> getCurrentHepatitsCMedData(int patientId,
		String hepatitisCClinicName) {
	List<PatientMedicationData> currentHepatitisCMedList=new ArrayList<PatientMedicationData>();
	try {
		String sqlQuery="select gmp.* "
				+ "from patient_medication_data gmp inner join  " 
				+"medicine_details  md on md.medicine_name=gmp.drugs "
				+"where LCASE(md.clinic_name)='"+hepatitisCClinicName+"'  and gmp.user_id="+patientId+"";
		SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		sqlQueryCriteria.addEntity(PatientMedicationData.class);
		currentHepatitisCMedList=sqlQueryCriteria.list();
		

	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return currentHepatitisCMedList;
}

/**
 * 
 * MOdified On 8/dec/2014 for NEW G-MAP
 */

public List<GenericMedActionPlan> getHepCChartLabMonitoringParameterData(
		int patientId, String hepatitisCClinicName) {
	List<GenericMedActionPlan> hepCLabChartMonitoringParameterList=new ArrayList<GenericMedActionPlan>();
	try {
		
		
	/*	String sqlQuery="select distinct gmp.monitoring_parameter,gmp.lab_unit from generic_med_action_plan "
				+" gmp inner join medicine_details md on gmp.drug_name=md.medicine_name where "
				+" md.clinic_name='"+hepatitisCClinicName+"' and gmp.patient_id="+patientId+" ";*/
		
		String sqlQuery="select distinct pt.lab_type,pt.units from general_med_action_plan "
				+" gmp inner join general_med_action_plan_parameters cp on cp.medActionPlanId=gmp.med_Action_PlanId "
		 		+" inner join procedure_type pt on pt.procedure_type_id=cp.procedure_type_id "
				+" inner join medicine_details md on gmp.drugName=md.medicine_name where "
				+" LCASE(md.clinic_name)='"+hepatitisCClinicName+"' and gmp.patientId="+patientId+" ";
		PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
		ResultSet resultSet=statement.executeQuery();
		while (resultSet.next()) {
			GenericMedActionPlan medActionPlan=new GenericMedActionPlan();
			medActionPlan.setLabUnit(resultSet.getString("lab_unit"));
			medActionPlan.setMonitoringParameter(resultSet.getString("monitoring_parameter"));
			
			hepCLabChartMonitoringParameterList.add(medActionPlan);
		}
				
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	return hepCLabChartMonitoringParameterList;
}

/**
 * Get All Psycho Pharm Medicine List
 */
public List<String> getAllPsychoPharmClinicMedicineList(
		String psychopharmClinicName) {
	List<String> psychoPharmClinicMedList=new ArrayList<String>();
	String sqlQuery="select medicine_name from medicine_details where clinic_name='"+psychopharmClinicName+"'";
	try {
		PreparedStatement preparedStatement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
		ResultSet resultSet=preparedStatement.executeQuery();
		while (resultSet.next()) {
			String medicineName=resultSet.getString("medicine_name");
			psychoPharmClinicMedList.add(medicineName);
		}
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return psychoPharmClinicMedList;
}

/**
 * Get All Hepititis C Clinic Med Name List
 */
public List<String> getHepatitisCClinicMedicineList(String hepatitisCClinicName) {
	// TODO Auto-generated method stub
	List<String> hepCClinicMedicineList=new ArrayList<String>();
	try {
		String sqlQuery="select medicine_name from medicine_details where clinic_name='"+hepatitisCClinicName+"'";
	
		PreparedStatement preparedStatement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
		ResultSet resultSet=preparedStatement.executeQuery();
		while (resultSet.next()) {
			String medicineName=resultSet.getString("medicine_name");
			hepCClinicMedicineList.add(medicineName);
		}
	
   } catch (HibernateException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
  } catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
  }
	return hepCClinicMedicineList;
}


@Override
public List<ContraindicatedMeds> getMasterContraindicatedMeds() {
	List<ContraindicatedMeds>contraindicatedMeds=new ArrayList<ContraindicatedMeds>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ContraindicatedMeds.class);
		criteria.add(Restrictions.eq("status", true));
		contraindicatedMeds=criteria.list();
	}catch(HibernateException he){
		he.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	logger.info("contraindicatedMeds size list from db "+contraindicatedMeds.size());
	return contraindicatedMeds;
}


@Override
public List<MasterMonitorParameters> getGeneralMedPlanLabs(String generalMapView) {
	List<MasterMonitorParameters>generalMedPlanParams=new ArrayList<MasterMonitorParameters>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MasterMonitorParameters.class);
		criteria.add(Restrictions.eq("viewCategory", generalMapView));
		criteria.add(Restrictions.eq("status", true));
		generalMedPlanParams=criteria.list();	
		for(MasterMonitorParameters masterMonitorParameters:generalMedPlanParams){
			Hibernate.initialize(masterMonitorParameters.getChildMonitoringParameters());
		
		}
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	logger.info("General MAP top labels "+generalMedPlanParams.size());
	return generalMedPlanParams;
}


@Override
public ParentMedActionPlan getAllMedPlansForPatient(
		PatientMedicationData patientMedicationData, int patientID) {
	ParentMedActionPlan medActionObj=null;
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ParentMedActionPlan.class);
		criteria.add(Restrictions.eq("patientId", patientID));
		criteria.add(Restrictions.eq("drugId", patientMedicationData.getDrugId()));
		medActionObj=(ParentMedActionPlan) criteria.uniqueResult();
		if(medActionObj!=null){
			Hibernate.initialize(medActionObj.getChildLabParameters());
		}
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Throwable t){
		t.printStackTrace();
	}
	return medActionObj;
}


@Override
	public boolean saveNewGeneralMedActionPlan(int patientId,
			ParentMedActionPlan parentMedActionPlan,
			List<ChildMedActionPlanParameter> temporaryChildMedActionPlansParams,Date dosingStartDate,List<PatientDiagnosesDetails> patientDiagnosisList) {

		int doctorId = new ContextUtil().getLoginId();
		String loggedInName = new ContextUtil().getLoggerFirstName() + " "
				+ new ContextUtil().getLoggedUserMiddleName() + " "
				+ new ContextUtil().getLoggerLastName();
		int clinicProviderId = new ContextUtil().getClinicProviderId();
		int providerId=new ContextUtil().getProviderId();
		logger.info("patient id==>" + patientId + " doctor ID ==>" + doctorId
				+ " clinic provider id ==>" + clinicProviderId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		boolean success = false;
		try {
			if (temporaryChildMedActionPlansParams!=null && temporaryChildMedActionPlansParams.size() > 0) {
				for (ChildMedActionPlanParameter childMedActionPlanParameter : temporaryChildMedActionPlansParams) {
					if (childMedActionPlanParameter.isDatabaseCheck()) {
						delete(childMedActionPlanParameter);
					}
				}
			}
			
			boolean isDiagnosisExists=false;
			if(parentMedActionPlan.getPatientDiagnosisDetailsObj()!=null){
			if (StringUtils.isNotBlank(parentMedActionPlan.getPatientDiagnosisDetailsObj().getIcdId())) {

				for (PatientDiagnosesDetails patdiagosisDetail : patientDiagnosisList) {

					if (patdiagosisDetail.getIcdId().equals(
							parentMedActionPlan.
							getPatientDiagnosisDetailsObj().getIcdId()))
						isDiagnosisExists = true;
				}
			}
			//If diagnosis doesn't exists in current patient diagnosis list then save it.
			if(!isDiagnosisExists){
				savePatientMedPlanDiagnosis(parentMedActionPlan.getPatientDiagnosisDetailsObj());
			}
			}
			
			
			
			//checking for updating MAP if goal or alert changes
			//once only get all MAP 
		/*	boolean onceCheckDb=true;
			List<ParentMedActionPlan>allMedPlansForPatient=new ArrayList<ParentMedActionPlan>();
			for(ChildMedActionPlanParameter cMapObj:parentMedActionPlan.getChildLabParameters()){
				
				if(cMapObj.isMedPlanUpdateRequired()){
					if(onceCheckDb){
						allMedPlansForPatient=generalMedPlansForParticularPatient(parentMedActionPlan.getPatientId());
						
						onceCheckDb=false;
					}
					updatedorSaveGenericToParticularMAP(allMedPlansForPatient,cMapObj);
				}
				
			}*/
			
			if(parentMedActionPlan.getId()>0){
				parentMedActionPlan.setUpdatedBy(new ContextUtil().getLoginId());
				parentMedActionPlan.setUpdatedDate(new Date());
			}else{
				parentMedActionPlan.setCreatedBy(new ContextUtil().getLoginId());
				parentMedActionPlan.setCreatedDate(new Date());
				parentMedActionPlan.setUpdatedBy(new ContextUtil().getLoginId());
				parentMedActionPlan.setUpdatedDate(new Date());
			}
			parentMedActionPlan.setClinicProviderId(clinicProviderId);
			parentMedActionPlan.setProviderId(providerId);
	//		parentMedActionPlan.setDosingStartDate(dosingStartDate);
			for(ChildMedActionPlanParameter childParameter:parentMedActionPlan.getChildLabParameters()){
				if(childParameter.getId()>0){
					childParameter.setUpdatedBy(new ContextUtil().getLoginId());
					childParameter.setUpdatedDate(new Date());
					
				}else{
					childParameter.setCreatedBy(new ContextUtil().getLoginId());
					childParameter.setCreatedDate(new Date());
					childParameter.setUpdatedBy(new ContextUtil().getLoginId());
					childParameter.setUpdatedDate(new Date());
				}
			}

			saveOrUpdate(parentMedActionPlan);

			if (parentMedActionPlan.getId() > 0) {
				success = true;
			}

		} catch (HibernateException hfe) {
			success = false;
			hfe.printStackTrace();
		} catch (Throwable t) {
			success = false;
			t.printStackTrace();
		}
		return success;

	}


private void savePatientMedPlanDiagnosis(
		PatientDiagnosesDetails patientDiagnosisDetailsObj) {
	
		logger.info("savePatientDiagnosis method in dao::::::");
		
		try {
			patientDiagnosisDetailsObj.setDate(new DateUtil().getTodayDate());
			patientDiagnosisDetailsObj.setDoctorId(new ContextUtil().getLoginId());
			patientDiagnosisDetailsObj.setCodeDescription(patientDiagnosisDetailsObj.getCodeDescription().trim());
			save(patientDiagnosisDetailsObj);	
		} 
		catch (Exception e) {
			logger.info("::exception in savePatientDiagnosis:::::::::::::::::::::::");
			e.printStackTrace();
		}
	
		logger.info("value is save in :::::::::::::::::::::::");
}


/**
 * @param patientId
 * @return List of ParentMedActionPlan
 * @see com.clinakos.data.dao.IPatientMedicineDAO#generalMedPlansForParticularPatient(int)
 */
public List<ParentMedActionPlan> generalMedPlansForParticularPatient(
		int patientId) {
	List<ParentMedActionPlan>allGeneralMedPlans=new ArrayList<ParentMedActionPlan>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ParentMedActionPlan.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("isDrugDiscontinued", false));
		allGeneralMedPlans=criteria.list();
		for(ParentMedActionPlan parentMedActionPlan:allGeneralMedPlans){
			Hibernate.initialize(parentMedActionPlan.getChildLabParameters());
			String createdUserName=getUserNameByUserId(parentMedActionPlan.getCreatedBy());
			parentMedActionPlan.setCreatedByName(createdUserName);
		
			if(parentMedActionPlan.getCreatedBy()!=parentMedActionPlan.getUpdatedBy()){
			String updatedUserName=getUserNameByUserId(parentMedActionPlan.getUpdatedBy());
			parentMedActionPlan.setUpdatedByName(updatedUserName);
			}else{
				parentMedActionPlan.setUpdatedByName(createdUserName);
			}
		}
		logger.info("all Med plans for Patient Id "+allGeneralMedPlans.size());
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(IndexOutOfBoundsException ie){
		ie.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return allGeneralMedPlans;
}


public String getUserNameByUserId(int createdBy) {
	Query query=getSessionFactory().getCurrentSession().createQuery("select ud.firstName,ud.middleName,ud.lastName from UserLoginDetail ud where userId=:userID");
	query.setInteger("userID", createdBy);
	String userName=new String();
	List userNameQuery=query.list();
	Iterator it=userNameQuery.iterator();
	while(it.hasNext()){
		 Object[] row = (Object[]) it.next();
		userName=row[0]+" "+row[1]+" "+row[2];
		
		
	}
	logger.info("user name obj "+userName);
	return userName;
}


/**
 * It is used for Delete ParentMedActionPlan which latest status is false 
 * @param parentMedActionPlan 
 * @see com.clinakos.data.dao.IPatientMedicineDAO#deleteGeneralMAPonSelectedDrug(ParentMedActionPlan)
 */
public void deleteGeneralMAPonSelectedDrug(
		ParentMedActionPlan parentMedActionPlan) {
	try{
		
		/*Query query =getSessionFactory().getCurrentSession().createQuery("delete ParentMedActionPlan where id=:id and drugId =:drugId and patientId =:patientId");
		query.setDouble("drugId", parentMedActionPlan.getDrugId());
		query.setInteger("id", parentMedActionPlan.getId());
		query.setInteger("patientId", parentMedActionPlan.getPatientId());
		int result = query.executeUpdate();*/
		
		getSessionFactory().getCurrentSession().delete(parentMedActionPlan);
		
		//after deleting MAP update the alerts Messages 
	   Query updateQuery = getSessionFactory().getCurrentSession().createQuery("update AlertGenericMedActionLab set status=:lateststatus  where drugId=:drugId and patientId=:patientId");
		updateQuery.setBoolean("lateststatus", false);
		updateQuery.setDouble("drugId",parentMedActionPlan.getDrugId());
		updateQuery.setInteger("patientId", parentMedActionPlan.getPatientId());
		int updateQueryResult = updateQuery.executeUpdate();
		logger.info("result:::::::::::::::::>>>>>>>><<<<<"+updateQueryResult);
		
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


@Override
public void updateGeneralMAP(ParentMedActionPlan parentMedActionPlan) {
	try{
	/*	GenericMedActionPlan genMedActionPlanObj=genericMedActionPlan;
		genMedActionPlanObj.setLastUpdatedDate(new Date());
		genMedActionPlanObj.setFrequencyValue(frequency);
		genericMedActionPlan.setLastUpdatedBy(doctorId);
		genericMedActionPlan.setLastUpdatedByName(loggedInName);
		update(genMedActionPlanObj);*/
		
		
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


@Override
public List<ParentMedActionPlan> getPsychopharmClinicMedPlans(int patientId,
		String psychopharmClinicName) {
	List<ParentMedActionPlan>psychopharmClinicMedPlans=new ArrayList<ParentMedActionPlan>();
	try{
		String sqlQuery="select gmp.* "
				+ "from general_med_action_plan gmp inner join  " 
				+"medicine_details  md on md.medicine_name=gmp.drugName "
				+"where md.clinic_name='"+psychopharmClinicName+"'  and gmp.patientId="+patientId+"";
		SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		sqlQueryCriteria.addEntity(ParentMedActionPlan.class);
		psychopharmClinicMedPlans=sqlQueryCriteria.list();
		
		for(ParentMedActionPlan pMAP:psychopharmClinicMedPlans){
			Hibernate.initialize(pMAP.getChildLabParameters());
			String createdUserName=getUserNameByUserId(pMAP.getCreatedBy());
			pMAP.setCreatedByName(createdUserName);
		
			if(pMAP.getCreatedBy()!=pMAP.getUpdatedBy()){
			String updatedUserName=getUserNameByUserId(pMAP.getUpdatedBy());
			pMAP.setUpdatedByName(updatedUserName);
			}else{
				pMAP.setUpdatedByName(createdUserName);
			}
		}
		
	}catch(HibernateException e){
		e.printStackTrace();
	}
	logger.info("Psychopharm Clinic Med Plans size "+psychopharmClinicMedPlans.size());
	return psychopharmClinicMedPlans;
}


@Override
public List<MasterMonitorParameters> getPsychopharmDefaultLabParameters(
		String psychopharmMapView, String genderOfPatient) {
	List<MasterMonitorParameters>psychopharmDefaultLabs=new ArrayList<MasterMonitorParameters>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MasterMonitorParameters.class);
		criteria.add(Restrictions.eq("viewCategory", psychopharmMapView));
		criteria.add(Restrictions.eq("gender", genderOfPatient));
		
		psychopharmDefaultLabs=criteria.list();
		for(MasterMonitorParameters masterMonitorParameters:psychopharmDefaultLabs){
			Hibernate.initialize(masterMonitorParameters.getChildMonitoringParameters());
		}
	}catch(HibernateException he){
		he.printStackTrace();
	}
	return psychopharmDefaultLabs;
}


/**
 * Get Reconcile Info data based on patient id for showing latest Reconcile info  
 * @param patientId 
 * @return ReconcileInfo
 * @see com.clinakos.data.dao.IPatientMedicineDAO#getLatestReconcileInfo(int)
 */
public ReconcileInfo getLatestReconcileInfo(int patientId) {
	ReconcileInfo reconcileInfo=new ReconcileInfo();
	try{
	/*	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ReconcileInfo.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("status", true));
		//criteria.setProjection(Projections.max("lastReconciledDate"));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.max("lastReconciledDate"),"lastReconciledDate")
				.add(Projections.property("lastReconciledBy"),"lastReconciledBy")
				);
		criteria.setResultTransformer(Transformers.aliasToBean(ReconcileInfo.class));
		reconcileInfo=(ReconcileInfo) criteria.uniqueResult();*/
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ReconcileInfo.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("status", true));
		criteria.addOrder(Order.desc("lastReconciledDate"));
		criteria.setMaxResults(1);
		reconcileInfo=(ReconcileInfo) criteria.uniqueResult();
		if(reconcileInfo!=null){
			reconcileInfo.setLastReconciledDateStr(new DateUtil().convertDateFormatUsingFormat(reconcileInfo.getLastReconciledDate(), MM_DD_YYYY_DATE_PATTERN));
			reconcileInfo.setLastReconciledByName(getUserNameByUserId(reconcileInfo.getLastReconciledBy()));
			logger.info("Last Reconciled By Name "+reconcileInfo.getLastReconciledByName()+"\n Last Reconciled Date "+reconcileInfo.getLastReconciledDateStr());
		}
		
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return reconcileInfo;
}


@Override
public void updateReconcileInfoonDeleteMed(
		PatientMedicationData changePatientMedicineBackUpdata, boolean status) {
	try{
		Query query =getSessionFactory().getCurrentSession().createQuery("update ReconcileInfo set status=:status where drugId =:drugId and patientId =:patientId");
		query.setDouble("drugId", changePatientMedicineBackUpdata.getDrugId());
		query.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
		query.setBoolean("status", status);
		int result = query.executeUpdate();
		logger.info(":::::::::::result:::"+result);
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


@Override
public List<GeriatricPrecaution> getGeriatricPrecautions(double selectedDrugId) {
	List<GeriatricPrecaution>geriatricPrecautions=new ArrayList<GeriatricPrecaution>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GeriatricPrecaution.class);
		criteria.add(Restrictions.eq("drugId", selectedDrugId));
		geriatricPrecautions=criteria.list();
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return geriatricPrecautions;
}


@Override
public List<PaediatricPrecaution> getPaediatricPrecautions(
		double selectedDrugId, Integer patientAgeInDaysVal) {
	List<PaediatricPrecaution>paediatricPrecautions=new ArrayList<PaediatricPrecaution>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PaediatricPrecaution.class);
		criteria.add(Restrictions.eq("drugId", selectedDrugId));
		paediatricPrecautions=criteria.list();
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return paediatricPrecautions;
}


@Override
public void saveReconcileInfoObj(
		PatientMedicationData patientMedicationDataForReconcile) throws HibernateException, Exception {
	 //Added by Nagaraj for ReconcileInfo to be saved in reconcile_info Table added on 14 jan 2015
	 saveReconcileInfo(patientMedicationDataForReconcile);
	
}


@Override
public void deleteSelectedRxDrug(
		PatientMedicationData changePatientMedicineBackUpdata) {
	try{
		savePatientMedHistoryAndDeletePatientMedicationData(changePatientMedicineBackUpdata);
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


@Override
public void updateMAPmessage(
		PatientMedicationData changePatientMedicineBackUpdata) {
	try{
		Query alertMedActionLab =getSessionFactory().getCurrentSession().createQuery("delete AlertGenericMedActionLab where drugId =:drugId and patientId =:patientId");
		alertMedActionLab.setString("drugId", String.valueOf((int)changePatientMedicineBackUpdata.getDrugId()));
		alertMedActionLab.setInteger("patientId", changePatientMedicineBackUpdata.getPatientId());
		int alertMessage = alertMedActionLab.executeUpdate();
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}



public List<EncounterSummary> getPatientEncounterSummaryHistoryData(
		int patientId) {
	List<EncounterSummary> encounterSummaryHistoryList=new ArrayList<EncounterSummary>();
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EncounterSummary.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.addOrder(Order.desc("id"));
		criteria.setMaxResults(10);
		encounterSummaryHistoryList=criteria.list();
		
		
	} catch (HibernateException he) {
		he.printStackTrace();
	}
	return encounterSummaryHistoryList;
}


public List<EncounterSummary> getEncounterSummaryHistoryData(int patientId,
		Date startDate, Date endDate) {
	List<EncounterSummary> encounterSummaryHistory=new ArrayList<EncounterSummary>();
	List<EncounterSummary> filterEncounterSummaryList=new ArrayList<EncounterSummary>();
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EncounterSummary.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.addOrder(Order.desc("encounterDate"));
		/*criteria.add(Restrictions.ge("encounterDate",  endDate));*/
		encounterSummaryHistory=criteria.list();
		Date searchEncounterDate=new DateUtil().AddDate(endDate, -1);
		System.out.println(searchEncounterDate);
		for(EncounterSummary summary:encounterSummaryHistory){
			if(summary.getEncounterDate().after(searchEncounterDate)){
				EncounterSummary encounterSummary=new EncounterSummary();
				encounterSummary.setEncounterDate(summary.getEncounterDate());
				encounterSummary.setIfYes(summary.getIfYes());
				encounterSummary.setPatientNotes(summary.getPatientNotes());
				encounterSummary.setPatientEducation(summary.getPatientEducation());
				encounterSummary.setEncounterId(summary.getEncounterId());
				encounterSummary.setReasonForEncounter(summary.getReasonForEncounter());
				encounterSummary.setEncounterNo(summary.getEncounterNo());
				filterEncounterSummaryList.add(encounterSummary);
			}
		}
		System.out.println("encounterSummaryHistory.size()"+encounterSummaryHistory.size());
		System.out.println("filterEncounterSummaryList"+filterEncounterSummaryList.size()+"Search Date"+searchEncounterDate);
	} catch (HibernateException he) {
		he.printStackTrace();
		
	}
	return filterEncounterSummaryList;
}




public EncounterSummary getSelectedEncounterSummaryData(int patientId,
		int providerId, Date selectedEncounterDateForPrint) {
	EncounterSummary summary=new EncounterSummary();
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EncounterSummary.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.eq("encounterDate", selectedEncounterDateForPrint));
		summary=(EncounterSummary) criteria.list().get(0);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return summary;
}



public List<MedandGenricmed> getMedAndGenrticMedPlanDataForSelectedDate(
		int patientId, int providerId, Date selectedEncounterDateForPrint) {

	List<MedandGenricmed>medActionPlanListforPatient=new ArrayList<MedandGenricmed>();
	List<MedandGenricmed>medActionPlanListforSelectedDate=new ArrayList<MedandGenricmed>();
	try{
		
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String selectedEncounterDateInString=formatter.format(selectedEncounterDateForPrint);
		Date minSelectedEncounterDate=formatter.parse(selectedEncounterDateInString);
		Date maxSelectedEncounterDate=new Date(minSelectedEncounterDate.getTime()+TimeUnit.DAYS.toMillis(1));
	   
	  Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
	  criteria.add(Restrictions.eq("patientId", patientId));
	  criteria.add(Restrictions.between("lastupdatedate", minSelectedEncounterDate, maxSelectedEncounterDate));
	  
	  medActionPlanListforPatient=criteria.list();

		
		for(MedandGenricmed medgenericmed:medActionPlanListforPatient)
		{
			MedandGenricmed medgen=new MedandGenricmed();
			medgen.setCreatedate(medgenericmed.getCreatedate());
			medgen.setMedicinename(medgenericmed.getMedicinename());
			medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
			if(StringUtils.isNotBlank(medgenericmed.getLastupdateby()) ){
				if(StringUtils.isAlphaSpace(medgenericmed.getLastupdateby())){
				medgen.setLastupdateby(medgenericmed.getLastupdateby());
			}else{
				medgen.setLastupdateby(getUserNameByUserId(Integer.valueOf(medgenericmed.getLastupdateby())));
			}
			}
			
			medgen.setLabfrequency(medgenericmed.getLabfrequency());
			medgen.setLab(medgenericmed.getLab());
			medgen.setAcceptablerange(medgenericmed.getAcceptablerange());
			medgen.setLastupdatedate(medgenericmed.getLastupdatedate());
			
			
			
		/*	if(((medgen.getCreatedate().after(minSelectedEncounterDate) || (medgen.getCreatedate().before(maxSelectedEncounterDate))||((medgen.getLastupdatedate().after(minSelectedEncounterDate) || (medgen.getLastupdatedate().before(minSelectedEncounterDate)))))))
	    	 {*/
				 System.out.println("date for med action plan"+medgen.getCreatedate());
				 medActionPlanListforSelectedDate.add(medgen);
	    	/* }*/
		}
		
		} 
	catch (Exception e) {
			e.printStackTrace();
		}
	return medActionPlanListforSelectedDate;
}



@Override
public void updateMedAactionPlanStatus(
		PatientMedicationData changePatientMedicineBackUpdata) {
	try{
		Query generalMedPlanQuery=getSessionFactory().getCurrentSession().createQuery("update ParentMedActionPlan set isDrugDiscontinued=:isMedDiscontinued where drugId=:drugid and patientId=:patientId");
		generalMedPlanQuery.setParameter("isMedDiscontinued", true);
		generalMedPlanQuery.setParameter("drugid",changePatientMedicineBackUpdata.getDrugId());
		generalMedPlanQuery.setParameter("patientId", changePatientMedicineBackUpdata.getPatientId());
		int genralMedPlanUpdateQuery=generalMedPlanQuery.executeUpdate();
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


/**
 * It will give the list of ParentMedActionPlanwhich has been discontinued 
 * @param patientId
 * @return List of ParentMedActionPlan
 * 
 */
public List<ParentMedActionPlan> getInactiveMedsWithActiveMAP(int patientId) {
	List<ParentMedActionPlan>inactiveMedsWithActiveMAP=new ArrayList<ParentMedActionPlan>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ParentMedActionPlan.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("isDrugDiscontinued", true));
		inactiveMedsWithActiveMAP=criteria.list();
		for(ParentMedActionPlan parentMedActionPlan:inactiveMedsWithActiveMAP){
			Hibernate.initialize(parentMedActionPlan.getChildLabParameters());
			String createdUserName=getUserNameByUserId(parentMedActionPlan.getCreatedBy());
			parentMedActionPlan.setCreatedByName(createdUserName);
		
			if(parentMedActionPlan.getCreatedBy()!=parentMedActionPlan.getUpdatedBy()){
			String updatedUserName=getUserNameByUserId(parentMedActionPlan.getUpdatedBy());
			parentMedActionPlan.setUpdatedByName(updatedUserName);
			}else{
				parentMedActionPlan.setUpdatedByName(createdUserName);
			}
		}
		logger.info("all Med plans for Patient Id "+inactiveMedsWithActiveMAP.size());
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return inactiveMedsWithActiveMAP;
}



public List<SendMessageEditRx> getSendMessageEditRDataForSelectedEncounterDate(
		int patientId, int providerId, Date selectedEncounterDateForPrint) {
	List<SendMessageEditRx> sendMessageEditRxList=new ArrayList<SendMessageEditRx>();
	try {
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String selectedEncounterDateInString=formatter.format(selectedEncounterDateForPrint);
		Date minSelectedEncounter=formatter.parse(selectedEncounterDateInString);
		Date maxSelectedEncounterDate=new Date(minSelectedEncounter.getTime()+TimeUnit.DAYS.toMillis(1));
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(SendMessageEditRx.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.between("messageSentDate",minSelectedEncounter, maxSelectedEncounterDate));
		sendMessageEditRxList=criteria.list();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return sendMessageEditRxList;
}



public List<PatientMedicationData> getAddedOrChangeMedDataOnSelectedEncounterDate(
		int patientId, int providerId, Date selectedEncounterDateForPrint) {
	    List<PatientMedicationData> addedOrChangedMedDataList=new ArrayList<PatientMedicationData>();
	try {
		DateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
		String selectedEncounterDateInString=formatter.format(selectedEncounterDateForPrint);
		Date minSelectedEncounterDate=formatter.parse(selectedEncounterDateInString);
		Date maxSelectedEncounterDate=new Date(minSelectedEncounterDate.getTime()+TimeUnit.DAYS.toMillis(1));
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.between("startDate", minSelectedEncounterDate,maxSelectedEncounterDate));
		addedOrChangedMedDataList=criteria.list();
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return addedOrChangedMedDataList;
}


/**
 * Get all current medication on Selected Encounter date 
 */
public List<PatientMedicationData> getPatientMedicationDataForSelectedEncounterDate(
		int patientId, int providerId, Date selectedEncounterDateForPrint) {
	List<PatientMedicationData> patientMedicationDataListForSelectedEncounterDate=new ArrayList<PatientMedicationData>();
	try {
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.le("startDate", selectedEncounterDateForPrint));
		criteria.addOrder(Order.desc("startDate"));
		patientMedicationDataListForSelectedEncounterDate=criteria.list();
	} catch (HibernateException he) {
		he.printStackTrace();
	}
	return patientMedicationDataListForSelectedEncounterDate;
}


@Override
public Map<Integer, List<PatientMedicationData>> getPatientMedicationDataMap(
		int providerId, UserLoginDetail[] selectedUserLoginDetail) {
	Map<Integer,List<PatientMedicationData>>medicationMap=new HashMap<Integer, List<PatientMedicationData>>();
	try{
		List<Integer>userIds=new ArrayList<Integer>();
		for(UserLoginDetail userLoginDetail:selectedUserLoginDetail){
			userIds.add(userLoginDetail.getUserId());
		}
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.in("patientId", userIds));
		
		List<PatientMedicationData>patientMedications=criteria.list();
		if(patientMedications.size()>0){
		medicationMap=getPatientMedicationMap(patientMedications);
		}
	}catch(HibernateException hfe){
		medicationMap=new HashMap<Integer, List<PatientMedicationData>>();
		hfe.printStackTrace();
	}catch(Exception e){
		medicationMap=new HashMap<Integer, List<PatientMedicationData>>();
		e.printStackTrace();
	}
	return medicationMap;
}


private Map<Integer, List<PatientMedicationData>> getPatientMedicationMap(
		List<PatientMedicationData> patientMedications) {
	Map<Integer,List<PatientMedicationData>>medicationMap=new HashMap<Integer, List<PatientMedicationData>>();
	for(PatientMedicationData patientMedicationData:patientMedications){
		if(medicationMap.containsKey(patientMedicationData.getPatientId())){
			List<PatientMedicationData>medicationList=medicationMap.get(patientMedicationData.getPatientId());
			medicationList.add(patientMedicationData);
			medicationMap.put(patientMedicationData.getPatientId(), medicationList);
		}else{
			List<PatientMedicationData>medicationList=new ArrayList<PatientMedicationData>();
			medicationList.add(patientMedicationData);
			medicationMap.put(patientMedicationData.getPatientId(), medicationList);
		}
	}
	return medicationMap;
}


@Override
public List<HashMap<Integer, List<PharmacyDetail>>> getPharmacyHistoryData(
		UserLoginDetail[] selectedUserLoginDetail, LocalDate todayDate,
		LocalDate endDate) {
	Map<Integer, List<PharmacyDetail>>pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();//For Latest (Date of Purchase) Pharmacy records
	Map<Integer,List<PharmacyDetail>>pharmacyHistoryFirstFilledMap=new HashMap<Integer, List<PharmacyDetail>>();//For Oldest(Date of Purchase) Pharmacy Records;
	List<HashMap<Integer, List<PharmacyDetail>>> pharmacyMaps=new ArrayList<HashMap<Integer,List<PharmacyDetail>>>();
	try{
		List<Integer>selectedUserList=new ArrayList<Integer>();
		for(UserLoginDetail userDetail:selectedUserLoginDetail){
			selectedUserList.add(userDetail.getUserId());
		}
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
		criteria.add(Restrictions.in("patientId", selectedUserList));
		criteria.add(Restrictions.between("dateOfPurchase", endDate.toDateTimeAtStartOfDay().toDate(),todayDate.toDateTimeAtStartOfDay().toDate()));
		criteria.add(Restrictions.ne("drugNameId", new Double(0)));
		criteria.addOrder(Order.desc("dateOfPurchase"));
		
		List<PharmacyDetail>pharmacyHistoryList=criteria.list();
		if(pharmacyHistoryList.size()>0){
			
		pharmacyHistoryMap=getPharmacyMap(pharmacyHistoryList);
		pharmacyMaps.add((HashMap<Integer, List<PharmacyDetail>>) pharmacyHistoryMap);
		/*Collections.sort(pharmacyHistoryList, PharmacyDetail.oldDatePurchasePharamacyComparator);
		pharmacyHistoryFirstFilledMap=getPharmacyMap(pharmacyHistoryList);
		pharmacyMaps.add( (HashMap<Integer, List<PharmacyDetail>>) pharmacyHistoryFirstFilledMap);*/
		}
		
		
	}catch(HibernateException  e){
		pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();
		pharmacyHistoryFirstFilledMap=new HashMap<Integer, List<PharmacyDetail>>();
		e.printStackTrace();
	}catch(Exception e){
		pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();
		pharmacyHistoryFirstFilledMap=new HashMap<Integer, List<PharmacyDetail>>();
		e.printStackTrace();
	}
	return pharmacyMaps;
}



private Map<Integer, List<PharmacyDetail>> getPharmacyMap(
		List<PharmacyDetail> pharmacyHistoryList) {
	logger.info("Creating Pharmacy Map");
	Map<Integer,List<PharmacyDetail>>pharmacyMap=new HashMap<Integer, List<PharmacyDetail>>();
	for(PharmacyDetail pharmacyDetail:pharmacyHistoryList){
		int patientId=pharmacyDetail.getPatientId();
		if(pharmacyMap.containsKey(patientId)){
			List<PharmacyDetail>pharmacyList=pharmacyMap.get(patientId);
			boolean isLatestDrugExists=getLatestPharmacyRecord(pharmacyDetail.getDrugId(),pharmacyDetail.getDrugNameId(),pharmacyList);
			if(!isLatestDrugExists){
			pharmacyList.add(pharmacyDetail);
			pharmacyMap.put(patientId, pharmacyList);
		}
		}else{
			List<PharmacyDetail>pharmacyList=new ArrayList<PharmacyDetail>();
			pharmacyList.add(pharmacyDetail);
			pharmacyMap.put(patientId, pharmacyList);
		}
	}
	return pharmacyMap;
}


private boolean getLatestPharmacyRecord(double drugId, double drugNameId,
		List<PharmacyDetail> pharmacyList) {
	for(PharmacyDetail pharmacyDetail:pharmacyList){
		if(drugNameId == pharmacyDetail.getDrugNameId() && drugId == pharmacyDetail.getDrugId()){
			return true;
		}
	}
	return false;
}


@Override
public void saveAutoFillReconcileMeds(
		Map<Integer, List<PatientMedicationData>> pharmacyToPatientMedicationObjMaps) {
	
		for(Map.Entry<Integer, List<PatientMedicationData>>entry:pharmacyToPatientMedicationObjMaps.entrySet()){
			try{
				
			int counter=0;
			logger.info("Patient Id "+entry.getKey());
			for(PatientMedicationData patientMedicationData:entry.getValue()){
			logger.info("Medication details :: Drug Id --> "+patientMedicationData.getDrugId()+" :: provider id --> "+patientMedicationData.getProviderId()+" :: Drug Name Id --> "+patientMedicationData.getDrugNameId());
			deletePatientMedicationData(patientMedicationData.getDrugId(),patientMedicationData.getPatientId(),patientMedicationData.getProviderId(),patientMedicationData.getDrugNameId());
			 getSessionFactory().getCurrentSession().save(patientMedicationData);
			counter=counter+1;
			if(counter% 50 ==0){
				getSessionFactory().getCurrentSession().flush();
				getSessionFactory().getCurrentSession().clear();
			}
			 
			}
		}catch(HibernateException hfe){
			hfe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}


/**
 * Method used to get pharmacy old history first filled date for Batch Reconcile process used in Batch Pull Process page 
 * 
 * @author Nagaraj
 * @param selectedUserIds
 * @return
 */
@Override
public Map<Integer, List<PharmacyDetail>> getPharmacyOldHistory(
		List<Integer> selectedUserIds) {
	Map<Integer, List<PharmacyDetail>>pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
		criteria.add(Restrictions.in("patientId", selectedUserIds));
		criteria.add(Restrictions.ne("drugNameId", new Double(0)));
		criteria.addOrder(Order.asc("dateOfPurchase"));
		List<PharmacyDetail>pharmacyHistoryList=criteria.list();
		
		for(PharmacyDetail pharmacyDetail:pharmacyHistoryList){
			logger.info("Drug "+pharmacyDetail.getDrugId() + " Drug Name "+pharmacyDetail.getDrugInfo() +" Date of Purchase "+pharmacyDetail.getDateOfPurchase());
		}
		
		if(pharmacyHistoryList!=null && pharmacyHistoryList.size()>0){
			pharmacyHistoryMap=getPharmacyOldMap(pharmacyHistoryList);
		}
		for(Map.Entry<Integer, List<PharmacyDetail>>entry:pharmacyHistoryMap.entrySet()){
			logger.info("Patient Id "+entry.getKey());
			List<PharmacyDetail>patientPharmacyList=entry.getValue();
			for(PharmacyDetail pharmacyDetail:patientPharmacyList){
				logger.info("Drug "+pharmacyDetail.getDrugId() + " Drug Name "+pharmacyDetail.getDrugInfo() +" Date of Purchase "+pharmacyDetail.getDateOfPurchase());
			}
		}
	}catch(HibernateException hfe){
		pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();
		hfe.printStackTrace();
	}catch(Exception e){
		pharmacyHistoryMap=new HashMap<Integer, List<PharmacyDetail>>();
		e.printStackTrace();
	}
	return pharmacyHistoryMap;
}


private Map<Integer, List<PharmacyDetail>> getPharmacyOldMap(
		List<PharmacyDetail> pharmacyHistoryList) {
	logger.info("Creating Pharmacy Map");
	Map<Integer,List<PharmacyDetail>>pharmacyMap=new HashMap<Integer, List<PharmacyDetail>>();
	for(PharmacyDetail pharmacyDetail:pharmacyHistoryList){
		int patientId=pharmacyDetail.getPatientId();
		if(pharmacyMap.containsKey(patientId)){
			List<PharmacyDetail>pharmacyList=pharmacyMap.get(patientId);
		//	boolean isLatestDrugExists=getLatestPharmacyRecord(pharmacyDetail.getDrugId(),pharmacyDetail.getDrugNameId(),pharmacyList);
		//	if(!isLatestDrugExists){
			pharmacyList.add(pharmacyDetail);
			pharmacyMap.put(patientId, pharmacyList);
		//}
		}else{
			List<PharmacyDetail>pharmacyList=new ArrayList<PharmacyDetail>();
			pharmacyList.add(pharmacyDetail);
			pharmacyMap.put(patientId, pharmacyList);
		}
	}
	return pharmacyMap;
}


@Override
public void saveInteractionAnalytics(
		List<BatchInteractionAnalytic> interactionAnalytics) {
	try{
		for(int counter=0;counter<interactionAnalytics.size();counter++){
			getSessionFactory().getCurrentSession().save(interactionAnalytics.get(counter));
			/*if(counter%50==0){
				getSessionFactory().getCurrentSession().flush();
				getSessionFactory().getCurrentSession().clear();
			}*/
		}
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}


/**
 * Get Interaction Analytics History Data based on ProviderId and PatientId   
 * @param patientId
 * @param providerId
 * @return List of Batch Interaction Analytics Data 
 * 
 */
public List<BatchInteractionAnalytic> getInteractionHistoryData(int patientId,
		int providerId) {
	List<BatchInteractionAnalytic>interactionHistories=new ArrayList<BatchInteractionAnalytic>();
	try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(BatchInteractionAnalytic.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.addOrder(Order.desc("batchPullDate"));
		interactionHistories=criteria.list();
		
		for(BatchInteractionAnalytic batchInteractionAnalytic:interactionHistories){
			Hibernate.initialize(batchInteractionAnalytic.getBatchPatientMedsHistories());
			logger.info("Batch Patient med histories size "+batchInteractionAnalytic.getBatchPatientMedsHistories().size());
			for(BatchPatientMedsHistory batchPatientMedsHistory:batchInteractionAnalytic.getBatchPatientMedsHistories()){
				logger.info("Drug Id "+batchPatientMedsHistory.getDrugId()+" Drug Name Id "+batchPatientMedsHistory.getDrugNameId()+" drug Name "+batchPatientMedsHistory.getDrugName());
				Hibernate.initialize(batchPatientMedsHistory.getDrugInteractions());// Initilize drugInteraction data 
				Hibernate.initialize(batchPatientMedsHistory.getDiseaseInteractions());// Initilize Disease interaction data 
				Hibernate.initialize(batchPatientMedsHistory.getAllergyInteractions()); // Initilize Allergy Interaction data 
				logger.info("Drug Interaction size "+batchPatientMedsHistory.getDrugInteractions().size()+" \n "+batchPatientMedsHistory.getDiseaseInteractions().size()+" \n "+batchPatientMedsHistory.getAllergyInteractions().size());
			}
		}
		
	}catch(HibernateException hfe){
		hfe.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	
	return interactionHistories;
}



/*@Override
public List<ChildMedActionPlanParameter> getPsychopharmDefaultLabParameters(
		String psychopharmMapView, String genderOfPatient) {
	try{
		
	}catch(HibernateException he){
		he.printStackTrace();
	}
	return null;
}*/



/******@author SAURABH
 * ****TO FIND OLD MEDICATION DATA
 * @see com.clinakos.data.dao.IUserDao#getOldMedicationDataList(int)
 */
@Override
public List<PharmacyDetail> getOldMedicationDataList(int patientId,
		List<PatientMedicationData> patientMedicationDataList) {
	logger.info("::::oldMedicationData::::one year before date= "+ new DateUtil().AddDate(-365));
	Date dateBeforeOneYear=new DateUtil().AddDate(-365);
	List<PharmacyDetail> pOldMed=new ArrayList<PharmacyDetail>();
	String hql="FROM  PharmacyDetail pd where  pd.patientId= :patientId and pd.dateOfPurchase<= :dateBeforeOneYear group by pd.drugId";
	Query query=getSessionFactory().getCurrentSession().createQuery(hql);
	query.setParameter("patientId", patientId);
	query.setParameter("dateBeforeOneYear", dateBeforeOneYear);
	pOldMed=query.list();
	return pOldMed;
}


@Override
public List<PatientProvider> getPatientList(int providerId) {
	List<PatientProvider>patientList = new ArrayList<PatientProvider>();
	try{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientProvider.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		criteria.addOrder(Order.asc("patientId"));
	//	criteria.setMaxResults(500);
		patientList = criteria.list();
	}catch(HibernateException e){
		e.printStackTrace();
	}
	return patientList;
}


}		
	
	



