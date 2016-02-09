package com.clinakos.data.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clinakos.data.dao.IPatientDAO;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.EmployerDetails;
import com.clinakos.data.model.patient.GenericMedActionPlan;
import com.clinakos.data.model.patient.Icd9Diagnosis;
import com.clinakos.data.model.patient.MasterDiagnosis;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.oro.text.regex.StringSubstitution;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.common.util.DateUtil;
import com.clinakos.data.dao.daoImpl.BaseDaoImpl;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.mysql.jdbc.CallableStatement;
import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;
import static com.clinakos.common.util.ClinakosConstant.ExternalDoctorDiagnosis;
public class PatientDAOImpl extends BaseDaoImpl implements IPatientDAO {
	
	public static final Logger logger= Logger.getLogger("PatientDAOImpl.class");

	/*// Method to fetch Care Team list for Particular Patient, Created  and modified by Anand S Jha 
	public List<DoctorDetail> fetchCareTeamList(int patientId) {
		// TODO Auto-generated method stub		
			List<DoctorDetail> doctorDetailList = new ArrayList<DoctorDetail>();
			List<DoctorDetail> docDetailList = new ArrayList<DoctorDetail>();
			List<UserLoginDetail> docPDetailList = new ArrayList<UserLoginDetail>();
			try {

				List<CareTeam> careTeamList = new ArrayList<CareTeam>();
				Criteria criteria4DoctorId = getSessionFactory()
						.getCurrentSession().createCriteria(CareTeam.class);
				criteria4DoctorId.add(Restrictions.eq("patientId", patientId));
				careTeamList = criteria4DoctorId.list();
				for (CareTeam ctlist : careTeamList) {				
					Criteria criteria4CareTeam = getSessionFactory()
							.getCurrentSession().createCriteria(DoctorDetail.class);
					criteria4CareTeam.add(Restrictions.eq("userId",
							ctlist.getDoctorId()));
					doctorDetailList = criteria4CareTeam.list();
					System.out.println("Fetched list:::"+doctorDetailList.size());
					for(DoctorDetail docD:doctorDetailList)
					{					
						System.out.println("trying to fetch personal details of "+ docD.getfName()+docD.getUserId());
						Criteria criteria4PersonalDetail=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class,"user");
						criteria4PersonalDetail.add(Restrictions.eq("userId", docD.getUserId()));
						docPDetailList=criteria4PersonalDetail.list();
						System.out.println("Fetching the personal list of "+docPDetailList.size());
					   for(UserLoginDetail docPData:docPDetailList)
					    {
						   docD.setfName(docPData.getFirstName());
						   docD.setlName(docPData.getLastName());
						   docD.setPhoneNumber(docPData.getPhoneNumber());
						   docD.setDoorNo(docPData.getDoorNo());
						   docD.setStreet(docPData.getStreet());
						   docD.setCity(docPData.getCity());
						   docD.setState(docPData.getState());
						   docD.setCountry(docPData.getCountry());
						   docD.setPincode(docPData.getPincode());
						   docD.setEmailId(docPData.getEmail());	
						   System.out.println("Fetched Doctor::::"+docD.getfName()+docD.getEmailId());
					     }
					}
					docDetailList.addAll(doctorDetailList);				
					
				}

			} catch (Exception e) {

				logger.debug(e.toString());
			}
			return docDetailList;
		}
*/

	/*
	 * Getting ICD 10 Type all Diagnoses List (non-Javadoc)
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#showPatientICD_10Diagnosis()
	 */
	public List<PatientDiagnosesDetails> showPatientICD_10Diagnosis() {
		// TODO Auto-generated method stub
		List<PatientDiagnosesDetails> patICD_10DiagonsisList = new ArrayList<PatientDiagnosesDetails>();
		List<PatientDiagnosesDetails> patICD_10DiagonsisList1 = new ArrayList<PatientDiagnosesDetails>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(PatientDiagnosesDetails.class);
			criteria.add(Restrictions.eq("patientId",
					new ContextUtil().getPatientId()));
			//criteria.add(Restrictions.eq("icdCodeType", "ICD10"));
			criteria.add(Expression.or((Restrictions.eq("icdCodeType", "ICD10")),(Restrictions.eq("icdCodeType", "ICD 10")))); //For displaying in patientDiagnoses page in patient portal by venu
			patICD_10DiagonsisList = criteria.list();
			
			for(PatientDiagnosesDetails diagnosesDetails:patICD_10DiagonsisList){
				PatientDiagnosesDetails patientDiagnosesDetails=new PatientDiagnosesDetails();
				if (diagnosesDetails.getDoctorId()==0) { 
					patientDiagnosesDetails.setDocFirstName(ExternalDoctorDiagnosis+"("+diagnosesDetails.getExternalDoctorUpin()+")");
				}
				else{
					UserLoginDetail loginDetail=findDoctorDetail(diagnosesDetails.getDoctorId());
					patientDiagnosesDetails.setDocFirstName(loginDetail.getFirstName());
				    patientDiagnosesDetails.setDocmiddleName(loginDetail.getMiddleName());
				    patientDiagnosesDetails.setDocLastName(loginDetail.getLastName());
				}
			    patientDiagnosesDetails.setCode(diagnosesDetails.getCode());
			    patientDiagnosesDetails.setCodeDescription(diagnosesDetails.getCodeDescription());
			    patientDiagnosesDetails.setDate(diagnosesDetails.getDate());
			    patientDiagnosesDetails.setIcdCodeType(diagnosesDetails.getIcdCodeType());
			    patientDiagnosesDetails.setIcdId(diagnosesDetails.getIcdId());
			    patientDiagnosesDetails.setDoctorId(diagnosesDetails.getDoctorId());
			    patICD_10DiagonsisList1.add(patientDiagnosesDetails);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patICD_10DiagonsisList1;
	}

	/*
	 * Feth value of Patient ICD code 9 Diagnosis (non-Javadoc)
	 * 
	 * @see com.clinakos.doctor.dao.IDoctorDao#showPatientICD_9CodeDiagnosis()
	 */
	public List<PatientDiagnosesDetails> showPatientICD_9CodeDiagnosis() {
		logger.info(":::::::::::::::showPatientICD_9CodeDiagnosis");
		List<PatientDiagnosesDetails> patientICD_9DiagonosisList = new ArrayList<PatientDiagnosesDetails>();
		List<PatientDiagnosesDetails> patientICD_9DiagonosisList1 = new ArrayList<PatientDiagnosesDetails>();
		try {
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(PatientDiagnosesDetails.class);
			criteria.add(Restrictions.eq("patientId",
					new ContextUtil().getPatientId()));
			//criteria.add(Restrictions.eq("icdCodeType", "ICD9"));
			criteria.add(Expression.or((Restrictions.eq("icdCodeType", "ICD9")),(Restrictions.eq("icdCodeType", "ICD 9")))); //For displaying in patientDiagnoses page in patient portal by venu
			patientICD_9DiagonosisList = criteria.list();

			System.out.println("patientICD_9DiagonosisList:::::"+patientICD_9DiagonosisList.size());
			
			for(PatientDiagnosesDetails diagnosesDetails:patientICD_9DiagonosisList){
				PatientDiagnosesDetails patientDiagnosesDetails=new PatientDiagnosesDetails();
				if (diagnosesDetails.getDoctorId()==0) { 
					patientDiagnosesDetails.setDocFirstName(ExternalDoctorDiagnosis+"("+diagnosesDetails.getExternalDoctorUpin()+")");
				}
				else{
					UserLoginDetail loginDetail=findDoctorDetail(diagnosesDetails.getDoctorId());
					patientDiagnosesDetails.setDocFirstName(loginDetail.getFirstName());
				    patientDiagnosesDetails.setDocmiddleName(loginDetail.getMiddleName());
				    patientDiagnosesDetails.setDocLastName(loginDetail.getLastName());
				}
			    patientDiagnosesDetails.setCode(diagnosesDetails.getCode());
			    patientDiagnosesDetails.setCodeDescription(diagnosesDetails.getCodeDescription());
			    patientDiagnosesDetails.setDate(diagnosesDetails.getDate());
			    patientDiagnosesDetails.setIcdCodeType(diagnosesDetails.getIcdCodeType());
			    patientDiagnosesDetails.setIcdId(diagnosesDetails.getIcdId());
			    patientDiagnosesDetails.setDoctorId(diagnosesDetails.getDoctorId());
			    patientICD_9DiagonosisList1.add(patientDiagnosesDetails);
				
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patientICD_9DiagonosisList1;
	}

/*
 * ********************************************** METHOD TO FIND PATIENT'S ALLERGY DETAIL
 * @see com.clinakos.data.dao.IPatientDAO#getAllergyList()
 */
	
	
	public List<PatientAllergy> getAllergyList() {
		
		List<PatientAllergy> patientAllergy= new ArrayList<PatientAllergy>();
		try {
			
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(PatientAllergy.class,"allergy");
			criteria.add(Restrictions.eq("allergy.patientId", new ContextUtil().getLoginId())); 
			criteria.setMaxResults(3);
			patientAllergy=criteria.list();
			/*for (PatientAllergy patientAllergy2 : patientAllergy) {
				System.out.println("value in allergy list::::"+patientAllergy2.getAllergy()+"::"+patientAllergy2.getPatientId());
			}*/
			
		} 
		catch (Exception e) {
			logger.error("exception in getAllergyList in patient DAO????????", e);
			e.printStackTrace();
		}
		
		return patientAllergy;
	}

/*
 * find clinic master detail list 
 * @author:Gopal Krishna jha..
 * (non-Javadoc)
 * @see com.clinakos.data.dao.IPatientDAO#findClinicList()
 */
	public List<ClinicMaster> findClinicList() {
		return getSessionFactory().getCurrentSession().createCriteria(ClinicMaster.class,"allergy").list();
	}


	/*
	 * find care team list of particular patient..
	 * @author: Gopal Krishna jha
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientDAO#findCareTeamDetailList(int)
	 */
	public List<DoctorDetail> findCareTeamDetailList(int patientId) throws HibernateException, SQLException,NullPointerException {
		logger.info("findCareTeamDetailList in patientDao impl::::");
		 List<DoctorDetail> careTeamDetailList=new ArrayList<DoctorDetail>();
		 //List<CareTeam>careTeamList=new ArrayList<CareTeam>();
		/* List<Integer> doctorIdList=new ArrayList<Integer>();
		 Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.setProjection(Projections.distinct(Projections.projectionList().
					add(Projections.property("doctorId")) ));
			
			
			doctorIdList=criteria.list();
			System.out.println("::::::::::::::&&&&"+doctorIdList);
			if(!doctorIdList.isEmpty())
			{
				for(Integer docId:doctorIdList){
					if(!(docId==0)){
						careTeamDetailList=getSessionFactory().getCurrentSession().createCriteria(DoctorDetail.class).add(Restrictions.in("userId", doctorIdList)).list();
						List<UserLoginDetail>userDetail=new ArrayList<UserLoginDetail>();
						userDetail=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class).add(Restrictions.in("userId", doctorIdList)).list();
						for(DoctorDetail doctorDetail:careTeamDetailList)
						{
							//doctorDetail.set
							List<String>doctorDetailIst= findUserDetailNameAddrressAndMore(doctorDetail.getUserId(),userDetail);
							doctorDetail.setfName(doctorDetailIst.get(0));
							doctorDetail.setlName(doctorDetailIst.get(1));
							doctorDetail.setDoorNo(doctorDetailIst.get(2));
							doctorDetail.setStreet(doctorDetailIst.get(3));
							doctorDetail.setCity(doctorDetailIst.get(4));
							doctorDetail.setCountry(doctorDetailIst.get(6));
							doctorDetail.setPincode(doctorDetailIst.get(7));
							doctorDetail.setPhoneNumber(doctorDetailIst.get(8));
							doctorDetail.setEmailId(doctorDetailIst.get(9));
							//doctorDetail.setfName(doctorDetailIst.get(0));
							//doctorDetail.setfName(doctorDetailIst.get(0));
							
						}
					}
					else if (docId==0) {
						System.out.println("O value Doctory Name ");
						
						List<CareTeam> integratedCareTeamList=new ArrayList<CareTeam>();
						Criteria criteria2=getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
						criteria2.add(Restrictions.eq("doctorId", 0));
						criteria2.add(Restrictions.eq("patientId", patientId));
						ProjectionList projectionList=Projections.projectionList();
						projectionList.add(Projections.distinct(Projections.projectionList().
								add( Projections.property("doctorFirstName"),"doctorFirstName")));
						projectionList.add(Projections.property("doctorFirstName"),"doctorFirstName");
						projectionList.add(Projections.property("doctorLastName"),"doctorLastName");
						criteria2.setProjection(projectionList);
					
								
						
						criteria2.setResultTransformer(Transformers.aliasToBean(CareTeam.class));
						integratedCareTeamList=criteria2.list();
						for(CareTeam careTeam:integratedCareTeamList){
						  System.out.println("careTeam.getDoctorFirstName()"+careTeam.getDoctorFirstName()+careTeam.getDoctorLastName());
						  DoctorDetail doctorDetail=new DoctorDetail();
							  doctorDetail.setfName(careTeam.getDoctorFirstName());
							  doctorDetail.setlName(careTeam.getDoctorLastName());
							  careTeamDetailList.add(doctorDetail);
						 
						}
						
						//careTeamDetailList.addAll(integrationCareTeamList);
					}
				}
				
			}*/
		  CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call proc_get_care_team_detail("+patientId+",'"+DecryptionKey+"')}");
		  ResultSet resultSet=statement.executeQuery();
		  while(resultSet.next()){
			  DoctorDetail doctorDetail=new DoctorDetail();
			  String firstNameforconvert=resultSet.getString("first_name");
			  String lastNameforconvert=resultSet.getString("last_name");
			  String doorNo=resultSet.getString("door_no");
			  String street=resultSet.getString("street");
			  String city=resultSet.getString("city");
			  String state=resultSet.getString("state");
			  String phoneNumber=resultSet.getString("phone_number");
			  String email=resultSet.getString("email");
			  String country=resultSet.getString("country");
			  String specialty=resultSet.getString("specialty");
			  String degree=resultSet.getString("degree");
			  String PinCode=resultSet.getString("pin_code");
			  String npi=resultSet.getString("npi");
			  String firstName = "N/A",lastName = "N/A";
			  int id=0;
			  
			  
			  if((!firstNameforconvert.isEmpty()) && StringUtils.isNotBlank(firstNameforconvert))
			  {
				  logger.info("hello inside if condition for first name");
			  char firstcharcter=firstNameforconvert.charAt(0);
				char first=Character.toUpperCase(firstcharcter);
				firstName=first+firstNameforconvert.substring(1).toLowerCase();
			  }
			  
			  if((!lastNameforconvert.isEmpty()) && StringUtils.isNotBlank(lastNameforconvert))
			  {
				  logger.info("hello inside if condition for last name");
					char secondcharcter=lastNameforconvert.charAt(0);
					char second=Character.toUpperCase(secondcharcter);
					lastName=second+lastNameforconvert.substring(1).toLowerCase();
				  
			  }
			  doctorDetail.setId(id);
			  doctorDetail.setfName(firstName);
			  doctorDetail.setlName(lastName);
			  //doctorDetail.setDoorNo(doorNo);
			  //doctorDetail.setStreet(street);
			  //doctorDetail.setCity(city);
			  //doctorDetail.setState(state);
			  doctorDetail.setPhoneNumber(phoneNumber);
			  doctorDetail.setEmailId(email);
			  //doctorDetail.setCountry(country);
			  doctorDetail.setSpeciality(specialty);
			  doctorDetail.setDegree(degree);
			  //doctorDetail.setPincode(PinCode);
			  doctorDetail.setClinakosUser(true);;
			  doctorDetail.setNpi(npi);
			  if (StringUtils.isNotBlank(doorNo)) {
				  doctorDetail.setDoorNo(doorNo+", ");
			   }
			  else{
				  doctorDetail.setDoorNo("");
			  }
			  if (StringUtils.isNotBlank(street)) {
				  doctorDetail.setStreet(street+", ");
			    }
			  else{
				  doctorDetail.setStreet("");
			  }
			  if (StringUtils.isNotBlank(city)) {
				  doctorDetail.setCity(city+", ");
			    }
			  else{
				  doctorDetail.setCity("");
			  }
			  if (StringUtils.isNotBlank(state)) {
				  doctorDetail.setState(state+", ");
			    }
			  else{
				  doctorDetail.setState("");
			  }
			  if (StringUtils.isNotBlank(country)) {
				  doctorDetail.setCountry(country+", ");
			    }
			  else{
				  doctorDetail.setCountry("");
			  }
			  if (StringUtils.isNotBlank(PinCode)) {
				  doctorDetail.setPincode(PinCode);
			    }
			  else{
				  doctorDetail.setPincode("");
			  }
			  doctorDetail.setAddress(doctorDetail.getDoorNo()+doctorDetail.getStreet()+doctorDetail.getCity()+doctorDetail.getState()+doctorDetail.getCountry()+doctorDetail.getPincode());
			  if (StringUtils.isBlank(doctorDetail.getAddress())) {
				doctorDetail.setAddress("");
			}
			  careTeamDetailList.add(doctorDetail);
			  
		  }
		  List<CareTeam> integratedCareTeamList=new ArrayList<CareTeam>();
		  Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(CareTeam.class);
		  criteria.add(Restrictions.eq("doctorId", 0));
			criteria.add(Restrictions.eq("patientId", patientId));
			integratedCareTeamList=criteria.list();
			
			for(CareTeam careTeam:integratedCareTeamList){
				DoctorDetail doctorDetail=new DoctorDetail();
				if((!(careTeam.getDoctorFirstName()==null)) && (StringUtils.isNotBlank(careTeam.getDoctorFirstName())) ){
					String docFirstName=Character.toUpperCase(careTeam.getDoctorFirstName().charAt(0))+careTeam.getDoctorFirstName().substring(1).toLowerCase();
					doctorDetail.setfName(docFirstName);
				}
				if((!(careTeam.getDoctorLastName()==null)  && (StringUtils.isNotBlank(careTeam.getDoctorLastName())))){
					String docLastName=Character.toUpperCase(careTeam.getDoctorLastName().charAt(0))+careTeam.getDoctorLastName().substring(1).toLowerCase();
					doctorDetail.setlName(docLastName);
				}
				doctorDetail.setId(careTeam.getId());
				doctorDetail.setClinakosUser(careTeam.isClinakosUser());
				doctorDetail.setNpi(careTeam.getNpi());
				doctorDetail.setAddress(careTeam.getAddress());
				doctorDetail.setEmailId(careTeam.getEmail());
				doctorDetail.setPhoneNumber(careTeam.getPhoneNumber());
				doctorDetail.setSpeciality(careTeam.getSpecialty());
				doctorDetail.setDegree(careTeam.getDegree());
				if (StringUtils.isBlank(doctorDetail.getAddress())) {
					doctorDetail.setAddress("");
				   }
				careTeamDetailList.add(doctorDetail);
			}
			System.out.println("careTeamDetailList.size()"+careTeamDetailList.size());
		return careTeamDetailList;
	}


	private List<String> findUserDetailNameAddrressAndMore(int userId, List<UserLoginDetail> userDetailList) {
		List<String>detailListOfDoctor=new ArrayList<String>();
		for(UserLoginDetail detail:userDetailList)
		{
			if(detail.getUserId()==userId)
			{
				detailListOfDoctor.add(detail.getFirstName());
				detailListOfDoctor.add(detail.getLastName());
				detailListOfDoctor.add(detail.getDoorNo());
				detailListOfDoctor.add(detail.getStreet());
				detailListOfDoctor.add(detail.getCity());
				detailListOfDoctor.add(detail.getState());
				//detailListOfDoctor.add(detail.getCity());
				detailListOfDoctor.add(detail.getCountry());
				detailListOfDoctor.add(detail.getPincode());
				detailListOfDoctor.add(detail.getPhoneNumber());
				detailListOfDoctor.add(detail.getEmail());
			}
		}
		return detailListOfDoctor;
	}

	/**
	 * Get All the Patient Diagnosis Detail Based on Patient Id 
	 * @param Patient Id
	 * @return List Patient Diagnosis Detail 
	 * @see com.clinakos.data.dao.IPatientDAO#
	 */
	
	public List<PatientDiagnosesDetails> findPatientDiognosisDetails(int patientId) {
		logger.info("getPatientICDDiognosisListDetails:::::::");
		List<PatientDiagnosesDetails> patDiagnosisList = new ArrayList<PatientDiagnosesDetails>();
		List<PatientDiagnosesDetails> diagnoList=new ArrayList<PatientDiagnosesDetails>();
		try {
			
			Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(PatientDiagnosesDetails.class);
			criteria.add(Restrictions.eq("patientId",patientId));
			criteria.addOrder(Order.desc("id"));
			diagnoList=criteria.list();
			for(PatientDiagnosesDetails diagnosesDetails:diagnoList){
				PatientDiagnosesDetails patientDiagnosesDetails=new PatientDiagnosesDetails();
				if (diagnosesDetails.getDoctorId()==0) { 
					patientDiagnosesDetails.setDocFirstName(ExternalDoctorDiagnosis+"("+diagnosesDetails.getExternalDoctorUpin()+")");
				}
				else{
					UserLoginDetail loginDetail=findDoctorDetail(diagnosesDetails.getDoctorId());
					patientDiagnosesDetails.setDocFirstName(loginDetail.getFirstName());
				    patientDiagnosesDetails.setDocmiddleName(loginDetail.getMiddleName());
				    patientDiagnosesDetails.setDocLastName(loginDetail.getLastName());
				}
			    patientDiagnosesDetails.setCode(diagnosesDetails.getCode());
			    patientDiagnosesDetails.setCodeDescription(diagnosesDetails.getCodeDescription());
			    patientDiagnosesDetails.setDate(diagnosesDetails.getDate());
			    patientDiagnosesDetails.setIcdCodeType(diagnosesDetails.getIcdCodeType());
			    patientDiagnosesDetails.setIcdId(diagnosesDetails.getIcdId());
			    patientDiagnosesDetails.setId(diagnosesDetails.getId());
			    patientDiagnosesDetails.setDoctorId(diagnosesDetails.getDoctorId());
			    patDiagnosisList.add(patientDiagnosesDetails);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return patDiagnosisList;
	}

	/**
	 * @param diagnosesDetails
	 * @param selectIntervention
	 * @see com.clinakos.data.dao.IPatientDAO#findPatientDiognosisDetails1(String, int)
	 * @return Patient Diagnosis List 
	 */
	public List<MasterDiagnosis> findPatientDiognosisDetails1(
			String diagnosesDetails,int selectIntervention) {
		logger.info("inside findPatientDiognosisDetails1 in patientDAOimpl::::::"+selectIntervention);
		diagnosesDetails=diagnosesDetails+"%";
		
		List<MasterDiagnosis>dignosisdetailsList=new ArrayList<MasterDiagnosis>();
		try {
			
		Criteria criteria = getSessionFactory().getCurrentSession()
				.createCriteria(MasterDiagnosis.class);
		if (selectIntervention==1) { 
			logger.info("inside findPatientDiognosisDetails1 in if condition::::::"+selectIntervention);
/*			criteria.add(Restrictions.disjunction()
			.add(Restrictions.like("shortDesc",diagnosesDetails,MatchMode.ANYWHERE))
			.add(Restrictions.like("longDesc",diagnosesDetails,MatchMode.ANYWHERE)));*/
			criteria.add(Restrictions.disjunction()
					.add(Restrictions.like("shortDesc",diagnosesDetails+"%"))
					.add(Restrictions.like("longDesc",diagnosesDetails+"%")));
		}
		    
		else if (selectIntervention==2) {
			logger.info("inside findPatientDiognosisDetails1 in else if condition:::::::"+selectIntervention);
			//criteria.add(Restrictions.like("formattedDxCode",diagnosesDetails,MatchMode.ANYWHERE));
			criteria.add(Restrictions.like("formattedDxCode",diagnosesDetails+"%"));
		}
		      dignosisdetailsList=criteria.list();
		    
		  	logger.info("size:::::::::::::"+dignosisdetailsList.size());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("EXCEPTION IN findPatientDiognosisDetails1 INSIDE PATIENT DAO:::::", e);
		}
		return  dignosisdetailsList;
	}


/*
 * Method to add Diagnosis
 * @ author: sanket mishra
 * @see com.clinakos.data.dao.IPatientDAO#savePatientDiagnosis(com.clinakos.data.model.patient.PatientDiagnosesDetails)
 */
	public void savePatientDiagnosis(
			PatientDiagnosesDetails patientDiagnosesDetails) {
		logger.info("savePatientDiagnosis method in dao::::::");
		
		try {
			patientDiagnosesDetails.setDate(new DateUtil().getTodayDate());
			patientDiagnosesDetails.setDoctorId(new ContextUtil().getLoginId());
			patientDiagnosesDetails.setCodeDescription(patientDiagnosesDetails.getCodeDescription().trim());
			/*String insert_diagnosis_data="insert into patient_diagnoses_details (icd_id,code_description,user_id,code,date,icd_code_type,doctor_detail_id) values " +
					"("+patientDiagnosesDetails.getIcdId()+",'"+patientDiagnosesDetails.getCodeDescription()+"',"+patientDiagnosesDetails.getPatientId()+",'"+patientDiagnosesDetails.getCode()+"','"+new DateUtil().DateInMySqlPattern(new Date())+"','"+patientDiagnosesDetails.getIcdCodeType()+"',"+new ContextUtil().getLoginId()+")";
			Query sqlQuery1 = getSessionFactory().getCurrentSession().createSQLQuery(insert_diagnosis_data);
		//	sqlQuery1.executeUpdate();
*/			//modified by gopal...
			save(patientDiagnosesDetails);
			
		} 
		catch (Exception e) {
			logger.info("::exception in savePatientDiagnosis:::::::::::::::::::::::");
			e.printStackTrace();
		}
		
	//	save(patientDiagnosesDetails);
		logger.info("value is save in :::::::::::::::::::::::");
	}
		
		
	/*
	 * Delete Diagnosis Detail Data based on Selection 
	 * @param patientDiagnosesDetails Selected Patient Diagnosis detail data  
	 * @author Sanket singh
	 * (non-Javadoc)
	 * @see com.clinakos.data.dao.IPatientMedicineDAO#savePatientAllergy()
	 * 
	 */

	public  void deleteDiagnosisDetails( PatientDiagnosesDetails patientDiagnosesDetails){
		//First delete from generic med action plan
		try{
		
			Query deleteQuery = getSessionFactory().getCurrentSession().createSQLQuery(
				    "delete from generic_med_action_plan "
				    + "where patient_diagonsis_id=:diagnosis_id and patient_id=:patientId");
			deleteQuery.setInteger("patientId", patientDiagnosesDetails.getPatientId());
				deleteQuery.setInteger("diagnosis_id", patientDiagnosesDetails.getId());
				int updated = deleteQuery.executeUpdate();	
		logger.info("updated{}{}{}{}"+updated);
		
		Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientDiagnosesDetails where icdId =:icdId and patientId =:patientId and code=:code");
		query.setInteger("patientId", patientDiagnosesDetails.getPatientId());
		 System.out.println("value deleted::::::::::::::::::::::"+patientDiagnosesDetails.getPatientId());
		query.setString("icdId", patientDiagnosesDetails.getIcdId());

	
		//query.setInteger("Id",patientDiagnosesDetails.getId());
		 System.out.println("value icd::::::::::::::::::::::"+patientDiagnosesDetails.getIcdId());
		query.setString("code",patientDiagnosesDetails.getCode() );
		
		logger.info("code:::::::::::::::"+patientDiagnosesDetails.getCode());
		System.out.println("PatientDiagnosis:::::::::::::");
		 query.executeUpdate();
		 System.out.println("value deleted::::::::::::::::::::::");
		}catch(HibernateException he){
			he.printStackTrace();
		}
	}

/*
 * ****************Method To Fetch icd9 Diagnosis List
 * @see com.clinakos.data.dao.IPatientDAO#getIcd9MasterList()
 * @return ICD 9 Patient Diagnosis  Data List 
 */
	public List<Icd9Diagnosis> getIcd9MasterList(String icdDiscription, int selectIntervention) {
					
	 logger.info("getIcd9MasterList starts in dao impl::::::::::"+icdDiscription+"::::"+selectIntervention);		
	 List<Icd9Diagnosis>icd9DiagnosisList=new ArrayList<Icd9Diagnosis>();	
	 try {
		 
		 Criteria criteria = getSessionFactory().getCurrentSession()
					.createCriteria(Icd9Diagnosis.class);
		
		 if(selectIntervention==1)
		 {
			 logger.info("getIcd9MasterList starts in dao impl:if condition:::::::::"+icdDiscription+"::::"+selectIntervention);	
			 //criteria.add(Restrictions.like("shortDesc",diagnosesDetails,MatchMode.ANYWHERE));
			 /*criteria.add(Restrictions.disjunction()
			     .add(Restrictions.like("shortDescription",icdDiscription,MatchMode.ANYWHERE))	
			     .add(Restrictions.like("longDiscription",icdDiscription,MatchMode.ANYWHERE)));*/
			 criteria.add(Restrictions.disjunction()
				     .add(Restrictions.like("shortDescription",icdDiscription+"%"))	
				     .add(Restrictions.like("longDiscription",icdDiscription+"%")));
		 }
	 else if (selectIntervention==2) {
		 logger.info("getIcd9MasterList starts in dao impl:else if condition:::::::::"+icdDiscription+"::::"+selectIntervention);
		// criteria.add(Restrictions.like("formatedCode",icdDiscription,MatchMode.ANYWHERE));		
		 criteria.add(Restrictions.like("formatedCode",icdDiscription+"%"));	
		 
	} 
		
		icd9DiagnosisList=criteria.list();
			
	
		 
	} catch (Exception e) {
		e.printStackTrace();
		logger.info("EXCEPTION IN getIcd9MasterList INSIDE PATIENT DAO:::::", e);
	}
	
	
	 return icd9DiagnosisList;		

	}
	
/*	>>>>>>>>>>>>>>>>>method for edit the diagnosis>>>>>>>>>>>>>>>>
 * Author Sanket Singh
 * @param  patientDiagnosesDetails
 * 
 * */
	public  void editDiagnosis(PatientDiagnosesDetails patientDiagnosesDetails)
	{
		logger.info("===value in dao======"+patientDiagnosesDetails.getIcdId()+":::::::id::"+patientDiagnosesDetails.getCode());
		String update_Reference=("update PatientDiagnosesDetails set icdId =:icdId"+
				" where code = :code"  );
		Query hql = getSessionFactory().getCurrentSession().createQuery( update_Reference);
		hql.setParameter("icdId",patientDiagnosesDetails.getIcdId());
		hql.setParameter("code", patientDiagnosesDetails.getCode());
		System.out.println("==id in dao==="+patientDiagnosesDetails.getIcdId());
	   //hql.setParameter("result", procedureResult.getResult());
	   logger.info("==update the value in dao==="+patientDiagnosesDetails.getIcdId());
		hql.executeUpdate();
		logger.info("===after updating the value===");
	}

   /**
    * Save Diaganosis from new crop to clinakos system 
    */
	public void diagnosisIntegrationFromNewCropToClinakos(
			Set<PatientDiagnosesDetails> patientDiagnosisSetFromIntegration,
			int patientId) {
		// TODO Auto-generated method stub
		/*Query query =getSessionFactory().getCurrentSession().createQuery("delete PatientDiagnosesDetails where  patientId =:patientId");
		query.setInteger("patientId", patientId);
		int result = query.executeUpdate();
		System.out.println("result in patient dao impl "+result);*/
		Set<PatientDiagnosesDetails> uniqueSet=new HashSet<PatientDiagnosesDetails>();
		Set<PatientDiagnosesDetails> duplicateSet=new HashSet<PatientDiagnosesDetails>();
		for(PatientDiagnosesDetails patDiagDetail:patientDiagnosisSetFromIntegration){
			if(uniqueSet.contains(patDiagDetail)){
				duplicateSet.add(patDiagDetail);
			}
			else {
				uniqueSet.add(patDiagDetail);
			}
			
		}
		for(PatientDiagnosesDetails patDiagnDetails:uniqueSet){
			System.out.println("Patient dao Impl dia code  "+patDiagnDetails.getCode()+"pat Id   "+patDiagnDetails.getPatientId()+"pres date  "+patDiagnDetails.getDate());
			if(!(patDiagnDetails.getIcdId()=="")){
				getSessionFactory().getCurrentSession().save(patDiagnDetails);
			}
			
		}
		
	}
	
	/**
	 * Find Doctor Name For DiagnosisDetail Data
	 */
	public UserLoginDetail findDoctorDetail(int doctorId){
		List<UserLoginDetail> userLoginDetails=new ArrayList<UserLoginDetail>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserLoginDetail.class);
		                  criteria.add(Restrictions.eq("userId", doctorId));
		 userLoginDetails=criteria.list();                 
		 UserLoginDetail detail=userLoginDetails.get(0);                 
		                  
		return detail;
		
	}

	
	public void insuranceDetailIntegrationFromNewCropToClinakos(
			List<UserInsuranceDetails> userInsuranceDetailListFromIntegration,
			int patientId) {
		// TODO Auto-generated method stub
		for(UserInsuranceDetails insuranceDetail:userInsuranceDetailListFromIntegration){
			UserInsuranceDetails userInsuranceDetail=new UserInsuranceDetails();
			userInsuranceDetail.setAlternateFormularyId(insuranceDetail.getAlternateFormularyId());
			userInsuranceDetail.setBenefitLoop(insuranceDetail.getBenefitLoop());
			userInsuranceDetail.setBin(insuranceDetail.getBin());
			userInsuranceDetail.setBinInfo(insuranceDetail.getBinInfo());
			userInsuranceDetail.setCacheExpiryTimestamp(insuranceDetail.getCacheExpiryTimestamp());
			userInsuranceDetail.setCardholderId(insuranceDetail.getCardholderId());
			userInsuranceDetail.setCoverageListId(insuranceDetail.getCoverageListId());
			userInsuranceDetail.setCardHolderName(insuranceDetail.getCardHolderName());
			userInsuranceDetail.setCopayId(insuranceDetail.getCopayId());
			userInsuranceDetail.setFormularyId(insuranceDetail.getFormularyId());
			userInsuranceDetail.setGroupNumber(insuranceDetail.getGroupNumber());
			userInsuranceDetail.setInsurancePlanIdNewCrop(insuranceDetail.getInsurancePlanIdNewCrop());
			userInsuranceDetail.setInsurancePlanName(insuranceDetail.getInsurancePlanName());
			userInsuranceDetail.setLtcBenefit(insuranceDetail.getLtcBenefit());
			userInsuranceDetail.setLtcBenefitDescription(insuranceDetail.getLtcBenefitDescription());
			userInsuranceDetail.setMailOrderBenefit(insuranceDetail.getMailOrderBenefit());
			userInsuranceDetail.setMailOrderBenefitDescription(insuranceDetail.getMailOrderBenefitDescription());
			userInsuranceDetail.setPbm(insuranceDetail.getPbm());
			userInsuranceDetail.setPbmId(insuranceDetail.getPbmId());
			userInsuranceDetail.setPbmPatientId(insuranceDetail.getPbmPatientId());
			userInsuranceDetail.setPersonCode(insuranceDetail.getPersonCode());
			userInsuranceDetail.setPharmacyBenefit(insuranceDetail.getPharmacyBenefit());
			userInsuranceDetail.setPharmacyBenefitDescription(insuranceDetail.getPharmacyBenefitDescription());
			userInsuranceDetail.setSource(insuranceDetail.getSource());
			userInsuranceDetail.setSpecialtyPharmacyBenefit(insuranceDetail.getSpecialtyPharmacyBenefit());
			userInsuranceDetail.setSpecialtyPharmacyBenefitDescription(insuranceDetail.getSpecialtyPharmacyBenefitDescription());
			userInsuranceDetail.setSubscriberDate(insuranceDetail.getSubscriberDate());
			userInsuranceDetail.setUserId(patientId);
			userInsuranceDetail.setPrimaryInsurance("yes");
		//	userInsuranceDetail.setInsuranceId(Integer.parseInt(insuranceDetail.getInsurancePlanIdNewCrop()));
			
			
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
			criteria.add(Restrictions.eq("userId", patientId));
			criteria.add(Restrictions.eq("primaryInsurance", "yes"));
			List<UserInsuranceDetails> insuranceList =criteria.list();
			UserInsuranceDetails detail=insuranceList.get(0);
			System.out.println("Patient Id"+detail.getUserId()+"Detail insurance type "+detail.getPrimaryInsurance());
			
				if(!(detail.getUserId()==patientId) && !(detail.getPrimaryInsurance().equals("yes"))){
					getSessionFactory().getCurrentSession().save(userInsuranceDetail);
				}
				else {
					
					
					Query query=getSessionFactory().getCurrentSession().createQuery("update UserInsuranceDetails  set insurancePlanName=:insurancePlanName , " +
							" source=:source , pbm=:pbm ,pbmId=:pbmId , insurancePlanIdNewCrop=:insurancePlanIdNewCrop , coverageListId=:coverageListId ," +
							" cardHolderName=:cardHolderName ,  groupNumber=:groupNumber ,   groupNumberId=:groupNumberId , formularyId=:formularyId ," +
							" cardholderId=:cardholderId , personCode=:personCode  , alternateFormularyId=:alternateFormularyId ," +
							" bin=:bin , copayId=:copayId , binInfo=:binInfo , pharmacyBenefit=:pharmacyBenefit , mailOrderBenefit=:mailOrderBenefit ," +
							"specialtyPharmacyBenefit=:specialtyPharmacyBenefit , ltcBenefit=:ltcBenefit , pbmPatientId=:pbmPatientId" +
							", subscriberDate=:subscriberDate , pharmacyBenefitDescription=:pharmacyBenefitDescription , mailOrderBenefitDescription=:mailOrderBenefitDescription" +
							", specialtyPharmacyBenefitDescription=:specialtyPharmacyBenefitDescription , ltcBenefitDescription=:ltcBenefitDescription" +
							", cacheExpiryTimestamp=:cacheExpiryTimestamp , benefitLoop=:benefitLoop" +
							" where userId=:patientId and primaryInsurance=:primaryInsurance");
					
					query.setParameter("insurancePlanName", userInsuranceDetail.getInsurancePlanName());
					query.setParameter("source", userInsuranceDetail.getSource());
					query.setParameter("pbm", userInsuranceDetail.getPbm());
					query.setParameter("pbmId", userInsuranceDetail.getPbmId());
					query.setParameter("insurancePlanIdNewCrop", userInsuranceDetail.getInsurancePlanIdNewCrop());
					query.setParameter("coverageListId", userInsuranceDetail.getCoverageListId());
					
					query.setParameter("cardHolderName", userInsuranceDetail.getCardHolderName());
					query.setParameter("groupNumber", userInsuranceDetail.getGroupNumber());
					query.setParameter("groupNumberId", userInsuranceDetail.getGroupNumberId());
					query.setParameter("formularyId", userInsuranceDetail.getFormularyId());
					query.setParameter("cardholderId", userInsuranceDetail.getCardholderId());
					query.setParameter("personCode", userInsuranceDetail.getPersonCode());
					query.setParameter("alternateFormularyId", userInsuranceDetail.getAlternateFormularyId());
					query.setParameter("bin", userInsuranceDetail.getBin());
					query.setParameter("copayId", userInsuranceDetail.getCopayId());
					query.setParameter("binInfo", userInsuranceDetail.getBinInfo());
					query.setParameter("pharmacyBenefit", userInsuranceDetail.getPharmacyBenefit());
					query.setParameter("mailOrderBenefit", userInsuranceDetail.getMailOrderBenefit());
					query.setParameter("specialtyPharmacyBenefit", userInsuranceDetail.getSpecialtyPharmacyBenefit());
					query.setParameter("ltcBenefit", userInsuranceDetail.getLtcBenefit());
					query.setParameter("pbmPatientId", userInsuranceDetail.getPbmPatientId());
					query.setParameter("subscriberDate", userInsuranceDetail.getSubscriberDate());
					query.setParameter("pharmacyBenefitDescription", userInsuranceDetail.getPharmacyBenefitDescription());
					query.setParameter("mailOrderBenefitDescription", userInsuranceDetail.getMailOrderBenefitDescription());
					query.setParameter("specialtyPharmacyBenefitDescription", userInsuranceDetail.getSpecialtyPharmacyBenefitDescription());
					query.setParameter("ltcBenefitDescription", userInsuranceDetail.getLtcBenefitDescription());
					query.setParameter("cacheExpiryTimestamp", userInsuranceDetail.getCacheExpiryTimestamp());
					query.setParameter("benefitLoop", userInsuranceDetail.getBenefitLoop());
					
					query.setParameter("patientId", patientId);
					query.setParameter("primaryInsurance", "yes");
					int result=query.executeUpdate();
					System.out.println(result);
				}
			
			
			
			
			
		}
		
	}

	// To get patient guarantor list for lab integration added by venu
	public List<PatientGuarantor> getPatientGuarantorList(int loggedPatient) {
		logger.info("inside getPatientGuarantorList "+loggedPatient);
		List<PatientGuarantor> patientGuarantorList = new ArrayList<PatientGuarantor>();
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(PatientGuarantor.class);		
		criteria.add(Restrictions.eq("userID", loggedPatient));
		patientGuarantorList=criteria.list();
		System.out.println("patientGuarantorList size in daoimpl "+patientGuarantorList.size());
		return patientGuarantorList;
	}

	
	public List<UserInsuranceDetails> getPatientInsuranceList(int loggedPatient) {
		logger.info("inside getPatientInsuranceList "+loggedPatient);
		List<UserInsuranceDetails> patientInsuranceList = new ArrayList<UserInsuranceDetails>();
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(UserInsuranceDetails.class);
		criteria.add(Restrictions.eq("userId", loggedPatient));
		patientInsuranceList=criteria.list();
		System.out.println("patientInsuranceList size in daoimpl "+patientInsuranceList.size());
		return patientInsuranceList;
	}



/*@saurabh
 * *****added by saurabh for 	
 * delete doctor detail from care Team
*/
	public void deleteDoctorFromCareTeam(int rowIdForDeleteCareTeam) {
		logger.info(":::::deleteDoctorFromCareTeam method called in patientDaoImpl::::::::::"+rowIdForDeleteCareTeam);
		try {
			String delete_doc=("delete CareTeam where id = :rowIdForDeleteCareTeam"  );
			Query hql = getSessionFactory().getCurrentSession().createQuery(delete_doc);
			hql.setParameter("rowIdForDeleteCareTeam",rowIdForDeleteCareTeam); 
			hql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("============EXCEPTION IN deleteDoctorFromCareTeam IN patientDaoImpl==========",e);
		}
		
	}

/*@saurabh
 * *****added by saurabh for 	
 * edit doctor detail in care Team
 */
	public void editDoctorFromCareTeam(CareTeam careTeam) {
		logger.info(":::::editDoctorFromCareTeam method called in patientDaoImpl::::::::::"+careTeam.getId());
		try {
			String delete_doc=("update CareTeam set doctorFirstName= :doctorFirstName,doctorLastName = :doctorLastName,specialty = :specialty,"
					+ "address = :address,phoneNumber = :phoneNumber,"
					+ "degree = :degree,email = :email,npi = :npi where id = :rowIdForEditCareTeam"  );
			Query hql = getSessionFactory().getCurrentSession().createQuery(delete_doc);
			hql.setParameter("rowIdForEditCareTeam",careTeam.getId());
			hql.setParameter("doctorFirstName",careTeam.getDoctorFirstName());
			hql.setParameter("doctorLastName",careTeam.getDoctorLastName());
			hql.setParameter("specialty",careTeam.getSpecialty());
			hql.setParameter("address",careTeam.getAddress());
			hql.setParameter("phoneNumber",careTeam.getPhoneNumber());
			hql.setParameter("degree",careTeam.getDegree());
			hql.setParameter("email",careTeam.getEmail());
			hql.setParameter("npi",careTeam.getNpi());
			hql.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("============EXCEPTION IN editDoctorFromCareTeam IN patientDaoImpl==========",e);
		}
	}

	

/*@author: saurabh
 * ********added by saurabh for
 * searching clinakos existing doctor for add doc in care team
 */
	public List<EmpProviderLocation> SearchDocFromExistingClinakos(
			String valueForSearchingClinakosDoc, int providerId) {
		List<EmpProviderLocation> providerLocationList=new ArrayList<EmpProviderLocation>();
		String roleName="ROLE_DOCTOR";
		String searchInPut=valueForSearchingClinakosDoc+"%";
		logger.info(":::::::::::inside SearchDocFromExistingClinakos method in PatientDAOImpl:::::::::"+providerId+roleName+searchInPut);
	    Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(EmpProviderLocation.class);
	    criteria.add(Restrictions.eq("providerId", providerId)); 
	    criteria.add(Restrictions.eq("roleName", roleName)); 
	    criteria.add(Restrictions.or(Restrictions.like("firstName", searchInPut),
	    							Restrictions.like("lastName", searchInPut)));
	    providerLocationList=criteria.list();
		return providerLocationList;
	}

/*@uthor: saurabh
 * **********to add clinakos doctor in care team
 * 
*/
	public void addClinakosDoctorIncareTeam(CareTeam careTeam) {
		logger.info(":::::::::::inside addClinakosDoctorIncareTeam method in PatientDAOImpl:::::::::");
		try {
			getSessionFactory().getCurrentSession().save(careTeam);
		}
		catch(Exception e){
			e.printStackTrace();
			logger.error(":::::::::::EXCEPTION IN addClinakosDoctorIncareTeam method in PatientDAOImpl:::::::::"+e);
		}
	}
	
}