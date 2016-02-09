package com.clinakos.viewController.webservicMangedBean;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.patient.PatientAllergy;
import com.clinakos.data.model.patient.PatientMedicationData;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.WebServiceClientImpl;
import com.clinakos.viewController.bean.PatientMedicineManageBean;

import https.secure_newcropaccounts_com.v7.webservices.AccountRequest;
import https.secure_newcropaccounts_com.v7.webservices.ArrayOfString;
import https.secure_newcropaccounts_com.v7.webservices.Credentials;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyDetailV2;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyInteraction;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyInteractionResponse;
import https.secure_newcropaccounts_com.v7.webservices.DrugAllergyInteractionV2;
import https.secure_newcropaccounts_com.v7.webservices.DrugDrugInteraction;
import https.secure_newcropaccounts_com.v7.webservices.DrugDrugInteractionResponse;
import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;
import https.secure_newcropaccounts_com.v7.webservices.ObjectFactory;
import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyDetail;
import https.secure_newcropaccounts_com.v7.webservices.PatientAllergyExtendedDetailV4;
import https.secure_newcropaccounts_com.v7.webservices.PatientInformationRequester;
import https.secure_newcropaccounts_com.v7.webservices.PatientRequest;

public class NcDrugDetailWSBean {
	public static final Logger logger = Logger.getLogger("NcDrugDetailWSBean.class");
	static https.secure_newcropaccounts_com.v7.webservices.ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();
	
	@Autowired
	private WebServiceTemplate DrugServiceDetail;
	@Autowired
	IUserService userService;
	private AuditInfo auditInfo;
	//private PatientMedicineManageBean patientMedicineManageBeanInNcDrugDetail= new PatientMedicineManageBean();
	

	/**
	 * Plain constructor
	 */
	public NcDrugDetailWSBean() {
		
		// TODO Auto-generated constructor stub
	}

 
	/**
	 * @param webServiceTemplate
	 */
	public NcDrugDetailWSBean(WebServiceTemplate webServiceTemplate) {
		
		this.DrugServiceDetail = webServiceTemplate;
	}
	
	Properties properties=null;
	
	/**
	 * Call Drug Drug Interaction Using Drug Services 
	 * @param patientMedicationDataList 
	 * @param providerLocation 
	 * @param selectedDrugId 
	 * @param d 
	 * @param d 
	 * @throws Exception 
	 * 
	 */
  public List<DrugInteraction> callDrugDrugInteraction(List<PatientMedicationData> patientMedicationDataList, ProviderLocation providerLocation, double selectedDrugId) {
	  
	  
	  System.out.println("patientMedicineManageBeanInNcDrugDetail::::::::::"+selectedDrugId);
	  List<DrugInteraction> drugInteractionList=new ArrayList<DrugInteraction>();
	  String accId = providerLocation.getAccountId(); 
	  String siteId = providerLocation.getSiteId();
	  try{
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
	  
		String patientId=Integer.toString(new ContextUtil().getPatientId());
		
		 logger.info("callDrugDrugInteraction:::::::::::"+accId+"::::siteId:"+siteId);
		String currentMedication="";
		String proposedMedication="";
		
		
		ArrayOfString currentMedications=new ArrayOfString();
		
		ArrayOfString proposedMedications=new ArrayOfString();
		
		if(!(selectedDrugId==0.0))
		{
			int currentMedicationId=(int) selectedDrugId;
			currentMedication=Integer.toString(currentMedicationId);
			currentMedications.getString().add(currentMedication);
			System.out.println("current Medication...12345.in for loop "+currentMedications+"In Int "+currentMedicationId+":::::::");
		
		}
		
		else
		{
		
		for(int i=0;i< patientMedicationDataList.size();i++){
			if(i==0){
				int currentMedicationId=(int) patientMedicationDataList.get(0).getDrugId();
				currentMedication=Integer.toString(currentMedicationId);
				currentMedications.getString().add(currentMedication);
				System.out.println("current Medication....in for loop "+currentMedication+"In Int "+currentMedicationId);
				break;
				
			}
			
		}
		}
		
		for(PatientMedicationData ptMdDta:patientMedicationDataList){
			int proposedMedicationId=(int) ptMdDta.getDrugId();
			proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
		}
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);
		
		PatientInformationRequester informationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		//informationRequester.setUserId(userId);
		//informationRequester.setUserType(userType);
		
		//Drug Drug Interaction Request 
		DrugDrugInteraction drugDrugInteractionRequest=WS_CLIENT_FACTORY.createDrugDrugInteraction();
		drugDrugInteractionRequest.setAccountRequest(accountRequest);
		drugDrugInteractionRequest.setCredentials(credentials);
		drugDrugInteractionRequest.setPatientInformationRequester(informationRequester);
		drugDrugInteractionRequest.setDrugStandardType("FDB");
		drugDrugInteractionRequest.setCurrentMedications(currentMedications);
		drugDrugInteractionRequest.setProposedMedications(proposedMedications);
		
		//Drug Drug Interaction Response
		
		WebServiceClientImpl<DrugDrugInteraction> wsClientForDrugDrugInteraction=new WebServiceClientImpl<DrugDrugInteraction>();
		DrugDrugInteractionResponse drugDrugInteractionResponse=(DrugDrugInteractionResponse) wsClientForDrugDrugInteraction.sendRequestAndReceiveResponse(drugDrugInteractionRequest, DrugServiceDetail);
		
		if(!(drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus().value().equals("OK"))){
			getAuditInfo().setComment(drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("DrugDrugInteraction WSDL");
			getAuditInfo().setCreateTime(new Date());
			drugInteractionList=new ArrayList<DrugInteraction>();
			userService.saveWSFailStatus(auditInfo);
		}
		else if (drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus().value().equals("OK")) {
			System.out.println("Status of drug drug Interaction "+drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus());
			if(!(drugDrugInteractionResponse.getDrugDrugInteractionResult().getDrugInteractionArray()==null)){
				 drugInteractionList=drugDrugInteractionResponse.getDrugDrugInteractionResult().getDrugInteractionArray().getDrugInteraction();
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
	  catch(Exception e){
		  e.printStackTrace();
	  }
		 logger.info("callDrugDrugInteraction:::::::::END***************************8::"+accId+"::::siteId:"+siteId);
		return drugInteractionList;	
		
  }


	/**
	 * @return the properties
	 * @throws IOException 
	 */
	public Properties getProperties() throws IOException {
		if(properties==null){
			properties=new Properties();
		}
		properties.load(NcDrugDetailWSBean.class.getClassLoader().getResourceAsStream("/com/clinakos/properties/webservices.properties"));
		return properties;
	}


	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
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
 * Call Drug Allergy  Intreaction API 
 * @param patientId
 * @param patientAllergyList
 * @param patientMedicationDataList
 * @param providerLocation
 * @param selectedDrugId 
 * @return
 * @throws Exception 
 */
	public List<DrugAllergyDetail> getDrugAllergyInteraction(String patientId, List<PatientAllergy> patientAllergyList,List<PatientMedicationData> patientMedicationDataList,
			ProviderLocation providerLocation, double selectedDrugId)  {
		// TODO Auto-generated method stub
		System.out.println("Drug Allergy Method intraction Started");
		System.out.println("selectedDrugId:::::::"+selectedDrugId);
		
		List<DrugAllergyDetail> drugAllergyDetails=new ArrayList<DrugAllergyDetail>();
		try{
			
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String accId =  providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String userId = Integer.toString(providerLocation.getProviderId());//Integer.toString(new ContextUtil().getProviderId()); -- modify by saurabh for multithreading
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
		List<DrugAllergyDetailV2> druAllergyDetailV2List=new ArrayList<DrugAllergyDetailV2>();
		PatientInformationRequester pInformationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		pInformationRequester.setUserId(userId);
		pInformationRequester.setUserType(userType);
		
		ArrayOfString allergies=new ArrayOfString();
		for(PatientAllergy patAllergy:patientAllergyList){
			
			String allergy=""+patAllergy.getCompositeAllergyId();
			System.out.println("patAllergy.getAllergyId()"+patAllergy.getCompositeAllergyId()+"allergy "+allergy);
			allergies.getString().add(allergy);
		}
		ArrayOfString proposedMedications=new ArrayOfString();
		String proposedMedication="";
		
		if(!(selectedDrugId==0.0))
		{
			int proposedMedicationId=(int) selectedDrugId;
			proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
			System.out.println("Allergy Medication...12345.in for loop "+proposedMedications+"drug allergy:::: "+proposedMedication+":::::::");
		
		}
		
		else
		{
		
		for(PatientMedicationData patMedData:patientMedicationDataList){
			int proposedMedicationId=(int) patMedData.getDrugId();
			 proposedMedication=Integer.toString(proposedMedicationId);
			 System.out.println("proposedMedication ::::::::;"+proposedMedication);
			proposedMedications.getString().add(proposedMedication);
		}
	}	
		//Drug Drug Inteaction Request 
		DrugAllergyInteraction drugAllergyInteractionRequest=WS_CLIENT_FACTORY.createDrugAllergyInteraction();
		drugAllergyInteractionRequest.setAccountRequest(accountRequest);
		drugAllergyInteractionRequest.setAllergies(allergies);
		drugAllergyInteractionRequest.setCredentials(credentials);
		drugAllergyInteractionRequest.setDrugStandardType("FDB");
		drugAllergyInteractionRequest.setPatientInformationRequester(pInformationRequester);
		drugAllergyInteractionRequest.setPatientRequest(patientRequest);
		drugAllergyInteractionRequest.setProposedMedications(proposedMedications);
		
		//Drug Allergy Interaction Response 
		WebServiceClientImpl<DrugAllergyInteraction> wsClientForDrugAllergyInteraction=new WebServiceClientImpl<DrugAllergyInteraction>();
		DrugAllergyInteractionResponse drugAllergyInteractionResponse=new DrugAllergyInteractionResponse();
		drugAllergyInteractionResponse=(DrugAllergyInteractionResponse) wsClientForDrugAllergyInteraction.sendRequestAndReceiveResponse(drugAllergyInteractionRequest, DrugServiceDetail);
		if(drugAllergyInteractionResponse.getDrugAllergyInteractionResult().getResult().getStatus().value().equals("OK")){
			if(!(drugAllergyInteractionResponse.getDrugAllergyInteractionResult().getDrugAllergyDetailArray()==null)){
				drugAllergyDetails=drugAllergyInteractionResponse.getDrugAllergyInteractionResult().getDrugAllergyDetailArray().getDrugAllergyDetail();
			}
		}
		else if (drugAllergyInteractionResponse.getDrugAllergyInteractionResult().getResult().getStatus().value().equals("OK")) {
			getAuditInfo().setComment(drugAllergyInteractionResponse.getDrugAllergyInteractionResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("DrugAllergyInteraction API");
			getAuditInfo().setCreateTime(new Date());
			drugAllergyDetails=new ArrayList<DrugAllergyDetail>();
			userService.saveWSFailStatus(auditInfo);
		}
		System.out.println("drugAllergyDetails.size()"+drugAllergyDetails.size());
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
		return drugAllergyDetails;
	}


public List<DrugInteraction> callDrugDrugInteractionAdmin(
		List<PatientMedicationData> patientMedicationDataList,
		ProviderLocation providerLocation, double selectedDrugId, int patientIdVal) {
	  System.out.println("patientMedicineManageBeanInNcDrugDetail::::::::::"+selectedDrugId);
	  List<DrugInteraction> drugInteractionList=new ArrayList<DrugInteraction>();
	  String accId = providerLocation.getAccountId(); 
	  String siteId = providerLocation.getSiteId();
	  try{
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
	  
		String patientId=Integer.toString(patientIdVal);
		
		 logger.info("callDrugDrugInteraction:::::::::::"+accId+"::::siteId:"+siteId);
		String currentMedication="";
		String proposedMedication="";
		
		
		ArrayOfString currentMedications=new ArrayOfString();
		
		ArrayOfString proposedMedications=new ArrayOfString();
		
		if(!(selectedDrugId==0.0))
		{
			int currentMedicationId=(int) selectedDrugId;
			currentMedication=Integer.toString(currentMedicationId);
			currentMedications.getString().add(currentMedication);
			System.out.println("current Medication...12345.in for loop "+currentMedications+"In Int "+currentMedicationId+":::::::");
		
		}
		
		else
		{
		
		for(int i=0;i< patientMedicationDataList.size();i++){
			if(i==0){
				int currentMedicationId=(int) patientMedicationDataList.get(0).getDrugId();
				currentMedication=Integer.toString(currentMedicationId);
				currentMedications.getString().add(currentMedication);
				System.out.println("current Medication....in for loop "+currentMedication+"In Int "+currentMedicationId);
				break;
				
			}
			
		}
		}
		
		for(PatientMedicationData ptMdDta:patientMedicationDataList){
			int proposedMedicationId=(int) ptMdDta.getDrugId();
			proposedMedication=Integer.toString(proposedMedicationId);
			proposedMedications.getString().add(proposedMedication);
		}
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		PatientRequest patientRequest=WS_CLIENT_FACTORY.createPatientRequest();
		patientRequest.setPatientId(patientId);
		
		PatientInformationRequester informationRequester=WS_CLIENT_FACTORY.createPatientInformationRequester();
		//informationRequester.setUserId(userId);
		//informationRequester.setUserType(userType);
		
		//Drug Drug Interaction Request 
		DrugDrugInteraction drugDrugInteractionRequest=WS_CLIENT_FACTORY.createDrugDrugInteraction();
		drugDrugInteractionRequest.setAccountRequest(accountRequest);
		drugDrugInteractionRequest.setCredentials(credentials);
		drugDrugInteractionRequest.setPatientInformationRequester(informationRequester);
		drugDrugInteractionRequest.setDrugStandardType("FDB");
		drugDrugInteractionRequest.setCurrentMedications(currentMedications);
		drugDrugInteractionRequest.setProposedMedications(proposedMedications);
		
		//Drug Drug Interaction Response
		
		WebServiceClientImpl<DrugDrugInteraction> wsClientForDrugDrugInteraction=new WebServiceClientImpl<DrugDrugInteraction>();
		DrugDrugInteractionResponse drugDrugInteractionResponse=(DrugDrugInteractionResponse) wsClientForDrugDrugInteraction.sendRequestAndReceiveResponse(drugDrugInteractionRequest, DrugServiceDetail);
		
		if(!(drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus().value().equals("OK"))){
			getAuditInfo().setComment(drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("DrugDrugInteraction WSDL");
			getAuditInfo().setCreateTime(new Date());
			drugInteractionList=new ArrayList<DrugInteraction>();
			userService.saveWSFailStatus(auditInfo);
		}
		else if (drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus().value().equals("OK")) {
			System.out.println("Status of drug drug Interaction "+drugDrugInteractionResponse.getDrugDrugInteractionResult().getResult().getStatus());
			if(!(drugDrugInteractionResponse.getDrugDrugInteractionResult().getDrugInteractionArray()==null)){
				 drugInteractionList=drugDrugInteractionResponse.getDrugDrugInteractionResult().getDrugInteractionArray().getDrugInteraction();
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
	  catch(Exception e){
		  e.printStackTrace();
	  }
		 logger.info("callDrugDrugInteraction:::::::::END***************************8::"+accId+"::::siteId:"+siteId);
		return drugInteractionList;	
}

	
	
}
