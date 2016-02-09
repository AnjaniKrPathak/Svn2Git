package com.clinakos.viewController.webservicMangedBean;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.SocketException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.PatientPBMDrugHistoryResult;
import com.clinakos.data.model.core.PatientPBMEligibilityDetailData;
import com.clinakos.data.model.core.PatientPbmDrugHistoryDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.data.model.patient.UserLoginDetail;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.WebServiceClientImpl;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import https.secure_newcropaccounts_com.v7.webservices.AccountRequest;
import https.secure_newcropaccounts_com.v7.webservices.ArrayOfString;
import https.secure_newcropaccounts_com.v7.webservices.Credentials;
import https.secure_newcropaccounts_com.v7.webservices.DownloadDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetailV2;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyInteractionV2;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyInteractionV2Response;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseInteraction;
import https.secure_newcropaccounts_com.v7.webservices.DrugDiseaseInteractionResponse;
import https.secure_newcropaccounts_com.v7.webservices.DrugFormularyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugHistoryDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugHistoryDetailResult;
import https.secure_newcropaccounts_com.v7.webservices.EligibilityDetailResultV3;
import https.secure_newcropaccounts_com.v7.webservices.EligibilityDetailV3;
import https.secure_newcropaccounts_com.v7.webservices.FormularyAlternativesWithDrugInfo2;
import https.secure_newcropaccounts_com.v7.webservices.FormularyAlternativesWithDrugInfo2Response;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageDetailV3;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageV3;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageV3Response;
import https.secure_newcropaccounts_com.v7.webservices.GetMostRecentDownloadUrl;
import https.secure_newcropaccounts_com.v7.webservices.GetMostRecentDownloadUrlResponse;
import https.secure_newcropaccounts_com.v7.webservices.GetPBMDrugHistoryV2;
import https.secure_newcropaccounts_com.v7.webservices.GetPBMDrugHistoryV2Response;
import https.secure_newcropaccounts_com.v7.webservices.GetPBMEligibilityV3;
import https.secure_newcropaccounts_com.v7.webservices.GetPBMEligibilityV3Response;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientAllergyHistoryV4;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientAllergyHistoryV4Response;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientFullMedicationHistory5;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientFullMedicationHistory5Response;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientFullMedicationHistory6;
import https.secure_newcropaccounts_com.v7.webservices.GetPatientFullMedicationHistory6Response;
import https.secure_newcropaccounts_com.v7.webservices.ObjectFactory;
import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyExtendedDetailV4;
import https.secure_newcropaccounts_com.v7.webservices.PatientDrugDetail5;
import https.secure_newcropaccounts_com.v7.webservices.PatientFullMedHistoryV6;
import https.secure_newcropaccounts_com.v7.webservices.PatientInformationRequester;
import https.secure_newcropaccounts_com.v7.webservices.PatientRequest;
import https.secure_newcropaccounts_com.v7.webservices.PrescriptionHistoryRequest;
import https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetail;
import org.apache.commons.lang.StringUtils;
public class NcUpdate1WSBean {
	public static final Logger logger = Logger.getLogger("NcUpdate1WSBean.class");
	/*public void init() throws Exception {
		 callDoctorDetailService(); 
		// callFullMedicationHistoryServices();
	}*/

	static https.secure_newcropaccounts_com.v7.webservices.ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();

	@Autowired
	private WebServiceTemplate Update1ServiceDetail;
	@Autowired
	private IUserService userService;

	//List<PatientDrugDetail5> patDrugDetailList = null;// new
														// ArrayList<PatientDrugDetail5>();
	List<FormularyCoverageDetailV3> fCoverageDetailV3List = null;
	List<DrugFormularyDetail> drugFormularyDetailList = new ArrayList<DrugFormularyDetail>();
	private DrugHistoryDetail drugHistoryDetail = null;
	private List<DrugHistoryDetail> drugHistoryDetailList = null;
	private List<DrugFormularyDetail> formularyDetailListForFormularySearch = new ArrayList<DrugFormularyDetail>();
	private List<FormularyDetail> formularyDetailList = null;

	Properties properties = null;
	private String medicineForFormularySearch = "";
	private InsuranceCompanies insuranceCompanies = null;

	private boolean showFormularyMedicineData;
	private AuditInfo auditInfo;
	List<PatientAllergyExtendedDetailV4> patientAllergyExtendedDetailV4List;

	// Plain constructor.
	public NcUpdate1WSBean() {

	}

	public NcUpdate1WSBean(WebServiceTemplate webServiceTemplate) {
		this.Update1ServiceDetail = webServiceTemplate;
	}

	public void resetValue()
	{
		drugHistoryDetailList =null;
	}
	
	public void callDoctorDetailService() throws Exception {

		System.out.println("======= Started =========");
		String accId = "demo";
		String siteId = "demo";
		String partName = "demo";
		String name = "demo";
		String password = "demo";

		List<RegisterPrescriberDetail> doctorDetailList = new ArrayList<RegisterPrescriberDetail>();
		System.out.println("Call Prepare method");

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		/*
		 * WebServiceClientImpl<AllLicensedPrescribersStatus> wsClient=new
		 * WebServiceClientImpl();
		 * 
		 * AllLicensedPrescribersStatus lpStatus=
		 * WS_CLIENT_FACTORY.createAllLicensedPrescribersStatus();
		 * 
		 * lpStatus.setAccountRequest(accountRequest);
		 * lpStatus.setCredentials(credentials);
		 */
		/*
		 * AllLicensedPrescribersStatusResponse
		 * allLicensedPrescribersStatusResponse
		 * =(AllLicensedPrescribersStatusResponse)
		 * wsClient.sendRequestAndReceiveResponse(lpStatus,
		 * this.DoctorServiceDetail);
		 */
		/*
		 * System.out.println("Doctor Detail Manged bean "+
		 * allLicensedPrescribersStatusResponse);
		 * System.out.println(allLicensedPrescribersStatusResponse
		 * .getAllLicensedPrescribersStatusResult
		 * ().getRegisterPrescriberDetailArray
		 * ().getRegisterPrescriberDetail().size());
		 * doctorDetailList=allLicensedPrescribersStatusResponse
		 * .getAllLicensedPrescribersStatusResult
		 * ().getRegisterPrescriberDetailArray().getRegisterPrescriberDetail();
		 * for(RegisterPrescriberDetail rs:doctorDetailList){
		 * System.out.println(
		 * "Doc First Name  "+rs.getDoctorFirstName()+"Doc Loc "
		 * +rs.getLocationName()+
		 * " Doc Start date "+rs.getStartDateTime()+" Doc end date "
		 * +rs.getStopDateTime()); }
		 */
	}

	/**
	 * Call Full medication History V5 value
	 * @param providerLocation 
	 * @param eDate 
	 * @param sDate 
	 */
	public List<PatientDrugDetail5> callFullMedicationHistoryServices(String prescriptionArchiveStatus, Date stDate, Date enDate, ProviderLocation providerLocation)
			throws Exception {
		System.out.println("::::::::::::::::::+"+stDate+"::::::::::end dsdate"+enDate);
		///String accId = getProperties().getProperty("accountId");
		//String siteId = getProperties().getProperty("siteId");
		
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		logger.info(":::::::::callFullMedicationHistoryServices method start:::"+accId+":::site id::"+siteId);
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String patientId = Integer.toString(new ContextUtil().getPatientId());
		//String sDate = getProperties().getProperty("startDate");
		//String eDate = getProperties().getProperty("endDate");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Date stDate = sdf.parse(sDate);
		//Date enDate = sdf.parse(eDate);
		GregorianCalendar gcs = new GregorianCalendar();
		gcs.setTime(stDate);
		XMLGregorianCalendar startDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gcs);
		GregorianCalendar gce = new GregorianCalendar();
		gce.setTime(enDate);
		XMLGregorianCalendar endDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gce);

		String userId = Integer.toString(new ContextUtil().getProviderId());

		List<PatientDrugDetail5> patientFullMedicationList = new ArrayList<PatientDrugDetail5>();

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		PatientRequest patientRequest = WS_CLIENT_FACTORY
				.createPatientRequest();
		patientRequest.setPatientId(patientId);

		PrescriptionHistoryRequest prescriptionHistoryRequest = WS_CLIENT_FACTORY
				.createPrescriptionHistoryRequest();
		prescriptionHistoryRequest.setStartHistory(startDate);
		prescriptionHistoryRequest.setEndHistory(endDate);
		prescriptionHistoryRequest.setPrescriptionStatus("C");
		prescriptionHistoryRequest.setPrescriptionArchiveStatus(prescriptionArchiveStatus);
		prescriptionHistoryRequest.setPrescriptionSubStatus("S");

		//System.out.println("::prescriptionHistoryRequest:"+prescriptionHistoryRequest);
		PatientInformationRequester patientInformationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		patientInformationRequester.setUserId(userId);
		patientInformationRequester.setUserType("%");

		WebServiceClientImpl<GetPatientFullMedicationHistory5> wsclient = new WebServiceClientImpl<GetPatientFullMedicationHistory5>();
		GetPatientFullMedicationHistory5 pFullMedStatus5 = WS_CLIENT_FACTORY
				.createGetPatientFullMedicationHistory5();
		pFullMedStatus5.setAccountRequest(accountRequest);
		pFullMedStatus5.setCredentials(credentials);
		pFullMedStatus5.setPatientIdType("%");
		pFullMedStatus5
				.setPatientInformationRequester(patientInformationRequester);
		pFullMedStatus5
				.setPrescriptionHistoryRequest(prescriptionHistoryRequest);

		pFullMedStatus5.setPatientRequest(patientRequest);
		try {
			//System.out.println("::::::::::::112"+pFullMedStatus5);
			//System.out.println(":::::::::::55");
			GetPatientFullMedicationHistory5Response history5Response = (GetPatientFullMedicationHistory5Response) wsclient
					.sendRequestAndReceiveResponse(pFullMedStatus5,
							Update1ServiceDetail);
			if(history5Response.getGetPatientFullMedicationHistory5Result().getResult().getStatus().value().equals("OK")){
				
				if (!history5Response.getGetPatientFullMedicationHistory5Result()
						.getPatientDrugDetail().getPatientDrugDetail5().isEmpty()){
					
					patientFullMedicationList = history5Response
							.getGetPatientFullMedicationHistory5Result()
							.getPatientDrugDetail().getPatientDrugDetail5();
				}
				
			}
			else if (!(history5Response.getGetPatientFullMedicationHistory5Result().getResult().getStatus().value().equals("OK"))) {
				 getAuditInfo().setComment(history5Response.getGetPatientFullMedicationHistory5Result().getResult().getMessage());
					getAuditInfo().setUserId(new ContextUtil().getLoginId());
					getAuditInfo().setMachineName("GetPatientFullMedicationHistory5 WSDL");
					getAuditInfo().setCreateTime(new Date());
	                patientFullMedicationList=new ArrayList<PatientDrugDetail5>();
					userService.saveWSFailStatus(auditInfo);
			}
		
			
		} catch (NullPointerException ne) {
			// System.out.println(":::::::::::::::::::::::exception ");
			ne.printStackTrace();
		} catch (Exception e) {
			// System.out.println(":::11::::::::::::::::::::exception ");
			e.printStackTrace();
		}
		return patientFullMedicationList;

	}

	/*public List<PatientDrugDetail5> getPatDrugDetailList(String prescriptionArchiveStatus, Date startDate, Date endDate) throws Exception {
		if (patDrugDetailList == null) {
			patDrugDetailList = new ArrayList<PatientDrugDetail5>();

		}
		patDrugDetailList = callFullMedicationHistoryServices(prescriptionArchiveStatus,startDate,endDate);

		return patDrugDetailList;
	}

	public void setPatDrugDetailList(List<PatientDrugDetail5> patDrugDetailList) {
		this.patDrugDetailList = patDrugDetailList;
	}*/

	/**
	 * Call FormularyCoverageV3 Soap For FormularyCoverage
	 * 
	 * @throws Exception
	 */
	public List<FormularyCoverageDetailV3> callFormularyCoverageV3()
			throws Exception {

		System.out.println("Accccccount Id"
				+ getProperties().getProperty("accountId") + "Siite Id"
				+ getProperties().getProperty("siteId"));
		String accId = getProperties().getProperty("accountId");
		String siteId = getProperties().getProperty("siteId");
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String patId = getProperties().getProperty("patientId");
		String userId = getProperties().getProperty("userId");
		String healtPlanId = "133";

		ArrayOfString arrayOfString = new ArrayOfString();
		arrayOfString.getString().add("8899");
		arrayOfString.getString().add("1614");
		arrayOfString.getString().add("2723");
		arrayOfString.getString().add("3561");
		arrayOfString.getString().add("1615");
		arrayOfString.getString().add("3693");

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		PatientRequest patientRequest = WS_CLIENT_FACTORY
				.createPatientRequest();
		patientRequest.setPatientId(patId);

		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType("D");

		FormularyCoverageV3 formularyCoverageV3Request = WS_CLIENT_FACTORY
				.createFormularyCoverageV3();
		formularyCoverageV3Request.setAccountRequest(accountRequest);
		formularyCoverageV3Request.setCredentials(credentials);
		formularyCoverageV3Request.setDrugConcept(arrayOfString);

		formularyCoverageV3Request.setDrugConceptType("M");
		formularyCoverageV3Request.setEligibilityIndex("0");
		formularyCoverageV3Request.setHealthplanID(healtPlanId);
		formularyCoverageV3Request.setHealthplanTypeID("M");
		formularyCoverageV3Request
				.setPatientInformationRequester(informationRequester);
		formularyCoverageV3Request.setPatientRequest(patientRequest);

		WebServiceClientImpl<FormularyCoverageV3> wsClientForFormularyCoverageV3 = new WebServiceClientImpl<FormularyCoverageV3>();

		FormularyCoverageV3Response formularyCoverageV3Response = (FormularyCoverageV3Response) wsClientForFormularyCoverageV3
				.sendRequestAndReceiveResponse(formularyCoverageV3Request,
						Update1ServiceDetail);
		System.out.println("Formulary Coverage Response"
				+ formularyCoverageV3Response.getFormularyCoverageV3Result()
						.getFormularyCoverageAlternativesDetailV3Array()
						.getFormularyCoverageDetailV3());
		List<FormularyCoverageDetailV3> formCoverageDetailV3List=new ArrayList<FormularyCoverageDetailV3>();
         if(formularyCoverageV3Response.getFormularyCoverageV3Result().getResult().getStatus().value().equals("OK")){
        	 if(!(formularyCoverageV3Response.getFormularyCoverageV3Result().getFormularyCoverageDetailV3Array()==null)){
        		 formCoverageDetailV3List = formularyCoverageV3Response
        					.getFormularyCoverageV3Result()
        					.getFormularyCoverageDetailV3Array()
        					.getFormularyCoverageDetailV3();
        	 }
         }
         else if (!(formularyCoverageV3Response.getFormularyCoverageV3Result().getResult().getStatus().value().equals("OK"))) {
        	getAuditInfo().setComment(formularyCoverageV3Response.getFormularyCoverageV3Result().getResult().getMessage());
 			getAuditInfo().setUserId(new ContextUtil().getLoginId());
 			getAuditInfo().setMachineName("DrugDrugInteraction WSDL");
 			getAuditInfo().setCreateTime(new Date());
 		    formCoverageDetailV3List=new ArrayList<FormularyCoverageDetailV3>();
 			userService.saveWSFailStatus(auditInfo);
		}
		 

		for (FormularyCoverageDetailV3 formDetail : formCoverageDetailV3List) {
			System.out.println("Formulary Class:"
					+ formDetail.getTherapeuticClassDesc() + "Form Sub Class"
					+ formDetail.getTherapeuticSubClassDesc());
		}
		return formCoverageDetailV3List;
	}

	/**
	 * Call FormularyAlternativesWithDrugInfo2 Soap For Formulary Alternative
	 * Drug Info
	 * @param providerLocation 
	 * 
	 * @throws Exception
	 */

	// public List<DrugFormularyDetail> callFormularyAlternativesWithDrugInfo2()
	// throws Exception{
	public List<FormularyDetail> callFormularyAlternativesWithDrugInfo2(
			String healthPlan, String drugConceptId, String formularyStatus, ProviderLocation providerLocation)
			throws Exception {

		/*Double drugId = 0.0;
		if (!(medicineForFormularySearch.equals(""))) {
			System.out.println("Drug Id " + "Med for search "
					+ medicineForFormularySearch + " User service "
					+ userService);
			drugId = userService
					.getDrugIdByDrugName(medicineForFormularySearch);
			System.out.println("DrugId in ws Bean " + drugId
					+ "getInsuranceCompanies().getId()"
					+ getInsuranceCompanies().getId());
		}
      */
		logger.info("callFormularyAlternativesWithDrugInfo2::::::::::::::::::::::::::::::::;"+"Drug Concept:::::::: "+drugConceptId+"Health Plan ::::::;"+healthPlan);
		drugFormularyDetailList = new ArrayList<DrugFormularyDetail>();
		formularyDetailList=new ArrayList<FormularyDetail>();
		String drugConcept = drugConceptId;

		if (!(healthPlan.equals("")) && (!(drugConcept == null))) {

			String accId = providerLocation.getAccountId();
			
			String siteId = providerLocation.getSiteId();
			String partName = getProperties().getProperty("partnerName");
			String name = getProperties().getProperty("name");
			String password = getProperties().getProperty("password");
			// String patId=Integer.toString(new ContextUtil().getPatientId());
			String userId = Integer.toString(new ContextUtil().getProviderId());
			String healthPlanId = healthPlan;

			AccountRequest accountRequest = WS_CLIENT_FACTORY
					.createAccountRequest();
			accountRequest.setAccountId(accId);
			accountRequest.setSiteId(siteId);

			Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
			credentials.setName(name);
			credentials.setPartnerName(partName);
			credentials.setPassword(password);

			// PatientRequest
			// patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
			// patientRequest.setPatientId(patId);

			PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
					.createPatientInformationRequester();
			informationRequester.setUserId(userId);
			informationRequester.setUserType("demo");

			FormularyAlternativesWithDrugInfo2 formularyAlternativesWithDrugInfo2 = WS_CLIENT_FACTORY
					.createFormularyAlternativesWithDrugInfo2();
			formularyAlternativesWithDrugInfo2
					.setAccountRequest(accountRequest);
			formularyAlternativesWithDrugInfo2.setCredentials(credentials);

			formularyAlternativesWithDrugInfo2.setDrugConcept(drugConcept);

			formularyAlternativesWithDrugInfo2.setDrugConceptType("F");
			formularyAlternativesWithDrugInfo2.setEligibilityIndex("0");
			formularyAlternativesWithDrugInfo2.setHealthplanID(healthPlanId);
			formularyAlternativesWithDrugInfo2.setHealthplanTypeID("S");
			formularyAlternativesWithDrugInfo2
					.setPatientInformationRequester(informationRequester);
			// formularyAlternativesWithDrugInfo2.setPatientRequest(patientRequest);
			try {

				WebServiceClientImpl<FormularyAlternativesWithDrugInfo2> wsClientForFormularyAlternativesWithDrugInfo2 = new WebServiceClientImpl<FormularyAlternativesWithDrugInfo2>();
				FormularyAlternativesWithDrugInfo2Response formularyAlternativesWithDrugInfo2Response = new FormularyAlternativesWithDrugInfo2Response();
				formularyAlternativesWithDrugInfo2Response = (FormularyAlternativesWithDrugInfo2Response) wsClientForFormularyAlternativesWithDrugInfo2
						.sendRequestAndReceiveResponse(
								formularyAlternativesWithDrugInfo2,
								Update1ServiceDetail);
				// if(formularyAlternativesWithDrugInfo2Response)
				//System.out.println("111::::>>>>>>>>formularyAlternativesWithDrugInfo"+formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getDrugFormularyDetailArray().getDrugFormularyDetail().size());
				//logger.info("formularyAlternativesWithDrugInfo In midddle"+formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getDrugFormularyDetailArray().getDrugFormularyDetail().size());
				if (!(formularyAlternativesWithDrugInfo2Response
						.getFormularyAlternativesWithDrugInfo2Result()
						.getResult().getStatus().value().equals("OK"))) {
					getAuditInfo()
							.setComment(
									formularyAlternativesWithDrugInfo2Response
											.getFormularyAlternativesWithDrugInfo2Result()
											.getResult().getMessage());
					getAuditInfo().setUserId(new ContextUtil().getLoginId());
					getAuditInfo().setMachineName(
							"FormularyAlternativesWithDrugInfo2 WSDL");
					getAuditInfo().setCreateTime(new Date());
					/*
					 * drugFormularyDetailList=new
					 * ArrayList<DrugFormularyDetail>();
					 */
					formularyDetailList = new ArrayList<FormularyDetail>();

					userService.saveWSFailStatus(auditInfo);
				}
				if (formularyAlternativesWithDrugInfo2Response
						.getFormularyAlternativesWithDrugInfo2Result()
						.getResult().getStatus().value().equals("OK")) {

					if (!(formularyAlternativesWithDrugInfo2Response
							.getFormularyAlternativesWithDrugInfo2Result()
							.getDrugFormularyDetailArray() == null))

						drugFormularyDetailList = formularyAlternativesWithDrugInfo2Response
								.getFormularyAlternativesWithDrugInfo2Result()
								.getDrugFormularyDetailArray()
								.getDrugFormularyDetail();

					for (DrugFormularyDetail drugForm : drugFormularyDetailList) {
						
						System.out.println("drugForm.getFormularyCoverage()"+drugForm.getFormularyCoverage()+"drugForm drugggggggggg"+drugForm.getDrugDetail().getDrug());
										
						FormularyDetail formularyDetail = new FormularyDetail();
						if ((formularyStatus.equals("51"))) {
							if (drugForm.getFormularyCoverage().equals("51"))
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
							formularyDetail.setTherapyType(drugForm
									.getDrugDetail().getTherapeuticClass());
							formularyDetail.setIsFormulary("Tier 1");
							formularyDetailList.add(formularyDetail);
						} else if (formularyStatus.equals("52")) {
							if (drugForm.getFormularyCoverage().equals("51"))
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
							formularyDetail.setTherapyType(drugForm
									.getDrugDetail().getTherapeuticClass());
							formularyDetail.setIsFormulary("Tier 1");
							formularyDetailList.add(formularyDetail);
							if (drugForm.getFormularyCoverage().equals("52")) {
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
								formularyDetail.setTherapyType(drugForm
										.getDrugDetail().getTherapeuticClass());
								formularyDetail.setIsFormulary("Tier 2");
								formularyDetailList.add(formularyDetail);
						}
						else if (formularyStatus.equals("53")) {
							if (drugForm.getFormularyCoverage().equals("51")) {
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
								formularyDetail.setTherapyType(drugForm
										.getDrugDetail().getTherapeuticClass());
								formularyDetail.setIsFormulary("Tier 1");
								formularyDetailList.add(formularyDetail);
								
							}if (drugForm.getFormularyCoverage().equals("52")) {
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
								formularyDetail.setTherapyType(drugForm
										.getDrugDetail().getTherapeuticClass());
								formularyDetail.setIsFormulary("Tier 2");
								formularyDetailList.add(formularyDetail);
							
							}if (drugForm.getFormularyCoverage().equals("53")) {
								formularyDetail.setMedicineName(drugForm
										.getDrugDetail().getDrug());
								formularyDetail.setTherapyType(drugForm
										.getDrugDetail().getTherapeuticClass());
								formularyDetail.setIsFormulary("Tier 3");
								formularyDetailList.add(formularyDetail);
							}
						  } else if (!(formularyStatus.equals("51")
								|| !(formularyStatus.equals("52")) || !(formularyStatus
									.equals("53")))) {
							formularyDetail.setMedicineName(drugForm
									.getDrugDetail().getDrug());
							formularyDetail.setTherapyType(drugForm
									.getDrugDetail().getTherapeuticClass());
							formularyDetail.setIsFormulary("On Formulary");
							formularyDetailList.add(formularyDetail);
						}

						// getFormularyDetailList().add(formularyDetail);
						
					}
				 }

				}
			}
            catch(SocketException se){
            	se.printStackTrace();
            }
			catch (NullPointerException ne) {
				// System.out.println(":::::::::::::::::::::::exception ");
				ne.printStackTrace();
			} catch (Exception e) {
				// System.out.println(":::11::::::::::::::::::::exception ");
				e.printStackTrace();
			}

			

		}
		//setShowFormularyMedicineData(true);
		setMedicineForFormularySearch("");
		// return drugFormularyDetailListDetailList;
		
	  logger.info("callFormularyAlternativesWithDrugInfo2:::::::::::::::: End ........."+"formularyDetailList.size()"+formularyDetailList.size());		
						
		return formularyDetailList;

	}

	/**
	 * @return the fCoverageDetailV3List
	 * @throws Exception
	 */
	public List<FormularyCoverageDetailV3> getfCoverageDetailV3List()
			throws Exception {
		if (fCoverageDetailV3List == null) {
			fCoverageDetailV3List = new ArrayList<FormularyCoverageDetailV3>();
		}
		fCoverageDetailV3List = callFormularyCoverageV3();
		return fCoverageDetailV3List;
	}

	/**
	 * @param fCoverageDetailV3List
	 *            the fCoverageDetailV3List to set
	 */
	public void setfCoverageDetailV3List(
			List<FormularyCoverageDetailV3> fCoverageDetailV3List) {
		this.fCoverageDetailV3List = fCoverageDetailV3List;
	}

	/**
	 * @return the drugFormularyDetailList
	 * @throws Exception
	 */
	public List<DrugFormularyDetail> getDrugFormularyDetailList() {
		/*
		 * if(drugFormularyDetailList==null){ drugFormularyDetailList=new
		 * ArrayList<DrugFormularyDetail>(); }
		 */
		// drugFormularyDetailList=callFormularyAlternativesWithDrugInfo2();
		return drugFormularyDetailList;
	}

	/**
	 * @param drugFormularyDetailList
	 *            the drugFormularyDetailList to set
	 */
	public void setDrugFormularyDetailList(
			List<DrugFormularyDetail> drugFormularyDetailList) {
		this.drugFormularyDetailList = drugFormularyDetailList;
	}

	/**
	 * Call Full Medication History Version 6
	 * @param providerLocation 
	 * @param endDate2 
	 * @param startDate2 
	 * @param prescriptionArchiveStatus 
	 * 
	 * @throws Exception
	 */

	public String callFullMedicationHistoryV6(ProviderLocation providerLocation, String prescriptionArchiveStatus, Date startDate2, Date endDate2)  {
		
		logger.info("callFullMedicationHistoryV6 method start::::::"+new Timestamp(new Date().getTime()));
		long startTime=System.currentTimeMillis();
		String xmlResponse="";
		try {
			
		
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");

		String patientId = Integer.toString(new ContextUtil().getPatientId());

		//String sDate = startDate2;
		//String eDate = getProperties().getProperty("endDate");
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date stDate =startDate2;
		Date enDate = endDate2;
		GregorianCalendar gcs = new GregorianCalendar();
		gcs.setTime(stDate);
		XMLGregorianCalendar startDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gcs);
		GregorianCalendar gce = new GregorianCalendar();
		gce.setTime(enDate);
		XMLGregorianCalendar endDate = DatatypeFactory.newInstance()
				.newXMLGregorianCalendar(gce);

		String userId = Integer.toString(new ContextUtil().getProviderId());

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		PatientRequest patientRequest = WS_CLIENT_FACTORY
				.createPatientRequest();
		patientRequest.setPatientId(patientId);

		PrescriptionHistoryRequest prescriptionHistoryRequest = WS_CLIENT_FACTORY
				.createPrescriptionHistoryRequest();
		prescriptionHistoryRequest.setStartHistory(startDate);
		prescriptionHistoryRequest.setEndHistory(endDate);
		prescriptionHistoryRequest.setPrescriptionStatus("C");
		prescriptionHistoryRequest.setPrescriptionArchiveStatus(prescriptionArchiveStatus);
		prescriptionHistoryRequest.setPrescriptionSubStatus("S");

		PatientInformationRequester patientInformationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		patientInformationRequester.setUserId(userId);
		patientInformationRequester.setUserType("%");

		WebServiceClientImpl<GetPatientFullMedicationHistory6> wsClientForFullMed6 = new WebServiceClientImpl<GetPatientFullMedicationHistory6>();
		GetPatientFullMedicationHistory6 pFullMedStatus6 = WS_CLIENT_FACTORY
				.createGetPatientFullMedicationHistory6();
		pFullMedStatus6.setAccountRequest(accountRequest);
		pFullMedStatus6.setCredentials(credentials);
		pFullMedStatus6.setIncludeSchema("N");
		pFullMedStatus6.setPatientIdType("%");
		pFullMedStatus6
				.setPatientInformationRequester(patientInformationRequester);
		pFullMedStatus6.setPatientRequest(patientRequest);
		pFullMedStatus6
				.setPrescriptionHistoryRequest(prescriptionHistoryRequest);

		GetPatientFullMedicationHistory6Response pFullMed6Response = (GetPatientFullMedicationHistory6Response)

		wsClientForFullMed6.sendRequestAndReceiveResponse(pFullMedStatus6,
				Update1ServiceDetail);
		
		
		if(pFullMed6Response.getGetPatientFullMedicationHistory6Result().getStatus().value().equals("OK")){
			logger.info("inside if  method start::::::"+new Timestamp(new Date().getTime()));
			 xmlResponse = pFullMed6Response
						.getGetPatientFullMedicationHistory6Result().getXmlResponse();
			 
			 logger.info("inside if  method end::::::"+new Timestamp(new Date().getTime()));
			 
		}
		else if (!(pFullMed6Response.getGetPatientFullMedicationHistory6Result().getStatus().value().equals("OK"))) {
			logger.info("inside else  method start::::::"+new Timestamp(new Date().getTime()));
			    getAuditInfo().setComment(pFullMed6Response.getGetPatientFullMedicationHistory6Result().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("FullMedicationHistoryV6 WSDL");
				getAuditInfo().setCreateTime(new Date());
				userService.saveWSFailStatus(auditInfo);
				logger.info("inside else  method start::::::"+new Timestamp(new Date().getTime()));
		}
		}
		catch(SocketException se){
			se.printStackTrace();
			logger.info("SocketException in callFullMedV6");
			
		}
		
		
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		
		long stopTime=System.currentTimeMillis();
		long takenTime=stopTime-startTime;
		System.out.println("Total Time taken in full med result :::"+takenTime);
		logger.info("Total Time taken in full med result :::"+takenTime);
		return xmlResponse;

	}

	/**
	 * 
	 * Convert FullMedicine V6 XML Response Using DOM Parser
	 * @param endDate 
	 * @param startDate 
	 * @param prescriptionArchiveStatus 
	 * @param providerLocation 
	 * 
	 * @throws Exception
	 */

	public List<PatientFullMedHistoryV6> getPatientFullMedHistoryV6UsingDomParser(String prescriptionArchiveStatus, Date startDate, Date endDate, ProviderLocation providerLocation) throws Exception {
		logger.info("Conversion full medication started now ........."+new Timestamp(new Date().getTime()));
		
		List<PatientFullMedHistoryV6> patientFullMedHistoryV6List=new ArrayList<PatientFullMedHistoryV6>();
		String fullMedHistoryV6 = decodePatMedFullHistoryV6(providerLocation,prescriptionArchiveStatus,startDate,endDate);
		long startTime=System.currentTimeMillis();
		System.out.println("Full med history V6 string format "+fullMedHistoryV6);
		logger.info("new Timestamp(new Date().getTime()));::::"+new Timestamp(new Date().getTime()));
		InputSource inputSource = null;
		if(!(fullMedHistoryV6.equals(""))){
		 inputSource = new InputSource(new StringReader(
				fullMedHistoryV6));
		
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		org.w3c.dom.Document document = documentBuilder.parse(inputSource);

		Element docElement = document.getDocumentElement();
		NodeList node = docElement.getChildNodes();

		

		
		
		if (node != null && node.getLength() > 0 && !(fullMedHistoryV6==null)) {
			for (int i = 0; i < node.getLength(); i++) {
				PatientFullMedHistoryV6 patientFullMedHistoryV6=new PatientFullMedHistoryV6();
				String drugName = "";
				String drugId="";
				String dispense = "";
				String drugInfo="";
				String dateMovedToPreviousMedications="";
				String physicianName="";
				//String diagnosisSource="";
				String externalPhysicianID="";
				
				String prescriptionDate="";
				String pharmacyFullInfo="";
				
				String pharmacyNCPDP="";

				String refillls="";
				//String refills="";
				String route="";
				String strengths="";
				//String strength="";
				String strengthsUOM ="";
				//String strengthUOM="";
				String drugType="";
				String patientId="";
				String prescriptionNotes="";
				//String prescripitionGUID="";
						

				String encounterIdentifier="";
				String episodeIdentifier="";
				
				String prescriptionGuid="";
				String externalPrescriptionID="";
				String externalSource="";
				//String prescriptionNotes="";
				String orderGUID="";
				
				String patientFriendlySIG="";
				
				String subStatus="";
				String takeAsNeeded="";
				String fullName="";
				String pharmacyType="";
				String pharmacyDetailType="";
				String accountName="";
				String archiveStatus="";
				String dispenseAsWritten="";
				String dosageForm="";
				String dosageNumberDescription="";
				String dosageFrequencyDescription="";
				String externalAccountID="";
				String externalPatientID="";
				
				String siteID="";
				String diagnosis="";
				String diagnosisSource="";
				//String status="";
				String prescriptionStatus="";
				String diagnosisName="";
				String PrescriptionTimestamp="";
				String daysSupply="";

				if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element e1 = (Element) node.item(i);

					if (e1.getNodeName().contains("Table")) {
						/*drugName = e1.getElementsByTagName("DosageForm")
								.item(0).getFirstChild().getNodeValue();*/
						// dispense = e1.getElementsByTagName("Dispense").item(0)
							//	 .getFirstChild().getNodeValue();
						
						// externalPhysicianID=getXMLTagValue(e1,"ExternalPhysicianID");
						/*System.out.println("1"+ e1.getElementsByTagName("ExternalPhysicianID")
								.item(0).getFirstChild().getNodeValue());*/
						
						 accountName=e1.getElementsByTagName("AccountName").item(0).getFirstChild().getNodeValue();
						 archiveStatus=e1.getElementsByTagName("Archive").item(0).getFirstChild().getNodeValue();
						 dispenseAsWritten=e1.getElementsByTagName("DispenseAsWritten").item(0).getFirstChild().getNodeValue();
						 dosageForm=e1.getElementsByTagName("DosageForm").item(0).getFirstChild().getNodeValue();
						 dosageNumberDescription=e1.getElementsByTagName("DosageNumberDescription").item(0).getFirstChild().getNodeValue();
						 dosageFrequencyDescription=e1.getElementsByTagName("DosageFrequencyDescription").item(0).getFirstChild().getNodeValue();

						//String encounterIdentifier="";
						/*if(!(e1.getElementsByTagName("EncounterIdentifier").item(0).getFirstChild()==null)){
							encounterIdentifier=e1.getElementsByTagName("EncounterIdentifier").item(0).getFirstChild().getNodeValue();
						}*/
						//String episodeIdentifier="";
						/*if(!(e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild()==null)){
							episodeIdentifier=e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild().getNodeValue();
						}*/
						
						dateMovedToPreviousMedications=e1.getElementsByTagName("DateMovedToPreviousMedications").item(0).getFirstChild().getNodeValue();

						if(!(e1.getElementsByTagName("EncounterIdentifier").item(0).getFirstChild()==null)){
							encounterIdentifier=e1.getElementsByTagName("EncounterIdentifier").item(0).getFirstChild().getNodeValue();
						}
						
						if(!(e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild()==null)){
							 episodeIdentifier=e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild().getNodeValue();
						}
						if(!(e1.getElementsByTagName("DateMovedToPreviousMedications").item(0).getFirstChild()==null)){
							 dateMovedToPreviousMedications=e1.getElementsByTagName("DateMovedToPreviousMedications").item(0).getFirstChild().getNodeValue();
						}
					   

						 externalAccountID=e1.getElementsByTagName("ExternalAccountID").item(0).getFirstChild().getNodeValue();
						 externalPatientID=e1.getElementsByTagName("ExternalAccountID").item(0).getFirstChild().getNodeValue();
						if(!(e1.getElementsByTagName("ExternalPhysicianID").item(0).getFirstChild()==null)){
						externalPhysicianID=e1.getElementsByTagName("ExternalPhysicianID").item(0).getFirstChild().getNodeValue();
						}
						if(!(e1.getElementsByTagName("PrescriptionGuid").item(0).getFirstChild()==null)){
							prescriptionGuid=e1.getElementsByTagName("PrescriptionGuid").item(0).getFirstChild().getNodeValue();
						}
						if(!(e1.getElementsByTagName("ExternalPrescriptionID").item(0).getFirstChild()==null)){
							externalPrescriptionID=e1.getElementsByTagName("ExternalPrescriptionID").item(0).getFirstChild().getNodeValue();
						}
						
						if(!(e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild()==null)){
							episodeIdentifier=e1.getElementsByTagName("EpisodeIdentifier").item(0).getFirstChild().getNodeValue();
						}
						 if(!(e1.getElementsByTagName("OrderGUID").item(0).getFirstChild()==null)){
							  orderGUID=e1.getElementsByTagName("OrderGUID").item(0).getFirstChild().getNodeValue();
						 }
					  
					    if(!(e1.getElementsByTagName("PrescriptionNotes").item(0).getFirstChild()==null)){
					    	 prescriptionNotes=e1.getElementsByTagName("PrescriptionNotes").item(0).getFirstChild().getNodeValue();
					    }
						 //refills=e1.getElementsByTagName("Refills").item(0).getFirstChild().getNodeValue();
						 route=e1.getElementsByTagName("Route").item(0).getFirstChild().getNodeValue();
						 siteID=e1.getElementsByTagName("SiteID").item(0).getFirstChild().getNodeValue();
						 prescriptionStatus=e1.getElementsByTagName("Status").item(0).getFirstChild().getNodeValue();
						/*if(!(e1.getElementsByTagName("ExternalSource").item(0)==null)){
							externalSource=e1.getElementsByTagName("ExternalSource").item(0).getFirstChild().getNodeValue();
						}*/
						
						/*if(!(e1.getElementsByTagName("Strength").item(0)==null)){
							 strength=e1.getElementsByTagName("Strength").item(0).getFirstChild().getNodeValue();
						}
						if(!(e1.getElementsByTagName("StrengthUOM").item(0)==null)){
							 strengthUOM=e1.getElementsByTagName("StrengthUOM").item(0).getFirstChild().getNodeValue();
						}*/
						if(!(e1.getElementsByTagName("SubStatus").item(0).getFirstChild()==null)){
							subStatus=e1.getElementsByTagName("SubStatus").item(0).getFirstChild().getNodeValue();
						}
						 if(!(e1.getElementsByTagName("TakeAsNeeded").item(0).getFirstChild()==null)){
							 takeAsNeeded=e1.getElementsByTagName("TakeAsNeeded").item(0).getFirstChild().getNodeValue();
						 }
						 physicianName =e1.getElementsByTagName("PhysicianName").item(0).getFirstChild().getNodeValue();
						 /*if(e1.getElementsByTagName("PhysicianName").item(0).getFirstChild()==null){
							 physicianName =e1.getElementsByTagName("PhysicianName").item(0).getFirstChild().getNodeValue();
						 }*/
						 if(!(e1.getElementsByTagName("DrugID").item(0).getFirstChild()==null)){
							 drugId=e1.getElementsByTagName("DrugID").item(0).getFirstChild().getNodeValue();
						 }
						  if(!(e1.getElementsByTagName("PrescriptionDate").item(0).getFirstChild()==null)){
							  prescriptionDate=e1.getElementsByTagName("PrescriptionDate").item(0).getFirstChild().getNodeValue(); 
						  }
						if(!(e1.getElementsByTagName("DrugInfo").item(0).getFirstChild()==null)){
							drugInfo=e1.getElementsByTagName("DrugInfo").item(0).getFirstChild().getNodeValue();
						}
						
						if(!(e1.getElementsByTagName("PharmacyFullInfo").item(0).getFirstChild()==null)){
							//pharmacyFullInfo=getXMLTagValue(e1,"PharmacyFullInfo");
							System.out.println(":::<><><><><><<><>:::::::");
							pharmacyFullInfo=e1.getElementsByTagName("PharmacyFullInfo").item(0).getFirstChild().getNodeValue();
						}
						if(!(e1.getElementsByTagName("FullName").item(0).getFirstChild()==null)){
						  fullName=e1.getElementsByTagName("FullName").item(0).getFirstChild().getNodeValue();
						}
					    if(!(e1.getElementsByTagName("DrugName").item(0).getFirstChild()==null)){
					    	 drugName=e1.getElementsByTagName("DrugName").item(0).getFirstChild().getNodeValue();
					    }
						

						 //fullName=e1.getElementsByTagName("FullName").item(0).getFirstChild().getNodeValue();
						 //drugName=e1.getElementsByTagName("DrugName").item(0).getFirstChild().getNodeValue();
						 patientId=e1.getElementsByTagName("PatientID").item(0).getFirstChild().getNodeValue();
						// String patientFriendlySIG="";
						 if(!(e1.getElementsByTagName("PatientFriendlySIG").item(0).getFirstChild()==null)){
							  patientFriendlySIG=e1.getElementsByTagName("PatientFriendlySIG").item(0).getFirstChild().getNodeValue();
						 }

						 if(!(e1.getElementsByTagName("PatientFriendlySIG").item(0).getFirstChild()==null)){
							 patientFriendlySIG=e1.getElementsByTagName("PatientFriendlySIG").item(0).getFirstChild().getNodeValue();
						 }

						 
						 if(!(e1.getElementsByTagName("PharmacyType").item(0).getFirstChild()==null)){
							 pharmacyType=e1.getElementsByTagName("PharmacyType").item(0).getFirstChild().getNodeValue();
						 }
						if(!(e1.getElementsByTagName("PharmacyType").item(0).getFirstChild()==null)){
							pharmacyDetailType=e1.getElementsByTagName("PharmacyType").item(0).getFirstChild().getNodeValue();
						}
						 
						 if(!(e1.getElementsByTagName("PharmacyNCPDP").item(0).getFirstChild()==null)){
							 pharmacyNCPDP =e1.getElementsByTagName("PharmacyNCPDP").item(0).getFirstChild().getNodeValue();
						 }
						 
						 if(e1.getElementsByTagName("DrugTypeID").item(0).getFirstChild().getNodeValue()!=null)
							 	drugType=e1.getElementsByTagName("DrugTypeID").item(0).getFirstChild().getNodeValue();
						 try{
							 System.out.println(e1.getElementsByTagName("Strength").getLength()+"::::::getElementsByTagNamegetElementsByTagName::::::::::::::::::::::::"+e1.getAttribute("Strength"));
						 if(e1.getElementsByTagName("Strength").getLength()>=1)
								 strengths=e1.getElementsByTagName("Strength").item(0).getFirstChild().getNodeValue();
						 }catch(NullPointerException e)
						 {
							 e.printStackTrace();
						 }
						 try{
							 if(e1.getElementsByTagName("StrengthUOM").getLength()>=1)
							 strengthsUOM=e1.getElementsByTagName("StrengthUOM").item(0).getFirstChild().getNodeValue();
						 } catch(NullPointerException e)
						 {
							 e.printStackTrace();
						 }
						 try{
						 if(e1.getElementsByTagName("Route").item(0).getFirstChild().getNodeValue()!=null)
							 route=e1.getElementsByTagName("Route").item(0).getFirstChild().getNodeValue();
						 }catch(NullPointerException e)
						 {
							 e.printStackTrace();
						 }
						 
						 try{
							 if(!(e1.getElementsByTagName("Dispense").item(0).getFirstChild()==null))
						
								 dispense=e1.getElementsByTagName("Dispense").item(0).getFirstChild().getNodeValue();
						 }catch(NullPointerException e)
						 {
							 e.printStackTrace();
						 }
						 
						 try{
						 if(e1.getElementsByTagName("Refills").item(0).getFirstChild().getNodeValue()!=null)
							 refillls=e1.getElementsByTagName("Refills").item(0).getFirstChild().getNodeValue();
						 }catch(NullPointerException e)
						 {
							 e.printStackTrace();
						 }
						 
						 
						 
						 try{
							 if(e1.getElementsByTagName("PrescriptionNotes").item(0).getFirstChild()!=null)
								 prescriptionNotes=e1.getElementsByTagName("PrescriptionNotes").item(0).getFirstChild().getNodeValue();
							 }catch(NullPointerException e)
							 {
								 e.printStackTrace();
							 }
						/* if(e1.getElementsByTagName("PrescriptionGuid").item(0).getFirstChild().getNodeValue()!=null)
							 prescripitionGUID=e1.getElementsByTagName("PrescriptionGuid").item(0).getFirstChild().getNodeValue();
						 
						 */
						 if(e1.getElementsByTagName("Diagnosis").item(0).getFirstChild()!=null){
							 diagnosis=e1.getElementsByTagName("Diagnosis").item(0).getFirstChild().getNodeValue();
							 
						 }
						 
						 
						 if(e1.getElementsByTagName("DiagnosisSource").item(0).getFirstChild()!=null){
							 diagnosisSource=e1.getElementsByTagName("DiagnosisSource").item(0).getFirstChild().getNodeValue();
						 }
						 if(e1.getElementsByTagName("DiagnosisName").item(0).getFirstChild()!=null){
							 diagnosisName=e1.getElementsByTagName("DiagnosisName").item(0).getFirstChild().getNodeValue();
							
						 }
						 
						 daysSupply=e1.getElementsByTagName("DaysSupply").item(0).getFirstChild().getNodeValue();
						 
						 PrescriptionTimestamp=e1.getElementsByTagName("PrescriptionTimestamp").item(0).getFirstChild().getNodeValue();
						 
						patientFullMedHistoryV6.setFullName(fullName);
						patientFullMedHistoryV6.setDrugID(drugId);
						patientFullMedHistoryV6.setDrugName(drugName);
						patientFullMedHistoryV6.setDrugInfo(drugInfo);
						patientFullMedHistoryV6.setPhysicianName(physicianName);
						patientFullMedHistoryV6.setPrescriptionDate(prescriptionDate);
						patientFullMedHistoryV6.setPatientFriendlySIG(patientFriendlySIG);
						patientFullMedHistoryV6.setPharmacyType(pharmacyType);
						patientFullMedHistoryV6.setPharmacyDetailType(pharmacyDetailType);
						patientFullMedHistoryV6.setPharmacyNCPDP(pharmacyNCPDP);
						patientFullMedHistoryV6.setPharmacyFullInfo(pharmacyFullInfo);
						patientFullMedHistoryV6.setAccountName(accountName);
						patientFullMedHistoryV6.setArchiveStatus(archiveStatus);
						patientFullMedHistoryV6.setDispenseAsWritten(dispenseAsWritten);
						patientFullMedHistoryV6.setDosageForm(dosageForm);
						patientFullMedHistoryV6.setDosageNumberDescription(dosageNumberDescription);
						patientFullMedHistoryV6.setDosageFrequencyDescription(dosageFrequencyDescription);
						patientFullMedHistoryV6.setEncounterIdentifier(encounterIdentifier);
						patientFullMedHistoryV6.setEpisodeIdentifier(episodeIdentifier);
						patientFullMedHistoryV6.setExternalAccountID(externalAccountID);
						patientFullMedHistoryV6.setExternalPatientID(externalPatientID);
						patientFullMedHistoryV6.setExternalPhysicianID(externalPhysicianID);
						patientFullMedHistoryV6.setDateMovedToPreviousMedications(dateMovedToPreviousMedications);
						patientFullMedHistoryV6.setPrescriptionGuid(prescriptionGuid);
						patientFullMedHistoryV6.setExternalPrescriptionID(externalPrescriptionID);
						patientFullMedHistoryV6.setExternalSource(externalSource);
						patientFullMedHistoryV6.setOrderGUID(orderGUID);
						patientFullMedHistoryV6.setPrescriptionNotes(prescriptionNotes);
						patientFullMedHistoryV6.setRoute(route);
						//patientFullMedHistoryV6.setRefills(refills);
						patientFullMedHistoryV6.setSiteID(siteID);
						//patientFullMedHistoryV6.setStrength(strength);
						//patientFullMedHistoryV6.setStrengthUOM(strengthUOM);
						patientFullMedHistoryV6.setTakeAsNeeded(takeAsNeeded);
						patientFullMedHistoryV6.setPrescriptionStatus(prescriptionStatus);
						patientFullMedHistoryV6.setSubStatus(subStatus);
						

						//patientFullMedHistoryV6.setPatientFriendlySIG(PrescriptionGuid);
						
						patientFullMedHistoryV6.setDrugTypeID(drugType);
						patientFullMedHistoryV6.setStrength(strengths);
						patientFullMedHistoryV6.setStrengthUOM(strengthsUOM);
						patientFullMedHistoryV6.setRoute(route);
						patientFullMedHistoryV6.setDispense(dispense);
						
						patientFullMedHistoryV6.setRefills(refillls);
						patientFullMedHistoryV6.setPrescriptionGuid(prescriptionGuid);
						patientFullMedHistoryV6.setPatientID(patientId);
						patientFullMedHistoryV6.setPrescriptionNotes(prescriptionNotes);
						
						patientFullMedHistoryV6.setDiagnosis(diagnosis);
						patientFullMedHistoryV6.setDiagnosisSource(diagnosisSource);
						patientFullMedHistoryV6.setDiagnosisName(diagnosisName);
						patientFullMedHistoryV6.setPrescriptionTimestamp(PrescriptionTimestamp);
						patientFullMedHistoryV6.setDaysSupply(daysSupply);
						
						System.out.println(archiveStatus+"::::::patientFullMedHistoryV6.getDiagnosis():::::::::::::><><"+patientFullMedHistoryV6.getDiagnosis()+"patientFullMedHistoryV6.getDiagnosisSource()"+patientFullMedHistoryV6.getDiagnosisSource());

					}
					
					patientFullMedHistoryV6List.add(patientFullMedHistoryV6);
					
					
				}
				
			}
		}
			
		}
		long stopTime=System.currentTimeMillis();
		long elapsedTime=stopTime-startTime;
		System.out.println("Total time taken in sax Parser "+elapsedTime);
		logger.info("Total time taken in sax Parser "+elapsedTime);
		return patientFullMedHistoryV6List;
	}

	/**
	 * Conversion of String response of soap file in UTF format
	 * @param providerLocation 
	 * @param endDate 
	 * @param startDate 
	 * @param prescriptionArchiveStatus 
	 * 
	 * @throws Exception
	 */
	public String decodePatMedFullHistoryV6(ProviderLocation providerLocation, String prescriptionArchiveStatus, Date startDate, Date endDate) throws Exception {
		
		logger.info("decodePatMedFullHistoryV6 Method :::::::");
		logger.info("decodePatMedFullHistoryV6 method start::::::"+new Timestamp(new Date().getTime()));
		
		String stringResponse = callFullMedicationHistoryV6(providerLocation,prescriptionArchiveStatus,startDate,endDate);
		long startTime=System.currentTimeMillis();
		Base64 decoder = new Base64();
		byte[] decodedByte = null;
		if(!(stringResponse.equals(""))){
			decodedByte = (byte[]) decoder.decode(stringResponse.getBytes());
		}

		System.out.println("----------------------------------");
		if(decodedByte==null)
			return new String("");
		
			long stopTime=System.currentTimeMillis();
		    long elapsedTime=stopTime-startTime;
		    
			System.out.println("Total time taken in decoding "+elapsedTime);
			logger.info("Total time taken in decoding "+elapsedTime);
		return new String(decodedByte);

	}

	/**
	 * Call getPbmEligblity V3 For trascation Id
	 * @param providerLocation 
	 * @param roleSecurity 
	 * 
	 * @throws Exception
	 */
	public EligibilityDetailResultV3 callGetPBMEligibilityV3(ProviderLocation providerLocation, RoleSecurity roleSecurity) throws Exception {

		logger.info("::callGetPBMEligibilityV3 method check by gopal::::");
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String userId = Integer.toString(new ContextUtil().getPatientId());
		String userType = getProperties().getProperty("userType");
		EligibilityDetailResultV3 elDetailResultV3=new EligibilityDetailResultV3();
		String patientXmlBase64EncodedData = convertPatientDetailStringInBase64Format(providerLocation,roleSecurity);
		if(!patientXmlBase64EncodedData.equals("")){	
			
		
		String xmlIn = "BASE64:"+patientXmlBase64EncodedData;
		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);

		GetPBMEligibilityV3 pbmEligibilityV3 = WS_CLIENT_FACTORY
				.createGetPBMEligibilityV3();
		pbmEligibilityV3.setAccountRequest(accountRequest);
		pbmEligibilityV3.setCredentials(credentials);
		pbmEligibilityV3.setIncludeSchema("Y");
		pbmEligibilityV3.setPatientInformationRequester(informationRequester);
		pbmEligibilityV3.setXmlIn(xmlIn);

		WebServiceClientImpl<GetPBMEligibilityV3> wsClientForPbmEligblityV3 = new WebServiceClientImpl<GetPBMEligibilityV3>();
		GetPBMEligibilityV3Response pbmEligibilityV3Response = (GetPBMEligibilityV3Response) wsClientForPbmEligblityV3
				.sendRequestAndReceiveResponse
				(pbmEligibilityV3, Update1ServiceDetail);
		
		if(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")){
			elDetailResultV3 = pbmEligibilityV3Response
					.getGetPBMEligibilityV3Result();
		}
		else if (pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")) {
			getAuditInfo().setComment(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("GetPBMEligibilityV3 WSDL");
			getAuditInfo().setCreateTime(new Date());
		    elDetailResultV3=new EligibilityDetailResultV3();
			userService.saveWSFailStatus(auditInfo);
		}
		}
        System.out.println("elDetailResultV3 :::::::::::  "+elDetailResultV3.getEligibilityGuid());
		/*
		 * System.out.println("----------------------------------------");
		 * System
		 * .out.println("Transcation Guid for eligiblity "+elDetailResultV3
		 * .getEligibilityGuid()+elDetailResultV3.getEligibilityDetailArray
		 * 
		 * ().getEligibilityDetailV3());
		 */
		return elDetailResultV3;
	}

	/**
	 * Call GetPBMDrugHistoryV2 Soap for PBM Drug History
	 * @param timeperiod 
	 * @param providerLocation 
	 * @param roleSecurity 
	 * 
	 * @throws Exception
	 * 
	 */
	public String callGetPBMDrugHistoryV2(int timeperiod, ProviderLocation providerLocation, RoleSecurity roleSecurity)  {
		
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String xmlResponse ="";
		 try{
		
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
	
       
		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		EligibilityDetailResultV3 eligibilityDetailResultV3 = callGetPBMEligibilityV3(providerLocation,roleSecurity);
		String eligibilityTransactionId = eligibilityDetailResultV3
				.getEligibilityGuid();

		GetPBMDrugHistoryV2 pbmDrugHistoryV2Request = WS_CLIENT_FACTORY
				.createGetPBMDrugHistoryV2();
		pbmDrugHistoryV2Request.setAccountRequest(accountRequest);
		pbmDrugHistoryV2Request.setCredentials(credentials);
		pbmDrugHistoryV2Request
				.setEligibilityTransactionId(eligibilityTransactionId);
		pbmDrugHistoryV2Request.setIncludeSchema("N");
		pbmDrugHistoryV2Request.setMonthsPrior(timeperiod);

		WebServiceClientImpl<GetPBMDrugHistoryV2> wsClientForPBMDrugHistoryV2 = new WebServiceClientImpl<GetPBMDrugHistoryV2>();
		GetPBMDrugHistoryV2Response pbmDrugHistoryV2Response = (GetPBMDrugHistoryV2Response) wsClientForPBMDrugHistoryV2
				.sendRequestAndReceiveResponse

				(pbmDrugHistoryV2Request, Update1ServiceDetail);
		
		
		
		if(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().value().equals("OK")){
			
			DrugHistoryDetailResult drugHistoryDetailResult = pbmDrugHistoryV2Response
					.getGetPBMDrugHistoryV2Result();
			xmlResponse= drugHistoryDetailResult.getResult()
					.getXmlResponse();
		}
		else if (!(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().equals("OK"))) {
			getAuditInfo().setComment(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("GetPBMDrugHistoryV2");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
		}
        }
        catch(SocketException se){
        	se.printStackTrace();
        }
        catch (NullPointerException ne) {
			// TODO: handle exception
        	ne.printStackTrace();
        	
		}
        catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
		}
		

		
		return xmlResponse;

	}

	/**
	 * Decode XML Resonse Result got by PBMDrugHistory
	 * @param timeperiod 
	 * @param providerLocation 
	 * @param roleSecurity 
	 * 
	 * @throws Exception
	 */
	public String decodeBase64XmlResonseOfPBMDrugHistory(int timeperiod, ProviderLocation providerLocation, RoleSecurity roleSecurity) throws Exception {
		String encodedXmlResponse = callGetPBMDrugHistoryV2(timeperiod,providerLocation,roleSecurity);
		String decodedResult="";
		if(!encodedXmlResponse.equals("")){
		Base64 decoder = new Base64();
		byte[] decodedByte = (byte[]) decoder.decode(encodedXmlResponse
				.getBytes());
		 decodedResult = new String(decodedByte);
		}
		return decodedResult;

	}

	/**
	 * Convert PBMDrugHistory xml format using sax parser
	 * @param timeperiod 
	 * @param providerLocation 
	 * @param roleSecurity 
	 * @throws Exception
	 */
	public List<DrugHistoryDetail> convertPBMDrugHistoryXMLToSaxParser(int timeperiod, ProviderLocation providerLocation, RoleSecurity roleSecurity)
			throws Exception {
		System.out.println("DOM Pareser Started");
		DefaultHandler defaultHandler = new DefaultHandler();
		String pbmHistory = decodeBase64XmlResonseOfPBMDrugHistory(timeperiod,providerLocation,roleSecurity);

		/*
		 * String
		 * pbmHistoryXml="<?xml version="+"1.0"+" encoding="+"UTF-8"+"?>"+
		 * pbmHistory; System.out.println(pbmHistoryXml);
		 */

		// Using Sax Parser

		/*
		 * MyHandler myHandler=new MyHandler(); File file=new File(
		 * "D:/ClnickosV0.4_8Oct/clinakos/src/main/java/com/clinakos/viewController/webservicMangedBean/DrugHistoryDetail.xml"
		 * ); InputStream inputStream=new FileInputStream(file); Reader
		 * reader=new InputStreamReader(inputStream); InputSource is=new
		 * InputSource(reader); is.setEncoding("UTF-8");
		 * 
		 * SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
		 * SAXParser saxParser=saxParserFactory.newSAXParser();
		 * 
		 * saxParser.parse(new StringReader(pbmHistory), myHandler);
		 * System.out.println("Drug Detail History Started ");
		 * List<DrugHistoryDetail>
		 * drugHistoryDetailList=myHandler.getDrugHistoryDetailList();
		 * for(DrugHistoryDetail detail:drugHistoryDetailList){
		 * System.out.println
		 * ("Doctor First Name"+detail.getDoctorFirstName()+"Doctor Last name"
		 * +detail.getDoctorLastName()+"  Drug Info "+detail.getDrugInfo()); }
		 */

		/***
		 * Using Dom Parser
		 */

		List<DrugHistoryDetail> drugHistoryDetails = new ArrayList<DrugHistoryDetail>();

		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

		if (!(pbmHistory.equals(""))) {

			InputSource inputSource = new InputSource(new StringReader(
					pbmHistory));
			org.w3c.dom.Document document = documentBuilder.parse(inputSource);

			Element docElement = document.getDocumentElement();
			NodeList node = docElement.getChildNodes();
			System.out.println("Total nodes in xml File" + node.getLength());
			String doctorFirstName = "";
			String doctorLastName = "";
			String drugInfo = "";
			String drugQuantity = "";
			String date="";
			String ndc = "";
			String drugId="";
			String source="";
			
			if (node != null && node.getLength() > 0) {
				for (int i = 0; i < node.getLength(); i++) {
					DrugHistoryDetail drDetail = new DrugHistoryDetail();

					if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
						Element e1 = (Element) node.item(i);
						System.out.println("Eleement "
								+ e1.getElementsByTagName("DrugQuantity")
										.item(0).getLocalName());
					
						if (e1.getNodeName().contains("Table")) {

							System.out.println(e1.getNodeName()+" child:::"+e1.getChildNodes()
									+ " Node value child value length "
									+ e1.getChildNodes().getLength());
							try{
								
							if(!(e1.getElementsByTagName("DrugInfo").item(0).getFirstChild()==null)){
								drugInfo = e1.getElementsByTagName("DrugInfo")
										.item(0).getFirstChild().getNodeValue();
							}
							if(!(e1.getElementsByTagName("DoctorFirstName").item(0).getFirstChild()==null)){
								doctorFirstName = e1
										.getElementsByTagName("DoctorFirstName")
										.item(0).getFirstChild().getNodeValue();
							}
							if(!(e1.getElementsByTagName("DoctorLastName").item(0).getFirstChild()==null)){
								doctorLastName = e1
										.getElementsByTagName("DoctorLastName")
										.item(0).getFirstChild().getNodeValue();
							}
							
							if(!(e1.getElementsByTagName("DrugQuantity").item(0).getFirstChild()==null)){
								drugQuantity = e1
										.getElementsByTagName("DrugQuantity")
										.item(0).getFirstChild().getNodeValue();
							}
							if(!(e1.getElementsByTagName("ScriptDate").item(0).getFirstChild()==null)){
								date=e1.getElementsByTagName("ScriptDate").item(0).getFirstChild().getNodeValue();
							}
							
							if(!(e1.getElementsByTagName("NDC").item(0).getFirstChild()==null)){
								ndc = e1.getElementsByTagName("NDC").item(0)
										.getFirstChild().getNodeValue();
							}
							if(!(e1.getElementsByTagName("Source").item(0).getFirstChild()==null)){
								source=e1.getElementsByTagName("Source").item(0)
										.getFirstChild().getNodeValue();
							}
							
							//if(!(e1.getElementsByTagName("DrugID").item(0).getFirstChild()==null)){
								drugId=e1.getElementsByTagName("DrugID").item(0).getFirstChild().getNodeValue();
						//	}
							
							
								
							
						
						
						drDetail.setDoctorFirstName(doctorFirstName);
						drDetail.setDoctorLastName(doctorLastName);
						drDetail.setDrugInfo(drugInfo);
						drDetail.setDrugQuantity(drugQuantity);
						drDetail.setFillDate(date);
						drDetail.setDrugNdc(ndc);
						drDetail.setDrugId(drugId);
						drDetail.setSource(source);
						System.out.println("Second if"
								+ drDetail.getDoctorFirstName()
								+ "   drug id "
								+ drDetail.getDrugId() +"source:::"+drDetail.getSource()+ " Drug Info "
								+ drDetail.getDrugInfo() +" fill date::"+drDetail.getFillDate());
						drugHistoryDetails.add(drDetail);
						
							}catch (NullPointerException e) {
									e.printStackTrace();
								}

							}
					}

				}

			}
			

		}
		if (drugHistoryDetails.size() == 0) {
			drugHistoryDetails = new ArrayList<DrugHistoryDetail>();
		}

		
		return drugHistoryDetails;
	}

	/**
	 * @return the drugHistoryDetail
	 */
	public DrugHistoryDetail getDrugHistoryDetail() {
		if (drugHistoryDetail == null) {
			drugHistoryDetail = new DrugHistoryDetail();
		}
		return drugHistoryDetail;
	}

	/**
	 * @param drugHistoryDetail
	 *            the drugHistoryDetail to set
	 */
	public void setDrugHistoryDetail(DrugHistoryDetail drugHistoryDetail) {
		this.drugHistoryDetail = drugHistoryDetail;
	}

	/**
	 * @param providerLocation 
	 * @param roleSecurity 
	 * @return the drugHistoryDetailList
	 * @throws Exception
	 */
	public List<DrugHistoryDetail> getDrugHistoryDetailList(ProviderLocation providerLocation, RoleSecurity roleSecurity) throws Exception {
		if (drugHistoryDetailList == null) {
			drugHistoryDetailList = new ArrayList<DrugHistoryDetail>();
			int timePeriod=3;
			drugHistoryDetailList = convertPBMDrugHistoryXMLToSaxParser(timePeriod,providerLocation,roleSecurity);
			
			}
		return drugHistoryDetailList;
		}
		

	/**
	 * @param drugHistoryDetailList
	 *            the drugHistoryDetailList to set
	 */
	public void setDrugHistoryDetailList(
			List<DrugHistoryDetail> drugHistoryDetailList) {
		this.drugHistoryDetailList = drugHistoryDetailList;
	}

	/**
	 * @return the properties
	 * @throws IOException
	 */
	public Properties getProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
		}
		properties.load(NcUpdate1WSBean.class.getClassLoader()
				.getResourceAsStream(
						"/com/clinakos/properties/webservices.properties"));
		return properties;
	}

	/**
	 * @param properties
	 *            the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * @return the medicineForFormularySearch
	 */
	public String getMedicineForFormularySearch() {
		return medicineForFormularySearch;
	}

	/**
	 * @param medicineForFormularySearch
	 *            the medicineForFormularySearch to set
	 */
	public void setMedicineForFormularySearch(String medicineForFormularySearch) {
		this.medicineForFormularySearch = medicineForFormularySearch;
	}

	/**
	 * @return the insuranceCompanies
	 */
	public InsuranceCompanies getInsuranceCompanies() {
		if (insuranceCompanies == null) {
			insuranceCompanies = new InsuranceCompanies();
		}
		return insuranceCompanies;
	}

	/**
	 * @param insuranceCompanies
	 *            the insuranceCompanies to set
	 */
	public void setInsuranceCompanies(InsuranceCompanies insuranceCompanies) {
		this.insuranceCompanies = insuranceCompanies;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService
	 *            the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the showFormularyMedicineData
	 */
	public boolean isShowFormularyMedicineData() {
		return showFormularyMedicineData;
	}

	/**
	 * @param showFormularyMedicineData
	 *            the showFormularyMedicineData to set
	 */
	public void setShowFormularyMedicineData(boolean showFormularyMedicineData) {
		this.showFormularyMedicineData = showFormularyMedicineData;
	}

	/**
	 * @return the formularyDetailListForDpOtimizer
	 * @throws Exception
	 */
	public List<DrugFormularyDetail> getFormularyDetailListForDpOtimizer()
			throws Exception {
		if (formularyDetailListForFormularySearch == null) {
			formularyDetailListForFormularySearch = new ArrayList<DrugFormularyDetail>();
		}
		/*
		 * formularyDetailListForDpOtimizer=callFormularyAlternativesWithDrugInfo2
		 * ();
		 */
		return formularyDetailListForFormularySearch;
	}

	/**
	 * @param formularyDetailListForDpOtimizer
	 *            the formularyDetailListForDpOtimizer to set
	 */
	public void setFormularyDetailListForDpOtimizer(
			List<DrugFormularyDetail> formularyDetailListForFormularySearch) {
		this.formularyDetailListForFormularySearch = formularyDetailListForFormularySearch;
	}

	/**
	 * Convert Patient Detail String In Base 64 Format for PBMEligblity
	 * @param providerLocation 
	 * @param roleSecurity 
	 * 
	 */

	public String convertPatientDetailStringInBase64Format(ProviderLocation providerLocation, RoleSecurity roleSecurity) {
		String patientDetailBase64FormatData = "";
		try {
			String patientDetailXml = xmlGenrationForPatientDetailData(providerLocation,roleSecurity);
			if(!patientDetailXml.equals("")){
			byte[] encodedPatientDetail = Base64.encodeBase64(patientDetailXml
					.getBytes());
			System.out.println("Encoded Patient Detail " + encodedPatientDetail
					+ "        In String Format  "
					+ new String(encodedPatientDetail));
			byte[] decoded = Base64.decodeBase64(encodedPatientDetail);
			System.out.println("Decode value " + new String(decoded));
			patientDetailBase64FormatData = new String(encodedPatientDetail);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patientDetailBase64FormatData;
	}

	/**
	 * XML Genration For Patient Detail Data Using Document Builder
	 * @param providerLocation 
	 * @param roleSecurity 
	 * 
	 */
	public String xmlGenrationForPatientDetailData(ProviderLocation providerLocation, RoleSecurity roleSecurity) {
		String xmlResult = "";
		UserLoginDetail patDetailData = getPatientDetailData();
		DoctorDetail doctorDetail = getDoctorDetailData();
		try {
			
			
			/*String usrAccountId = providerLocation.getAccountId();
			String usrAddress1 = doctorDetail.getDoorNo();
			String usrAddress2 = doctorDetail.getStreet();
			String usrCity = doctorDetail.getCity();
			String usrState = doctorDetail.getState();
			String usrZip = doctorDetail.getPincode();;
		
			String usrCountry = doctorDetail.getCountry();
			String usrPrimaryPhoneNumber = doctorDetail.getPhoneNumber();
			String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
			String usrLocId = Integer.toString(providerLocation.getId());
			String usrLocationName = providerLocation.getLocation();

			String locAddress1 = providerLocation.getAddressLine1();
			String locAddress2 = providerLocation.getAddressLine2();
			String locCity = providerLocation.getCity();
			String locState = providerLocation.getState();
			String locZip = providerLocation.getZipCode();
			
			String locCountry = providerLocation.getCountry();
			String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

			String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
			String doctorFirstName = doctorDetail.getfName();
			String doctorLastName = doctorDetail.getlName();
			String doctorMiddleName = doctorDetail.getMidName();
			String namePrefix = "Mr.";
			String nameSuffix = "Jr";
			String prescDea = doctorDetail.getDea();
			String prescUpin = doctorDetail.getUpin();
			String prescLicenseState = doctorDetail.getDocLicenseState();
			String prescNpi = doctorDetail.getNpi();
			String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

			String patientId = Integer.toString(patDetailData.getUserId());
			String pLastName = patDetailData.getLastName();
			String pFirstName = patDetailData.getFirstName();
			String pMiddleName = patDetailData.getMiddleName();
			String patMedicalRecordNumber =Integer.toString(patDetailData.getUserId());
			String patAddress1 = patDetailData.getDoorNo()
					+ patDetailData.getStreet();
			String patCity = patDetailData.getCity();
			String patState = patDetailData.getState();
			String patZip = patDetailData.getPincode();
			String patCountry = patDetailData.getCountry();
			String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
			Date paDob = patDetailData.getDateOfBirth();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String patDOB = df.format(paDob);
			String gender = patDetailData.getGender();
			String patGender = "";
			if (gender.equalsIgnoreCase("Male")) {
				patGender = "M";
			} else {
				patGender = "F";
			}
			System.out.println("PatientId  " + patDetailData.getFirstName()
					+ patDetailData.getLastName() + "-1" + " patAddress1 "
					+ patDetailData.getDoorNo() + patDetailData.getStreet());
			System.out.println("City State Zip" + patDetailData.getCity()
					+ patDetailData.getState() + patDetailData.getPincode());*/
			
			
			String usrAccountId = providerLocation.getAccountId();
			String usrAccountName=doctorDetail.getfName()+" "+doctorDetail.getlName();
			String usrAddress1 = doctorDetail.getStreet();
			String usrAddress2 = doctorDetail.getStreet();
			String usrCity = doctorDetail.getCity();
			String usrState = doctorDetail.getState();
			String usrZip = doctorDetail.getPincode();;
			
			String usrCountry = doctorDetail.getCountry();
			String usrPrimaryPhoneNumber = providerLocation.getPrimaryPhoneNumber();
			String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
			String usrLocId = Integer.toString(providerLocation.getId());
			String usrLocationName = providerLocation.getLocation();

			String locAddress1 = providerLocation.getAddressLine1();
			String locAddress2 = providerLocation.getAddressLine2();
			String locCity = providerLocation.getCity();
			String locState = providerLocation.getState();
			String locZip = providerLocation.getZipCode();
		
			String locCountry = providerLocation.getCountry();
			String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

			String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
			String doctorFirstName = doctorDetail.getfName();
			String doctorLastName = doctorDetail.getlName();
			String doctorMiddleName = doctorDetail.getMidName();
			
			String prescDea = doctorDetail.getDea();
			String prescUpin = doctorDetail.getUpin();
			String prescLicenseState = doctorDetail.getDocLicenseState();
			String prescNpi = doctorDetail.getNpi();
			String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

			String patientId = Integer.toString(patDetailData.getUserId());
			String pLastName = patDetailData.getLastName();
			String pFirstName = patDetailData.getFirstName();
			String pMiddleName = patDetailData.getMiddleName();
			String patMedicalRecordNumber = Integer.toString(patDetailData.getUserId());
			String patAddress1 = patDetailData.getDoorNo()
					+ patDetailData.getStreet();
			String patCity = patDetailData.getCity();
			String patState = patDetailData.getState();
			String patZip = patDetailData.getPincode();
			String patCountry = patDetailData.getCountry();
			String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
			Date paDob = patDetailData.getDateOfBirth();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String patDOB = df.format(paDob);
			String gender = patDetailData.getGender().trim();
			String patGender = "";
			if (gender.equalsIgnoreCase("Male")) {
				patGender = "M";
			 }
			else if (gender.equalsIgnoreCase("Others")) {
				patGender = "U";
			}
			else {
				patGender = "F";
			}
			System.out.println("PatientId  " + patDetailData.getFirstName()
					+ patDetailData.getLastName() + "-1" + " patAddress1 "
					+ patDetailData.getDoorNo() + patDetailData.getStreet());
			System.out.println("City State Zip" + patDetailData.getCity()
					+ patDetailData.getState() + patDetailData.getPincode()+"paDob  "+paDob+"patDOB"+patDOB+"patGender"+patGender
					+"patHomeTelephone"+patHomeTelephone+"patAddress1"+patAddress1+"patDetailData.getMiddleName()"+patDetailData.getMiddleName());

			// Instance of Document and Document Builder

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();

			org.w3c.dom.Document document = documentBuilder.newDocument();

			// Root Element NcScript

			Element ncScript = document.createElement("NCScript");
			document.appendChild(ncScript);

			Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
			ncScriptXsd.setValue(getProperties().getProperty("xsd"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
			ncScriptXsi.setValue(getProperties().getProperty("xsi"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXmlns = document.createAttribute("xmlns");
			ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
			ncScript.setAttributeNode(ncScriptXmlns);
			// Credentials Element

			Element credentials = document.createElement("Credentials");
			ncScript.appendChild(credentials);
			Element partnerName = document.createElement("partnerName");
			partnerName.appendChild(document.createTextNode(getProperties()
					.getProperty("partnerName")));
			credentials.appendChild(partnerName);
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(getProperties()
					.getProperty("name")));
			credentials.appendChild(name);
			Element password = document.createElement("password");
			password.appendChild(document.createTextNode(getProperties()
					.getProperty("password")));
			credentials.appendChild(password);
			Element productName = document.createElement("productName");
			productName.appendChild(document.createTextNode(getProperties()
					.getProperty("productName")));
			credentials.appendChild(productName);
			Element productVersion = document.createElement("productVersion");
			productVersion.appendChild(document.createTextNode(getProperties()
					.getProperty("productVersion")));
			credentials.appendChild(productVersion);
			// User Role Element

			Element userRole = document.createElement("UserRole");
			ncScript.appendChild(userRole);
			Element user = document.createElement("user");
			user.appendChild(document.createTextNode(roleSecurity.getRoleType()));
			userRole.appendChild(user);
			Element role = document.createElement("role");
			role.appendChild(document.createTextNode(roleSecurity.getNewCropRole()));
			userRole.appendChild(role);

			// Destination Element
			Element destination = document.createElement("Destination");
			ncScript.appendChild(destination);
			Element requestedPage = document.createElement("requestedPage");
			requestedPage.appendChild(document
					.createTextNode("ws-pbm-eligibility"));
			destination.appendChild(requestedPage);

			// Account Element
			Element account = document.createElement("Account");
			ncScript.appendChild(account);
			Attr accAttr = document.createAttribute("ID");
			accAttr.setValue(providerLocation.getAccountId());
			account.setAttributeNode(accAttr);
			Element accountName = document.createElement("accountName");
			accountName.appendChild(document.createTextNode(providerLocation.getAccountId()
					));
			account.appendChild(accountName);
			Element siteId = document.createElement("siteID");
			siteId.appendChild(document.createTextNode(providerLocation.getSiteId()
					));
			account.appendChild(siteId);

			Element accountAddress = document.createElement("AccountAddress");
			account.appendChild(accountAddress);
			Element accAddress1 = document.createElement("address1");
			accAddress1.appendChild(document.createTextNode(usrAddress1));
			accountAddress.appendChild(accAddress1);
			Element accAddress2 = document.createElement("address2");
			accAddress2.appendChild(document.createTextNode(usrAddress2));
			accountAddress.appendChild(accAddress2);
			Element accCity = document.createElement("city");
			accCity.appendChild(document.createTextNode(usrCity));
			accountAddress.appendChild(accCity);
			Element accState = document.createElement("state");
			accState.appendChild(document.createTextNode(usrState));
			accountAddress.appendChild(accState);
			Element accZip = document.createElement("zip");
			accZip.appendChild(document.createTextNode(usrZip));
			accountAddress.appendChild(accZip);
			/*Element accZip4 = document.createElement("zip4");
			accZip4.appendChild(document.createTextNode(usrZip4));
			accountAddress.appendChild(accZip4);*/
			Element accCountry = document.createElement("country");
			accCountry.appendChild(document.createTextNode(usrCountry));
			accountAddress.appendChild(accCountry);

			Element accountPrimaryPhoneNumber = document
					.createElement("accountPrimaryPhoneNumber");
			accountPrimaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			account.appendChild(accountPrimaryPhoneNumber);
			Element accountPrimaryFaxNumber = document
					.createElement("accountPrimaryFaxNumber");
			accountPrimaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			account.appendChild(accountPrimaryFaxNumber);

			// Location Element
			Element location = document.createElement("Location");
			ncScript.appendChild(location);
			Attr locAttr = document.createAttribute("ID");
			locAttr.setValue(usrLocId);
			location.setAttributeNode(locAttr);
			Element locationName = document.createElement("locationName");
			locationName.appendChild(document.createTextNode(usrLocationName));
			location.appendChild(locationName);

			Element locationAddress = document.createElement("LocationAddress");
			location.appendChild(locationAddress);
			Element lAddress1 = document.createElement("address1");
			lAddress1.appendChild(document.createTextNode(locAddress1));
			locationAddress.appendChild(lAddress1);
			Element lAddress2 = document.createElement("address2");
			lAddress2.appendChild(document.createTextNode(locAddress2));
			locationAddress.appendChild(lAddress2);
			Element lCity = document.createElement("city");
			lCity.appendChild(document.createTextNode(locCity));
			locationAddress.appendChild(lCity);
			Element lState = document.createElement("state");
			lState.appendChild(document.createTextNode(locState));
			locationAddress.appendChild(lState);
			Element lZip = document.createElement("zip");
			lZip.appendChild(document.createTextNode(locZip));
			locationAddress.appendChild(lZip);
			/*Element lZip4 = document.createElement("zip4");
			lZip4.appendChild(document.createTextNode(locZip4));
			locationAddress.appendChild(lZip4);*/
			Element lCountry = document.createElement("country");
			lCountry.appendChild(document.createTextNode(locCountry));
			locationAddress.appendChild(lCountry);

			Element primaryPhoneNumber = document
					.createElement("primaryPhoneNumber");
			primaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			location.appendChild(primaryPhoneNumber);
			Element primaryFaxNumber = document
					.createElement("primaryFaxNumber");
			primaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			location.appendChild(primaryFaxNumber);
			Element pharmacyContactNumber = document
					.createElement("pharmacyContactNumber");
			pharmacyContactNumber.appendChild(document
					.createTextNode(usrPharmacyContactNumber));
			location.appendChild(pharmacyContactNumber);

			// LicensedPrescriber Element

			Element licensedPrescriber = document
					.createElement("LicensedPrescriber");
			ncScript.appendChild(licensedPrescriber);
			Attr licensedPrescriberAttr = document.createAttribute("ID");
			licensedPrescriberAttr.setValue(licensedPrescriberId);
			licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
			Element licensedPrescriberName = document
					.createElement("LicensedPrescriberName");
			licensedPrescriber.appendChild(licensedPrescriberName);
			Element docLastName = document.createElement("last");
			docLastName.appendChild(document.createTextNode(doctorLastName));
			licensedPrescriberName.appendChild(docLastName);
			Element docFirstName = document.createElement("first");
			docFirstName.appendChild(document.createTextNode(doctorFirstName));
			licensedPrescriberName.appendChild(docFirstName);
			Element docMidName = document.createElement("middle");
			docMidName.appendChild(document.createTextNode(doctorMiddleName));
			licensedPrescriberName.appendChild(docMidName);
			
			/*Element prefix = document.createElement("prefix");
			prefix.appendChild(document.createTextNode(namePrefix));
			licensedPrescriberName.appendChild(prefix);
			Element suffix = document.createElement("suffix");
			suffix.appendChild(document.createTextNode(nameSuffix));
			licensedPrescriberName.appendChild(suffix);*/

			Element dea = document.createElement("dea");
			dea.appendChild(document.createTextNode(prescDea));
			licensedPrescriber.appendChild(dea);
			Element upin = document.createElement("upin");
			upin.appendChild(document.createTextNode(prescUpin));
			licensedPrescriber.appendChild(upin);
			Element licenseState = document.createElement("licenseState");
			licenseState
					.appendChild(document.createTextNode(prescLicenseState));
			licensedPrescriber.appendChild(licenseState);
			Element licenseNumber = document.createElement("licenseNumber");
			licenseNumber.appendChild(document
					.createTextNode(prescLicenseNumber));
			licensedPrescriber.appendChild(licenseNumber);
			Element npi = document.createElement("npi");
			npi.appendChild(document.createTextNode(prescNpi));
			licensedPrescriber.appendChild(npi);

			// Patient Element

			Element patient = document.createElement("Patient");
			ncScript.appendChild(patient);
			Attr patientIdAttr = document.createAttribute("ID");
			patientIdAttr.setValue(patientId);
			patient.setAttributeNode(patientIdAttr);
			Element patientName = document.createElement("PatientName");
			patient.appendChild(patientName);
			Element patLastName = document.createElement("last");
			patLastName.appendChild(document.createTextNode(pLastName));
			patientName.appendChild(patLastName);
			Element patFirstName = document.createElement("first");
			patFirstName.appendChild(document.createTextNode(pFirstName));
			patientName.appendChild(patFirstName);
			Element patMiddleName = document.createElement("middle");
			patMiddleName.appendChild(document.createTextNode(pMiddleName));
			patientName.appendChild(patMiddleName);

			Element medicalRecordNumber = document
					.createElement("medicalRecordNumber");
			medicalRecordNumber.appendChild(document
					.createTextNode(patMedicalRecordNumber));
			patient.appendChild(medicalRecordNumber);

			Element patientAddress = document.createElement("PatientAddress");
			patient.appendChild(patientAddress);
			Element pAddress1 = document.createElement("address1");
			pAddress1.appendChild(document.createTextNode(patAddress1));
			patientAddress.appendChild(pAddress1);
			Element pCity = document.createElement("city");
			pCity.appendChild(document.createTextNode(patCity));
			patientAddress.appendChild(pCity);
			Element pState = document.createElement("state");
			pState.appendChild(document.createTextNode(patState));
			patientAddress.appendChild(pState);
			Element pZip = document.createElement("zip");
			pZip.appendChild(document.createTextNode(patZip));
			patientAddress.appendChild(pZip);
			Element pCountry = document.createElement("country");
			pCountry.appendChild(document.createTextNode(patCountry));
			patientAddress.appendChild(pCountry);

			Element patientContact = document.createElement("PatientContact");
			patient.appendChild(patientContact);
			Element pHomeTelephone = document.createElement("homeTelephone");
			pHomeTelephone.appendChild(document
					.createTextNode(patHomeTelephone));
			patientContact.appendChild(pHomeTelephone);

			Element patientCharacteristics = document
					.createElement("PatientCharacteristics");
			patient.appendChild(patientCharacteristics);
			Element pDob = document.createElement("dob");
			pDob.appendChild(document.createTextNode(patDOB));
			patientCharacteristics.appendChild(pDob);
			Element pGender = document.createElement("gender");
			pGender.appendChild(document.createTextNode(patGender));
			patientCharacteristics.appendChild(pGender);

			// Write code into XML File
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			StringWriter stringWriter = new StringWriter();
			StreamResult result = new StreamResult(stringWriter);
			DOMSource domSource = new DOMSource(document);
			if(document!=null){
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
				domSource = new DOMSource(document);
				OutputFormat format = new OutputFormat(document);
		        format.setIndenting(true);
		        XMLSerializer serializer = new XMLSerializer(System.out, format);
		        serializer.serialize(document);
		       
		      
			}
			if((domSource!=null && (result!=null))){
				logger.info("domSource Formulary Composite "+domSource+"documentttttttt"+document);
				try {
					transformer.transform(domSource, result);
				}catch(TransformerException e){
					e.printStackTrace();
				}
				catch (NullPointerException e) {
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}

			// Convert Xml format to one line String

			xmlResult = stringWriter.toString();
			logger.info("XMl Result..........for Formulary Composite " + xmlResult);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return xmlResult;

	}

	/**
	 * Doctor Detail Data for PBMEligblity XMLIn Value
	 * 
	 */
	public DoctorDetail getDoctorDetailData() {
		DoctorDetail doctorDetail = userService.findDoctorDetailData();
		System.out.println("Doc Name" + doctorDetail.getfName()
				+ " Doc Email  " + doctorDetail.getEmailId());
		return doctorDetail;
	}

	/**
	 * Patient Detail Data For PBM Eligblity XMLIn Value
	 * 
	 */
	public UserLoginDetail getPatientDetailData() {
		UserLoginDetail patientDetailData = userService.findPatientDetailData();
		System.out.println("Patient Name " + patientDetailData.getFirstName()
				+ "Patient Email " + patientDetailData.getEmail());
		return patientDetailData;
	}

	/**
	 * Search Medicine Drug Dose Combo For Formulary Search
	 * 
	 */
	public void getDrugDoseComboForFormularyMedicine() {
		userService
				.findAllDrugDoseComboForFormularyMedicine(medicineForFormularySearch);
	}

	/**
	 * @return the formularyDetailListForFormularySearch
	 */
	public List<DrugFormularyDetail> getFormularyDetailListForFormularySearch() {
		if (formularyDetailList == null) {
			formularyDetailList = new ArrayList<FormularyDetail>();
		}
		return formularyDetailListForFormularySearch;
	}

	/**
	 * @param formularyDetailListForFormularySearch
	 *            the formularyDetailListForFormularySearch to set
	 */
	public void setFormularyDetailListForFormularySearch(
			List<DrugFormularyDetail> formularyDetailListForFormularySearch) {
		this.formularyDetailListForFormularySearch = formularyDetailListForFormularySearch;
	}

	/**
	 * @return the formularyDetailList
	 */
	public List<FormularyDetail> getFormularyDetailList() {
		if (formularyDetailList == null) {
			formularyDetailList = new ArrayList<FormularyDetail>();
		}
		return formularyDetailList;
	}

	/**
	 * @param formularyDetailList
	 *            the formularyDetailList to set
	 */
	public void setFormularyDetailList(List<FormularyDetail> formularyDetailList) {
		this.formularyDetailList = formularyDetailList;
	}

	/**
	 * Get All Patient Allergy History V4
	 * @param providerLocation 
	 * 
	 * @throws Exception
	 */
	public List<PatientAllergyExtendedDetailV4> getPatientAllergyHistory(ProviderLocation providerLocation)
			 {
		logger.info("getPatientAllergyHistory in NCupdate1:::");
		//String accId = getProperties().getProperty("accountId");
		//String siteId = getProperties().getProperty("siteId");
		try{
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		logger.info("getPatientAllergyHistory in NCupdate1:::"+accId+"::siteId::::::"+siteId);
		
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String patientId = Integer.toString(new ContextUtil().getPatientId());
		//String userId = getProperties().getProperty("userId");
		String userId = Integer.toString(new ContextUtil().getPatientId());
		String userType = getProperties().getProperty("userType");
		System.out.println("::::::::::::userId:::"+userId+":::::userType::"+userType);
		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		PatientRequest patientRequest = WS_CLIENT_FACTORY
				.createPatientRequest();
		patientRequest.setPatientId(patientId);

		PatientInformationRequester patientInformationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		patientInformationRequester.setUserId(userId);
		patientInformationRequester.setUserType(userType);
		patientAllergyExtendedDetailV4List = new ArrayList<PatientAllergyExtendedDetailV4>();

		// Patient Allergy History Request

		GetPatientAllergyHistoryV4 patientAllergyHistoryV4Request = WS_CLIENT_FACTORY
				.createGetPatientAllergyHistoryV4();
		patientAllergyHistoryV4Request.setAccountRequest(accountRequest);
		patientAllergyHistoryV4Request.setCredentials(credentials);
		patientAllergyHistoryV4Request
				.setPatientInformationRequester(patientInformationRequester);
		patientAllergyHistoryV4Request.setPatientRequest(patientRequest);

		// Patient Allergy History Response

		WebServiceClientImpl<GetPatientAllergyHistoryV4> wsClientForPatientAllergyHistoryV4 = new WebServiceClientImpl<GetPatientAllergyHistoryV4>();
		GetPatientAllergyHistoryV4Response patientAllergyHistoryV4Response = (GetPatientAllergyHistoryV4Response) wsClientForPatientAllergyHistoryV4
				.sendRequestAndReceiveResponse(patientAllergyHistoryV4Request,
						Update1ServiceDetail);
		if (patientAllergyHistoryV4Response
				.getGetPatientAllergyHistoryV4Result().getResult().getStatus()
				.value().equals("OK")) {

			if (!(patientAllergyHistoryV4Response
					.getGetPatientAllergyHistoryV4Result()
					.getPatientAllergyExtendedDetailV4() == null)) {

				patientAllergyExtendedDetailV4List = patientAllergyHistoryV4Response
						.getGetPatientAllergyHistoryV4Result()
						.getPatientAllergyExtendedDetailV4()
						.getPatientAllergyExtendedDetailV4();
			}
		}
		else if (patientAllergyHistoryV4Response
				.getGetPatientAllergyHistoryV4Result().getResult().getStatus()
				.value().equals("OK")) {
			getAuditInfo().setComment(patientAllergyHistoryV4Response.getGetPatientAllergyHistoryV4Result().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("GetPatientAllergyHistoryV4 WSDL");
			getAuditInfo().setCreateTime(new Date());
			patientAllergyExtendedDetailV4List=new ArrayList<PatientAllergyExtendedDetailV4>();
			userService.saveWSFailStatus(auditInfo);
			
		}
		}
		catch(SocketException se){
			se.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return patientAllergyExtendedDetailV4List;
	}

	/**
	 * @return the auditInfo
	 */
	public AuditInfo getAuditInfo() {
		if (auditInfo == null) {
			auditInfo = new AuditInfo();
		}
		return auditInfo;
	}

	/**
	 * @param auditInfo
	 *            the auditInfo to set
	 */
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	/**
	 * @return the patientAllergyExtendedDetailV4List
	 */
	public List<PatientAllergyExtendedDetailV4> getPatientAllergyExtendedDetailV4List() {
		if (patientAllergyExtendedDetailV4List == null) {
			patientAllergyExtendedDetailV4List = new ArrayList<PatientAllergyExtendedDetailV4>();
		}
		return patientAllergyExtendedDetailV4List;
	}

	/**
	 * @param patientAllergyExtendedDetailV4List
	 *            the patientAllergyExtendedDetailV4List to set
	 */
	public void setPatientAllergyExtendedDetailV4List(
			List<PatientAllergyExtendedDetailV4> patientAllergyExtendedDetailV4List) {
		this.patientAllergyExtendedDetailV4List = patientAllergyExtendedDetailV4List;
	}
	
	/**
	 * Xml Genration for patient allergy update 
	 * 
	 */
	/*public void createPatientCodifiedAllergy(){
		// Instance of Document and Document Builder

		String xmlResult = "";
		UserLoginDetail patDetailData = getPatientDetailData();
		DoctorDetail doctorDetail = getDoctorDetailData();
		try {
			String usrAccountId = "demo";
			String usrAddress1 = "232323 Test";
			String usrAddress2 = "Suite 240";
			String usrCity = "Boston";
			String usrState = "MA";
			String usrZip = "10409";
			String usrZip4 = "1234";
			String usrCountry = "US";
			String usrPrimaryPhoneNumber = "5555551212";
			String usrPrimaryFaxNumber = "5555551313";
			String usrLocId = "2419";
			String usrLocationName = "Central City";

			String locAddress1 = "232323 Test";
			String locAddress2 = "Suite 240";
			String locCity = "Boston";
			String locState = "MA";
			String locZip = "10409";
			String locZip4 = "1234";
			String locCountry = "US";
			String usrPharmacyContactNumber = "5555551212";

			String licensedPrescriberId = "DEMOLP1";
			String doctorFirstName = doctorDetail.getfName();
			String doctorLastName = doctorDetail.getlName();
			String doctorMiddleName = doctorDetail.getMidName();
			String namePrefix = "Mr.";
			String nameSuffix = "Jr";
			String prescDea = doctorDetail.getDea();
			String prescUpin = doctorDetail.getUpin();
			String prescLicenseState = doctorDetail.getDocLicenseState();
			String prescNpi = doctorDetail.getNpi();
			String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

			String patientId = patDetailData.getFirstName()
					+ patDetailData.getLastName() + "-1";
			String pLastName = patDetailData.getLastName();
			String pFirstName = patDetailData.getFirstName();
			String pMiddleName = patDetailData.getMiddleName();
			String patMedicalRecordNumber = patDetailData.getFirstName()
					+ patDetailData.getLastName();
			String patAddress1 = patDetailData.getDoorNo()
					+ patDetailData.getStreet();
			String patCity = patDetailData.getCity();
			String patState = patDetailData.getState();
			String patZip = patDetailData.getPincode();
			String patCountry = patDetailData.getCountry();
			String patHomeTelephone = patDetailData.getPhoneNumber();
			Date paDob = patDetailData.getDateOfBirth();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String patDOB = df.format(paDob);
			String gender = patDetailData.getGender();
			String patGender = "";
			if (gender.equals("Male")) {
				patGender = "M";
			} else {
				patGender = "F";
			}
			String patAllergyId="123";
			String patAllergyTypeID="FDB";
			String patAllergySeverityTypeID="Mild";
			String patAllergyComment="Mainly on the arms";
			
			
			System.out.println("PatientId  " + patDetailData.getFirstName()
					+ patDetailData.getLastName() + "-1" + " patAddress1 "
					+ patDetailData.getDoorNo() + patDetailData.getStreet());
			System.out.println("City State Zip" + patDetailData.getCity()
					+ patDetailData.getState() + patDetailData.getPincode());

			// Instance of Document and Document Builder

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();

			org.w3c.dom.Document document = documentBuilder.newDocument();

			// Root Element NcScript

			Element ncScript = document.createElement("NCScript");
			document.appendChild(ncScript);

			Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
			ncScriptXsd.setValue(getProperties().getProperty("xsd"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
			ncScriptXsi.setValue(getProperties().getProperty("xsi"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXmlns = document.createAttribute("xmlns");
			ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
			ncScript.setAttributeNode(ncScriptXmlns);
			// Credentials Element

			Element credentials = document.createElement("Credentials");
			ncScript.appendChild(credentials);
			Element partnerName = document.createElement("partnerName");
			partnerName.appendChild(document.createTextNode("newcropdemo"));
			credentials.appendChild(partnerName);
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode("demo"));
			credentials.appendChild(name);
			Element password = document.createElement("password");
			password.appendChild(document.createTextNode(getProperties()
					.getProperty("password")));
			credentials.appendChild(password);
			Element productName = document.createElement("productName");
			productName.appendChild(document.createTextNode(getProperties()
					.getProperty("productName")));
			credentials.appendChild(productName);
			Element productVersion = document.createElement("productVersion");
			productVersion.appendChild(document.createTextNode(getProperties()
					.getProperty("productVersion")));
			credentials.appendChild(productVersion);
			// User Role Element

			Element userRole = document.createElement("UserRole");
			ncScript.appendChild(userRole);
			Element user = document.createElement("user");
			user.appendChild(document.createTextNode(getProperties()
					.getProperty("user")));
			userRole.appendChild(user);
			Element role = document.createElement("role");
			role.appendChild(document.createTextNode(getProperties()
					.getProperty("role")));
			userRole.appendChild(role);

			// Destination Element
			Element destination = document.createElement("Destination");
			ncScript.appendChild(destination);
			Element requestedPage = document.createElement("requestedPage");
			requestedPage.appendChild(document
					.createTextNode("ws-pbm-eligibility"));
			destination.appendChild(requestedPage);

			// Account Element
			Element account = document.createElement("Account");
			ncScript.appendChild(account);
			Attr accAttr = document.createAttribute("ID");
			accAttr.setValue(getProperties().getProperty("id"));
			account.setAttributeNode(accAttr);
			Element accountName = document.createElement("accountName");
			accountName.appendChild(document.createTextNode(getProperties()
					.getProperty("accountName")));
			account.appendChild(accountName);
			Element siteId = document.createElement("siteID");
			siteId.appendChild(document.createTextNode(getProperties()
					.getProperty("siteId")));
			account.appendChild(siteId);

			Element accountAddress = document.createElement("AccountAddress");
			account.appendChild(accountAddress);
			Element accAddress1 = document.createElement("address1");
			accAddress1.appendChild(document.createTextNode(usrAddress1));
			accountAddress.appendChild(accAddress1);
			Element accAddress2 = document.createElement("address2");
			accAddress2.appendChild(document.createTextNode(usrAddress2));
			accountAddress.appendChild(accAddress2);
			Element accCity = document.createElement("city");
			accCity.appendChild(document.createTextNode(usrCity));
			accountAddress.appendChild(accCity);
			Element accState = document.createElement("state");
			accState.appendChild(document.createTextNode(usrState));
			accountAddress.appendChild(accState);
			Element accZip = document.createElement("zip");
			accZip.appendChild(document.createTextNode(usrZip));
			accountAddress.appendChild(accZip);
			Element accZip4 = document.createElement("zip4");
			accZip4.appendChild(document.createTextNode(usrZip4));
			accountAddress.appendChild(accZip4);
			Element accCountry = document.createElement("country");
			accCountry.appendChild(document.createTextNode(usrCountry));
			accountAddress.appendChild(accCountry);

			Element accountPrimaryPhoneNumber = document
					.createElement("accountPrimaryPhoneNumber");
			accountPrimaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			account.appendChild(accountPrimaryPhoneNumber);
			Element accountPrimaryFaxNumber = document
					.createElement("accountPrimaryFaxNumber");
			accountPrimaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			account.appendChild(accountPrimaryFaxNumber);

			// Location Element
			Element location = document.createElement("Location");
			ncScript.appendChild(location);
			Attr locAttr = document.createAttribute("ID");
			locAttr.setValue(usrLocId);
			location.setAttributeNode(locAttr);
			Element locationName = document.createElement("locationName");
			locationName.appendChild(document.createTextNode(usrLocationName));
			location.appendChild(locationName);

			Element locationAddress = document.createElement("LocationAddress");
			location.appendChild(locationAddress);
			Element lAddress1 = document.createElement("address1");
			lAddress1.appendChild(document.createTextNode(locAddress1));
			locationAddress.appendChild(lAddress1);
			Element lAddress2 = document.createElement("address2");
			lAddress2.appendChild(document.createTextNode(locAddress2));
			locationAddress.appendChild(lAddress2);
			Element lCity = document.createElement("city");
			lCity.appendChild(document.createTextNode(locCity));
			locationAddress.appendChild(lCity);
			Element lState = document.createElement("state");
			lState.appendChild(document.createTextNode(locState));
			locationAddress.appendChild(lState);
			Element lZip = document.createElement("zip");
			lZip.appendChild(document.createTextNode(locZip));
			locationAddress.appendChild(lZip);
			Element lZip4 = document.createElement("zip4");
			lZip4.appendChild(document.createTextNode(locZip4));
			locationAddress.appendChild(lZip4);
			Element lCountry = document.createElement("country");
			lCountry.appendChild(document.createTextNode(locCountry));
			locationAddress.appendChild(lCountry);

			Element primaryPhoneNumber = document
					.createElement("primaryPhoneNumber");
			primaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			location.appendChild(primaryPhoneNumber);
			Element primaryFaxNumber = document
					.createElement("primaryFaxNumber");
			primaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			location.appendChild(primaryFaxNumber);
			Element pharmacyContactNumber = document
					.createElement("pharmacyContactNumber");
			pharmacyContactNumber.appendChild(document
					.createTextNode(usrPharmacyContactNumber));
			location.appendChild(pharmacyContactNumber);

			// LicensedPrescriber Element

			Element licensedPrescriber = document
					.createElement("LicensedPrescriber");
			ncScript.appendChild(licensedPrescriber);
			Attr licensedPrescriberAttr = document.createAttribute("ID");
			licensedPrescriberAttr.setValue(licensedPrescriberId);
			licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
			Element licensedPrescriberName = document
					.createElement("LicensedPrescriberName");
			licensedPrescriber.appendChild(licensedPrescriberName);
			Element docLastName = document.createElement("last");
			docLastName.appendChild(document.createTextNode(doctorLastName));
			licensedPrescriberName.appendChild(docLastName);
			Element docFirstName = document.createElement("first");
			docFirstName.appendChild(document.createTextNode(doctorFirstName));
			licensedPrescriberName.appendChild(docFirstName);
			Element docMidName = document.createElement("middle");
			docMidName.appendChild(document.createTextNode(doctorMiddleName));
			licensedPrescriberName.appendChild(docMidName);
			Element prefix = document.createElement("prefix");
			prefix.appendChild(document.createTextNode(namePrefix));
			licensedPrescriberName.appendChild(prefix);
			Element suffix = document.createElement("suffix");
			suffix.appendChild(document.createTextNode(nameSuffix));
			licensedPrescriberName.appendChild(suffix);

			Element dea = document.createElement("dea");
			dea.appendChild(document.createTextNode(prescDea));
			licensedPrescriber.appendChild(dea);
			Element upin = document.createElement("upin");
			upin.appendChild(document.createTextNode(prescUpin));
			licensedPrescriber.appendChild(upin);
			Element licenseState = document.createElement("licenseState");
			licenseState
					.appendChild(document.createTextNode(prescLicenseState));
			licensedPrescriber.appendChild(licenseState);
			Element licenseNumber = document.createElement("licenseNumber");
			licenseNumber.appendChild(document
					.createTextNode(prescLicenseNumber));
			licensedPrescriber.appendChild(licenseNumber);
			Element npi = document.createElement("npi");
			npi.appendChild(document.createTextNode(prescNpi));
			licensedPrescriber.appendChild(npi);

			// Patient Element

			Element patient = document.createElement("Patient");
			ncScript.appendChild(patient);
			Attr patientIdAttr = document.createAttribute("ID");
			patientIdAttr.setValue(patientId);
			patient.setAttributeNode(patientIdAttr);
			Element patientName = document.createElement("PatientName");
			patient.appendChild(patientName);
			Element patLastName = document.createElement("last");
			patLastName.appendChild(document.createTextNode(pLastName));
			patientName.appendChild(patLastName);
			Element patFirstName = document.createElement("first");
			patFirstName.appendChild(document.createTextNode(pFirstName));
			patientName.appendChild(patFirstName);
			Element patMiddleName = document.createElement("middle");
			patMiddleName.appendChild(document.createTextNode(pMiddleName));
			patientName.appendChild(patMiddleName);

			Element medicalRecordNumber = document
					.createElement("medicalRecordNumber");
			medicalRecordNumber.appendChild(document
					.createTextNode(patMedicalRecordNumber));
			patient.appendChild(medicalRecordNumber);

			Element patientAddress = document.createElement("PatientAddress");
			patient.appendChild(patientAddress);
			Element pAddress1 = document.createElement("address1");
			pAddress1.appendChild(document.createTextNode(patAddress1));
			patientAddress.appendChild(pAddress1);
			Element pCity = document.createElement("city");
			pCity.appendChild(document.createTextNode(patCity));
			patientAddress.appendChild(pCity);
			Element pState = document.createElement("state");
			pState.appendChild(document.createTextNode(patState));
			patientAddress.appendChild(pState);
			Element pZip = document.createElement("zip");
			pZip.appendChild(document.createTextNode(patZip));
			patientAddress.appendChild(pZip);
			Element pCountry = document.createElement("country");
			pCountry.appendChild(document.createTextNode(patCountry));
			patientAddress.appendChild(pCountry);

			Element patientContact = document.createElement("PatientContact");
			patient.appendChild(patientContact);
			Element pHomeTelephone = document.createElement("homeTelephone");
			pHomeTelephone.appendChild(document
					.createTextNode(patHomeTelephone));
			patientContact.appendChild(pHomeTelephone);

			Element patientCharacteristics = document
					.createElement("PatientCharacteristics");
			patient.appendChild(patientCharacteristics);
			Element pDob = document.createElement("dob");
			pDob.appendChild(document.createTextNode(patDOB));
			patientCharacteristics.appendChild(pDob);
			Element pGender = document.createElement("gender");
			pGender.appendChild(document.createTextNode(patGender));
			patientCharacteristics.appendChild(pGender);
			
			//Patient Allergies Element 
			Element patientAllergies=document.createElement("PatientAllergies");
			patient.appendChild(patientAllergies);
			Element pAllergyID=document.createElement("allergyID");
			pAllergyID.appendChild(document.createTextNode(patAllergyId));
			patientAllergies.appendChild(pAllergyID);
			Element pAllergyTypeID=document.createElement("allergyTypeID");
			pAllergyTypeID.appendChild(document.createTextNode(patAllergyTypeID));
			patientAllergies.appendChild(pAllergyTypeID);
			Element pAllergySeverityTypeID=document.createElement("allergySeverityTypeID");
			pAllergySeverityTypeID.appendChild(document.createTextNode(patAllergySeverityTypeID));
			patientAllergies.appendChild(pAllergySeverityTypeID);
			Element pAllergyComment=document.createElement("allergyComment");
			pAllergyComment.appendChild(document.createTextNode(patAllergyComment));
			patientAllergies.appendChild(pAllergyComment);

			// Write code into XML File
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();

			StringWriter stringWriter = new StringWriter();
			StreamResult result = new StreamResult(stringWriter);
			DOMSource domSource = new DOMSource(document);
			transformer.transform(domSource, result);

			// Convert Xml format to one line String

			xmlResult = stringWriter.toString();
			System.out.println("XMl Result.........." + xmlResult);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}*/
	
	public List<FormularyDetail> getFormularyAlternativeWithDrugInfoForDpOptimizer(String healthPlan,String patientId,String medicineNameForOptimization,String drugConcept, ProviderLocation providerLocation) {
		drugFormularyDetailList=new ArrayList<DrugFormularyDetail>();
		formularyDetailList=new ArrayList<FormularyDetail>();
		System.out.println("Medicine name for optimization "+medicineNameForOptimization+"Drug Concept "+drugConcept);
		
		
		try{
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String patId=patientId;
		String userId = Integer.toString(new ContextUtil().getProviderId());
		String healthPlanId = healthPlan;

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		 PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		 patientRequest.setPatientId(patId);

		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType("demo");

		FormularyAlternativesWithDrugInfo2 formularyAlternativesWithDrugInfo2 = WS_CLIENT_FACTORY
				.createFormularyAlternativesWithDrugInfo2();
		formularyAlternativesWithDrugInfo2
				.setAccountRequest(accountRequest);
		formularyAlternativesWithDrugInfo2.setCredentials(credentials);

		formularyAlternativesWithDrugInfo2.setDrugConcept(drugConcept);

		formularyAlternativesWithDrugInfo2.setDrugConceptType("F");
		formularyAlternativesWithDrugInfo2.setEligibilityIndex("0");
		formularyAlternativesWithDrugInfo2.setHealthplanID(healthPlanId);
		formularyAlternativesWithDrugInfo2.setHealthplanTypeID("S");
		formularyAlternativesWithDrugInfo2
				.setPatientInformationRequester(informationRequester);
		
	    WebServiceClientImpl<FormularyAlternativesWithDrugInfo2> wsClientForFormularyAlternativesWithDrugInfo2 = new WebServiceClientImpl<FormularyAlternativesWithDrugInfo2>();
		FormularyAlternativesWithDrugInfo2Response formularyAlternativesWithDrugInfo2Response = new FormularyAlternativesWithDrugInfo2Response();
		formularyAlternativesWithDrugInfo2Response = (FormularyAlternativesWithDrugInfo2Response) wsClientForFormularyAlternativesWithDrugInfo2.sendRequestAndReceiveResponse(formularyAlternativesWithDrugInfo2,
					Update1ServiceDetail);
		System.out.println("formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getResult().getStatus()"+
				  formularyAlternativesWithDrugInfo2Response
				.getFormularyAlternativesWithDrugInfo2Result()
				.getResult().getStatus());
		if (formularyAlternativesWithDrugInfo2Response
				.getFormularyAlternativesWithDrugInfo2Result()
				.getResult().getStatus().value().equals("OK")) {
			System.out.println("formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getDrugFormularyDetailArray()"+formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getDrugFormularyDetailArray());
				
				
		if (!(formularyAlternativesWithDrugInfo2Response
				.getFormularyAlternativesWithDrugInfo2Result()
				.getDrugFormularyDetailArray() == null)){

			drugFormularyDetailList = formularyAlternativesWithDrugInfo2Response
					.getFormularyAlternativesWithDrugInfo2Result()
					.getDrugFormularyDetailArray()
					.getDrugFormularyDetail();
		for(DrugFormularyDetail drFormularyDetail:drugFormularyDetailList){
			
			System.out.println("Formulary Coverage :::::::; in nc update ws bean "+drFormularyDetail.getFormularyCoverage());
			FormularyDetail formularyDetail=new FormularyDetail();
			formularyDetail.setMedicineName(medicineNameForOptimization);
			formularyDetail.setAlternateMedicine(drFormularyDetail.getDrugDetail().getDrug());
			String formularyCoverage="";
			if(drFormularyDetail.getFormularyCoverage().equals("50")){
				formularyCoverage="On Formulary";
			}
			else if (drFormularyDetail.getFormularyCoverage().equals("51")) {
				formularyCoverage="Tier 1";
			}
			else if (drFormularyDetail.getFormularyCoverage().equals("52")) {
				formularyCoverage="Tier 2";
			}
			else if (drFormularyDetail.getFormularyCoverage().equals("53")) {
				formularyCoverage="Tier 3";
			}
			formularyDetail.setFormulayTier(formularyCoverage);
			formularyDetailList.add(formularyDetail);
		  }
	     }
		
	    }
		else if (!(formularyAlternativesWithDrugInfo2Response
				.getFormularyAlternativesWithDrugInfo2Result()
				.getResult().getStatus().value().equals("OK"))) {
			getAuditInfo().setComment(formularyAlternativesWithDrugInfo2Response.getFormularyAlternativesWithDrugInfo2Result().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("FormularyAlternativeWithDrugInfo WSDL");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
			
		}
	 }
		catch(SocketException se){
			se.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return formularyDetailList;
	}
	
	/**
	 * Call Api  GetMostRecentDownloadUrl 
	 * @param providerLocation 
	 * @throws Exception 
	 * 
	 */
	public DownloadDetail getMostRecentDownloadUrl(ProviderLocation providerLocation) throws Exception{
		System.out.println("getMostRecentDownloadUrl() ");
		DownloadDetail downloadDetail=new DownloadDetail();
		try{
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		Integer desiredData=1;
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		GetMostRecentDownloadUrl mostRecentDownloadUrlRequest=WS_CLIENT_FACTORY.createGetMostRecentDownloadUrl();
		mostRecentDownloadUrlRequest.setAccountRequest(accountRequest);
		mostRecentDownloadUrlRequest.setCredentials(credentials);
		mostRecentDownloadUrlRequest.setDesiredData(1);
		
		WebServiceClientImpl<GetMostRecentDownloadUrl> wsClientForGetMostRecentDownloadUrl=new WebServiceClientImpl<GetMostRecentDownloadUrl>();
		GetMostRecentDownloadUrlResponse mostRecentDownloadUrlResponse=new GetMostRecentDownloadUrlResponse();
		mostRecentDownloadUrlResponse=(GetMostRecentDownloadUrlResponse) wsClientForGetMostRecentDownloadUrl.sendRequestAndReceiveResponse(mostRecentDownloadUrlRequest, Update1ServiceDetail);
		
		if(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getResult().getStatus().value().equals("OK")){
			if(!(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail()==null)){
			
		   String requestDate = FastDateFormat.getInstance("yyyy-MM-dd").format(System.currentTimeMillis( ));
		   
		   downloadDetail.setCchitDrugDatabaseDate(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getCchitDrugDatabaseDate());
		   downloadDetail.setLatestDownloadDay(requestDate);
		   downloadDetail.setComments(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getComments());
		   downloadDetail.setLatestDownloadMonth(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getLatestDownloadMonth());
		   downloadDetail.setLatestDownloadSize(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getLatestDownloadSize());
		   downloadDetail.setLatestDownloadUrl(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getLatestDownloadUrl());
		   downloadDetail.setLatestDownloadYear(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getDownloadDetail().getLatestDownloadYear());
			}
		}
		else if (!mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getResult().getStatus().value().equals("OK")) {
			getAuditInfo().setComment(mostRecentDownloadUrlResponse.getGetMostRecentDownloadUrlResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("GetMostRecentDownloadUrl WSDL");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
		}
	   }
		catch(SocketException se){
			se.printStackTrace();
		}
		catch(NullPointerException ne){
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return downloadDetail;
		
	}
	
	/**
	 * Call Drug Allergy Interaction V2
	 * @param medicationListForPatient 
	 * @param list 
	 * @param patientId
	 * @param providerLocation 
	 * @throws Exception 
	 * 
	 */
	public List<DrugAllergyDetailV2> callDrugAllergyInteractionV2(String patientId, List<PatientAllergy> patientAllergyList, List<PatientMedicationData> patientMedicationList, ProviderLocation providerLocation) {
		System.out.println("callDrugAllergyInteractionV2 Method in nc update ws bean ");
		List<DrugAllergyDetailV2> druAllergyDetailV2List=new ArrayList<DrugAllergyDetailV2>();
		try{
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String accId =  providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String userId = Integer.toString(new ContextUtil().getProviderId());
		String userType=getProperties().getProperty("userType");
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);
		
		PatientInformationRequester pInformationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		pInformationRequester.setUserId(userId);
		pInformationRequester.setUserType(userType);
		
		ArrayOfString allergies=new ArrayOfString();
		for(PatientAllergy patAllergy:patientAllergyList){
			
			String allergy=""+patAllergy.getCompositeAllergyId();
			System.out.println("patAllergy.getAllergyId()"+patAllergy.getCompositeAllergyId()+"allergy "+allergy);
			allergies.getString().add(allergy);
			System.out.println("Allergy Name "+allergy);
			
		}
		System.out.println("allergiess"+allergies);
		ArrayOfString proposedMedications=new ArrayOfString();
		String proposedMedication="";
		for(PatientMedicationData patMedData:patientMedicationList){
			int proposedMedicationId=(int) patMedData.getDrugId();
			 proposedMedication=Integer.toString(proposedMedicationId);
			 System.out.println("proposedMedication ::::::::;"+proposedMedication);
			proposedMedications.getString().add(proposedMedication);
		}
			
		
		//Drug Allergy Interaction V2 Request
		DrugAllergyInteractionV2 drugAllergyInteractionV2Request=WS_CLIENT_FACTORY.createDrugAllergyInteractionV2();
		drugAllergyInteractionV2Request.setAccountRequest(accountRequest);
		drugAllergyInteractionV2Request.setCredentials(credentials);
		drugAllergyInteractionV2Request.setPatientInformationRequester(pInformationRequester);
		drugAllergyInteractionV2Request.setPatientRequest(patientRequest);
		drugAllergyInteractionV2Request.setAllergies(allergies);
		drugAllergyInteractionV2Request.setProposedMedications(proposedMedications);
		drugAllergyInteractionV2Request.setDrugStandardType("FDB");
		
		//Drug Allergy Interaction V2 Response 
		WebServiceClientImpl<DrugAllergyInteractionV2> wsClientForDrugAllergyInteractionV2=new WebServiceClientImpl<DrugAllergyInteractionV2>();
		DrugAllergyInteractionV2Response drugAllergyInteractionV2Response=new DrugAllergyInteractionV2Response();
		drugAllergyInteractionV2Response=(DrugAllergyInteractionV2Response) wsClientForDrugAllergyInteractionV2.sendRequestAndReceiveResponse(drugAllergyInteractionV2Request, Update1ServiceDetail);
		System.out.println("drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getResult().getStatus() "+drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getResult().getStatus());
		if(drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getResult().getStatus().value().equals("OK")){
			System.out.println("drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2()"+drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2());
			if(!(drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2()==null)){
				System.out.println("drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2()"+drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2());
			druAllergyDetailV2List=drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getDrugAllergyDetailArrayV2().getDrugAllergyDetailV2();
			System.out.println("druAllergyDetailV2List.size()"+druAllergyDetailV2List.size());
			for(DrugAllergyDetailV2 drgAllergy:druAllergyDetailV2List){
				System.out.println("drgAllergy.getPerformance()"+drgAllergy.getPerformance()+"drgAllergy.getDescription()) "+drgAllergy.getDescription());
			}
		 }
			else if (!(drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getResult().getStatus().value().equals("OK"))) {
				getAuditInfo().setComment(drugAllergyInteractionV2Response.getDrugAllergyInteractionV2Result().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("DrugAllergyInteractionV2 WSDL");
				getAuditInfo().setCreateTime(new Date());
				userService.saveWSFailStatus(auditInfo);
			}
		}
		}
		catch(SocketException se){
			se.printStackTrace();
		}
		catch (NullPointerException ne) {
			// TODO: handle exception
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return druAllergyDetailV2List;
	}
	
	/**
	 * Call Drug Disease Interaction API 
	 * @param patientMedicationDataList 
	 * 
	 * @param patientId 
	 * @param patientDiagnosesList 
	 * @param providerLocation 
	 * @param selectedDrugId 
	 * @throws Exception 
	 * 
	 */
	
	public List<DrugDiseaseDetail> callDrugDiseaseInteraction(String patientId, List<PatientMedicationData> patientMedicationDataList,
			List<PatientDiagnosesDetails> patientDiagnosesList, ProviderLocation providerLocation, double selectedDrugId) {
		
		System.out.println("callDrugDiseaseInteraction:::"+selectedDrugId);
		List<DrugDiseaseDetail> drugDiseaseDetailList=new ArrayList<DrugDiseaseDetail>();
		try{
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String userId = Integer.toString(new ContextUtil().getProviderId());
		String userType=getProperties().getProperty("userType");
		String diseaseCodeType="";
		String drugStandardType="FDB";
		
		ArrayOfString diseaseList=new ArrayOfString();
		for(PatientDiagnosesDetails pDiagnosesDetails:patientDiagnosesList){
			//Double diagnosisId=pDiagnosesDetails.getIcdId();// Commented By Anjani
			String disease=pDiagnosesDetails.getIcdId();
			
			diseaseList.getString().add(disease);
			diseaseCodeType=pDiagnosesDetails.getIcdCodeType();
			
		}
		
		
		
		ArrayOfString proposedMedications=new ArrayOfString();
		
		if(!(selectedDrugId==0.0))
		{
			int proposedMedicationId=(int) selectedDrugId;
			String  proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
			System.out.println("diagnosis Medication...12345.in for loop "+proposedMedications+"drug diagnosis:::: "+proposedMedication+":::::::");
		
		}
		
		else
		{
		for(PatientMedicationData patMedData:patientMedicationDataList){
			int proposedMedicationId=(int) patMedData.getDrugId();
			String  proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
			}
		}
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		PatientInformationRequester pInformationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		pInformationRequester.setUserId(userId);
		pInformationRequester.setUserType(userType);
		
		PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);
		
		//Drug Disease Interaction Request 
		DrugDiseaseInteraction drugDiseaseInteractionRequest=WS_CLIENT_FACTORY.createDrugDiseaseInteraction();
		drugDiseaseInteractionRequest.setAccountRequest(accountRequest);
		drugDiseaseInteractionRequest.setCredentials(credentials);
		drugDiseaseInteractionRequest.setDiseaseCodeType(diseaseCodeType);
		drugDiseaseInteractionRequest.setDrugStandardType(drugStandardType);
		drugDiseaseInteractionRequest.setPatientInformationRequester(pInformationRequester);
		drugDiseaseInteractionRequest.setPatientRequest(patientRequest);
		drugDiseaseInteractionRequest.setDiseaseList(diseaseList);
		drugDiseaseInteractionRequest.setProposedMedications(proposedMedications);
		
		//Drug Disease Interaction Response 
		WebServiceClientImpl<DrugDiseaseInteraction> wsClientForDrugDiseaseInteractionRequest=new WebServiceClientImpl<DrugDiseaseInteraction>();
		DrugDiseaseInteractionResponse drugDiseaseInteractionResponse=new DrugDiseaseInteractionResponse();
		drugDiseaseInteractionResponse=(DrugDiseaseInteractionResponse) wsClientForDrugDiseaseInteractionRequest.sendRequestAndReceiveResponse(drugDiseaseInteractionRequest, Update1ServiceDetail);
		if(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getStatus().value().equals("OK")){
			if(!(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getDrugDiseaseDetailArray()==null)){
			   drugDiseaseDetailList=drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getDrugDiseaseDetailArray().getDrugDiseaseDetail();
			   
			}
		}
		else if (!(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getStatus().value().equals("OK"))) {
			getAuditInfo().setComment(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("DrugDiseaseInteraction WSDL");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
		}
		}
		catch(SocketException se){
			se.printStackTrace();
		}
		catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return drugDiseaseDetailList;
	}
	/**
	 * Decode Pbm Eligblity Result 
	 */
	public String decodeEligibilityDetailResultV3(ProviderLocation providerLocation,RoleSecurity roleSecurity){
		byte[] decodedByte = null;
		try {
			EligibilityDetailResultV3 eligblityResult=callGetPBMEligibilityV3(providerLocation, roleSecurity);
			String xmlResponse=eligblityResult.getResult().getXmlResponse();
			Base64 decoder=new Base64();
		
			if(!(xmlResponse.equals(""))){
			decodedByte = (byte[]) decoder.decode(xmlResponse.getBytes());
			}
			System.out.println(new String(decodedByte));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(decodedByte);
		
	}
	
	/*
	 * Parsing Decoded Eligblity Detail Result 
	 * 
	 */
	public List<UserInsuranceDetails> getEligblityDetailResultUsingDomParser(ProviderLocation providerLocation,RoleSecurity roleSecurity) throws ParserConfigurationException, SAXException, IOException{
		String eligblityDetailResult=decodeEligibilityDetailResultV3(providerLocation, roleSecurity);
		List<UserInsuranceDetails> userInsuranceDetailList=new ArrayList<UserInsuranceDetails>();
		InputSource inputSource = null;
		if(!eligblityDetailResult.equals("")){
			inputSource = new InputSource(new StringReader(eligblityDetailResult));
			DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			org.w3c.dom.Document document = documentBuilder.parse(inputSource);

			Element docElement = document.getDocumentElement();
			NodeList node = docElement.getChildNodes();
			
			
            if(node != null && node.getLength() > 0 && eligblityDetailResult!=null){
            	for(int i=0;i<node.getLength();i++){
            		if(node.item(i).getNodeType()== Node.ELEMENT_NODE ){
            			Element e1 = (Element) node.item(i);
            			if (e1.getNodeName().contains("Table")) {
            				UserInsuranceDetails insuranceDetails=new UserInsuranceDetails();
            				
            				insuranceDetails.setSource(e1.getElementsByTagName("Source").item(0).getFirstChild().getNodeValue());
            				insuranceDetails.setPbm(e1.getElementsByTagName("PBM").item(0).getFirstChild().getNodeValue());
            				insuranceDetails.setPbmId(e1.getElementsByTagName("PBMId").item(0).getFirstChild().getNodeValue());
            				if(!(e1.getElementsByTagName("PlanName").item(0).getFirstChild()==null)){
            				insuranceDetails.setInsurancePlanName(e1.getElementsByTagName("PlanName").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("PlanId").item(0).getFirstChild()==null)){
            					insuranceDetails.setInsurancePlanIdNewCrop(e1.getElementsByTagName("PlanId").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("CardholderName").item(0).getFirstChild()==null)){
            					insuranceDetails.setCardHolderName(e1.getElementsByTagName("CardholderName").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("CardholderId").item(0).getFirstChild()==null)){
            					insuranceDetails.setCardholderId(e1.getElementsByTagName("CardholderId").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("PersonCode").item(0).getFirstChild()==null)){
            					insuranceDetails.setPersonCode(e1.getElementsByTagName("PersonCode").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("GroupNumber").item(0).getFirstChild()==null)){
            					insuranceDetails.setGroupNumber(e1.getElementsByTagName("GroupNumber").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("GroupNumberId").item(0).getFirstChild()==null)){
            					insuranceDetails.setGroupNumberId(e1.getElementsByTagName("GroupNumberId").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("formularyId").item(0).getFirstChild()==null)){
            					insuranceDetails.setFormularyId(e1.getElementsByTagName("formularyId").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("AlternateFormularyId").item(0).getFirstChild()==null)){
            					insuranceDetails.setAlternateFormularyId(e1.getElementsByTagName("AlternateFormularyId").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("PharmacyBenefit").item(0).getFirstChild()==null)){
            					insuranceDetails.setPharmacyBenefit(e1.getElementsByTagName("PharmacyBenefit").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("MailOrderBenefit").item(0).getFirstChild()==null)){
            					insuranceDetails.setMailOrderBenefit(e1.getElementsByTagName("MailOrderBenefit").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("SpecialtyPharmacyBenefit").item(0).getFirstChild()==null)){
            					insuranceDetails.setSpecialtyPharmacyBenefit(e1.getElementsByTagName("SpecialtyPharmacyBenefit").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("LTCBenefit").item(0).getFirstChild()==null)){
            					insuranceDetails.setLtcBenefit(e1.getElementsByTagName("LTCBenefit").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("PBMPatientId").item(0).getFirstChild()==null)){
            					insuranceDetails.setPbmPatientId(e1.getElementsByTagName("PBMPatientId").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("SubscriberDate").item(0).getFirstChild()==null)){
            					insuranceDetails.setSubscriberDate(e1.getElementsByTagName("SubscriberDate").item(0).getFirstChild().getNodeValue());
            				}
            				
            				if(!(e1.getElementsByTagName("PharmacyBenefitDescription").item(0).getFirstChild()==null)){
            					insuranceDetails.setPharmacyBenefitDescription(e1.getElementsByTagName("PharmacyBenefitDescription").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("MailOrderBenefitDescription").item(0).getFirstChild()==null)){
            					insuranceDetails.setMailOrderBenefitDescription(e1.getElementsByTagName("MailOrderBenefitDescription").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("SpecialtyPharmacyBenefitDescription").item(0).getFirstChild()==null)){
            					insuranceDetails.setSpecialtyPharmacyBenefitDescription(e1.getElementsByTagName("SpecialtyPharmacyBenefitDescription").item(0).getFirstChild().getNodeValue());
            				}
            				if(!(e1.getElementsByTagName("LTCBenefitDescription").item(0).getFirstChild()==null)){
            					insuranceDetails.setLtcBenefit(e1.getElementsByTagName("LTCBenefitDescription").item(0).getFirstChild().getNodeValue());
            				}
            				
            				insuranceDetails.setCacheExpiryTimestamp(e1.getElementsByTagName("CacheExpiryTimestamp").item(0).getFirstChild().getNodeValue());
            				insuranceDetails.setBenefitLoop(e1.getElementsByTagName("BenefitLoop").item(0).getFirstChild().getNodeValue());
            				
            				
            			   userInsuranceDetailList.add(insuranceDetails);
            			
            			}
            		}
            		
            	}
            	
            }
					
		}
		for(UserInsuranceDetails usInsuranceDetails:userInsuranceDetailList){
			System.out.println("Formulary Id "+usInsuranceDetails.getFormularyId() +"  Size of health plan list "+userInsuranceDetailList.size());
		}
		return userInsuranceDetailList;
		
				
		
	}
	
	
	
	/**
	   * Get all Patient Pbm Eligblity Value 
	   * @param providerLocation
	   * @param roleSecurity
	   * @param patientDetail
	   * @return
	 * @throws Exception 
	   */
		public ArrayList<PatientPbmDrugHistoryDetail> callGetPBMEligibilityV3(
				ProviderLocation providerLocation, RoleSecurity roleSecurity,
				UserLoginDetail patientDetail) throws Exception {
			
			String accId = providerLocation.getAccountId();
			String siteId = providerLocation.getSiteId();
			String partName = getProperties().getProperty("partnerName");
			String name = getProperties().getProperty("name");
			String password = getProperties().getProperty("password");
			String userId = getProperties().getProperty("userId");
			String userType = getProperties().getProperty("userType");
			EligibilityDetailResultV3 elDetailResultV3=new EligibilityDetailResultV3();
			List<EligibilityDetailResultV3> eligibilityDetailResultV3List=new ArrayList<EligibilityDetailResultV3>();
			
			ArrayList<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryListForPBMEligblity=new ArrayList<PatientPbmDrugHistoryDetail>();
			ArrayList<PatientPbmDrugHistoryDetail> patientXmlBase64EncodedDataList = convertPatientDetailStringInBase64Format(providerLocation,roleSecurity,
																											patientDetail);
		
			for(PatientPbmDrugHistoryDetail patientXmlBase64EncodedData:patientXmlBase64EncodedDataList){
				PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetailPBMEligblity=new PatientPbmDrugHistoryDetail();
			
			if(!patientXmlBase64EncodedData.getPatientXmlResult().equals("")){	
				
			
			String xmlIn = "BASE64:" + patientXmlBase64EncodedData.getPatientXmlResult();
			AccountRequest accountRequest = WS_CLIENT_FACTORY
					.createAccountRequest();
			accountRequest.setAccountId(accId);
			accountRequest.setSiteId(siteId);

			Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
			credentials.setName(name);
			credentials.setPartnerName(partName);
			credentials.setPassword(password);

			PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
					.createPatientInformationRequester();
			informationRequester.setUserId(userId);
			informationRequester.setUserType(userType);

			GetPBMEligibilityV3 pbmEligibilityV3 = WS_CLIENT_FACTORY
					.createGetPBMEligibilityV3();
			pbmEligibilityV3.setAccountRequest(accountRequest);
			pbmEligibilityV3.setCredentials(credentials);
			pbmEligibilityV3.setIncludeSchema("Y");
			pbmEligibilityV3.setPatientInformationRequester(informationRequester);
			pbmEligibilityV3.setXmlIn(xmlIn);

			WebServiceClientImpl<GetPBMEligibilityV3> wsClientForPbmEligblityV3 = new WebServiceClientImpl<GetPBMEligibilityV3>();
			GetPBMEligibilityV3Response pbmEligibilityV3Response = (GetPBMEligibilityV3Response) wsClientForPbmEligblityV3
					.sendRequestAndReceiveResponse
					(pbmEligibilityV3, Update1ServiceDetail);
			
			if(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")){
				elDetailResultV3 = pbmEligibilityV3Response
						.getGetPBMEligibilityV3Result();
				
				patientPbmDrugHistoryDetailPBMEligblity.setPatientPbmEligblityGuid(elDetailResultV3.getEligibilityGuid());
				patientPbmDrugHistoryDetailPBMEligblity.setPatientId(patientXmlBase64EncodedData.getPatientId());
				patientPbmDrugHistoryDetailPBMEligblity.setPbmEligblityXmlResponse(elDetailResultV3.getResult().getXmlResponse());
				for(EligibilityDetailV3 eligibilityDetailV3:elDetailResultV3.getEligibilityDetailArray().getEligibilityDetailV3() ){
					patientPbmDrugHistoryDetailPBMEligblity.setPharmacyBenefit(eligibilityDetailV3.getPharmacyBenefit());
					patientPbmDrugHistoryDetailPBMEligblity.setMailOrderBenefit(eligibilityDetailV3.getMailOrderBenefit());
					patientPbmDrugHistoryDetailPBMEligblity.setSource(eligibilityDetailV3.getSource());
					patientPbmDrugHistoryDetailPBMEligblity.setPbm(eligibilityDetailV3.getPBM());
					patientPbmDrugHistoryDetailPBMEligblity.setHealthPlanName(eligibilityDetailV3.getPlan());
				}
				
				patientPbmDrugHistoryListForPBMEligblity.add(patientPbmDrugHistoryDetailPBMEligblity);
				
				//eligibilityDetailResultV3List.add(elDetailResultV3);
			}
			else if (pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")) {
				getAuditInfo().setComment(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("GetPBMEligibilityV3 WSDL");
				getAuditInfo().setCreateTime(new Date());
			    elDetailResultV3=new EligibilityDetailResultV3();
				userService.saveWSFailStatus(auditInfo);
			}
		 }
	   }
	    System.out.println("patientPbmDrugHistoryListForPBMEligblity"+patientPbmDrugHistoryListForPBMEligblity.size());
			
			return patientPbmDrugHistoryListForPBMEligblity;
			
		}
		
	   /**
	    * Convert All Patient Detail in Base64 Format 
	    * @param providerLocation
	    * @param roleSecurity
	    * @param patientDetail
	    * @return
	    */
	  private ArrayList<PatientPbmDrugHistoryDetail> convertPatientDetailStringInBase64Format(
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			UserLoginDetail patientDetail) {
		// TODO Auto-generated method stub
		String patientDetailBase64FormatData = "";
		ArrayList<String> patientDetailBase64FormatDataList=new ArrayList<String>();
		ArrayList<PatientPbmDrugHistoryDetail> patientPBMBase64DetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		try {
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetails= xmlGenrationForPatientDetailData(providerLocation,roleSecurity,
																				patientDetail);

			for(PatientPbmDrugHistoryDetail patientDetailXml:patientPbmDrugHistoryDetails){
				if(!patientDetailXml.getPatientXmlResult().equals("")){
					PatientPbmDrugHistoryDetail patientDetailBase64Data=new PatientPbmDrugHistoryDetail();
					byte[] encodedPatientDetail = Base64.encodeBase64(patientDetailXml.getPatientXmlResult()
							.getBytes());
					System.out.println("Encoded Patient Detail " + encodedPatientDetail
							+ "        In String Format  "
							+ new String(encodedPatientDetail));
					byte[] decoded = Base64.decodeBase64(encodedPatientDetail);
					System.out.println("Decode value " + new String(decoded));
					patientDetailBase64FormatData = new String(encodedPatientDetail);
					patientDetailBase64Data.setPatientXmlResult(patientDetailBase64FormatData);
					patientDetailBase64Data.setPatientId(patientDetailXml.getPatientId());
					patientPBMBase64DetailList.add(patientDetailBase64Data);
					
					
					}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patientPBMBase64DetailList;
		
	}

	  private List<PatientPbmDrugHistoryDetail> xmlGenrationForPatientDetailData(
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			UserLoginDetail patDetailData) {
		  logger.info("getRoleSecurity:::xmlGenrationForPatientDetailData::::"+roleSecurity.getId()+":::"+roleSecurity.getRoleName()+":::"+roleSecurity.getRoleType()+":::"+roleSecurity.getNewCropRole());  
		  String xmlResult = "";
			
			/*	List<String> xmlResultList=new ArrayList<String>();*/
				List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
			DoctorDetail doctorDetail = getDoctorDetailData();
			try {
				/*for(UserLoginDetail patDetailData:patientDetailData){*/
					
				String usrAccountId = providerLocation.getAccountId();
				String usrAccountName=doctorDetail.getfName()+" "+doctorDetail.getlName();
				String usrAddress1 = doctorDetail.getStreet();
				String usrAddress2 = doctorDetail.getStreet();
				String usrCity = doctorDetail.getCity();
				String usrState = doctorDetail.getState();
				String usrZip = doctorDetail.getPincode();;
				/*String usrZip4 = Integer.toString(providerLocation.getZip4Code());*/
				String usrCountry = doctorDetail.getCountry();
				String usrPrimaryPhoneNumber = providerLocation.getPrimaryPhoneNumber();
				String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
				String usrLocId = Integer.toString(providerLocation.getId());
				String usrLocationName = providerLocation.getLocation();

				String locAddress1 = providerLocation.getAddressLine1();
				String locAddress2 = providerLocation.getAddressLine2();
				String locCity = providerLocation.getCity();
				String locState = providerLocation.getState();
				String locZip = providerLocation.getZipCode();
			/*	String locZip4 = Integer.toString(providerLocation.getZip4Code());*/
				String locCountry = providerLocation.getCountry();
				String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

				String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
				String doctorFirstName = doctorDetail.getfName();
				String doctorLastName = doctorDetail.getlName();
				String doctorMiddleName = doctorDetail.getMidName();
				/*String namePrefix = "Mr.";
				String nameSuffix = "Jr";*/
				String prescDea = doctorDetail.getDea();
				String prescUpin = doctorDetail.getUpin();
				String prescLicenseState = doctorDetail.getDocLicenseState();
				String prescNpi = doctorDetail.getNpi();
				String prescLicenseNumber = doctorDetail.getDocLicenseNumber();
                
				String patientId=new String();
				
				 patientId = Integer.toString(patDetailData.getUserId());
				 String pLastName=new String();
				 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getLastName())){
					 pLastName = patDetailData.getLastName().trim();
				 }
				 else {
					 pLastName="";
				}
				 String pFirstName=new String();
				 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getFirstName())){
					 pFirstName = patDetailData.getFirstName().trim();
				 }
				else {
					 pFirstName="";
				}
				 String pMiddleName=new String();
				 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getMiddleName())){
					 pMiddleName = patDetailData.getMiddleName().trim();
				 }
				else {
					 pMiddleName="";
				}
				 String patMedicalRecordNumber=new String();
				
				patMedicalRecordNumber = Integer.toString(patDetailData.getUserId());
				String patAddress1=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getDoorNo())){
					patAddress1 = patDetailData.getDoorNo().trim();
				}
				else {
					patAddress1="";
				}
				String patAddress2=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getStreet())){
					patAddress2=patDetailData.getStreet().trim();
				}
				else {
					patAddress2="";
				}
				String patCity=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getCity())){
					patCity = patDetailData.getCity().trim();
				}
				else {
					patCity="";
				}
				String  patState=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getState())){
					 patState = patDetailData.getState().trim();
				}
				else {
					patState="";
				}
				String patZip=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getPincode())){
					patZip = patDetailData.getPincode().trim();
				}
				else {
					patZip="";
				}
				String patCountry=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getCountry())){
					patCountry = patDetailData.getCountry().trim();
				}
				else {
					patCountry="";
				}
				String patHomeTelephone=new String();
				
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getPhoneNumber())){
				patHomeTelephone = patDetailData.getPhoneNumber().trim();
				}
				else {
					patHomeTelephone="";
				}
				
				Date paDob = patDetailData.getDateOfBirth();
				DateFormat df = new SimpleDateFormat("yyyyMMdd");
				String patDOB = df.format(paDob);
				String gender=new String();
				if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getGender())){
					 gender = patDetailData.getGender().trim();
				}
				else {
					gender="";
				}
				
				String patGender = new String();
				if (gender.equalsIgnoreCase("Male")) {
						patGender = "M";
				 } 
				else if (gender.equalsIgnoreCase("Others")) {
					patGender = "U";
				}
				else {
					patGender = "F";
				}
				
				System.out.println("First Name  " + patDetailData.getFirstName() +"Last Name"
						+ patDetailData.getLastName() + "address 1 " + " patAddress1 "
						+ patDetailData.getDoorNo() +" Address2 "+ patDetailData.getStreet());
				System.out.println("City State Zip " + patDetailData.getCity()
						+ patDetailData.getState() + patDetailData.getPincode() +"paDob  "+paDob+"patDOB  "+patDOB+"patGender"+patGender
						+"patHomeTelephone "+patHomeTelephone +"patAddress1"+patAddress1+"patDetailData.getPinCode:::::::"+patZip);

				// Instance of Document and Document Builder

				DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory
						.newDocumentBuilder();

				org.w3c.dom.Document document = documentBuilder.newDocument();
						
				// Root Element NcScript

				Element ncScript = document.createElement("NCScript");
				document.appendChild(ncScript);

				Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
				ncScriptXsd.setValue(getProperties().getProperty("xsd"));
				ncScript.setAttributeNode(ncScriptXsd);
				Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
				ncScriptXsi.setValue(getProperties().getProperty("xsi"));
				ncScript.setAttributeNode(ncScriptXsd);
				Attr ncScriptXmlns = document.createAttribute("xmlns");
				ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
				ncScript.setAttributeNode(ncScriptXmlns);
				// Credentials Element

				Element credentials = document.createElement("Credentials");
				ncScript.appendChild(credentials);
				Element partnerName = document.createElement("partnerName");
				partnerName.appendChild(document.createTextNode(getProperties()
						.getProperty("partnerName")));
				credentials.appendChild(partnerName);
				Element name = document.createElement("name");
				name.appendChild(document.createTextNode(getProperties()
						.getProperty("name")));
				credentials.appendChild(name);
				Element password = document.createElement("password");
				password.appendChild(document.createTextNode(getProperties()
						.getProperty("password")));
				credentials.appendChild(password);
				Element productName = document.createElement("productName");
				productName.appendChild(document.createTextNode(getProperties()
						.getProperty("productName")));
				credentials.appendChild(productName);
				Element productVersion = document.createElement("productVersion");
				productVersion.appendChild(document.createTextNode(getProperties()
						.getProperty("productVersion")));
				credentials.appendChild(productVersion);
				// User Role Element

				Element userRole = document.createElement("UserRole");
				ncScript.appendChild(userRole);
				Element user = document.createElement("user");
				user.appendChild(document.createTextNode(roleSecurity.getRoleType()));
				userRole.appendChild(user);
				Element role = document.createElement("role");
				role.appendChild(document.createTextNode(roleSecurity.getNewCropRole()));
				userRole.appendChild(role);

				// Destination Element
				Element destination = document.createElement("Destination");
				ncScript.appendChild(destination);
				Element requestedPage = document.createElement("requestedPage");
				requestedPage.appendChild(document
						.createTextNode("ws-pbm-eligibility"));
				destination.appendChild(requestedPage);

				// Account Element
				Element account = document.createElement("Account");
				ncScript.appendChild(account);
				Attr accAttr = document.createAttribute("ID");
				accAttr.setValue(providerLocation.getAccountId());
				account.setAttributeNode(accAttr);
				Element accountName = document.createElement("accountName");
				accountName.appendChild(document.createTextNode(usrAccountName
						));
				account.appendChild(accountName);
				Element siteId = document.createElement("siteID");
				siteId.appendChild(document.createTextNode(providerLocation.getSiteId()
						));
				account.appendChild(siteId);

				Element accountAddress = document.createElement("AccountAddress");
				account.appendChild(accountAddress);
				Element accAddress1 = document.createElement("address1");
				accAddress1.appendChild(document.createTextNode(usrAddress1));
				accountAddress.appendChild(accAddress1);
				
				Element accAddress2 = document.createElement("address2");
				accAddress2.appendChild(document.createTextNode(usrAddress2));
				accountAddress.appendChild(accAddress2);
				
				Element accCity = document.createElement("city");
				accCity.appendChild(document.createTextNode(usrCity));
				accountAddress.appendChild(accCity);
				Element accState = document.createElement("state");
				accState.appendChild(document.createTextNode(usrState));
				accountAddress.appendChild(accState);
				Element accZip = document.createElement("zip");
				accZip.appendChild(document.createTextNode(usrZip));
				accountAddress.appendChild(accZip);
				
				/*Element accZip4 = document.createElement("zip4");
				accZip4.appendChild(document.createTextNode(usrZip4));
				accountAddress.appendChild(accZip4);*/
				
				Element accCountry = document.createElement("country");
				accCountry.appendChild(document.createTextNode(usrCountry));
				accountAddress.appendChild(accCountry);

				Element accountPrimaryPhoneNumber = document
						.createElement("accountPrimaryPhoneNumber");
				accountPrimaryPhoneNumber.appendChild(document
						.createTextNode(usrPrimaryPhoneNumber));
				account.appendChild(accountPrimaryPhoneNumber);
				Element accountPrimaryFaxNumber = document
						.createElement("accountPrimaryFaxNumber");
				accountPrimaryFaxNumber.appendChild(document
						.createTextNode(usrPrimaryFaxNumber));
				account.appendChild(accountPrimaryFaxNumber);

				// Location Element
				Element location = document.createElement("Location");
				ncScript.appendChild(location);
				Attr locAttr = document.createAttribute("ID");
				locAttr.setValue(usrLocId);
				location.setAttributeNode(locAttr);
				Element locationName = document.createElement("locationName");
				locationName.appendChild(document.createTextNode(usrLocationName));
				location.appendChild(locationName);

				Element locationAddress = document.createElement("LocationAddress");
				location.appendChild(locationAddress);
				Element lAddress1 = document.createElement("address1");
				lAddress1.appendChild(document.createTextNode(locAddress1));
				locationAddress.appendChild(lAddress1);
				
				Element lAddress2 = document.createElement("address2");
				lAddress2.appendChild(document.createTextNode(locAddress2));
				locationAddress.appendChild(lAddress2);
				
				Element lCity = document.createElement("city");
				lCity.appendChild(document.createTextNode(locCity));
				locationAddress.appendChild(lCity);
				Element lState = document.createElement("state");
				lState.appendChild(document.createTextNode(locState));
				locationAddress.appendChild(lState);
				Element lZip = document.createElement("zip");
				lZip.appendChild(document.createTextNode(locZip));
				locationAddress.appendChild(lZip);
				
				/*Element lZip4 = document.createElement("zip4");
				lZip4.appendChild(document.createTextNode(locZip4));
				locationAddress.appendChild(lZip4);*/
				
				
				Element lCountry = document.createElement("country");
				lCountry.appendChild(document.createTextNode(locCountry));
				locationAddress.appendChild(lCountry);

				Element primaryPhoneNumber = document
						.createElement("primaryPhoneNumber");
				primaryPhoneNumber.appendChild(document
						.createTextNode(usrPrimaryPhoneNumber));
				location.appendChild(primaryPhoneNumber);
				Element primaryFaxNumber = document
						.createElement("primaryFaxNumber");
				primaryFaxNumber.appendChild(document
						.createTextNode(usrPrimaryFaxNumber));
				location.appendChild(primaryFaxNumber);
				Element pharmacyContactNumber = document
						.createElement("pharmacyContactNumber");
				pharmacyContactNumber.appendChild(document
						.createTextNode(usrPharmacyContactNumber));
				location.appendChild(pharmacyContactNumber);

				// LicensedPrescriber Element

				Element licensedPrescriber = document
						.createElement("LicensedPrescriber");
				ncScript.appendChild(licensedPrescriber);
				Attr licensedPrescriberAttr = document.createAttribute("ID");
				licensedPrescriberAttr.setValue(licensedPrescriberId);
				licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
				Element licensedPrescriberName = document
						.createElement("LicensedPrescriberName");
				licensedPrescriber.appendChild(licensedPrescriberName);
				Element docLastName = document.createElement("last");
				docLastName.appendChild(document.createTextNode(doctorLastName));
				licensedPrescriberName.appendChild(docLastName);
				Element docFirstName = document.createElement("first");
				docFirstName.appendChild(document.createTextNode(doctorFirstName));
				licensedPrescriberName.appendChild(docFirstName);
				Element docMidName = document.createElement("middle");
				docMidName.appendChild(document.createTextNode(doctorMiddleName));
				licensedPrescriberName.appendChild(docMidName);
				
				/*Element prefix = document.createElement("prefix");
				prefix.appendChild(document.createTextNode(namePrefix));
				licensedPrescriberName.appendChild(prefix);
				Element suffix = document.createElement("suffix");
				suffix.appendChild(document.createTextNode(nameSuffix));
				licensedPrescriberName.appendChild(suffix);*/

				Element dea = document.createElement("dea");
				dea.appendChild(document.createTextNode(prescDea));
				licensedPrescriber.appendChild(dea);
				
				/*Element upin = document.createElement("upin");
				upin.appendChild(document.createTextNode(prescUpin));
				licensedPrescriber.appendChild(upin);*/
				
				Element licenseState = document.createElement("licenseState");
				licenseState
						.appendChild(document.createTextNode(prescLicenseState));
				licensedPrescriber.appendChild(licenseState);
				Element licenseNumber = document.createElement("licenseNumber");
				licenseNumber.appendChild(document
						.createTextNode(prescLicenseNumber));
				licensedPrescriber.appendChild(licenseNumber);
				Element npi = document.createElement("npi");
				npi.appendChild(document.createTextNode(prescNpi));
				licensedPrescriber.appendChild(npi);

				// Patient Element

				Element patient = document.createElement("Patient");
				ncScript.appendChild(patient);
				Attr patientIdAttr = document.createAttribute("ID");
				patientIdAttr.setValue(patientId);
				patient.setAttributeNode(patientIdAttr);
				Element patientName = document.createElement("PatientName");
				patient.appendChild(patientName);
				Element patLastName = document.createElement("last");
				patLastName.appendChild(document.createTextNode(pLastName));
				patientName.appendChild(patLastName);
				Element patFirstName = document.createElement("first");
				patFirstName.appendChild(document.createTextNode(pFirstName));
				patientName.appendChild(patFirstName);
				Element patMiddleName = document.createElement("middle");
				patMiddleName.appendChild(document.createTextNode(pMiddleName));
				patientName.appendChild(patMiddleName);

				Element medicalRecordNumber = document
						.createElement("medicalRecordNumber");
				medicalRecordNumber.appendChild(document
						.createTextNode(patMedicalRecordNumber));
				patient.appendChild(medicalRecordNumber);

				Element patientAddress = document.createElement("PatientAddress");
				patient.appendChild(patientAddress);
				Element pAddress1 = document.createElement("address1");
				pAddress1.appendChild(document.createTextNode(patAddress1));
				patientAddress.appendChild(pAddress1);
				Element pAddress2 = document.createElement("address2");
				pAddress1.appendChild(document.createTextNode(patAddress2));
				patientAddress.appendChild(pAddress2);
				Element pCity = document.createElement("city");
				pCity.appendChild(document.createTextNode(patCity));
				patientAddress.appendChild(pCity);
				Element pState = document.createElement("state");
				pState.appendChild(document.createTextNode(patState));
				patientAddress.appendChild(pState);
				Element pZip = document.createElement("zip");
				pZip.appendChild(document.createTextNode(patZip));
				patientAddress.appendChild(pZip);
				Element pCountry = document.createElement("country");
				pCountry.appendChild(document.createTextNode(patCountry));
				patientAddress.appendChild(pCountry);

				Element patientContact = document.createElement("PatientContact");
				patient.appendChild(patientContact);
				Element pHomeTelephone = document.createElement("homeTelephone");
				
				pHomeTelephone.appendChild(document.createTextNode(patHomeTelephone));
				
				patientContact.appendChild(pHomeTelephone);

				Element patientCharacteristics = document
						.createElement("PatientCharacteristics");
				patient.appendChild(patientCharacteristics);
				Element pDob = document.createElement("dob");
				pDob.appendChild(document.createTextNode(patDOB));
				patientCharacteristics.appendChild(pDob);
				Element pGender = document.createElement("gender");
				pGender.appendChild(document.createTextNode(patGender));
				patientCharacteristics.appendChild(pGender);

				// Write code into XML File
				if(document!=null){
					TransformerFactory transformerFactory = TransformerFactory
							.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
		             
					StringWriter stringWriter = new StringWriter();
					StreamResult result = new StreamResult(stringWriter);
					System.out.println("-----------------");
					String output=stringWriter.getBuffer().toString();
					System.out.println("-----------------");
					DOMSource domSource = new DOMSource(document);
					if(document!=null){
						transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
						domSource = new DOMSource(document);
						OutputFormat format = new OutputFormat(document);
				        format.setIndenting(true);
				        XMLSerializer serializer = new XMLSerializer(System.out, format);
				        serializer.serialize(document);
				       
				      
					}
					if((domSource!=null && (result!=null))){
						System.out.println("domSource"+domSource+"documentttttttt"+document);
						try {
							transformer.transform(domSource, result);
						}catch(TransformerException e){
							e.printStackTrace();
						}
						catch (NullPointerException e) {
							e.printStackTrace();
						}catch(Exception e){
							e.printStackTrace();
						}
						
					}
					

					// Convert Xml format to one line String

					xmlResult = stringWriter.toString();
					PatientPbmDrugHistoryDetail drugHistoryDetail =new PatientPbmDrugHistoryDetail();
					drugHistoryDetail.setPatientXmlResult(xmlResult);
					drugHistoryDetail.setPatientId(patDetailData.getUserId());
					patientPbmDrugHistoryDetailList.add(drugHistoryDetail);
					//xmlResultList.add(xmlResult);
					System.out.println("XMl Result.........." + xmlResult);
				}

			/*}*/

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return patientPbmDrugHistoryDetailList;
		
	}
	  
	  /**
	   * 
	   * @param timeperiod
	   * @param providerLocation
	   * @param roleSecurity
	   * @param patientDetail
	   */
	  public List<PatientPbmDrugHistoryDetail> callGetPBMDrugHistoryV2(int timeperiod, ProviderLocation providerLocation, RoleSecurity roleSecurity,
			  										UserLoginDetail patientDetail) {
		 // logger.info("*********callGetPBMDrugHistoryV2 starts*****************batchDataPulling process optimization="+new Date());
		  String accId = providerLocation.getAccountId();
			String siteId = providerLocation.getSiteId();
			String xmlResponse ="";
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
			List<DrugHistoryDetailResult> drugHistoryDetailResultList=new ArrayList<DrugHistoryDetailResult>();
			System.out.println("timeperiod in nc update ws bean "+timeperiod);
			 try{
			
			String partName = getProperties().getProperty("partnerName");
			String name = getProperties().getProperty("name");
			String password = getProperties().getProperty("password");
			logger.info("password:::::::::::"+password);
		
	     
			AccountRequest accountRequest = WS_CLIENT_FACTORY
					.createAccountRequest();
			accountRequest.setAccountId(accId);
			accountRequest.setSiteId(siteId);

			Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
			credentials.setName(name);
			credentials.setPartnerName(partName);
			credentials.setPassword(password);
	        
			
			
			List<PatientPbmDrugHistoryDetail> eligibilityDetailResultV3List = callGetPBMEligibilityV3(providerLocation,roleSecurity,patientDetail);
			System.out.println("eligibilityDetailResultV3List :::::::::::::"+eligibilityDetailResultV3List);
			for(PatientPbmDrugHistoryDetail eligibilityDetailResultV3:eligibilityDetailResultV3List){
				String eligibilityTransactionId = eligibilityDetailResultV3.getPatientPbmEligblityGuid();
				System.out.println("eligibilityTransactionId ::::::::::"+eligibilityTransactionId);

				GetPBMDrugHistoryV2 pbmDrugHistoryV2Request = WS_CLIENT_FACTORY
						.createGetPBMDrugHistoryV2();
				pbmDrugHistoryV2Request.setAccountRequest(accountRequest);
				pbmDrugHistoryV2Request.setCredentials(credentials);
				pbmDrugHistoryV2Request
						.setEligibilityTransactionId(eligibilityTransactionId);
				pbmDrugHistoryV2Request.setIncludeSchema("N");
				pbmDrugHistoryV2Request.setMonthsPrior(timeperiod);

				WebServiceClientImpl<GetPBMDrugHistoryV2> wsClientForPBMDrugHistoryV2 = new WebServiceClientImpl<GetPBMDrugHistoryV2>();
				GetPBMDrugHistoryV2Response pbmDrugHistoryV2Response = (GetPBMDrugHistoryV2Response) wsClientForPBMDrugHistoryV2
						.sendRequestAndReceiveResponse

						(pbmDrugHistoryV2Request, Update1ServiceDetail);
				
				System.out.println("pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus()"+pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus());
				
				if(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().value().equals("OK")){
					PatientPbmDrugHistoryDetail pbmDrugHistoryDetail=new PatientPbmDrugHistoryDetail();
				
					DrugHistoryDetailResult drugHistoryDetailResult = pbmDrugHistoryV2Response
							.getGetPBMDrugHistoryV2Result();
					pbmDrugHistoryDetail.setPbmDrugHistoryXmlResponse(drugHistoryDetailResult.getResult().getXmlResponse());
					pbmDrugHistoryDetail.setPatientId(eligibilityDetailResultV3.getPatientId());
					pbmDrugHistoryDetail.setPatientPbmEligblityGuid(eligibilityDetailResultV3.getPatientPbmEligblityGuid());
					pbmDrugHistoryDetail.setPbmEligblityXmlResponse(eligibilityDetailResultV3.getPbmEligblityXmlResponse());
					pbmDrugHistoryDetail.setPharmacyBenefit(eligibilityDetailResultV3.getPharmacyBenefit());
					pbmDrugHistoryDetail.setMailOrderBenefit(eligibilityDetailResultV3.getMailOrderBenefit());
					pbmDrugHistoryDetail.setSource(eligibilityDetailResultV3.getSource());
					pbmDrugHistoryDetail.setHealthPlanName(eligibilityDetailResultV3.getHealthPlanName());
					pbmDrugHistoryDetail.setPbm(eligibilityDetailResultV3.getPbm());
					pbmDrugHistoryDetail.setRequestDate(new Date());
					
					patientPbmDrugHistoryDetailList.add(pbmDrugHistoryDetail);
					//drugHistoryDetailResultList.add(drugHistoryDetailResult);
					
					//Thread.sleep(60000);
					
				}
				else if (!(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().equals("OK"))) {
					try {
						getAuditInfo().setComment(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getMessage());
						getAuditInfo().setUserId(new ContextUtil().getLoginId());
						getAuditInfo().setMachineName("GetPBMDrugHistoryV2");
						getAuditInfo().setCreateTime(new Date());
						userService.saveWSFailStatus(auditInfo);
					} catch(NullPointerException ne){
						ne.printStackTrace();
					}
					catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					
				}
			}
			
			
	      }
	      catch(SocketException se){
	      	se.printStackTrace();
	      }
	      catch (NullPointerException ne) {
				// TODO: handle exception
	      	ne.printStackTrace();
	      	
			}
	      catch (Exception e) {
				// TODO: handle exception
	      	e.printStackTrace();
			}
	// logger.info("*********callGetPBMDrugHistoryV2 ends*****************batchDataPulling process optimization="+new Date());
			return patientPbmDrugHistoryDetailList;
			

			
			
	  }
	  
	 /**
	  * overloaded method for decode in Base64 for PBM Drug History Response 
	  * @param i
	  * @param providerLocation
	  * @param roleSecurity
	  * @param patientPbmDrugHistoryDetailList
	  */
	  
	  public ArrayList<PatientPBMDrugHistoryResult> decodeBase64XmlResonseOfPBMDrugHistory(int timePeriod,
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList) {
		  
		  
		   ArrayList<PatientPBMDrugHistoryResult> decodeDrugHistoryResultList=new ArrayList<PatientPBMDrugHistoryResult>();
		 
		   String decodedResult="";
		   for(PatientPbmDrugHistoryDetail pbmDrugHistoryDetail:patientPbmDrugHistoryDetailList){
			   PatientPBMDrugHistoryResult drugHistoryResult=new PatientPBMDrugHistoryResult();
			   if(!pbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse().equals("")){
					Base64 decoder = new Base64();
					byte[] decodedByte = (byte[]) decoder.decode(pbmDrugHistoryDetail.getPbmDrugHistoryXmlResponse()
							.getBytes());
					 decodedResult = new String(decodedByte);
					 drugHistoryResult.setPbmXmlInBase64DecodeResult(decodedResult);
					 drugHistoryResult.setPatientId(pbmDrugHistoryDetail.getPatientId());
					 drugHistoryResult.setPatientPbmEligblityGuid(pbmDrugHistoryDetail.getPatientPbmEligblityGuid());
					 decodeDrugHistoryResultList.add(drugHistoryResult);
					 System.out.println( "decodedResult   :::"+ decodedResult);
					 logger.info("decodedResult   :::"+ decodedResult);
				
					}
		   }
			
			
			return decodeDrugHistoryResultList;
		
		
	  }
	  
	  
	  /**
	   * 
	   * @param i
	   * @param providerLocation
	   * @param roleSecurity
	   * @param patientPbmDrugHistoryDetailList
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	   */

	public List<PatientPBMDrugHistoryResult> convertPBMDrugHistoryXMLToSaxParser(int timePeriod,
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList) throws ParserConfigurationException, SAXException, IOException {
		logger.info("<<<<<<<<<<<<<<<<<METHOD convertPBMDrugHistoryXMLToSaxParser CALLED IN NcUpdate1WSBean>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		List<DrugHistoryDetail> drugHistoryDetails = new ArrayList<DrugHistoryDetail>();
		List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResults = decodeBase64XmlResonseOfPBMDrugHistory(timePeriod,providerLocation,roleSecurity,patientPbmDrugHistoryDetailList);
	     
		List<PatientPBMDrugHistoryResult> pbmDrugHistoryResultList=new ArrayList<PatientPBMDrugHistoryResult>();
		for( PatientPBMDrugHistoryResult pbmDrugHistoryResult:patientPBMDrugHistoryResults){
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();

			if (!(pbmDrugHistoryResult.getPbmXmlInBase64DecodeResult().equals(""))) {

				InputSource inputSource = new InputSource(new StringReader(
						pbmDrugHistoryResult.getPbmXmlInBase64DecodeResult()));
				org.w3c.dom.Document document = documentBuilder.parse(inputSource);

				Element docElement = document.getDocumentElement();
				NodeList node = docElement.getChildNodes();
				System.out.println("Total nodes in xml File" + node.getLength());
				
				if (node != null && node.getLength() > 0) {
					for (int i = 0; i < node.getLength(); i++) {
						String doctorFirstName = new String();
						String doctorLastName =new String();
						String drugInfo = new String();
						String drugQuantity = new String();
						String date=new String();
						String ndc = new String();
						String drugId=new String();
						String healthPlanName=new String();
						String pharmacyName=new String();
						String pharmacyContactNumber=new String();
						String daysSupply=new String();
						String sig=new String();
						String drugHistoryTransactionGuid=new String();
						String doctorContactNumber=new String();
						String source=new String();

						PatientPBMDrugHistoryResult drugHistoryResult=new PatientPBMDrugHistoryResult();
						try{
						if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
							Element e1 = (Element) node.item(i);
							System.out.println("Eleement "
									+ e1.getElementsByTagName("DrugQuantity")
											.item(0).getLocalName());
						
							if (e1.getNodeName().contains("Table")) {

								System.out.println(e1.getNodeName()
										+ " Node value child value length "
										+ e1.getChildNodes().getLength());
								
									if(!(e1.getElementsByTagName("DrugInfo").item(0).getFirstChild()==null)){
										drugInfo = e1.getElementsByTagName("DrugInfo")
												.item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDrugInfo(drugInfo);
									}
									
									if(!(e1.getElementsByTagName("DoctorFirstName").item(0).getFirstChild()==null)){
										doctorFirstName = e1
												.getElementsByTagName("DoctorFirstName")
												.item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDoctorFirstName(doctorFirstName);
									}
											
									if(!(e1.getElementsByTagName("DoctorLastName").item(0).getFirstChild()== null)){
											doctorLastName = e1
													.getElementsByTagName("DoctorLastName")
													.item(0).getFirstChild().getNodeValue();
											//drugHistoryResult.setDoctorLastName(doctorLastName);
									}
									if(!(e1.getElementsByTagName("DrugQuantity").item(0).getFirstChild()== null)){
										drugQuantity = e1
												.getElementsByTagName("DrugQuantity")
												.item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDrugQuantity(drugQuantity);
									}
													
									if(!(e1.getElementsByTagName("ScriptDate").item(0)==null)){
										if(!(e1.getElementsByTagName("ScriptDate").item(0).getFirstChild()==null)){
											date=e1.getElementsByTagName("ScriptDate").item(0).getFirstChild().getNodeValue();
											//drugHistoryResult.setFillDate(date);
										}
										
									}
									
									if(!(e1.getElementsByTagName("NDC").item(0)==null)){
										if(!(e1.getElementsByTagName("NDC").item(0).getFirstChild()==null)){
											ndc = e1.getElementsByTagName("NDC").item(0)
													.getFirstChild().getNodeValue();
											//drugHistoryResult.setDrugNdc(ndc);
										}
										
									}
									
									
									
										if(!(e1.getElementsByTagName("HealthplanName").item(0)==null)){
											if(!(e1.getElementsByTagName("HealthplanName").item(0).getFirstChild()==null)){
												healthPlanName=e1.getElementsByTagName("HealthplanName").item(0).getFirstChild().getNodeValue();
												//drugHistoryResult.setHealthPlanName(healthPlanName);
											}
											
										}
							
									
									if(!(e1.getElementsByTagName("PharmacyName").item(0).getFirstChild()==null)){
										pharmacyName=e1.getElementsByTagName("PharmacyName").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setPharmacyName(pharmacyName);
									}
									if(!(e1.getElementsByTagName("PharmacyContactNumber").item(0).getFirstChild()==null)){
										pharmacyContactNumber=e1.getElementsByTagName("PharmacyContactNumber").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setPharmacyContactNumber(pharmacyContactNumber);
									}
									if(!(e1.getElementsByTagName("DaysSupply").item(0).getFirstChild()==null)){
										daysSupply=e1.getElementsByTagName("DaysSupply").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDaysSupply(daysSupply);
									}
									/*if(e1.getElementsByTagName("Sig").item(0)!=null){
										sig=e1.getElementsByTagName("Sig").item(0).getFirstChild().getNodeValue();
										drugHistoryResult.setSig(sig);
									}*/
									if(!(e1.getElementsByTagName("DrugHistoryTransactionGuid").item(0).getFirstChild()==null)){
										drugHistoryTransactionGuid=e1.getElementsByTagName("DrugHistoryTransactionGuid").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDrugHistoryTransactionGuid(drugHistoryTransactionGuid);
									}
									if(!(e1.getElementsByTagName("DoctorContactNumber").item(0).getFirstChild()==null)){
										doctorContactNumber=e1.getElementsByTagName("DoctorContactNumber").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setDoctorContactNumber(doctorContactNumber);
									}
									if(!(e1.getElementsByTagName("Source").item(0).getFirstChild()==null)){
										source=e1.getElementsByTagName("Source").item(0).getFirstChild().getNodeValue();
										//drugHistoryResult.setSource(source);
									}
								
									if(!(e1.getElementsByTagName("DrugID").item(0)==null)){
										if(!(e1.getElementsByTagName("DrugID").item(0).getFirstChild()==null)){
											drugId=e1.getElementsByTagName("DrugID").item(0).getFirstChild().getNodeValue();
											//drugHistoryResult.setDrugId(drugId);
										}
									}
									
									logger.info("2drugId:::::"+drugId+"druginfo::::"+drugInfo);	
								

							}
							
							
							drugHistoryResult.setDoctorFirstName(doctorFirstName);
							drugHistoryResult.setDoctorLastName(doctorLastName);
							drugHistoryResult.setDrugInfo(drugInfo);
							drugHistoryResult.setDrugQuantity(drugQuantity);
							drugHistoryResult.setFillDate(date);
							drugHistoryResult.setDrugNdc(ndc);
							drugHistoryResult.setDrugId(drugId);
							drugHistoryResult.setHealthPlanName(healthPlanName);
							drugHistoryResult.setPharmacyName(pharmacyName);
							drugHistoryResult.setPharmacyContactNumber(pharmacyContactNumber);
							drugHistoryResult.setDaysSupply(daysSupply);
							drugHistoryResult.setSig(sig);
							drugHistoryResult.setDrugHistoryTransactionGuid(drugHistoryTransactionGuid);
							drugHistoryResult.setDoctorContactNumber(doctorContactNumber);
							drugHistoryResult.setSource(source);
							
							drugHistoryResult.setPatientId(pbmDrugHistoryResult.getPatientId());
							drugHistoryResult.setPatientPbmEligblityGuid(pbmDrugHistoryResult.getPatientPbmEligblityGuid());
							
							
							
							
							pbmDrugHistoryResultList.add(drugHistoryResult);
							
						}
						
						}
						
						catch(NullPointerException ne){
							//ne.printStackTrace();
							ne.getMessage();
							logger.info("1112drugId:::::"+drugId+"druginfo::::"+drugInfo);
							drugId="";
						}
						
					}

				}
				

			}
			logger.info("pbmDrugHistoryResultList.size()"+pbmDrugHistoryResultList.size());
			
		}
		return pbmDrugHistoryResultList;

		
		
		
		
		
	}
	  
	 /**
	  * Decode Patient PBMEligibility Detail In BASE64 Format 
	 * @param patientPbmDrugHistoryDetailList 
	  * @return
	  */
	 public List<PatientPBMEligibilityDetailData> decodePatientPBMEligblityDetailInBase64(List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList){
		 String  decodedResult="";
		 List<PatientPBMEligibilityDetailData> patientPbmEligibilityDecodeDetailList=new ArrayList<PatientPBMEligibilityDetailData>();
		 for(PatientPbmDrugHistoryDetail pbmDrugHistoryPbmEligibility:patientPbmDrugHistoryDetailList){
			 PatientPBMEligibilityDetailData eligibilityDetailData=new PatientPBMEligibilityDetailData();
			 if(!(pbmDrugHistoryPbmEligibility.getPbmDrugHistoryXmlResponse().equals(""))){
				     Base64 decoder = new Base64();
					 byte[] decodedByte = (byte[]) decoder.decode(pbmDrugHistoryPbmEligibility.getPbmEligblityXmlResponse()
							.getBytes());
					 decodedResult = new String(decodedByte);
					 System.out.println(decodedResult);
					 eligibilityDetailData.setPbmEligibilityDecodedResult(decodedResult);
					 eligibilityDetailData.setPatientId(pbmDrugHistoryPbmEligibility.getPatientId());
					 
					 patientPbmEligibilityDecodeDetailList.add(eligibilityDetailData);
			 }
			 
		 }
		   
		return patientPbmEligibilityDecodeDetailList;
		 
	 }

	   /**
	    * Sax Parser Method for Patient PBM Eligblity XML Response 
	    * @param patientPbmDrugHistoryDetailList
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	    */
	    public List<PatientPBMEligibilityDetailData> parsePatientPbmEligblityXmlResponse(
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList) throws ParserConfigurationException, SAXException, IOException {
		   
	    	
	    	List<PatientPBMEligibilityDetailData> patientPBMEligibilityDetailDataList=new ArrayList<PatientPBMEligibilityDetailData>();
	    	
	    	List<PatientPBMEligibilityDetailData> patientPBMEligibilityDetailXmlDecodedList=decodePatientPBMEligblityDetailInBase64(patientPbmDrugHistoryDetailList);
	    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			for(PatientPBMEligibilityDetailData pbmEligblityDecodedDetail:patientPBMEligibilityDetailXmlDecodedList){
				System.out.println("pbmDrugHistoryDetail.getPbmEligblityXmlResponse()"+pbmEligblityDecodedDetail.getPbmEligibilityDecodedResult());
				InputSource inputSource = new InputSource(new StringReader(
						pbmEligblityDecodedDetail.getPbmEligibilityDecodedResult()));
				org.w3c.dom.Document document = documentBuilder.parse(inputSource);

				Element docElement = document.getDocumentElement();
				NodeList node = docElement.getChildNodes();
				
				if (node != null && node.getLength() > 0) {
					for (int i = 0; i < node.getLength(); i++) {
						if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
							Element e1 = (Element) node.item(i);
							String source="";
							String pbm="";
							String pbmId="";
							String planName="";
							String planId="";
							String coverageListId="";
							String cardholderName="";
							String cardholderId="";
							String ssn="";
							String personCode="";
							String groupNumber="";
							String groupNumberId="";
							String formularyId="";
							String alternateFormularyId="";
							String copayId="";
							String pharmacyBenefit="";
							String mailOrderBenefit="";
							String specialtyPharmacyBenefit="";
							String lTCBenefit="";
							String personType="";
							String nameLast="";
							String nameFirst="";
							String nameMiddle="";
							String address1="";
							String address2="";
							String cityName="";
							String stateCode="";
							String postalCode="";
							String dob="";
							String gender="";
							String subscriberDate="";
							String pharmacyBenefitDescription="";
							String mailOrderBenefitDescription="";
							String specialtyPharmacyBenefitDescription="";
							String ltcBenefitDescription ="";
							String patientDemographicsChanged="";
							String cacheExpiryTimestamp="";
							String benefitLoop="";
							
							if(e1.getNodeName().contains("Table")){
								PatientPBMEligibilityDetailData pbmEligibilityDetailData=new PatientPBMEligibilityDetailData();
								try {
									if(!(e1.getElementsByTagName("Source").item(0).getFirstChild()==null)){
										source=e1.getElementsByTagName("Source").item(0).getFirstChild().getNodeValue();
									}
								 
									if(!(e1.getElementsByTagName("PBM").item(0)==null)){
										if(!(e1.getElementsByTagName("PBM").item(0).getFirstChild()==null)){
											pbm=e1.getElementsByTagName("PBM").item(0).getFirstChild().getNodeValue();
										}
										
									}
									if(!(e1.getElementsByTagName("PBMId").item(0)==null)){
										if(!(e1.getElementsByTagName("PBMId").item(0)==null)){
											pbmId=e1.getElementsByTagName("PBMId").item(0).getFirstChild().getNodeValue();
										}
									}
									if(!(e1.getElementsByTagName("PlanName").item(0).getFirstChild()==null)){
										planName=e1.getElementsByTagName("PlanName").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("PlanId").item(0).getFirstChild()==null)){
										planId=e1.getElementsByTagName("PlanId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("CoverageListId").item(0).getFirstChild()==null)){
										coverageListId=e1.getElementsByTagName("CoverageListId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("CardholderName").item(0).getFirstChild()==null)){
										cardholderName=e1.getElementsByTagName("CardholderName").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("CardholderId").item(0).getFirstChild()==null)){
										cardholderId=e1.getElementsByTagName("CardholderId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("SSN").item(0).getFirstChild()==null)){
										ssn=e1.getElementsByTagName("SSN").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("PersonCode").item(0).getFirstChild()==null)){
										personCode=e1.getElementsByTagName("PersonCode").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("GroupNumber").item(0).getFirstChild()==null)){
										groupNumber=e1.getElementsByTagName("GroupNumber").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("GroupNumberId").item(0).getFirstChild()==null)){
										groupNumberId=e1.getElementsByTagName("GroupNumberId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("formularyId").item(0).getFirstChild()==null)){
										formularyId=e1.getElementsByTagName("formularyId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("AlternateFormularyId").item(0).getFirstChild()==null)){
										alternateFormularyId=e1.getElementsByTagName("AlternateFormularyId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("CopayId").item(0).getFirstChild()==null)){
										copayId=e1.getElementsByTagName("CopayId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("PharmacyBenefit").item(0).getFirstChild()==null)){
										pharmacyBenefit=e1.getElementsByTagName("PharmacyBenefit").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("MailOrderBenefit").item(0).getFirstChild()==null)){
										mailOrderBenefit=e1.getElementsByTagName("MailOrderBenefit").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("SpecialtyPharmacyBenefit").item(0).getFirstChild()==null)){
										specialtyPharmacyBenefit=e1.getElementsByTagName("SpecialtyPharmacyBenefit").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("LTCBenefit").item(0).getFirstChild()==null)){
										lTCBenefit=e1.getElementsByTagName("LTCBenefit").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("PBMPatientId").item(0).getFirstChild()==null)){
										lTCBenefit=e1.getElementsByTagName("PBMPatientId").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("PersonType").item(0).getFirstChild()==null)){
										personType=e1.getElementsByTagName("PersonType").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("NameLast").item(0).getFirstChild()==null)){
										nameLast=e1.getElementsByTagName("NameLast").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("NameFirst").item(0).getFirstChild()==null)){
										nameFirst=e1.getElementsByTagName("NameFirst").item(0).getFirstChild().getNodeValue();
									}
									if(!(e1.getElementsByTagName("NameMiddle").item(0).getFirstChild()==null)){
										nameMiddle=e1.getElementsByTagName("NameMiddle").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("Address1").item(0).getFirstChild()==null)){
										address1=e1.getElementsByTagName("Address1").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("Address2").item(0).getFirstChild()==null)){
										address2=e1.getElementsByTagName("Address2").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("CityName").item(0).getFirstChild()==null)){
										cityName=e1.getElementsByTagName("CityName").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("StateCode").item(0).getFirstChild()==null)){
										stateCode=e1.getElementsByTagName("StateCode").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("PostalCode").item(0).getFirstChild()==null)){
										postalCode=e1.getElementsByTagName("PostalCode").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("DOB").item(0).getFirstChild()==null)){
										dob=e1.getElementsByTagName("DOB").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("Gender").item(0).getFirstChild()==null)){
										gender=e1.getElementsByTagName("Gender").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("SubscriberDate").item(0).getFirstChild()==null)){
										subscriberDate=e1.getElementsByTagName("SubscriberDate").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("PharmacyBenefitDescription").item(0).getFirstChild()==null)){
										pharmacyBenefitDescription=e1.getElementsByTagName("PharmacyBenefitDescription").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("MailOrderBenefitDescription").item(0).getFirstChild()==null)){
										mailOrderBenefitDescription=e1.getElementsByTagName("MailOrderBenefitDescription").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("SpecialtyPharmacyBenefitDescription").item(0).getFirstChild()==null)){
										specialtyPharmacyBenefitDescription=e1.getElementsByTagName("SpecialtyPharmacyBenefitDescription").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("LTCBenefitDescription").item(0).getFirstChild()==null)){
										ltcBenefitDescription=e1.getElementsByTagName("LTCBenefitDescription").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("PatientDemographicsChanged").item(0).getFirstChild()==null)){
										patientDemographicsChanged=e1.getElementsByTagName("PatientDemographicsChanged").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("CacheExpiryTimestamp").item(0).getFirstChild()==null)){
										cacheExpiryTimestamp=e1.getElementsByTagName("CacheExpiryTimestamp").item(0).getFirstChild().getNodeValue();
									} 
									if(!(e1.getElementsByTagName("BenefitLoop").item(0).getFirstChild()==null)){
										specialtyPharmacyBenefitDescription=e1.getElementsByTagName("BenefitLoop").item(0).getFirstChild().getNodeValue();
									} 
									
									pbmEligibilityDetailData.setAddress1(address1);
									pbmEligibilityDetailData.setAddress2(address2);
									pbmEligibilityDetailData.setAlternateFormularyId(alternateFormularyId);
									pbmEligibilityDetailData.setBenefitLoop(benefitLoop);
									pbmEligibilityDetailData.setCacheExpiryTimestamp(cacheExpiryTimestamp);
									pbmEligibilityDetailData.setCardholderId(cardholderId);
									pbmEligibilityDetailData.setCardholderName(cardholderName);
									pbmEligibilityDetailData.setCityName(cityName);
									pbmEligibilityDetailData.setCopayId(copayId);
									pbmEligibilityDetailData.setCoverageListId(coverageListId);
									pbmEligibilityDetailData.setDob(dob);
									pbmEligibilityDetailData.setFormularyId(formularyId);
									pbmEligibilityDetailData.setGroupNumber(groupNumber);
									pbmEligibilityDetailData.setGroupNumberId(groupNumberId);
									pbmEligibilityDetailData.setlTCBenefit(lTCBenefit);
									pbmEligibilityDetailData.setLtcBenefitDescription(ltcBenefitDescription);
									pbmEligibilityDetailData.setMailOrderBenefit(mailOrderBenefit);
									pbmEligibilityDetailData.setMailOrderBenefitDescription(mailOrderBenefitDescription);
									pbmEligibilityDetailData.setNameFirst(nameFirst);
									pbmEligibilityDetailData.setNameLast(nameLast);
									pbmEligibilityDetailData.setNameMiddle(nameMiddle);
									pbmEligibilityDetailData.setPatientDemographicsChanged(patientDemographicsChanged);
									pbmEligibilityDetailData.setPatientId(pbmEligblityDecodedDetail.getPatientId());
									pbmEligibilityDetailData.setPbm(pbm);
									pbmEligibilityDetailData.setPbmId(pbmId);
									pbmEligibilityDetailData.setPersonCode(personCode);
									pbmEligibilityDetailData.setPersonType(personType);
									pbmEligibilityDetailData.setPharmacyBenefit(pharmacyBenefit);
									pbmEligibilityDetailData.setPharmacyBenefitDescription(pharmacyBenefitDescription);
									pbmEligibilityDetailData.setPlanId(planId);
									pbmEligibilityDetailData.setPlanName(planName);
									pbmEligibilityDetailData.setPostalCode(postalCode);
									pbmEligibilityDetailData.setSource(source);
									pbmEligibilityDetailData.setSpecialtyPharmacyBenefit(specialtyPharmacyBenefit);
									pbmEligibilityDetailData.setSpecialtyPharmacyBenefitDescription(specialtyPharmacyBenefitDescription);
									pbmEligibilityDetailData.setSsn(ssn);
									pbmEligibilityDetailData.setStateCode(stateCode);
									pbmEligibilityDetailData.setSubscriberDate(subscriberDate);
									
									
									patientPBMEligibilityDetailDataList.add(pbmEligibilityDetailData);
									
									
									
								} catch (NullPointerException e) {
									// TODO: handle exception
									e.printStackTrace();
								}
								
								
								
							}
						}
					}
				}
			}
			return patientPBMEligibilityDetailDataList;
			
		
	  }

/****@author Saurabh
 * For ForMultiThreading call of batch data pull of patients
 * by admin loggin
 */
public List<PatientPbmDrugHistoryDetail> callGetPBMDrugHistoryV2ForMultiThreading(int timeperiod, ProviderLocation providerLocation, RoleSecurity roleSecurity,
					UserLoginDetail patientDetail,DoctorDetail doctorDetailForMultiThreading, int loginIdForMultiThreading) {

	  //logger.info("*********callGetPBMDrugHistoryV2 starts*****************batchDataPulling process optimization="+new Date());
	  String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String xmlResponse ="";
		List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		List<DrugHistoryDetailResult> drugHistoryDetailResultList=new ArrayList<DrugHistoryDetailResult>();
		System.out.println("timeperiod in nc update ws bean "+timeperiod);
		 try{
		
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		logger.info("password:::::::::::"+password);
		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		List<PatientPbmDrugHistoryDetail> eligibilityDetailResultV3List = callGetPBMEligibilityV3ForMultiThreading(providerLocation,roleSecurity,patientDetail,doctorDetailForMultiThreading);
		System.out.println("eligibilityDetailResultV3List :::::::::::::"+eligibilityDetailResultV3List);
		for(PatientPbmDrugHistoryDetail eligibilityDetailResultV3:eligibilityDetailResultV3List){
			String eligibilityTransactionId = eligibilityDetailResultV3.getPatientPbmEligblityGuid();
			System.out.println("eligibilityTransactionId ::::::::::"+eligibilityTransactionId);

			GetPBMDrugHistoryV2 pbmDrugHistoryV2Request = WS_CLIENT_FACTORY
					.createGetPBMDrugHistoryV2();
			pbmDrugHistoryV2Request.setAccountRequest(accountRequest);
			pbmDrugHistoryV2Request.setCredentials(credentials);
			pbmDrugHistoryV2Request
					.setEligibilityTransactionId(eligibilityTransactionId);
			pbmDrugHistoryV2Request.setIncludeSchema("N");
			pbmDrugHistoryV2Request.setMonthsPrior(timeperiod);

			WebServiceClientImpl<GetPBMDrugHistoryV2> wsClientForPBMDrugHistoryV2 = new WebServiceClientImpl<GetPBMDrugHistoryV2>();
			GetPBMDrugHistoryV2Response pbmDrugHistoryV2Response = (GetPBMDrugHistoryV2Response) wsClientForPBMDrugHistoryV2
					.sendRequestAndReceiveResponse

					(pbmDrugHistoryV2Request, Update1ServiceDetail);
			
			System.out.println("pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus()"+pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus());
			
			if(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().value().equals("OK")){
				PatientPbmDrugHistoryDetail pbmDrugHistoryDetail=new PatientPbmDrugHistoryDetail();
			
				DrugHistoryDetailResult drugHistoryDetailResult = pbmDrugHistoryV2Response
						.getGetPBMDrugHistoryV2Result();
				pbmDrugHistoryDetail.setPbmDrugHistoryXmlResponse(drugHistoryDetailResult.getResult().getXmlResponse());
				pbmDrugHistoryDetail.setPatientId(eligibilityDetailResultV3.getPatientId());
				pbmDrugHistoryDetail.setPatientPbmEligblityGuid(eligibilityDetailResultV3.getPatientPbmEligblityGuid());
				pbmDrugHistoryDetail.setPbmEligblityXmlResponse(eligibilityDetailResultV3.getPbmEligblityXmlResponse());
				pbmDrugHistoryDetail.setPharmacyBenefit(eligibilityDetailResultV3.getPharmacyBenefit());
				pbmDrugHistoryDetail.setMailOrderBenefit(eligibilityDetailResultV3.getMailOrderBenefit());
				pbmDrugHistoryDetail.setSource(eligibilityDetailResultV3.getSource());
				pbmDrugHistoryDetail.setHealthPlanName(eligibilityDetailResultV3.getHealthPlanName());
				pbmDrugHistoryDetail.setPbm(eligibilityDetailResultV3.getPbm());
				pbmDrugHistoryDetail.setRequestDate(new Date());
				pbmDrugHistoryDetail.setPulledBy(loginIdForMultiThreading);
				if (StringUtils.isNotBlank(eligibilityDetailResultV3.getPbmEligblityXmlResponse())) {
					pbmDrugHistoryDetail.setPbmExist(true);
				}
				if (StringUtils.isNotBlank(drugHistoryDetailResult.getResult().getXmlResponse())) {
					pbmDrugHistoryDetail.setDrugHistoryExist(true);
				}
				patientPbmDrugHistoryDetailList.add(pbmDrugHistoryDetail);
				//drugHistoryDetailResultList.add(drugHistoryDetailResult);
				
				//Thread.sleep(60000);
				
			}
			else if (!(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getStatus().equals("OK"))) {
				try {
					getAuditInfo().setComment(pbmDrugHistoryV2Response.getGetPBMDrugHistoryV2Result().getResult().getMessage());
					getAuditInfo().setUserId(new ContextUtil().getLoginId());
					getAuditInfo().setMachineName("GetPBMDrugHistoryV2");
					getAuditInfo().setCreateTime(new Date());
					userService.saveWSFailStatus(auditInfo);
				} catch(NullPointerException ne){
					ne.printStackTrace();
				}
				catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			}
		}
		
		
    }
    catch(SocketException se){
    	se.printStackTrace();
    }
    catch (NullPointerException ne) {
			// TODO: handle exception
    	ne.printStackTrace();
    	
		}
    catch (Exception e) {
			// TODO: handle exception
    	e.printStackTrace();
		}
//	 logger.info("*********callGetPBMDrugHistoryV2 ends*****************batchDataPulling process optimization="+new Date());
		return patientPbmDrugHistoryDetailList;

}

/******@author SAURABH
 * 
 */

public ArrayList<PatientPbmDrugHistoryDetail> callGetPBMEligibilityV3ForMultiThreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
				UserLoginDetail patientDetail,DoctorDetail doctorDetailForMultiThreading) throws Exception {
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String userId = getProperties().getProperty("userId");
		String userType = getProperties().getProperty("userType");
		EligibilityDetailResultV3 elDetailResultV3=new EligibilityDetailResultV3();
		List<EligibilityDetailResultV3> eligibilityDetailResultV3List=new ArrayList<EligibilityDetailResultV3>();
		
		ArrayList<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryListForPBMEligblity=new ArrayList<PatientPbmDrugHistoryDetail>();
		ArrayList<PatientPbmDrugHistoryDetail> patientXmlBase64EncodedDataList = convertPatientDetailStringInBase64FormatForMultiThreading(providerLocation,roleSecurity,
																										patientDetail,doctorDetailForMultiThreading);
		
		for(PatientPbmDrugHistoryDetail patientXmlBase64EncodedData:patientXmlBase64EncodedDataList){
			PatientPbmDrugHistoryDetail patientPbmDrugHistoryDetailPBMEligblity=new PatientPbmDrugHistoryDetail();
		
		if(!patientXmlBase64EncodedData.getPatientXmlResult().equals("")){	
			
		
		String xmlIn = "BASE64:" + patientXmlBase64EncodedData.getPatientXmlResult();
		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);
		
		GetPBMEligibilityV3 pbmEligibilityV3 = WS_CLIENT_FACTORY
				.createGetPBMEligibilityV3();
		pbmEligibilityV3.setAccountRequest(accountRequest);
		pbmEligibilityV3.setCredentials(credentials);
		pbmEligibilityV3.setIncludeSchema("Y");
		pbmEligibilityV3.setPatientInformationRequester(informationRequester);
		pbmEligibilityV3.setXmlIn(xmlIn);
		
		WebServiceClientImpl<GetPBMEligibilityV3> wsClientForPbmEligblityV3 = new WebServiceClientImpl<GetPBMEligibilityV3>();
		GetPBMEligibilityV3Response pbmEligibilityV3Response = (GetPBMEligibilityV3Response) wsClientForPbmEligblityV3
				.sendRequestAndReceiveResponse
				(pbmEligibilityV3, Update1ServiceDetail);
		
		if(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")){
			elDetailResultV3 = pbmEligibilityV3Response
					.getGetPBMEligibilityV3Result();
			
			patientPbmDrugHistoryDetailPBMEligblity.setPatientPbmEligblityGuid(elDetailResultV3.getEligibilityGuid());
			patientPbmDrugHistoryDetailPBMEligblity.setPatientId(patientXmlBase64EncodedData.getPatientId());
			patientPbmDrugHistoryDetailPBMEligblity.setPbmEligblityXmlResponse(elDetailResultV3.getResult().getXmlResponse());
			for(EligibilityDetailV3 eligibilityDetailV3:elDetailResultV3.getEligibilityDetailArray().getEligibilityDetailV3() ){
				patientPbmDrugHistoryDetailPBMEligblity.setPharmacyBenefit(eligibilityDetailV3.getPharmacyBenefit());
				patientPbmDrugHistoryDetailPBMEligblity.setMailOrderBenefit(eligibilityDetailV3.getMailOrderBenefit());
				patientPbmDrugHistoryDetailPBMEligblity.setSource(eligibilityDetailV3.getSource());
				patientPbmDrugHistoryDetailPBMEligblity.setPbm(eligibilityDetailV3.getPBM());
				patientPbmDrugHistoryDetailPBMEligblity.setHealthPlanName(eligibilityDetailV3.getPlan());
			}
			
			patientPbmDrugHistoryListForPBMEligblity.add(patientPbmDrugHistoryDetailPBMEligblity);
			
			//eligibilityDetailResultV3List.add(elDetailResultV3);
		}
		else if (pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")) {
			getAuditInfo().setComment(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("GetPBMEligibilityV3 WSDL");
			getAuditInfo().setCreateTime(new Date());
		    elDetailResultV3=new EligibilityDetailResultV3();
			userService.saveWSFailStatus(auditInfo);
		}
		}
		}
		System.out.println("patientPbmDrugHistoryListForPBMEligblity"+patientPbmDrugHistoryListForPBMEligblity.size());
		
		return patientPbmDrugHistoryListForPBMEligblity;
		
   }

/*****@author SAURABH
 * 
 */
private ArrayList<PatientPbmDrugHistoryDetail> convertPatientDetailStringInBase64FormatForMultiThreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		UserLoginDetail patientDetail,DoctorDetail doctorDetailForMultiThreading) {
		String patientDetailBase64FormatData = "";
		ArrayList<String> patientDetailBase64FormatDataList=new ArrayList<String>();
		ArrayList<PatientPbmDrugHistoryDetail> patientPBMBase64DetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		try {
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetails= xmlGenrationForPatientDetailDataForMultiThreading(providerLocation,roleSecurity,
																				patientDetail,doctorDetailForMultiThreading);
	
			for(PatientPbmDrugHistoryDetail patientDetailXml:patientPbmDrugHistoryDetails){
				if(!patientDetailXml.getPatientXmlResult().equals("")){
					PatientPbmDrugHistoryDetail patientDetailBase64Data=new PatientPbmDrugHistoryDetail();
					byte[] encodedPatientDetail = Base64.encodeBase64(patientDetailXml.getPatientXmlResult()
							.getBytes());
					System.out.println("Encoded Patient Detail " + encodedPatientDetail
							+ "        In String Format  "
							+ new String(encodedPatientDetail));
					byte[] decoded = Base64.decodeBase64(encodedPatientDetail);
					System.out.println("Decode value " + new String(decoded));
					patientDetailBase64FormatData = new String(encodedPatientDetail);
					patientDetailBase64Data.setPatientXmlResult(patientDetailBase64FormatData);
					patientDetailBase64Data.setPatientId(patientDetailXml.getPatientId());
					patientPBMBase64DetailList.add(patientDetailBase64Data);
					
					
					}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientPBMBase64DetailList;
  }

/******@author SAURABH
 * 
 */

private List<PatientPbmDrugHistoryDetail> xmlGenrationForPatientDetailDataForMultiThreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		UserLoginDetail patDetailData,DoctorDetail doctorDetailForMultiThreading) {
	  logger.info("getRoleSecurity:::xmlGenrationForPatientDetailData::::"+roleSecurity.getId()+":::"+roleSecurity.getRoleName()+":::"+roleSecurity.getRoleType()+":::"+roleSecurity.getNewCropRole());  
	  String xmlResult = "";
		
		/*	List<String> xmlResultList=new ArrayList<String>();*/
			List<PatientPbmDrugHistoryDetail> patientPbmDrugHistoryDetailList=new ArrayList<PatientPbmDrugHistoryDetail>();
		DoctorDetail doctorDetail = doctorDetailForMultiThreading;
		try {
			/*for(UserLoginDetail patDetailData:patientDetailData){*/
				
			String usrAccountId = providerLocation.getAccountId();
			String usrAccountName=doctorDetail.getfName()+" "+doctorDetail.getlName();
			String usrAddress1 = doctorDetail.getStreet();
			String usrAddress2 = doctorDetail.getStreet();
			String usrCity = doctorDetail.getCity();
			String usrState = doctorDetail.getState();
			String usrZip = doctorDetail.getPincode();;
			/*String usrZip4 = Integer.toString(providerLocation.getZip4Code());*/
			String usrCountry = doctorDetail.getCountry();
			String usrPrimaryPhoneNumber = providerLocation.getPrimaryPhoneNumber();
			String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
			String usrLocId = Integer.toString(providerLocation.getId());
			String usrLocationName = providerLocation.getLocation();

			String locAddress1 = providerLocation.getAddressLine1();
			String locAddress2 = providerLocation.getAddressLine2();
			String locCity = providerLocation.getCity();
			String locState = providerLocation.getState();
			String locZip = providerLocation.getZipCode();
		/*	String locZip4 = Integer.toString(providerLocation.getZip4Code());*/
			String locCountry = providerLocation.getCountry();
			String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

			String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
			String doctorFirstName = doctorDetail.getfName();
			String doctorLastName = doctorDetail.getlName();
			String doctorMiddleName = doctorDetail.getMidName();
			/*String namePrefix = "Mr.";
			String nameSuffix = "Jr";*/
			String prescDea = doctorDetail.getDea();
			String prescUpin = doctorDetail.getUpin();
			String prescLicenseState = doctorDetail.getDocLicenseState();
			String prescNpi = doctorDetail.getNpi();
			String prescLicenseNumber = doctorDetail.getDocLicenseNumber();
          
			String patientId=new String();
			
			 patientId = Integer.toString(patDetailData.getUserId());
			 String pLastName=new String();
			 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getLastName())){
				 pLastName = patDetailData.getLastName().trim();
			 }
			 else {
				 pLastName="";
			}
			 String pFirstName=new String();
			 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getFirstName())){
				 pFirstName = patDetailData.getFirstName().trim();
			 }
			else {
				 pFirstName="";
			}
			 String pMiddleName=new String();
			 if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getMiddleName())){
				 pMiddleName = patDetailData.getMiddleName().trim();
			 }
			else {
				 pMiddleName="";
			}
			 String patMedicalRecordNumber=new String();
			
			patMedicalRecordNumber = Integer.toString(patDetailData.getUserId());
			String patAddress1=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getDoorNo())){
				patAddress1 = patDetailData.getDoorNo().trim();
			}
			else {
				patAddress1="";
			}
			String patAddress2=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getStreet())){
				patAddress2=patDetailData.getStreet().trim();
			}
			else {
				patAddress2="";
			}
			String patCity=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getCity())){
				patCity = patDetailData.getCity().trim();
			}
			else {
				patCity="";
			}
			String  patState=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getState())){
				 patState = patDetailData.getState().trim();
			}
			else {
				patState="";
			}
			String patZip=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getPincode())){
				patZip = patDetailData.getPincode().trim();
			}
			else {
				patZip="";
			}
			String patCountry=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getCountry())){
				patCountry = patDetailData.getCountry().trim();
			}
			else {
				patCountry="";
			}
			String patHomeTelephone=new String();
			
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getPhoneNumber())){
			patHomeTelephone = patDetailData.getPhoneNumber().trim();
			}
			else {
				patHomeTelephone="";
			}
			
			Date paDob = patDetailData.getDateOfBirth();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String patDOB = df.format(paDob);
			String gender=new String();
			if(org.apache.commons.lang.StringUtils.isNotBlank(patDetailData.getGender())){
				 gender = patDetailData.getGender().trim();
			}
			else {
				gender="";
			}
			
			String patGender = new String();
			if (gender.equalsIgnoreCase("Male")) {
					patGender = "M";
			 } 
			else if (gender.equalsIgnoreCase("Others")) {
				patGender = "U";
			}
			else {
				patGender = "F";
			}
			
			System.out.println("First Name  " + patDetailData.getFirstName() +"Last Name"
					+ patDetailData.getLastName() + "address 1 " + " patAddress1 "
					+ patDetailData.getDoorNo() +" Address2 "+ patDetailData.getStreet());
			System.out.println("City State Zip " + patDetailData.getCity()
					+ patDetailData.getState() + patDetailData.getPincode() +"paDob  "+paDob+"patDOB  "+patDOB+"patGender"+patGender
					+"patHomeTelephone "+patHomeTelephone +"patAddress1"+patAddress1+"patDetailData.getPinCode:::::::"+patZip);

			// Instance of Document and Document Builder

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();

			org.w3c.dom.Document document = documentBuilder.newDocument();
					
			// Root Element NcScript

			Element ncScript = document.createElement("NCScript");
			document.appendChild(ncScript);

			Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
			ncScriptXsd.setValue(getProperties().getProperty("xsd"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
			ncScriptXsi.setValue(getProperties().getProperty("xsi"));
			ncScript.setAttributeNode(ncScriptXsd);
			Attr ncScriptXmlns = document.createAttribute("xmlns");
			ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
			ncScript.setAttributeNode(ncScriptXmlns);
			// Credentials Element

			Element credentials = document.createElement("Credentials");
			ncScript.appendChild(credentials);
			Element partnerName = document.createElement("partnerName");
			partnerName.appendChild(document.createTextNode(getProperties()
					.getProperty("partnerName")));
			credentials.appendChild(partnerName);
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode(getProperties()
					.getProperty("name")));
			credentials.appendChild(name);
			Element password = document.createElement("password");
			password.appendChild(document.createTextNode(getProperties()
					.getProperty("password")));
			credentials.appendChild(password);
			Element productName = document.createElement("productName");
			productName.appendChild(document.createTextNode(getProperties()
					.getProperty("productName")));
			credentials.appendChild(productName);
			Element productVersion = document.createElement("productVersion");
			productVersion.appendChild(document.createTextNode(getProperties()
					.getProperty("productVersion")));
			credentials.appendChild(productVersion);
			// User Role Element

			Element userRole = document.createElement("UserRole");
			ncScript.appendChild(userRole);
			Element user = document.createElement("user");
			user.appendChild(document.createTextNode(roleSecurity.getRoleType()));
			userRole.appendChild(user);
			Element role = document.createElement("role");
			role.appendChild(document.createTextNode(roleSecurity.getNewCropRole()));
			userRole.appendChild(role);

			// Destination Element
			Element destination = document.createElement("Destination");
			ncScript.appendChild(destination);
			Element requestedPage = document.createElement("requestedPage");
			requestedPage.appendChild(document
					.createTextNode("ws-pbm-eligibility"));
			destination.appendChild(requestedPage);

			// Account Element
			Element account = document.createElement("Account");
			ncScript.appendChild(account);
			Attr accAttr = document.createAttribute("ID");
			accAttr.setValue(providerLocation.getAccountId());
			account.setAttributeNode(accAttr);
			Element accountName = document.createElement("accountName");
			accountName.appendChild(document.createTextNode(usrAccountName
					));
			account.appendChild(accountName);
			Element siteId = document.createElement("siteID");
			siteId.appendChild(document.createTextNode(providerLocation.getSiteId()
					));
			account.appendChild(siteId);

			Element accountAddress = document.createElement("AccountAddress");
			account.appendChild(accountAddress);
			Element accAddress1 = document.createElement("address1");
			accAddress1.appendChild(document.createTextNode(usrAddress1));
			accountAddress.appendChild(accAddress1);
			
			Element accAddress2 = document.createElement("address2");
			accAddress2.appendChild(document.createTextNode(usrAddress2));
			accountAddress.appendChild(accAddress2);
			
			Element accCity = document.createElement("city");
			accCity.appendChild(document.createTextNode(usrCity));
			accountAddress.appendChild(accCity);
			Element accState = document.createElement("state");
			accState.appendChild(document.createTextNode(usrState));
			accountAddress.appendChild(accState);
			Element accZip = document.createElement("zip");
			accZip.appendChild(document.createTextNode(usrZip));
			accountAddress.appendChild(accZip);
			
			/*Element accZip4 = document.createElement("zip4");
			accZip4.appendChild(document.createTextNode(usrZip4));
			accountAddress.appendChild(accZip4);*/
			
			Element accCountry = document.createElement("country");
			accCountry.appendChild(document.createTextNode(usrCountry));
			accountAddress.appendChild(accCountry);

			Element accountPrimaryPhoneNumber = document
					.createElement("accountPrimaryPhoneNumber");
			accountPrimaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			account.appendChild(accountPrimaryPhoneNumber);
			Element accountPrimaryFaxNumber = document
					.createElement("accountPrimaryFaxNumber");
			accountPrimaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			account.appendChild(accountPrimaryFaxNumber);

			// Location Element
			Element location = document.createElement("Location");
			ncScript.appendChild(location);
			Attr locAttr = document.createAttribute("ID");
			locAttr.setValue(usrLocId);
			location.setAttributeNode(locAttr);
			Element locationName = document.createElement("locationName");
			locationName.appendChild(document.createTextNode(usrLocationName));
			location.appendChild(locationName);

			Element locationAddress = document.createElement("LocationAddress");
			location.appendChild(locationAddress);
			Element lAddress1 = document.createElement("address1");
			lAddress1.appendChild(document.createTextNode(locAddress1));
			locationAddress.appendChild(lAddress1);
			
			Element lAddress2 = document.createElement("address2");
			lAddress2.appendChild(document.createTextNode(locAddress2));
			locationAddress.appendChild(lAddress2);
			
			Element lCity = document.createElement("city");
			lCity.appendChild(document.createTextNode(locCity));
			locationAddress.appendChild(lCity);
			Element lState = document.createElement("state");
			lState.appendChild(document.createTextNode(locState));
			locationAddress.appendChild(lState);
			Element lZip = document.createElement("zip");
			lZip.appendChild(document.createTextNode(locZip));
			locationAddress.appendChild(lZip);
			
			/*Element lZip4 = document.createElement("zip4");
			lZip4.appendChild(document.createTextNode(locZip4));
			locationAddress.appendChild(lZip4);*/
			
			
			Element lCountry = document.createElement("country");
			lCountry.appendChild(document.createTextNode(locCountry));
			locationAddress.appendChild(lCountry);

			Element primaryPhoneNumber = document
					.createElement("primaryPhoneNumber");
			primaryPhoneNumber.appendChild(document
					.createTextNode(usrPrimaryPhoneNumber));
			location.appendChild(primaryPhoneNumber);
			Element primaryFaxNumber = document
					.createElement("primaryFaxNumber");
			primaryFaxNumber.appendChild(document
					.createTextNode(usrPrimaryFaxNumber));
			location.appendChild(primaryFaxNumber);
			Element pharmacyContactNumber = document
					.createElement("pharmacyContactNumber");
			pharmacyContactNumber.appendChild(document
					.createTextNode(usrPharmacyContactNumber));
			location.appendChild(pharmacyContactNumber);

			// LicensedPrescriber Element

			Element licensedPrescriber = document
					.createElement("LicensedPrescriber");
			ncScript.appendChild(licensedPrescriber);
			Attr licensedPrescriberAttr = document.createAttribute("ID");
			licensedPrescriberAttr.setValue(licensedPrescriberId);
			licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
			Element licensedPrescriberName = document
					.createElement("LicensedPrescriberName");
			licensedPrescriber.appendChild(licensedPrescriberName);
			Element docLastName = document.createElement("last");
			docLastName.appendChild(document.createTextNode(doctorLastName));
			licensedPrescriberName.appendChild(docLastName);
			Element docFirstName = document.createElement("first");
			docFirstName.appendChild(document.createTextNode(doctorFirstName));
			licensedPrescriberName.appendChild(docFirstName);
			Element docMidName = document.createElement("middle");
			docMidName.appendChild(document.createTextNode(doctorMiddleName));
			licensedPrescriberName.appendChild(docMidName);
			
			/*Element prefix = document.createElement("prefix");
			prefix.appendChild(document.createTextNode(namePrefix));
			licensedPrescriberName.appendChild(prefix);
			Element suffix = document.createElement("suffix");
			suffix.appendChild(document.createTextNode(nameSuffix));
			licensedPrescriberName.appendChild(suffix);*/

			Element dea = document.createElement("dea");
			dea.appendChild(document.createTextNode(prescDea));
			licensedPrescriber.appendChild(dea);
			
			/*Element upin = document.createElement("upin");
			upin.appendChild(document.createTextNode(prescUpin));
			licensedPrescriber.appendChild(upin);*/
			
			Element licenseState = document.createElement("licenseState");
			licenseState
					.appendChild(document.createTextNode(prescLicenseState));
			licensedPrescriber.appendChild(licenseState);
			Element licenseNumber = document.createElement("licenseNumber");
			licenseNumber.appendChild(document
					.createTextNode(prescLicenseNumber));
			licensedPrescriber.appendChild(licenseNumber);
			Element npi = document.createElement("npi");
			npi.appendChild(document.createTextNode(prescNpi));
			licensedPrescriber.appendChild(npi);

			// Patient Element

			Element patient = document.createElement("Patient");
			ncScript.appendChild(patient);
			Attr patientIdAttr = document.createAttribute("ID");
			patientIdAttr.setValue(patientId);
			patient.setAttributeNode(patientIdAttr);
			Element patientName = document.createElement("PatientName");
			patient.appendChild(patientName);
			Element patLastName = document.createElement("last");
			patLastName.appendChild(document.createTextNode(pLastName));
			patientName.appendChild(patLastName);
			Element patFirstName = document.createElement("first");
			patFirstName.appendChild(document.createTextNode(pFirstName));
			patientName.appendChild(patFirstName);
			Element patMiddleName = document.createElement("middle");
			patMiddleName.appendChild(document.createTextNode(pMiddleName));
			patientName.appendChild(patMiddleName);

			Element medicalRecordNumber = document
					.createElement("medicalRecordNumber");
			medicalRecordNumber.appendChild(document
					.createTextNode(patMedicalRecordNumber));
			patient.appendChild(medicalRecordNumber);

			Element patientAddress = document.createElement("PatientAddress");
			patient.appendChild(patientAddress);
			Element pAddress1 = document.createElement("address1");
			pAddress1.appendChild(document.createTextNode(patAddress1));
			patientAddress.appendChild(pAddress1);
			Element pAddress2 = document.createElement("address2");
			pAddress1.appendChild(document.createTextNode(patAddress2));
			patientAddress.appendChild(pAddress2);
			Element pCity = document.createElement("city");
			pCity.appendChild(document.createTextNode(patCity));
			patientAddress.appendChild(pCity);
			Element pState = document.createElement("state");
			pState.appendChild(document.createTextNode(patState));
			patientAddress.appendChild(pState);
			Element pZip = document.createElement("zip");
			pZip.appendChild(document.createTextNode(patZip));
			patientAddress.appendChild(pZip);
			Element pCountry = document.createElement("country");
			pCountry.appendChild(document.createTextNode(patCountry));
			patientAddress.appendChild(pCountry);

			Element patientContact = document.createElement("PatientContact");
			patient.appendChild(patientContact);
			Element pHomeTelephone = document.createElement("homeTelephone");
			
			pHomeTelephone.appendChild(document.createTextNode(patHomeTelephone));
			
			patientContact.appendChild(pHomeTelephone);

			Element patientCharacteristics = document
					.createElement("PatientCharacteristics");
			patient.appendChild(patientCharacteristics);
			Element pDob = document.createElement("dob");
			pDob.appendChild(document.createTextNode(patDOB));
			patientCharacteristics.appendChild(pDob);
			Element pGender = document.createElement("gender");
			pGender.appendChild(document.createTextNode(patGender));
			patientCharacteristics.appendChild(pGender);

			// Write code into XML File
			if(document!=null){
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
	             
				StringWriter stringWriter = new StringWriter();
				StreamResult result = new StreamResult(stringWriter);
				System.out.println("-----------------");
				String output=stringWriter.getBuffer().toString();
				System.out.println("-----------------");
				DOMSource domSource = new DOMSource(document);
				if(document!=null){
					transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					domSource = new DOMSource(document);
					OutputFormat format = new OutputFormat(document);
			        format.setIndenting(true);
			        XMLSerializer serializer = new XMLSerializer(System.out, format);
			        serializer.serialize(document);
			       
			      
				}
				if((domSource!=null && (result!=null))){
					System.out.println("domSource"+domSource+"documentttttttt"+document);
					try {
						transformer.transform(domSource, result);
					}catch(TransformerException e){
						e.printStackTrace();
					}
					catch (NullPointerException e) {
						e.printStackTrace();
					}catch(Exception e){
						e.printStackTrace();
					}
					
				}
				

				// Convert Xml format to one line String

				xmlResult = stringWriter.toString();
				PatientPbmDrugHistoryDetail drugHistoryDetail =new PatientPbmDrugHistoryDetail();
				drugHistoryDetail.setPatientXmlResult(xmlResult);
				drugHistoryDetail.setPatientId(patDetailData.getUserId());
				patientPbmDrugHistoryDetailList.add(drugHistoryDetail);
				//xmlResultList.add(xmlResult);
				System.out.println("XMl Result.........." + xmlResult);
			}

		/*}*/

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return patientPbmDrugHistoryDetailList;
    }

public EligibilityDetailResultV3 callGetPBMEligibilityV3ForPatient(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patientId, int providerId) throws Exception {
	logger.info("::callGetPBMEligibilityV3 method check by gopal::::");
	String accId = providerLocation.getAccountId();
	String siteId = providerLocation.getSiteId();
	String partName = getProperties().getProperty("partnerName");
	String name = getProperties().getProperty("name");
	String password = getProperties().getProperty("password");
	String userId = Integer.toString(patientId);
	String userType = getProperties().getProperty("userType");
	EligibilityDetailResultV3 elDetailResultV3=new EligibilityDetailResultV3();
	String patientXmlBase64EncodedData = convertPatientDetailStringInBase64FormatForPatient(providerLocation,roleSecurity,patientId,providerId);
	if(!patientXmlBase64EncodedData.equals("")){	
		
	
	String xmlIn = "BASE64:"+patientXmlBase64EncodedData;
	AccountRequest accountRequest = WS_CLIENT_FACTORY
			.createAccountRequest();
	accountRequest.setAccountId(accId);
	accountRequest.setSiteId(siteId);

	Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
	credentials.setName(name);
	credentials.setPartnerName(partName);
	credentials.setPassword(password);

	PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
			.createPatientInformationRequester();
	informationRequester.setUserId(userId);
	informationRequester.setUserType(userType);

	GetPBMEligibilityV3 pbmEligibilityV3 = WS_CLIENT_FACTORY
			.createGetPBMEligibilityV3();
	pbmEligibilityV3.setAccountRequest(accountRequest);
	pbmEligibilityV3.setCredentials(credentials);
	pbmEligibilityV3.setIncludeSchema("Y");
	pbmEligibilityV3.setPatientInformationRequester(informationRequester);
	pbmEligibilityV3.setXmlIn(xmlIn);

	WebServiceClientImpl<GetPBMEligibilityV3> wsClientForPbmEligblityV3 = new WebServiceClientImpl<GetPBMEligibilityV3>();
	GetPBMEligibilityV3Response pbmEligibilityV3Response = (GetPBMEligibilityV3Response) wsClientForPbmEligblityV3
			.sendRequestAndReceiveResponse
			(pbmEligibilityV3, Update1ServiceDetail);
	
	if(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")){
		elDetailResultV3 = pbmEligibilityV3Response
				.getGetPBMEligibilityV3Result();
	}
	else if (pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")) {
		getAuditInfo().setComment(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getMessage());
		getAuditInfo().setUserId(new ContextUtil().getLoginId());
		getAuditInfo().setMachineName("GetPBMEligibilityV3 WSDL");
		getAuditInfo().setCreateTime(new Date());
	    elDetailResultV3=new EligibilityDetailResultV3();
		userService.saveWSFailStatus(auditInfo);
	}
	}
    System.out.println("elDetailResultV3 :::::::::::  "+elDetailResultV3.getEligibilityGuid());
	/*
	 * System.out.println("----------------------------------------");
	 * System
	 * .out.println("Transcation Guid for eligiblity "+elDetailResultV3
	 * .getEligibilityGuid()+elDetailResultV3.getEligibilityDetailArray
	 * 
	 * ().getEligibilityDetailV3());
	 */
	return elDetailResultV3;
}

private String convertPatientDetailStringInBase64FormatForPatient(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patientId, int providerId) {
	
	String patientDetailBase64FormatData = "";
	try {
		String patientDetailXml = xmlGenrationForPatientDetailDataForPatient(providerLocation,roleSecurity,patientId,providerId);
		if(!patientDetailXml.equals("")){
		byte[] encodedPatientDetail = Base64.encodeBase64(patientDetailXml
				.getBytes());
		System.out.println("Encoded Patient Detail " + encodedPatientDetail
				+ "        In String Format  "
				+ new String(encodedPatientDetail));
		byte[] decoded = Base64.decodeBase64(encodedPatientDetail);
		System.out.println("Decode value " + new String(decoded));
		patientDetailBase64FormatData = new String(encodedPatientDetail);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return patientDetailBase64FormatData;
}

private String xmlGenrationForPatientDetailDataForPatient(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patient_Id, int providerId) {
	String xmlResult = "";
	UserLoginDetail patDetailData = getPatientDetailData(patient_Id,providerId);
	DoctorDetail doctorDetail = getDoctorDetailData();
	try {
		
		
		/*String usrAccountId = providerLocation.getAccountId();
		String usrAddress1 = doctorDetail.getDoorNo();
		String usrAddress2 = doctorDetail.getStreet();
		String usrCity = doctorDetail.getCity();
		String usrState = doctorDetail.getState();
		String usrZip = doctorDetail.getPincode();;
	
		String usrCountry = doctorDetail.getCountry();
		String usrPrimaryPhoneNumber = doctorDetail.getPhoneNumber();
		String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
		String usrLocId = Integer.toString(providerLocation.getId());
		String usrLocationName = providerLocation.getLocation();

		String locAddress1 = providerLocation.getAddressLine1();
		String locAddress2 = providerLocation.getAddressLine2();
		String locCity = providerLocation.getCity();
		String locState = providerLocation.getState();
		String locZip = providerLocation.getZipCode();
		
		String locCountry = providerLocation.getCountry();
		String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

		String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
		String doctorFirstName = doctorDetail.getfName();
		String doctorLastName = doctorDetail.getlName();
		String doctorMiddleName = doctorDetail.getMidName();
		String namePrefix = "Mr.";
		String nameSuffix = "Jr";
		String prescDea = doctorDetail.getDea();
		String prescUpin = doctorDetail.getUpin();
		String prescLicenseState = doctorDetail.getDocLicenseState();
		String prescNpi = doctorDetail.getNpi();
		String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

		String patientId = Integer.toString(patDetailData.getUserId());
		String pLastName = patDetailData.getLastName();
		String pFirstName = patDetailData.getFirstName();
		String pMiddleName = patDetailData.getMiddleName();
		String patMedicalRecordNumber =Integer.toString(patDetailData.getUserId());
		String patAddress1 = patDetailData.getDoorNo()
				+ patDetailData.getStreet();
		String patCity = patDetailData.getCity();
		String patState = patDetailData.getState();
		String patZip = patDetailData.getPincode();
		String patCountry = patDetailData.getCountry();
		String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
		Date paDob = patDetailData.getDateOfBirth();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String patDOB = df.format(paDob);
		String gender = patDetailData.getGender();
		String patGender = "";
		if (gender.equalsIgnoreCase("Male")) {
			patGender = "M";
		} else {
			patGender = "F";
		}
		System.out.println("PatientId  " + patDetailData.getFirstName()
				+ patDetailData.getLastName() + "-1" + " patAddress1 "
				+ patDetailData.getDoorNo() + patDetailData.getStreet());
		System.out.println("City State Zip" + patDetailData.getCity()
				+ patDetailData.getState() + patDetailData.getPincode());*/
		
		
		String usrAccountId = providerLocation.getAccountId();
		String usrAccountName=doctorDetail.getfName()+" "+doctorDetail.getlName();
		String usrAddress1 = doctorDetail.getStreet();
		String usrAddress2 = doctorDetail.getStreet();
		String usrCity = doctorDetail.getCity();
		String usrState = doctorDetail.getState();
		String usrZip = doctorDetail.getPincode();;
		
		String usrCountry = doctorDetail.getCountry();
		String usrPrimaryPhoneNumber = providerLocation.getPrimaryPhoneNumber();
		String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
		String usrLocId = Integer.toString(providerLocation.getId());
		String usrLocationName = providerLocation.getLocation();

		String locAddress1 = providerLocation.getAddressLine1();
		String locAddress2 = providerLocation.getAddressLine2();
		String locCity = providerLocation.getCity();
		String locState = providerLocation.getState();
		String locZip = providerLocation.getZipCode();
	
		String locCountry = providerLocation.getCountry();
		String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

		String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
		String doctorFirstName = doctorDetail.getfName();
		String doctorLastName = doctorDetail.getlName();
		String doctorMiddleName = doctorDetail.getMidName();
		
		String prescDea = doctorDetail.getDea();
		String prescUpin = doctorDetail.getUpin();
		String prescLicenseState = doctorDetail.getDocLicenseState();
		String prescNpi = doctorDetail.getNpi();
		String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

		String patientId = Integer.toString(patDetailData.getUserId());
		String pLastName = patDetailData.getLastName();
		String pFirstName = patDetailData.getFirstName();
		String pMiddleName = patDetailData.getMiddleName();
		String patMedicalRecordNumber = Integer.toString(patDetailData.getUserId());
		String patAddress1 = patDetailData.getDoorNo()
				+ patDetailData.getStreet();
		String patCity = patDetailData.getCity();
		String patState = patDetailData.getState();
		String patZip = patDetailData.getPincode();
		String patCountry = patDetailData.getCountry();
		String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
		Date paDob = patDetailData.getDateOfBirth();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String patDOB = df.format(paDob);
		String gender = patDetailData.getGender().trim();
		String patGender = "";
		if (gender.equalsIgnoreCase("Male")) {
			patGender = "M";
		 }
		else if (gender.equalsIgnoreCase("Others")) {
			patGender = "U";
		}
		else {
			patGender = "F";
		}
		System.out.println("PatientId  " + patDetailData.getFirstName()
				+ patDetailData.getLastName() + "-1" + " patAddress1 "
				+ patDetailData.getDoorNo() + patDetailData.getStreet());
		System.out.println("City State Zip" + patDetailData.getCity()
				+ patDetailData.getState() + patDetailData.getPincode()+"paDob  "+paDob+"patDOB"+patDOB+"patGender"+patGender
				+"patHomeTelephone"+patHomeTelephone+"patAddress1"+patAddress1+"patDetailData.getMiddleName()"+patDetailData.getMiddleName());

		// Instance of Document and Document Builder

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();

		org.w3c.dom.Document document = documentBuilder.newDocument();

		// Root Element NcScript

		Element ncScript = document.createElement("NCScript");
		document.appendChild(ncScript);

		Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
		ncScriptXsd.setValue(getProperties().getProperty("xsd"));
		ncScript.setAttributeNode(ncScriptXsd);
		Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
		ncScriptXsi.setValue(getProperties().getProperty("xsi"));
		ncScript.setAttributeNode(ncScriptXsd);
		Attr ncScriptXmlns = document.createAttribute("xmlns");
		ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
		ncScript.setAttributeNode(ncScriptXmlns);
		// Credentials Element

		Element credentials = document.createElement("Credentials");
		ncScript.appendChild(credentials);
		Element partnerName = document.createElement("partnerName");
		partnerName.appendChild(document.createTextNode(getProperties()
				.getProperty("partnerName")));
		credentials.appendChild(partnerName);
		Element name = document.createElement("name");
		name.appendChild(document.createTextNode(getProperties()
				.getProperty("name")));
		credentials.appendChild(name);
		Element password = document.createElement("password");
		password.appendChild(document.createTextNode(getProperties()
				.getProperty("password")));
		credentials.appendChild(password);
		Element productName = document.createElement("productName");
		productName.appendChild(document.createTextNode(getProperties()
				.getProperty("productName")));
		credentials.appendChild(productName);
		Element productVersion = document.createElement("productVersion");
		productVersion.appendChild(document.createTextNode(getProperties()
				.getProperty("productVersion")));
		credentials.appendChild(productVersion);
		// User Role Element

		Element userRole = document.createElement("UserRole");
		ncScript.appendChild(userRole);
		Element user = document.createElement("user");
		user.appendChild(document.createTextNode(roleSecurity.getRoleType()));
		userRole.appendChild(user);
		Element role = document.createElement("role");
		role.appendChild(document.createTextNode(roleSecurity.getNewCropRole()));
		userRole.appendChild(role);

		// Destination Element
		Element destination = document.createElement("Destination");
		ncScript.appendChild(destination);
		Element requestedPage = document.createElement("requestedPage");
		requestedPage.appendChild(document
				.createTextNode("ws-pbm-eligibility"));
		destination.appendChild(requestedPage);

		// Account Element
		Element account = document.createElement("Account");
		ncScript.appendChild(account);
		Attr accAttr = document.createAttribute("ID");
		accAttr.setValue(providerLocation.getAccountId());
		account.setAttributeNode(accAttr);
		Element accountName = document.createElement("accountName");
		accountName.appendChild(document.createTextNode(providerLocation.getAccountId()
				));
		account.appendChild(accountName);
		Element siteId = document.createElement("siteID");
		siteId.appendChild(document.createTextNode(providerLocation.getSiteId()
				));
		account.appendChild(siteId);

		Element accountAddress = document.createElement("AccountAddress");
		account.appendChild(accountAddress);
		Element accAddress1 = document.createElement("address1");
		accAddress1.appendChild(document.createTextNode(usrAddress1));
		accountAddress.appendChild(accAddress1);
		Element accAddress2 = document.createElement("address2");
		accAddress2.appendChild(document.createTextNode(usrAddress2));
		accountAddress.appendChild(accAddress2);
		Element accCity = document.createElement("city");
		accCity.appendChild(document.createTextNode(usrCity));
		accountAddress.appendChild(accCity);
		Element accState = document.createElement("state");
		accState.appendChild(document.createTextNode(usrState));
		accountAddress.appendChild(accState);
		Element accZip = document.createElement("zip");
		accZip.appendChild(document.createTextNode(usrZip));
		accountAddress.appendChild(accZip);
		/*Element accZip4 = document.createElement("zip4");
		accZip4.appendChild(document.createTextNode(usrZip4));
		accountAddress.appendChild(accZip4);*/
		Element accCountry = document.createElement("country");
		accCountry.appendChild(document.createTextNode(usrCountry));
		accountAddress.appendChild(accCountry);

		Element accountPrimaryPhoneNumber = document
				.createElement("accountPrimaryPhoneNumber");
		accountPrimaryPhoneNumber.appendChild(document
				.createTextNode(usrPrimaryPhoneNumber));
		account.appendChild(accountPrimaryPhoneNumber);
		Element accountPrimaryFaxNumber = document
				.createElement("accountPrimaryFaxNumber");
		accountPrimaryFaxNumber.appendChild(document
				.createTextNode(usrPrimaryFaxNumber));
		account.appendChild(accountPrimaryFaxNumber);

		// Location Element
		Element location = document.createElement("Location");
		ncScript.appendChild(location);
		Attr locAttr = document.createAttribute("ID");
		locAttr.setValue(usrLocId);
		location.setAttributeNode(locAttr);
		Element locationName = document.createElement("locationName");
		locationName.appendChild(document.createTextNode(usrLocationName));
		location.appendChild(locationName);

		Element locationAddress = document.createElement("LocationAddress");
		location.appendChild(locationAddress);
		Element lAddress1 = document.createElement("address1");
		lAddress1.appendChild(document.createTextNode(locAddress1));
		locationAddress.appendChild(lAddress1);
		Element lAddress2 = document.createElement("address2");
		lAddress2.appendChild(document.createTextNode(locAddress2));
		locationAddress.appendChild(lAddress2);
		Element lCity = document.createElement("city");
		lCity.appendChild(document.createTextNode(locCity));
		locationAddress.appendChild(lCity);
		Element lState = document.createElement("state");
		lState.appendChild(document.createTextNode(locState));
		locationAddress.appendChild(lState);
		Element lZip = document.createElement("zip");
		lZip.appendChild(document.createTextNode(locZip));
		locationAddress.appendChild(lZip);
		/*Element lZip4 = document.createElement("zip4");
		lZip4.appendChild(document.createTextNode(locZip4));
		locationAddress.appendChild(lZip4);*/
		Element lCountry = document.createElement("country");
		lCountry.appendChild(document.createTextNode(locCountry));
		locationAddress.appendChild(lCountry);

		Element primaryPhoneNumber = document
				.createElement("primaryPhoneNumber");
		primaryPhoneNumber.appendChild(document
				.createTextNode(usrPrimaryPhoneNumber));
		location.appendChild(primaryPhoneNumber);
		Element primaryFaxNumber = document
				.createElement("primaryFaxNumber");
		primaryFaxNumber.appendChild(document
				.createTextNode(usrPrimaryFaxNumber));
		location.appendChild(primaryFaxNumber);
		Element pharmacyContactNumber = document
				.createElement("pharmacyContactNumber");
		pharmacyContactNumber.appendChild(document
				.createTextNode(usrPharmacyContactNumber));
		location.appendChild(pharmacyContactNumber);

		// LicensedPrescriber Element

		Element licensedPrescriber = document
				.createElement("LicensedPrescriber");
		ncScript.appendChild(licensedPrescriber);
		Attr licensedPrescriberAttr = document.createAttribute("ID");
		licensedPrescriberAttr.setValue(licensedPrescriberId);
		licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
		Element licensedPrescriberName = document
				.createElement("LicensedPrescriberName");
		licensedPrescriber.appendChild(licensedPrescriberName);
		Element docLastName = document.createElement("last");
		docLastName.appendChild(document.createTextNode(doctorLastName));
		licensedPrescriberName.appendChild(docLastName);
		Element docFirstName = document.createElement("first");
		docFirstName.appendChild(document.createTextNode(doctorFirstName));
		licensedPrescriberName.appendChild(docFirstName);
		Element docMidName = document.createElement("middle");
		docMidName.appendChild(document.createTextNode(doctorMiddleName));
		licensedPrescriberName.appendChild(docMidName);
		
		/*Element prefix = document.createElement("prefix");
		prefix.appendChild(document.createTextNode(namePrefix));
		licensedPrescriberName.appendChild(prefix);
		Element suffix = document.createElement("suffix");
		suffix.appendChild(document.createTextNode(nameSuffix));
		licensedPrescriberName.appendChild(suffix);*/

		Element dea = document.createElement("dea");
		dea.appendChild(document.createTextNode(prescDea));
		licensedPrescriber.appendChild(dea);
		Element upin = document.createElement("upin");
		upin.appendChild(document.createTextNode(prescUpin));
		licensedPrescriber.appendChild(upin);
		Element licenseState = document.createElement("licenseState");
		licenseState
				.appendChild(document.createTextNode(prescLicenseState));
		licensedPrescriber.appendChild(licenseState);
		Element licenseNumber = document.createElement("licenseNumber");
		licenseNumber.appendChild(document
				.createTextNode(prescLicenseNumber));
		licensedPrescriber.appendChild(licenseNumber);
		Element npi = document.createElement("npi");
		npi.appendChild(document.createTextNode(prescNpi));
		licensedPrescriber.appendChild(npi);

		// Patient Element

		Element patient = document.createElement("Patient");
		ncScript.appendChild(patient);
		Attr patientIdAttr = document.createAttribute("ID");
		patientIdAttr.setValue(patientId);
		patient.setAttributeNode(patientIdAttr);
		Element patientName = document.createElement("PatientName");
		patient.appendChild(patientName);
		Element patLastName = document.createElement("last");
		patLastName.appendChild(document.createTextNode(pLastName));
		patientName.appendChild(patLastName);
		Element patFirstName = document.createElement("first");
		patFirstName.appendChild(document.createTextNode(pFirstName));
		patientName.appendChild(patFirstName);
		Element patMiddleName = document.createElement("middle");
		patMiddleName.appendChild(document.createTextNode(pMiddleName));
		patientName.appendChild(patMiddleName);

		Element medicalRecordNumber = document
				.createElement("medicalRecordNumber");
		medicalRecordNumber.appendChild(document
				.createTextNode(patMedicalRecordNumber));
		patient.appendChild(medicalRecordNumber);

		Element patientAddress = document.createElement("PatientAddress");
		patient.appendChild(patientAddress);
		Element pAddress1 = document.createElement("address1");
		pAddress1.appendChild(document.createTextNode(patAddress1));
		patientAddress.appendChild(pAddress1);
		Element pCity = document.createElement("city");
		pCity.appendChild(document.createTextNode(patCity));
		patientAddress.appendChild(pCity);
		Element pState = document.createElement("state");
		pState.appendChild(document.createTextNode(patState));
		patientAddress.appendChild(pState);
		Element pZip = document.createElement("zip");
		pZip.appendChild(document.createTextNode(patZip));
		patientAddress.appendChild(pZip);
		Element pCountry = document.createElement("country");
		pCountry.appendChild(document.createTextNode(patCountry));
		patientAddress.appendChild(pCountry);

		Element patientContact = document.createElement("PatientContact");
		patient.appendChild(patientContact);
		Element pHomeTelephone = document.createElement("homeTelephone");
		pHomeTelephone.appendChild(document
				.createTextNode(patHomeTelephone));
		patientContact.appendChild(pHomeTelephone);

		Element patientCharacteristics = document
				.createElement("PatientCharacteristics");
		patient.appendChild(patientCharacteristics);
		Element pDob = document.createElement("dob");
		pDob.appendChild(document.createTextNode(patDOB));
		patientCharacteristics.appendChild(pDob);
		Element pGender = document.createElement("gender");
		pGender.appendChild(document.createTextNode(patGender));
		patientCharacteristics.appendChild(pGender);

		// Write code into XML File
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		DOMSource domSource = new DOMSource(document);
		System.out.println("domSource"+domSource+"documentttttttt"+document);
		transformer.transform(domSource, result);

		// Convert Xml format to one line String

		xmlResult = stringWriter.toString();
		System.out.println("XMl Result.........." + xmlResult);

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return xmlResult;
}

private UserLoginDetail getPatientDetailData(Integer patient_Id, int providerId) {
	UserLoginDetail patientDetailData = userService.findPatientDetailData(patient_Id,providerId);
	System.out.println("Patient Name " + patientDetailData.getFirstName()
			+ "Patient Email " + patientDetailData.getEmail());
	return patientDetailData;
}

/****@author saurabh
 * *****@modified by: saurabh
 * ******for multiThreading
 */

public List<DrugDiseaseDetail> callDrugDiseaseInteractionForMultithreading(String patientId, List<PatientMedicationData> patientMedicationDataList,
		List<PatientDiagnosesDetails> patientDiagnosesList, ProviderLocation providerLocation, double selectedDrugId,int providerIdForLoggedInUser) 
		{
		System.out.println("callDrugDiseaseInteractionForMultithreading:::"+selectedDrugId);
		List<DrugDiseaseDetail> drugDiseaseDetailList=new ArrayList<DrugDiseaseDetail>();
		try{
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String userId = Integer.toString(providerIdForLoggedInUser);
		String userType=getProperties().getProperty("userType");
		String diseaseCodeType="";
		String drugStandardType="FDB";
		
		ArrayOfString diseaseList=new ArrayOfString();
		for(PatientDiagnosesDetails pDiagnosesDetails:patientDiagnosesList){
			//Double diagnosisId=pDiagnosesDetails.getIcdId();// Commented By Anjani
			String disease=pDiagnosesDetails.getIcdId();
			
			diseaseList.getString().add(disease);
			diseaseCodeType=pDiagnosesDetails.getIcdCodeType();
			
		}
		
		
		
		ArrayOfString proposedMedications=new ArrayOfString();
		
		if(!(selectedDrugId==0.0))
		{
			int proposedMedicationId=(int) selectedDrugId;
			String  proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
			System.out.println("diagnosis Medication...12345.in for loop "+proposedMedications+"drug diagnosis:::: "+proposedMedication+":::::::");
		
		}
		
		else
		{
		for(PatientMedicationData patMedData:patientMedicationDataList){
			int proposedMedicationId=(int) patMedData.getDrugId();
			String  proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
			}
		}
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		PatientInformationRequester pInformationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		pInformationRequester.setUserId(userId);
		pInformationRequester.setUserType(userType);
		
		PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);
		
		//Drug Disease Interaction Request 
		DrugDiseaseInteraction drugDiseaseInteractionRequest=WS_CLIENT_FACTORY.createDrugDiseaseInteraction();
		drugDiseaseInteractionRequest.setAccountRequest(accountRequest);
		drugDiseaseInteractionRequest.setCredentials(credentials);
		drugDiseaseInteractionRequest.setDiseaseCodeType(diseaseCodeType);
		drugDiseaseInteractionRequest.setDrugStandardType(drugStandardType);
		drugDiseaseInteractionRequest.setPatientInformationRequester(pInformationRequester);
		drugDiseaseInteractionRequest.setPatientRequest(patientRequest);
		drugDiseaseInteractionRequest.setDiseaseList(diseaseList);
		drugDiseaseInteractionRequest.setProposedMedications(proposedMedications);
		
		//Drug Disease Interaction Response 
		WebServiceClientImpl<DrugDiseaseInteraction> wsClientForDrugDiseaseInteractionRequest=new WebServiceClientImpl<DrugDiseaseInteraction>();
		DrugDiseaseInteractionResponse drugDiseaseInteractionResponse=new DrugDiseaseInteractionResponse();
		drugDiseaseInteractionResponse=(DrugDiseaseInteractionResponse) wsClientForDrugDiseaseInteractionRequest.sendRequestAndReceiveResponse(drugDiseaseInteractionRequest, Update1ServiceDetail);
		if(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getStatus().value().equals("OK")){
			if(!(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getDrugDiseaseDetailArray()==null)){
			   drugDiseaseDetailList=drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getDrugDiseaseDetailArray().getDrugDiseaseDetail();
			   
			}
		}
		else if (!(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getStatus().value().equals("OK"))) {
			getAuditInfo().setComment(drugDiseaseInteractionResponse.getDrugDiseaseInteractionResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("DrugDiseaseInteraction WSDL");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
		}
		}
		catch(SocketException se){
			se.printStackTrace();
		}
		catch (NullPointerException ne) {
			ne.printStackTrace();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return drugDiseaseDetailList;
		
   }

/*******author saurabh
 * *****calling it from NcFormullaryBean
 * *******for multithreading implementation
 */

public EligibilityDetailResultV3 callGetPBMEligibilityV3ForPatientForMultithreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patientId, int providerId, DoctorDetail doctorDetailForMultithreading) throws Exception {
	logger.info("::callGetPBMEligibilityV3 method check by gopal::::");
	String accId = providerLocation.getAccountId();
	String siteId = providerLocation.getSiteId();
	String partName = getProperties().getProperty("partnerName");
	String name = getProperties().getProperty("name");
	String password = getProperties().getProperty("password");
	String userId = Integer.toString(patientId);
	String userType = getProperties().getProperty("userType");
	EligibilityDetailResultV3 elDetailResultV3=new EligibilityDetailResultV3();
	String patientXmlBase64EncodedData = convertPatientDetailStringInBase64FormatForPatientForMultithreading(providerLocation,roleSecurity,patientId,
											providerId, doctorDetailForMultithreading);
	if(!patientXmlBase64EncodedData.equals("")){	
		
	
	String xmlIn = "BASE64:"+patientXmlBase64EncodedData;
	AccountRequest accountRequest = WS_CLIENT_FACTORY
			.createAccountRequest();
	accountRequest.setAccountId(accId);
	accountRequest.setSiteId(siteId);

	Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
	credentials.setName(name);
	credentials.setPartnerName(partName);
	credentials.setPassword(password);

	PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
			.createPatientInformationRequester();
	informationRequester.setUserId(userId);
	informationRequester.setUserType(userType);

	GetPBMEligibilityV3 pbmEligibilityV3 = WS_CLIENT_FACTORY
			.createGetPBMEligibilityV3();
	pbmEligibilityV3.setAccountRequest(accountRequest);
	pbmEligibilityV3.setCredentials(credentials);
	pbmEligibilityV3.setIncludeSchema("Y");
	pbmEligibilityV3.setPatientInformationRequester(informationRequester);
	pbmEligibilityV3.setXmlIn(xmlIn);

	WebServiceClientImpl<GetPBMEligibilityV3> wsClientForPbmEligblityV3 = new WebServiceClientImpl<GetPBMEligibilityV3>();
	GetPBMEligibilityV3Response pbmEligibilityV3Response = (GetPBMEligibilityV3Response) wsClientForPbmEligblityV3
			.sendRequestAndReceiveResponse
			(pbmEligibilityV3, Update1ServiceDetail);
	
	if(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")){
		elDetailResultV3 = pbmEligibilityV3Response
				.getGetPBMEligibilityV3Result();
	}
	else if (pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getStatus().value().equals("OK")) {
		getAuditInfo().setComment(pbmEligibilityV3Response.getGetPBMEligibilityV3Result().getResult().getMessage());
		getAuditInfo().setUserId(new ContextUtil().getLoginId());
		getAuditInfo().setMachineName("GetPBMEligibilityV3 WSDL");
		getAuditInfo().setCreateTime(new Date());
	    elDetailResultV3=new EligibilityDetailResultV3();
		userService.saveWSFailStatus(auditInfo);
	}
	}
    System.out.println("elDetailResultV3 :::::::::::  "+elDetailResultV3.getEligibilityGuid());
	/*
	 * System.out.println("----------------------------------------");
	 * System
	 * .out.println("Transcation Guid for eligiblity "+elDetailResultV3
	 * .getEligibilityGuid()+elDetailResultV3.getEligibilityDetailArray
	 * 
	 * ().getEligibilityDetailV3());
	 */
	return elDetailResultV3;
}

/*******author saurabh
 * *******for multithreading implementation
 */

private String convertPatientDetailStringInBase64FormatForPatientForMultithreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patientId, int providerId, DoctorDetail doctorDetailForMultithreading) {
	
	String patientDetailBase64FormatData = "";
	try {
		String patientDetailXml = xmlGenrationForPatientDetailDataForPatientForMultithreading(providerLocation,roleSecurity,patientId,
											providerId, doctorDetailForMultithreading);
		if(!patientDetailXml.equals("")){
		byte[] encodedPatientDetail = Base64.encodeBase64(patientDetailXml
				.getBytes());
		System.out.println("Encoded Patient Detail " + encodedPatientDetail
				+ "        In String Format  "
				+ new String(encodedPatientDetail));
		byte[] decoded = Base64.decodeBase64(encodedPatientDetail);
		System.out.println("Decode value " + new String(decoded));
		patientDetailBase64FormatData = new String(encodedPatientDetail);
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return patientDetailBase64FormatData;
}

/*******author saurabh
 * *******for multithreading implementation
 */

private String xmlGenrationForPatientDetailDataForPatientForMultithreading(
		ProviderLocation providerLocation, RoleSecurity roleSecurity,
		Integer patient_Id, int providerId, DoctorDetail doctorDetailForMultithreading) {
	String xmlResult = "";
	UserLoginDetail patDetailData = getPatientDetailData(patient_Id,providerId);
	DoctorDetail doctorDetail = doctorDetailForMultithreading;
	try {
		
		
		/*String usrAccountId = providerLocation.getAccountId();
		String usrAddress1 = doctorDetail.getDoorNo();
		String usrAddress2 = doctorDetail.getStreet();
		String usrCity = doctorDetail.getCity();
		String usrState = doctorDetail.getState();
		String usrZip = doctorDetail.getPincode();;
	
		String usrCountry = doctorDetail.getCountry();
		String usrPrimaryPhoneNumber = doctorDetail.getPhoneNumber();
		String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
		String usrLocId = Integer.toString(providerLocation.getId());
		String usrLocationName = providerLocation.getLocation();

		String locAddress1 = providerLocation.getAddressLine1();
		String locAddress2 = providerLocation.getAddressLine2();
		String locCity = providerLocation.getCity();
		String locState = providerLocation.getState();
		String locZip = providerLocation.getZipCode();
		
		String locCountry = providerLocation.getCountry();
		String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

		String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
		String doctorFirstName = doctorDetail.getfName();
		String doctorLastName = doctorDetail.getlName();
		String doctorMiddleName = doctorDetail.getMidName();
		String namePrefix = "Mr.";
		String nameSuffix = "Jr";
		String prescDea = doctorDetail.getDea();
		String prescUpin = doctorDetail.getUpin();
		String prescLicenseState = doctorDetail.getDocLicenseState();
		String prescNpi = doctorDetail.getNpi();
		String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

		String patientId = Integer.toString(patDetailData.getUserId());
		String pLastName = patDetailData.getLastName();
		String pFirstName = patDetailData.getFirstName();
		String pMiddleName = patDetailData.getMiddleName();
		String patMedicalRecordNumber =Integer.toString(patDetailData.getUserId());
		String patAddress1 = patDetailData.getDoorNo()
				+ patDetailData.getStreet();
		String patCity = patDetailData.getCity();
		String patState = patDetailData.getState();
		String patZip = patDetailData.getPincode();
		String patCountry = patDetailData.getCountry();
		String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
		Date paDob = patDetailData.getDateOfBirth();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String patDOB = df.format(paDob);
		String gender = patDetailData.getGender();
		String patGender = "";
		if (gender.equalsIgnoreCase("Male")) {
			patGender = "M";
		} else {
			patGender = "F";
		}
		System.out.println("PatientId  " + patDetailData.getFirstName()
				+ patDetailData.getLastName() + "-1" + " patAddress1 "
				+ patDetailData.getDoorNo() + patDetailData.getStreet());
		System.out.println("City State Zip" + patDetailData.getCity()
				+ patDetailData.getState() + patDetailData.getPincode());*/
		
		
		String usrAccountId = providerLocation.getAccountId();
		String usrAccountName=doctorDetail.getfName()+" "+doctorDetail.getlName();
		String usrAddress1 = doctorDetail.getStreet();
		String usrAddress2 = doctorDetail.getStreet();
		String usrCity = doctorDetail.getCity();
		String usrState = doctorDetail.getState();
		String usrZip = doctorDetail.getPincode();;
		
		String usrCountry = doctorDetail.getCountry();
		String usrPrimaryPhoneNumber = providerLocation.getPrimaryPhoneNumber();
		String usrPrimaryFaxNumber = providerLocation.getFaxNumber();
		String usrLocId = Integer.toString(providerLocation.getId());
		String usrLocationName = providerLocation.getLocation();

		String locAddress1 = providerLocation.getAddressLine1();
		String locAddress2 = providerLocation.getAddressLine2();
		String locCity = providerLocation.getCity();
		String locState = providerLocation.getState();
		String locZip = providerLocation.getZipCode();
	
		String locCountry = providerLocation.getCountry();
		String usrPharmacyContactNumber = providerLocation.getPrimaryContactNumber();

		String licensedPrescriberId = Integer.toString(doctorDetail.getUserId());
		String doctorFirstName = doctorDetail.getfName();
		String doctorLastName = doctorDetail.getlName();
		String doctorMiddleName = doctorDetail.getMidName();
		
		String prescDea = doctorDetail.getDea();
		String prescUpin = doctorDetail.getUpin();
		String prescLicenseState = doctorDetail.getDocLicenseState();
		String prescNpi = doctorDetail.getNpi();
		String prescLicenseNumber = doctorDetail.getDocLicenseNumber();

		String patientId = Integer.toString(patDetailData.getUserId());
		String pLastName = patDetailData.getLastName();
		String pFirstName = patDetailData.getFirstName();
		String pMiddleName = patDetailData.getMiddleName();
		String patMedicalRecordNumber = Integer.toString(patDetailData.getUserId());
		String patAddress1 = patDetailData.getDoorNo()
				+ patDetailData.getStreet();
		String patCity = patDetailData.getCity();
		String patState = patDetailData.getState();
		String patZip = patDetailData.getPincode();
		String patCountry = patDetailData.getCountry();
		String patHomeTelephone = patDetailData.getPhoneNumber().replace("-", "");
		Date paDob = patDetailData.getDateOfBirth();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String patDOB = df.format(paDob);
		String gender = patDetailData.getGender().trim();
		String patGender = "";
		if (gender.equalsIgnoreCase("Male")) {
			patGender = "M";
		 }
		else if (gender.equalsIgnoreCase("Others")) {
			patGender = "U";
		}
		else {
			patGender = "F";
		}
		System.out.println("PatientId  " + patDetailData.getFirstName()
				+ patDetailData.getLastName() + "-1" + " patAddress1 "
				+ patDetailData.getDoorNo() + patDetailData.getStreet());
		System.out.println("City State Zip" + patDetailData.getCity()
				+ patDetailData.getState() + patDetailData.getPincode()+"paDob  "+paDob+"patDOB"+patDOB+"patGender"+patGender
				+"patHomeTelephone"+patHomeTelephone+"patAddress1"+patAddress1+"patDetailData.getMiddleName()"+patDetailData.getMiddleName());

		// Instance of Document and Document Builder

		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();

		org.w3c.dom.Document document = documentBuilder.newDocument();

		// Root Element NcScript

		Element ncScript = document.createElement("NCScript");
		document.appendChild(ncScript);

		Attr ncScriptXsd = document.createAttribute("xmlns:xsd");
		ncScriptXsd.setValue(getProperties().getProperty("xsd"));
		ncScript.setAttributeNode(ncScriptXsd);
		Attr ncScriptXsi = document.createAttribute("xmlns:xsi");
		ncScriptXsi.setValue(getProperties().getProperty("xsi"));
		ncScript.setAttributeNode(ncScriptXsd);
		Attr ncScriptXmlns = document.createAttribute("xmlns");
		ncScriptXmlns.setValue(getProperties().getProperty("xmlns"));
		ncScript.setAttributeNode(ncScriptXmlns);
		// Credentials Element

		Element credentials = document.createElement("Credentials");
		ncScript.appendChild(credentials);
		Element partnerName = document.createElement("partnerName");
		partnerName.appendChild(document.createTextNode(getProperties()
				.getProperty("partnerName")));
		credentials.appendChild(partnerName);
		Element name = document.createElement("name");
		name.appendChild(document.createTextNode(getProperties()
				.getProperty("name")));
		credentials.appendChild(name);
		Element password = document.createElement("password");
		password.appendChild(document.createTextNode(getProperties()
				.getProperty("password")));
		credentials.appendChild(password);
		Element productName = document.createElement("productName");
		productName.appendChild(document.createTextNode(getProperties()
				.getProperty("productName")));
		credentials.appendChild(productName);
		Element productVersion = document.createElement("productVersion");
		productVersion.appendChild(document.createTextNode(getProperties()
				.getProperty("productVersion")));
		credentials.appendChild(productVersion);
		// User Role Element

		Element userRole = document.createElement("UserRole");
		ncScript.appendChild(userRole);
		Element user = document.createElement("user");
		user.appendChild(document.createTextNode(roleSecurity.getRoleType()));
		userRole.appendChild(user);
		Element role = document.createElement("role");
		role.appendChild(document.createTextNode(roleSecurity.getNewCropRole()));
		userRole.appendChild(role);

		// Destination Element
		Element destination = document.createElement("Destination");
		ncScript.appendChild(destination);
		Element requestedPage = document.createElement("requestedPage");
		requestedPage.appendChild(document
				.createTextNode("ws-pbm-eligibility"));
		destination.appendChild(requestedPage);

		// Account Element
		Element account = document.createElement("Account");
		ncScript.appendChild(account);
		Attr accAttr = document.createAttribute("ID");
		accAttr.setValue(providerLocation.getAccountId());
		account.setAttributeNode(accAttr);
		Element accountName = document.createElement("accountName");
		accountName.appendChild(document.createTextNode(providerLocation.getAccountId()
				));
		account.appendChild(accountName);
		Element siteId = document.createElement("siteID");
		siteId.appendChild(document.createTextNode(providerLocation.getSiteId()
				));
		account.appendChild(siteId);

		Element accountAddress = document.createElement("AccountAddress");
		account.appendChild(accountAddress);
		Element accAddress1 = document.createElement("address1");
		accAddress1.appendChild(document.createTextNode(usrAddress1));
		accountAddress.appendChild(accAddress1);
		Element accAddress2 = document.createElement("address2");
		accAddress2.appendChild(document.createTextNode(usrAddress2));
		accountAddress.appendChild(accAddress2);
		Element accCity = document.createElement("city");
		accCity.appendChild(document.createTextNode(usrCity));
		accountAddress.appendChild(accCity);
		Element accState = document.createElement("state");
		accState.appendChild(document.createTextNode(usrState));
		accountAddress.appendChild(accState);
		Element accZip = document.createElement("zip");
		accZip.appendChild(document.createTextNode(usrZip));
		accountAddress.appendChild(accZip);
		/*Element accZip4 = document.createElement("zip4");
		accZip4.appendChild(document.createTextNode(usrZip4));
		accountAddress.appendChild(accZip4);*/
		Element accCountry = document.createElement("country");
		accCountry.appendChild(document.createTextNode(usrCountry));
		accountAddress.appendChild(accCountry);

		Element accountPrimaryPhoneNumber = document
				.createElement("accountPrimaryPhoneNumber");
		accountPrimaryPhoneNumber.appendChild(document
				.createTextNode(usrPrimaryPhoneNumber));
		account.appendChild(accountPrimaryPhoneNumber);
		Element accountPrimaryFaxNumber = document
				.createElement("accountPrimaryFaxNumber");
		accountPrimaryFaxNumber.appendChild(document
				.createTextNode(usrPrimaryFaxNumber));
		account.appendChild(accountPrimaryFaxNumber);

		// Location Element
		Element location = document.createElement("Location");
		ncScript.appendChild(location);
		Attr locAttr = document.createAttribute("ID");
		locAttr.setValue(usrLocId);
		location.setAttributeNode(locAttr);
		Element locationName = document.createElement("locationName");
		locationName.appendChild(document.createTextNode(usrLocationName));
		location.appendChild(locationName);

		Element locationAddress = document.createElement("LocationAddress");
		location.appendChild(locationAddress);
		Element lAddress1 = document.createElement("address1");
		lAddress1.appendChild(document.createTextNode(locAddress1));
		locationAddress.appendChild(lAddress1);
		Element lAddress2 = document.createElement("address2");
		lAddress2.appendChild(document.createTextNode(locAddress2));
		locationAddress.appendChild(lAddress2);
		Element lCity = document.createElement("city");
		lCity.appendChild(document.createTextNode(locCity));
		locationAddress.appendChild(lCity);
		Element lState = document.createElement("state");
		lState.appendChild(document.createTextNode(locState));
		locationAddress.appendChild(lState);
		Element lZip = document.createElement("zip");
		lZip.appendChild(document.createTextNode(locZip));
		locationAddress.appendChild(lZip);
		/*Element lZip4 = document.createElement("zip4");
		lZip4.appendChild(document.createTextNode(locZip4));
		locationAddress.appendChild(lZip4);*/
		Element lCountry = document.createElement("country");
		lCountry.appendChild(document.createTextNode(locCountry));
		locationAddress.appendChild(lCountry);

		Element primaryPhoneNumber = document
				.createElement("primaryPhoneNumber");
		primaryPhoneNumber.appendChild(document
				.createTextNode(usrPrimaryPhoneNumber));
		location.appendChild(primaryPhoneNumber);
		Element primaryFaxNumber = document
				.createElement("primaryFaxNumber");
		primaryFaxNumber.appendChild(document
				.createTextNode(usrPrimaryFaxNumber));
		location.appendChild(primaryFaxNumber);
		Element pharmacyContactNumber = document
				.createElement("pharmacyContactNumber");
		pharmacyContactNumber.appendChild(document
				.createTextNode(usrPharmacyContactNumber));
		location.appendChild(pharmacyContactNumber);

		// LicensedPrescriber Element

		Element licensedPrescriber = document
				.createElement("LicensedPrescriber");
		ncScript.appendChild(licensedPrescriber);
		Attr licensedPrescriberAttr = document.createAttribute("ID");
		licensedPrescriberAttr.setValue(licensedPrescriberId);
		licensedPrescriber.setAttributeNode(licensedPrescriberAttr);
		Element licensedPrescriberName = document
				.createElement("LicensedPrescriberName");
		licensedPrescriber.appendChild(licensedPrescriberName);
		Element docLastName = document.createElement("last");
		docLastName.appendChild(document.createTextNode(doctorLastName));
		licensedPrescriberName.appendChild(docLastName);
		Element docFirstName = document.createElement("first");
		docFirstName.appendChild(document.createTextNode(doctorFirstName));
		licensedPrescriberName.appendChild(docFirstName);
		Element docMidName = document.createElement("middle");
		docMidName.appendChild(document.createTextNode(doctorMiddleName));
		licensedPrescriberName.appendChild(docMidName);
		
		/*Element prefix = document.createElement("prefix");
		prefix.appendChild(document.createTextNode(namePrefix));
		licensedPrescriberName.appendChild(prefix);
		Element suffix = document.createElement("suffix");
		suffix.appendChild(document.createTextNode(nameSuffix));
		licensedPrescriberName.appendChild(suffix);*/

		Element dea = document.createElement("dea");
		dea.appendChild(document.createTextNode(prescDea));
		licensedPrescriber.appendChild(dea);
		Element upin = document.createElement("upin");
		upin.appendChild(document.createTextNode(prescUpin));
		licensedPrescriber.appendChild(upin);
		Element licenseState = document.createElement("licenseState");
		licenseState
				.appendChild(document.createTextNode(prescLicenseState));
		licensedPrescriber.appendChild(licenseState);
		Element licenseNumber = document.createElement("licenseNumber");
		licenseNumber.appendChild(document
				.createTextNode(prescLicenseNumber));
		licensedPrescriber.appendChild(licenseNumber);
		Element npi = document.createElement("npi");
		npi.appendChild(document.createTextNode(prescNpi));
		licensedPrescriber.appendChild(npi);

		// Patient Element

		Element patient = document.createElement("Patient");
		ncScript.appendChild(patient);
		Attr patientIdAttr = document.createAttribute("ID");
		patientIdAttr.setValue(patientId);
		patient.setAttributeNode(patientIdAttr);
		Element patientName = document.createElement("PatientName");
		patient.appendChild(patientName);
		Element patLastName = document.createElement("last");
		patLastName.appendChild(document.createTextNode(pLastName));
		patientName.appendChild(patLastName);
		Element patFirstName = document.createElement("first");
		patFirstName.appendChild(document.createTextNode(pFirstName));
		patientName.appendChild(patFirstName);
		Element patMiddleName = document.createElement("middle");
		patMiddleName.appendChild(document.createTextNode(pMiddleName));
		patientName.appendChild(patMiddleName);

		Element medicalRecordNumber = document
				.createElement("medicalRecordNumber");
		medicalRecordNumber.appendChild(document
				.createTextNode(patMedicalRecordNumber));
		patient.appendChild(medicalRecordNumber);

		Element patientAddress = document.createElement("PatientAddress");
		patient.appendChild(patientAddress);
		Element pAddress1 = document.createElement("address1");
		pAddress1.appendChild(document.createTextNode(patAddress1));
		patientAddress.appendChild(pAddress1);
		Element pCity = document.createElement("city");
		pCity.appendChild(document.createTextNode(patCity));
		patientAddress.appendChild(pCity);
		Element pState = document.createElement("state");
		pState.appendChild(document.createTextNode(patState));
		patientAddress.appendChild(pState);
		Element pZip = document.createElement("zip");
		pZip.appendChild(document.createTextNode(patZip));
		patientAddress.appendChild(pZip);
		Element pCountry = document.createElement("country");
		pCountry.appendChild(document.createTextNode(patCountry));
		patientAddress.appendChild(pCountry);

		Element patientContact = document.createElement("PatientContact");
		patient.appendChild(patientContact);
		Element pHomeTelephone = document.createElement("homeTelephone");
		pHomeTelephone.appendChild(document
				.createTextNode(patHomeTelephone));
		patientContact.appendChild(pHomeTelephone);

		Element patientCharacteristics = document
				.createElement("PatientCharacteristics");
		patient.appendChild(patientCharacteristics);
		Element pDob = document.createElement("dob");
		pDob.appendChild(document.createTextNode(patDOB));
		patientCharacteristics.appendChild(pDob);
		Element pGender = document.createElement("gender");
		pGender.appendChild(document.createTextNode(patGender));
		patientCharacteristics.appendChild(pGender);

		// Write code into XML File
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		StringWriter stringWriter = new StringWriter();
		StreamResult result = new StreamResult(stringWriter);
		DOMSource domSource = new DOMSource(document);
		System.out.println("domSource"+domSource+"documentttttttt"+document);
		transformer.transform(domSource, result);

		// Convert Xml format to one line String

		xmlResult = stringWriter.toString();
		System.out.println("XMl Result.........." + xmlResult);

	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return xmlResult;
}

}
