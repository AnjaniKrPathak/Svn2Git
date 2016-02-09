package com.clinakos.viewController.webservicMangedBean;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import https.secure_newcropaccounts_com.v7.webservices.AccountRequest;
import https.secure_newcropaccounts_com.v7.webservices.ArrayOfString;
import https.secure_newcropaccounts_com.v7.webservices.Credentials;
import https.secure_newcropaccounts_com.v7.webservices.DrugFormularyDetail;
import https.secure_newcropaccounts_com.v7.webservices.EligibilityDetailResultV3;
import https.secure_newcropaccounts_com.v7.webservices.FormularyAlternatives;
import https.secure_newcropaccounts_com.v7.webservices.FormularyAlternativesResponse;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverage;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageDetail;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageResponse;
import https.secure_newcropaccounts_com.v7.webservices.ObjectFactory;
import https.secure_newcropaccounts_com.v7.webservices.PatientInformationRequester;
import https.secure_newcropaccounts_com.v7.webservices.PatientRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.data.model.patient.DoctorDetail;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.WebServiceClientImpl;

import static com.clinakos.common.util.ClinakosConstant.*;

public class NcFormulary1WSBean {
	public static final Logger logger = Logger.getLogger("NcFormulary1WSBean.class");
	public void init() throws Exception {

	}

	static https.secure_newcropaccounts_com.v7.webservices.ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();

	@Autowired
	private WebServiceTemplate Formulary1ServiceDetail;
	Properties properties;
	private List<FormularyCoverageDetail> formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>(); 
	 @Autowired
	 private IUserService userService;
	 @Autowired
	 private NcUpdate1WSBean ncUpdate1WSBean;
	 private List<FormularyDetail> formularyDetails=null;
	 private FormularyDetail formularyDetail=null;
	 private AuditInfo auditInfo;
	 Map<String,List<FormularyDetail>> formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
	 List<FormularyDetail> alternativeFormularyDrugList;
	

	/**
	 * @return the properties
	 * @throws IOException 
	 */
	public Properties getProperties() throws IOException {
		if(properties==null){
			properties=new Properties();
		}
		properties.load(NcFormulary1WSBean.class.getClassLoader().getResourceAsStream("/com/clinakos/properties/webservices.properties"));
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
	 * 
	 * @param formulary1ServiceDetail
	 */
	public NcFormulary1WSBean(WebServiceTemplate formulary1ServiceDetail) {

		Formulary1ServiceDetail = formulary1ServiceDetail;
	}

	/**
	 * Plain Constructor
	 */
	public NcFormulary1WSBean() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * Call Formulary Alternative WSDL Request
	 * @throws Exception 
	 */
	/*public void callFormularyAlternatives() throws Exception {
		    String accId = getProperties().getProperty("accountId");  
			String siteId = getProperties().getProperty("siteId");
			String partName=getProperties().getProperty("partnerName");
			String name=getProperties().getProperty("name");
			String password=getProperties().getProperty("password");
			String eligibilityIndex="0";
			
			String patientId="BrucePaltrow-1";
			String userId=getProperties().getProperty("userId");
			String userType=getProperties().getProperty("userType");
			String deugConcept="228042";
			String drugConceptType="F";
			String healthPlanId="133";
			String healthPlanTypeId="S";
			
			
		   AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		   accountRequest.setAccountId(accId);
		   accountRequest.setSiteId(siteId);
		   
		   Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		   credentials.setName(name);
		   credentials.setPartnerName(partName);
		   credentials.setPassword(password);
		   
		   PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		   patientRequest.setPatientId(patientId);
		   
		   PatientInformationRequester patientInformationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		   patientInformationRequester.setUserId(userId);
		   patientInformationRequester.setUserType("%");
		   
		   //Formulary Alternatives Request 
		   FormularyAlternatives formularyAlternativesRequest=WS_CLIENT_FACTORY.createFormularyAlternatives();
		   formularyAlternativesRequest.setAccountRequest(accountRequest);
		   formularyAlternativesRequest.setCredentials(credentials);
		   formularyAlternativesRequest.setPatientInformationRequester(patientInformationRequester);
		   formularyAlternativesRequest.setPatientRequest(patientRequest);
		   formularyAlternativesRequest.setEligibilityIndex(eligibilityIndex);
		   formularyAlternativesRequest.setDrugConcept(deugConcept);
		   formularyAlternativesRequest.setDrugConceptType(drugConceptType);
		   formularyAlternativesRequest.setHealthplanID(healthPlanId);
		   formularyAlternativesRequest.setHealthplanTypeID(healthPlanTypeId);
		   
		   // Formulary Alternatives Response
		   
		   WebServiceClientImpl<FormularyAlternatives> wsClientForFormularyAlternatives=new WebServiceClientImpl<FormularyAlternatives>();
		   
		   FormularyAlternativesResponse formularyAlternativesResponse=(FormularyAlternativesResponse) wsClientForFormularyAlternatives.sendRequestAndReceiveResponse(formularyAlternativesRequest, Formulary1ServiceDetail);
		   if(formularyAlternativesResponse.getFormularyAlternativesResult().getResult().getStatus().value().equals("OK")){
			   if(!(formularyAlternativesResponse.getFormularyAlternativesResult().getFormularyCoverageDetailArray()==null)){
				   formularyCoverageDetailList=formularyAlternativesResponse.getFormularyAlternativesResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail();
			   }
			   
		   }
		   else if (!(formularyAlternativesResponse.getFormularyAlternativesResult().getResult().getStatus().value().equals("OK"))) {
			    getAuditInfo().setComment(formularyAlternativesResponse.getFormularyAlternativesResult().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("FormularyAlternatives WSDL");
				getAuditInfo().setCreateTime(new Date());
                formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>();
				userService.saveWSFailStatus(auditInfo);
			
		  }
	
	}
		*/

		   
		 
		   
	

	/**
	 * @return the formularyCoverageDetailList
	 */
	public List<FormularyCoverageDetail> getFormularyCoverageDetailList() {
		return formularyCoverageDetailList;
	}

	/**
	 * @param formularyCoverageDetailList the formularyCoverageDetailList to set
	 */
	
	public void setFormularyCoverageDetailList(
			List<FormularyCoverageDetail> formularyCoverageDetailList) {
		this.formularyCoverageDetailList = formularyCoverageDetailList;
	}

	/**
	 * Get  FormularyCoverage Using FormularyCoverage  WSDL 
	 * @param providerLocation 
	 * @throws Exception 
	 * 
	 */
	public  Map<String,List<FormularyDetail>> getFormularyCoverage(Integer insuranceId,String medicineForFormulary, ProviderLocation providerLocation) {
		System.out.println(" Insurance Id "+insuranceId +"Medicine Name "+medicineForFormulary);
		formularyDetails=new ArrayList<FormularyDetail>();
		formularyDetail=new FormularyDetail();
		alternativeFormularyDrugList=new ArrayList<FormularyDetail>();
		Integer drugId=0;
		if(!(medicineForFormulary.equals(""))){
			Double drugIdValue=userService.getDrugIdByDrug(medicineForFormulary);
			drugId=(int)drugIdValue.doubleValue();
			
		}
		
	
		try{
		String accId = providerLocation.getAccountId();  
		String siteId = providerLocation.getSiteId();
		logger.info("getFormularyCoverage method start********************"+"drugId "+drugId+"Medicine Name for formulary::::::::::"+medicineForFormulary);
		
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
		String eligibilityIndex="0";
		/*String patientId=Integer.toString(new ContextUtil().getPatientId());*/
		String userId=getProperties().getProperty("userId");
		
		String userType=getProperties().getProperty("userType");
		String healthPlanId=insuranceId.toString();
		String healthPlanTypeId="S";
		String drugConceptType=getProperties().getProperty("drugConceptType");
		String drugConcept=Integer.toString(drugId);;
		ArrayOfString drugConceptArray=new ArrayOfString();
		drugConceptArray.getString().add(drugConcept);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		/*PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);*/
		
		PatientInformationRequester informationRequester =WS_CLIENT_FACTORY.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);
		
		//Forulary Coverage Request 
		FormularyCoverage formularyCoverageRequest=WS_CLIENT_FACTORY.createFormularyCoverage();
		formularyCoverageRequest.setAccountRequest(accountRequest);
		formularyCoverageRequest.setCredentials(credentials);
		/*formularyCoverageRequest.setPatientRequest(patientRequest);*/
		formularyCoverageRequest.setPatientInformationRequester(informationRequester);
		formularyCoverageRequest.setHealthplanID(healthPlanId);
		formularyCoverageRequest.setHealthplanTypeID(healthPlanTypeId);
		formularyCoverageRequest.setEligibilityIndex(eligibilityIndex);
		formularyCoverageRequest.setDrugConceptType(drugConceptType);
		formularyCoverageRequest.setDrugConcept(drugConceptArray);
		
		//Formulary Coverage Response 
		WebServiceClientImpl<FormularyCoverage> wsClientForFormularyCoverage=new WebServiceClientImpl<FormularyCoverage>();
		FormularyCoverageResponse formularyCoverageResponse=(FormularyCoverageResponse) wsClientForFormularyCoverage.sendRequestAndReceiveResponse(formularyCoverageRequest, Formulary1ServiceDetail);
		System.out.println("formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus()"+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus());
		if(!(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK") )){
			
			System.out.println(" Inside if Condition Status NC Formulary WS Bean  "+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus() +"Message" +formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setComment(formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("FormularyCoverage WSDL");
			getAuditInfo().setCreateTime(new Date());
			formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
			userService.saveWSFailStatus(auditInfo);
		}
		else if(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK")){
			System.out.println("Formulary Coverage in formulary 1 bean outside ok if  "+formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail());
		 if(!(formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail()==null)  ){
			System.out.println("Formulary Coverage in formulary 1 bean  insid ok if  "+formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail());
		 List<FormularyCoverageDetail> formularyCoverageDetailList=formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail();
		 for(FormularyCoverageDetail coverageDetail:formularyCoverageDetailList){
			logger.info("getFormulary Coverage Middle inside formulary detail "+"coverageDetail.getFormularyStatus():::::::::::::::::::::::::"+coverageDetail.getFormularyStatus());
			if(coverageDetail.getFormularyStatus().equals("51")){
				//formularyDetails=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept);
				
				System.out.println("Formulary Coverage in formulary 1 bean  "+coverageDetail.getFormularyStatus());
				formularyDetail.setFormulayTier("Tier 1");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Preferred Level 1");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
			else if (coverageDetail.getFormularyStatus().equals("52")) {
				formularyDetail.setFormulayTier("Tier 2");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Preferred Level 2");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
			else if (coverageDetail.getFormularyStatus().equals("53")) {
				formularyDetail.setFormulayTier("Tier 3");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Preferred Level 3");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			 }
			//Added according to #696 ticket
        	else if(StringUtils.isNotBlank(coverageDetail.getFormularyStatus()) && Integer.parseInt(coverageDetail.getFormularyStatus())>=54 && Integer.parseInt(coverageDetail.getFormularyStatus())<=59){
        		formularyDetail.setFormulayTier(TIER_4_LABEL);
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary(TIER_4_PREFERRED_LEVEL);
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
        	}
			else if (coverageDetail.getFormularyStatus().equals("10")) {
				formularyDetail.setFormulayTier("Unlisted Drug");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Unlisted Drug");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
				//formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
			}
			else if (coverageDetail.getFormularyStatus().equals("15")) {
				formularyDetail.setFormulayTier("Not Reimbursed");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Not Reimbursed");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
				
			}
			else if (coverageDetail.getFormularyStatus().equals("20")) {
				formularyDetail.setFormulayTier("Non Formulary");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Non Formulary");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
			else if (coverageDetail.getFormularyStatus().equals("30")) {
				formularyDetail.setFormulayTier("Prior Authorization");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("Prior Authorization");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
			else if (coverageDetail.getFormularyStatus().equals("50")) {
				formularyDetail.setFormulayTier("On Formulary");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("On Formulary");
				formularyDetails.add(formularyDetail);
				alternativeFormularyDrugList=ncUpdate1WSBean.callFormularyAlternativesWithDrugInfo2(healthPlanId,drugConcept,coverageDetail.getFormularyStatus(),providerLocation);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
			else {
				formularyDetail.setFormulayTier("Not In Formulary");
				formularyDetail.setMedicineName(medicineForFormulary);
				formularyDetail.setIsFormulary("No Formulary Coverage");
				formularyDetails.add(formularyDetail);
				formularyAlternativeDrugInfoMap.put("formularyDetails", formularyDetails);
				formularyAlternativeDrugInfoMap.put("alternativeFormularyDrugList", alternativeFormularyDrugList);
			}
		   }
		 
			
		 }
		}
		}
		catch(NumberFormatException nfe){
			nfe.printStackTrace();
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
		logger.info("getFormularyCoverage method END><><><><><><><><<><<><><><><<***"+"formularyCoverageDetailList.size()"+formularyCoverageDetailList.size());
		medicineForFormulary="";
		return formularyAlternativeDrugInfoMap;
		
		
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the ncUpdate1WSBean
	 */
	public NcUpdate1WSBean getNcUpdate1WSBean() {
		return ncUpdate1WSBean;
	}

	/**
	 * @param ncUpdate1WSBean the ncUpdate1WSBean to set
	 */
	public void setNcUpdate1WSBean(NcUpdate1WSBean ncUpdate1WSBean) {
		this.ncUpdate1WSBean = ncUpdate1WSBean;
	}

	/**
	 * @return the formularyDetails
	 */
	public List<FormularyDetail> getFormularyDetails() {
		if(formularyDetails==null){
			formularyDetails=new ArrayList<FormularyDetail>();
		}
		return formularyDetails;
	}

	/**
	 * @param formularyDetails the formularyDetails to set
	 */
	public void setFormularyDetails(List<FormularyDetail> formularyDetails) {
		this.formularyDetails = formularyDetails;
	}

	/**
	 * @return the formularyDetail
	 */
	public FormularyDetail getFormularyDetail() {
		if(formularyDetail==null){
			formularyDetail=new FormularyDetail();
		}
		return formularyDetail;
	}

	/**
	 * @param formularyDetail the formularyDetail to set
	 */
	public void setFormularyDetail(FormularyDetail formularyDetail) {
		this.formularyDetail = formularyDetail;
	}

	/**
	 * @return the auditInfo
	 */
	public AuditInfo getAuditInfo() {
		if(auditInfo==null){
			auditInfo=new AuditInfo();
		}
		return auditInfo;
	}

	/**
	 * @param auditInfo the auditInfo to set
	 */
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	

	/**
	 * @return the alternativeFormularyDrugList
	 */
	public List<FormularyDetail> getAlternativeFormularyDrugList() {
		if(alternativeFormularyDrugList==null){
			alternativeFormularyDrugList=new ArrayList<FormularyDetail>();
		}
		return alternativeFormularyDrugList;
	}

	/**
	 * @param alternativeFormularyDrugList the alternativeFormularyDrugList to set
	 */
	public void setAlternativeFormularyDrugList(
			List<FormularyDetail> alternativeFormularyDrugList) {
		this.alternativeFormularyDrugList = alternativeFormularyDrugList;
	}

	/**
	 * @return the formularyAlternativeDrugInfoMap
	 */
	public Map<String, List<FormularyDetail>> getFormularyAlternativeDrugInfoMap() {
		return formularyAlternativeDrugInfoMap;
	}

	/**
	 * @param formularyAlternativeDrugInfoMap the formularyAlternativeDrugInfoMap to set
	 */
	public void setFormularyAlternativeDrugInfoMap(
			Map<String, List<FormularyDetail>> formularyAlternativeDrugInfoMap) {
		this.formularyAlternativeDrugInfoMap = formularyAlternativeDrugInfoMap;
	}
	/**
	 * 
	 * @param insuranceId
	 * @param medicineForFormulary
	 * @param providerLocation
	 * @return
	 * @throws Exception
	 */
	public  List<FormularyCoverageDetail> getFormularyCoverageForComplianceSymbol(Integer insuranceId,String medicineId, ProviderLocation providerLocation,RoleSecurity roleSecurity) throws Exception {
		
		/*Integer drugId=0;
		if(!(medicineForFormulary.equals(""))){
			Double drugIdValue=userService.getDrugIdByDrugName(medicineForFormulary);
			drugId=(int)drugIdValue.doubleValue();
			
		}*/
		EligibilityDetailResultV3 eligibilityDetailResultV3=ncUpdate1WSBean.callGetPBMEligibilityV3(providerLocation,roleSecurity);
		 List<FormularyCoverageDetail> formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>();
		String accId = providerLocation.getAccountId();  
		String siteId = providerLocation.getSiteId();
		
		
		try{
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
		String eligibilityIndex="0";
		/*String patientId=Integer.toString(new ContextUtil().getPatientId());*/
		String userId=getProperties().getProperty("userId");
		
		String userType=getProperties().getProperty("userType");
		String healthPlanId=eligibilityDetailResultV3.getEligibilityGuid();
		String healthPlanTypeId="R";
		String drugConceptType=getProperties().getProperty("drugConceptType");
		String drugConcept=medicineId;
		logger.info("In NcUpdate WS bean Drug Concept :::"+drugConcept+"In nc update medicine IDDddd"+medicineId);
		ArrayOfString drugConceptArray=new ArrayOfString();
		drugConceptArray.getString().add(drugConcept);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		/*PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);*/
		
		PatientInformationRequester informationRequester =WS_CLIENT_FACTORY.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);
		
		//Forulary Coverage Request 
		FormularyCoverage formularyCoverageRequest=WS_CLIENT_FACTORY.createFormularyCoverage();
		formularyCoverageRequest.setAccountRequest(accountRequest);
		formularyCoverageRequest.setCredentials(credentials);
		/*formularyCoverageRequest.setPatientRequest(patientRequest);*/
		formularyCoverageRequest.setPatientInformationRequester(informationRequester);
		formularyCoverageRequest.setHealthplanID(healthPlanId);
		formularyCoverageRequest.setHealthplanTypeID(healthPlanTypeId);
		formularyCoverageRequest.setEligibilityIndex(eligibilityIndex);
		formularyCoverageRequest.setDrugConceptType(drugConceptType);
		formularyCoverageRequest.setDrugConcept(drugConceptArray);
		
		//Formulary Coverage Response 
		WebServiceClientImpl<FormularyCoverage> wsClientForFormularyCoverage=new WebServiceClientImpl<FormularyCoverage>();
		FormularyCoverageResponse formularyCoverageResponse=(FormularyCoverageResponse) wsClientForFormularyCoverage.sendRequestAndReceiveResponse(formularyCoverageRequest, Formulary1ServiceDetail);
		System.out.println("formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus()"+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus());
		if(!(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK") )){
			
			System.out.println(" Inside if Condition Status NC Formulary WS Bean  "+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus() +"Message" +formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setComment(formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("FormularyCoverage WSDL");
			getAuditInfo().setCreateTime(new Date());
			formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
			userService.saveWSFailStatus(auditInfo);
		}
		else if(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK")){
			
		 if(!(formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail()==null)  ){
			System.out.println("Formulary Coverage in formulary 1 bean  insid ok if  "+formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail());
		  List<FormularyCoverageDetail> foCoverageDetails=formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail();
		
	        for(FormularyCoverageDetail coverageDetail:foCoverageDetails){
	        	FormularyCoverageDetail formularyCoverageDetail=new FormularyCoverageDetail();
	        	if(coverageDetail.getFormularyStatus().equals("50")){
	        		formularyCoverageDetail.setFormularyStatus("On Formulary");
	        	}
	        	else if (coverageDetail.getFormularyStatus().equals("51")) {
	        		formularyCoverageDetail.setFormularyStatus("Tier 1");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("52")) {
					formularyCoverageDetail.setFormularyStatus("Tier 2");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("53")) {
	        		formularyCoverageDetail.setFormularyStatus("Tier 3");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("10")) {
	        		formularyCoverageDetail.setFormularyStatus("Unlisted Drug");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("15")) {
	        		formularyCoverageDetail.setFormularyStatus("Not Reimbursed");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("20")) {
	        		formularyCoverageDetail.setFormularyStatus("Non Formulary");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("30")) {
	        		formularyCoverageDetail.setFormularyStatus("Prior Authorization");
				}
	        	//Added according to #696 ticket
	        	else if(StringUtils.isNotBlank(coverageDetail.getFormularyStatus()) && Integer.parseInt(coverageDetail.getFormularyStatus())>=54 && Integer.parseInt(coverageDetail.getFormularyStatus())<=99){
	        		formularyCoverageDetail.setFormularyStatus(TIER_4_LABEL);
	        	}
	        	/*else {
					formularyCoverageDetail.setFormularyStatus("Non Formulary");
				}*/
	        	formularyCoverageDetailList.add(formularyCoverageDetail);
	         }
		  }
		 
	
	   }
		}
		catch(NumberFormatException nfe){
			logger.info("Number format exception for formulary status In Tiers");
			nfe.printStackTrace();
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
	logger.info("formularyCoverageDetailList in nc Formulary "+formularyCoverageDetailList.size());
		return formularyCoverageDetailList;
	}

	public List<FormularyCoverageDetail> getFormularyCoverageForComplianceSymbolForPatient(
			int insuranceId, String medicineId,
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			Integer patientId, int providerId) throws Exception { 
		EligibilityDetailResultV3 eligibilityDetailResultV3=ncUpdate1WSBean.callGetPBMEligibilityV3ForPatient(providerLocation,roleSecurity,patientId,providerId);
		 List<FormularyCoverageDetail> formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>();
		String accId = providerLocation.getAccountId();  
		String siteId = providerLocation.getSiteId();
		
		
		try{
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
		String eligibilityIndex="0";
		/*String patientId=Integer.toString(new ContextUtil().getPatientId());*/
		String userId=getProperties().getProperty("userId");
		
		String userType=getProperties().getProperty("userType");
		String healthPlanId=eligibilityDetailResultV3.getEligibilityGuid();
		String healthPlanTypeId="R";
		String drugConceptType=getProperties().getProperty("drugConceptType");
		String drugConcept=medicineId;
		logger.info("In NcUpdate WS bean Drug Concept :::"+drugConcept+"In nc update medicine IDDddd"+medicineId);
		ArrayOfString drugConceptArray=new ArrayOfString();
		drugConceptArray.getString().add(drugConcept);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		/*PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);*/
		
		PatientInformationRequester informationRequester =WS_CLIENT_FACTORY.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);
		
		//Forulary Coverage Request 
		FormularyCoverage formularyCoverageRequest=WS_CLIENT_FACTORY.createFormularyCoverage();
		formularyCoverageRequest.setAccountRequest(accountRequest);
		formularyCoverageRequest.setCredentials(credentials);
		/*formularyCoverageRequest.setPatientRequest(patientRequest);*/
		formularyCoverageRequest.setPatientInformationRequester(informationRequester);
		formularyCoverageRequest.setHealthplanID(healthPlanId);
		formularyCoverageRequest.setHealthplanTypeID(healthPlanTypeId);
		formularyCoverageRequest.setEligibilityIndex(eligibilityIndex);
		formularyCoverageRequest.setDrugConceptType(drugConceptType);
		formularyCoverageRequest.setDrugConcept(drugConceptArray);
		
		//Formulary Coverage Response 
		WebServiceClientImpl<FormularyCoverage> wsClientForFormularyCoverage=new WebServiceClientImpl<FormularyCoverage>();
		FormularyCoverageResponse formularyCoverageResponse=(FormularyCoverageResponse) wsClientForFormularyCoverage.sendRequestAndReceiveResponse(formularyCoverageRequest, Formulary1ServiceDetail);
		System.out.println("formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus()"+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus());
		if(!(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK") )){
			
			System.out.println(" Inside if Condition Status NC Formulary WS Bean  "+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus() +"Message" +formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setComment(formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("FormularyCoverage WSDL");
			getAuditInfo().setCreateTime(new Date());
			formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
			userService.saveWSFailStatus(auditInfo);
		}
		else if(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK")){
			
		 if(!(formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail()==null)  ){
			System.out.println("Formulary Coverage in formulary 1 bean  insid ok if  "+formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail());
		  List<FormularyCoverageDetail> foCoverageDetails=formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail();
		
	        for(FormularyCoverageDetail coverageDetail:foCoverageDetails){
	        	FormularyCoverageDetail formularyCoverageDetail=new FormularyCoverageDetail();
	        	if(coverageDetail.getFormularyStatus().equals("50")){
	        		formularyCoverageDetail.setFormularyStatus("On Formulary");
	        	}
	        	else if (coverageDetail.getFormularyStatus().equals("51")) {
	        		formularyCoverageDetail.setFormularyStatus("Tier 1");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("52")) {
					formularyCoverageDetail.setFormularyStatus("Tier 2");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("53")) {
	        		formularyCoverageDetail.setFormularyStatus("Tier 3");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("10")) {
	        		formularyCoverageDetail.setFormularyStatus("Unlisted Drug");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("15")) {
	        		formularyCoverageDetail.setFormularyStatus("Not Reimbursed");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("20")) {
	        		formularyCoverageDetail.setFormularyStatus("Non Formulary");
				}
	        	else if (coverageDetail.getFormularyStatus().equals("30")) {
	        		formularyCoverageDetail.setFormularyStatus("Prior Authorization");
				}
	        	//Added according to #696 ticket
	        	else if(StringUtils.isNotBlank(coverageDetail.getFormularyStatus()) && Integer.parseInt(coverageDetail.getFormularyStatus())>=54 && Integer.parseInt(coverageDetail.getFormularyStatus())<=99){
	        		formularyCoverageDetail.setFormularyStatus(TIER_4_LABEL);
	        	}
	        	/*else {
					formularyCoverageDetail.setFormularyStatus("Non Formulary");
				}*/
	        	formularyCoverageDetailList.add(formularyCoverageDetail);
	         }
		  }
		 
	
	   }
		}
		catch(NumberFormatException nfe){
			logger.info("Number format exception for formulary status In Tiers");
			nfe.printStackTrace();
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
	logger.info("formularyCoverageDetailList in nc Formulary "+formularyCoverageDetailList.size());
		return formularyCoverageDetailList;
	}

	public List<FormularyCoverageDetail> getFormularyCoverageForComplianceSymbolForPatientForMultithreading(
			int insuranceId, String medicineId,
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			Integer patientId, int providerId, DoctorDetail doctorDetailForMultithreading) throws Exception {
			EligibilityDetailResultV3 eligibilityDetailResultV3=ncUpdate1WSBean.callGetPBMEligibilityV3ForPatientForMultithreading(providerLocation,roleSecurity,
								patientId,providerId,doctorDetailForMultithreading);
			 List<FormularyCoverageDetail> formularyCoverageDetailList=new ArrayList<FormularyCoverageDetail>();
			String accId = providerLocation.getAccountId();  
			String siteId = providerLocation.getSiteId();
			
			
			try{
			String partName=getProperties().getProperty("partnerName");
			String name=getProperties().getProperty("name");
			String password=getProperties().getProperty("password");
			String eligibilityIndex="0";
			/*String patientId=Integer.toString(new ContextUtil().getPatientId());*/
			String userId=getProperties().getProperty("userId");
			
			String userType=getProperties().getProperty("userType");
			String healthPlanId=eligibilityDetailResultV3.getEligibilityGuid();
			String healthPlanTypeId="R";
			String drugConceptType=getProperties().getProperty("drugConceptType");
			String drugConcept=medicineId;
			logger.info("In NcUpdate WS bean Drug Concept :::"+drugConcept+"In nc update medicine IDDddd"+medicineId);
			ArrayOfString drugConceptArray=new ArrayOfString();
			drugConceptArray.getString().add(drugConcept);
			
			Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
			credentials.setName(name);
			credentials.setPartnerName(partName);
			credentials.setPassword(password);
			
			AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
			accountRequest.setAccountId(accId);
			accountRequest.setSiteId(siteId);
			
			/*PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
			patientRequest.setPatientId(patientId);*/
			
			PatientInformationRequester informationRequester =WS_CLIENT_FACTORY.createPatientInformationRequester();
			informationRequester.setUserId(userId);
			informationRequester.setUserType(userType);
			
			//Forulary Coverage Request 
			FormularyCoverage formularyCoverageRequest=WS_CLIENT_FACTORY.createFormularyCoverage();
			formularyCoverageRequest.setAccountRequest(accountRequest);
			formularyCoverageRequest.setCredentials(credentials);
			/*formularyCoverageRequest.setPatientRequest(patientRequest);*/
			formularyCoverageRequest.setPatientInformationRequester(informationRequester);
			formularyCoverageRequest.setHealthplanID(healthPlanId);
			formularyCoverageRequest.setHealthplanTypeID(healthPlanTypeId);
			formularyCoverageRequest.setEligibilityIndex(eligibilityIndex);
			formularyCoverageRequest.setDrugConceptType(drugConceptType);
			formularyCoverageRequest.setDrugConcept(drugConceptArray);
			
			//Formulary Coverage Response 
			WebServiceClientImpl<FormularyCoverage> wsClientForFormularyCoverage=new WebServiceClientImpl<FormularyCoverage>();
			FormularyCoverageResponse formularyCoverageResponse=(FormularyCoverageResponse) wsClientForFormularyCoverage.sendRequestAndReceiveResponse(formularyCoverageRequest, Formulary1ServiceDetail);
			System.out.println("formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus()"+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus());
			if(!(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK") )){
				
				System.out.println(" Inside if Condition Status NC Formulary WS Bean  "+formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus() +"Message" +formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
				getAuditInfo().setComment(formularyCoverageResponse.getFormularyCoverageResult().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("FormularyCoverage WSDL");
				getAuditInfo().setCreateTime(new Date());
				formularyAlternativeDrugInfoMap=new HashMap<String, List<FormularyDetail>>();
				userService.saveWSFailStatus(auditInfo);
			}
			else if(formularyCoverageResponse.getFormularyCoverageResult().getResult().getStatus().value().equals("OK")){
				
			 if(!(formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail()==null)  ){
				System.out.println("Formulary Coverage in formulary 1 bean  insid ok if  "+formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail());
			  List<FormularyCoverageDetail> foCoverageDetails=formularyCoverageResponse.getFormularyCoverageResult().getFormularyCoverageDetailArray().getFormularyCoverageDetail();
			
		        for(FormularyCoverageDetail coverageDetail:foCoverageDetails){
		        	FormularyCoverageDetail formularyCoverageDetail=new FormularyCoverageDetail();
		        	if(coverageDetail.getFormularyStatus().equals("50")){
		        		formularyCoverageDetail.setFormularyStatus("On Formulary");
		        	}
		        	else if (coverageDetail.getFormularyStatus().equals("51")) {
		        		formularyCoverageDetail.setFormularyStatus("Tier 1");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("52")) {
						formularyCoverageDetail.setFormularyStatus("Tier 2");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("53")) {
		        		formularyCoverageDetail.setFormularyStatus("Tier 3");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("10")) {
		        		formularyCoverageDetail.setFormularyStatus("Unlisted Drug");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("15")) {
		        		formularyCoverageDetail.setFormularyStatus("Not Reimbursed");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("20")) {
		        		formularyCoverageDetail.setFormularyStatus("Non Formulary");
					}
		        	else if (coverageDetail.getFormularyStatus().equals("30")) {
		        		formularyCoverageDetail.setFormularyStatus("Prior Authorization");
					}
		        	//Added according to #696 ticket
		        	else if(StringUtils.isNotBlank(coverageDetail.getFormularyStatus()) && Integer.parseInt(coverageDetail.getFormularyStatus())>=54 && Integer.parseInt(coverageDetail.getFormularyStatus())<=99){
		        		formularyCoverageDetail.setFormularyStatus(TIER_4_LABEL);
		        	}
		        	/*else {
						formularyCoverageDetail.setFormularyStatus("Non Formulary");
					}*/
		        	formularyCoverageDetailList.add(formularyCoverageDetail);
		         }
			  }
			 
		
		   }
			}
			catch(NumberFormatException nfe){
				logger.info("Number format exception for formulary status In Tiers");
				nfe.printStackTrace();
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
		logger.info("formularyCoverageDetailList in nc Formulary "+formularyCoverageDetailList.size());
			return formularyCoverageDetailList;
		
		
	}
}
