package com.clinakos.data.dao.daoImpl;


import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;
import static com.clinakos.common.util.ClinakosConstant.YES;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.web.util.UriUtils;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.NotificationUtil;
import com.clinakos.data.dao.IDashBoardDAO;
import com.clinakos.data.model.core.DiagnosisUploadData;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.core.ForgotPasswordSendingLink;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.LoginSecurity;
import com.clinakos.data.model.core.OrganisationType;
import com.clinakos.data.model.core.PatientUploadData;
import com.clinakos.data.model.core.PatientUploadDataErrorMessages;
import com.clinakos.data.model.core.ProviderDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.ProviderUser;
import com.clinakos.data.model.core.ResultProcNetworkChart;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.core.RptDrugCategory;
import com.clinakos.data.model.patient.ChartModel;
import com.clinakos.data.model.patient.ClinicDoctor;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.ClinicProvider;
import com.clinakos.data.model.patient.DiagnosesChart;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.DrugCategoryDetail;
import com.clinakos.data.model.patient.EmployerDetails;
import com.clinakos.data.model.patient.FormularyChart;
import com.clinakos.data.model.patient.Icd9Diagnosis;
import com.clinakos.data.model.patient.LOVCode;
import com.clinakos.data.model.patient.LOVType;
import com.clinakos.data.model.patient.MasterDiagnosis;
import com.clinakos.data.model.patient.MasterTimeZone;
import com.clinakos.data.model.patient.NetworkChart;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientCountForDrugGene;
/*import com.clinakos.data.model.patient.NetworkChart;*/
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.PatientProvider;
import com.clinakos.data.model.patient.PatientProviderClinic;
import com.clinakos.data.model.patient.PatientProviderClinicHistory;
import com.clinakos.data.model.patient.PatientVital;
import com.clinakos.data.model.patient.SpecialityDrugCategory;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.data.model.patient.ViewFunctionalDetails;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

import https.secure_newcropaccounts_com.v7.webservices.DownloadDetail;



public class DashBoardDAOImpl extends BaseDaoImpl implements IDashBoardDAO{
	

	/*
	 * find the patient list according to firstname, lastname,dateofbirth,phone number, socialsecurity number and phone number ....by gopal
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#searchPatient(com.clinakos.core.model.UserLoginDetail)
	 */
	public List<UserLoginDetail> searchPatient(UserLoginDetail userLoginDetail,String role) {
		
		logger.info("searchPatient method in DashBoard dao impl start::::"+new ContextUtil().getProviderId()+"::"+userLoginDetail.getPhoneNumber()
				+":::"+userLoginDetail.getLastName()+":::"+userLoginDetail.getFirstName());
		
		List<UserLoginDetail> userLoginDetailList=new ArrayList<UserLoginDetail>();
		List<Integer>patientIdLIst=new ArrayList<Integer>();
		List<PatientProvider>patientProviderList=new ArrayList<PatientProvider>();
		patientProviderList=getSessionFactory().getCurrentSession().createCriteria(PatientProvider.class).
							add(Restrictions.eq("providerId", new ContextUtil().getProviderId())).list();
		
		for(PatientProvider patientProvider:patientProviderList )
		{
			patientIdLIst.add(patientProvider.getPatientId());
		}
		if(!(patientIdLIst.size()==0))
				{
			Criteria criteria=null;
			if(!(role.equalsIgnoreCase("ROLE_PHARMA"))){
				
			
			
			if(userLoginDetail.getLastName().length()>=1 && userLoginDetail.getFirstName().length()>=1)
			{
				System.out.println("under method:::::");
				
				criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class,"user");
				criteria.add(Restrictions.in("userId", patientIdLIst))
				//criteria.add(Restrictions.disjunction()
			   // .add(Restrictions.eq("user.userId", userLoginDetail.getId()))
			    //.add(Restrictions.eq("user.firstName", userLoginDetail.getFirstName()))
			    .add(Restrictions.ilike("user.firstName", userLoginDetail.getFirstName()))
			    //.add(Restrictions.eq("user.lastName", userLoginDetail.getLastName()))
			     .add(Restrictions.ilike("user.lastName", userLoginDetail.getLastName()));
				userLoginDetailList=criteria.list();
		
			}
			
			else {
				
				System.out.println("under method::2:::");
		  criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class,"user");
							criteria.add(Restrictions.in("userId", patientIdLIst));
				criteria.add(Restrictions.disjunction()
		        .add(Restrictions.eq("user.userId", userLoginDetail.getId()))
		        //.add(Restrictions.eq("user.firstName", userLoginDetail.getFirstName()))
		        .add(Restrictions.ilike("user.firstName", userLoginDetail.getFirstName()))
		        //.add(Restrictions.eq("user.lastName", userLoginDetail.getLastName()))
		         .add(Restrictions.ilike("user.lastName", userLoginDetail.getLastName()))
		        .add(Restrictions.eq("user.socialSecurityNumber", userLoginDetail.getSocialSecurityNumber()))
		        .add(Restrictions.eq("user.phoneNumber", userLoginDetail.getPhoneNumber()))
		        .add(Restrictions.eq("user.dateOfBirth", userLoginDetail.getDateOfBirth())));
		      //  .createCriteria("user.loginDetailId")))));
		       // .add( Restrictions.eq("role",new Integer(3)) );
				
			}
			
			}
			else if (role.equalsIgnoreCase("ROLE_PHARMA")) {
				criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				criteria.add(Restrictions.eq("userId", userLoginDetail.getId()));
			}
				
		  userLoginDetailList=criteria.list();
			
		for(UserLoginDetail us:userLoginDetailList)
			logger.info(":::::::::name::+"+us.getFirstName()+":::::::"+us.getId()+"::::"+us.getLastName()+"LLLLLL::"+us.getSocialSecurityNumber()+":::::::"+us.getPhoneNumber()+"KKKKK::::"+us.getDateOfBirth());
		
				}
		return userLoginDetailList;
	}
	
	/*
	 * find the patient list according to firstname, lastname,id ....by venu
	 */
	public List<UserLoginDetail> searchPatientNew(UserLoginDetail userLoginDetail,String role) 
	{

		logger.info("searchPatientNew method in DashBoard dao impl start::::"+new ContextUtil().getProviderId()+"::"+userLoginDetail.getSearchKey());

		List<UserLoginDetail> alluserLoginDetailList=new ArrayList<UserLoginDetail>();
		List<UserLoginDetail> firstNameLoginDetailList=new ArrayList<UserLoginDetail>();
		List<UserLoginDetail> lastNameLoginDetailList=new ArrayList<UserLoginDetail>();
		List<UserLoginDetail> allLoginDetailList=new ArrayList<UserLoginDetail>();
		List<UserLoginDetail> IDLoginDetailList=new ArrayList<UserLoginDetail>();
		List<Integer>newpatientIdLIst=new ArrayList<Integer>();
		List<PatientProvider>patientProviderList=new ArrayList<PatientProvider>();
		patientProviderList=getSessionFactory().getCurrentSession().createCriteria(PatientProvider.class).
							add(Restrictions.eq("providerId", new ContextUtil().getProviderId())).list();
		
		for(PatientProvider patientProvider:patientProviderList )
		{
			newpatientIdLIst.add(patientProvider.getPatientId());
		}
		if(!(newpatientIdLIst.size()==0))		
		{
			if(!(role.equalsIgnoreCase("ROLE_PHARMA"))){
				
			
			if(userLoginDetail.getSearchKey().equals("%"))
			{
				System.out.println("getSearchKey:::::::"+userLoginDetail.getSearchKey().toUpperCase());
				Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				criteria.add(Restrictions.in("userId", newpatientIdLIst));
				criteria.add(Restrictions.like("firstName", userLoginDetail.getSearchKey().toUpperCase()+"%"));
				criteria.add(Restrictions.like("lastName", userLoginDetail.getSearchKey().toUpperCase()+"%"));		
				allLoginDetailList=criteria.list();	
			
			}
			else
			{
				System.out.println("getSearchKey::name:::::"+userLoginDetail.getSearchKey());
			/* For retreiving firstname from the list by venu*/
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			criteria.add(Restrictions.in("userId", newpatientIdLIst));
			criteria.add(Restrictions.like("firstName", userLoginDetail.getSearchKey().toUpperCase()+"%"));
			firstNameLoginDetailList=criteria.list();			
			/* firstname list end*/

			/* For retreiving lastname from the list by venu*/
			Criteria criteria1 = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			criteria1.add(Restrictions.in("userId", newpatientIdLIst));
			criteria1.add(Restrictions.like("lastName", userLoginDetail.getSearchKey().toUpperCase()+"%"));		
			lastNameLoginDetailList=criteria1.list();			
			/* lastname list end*/
			
			/* For retreiving ID from the list by venu*/
			String s1=userLoginDetail.getSearchKey();
			try
			{
				int i=Integer.parseInt(s1);
				Criteria criteria2=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				criteria2.add(Restrictions.in("userId", newpatientIdLIst));
				criteria2.add(Restrictions.eq("userId",i));
				IDLoginDetailList=criteria2.list();
						
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println(":::::::::Exception ::::::::::::");
				
			}
			/* ID list end*/
		}
			alluserLoginDetailList.addAll(firstNameLoginDetailList);
			alluserLoginDetailList.addAll(lastNameLoginDetailList);
			alluserLoginDetailList.addAll(IDLoginDetailList);
			alluserLoginDetailList.addAll(allLoginDetailList);
	 }
		else if (role.equalsIgnoreCase("ROLE_PHARMA")) {
			String searchedId=userLoginDetail.getSearchKey();
			int searchedPatientId = 0;
			try {
				if(StringUtils.isNumeric(searchedId)){
					searchedPatientId=Integer.parseInt(searchedId);
				}
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			 
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
			criteria.add(Restrictions.eq("userId", searchedPatientId));
			alluserLoginDetailList=criteria.list();
			
		}
			

		}
		return alluserLoginDetailList;
	}

	public List<String> findPatientProviderClinicList(int id) {
	System.out.println("findPatientProviderClinicList:::::::"+id);
		List<PatientProviderClinic> findPatientProviderClinicList = new ArrayList<PatientProviderClinic>();
		
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientProviderClinic.class);
		criteria.add(Restrictions.eq("patientId", id));
		findPatientProviderClinicList=criteria.list();
		List<Integer>clinicProviderId= new ArrayList<Integer>();
		for( PatientProviderClinic patProClinic:findPatientProviderClinicList)
		{
			clinicProviderId.add(patProClinic.getClinicProviderId());
		}
		List<ClinicProvider> clinicProviderList = new ArrayList<ClinicProvider>();
		if(clinicProviderId.size()>0){
		clinicProviderList=getSessionFactory().getCurrentSession().createCriteria(ClinicProvider.class).
				              add(Restrictions.in("id", clinicProviderId)).list();
		}
		List<ClinicMaster>clinicmasterList=getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class).list();
		List<String>clinicNameList=new ArrayList<String>();
		for(ClinicProvider clinicProvider:clinicProviderList)
		{
			String clinicName=findClinicNameAccordingToId(clinicmasterList,clinicProvider.getClinicId());
		
			clinicNameList.add(clinicName);
		}
		
		return clinicNameList;
		
	}

	private String findClinicNameAccordingToId(List<ClinicMaster> clinicmasterList, int clinicId) {
	
		String clinicName = "";
		for(ClinicMaster clinicmaster:clinicmasterList)
		{
			if(clinicmaster.getId()==clinicId)
				clinicName=clinicmaster.getClinicName();
		}

		return clinicName;
	}

	/*
	 * find the diagnosis detail name according to particular patient 
	 * author: Gopal krishna jha from Lumbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findDiagnosisDetailList(int)
	 */

	public List<PatientDiagnosesDetails> findDiagnosisDetailList(int patientId) {
		logger.info("findDiagnosisDetailList::; in dashboard dao::::"+patientId);
		List<PatientDiagnosesDetails> pdList=new ArrayList<PatientDiagnosesDetails>();
		List<PatientDiagnosesDetails> pdListAfterTrim= new ArrayList<PatientDiagnosesDetails>();
		Query query=getSessionFactory().getCurrentSession().createQuery("from  PatientDiagnosesDetails  where patientId =:patientId ");
		query.setInteger("patientId", patientId);
		query.setMaxResults(2);
		pdList=query.list();
		for (PatientDiagnosesDetails pd : pdList) {
			logger.info("length of dig bfr trim======"+pd.getCodeDescription().length());
			pd.setCodeDescription(pd.getCodeDescription().trim());
			logger.info("length of dig aftr trim======"+pd.getCodeDescription().length());
			pdListAfterTrim.add(pd);
		}
		return pdListAfterTrim;
	}

	/*
	 * find patient vital according to particular patient id by gopal krishna jha from Lumbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findParticularPatientVitalDetail(int)
	 */

	public PatientVital findParticularPatientVitalDetail(int patientId) {
	
		logger.info("findParticularPatientVitalDetail method start in Userdaoimpl class"+patientId);
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientVital.class);
	
		criteria.add(Restrictions.eq("patientIdForVital",patientId));
		criteria.setProjection(Projections.max("id"));
		       /* .add(Restrictions.eq("Id", patientId));*/
		
		PatientVital patientVitalDetail=new PatientVital();
		
		
		
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("weight"))
					.add(Projections.property("height"))
			        .add(Projections.property("id"))
			        .add(Projections.property("patientIdForVital"))
			       /* .add(Projections.property("temperature"))
			        .add(Projections.property("respiration"))
			        .add(Projections.property("systolic"))
			        .add(Projections.property("diastolic"))
			        .add(Projections.property("pulse"))*/
					
					)
			        ; 
			List<Object> vitalDetailList = criteria.list();
			System.out.println("Size of list====="+vitalDetailList.size());
			try {
			for(Object obj: vitalDetailList){
			  Object[] row = (Object[]) obj;
			  patientVitalDetail.setWeight(((Double) row[0]));
			 patientVitalDetail.setHeight(((String) row[1]));		 
			 patientVitalDetail.setId((Integer) row[2]);
			 patientVitalDetail.setPatientIdForVital((Integer) row[3]);
			 /* patientVitalDetail.setTemperature((Double) row[4]);
			 patientVitalDetail.setRespiration((Integer) row[5]);
			 patientVitalDetail.setSystolic((Integer) row[6]);
			 patientVitalDetail.setDiastolic((Integer) row[7]);
			 patientVitalDetail.setPulse((Integer) row[8]);*/
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		
		
		return patientVitalDetail;
	}

	/*
	 * find liver function and kidne function default value 
	 * @author Gopal Krishna jha from lumbini
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findViewFunctionDetailList(int)
	 */
	public List<ViewFunctionalDetails> findViewFunctionDetailList(int patientId) {
		 
		
			
		return getSessionFactory().getCurrentSession().createCriteria(ViewFunctionalDetails.class).add(Restrictions.eq("patientId", patientId)).list();
	}


	/*
	 * find Lov type list for kidney function and liver function 
	 * @author: Gopal Krishna jha from LUMBINI
	 * (non-Javadoc)
	 * @see com.clinakos.core.dao.IUserDao#findLovTypeListInDetail()
	 */

	public List<LOVType> findLovTypeListInDetail() {
		logger.info("findLovTypeListInDetail method started in user dao impl");
		List lOVTypeList = getSessionFactory().getCurrentSession().createQuery("FROM LOVType").list(); 
		     
		 for (Iterator iterator = lOVTypeList.iterator(); iterator.hasNext();){
			 LOVType ltype = (LOVType) iterator.next();
			  Set lcode = ltype.getLovCodeDetail();
			  for (Iterator iterator2 =lcode.iterator(); iterator2.hasNext();){
			        LOVCode lcode1 = (LOVCode) iterator2.next(); 
			       
			  }
			}
		return lOVTypeList;
	}
	/*
	 * FIND USER INSURANCE DETAIL ACCORDING TO ID
	 * @AUTHOR:gOPAL kRISHAN JHA
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IDashBoardDAO#findUserInsuranceDetails(int)
	 * 
	 */
	public List<String> findUserInsuranceDetails(int id) {
	
			List<UserInsuranceDetails> userInsuranceDetailsList = new ArrayList<UserInsuranceDetails>();
			logger.info("findUserInsuranceDetails mehod in dashboarddaoimpl:::"+id);
			List<String>insuranceNameList=new ArrayList<String>();
			try {
				Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class)
						.add(Restrictions.eq("userId", id));
				criteria.add(Restrictions.isNotNull("insuranceId"));
				        
				userInsuranceDetailsList= criteria.list();
				
				List<InsuranceCompanies> insuranceCompanyId = getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class).list();
				
				for (UserInsuranceDetails userIns : userInsuranceDetailsList)
				{
					
					if(userIns.getInsuranceId()!=0){
					 String insuranceName=findInsurancenameAccordingToId(insuranceCompanyId,userIns.getInsuranceId());
					 insuranceNameList.add(insuranceName);
				   System.out.println("::::::::::::::userIns.getPrimaryInsurance():::"+userIns.getPrimaryInsurance());
				   if(!(userIns.getPrimaryInsurance()==null)){
					   if(userIns.getPrimaryInsurance().equals(YES))
						 {
							//System.out.println(userIns.getPrimaryInsurance()+":::::::::::::::::userIns.getId():::"+userIns.getInsuranceId());
							 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
							 session.setAttribute("insuranceId",userIns.getInsuranceId());
							 
						 }  
				   }
					 
					}
					if(userIns.getInsuranceId()==0)
					insuranceNameList.add(userIns.getInsuranceName());
				}
				
				if(userInsuranceDetailsList.isEmpty())
				{
					 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					 session.setAttribute("insuranceId",0);
				}
				
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			/*Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class)
					.add(Restrictions.eq("userId", id));
			criteria.add(Restrictions.isNotNull("insuranceId"));
			        
			userInsuranceDetailsList= criteria.list();
			
			List<InsuranceCompanies> insuranceCompanyId = getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class).list();
			List<String>insuranceNameList=new ArrayList<String>();
			for (UserInsuranceDetails userIns : userInsuranceDetailsList)
			{
				
				if(userIns.getInsuranceId()!=0){
				 String insuranceName=findInsurancenameAccordingToId(insuranceCompanyId,userIns.getInsuranceId());
				 insuranceNameList.add(insuranceName);
			   System.out.println("::::::::::::::userIns.getPrimaryInsurance():::"+userIns.getPrimaryInsurance());
				 if(userIns.getPrimaryInsurance().equals(YES))
				 {
					//System.out.println(userIns.getPrimaryInsurance()+":::::::::::::::::userIns.getId():::"+userIns.getInsuranceId());
					 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
					 session.setAttribute("insuranceId",userIns.getInsuranceId());
					 
				 } 
				}
				if(userIns.getInsuranceId()==0)
				insuranceNameList.add(userIns.getInsuranceName());
			}
			
			if(userInsuranceDetailsList.isEmpty())
			{
				 HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				 session.setAttribute("insuranceId",0);
			}
*/			
			return insuranceNameList;
			
		}

	/*
	 * FIND iNSURANCE ANME ACCORDING TO THERE id
	 * @author:Gopal Krishna jha
	 */
		private String findInsurancenameAccordingToId(List<InsuranceCompanies> insuranceCompanyId, int insuranceId) {
			
			String insuranceName = "";
			for(InsuranceCompanies insura: insuranceCompanyId)
			{
			if(insura.getId()==insuranceId)
				insuranceName=insura.getCompanyName();
		
			}
			
			return insuranceName;
		}

		public List<PatientDiagnosesDetails> showPatientDiagnosesDetails() {
			// TODO Auto-generated method stub
			PatientVital patientvital=new PatientVital();
			
			List<PatientDiagnosesDetails> patientDiagnosesDetailList=new ArrayList<PatientDiagnosesDetails>();
			try {
				Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientDiagnosesDetails.class);
				criteria.add(Restrictions.eq("patientId", new ContextUtil().getLoginId()));
				ProjectionList projectionList=Projections.projectionList();
				projectionList.add(Projections.property("code"),"code");
				criteria.setProjection(projectionList);
                criteria.setMaxResults(3);
				patientDiagnosesDetailList=criteria.setResultTransformer(Transformers.aliasToBean(PatientDiagnosesDetails.class)).list();
				
				patientDiagnosesDetailList=criteria.list();
				
			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return patientDiagnosesDetailList;
			
		}
		
		/*fetch the Height,weight bmi  login on patient profile page 
		 *  written by Sanket singh
		 * @see com.clinakos.data.dao.IDashBoardDAO#showLoggedPatientVitalData()
		 */
		

		public PatientVital showLoggedPatientVitalData() {
			// TODO Auto-generated method stub
			PatientVital patientVital = new PatientVital();
			
		
			try {
				 Criteria criteria= getSessionFactory().getCurrentSession().createCriteria(PatientVital.class);
				 criteria.add(Restrictions.eq("patientIdForVital", new ContextUtil().getPatientId()));
				 
				
				 criteria.setProjection(Projections.projectionList()
							.add(Projections.property("weight"))
							.add(Projections.property("height")));
				
				 List<Object> vitalDetailList = criteria.list();
					System.out.println("Size of list====="+vitalDetailList.size());
					for(Object obj1: vitalDetailList){
					  Object[] row = (Object[]) obj1;
					  patientVital.setWeight(((Double) row[0]));
					  patientVital.setHeight(((String) row[1]));		 
					
				 
				 patientVital=(PatientVital) criteria.list().get(0);
				   
					}
			} catch (Exception e) {
				// TODO: handle exception
			}
	      return patientVital;
			
		}

		
		/*	>>>>>>>>>>>>>>>>>>>>..recover password>>>>>>>>>>>>>>*/
		
		
		/*public List<LoginSecurity>getUserForgetPassword(String email){
			List<UserLoginDetail>userloginlist=new ArrayList<UserLoginDetail>();
			
			List<LoginSecurity>loginsecuritylist=new ArrayList<LoginSecurity>();
			int userId = 0;
			String pwd ="";		
			try{
					Criteria criteria =  getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				criteria.add(Restrictions.eq("email",email));
				
				userloginlist = criteria.list();
				if(userloginlist.size()==0){
		        	 System.out.println("=== no email found in db");
		        	 System.out.println("======= IF loginsecuritylist ======"+loginsecuritylist.size());
		        	
		        	//return loginsecuritylist;
				}else{
			
						System.out.println("======= else loginsecuritylist ======"+loginsecuritylist.size());
						
						
			for(UserLoginDetail object :userloginlist){
				userId = object.getUserId();			
			}
				
				
			Criteria criteria1= getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
			criteria1.add(Restrictions.eq("id",userId));
			loginsecuritylist = criteria1.list();
			for(LoginSecurity obj :loginsecuritylist){
				pwd = obj.getPassword();		
			
			}
	
			
				}	
				
			}catch(Exception ex){
			
				ex.printStackTrace();
			}

			
			
		
		
		return loginsecuritylist;

				

		

 }*/
		/*
		 * find password for forgot pwd
		 * @author: Gopal Krishna jHa
		 * (non-Javadoc)
		 * @see com.clinakos.data.dao.IDashBoardDAO#findUserPassword(java.lang.String)
		 */
		public String findUserPassword(String email) {
			logger.info("findUserPassword in dao method:::"+email);
			String mailId="";
			try {
				Criteria criteria_loginSecurity=getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
				criteria_loginSecurity.add(Restrictions.eq("userId",email));
				List<LoginSecurity>loginSecurityList=new ArrayList<LoginSecurity>();
				loginSecurityList=criteria_loginSecurity.list();
				if(!(loginSecurityList.isEmpty()))
				{
					mailId=String.valueOf(loginSecurityList.get(0).getId());
					logger.info("#########value of mailId in DAO#######="+mailId);
					Criteria criteria_userDetails=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
					criteria_userDetails.add(Restrictions.eq("userId",Integer.valueOf(mailId)));
					UserLoginDetail userLoginDetail=(UserLoginDetail) criteria_userDetails.list().get(0);
					logger.info("::::::"+userLoginDetail.getEmail());
					mailId+="-"+userLoginDetail.getEmail();
				}
				logger.info("#########value of mailId in DAO#######="+mailId);
				
			} catch (HibernateException he) {
				he.printStackTrace();
				mailId="";
			}
			catch (Exception e) {
				e.printStackTrace();
				mailId="";
			}
			
			return mailId;
		}

		
		/*public List<UserLoginDetail> findUserDetailsForAdmin() {
			// TODO Auto-generated method stub
			List<UserLoginDetail>detailsummaryList= getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class).list();
			
			for(UserLoginDetail userDetailsSummary:detailsummaryList)
			{
				userDetailsSummary.setUserLoginId(findUserLoginId(userDetailsSummary.getId()));
				
			}
			
			
			return detailsummaryList;
		}

		private String findUserLoginId(Integer id) {
			
			// TODO Auto-generated method stub
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);
			
			criteria.setProjection(Projections.projectionList().
			         add(Projections.property("userId") ));
	                 criteria.add(Restrictions.eq("id",id));
	                 LoginSecurity userDet=new LoginSecurity();
	          
	                 String name = null;
	                 List<Object> adminUserDetails = criteria.list();
	         		for(Object obj: adminUserDetails){
	         			
	         			System.out.println("nameDetailsListForAdmin::::"+obj.toString());
	         			//name.set((String)row[0]);
	         		  Object[] row = (Object[]) obj;
	         		
	         		name=(String)row[0];
	         		}
			return name;
		}*/

		
		public List<LoginSecurity> findDetailsForAdmin() {
			// TODO Auto-generated method stub
			List<RoleSecurity>roleSecurityList=new ArrayList<RoleSecurity>();
			roleSecurityList=getSessionFactory().getCurrentSession().createCriteria(RoleSecurity.class).list();
			List<UserLoginDetail>UserLoginDetailList=new ArrayList<UserLoginDetail>();
			UserLoginDetailList=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class).list();
			List<LoginSecurity>userDetailsList = getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class).list();
			for(LoginSecurity userSummary:userDetailsList)
			{
				userSummary.setUserLoginId(findUserLoginStatus(userSummary.getId(),UserLoginDetailList));
				userSummary.setRoleName(findRoleStatus(userSummary.getRole(),roleSecurityList));
				
			}
			return userDetailsList;
		}

		private String findRoleStatus(int role, List<RoleSecurity> roleSecurityList) {
			
			String nameRole="";
			for(RoleSecurity roleSecurity:roleSecurityList)
			{
				if(roleSecurity.getId()==role)
					nameRole=roleSecurity.getRoleName();
			}
			return nameRole;
		}

		//private String findUserLoginStatus(int id) {
			// TODO Auto-generated method stub
			public String findUserLoginStatus(int id, List<UserLoginDetail> userLoginDetailList) {
				String name="";
				for(UserLoginDetail userLoginDetail:userLoginDetailList)
				{
					if(userLoginDetail.getUserId()==id)
						name=userLoginDetail.getFirstName()+" "+userLoginDetail.getMiddleName()+" "+userLoginDetail.getLastName();
				}
				
			return name;
		}

		/**
		 * Save Latest New Crop Download url detail data 
		 */
			public void saveNewCropDownloadDetailData(
					DownloadDetail downloadDetail) {
				// TODO Auto-generated method stub
				getSessionFactory().getCurrentSession().save(downloadDetail);
				
			}

	/**
	 * Get All Recent Download Detail Data 
	 * @throws SQLException 
	 * @throws HibernateException 
	 */
		public List<DownloadDetail> getRecentDownloadDetailData() throws HibernateException, SQLException {
			// TODO Auto-generated method stub
			List<DownloadDetail> downloadDetails=new ArrayList<DownloadDetail>();
			
		/*	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(DownloadDetail.class);
		
			//criteria.addOrder(Order.desc("latestDownloadDay"));
			//criteria.setMaxResults(5);
			downloadDetails=criteria.list();*/
			
			String sqlQuery="SELECT dud.latest_download_month,dud.latest_download_year,dud.latest_download_day,dud.latest_download_size,dud.latest_download_url,"+
					"dud.cchit_drug_database_date from newcrop_latest_download_url_detail dud order by  download_url_id desc limit 5";
			PreparedStatement statemen=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareStatement(sqlQuery);
			ResultSet resultSet=statemen.executeQuery();
			while (resultSet.next()) {
				DownloadDetail detail=new DownloadDetail();
				detail.setLatestDownloadDay(resultSet.getString("latest_download_day"));
				detail.setCchitDrugDatabaseDate(resultSet.getString("cchit_drug_database_date"));
				detail.setLatestDownloadMonth(resultSet.getString("latest_download_month"));
				detail.setLatestDownloadUrl(resultSet.getString("latest_download_url"));
				detail.setLatestDownloadYear(resultSet.getString("dud.latest_download_year"));
				downloadDetails.add(detail);
			}
					
			
			return downloadDetails;
		}



 public List<EmpProviderLocation> getEmpProviderLocationList() {
	    logger.info(":::::::::::inside getEmpProviderLocationList DashboardDaoImpl:::::::::::::"+new ContextUtil().getProviderId());
	    int providerId=new ContextUtil().getProviderId();
	    List<EmpProviderLocation> providerLocationList=new ArrayList<EmpProviderLocation>();
	    if (providerId==0) {
	    	logger.info(":::::::::::inside if block:::::::::"+providerId);
	    	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EmpProviderLocation.class);
		    criteria.addOrder(Order.asc("id"));
		    providerLocationList=criteria.list();
		   }
	    else  {
	    	logger.info(":::::::::::inside else block:::::::::"+providerId);
		    Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EmpProviderLocation.class);
		    criteria.add(Restrictions.eq("providerId", providerId)); 
		    criteria.addOrder(Order.asc("id"));
		    providerLocationList=criteria.list();
	     }
		    return providerLocationList;
  	 }

/**
 * It will give data of Diagnoses Chart Data 
 * @return List<DiagnosesChart> 
 */
  public List<DiagnosesChart> diagnosesDetail() {
	 List<DiagnosesChart>  diagnosisChartDataList = new ArrayList<DiagnosesChart>();
	 System.out.println("diagnosesDetail::::::method start in dashboasrddao impl::::::: ");
	 try {
		 String hql="FROM DiagnosesChart";
		 Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		 diagnosisChartDataList=query.list();
		 
		 
	} catch (Exception e) {
		logger.error("exception in diagnosesDetail dashboardImpl", e);
		e.printStackTrace();
	}
	 
	return diagnosisChartDataList;
}
  
  
  public List<FormularyChart>  findFormularyChart(Map<String, String> formularyMAP) {
		logger.info("findFormularyChart method start in user dao impl:::" );
		
		List<FormularyChart> patientFormularyChartList=new ArrayList<FormularyChart>();		
		
		try
		{
			String hql="select medicine_id,formulay_tier from formulary_chart_detail where provider_id= "+new ContextUtil().getProviderId()+"";
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall(hql);
			ResultSet resultset = statement.executeQuery();
			while (resultset.next()) {
				FormularyChart formulary = new FormularyChart();
				formulary.setMedicineId(resultset.getInt("medicine_id"));
				if(StringUtils.isNotBlank(resultset.getString("formulay_tier"))){
					formulary.setFormulayTier(formularyMAP.get(resultset.getString("formulay_tier")));
				//formulary.setFormulayTier(resultset.getString("formulay_tier"));
				}
				patientFormularyChartList.add(formulary);
			}
		}
		catch(Exception e)
		{
			logger.error("exception in findFormularyChart", e);
			e.printStackTrace();
		}
		
		
		//System.out.println("size of list:::::"+patientFormularyChartList.size());
		
		/*for(FormularyDetail fd : patientFormularyChartList)
		{
			System.out.println("Medicine Id============ "+fd.getMedicineId());
			System.out.println("Tier============== "+fd.getFormulayTier());
		}*/
		System.out.println("List size================= "+patientFormularyChartList.size());
		return patientFormularyChartList;
	}
  

	
  public List<PatientCountForDrugGene> fetchDrugGeneData() {
      List<PatientCountForDrugGene>  drugGeneList = new ArrayList<PatientCountForDrugGene>();
      List<PatientCountForDrugGene>  pcList = new ArrayList<PatientCountForDrugGene>();
      logger.info("fetchDrugGeneData::::staterd in dashBoardDaoImpl:::::::::::");
      try {CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().
			  prepareCall("{call proc_drugGeneIntaraction_forHeatMap("+new ContextUtil().getProviderId()+")}");
		
		ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PatientCountForDrugGene patCount = new PatientCountForDrugGene();
				String medName=resultSet.getString("drug_name");
				String geneSymbol=resultSet.getString("gene");
				int patientCount=resultSet.getInt("no_of_patient");
				patCount.setMedicineName(medName);
				patCount.setGeneSymbol(geneSymbol);
				patCount.setPatientCount(patientCount);
				System.out.println("fetchDrugGeneData:::::::"+patCount.getMedicineName());
				pcList.add(patCount);
			}
			drugGeneList.addAll(pcList);
      } catch (Exception e) {
				e.printStackTrace();
			}
			
	return drugGeneList;

  }
 /* public List<NetworkChart>  findNetworkChart() {
		logger.info("findFormularyChart method start in user dao impl:::");
		
		List<NetworkChart> patientFormularyChartList=new ArrayList<NetworkChart>();		
		
		try
		{
			String hql="from NetworkChart";
			Query query=getSessionFactory().getCurrentSession().createQuery(hql);
			patientFormularyChartList = query.list();
		}
		catch(Exception e)
		{
			logger.error("exception in findFormularyChart", e);
			e.printStackTrace();
		}
		
		
		System.out.println("size of list:::::"+patientFormularyChartList.size());
		
		for(NetworkChart fd : patientFormularyChartList)
		{
			System.out.println("Medicine Id============ "+fd.getProvider_id());
			System.out.println("Tier============== "+fd.getFirst_name());
		}
		System.out.println("List size================= "+patientFormularyChartList.size());
		return patientFormularyChartList;
	}*/
  
  
  /*****@modified by : SAURABH
   * **********getting values for network chart on physician & pharma dashboard 
   * *******calling table data in place of calling procedure proc_networkChart
   */ 
  public List<NetworkChart>  findNetworkChart(int limitFlag,int logedUserId, String logedUserFirstName, String logedUserLastName) {
	  logger.info(limitFlag+"findNetworkChart method start in Dashboard dao impl:::"+logedUserId/*+" "+ logedUserFirstName+" "+logedUserLastName*/);
	  int providerId=new ContextUtil().getProviderId();
	  List<NetworkChart> networkList = new ArrayList<NetworkChart>();
	  List<NetworkChart> AllnetworkList = new ArrayList<NetworkChart>();
	  List<NetworkChart> doctorNameList = new ArrayList<NetworkChart>();
	  List<ResultProcNetworkChart> rList=new ArrayList<ResultProcNetworkChart>();
	  int doctor_id=0;
	  try {
		  
		    final String HQL1 = "From ResultProcNetworkChart E WHERE E.sharedDoctorId = :logedUserId GROUP BY doctorId";
		    Query query = getSessionFactory().getCurrentSession().createQuery(HQL1);
		    query.setInteger("logedUserId", logedUserId);
		    rList=query.list();
		    for (ResultProcNetworkChart r : rList) {
				  NetworkChart uld=new NetworkChart();
				  String doctor_name=r.getDoctorFirstName();
				  doctor_id=r.getDoctorId();
				  int patient_id=r.getUserId();
				  try {
					  uld.setDoctor_prescriptions(r.getDoctorPrescriptions());
				} catch (NullPointerException ne) {
					logger.error(":::::::::::NullPointerException exception in uld setDoctor_prescriptions");
					uld.setDoctor_prescriptions(0);
				}
				  uld.setDoctor_name(doctor_name);
				  uld.setDoctor_id(doctor_id);
				  uld.setPatient_id(patient_id);
				  //uld.setDoctor_prescriptions(doctor_prescriptions);
				  networkList.add(uld);
				  System.out.println(uld.getDoctor_id() + uld.getDoctor_name() + uld.getPatient_id() );
				}		
	  } catch (HibernateException he) {
		  he.printStackTrace();
	  } catch (Exception e) {
		  e.printStackTrace();
	  } 
	  AllnetworkList.addAll(0,doctorNameList);
	  AllnetworkList.addAll(networkList);	 
	  	System.out.println("AllnetworkList inside findNetworkChart::::::--"+ AllnetworkList.size());
	  return AllnetworkList;
  }


   public List<SpecialityDrugCategory> findSpecialityDrugDetails() {
	   logger.info("findSpecialityDrugDetails method started in dashBoardDaoImpl:::::::::::::::");
	   List<SpecialityDrugCategory> specialityDrugDataList = new  ArrayList<SpecialityDrugCategory>();
        try {
			String hql = "select med_count,drug_type from vw_speciality_drug_detail where provider_id= "+new ContextUtil().getProviderId()+"";
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall(hql);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				SpecialityDrugCategory specialityData = new SpecialityDrugCategory();
				specialityData.setMedCountForDrugCategory(result.getInt("med_count"));
				specialityData.setDrugCategory(result.getString("drug_type"));
				logger.info("medcount::::::::::"+specialityData.getMedCountForDrugCategory()+"drugType::::::"+specialityData.getDrugCategory());
				specialityDrugDataList.add(specialityData);				
			}
		} catch (Exception e) {
			logger.error("exception in findSpecialityDrugDetails", e);
			e.printStackTrace();
		}
	return specialityDrugDataList;
  }

  public List<DrugCategoryDetail> findDrugCategoryDetailList() {
	logger.info("findDrugCategoryDetailList method started in dashBoardDaoImpl::::::::");
	List<DrugCategoryDetail> drugCategoryDataList = new ArrayList<DrugCategoryDetail>();
	try {
		String query ="select drug_category,drugCount from vw_drug_category_detail where provider_id= "+new ContextUtil().getProviderId()+"";
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall(query);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			DrugCategoryDetail categoryData = new DrugCategoryDetail();
			categoryData.setMedicineCategory(result.getString("drug_category"));
			categoryData.setDrugCountPerCategory(result.getInt("drugCount"));
			drugCategoryDataList.add(categoryData);
		}
	} catch (Exception e) {
		logger.error("exception in findDrugCategoryDetailList", e);
		e.printStackTrace();
	}
	 return drugCategoryDataList;
   }

@SuppressWarnings({ "unchecked", "null" })

public List<UserLoginDetail> fetchPatientByFormulary(String levelVal4Formulary, int providerId) {
	// TODO Auto-generated method stub
	 List<UserLoginDetail> returnResultList = new ArrayList<UserLoginDetail>() ;
	 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
	try
	{
		
		/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getPatientListByFormulary('"+levelVal4Formulary+"' , "+ providerId +", '"+DecryptionKey+"')}");
																										
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
			 fc.setGender(resultSet.getString("gender"));
			/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
			 resultList.add(fc);
			
		 }
		 returnResultList.addAll(resultList);
		/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
	}
	catch (Exception e) {
		logger.error("exception in fetchPatientByFormulary:: ", e);
		e.printStackTrace();
	}
	
	return returnResultList;
}

public List<UserLoginDetail> numberofpatientforanticoagforintiationphase(Integer providerId, String medicinestage, int procedureType, String start_result, String end_result, String levelVal)
{
	
	List<UserLoginDetail> anticoagpatientlist=new ArrayList<UserLoginDetail>();
	
	 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
	logger.info("{}{}{}{}findnumberofpatientforanticoagclinicforintiationphase in dashboard{}{}{}{}");		
	
	try
	{
		
		/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
		
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_anticoag_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+levelVal+"','"+DecryptionKey+"')}");
																										
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
			 fc.setGender(resultSet.getString("gender"));
			 fc.setResult(resultSet.getDouble("result"));
			 fc.setMedicine_stage(resultSet.getString("medicine_stage"));
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

/*
 * find clinic and provider id  of particular Doctor who is log in...
 * @author: Gopal Krishna JHA
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IUserDao#findClinicAndProviderId(int)
 */
public List<Integer> findClinicAndProviderId(int loginId) {
	logger.info("findClinicAndProviderId method in dashboarddao imple start::::"+loginId);
	List<Integer> clinicList=new ArrayList<Integer>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ClinicDoctor.class);
	
			criteria.setProjection(Projections.projectionList().
			         add(Projections.property("clinicProviderID") ));
	                 criteria.add(Restrictions.eq("doctorId",loginId));
	 int clinicProviderId =0;
	 System.out.println("lict size:::::"+criteria.list().size());
	 if(criteria.list().size()!=0)
	 {
		 System.out.println("akos2013r:::::::"+criteria.list().size());
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

/*@author : vinod
 * ** to add new patient record
 * @see com.clinakos.data.dao.IDashBoardDAO#savepateintData(com.clinakos.data.model.core.LoginSecurity, com.clinakos.data.model.patient.UserLoginDetail, com.clinakos.data.model.patient.EmployerDetails, com.clinakos.data.model.patient.PatientVital, java.util.List, com.clinakos.data.model.patient.PatientProvider, java.util.List, java.util.List, java.util.List, com.clinakos.data.model.patient.PatientGuarantor)
 ********modified by : saurabh
 */

 public boolean savepateintData(LoginSecurity loginSecurity,UserLoginDetail userLoginDetailsNewPatient,EmployerDetails employerDetails,PatientVital patientVitalNewPatient,
		 List<PatientAllergy> newPatientAllergyList,PatientProvider patientProviderDetail,
		 List<PatientDiagnosesDetails> newPateintDiagosesList,List<PatientProviderClinic> patientProviderClinicListNewPatient,List<UserInsuranceDetails> patientInsuranceDetailList, PatientGuarantor patientGuarantorNewPatient) {
	logger.info("savepateintData calling in dashboardDaoImpl::::::");
	UserInsuranceDetails userInsuranceDetailsForAddPatient=new UserInsuranceDetails();
	Session session = getSessionFactory().openSession();
	Transaction transaction = null;
	boolean checkStatus=false;
	try {
			try {
				transaction =session.beginTransaction();
				session.save(loginSecurity);
				session.save(userLoginDetailsNewPatient);
				session.save(patientProviderDetail);
				session.save(patientGuarantorNewPatient);
				/*if (patientInsuranceDetailList.isEmpty()) {
					userInsuranceDetailsForAddPatient.setUserId(userLoginDetailsNewPatient.getUserId());
					session.save(userInsuranceDetailsForAddPatient);
				}*/
				//transaction.commit();
                session.getTransaction().commit();
				if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
				checkStatus=true;
				}
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
				//checkStatus=false;
				logger.error("error in savePatientdata::::::::userLoginDetailsNewPatient::::dashBoardDao::::");
			}
		
		logger.info("check status :::::::"+checkStatus);
		if(checkStatus){
			transaction = session.beginTransaction();
			session.save(employerDetails);
			//patientVital.setPatientIdForVital(userLoginDetail.getUserId());
			for (PatientAllergy patientAllergy : newPatientAllergyList) {
				patientAllergy.setPatientId(userLoginDetailsNewPatient.getUserId());
				session.save(patientAllergy);
			}
			for(PatientDiagnosesDetails diagnosisDetl:newPateintDiagosesList){
				diagnosisDetl.setPatientId(userLoginDetailsNewPatient.getUserId());
				diagnosisDetl.setDoctorId(new ContextUtil().getLoginId());
				session.save(diagnosisDetl);
			}
			for(UserInsuranceDetails userInsurancedtl:patientInsuranceDetailList){
				userInsurancedtl.setUserId(userLoginDetailsNewPatient.getUserId());
				session.save(userInsurancedtl);
			}
			for(PatientProviderClinic clinicDetl:patientProviderClinicListNewPatient){
				clinicDetl.setPatientId(userLoginDetailsNewPatient.getUserId());
				session.save(clinicDetl);
			}
			session.save(patientVitalNewPatient);
			session.getTransaction().commit();
			checkStatus=true;
		}
	 		
	} catch (Exception e) {
      e.printStackTrace();
      transaction.rollback();
      checkStatus=false;
      logger.error("error at savepateintData::::dashboardDao:::: ");
	}finally {
		session.flush();
		session.clear();
		session.close();
	}
	return checkStatus;
   }



public List<UserLoginDetail> fetchPatientBySelectedCategory(int i,String medicinestage,int procedureType,double start_result,double end_result,
		String levelVal4Formulary, int providerId) {
	// TODO Auto-generated method stub
	 List<UserLoginDetail> returnResultList = new ArrayList<UserLoginDetail>() ;
	 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
	 String chartType="Tier 1";
	 
	 int breakPoint=0;
	 

	 
	 
	 /*levelVal4Formulary*/
	try
	{
		/*"Tier 1" phi phm pmi pmm pli plm*/
		logger.info("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );
		/*CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_getPatientListByFormulary('"+chartType+"' , "+ providerId +")}");*/
		
		 if(levelVal4Formulary.equalsIgnoreCase("phi"))
		 {
			 breakPoint=6;
		 }
		 
		 if(levelVal4Formulary.equalsIgnoreCase("pmi"))
		 {
			 breakPoint=5;
		 }
		 
		 if(levelVal4Formulary.equalsIgnoreCase("pli"))
		 {
			 breakPoint=4;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("phm"))
		 {
			 breakPoint=5;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("pmm"))
		 {
			 breakPoint=4;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("plm"))
		 {
			 breakPoint=6;
		 }
		 
		 /*Hep c dummy data */
		 if(levelVal4Formulary.equalsIgnoreCase("hhi"))
		 {
			 breakPoint=6;
		 }
		 
		 if(levelVal4Formulary.equalsIgnoreCase("hmi"))
		 {
			 breakPoint=4;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("hli"))
		 {
			 breakPoint=5;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("hhm"))
		 {
			 breakPoint=4;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("hmm"))
		 {
			 breakPoint=2;
		 }
		 if(levelVal4Formulary.equalsIgnoreCase("hlm"))
		 {
			 breakPoint=3;
		 }
		 
		/*String medicinestage="Initiation";
		int procedureType=2;
		double start_result=2.0;
		double end_result=4.0;*/
		
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_clinic_analytic_status('"+medicinestage+"' , "+ providerId +", "+ start_result +", "+ end_result +", "+ procedureType +",'"+DecryptionKey+"')}");
		
		 ResultSet resultSet = statement.executeQuery();
		 
		 while (resultSet.next()) {
			 
			 if(resultList.size()<= breakPoint)
			 {
				 UserLoginDetail fc = new UserLoginDetail();
				 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
				 String patientId=resultSet.getString("PatientId");
				 fc.setUserId(Integer.parseInt(patientId));
				 fc.setFirstName(resultSet.getString("FName"));
				 fc.setMiddleName(resultSet.getString("mName"));
				 fc.setLastName(resultSet.getString("lName"));
				 fc.setDateOfBirth(resultSet.getDate("DOB"));
				 fc.setGender(resultSet.getString("gender"));
				 resultList.add(fc);
				 if(resultList.size()== breakPoint)
				 {
					 break;
				 }
			 }
			 
			 
		System.out.println("Result size is now "+resultList.size());
		 }
		 returnResultList.addAll(resultList);
		logger.info("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + returnResultList.size());
	}
	catch (Exception e) {
		logger.error("exception in fetchPatientByFormulary:: ", e);
		e.printStackTrace();
	}
	
	return returnResultList;
}


public List<Integer> getMaxUserId() {
	List<Integer> maxUserId = null;
	try {
		String sql ="select max(id) from LoginSecurity";
		Query query = getSessionFactory().getCurrentSession().createQuery(sql);
		maxUserId= (List<Integer>) query.list();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return maxUserId;
}


  public boolean checkForEmailExist(String email) {
	boolean status = false;
	try {
		String sql ="from UserLoginDetail where email ='"+email+"'";
		Query query = getSessionFactory().getCurrentSession().createQuery(sql);
		List emailFromDB = query.list();
		logger.info("getting email from db:::::::"+emailFromDB.size());
		if(emailFromDB.size() != 0){
			status =true;
		}
	} catch (Exception e) {
	
	}
	return status;
	
 }


  public List<ClinicMaster> findClinicMasterDetail(String userInputForAddClinic) {
	 List<ClinicMaster>  clinicDetail = new ArrayList<ClinicMaster>();
	 try {
		 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class); 
		 criteria.add(Restrictions.like("clinicName", userInputForAddClinic+"%"));
		  clinicDetail =criteria.list();
		  logger.info("clinicMaster Data size::::"+clinicDetail.size());
	} catch (Exception e) {
		
	}
	return clinicDetail;
 }


  public List<ClinicProvider> findclinicProviderDetail(int providerId) {
	List<ClinicProvider> clinicproviderDetailList = new ArrayList<ClinicProvider>();
	try {
		Criteria criteria =getSessionFactory().getCurrentSession().createCriteria(ClinicProvider.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		//criteria.setProjection(Projections.distinct(Projections.property("clinicId")));
		clinicproviderDetailList = criteria.list();
	} catch (Exception e) {
		
	}
	return clinicproviderDetailList;
  }


   public List<InsuranceCompanies> fetchInsuranceDetail(String userInputForAddInsurance) {
	 List<InsuranceCompanies> insuranceDetails = new ArrayList<InsuranceCompanies>();
	try{
		 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class);
		 criteria.add(Restrictions.like("companyName", userInputForAddInsurance+"%"));
		 insuranceDetails = criteria.list();
	}
   catch(Exception e){
	   e.printStackTrace();
	   logger.error("error at fetchInsuranceDetail method in dashBoardDao:::");
   }
	return insuranceDetails;
   }



	
	public List<PatientProviderClinic> fetchPatientClinicData(int patientId) {
		logger.info("fetchPatientClinicData::::::method starts:::::");
		 List<PatientProviderClinic>   fetchClinicsDataList = new ArrayList<PatientProviderClinic>();
		 List<ClinicProvider> clinicProviderData = new ArrayList<ClinicProvider>();
		 List<ClinicMaster>  clinicMasterList = new ArrayList<ClinicMaster>();
		 try{
		 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientProviderClinic.class);
		 criteria.add(Restrictions.eq("patientId", patientId));
		 criteria.addOrder(Order.desc("id"));
		  criteria.setProjection(Projections.distinct(Projections.property("clinicProviderId")));
				  
		 List<Integer> clinicProviderId = criteria.list();
		 
		 for(int clinicData:clinicProviderId){
			 logger.info("clinicProviderId::::::::"+clinicData);
			 try {
				 Criteria criteria2 = getSessionFactory().getCurrentSession().createCriteria(ClinicProvider.class);
				 criteria2.add(Restrictions.eq("id",clinicData));
				 criteria2.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
				 clinicProviderData=criteria2.list();
				 
				 for(ClinicProvider clinicprovider:clinicProviderData){
					 logger.info("clinicId:::::::"+clinicprovider.getClinicId());
					 try {
						Criteria criteria3 = getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class);
						criteria3.add(Restrictions.eq("id", clinicprovider.getClinicId()));
						clinicMasterList= criteria3.list();
						
						for(ClinicMaster clinic:clinicMasterList){
					   PatientProviderClinic pateintClinicData = new PatientProviderClinic();
							pateintClinicData.setClinicId(clinic.getId());
							pateintClinicData.setClinicName(clinic.getClinicName());
							pateintClinicData.setProviderLocationId(clinicprovider.getProviderLocationId());
							fetchClinicsDataList.add(pateintClinicData);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}
		 }
		 }catch(Exception e){
			 e.printStackTrace();
			 logger.error("error at fetchPatientClinicData in dashBoardDaoImpl:::::::::");
		 }
		return fetchClinicsDataList;
	}

	
	public EmployerDetails findPatientEmployerDetails(int patientId) {
		logger.info("findPatientEmployerDetails methos starts in dashBoardDao ::::::::");
		EmployerDetails emplyerDetails = new EmployerDetails();
		   try {
			 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(EmployerDetails.class);
			 criteria.add(Restrictions.eq("patientId", patientId));
			 criteria.setProjection(Projections.projectionList()
						.add(Projections.property("companyName"))
						.add(Projections.property("addressLine1"))
			            .add(Projections.property("addressLine2"))
				        .add(Projections.property("companyCity"))
				        .add(Projections.property("companyState"))
				        .add(Projections.property("companyCountry"))
				        .add(Projections.property("companyZipCode"))
				        .add(Projections.property("companyPhoneNumber"))
				        .add(Projections.property("id"))
				        .add(Projections.property("patientId")));
			 List<Object> employerData =criteria.list();
			 for(Object obj:employerData){
				 Object[] row = (Object[]) obj;
				 emplyerDetails.setCompanyName((String) row[0]);
				 emplyerDetails.setAddressLine1((String) row[1]);
				 emplyerDetails.setAddressLine2((String) row[2]);
				 emplyerDetails.setCompanyCity((String) row[3]);
				 emplyerDetails.setCompanyState((String) row[4]);
				 emplyerDetails.setCompanyCountry((String) row[5]);
				 emplyerDetails.setCompanyZipCode((String) row[6]);
				 emplyerDetails.setCompanyPhoneNumber((String) row[7]);
				 emplyerDetails.setId((Integer) row[8]);
				 emplyerDetails.setPatientId((Integer) row[9]);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emplyerDetails;
	}

	
	
	
	public List<UserInsuranceDetails> fetchPatientInsuranceDetail(int patientId) {
		logger.info("fetchPatientInsuranceDetail::::::starts");
		List<UserInsuranceDetails> userInsuranceList = new ArrayList<UserInsuranceDetails>();
		List<UserInsuranceDetails> tempInsuranceList = new ArrayList<UserInsuranceDetails>();
		List<InsuranceCompanies> masterInsuranceCompanyList = new ArrayList<InsuranceCompanies>();
		try {
			 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
			   criteria.add(Restrictions.eq("userId", patientId));
			   criteria.addOrder(Order.desc("id"));
			   //criteria.setProjection(Projections.distinct(Projections.property("insuranceId")));
			  // List<Integer> insuranceId = criteria.list();
			   tempInsuranceList = criteria.list();
			   
			   for (UserInsuranceDetails patientInsurance : tempInsuranceList) {
				logger.info("patient insurance id::::::"+patientInsurance.getInsuranceId());
				Criteria criteria2 = getSessionFactory().getCurrentSession().createCriteria(InsuranceCompanies.class);
				  criteria2.add(Restrictions.eq("id", patientInsurance.getInsuranceId()));
				  masterInsuranceCompanyList = criteria2.list();
				  for (InsuranceCompanies insuranceData : masterInsuranceCompanyList) {
					  UserInsuranceDetails userInsurance = new UserInsuranceDetails();
					  userInsurance.setInsuranceId(insuranceData.getId());
					  userInsurance.setCompanyName(insuranceData.getCompanyName());
					  userInsurance.setPrimaryInsurance(patientInsurance.getPrimaryInsurance());
					  userInsurance.setSecondaryInsurance(patientInsurance.getSecondaryInsurance());
					  userInsurance.setTertiaryInsurance(patientInsurance.getTertiaryInsurance());
					  userInsurance.setPayorName(patientInsurance.getPayorName());
					  userInsurance.setId(patientInsurance.getId());
					  //userInsurance.setPrimaryInsurance(primaryInsurance)
					  userInsuranceList.add(userInsurance);
				}
			}
			   
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInsuranceList;
	}

	//Method to plot chart for Drug Drill down by Anand S Jha
	@Override
	public List<RptDrugCategory> fetchDrugByCategoryChart(String levelVal4DrugChart,
			int providerId) {
		// TODO Auto-generated method stub
		List<RptDrugCategory> drillDownDrugList=new ArrayList<RptDrugCategory>();
		List<RptDrugCategory> drillDownDrugList1=new ArrayList<RptDrugCategory>();
		 CallableStatement statement;
		  try {
			  System.out.println("fetching data fro drill down drug: for "+levelVal4DrugChart);
			  statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_DrugDrillDownChart("+providerId+",'"+levelVal4DrugChart+"')}");

			  ResultSet resultSet=statement.executeQuery();


			  while (resultSet.next()) {
				  RptDrugCategory uld=new RptDrugCategory();
				
				  logger.info("Drugs name is "+resultSet.getString("drugs")+"Total Patients are "+resultSet.getInt("toatl_count"));
				 
				  String drug_name=resultSet.getString("drugs");
				  Integer total_patient=resultSet.getInt("toatl_count");
				  
				  uld.setDrugName(drug_name);
				  uld.setTotalPatient(total_patient);
				
				  System.out.println(uld.getDrugName() + uld.getTotalPatient());
				
				  drillDownDrugList1.add(uld);
			  }
		  } catch (HibernateException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
		  }		

		  drillDownDrugList.addAll(drillDownDrugList1);	 
		  	System.out.println("list size:for Drug Category:anand:::--"+ drillDownDrugList.size());
		 
			return drillDownDrugList;
	}

	
	public void savePatientEditProfile(UserLoginDetail userLoginDetail,EmployerDetails patientEmployerDetails,PatientVital patientVital,
			List<UserInsuranceDetails> copyOfOriginalPateintInsuranceEditProfileList,List<UserInsuranceDetails> pateintInsuranceEditProfileList,
			List<UserInsuranceDetails> temporaryDeleteInsuranceListProfile, List<PatientProviderClinic> copyOfOriginalPatientClinicDataListprofile,List<PatientProviderClinic> patientClinicDataListprofile,
			List<PatientProviderClinic> temporaryDeleteClinicListProfile, List<PatientDiagnosesDetails> editPateintProfileDiagosesList,List<PatientDiagnosesDetails> temporaryAddDiagnosesEditProfileList,
			List<PatientDiagnosesDetails> deleteDiagnosesEditProfileList, List<PatientAllergy> allergyListForEditPatient,List<PatientAllergy> temporaryAddAllergyForProfile, List<PatientAllergy> deleteAllergyForProfilePageList, 
			PatientGuarantor patientGuarantor, List<UserInsuranceDetails> insuranceListForUpdate) {
		
		Session session = getSessionFactory().openSession();
	    Transaction transaction = null;
	    
		boolean checkEditTransaction = false;
		try {
			
			try{
				transaction = session.beginTransaction();
				session.update(userLoginDetail);
				if(patientEmployerDetails.getPatientId()==0){
					patientEmployerDetails.setPatientId(new ContextUtil().getPatientId());
					session.save(patientEmployerDetails);
				}
				if(patientEmployerDetails.getId()!=0){
					session.update(patientEmployerDetails);
				}
				if(patientVital.getId()!=0){
					session.update(patientVital);
				}
				if(patientGuarantor.getId()==0 && patientGuarantor.getGuarantorFirstName()!=null){
					session.save(patientGuarantor);
				}
				if(patientGuarantor.getId()!=0){
					session.update(patientGuarantor);
				}
				
				for(UserInsuranceDetails uid : insuranceListForUpdate){
					session.update(uid);
				}
				
			session.getTransaction().commit();
			if(!session.getTransaction().wasCommitted()){
				checkEditTransaction=true;
				}
			} catch (Exception e) {
				transaction.rollback();
				e.printStackTrace();
			}
			
			
			
			
			if(checkEditTransaction){
			
				for (UserInsuranceDetails userInsuranceDetails : temporaryDeleteInsuranceListProfile) {
					System.out.println("insurance delete status::::"+userInsuranceDetails.isCheckInsuranceDelete());
					//if(userInsuranceDetails.isCheckInsuranceDelete()){
						logger.info("inside delete insurance:::::::::::::");
					     Query query =getSessionFactory().getCurrentSession().createQuery("delete UserInsuranceDetails where insuranceId =:insuranceId and userId =:patientId");
						query.setInteger("insuranceId", userInsuranceDetails.getInsuranceId());
						query.setInteger("patientId", userInsuranceDetails.getUserId());
						query.executeUpdate();
				}
				
						
						for(PatientProviderClinic patientpClinic:temporaryDeleteClinicListProfile){
							System.out.println("clinic delete status::::"+patientpClinic.isCheckDeleteClinic()+"providerlocationid"+patientpClinic.getProviderLocationId());
							//if(patientpClinic.isCheckDeleteClinic()){
								Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientProviderClinic where clinicProviderId =:clinicProviderId and patientId =:patientId");
								query.setInteger("clinicProviderId",patientpClinic.getClinicProviderId());
								query.setInteger("patientId", patientpClinic.getPatientId());
								query.executeUpdate();
								
						}
						
						
			for(PatientAllergy allergy:deleteAllergyForProfilePageList){
				System.out.println("allergy delete status::::"+allergy.isCheckDbStatus());
					Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientAllergy where compositeAllergyId =:allergyCompositID and patientId=:patientID");
					query.setInteger("allergyCompositID",allergy.getCompositeAllergyId());
					//query.setInteger("dbID", allergy.getId());
					query.setInteger("patientID", allergy.getPatientId());
					query.executeUpdate();
			}
			
			for(PatientDiagnosesDetails diagnoses:deleteDiagnosesEditProfileList){
				logger.info("for each loop editPateintProfileDiagosesList========");
				Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientDiagnosesDetails where icdId =:diagnosesIcdId and patientId=:pateintID");
				query.setString("diagnosesIcdId",diagnoses.getIcdId());
				//query.setInteger("diagnosesId", diagnoses.getId());
				query.setInteger("pateintID", diagnoses.getPatientId());
				query.executeUpdate();
		    }
			
			for (UserInsuranceDetails saveInsurance : copyOfOriginalPateintInsuranceEditProfileList) {
				 if(!pateintInsuranceEditProfileList.contains(saveInsurance)){
					 getSessionFactory().getCurrentSession().save(saveInsurance);
				 }
			}
			
				 for(PatientProviderClinic saveclinic:copyOfOriginalPatientClinicDataListprofile){
						if(!patientClinicDataListprofile.contains(saveclinic)){
							getSessionFactory().getCurrentSession().save(saveclinic);
						}
				 }
				 
			for(PatientDiagnosesDetails saveDiagnoses:temporaryAddDiagnosesEditProfileList){
					getSessionFactory().getCurrentSession().save(saveDiagnoses);
			}
			for(PatientAllergy saveAllergy:temporaryAddAllergyForProfile){
				getSessionFactory().getCurrentSession().save(saveAllergy);
			}
			for(PatientProviderClinic patientpClinicforhistory:temporaryDeleteClinicListProfile)
			{
				logger.info("inside clinic history");
				PatientProviderClinicHistory pclinichistory=new PatientProviderClinicHistory();
				pclinichistory.setClinicProviderId(patientpClinicforhistory.getClinicProviderId());
				pclinichistory.setPatientId(patientpClinicforhistory.getPatientId());
				pclinichistory.setDeletedate(new Date());
				pclinichistory.setPrescibername("Dr "+new ContextUtil().getLoggerFirstName()+" "+new ContextUtil().getLoggedUserMiddleName()+" "+new ContextUtil().getLoggerLastName());
				pclinichistory.setProviderid(new ContextUtil().getProviderId());
				getSessionFactory().getCurrentSession().save(pclinichistory);
			}
			
	       }
			
		}catch (Exception e) {
			transaction.rollback();
			checkEditTransaction =false;
			e.printStackTrace();
	   }finally{
			session.flush();
			session.clear();
			session.close();
		}
		
	}

/*******Modified by SAURABH
 * *****for calculating number of doc in network
 * ********instead of calling proc, count from table
 * 	(non-Javadoc)
 * @see com.clinakos.data.dao.IDashBoardDAO#getTotalDoctor(int, int, java.lang.String, java.lang.String)
 */
	@Override
	public int getTotalDoctor(int limitFlag, int logedUserId,
			String logedUserFirstName, String logedUserLastName) {
		  List<ResultProcNetworkChart> rl=new ArrayList<ResultProcNetworkChart>();
		  int doctorCount = 0;
		  try {
			  final String HQL1 = "from ResultProcNetworkChart E WHERE E.sharedDoctorId = :logedUserId GROUP BY doctorId";
			  Query query = getSessionFactory().getCurrentSession().createQuery(HQL1);
			  query.setInteger("logedUserId", logedUserId);
			  rl= query.list();
			  doctorCount=rl.size();
		  } catch (HibernateException e) {
			  e.printStackTrace();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }		
		  System.out.println("######list size:for Network:anand:::--"+doctorCount );
		return doctorCount ;
	}

	
	public void saveClinicProviderData(ClinicProvider clinicData) {
		logger.info("saveClinicProviderData method starts in dashboardManageBean::::::::");
		try {
			save(clinicData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//For Psychopharm chart low initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumlowforintiationphase(String statusID) {
				
				logger.info("findnumberofpatientforlithiumlowforintiationphase :method calling");
				
				List<UserLoginDetail>medactionpatientlist=new ArrayList<UserLoginDetail>();
				List<UserLoginDetail> anticoagpatientlist=new ArrayList<UserLoginDetail>();
				
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				int providerId=new ContextUtil().getProviderId();
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_psychopharmClinic(?,?,?)");
					statement.setString(1, statusID);
					statement.setInt(2, providerId);
					statement.setString(3, DecryptionKey);
					System.out.println("********findnumberofpatientforlithiumlowforintiationphase**** "+statusID);																								
					 ResultSet resultSet = statement.executeQuery();
					
					 while (resultSet.next()) {
						 UserLoginDetail fc = new UserLoginDetail();
						 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
						 String patientId=resultSet.getString("PatientId");				
						 fc.setUserId(Integer.parseInt(patientId));				
						/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
						 resultList.add(fc);
						
					 }
					 medactionpatientlist.addAll(resultList);
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in findnumberofpatientforlithiumlowforintiationphase:: ", e);
					e.printStackTrace();
				}
				return medactionpatientlist;
			}

			//For Psychopharm chart medium initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumMediumforintiationphase(String statusID) {
				logger.info("findnumberofpatientforlithiumMediumforintiationphase :method calling");
				
				List<UserLoginDetail>medactionpatientlist=new ArrayList<UserLoginDetail>();
				List<UserLoginDetail> anticoagpatientlist=new ArrayList<UserLoginDetail>();
				
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				int providerId=new ContextUtil().getProviderId();
				logger.info("provider id "+providerId);
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_psychopharmClinic(?,?,?)");
					statement.setString(1, statusID);
					statement.setInt(2, providerId);
					statement.setString(3, DecryptionKey);
					System.out.println("********findnumberofpatientforlithiumMediumforintiationphase**** "+statusID);																							
					 ResultSet resultSet = statement.executeQuery();
					
					 while (resultSet.next()) {
						 UserLoginDetail fc = new UserLoginDetail();
						 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
						 String patientId=resultSet.getString("PatientId");				
						 fc.setUserId(Integer.parseInt(patientId));				
						/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
						 resultList.add(fc);
						
					 }
					 medactionpatientlist.addAll(resultList);
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in findnumberofpatientforlithiumMediumforintiationphase:: ", e);
					e.printStackTrace();
				}
				return medactionpatientlist;
			}

			//For Psychopharm chart high initiation
			public List<UserLoginDetail> findnumberofpatientforlithiumHighforintiationphase(String statusID) {
				logger.info("findnumberofpatientforlithiumHighforintiationphase :method calling");
				
				List<UserLoginDetail>medactionpatientlist=new ArrayList<UserLoginDetail>();
				List<UserLoginDetail> anticoagpatientlist=new ArrayList<UserLoginDetail>();
				
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				int providerId=new ContextUtil().getProviderId();
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_psychopharmClinic(?,?,?)");
					statement.setString(1, statusID);
					statement.setInt(2, providerId);
					statement.setString(3, DecryptionKey);
					System.out.println("********findnumberofpatientforlithiumHighforintiationphase**** "+statusID);																								
					 ResultSet resultSet = statement.executeQuery();
					
					 while (resultSet.next()) {
						 UserLoginDetail fc = new UserLoginDetail();
						 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
						 String patientId=resultSet.getString("PatientId");				
						 fc.setUserId(Integer.parseInt(patientId));				
						/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
						 resultList.add(fc);
						
					 }
					 medactionpatientlist.addAll(resultList);
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in findnumberofpatientforlithiumHighforintiationphase:: ", e);
					e.printStackTrace();
				}
				return medactionpatientlist;
			}

			// For psychopharm clinic chart while clicking the chart
			public List<UserLoginDetail> findnumberofpatientforlithium(String statusID) {
				
				logger.info("findnumberofpatientforlithiumHighforintiationphase :method calling");
				
				List<UserLoginDetail>medactionpatientlist=new ArrayList<UserLoginDetail>();
							
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				int providerId=new ContextUtil().getProviderId();
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_psychopharmClinic(?,?,?)");
					statement.setString(1, statusID);
					statement.setInt(2, providerId);
					statement.setString(3, DecryptionKey);																								
					 ResultSet resultSet = statement.executeQuery();
					
					 while (resultSet.next()) {
						 UserLoginDetail fc = new UserLoginDetail();
						 fc.setId(Integer.parseInt(resultSet.getString("PatientId")));
						 String patientId=resultSet.getString("PatientId");
						 fc.setUserId(Integer.parseInt(patientId));
						 fc.setFirstName(resultSet.getString("FName"));
						 fc.setMiddleName(resultSet.getString("mName"));
						 fc.setLastName(resultSet.getString("lName"));
						 fc.setDateOfBirth(resultSet.getDate("DOB"));
						 fc.setGender(resultSet.getString("gender"));
						 fc.setResult(resultSet.getDouble("result"));
						 
						/*System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getPatientId());*/
						 resultList.add(fc);
						
					 }
					 medactionpatientlist.addAll(resultList);
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in findnumberofpatientforlithiumHighforintiationphase:: ", e);
					e.printStackTrace();
				}
				return medactionpatientlist;
			}
			
			//For getting gender for particular patient for patient portal
			public String fetchGenderFromDB(int logedUserId) {
				
				String gender = new String();
				List<UserLoginDetail> genderList = new ArrayList<UserLoginDetail>();
				Criteria crit = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
				crit.add(Restrictions.eq("userId", logedUserId));
				genderList=crit.list();
				for(UserLoginDetail uld : genderList)
				{
					gender=uld.getGender();
				}
				return gender;
			}

			
			//Method to fetch oncology patient list
			@Override
			public List<UserLoginDetail> fetchListForOncology(String levelVal, int logedUserId) {
				// TODO Auto-generated method stub
				
				System.out.println("going to fetch list for oncology::"+levelVal+" and "+logedUserId);
logger.info("findnumberofpatientforlithiumHighforintiationphase :method calling");
				
				List<UserLoginDetail>oncologyPlist=new ArrayList<UserLoginDetail>();
							
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_fetchListForOncology('"+levelVal+"','"+DecryptionKey+"')}");
																													
					 ResultSet resultSet = statement.executeQuery();
					
					 while (resultSet.next()) {
						 UserLoginDetail fc = new UserLoginDetail();
						 fc.setId(Integer.parseInt(resultSet.getString("patientId")));
						 String patientId=resultSet.getString("patientId");
						 fc.setUserId(Integer.parseInt(patientId));
						 fc.setFirstName(resultSet.getString("fname"));
						 fc.setMiddleName(resultSet.getString("mname"));
						 fc.setLastName(resultSet.getString("lname"));
						 fc.setDateOfBirth(resultSet.getDate("dob"));
						 fc.setGender(resultSet.getString("gender"));
						 fc.setResult(resultSet.getDouble("result"));
						 
						System.out.println("ID is coming from Proc is "+resultSet.getString("PatientId") + " And Set as "+ fc.getUserId());
						 resultList.add(fc);
						
					 }
					 oncologyPlist.addAll(resultList);
					 System.out.println("list size coming for oncology is "+oncologyPlist.size());
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in findnumberofpatientforlithiumHighforintiationphase:: ", e);
					e.printStackTrace();
				}
				
				
				return oncologyPlist;
			}

			
			//Method to fetch Drug Gene interaction patient list by Anand S Jha on 11th September 2014
			@Override
			public List<UserLoginDetail> fetchListForDrugGeneInteraction(int limitFlag, int logedUserId, String drugNameFromHeatMap, String geneNameFromHeatMap,int provider_id) {
				// TODO Auto-generated method stub
				
				//System.out.println("Trying to fetch heatmap patient list for limitFlag"+limitFlag+"provided drug "+drugNameFromHeatMap+" and gene "+geneNameFromHeatMap+" in daoImpl::"+logedUserId+"provider::"+provider_id);
				List<UserLoginDetail>heatMapPList=new ArrayList<UserLoginDetail>();
				
				 List<UserLoginDetail> resultList = new ArrayList<UserLoginDetail>() ;
				
				try
				{
					
					/*logger.error("Fetching Patients under formulary " + levelVal4Formulary + " for Provider id "+ providerId );*/
					
					CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_fetchPListForHeatMap("+limitFlag+","+logedUserId+",'"+drugNameFromHeatMap+"','"+geneNameFromHeatMap+"',"+provider_id+",'"+DecryptionKey+"')}");
					
																													
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
				
						 
						/*System.out.println("ID is coming from Proc is "+resultSet.getString("user_id") + " And Set as "+ fc.getUserId());*/
						 resultList.add(fc);
						
					 }
					 heatMapPList.addAll(resultList);
					/* System.out.println("list size coming for heat Map patient list is "+heatMapPList.size());*/
					/*logger.error("Patient under formulary " + levelVal4Formulary + " for Provider id "+ providerId + " are " + resultList.size());*/
				}
				catch (Exception e) {
					logger.error("exception in heatMapPList:: ", e);
					e.printStackTrace();
				}
				
				
				return heatMapPList;
			}

			
			//Method to fetch Medicine list for Speciality vs Non Speciality Medicine by Anand S Jha on 24th September 2014
			@Override
			public List<SpecialityDrugCategory> fetchPatientBySpeciality(int providerId, String levelVal4SPVSNONSPDrugChart) {
				// TODO Auto-generated method stub
				List<SpecialityDrugCategory> drillDownSPDrugList=new ArrayList<SpecialityDrugCategory>();
				List<SpecialityDrugCategory> drillDownSPDrugListNew=new ArrayList<SpecialityDrugCategory>();
				 CallableStatement statement;
				  try {
					  System.out.println("fetching data fro drill down drug: for "+levelVal4SPVSNONSPDrugChart);					 
					  
					  statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_MedListDrillSpecialityvsNon("+providerId+",'"+levelVal4SPVSNONSPDrugChart+"')}");

					  ResultSet resultSet=statement.executeQuery();


					  while (resultSet.next()) {
						  SpecialityDrugCategory mld=new SpecialityDrugCategory();
						
						  logger.info("Drugs name is "+resultSet.getString("durgs")+"Total Patients are "+resultSet.getInt("patient_count"));
						 
						  String drug_name=resultSet.getString("durgs");
						  Integer total_patient=resultSet.getInt("patient_count");
						  
						  mld.setMedName(drug_name);
						  mld.setPatientCountForDrugCategory(total_patient);
						
						  System.out.println("ANAND:SPECIALITY:::"+mld.getMedName()+" with count :: "+mld.getPatientCountForDrugCategory());
						
						  drillDownSPDrugListNew.add(mld);
					  }
				  } catch (HibernateException e) {
					  // TODO Auto-generated catch block
					  e.printStackTrace();
				  } catch (SQLException e) {
					  // TODO Auto-generated catch block
					  e.printStackTrace();
				  }		

				  drillDownSPDrugList.addAll(drillDownSPDrugListNew);	 
				  	System.out.println("list size:for Drug Category:anand:::--"+ drillDownSPDrugList.size());
				 
					return drillDownSPDrugList;
			}
			
			
			public PatientGuarantor getPatientGuarantor(int loggedPatient) {
				logger.info("inside getPatientGuarantor "+loggedPatient);
				
				PatientGuarantor patientGuarantor = new PatientGuarantor();
				try 
				{
				Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientGuarantor.class);		
				criteria.add(Restrictions.eq("userID", loggedPatient));
				criteria.setProjection(Projections.projectionList()
						.add(Projections.property("id"))
						.add(Projections.property("userID"))
						.add(Projections.property("guarantorID"))
						.add(Projections.property("guarantorFirstName"))
						.add(Projections.property("guarantorMiddleName"))
						.add(Projections.property("guarantorLastName"))
						.add(Projections.property("guarantorDOB"))
						.add(Projections.property("guarantorGender"))
						.add(Projections.property("guarantorAddress1"))
						.add(Projections.property("guarantorAddress2"))
						.add(Projections.property("guarantorCity"))
						.add(Projections.property("guarantorState"))
						.add(Projections.property("guarantorZip"))
						.add(Projections.property("guarantorZip4"))
						.add(Projections.property("guarantorCountry"))
						.add(Projections.property("guarantorHomeTelephone"))
						.add(Projections.property("guarantorWorkTelephone"))
						.add(Projections.property("guarantorRelationship")));
				List<Object> guarantorData = criteria.list();
				
				for(Object obj : guarantorData)
				{
					Object[] row = (Object[]) obj;
					patientGuarantor.setId((Integer) row[0]);
					patientGuarantor.setUserID((Integer) row[1]);
					patientGuarantor.setGuarantorID((Integer) row[2]);
					patientGuarantor.setGuarantorFirstName((String) row[3]);
					patientGuarantor.setGuarantorMiddleName((String) row[4]);
					patientGuarantor.setGuarantorLastName((String) row[5]);
					patientGuarantor.setGuarantorDOB((Date) row[6]);
					patientGuarantor.setGuarantorGender((String) row[7]);
					patientGuarantor.setGuarantorAddress1((String) row[8]);
					patientGuarantor.setGuarantorAddress2((String) row[9]);
					patientGuarantor.setGuarantorCity((String) row[10]);
					patientGuarantor.setGuarantorState((String) row[11]);
					patientGuarantor.setGuarantorZip((String) row[12]);
					patientGuarantor.setGuarantorZip4((String) row[13]);
					patientGuarantor.setGuarantorCountry((String) row[14]);
					patientGuarantor.setGuarantorHomeTelephone((String) row[15]);
					patientGuarantor.setGuarantorWorkTelephone((String) row[16]);
					patientGuarantor.setGuarantorRelationship((String) row[17]);
				}
				}
				catch(Exception e){
					e.printStackTrace();
				}
				
				return patientGuarantor;
			}			

			@Override
			public UserInsuranceDetails getInsurance(int loggedID,
					int insuranceID) {
				// TODO Auto-generated method stub
				UserInsuranceDetails userInsuranceDetails = new UserInsuranceDetails();
				List<UserInsuranceDetails> list = new ArrayList<UserInsuranceDetails>();
				Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
				criteria.add(Restrictions.eq("userId", loggedID));
				criteria.add(Restrictions.eq("insuranceId", insuranceID));
				userInsuranceDetails=(UserInsuranceDetails) criteria.list().get(0);
				
				return userInsuranceDetails;
			}
/******@author PRASAD
 * for creating new user by admin/super-admin
 * (non-Javadoc)
 * MODIFY BY SAURABH, for map/save nurse & MA data into clinicDoctor
 */
			@Override
			public boolean saveSuperAdminData(UserLoginDetail userLoginDetailsNewPatient,DoctorDetail doctorDetail,LoginSecurity loginSecurity,ClinicProvider clinicProvider,
					ClinicDoctor clinicDoctor, ProviderUser providerUser) {
				logger.info("##########saveSuperAdminData calling in dashboardDaoImpl::::::");
				int loginSecurityId = 0;
				Session session = getSessionFactory().openSession();
				Transaction transaction = null;
				boolean checkStatus=false;
				try {
						try {
							transaction =session.beginTransaction();
							session.save(loginSecurity);
							loginSecurityId=loginSecurity.getId();
							logger.info("Generated Login Security Id after Saving to User_login_security table "+loginSecurityId);
							loginSecurity.setUserId(userLoginDetailsNewPatient.getFirstName()+loginSecurityId);
							session.update(loginSecurity);							
							providerUser.setUserId(loginSecurityId);
							session.save(providerUser);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in saveSuperAdminData::::::::loginSecurity::::dashBoardDaoImpl::::");
						}
						try {
							transaction =session.beginTransaction();
							userLoginDetailsNewPatient.setUserId(loginSecurityId);
							session.save(userLoginDetailsNewPatient);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in saving user data::::::::userLoginDetailsNewPatient::::dashBoardDaoImpl::::");
						}
				if (!(loginSecurity.getRole()==1)) {
//------------------------for doctor details						
						if(loginSecurity.getRole()==2)
						{
						try {
							transaction =session.beginTransaction();
							doctorDetail.setUserId(loginSecurityId);
							session.save(doctorDetail);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in saveSuperAdminData::::::::doctorDetail::::dashBoardDaoImpl::::");
						}
						}
						try {
							transaction =session.beginTransaction();
							session.save(clinicProvider);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in saveSuperAdminData::::::::clinicProvider::::dashBoardDaoImpl::::");
						}
						try {
							logger.info("########clinicProvider id ############"+clinicProvider.getId());
							clinicDoctor.setClinicProviderID(clinicProvider.getId());
							transaction =session.beginTransaction();
							clinicDoctor.setDoctorId(loginSecurityId);
							session.save(clinicDoctor);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in saveSuperAdminData::::::::clinicDoctor::::dashBoardDaoImpl::::");
						}
						
					
					logger.info("check status :::::::"+checkStatus);
				  }		
				} catch (Exception e) {
			      e.printStackTrace();
			      transaction.rollback();
			      checkStatus=false;
			      logger.error("error at savepateintData::::dashboardDao:::: ");
				}finally {
					session.flush();
					session.clear();
					session.close();
				}
				if (checkStatus) {
					// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Sorry,Your Process Has Been Failed! Please Try Again", ""));
				  if (StringUtils.isNotBlank(userLoginDetailsNewPatient.getEmail())) {
						String mailSubject="Welcome to Clinakos";
						String mailMsg="<table bgcolor=WhiteSmoke border=0 width=100%><tr><td><br>Welcome! &nbsp;</br>";
							   mailMsg="Hello  <b>"+userLoginDetailsNewPatient.getFirstName()+"</b>,  <br></br>";
							   mailMsg+="<br></br>Thank you for registering with Clinakos.Please use following credentials for login.<br></br>";
							   mailMsg+="<br></br>UserName:"+loginSecurity.getUserId()+"<br></br>";
							   mailMsg+="Password:"+loginSecurity.getConfirmPassword()+"<br></br>";
							   mailMsg+="<br></br><a href=\"https://care.clinakos.com/clinakos/\">Click </a>here to log in to your new account.<br></br>";
							   mailMsg+="<br></br>*******It is a system generated mail.Please do not reply.********</b></td></tr></table>";
							   try {
								new NotificationUtil().sendMail(userLoginDetailsNewPatient.getEmail().trim(),mailSubject,mailMsg);
							} catch (MessagingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    }
				 }
				 else{
					 logger.info("in else part saving status of pateint data:::::::"+checkStatus);
				 }   
				return checkStatus;
			}

			@Override
			public boolean saveOrganizationData(ProviderDetail providerDetail,ProviderLocation providerLocation){
        logger.info("saveOrganizationData calling in dashboardDaoImpl::::::");
				providerLocation.setCountry(providerLocation.getCountry().toUpperCase());
				providerLocation.setState(providerLocation.getState().toUpperCase());
				Session session = getSessionFactory().openSession();
				Transaction transaction = null;
				boolean checkStatus=false;
				
				providerDetail.setAddress1(providerLocation.getAddressLine1());
				providerDetail.setAddress2(providerLocation.getAddressLine2());
				providerDetail.setCity(providerLocation.getCity());
				providerDetail.setState(providerLocation.getState());
				providerDetail.setCountry(providerLocation.getCountry());
				providerDetail.setAccountId(providerLocation.getAccountId());
				providerDetail.setSiteId(providerLocation.getSiteId());
				providerDetail.setZip(providerLocation.getZipCode());
				providerDetail.setPrimaryFaxNumber(providerLocation.getFaxNumber());
				providerDetail.setPrimaryPhoneNumber(providerLocation.getPrimaryPhoneNumber());
				providerDetail.setLocationName(providerLocation.getLocation());
				providerLocation.setProviderDetail(providerDetail);
				
				
				providerDetail.getProviderLocation().add(providerLocation);
				try {
						try {
							transaction =session.beginTransaction();
							session.save(providerDetail);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in save ProviderDetail::::::::ProviderDetail::::dashBoardDao::::");
						}
						
						
						try {
							transaction =session.beginTransaction();
							session.save(providerLocation);
			                session.getTransaction().commit();
							if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
							checkStatus=true;
							}
						} catch (Exception e) {
							transaction.rollback();
							e.printStackTrace();
							//checkStatus=false;
							logger.error("error in Saving providerLocation::::::::providerLocation::::dashBoardDao::::");
						}
						
						
				 		
				} catch (Exception e) {
			      e.printStackTrace();
			      transaction.rollback();
			      checkStatus=false;
			      logger.error("error at savepateintData::::dashboardDao:::: ");
				}finally {
					session.flush();
					session.clear();
					session.close();
				}
				return checkStatus;
			}
			
			@Override
			public List<OrganisationType> organisationTypeList(){

				logger.info("OrganisationTypeList::::::method starts:::::");
				
				 List<OrganisationType> organisationTypeList = new ArrayList<OrganisationType>();
				
				 try{
				 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(OrganisationType.class);		  
				 organisationTypeList = criteria.list();
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at OrganisationTypeList in dashBoardDaoImpl:::::::::");
				 }
				return organisationTypeList;
			
				
			}
			@Override
			public List<RoleSecurity> getRoleSecurityList(){
				logger.info("getroleSecurityList::::::method starts:::::");
				 List<RoleSecurity> roleSecurityList = new ArrayList<RoleSecurity>();
				 try {
					 if (new ContextUtil().getRole().equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
						   Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(RoleSecurity.class);
						   criteria.add(Restrictions.eq("roleName", "ROLE_ADMIN"));
						  List<RoleSecurity> rsl=criteria.list();
						  roleSecurityList.addAll(rsl);
					   }
					 else if (new ContextUtil().getRole().equalsIgnoreCase("ROLE_ADMIN")) {
						 Criteria criteriaAdmin=getSessionFactory().getCurrentSession().createCriteria(RoleSecurity.class);
						 criteriaAdmin.add(Restrictions.ne("roleName", "ROLE_ADMIN"));
						 criteriaAdmin.add(Restrictions.ne("roleName", "ROLE_SUPER_ADMIN"));
						 criteriaAdmin.add(Restrictions.ne("roleName", "ROLE_PATIENT"));
						 List<RoleSecurity> rslAdmin=criteriaAdmin.list();
						 roleSecurityList.addAll(rslAdmin);
					  }
					 
					} catch (Exception e) {
						logger.error("exception in getroleSecurityList", e);
						e.printStackTrace();
					}
				return roleSecurityList;
			}
			
			@Override
			public List<ProviderDetail> getOrganizationList(){

				 List<ProviderDetail> providerDetailList = new ArrayList<ProviderDetail>();
				 String loggedInUserRoleName=new ContextUtil().getRole();
				 
				 try{
				      if (loggedInUserRoleName.equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
				    	  Criteria criteria1 = getSessionFactory().getCurrentSession().createCriteria(ProviderDetail.class);		  
							 providerDetailList = criteria1.list();
					 }	
				     else if (loggedInUserRoleName.equalsIgnoreCase("ROLE_ADMIN")) {
				    	 Criteria criteria2 = getSessionFactory().getCurrentSession().createCriteria(ProviderDetail.class);	
				    	 criteria2.add(Restrictions.eq("id", new ContextUtil().getProviderId())); 
						 providerDetailList = criteria2.list();
					 }  
				     else{
				    	  providerDetailList = new ArrayList<ProviderDetail>();
				      }
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at OrganisationTypeList in dashBoardDaoImpl:::::::::");
				 }
				return providerDetailList;
			}
			
			
			public boolean updateOrganizationData(ProviderDetail providerDetailEditObj){
				boolean checkEditTransaction = false;
				Session session = getSessionFactory().openSession();
			    Transaction transaction = null;
			    try {
				
				    try{
						transaction = session.beginTransaction();
						session.update(providerDetailEditObj);
						Query query =session.createQuery("update  ProviderLocation set accountId = :accountId,siteId=:siteId " +
								"where  providerId = :id");
						query.setString("accountId", providerDetailEditObj.getAccountId());
						query.setString("siteId", providerDetailEditObj.getSiteId());
						query.setInteger("id", providerDetailEditObj.getId());
						query.executeUpdate();
						//session.update(providerDetailEditObj);
					    session.getTransaction().commit();
					    if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
						     checkEditTransaction=true;
						 }
					} catch (Exception e) {
						transaction.rollback();
						e.printStackTrace();
					}
			    }catch (Exception e) {
					transaction.rollback();
					checkEditTransaction =false;
					e.printStackTrace();
			   }finally{
					session.flush();
					session.clear();
					session.close();
				}
				return checkEditTransaction;
				
			}
			public boolean deleteSelectedOrg(int orgId){
				Session session = getSessionFactory().openSession();
			    
				boolean checkEditTransaction = false;
				try {
            	logger.info("inside delete organization:::::::::::::");
					     Query query =getSessionFactory().getCurrentSession().createQuery("delete ProviderDetail where id =:providerId ");
						query.setInteger("providerId", orgId);
						query.executeUpdate();
						 Query query2 =getSessionFactory().getCurrentSession().createQuery("delete ProviderLocation where providerId =:providerId ");
							query2.setInteger("providerId", orgId);
							query2.executeUpdate();
				}catch (Exception e) {checkEditTransaction =false;
					e.printStackTrace();
			   }finally{
					session.flush();
					session.clear();
					session.close();
				}
				
				
				return checkEditTransaction ;
			}

			
			public List<UserLoginDetail> getUserLoginDetailListToDisplay(){
				
				logger.info("getUserLoginDetailListToDisplay::::::method starts:::::");
				
				 List<UserLoginDetail> userLoginDetailList = new ArrayList<UserLoginDetail>();
				
				 try{
			/*	 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);		  
				 userLoginDetailList = criteria.list();*/
					/* String sql="";*/
					 
					 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProviderUser.class);
					 criteria.add(Restrictions.eq("providerId", new ContextUtil().getProviderId()));
					 List<ProviderUser>providerUserList=criteria.list();
					 List<Integer>userIds=new ArrayList<Integer>();
					 for(ProviderUser providerUser:providerUserList){
						 userIds.add(providerUser.getUserId());
					 }
	//--------------modify by saurabh, for patientProviderList size is zero.IN query shoe exception when list is empty
					 if (providerUserList.size()>0) {
						 Criteria userDetailsCriteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
						 userDetailsCriteria.add(Restrictions.in("userId", userIds));
						 userLoginDetailList=userDetailsCriteria.list();
					 }
					 else {
						 userLoginDetailList=new ArrayList<UserLoginDetail>();
					}
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at OrganisationTypeList in dashBoardDaoImpl:::::::::");
				 }
				return userLoginDetailList;
			}
			
			public DoctorDetail getdisplayDoctorDetailListToDisplay(int userId){
				
				logger.info("Doctor Details of ::::::method starts:::::"+userId);
				
				 List<DoctorDetail> doctorDetailList = new ArrayList<DoctorDetail>();
				 DoctorDetail doctorDetailObj = new DoctorDetail();
				
				 try{
				 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(DoctorDetail.class);	
				 criteria.add(Restrictions.eq("userId", userId));
				 doctorDetailList = criteria.list();
				 
				 for(DoctorDetail dctrObj:doctorDetailList){
					 doctorDetailObj.setDea(dctrObj.getDea());
					 doctorDetailObj.setSpeciality(dctrObj.getSpeciality());
					 doctorDetailObj.setIsActive(dctrObj.getIsActive());
					 doctorDetailObj.setNpi(dctrObj.getNpi());
					 doctorDetailObj.setDocLicenseNumber(dctrObj.getDocLicenseNumber());
					 doctorDetailObj.setDocLicenseState(dctrObj.getDocLicenseState());
					 doctorDetailObj.setUserId(dctrObj.getUserId());
					 
				 }
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at OrganisationTypeList in dashBoardDaoImpl:::::::::");
				 }
				return doctorDetailObj;
			}
			
			public LoginSecurity getLoginsecurityDetails(String userId){
				logger.info("LoginSecurity Details ::::::method starts:::::"+userId);
				
				 List<LoginSecurity> loginSecurityDetailList = new ArrayList<LoginSecurity>();
				 LoginSecurity loginSecurityDetailObj = new LoginSecurity();
				
				 try{
				 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(LoginSecurity.class);	
				 criteria.add(Restrictions.eq("userId", userId));
				 loginSecurityDetailList = criteria.list();
				 
				 for(LoginSecurity loginObj:loginSecurityDetailList){
					 loginSecurityDetailObj.setRole(loginObj.getRole());
					 loginSecurityDetailObj.setStatus(loginObj.isStatus());
					 
				 }
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at LoginSecurity in dashBoardDaoImpl:::::::::"+e);
				 }
				return loginSecurityDetailObj;
			}
			public boolean updateUserData(UserLoginDetail userLoginDetailEditObj,DoctorDetail doctorDetailEditObj,LoginSecurity loginSecurityEditObj){
				logger.info("LoginSecurity Details ::::::Upadte method starts:::::"+userLoginDetailEditObj.getUserId());
				
				Session session = getSessionFactory().openSession();
			    Transaction transaction = null;
			    
				boolean checkEditTransaction = false;
				try {
					
					try{
						transaction = session.beginTransaction();
						session.update(userLoginDetailEditObj);
					 
					 
					if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
						checkEditTransaction=true;
						}
					} catch (Exception e) {
						transaction.rollback();
						e.printStackTrace();
					}
					if(loginSecurityEditObj.getRole()==2){
					try{
						System.out.println("inside try of DoctorDetail  "+doctorDetailEditObj.getUserId());
						Query query =getSessionFactory().getCurrentSession().createQuery("update  DoctorDetail set dea = :deaNo,npi=:npiNo,docLicenseNumber=:docLicenseNo," +
								"docLicenseState=:docLicenseState,speciality=:Userspeciality " +
								" where  userId =:userid");
						query.setString("deaNo", doctorDetailEditObj.getDea());
						query.setString("npiNo", doctorDetailEditObj.getNpi());
						query.setString("docLicenseNo", doctorDetailEditObj.getDocLicenseNumber());
						query.setString("docLicenseState", doctorDetailEditObj.getDocLicenseState());
						query.setString("Userspeciality", doctorDetailEditObj.getSpeciality());
						query.setInteger("userid", doctorDetailEditObj.getUserId());
						
						query.executeUpdate();
						
					
					} catch (Exception e) {
						logger.info("Exception inside doctor detail update "+e);
						
					}
					}
					
					try{
						System.out.println("inside try of Login sec  "+userLoginDetailEditObj.getUserId());
						Query query =getSessionFactory().getCurrentSession().createQuery("update  LoginSecurity set role = :roleId,status=:statusOfUser " +
								"where  id = :userId");
						query.setInteger("roleId", loginSecurityEditObj.getRole());
						query.setBoolean("statusOfUser", loginSecurityEditObj.isStatus());
						query.setInteger("userId", userLoginDetailEditObj.getUserId());
						
						
					
					} catch (Exception e) {
						logger.info("Exception inside LoginSecurity update "+e);
					}
					session.getTransaction().commit();
				}catch (Exception e) {
					transaction.rollback();
					checkEditTransaction =false;
					e.printStackTrace();
			   }finally{
					session.flush();
					session.clear();
					session.close();
				}
				return checkEditTransaction;
				
			
			}
			
			public boolean deleteSelectedUser(int userId,String emailId){
              Session session = getSessionFactory().openSession();
			    
				boolean checkEditTransaction = false;
				try {
            	logger.info("inside delete deleteSelectedUser :::::::::::::");
					    Query queryLogin =getSessionFactory().getCurrentSession().createQuery("delete UserLoginDetail where userId =:userId ");
					    queryLogin.setInteger("userId", userId);
					    queryLogin.executeUpdate();
						
						Query queryDoctor =getSessionFactory().getCurrentSession().createQuery("delete DoctorDetail where userId =:userId ");
						queryDoctor.setInteger("userId", userId);
						queryDoctor.executeUpdate();
							
						Query queryLoginSec =getSessionFactory().getCurrentSession().createQuery("delete LoginSecurity where id =:userId ");
						queryLoginSec.setInteger("userId", userId);
						queryLoginSec.executeUpdate();
						
						Query queryProviderUser =getSessionFactory().getCurrentSession().createQuery("delete ProviderUser where userId =:userId ");
						queryProviderUser.setInteger("userId", userId);
						queryProviderUser.executeUpdate();
						
						Query queryClinicDoctor =getSessionFactory().getCurrentSession().createQuery("delete ClinicDoctor where doctorId =:userId ");
						queryClinicDoctor.setInteger("userId", userId);
						queryClinicDoctor.executeUpdate();
						
				}catch (Exception e) {checkEditTransaction =false;
					e.printStackTrace();
			   }finally{
					session.flush();
					session.clear();
					session.close();
				}
				
				
				return checkEditTransaction ;
			}
			
			public List<MasterTimeZone> fetchTimezoneList(){
				logger.info("Fetching Time Zone List  :::::::::::::");
				 List<MasterTimeZone> timeZoneList = new ArrayList<MasterTimeZone>();
				
				 try{
				 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(MasterTimeZone.class);		  
				 timeZoneList = criteria.list();
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at MasterTimeZone in dashBoardDaoImpl:::::::::");
				 }
				return timeZoneList;
			
			}
			
			public List<ProviderDetail> fetchCompanyList(int providerId){
				logger.info("Fetching companyList  :::::::::::::"+providerId);
				 List<ProviderDetail> companyList = new ArrayList<ProviderDetail>();
				
				 try{
	//----if condition to check logged in admin is provider-admin or super-admin by saurabh				 
					 if (providerId>0) {
						 System.out.println("########inside if");
						 Criteria criteria1 = getSessionFactory().getCurrentSession().createCriteria(ProviderDetail.class);
						 criteria1.add(Restrictions.eq("id",new ContextUtil().getProviderId())) ;
						 companyList = criteria1.list();
					 }
					 else if(providerId==0) {
						 System.out.println("########inside else if");
						 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProviderDetail.class);		  
						 companyList = criteria.list();
					}
					 
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at fetchCompanyList in dashBoardDaoImpl:::::::::");
				 }
				return companyList;
			}
			public List <ClinicMaster> fetchClinicList(){
				logger.info("Fetching ClinicDoctor List  :::::::::::::");
				 List<ClinicMaster> clinicList = new ArrayList<ClinicMaster>();
				
				 try{
				 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class);		  
				 clinicList = criteria.list();
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at ClinicDoctor in dashBoardDaoImpl:::::::::");
				 }
				return clinicList;
			}
/*******Modified by : saurabh
 * 
 * *******for role based fetch of the list
 * @see com.clinakos.data.dao.IDashBoardDAO#fetchProviderLocationList(int, java.lang.String)
 */
			public List<ProviderLocation> fetchProviderLocationList(int providerId, String roleName){
				logger.info("Fetching ProviderLocation List  :::::::::::::"+providerId);
				 List<ProviderLocation> providerLocationList = new ArrayList<ProviderLocation>();
				 try{
					 if (roleName.equalsIgnoreCase("ROLE_SUPER_ADMIN")) {
						Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProviderLocation.class);
						 providerLocationList = criteria.list();
					 }
					else{
						Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProviderLocation.class);
						 criteria.add(Restrictions.eq("providerId", providerId));
						 providerLocationList = criteria.list();
					}
				 
				 }catch(Exception e){
					 e.printStackTrace();
					 logger.error("error at ClinicDoctor in dashBoardDaoImpl:::::::::");
				 }
				return providerLocationList;
			}
			
			public boolean saveBrachAddress(ProviderLocation providerLocation){
				logger.info("Saving ProviderLocation for Branch Address :::::::::::::");
				Session session = getSessionFactory().openSession();
				Transaction transaction = null;
				boolean checkStatus=false;
			   ProviderDetail providerDetail = new ProviderDetail();
			   providerDetail.setId(providerLocation.getProviderId());
               providerLocation.setProviderDetail(providerDetail);
				
				
				providerDetail.getProviderLocation().add(providerLocation);
				try {
					transaction =session.beginTransaction();
					session.save(providerLocation);
	                session.getTransaction().commit();
					if(!getSessionFactory().getCurrentSession().getTransaction().wasCommitted()){
					checkStatus=true;
					}
				} catch (Exception e) {
					transaction.rollback();
					e.printStackTrace();
					//checkStatus=false;
					logger.error("error in save providerLocation::::::::Branch Address::::dashBoardDaoImpl::::");
				}
				return checkStatus ;
			}
			 public void deleteBranchLocation(int locId){
				 logger.info("Deleting ProviderLocation for Branch Address :::::::::::::");
				 try {
					 Query queryDoctor =getSessionFactory().getCurrentSession().createQuery("delete ProviderLocation where id =:locId ");
						queryDoctor.setInteger("locId", locId);
						queryDoctor.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
				 
			 }

			
			public List<ProviderUser> findProviderIdForAdmin() {
				// TODO Auto-generated method stub
				return null;
			}


			/**
			 * Save Patient Vital Data  
			 * @param patientVital
			 * @param patientId
			 * @see com.clinakos.data.dao.IDashBoardDAO#addPatientVital(PatientVital, int)
			 * 	
			 */
			public void addPatientVital(PatientVital patientVital, int patientId) {
				try {
					getSessionFactory().getCurrentSession().save(patientVital); // Save data 
				} catch (HibernateException he) {
					he.printStackTrace();
				}
				
			}


/*@author: saurabh
 * To check socialSecurityNumber is exist
 * @see com.clinakos.data.dao.IDashBoardDAO#checkForsocialSecurityNumber(java.lang.String)
 */
  public boolean checkForsocialSecurityNumber(
	String socialSecurityNumber) {
	  boolean status = false;
		try {
			String sql ="from UserLoginDetail where social_security_number ='"+socialSecurityNumber+"'";
			Query query = getSessionFactory().getCurrentSession().createQuery(sql);
			List socialSecurityNumberFromDB = query.list();
			logger.info("getting email from db:::::::"+socialSecurityNumberFromDB.size());
			if(socialSecurityNumberFromDB.size() != 0){
				status =true;
			}
		} catch (Exception e) {
		
		}
		return status;
  }

/**
 * Get Patient Vital Data Based on Patient Id 
 * @see com.clinakos.data.dao.IDashBoardDAO#getLatestPatientVitalDataForDispay(int)
 * @param patientId
 * @return Patient Vital Object 
 */
public PatientVital getLatestPatientVitalDataForDispay(int patientId) {
	PatientVital patientVitalDataForDisplay=new PatientVital();
    try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientVital.class);
		criteria.add(Restrictions.eq("patientIdForVital", patientId));
		criteria.setProjection(Projections.max("id"));
		int maxId=(Integer) criteria.list().get(0);
		patientVitalDataForDisplay=(PatientVital) getSessionFactory().getCurrentSession().get(PatientVital.class, maxId);
		
	} catch (HibernateException he) {
		he.printStackTrace();
	}
    catch (NullPointerException ne) {
	   ne.printStackTrace();
	}
	return patientVitalDataForDisplay;
}




	

/**************@uthor: saurabh
 * *********for saving forgot password link in forgotPasswordSendingLink
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IDashBoardDAO#saveforgotPasswordSendingLink(java.lang.String, int)
 */
public void saveforgotPasswordSendingLink(ForgotPasswordSendingLink forgotPasswordSendingLink) {
	logger.info("###### saveforgotPasswordSendingLink method start in Dashboard  DAO###############");
	try {
		getSessionFactory().getCurrentSession().save(forgotPasswordSendingLink);
	} 
	catch (HibernateException he) {
		he.printStackTrace();
		logger.error("###### exception in saveforgotPasswordSendingLink method in Dashboard  DAO###############",he);
	}
	catch (Exception e) {
		e.printStackTrace();
		
	}
}
	




/*****@author SAURABH	
 * **********for checking valid forget password url & authorize user
 */
  public ForgotPasswordSendingLink checkValidForgotPasswordUrl(String queryString) {
	  try {
		logger.info("#############checkValidForgotPasswordUrl called in dashboard DAOimpl##############"+queryString+"  :::"+UriUtils.encodeUri(queryString, "UTF-8")+"  ::::"+
					URLEncoder.encode(queryString,"UTF-8"));
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	  ForgotPasswordSendingLink forgotPasswordSendingLink= new ForgotPasswordSendingLink();
	  try {
		  Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ForgotPasswordSendingLink.class);
		  criteria.add(Restrictions.eq("sendingLink", queryString));
		  criteria.add(Restrictions.eq("active", true));
		  forgotPasswordSendingLink=(ForgotPasswordSendingLink) criteria.list().get(0);
	} catch (Exception e) {
		e.printStackTrace();
		forgotPasswordSendingLink= new ForgotPasswordSendingLink();
	}
	  
	  return forgotPasswordSendingLink;
  }

/*******@author Saurabh
 * 
 * ****for updating password using forgot password
*/
public void resetPasswordForForgotPassword(int userId, String confirmPassword) {
	logger.info("#############resetPasswordForForgotPassword called in dashboard DAOimpl##############3");
	try {
		Query query = getSessionFactory().getCurrentSession().createQuery("update LoginSecurity set password = :confirmPassword" +
				" where id = :userId");
		query.setParameter("confirmPassword", confirmPassword);
		query.setParameter("userId", userId);
		int result = query.executeUpdate();
	} 
	catch (HibernateException he) {
		he.printStackTrace();
		logger.error("###### exception in resetPasswordForForgotPassword method in Dashboard  DAO###############",he);
	}
	catch (Exception e) {
		e.printStackTrace();
		logger.error("###### exception in resetPasswordForForgotPassword method in Dashboard  DAO###############",e);
	}
}

/*******@author Saurabh
 * 
 * ****for updating password Reset Date, for forgot password
*/
public void updatePasswordResetDate(int forgotPasswordSendingLinkId) {
	logger.info("#############updatePasswordResetDate called in dashboard DAOimpl##############3");
	try {
		Query query = getSessionFactory().getCurrentSession().createQuery("update ForgotPasswordSendingLink set modifiedDate = :currentDate, active = :isActive" +
				" where id = :id");
		query.setParameter("currentDate", new Date());
		query.setParameter("isActive", false);
		query.setParameter("id", forgotPasswordSendingLinkId);
		int result = query.executeUpdate();
 } 
 catch (HibernateException he) {
	he.printStackTrace();
	logger.error("###### exception in resetPasswordForForgotPassword method in Dashboard  DAO###############",he);
 }
 catch (Exception e) {
	e.printStackTrace();
	logger.error("###### exception in resetPasswordForForgotPassword method in Dashboard  DAO###############",e);
 }
 
}

/**
 * Save Patient Upload data in middle table 
 */
public void savePatientUploadData(List<PatientUploadData> patientUploadDataList,int providerId,int loginId) {
	
	Query query=getSessionFactory().getCurrentSession().createQuery("delete PatientUploadData where adminId=:createdBy");
	query.setParameter("createdBy", loginId);
	
	int result=query.executeUpdate();
	System.out.println("Result : :::: "+result);
	/*if(result>0){*/
		for(PatientUploadData patUploadData:patientUploadDataList){
			
			getSessionFactory().getCurrentSession().save(patUploadData);
			
		/*}*/
	}
	
 }


public String saveUploadedCsvData(int providerId,int providerLocationId)  {
	
	
	CallableStatement statement=null;
	
	try {
        /*session=getSessionFactory().getCurrentSession();
        transaction=session.beginTransaction();*/
        
        
		statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_upload_patient_data(?,?,?)");
		statement.getConnection().setAutoCommit(false);
		statement.setInt(1, providerId);
		statement.setInt(2, providerLocationId);
		statement.setString(3, DecryptionKey);
		   ResultSet resultSet=statement.executeQuery();
		   System.out.println("Fetch Direction"+resultSet.getFetchDirection()+"Fetch Size"+resultSet.getFetchSize());
		   int size=resultSet.getRow();
		   String message=Integer.toString(size);
		   System.out.println("No of Row "+message );
		   
		   
		 statement.getConnection().commit();
		 
		 
		   
	} catch (HibernateException he) {
		String message="Falied to save data in database "+he.getMessage();
		
		he.printStackTrace();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return message;
	}
	catch (SQLException se) {
		String message="Falied to save data in database "+se.getMessage();
		
		se.printStackTrace();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	catch (NullPointerException ne) {
		String message="Failed to save data in database Null Pointer Exception "+ne.getMessage();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	catch (Exception e) {
		String message="Failed to save data in database "+e.getMessage();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e1) {
						e1.printStackTrace();
		}
		return message;
	}
	
	finally{
		try {
			if(statement!=null){
				statement.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		
	}
	return "";
   
	
}


public List<PatientUploadData> getUploadedPatientData(int providerId) {
	
	List<PatientUploadData> uploadedPatientDataList=new ArrayList<PatientUploadData>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientUploadData.class);
	criteria.add(Restrictions.eq("providerId", providerId));
	uploadedPatientDataList=criteria.list();
	return uploadedPatientDataList;
}


public void saveUploadedDataErrorValue(
		List<PatientUploadDataErrorMessages> uploadedDataErrorList,int providerId,int adminId) {
	Query query=getSessionFactory().getCurrentSession().createQuery("delete PatientUploadDataErrorMessages where adminId=:createdBy");
	query.setParameter("createdBy", adminId);
	
	int result=query.executeUpdate();
	for(PatientUploadDataErrorMessages uploadDataErrorMessages:uploadedDataErrorList){
		getSessionFactory().getCurrentSession().save(uploadDataErrorMessages);
	}
	
}


public List<PatientUploadDataErrorMessages> getPatientUploadedDataErrorMessage(
		int providerId, int loginId) {
	List<PatientUploadDataErrorMessages> patientUploadDataErrorMessageList=new ArrayList<PatientUploadDataErrorMessages>();
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientUploadDataErrorMessages.class);
		criteria.add(Restrictions.eq("providerId", providerId));
		patientUploadDataErrorMessageList=criteria.list();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return patientUploadDataErrorMessageList;
}


 public List<ProviderDetail> getProviderDetailData() {
	List<ProviderDetail> providerDetailList=new ArrayList<ProviderDetail>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProviderDetail.class);
	providerDetailList=criteria.list();
	return providerDetailList;
}


public List<ProviderLocation> getProviderLocationDetailData(int providerId) {
	List<ProviderLocation> providerLocationList=new ArrayList<ProviderLocation>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ProviderLocation.class);
	criteria.add(Restrictions.eq("providerId", providerId));
	providerLocationList=criteria.list();
	return providerLocationList;
}

@Override
public List<ChartModel> getGenderPieChartData(int providerId,
		String decryptionkey) {
	List<ChartModel>genderPieChartData=new ArrayList<ChartModel>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_gender_split("+providerId+",'"+decryptionkey+"')}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			ChartModel model=new ChartModel();
			model.setCountValue(resultSet.getInt("no_of_patients"));
			model.setRangeName(resultSet.getString("gender_split"));
			genderPieChartData.add(model);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return genderPieChartData;
}

@Override
public List<ChartModel> getAgeSplitChartData(int providerId) {
	List<ChartModel>agePieChartData=new ArrayList<ChartModel>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_age_split("+providerId+")}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			ChartModel model=new ChartModel();
			model.setCountValue(resultSet.getInt("age_counter"));
			model.setRangeName(resultSet.getString("age_split"));
			model.setLowRangeVal(resultSet.getString("age_split_low_range"));
			model.setHighRangeVal(resultSet.getString("age_split_high_range"));
			agePieChartData.add(model);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return agePieChartData;
}

@Override
public List<ChartModel> getNoOfPatientsOnRAdrugs(int providerId) {
	List<ChartModel>noOfPatientsOnRAdrugs=new ArrayList<ChartModel>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharama_ra_drug("+providerId+")}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			ChartModel model=new ChartModel();
			model.setCountValue(resultSet.getInt("no_of_patients"));
			model.setRangeName(resultSet.getString("drug_name"));
			noOfPatientsOnRAdrugs.add(model);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return noOfPatientsOnRAdrugs;
}

@Override
public List<ChartModel> getEnbrelConcurrentMeds(int providerId) {
	List<ChartModel>noOfPatientsOnRAdrugs=new ArrayList<ChartModel>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_enbrel_concurrent_meds("+providerId+")}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			ChartModel model=new ChartModel();
			model.setCountValue(resultSet.getInt("drug_count"));
			model.setRangeName(resultSet.getString("drugs"));
			model.setDrugNameId(resultSet.getDouble("drug_name_id"));
			noOfPatientsOnRAdrugs.add(model);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return noOfPatientsOnRAdrugs;
}

/**
 * Method used to get User details based on provider id,low age range and High age range selected from Pharma analytic Age split graph
 * 
 */
@Override
public List<UserLoginDetail> fetchPateintsForAgeSplit(int providerId,
		int lowRangeValue, int highRangeValue) {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_fetch_patients_on_age("+providerId+","+lowRangeValue+","+highRangeValue+",'"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		userDetails=getUserDetails(resultSet); 
		
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}catch(SQLException se){
	se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return userDetails;
}


/**
 * 
 * Use this method only for analytic part:Doctor analytic and Pharma analytic
 * @param resultSet
 * @return
 * @throws NumberFormatException
 * @throws SQLException
 */
private List<UserLoginDetail> getUserDetails(ResultSet resultSet) throws NumberFormatException, SQLException {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
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
		 
		 userDetails.add(fc);
	 }
	return userDetails;
}

@Override
public List<UserLoginDetail> fetchPateintsForGenderSplit(int providerId,
		String rangeName, String decryptionkey) {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_fetch_patients_on_gender("+providerId+",'"+rangeName+"','"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		userDetails=getUserDetails(resultSet); 
		
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}catch(SQLException se){
	se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return userDetails;
}



@Override
public List<UserLoginDetail> fetchPatientsForEnbrelConcurrentMeds(
		int providerId, String drugName, double drugId, double drugNameId,String decryptionkey) {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_fetch_patients_concurrent_meds("+providerId+","+drugId+","+drugNameId+",'"+drugName+"','"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		userDetails=getUserDetails(resultSet); 
		
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}catch(SQLException se){
	se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return userDetails;
}

@Override
public List<UserLoginDetail> fetchPatientsForRAdrug(int providerId,
		String drugName, double drugId, String decryptionkey) {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_fetch_patients_RA_drug("+providerId+","+drugId+",'"+drugName+"','"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		userDetails=getUserDetails(resultSet); 
		
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}catch(SQLException se){
	se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return userDetails;
}

@Override
public List<ChartModel> getTimeOnEnbrelData(int providerId) {
	List<ChartModel>noOfPatientsOnEnbrel=new ArrayList<ChartModel>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call pharma_time_on_enbrel("+providerId+")}");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()){
			ChartModel model=new ChartModel();
			model.setCountValue(resultSet.getInt("no_of_patients"));
			model.setRangeName(resultSet.getString("month_split"));
			model.setPatients(resultSet.getString("patients"));
			noOfPatientsOnEnbrel.add(model);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	return noOfPatientsOnEnbrel;
}

@Override
public List<UserLoginDetail> getPatientInfoForTimeOnEnbrel(int providerId,
		String patientIds, String decryptionkey) {
	List<UserLoginDetail>userDetails=new ArrayList<UserLoginDetail>();
	try{
		CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call patient_info("+providerId+",'"+patientIds+"','"+DecryptionKey+"')}");
		ResultSet resultSet = statement.executeQuery();
		userDetails=getUserDetails(resultSet); 
	}catch(NumberFormatException nfe){
		nfe.printStackTrace();
	}catch(SQLException se){
	se.printStackTrace();
	}catch(Exception e){
		e.printStackTrace();
	}
	return userDetails;
}

/***********@author: saurabh
 * ************to display list of locations on click of edit provider
 * *****************by role super-admin
 */

 public List<ProviderLocation> getEditProviderLocationList(int providerId) {
	 logger.info("Fetching ProviderLocation List  :::::::::::::"+providerId);
	 List<ProviderLocation> editProviderLocationList = new ArrayList<ProviderLocation>();
	 try{
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(ProviderLocation.class);
			 criteria.add(Restrictions.eq("providerId", providerId));
			 editProviderLocationList = criteria.list();
		}
	 catch(Exception ex){
		 logger.error("<<<<<<<<<<exception in getEditProviderLocationList dashBoard Dao>>>>>>>>>", ex);
		 ex.printStackTrace();
	 }
	 return editProviderLocationList;
 }

/**
 * Give the Pbm Name Data Based on userId 
 * @see com.clinakos.data.dao.IDashBoardDAO#getPbmNameData(int)
 * @param userId
 * @return Pbm Name List 
 */
public List<String> getPbmNameData(int userId) {
	List<String> pbmNameList=new ArrayList<String>();
	try {
		/*Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.isNotNull("pbm"));
		criteria.add(Restrictions.isNotNull("insuranceId"));*/
		String query="select pbm from user_insurance_details where pbm is not null and user_id="+userId;
		PreparedStatement statement=(PreparedStatement) getSessionFactory().getCurrentSession().connection().prepareCall(query);
		ResultSet set=statement.executeQuery();
		
		while(set.next()){
			String pbm=new String();
			pbm=set.getString("pbm");
			pbmNameList.add(pbm);
		}
		
		
		
		logger.info("PBM NAMe Size "+pbmNameList.size()+"PBM NAMe "+pbmNameList);
	}catch(HibernateException he){
		he.printStackTrace();
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return pbmNameList;
}

/******@author SAURABH 	
 * ********to save/update edited provider data
 * 
 */
	public void saveEditProviderLocationObj(ProviderLocation editProviderLocationObj) {
		logger.info("::::::::::::inside saveEditProviderLocationObj dashboard DAO::::::");
		try{
			Query query =getSessionFactory().getCurrentSession().createQuery("update ProviderLocation set addressLine1= :addressLine1, addressLine2= :addressLine2,"
					+ " zipCode= :zipCode, city= :city, state= :state, primaryPhoneNumber= :primaryPhoneNumber, faxNumber= :faxNumber, location= :location, "
					+ "accountId= :accountId, siteId= :siteId where id= :id");
			query.setInteger("id", editProviderLocationObj.getId());
			query.setString("addressLine1", editProviderLocationObj.getAddressLine1());
			query.setString("addressLine2", editProviderLocationObj.getAddressLine2());
			query.setString("zipCode", editProviderLocationObj.getZipCode());
			query.setString("city", editProviderLocationObj.getCity());
			query.setString("state", editProviderLocationObj.getState());
			query.setString("primaryPhoneNumber", editProviderLocationObj.getPrimaryPhoneNumber());
			query.setString("faxNumber", editProviderLocationObj.getFaxNumber());
			query.setString("location", editProviderLocationObj.getLocation());
			query.setString("accountId", editProviderLocationObj.getAccountId());
			query.setString("siteId", editProviderLocationObj.getSiteId());
			int rowsAffected = query.executeUpdate();
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

/*****@author Saurabh
 * 	(non-Javadoc)
 * @see com.clinakos.data.dao.IDashBoardDAO#saveDiagnosisUploadData(java.util.List, int, int, int)
 */
public void saveDiagnosisUploadData(List<DiagnosisUploadData> diagnosisUploadDataList, int providerId, int loginId,
		int providerLocationId) {
		try{
			Query query=getSessionFactory().getCurrentSession().createQuery("delete DiagnosisUploadData where adminId=:createdBy");
			query.setParameter("createdBy", loginId);
			int result=query.executeUpdate(); 
			System.out.println("Result : :::: "+result);
			for (DiagnosisUploadData diagnosisUploadData : diagnosisUploadDataList) {
				getSessionFactory().getCurrentSession().save(diagnosisUploadData);
			}
		}
		catch(HibernateException he){
			he.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
  }

/****@author Saurabh
 * For getting all the uploaded diagnosis data
 * @see com.clinakos.data.dao.IDashBoardDAO#getDiagnosisUploadData(int)
 */
public List<DiagnosisUploadData> getDiagnosisUploadData(int loginId) {
	List<DiagnosisUploadData> diagnosisUpload=new ArrayList<DiagnosisUploadData>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(DiagnosisUploadData.class);
	criteria.add(Restrictions.eq("adminId", loginId));
	diagnosisUpload=criteria.list();
	return diagnosisUpload;
}


/****@author Saurabh
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IDashBoardDAO#saveDiagnosisData()
 */
public String saveDiagnosisData() {
	CallableStatement statement=null;
	try {
		statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("call proc_upload_patient_diagnosis_data(?)");
		statement.getConnection().setAutoCommit(false);
		statement.setInt(1, new ContextUtil().getLoginId());
		   ResultSet resultSet=statement.executeQuery();
		   System.out.println("Fetch Direction"+resultSet.getFetchDirection()+"Fetch Size"+resultSet.getFetchSize());
		   int size=resultSet.getRow();
		   String message=Integer.toString(size);
		   System.out.println("No of Row "+message );
		 statement.getConnection().commit();
	} catch (HibernateException he) {
		String message="Falied to save data in database "+he.getMessage();
		he.printStackTrace();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return message;
	}
	catch (SQLException se) {
		String message="Falied to save data in database "+se.getMessage();
		
		se.printStackTrace();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	catch (NullPointerException ne) {
		String message="Failed to save data in database Null Pointer Exception "+ne.getMessage();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}
	catch (Exception e) {
		String message="Failed to save data in database "+e.getMessage();
		try {
			statement.getConnection().rollback();
		} catch (SQLException e1) {
						e1.printStackTrace();
		}
		return message;
	}
	finally{
		try {
			if(statement!=null){
				statement.close();
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
	return "";
  }

/******@author saurabh
 * ****get all icd10 code from master table
 * 
 */
@Override
public HashMap<Integer, String> findMasterICD10DiagnosisMap() {
	HashMap<Integer, String> mdMap = new HashMap<Integer, String>();
	List<MasterDiagnosis> mdList= new ArrayList<MasterDiagnosis>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(MasterDiagnosis.class);
	//criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("formattedDxCode"))));
	mdList=criteria.list();
	System.out.println("findMasterDiagnosisMap::::list.size= "+mdList.size());
	for (MasterDiagnosis m : mdList) {
		mdMap.put(m.getDxId(), m.getFormattedDxCode());
	}
	System.out.println("findMasterDiagnosisMap::::map.size= "+mdMap.size());
	return mdMap;
}

/******@author saurabh
 * ****get all icd9 code from master table
 * 
 */
@Override
public HashMap<Integer, String> findMasterICD9DiagnosisMap() {
	HashMap<Integer, String> mdMap = new HashMap<Integer, String>();
	List<Icd9Diagnosis> mdList= new ArrayList<Icd9Diagnosis>();
	Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(Icd9Diagnosis.class);
	//criteria.setProjection(Projections.projectionList().add(Projections.distinct(Projections.property("formattedDxCode"))));
	mdList=criteria.list();
	System.out.println("findMasterDiagnosisMap::::list.size= "+mdList.size());
	for (Icd9Diagnosis m : mdList) {
		mdMap.put(m.getId(), m.getFormatedCode());
	}
	System.out.println("findMasterDiagnosisMap::::map.size= "+mdMap.size());
	return mdMap;
}


/*@Scheduled(fixedDelay = 5000)
//@Scheduled(fixedRate = 5000)
public void demoServiceMethod()
{
    System.out.println("Method executed at every 5 seconds. Current time is :: "+ new Date());
}*/

}

			
			
             

