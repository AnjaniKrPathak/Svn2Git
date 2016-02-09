package com.clinakos.data.dao.daoImpl;

import static com.clinakos.common.util.ClinakosConstant.DRUG_DISEASE_INTERACTION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_DRUG_INTERECTION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRADICATION;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_DISEASE;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_LEVEL_SEARCH_STR_RELATIVE;
import static com.clinakos.common.util.ClinakosConstant.DRUG_SEVERITY_TOOLTIP;
import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;
import static com.clinakos.common.util.ClinakosConstant.HIGH_SEVERITY_LEVEL;
import static com.clinakos.common.util.ClinakosConstant.MEDIUM_SEVERITY_LEVEL;
import static com.clinakos.common.util.ClinakosConstant.NORMAL_WEIGHT;
import static com.clinakos.common.util.ClinakosConstant.OBESE;
import static com.clinakos.common.util.ClinakosConstant.OVER_WEIGHT;
import static com.clinakos.common.util.ClinakosConstant.UNDERWEIGHT;
import static com.clinakos.common.util.ClinakosConstant.YES;
import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyExtendedDetailV4;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;





import org.apache.commons.lang.StringUtils;
import org.apache.cxf.service.invoker.SessionFactory;
import org.apache.cxf.ws.policy.attachment.wsdl11.Wsdl11AttachmentPolicyProvider;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.impl.CriteriaImpl.Subcriteria;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.dao.IUserDao;
import com.clinakos.data.model.core.AdminDetails;
import com.clinakos.data.model.core.AnticoagProviderLocation;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.BatchAllergyInteraction;
import com.clinakos.data.model.core.BatchDiseaseInteraction;
import com.clinakos.data.model.core.BatchDrugInteraction;
import com.clinakos.data.model.core.BatchInteractionAnalytic;
import com.clinakos.data.model.core.BatchPatientMedsHistory;
import com.clinakos.data.model.core.AuditPatientDeactivation;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.LoginSecurity;
import com.clinakos.data.model.core.PatientFormularyCompositeCopayInfo;
import com.clinakos.data.model.core.PatientFormularyCompositeDrugDetailInfo;
import com.clinakos.data.model.core.PatientPBMDrugHistoryResult;
import com.clinakos.data.model.core.PatientPBMEligibilityDetailData;
import com.clinakos.data.model.core.PatientPbmDrugHistoryDetail;
import com.clinakos.data.model.core.PracticeSearchResultSet;
import com.clinakos.data.model.core.ProviderDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.ProviderUser;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicDoctor;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.ClinicProvider;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.DrugInteractionOverview;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.PharmacogenomicsUserSummary;
import com.clinakos.data.model.patient.PharmacyDetail;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ReconcileInfo;
import com.clinakos.data.model.patient.SendMessageEditRx;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.WSDrug;
import com.clinakos.service.IPatientMedicineService;
import com.clinakos.service.serviceImpl.PatientMedicineServiceImpl;
import com.clinakos.viewController.bean.BatchInteractionBean;
import com.clinakos.viewController.bean.PatientMedicineManageBean;
import com.google.common.primitives.Ints;
import com.mysql.jdbc.CallableStatement;

import static com.clinakos.common.util.ClinakosConstant.*;



public class UserDaoImpl extends BaseDaoImpl implements IUserDao {
	public static final Logger logger = Logger.getLogger("UserDaoImpl.class");
	
	
	
	BatchInteractionBean batchInteractionBean=new BatchInteractionBean();
	

	/*
	 * find the company name details>>>>
	 * (non-Javadoc) 
	 * @see com.clinakos.core.dao.IUserDao#insuranceCompanies(com.clinakos.core.model.InsuranceCompanies)
	 */
	public List<InsuranceCompanies> insuranceCompanies() {
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class);
		//criteria.setMaxResults(10);
		
		List<InsuranceCompanies> insuranceCompanieList=criteria.list();
		return insuranceCompanieList;
	
	}

	/*
	 * find medicine formulary according to insurance company name and medicine name 
	 *Author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#MediciationDetails(int, java.lang.String)
	 */

	public List<FormularyDetail> findFormularySearchDetail(int insuranceId, String medicineName) {
		List<FormularyDetail>formularyDetailList=new ArrayList<FormularyDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(FormularyDetail.class);
		criteria.add(Restrictions.eq("insuranceId", insuranceId));
		criteria.add(Restrictions.eq("medicineName", medicineName));
		formularyDetailList=criteria.list();
		return formularyDetailList;
	}

	/*
	 * user entry save in databse i,e when user login, logout, any update by user 
	 * code developed by Gopal Krishan jha from LI
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#saveAudiValue(int, java.lang.String, javax.servlet.http.HttpServletRequest)
	 */
	
	public void saveAuditValue(AuditInfo auditInfo) {
		logger.info("saveAudiValue method in dao........"+auditInfo.getComment());
		try {
			save(auditInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			
		}


/*
 * find loged user id by gopal krishna jha from LI
 * (non-Javadoc)
 * @see com.clinakos.core.dao.IUserDao#findUserId(java.lang.String, java.lang.String)
 */
	
	public LoginSecurity findUserId(String username,String password) {
		logger.info("findUserId method start in UserDao impl"+username);
		//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		List<LoginSecurity>userDetailList= new ArrayList<LoginSecurity>();
		LoginSecurity userDetail=new LoginSecurity();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
		int userId=0,role=0;
		criteria.add(Restrictions.eq("userId",username));
		/*criteria.setProjection( Projections.projectionList().
				add( Projections.property("id") ).
				add( Projections.property("role") ));*/
		userDetailList=criteria.list();
		for (LoginSecurity loginSecurity : userDetailList) {
			//userId=loginSecurity.getId();
			//role=loginSecurity.getRole();
			userDetail=loginSecurity;
		}
	/*if (role==3) {
			System.out.println("hi i m patient:::::::"+userId);
			//HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			HttpSession session = request.getSession();
			session.setAttribute("patientId",userId);*/
		//}
		return userDetail;
	}

	/*
	 * find clinic and provider id  of particular Doctor who is log in...
	 * @author: Gopal Krishna JHA
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findClinicAndProviderId(int)
	 */
	public List<Integer> findClinicAndProviderId(int loginId) {
		logger.info("findClinicAndProviderId method in userdao imple start::::"+loginId);
		List<Integer> clinicList=new ArrayList<Integer>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicDoctor.class);
		
				criteria.setProjection(Projections.projectionList().
				         add(Projections.property("clinicProviderID") ));
		                 criteria.add(Restrictions.eq("doctorId",loginId));
		         List<Integer>  clinicProviderList = criteria.list();
		 int clinicProviderId =0;
		 System.out.println("lict size:::::"+clinicProviderList.size());
		 if(clinicProviderList.size()!=0)
		 {
			 System.out.println("akos2013r:::::::"+clinicProviderList.size());
		 clinicProviderId=(Integer)criteria.uniqueResult();
		clinicList.add(clinicProviderId);
		ClinicProvider clinicProvider=new ClinicProvider();
		 clinicProvider=(ClinicProvider) getSessionFactory().getCurrentSession().get(ClinicProvider.class, clinicProviderId);
		
		clinicList.add(clinicProvider.getClinicId());
		clinicList.add(clinicProvider.getProviderId());
		 }
		 logger.info("method end findClinicAndProviderId");
		return clinicList;
	}

	/*
	 * find total number of patient of particular clinic
	 * @author Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findnumberOfPatientParticularProviser(java.lang.Integer)
	 */
	
	public List<Integer> findnumberOfPatientParticularProvider(Integer providerId) {
		logger.info("findnumberOfPatientParticularProvider in dao impl::::::::::"+providerId);
		List<PatientProviderClinic>patientProviderClinicList=new ArrayList<PatientProviderClinic>();
		List<Integer>clinicProviderIDList=new ArrayList<Integer>();
		//clinicProviderIDList=getSessionFactory().getCurrentSession().createCriteria(persistentClass);
		
		//List<ClinicProvider>clinicProviderLIst=
				clinicProviderIDList=	getSessionFactory().getCurrentSession().createCriteria(PatientProvider.class).
				setProjection( Projections.projectionList().
						add( Projections.property("patientId")) ).
				add(Restrictions.eq("providerId", providerId)).list();
		
		
		/*criteria.setProjection( Projections.projectionList().
				add( Projections.property("id") ).
				add( Projections.property("role") ))*/
		
		//List<Integer> clinicProviderIdList=new ArrayList<Integer>();
		/*for(ClinicProvider clinicprovider:clinicProviderLIst)
			{
			clinicProviderIDList.add(clinicprovider.getId());
			}
		*/
		System.out.println("clinicProviderIDList::::::::"+clinicProviderIDList);
		/*if(!(clinicProviderIDList.isEmpty()))
				{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientProviderClinic.class)
					.add(Restrictions.in("clinicProviderId", clinicProviderIDList));
			patientProviderClinicList=criteria.list();
					
				}*/
		return clinicProviderIDList;
	}
	
   /*method for showing anticoag clinic status start added by umesh*/
	// Maintenance for low
	public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinic(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		//System.out.println("hello umesh providerid is"+providerId);
		/*List<AnticoagProviderLocation>anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		anticoagpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
				setProjection( Projections.projectionList().
						add(Projections.distinct(Projections.property("patientid")) ) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", MAINTAINENCE_STAGE)).add(Restrictions.eq("procedureType",2)).
						add(Restrictions.ge("result","2")).add(Restrictions.le("result","3")).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		//medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", MAINTAINENCE_STAGE))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","2"))
				.add(Restrictions.le("aplObj.result","3"));
		
		anticoagpatientlist=criteria.list();*/
		
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	// Maintenance for medium
	public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatient(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		//System.out.println("hello umesh providerid is"+providerId);
		/*List<AnticoagProviderLocation>anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		anticoagpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
				setProjection( Projections.projectionList().
						add(Projections.distinct(Projections.property("patientid")) ) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "MAINTAINENCE_STAGE")).add(Restrictions.eq("procedureType",2)).
						add(Restrictions.ge("result","3")).add(Restrictions.le("result","4.5")).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		//medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", MAINTAINENCE_STAGE))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","3"))
				.add(Restrictions.le("aplObj.result","4.5"));
		
		anticoagpatientlist=criteria.list();*/
		
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	
	
	// Maintenance for high
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplan(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{	
		
		/*List<AnticoagProviderLocation>medactionpatientlist=new ArrayList<AnticoagProviderLocation>();
		medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).setProjection( Projections.projectionList().
				add(Projections.distinct(Projections.property("patientid")) ) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", MAINTAINENCE_STAGE)).add(Restrictions.eq("procedureType",2)).
				add(Restrictions.gt("result","0")).add(Restrictions.le("result","2")).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		//medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", MAINTAINENCE_STAGE))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","0"))
				.add(Restrictions.le("aplObj.result","2"));
		
		medactionpatientlist=criteria.list();*/
		
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrange(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{	
		
		/*List<AnticoagProviderLocation>medactionpatientforgreaterrangelist=new ArrayList<AnticoagProviderLocation>();
		medactionpatientforgreaterrangelist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
				setProjection( Projections.projectionList().
						add(Projections.distinct(Projections.property("patientid")) ) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", MAINTAINENCE_STAGE)).add(Restrictions.eq("procedureType",2)).
				add(Restrictions.gt("result","4.5")).list();*/
		
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	
	public List<Integer> findnumberofpatientinrlab(Integer providerId)
	{	
		
		List<Integer>numberofpatientforinrlab=new ArrayList<Integer>();
		numberofpatientforinrlab=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).setProjection( Projections.projectionList().
				add(Projections.distinct(Projections.property("patientid")) ) ).
				
				
						add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", MAINTAINENCE_STAGE)).add(Restrictions.eq("procedureType",2)).list();
		
		return numberofpatientforinrlab;
		
	}
	
	
	public List<AnticoagProviderLocation> findnumberofpatientinrlabforintiationphase(Integer providerId)
	{	
		
		List<AnticoagProviderLocation>numberofpatientforinrlab=new ArrayList<AnticoagProviderLocation>();
		numberofpatientforinrlab=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).setProjection(Projections.projectionList().add( Projections.distinct(Projections.property("patientid")) ) ).
				add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "Initiation")).add(Restrictions.eq("procedureType",2)).list();
		
		return numberofpatientforinrlab;
		
	}
	
	
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforgraterrangeintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result)
	{	
		logger.info("findnumberofpatientformedactionplanforgraterrangeintiationphase::::::::::method calling");
		/*List<AnticoagProviderLocation>medactionpatientforgreaterrangelist=new ArrayList<AnticoagProviderLocation>();
		medactionpatientforgreaterrangelist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
				setProjection( Projections.projectionList().
						add(Projections.distinct(Projections.property("patientid"))) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "Initiation")).add(Restrictions.eq("procedureType",2)).
				add(Restrictions.gt("result","4.5")).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		//medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", "Initiation"))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","4.5"));
				
		
		medactionpatientforgreaterrangelist=criteria.list();*/
		
		List<AnticoagProviderLocation>medactionpatientforgreaterrangelist=new ArrayList<AnticoagProviderLocation>();
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	// Initiation list for High
	public List<AnticoagProviderLocation> findnumberofpatientformedactionplanforintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{	
		logger.info("findnumberofpatientformedactionplanforintiationphase:method calling");
		/*List<AnticoagProviderLocation>medactionpatientlist=new ArrayList<AnticoagProviderLocation>();
		List<AnticoagProviderLocation>medactionpatientlistNew=new ArrayList<AnticoagProviderLocation>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		//medactionpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", "Initiation"))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","0"))
				.add(Restrictions.le("aplObj.result","2"));//.list();
		
		medactionpatientlist=criteria.list();*/
		List<AnticoagProviderLocation>medactionpatientlist=new ArrayList<AnticoagProviderLocation>();
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +", '"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	// Initiation list for low
	public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		//System.out.println("hello umesh providerid is"+providerId);
		/*List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		anticoagpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class)).
				setProjection( Projections.projectionList().
						add( Projections.distinct(Projections.property("patientid"))) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "Initiation")).add(Restrictions.eq("procedureType",2)).
						add(Restrictions.gt("result","2")).add(Restrictions.le("result","3")).
						
						list();*/
		logger.info("{}{}{}{}findnumberofpatientforanticoagclinicforintiationphase{}{}{}{}");
		/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
						.add(Restrictions.eq("aplObj.medicinestage", medicinestage))
						.add(Restrictions.eq("aplObj.procedureType",procedureType))
						.add(Restrictions.ge("aplObj.result",start_result))
						.add(Restrictions.le("aplObj.result",end_result));
		criteria.setProjection(Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
						.add(Projections.property("aplObj.middle_name"),"middle_name")
						.add(Projections.property("aplObj.last_name"),"last_name")
						//.add(Projections.property("aplObj.user_id"),"user_id")
						.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
						.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));		
						
		anticoagpatientlist=criteria.list();*/
		
	
		
		/*List<AnticoagProviderLocation> anticoagProviderLocationList = new ArrayList<AnticoagProviderLocation>();
		anticoagProviderLocationList.addAll(anticoagpatientlist);*/
		
		/*for(AnticoagProviderLocation apl : anticoagpatientlist)
		{
			System.out.println("******************* "+apl.getFirst_name()+" "+apl.getMiddle_name()+" "+apl.getLast_name());
		}*/
		
		List<AnticoagProviderLocation>medactionpatientlist=new ArrayList<AnticoagProviderLocation>();
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +", '"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}
	
	
	/*public List<AnticoagProviderLocation> numberofpatientforanticoagforintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result)
	{
		//System.out.println("hello umesh providerid is"+providerId);
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		anticoagpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class)).
				setProjection( Projections.projectionList().
						add( Projections.distinct(Projections.property("patientid"))).add(Projections.property("first_name"))
						.add(Projections.property("middle_name"))
						.add(Projections.property("last_name")) ).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "Initiation")).add(Restrictions.eq("procedureType",2)).
						add(Restrictions.ge("result","2")).add(Restrictions.le("result","3")).
						
						list();
		logger.info("{}{}{}{}findnumberofpatientforanticoagclinicforintiationphase{}{}{}{}");
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
						.add(Restrictions.eq("aplObj.medicinestage", medicinestage))
						.add(Restrictions.eq("aplObj.procedureType",procedureType))
						.add(Restrictions.gt("aplObj.result",start_result))
						.add(Restrictions.le("aplObj.result",end_result));
		criteria.setProjection(Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
						.add(Projections.property("aplObj.middle_name"),"middle_name")
						.add(Projections.property("aplObj.last_name"),"last_name")
						//.add(Projections.property("aplObj.user_id"),"user_id")
						.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
						.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));		
						
		anticoagpatientlist=criteria.list();
		
	
		
		List<AnticoagProviderLocation> anticoagProviderLocationList = new ArrayList<AnticoagProviderLocation>();
		anticoagProviderLocationList.addAll(anticoagpatientlist);
		
		for(AnticoagProviderLocation apl : anticoagpatientlist)
		{
			System.out.println("anticoagpatientlist+++++++++++++ "+anticoagpatientlist.size());
			System.out.println("******************* "+apl.getFirst_name()+" "+apl.getMiddle_name()+" "+apl.getLast_name());
		}
		
		return anticoagpatientlist;
		
	}*/
	
	public List<UserLoginDetail> numberofpatientforanticoagforintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result)
	{
		
		List<UserLoginDetail> anticoagpatientlist=new ArrayList<UserLoginDetail>();
		
		 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
		logger.info("{}{}{}{}findnumberofpatientforanticoagclinicforintiationphase{}{}{}{}");		
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 UserLoginDetail fc = new UserLoginDetail();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in fetchPatientByFormulary:: ", e);
			e.printStackTrace();
		}
		
		
		return anticoagpatientlist;
		
	}
	
	// Initiation list for Medium
	public List<AnticoagProviderLocation> findnumberofpatientforanticoagclinicforhigerendofpatientforintiation(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String statusID)
	{
		//System.out.println("hello umesh providerid is"+providerId);
		/*List<AnticoagProviderLocation>anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		anticoagpatientlist=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class).
				setProjection( Projections.projectionList().	
						add(Projections.distinct(Projections.property("patientid"))).add(Projections.property("patientid")).add(Projections.property("first_name"))
						.add(Projections.property("middle_name"))
						.add(Projections.property("last_name"))
						.add(Projections.property("date_of_birth"))).add(Restrictions.eq("providerId", providerId)).add(Restrictions.eq("medicinestage", "Initiation")).add(Restrictions.eq("procedureType",2)).
						add(Restrictions.ge("result","3")).add(Restrictions.le("result","4.5")).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AnticoagProviderLocation.class,"aplObj");
		criteria.setProjection( Projections.projectionList()
				.add( Projections.distinct(Projections.property("aplObj.patientid")))
				.add(Projections.property("aplObj.patientid"),"patientid")
				.add(Projections.property("aplObj.first_name"),"first_name")
				.add(Projections.property("aplObj.middle_name"),"middle_name")
				.add(Projections.property("aplObj.last_name"),"last_name")
				.add(Projections.property("aplObj.date_of_birth"),"date_of_birth"))
				.setResultTransformer(Transformers.aliasToBean(AnticoagProviderLocation.class));
		criteria.add(Restrictions.eq("aplObj.providerId", providerId))
				.add(Restrictions.eq("aplObj.medicinestage", "Initiation"))
				.add(Restrictions.eq("aplObj.procedureType",2))
				.add(Restrictions.gt("aplObj.result","3"))
				.add(Restrictions.le("aplObj.result","4.5"));//.list();
		
		anticoagpatientlist=criteria.list();*/
		
		/*List<AnticoagProviderLocation> anticoagProviderLocationList = new ArrayList<AnticoagProviderLocation>();
		anticoagProviderLocationList.add((AnticoagProviderLocation) anticoagpatientlist);*/
		/*for(AnticoagProviderLocation apl : anticoagpatientlist)
		{
			//System.out.println("******************* "+apl.getFirst_name()+" "+apl.getMiddle_name()+" "+apl.getLast_name());
			System.out.println("anticoagpatientlist size in dao "+anticoagpatientlist.size()+" "+apl.getPatientid());
		}*/
		
		List<AnticoagProviderLocation>medactionpatientlist=new ArrayList<AnticoagProviderLocation>();
		List<AnticoagProviderLocation> anticoagpatientlist=new ArrayList<AnticoagProviderLocation>();
		
		 List<AnticoagProviderLocation> resultList = new ArrayList<AnticoagProviderLocation>() ;
		
		try
		{
			
			/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
			
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +", '"+statusID+"','"+DecryptionKey+"')}");
																											
			 ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 AnticoagProviderLocation fc = new AnticoagProviderLocation();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				/* fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));*/
				 fc.setPatientid(Integer.parseInt(patientId));
				 /*fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 fc.setResult(resultSet.getDouble("result"));*/
				/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
				 resultList.add(fc);
				
			 }
			 anticoagpatientlist.addAll(resultList);
			/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
		}
		catch (Exception e) {
			logger.error("exception in findnumberofpatientformedactionplanforintiationphase:: ", e);
			e.printStackTrace();
		}
		
		return anticoagpatientlist;
		
	}

	/*
	 * find Insurance Graph Detail according to there primary insurance
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findInsuranceGraphDetail(int, java.util.List, java.util.List)
	 */
	
	public List<Object>  findInsuranceGraphDetail(int clinicProviderId,List<Integer> patientProviderClinicList,List<InsuranceCompanies> insuranceCompanyList) {
		//ChartSeries insuranceChartdetail = new ChartSeries(); 
		//CartesianChartModel chart=new CartesianChartModel(); 

		logger.info("findInsuranceGraphDetail method start in user dao impl:::"+clinicProviderId);
		List<Object> userInsuranceDetailsList=new ArrayList<Object>();
		//List<Integer> patientId=new ArrayList<Integer>();
		/*for(PatientProviderClinic ppClinicId:patientProviderClinicList)
			{
			patientId.add(ppClinicId.getPatientId());
			logger.info("patient id:::"+ppClinicId.getPatientId());
			}*/
		
		//System.out.println(":::::::::::::::::::size ::::"+patientId.size()+":::"+patientId.isEmpty());
		
		if(!(patientProviderClinicList.isEmpty() )|| patientProviderClinicList.size()>=1)
			{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
			criteria.add(Restrictions.eq("primaryInsurance", "yes"));
			criteria.add(Restrictions.isNotNull("insuranceId"));
			
			
				logger.info("::::::::patientProviderClinicList::::::"+patientProviderClinicList+"size:::::::"+patientProviderClinicList.size());
			criteria.add(Restrictions.in("userId", patientProviderClinicList));
			
			
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("insuranceId"))
					.add(Projections.countDistinct("userId").as("patientCount"))
					.add(Projections.groupProperty("insuranceId")));
			criteria.addOrder(Order.desc("patientCount"));
		
		
			
			userInsuranceDetailsList = criteria.list();
			
			for(Object user:userInsuranceDetailsList)
			{
				  Object[] row = (Object[]) user;
				  System.out.println("<<<<<<<<<<<<<<,:::::"+row[0]+":::insurance id::"+row[1]);
				  
			}
			
			}
		return userInsuranceDetailsList;
	}


	/*find virtual clinic pie chart according to particular patient 
	 * @auhor gopal krishna jha from lumbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findClinicPieChart(int)
	 */

	public List<Object>  findClinicPieChart(int clinicProviderId) {
		logger.info("findClinicPieChart method start in user dao impl:::"+clinicProviderId);
		//PieChartModel pieModel = new PieChartModel(); 
		List<ClinicProvider>clinicProviderLIst=getSessionFactory().getCurrentSession().createCriteria(ClinicProvider.class).
				add(Restrictions.eq("providerId", clinicProviderId)).list();
		List<Integer> clinicProviderIdList=new ArrayList<Integer>();
		for(ClinicProvider clinicprovider:clinicProviderLIst)
			{
			clinicProviderIdList.add(clinicprovider.getClinicId());
			
			System.out.println("value in list1:::::::::::::"+clinicprovider.getClinicId());
			}
		System.out.println("value in list:::::::::::::"+clinicProviderIdList.size());
		//List<ClinicMaster>clinicmasterList=getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class).list();
		
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientProviderClinic.class);
	
		criteria.add(Restrictions.in("clinicProviderId", clinicProviderIdList));
				
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("clinicProviderId"))
				.add(Projections.count("patientId"))
				.add(Projections.groupProperty("clinicProviderId")));
		  /*String select ="SELECT provider_id, clinic_id, count(patient_id)  FROM clinic_patient_provider pp inner join clinic_provider cp on cp.id = pp.clinic_provider_id group by clinic_id, provider_id";
          Query query = getSessionFactory().getCurrentSession().createQuery(select);*/
		
		
		List<Object> patientProviderClinicsList = criteria.list();
		//System.out.println("size of list:::::"+patientProviderClinicsList.size());
		return patientProviderClinicsList;
	}

	/*
	 * find clinic name according to clinic id ..
	 * @author : gopal krishna jha
	 */

	private String findClinicNameAccordingToId(
			List<ClinicMaster> clinicmasterList, int clinicId) {
		String clinicName="";
		for(ClinicMaster clinicmaster:clinicmasterList)
		{
			if(clinicmaster.getId()==clinicId)
				clinicName=clinicmaster.getClinicName();
		}
		return clinicName;
	}

	/*
	 * find average medicine per patient according to particular provider....
	 * @author : Gopal Krishna Jha from lumbini...
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findaverageMedicinePerPatient(int)
	 */
	
	public int findaverageMedicinePerPatient(
			List<Integer> numberOfPatientParticularProviser) {
		
		int avgmedicine=0;
		try{
		List<PatientMedicationData> patientMedicineDataList=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class).
				add(Restrictions.in("patientId", numberOfPatientParticularProviser)).list();
		if(numberOfPatientParticularProviser.size()>0){
		avgmedicine=patientMedicineDataList.size()/numberOfPatientParticularProviser.size();
		}
		}catch(ArithmeticException ae){
			avgmedicine=0;
			ae.printStackTrace();
			
		}catch(Exception e){
			avgmedicine=0;
			e.printStackTrace();
		}
		logger.info("Average medicine per patient "+avgmedicine);
		return avgmedicine;
	}

	/*
	 * find average diagnoses per patient belongs to particular provider id
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findaverageDiagnoses(int, int)
	 */

	public int findaverageDiagnoses(List<Integer> numberOfPatientParticularProvider
			) {
		
		int avgDiagnosis=0;
		logger.info("findaverageDiagnoses inside user dao impl:::"+numberOfPatientParticularProvider);
		/*List<Integer> patientId=new ArrayList<Integer>();
		for(PatientProviderClinic clinicId:numberOfPatientParticularProvider)
			{
				patientId.add(clinicId.getPatientId());
			}*/
		try{
		Query query=getSessionFactory().getCurrentSession().createQuery("from  PatientDiagnosesDetails  where patientId in (:patientId) ");
		query.setParameterList("patientId", numberOfPatientParticularProvider);
		
		//Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientDiagnosesDetails.class);
		//criteria.add(Restrictions.in("patientId", numberOfPatientParticularProvider));
		//List<PatientDiagnosesDetails> patientMedicineDataList=criteria.list();
		List<PatientDiagnosesDetails> patientMedicineDataList=query.list();
		if(numberOfPatientParticularProvider.size()>0){
		avgDiagnosis=patientMedicineDataList.size()/numberOfPatientParticularProvider.size();
		}
		}catch(ArithmeticException ae){
			avgDiagnosis=0;
			ae.printStackTrace();
		}catch(Exception e){
			avgDiagnosis=0;
			e.printStackTrace();
		}
		logger.info("Average Diagnosis "+avgDiagnosis);
		return avgDiagnosis;
	}

	/*
	 * for finding average physicians per patients which  belongs to particular provider id
	 * @author: Gopal Krishna Jha from lUmbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findaveragPhysicians(int, int)
	 */
	
	public int findaveragPhysicians(int toalNoOfPatient, int providerId) {
		int avgPhysicians = 0;
		try{
		List<ClinicProvider>clinicProviderLIst=getSessionFactory().getCurrentSession().createCriteria(ClinicProvider.class).
				add(Restrictions.eq("providerId", providerId)).list();
		
		List<Integer> clinicProviderIdList=new ArrayList<Integer>();
		for(ClinicProvider clinicprovider:clinicProviderLIst)
			{
					clinicProviderIdList.add(clinicprovider.getProviderId());
			}
		
		List<Integer>totalNoOfPatient= new ArrayList<Integer>();
				Criteria patientcriteria = getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
				patientcriteria.add(Restrictions.in("providerId", clinicProviderIdList));
				patientcriteria.setProjection(Projections.distinct(Projections.property("patientId")));
				totalNoOfPatient = patientcriteria.list();
				logger.info("patientList::::::::size "+totalNoOfPatient.size());
				//logger.info("patientList:::::::: "+totalNoOfPatient.get(0));
				
	//	List<CareTeam>doctorList= new ArrayList<CareTeam>();
				List doctorList=new ArrayList<Object>();
				Criteria doctorcriteria = getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
		        doctorcriteria.add(Restrictions.in("providerId", clinicProviderIdList));
		        doctorcriteria.setProjection(Projections.projectionList()
		        		.add(Projections.countDistinct("doctorId"))
		        		.add(Projections.groupProperty("patientId")));
		      // doctorcriteria.setResultTransformer(Transformers.aliasToBean(CareTeam.class));
		       doctorList =doctorcriteria.list();
		       Long totalDoctorCountAccPatient = (long) 0;
		    
		       for(Object obj:doctorList){
		    	   Object[] row = (Object[]) obj; 
		    	  // sum = sum +(int)row[0];
		    	   totalDoctorCountAccPatient =totalDoctorCountAccPatient+(Long) row[0];
		    	/*   logger.info("values of object to int::: "+((Integer)row[0]).intValue());*/
		    	   System.out.println("Total doctor according to Patient::::: "+totalDoctorCountAccPatient);
		       }
		logger.info("totalDoctorCountAccPatient::::::: "+totalDoctorCountAccPatient);
	//	Test 
		if(totalNoOfPatient.size()>0){
	     avgPhysicians=Integer.valueOf(String.valueOf(totalDoctorCountAccPatient/totalNoOfPatient.size()));
		}
		}catch(HibernateException he){
			avgPhysicians=0;
			he.printStackTrace();
		}catch(ArithmeticException ae){
			avgPhysicians=0;
			ae.printStackTrace();
		}catch(Exception e){
			avgPhysicians=0;
			e.printStackTrace();
		}
		logger.info("Average physicians :::::::: "+avgPhysicians);
		return avgPhysicians;
	}

	/*
	 * find Pharmacogenomics percentage of particular provider 
	 * @author : Gopal Krishna Jha from Lumbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findPharmacogenomicsPercentage(java.util.List)
	 */
	


	public int findPharmacogenomicsPercentage(List<Integer> numberOfPatientParticularProvider) {
		/*List<Integer>patientIdList=new ArrayList<Integer>();
		
		for(PatientProviderClinic patProviderClinic:numberOfPatientParticularProvider)
		{
			
			patientIdList.add(patProviderClinic.getPatientId());
		}*/
		try{
			Long noOfUser=	(Long) getSessionFactory().getCurrentSession().createCriteria(PharmacogenomicsUserSummary.class).
										setProjection(Projections.countDistinct("userId")).
				add(Restrictions.in("userId", numberOfPatientParticularProvider)).uniqueResult();
		
		return Integer.valueOf(String.valueOf((noOfUser*100)/numberOfPatientParticularProvider.size()));
		}catch(ArithmeticException ae){
			
			ae.printStackTrace();
			return 0;
			
		}catch(HibernateException he){
			he.printStackTrace();
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

/*
 * find average pharmacy per patient using no of patient of particular provider and they r using drugs that belongs to which pharmacy..
 * @author: Gopal Krishna jha
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findAveragePharmacyPerPatient(java.util.List)
 */

	public int findAveragePharmacyPerPatient(List<Integer> numberOfPatientParticularProvider) {
		
			/*List<Integer>patientIdList=new ArrayList<Integer>();
		
		for(PatientProviderClinic patProviderClinic:numberOfPatientParticularProvider)
		{
			patientIdList.add(patProviderClinic.getPatientId());
		}*/
		try{
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		criteria.add(Restrictions.in("patientId", numberOfPatientParticularProvider));
		
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("pharmacyName"))
				.add(Projections.count("pharmacyName"))
				.add(Projections.groupProperty("pharmacyName"))
				.add(Projections.groupProperty("patientId")));
		
		Long noOfpharmcay=(long) 0;
		List<Object> patientmedicationList = criteria.list();
		for(Object obj: patientmedicationList){
		  Object[] row = (Object[]) obj;
		
		  noOfpharmcay=noOfpharmcay+(Long)row[1];
		 }
		return Math.round(Integer.valueOf(String.valueOf(noOfpharmcay))/numberOfPatientParticularProvider.size());
		}catch(ArithmeticException ae){
			ae.printStackTrace();
			return 0;
		}catch(HibernateException he){
			he.printStackTrace();
			return 0;
			
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/*
	 * find all compliance detail for pie chart
	 * @author Ajay rana
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findAllComplianceDetails()
	 */
	public List<Object> findAllComplianceDetails() {
		logger.info("findAllComplianceDetails method start in userDAOImpl::::::::::::");
	 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
		  criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
		  criteria.add(Restrictions.gt("compliancePercentage", 0));
		  
		  criteria.setProjection(Projections.projectionList()
		    //.add(Projections.count("patientId"))
		    .add(Projections.avg("compliancePercentage"))
		    .add(Projections.groupProperty("patientId")));
		  
		  
		  List<Object> patientmedicationList = criteria.list();
		  return  patientmedicationList;
	}

	/*
	 * find Patient medication list according to provider id to find the no of medicine belongs to different compliance symbol...
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findPatientMedicationDataListAccordingToProvider(int)
	 */
	public List<BigInteger> findPatientMedicationDataListAccordingToProvider(int providerId) {
		List<BigInteger> ComplianceCountList = new ArrayList<BigInteger>();
		//List<BigInteger> ComplianceCountList1 = new ArrayList<BigInteger>();
		
		// fetching where compliance percentage is 60 from PatientMedicationData
			try{
		String sql = "select count(*) as count from (select user_id, avg(compliance_percentage) as Compliance from patient_medication_data where provider_id=:providerId group by user_id) " +
				"t1 where t1.Compliance > 0 and t1.Compliance < 60";
		SQLQuery query = getSessionFactory().getCurrentSession().createSQLQuery(sql);
		query.setInteger("providerId", providerId);
		logger.info("Compliance 60 from query "+query.list().get(0));
		List<BigInteger> patMedList60 = query.list();
		System.out.println("patientmedicationList:::::::::size:::::60 "+patMedList60.get(0));
		ComplianceCountList.addAll(patMedList60);
		
		// fetching where compliance percentage is 60-80 from PatientMedicationData
		
		
		String sql2 = "select count(*) as count from (select user_id, avg(compliance_percentage) as Compliance from patient_medication_data where provider_id=:providerId group by user_id) " +
				"t1 where t1.Compliance > 60  and t1.Compliance<80";
		Query query2 = getSessionFactory().getCurrentSession().createSQLQuery(sql2);
		query2.setInteger("providerId", providerId);
		List<BigInteger> patMedList80 = query2.list();
			System.out.println("patientmedicationList:::::::::size:::::80 "+patMedList80.get(0));
			ComplianceCountList.addAll(patMedList80);
			
			
		//fetching where fetching where compliance percentage is >80 from PatientMedicationData
			
			String sql3 = "select count(*) as count  from (select user_id, avg(compliance_percentage) as Compliance from patient_medication_data where provider_id=:providerId group by user_id) " +
					"t1 where t1.Compliance > 80 ";
			Query query3 = getSessionFactory().getCurrentSession().createSQLQuery(sql3);
			query3.setInteger("providerId", providerId);
			 List<BigInteger> patMedList100 = query3.list();
			System.out.println("patientmedicationList:::::::::size:::::100 "+patMedList100.get(0));
			ComplianceCountList.addAll(patMedList100);
			}
			catch(Exception e){
				e.printStackTrace();
				logger.error("error in findPatientMedicationDataListAccordingToProvider method::: "+e);
			}
		return  ComplianceCountList;
		//return getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class).add(Restrictions.eq("providerId", providerId)).list();
	}
	/*
	 * find clinic list 
	 * @author:Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findClinicMasterList()
	 */
	public List<ClinicMaster> findClinicMasterList() {
		List<ClinicMaster>clinicmasterList=getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class).list();
		return clinicmasterList;
	}
	/*
	 *find user insurance id ....
	 *@author: Gopal Krishna jha.. 
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findUserInsuranceID(int)
	 * MODIFIED BY SAURABH FOR CATCHING EXCEPTION WHEN THE QUERY RETURN NULL
	 */
	
	public int findUserInsuranceID(int userId) {
		logger.info("findUserInsuranceID in userdao impl:::");
		int userInsuranceId=0;
		try {
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
			criteria.add(Restrictions.eq("userId", userId));
			criteria.add(Restrictions.eq("primaryInsurance", YES));
			criteria.setProjection( Projections.projectionList().
					add( Projections.property("insuranceId") ));
			
				userInsuranceId=(Integer) criteria.uniqueResult();
		}catch(NullPointerException nfe){
			logger.error(":::::NULLPOINTER EXCEPTION IN findUserInsuranceID IN USER DAOIMPL:::::", nfe);
			userInsuranceId=0;
			nfe.printStackTrace();
		
		}
		catch (HibernateException e) {
			e.printStackTrace();
			logger.error(":::::EXCEPTION IN findUserInsuranceID IN USER DAOIMPL:::::", e);
		}
		
		return userInsuranceId;
	}
	/*
	 * find user login detail ..
	 * @author: Gopal Krishna JHa
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findUserLoginDetail(int)
	 */
	public UserLoginDetail findUserLoginDetail(int id) {
		logger.info("findUserLoginDetail method in user dao start:::"+id);
		return (UserLoginDetail) getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class)
				.add(Restrictions.eq("userId", id)).list().get(0);
	}

	/**
	 * Find Drug Id on the basis of Drug Name
	 * For Formulary Coverage
	 */
	public Double getDrugIdByDrugName(String medicineForFormularySearch) {
		// TODO Auto-generated method stub
		//System.out.println();
		logger.info("getDrugIdByDrugName method start in dao"+"medicineForFormularySearch:::::::::::::"+medicineForFormularySearch);
		WSDrug wsDrug=new WSDrug();
		Double drugId=0.0;
		Criteria wsDrugCriteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		wsDrugCriteria.add(Restrictions.eq("drugName", medicineForFormularySearch));
		List<WSDrug> wsDrugList=wsDrugCriteria.list();
		if(!(wsDrugList.size()==0)){
		 wsDrug=(WSDrug) wsDrugList.get(0);
		 drugId=wsDrug.getDrugId();
		}
		logger.info("getDrugIdByDrugName endddddddddd"+"drug Iddddddddddddd"+drugId);
		return drugId;
	}

	/**
	 * Find Doctor Detail Data on the basis of login Id
	 * *********modified by saurabh 
	 * *********for new crop data pull by admin
	 */
	public DoctorDetail findDoctorDetailData() {
		Integer docLoginId=new ContextUtil().getLoginId();
		System.out.println("Login Id " +docLoginId);
		DoctorDetail doctorDetail=new DoctorDetail();
		List<DoctorDetail> docDetailList=new ArrayList<DoctorDetail>();
		List<UserLoginDetail> userDetailList=new ArrayList<UserLoginDetail>();
		List<AdminDetails> adminDetailList=new ArrayList<AdminDetails>();
		if (new ContextUtil().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AdminDetails.class);
			criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
			criteria.add(Restrictions.eq("providerLocationId", new ContextUtil().getProviderLocationId()));
			adminDetailList=criteria.list();
			if(!(adminDetailList.size()==0)){
				// doctorDetail=adminDetailList.get(0);
				for(AdminDetails admin:adminDetailList){
					doctorDetail.setUpin(admin.getUpin());
					doctorDetail.setSpeciality(admin.getSpeciality());
					doctorDetail.setNpi(admin.getNpi());
					doctorDetail.setDocLicenseState(admin.getDocLicenseState());
					doctorDetail.setDocLicenseNumber(admin.getDocLicenseNumber());
					doctorDetail.setDegree(admin.getDegree());
					doctorDetail.setDea(admin.getDea());
					doctorDetail.setCity(admin.getCity());
					doctorDetail.setfName(admin.getfName());
					doctorDetail.setMidName(admin.getMidName());
					doctorDetail.setlName(admin.getlName());
					doctorDetail.setState(admin.getState());
					doctorDetail.setCountry(admin.getState());
					doctorDetail.setStreet(admin.getCountry());
					doctorDetail.setPhoneNumber(admin.getPhoneNumber());
					doctorDetail.setPincode(admin.getPincode());
					doctorDetail.setDoorNo(admin.getDoorNo());
					doctorDetail.setEmailId(admin.getEmailId());
					doctorDetail.setUserId(admin.getUserId());
		         }
 		      }
			for (AdminDetails d : adminDetailList) {
				System.out.println(":::findDoctorDetailData::::"+d.getId()+d.getDegree());
			}
		}
		else {
			Criteria usrLoginCriteria= getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			usrLoginCriteria.add(Restrictions.eq("userId", docLoginId));
			userDetailList=usrLoginCriteria.list();
			Criteria docCriteria=getSessionFactory().getCurrentSession().createCriteria(DoctorDetail.class);
			docCriteria.add(Restrictions.eq("userId", new ContextUtil().getLoginId()));
			docDetailList=docCriteria.list();
			if(!(docDetailList.size()==0)){
				 doctorDetail=docDetailList.get(0);
				for(UserLoginDetail userDetail:userDetailList){
					doctorDetail.setCity(userDetail.getCity());
					doctorDetail.setfName(userDetail.getFirstName());
					doctorDetail.setlName(userDetail.getLastName());
					doctorDetail.setState(userDetail.getState());
					doctorDetail.setCountry(userDetail.getState());
					doctorDetail.setStreet(userDetail.getStreet());
					doctorDetail.setPhoneNumber(userDetail.getPhoneNumber());
					doctorDetail.setPincode(userDetail.getPincode());
					doctorDetail.setMidName(userDetail.getMiddleName());
					doctorDetail.setDoorNo(userDetail.getDoorNo());
					doctorDetail.setEmailId(userDetail.getEmail());
					doctorDetail.setUserId(userDetail.getUserId());
		         }
  		      }
		  }
		return doctorDetail;
	}

	/**
	 * Find Patient detail data on the basis of Patient Id
	 */
	public UserLoginDetail findPatientDetailData() {
		UserLoginDetail patientDetailData=new UserLoginDetail();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			criteria.add(Restrictions.eq("userId", new ContextUtil().getPatientId()));
			 patientDetailData=(UserLoginDetail) criteria.list().get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return patientDetailData;
	}

	/**
	 * All Drug Id on the basis of Selected multiple Formulary drug 
	 */
	
	public ArrayList<String> getAllDrugIdBySelectedFormularyDrug(
			FormularyDetail[] selectedFormularyDrug) {
		// TODO Auto-generated method stub
		Criteria criteria=null;
		List<WSDrug> drugList=new ArrayList<WSDrug>();
		ArrayList<String> drugIdList=new ArrayList<String>();
	    Double drugId=0.0;
		for(FormularyDetail formuDetail:selectedFormularyDrug){
			String drug=formuDetail.getMedicineName();
			criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
			criteria.add(Restrictions.eq("drugName", drug));
		}
		drugList=criteria.list();
		for(WSDrug wsdrug:drugList){
			System.out.println("Drug id in user dao impal ........"+wsdrug.getDrugId());
			drugId=wsdrug.getDrugId();
			Integer drugIdValue=(int) drugId.doubleValue();
			String drugConcept=Integer.toString(drugIdValue);
			drugIdList.add(drugConcept);
			
		}
		
		return drugIdList;
	}

   /**
    * Drug Dose combination for for formulary search 
    *  medicine 
    */
	public List<WSDrug> findAllDrugDoseComboForFormularyMedicine(
			String medicineForFormularySearch) {
		// TODO Auto-generated method stub
		List<WSDrug> wsDrugList=new ArrayList<WSDrug>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		/*criteria.add(Restrictions.eq("drugName", medicineForFormularySearch));*/
		criteria.add(Restrictions.like("drugName", medicineForFormularySearch, MatchMode.START));
		wsDrugList=criteria.list();
		
		/*for(WSDrug wsDrug:wsDrugList){
			System.out.println("Drug Name "+wsDrug.getDrugName() +"Drug Name Id"+wsDrug.getDrugNameId());
		}*/
		return wsDrugList;
		
	}

    /**
     * Get Insurance Detail For Formulary Search 
     */
   public List<InsuranceCompanies> getInsuranceDetail(
		String insuranceCompanyForFormularySearch) {
	// TODO Auto-generated method stub
	   System.out.println("In user dao ::::::::::::::::;;"+insuranceCompanyForFormularySearch);
	   List<InsuranceCompanies> insuranceCompanieList=new ArrayList<InsuranceCompanies>();
	   Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class);
	   criteria.add(Restrictions.like("companyName",insuranceCompanyForFormularySearch, MatchMode.START));
	   insuranceCompanieList=criteria.list();
	   if(insuranceCompanieList.size()==0){
		   insuranceCompanieList=new ArrayList<InsuranceCompanies>();
	   }
	  return insuranceCompanieList;
   }

	/*
	 * find provider location detail where dotorl login..
	 * @author:Gopal Krishna Jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#findProviderLocationDetail(int)
	 */
	public ProviderLocation findProviderLocationDetail(int providerId) {
		//logger.info("findProviderLocationDetail method start in user daoimp::::"+providerId+"::::"+new ContextUtil().getClinicProviderId());
		ProviderLocation providerLocation=new ProviderLocation();
		if (new ContextUtil().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
			providerLocation=(ProviderLocation) getSessionFactory().getCurrentSession().get(ProviderLocation.class, new ContextUtil().getProviderLocationId());
		}
		else {
			ClinicProvider clinicProvider=new ClinicProvider();
			clinicProvider=(ClinicProvider) getSessionFactory().getCurrentSession().get(ClinicProvider.class, new ContextUtil().getClinicProviderId());
			System.out.println(":::::::::::::"+clinicProvider.getProviderLocationId());

			providerLocation=(ProviderLocation) getSessionFactory().getCurrentSession().get(ProviderLocation.class, clinicProvider.getProviderLocationId());
		}
		/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProviderLocation.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		List<ProviderLocation>ProviderLocation=new ArrayList<ProviderLocation>();
		ProviderLocation=criteria.list();*/
		//for()
		//providerLocation=(ProviderLocation) criteria.uniqueResult();
		//*
		//providerLocation=ProviderLocation.get(0);
		System.out.println("::::::::::::::::::::::::"+providerLocation.getLocation());

		return providerLocation;
	}


	public RoleSecurity findRoleDetails(int loginId) {
		logger.info("findRoleDetails::::::::::"+loginId);
		LoginSecurity loginSecurity=new LoginSecurity();
		RoleSecurity roleSecurity = new RoleSecurity();
		loginSecurity=(LoginSecurity) getSessionFactory().getCurrentSession().load(LoginSecurity.class, loginId);
		
		System.out.println("loginSecurity.getRole():::::"+loginSecurity.getRole());
		//RoleSecurity roleSecurity=new 
		roleSecurity=(RoleSecurity) getSessionFactory().getCurrentSession().load(RoleSecurity.class, loginSecurity.getRole());
		System.out.println("::::::"+roleSecurity.getNewCropRole());
		return roleSecurity;
	}

	
	public Double getDrugByDrug(String medicineForFormulary) {
		// TODO Auto-generated method stub
		WSDrug wsDrug=new WSDrug();
		Double drugId=0.0;
		Criteria wsDrugCriteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		wsDrugCriteria.add(Restrictions.eq("drugDetail", medicineForFormulary));
		List<WSDrug> wsDrugList=wsDrugCriteria.list();
		if(!(wsDrugList.size()==0)){
		 wsDrug=(WSDrug) wsDrugList.get(0);
		 drugId=wsDrug.getDrugId();
		}
		logger.info("getDrugIdByDrug endddddddddd"+"drug Iddddddddddddd"+drugId);
		return drugId;
		
	}

	
	public void savePatientPBMDrugHistoryDetailResult(PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetail) {
		
		boolean pbmDrugHistoryResultStatus=false;
		if(StringUtils.isNotEmpty(patientPbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse())){
			pbmDrugHistoryResultStatus=true;
		}
		try {
			getSessionFactory().getCurrentSession().save(patientPbmDrugHistoryDetail);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		
		
		String hql="UPDATE UserLoginDetail set pbmDrugHistoryDetailStatus = :pbmDrugHistoryDetailStatus, "
				+ "pbmDrugHistoryResultStatus = :pbmDrugHistoryResultStatus WHERE userId = :userId";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("pbmDrugHistoryDetailStatus", true);
		query.setParameter("userId", patientPbmDrugHistoryDetail.getPatientId());
		query.setParameter("pbmDrugHistoryResultStatus", pbmDrugHistoryResultStatus);
		int result = query.executeUpdate();
		logger.info(pbmDrugHistoryResultStatus+"message status"+result);
		/*if(StringUtils.isNotEmpty(patientPbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse())){
			logger.info("PBMResult Status :::::::::::::");
			String sqlQuery="UPDATE UserLoginDetail set pbmDrugHistoryResultStatus = :pbmDrugHistoryResultStatus "  + 
		             "WHERE userId = :userId";
			Query query1=getSessionFactory().getCurrentSession().createQuery(sqlQuery);
			query1.setParameter("pbmDrugHistoryResultStatus", true);
			query1.setParameter("userId", patientPbmDrugHistoryDetail.getPatientId());
			int result1 = query1.executeUpdate();
			logger.info("message status"+result1);
		}*/
		
	}

	/**
	 * Get All Patient Detail Result 
	 * @author Anjani
	 * modified by saurabh for selecting patient according to provider id of loggedIn user
	 */
	public List<UserLoginDetail> getAllPatientDetailList() {
		int providerId=new ContextUtil().getProviderId();
		//List<LoginSecurity>  loginSecuritieList=new ArrayList<LoginSecurity>();
		List<UserLoginDetail> patientDetailList=new ArrayList<UserLoginDetail>();
		try {
			String sql_query="select `ud`.* from `user_details` `ud` join `patient_provider` `pr` on `pr`.`patient_id`=`ud`.`user_id`"+
							  "join `login_security` `ls` on ls.id=`ud`.`user_id` where `ls`.`role_id`=3 and `ls`.`status`=1 and `pr`.`provider_id`= :providerId";
			SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(sql_query);
			sqlQuery.setParameter("providerId", new ContextUtil().getProviderId());
			sqlQuery.addEntity(UserLoginDetail.class);
			patientDetailList = sqlQuery.list();
		
		/*Criteria loginSecurityCriteria=getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
		loginSecurityCriteria.add(Restrictions.eq("role", 3));
		loginSecurityCriteria.add(Restrictions.eq("status", true));
		List<LoginSecurity>  loginSecuritieList=loginSecurityCriteria.list();*/
		
		//modified by gopal use in query
		/*List<Integer>idList=new ArrayList<Integer>();
		for(LoginSecurity loginSecurity:loginSecuritieList){
			idList.add(loginSecurity.getId());
		}
		logger.info("id list::::"+idList);
		Criteria patientDetailCriteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class)
				 .add(Restrictions.in("userId", idList));
		patientDetailList=patientDetailCriteria.list();*/
		/*for(LoginSecurity loginSecurity:loginSecuritieList){
			System.out.println("loginSecurity.getId()"+loginSecurity.getId());
			UserLoginDetail patientDetail=(UserLoginDetail) getSessionFactory().getCurrentSession().get(UserLoginDetail.class,loginSecurity.getId());
			Criteria patientDetailCriteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class)
					 .add(Restrictions.eq("userId", loginSecurity.getId()));
			List<UserLoginDetail> userLoginDetails=patientDetailCriteria.list();
			
			UserLoginDetail patientDetail=userLoginDetails.get(0);
			patientDetailList.add(patientDetail) ;              
		}*/
			for(UserLoginDetail detail:patientDetailList){
				System.out.println("First Name "+detail.getFirstName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(":::::::::EXCEPTION IN getAllPatientDetailList METHOD IN USER DAO::::::::", e);
		}	
		return patientDetailList;
	}

	/**
	 * Get PBM Drug History Detail Data  
	 */
	public List<PatientPbmDrugHistoryDetail> getAllPatientPbmDrugHistoryDetail(int patientId) {
		List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetails=new ArrayList<PatientPbmDrugHistoryDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientPbmDrugHistoryDetail.class);
		criteria.add(Restrictions.eq("status", true));
		criteria.add(Restrictions.eq("patientId", patientId));
		patientPbmDrugHistoryDetails=criteria.list();
				
		
		return patientPbmDrugHistoryDetails;
	}

	/**
	 * Save Patient PBM Drug History Result in Clinakos System Database 
	 */
	public void savePatientPBMDrugHistoryResult(
			PatientPBMDrugHistoryResult drugHistoryResult) {
	       try {
	    	   getSessionFactory().getCurrentSession().save(drugHistoryResult);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
			
			/*String hql="UPDATE UserLoginDetail set pbmDrugHistoryResultStatus = :pbmDrugHistoryResultStatus"  + 
		             "WHERE userId = :userId";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			query.setParameter("pbmDrugHistoryResultStatus", true);
			query.setParameter("userId", drugHistoryResult.getPatientId());
			int result = query.executeUpdate();
			logger.info("Message Status :::::::::"+result);*/
	
		
		
	}

	/**
	 * Get PatientPBM Drug History Result Data 
	 */
	public List<PatientPBMDrugHistoryResult> getAllPatientPBMDrugHistoryResultValue(int patientId) {
		// TODO Auto-generated method stub
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientPBMDrugHistoryResult.class);
		                 criteria.add(Restrictions.eq("status", true));
		                 criteria.add(Restrictions.eq("patientId", patientId));
		List<PatientPBMDrugHistoryResult> drugHistoryResults=criteria.list();
		return drugHistoryResults;
	}

	/**
	 * Save Formulary CompositeDrugDetail Info
	 */
	public void savePatientFormularyCompositeDrugDetailInfoData(
			ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList) {
		for(PatientFormularyCompositeDrugDetailInfo compositeDrugDetailInfo:formularyCompositeDrugDetailInfoList){
			getSessionFactory().getCurrentSession().save(compositeDrugDetailInfo);
		}
		
	}

	/**
	 * Save FormularyComposite Copay Info Data 
	 */
	public void savePatientFormularyCompositeCopayInfoData(
			ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList) {
		for(PatientFormularyCompositeCopayInfo compositeCopayInfo:formularyCompositeCopayInfoList){
			getSessionFactory().getCurrentSession().save(compositeCopayInfo);
		}
		
	}

	/**
	 * Save Patient PBM Eligibility Detail Data 
	 */
	public void savePatientPBMEligibilityDetailData(
			List<PatientPBMEligibilityDetailData> pbmEligibilityDetailDataList) {
		for(PatientPBMEligibilityDetailData pbmEligibilityDetailData:pbmEligibilityDetailDataList){
			getSessionFactory().getCurrentSession().save(pbmEligibilityDetailData);
		}
		
	}

	public ProviderDetail getProviderDetailData(int providerId) {
		// TODO Auto-generated method stub
		ProviderDetail providerDetail=(ProviderDetail) getSessionFactory().getCurrentSession().get(ProviderDetail.class, providerId);
		return providerDetail;
	}

	/**
	 * Update Pharmacy detail and User Insurance Detail table 
	 * @throws SQLException 
	 * @throws HibernateException 
	 */
	public void updateHealthPlanAndPharmacyDetailData(int userId) throws HibernateException, SQLException {
		CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession()
				.connection().prepareCall("{call proc_update_healthplan_and_pharmacy_detail("+userId+")}");
		ResultSet resultSet=statement.executeQuery();
		updateDrugIdBasedOnDrugName(userId);
		
	}
    /**
     * Get DrugName  Based on Empty DrugId from Pharmacy Detail Table 
     * 
     * @author Anjani
     * @param userId
     */
    private void updateDrugIdBasedOnDrugName(int userId) {
		// TODO Auto-generated method stub
    	List<PharmacyDetail> pharmacyDetails=new ArrayList<PharmacyDetail>();
    	try {
    		/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PharmacyDetail.class);
            criteria.add(Restrictions.eq("Double.toString(drugId)", ""));
        	criteria.add(Restrictions.eq("patientId", userId));
        	pharmacyDetails=criteria.list();*/
    		
    		String sqlQuery="Select * from pharmacy_details where patient_id=:patientId and drug_id=:drugId";
    		SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
    		query.addEntity(PharmacyDetail.class);
    		query.setParameter("patientId", userId);
    		query.setParameter("drugId", "");
    		pharmacyDetails=query.list();
		} catch (HibernateException hbe) {
			hbe.printStackTrace();
		}
    	
    	
    	
    	
    	for(PharmacyDetail phDetail:pharmacyDetails){
    		logger.info("Drug Info ::::::::"+phDetail.getDrugInfo());
    		splitDrugName(phDetail.getDrugInfo(),userId);
    	}
    	
    	
		
	}
    /**
     * Split Drug Name for Compare Drug to Master Data Base Table
     * @author Anjani 
     * @param drug
     * @param userId 
     */
	private void splitDrugName(String drug, int userId) {
		// TODO Auto-generated method stub
		String[] drugNameArray=drug.split(" ");
		String fullDrugName="";
		List<WSDrug> masterDrugList=new ArrayList<WSDrug>();
		for(String str:drugNameArray){
			
			fullDrugName+=str;
			
			 masterDrugList=getDrugFromMasterDrugBasedOnDrugName(drugNameArray[0]);
			String masterDrug="";
			for(WSDrug wsDrug:masterDrugList){
				masterDrug=splitMasterDrugData(wsDrug.getDrugName());
				logger.info("masterDrug"+masterDrug);
				if(masterDrug.toLowerCase().equals(fullDrugName.toLowerCase())){
					logger.info("Drug Matched");
					updatePharamcyDetailBasedOnDrugName(wsDrug.getDrugId(),userId,drug);
				}
			}
			
		}
		
		
		
	}
	
	/**
	 * @author Anjani
	 * Update DrugId After Matching Drug Name 
	 * @param drugId
	 * @param userId
	 * @param fullDrugName 
	 */
    private void updatePharamcyDetailBasedOnDrugName(Double drugId, int userId, String dugName) {
		// TODO Auto-generated method stub
    
    	String updateQuery="update pharmacy_details set drug_id=? where patient_id=? and drug_info=?";
    	try {
			PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(updateQuery);
			statement.setDouble(1, drugId);
			statement.setInt(2, userId);
			statement.setString(3, dugName);
			int i=statement.executeUpdate();
			logger.info("Query Updated "+i);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}

	private String splitMasterDrugData(String drugName) {
    	String[] masterDrugArray=drugName.split(" ");
    	String masterDrugName="";
    	for(int i=0;i<masterDrugArray.length;i++){
    		
    				
    				
    				
    		masterDrugName+=masterDrugArray[i];
    	}
    	logger.info("masterDrugName"+masterDrugName);
    	
		return masterDrugName;
		// TODO Auto-generated method stub
		
	}

	/**
     * @author Anjani
     * Get all Drug From Master Drug Table using Drug Name
     * @param string
     * @return 
     */
	private List<WSDrug> getDrugFromMasterDrugBasedOnDrugName(String drugName) {
		// TODO Auto-generated method stub
		List<WSDrug> patientMatchDrugList=new ArrayList<WSDrug>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(WSDrug.class);
		criteria.add(Restrictions.like("drugName", drugName, MatchMode.START)); 
		patientMatchDrugList=criteria.list();
		logger.info("patientMatchDrugList.size()"+patientMatchDrugList.size());
		return patientMatchDrugList;
	}

	/**
     * Care Team Integration Through WebServices 
     */
	public void careTeamintegration(int userId) {
		try {
			CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_care_team_integration("+userId+")}");
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
	 * Get PatientDetail Data
	 */
	public List<UserLoginDetail> getPatientDetailList(int patientId) {
		// TODO Auto-generated method stub
		List<UserLoginDetail> patientDetailList=new ArrayList<UserLoginDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
		criteria.add(Restrictions.eq("userId", patientId));
		criteria.add(Restrictions.eq("status", true));
		patientDetailList=criteria.list();
		return patientDetailList;
	}

	/**
	 * Delete Patient PBM Drug History Detail Result 
	 */
	public void deletePatientPBMDrugHistoryDetailResult(int patientId) {
		String hql="delete from PatientPbmDrugHistoryDetail where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql).setString("patientId", Integer.toString(patientId)).executeUpdate();
		
	}

	/**
	 * Delete PatientPBMDrugHistoryResult 
	 */
	public void deletePatientPBMDrugHistoryResult(int patientId) {
		String hql="delete from PatientPBMDrugHistoryResult where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql).setString("patientId", Integer.toString(patientId)).executeUpdate();
		
	}


/*
 * 	***********Method for fetching all records from LoginSecurity for password encryption
 * @see com.clinakos.data.dao.IUserDao#fetchAllRecordsFromLogin()
 * ******@author: saurabh
 */
	public List<LoginSecurity> fetchAllRecordsFromLogin() {
		logger.info("==========fetchAllRecordsFromLogin called in userDaoImpl=========");
		List<LoginSecurity> ls= new ArrayList<LoginSecurity>();
		Criteria criteria= getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
		ls=criteria.list();
		return ls;
	}

/*
 * *********Method to update plain password INTO encrypted Password
 * **********@author: saurabh
 * @see com.clinakos.data.dao.IUserDao#updatePlainPasswordToEncrypt(int, java.lang.String)
 */
	public void updatePlainPasswordToEncrypt(int rowId, String encryptedPassword) {
		logger.info("=========method updatePlainPasswordToEncrypt called in userDAO IMPL======rowId="+rowId+"=encryptedPassword=="+encryptedPassword);
		try {
			Query hqlQuery=getSessionFactory().getCurrentSession().createQuery("update LoginSecurity set password = :encryptedPassword where id= :rowId");
			hqlQuery.setParameter("encryptedPassword", encryptedPassword);
			hqlQuery.setParameter("rowId", rowId);
			int updateId = hqlQuery.executeUpdate();
			logger.info("=======password update completed==========");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("======EXCEPTION IN updatePlainPasswordToEncrypt USER DAOIMPL===================", e);
		}	
		
	}


	public List<UserLoginDetail> fetchPateintListForCompliance(
			String selectedCompliancePart, int providerId) {
		logger.info("fetchPateintListForCompliance:::::::::started in userDAO::");
		List<UserLoginDetail> compliancePatientDetails = new ArrayList<UserLoginDetail>();
		try {
			  
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_fetchCompliancePatient('"+selectedCompliancePart+"',"+providerId+",'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			
			 while (resultSet.next()) {
				 UserLoginDetail fc = new UserLoginDetail();
				 fc.setId(Integer.parseInt(resultSet.getString("user_id")));
				 String patientId=resultSet.getString("user_id");
				 fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("first_name"));
				 fc.setMiddleName(resultSet.getString("middle_name"));
				 fc.setLastName(resultSet.getString("last_name"));
				 fc.setDateOfBirth(resultSet.getDate("date_of_birth"));
				 fc.setGender(resultSet.getString("gender"));
				 
				 compliancePatientDetails.add(fc);
			 }
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("error at fetchPateintListForCompliance method in userDAO:::::",e);
		}
		
		return compliancePatientDetails;
	}
	/*
	 * update compliance caluculation
	 * call proc written by saurabh
	 * @author: Gopal
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IUserDao#updateComplianceValue()
	 */
	@Override
	public void updateComplianceValue() {
		logger.info("updateComplianceValue:::::::::::::::::");
		
		CallableStatement statement;
		try {
			statement = (CallableStatement) getSessionFactory().
					getCurrentSession().connection().prepareCall("{call proc_calculateComplianceForDemo()}");
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
     * Save New Crop Allergy in Clinakos System 
     */
	/*public void saveAllergy(
			List<PatientAllergyExtendedDetailV4> patientAllergyExtendedList,
			int patientId) {
		// TODO Auto-generated method stub
		String hql="delete from PatientAllergy where patientId=:patientId";
		Query query=getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("patientId", patientId);
		int res=query.executeUpdate();
		for (PatientAllergyExtendedDetailV4 patAllExt:patientAllergyExtendedList) {
			PatientAllergy patientAllergy=new PatientAllergy();
			patientAllergy.setAllergy(patAllExt.getAllergyName());
			patientAllergy.setAllergyDescription(patAllExt.getAllergySeverityName());
			patientAllergy.setPatientId(patientId);
			getSessionFactory().getCurrentSession().save(patientAllergy);
			
		}
		
	}
*/
	
/*
 * to find provider of the logged in admin by saurabh
 * @see com.clinakos.data.dao.IUserDao#findProviderIdForAdmin(int)
 */
	  public ProviderUser findProviderIdForAdmin(int userId) {
		  logger.info(":::::::findProviderIdForAddmin called in dashboardDaoImpl::::::::");
		  ProviderUser prUser= new ProviderUser();
		  try {
			  prUser= (ProviderUser) getSessionFactory().getCurrentSession().createCriteria(ProviderUser.class)
				 .add(Restrictions.eq("userId", userId)).list().get(0);
			  
			} catch (Exception e) {
				e.printStackTrace();
				logger.info(":::::::EXCEPTION IN findProviderIdForAddmin in userDaoImpl::::::::"+e);
			}
		  return prUser;
		  //return ProviderUser;
	   }

/*
 * ***********************USED ONLY FOR CORRECT OLD PHARMACY DATA
 * *******************added by @saurabh to update
 * *****************pbm_drug_result and care_team
 * at the time of removing this method, remove this method from service and dao layer
 */
	public void deletePatientDrugData(int patientId) {
		logger.info(">>>>>>>>>>>>>>>>>>METHOD deletePatientDrugData CALLED IN USERDAO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		try {
		logger.info("######################delete from PatientPBMDrugHistoryResult#####################################");
		String hql1="delete from PatientPBMDrugHistoryResult where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql1).setString("patientId", Integer.toString(patientId)).executeUpdate();
		logger.info("###################delete from PharmacyDetail#############################");
		String hql2="delete from PharmacyDetail where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql2).setString("patientId", Integer.toString(patientId)).executeUpdate();
		logger.info("###################delete from CareTeam############################");
		String hql3="delete from CareTeam where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql3).setString("patientId", Integer.toString(patientId)).executeUpdate();
		logger.info("##########################delete from PatientMedicationData#######################");
		String hql4="delete from PatientMedicationData where patientId=:patientId";
		getSessionFactory().getCurrentSession().createQuery(hql4).setString("patientId", Integer.toString(patientId)).executeUpdate();
		logger.info(">>>>>>>>>>>>>>>>>>METHOD deletePatientDrugData COMPLETEDDDDD IN USERDAO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		} catch (Exception e) {
			logger.error("<<<<<<<<<<<<<<<<EXCEPTION IN deletePatientDrugData INSDIE USERDAO>>>>>>>>>>>>>>>>",e);
		}
	}
	
/*
* ***********************USED ONLY FOR CORRECT OLD PHARMACY DATA
* *******************added by @saurabh to update
* *****************pbm_drug_result and care_team
* at the time of removing this method, remove this method from service and dao layer
*/
	public List<UserLoginDetail> getPatientList() {
		List<UserLoginDetail> patientList=new ArrayList<UserLoginDetail>();
		try {
			String sql_query="select `ud`.* from `user_details` `ud` join `patient_pbm_drug_history_detail` `pdd` on `pdd`.`patient_id`=`ud`.`user_id`"+
							"join `patient_provider` `pr` on `pr`.`patient_id`=`ud`.`user_id` where `pdd`.`update_status`=1 and `pr`.`provider_id`= :providerId";
			SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(sql_query);
			sqlQuery.setParameter("providerId", new ContextUtil().getProviderId());
			sqlQuery.addEntity(UserLoginDetail.class);
			patientList = sqlQuery.list();
			for(UserLoginDetail detail:patientList){
				System.out.println("getPatientList First Name ="+detail.getFirstName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(":::::::::EXCEPTION IN getPatientList METHOD IN USER DAO::::::::", e);
		}	
		return patientList;
	
	}

	
	public List<UserLoginDetail> getpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId) {
		List<UserLoginDetail> userDataListWithPatientConsent=new ArrayList<UserLoginDetail>();
		
			String sql_query= "select ud.user_id,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name,AES_DECRYPT(ud.middle_name,'akos') as middle_name , " +
					"AES_DECRYPT(ud.gender,'akos') as gender,AES_DECRYPT(ud.state,'akos') as state ,AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth,ud.patient_consent ,AES_DECRYPT(ud.phone_number,'akos') as patient_telephone ," +
					"AES_DECRYPT(ud.door_no,'akos') as address1 , AES_DECRYPT(ud.street,'akos') as address2, AES_DECRYPT(ud.city,'akos') as city , AES_DECRYPT(ud.country,'akos') as country , AES_DECRYPT(ud.pin_code,'akos') as pin_code  ," +
					"ud.pbm_drug_history_detail_status ,ud.pbm_drug_history_result_status ,"+
					" ls.created_on,ls.group_upload_id  from user_details ud join login_security ls on "+  
					" ud.user_id=ls.id join patient_provider pp on ud.user_id=pp.patient_id "+
					"where ud.patient_consent='Y' and ls.role_id=3 and ls.group_upload_id =(select max(group_upload_id) from login_security )  and pp.provider_id="+providerId 
					+" order by ud.user_id desc ";
			
			try {
				PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
				ResultSet resultSet=statement.executeQuery();
				while (resultSet.next()) {
					UserLoginDetail userDetail=new UserLoginDetail();
					userDetail.setUserId(resultSet.getInt("user_id"));
					userDetail.setFirstName(resultSet.getString("first_name"));
					userDetail.setMiddleName(resultSet.getString("middle_name"));
					userDetail.setLastName(resultSet.getString("last_name"));
					userDetail.setGender(resultSet.getString("gender"));
					userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
					userDetail.setState(resultSet.getString("state"));
					userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
					userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
					userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
					userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
					userDetail.setDoorNo(resultSet.getString("address1"));
					userDetail.setStreet(resultSet.getString("address2"));
					userDetail.setCity(resultSet.getString("city"));
					userDetail.setCountry(resultSet.getString("country"));
					userDetail.setPincode(resultSet.getString("pin_code"));
					
					userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
					userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));
					
					userDataListWithPatientConsent.add(userDetail);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(UserLoginDetail ud:userDataListWithPatientConsent){
				System.out.println("State ::::::::"+ud.getState()+"Gender "+ud.getGender());
			}
		
		
		return userDataListWithPatientConsent;
	}

	
	public List<Date> getAllPatientBatchDataData(int providerId,
			int providerLocationId) {
		List<Date> allPatientBatchDataList=new ArrayList<Date>();
		String sql_query="select distinct(ls.created_on) from login_security ls join "+
				 "patient_provider pp on pp.patient_id=ls.id where ls.created_on is not null and pp.provider_id="+ providerId 
				 +" order by (ls.created_on) desc ";
		try {
			PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
			Date patientBatchDate=resultSet.getDate("created_on");
			allPatientBatchDataList.add(patientBatchDate);
				
			}
		} catch (HibernateException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allPatientBatchDataList;
	}

	
	public List<Integer> getPatientBatchNumberData(
			Date patientBatchCreatedDate, int providerId, int providerLocationId) {
		List<Integer> patientBatchNumberList=new ArrayList<Integer>();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("patientBatchCreatedDate "+patientBatchCreatedDate);
		String sql_query="select distinct(ls.group_upload_id) from login_security ls join "+
				 "patient_provider pp on pp.patient_id=ls.id where pp.provider_id=:providerId and ls.group_upload_id !=0"+
				 " order by ls.group_upload_id desc " ;
	
		try {
			SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sql_query);
			query.setParameter("providerId", providerId);
			/*query.setParameter("patientBatchCreatedDate", format.format(patientBatchCreatedDate));*/
			patientBatchNumberList=query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return patientBatchNumberList;
	}

	
	public List<UserLoginDetail> getPatientDataWithPatientConsentByBatchNoFilter(
			int providerId, int providerLocationId, String patientBatchDate,
			int patientBatchNo,char patientConsent) {
		List<UserLoginDetail> patientDataWithPatientConsentWithPatientBatchFilter=new ArrayList<UserLoginDetail>();
		String sql_query="select ud.user_id ,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name," +
		"AES_DECRYPT(ud.middle_name,'akos') as middle_name,AES_DECRYPT(ud.door_no,'akos') as address1 , AES_DECRYPT(ud.street,'akos') as address2, " +
		"AES_DECRYPT(ud.city,'akos') as city , AES_DECRYPT(ud.country,'akos') as country , " +
		"AES_DECRYPT(ud.gender,'akos') gender,AES_DECRYPT(ud.state,'akos') as state, " +
		"AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth, ud.patient_consent ,AES_DECRYPT(ud.phone_number,'akos') as patient_telephone , AES_DECRYPT(ud.pin_code, 'akos') as pin_code ," +
		"ud.pbm_drug_history_detail_status ,ud.pbm_drug_history_result_status ,"+
		" ls.created_on,ls.group_upload_id  from user_details ud join login_security ls on "+ 
		" ud.user_id=ls.id join patient_provider pp on ud.user_id=pp.patient_id "+
		"where ud.patient_consent='Y' and ls.role_id=3 and ls.group_upload_id = " +patientBatchNo + "  and pp.provider_id="+providerId  
		+" order by ud.user_id desc "; 
		
		try {
			PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				UserLoginDetail userDetail=new UserLoginDetail();
				userDetail.setUserId(resultSet.getInt("user_id"));
				userDetail.setFirstName(resultSet.getString("first_name"));
				userDetail.setMiddleName(resultSet.getString("middle_name"));
				userDetail.setLastName(resultSet.getString("last_name"));
				userDetail.setGender(resultSet.getString("gender"));
				userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
				userDetail.setState(resultSet.getString("state"));
				userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
				userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
				userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
				userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
				
				userDetail.setDoorNo(resultSet.getString("address1"));
				userDetail.setStreet(resultSet.getString("address2"));
				userDetail.setCity(resultSet.getString("city"));
				userDetail.setCountry(resultSet.getString("country"));
				userDetail.setPincode(resultSet.getString("pin_code"));
				
				userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
				userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));
				
				
				patientDataWithPatientConsentWithPatientBatchFilter.add(userDetail);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patientDataWithPatientConsentWithPatientBatchFilter;
	}

	@Override
	public List<Integer> calTotalNumberOfDrugHistory(Integer providerId) {
		List<Integer>pharmaDashboardAnalytics=new ArrayList<Integer>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_records_pulled("+providerId+")}");
			ResultSet resultSet = statement.executeQuery();
			int totalNumberOfDrugHistory=0;
			int totalNumberOfPatients=0;
			 while (resultSet.next()) {
				totalNumberOfDrugHistory=resultSet.getInt("total_number_of_records_pulled");
				totalNumberOfPatients=resultSet.getInt("number_of_patients");
				pharmaDashboardAnalytics.add(totalNumberOfDrugHistory);
				pharmaDashboardAnalytics.add(totalNumberOfPatients);
				logger.info("Total Number of drug history records pulled "+totalNumberOfDrugHistory +"for Provider Id "+providerId+"Total number of patients having records pulled "+totalNumberOfPatients);
			 }
			 return pharmaDashboardAnalytics;
			 
		}catch(Exception e){
			e.printStackTrace();
			pharmaDashboardAnalytics=null;
			return pharmaDashboardAnalytics;
		}
		
	}

	@Override
	public List<Object> findEnbrelAdherenceComplianceDetails() {
		int providerId=new ContextUtil().getProviderId();
		List<Object>enbrelAdherenceList=new ArrayList<Object>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_enbrel_adherence("+providerId+")}");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				enbrelAdherenceList.add(resultSet.getInt("Compliance"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		for(Object compliance:enbrelAdherenceList){
			logger.info("Object value "+compliance.toString());
		}
		logger.info("Enbrel Adherence List size "+enbrelAdherenceList.size());
		return enbrelAdherenceList;
	}

	@Override
	public List<ChartModel> findEnbrelAdherencePatients(Integer providerId) {
		List<ChartModel>noOfPatientsOnEnbrelAdherence=new ArrayList<ChartModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_enbrel_adherence_chart_base("+providerId+")}");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				ChartModel model=new ChartModel();
				model.setCountValue(resultSet.getInt("no_of_patients"));
				model.setRangeName(resultSet.getString("adherence_split"));
				noOfPatientsOnEnbrelAdherence.add(model);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return noOfPatientsOnEnbrelAdherence;
	}


	@Override
	public Integer getPatientsCurrentlyOnEnbrel(Integer providerId) {
	 Integer patientsCurrentlyOnEnbrel=0;
	 try{
		 CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_patient_enbrel_count("+providerId+")}");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				patientsCurrentlyOnEnbrel=resultSet.getInt("no_of_patients_having_enbrel");
			}
			
	 }catch(Exception e){
		 patientsCurrentlyOnEnbrel=0;
		 e.printStackTrace();
	 }
		return patientsCurrentlyOnEnbrel;
	}

	
	public List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDate(
			int providerId, int providerLocationId, String patientBatchDate,int patientBatchNo,
			char patientConsent) {
		List<UserLoginDetail> batchDataPullPatientDataBasedOnBatchCreatedDate=new ArrayList<UserLoginDetail>();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date batchDate=null;
		try {
			if(patientBatchDate!=null){
				batchDate=dateFormat.parse(patientBatchDate);
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql_query="select ud.user_id ,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name," +
				"AES_DECRYPT(ud.middle_name,'akos') as middle_name,AES_DECRYPT(ud.door_no,'akos') as address1 , AES_DECRYPT(ud.street,'akos') as address2, " +
				"AES_DECRYPT(ud.city,'akos') as city , AES_DECRYPT(ud.country,'akos') as country , AES_DECRYPT(ud.pin_code,'akos') as pin_code ," +
				"AES_DECRYPT(ud.gender,'akos') gender,AES_DECRYPT(ud.state,'akos') as state, " +
				"AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth,ud.patient_consent ,AES_DECRYPT(ud.phone_number,'akos') as patient_telephone ," +
				"ud.pbm_drug_history_detail_status ,ud.pbm_drug_history_result_status , "+
				" ls.created_on,ls.group_upload_id  from user_details ud join login_security ls on "+ 
				" ud.user_id=ls.id join patient_provider pp on ud.user_id=pp.patient_id "+
				"where ud.patient_consent='Y' and ls.role_id=3 and ls.created_on = " + "' "+ dateFormat.format(batchDate) +"'" +"  and pp.provider_id="+providerId   
				+" order by ud.user_id desc "; 
				
				try {
					PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
					ResultSet resultSet=statement.executeQuery();
					while (resultSet.next()) {
						UserLoginDetail userDetail=new UserLoginDetail();
						userDetail.setUserId(resultSet.getInt("user_id"));
						userDetail.setFirstName(resultSet.getString("first_name"));
						userDetail.setMiddleName(resultSet.getString("middle_name"));
						userDetail.setLastName(resultSet.getString("last_name"));
						userDetail.setGender(resultSet.getString("gender"));
						userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
						userDetail.setState(resultSet.getString("state"));
						userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
						userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
						userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
						userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
						
						userDetail.setDoorNo(resultSet.getString("address1"));
						userDetail.setStreet(resultSet.getString("address2"));
						userDetail.setCity(resultSet.getString("city"));
						userDetail.setCountry(resultSet.getString("country"));
						userDetail.setPincode(resultSet.getString("pin_code"));
						
						userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
						userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));
						
						
						batchDataPullPatientDataBasedOnBatchCreatedDate.add(userDetail);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return batchDataPullPatientDataBasedOnBatchCreatedDate;
	}
	
	
	public List<UserLoginDetail> getAllPatientDataForBatchPullBasedOnBatchDateAndBatchNo(
			int providerId, int providerLocationId, String patientBatchDate,int patientBatchNo ,
			char patientConsent) {
		List<UserLoginDetail> batchDataPullPatientDataBasedOnBatchCreatedDate=new ArrayList<UserLoginDetail>();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		Date batchDate=null;
		try {
			if(patientBatchDate!=null){
				batchDate=dateFormat.parse(patientBatchDate);
			}
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String sql_query="select ud.user_id ,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name," +
				"AES_DECRYPT(ud.middle_name,'akos') as middle_name,AES_DECRYPT(ud.door_no,'akos') as address1 , AES_DECRYPT(ud.street,'akos') as address2, " +
				"AES_DECRYPT(ud.city,'akos') as city , AES_DECRYPT(ud.country,'akos') as country , " +
				"AES_DECRYPT(ud.gender,'akos') gender,AES_DECRYPT(ud.state,'akos') as state, " +
				"AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth,ud.patient_consent ,AES_DECRYPT(ud.phone_number,'akos') as patient_telephone ,AES_DECRYPT(ud.pin_code,'akos') as pin_code ," +
				"ud.pbm_drug_history_detail_status ,ud.pbm_drug_history_result_status ,"+
				" ls.created_on,ls.group_upload_id  from user_details ud join login_security ls on "+ 
				" ud.user_id=ls.id join patient_provider pp on ud.user_id=pp.patient_id "+
				"where ud.patient_consent='Y' and ls.role_id=3 and ls.created_on = " + "' "+ dateFormat.format(batchDate) +"'"+" and ls.group_upload_id = "+ patientBatchNo +"  and pp.provider_id="+providerId   
				+" order by ud.user_id desc "; 
				
				try {
					PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
					ResultSet resultSet=statement.executeQuery();
					while (resultSet.next()) {
						UserLoginDetail userDetail=new UserLoginDetail();
						userDetail.setUserId(resultSet.getInt("user_id"));
						userDetail.setFirstName(resultSet.getString("first_name"));
						userDetail.setMiddleName(resultSet.getString("middle_name"));
						userDetail.setLastName(resultSet.getString("last_name"));
						userDetail.setGender(resultSet.getString("gender"));
						userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
						userDetail.setState(resultSet.getString("state"));
						userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
						userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
						userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
						userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
						
						userDetail.setDoorNo(resultSet.getString("address1"));
						userDetail.setStreet(resultSet.getString("address2"));
						userDetail.setCity(resultSet.getString("city"));
						userDetail.setCountry(resultSet.getString("country"));
						userDetail.setPincode(resultSet.getString("pin_code"));
						
						userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
						userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));
						
						
						batchDataPullPatientDataBasedOnBatchCreatedDate.add(userDetail);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return batchDataPullPatientDataBasedOnBatchCreatedDate;
	}
	
	@Override
	public List<UserLoginDetail> fetchPateintsForEnbrelCompliance(
			String selectedCompliancePart, int providerId) {
		
			logger.info("fetchPateintListForCompliance:::::::::started in userDAO::");
			List<UserLoginDetail> compliancePatientDetails = new ArrayList<UserLoginDetail>();
			try {
				  
				CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_fetch_patients_enbrel_compliance('"+selectedCompliancePart+"',"+providerId+",'"+DecryptionKey+"')}");
				ResultSet resultSet = statement.executeQuery();
				
				 while (resultSet.next()) {
					 UserLoginDetail fc = new UserLoginDetail();
					 fc.setId(Integer.parseInt(resultSet.getString("user_id")));
					 String patientId=resultSet.getString("user_id");
					 fc.setUserId(Integer.parseInt(patientId));
					 fc.setFirstName(resultSet.getString("first_name"));
					 fc.setMiddleName(resultSet.getString("middle_name"));
					 fc.setLastName(resultSet.getString("last_name"));
					 fc.setDateOfBirth(resultSet.getDate("date_of_birth"));
					 fc.setGender(resultSet.getString("gender"));
					 
					 compliancePatientDetails.add(fc);
				 }
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("error at fetchPateintListForCompliance method in userDAO:::::",e);
			}
			
			return compliancePatientDetails;
		
	}

	
	public List<UserLoginDetail> getAllpatientDataWithPatientConsentForDataPullProcess(
			int providerId, int providerLocationId) {
		
		List<UserLoginDetail> allBatchDataPullPatientData=new ArrayList<UserLoginDetail>();
		String sql_query="select ud.user_id ,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name," +
				"AES_DECRYPT(ud.middle_name,'akos') as middle_name,AES_DECRYPT(ud.door_no,'akos') as address1, AES_DECRYPT(ud.street,'akos') as address2, " +
				"AES_DECRYPT(ud.city,'akos') as city, AES_DECRYPT(ud.country,'akos') as country, " +
				"AES_DECRYPT(ud.gender,'akos') gender,AES_DECRYPT(ud.state,'akos') as state, " +
				"AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth,ud.patient_consent ,AES_DECRYPT(ud.phone_number,'akos') as patient_telephone ,AES_DECRYPT(ud.pin_code, 'akos') as pin_code," +
				"ud.pbm_drug_history_detail_status ,ud.pbm_drug_history_result_status ,"+
				" ls.created_on,ls.group_upload_id,ls.id  from user_details ud join login_security ls on "+ 
				" ud.user_id=ls.id join patient_provider pp on ud.user_id=pp.patient_id "+
				"where ud.patient_consent='Y' and ls.role_id=3 and pp.provider_id="+providerId  
				+"  order by ud.user_id desc "; 
				
				try {
					PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
					ResultSet resultSet=statement.executeQuery();
					while (resultSet.next()) {
						UserLoginDetail userDetail=new UserLoginDetail();
						userDetail.setUserId(resultSet.getInt("user_id"));
						userDetail.setFirstName(resultSet.getString("first_name"));
						userDetail.setMiddleName(resultSet.getString("middle_name"));
						userDetail.setLastName(resultSet.getString("last_name"));
						userDetail.setGender(resultSet.getString("gender"));
						userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
						userDetail.setState(resultSet.getString("state"));
						userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
						userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
						userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
						userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
						
						userDetail.setDoorNo(resultSet.getString("address1"));
						userDetail.setStreet(resultSet.getString("address2"));
						userDetail.setCity(resultSet.getString("city"));
						userDetail.setPincode(resultSet.getString("pin_code"));
						userDetail.setCountry(resultSet.getString("country"));
						
						userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
						userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));
						
						
						
						allBatchDataPullPatientData.add(userDetail);
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return allBatchDataPullPatientData;
	}

	
	public void doDeIdentificationDataProcess(int providerId,String decreptionKey) {
		CallableStatement statement=null;
		
			try {
				statement=(CallableStatement)getSessionFactory().getCurrentSession().connection().prepareCall("call proc_get_de_idetification_data(?,?)");
				statement.getConnection().setAutoCommit(false);
				statement.setInt(1, providerId);
				statement.setString(2, decreptionKey);
				ResultSet resultSet=statement.executeQuery();
				statement.getConnection().commit();
			
			
			} catch (HibernateException he) {
				he.printStackTrace();
				try {
					statement.getConnection().rollback();
				} catch (SQLException sqe) {
					
					sqe.printStackTrace();
				}
				
			} catch (SQLException se) {
				
				se.printStackTrace();
				
				try {
					statement.getConnection().rollback();
				} catch (SQLException sqe) {
					
					sqe.printStackTrace();
				}
			}
			
			catch (NullPointerException ne) {
				String message="Failed to save data in database Null Pointer Exception "+ne.getMessage();
				try {
					statement.getConnection().rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		
		
		
	}

	@Override
	public List<ChartModel> getInsuranceChartForProvider(
			int providerId,boolean isAllRecordsNeeded) {
		List<ChartModel>insuranceData=new ArrayList<ChartModel>();
		try{
			int allrecordsVal=0;
			if(isAllRecordsNeeded){
				allrecordsVal=1;
			}else{
				allrecordsVal=0;
			}
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_insurance_analytic("+providerId+","+allrecordsVal+")}");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				ChartModel model=new ChartModel();
				model.setCountValue(resultSet.getInt("no_of_patients"));
				model.setRangeName(resultSet.getString("pbm"));
				insuranceData.add(model);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return insuranceData;
	}
	
	/***@uthor: SAURABH
	* ******for fethcing all patients record for 
	 * ************batchPatientForReportList screen
	 */
		public List<UserLoginDetail> fetchbatchPatientForReportList(int providerId, int providerLocationId,
				 int fromPatientIdForReport, int toPatientIdForReport) {
			String sql_query;
			List<UserLoginDetail> patientList=new ArrayList<UserLoginDetail>();
			if (fromPatientIdForReport==0 || toPatientIdForReport==0) {
				 sql_query= "select ud.user_id,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name,AES_DECRYPT(ud.middle_name,'akos') as middle_name , " +
						"AES_DECRYPT(ud.gender,'akos') as gender,AES_DECRYPT(ud.state,'akos') as state ,AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth"+
						" from user_details ud  join patient_provider pp on ud.user_id=pp.patient_id "+
						"where pp.provider_id="+providerId+" order by ud.user_id desc ";
			}
			else{
			 sql_query= "select ud.user_id,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name,AES_DECRYPT(ud.middle_name,'akos') as middle_name , " +
					"AES_DECRYPT(ud.gender,'akos') as gender,AES_DECRYPT(ud.state,'akos') as state ,AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth"+
					" from user_details ud  join patient_provider pp on ud.user_id=pp.patient_id "+
					"where pp.provider_id="+providerId+" between "+fromPatientIdForReport+" and "+toPatientIdForReport+" order by ud.user_id desc ";
			   }
			try {
				PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
				ResultSet resultSet=statement.executeQuery();
				while (resultSet.next()) {
					UserLoginDetail userDetail=new UserLoginDetail();
					userDetail.setUserId(resultSet.getInt("user_id"));
					userDetail.setFirstName(resultSet.getString("first_name"));
					userDetail.setMiddleName(resultSet.getString("middle_name"));
					userDetail.setLastName(resultSet.getString("last_name"));
					userDetail.setGender(resultSet.getString("gender"));
					/*userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));*/ // Temp Commented 
					userDetail.setState(resultSet.getString("state"));
					
					patientList.add(userDetail);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 catch (HibernateException e) {
				e.printStackTrace();
			}
			return patientList;
		}


	public List<Integer> fetchPatientIdList() {
		List<UserLoginDetail> patientList1=new ArrayList<UserLoginDetail>();
		List<Integer> pt=new ArrayList<Integer>();
		String sql_query= "select ud.user_id,AES_DECRYPT(ud.first_name,'akos') as first_name,AES_DECRYPT(ud.last_name,'akos') as last_name,AES_DECRYPT(ud.middle_name,'akos') as middle_name , " +
				"AES_DECRYPT(ud.gender,'akos') as gender,AES_DECRYPT(ud.state,'akos') as state ,AES_DECRYPT(ud.date_of_birth,'akos') as date_of_birth"+
				" from user_details ud  join patient_provider pp on ud.user_id=pp.patient_id "+
				"where pp.provider_id="+new ContextUtil().getProviderId()+" order by ud.user_id desc ";
		try {
			PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
			ResultSet resultSet=statement.executeQuery();
			while (resultSet.next()) {
				UserLoginDetail userDetail=new UserLoginDetail();
				userDetail.setUserId(resultSet.getInt("user_id"));
				userDetail.setFirstName(resultSet.getString("first_name"));
				userDetail.setMiddleName(resultSet.getString("middle_name"));
				userDetail.setLastName(resultSet.getString("last_name"));
				userDetail.setGender(resultSet.getString("gender"));
				/*userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));*/ //Temp Commented 
				userDetail.setState(resultSet.getString("state"));
				
				patientList1.add(userDetail);
				pt.add(userDetail.getUserId());
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 catch (HibernateException e) {
			e.printStackTrace();
		}
		return pt;
	}

	/**
	 * Get Current Medication Data
	 * For Patient Batch Pull Report 
	 * @author Anjani
	 */
	public List<PatientMedicationData> getCurrentMedicationDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId) {
		List<PatientMedicationData> currentMedicationList=new ArrayList<PatientMedicationData>();
		Criteria criteria=null;
		for(UserLoginDetail userDetail:selectedUserDetailForReport){
			   List<PatientMedicationData> patMedList=new ArrayList<PatientMedicationData>();
				criteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
	            criteria.add(Restrictions.eq("patientId", userDetail.getUserId()));
	            criteria.add(Restrictions.eq("providerId", providerId));
	            criteria.addOrder(Order.desc("patientId"));
	            patMedList=criteria.list();
	            currentMedicationList.addAll(patMedList);
			
		}
		logger.info("Medication List Size "+currentMedicationList.size());
		return currentMedicationList;
	}

/**
 * Get Patient Detail Data 
 * For Batch Pull Data Report 
 * @author Anjani
 * 
 */
	public List<UserLoginDetail> getPatientDetailDataForBatchReport(
			UserLoginDetail[] selectedUserDetailForReport, int providerId,
			int providerLocationId) {
		List<UserLoginDetail> patientDetailList=new ArrayList<UserLoginDetail>();
		DoctorDetail doctorDetail=findDoctorDetailData();
		try {
			List<UserLoginDetail> patientDetailData=new ArrayList<UserLoginDetail>();
			for(UserLoginDetail userDetail:selectedUserDetailForReport){
				List<PatientMedicationData> patMedList=new ArrayList<PatientMedicationData>();
				List<PatientDiagnosesDetails> patDiagnosisList=new ArrayList<PatientDiagnosesDetails>();
				List<SendMessageEditRx> patSendMessageEditRxList=new ArrayList<SendMessageEditRx>();
				List<ProcedureResultHistory> patLabResultList=new ArrayList<ProcedureResultHistory>();
				List<BatchInteractionAnalytic> interactionHistoryData=new ArrayList<BatchInteractionAnalytic>();
				List<DrugInteractionOverview> drugInteractionsOverviewList=new ArrayList<DrugInteractionOverview>();
				 
				 
				   patientDetailData=getPatientDetailDataForBatchReport(userDetail.getUserId(),providerId);
				   // Get Data of Med Detail for Batch Report
				   patMedList=getPatMedDetailForBatchReport(userDetail.getUserId(),providerId);
				   // Get Patient Vital Data 
					Criteria patVitalCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientVital.class);
					patVitalCriteria.add(Restrictions.eq("patientIdForVital", userDetail.getUserId()));
					patVitalCriteria.addOrder(Order.desc("id"));
					patVitalCriteria.setMaxResults(1);
					// Get Patient Vital Data 
					PatientVital vital=(PatientVital)patVitalCriteria.uniqueResult();
					// Get Diagnosis Detail Data 
					
					patDiagnosisList=getPatDiagnosisDetaildataForBatchReport(userDetail.getUserId(),providerId);
					
					//Get Reconsile Info Data 
					Criteria reconsileInfoCriteria=getSessionFactory().getCurrentSession().createCriteria(ReconcileInfo.class);
					reconsileInfoCriteria.add(Restrictions.eq("patientId", userDetail.getUserId()));
					reconsileInfoCriteria.add(Restrictions.eq("status", true));
					reconsileInfoCriteria.addOrder(Order.desc("lastReconciledDate"));
					reconsileInfoCriteria.setMaxResults(1);
					ReconcileInfo reconcileInfo=(ReconcileInfo)reconsileInfoCriteria.uniqueResult();
					
					
					String bmiMessage=findBodyMassIndex(vital.getHeight(),vital.getWeight(),userDetail.getGender());
					
					Criteria sendMessageEditRxCritirea=getSessionFactory().getCurrentSession().createCriteria(SendMessageEditRx.class);
					sendMessageEditRxCritirea.add(Restrictions.eq("patientId", userDetail.getUserId()));
					patSendMessageEditRxList=sendMessageEditRxCritirea.list();
					logger.info("patSendMessageEditRxList.size()"+patSendMessageEditRxList.size());
					
					
					Criteria patLabCrietria=getSessionFactory().getCurrentSession().createCriteria(ProcedureResultHistory.class);
					patLabCrietria.add(Restrictions.eq("patientId",userDetail.getUserId()));
					patLabResultList=patLabCrietria.list();
					
					//For Max Date Query 
					DetachedCriteria maxQuery = DetachedCriteria.forClass(BatchInteractionAnalytic.class);
					maxQuery.add(Restrictions.eq("patientId", userDetail.getUserId()));
					/*maxQuery.add(Restrictions.eq("providerId", providerId));*/ //Commented for test
					maxQuery.setProjection( Projections.max( "batchPullDate"));
					
					Criteria batchIteractionDataCriteria=getSessionFactory().getCurrentSession().createCriteria(BatchInteractionAnalytic.class);
					batchIteractionDataCriteria.add(Restrictions.eq("patientId", userDetail.getUserId()));
					/*batchIteractionDataCriteria.add(Restrictions.eq("providerId", providerId));*/ //Commented for test  
					batchIteractionDataCriteria.add(Property.forName("batchPullDate").eq(maxQuery));
					
					interactionHistoryData=  batchIteractionDataCriteria.list();
					
					logger.info("interactionHistoryData List Sizxe"+interactionHistoryData.size());
				
					for(BatchInteractionAnalytic batchInteractionAnalytic:interactionHistoryData){
						for(BatchPatientMedsHistory batchPatientMedsHistory:batchInteractionAnalytic.getBatchPatientMedsHistories()){
							Set<Integer>severityLevels=new TreeSet<Integer>();
							severityLevels=batchInteractionBean.getMaximumSeverityLevels(batchPatientMedsHistory);
							if(severityLevels!=null && !severityLevels.isEmpty()){
							int[]severeLevels=Ints.toArray(severityLevels);
							String highestSevereLevels=String.valueOf(severeLevels[0]);
							logger.info("Highest Severeity levels "+highestSevereLevels+"Batch Pull Max Date"+batchInteractionAnalytic.getBatchPullDate());
							batchPatientMedsHistory.setSeverityLevel(highestSevereLevels);
							if(Integer.parseInt(batchPatientMedsHistory.getSeverityLevel())==1){
								List<DrugInteractionOverview> drugInteractions = getDrugInteractionsReport(batchPatientMedsHistory.getDrugInteractions());
								List<DrugInteractionOverview> diseaseInteractions=getDiseaseInteractionsReport( batchPatientMedsHistory.getDiseaseInteractions());
								//List<DrugInteractionOverview> allergyInteractions=getAllergyInteractionsReport(batchPatientMedsHistory.getAllergyInteractions());
								drugInteractionsOverviewList.addAll(drugInteractions);
								drugInteractionsOverviewList.addAll(diseaseInteractions);
							}
							
							
							}
						}
					}
					
					for(PatientMedicationData patMedData:patMedList){
						ArrayList<Integer> severityDataList=new ArrayList<Integer>();
						int maxSeverity=0;
						List<DrugInteractionOverview> drugInteractionsList=new ArrayList<DrugInteractionOverview>();
						for(BatchInteractionAnalytic batchInteractionAnalytic:interactionHistoryData){
							for(BatchPatientMedsHistory batchPatientMedsHistory:batchInteractionAnalytic.getBatchPatientMedsHistories()){
								logger.info("pat Med Drug Id"+patMedData.getDrugId()+"batch patient med Drug Id"+batchPatientMedsHistory.getDrugId());
								if(patMedData.getDrugId()==batchPatientMedsHistory.getDrugId()){
									Set<Integer>severityLevels=new TreeSet<Integer>();
									severityLevels=batchInteractionBean.getMaximumSeverityLevels(batchPatientMedsHistory);
									if(severityLevels!=null && !severityLevels.isEmpty()){
									int[]severeLevels=Ints.toArray(severityLevels);
									String highestSevereLevels=String.valueOf(severeLevels[0]);
									severityDataList.add(Integer.valueOf(highestSevereLevels));
									logger.info("Highest Severeity levels "+highestSevereLevels);
									batchPatientMedsHistory.setSeverityLevel(highestSevereLevels);
									List<DrugInteractionOverview> drugInteractions = getDrugInteractionsReport(batchPatientMedsHistory.getDrugInteractions());
									List<DrugInteractionOverview> diseaseInteractions=getDiseaseInteractionsReport( batchPatientMedsHistory.getDiseaseInteractions());
									//List<DrugInteractionOverview> allergyInteractions=getAllergyInteractionsReport(batchPatientMedsHistory.getAllergyInteractions());
									drugInteractionsList.addAll(drugInteractions);
									drugInteractionsList.addAll(diseaseInteractions);
									
									
								}
									
									logger.info("drugInteractionsList.size()"+drugInteractionsList.size());
								   
								}
							}
						}
						
						for(Integer i:severityDataList){
							if(i>maxSeverity){
								maxSeverity=i;
							}
						}
						logger.info("Max Severity"+maxSeverity+" "+severityDataList.size()+severityDataList.toArray());
						patMedData.setMaxSeverity(maxSeverity);
						patMedData.setTotalCount(Integer.toString(drugInteractionsList.size()));
					}
					
					for(UserLoginDetail patientDetail:patientDetailData){
						UserLoginDetail patDetail=new UserLoginDetail();
						patDetail.setFirstName(patientDetail.getFirstName());
						patDetail.setLastName(patientDetail.getLastName());
						patDetail.setMiddleName(patientDetail.getMiddleName());
						patDetail.setDateOfBirth(patientDetail.getDateOfBirth());
						patDetail.setPatientMedicationDataList(patMedList);
						if(StringUtils.isNotEmpty((Double.toString(vital.getWeight()))) && (vital.getWeight() !=0.0)){
							patDetail.setWeight(Double.toString(vital.getWeight()));
						}
						else if (!(StringUtils.isNotEmpty((vital.getWeight().toString())))) {
							patDetail.setWeight("");
						}
						
						if(StringUtils.isNotEmpty(vital.getHeight()) && !(vital.getHeight().equals(0.00))){
							patDetail.setHeight(vital.getHeight());
						}
						else if (!(StringUtils.isNotEmpty(vital.getHeight())) || (vital.getHeight().equals(0.00))) {
							patDetail.setHeight("");
						}
						if(StringUtils.isNotEmpty(Integer.toString(vital.getPulse())) && (vital.getPulse() != 0)){
							patDetail.setPulse((Integer.toString(vital.getPulse())));
						}
						else if (!(StringUtils.isNotEmpty(Integer.toString(vital.getPulse())))) {
							patDetail.setPulse("");
						}
						if(StringUtils.isNotEmpty(Double.toString(vital.getTemperature())) && (vital.getTemperature() !=0.0)  ){
							patDetail.setTemperature(Double.toString(vital.getTemperature()));
						}
						else if (!(StringUtils.isNotEmpty(Double.toString(vital.getTemperature())))) {
							patDetail.setTemperature("");
						}
						
						
						if(StringUtils.isNotEmpty(Integer.toString(vital.getSystolic())) && (vital.getSystolic() != 0) ){
							patDetail.setSystolic(Integer.toString(vital.getSystolic()));
						}
						else if (!(StringUtils.isNotEmpty(Integer.toString(vital.getSystolic()))) || (vital.getSystolic()==0)) {
							patDetail.setSystolic("");
							
						}
						if(StringUtils.isNotEmpty(Integer.toString(vital.getDiastolic())) && (vital.getDiastolic() !=0)){
							patDetail.setDiastolic(Integer.toString(vital.getDiastolic()));
						}
						else if (!(StringUtils.isNotEmpty(Integer.toString(vital.getDiastolic()))) || (vital.getDiastolic()==0)) {
							patDetail.setDiastolic("");
						}
						if(!(bmiMessage.equals(0.0)) && !(bmiMessage.equals(0.0))){
							patDetail.setBmiRange(bmiMessage);
								}
						else if (bmiMessage.equals(0.0)) {
							patDetail.setBmiRange("");
						}
						if(!(reconcileInfo== null)){
							if(!(reconcileInfo.getLastReconciledDate()==null)){
								patDetail.setLastReconciledDate(new SimpleDateFormat("yyyy-MM-dd").format(reconcileInfo.getLastReconciledDate()));
							}
							else if (reconcileInfo.getLastReconciledDate()==null) {
								patDetail.setLastReconciledDate("");
							}
							
						}
					   
						patDetail.setDocFName(doctorDetail.getfName());
						patDetail.setDocLastName(doctorDetail.getlName());
						patDetail.setDocMidName(doctorDetail.getMidName());
						if(StringUtils.isNotEmpty(doctorDetail.getPhoneNumber())){
							patDetail.setDocPhoneNo(doctorDetail.getPhoneNumber());
						}
						if(doctorDetail.getPhoneNumber() == null){
							patDetail.setDocPhoneNo("");
						}
						
						patDetail.setDocStreet(doctorDetail.getStreet());
						patDetail.setDocCity(doctorDetail.getCity());
						patDetail.setPatDiagnosisList(patDiagnosisList);
						patDetail.setPatSendMessageEditRxList(patSendMessageEditRxList);
						patDetail.setPatLabReslutList(patLabResultList);
						patDetail.setDrugInteractionOverviewList(drugInteractionsOverviewList);
						
						
						
						patientDetailList.add(patDetail);
						
					}
					
					for(UserLoginDetail uld:patientDetailList){
						System.out.println(" user detail "+uld.getPatLabReslutList().size());
						logger.info("uld.getDrugInteractionOverviewList().size()"+uld.getDrugInteractionOverviewList().size());
						for(DrugInteractionOverview druIn:uld.getDrugInteractionOverviewList()){
							logger.info("Clinical Effect "+druIn.getClinicalEffects()+" Drug Name "+druIn.getDrugName());
						}
						
						
					}
				  
			}
		} catch (HibernateException hbe) {
			hbe.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
			   
		return patientDetailList;
	}
	private List<UserLoginDetail> getPatientDetailDataForBatchReport(int userId, int providerId) {
	// TODO Auto-generated method stub
		List<UserLoginDetail>  patientDetailData=new ArrayList<UserLoginDetail>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			   criteria.add(Restrictions.eq("userId", userId));
			   
			   patientDetailData=criteria.list();	
		} catch (HibernateException he) {
			// TODO: handle exception
			he.printStackTrace();
			logger.info("cauth Exception in getPatientDetailDataForBatchReport");
			
		}
		  
	return patientDetailData;
}

	/**
	 * Get Patient Diagnosis Detail For Selected Patient
	 * For Batch Report  
	 * @author Anjani
	 * @param userId
	 * @param providerId
	 * @return
	 */
	private List<PatientDiagnosesDetails> getPatDiagnosisDetaildataForBatchReport(int userId, int providerId) {
	// TODO Auto-generated method stub
		List<PatientDiagnosesDetails> patDiagnosisList=new ArrayList<PatientDiagnosesDetails>();
		try {
			Criteria diagonosisCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientDiagnosesDetails.class);
			diagonosisCriteria.add(Restrictions.eq("patientId", userId));
			patDiagnosisList=diagonosisCriteria.list();
		} catch (HibernateException he) {
			// TODO: handle exception
			he.printStackTrace();
		}
	return patDiagnosisList;
}

	/**
	 * Get Med Data based On Patient Id
	 * For Patient Batch report 
	 * @author Anjani
	 * @param userId
	 * @param providerId
	 * @return
	 */
	private List<PatientMedicationData> getPatMedDetailForBatchReport(int userId, int providerId) {
	// TODO Auto-generated method stub
		List<PatientMedicationData> patMedList=new ArrayList<PatientMedicationData>();
		try {
			Criteria medDetailCriteria=getSessionFactory().getCurrentSession().createCriteria(PatientMedicationData.class);
			  
			medDetailCriteria.add(Restrictions.eq("patientId", userId));
			/*medDetailCriteria.add(Restrictions.eq("providerId", providerId));*/ //Commented for test
			patMedList=medDetailCriteria.list();
		} catch (HibernateException he) {
			he.printStackTrace();
			logger.info("Exception Caught in getPatMedDetailForBatchReport");
		}
		
	return patMedList;
}

	private List<DrugInteractionOverview> getAllergyInteractionsReport(
		List<BatchAllergyInteraction> allergyInteractions) {
	// TODO Auto-generated method stub
	return null;
}

	private List<DrugInteractionOverview> getDiseaseInteractionsReport(
		List<BatchDiseaseInteraction> diseaseInteractions) {
		List<DrugInteractionOverview>diseaseInteractionsData=new ArrayList<DrugInteractionOverview>();
		for(BatchDiseaseInteraction drugDiseaseDetail:diseaseInteractions){
			DrugInteractionOverview drugInteractionOverview =new DrugInteractionOverview();
			drugInteractionOverview.setDrugName(drugDiseaseDetail.getDrugName());
			drugInteractionOverview.setClinicalEffects("");
			drugInteractionOverview.setConflict(drugDiseaseDetail.getInteractionText());
			drugInteractionOverview.setIssueType(DRUG_DISEASE_INTERACTION);
			

			drugInteractionOverview.setPatientManagement(drugDiseaseDetail.getSeverityLevelText());
			if(StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_DISEASE)){
				drugInteractionOverview.setSeverityLevel(HIGH_SEVERITY_LEVEL);
				drugInteractionOverview.setSeverityLevelToolTip(drugDiseaseDetail.getSeverityLevelShortText());
			}else if((StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_RELATIVE))||
					(StringUtils.equals(drugDiseaseDetail.getSeverityLevelShortText(), DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRADICATION))){
				drugInteractionOverview.setSeverityLevel(MEDIUM_SEVERITY_LEVEL);
				drugInteractionOverview.setSeverityLevelToolTip(drugDiseaseDetail.getSeverityLevelShortText());
			}
			diseaseInteractionsData.add(drugInteractionOverview);
		}
		return diseaseInteractionsData;
}

	private List<DrugInteractionOverview> getDrugInteractionsReport(
		List<BatchDrugInteraction> drugInteractions) {
		List<DrugInteractionOverview>drugInteractionsData=new ArrayList<DrugInteractionOverview>();
		for(BatchDrugInteraction drugInteractionData:drugInteractions){
			DrugInteractionOverview drugInteractionOverview=new DrugInteractionOverview();
			drugInteractionOverview.setClinicalEffects(drugInteractionData.getClinicalEffects());
			drugInteractionOverview.setPatientManagement(drugInteractionData.getPatientManagement());
			drugInteractionOverview.setIssueType(DRUG_DRUG_INTERECTION);
			logger.info("Drug 1 Name"+drugInteractionData.getDrug1Name()+"Clinacla effect"+drugInteractionData.getClinicalEffects());
			drugInteractionOverview.setDrugName(drugInteractionData.getDrug1Name());
			drugInteractionOverview.setConflict(drugInteractionData.getDrug2Name());
			
			drugInteractionOverview.setSeverityLevel(drugInteractionData.getSeverityLevel());
			drugInteractionOverview.setSeverityLevelToolTip(DRUG_SEVERITY_TOOLTIP);//Need to modify
			drugInteractionsData.add(drugInteractionOverview);
		}
		return drugInteractionsData;
}



	


	public String findBodyMassIndex(String heightInString, double weight, String gender) {
		
		PatientVital patientVital=new PatientVital();

		logger.info("findBodyMassIndex method in dashboard manage bean start::"
				+ heightInString +"gender::::"+gender);
	
		double massLB = weight * 2.2046;
		System.out.println("=====weight==========="+weight);
		double massLbRound = Math.round(massLB * 100.0) / 100.0;
		patientVital.setWeight(massLbRound);
		int heightInInch = 0;
		double bmi,bmi1 = 0;
		// String heightInString=Double.toString(height);

		String feet, inch;
		// int x=heightInString.lastIndexOf(".");
		if(heightInString!=null)
			{
			if(heightInString.contains("."))
			{
				if(!heightInString.substring(0, heightInString.indexOf(".")).isEmpty())
				{
			feet = heightInString.substring(0, heightInString.indexOf("."));
				}
				else
				{
					feet="0";
				}
				if(!heightInString.substring(heightInString.indexOf(".") + 1,
				heightInString.length()).isEmpty())
				{
			inch = heightInString.substring(heightInString.indexOf(".") + 1,
				heightInString.length());
				}
				else
				{
					inch="0";
				}
			heightInInch = ((Integer.parseInt(feet) * 12) + (Integer.parseInt(inch)));
			patientVital.setHeight(feet+"."+inch);
			}
			else
			{
				feet = heightInString;
				inch = "0";
				heightInInch = ((Integer.parseInt(feet) * 12) + (Integer.parseInt(inch)));
				patientVital.setHeight(feet+"."+inch);
				
			}
			System.out.println(massLB+">>>>>>>>>>>>>>>>>>>>heightInInch:::"+heightInInch);
			bmi = (massLB * 703) / (heightInInch * heightInInch);
			bmi1 = Math.round(bmi * 100.0) / 100.0;
			}
		System.out.println(":::::::::bmi:::"+bmi1);
		patientVital.setBodyMassIndex(bmi1);
		int bmiForCheck = (int) (bmi1 * 100);
		String message = "";
		double IBW = 0;
		
		double normalBMIMinweightInLB, normalBMIMaxweightInLB, roundnormalBMIMinweightInLB, roundnormalBMIMaxweightInLB;
		
		
		
		if(heightInString!=null)
		{
					if(gender.equalsIgnoreCase("Female"))
					{
						IBW = 45.5+(heightInInch-60)*2.3;
						System.out.println("IBW::::Female:details:::"+IBW);
						IBW=IBW*2.20462;
						IBW=(double)Math.round(IBW * 100) / 100;
						patientVital.setBmiRange(""+IBW);
					}
					if(gender.equalsIgnoreCase("Male"))
					{
						IBW = 50+(heightInInch-60)*2.3;
						System.out.println("IBW:::Male::details:::"+IBW);
						IBW=IBW*2.20462;
						IBW=(double)Math.round(IBW * 100) / 100;
						patientVital.setBmiRange(""+IBW);
					}
					
					System.out.println("bmiForCheck:::::::"+bmiForCheck);
					if (bmiForCheck < 1850) {
						message = UNDERWEIGHT;
					}
					if (bmiForCheck >= 1850 && bmiForCheck < 2490) {
						message = NORMAL_WEIGHT;
					}
			
					if (bmiForCheck >= 2500 && bmiForCheck < 2990) {
						message = OVER_WEIGHT;
					}
			
					
					if (bmiForCheck >= 3000) {
						message = OBESE;
					}
		}
					

		String bmiMessage=Double.toString(bmi1);
		
		return bmiMessage;
		

	}

/*****@author Saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findProviderLocationForMultiThreading(int, java.lang.String)
 */
@Override
public ProviderLocation findProviderLocationForMultiThreading(int providerIdForMultiThreading,
		String loggedInRoleForMultiThreading) {
	ProviderLocation providerLocationForMultiThreading=new ProviderLocation();
	if (loggedInRoleForMultiThreading.equalsIgnoreCase("ROLE_ADMIN")) {
		providerLocationForMultiThreading=(ProviderLocation) getSessionFactory().getCurrentSession().get(ProviderLocation.class, providerIdForMultiThreading);
	}
	return providerLocationForMultiThreading;

}

/***@author:Saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findDoctorDetailDataForMultiThreading(int, int, java.lang.String, int)
 */
@Override
public DoctorDetail findDoctorDetailDataForMultiThreading(int providerIdForMultiThreading,
		int providerLocationIdForMultiThreading, String loggedInRoleForMultiThreading, int loginIdForMultiThreading) {
	logger.info("inside findDoctorDetailDataForMultiThreading DAO::::providerLocationIdForMultiThreading= "+providerLocationIdForMultiThreading+
			" =providerLocationIdForMultiThreading= "+providerLocationIdForMultiThreading+" =loggedInRoleForMultiThreading= "+loggedInRoleForMultiThreading+
			" =loginIdForMultiThreading= "+loginIdForMultiThreading);
	Integer docLoginId=loginIdForMultiThreading;
	DoctorDetail doctorDetailForMultiThreading=new DoctorDetail();
	List<AdminDetails> adminDetailListForMultiThreading=new ArrayList<AdminDetails>();
	try{
	if (loggedInRoleForMultiThreading.equalsIgnoreCase("ROLE_ADMIN")) {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(AdminDetails.class);
		criteria.add(Restrictions.eq("providerId", providerIdForMultiThreading));
		criteria.add(Restrictions.eq("providerLocationId", providerLocationIdForMultiThreading));
		adminDetailListForMultiThreading=criteria.list();
		if(!(adminDetailListForMultiThreading.size()==0)){
			for(AdminDetails admin:adminDetailListForMultiThreading){
				doctorDetailForMultiThreading.setUpin(admin.getUpin());
				doctorDetailForMultiThreading.setSpeciality(admin.getSpeciality());
				doctorDetailForMultiThreading.setNpi(admin.getNpi());
				doctorDetailForMultiThreading.setDocLicenseState(admin.getDocLicenseState());
				doctorDetailForMultiThreading.setDocLicenseNumber(admin.getDocLicenseNumber());
				doctorDetailForMultiThreading.setDegree(admin.getDegree());
				doctorDetailForMultiThreading.setDea(admin.getDea());
				doctorDetailForMultiThreading.setCity(admin.getCity());
				doctorDetailForMultiThreading.setfName(admin.getfName());
				doctorDetailForMultiThreading.setMidName(admin.getMidName());
				doctorDetailForMultiThreading.setlName(admin.getlName());
				doctorDetailForMultiThreading.setState(admin.getState());
				doctorDetailForMultiThreading.setCountry(admin.getState());
				doctorDetailForMultiThreading.setStreet(admin.getCountry());
				doctorDetailForMultiThreading.setPhoneNumber(admin.getPhoneNumber());
				doctorDetailForMultiThreading.setPincode(admin.getPincode());
				doctorDetailForMultiThreading.setDoorNo(admin.getDoorNo());
				doctorDetailForMultiThreading.setEmailId(admin.getEmailId());
				doctorDetailForMultiThreading.setUserId(admin.getUserId());
	         }
		      }
		for (AdminDetails d : adminDetailListForMultiThreading) {
			logger.info(":::findDoctorDetailData::::"+d.getId()+d.getDegree());
		}
	}
	}catch(NullPointerException ne){
		logger.error("::::EXCEPTION IN findDoctorDetailDataForMultiThreading::::"+ne.getMessage());
	}
	return doctorDetailForMultiThreading;
}

@Override
public UserLoginDetail findPatientDetailData(Integer patient_Id, int providerId) {
	UserLoginDetail patientDetailData =new UserLoginDetail();
	try{
	 patientDetailData=(UserLoginDetail) getSessionFactory().getCurrentSession().get(UserLoginDetail.class, patient_Id);
	}catch(HibernateException he){
		he.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return patientDetailData;
}

/*****@author Saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findNumberOfPatientsHavingPbmElegiblity(int)
 */
@Override
public Long findNumberOfPatientsHavingPbmElegiblity(int loginIdForMultiThreading) {
	Long countPbmExist ;
	try {
		Query hqlQuery=getSessionFactory().getCurrentSession().
				createQuery("select count(*) FROM PatientPbmDrugHistoryDetail WHERE pulledBy= :pulledById and pbmExist= :valueOfpbmExist");
		hqlQuery.setParameter("pulledById", loginIdForMultiThreading);
		hqlQuery.setParameter("valueOfpbmExist", true);
		countPbmExist = (Long)hqlQuery.uniqueResult();
	} catch (Exception e) {
		//e.printStackTrace();
		logger.error("======EXCEPTION IN findNumberOfPatientsHavingPbmElegiblity USER DAOIMPL===================", e);
		countPbmExist = (long) 0;
		logger.info("======DUE TO EXCEPTION IN findNumberOfPatientsHavingPbmElegiblity UserDao NEW VALUE ASSIGN TO COUNT =================== "+ countPbmExist);
	}	
	return countPbmExist;
}

/*****@author Saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findNumberOfpulledDrugs(int)
 */
@Override
public Long findNumberOfpulledDrugs(int loginIdForMultiThreading) {
	Long countDrugHistoryExist ;
	try {
		Query hqlQuery=getSessionFactory().getCurrentSession().
				createQuery("select count(*) FROM PatientPbmDrugHistoryDetail WHERE pulledBy= :pulledById and drugHistoryExist= :valueOfDrugHistoryExist");
		hqlQuery.setParameter("pulledById", loginIdForMultiThreading);
		hqlQuery.setParameter("valueOfDrugHistoryExist", true);
		countDrugHistoryExist = (Long)hqlQuery.uniqueResult();
	} catch (Exception e) {
		logger.error("======EXCEPTION IN findNumberOfpulledDrugs USER DAOIMPL===================", e);
		countDrugHistoryExist = (long) 0;
		logger.info("======DUE TO EXCEPTION IN findNumberOfpulledDrugs USERDAO NEW VALUE ASSIGN TO countDrugHistoryExist =================== "+ countDrugHistoryExist);
	}	
	return countDrugHistoryExist;
}

@Override
public List<UserLoginDetail> getPatientsHavingDevices(int providerId,boolean isConnectedDevices) {
	List<UserLoginDetail> patientDetailList=new ArrayList<UserLoginDetail>();
	try {
		String sql_query="select ud.Id as id ,ud.user_id as user_id, AES_DECRYPT(ud.first_name,'"+DecryptionKey+"') as first_name,AES_DECRYPT(ud.last_name,'"+DecryptionKey+"') as last_name from user_details ud join patient_provider pr on pr.patient_id=ud.user_id "+
						  "join login_security ls on ls.id=ud.user_id where ls.role_id=3 and ls.status=1 and pr.provider_id= "+providerId+" and ud.is_connected_to_devices = "+isConnectedDevices;
		/*SQLQuery sqlQuery=getSessionFactory().getCurrentSession().createSQLQuery(sql_query);
		sqlQuery.setParameter("providerId", new ContextUtil().getProviderId());
		sqlQuery.addEntity(UserLoginDetail.class);
		patientDetailList = sqlQuery.list();*/
		
		PreparedStatement statement=getSessionFactory().getCurrentSession().connection().prepareStatement(sql_query);
		ResultSet resultSet=statement.executeQuery();
		while (resultSet.next()) {
			UserLoginDetail userDetail=new UserLoginDetail();
			userDetail.setId(resultSet.getInt("id"));
			userDetail.setUserId(resultSet.getInt("user_id"));
			userDetail.setFirstName(resultSet.getString("first_name"));
			//userDetail.setMiddleName(resultSet.getString("middle_name"));
			userDetail.setLastName(resultSet.getString("last_name"));
		/*	userDetail.setGender(resultSet.getString("gender"));
			userDetail.setDateOfBirth(resultSet.getDate("date_of_birth"));
			userDetail.setState(resultSet.getString("state"));
			userDetail.setPatientBatchCreatedDate(resultSet.getDate("created_on"));
			userDetail.setPatientBatchNo(resultSet.getInt("group_upload_id"));
			userDetail.setPatientConsent(resultSet.getString("patient_consent").charAt(0));
			userDetail.setPhoneNumber(resultSet.getString("patient_telephone"));
			userDetail.setDoorNo(resultSet.getString("address1"));
			userDetail.setStreet(resultSet.getString("address2"));
			userDetail.setCity(resultSet.getString("city"));
			userDetail.setCountry(resultSet.getString("country"));
			userDetail.setPincode(resultSet.getString("pin_code"));
			
			userDetail.setPbmDrugHistoryDetailStatus(resultSet.getBoolean("pbm_drug_history_detail_status"));
			userDetail.setPbmDrugHistoryResultStatus(resultSet.getBoolean("pbm_drug_history_result_status"));*/
			
			patientDetailList.add(userDetail);
			
		}
}catch(Exception e){
	e.printStackTrace();
}
	return patientDetailList;
}

@Override
public UserLoginDetail getPatientDetails(int providerId, int patientId, boolean isPatientActive) {
	UserLoginDetail userDetail = null;
	try{
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
		criteria.add(Restrictions.eq("userId", patientId));
		criteria.add(Restrictions.eq("status",isPatientActive));
		userDetail = (UserLoginDetail) criteria.uniqueResult();
	}catch(HibernateException e){
		userDetail=new UserLoginDetail();
		e.printStackTrace();
	}
	return userDetail;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDiagnosis(
		String diagnosis, String decryptionkey) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_diagnosis('"+diagnosis+"' , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}

private List<PracticeSearchResultSet> getPracticeColumnData(ResultSet resultSet) throws SQLException {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	while(resultSet.next()){
		PracticeSearchResultSet resultObj = new PracticeSearchResultSet();
		resultObj.setPatientId(resultSet.getInt("patient_id"));
		resultObj.setFirstName(resultSet.getString("first_name"));
		resultObj.setLastName(resultSet.getString("last_name"));
		resultObj.setProviderName(resultSet.getString("practice_name"));
		practiceSearchData.add(resultObj);
	}
	return practiceSearchData;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDrugByName(String drugs,
		String decryptionkey) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_drugs_by_name('"+drugs+"' , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDrugById(Double drugId,
		String decryptionkey) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_drugs_by_id("+drugId+" , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}



/**
 * @return the batchInteractionBean
 */
public BatchInteractionBean getBatchInteractionBean() {
	return batchInteractionBean;
}

/**
 * @param batchInteractionBean the batchInteractionBean to set
 */
public void setBatchInteractionBean(BatchInteractionBean batchInteractionBean) {
	this.batchInteractionBean = batchInteractionBean;
}





@Override
public List<UserLoginDetail> getAllPatientDataforProvider(int providerId,
		String decryptionkey) {
	List<UserLoginDetail> patientData = new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_patient_data("+providerId+" , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		patientData = getPatientColumnData(resultSet);
	}catch(SQLException s){
		s.printStackTrace();
	}
	return patientData;
}

private List<UserLoginDetail> getPatientColumnData(ResultSet resultSet) throws SQLException {
	List<UserLoginDetail> patientData = new ArrayList<UserLoginDetail>();
	while(resultSet.next()){
		UserLoginDetail resultObj = new UserLoginDetail();
		resultObj.setUserId(resultSet.getInt("patient_id"));
		resultObj.setFirstName(resultSet.getString("first_name"));
		resultObj.setLastName(resultSet.getString("last_name"));
		resultObj.setProviderId(resultSet.getInt("provider_id"));
		patientData.add(resultObj);
	}
	return patientData;
}

@Override
public boolean deletePatientAllData(int providerId, int userId) {

	
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_delete_patient_all_data("+providerId+" , "+userId+")}");
		ResultSet resultSet = statement.executeQuery();
		return true;
	}catch(SQLException s){		
		s.printStackTrace();
		return false;
	}
	
}

@Override
public boolean insertPatientAllDataToDeactivation(int providerId, int userId) {
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_insert_deactivated_patient_all_data("+providerId+" , "+userId+")}");
		ResultSet resultSet = statement.executeQuery();
		return true;
	}catch(SQLException s){		
		s.printStackTrace();
		return false;
	}
}

@Override
public boolean saveAuditForPatientDeactivation(
		List<AuditPatientDeactivation> auditPatientDeactiveRecords) {
	Session session = null;
	try{
		session = getSessionFactory().openSession();
		Transaction transaction =  session.beginTransaction();
		for(int i= 0; i<auditPatientDeactiveRecords.size();i++){
			session.save(auditPatientDeactiveRecords.get(i));
			if( i % 50 == 0){
				session.flush();
				session.clear();
			}
		}
		transaction.commit();		
		return true;
	}catch(HibernateException e){
		e.printStackTrace();
		return false;
	}finally{
		if(session != null){
			session.close();
		}
	}
	
}

@Override
public List<UserLoginDetail> getDeactivatedPatients(int providerId,
		String decryptionkey) {
	List<UserLoginDetail> deactivatedPatients = new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_deactivated_patient("+providerId+" , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			UserLoginDetail resultObj = new UserLoginDetail();
			//resultObj.setUserId(resultSet.getInt("patient_id"));
			resultObj.setDeactivatedDate(resultSet.getDate(1));
			resultObj.setFirstName(resultSet.getString(2));
			resultObj.setLastName(resultSet.getString(3));
			resultObj.setUserFirstName(resultSet.getString(4));
			resultObj.setUserLastName(resultSet.getString(5));
			resultObj.setUserId(resultSet.getInt(6));
			resultObj.setProviderId(resultSet.getInt(7));
			/*resultObj.setProviderId(resultSet.getInt("provider_id"));*/
			deactivatedPatients.add(resultObj);
		}
	}catch(SQLException s){
		s.printStackTrace();
	}
	return deactivatedPatients;
}

/****@author Saurabh
 * (non-Javadoc) For updating pbmDrugHistoryDetail & pbmDrugHistoryResult table
 * making boolean-false entry for old pulled records
 * PbmExist  & DrugHistoryExist 
 * @see com.clinakos.data.dao.IUserDao#resetOldRecords(int)
 */
@Override
public void resetOldRecords(int loginIdForMultiThreading) {
	try {
		
	//for pbmDrugHistoryDetail
		Query query=getSessionFactory().getCurrentSession().createQuery("update PatientPbmDrugHistoryDetail set pbmExist=:resetPbmFlag ,"
				+ "drugHistoryExist=:resetDrugFlag where pulledBy=:pulledById");
		query.setParameter("pulledById", loginIdForMultiThreading);
		query.setParameter("resetPbmFlag", false);
		query.setParameter("resetDrugFlag", false);
		int pbmDrugHistoryDetail=query.executeUpdate();
	
	//for pbmDrugHistoryResult
		Query query2=getSessionFactory().getCurrentSession().createQuery("update PatientPBMDrugHistoryResult set drugHistoryExist=:resetDrugFlag2"
				+ " where pulledBy=:pulledById2");
		query2.setParameter("pulledById2", loginIdForMultiThreading);
		query2.setParameter("resetDrugFlag2", false);
		int pbmDrugHistoryResult=query2.executeUpdate();
	}catch(HibernateException he){
		he.printStackTrace();} 
	catch (Exception e) {
		e.printStackTrace();
	}
}

@Override
public Long findPatientHaveMedications(int loginIdForMultiThreading) {
	Long countDrugHistoryExist ;
	try {
		Query hqlQuery=getSessionFactory().getCurrentSession().
				createQuery("select count(*) FROM PatientPBMDrugHistoryResult WHERE pulledBy= :pulledById and drugHistoryExist= :valueOfDrugHistoryExist");
		hqlQuery.setParameter("pulledById", loginIdForMultiThreading);
		hqlQuery.setParameter("valueOfDrugHistoryExist", true);
		countDrugHistoryExist = (Long)hqlQuery.uniqueResult();
	} catch (Exception e) {
		logger.error("======EXCEPTION IN findPatientHaveMedications USER DAOIMPL===================", e);
		countDrugHistoryExist = (long) 0;
		logger.info("======DUE TO EXCEPTION IN findPatientHaveMedications USERDAO NEW VALUE ASSIGN TO countDrugHistoryExist =================== "+ countDrugHistoryExist);
	}	
	return countDrugHistoryExist;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesDiagnosisById(
		String diagnosis) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_diagnosis_by_icd('"+diagnosis+"' , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesLabByName(String lab,
		String decryptionkey) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_lab_by_name('"+lab+"' , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}

@Override
public List<PracticeSearchResultSet> findAllPracticesLabByLoincCode(String lab,
		String decryptionkey) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_lab_by_code('"+lab+"' , "+"'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}

@Override
public List<PracticeSearchResultSet> findPracticeDataBasedOnDate(Date fromDate,
		Date toDate) {
	List<PracticeSearchResultSet> practiceSearchData = new ArrayList<PracticeSearchResultSet>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_practice_search_by_date('"+fromDate.toString()+"' , '"+toDate.toString()+"','"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		practiceSearchData = getPracticeColumnData(resultSet);
		
	
	}catch(SQLException e){
		e.printStackTrace();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return practiceSearchData;
}
	
}
