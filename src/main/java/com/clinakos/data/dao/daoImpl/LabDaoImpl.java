package com.clinakos.data.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.persistence.CascadeType;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.annotations.Cascade;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.dao.ILabDAO;
import com.clinakos.data.model.patient.EmdeonLabOrders;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.LabOrderCommonSegment;
import com.clinakos.data.model.patient.LabOrderObservationDetail;
import com.clinakos.data.model.patient.LabOrderObservationRequestDetail;
import com.clinakos.data.model.patient.LabResultDetailClinakos;
import com.clinakos.data.model.patient.LoincKeyMaster;
import com.clinakos.data.model.patient.MedActionPlan;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.ORUPatientLabResult;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientMedicationHistory;
import com.clinakos.data.model.patient.PharmacogenomicsRecomendations;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.SigCode;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class LabDaoImpl extends BaseDaoImpl implements ILabDAO {

	public static final Logger logger = Logger.getLogger("LabDaoImpl.class");
	/*
	 * Getting the value of Latest Procedure Result Data (non-Javadoc)
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#showProcedureResultData()
	 */
	
	public List<ProcedureResultHistory> showProcedureResultData() {
		// TODO Auto-generated method stub
		logger.info("showProcedureResultData method:::");
		List<ProcedureResultHistory> procedureResults = new ArrayList<ProcedureResultHistory>();
		List<ProcedureResultHistory> procedureResultsListAfterAddingSqlList = new ArrayList<ProcedureResultHistory>();
		String space=" ";
		int patientId=new ContextUtil().getPatientId();
		try {
			String sql="select t1.procedure_result_history_id,t1.Procedure_type_id,t1.lab_type,t1.user_id,t1.result, t1.provider_id,t1.doctor_id,"+
						" t1.comments,t1.result_status,t1.date_ordered,t1.laboratory_name,t1.date_result as date_result "+
						" from procedure_result_history t1 inner join (SELECT Procedure_type_id,lab_type,user_id,max(date_result) as date_result"+
						" FROM procedure_result_history where user_id="+patientId+" group by lab_type) t2 on t1.date_result = t2.date_result"+ 
						" and t1.lab_type=t2.lab_type and t1.user_id=t2.user_id";
			SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(sql);
			
			sqlQuery.addEntity(ProcedureResultHistory.class);
			List p = sqlQuery.list();
			Iterator it = p.iterator();
			 while(it.hasNext()){
				 ProcedureResultHistory proc = (ProcedureResultHistory)it.next();
				 procedureResultsListAfterAddingSqlList.add(proc);
			 }
			 System.out.println(":::::::::::::size:::"+procedureResultsListAfterAddingSqlList.size());
			for (ProcedureResultHistory pr1 : procedureResultsListAfterAddingSqlList) {
				
				logger.info("showProcedureResultData:11:sql:::"+pr1.getId()+"::"+pr1.getProcedureType().getId()+"::::"+pr1.getPatientId()+":::"+pr1.getDateResult());
				Criteria doctorDetailCriteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				doctorDetailCriteria.add(Restrictions.eq("userId", pr1.getDoctorId()));
				UserLoginDetail userLoginDetail=(UserLoginDetail) doctorDetailCriteria.list().get(0);
				
				/*pr1.setDoctorName(pr1.getUserLoginDetail().getFirstName()+space+pr1.getUserLoginDetail().getMiddleName()+space+pr1.getUserLoginDetail().getLastName());*/ // Commented By Anjani
				pr1.setDoctorName(userLoginDetail.getFirstName()+space+userLoginDetail.getMiddleName()+space+userLoginDetail.getLastName());
				//pr1.setLabRange(pr1.getProcedureType().getRange());
				pr1.setLabUnit(pr1.getProcedureType().getUnits());
				pr1.setLabTypeId(pr1.getProcedureType().getId());
				procedureResults.add(pr1);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return procedureResults;
	}

	/*
	 * ***************** METHOD FOR GETTING VALUES FOR CHARTSSSSSSS
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#labResultValueForChart()
	 */

	public List<ProcedureResultHistory> labResultValueForChart(Date periodDate,
			String labName,int labId) {

		List<ProcedureResultHistory> procedureHistorylist = new ArrayList<ProcedureResultHistory>();
		logger.info("labResultValueForChart method start in Doctor DAO::::::"+periodDate+":::"+labId);
		try {
			
			if (labName.equalsIgnoreCase("lab")) {
				logger.info("method labResultValueForChart inside if block:::::::::labName="+labName+"::labId="+labId);
				Criteria criteria = getSessionFactory().getCurrentSession()
						.createCriteria(ProcedureResultHistory.class, "pro");
				//criteria.createAlias("pro.procedureOrder", "proOrder");
				criteria.createAlias("pro.procedureType", "procType");
				criteria.add(Restrictions.ge("pro.dateResult", periodDate));
				criteria.add(Restrictions.ne("pro.result", 0.0));
				criteria.add(Restrictions.eq("procType.id", labId));
				criteria.add(Restrictions.eq("pro.patientId",
						new ContextUtil().getPatientId()));
				criteria.add(Restrictions.eq("pro.providerId",
						new ContextUtil().getProviderId()));
				criteria.addOrder(Order.asc("pro.dateResult"));
				procedureHistorylist = criteria.list();
				logger.info("procedureHistorylist value in If condition"+procedureHistorylist.size());
			}
			else if (!(labName.equalsIgnoreCase("lab"))) {
				logger.info("method labResultValueForChart inside else if block:::::::::labName="+labName+"::labId="+labId);
				Criteria criteria = getSessionFactory().getCurrentSession()
						.createCriteria(ProcedureResultHistory.class, "pro");
				//criteria.createAlias("pro.procedureOrder", "proOrder");
				criteria.createAlias("pro.procedureType", "procType");
				criteria.add(Restrictions.ge("pro.dateResult", periodDate));
				criteria.add(Restrictions.ne("pro.result", 0.0));
				criteria.add(Restrictions.eq("pro.labName", labName));
				criteria.add(Restrictions.eq("pro.patientId",
						new ContextUtil().getPatientId()));
				criteria.add(Restrictions.eq("pro.providerId",
						new ContextUtil().getProviderId()));
				criteria.addOrder(Order.asc("pro.dateResult"));
				procedureHistorylist = criteria.list();
				logger.info("procedureHistorylist value in Else  If condition"+procedureHistorylist.size());
			}
		
		} catch (Exception e) {
			logger.error(
					"Exception in labResultValueForChart method DAO::::::", e);
			e.printStackTrace();
		}
		return procedureHistorylist;
	}

	/*
	 * Getting the value of Procedure Result History Data (non-Javadoc)
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#showProcedureResultHistoryData()
	 * Getting data of
	 */
	public List<ProcedureResultHistory> showProcedureResultHistoryData(
			String procudreType) {
		logger.info("showProcedureResultHistoryData in dao impl::::"+procudreType);
		List<ProcedureResultHistory> procedureResultHistories = new ArrayList<ProcedureResultHistory>();
		try {
			
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(ProcedureResultHistory.class, "pro");
			//criteria.createAlias("pro.procedureOrder", "proOrder");
			criteria.createAlias("pro.procedureType", "procType");
			criteria.add(Restrictions.eq("pro.labName", procudreType));
			criteria.add(Restrictions.eq("pro.patientId",
					new ContextUtil().getPatientId()));
			criteria.add(Restrictions.eq("pro.providerId",
					new ContextUtil().getProviderId()));
			criteria.addOrder(Order.asc("pro.dateResult"));
			procedureResultHistories = criteria.list();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return procedureResultHistories;
	}

	/*
	 * **************************************************METHOD TO LOAD
	 * LOINC_MASTER INTO DROUP DOWN OF LAB CHART
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#getloincMasterList()
	 */

	public List<LoincKeyMaster> getloincMasterList() {
		logger.info("getloincMasterList method start in Doctor DAO::::::");
		List<LoincKeyMaster> loincList = new ArrayList<LoincKeyMaster>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(LoincKeyMaster.class, "loinc");
			criteria.add(Restrictions.eq("loinc.patientId",
					new ContextUtil().getPatientId()));
			loincList = criteria.list();

		} catch (Exception e) {
			logger.error("Exception in getloincMasterList method DAO:::::::::", e);
			e.printStackTrace();
		}

		return loincList;
	}

	/*
	 * ***************************************************************METHOD TO
	 * FIND Y-AXIS MAX AND MIN VALUE IN LAB TEST CAHRT @ LABRESULT.XHTML
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#findlabTestMaxValue(int)
	 */

	public String findlabTestMaxValue(int chartTypeID) {
		logger.info("findlabTestMaxValue method start in Doctor DAO::::::");
		String labTestValue = null;
		Double labMaxValue;
		Double labMinValue;
		try {

			String sql = "select max(prh.result) from procedure_result_history prh "
					+ " right outer join procedure_type"
					+ " pt on prh.procedure_type_id=pt.procedure_type_id where pt.procedure_type_id='"
					+ chartTypeID + "'and prh.user_id="+new ContextUtil().getPatientId()+"";
			//System.out.println("test:::::saurabh::::"+sql);

			SQLQuery sqlQuery = getSessionFactory().getCurrentSession()
					.createSQLQuery(sql);
			//System.out.println("test:::::sqlQuery.uniqueResult() max value::::"+sqlQuery.uniqueResult());
			if (sqlQuery.uniqueResult()!=null) {
				 labMaxValue = (Double) sqlQuery.uniqueResult()
						+ (((Double) sqlQuery.uniqueResult() * 10) / 100);
			}
			
			else {
				 labMaxValue=0.00;
			}
			
			//System.out.println("labTestMaxValue?????????"+labMaxValue+"?????");

			String sqlMin = "select min(prh.result) from procedure_result_history prh "
					+ " right outer join procedure_type"
					+ " pt on prh.procedure_type_id=pt.procedure_type_id where pt.procedure_type_id='"
					+ chartTypeID + "'and prh.user_id="+new ContextUtil().getPatientId()+"";
			SQLQuery sqlQueryMin = getSessionFactory().getCurrentSession()
					.createSQLQuery(sqlMin);
			//System.out.println("test:::::sqlQueryMin.uniqueResult()..min value::::"+sqlQueryMin.uniqueResult());
			if (sqlQueryMin.uniqueResult()!=null) {
				 labMinValue = (Double) sqlQueryMin.uniqueResult()
						- (((Double) sqlQueryMin.uniqueResult() * 5) / 100);
				
			}
			else {
				
				 labMinValue=0.00;
			}
			//System.out.println("value of lab chart::::"+labMinValue+":::"+labMinValue);
			labTestValue = String.valueOf(Math.ceil(labMaxValue)).concat("-")
					.concat(String.valueOf(Math.floor(labMinValue)));
			
			//System.out.println("value of lab chart::::"+labTestValue);
		}

		catch (Exception e) {
			logger.error("Exception in findlabTestMaxValue method DAO:::::::::", e);
			e.printStackTrace();
		}

		return labTestValue;
	}

	public List<PharmacogenomicsUserSummary> findPharmacogenomicsUserSummaryList(
			int patientId) {
		// TODO Auto-generated method stub
       
		List<PharmacogenomicsUserSummary> pharmacogenomicsUserSummaryList = new ArrayList<PharmacogenomicsUserSummary>();
		List<PharmacogenomicsUserSummary> pharmacogenomicsUserSummaryListAfterTrim = new ArrayList<PharmacogenomicsUserSummary>();
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(PharmacogenomicsUserSummary.class);
		criteria.add(Restrictions.eq("userId", patientId));
		pharmacogenomicsUserSummaryList = criteria.list();
		for (PharmacogenomicsUserSummary pgx : pharmacogenomicsUserSummaryList) {
			logger.info("pgx detail bfr trim:::::::::"+pgx.getGeneSymbol().length()+":::::"+pgx.getPhenoType().length());
			pgx.setGeneSymbol(pgx.getGeneSymbol().trim());
			pgx.setPhenoType(pgx.getPhenoType().trim());
			logger.info("pgx detail after trim:::::::::"+pgx.getGeneSymbol().length()+":::::"+pgx.getPhenoType().length());
			pharmacogenomicsUserSummaryListAfterTrim.add(pgx);
			
		}
		return pharmacogenomicsUserSummaryListAfterTrim;
	}

	public List<ProcedureResultHistory> showProcedureResultDataForPatient() {

		List<ProcedureResultHistory> proListForPatient=new ArrayList<ProcedureResultHistory>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProcedureResultHistory.class,"proRes");
			//criteria.createAlias("proRes.procedureOrder", "proOrd");
			criteria.add(Restrictions.eq("proRes.patientId", new ContextUtil().getLoginId()));
			proListForPatient=criteria.list();  
			//for (ProcedureResult p : proListForPatient) {
				//System.out.println("value in lab result list ::::::"+p.getProcedureOrder().getProcedureType().getName());
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return proListForPatient;
	}

	
	public List<PharmacogenomicsUserSummary> findPharmacogenomicsSummaryBasedOnMedicine() {
		// TODO Auto-generated method stub
		List<PharmacogenomicsUserSummary> pharmcoSummaryBasedOnMedicineList=new ArrayList<PharmacogenomicsUserSummary>();
		try {
			
	int patientId=new ContextUtil().getPatientId();
			
	
	
	/*String sql_query=
	" SELECT pus.id, pus.user_id, gene_reco.drug_name, pus.gene_symbol, pus.geno_type, pus.pheno_type,gene_reco.geno_type, gene_reco.attention_rating " 
	+" FROM pharmacogenomics_user_summary pus "

	+" left outer  join"
	+" ( SELECT pmd.user_id, pc.gene_symbol, pc.geno_type, pr.* from pharmacogenomics_classification pc "
	 +" join pharmacogenomics_recommendations pr on pr.Pharmacogenomics_classfication_id = pc.id "
	+" inner join patient_medication_data pmd on pmd.drugs = pr.drug_name) gene_reco "
	+" on pus.gene_symbol = gene_reco.gene_symbol  and gene_reco.geno_type like concat ('%',pus.geno_type,'%') "  
	+" and gene_reco.user_id = pus.user_id "

	+" where pus.user_id= "+ patientId

	+" UNION "

	+ " SELECT pmd.user_id, pmd.drugs, pc.gene_symbol, pus.geno_type, pus.id, pus.pheno_type,pc.geno_type, pr.attention_rating FROM pharmacogenomics_recommendations pr "
	+" inner join patient_medication_data pmd  on pmd.drugs = pr.drug_name "
	+ " inner join pharmacogenomics_classification pc "
	+ " on pc.id = pr.Pharmacogenomics_classfication_id "
	+ " left outer join pharmacogenomics_user_summary pus "
	+ " on pus.gene_symbol=pc.gene_symbol "
	+ " where (pus.gene_symbol is NULL or pus.geno_type='' ) "
	+ " and pmd.user_id= " + patientId ;*/
						/* PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);*/
		                CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call getPharmecogenomicsSummaryBasedOnMedicine("+patientId+")}");
						 ResultSet resultSet=statement.executeQuery();
						 while(resultSet.next()){
							 PharmacogenomicsUserSummary phSummary=new PharmacogenomicsUserSummary();
							 phSummary.setGeneSymbol(resultSet.getString("gene_symbol"));
							 phSummary.setGenoType(resultSet.getString("geno_type"));
							 phSummary.setPhenoType(resultSet.getString("pheno_type"));
							 phSummary.setAttentionRating(resultSet.getString("attention_rating"));
							 phSummary.setDrugName(resultSet.getString("drug_name"));
						   
							 pharmcoSummaryBasedOnMedicineList.add(phSummary);
						 }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("pharmcoSummaryBasedOnMedicineList.size()"+pharmcoSummaryBasedOnMedicineList.size());
			return pharmcoSummaryBasedOnMedicineList;
		
	}
	
	/**
	 * Get the medication data according to patient medicine
	 * @return
	 */
	public List<PharmacogenomicsRecomendations> patientMedicationDataForPharmecogenomicIndicatior(){
		 List<PharmacogenomicsRecomendations> pharmcoRecomendList=new ArrayList<PharmacogenomicsRecomendations>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			                  criteria.add(Restrictions.eq("patientId", new ContextUtil().getPatientId()));
			 List<PatientMedicationData> patientMedicationDataList=criteria.list();
					 
			 List<String> medicineList=new ArrayList<String>();  
			 for(PatientMedicationData medData:patientMedicationDataList){
				 medicineList.add(medData.getDrugs());
			 }
			 Criteria pharmcoRecomdCriteria=getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsRecomendations.class);
			 pharmcoRecomdCriteria.add(Restrictions.in("drugName", medicineList));
			 pharmcoRecomendList=pharmcoRecomdCriteria.list();
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pharmcoRecomendList;
		
	}

	/**
	 * Get Pharmecogenomics attention ratiing on the basis of
	 * medicine name and user gene_symbol and genotype
	 */
	public PharmacogenomicsRecomendations findPharmecogenomicsValueForMedReview(String drugs,
			int patientId) {
		// TODO Auto-generated method stub
		String attentionRating=null;
		System.out.println("Method in dao impl For getting attention rating"+drugs);
		PharmacogenomicsRecomendations pRecomendations=new PharmacogenomicsRecomendations();
		try {
			
			/*String sql_query=
					" SELECT gene_reco.attention_rating "
					+" FROM pharmacogenomics_user_summary pus "
					+" left outer  join "
					+" (SELECT pmd.user_id, pc.gene_symbol,  pr.* from pharmacogenomics_classification pc "
					+" join pharmacogenomics_recommendations pr on pr.Pharmacogenomics_classfication_id = pc.id "
					+" inner join patient_medication_data pmd on pmd.drugs = pr.drug_name) gene_reco "
					+" on pus.gene_symbol = gene_reco.gene_symbol "  
					+" and gene_reco.user_id = pus.user_id "
				 +"	where pus.user_id= " +patientId  +" &&  gene_reco.drug_name=  '"+drugs+"' "
				+ "	UNION "
				+"	SELECT pr.attention_rating FROM pharmacogenomics_recommendations pr "
				+"	inner join patient_medication_data pmd  on pmd.drugs = pr.drug_name "
				+"	inner join pharmacogenomics_classification pc "
				+"	on pc.id = pr.Pharmacogenomics_classfication_id "
				+"	left outer join pharmacogenomics_user_summary pus "
				+"	on pus.gene_symbol=pc.gene_symbol "
				+"	where (pus.gene_symbol is NULL or pus.geno_type='' ) "
				+"	and pmd.user_id= " +patientId +" && pmd.drugs=  '" +drugs+"' " ;
			PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);*/
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call getAttentionRatingForParticularMedicine("+patientId+",'"+drugs+"')}");
			System.out.println("Query excution started "+drugs);
			
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				
				String recomendation=resultSet.getString("recommendation");
				System.out.println("recomendationnnnnnnnnnnnnnnnn"+recomendation);
				attentionRating=resultSet.getString("attention_rating");
				String ovaleMessage=resultSet.getString("implication_ovale_message");
				pRecomendations.setAttentionRating(attentionRating);
				pRecomendations.setRecommendation(recomendation);
				pRecomendations.setOvaleMessage(ovaleMessage);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pRecomendations;
	}

	/*
	 * ***********************Method to find Lab Reminders for patient
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#findRemindersForLab(int)
	 */
											
			public List<MedandGenricmed> findRemindersForLab(int loginId,Date nextDate) {
			
			  logger.info("findRemindersForLab called in LabDAO:::::::"+nextDate+"::"+new Date());
			  
			  List<MedandGenricmed> medPlanList=new ArrayList<MedandGenricmed>();
			  try {
				  String hql="from MedandGenricmed where patientId= :patientId and " +
						  		"id in (select MAX(id) from MedandGenricmed where patientId= :patientId group by lab )";
				  Query query=getSessionFactory().getCurrentSession().createQuery(hql);
				  query.setParameter("patientId", loginId);
				  medPlanList=query.list();
				  System.out.println();
			  } 
			  catch (Exception e) {
				  logger.error("exception in findRemindersForLab in LabDAO:::::", e);
				  e.printStackTrace();
			  }
			 return medPlanList;
		 }
 

			
			/*
			 * 
			 *  Save added new lab data 
			 * @param resultDate
			 * @param result
			 * @param labTypeId
			 * @param labType
			 * @param loincCode
			 * @see com.clinakos.data.dao.ILabDAO#saveLabDetailValue(com.clinakos.data.model.patient.ProcedureResult)
			 * @uthor Saurabh LI and MOdify By Gopal Krishna Jha
			 */
						public void saveLabDetailValue(Date resultDate,String result, int labTypeId,
										String labType, String loincCode)
						{
							logger.info("::::saveLabDetailValue in daoimpl::::"+"::labTypeId:::"+labTypeId+"::result::"+
											result+"::lab date:"+resultDate+"=====labType="+labType+
											"====loincCode="+loincCode);
							
						try {
							System.out.println("insert_lab_data_history_sql:::");
						//---------------------insert into 	procedure_result_history
							String insert_lab_data_history_sql="";
									//if(!(procedureResultHistory.getDateOrdered()==null)){
							/*insert_lab_data_history_sql="insert into procedure_result_history (procedure_type_id, result, provider_id, doctor_id, user_id,date_result, " +
									" lab_type,loinc_code) values ("+labTypeId+","+result+",'"+new ContextUtil().getProviderId()+"','"+new ContextUtil().getLoginId()+"', " +
											" '"+new ContextUtil().getPatientId()+"','"+new DateUtil().DateAndTimeInMySqlPattern(resultDate)+"','"+labType+"', " +
													"'"+loincCode+"')";*/
							
							insert_lab_data_history_sql="insert into procedure_result_history (procedure_type_id, result, provider_id, doctor_id, user_id,date_result, " +
									" lab_type,loinc_code) values (?,?,?,?,?,?,?,?)";
								//insert_lab_data_history_sql="insert into procedure_result_history (procedure_type_id, result, provider_id, doctor_id, user_id, date_result) values ("+labTypeId+","+procedureResultHistory.getResult()+",'"+new ContextUtil().getProviderId()+"','"+new ContextUtil().getLoginId()+"','"+new ContextUtil().getPatientId()+"','"+new DateUtil().DateAndTimeInMySqlPattern(procedureResultHistory.getDateResult())+"')";
							 
							Query sqlQuery4 = getSessionFactory().getCurrentSession().createSQLQuery(insert_lab_data_history_sql);
							sqlQuery4.setInteger(0, labTypeId);
							sqlQuery4.setString(1, result);
							sqlQuery4.setInteger(2, new ContextUtil().getProviderId());
							sqlQuery4.setInteger(3, new ContextUtil().getLoginId());
							sqlQuery4.setInteger(4, new ContextUtil().getPatientId());
							sqlQuery4.setTimestamp(5, resultDate);
							sqlQuery4.setString(6, labType);
							sqlQuery4.setString(7, loincCode);
							sqlQuery4.executeUpdate();
							logger.info("insert_lab_data_history_sql:::"+insert_lab_data_history_sql);
							if ("Weight".equalsIgnoreCase(labType)) {
								logger.info("===================inside if block of saveLabDetailValue LABDAO for updating weight============================");
								double weightInLb=Double.parseDouble(result);
								double weightInKg=(weightInLb/2.2046);
								logger.info("=============Value of weight in KG inside saveLabDetailValue==================="+weightInKg+"====weightInLb==="+weightInLb);
								Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("update PatientVital set weight = :weight" +
					    				" where patientIdForVital = :patientIdForVital");
									hqlQuery.setParameter("weight", weightInKg);
									hqlQuery.setParameter("patientIdForVital", new ContextUtil().getPatientId());
									int weightResult = hqlQuery.executeUpdate();
								logger.info("===================UPDATE OVER FOR PATIENT VITALS of saveLabDetailValue LABDAO============================");	
							}
						  } 
						catch (Exception e) {
							e.printStackTrace();
						  }	
						
						}
				
						
			/*
			 * Method to find all lab type
			 * @see com.clinakos.data.dao.ILabDAO#getLabTypeMaster()
			 * @uthor Saurabh : LI
			 */
						public List<ProcedureType> getLabTypeMaster() {

							logger.info("getLabTypeMaster method start in Doctor DAO::::::");
							List<ProcedureType> labType = new ArrayList<ProcedureType>();
							try {
								Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class);
								ProjectionList projectionList = Projections.projectionList();
								ProjectionList projectionList2 = Projections.projectionList();
								projectionList2.add(Projections.distinct(projectionList.add(Projections.property("labType"), "labType")));
								projectionList2.add(Projections.property("id"), "id");
								projectionList2.add(Projections.property("labType"), "labType");
								projectionList2.add(Projections.property("units"), "units");
								criteria.setProjection(projectionList2);
								criteria.setResultTransformer(Transformers.aliasToBean(ProcedureType.class)); 
								labType = criteria.list();
								for (ProcedureType lo : labType) {
									System.out.println("value of lo :name:::" +lo.getLabType()+":::id::"+lo.getId());
								}

							} catch (Exception e) {
								logger.error("Exception in getLabTypeMaster method DAO:::::::::", e);
								e.printStackTrace();
							}

							return labType;
							
						}

/*	
 * @param rowIdnumber
 * (non-Javadoc)
 * @see com.clinakos.data.dao.ILabDAO#deleteLabDetails(int)
 *     Method for delete the LabResult
 *     Author @sanket singh 
 *     modified by : saurabh
 * 
 */
	public  void deleteLabDetails(int rowIdnumber){
		logger.info("delete Lab history Details started:::::::::::::::::::::"+rowIdnumber);
		Query query =getSessionFactory().getCurrentSession().createQuery("delete ProcedureResultHistory where id=:id ");
		query.setInteger("id", rowIdnumber);
	    query.executeUpdate();
				
	}
					
/*	
 * Method for  edit the current labResult data
 * Author @sanket singh
 * modified by : saurabh
 * @param selectedCurrentLabIdForEdit current Lab Id of selected Lab Result 
 * @param selectedCurrentLabNameForEdit selected Current Lab name 
 * @see com.clinakos.data.dao.ILabDAO#editLabResult(int,double,String)
 * 
 */
						
  public void editLabResult(int selectedCurrentLabIdForEdit,double selectedCurrentLabResultForEdit,String selectedCurrentLabNameForEdit)
   {
	  int patientId=new ContextUtil().getPatientId();
		try{
		logger.info("inside editLabResult===in labdao======"+selectedCurrentLabResultForEdit+":::::::id::"+selectedCurrentLabIdForEdit+"::::labType:"+selectedCurrentLabNameForEdit);
		String update_Reference=("update ProcedureResultHistory set result = :result" +
							" where id = :id");
		Query	 hql = getSessionFactory().getCurrentSession().createQuery( update_Reference);
		hql.setParameter("result",selectedCurrentLabResultForEdit);
		hql.setParameter("id", selectedCurrentLabIdForEdit);
		hql.executeUpdate();
		logger.info("::::::::::::after updating result the value:::::::");
		//ProcedureResultHistory procedureResultHistoryObj=(ProcedureResultHistory) getSessionFactory().getCurrentSession().get(ProcedureResultHistory.class, procedureResultHistory.getId());
		//String labType=procedureResultHistoryObj.getProcedureType().getLabType();
		String alertTableQuery="update AlertGenericMedActionLab set labResultValue=:updatedValue where patientId=:patientID and status=:activeAttr and labType=:monitoringParamter";
		Query alertQuery=getSessionFactory().getCurrentSession().createQuery(alertTableQuery);
		alertQuery.setParameter("patientID", patientId);
		alertQuery.setParameter("updatedValue", selectedCurrentLabResultForEdit);
		alertQuery.setParameter("monitoringParamter", selectedCurrentLabNameForEdit);
		alertQuery.setParameter("activeAttr", true);
		int queryExcute=alertQuery.executeUpdate();
		logger.info("updating alerts med action after editing lab value "+queryExcute);
	if("Weight".equalsIgnoreCase(selectedCurrentLabNameForEdit)) { 
			logger.info("===================inside if block of editLabResult for updating weight============================");
			double weightInKg=(selectedCurrentLabResultForEdit/2.2046);
			logger.info("=============Value of weight in KG inside editLabResult==================="+weightInKg);
			Query hqlQuery = getSessionFactory().getCurrentSession().createQuery("update PatientVital set weight = :weight" +
				" where patientIdForVital = :patientIdForVital");
			hqlQuery.setParameter("weight", weightInKg);
			hqlQuery.setParameter("patientIdForVital", new ContextUtil().getPatientId());
			int weightResult = hqlQuery.executeUpdate();
		logger.info("===================UPDATE OVER FOR PATIENT VITALS INSIDE editLabResult============================");
	  }
	}catch(NullPointerException ne){
		ne.printStackTrace();
	}
	catch(HibernateException he){
		he.printStackTrace();
	}
			
 }

 /*	
  * Method for  edit the labResult data 
   * Author @sanket singh
   * 
   *
   * modified by : saurabh
   * @param rowIdOfLabHistory selected id of row history data 
   * @param labHistoryResult data of lab history result 
   * @see com.clinakos.data.dao.ILabDAO#editHistory(
   * 
  */
	public void editHistory(int rowIdOfLabHistory,double labHistoryResult)
	{
		
		int patientId=new ContextUtil().getPatientId();
		try{
		String update_History=("update ProcedureResultHistory set result = :result" +
								" where id = :id");
		Query hqlhistory=getSessionFactory().getCurrentSession().createQuery(update_History);
		hqlhistory.setParameter("id",rowIdOfLabHistory );
		hqlhistory.setParameter("result", labHistoryResult);
		logger.info(" ====value before updation the "+labHistoryResult+"id:::::::::"+rowIdOfLabHistory);
		hqlhistory.executeUpdate();
		
		ProcedureResultHistory procedureResultHistory=(ProcedureResultHistory) getSessionFactory().getCurrentSession().get(ProcedureResultHistory.class, rowIdOfLabHistory);
		String labType=procedureResultHistory.getProcedureType().getLabType();
		String alertTableQuery="update AlertGenericMedActionLab set labResultValue=:updatedValue where patientId=:patientID and status=:activeAttr and labType=:monitoringParamter";
		Query alertQuery=getSessionFactory().getCurrentSession().createQuery(alertTableQuery);
		alertQuery.setParameter("patientID", patientId);
		alertQuery.setParameter("updatedValue", procedureResultHistory.getResult());
		alertQuery.setParameter("monitoringParamter", labType);
		alertQuery.setParameter("activeAttr", true);
		int queryExcute=alertQuery.executeUpdate();
		logger.info("updating alerts med action after editing lab value "+queryExcute);
				
		}catch(NullPointerException ne){
			ne.printStackTrace();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
}

/*
 * ********method to show llab history on row expension
 * *****@uthor: saurabh
 *  */
 public List<ProcedureResultHistory> getprocedureResultHistoryListOnRowExpension(
		 String selectedLabName, int rowId) {

	 logger.info("getprocedureResultHistoryListOnRowExpension in dao impl::::"+rowId+"::"+rowId);
		List<ProcedureResultHistory> prhList = new ArrayList<ProcedureResultHistory>();
		try {
			
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(ProcedureResultHistory.class, "pro");
			//criteria.createAlias("pro.procedureOrder", "proOrder");
			criteria.createAlias("pro.procedureType", "procType");
			//criteria.add(Restrictions.lt("pro.dateOrdered", selectedLabDate));
			criteria.add(Restrictions.eq("pro.labName", selectedLabName));
			criteria.add(Restrictions.eq("pro.patientId",
					new ContextUtil().getPatientId()));
			criteria.add(Restrictions.eq("pro.providerId", new ContextUtil().getProviderId()));
			criteria.addOrder(Order.desc("pro.dateResult"));
			prhList = criteria.list();
		
		} catch (Exception e) {
			logger.error("EXCEPTION in getprocedureResultHistoryListOnRowExpension in dao impl::::", e);
			e.printStackTrace();
		}
		return prhList;
	
 }
 public List<ProcedureResultHistory> showProcedureHistoryData() {
		List<ProcedureResultHistory> procedureResultHistoryListForLineChart = new ArrayList<ProcedureResultHistory>();
		try {


	Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(ProcedureResultHistory.class);
			criteria.add(Restrictions.eq("patientId",new ContextUtil().getPatientId()));
			criteria.add(Restrictions.eq("providerId",new ContextUtil().getProviderId()));
			procedureResultHistoryListForLineChart = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return procedureResultHistoryListForLineChart;
	}

/*Fetching The Medicine From Medication Data for HighChart at Anticog Page.
 * 
 * Author @Sanket Singh
 * 
 * 
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
	}*/

 
 
/* find Doctor name according to doctor id::
 * Author @sanket Singh
 * 
 * 
 * */
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
	 * find lab detail value according to proc type id..
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.ILabDAO#findCurrentLabValue(int, int, int, int)
	 */
	public ProcedureResultHistory findCurrentLabValue(int patientId,
			int providerId, int loginId,  String labName) {
		logger.info(" findCurrentLabValue method start...");
		ProcedureResultHistory labDetail=new ProcedureResultHistory();
		String sql_query="SELECT * FROM procedure_result_history where user_id="+patientId+"  and lab_type='"+labName+"'  " +
				"and provider_id="+providerId+" order by date_result desc";
		SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(sql_query);
		
		sqlQuery.addEntity(ProcedureResultHistory.class);
		List<ProcedureResultHistory>procedureResultHistoryList=new ArrayList<ProcedureResultHistory>();
		procedureResultHistoryList = sqlQuery.list();
		if(!procedureResultHistoryList.isEmpty())
			labDetail=procedureResultHistoryList.get(0);
		
		return labDetail;
	}
	
	
	/**
	 * Save Lab Result Detail From New Crop Web Service to Clinakos System 
	 */
	public void saveLabResltDetailDataFromNewCrop(
			List<LabResultDetailClinakos> labDetailClinakosList) {
		  for(LabResultDetailClinakos labResultDetailClinakos:labDetailClinakosList){
			  getSessionFactory().getCurrentSession().save(labResultDetailClinakos);
		  }
	}

	/**
	 * Save PatientLabResult Data From NewCrop To Clinakos After decoding HL7 message  
	 * 
	 */
	public void saveOruPatientLabResultFromNewCropToClinakos(
			List<ORUPatientLabResult> wsPatientLabResultList,Date reportDate) {
		try {
			List<ORUPatientLabResult> oruPatientLabResultList=new ArrayList<ORUPatientLabResult>();
			
			
			 
			 
			 
			 
			 System.out.println("oruPatientLabResultList ::::::::"+oruPatientLabResultList.size());
			 Query query =getSessionFactory().getCurrentSession().createQuery("delete ORUPatientLabResult where requestReportDate=:requestReportDate");
				query.setDate("requestReportDate", reportDate);
			    query.executeUpdate();
			    for(ORUPatientLabResult oruPatientLabResult:wsPatientLabResultList){
			    	 getSessionFactory().getCurrentSession().save(oruPatientLabResult);
			    }
			   
			
			
			
			 
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 
		
	}
	
   /**
    * Get the value of all patient webservice lab result  
    */
	public List<ORUPatientLabResult> getAllWebServicesPatientLabResult() {
		System.out.println("Method started in dao impl for patient lab result ");
		List<ORUPatientLabResult> patientLabResults=new ArrayList<ORUPatientLabResult>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ORUPatientLabResult.class);
		patientLabResults=criteria.list();
		for(ORUPatientLabResult labResult:patientLabResults){
			System.out.println("Comment  :::::::::::::"+labResult.getComment()+":::::labResult.getAccessionNo()"+labResult.getAccessionNo());
		}
		
		
		return patientLabResults;
	}
	/*Using for high chart at General Med Action Plan
	 * 
	 * 
	 * */

	public List<GenericMedActionPlan> getAllGenericMedActionPlan(int id) {
		List<GenericMedActionPlan>allGenericMedActionPlans=new ArrayList<GenericMedActionPlan>();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedActionPlan.class);
			criteria.add(Restrictions.eq("patientId",new ContextUtil().getPatientId()));
			
		allGenericMedActionPlans=criteria.list();
		}catch(HibernateException he){
			he.printStackTrace();
		}
		logger.info("Generic Med action Plans size for  ID "+id+" ===>"+allGenericMedActionPlans.size()+"patient id==="+new ContextUtil().getPatientId());
		return allGenericMedActionPlans;
	}

	public List<GenericMedActionPlan> getAllGenericMedActionPlan1(int patientId) {
		List<GenericMedActionPlan>allGenericMedActionPlansforChar=new ArrayList<GenericMedActionPlan>();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(GenericMedActionPlan.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			allGenericMedActionPlansforChar=criteria.list();
			logger.info("size of list==="+allGenericMedActionPlansforChar.size());
		}catch(HibernateException he)
		{
			he.printStackTrace();
		}
		return allGenericMedActionPlansforChar;
	}

	
	
	

	/*create this Medthod For Displaying the Medicine List At DropDown the list for HighChart
	 * 
	 * @Author sanket Singh
	*/
	public List<MedandGenricmed> fetchGenericList(int patientId) {
		List<MedandGenricmed>medActionListForChart=new ArrayList<MedandGenricmed>();
		
		Date todayDate=new Date();
		//logger.info("value of date in dao :::::"+todayDate);
			 
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
			criteria.add(Restrictions.eq("patientId",new ContextUtil().getPatientId()));
		     criteria.setProjection(Projections.distinct(Projections.projectionList()
		  .add(Projections.property("medicinename"), "medicinename").add(Projections.property("drugNameId"), "drugNameId") ))
		.setResultTransformer(Transformers.aliasToBean(MedandGenricmed.class)); 
		
			medActionListForChart=criteria.list();
			
			logger.info("list size of medicine list:::"+medActionListForChart.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
			return medActionListForChart;
			
		}
/*
 * @Author: sanket
 * @see com.clinakos.data.dao.ILabDAO#getProcedureHistoryListForChart(int, int)
 * 
 * Fetch the value According to dateResult at MAP
 */
	public List<ProcedureResultHistory> getProcedureHistoryListForChart(
			String lab, int patientId,Date periodDate) {
		List<ProcedureResultHistory>prHistoryList=new ArrayList<ProcedureResultHistory>();
		Date todayDate=new Date();
		logger.info("insside getProcedureHistoryListForChart lab DAO==="+lab+"::::"+patientId+":::"+periodDate+"::"+todayDate);
		try{
			Criteria criteria = getSessionFactory().getCurrentSession()
			.createCriteria(ProcedureResultHistory.class, "pro");
			criteria.createAlias("pro.procedureType", "procType");
			criteria.add(Restrictions.ge("pro.dateResult", periodDate));
			criteria.add(Restrictions.eq("procType.labType", lab));
			criteria.add(Restrictions.eq("pro.patientId",patientId));
			criteria.addOrder(Order.asc("pro.dateResult"));
			
			prHistoryList = criteria.list();
			
			
			logger.info("prHistoryList size in dao:::"+prHistoryList.size());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	//	logger.info("getProcedureHistoryListForChart sanket list size==="+prHistoryList.size());
		return prHistoryList;
	}
       /**
        * It is used for get lab data based on patientId and lab value  
        * @param patientId
        * @param lab
        * @return List of MedandGenricmed 
        */
        public List<MedandGenricmed> fetchGenericListforLab(int patientId, String lab) {
        	
        	List<MedandGenricmed>medActionLabList=new ArrayList<MedandGenricmed>();
        	
        	
        	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MedandGenricmed.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.setProjection(Projections.distinct(Projections.projectionList().add(Projections.property("lab"), "lab")))
			.setResultTransformer(Transformers.aliasToBean(MedandGenricmed.class)); 
			medActionLabList=criteria.list();
			
			for (MedandGenricmed gmap : medActionLabList) {
				gmap.setLabUnit(getLabUnitForGenericMedaction(gmap.getLab()));
			}
	
	          return medActionLabList;
            }

	

    		private String getLabUnitForGenericMedaction(String lab) {
    				
    			 String labUnit="";
    			 String hql="select units FROM ProcedureType where labType= :labType";
    				Query query=getSessionFactory().getCurrentSession().createQuery(hql);
    				query.setParameter("labType", lab);
    				if(!query.list().isEmpty())
    				labUnit=(String) query.list().get(0);
    				System.out.println("labUnit:For:labchart:::"+labUnit);
    				
			return labUnit;
		}

			/* Methode for display value on Drug Dose Response Curve
    		 * 
    		 * @Author Sanket Singh
    		 * */

		public List<PatientMedicationHistory> fetchGenericListforDisplay(int patientId,
				String medicinename,Date periodDate) {
			logger.info("inside patientmedication history:::::");
			List<PatientMedicationHistory>medActionListforDisplay=new ArrayList<PatientMedicationHistory>();
			try{
    			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationHistory.class);
    			criteria.add(Restrictions.eq("patientId",new ContextUtil().getPatientId()));
    	         criteria.add(Restrictions.eq("drugs",medicinename));
    	        criteria.add(Restrictions.ge("startDate",periodDate));
    	        criteria.addOrder(Order.asc("startDate"));
    	        
    			medActionListforDisplay=criteria.list();
    			
    			logger.info("list of display for medicine in high at MAP:::"+medActionListforDisplay.size());
    		}
    		catch(Exception e){
    			e.printStackTrace();
    		}
    		
    			return medActionListforDisplay;
    			
    		}
/*******Method to search lab according to lab type/loinc code
 * @param searchLabOption
 * @param inputForLabSearch data for lab search 
 * (non-Javadoc)
 * @see com.clinakos.data.dao.ILabDAO#getProcedureTypeAferSearch(int, java.lang.String)
 */
		public List<ProcedureType> getProcedureTypeAferSearch(
				int searchLabOption, String inputForLabSearch) {
			logger.info("=========call searchLab inside labDAO IMPL::::searchLabOption="+searchLabOption+
						"::inputForLabSearch="+inputForLabSearch);
			List<ProcedureType> prList=new ArrayList<ProcedureType>();
			try {
				if (searchLabOption==1) {
					logger.info("=========inside elseIF block of getProcedureTypeAferSearch::::searchLabOption="+searchLabOption);
					Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class);
					criteria.add(Restrictions.like("labType", inputForLabSearch+"%"));  
					criteria.addOrder(Order.asc("rank"));
					prList=criteria.list();
					int listsize=prList.size();
					if(listsize==0)
					{
						logger.info("inside list size zero");
						Criteria criteria1=getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class);
						criteria1.add(Restrictions.like("longName", inputForLabSearch+"%"));  
						criteria1.addOrder(Order.asc("rank"));
						prList=criteria1.list();
						logger.info("inside list size"+prList.size());
					}
				}
				else if (searchLabOption==2) {
					logger.info("=========inside elseIF block of getProcedureTypeAferSearch::::searchLabOption="+searchLabOption);			
					Criteria criteria1=getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class);
					criteria1.add(Restrictions.like("loincCode", inputForLabSearch+"%"));
					criteria1.addOrder(Order.asc("rank"));
					prList=criteria1.list();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("===========EXCEPTION IN getProcedureTypeAferSearch INSIDE LAB DAO=====",e);
			}
			return prList;
		}
		
	public List<SigCode> fetchsigcodelistforgraph()
	{
		List<SigCode> sidcode=new ArrayList<SigCode>();
		sidcode= getSessionFactory().getCurrentSession().createCriteria(SigCode.class).list();
		return sidcode;
	}
	
	public List<PharmacyDetail> findPharmacyDetailHistoryListAccordingToParticularDrugId(
			double drugId, int patientId,Date periodDate) {
		List<PharmacyDetail>pharmacyDetailList=new ArrayList<PharmacyDetail>();
		logger.info("findPharmacyDetailHistoryListAccordingToParticularDrugId in dao::"+drugId);
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("drugNameId",drugId));
		criteria.add(Restrictions.ge("dateOfPurchase",periodDate));
		criteria.addOrder(Order.asc("dateOfPurchase"));
		pharmacyDetailList=criteria.list();
		return pharmacyDetailList;
	}
 public List<PatientMedicationData> fetchpatientmedicationlist(int patientId, String medicinename,Date periodDate)
 {
	 List<PatientMedicationData> patientmedicationdatalist=new ArrayList<PatientMedicationData>();
	 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.eq("patientId", patientId));
		criteria.add(Restrictions.eq("drugs",medicinename));
		 criteria.add(Restrictions.ge("startDate",periodDate));
	        criteria.addOrder(Order.asc("startDate"));
		patientmedicationdatalist=criteria.list();
		return patientmedicationdatalist;
 }

/**
 * Integrate Emedon Lab Data in Clinakos Procedure Result History Table 
 */
public void integrateEmedonLabDataInClinakos(Date reportDate) {
	try {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String requestReportDate=format.format(reportDate);
		CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_patient_lab_integration_with_emdeon('"+requestReportDate+"')}");
		ResultSet resultSet=statement.executeQuery();
	} catch (HibernateException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

 /**
  * Fetch  PsychoPharm Related Lab Data for PsychoPharm Page 
  */
 public List<ProcedureResultHistory> getPsychoPharmLabResultData(int patientId,String psychoPharmClinicName) {
	 List<ProcedureResultHistory> psychoPharmLabResultList=new ArrayList<ProcedureResultHistory>();
	 try {
		
	 /* String sqlQuery="select *  from " 
			 +" (select distinct prh.lab_type,prh.result,prh.date_result from "
					+"   procedure_result_history prh inner join (select distinct gmp.monitoring_parameter "
					+" from generic_med_action_plan gmp inner join medicine_details md "
					+" on md.medicine_name=gmp.drug_name where md.clinic_name='"+psychoPharmClinicName+"' and "
					+" gmp.patient_id="+patientId+" ) plt on "
					+" plt.monitoring_parameter=prh.lab_type  where prh.user_id="+patientId+" "
					+" order by prh.date_result desc) t1 group by t1.lab_type "; */
		 String sqlQuery="select *  from " 
				 +" (select distinct prh.lab_type,prh.result,prh.date_result from "
						+"   procedure_result_history prh inner join (select distinct pt.lab_type "
						+" from general_med_action_plan gmp inner join medicine_details md "
						+" on md.medicine_name=gmp.drugName "
						+ " inner join general_med_action_plan_parameters cmp on cmp.medActionPlanId=gmp.med_Action_PlanId "
						+ " inner join procedure_type pt on pt.procedure_type_id=cmp.procedure_type_id"
						+ " where LCASE(md.clinic_name)='"+psychoPharmClinicName+"' and "
						+" gmp.patientId="+patientId+" ) plt on "
						+" plt.lab_type=prh.lab_type  where prh.user_id="+patientId+" "
						+" order by prh.date_result desc) t1 group by t1.lab_type "; 
	  PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
	  ResultSet resultSet=statement.executeQuery();
	  while (resultSet.next()) {
		ProcedureResultHistory resultHistory=new ProcedureResultHistory();
		resultHistory.setLabName(resultSet.getString("lab_type"));
		resultHistory.setDateResult(resultSet.getDate("date_result"));
		resultHistory.setResult(resultSet.getDouble("result"));
		psychoPharmLabResultList.add(resultHistory);
	}
      System.out.println("psychoPharmLabResultList.size()"+psychoPharmLabResultList.size());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	                 
	return psychoPharmLabResultList;
}

/*
 * Get all lab result value for Hep C Clinic 
 * (non-Javadoc)
 * @see com.clinakos.data.dao.ILabDAO#getHepCLabResultData(int, java.lang.String)
 */
public List<ProcedureResultHistory> getHepCLabResultData(int patientId,
		String hepatitisCClinicName) {
	List<ProcedureResultHistory> hepCClinicLabResultList=new ArrayList<ProcedureResultHistory>();
	try{
	/* String sqlQuery="select *  from " 
			 +" (select distinct prh.lab_type,prh.result,prh.date_result from "
					+"   procedure_result_history prh inner join (select distinct gmp.monitoring_parameter "
					+" from generic_med_action_plan gmp inner join medicine_details md "
					+" on md.medicine_name=gmp.drug_name where md.clinic_name='"+hepatitisCClinicName+"' and "
					+" gmp.patient_id="+patientId+" ) plt on "
					+" plt.monitoring_parameter=prh.lab_type  where prh.user_id="+patientId+" "
					+" order by prh.date_result desc) t1 group by t1.lab_type "; */
		 String sqlQuery="select *  from " 
				 +" (select distinct prh.lab_type,prh.result,prh.date_result from "
						+"   procedure_result_history prh inner join (select distinct pt.lab_type "
						+" from general_med_action_plan gmp inner join medicine_details md "
						+" on md.medicine_name=gmp.drugName "
						+ " inner join general_med_action_plan_parameters cmp on cmp.medActionPlanId=gmp.med_Action_PlanId "
						+ " inner join procedure_type pt on pt.procedure_type_id=cmp.procedure_type_id"
						+ " where LCASE(md.clinic_name)='"+hepatitisCClinicName+"' and "
						+" gmp.patientId="+patientId+" ) plt on "
						+" plt.lab_type=prh.lab_type  where prh.user_id="+patientId+" "
						+" order by prh.date_result desc) t1 group by t1.lab_type "; 
	 
	    /*SQLQuery sqlQueryCriteria=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
	                              sqlQueryCriteria.addEntity(ProcedureResultHistory.class);
	                              
	    hepCClinicLabResultList=sqlQueryCriteria.list();*/
	  PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
	  ResultSet resultSet=statement.executeQuery();
	  while (resultSet.next()) {
		ProcedureResultHistory resultHistory=new ProcedureResultHistory();
		resultHistory.setLabName(resultSet.getString("lab_type"));
		resultHistory.setDateResult(resultSet.getDate("date_result"));
		resultHistory.setResult(resultSet.getDouble("result"));
		hepCClinicLabResultList.add(resultHistory);
	
		
	
	  }
	
	  System.out.println("hep C Lab Result Data List"+hepCClinicLabResultList.size());
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	                 
	return hepCClinicLabResultList;
}


public ORUPatientLabResult getEmedonLabResult(String medRecordId) {
	// TODO Auto-generated method stub
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ORUPatientLabResult.class);
	criteria.add(Restrictions.eq("patientId", medRecordId));
	ORUPatientLabResult patientLabResult=(ORUPatientLabResult) criteria.list().get(0);
	return patientLabResult;
}

@Override
public List<ProcedureType> getPsychopharmProcedureTypes(
		List<String> labParameters) {
	List<ProcedureType>psychopharmProcedureTypes=new ArrayList<ProcedureType>();
	logger.info("{--}{--}{--}Psychopharm PHQ-9 and Altman Lab :getting Procedure Type obj fired{--}{--}{--}");
	try{
		/*Query query=getSessionFactory().getCurrentSession().createQuery("from ProcedureType where labType in (:psychopharmLabParams)");
		query.setParameterList("psychopharmLabParams", labParameters);
		query.setResultTransformer(Transformers.aliasToBean(ProcedureType.class));
		psychopharmProcedureTypes=query.list();*/
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProcedureType.class);
		criteria.add(Restrictions.in("labType", labParameters));
		//criteria.setResultTransformer(Transformers.aliasToBean(ProcedureType.class));
		psychopharmProcedureTypes=criteria.list();
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	logger.info("psychopharmProcedureTypes List size =====> "+psychopharmProcedureTypes.size());
	return psychopharmProcedureTypes;
}


public void saveEmdeonLabOrderData(
		ArrayList<EmdeonLabOrders> emdeonLabOrdersList) {
	 try {
		for(EmdeonLabOrders labOrders:emdeonLabOrdersList){
			for(LabOrderCommonSegment commonSegment:labOrders.getLabOrderCommonSegmentList()){
				getSessionFactory().getCurrentSession().save(commonSegment);
			}
			for(LabOrderObservationRequestDetail observationRequestDetail:labOrders.getLabOrderObservationRequestDetailList()){
				getSessionFactory().getCurrentSession().save(observationRequestDetail);
			}
			for(LabOrderObservationDetail observationDetail:labOrders.getLabOrderObservationDetailList()){
				getSessionFactory().getCurrentSession().save(observationDetail);
			}
			for(PatientDiagnosesDetails diagnosesDetails:labOrders.getDiagnosesDetailList()){
				getSessionFactory().getCurrentSession().save(diagnosesDetails);
			}
		}
		 
	} catch (HibernateException he) {
		he.printStackTrace();
	}
	
}




	}


