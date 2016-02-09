package com.clinakos.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.clinakos.data.model.patient.ValidicUsersModel;
import com.validic.UserResponse;
import com.validic.ValidicBiometricmeasurement;
import com.validic.ValidicDiabetesMeasurements;
import com.validic.ValidicFitnessActivity;
import com.validic.ValidicNutrition;
import com.validic.ValidicSleep;
import com.validic.ValidicRoutineActivity;
import com.validic.ValidicTobaccoCessation;
import com.validic.ValidicWeight;


public interface IValidicRestService {

	void saveValidicUserCredential(UserResponse user);

	void saveValidicDibitesMeasurementData(
			List<ValidicDiabetesMeasurements> diabetes);

	void saveValidicFitnessActivity(List<ValidicFitnessActivity> fitness);

	void saveValidicBiometricsMeasurementData(
			ArrayList<ValidicBiometricmeasurement> biometrics);


	void saveValidicNutritionData(List<ValidicNutrition> list);

	void saveValidicSleepData(List<ValidicSleep> sleeps);


	void saveValidicWeightDetails(List<ValidicWeight> weight);

	void saveValidicRoutineActivity(List<ValidicRoutineActivity> routine);

	void saveTobaccoCessationData(
			List<ValidicTobaccoCessation> tobacco_cessation);
	List<ValidicWeight> getValidicPatientWeightData(int patientId);
	List<ValidicWeight> getValidicPatientWeightData(int patientId, Date startDate, Date endDate, String sourceName);

	List<String> getValidicWeightSourceNameData(int patientId);

	List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId);

	List<String> validicDiabitesMeasurementSourceNameData(String patientId);

	List<ValidicDiabetesMeasurements> getValidicPatientDiabetesMeasurementData(
			String patientId, Date startDate, Date endDate, String sourceName);

	List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId);

	List<ValidicBiometricmeasurement> getValidicBiometricMeasurementData(
			String patientId, Date startDate,
			Date endDate, String sourceName	);

	List<String> getValidicBiometricMeasurementSourceNameData(String patientId);

	List<ValidicNutrition> getValidicPatientNutritionsData(String patientId);

	List<ValidicNutrition> getValidicPatientNutritionsData(String patientId,
			Date startDate, Date endDate, String source_name);

	List<String> getValidicNutritionSourceNameData(String patientId);

	List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
			String patientId);

	List<ValidicFitnessActivity> getValidicPatientFitnessActivityData(
			String patientId, Date startDate, Date endDate, String source_name);

	List<String> getValidicFitnessActivitySourceName(String string);

	List<ValidicSleep> validicPatientSleepData(String patientId);

	List<String> getValidicSleepSourceNameData(String patientId);

	List<ValidicSleep> validicPatientSleepData(String patientId, Date startDate,
			Date endDate, String sourceName);

	List<ValidicTobaccoCessation> getValidicPatientTobaccoData(String patientId);

	List<ValidicTobaccoCessation> getValidicPatientTobaccoData(String patientId,
			Date startDate, Date endDate, String source_name);

	List<String> getTobaccoCessationSourceNameData(String string);

	List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId);

	List<ValidicRoutineActivity> getValidicPatientRoutineActivityData(
			String patientId, Date startDate, Date endDate, String source_name);

	List<String> getValidicRoutineActivitySourceNameData(String patientId);

	void callTestProc(int i) throws HibernateException, SQLException;

	UserResponse getValidicUser(int patientId, int providerId);

	List<ValidicFitnessActivity> getClinakosValidicFitnessData(int patientId,
			UserResponse userObj);

	void saveToClinakosActivityFitness(
			List<ValidicFitnessActivity> clinakosValidicFitnessData, int patientId, int providerId);

	void saveToClinakosActivityDiabetes(
			List<ValidicDiabetesMeasurements> latestDiabetesData,
			int patientId, int providerId);

	void saveToClinakosBiometricActivity(
			List<ValidicBiometricmeasurement> userBiometricMeasurements2,
			int patientId, int providerId);

	void saveToClinakosRoutineActivity(
			List<ValidicRoutineActivity> latestRoutineData, int patientId,
			int providerId);

	void saveToClinakosNutritionActivity(
			List<ValidicNutrition> userNutritions2, int patientId,
			int providerId);

	void saveToClinakosSleepActivity(List<ValidicSleep> userSleepActivities2,
			int patientId, int providerId);

	void saveToClinakosWeightActivity(
			List<ValidicWeight> userWeightActivities2, int patientId,
			int providerId);

	void saveToClinakosTobaccoCessationActivity(
			List<ValidicTobaccoCessation> userTobaccoCessationActivities2,
			int patientId, int providerId);

	List<ValidicUsersModel> get_vFitnessUsers(int providerId);

	List<ValidicUsersModel> get_vNutritionUsers(int providerId);

	List<ValidicUsersModel> get_vRoutineUsers(int providerId);

	List<ValidicUsersModel> get_vDiabetesUsers(int providerId);

	List<ValidicUsersModel> get_vBiometricUsers(int providerId);

	List<ValidicUsersModel> get_vSleepUsers(int providerId);

	List<ValidicUsersModel> get_vWeightUsers(int providerId);

	List<ValidicUsersModel> getUniqueUsers(
			List<ValidicUsersModel> fitnessUsers);
		


}
