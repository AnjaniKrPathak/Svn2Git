package com.clinakos.service.serviceImpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

import com.clinakos.data.dao.IPatientDAO;
import com.clinakos.data.model.core.EmpProviderLocation;
import com.clinakos.data.model.patient.CareTeam;
import com.clinakos.data.model.patient.ClinicMaster;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.Icd9Diagnosis;
import com.clinakos.data.model.patient.MasterDiagnosis;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.service.IPatientService;

public class PatientServiceImpl implements IPatientService {
	private IPatientDAO patientDAO;
	
	
	
	

	/**
	 * @return the patientDAO
	 */
	public IPatientDAO getPatientDAO() {
		return patientDAO;
	}




	/**
	 * @param patientDAO the patientDAO to set
	 */
	public void setPatientDAO(IPatientDAO patientDAO) {
		this.patientDAO = patientDAO;
	}
 
	
	/*// Method to fetch Care Team list for Particular Patient, Created on 28th June 2013 by Anand S Jha
	public List<DoctorDetail> fetchCareTeamList(int patientId) {
		// TODO Auto-generated method stub		
		return patientDAO.fetchCareTeamList(patientId);
	}*/

	/* (non-Javadoc)
	 * @see com.clinakos.doctor.service.IDoctorService#getPatientICD_10Diagonsis()
	 */
	public List<PatientDiagnosesDetails> getPatientICD_10Diagonsis() {
		// TODO Auto-generated method stub
		return patientDAO.showPatientICD_10Diagnosis();
	}

	/* (non-Javadoc)
	 * @see com.clinakos.doctor.service.IDoctorService#getPatientICD_9CodeDiagonsis()
	 */
	public List<PatientDiagnosesDetails> getPatientICD_9CodeDiagonsis() {
		// TODO Auto-generated method stub
		return patientDAO.showPatientICD_9CodeDiagnosis();
	}


/*
 * ************************************ METHOD TO FIND PATIENT'S ALLERGY DETAIL
 * @see com.clinakos.service.IPatientService#getAllergyList()
 */

	public List<PatientAllergy> getAllergyList() {

		return patientDAO.getAllergyList();
	}




	public List<ClinicMaster> findClinicList() {
		// TODO Auto-generated method stub
		return patientDAO.findClinicList();
	}

	public List<DoctorDetail> findCareTeamDetailList(int patientId) throws HibernateException, SQLException {
		return patientDAO.findCareTeamDetailList(patientId);
	}




    /**
     * @see com.clinakos.service.IPatientService#findPatientDiognosisDetails(int)
     * @param PatientId 
     * @return List of Patient Diagnosis Detail 	
     */
	public List<PatientDiagnosesDetails> findPatientDiognosisDetails(
			int patientId) {
		// TODO Auto-generated method stub
		return patientDAO.findPatientDiognosisDetails(patientId);
	}



/***
 * 
 * @param diagnosesDetails
 * @param selectIntervention
 * @see com.clinakos.service.IPatientService#findPatientDiognosisDetails1(String, int)
 * @return Patient Diagnosis list 
 */
	
	public List<MasterDiagnosis> findPatientDiognosisDetails1(
			String diagnosesDetails,int selectIntervention) {
		// TODO Auto-generated method stub
		return patientDAO.findPatientDiognosisDetails1(diagnosesDetails,selectIntervention);
	}


	/**
	 * Save Patient Diagnosis Detail based on Selection 
	 * @param patientDiagnosesDetails Selected Patient Diagnosis Detail 
	 * @see com.clinakos.service.IPatientService#savePatientDiagnosis(PatientDiagnosesDetails)
	 * 
	 * 
	 */
	
	public void savePatientDiagnosis(
			PatientDiagnosesDetails patientDiagnosesDetails) {
		patientDAO.savePatientDiagnosis(patientDiagnosesDetails);
		
	}
/**
 * Delete selected Diagnosis detail data 
 * @param patientDiagnosesDetails Selected Patient Diagnosis  detail data 
 * @see com.clinakos.service.IPatientService#deleteDiagnosisDetails(PatientDiagnosesDetails) 
 */

public void  deleteDiagnosisDetails(PatientDiagnosesDetails patientDiagnosesDetails){

patientDAO.deleteDiagnosisDetails( patientDiagnosesDetails);
}



/*
 * 
 * ******************Method To Fetch icd9 Diagnosis Lists
 * 
 * @see com.clinakos.service.IPatientService#getIcd9MasterList()
 */
	public List<Icd9Diagnosis> getIcd9MasterList(String icdDiscription,int selectIntervention) {
		
		return patientDAO.getIcd9MasterList(icdDiscription,selectIntervention);
	}
	
	/**
	 * @param patientDiagnosesDetails object for modification 
	 * @see com.clinakos.service.IPatientService#editDiagnosisDetails(PatientDiagnosesDetails)
	 */
	public  void editDiagnosisDetails(PatientDiagnosesDetails patientDiagnosesDetails)
	{
		 patientDAO.editDiagnosis(patientDiagnosesDetails);
	}




	
	public void diagnosisIntegrationFromNewCropToClinakos(
			Set<PatientDiagnosesDetails> patientDiagnosisSetFromIntegration,
			int patientId) {
		// TODO Auto-generated method stub
		patientDAO.diagnosisIntegrationFromNewCropToClinakos(patientDiagnosisSetFromIntegration,patientId);
		
	}




	
	public void insuranceDetailIntegrationFromNewCropToClinakos(
			List<UserInsuranceDetails> userInsuranceDetailListFromIntegration,
			int patientId) {
		// TODO Auto-generated method stub
		patientDAO.insuranceDetailIntegrationFromNewCropToClinakos(userInsuranceDetailListFromIntegration,patientId);
		
	}

	// To get patient guarantor list for lab integration added by venu
	public List<PatientGuarantor> getPatientGuarantorList(int loggedPatient) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientGuarantorList(loggedPatient);
	}

	@Override
	public List<UserInsuranceDetails> getPatientInsuranceList(int loggedPatient) {
		// TODO Auto-generated method stub
		return patientDAO.getPatientInsuranceList(loggedPatient);
	}


/*@saurabh
 * *****added by saurabh for 	
 * delete doctor detail from care Team
*/
	public void deleteDoctorFromCareTeam(int RowIdForDeleteCareTeam) {
		patientDAO.deleteDoctorFromCareTeam(RowIdForDeleteCareTeam);
	}


/*@saurabh
 * *****added by saurabh for 	
 * edit doctor detail in care Team
 */
	public void editDoctorFromCareTeam(CareTeam careTeam) {
		patientDAO.editDoctorFromCareTeam(careTeam);
	}

/*@author: saurabh
 * ********added by saurabh for
 * searching clinakos existing doctor for add doc in care team
 */
	public List<EmpProviderLocation> searchDocFromExistingClinakos(
			String valueForSearchingClinakosDoc, int providerId) {
		return patientDAO.SearchDocFromExistingClinakos(valueForSearchingClinakosDoc,providerId);
	}

/*@uthor: saurabh
 * **********to add clinakos doctor in care team
 * 
*/
	public void addClinakosDoctorIncareTeam(CareTeam careTeam) {
		patientDAO.addClinakosDoctorIncareTeam(careTeam);
	}

/*@uthor: saurabh
 * **********to add non-clinakos doctor in care team
 * 
*/
	public void saveDocForCareTeam(CareTeam careTeam) {
		careTeam.setClinakosUser(false);
		careTeam.setDoctorId(0);
		careTeam.setProviderId(0);
		patientDAO.addClinakosDoctorIncareTeam(careTeam);
	}
}
