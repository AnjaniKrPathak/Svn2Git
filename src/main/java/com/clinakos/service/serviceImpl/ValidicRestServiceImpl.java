
package com.clinakos.service.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.clinakos.data.dao.IValidicRestDAO;
import com.clinakos.data.model.patient.ValidicUsersModel;
import com.clinakos.service.IValidicRestService;
import com.validic.UserResponse;
import com.validic.ValidicBiometricmeasurement;
import com.validic.ValidicDiabetesMeasurements;
import com.validic.ValidicFitnessActivity;
import com.validic.ValidicTobaccoCessation;
import com.validic.ValidicNutrition;
import com.validic.ValidicSleep;
import com.validic.ValidicRoutineActivity;
import com.validic.ValidicWeight;

import static com.clinakos.common.util.ClinakosConstant.DecryptionKey;


public class ValidicRestServiceImpl implements IValidicRestService{
	
	public static final Logger logger = Logger.getLogger("ValidicRestServiceImpl.class");
	
	private IValidicRestDAO validicRestDAO;
	
	

	/**
	 * @return the validicRestDAO
	 */
	public IValidicRestDAO getValidicRestDAO() {
		return validicRestDAO;
	}

	/**
	 * @param validicRestDAO the validicRestDAO to set
	 */
	public void setValidicRestDAO(IValidicRestDAO validicRestDAO) {
		this.validicRestDAO = validicRestDAO;
	}

	
	public void saveValidicUserCredential(UserResponse user) {
		validicRestDAO.saveValidicUserCredential(user);
		
		
	}

	
	public void saveValidicDibitesMeasurementData(
			List<ValidicDiabetesMeasurements> diabetes) {
	   validicRestDAO.saveValidicDibitesMeasurementData(diabetes);
	}

	
	public void saveValidicFitnessActivity(List<ValidicFitnessActivity> fitness) {
		validicRestDAO.saveValidicFitnessActivity(fitness);
		
	}

	
	public void saveValidicBiometricsMeasurementData(
			ArrayList<ValidicBiometricmeasurement> biometrics) {
		validicRestDAO.saveValidicBiometricsMeasurementData(biometrics);
		
	}


	public void saveValidicNutritionData(List<ValidicNutrition> nutritions) {
	   validicRestDAO.saveValidicNutritionData(nutritions);
		
	}

	

	public void saveValidicSleepData(List<ValidicSleep> sleeps) {
		validicRestDAO.saveValidicSleepData(sleeps);
		
	}
	

	public void saveValidicWeightDetails(List<ValidicWeight> weight) {
		validicRestDAO.saveValidicWeightDetails(weight);
		
	}


	
	public void saveValidicRoutineActivity(List<ValidicRoutineActivity> routine) {
		validicRestDAO.saveValidicRoutineActivity(routine);
		
	}

	
	public void saveTobaccoCessationData(
			List<ValidicTobaccoCessation> tobacco_cessation) {
		validicRestDAO.saveTobaccoCessationData(tobacco_cessation);
		
	}

	
	public List<ValidicWeight> getValidicPatientWeightData(int patientId) {
		
		return validicRestDAO.getValidicPatientWeightData(patientId);
	}

	
	public List<ValidicWeight> getValidicPatientWeightData(int patientId,
			Date startDate, Date endDate,String sourceName) {
		
		return validicRestDAO.getValidicPatientWeightData(patientId,startDate,endDate,sourceName);
	}

	
	public List<String> getValidicWeightSourceNameData(int patientId) {
		
		return validicRestDAO.getValidicWeightSourceNameData(patientId);
	}

	
	public List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId) {
		
		return validicRestDAO.getValidicPatientDiabetesMeasurementData(patientId);
	}

	
	public List<String> validicDiabitesMeasurementSourceNameData(
			String patientId) {
		
		return validicRestDAO.validicDiabitesMeasurementSourceNameData(patientId);
	}
	
	public List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId,Date startDate,Date endDate,String sourceName) {
		
		return validicRestDAO.getValidicPatientDiabetesMeasurementData(patientId,startDate,endDate,sourceName);
	}

	
	public List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId) {
		
		return validicRestDAO.getValidicBiometricMeasurementData(patientId);
	}

	
	public List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId, Date startDate, Date endDate, String sourceName) {
		
		return validicRestDAO.getValidicBiometricMeasurementData(patientId,startDate,endDate,sourceName);
	}

	
	public List<String> getValidicBiometricMeasurementSourceNameData(
			String patientId) {
		
		return validicRestDAO.getValidicBiometricMeasurementSourceNameData(patientId);
	}

	
	public List<ValidicNutrition> getValidicPatientNutritionsData(
			String patientId) {
		
		return validicRestDAO.getValidicPatientNutritionsData(patientId);
	}


	public List<ValidicNutrition> getValidicPatientNutritionsData(
			String patientId, Date startDate, Date endDate, String source_name) {
		
		return validicRestDAO.getValidicPatientNutritionsData(patientId,startDate,endDate,source_name);
	}

	
	public List<String> getValidicNutritionSourceNameData(String patientId) {
		
		return validicRestDAO.getValidicNutritionSourceNameData(patientId);
	}

	
	public List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
			String patientId) {
		
		return validicRestDAO.getValidicPatientFitnessActivityData(patientId);
	}

	
	public List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
			String patientId, Date startDate, Date endDate, String source_name) {
	    
		return validicRestDAO.getValidicPatientFitnessActivityData(patientId,startDate,endDate,source_name);
	}

	
	public List<String> getValidicFitnessActivitySourceName(String patientId) {
	
		return validicRestDAO.getValidicFitnessActivitySourceName(patientId);
	}


	public List<ValidicSleep> validicPatientSleepData(String patientId) {
		
		return validicRestDAO.validicPatientSleepData(patientId);
	}

	
	public List<String> getValidicSleepSourceNameData(String patientId) {
		
		return validicRestDAO.getValidicSleepSourceNameData(patientId);
	}

	
	public List<ValidicSleep> validicPatientSleepData(String patientId,
			Date startDate, Date endDate,String sourceName) {
		
		return validicRestDAO.validicPatientSleepData(patientId,startDate,endDate,sourceName);
	}

	
	public List<ValidicTobaccoCessation> getValidicPatientTobaccoData(
			String patientId) {
		
		return validicRestDAO.getValidicPatientTobaccoData(patientId);
	}

	
	public List<ValidicTobaccoCessation> getValidicPatientTobaccoData(
			String patientId, Date startDate, Date endDate, String source_name) {
		
		return validicRestDAO.getValidicPatientTobaccoData(patientId,startDate,endDate,source_name);
	}

	
	public List<String> getTobaccoCessationSourceNameData(String patientId) {
		
		return validicRestDAO.getTobaccoCessationSourceNameData(patientId);
	}

	
	public List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId) {
		
		return validicRestDAO.getValidicPatientRoutineActivityData(patientId);
	}

	
	public List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId, Date startDate, Date endDate, String source_name) {
		
		return validicRestDAO.getValidicPatientRoutineActivityData(patientId,startDate,endDate,source_name);
	}

	
	public List<String> getValidicRoutineActivitySourceNameData(String patientId) {
		
		return validicRestDAO.getValidicRoutineActivitySourceNameData(patientId);
	}

	
	public void callTestProc(int i) throws HibernateException, SQLException {
		validicRestDAO.callTestProc(i);
		
	}

	@Override
	public UserResponse getValidicUser(int patientId, int providerId) {
		
		return validicRestDAO.getValidicUser(patientId,providerId);
	}

	@Override
	public List<ValidicFitnessActivity> getClinakosValidicFitnessData(
			int patientId, UserResponse userObj) {
		return validicRestDAO.getClinakosValidicFitnessData(patientId,userObj);
	}


	@Override
	public void saveToClinakosActivityFitness(
			List<ValidicFitnessActivity> clinakosValidicFitnessData,
			int patientId, int providerId) {
		validicRestDAO.saveToClinakosActivityFitness(clinakosValidicFitnessData,patientId, providerId);
		
	}

	@Override
	public void saveToClinakosActivityDiabetes(
			List<ValidicDiabetesMeasurements> latestDiabetesData,
			int patientId, int providerId) {
		validicRestDAO.saveToClinakosActivityDiabetes(latestDiabetesData,patientId,providerId);
	}

	@Override
	public void saveToClinakosBiometricActivity(
			List<ValidicBiometricmeasurement> userBiometricMeasurements2,
			int patientId, int providerId) {
		
		validicRestDAO.saveToClinakosBiometricActivity(userBiometricMeasurements2,patientId,providerId);
	}

	@Override
	public void saveToClinakosRoutineActivity(
			List<ValidicRoutineActivity> latestRoutineData, int patientId,
			int providerId) {
		validicRestDAO.saveToClinakosRoutineActivity(latestRoutineData,patientId,providerId);
		
	}

	@Override
	public void saveToClinakosNutritionActivity(
			List<ValidicNutrition> userNutritions2, int patientId,
			int providerId) {
		validicRestDAO.saveToClinakosNutritionActivity(userNutritions2,patientId,providerId);
		
	}

	@Override
	public void saveToClinakosSleepActivity(
			List<ValidicSleep> userSleepActivities2, int patientId,
			int providerId) {
		validicRestDAO.saveToClinakosSleepActivity(userSleepActivities2,patientId,providerId);
		
	}

	@Override
	public void saveToClinakosWeightActivity(
			List<ValidicWeight> userWeightActivities2, int patientId,
			int providerId) {
		validicRestDAO.saveToClinakosWeightActivity(userWeightActivities2,patientId,providerId);
		
	}

	@Override
	public void saveToClinakosTobaccoCessationActivity(
			List<ValidicTobaccoCessation> userTobaccoCessationActivities2,
			int patientId, int providerId) {
		validicRestDAO.saveToClinakosTobaccoCessationActivity(userTobaccoCessationActivities2,patientId,providerId);
		
	}

	@Override
	public List<ValidicUsersModel> get_vFitnessUsers(int providerId) {
	
		return validicRestDAO.get_vFitnessUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vNutritionUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vNutritionUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vRoutineUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vRoutineUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vDiabetesUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vDiabetesUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vBiometricUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vBiometricUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vSleepUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vSleepUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> get_vWeightUsers(int providerId) {
		// TODO Auto-generated method stub
		return validicRestDAO.get_vWeightUsers(providerId,DecryptionKey);
	}

	@Override
	public List<ValidicUsersModel> getUniqueUsers(
			List<ValidicUsersModel> fitnessUsers) {
		List<ValidicUsersModel> vFitnessUsers = new ArrayList<ValidicUsersModel>();
		
		Map<Integer,Set<String>> userAppMap = new HashMap<Integer,Set<String>>();
		for(ValidicUsersModel validicUsersModel : fitnessUsers){
			int userId = validicUsersModel.getClinakosUserId();
			if(userAppMap.containsKey(userId)){
				Set<String>apps = userAppMap.get(userId);
				apps.add((validicUsersModel.getAppSourceName()));
				userAppMap.put(userId, apps);
				validicUsersModel.setAppSource(apps);
				validicUsersModel.setUserAppMap(userAppMap);
			}else{
				Set<String>appSource = new HashSet<String>();
			appSource.add(validicUsersModel.getAppSourceName());
			userAppMap.put(userId, appSource);
			validicUsersModel.setAppSource(appSource);
			validicUsersModel.setUserAppMap(userAppMap);
			}
			if(vFitnessUsers.contains(validicUsersModel)){
				vFitnessUsers.remove(validicUsersModel);
			}
			logger.info("user id "+userId);
			logger.info("app source size "+validicUsersModel.getUserAppMap());
			vFitnessUsers.add(validicUsersModel);
		}
		logger.info("vFitnessUsers size "+vFitnessUsers.size());
		return vFitnessUsers;
	}

}
