package com.clinakos.data.dao.daoImpl;

import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;






import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;

import com.clinakos.data.dao.IValidicRestDAO;
import com.clinakos.data.model.core.PracticeSearchResultSet;
import com.clinakos.data.model.patient.ValidicUsersModel;
import com.mysql.jdbc.CallableStatement;
import com.validic.UserResponse;
import com.validic.ValidicBiometricmeasurement;
import com.validic.ValidicDiabetesMeasurements;
import com.validic.ValidicFitnessActivity;
import com.validic.ValidicNutrition;
import com.validic.ValidicRoutineActivity;
import com.validic.ValidicSleep;
import com.validic.ValidicTobaccoCessation;
import com.validic.ValidicWeight;

public class ValidicRestDAOImpl extends BaseDaoImpl implements IValidicRestDAO {

	
	public void saveValidicUserCredential(UserResponse user) {
		getSessionFactory().getCurrentSession().save(user);
		
	}


	public void saveValidicDibitesMeasurementData(
			List<ValidicDiabetesMeasurements> diabetes) {
		for(ValidicDiabetesMeasurements validicDiabitesMeasurements:diabetes){
			String clinakosUserId = getClinakosIdBasedOnValidicUserId(validicDiabitesMeasurements.getUser_id());
			validicDiabitesMeasurements.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(validicDiabitesMeasurements);
		}
		
	}


	
	public void saveValidicFitnessActivity(List<ValidicFitnessActivity> fitness) {
		for(ValidicFitnessActivity fitnessActivity:fitness){
			String clinakosUserId = getClinakosIdBasedOnValidicUserId(fitnessActivity.getUser_id());
			fitnessActivity.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(fitnessActivity);
		}
		
	}


	
	public void saveValidicBiometricsMeasurementData(
			ArrayList<ValidicBiometricmeasurement> biometrics) {
          for(ValidicBiometricmeasurement biometricmeasurement:biometrics){
        	  String clinakosUserId = getClinakosIdBasedOnValidicUserId(biometricmeasurement.getUser_id());
        	  biometricmeasurement.setClinakosUserId(clinakosUserId);
        	  getSessionFactory().getCurrentSession().save(biometricmeasurement);
          }
		
	}
	
	public void saveValidicWeightDetails(List<ValidicWeight> weight) {
		try {
			for(ValidicWeight weightData:weight){
				String clinakosUserId = getClinakosIdBasedOnValidicUserId(weightData.getUser_id());
				weightData.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(weightData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public void saveValidicRoutineActivity(List<ValidicRoutineActivity> routine) {
		System.out.println("saving data into validicRoutinetbl:::::::::::in ValidiRestDAO:::");
		try {
			for(ValidicRoutineActivity routineData:routine){
				String clinakosUserId = getClinakosIdBasedOnValidicUserId(routineData.getUser_id());
				routineData.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(routineData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public void saveValidicNutritionData(List<ValidicNutrition> nutritions) {
		for(ValidicNutrition nutrition:nutritions){
			String clinakosUserId = getClinakosIdBasedOnValidicUserId(nutrition.getUser_id());
			nutrition.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(nutrition);
			
		}
		
	}


	
	public void saveValidicSleepData(List<ValidicSleep> sleeps) {
		for(ValidicSleep sleep:sleeps){
			String clinakosUserId = getClinakosIdBasedOnValidicUserId(sleep.getUser_id());
			sleep.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(sleep);
		}
		
	}


	
	public void saveTobaccoCessationData(
			List<ValidicTobaccoCessation> tobacco_cessation) {
		for(ValidicTobaccoCessation tobaccoCessation:tobacco_cessation){
			String clinakosUserId = getClinakosIdBasedOnValidicUserId(tobaccoCessation.getUser_id());
			tobaccoCessation.setClinakosUserId(clinakosUserId);
			getSessionFactory().getCurrentSession().save(tobaccoCessation);
		}
		
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClinakosIdBasedOnValidicUserId(String validicUserId){
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserResponse.class);
		                 criteria.add(Restrictions.eq("_id", validicUserId));
		List<UserResponse> validicUserList= criteria.list();
		System.out.println("User List Size "+validicUserList.size());
		UserResponse userResponse=validicUserList.get(0);
		String clinaUserId=userResponse.getUid();
		return clinaUserId ;
		
	}


	
	public List<ValidicWeight> getValidicPatientWeightData(int patientId) {
		
		String patientStrId=Integer.toString(patientId);
		List<ValidicWeight> patientValidicWeightList=new ArrayList<ValidicWeight>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicWeight.class);
		                  criteria.add(Restrictions.eq("clinakosUserId", patientStrId));
		patientValidicWeightList=criteria.list();                  
		return patientValidicWeightList;
	}


	
	public List<ValidicWeight> getValidicPatientWeightData(int patientId,
			Date startDate, Date endDate,String sourceName) {
		System.out.println("Dao Method for filteration .........");
		String patientStrId=Integer.toString(patientId);
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String lowerDate=dateFormat.format(startDate);
		String higherDate=dateFormat.format(endDate);
		System.out.println("lowe date "+lowerDate+"Highr Date "+higherDate);
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicWeight.class);
		                 criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
		                 
		                  criteria.add(Restrictions.eq("clinakosUserId", patientStrId));
		                  criteria.add(Restrictions.eq("source_name", sourceName));
		List<ValidicWeight> validicWeightList=criteria.list();                  
		System.out.println("Size of filter list "+validicWeightList.size());
		return validicWeightList;
	}


	
	public List<String> getValidicWeightSourceNameData(int patientId) {
	    String patientStrId=Integer.toString(patientId);
		String sqlQuery="select distinct(source_name) FROM validic_weight where clinakos_user_id="+patientStrId;
		SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		List<String> weightSourceNameList=query.list();
		return weightSourceNameList;
	}


	
	public List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId) {
		List<ValidicDiabetesMeasurements> validicDiabitesMeasurementList=new ArrayList<ValidicDiabetesMeasurements>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicDiabetesMeasurements.class);
            criteria.add(Restrictions.eq("clinakosUserId", patientId));
            validicDiabitesMeasurementList =criteria.list(); 
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		               
		return validicDiabitesMeasurementList;
	}


	
	public List<String> validicDiabitesMeasurementSourceNameData(
			String patientId) {
		String sqlQuery="select distinct(source_name) FROM validic_diabetes_measurements where clinakos_user_id="+patientId;
		SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		List<String> diabetesMeasurementSourceNameList=query.list();
		return diabetesMeasurementSourceNameList;
	}
	
	public List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId, Date startDate, Date endDate, String sourceName) {
		List<ValidicDiabetesMeasurements> diabitesMeasurementList=new ArrayList<ValidicDiabetesMeasurements>();
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String lowerDate=dateFormat.format(startDate);
		String higherDate=dateFormat.format(endDate);
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicDiabetesMeasurements.class);
            criteria.add(Restrictions.eq("clinakosUserId", patientId));
            criteria.add(Restrictions.eq("source_name", sourceName));
            criteria.add(Restrictions.between("timestamp", lowerDate,higherDate));
            diabitesMeasurementList =criteria.list(); 
            System.out.println("Diabetes Measurement List Size "+diabitesMeasurementList.size());
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		               
		return diabitesMeasurementList;
	}



	public List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId) {
		List<ValidicBiometricmeasurement> validicBiometricDataList=new ArrayList<ValidicBiometricmeasurement>();
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicBiometricmeasurement.class);
		criteria.add(Restrictions.eq("clinakosUserId", patientId));
		validicBiometricDataList=criteria.list();
		return validicBiometricDataList;
	}

 /**
  * Validic BiometricMeasurement Data
  * 
  */
	
	public List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId, Date startDate, Date endDate, String sourceName) {
		List<ValidicBiometricmeasurement> validicBiometricMeasurementDataList=new ArrayList<ValidicBiometricmeasurement>();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String lowerDate=format.format(startDate);
		String higherDate=format.format(endDate);
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicBiometricmeasurement.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
			criteria.add(Restrictions.eq("source_name", sourceName));
			validicBiometricMeasurementDataList=criteria.list();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		
		return validicBiometricMeasurementDataList;
	}



public List<String> getValidicBiometricMeasurementSourceNameData(
		String patientId) {
	String sqlQuery="select distinct(source_name) FROM validic_biometric_measurements where clinakos_user_id="+patientId;
	SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
	List<String> biometricMeasurementSourceNameList=query.list();
	return biometricMeasurementSourceNameList;
}



public List<ValidicNutrition> getValidicPatientNutritionsData(String patientId) {
	List<ValidicNutrition> patientNutritionDataList=new ArrayList<ValidicNutrition>();
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicNutrition.class);
		criteria.add(Restrictions.eq("clinakosUserId", patientId));
		patientNutritionDataList=criteria.list();
		
	} catch (NullPointerException ne) {
		ne.printStackTrace();
		
	}
  	
	return patientNutritionDataList;
}

 /**
  * Get After Filteration Validic Nutrition Data 
  */

public List<ValidicNutrition> getValidicPatientNutritionsData(String patientId,
		Date startDate, Date endDate, String source_name) {
	List<ValidicNutrition> validicNutritions=new ArrayList<ValidicNutrition>();
	try {
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String lowerDate=format.format(startDate);
		String higherDate=format.format(endDate);
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicNutrition.class);
		criteria.add(Restrictions.eq("clinakosUserId", patientId));
		criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
		criteria.add(Restrictions.eq("source_name", source_name));
		validicNutritions=criteria.list();
		
	} catch (NullPointerException ne) {
		ne.printStackTrace();
	}
	return validicNutritions;
}



public List<String> getValidicNutritionSourceNameData(String patientId) {
	String sqlQuery="select distinct(source_name) FROM validic_nutrition where clinakos_user_id="+patientId;
	SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
	List<String> nutritionSourceNameList=query.list();
	return nutritionSourceNameList;
}


/**
 * Get Validic Fitness Activity Data 
 */
public List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
		String patientId) {
	List<ValidicFitnessActivity> validicFitnessActivities=new ArrayList<ValidicFitnessActivity>();
	try {
		 Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicFitnessActivity.class);
		 criteria.add(Restrictions.eq("clinakosUserId", patientId));
		 validicFitnessActivities=criteria.list();
	} catch (NullPointerException ne) {
		ne.printStackTrace();
	}
	
	
	return validicFitnessActivities;
}



public List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
		String patientId, Date startDate, Date endDate, String source_name) {
	List<ValidicFitnessActivity> validicFitnessActivities=new ArrayList<ValidicFitnessActivity>();
	DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	String lowerDate=format.format(startDate);
	String higherDate=format.format(endDate);
	try {
		Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicFitnessActivity.class);
		criteria.add(Restrictions.eq("clinakosUserId", patientId));
		criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
		criteria.add(Restrictions.eq("source_name", source_name));
		validicFitnessActivities=criteria.list();
	} catch (NullPointerException ne) {
		ne.printStackTrace();
	}
	
	
	return validicFitnessActivities;
}



    public List<String> getValidicFitnessActivitySourceName(String patientId) {
	    String sqlQuery="select distinct(source_name) FROM validic_fitness_activity where clinakos_user_id="+patientId;
	    SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
	    List<String> fitnessActivitySourceNameList=query.list();
		return fitnessActivitySourceNameList;
   }


	
	public List<ValidicSleep> validicPatientSleepData(String patientId) {
		List<ValidicSleep> validicSleeps=new ArrayList<ValidicSleep>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicSleep.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			validicSleeps=criteria.list();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		
		
		return validicSleeps;
	}



	public List<String> getValidicSleepSourceNameData(String patientId) {
		 String sqlQuery="select distinct(source_name) FROM validic_sleep where clinakos_user_id="+patientId;
		    SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		    List<String> sleepSourceNameList=query.list();
		return sleepSourceNameList;
	}


	public List<ValidicSleep> validicPatientSleepData(String patientId,
			Date startDate, Date endDate,String sourceName) {
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String lowerDate=format.format(startDate);
		String higherDate=format.format(endDate);
		List<ValidicSleep> validicSleeps=new ArrayList<ValidicSleep>();
		System.out.println("Sleep Filter method started now "+higherDate+"Lower Date"+lowerDate);
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicSleep.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
			criteria.add(Restrictions.eq("source_name", sourceName));
			validicSleeps=criteria.list();
			System.out.println("Size of filter List"+validicSleeps.size());
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		return validicSleeps;
	}


	/**
	 * Validic Patient Tobacco Cessation Data 
	 */
	public List<ValidicTobaccoCessation> getValidicPatientTobaccoData(
			String patientId) {
		List<ValidicTobaccoCessation> tobaccoCessationList=new ArrayList<ValidicTobaccoCessation>();
		try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicTobaccoCessation.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			tobaccoCessationList=criteria.list();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		
		return tobaccoCessationList;
	}



	public List<ValidicTobaccoCessation> getValidicPatientTobaccoData(
			String patientId, Date startDate, Date endDate, String source_name) {
		    List<ValidicTobaccoCessation> tobaccoCessations=new ArrayList<ValidicTobaccoCessation>();
		
		 try {
			 DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			 String lowerDate=dateFormat.format(startDate);
			 String higherDate=dateFormat.format(endDate);
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicTobaccoCessation.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			criteria.add(Restrictions.eq("source_name", source_name));
			criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
			tobaccoCessations=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tobaccoCessations;
	}


	
	public List<String> getTobaccoCessationSourceNameData(String patientId) {
		    String sqlQuery="select distinct(source_name) FROM validic_tobacco_cessation where clinakos_user_id="+patientId;
		    SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		    List<String> tobaccoSourceNameList=query.list();
		return tobaccoSourceNameList;
	}



	public List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId) {
		List<ValidicRoutineActivity> routineActivities=new ArrayList<ValidicRoutineActivity>();
	    try {
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicRoutineActivity.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			routineActivities=criteria.list();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		return routineActivities;
	}


	/**
	 * Get 
	 */
	public List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId, Date startDate, Date endDate, String source_name) {
		List<ValidicRoutineActivity> validicRoutineActivities=new ArrayList<ValidicRoutineActivity>();
		try {
			DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			String lowerDate=format.format(startDate);
			String higherDate=format.format(endDate);
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicRoutineActivity.class);
			criteria.add(Restrictions.eq("clinakosUserId", patientId));
			criteria.add(Restrictions.eq("source_name", source_name));
			criteria.add(Restrictions.between("timestamp", lowerDate, higherDate));
			validicRoutineActivities=criteria.list();
		} catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		return validicRoutineActivities;
	}


	
	public List<String> getValidicRoutineActivitySourceNameData(String patientId) {
		    String sqlQuery="select distinct(source_name) FROM validic_routine_activity where clinakos_user_id="+patientId;
		    SQLQuery query=getSessionFactory().getCurrentSession().createSQLQuery(sqlQuery);
		    List<String> routineActivitySourceNameList=query.list();
		return routineActivitySourceNameList;
	}


	
	public void callTestProc(int i) throws HibernateException, SQLException {
		System.out.println("Proc Rum started ::::::::::::");
		CallableStatement statement=(CallableStatement) getSessionFactory().getCurrentSession()
				.connection().prepareCall("{call proc_for_testing_java_app("+4899+")}");
		System.out.println("proc run in middle e::::::::::");
		ResultSet resultSet=statement.executeQuery();
		
		System.out.println("Proc run completed ::::::::::::::;");
		
	}


	@Override
	public UserResponse getValidicUser(int patientId, int providerId) {
		UserResponse user=null;
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(UserResponse.class);
			criteria.add(Restrictions.eq("patientId", patientId));
			criteria.add(Restrictions.eq("providerId", providerId));
			user=(UserResponse) criteria.uniqueResult();
		}catch(HibernateException hfe){
			hfe.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public List<ValidicFitnessActivity> getClinakosValidicFitnessData(
			int patientId, UserResponse userObj) {
		List<ValidicFitnessActivity>clinakosFitnessData=new ArrayList<ValidicFitnessActivity>();
		try{
			Criteria criteria=getSessionFactory().getCurrentSession().createCriteria(ValidicFitnessActivity.class);
			criteria.add(Restrictions.eq("clinakosUserId", String.valueOf(patientId)));
			clinakosFitnessData=criteria.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		return clinakosFitnessData;
	}


	@Override
	public void saveToClinakosActivityFitness(
			List<ValidicFitnessActivity> clinakosValidicFitnessData,int patientId, int providerId) {
		try{
			for(ValidicFitnessActivity validicFitnessActivity:clinakosValidicFitnessData){
				validicFitnessActivity.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(validicFitnessActivity);
			}
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}


	@Override
	public void saveToClinakosActivityDiabetes(
			List<ValidicDiabetesMeasurements> latestDiabetesData,
			int patientId, int providerId) {
		try{
			for(ValidicDiabetesMeasurements vDiabetesData:latestDiabetesData){
				vDiabetesData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vDiabetesData);
			}
			
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosBiometricActivity(
			List<ValidicBiometricmeasurement> userBiometricMeasurements2,
			int patientId, int providerId) {
		try{
			for(ValidicBiometricmeasurement vBiometricData:userBiometricMeasurements2){
				vBiometricData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vBiometricData);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosRoutineActivity(
			List<ValidicRoutineActivity> latestRoutineData, int patientId,
			int providerId) {
		try{
			for(ValidicRoutineActivity vRoutineData:latestRoutineData){
				vRoutineData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vRoutineData);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosNutritionActivity(
			List<ValidicNutrition> userNutritions2, int patientId,
			int providerId) {
		try{
			for(ValidicNutrition vNutritionData:userNutritions2){
				vNutritionData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vNutritionData);
			}
			
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosSleepActivity(
			List<ValidicSleep> userSleepActivities2, int patientId,
			int providerId) {
		try{
			for(ValidicSleep vSleepData:userSleepActivities2){
				vSleepData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vSleepData);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosWeightActivity(
			List<ValidicWeight> userWeightActivities2, int patientId,
			int providerId) {
		try{
			for(ValidicWeight vWeightData:userWeightActivities2){
				vWeightData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vWeightData);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public void saveToClinakosTobaccoCessationActivity(
			List<ValidicTobaccoCessation> userTobaccoCessationActivities2,
			int patientId, int providerId) {
		try{
			for(ValidicTobaccoCessation vTobaccoData:userTobaccoCessationActivities2){
				vTobaccoData.setClinakosUserId(String.valueOf(patientId));
				getSessionFactory().getCurrentSession().save(vTobaccoData);
			}
		}catch(HibernateException he){
			he.printStackTrace();
		}
		
	}


	@Override
	public List<ValidicUsersModel> get_vFitnessUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vFitnessUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_fitness_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vFitnessUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vFitnessUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vNutritionUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vNutritionUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_nutrition_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vNutritionUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vNutritionUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vRoutineUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vRoutineUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_routine_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vRoutineUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vRoutineUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vDiabetesUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vDiabetesUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_diabetes_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vDiabetesUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vDiabetesUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vBiometricUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vBiometricUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_biometric_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vBiometricUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vBiometricUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vSleepUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vSleepUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_sleep_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vSleepUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vSleepUsers;
	}
	
	@Override
	public List<ValidicUsersModel> get_vWeightUsers(int providerId,
			String decryptionkey) {
		List<ValidicUsersModel>vWeightUsers = new ArrayList<ValidicUsersModel>();
		try{
			CallableStatement statement = (CallableStatement) getSessionFactory().getCurrentSession().connection().prepareCall("{call validic_weight_users("+providerId+" , "+"'"+DecryptionKey+"')}");
			ResultSet resultSet = statement.executeQuery();
			vWeightUsers = getValidicUsersColumnData(resultSet);
		}catch(SQLException s){
			s.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return vWeightUsers;
	}


	private List<ValidicUsersModel> getValidicUsersColumnData(
			ResultSet resultSet) throws SQLException{
		List<ValidicUsersModel>vUsers = new ArrayList<ValidicUsersModel>();
		while(resultSet.next()){
			ValidicUsersModel resultObj = new ValidicUsersModel();
			resultObj.setClinakosUserId(resultSet.getInt("patient_id"));
			resultObj.setFirstName(resultSet.getString("first_name"));
			resultObj.setLastName(resultSet.getString("last_name"));
			resultObj.setUserAccessToken(resultSet.getString("user_access_token"));
			resultObj.setAppSourceName(resultSet.getString("source_name"));
			resultObj.setValidicUserId(resultSet.getString("validic_user_id"));
			vUsers.add(resultObj);
		}
		return vUsers;
	}


}
