package com.clinakos.data.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.Icd9Diagnosis;
import com.clinakos.data.model.patient.MasterDiagnosis;
import com.clinakos.data.model.patient.MedandGenricmed;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.UserInsuranceDetails;

public interface IPatientDAO {
	//List<DoctorDetail> fetchCareTeamList(int patientId); // Method to fetch Care Team list for
															// Particular
															// Patient, Created
															// on 29th June 2013
															// by Anand S Jha

	/**
	 * @return
	 */
	List<PatientDiagnosesDetails> showPatientICD_10Diagnosis();

	/**
	 * @return
	 */
	List<PatientDiagnosesDetails> showPatientICD_9CodeDiagnosis();

	List<PatientAllergy> getAllergyList();

	List<ClinicMaster> findClinicList();

	List<DoctorDetail> findCareTeamDetailList(int patientId) throws HibernateException, SQLException;

	

	List<PatientDiagnosesDetails> findPatientDiognosisDetails(int patientId);

	List<MasterDiagnosis> findPatientDiognosisDetails1(
			String diagnosesDetails, int selectIntervention);





 public void savePatientDiagnosis(PatientDiagnosesDetails patientDiagnosesDetails);

void deleteDiagnosisDetails(PatientDiagnosesDetails patientDiagnosesDetails);

List<Icd9Diagnosis> getIcd9MasterList(String icdDiscription,int selectIntervention);

void editDiagnosis(PatientDiagnosesDetails patientDiagnosesDetails);

void diagnosisIntegrationFromNewCropToClinakos(
		Set<PatientDiagnosesDetails> patientDiagnosisSetFromIntegration,
		int patientId);

void insuranceDetailIntegrationFromNewCropToClinakos(
		List<UserInsuranceDetails> userInsuranceDetailListFromIntegration,
		int patientId);

//To get patient guarantor list for lab integration added by venu
List<PatientGuarantor> getPatientGuarantorList(int loggedPatient);

List<UserInsuranceDetails> getPatientInsuranceList(int loggedPatient);


void deleteDoctorFromCareTeam(int rowIdForDeleteCareTeam);

void editDoctorFromCareTeam(CareTeam careTeam);

List<EmpProviderLocation> SearchDocFromExistingClinakos(
		String valueForSearchingClinakosDoc, int providerId);

void addClinakosDoctorIncareTeam(CareTeam careTeam);



}
