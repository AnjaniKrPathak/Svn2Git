package com.clinakos.viewController.webservicMangedBean;

import java.io.IOException;
import java.io.StringReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.FormularyDetail;
import com.clinakos.data.model.core.PatientFormularyCompositeCopayInfo;
import com.clinakos.data.model.core.PatientFormularyCompositeDrugDetailInfo;
import com.clinakos.data.model.core.PatientPBMDrugHistoryResult;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.core.RoleSecurity;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.WebServiceClientImpl;
import com.clinakos.viewController.bean.PatientMedicineManageBean;

import https.secure_newcropaccounts_com.v7.webservices.AccountRequest;
import https.secure_newcropaccounts_com.v7.webservices.Credentials;
import https.secure_newcropaccounts_com.v7.webservices.FormularyComposite;

import https.secure_newcropaccounts_com.v7.webservices.DrugInteraction;
import https.secure_newcropaccounts_com.v7.webservices.EligibilityDetailResultV3;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCompositeResponse;
import https.secure_newcropaccounts_com.v7.webservices.FormularyCoverageV3;
import https.secure_newcropaccounts_com.v7.webservices.ObjectFactory;
import https.secure_newcropaccounts_com.v7.webservices.PatientDrugDetail;
import https.secure_newcropaccounts_com.v7.webservices.PatientInformationRequester;
import https.secure_newcropaccounts_com.v7.webservices.PatientRequest;
import https.secure_newcropaccounts_com.v7.webservices.Result;
import static com.clinakos.common.util.ClinakosConstant.*;

public class NcFormulary2WSBean {
	public static final Logger logger = Logger.getLogger("NcFormulary2WSBean.class");

	public void init() throws Exception {

	}

	static https.secure_newcropaccounts_com.v7.webservices.ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();

	@Autowired
	private WebServiceTemplate FormularyServiceDetail;
	@Autowired
	private NcUpdate1WSBean updateWebServiceBean;

	@Autowired
	private IUserService userService;
	/*private String otherMedForSearch;*/
	/*private FormularyDetail[] selectedFormulayDrug;*/
	private AuditInfo auditInfo;

	/**
 * 
 */
	public NcFormulary2WSBean() {

		// TODO Auto-generated constructor stub
	}

	/**
	 * @param formularyServiceDetail
	 */
	public NcFormulary2WSBean(WebServiceTemplate webServiceTemplate) {

		this.FormularyServiceDetail = webServiceTemplate;
	}

	Properties properties = null;

	/**
	 * @return the properties
	 * @throws IOException
	 */
	public Properties getProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
		}
		properties.load(NcFormulary2WSBean.class.getClassLoader()
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
	 * Call Formulary Coverage Detail Services
	 * @param providerLocation 
	 * @param drugConcept2 
	 * @param otherMed 
	 * @param roleSecurity 
	 * 
	 * @throws IOException
	 */

	public String callFormularyCompositeDetail(String patientId, String otherMed, String drugConcept, ProviderLocation providerLocation, RoleSecurity roleSecurity)
			 {

		String xmlResponse = "";
        try{
		EligibilityDetailResultV3 eligibilityDetailResultV3 = updateWebServiceBean
				.callGetPBMEligibilityV3(providerLocation,roleSecurity);
		String accId = providerLocation.getAccountId();
		String siteId = providerLocation.getSiteId();
		String partName = getProperties().getProperty("partnerName");
		String name = getProperties().getProperty("name");
		String password = getProperties().getProperty("password");
		String eligibilityIndex = "0";

		//String patientId =patientId ;
		String userId = getProperties().getProperty("userId");
		String userType = getProperties().getProperty("userType");
		String drugConceptType = "F";
		String healthPlanId = eligibilityDetailResultV3.getEligibilityGuid();
		/*String healthPlanId="8510cf10-e713-4f62-bae8-e0a5dc9cb44b";*/
		System.out.println("eligibilityDetailResultV3.getEligibilityGuid()::::::::::   "+healthPlanId);

		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);

		AccountRequest accountRequest = WS_CLIENT_FACTORY
				.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);

		PatientRequest patientRequest = WS_CLIENT_FACTORY
				.createPatientRequest();
		patientRequest.setPatientId(patientId);

		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
				.createPatientInformationRequester();
		informationRequester.setUserId(userId);
		informationRequester.setUserType(userType);

		// Formulary Composite Request

		FormularyComposite formularyCompositeRequest = WS_CLIENT_FACTORY
				.createFormularyComposite();
		formularyCompositeRequest.setAccountRequest(accountRequest);
		formularyCompositeRequest.setCredentials(credentials);
		
		formularyCompositeRequest.setDrugConcept(drugConcept);
		formularyCompositeRequest.setDrugConceptType(drugConceptType);
		formularyCompositeRequest.setEligibilityIndex(eligibilityIndex);
		formularyCompositeRequest.setHealthplanID(healthPlanId);
		formularyCompositeRequest.setHealthplanTypeID("R");
		formularyCompositeRequest.setIncludeSchema("N");
		formularyCompositeRequest
				.setPatientInformationRequester(informationRequester);
		formularyCompositeRequest.setPatientRequest(patientRequest);

		WebServiceClientImpl<FormularyComposite> wsClientForFormularyComposite = new WebServiceClientImpl<FormularyComposite>();
		FormularyCompositeResponse formularyCompositeResponse = (FormularyCompositeResponse) wsClientForFormularyComposite
				.sendRequestAndReceiveResponse(formularyCompositeRequest,
						FormularyServiceDetail);
		
		if (formularyCompositeResponse.getFormularyCompositeResult()
				.getStatus().value().equals("OK")) {
			Result result = formularyCompositeResponse
					.getFormularyCompositeResult();
			if(!(result.getXmlResponse().equals(""))){
				xmlResponse = result.getXmlResponse();
			}
			
		} else if (formularyCompositeResponse.getFormularyCompositeResult()
				.getStatus().value().equals("OK")) {
			getAuditInfo().setComment(
					formularyCompositeResponse.getFormularyCompositeResult()
							.getMessage());
			getAuditInfo().setUserId(new ContextUtil().getLoginId());
			getAuditInfo().setMachineName("FormularyCompositeDetail WSDL");
			getAuditInfo().setCreateTime(new Date());
			userService.saveWSFailStatus(auditInfo);
		}

		System.out.println("XML Response " + xmlResponse);
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
	 * @return the updateWebServiceBean
	 */
	public NcUpdate1WSBean getUpdateWebServiceBean() {
		return updateWebServiceBean;
	}

	/**
	 * @param updateWebServiceBean
	 *            the updateWebServiceBean to set
	 */
	public void setUpdateWebServiceBean(NcUpdate1WSBean updateWebServiceBean) {
		this.updateWebServiceBean = updateWebServiceBean;
	}

	/**
	 * Decode Formulary Composite XML Response Base 64 Encode Format to Base 8
	 * Format
	 * @param roleSecurity 
	 * 
	 * @throws Exception
	 */
	public String decodeFormularyCompositeXmlResponse(String patientId, String otherMed, String drugConcept,
			ProviderLocation providerLocation, RoleSecurity roleSecurity)
			throws Exception {

		String xmlResponse = callFormularyCompositeDetail(patientId,otherMed,drugConcept,providerLocation,roleSecurity);
		String decodedResult="";
		if(!(xmlResponse.equals(""))){
		Base64 decoder = new Base64();
		byte[] decodedByte = (byte[]) decoder.decode(xmlResponse.getBytes());
		decodedResult = new String(decodedByte);
		
		}

		return decodedResult;
	}

	/**
	 * Decode Formulary Composite Result Using Dom Parser
	 * @param roleSecurity 
	 * 
	 * @throws Exception
	 */
	public List<FormularyDetail> convertFormularyCompositeUsingDomParser(
			String patientId, String otherMed, String drugConcept,
			ProviderLocation providerLocation, RoleSecurity roleSecurity)
			throws Exception {
		System.out.println("Dom method started now .........");

	    	List<FormularyDetail> formularyDetails = new ArrayList<FormularyDetail>();
	    	List<FormularyDetail> formularyDetailForCopay=new ArrayList<FormularyDetail>();
	    	List<FormularyDetail> formularyDetailWithCoPay=new ArrayList<FormularyDetail>();
	    	try{
				String formularyComposite = decodeFormularyCompositeXmlResponse(patientId, otherMed, drugConcept, providerLocation,roleSecurity);
				System.out.println("convertFormularyCompositeUsingDomParser in nc formulary ws 2 bean :::::::: "+formularyComposite);
			  if(!(formularyComposite.equals(""))){
				InputSource inputSource = new InputSource(new StringReader(
						formularyComposite));
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder documentBuilder = builderFactory
						.newDocumentBuilder();
				org.w3c.dom.Document document = documentBuilder
						.parse(inputSource);

				Element docElement = document.getDocumentElement();
				NodeList node = docElement.getChildNodes();
				System.out.println(" Total nodes in xml File "
						+ node.getLength());
				
				if (node != null && !(node.getLength()==0)) {
					for (int i = 0; i < node.getLength(); i++) {
						FormularyDetail formularyDetail = new FormularyDetail();

						String formulayTier = "";
						String isFormulary = "";
						String altrnativeDrug = "";
						String medicineName = "";
						String drugId="";
						if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
							Element e1 = (Element) node.item(i);
						
							if (e1.getNodeName().equals("Table3")) {
							
							
								formulayTier = e1
										.getElementsByTagName("FormularyCode")
										.item(0).getFirstChild().getNodeValue();
								isFormulary = e1
										.getElementsByTagName("FormularyText")
										.item(0).getFirstChild().getNodeValue();
								altrnativeDrug = e1
										.getElementsByTagName("DrugInfoPlus")
										.item(0).getFirstChild().getNodeValue();
								medicineName = e1
										.getElementsByTagName("DrugInfoPlus")
										.item(0).getFirstChild().getNodeValue();
								drugId=e1.getElementsByTagName("drugId")
										.item(0).getFirstChild().getNodeValue();
										
								System.out
										.println("In If condition Formulary Tier "
												+ formulayTier
												+ "Dispeense ......."
												+ isFormulary);
								
								String formuTier="";
								if(formulayTier.equals("50")){
									formuTier=ON_FORMULARY;
								}
								else if (formulayTier.equals("51")) {
									formuTier=PREFERRED_LEVEL_1;
								}
								else if (formulayTier.equals("52")) {
									formuTier=PREFERRED_LEVEL_2;
								}
								else if (formulayTier.equals("53")) {
									formuTier=PREFERRED_LEVEL_3;
								}
								else if(StringUtils.isNotBlank(formulayTier) && Integer.parseInt(formulayTier)>=54 && Integer.parseInt(formulayTier)<=99){
									formuTier=TIER_4_LABEL;
								}
								else if(formulayTier.equals("10")){
									formuTier=UNLISTED_DRUG;
								}
								else if(formulayTier.equals("15")){
									formuTier=NOT_REIMBURSED;
								}
								else if(formulayTier.equals("20")){
									formuTier=NON_FORMULARY;
								}
								else if(formulayTier.equals("30")){
									formuTier=PRIOR_AUTHORIZATION;
								}
								
								formularyDetail.setMedicineName(otherMed);
								formularyDetail
										.setAlternateMedicine(altrnativeDrug);
								formularyDetail.setIsFormulary(isFormulary);
								formularyDetail.setFormulayTier(formuTier);
								formularyDetail.setMedicineId(Integer.parseInt(drugId));
								
	                            formularyDetails.add(formularyDetail);
							}
							if(e1.getNodeName().equals("Table4")){
								FormularyDetail detail=new FormularyDetail();
								String copayDesc=e1.getElementsByTagName("CopayDesc").item(0).getFirstChild().getNodeValue();
								String medId=e1.getElementsByTagName("drugId").item(0).getFirstChild().getNodeValue();
								String pharmacyTypeDesc=e1.getElementsByTagName("PharmacyTypeDesc").item(0).getFirstChild().getNodeValue();
								detail.setPatientCopay(copayDesc);
								detail.setMedicineId(Integer.parseInt(medId));
								detail.setPharmacyTypeDesc(pharmacyTypeDesc);
								formularyDetailForCopay.add(detail);
								
								System.out.println("formularyDetailForCopay.size()"+formularyDetailForCopay.size());
							
							}
							
						}
					}
					
				}
				for(FormularyDetail fDetail:formularyDetails){
					FormularyDetail formularyWithCopay=new FormularyDetail();
					
					for(FormularyDetail formCopyDetail:formularyDetailForCopay){
						if(fDetail.getMedicineId()==formCopyDetail.getMedicineId()){
							formularyWithCopay.setPatientCopay(formCopyDetail.getPatientCopay());
							formularyWithCopay.setPharmacyTypeDesc(formCopyDetail.getPharmacyTypeDesc());
						}
						
					}
					formularyWithCopay.setMedicineName(fDetail.getMedicineName());
					formularyWithCopay.setAlternateMedicine(fDetail.getAlternateMedicine());
					formularyWithCopay.setFormulayTier(fDetail.getFormulayTier());
					formularyDetailWithCoPay.add(formularyWithCopay);
					logger.info(formularyDetailWithCoPay.size()+"formularyDetailWithCoPay.size()");
					
				}
				
			  }
				/*if(formularyDetailWithCoPay.isEmpty()){
					formularyDetailWithCoPay=new ArrayList<FormularyDetail>();
				}*/
			
	    	}catch(NumberFormatException nfe){
	    		nfe.printStackTrace();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
		return formularyDetailWithCoPay;
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
	    * Call Formulary Composite OverLoaded Method 
	    * @param providerLocation
	    * @param roleSecurity
	    * @param patientPBMDrugHistoryResultList
	    */
		public ArrayList<PatientFormularyCompositeDrugDetailInfo> callFormularyCompositeDetail(ProviderLocation providerLocation,
				RoleSecurity roleSecurity,
				List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList) {
			System.out.println("callFormularyCompositeDetail ::::::::::::::::::::::::::::;"+patientPBMDrugHistoryResultList.size());
			String xmlResponse = "";
			ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeXmlResponseList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
	        try{
	        	for(PatientPBMDrugHistoryResult drugHistoryResult:patientPBMDrugHistoryResultList){
	        		
	        		System.out.println("Drug Id "+drugHistoryResult.getDrugId()+"Drug Info "+drugHistoryResult.getDrugInfo());
	        		String accId = providerLocation.getAccountId();
	        		String siteId = providerLocation.getSiteId();
	        		String partName = getProperties().getProperty("partnerName");
	        		String name = getProperties().getProperty("name");
	        		String password = getProperties().getProperty("password");
	        		String eligibilityIndex = "0";

	        		//String patientId =patientId ;
	        		String userId = getProperties().getProperty("userId");
	        		String userType = getProperties().getProperty("userType");
	        		String drugConceptType = "F";
	        		String healthPlanId = drugHistoryResult.getPatientPbmEligblityGuid();
	        		/*String healthPlanId ="8510cf10-e713-4f62-bae8-e0a5dc9cb44b";*/

	        		Credentials credentials = WS_CLIENT_FACTORY.createCredentials();
	        		credentials.setName(name);
	        		credentials.setPartnerName(partName);
	        		credentials.setPassword(password);

	        		AccountRequest accountRequest = WS_CLIENT_FACTORY
	        				.createAccountRequest();
	        		accountRequest.setAccountId(accId);
	        		accountRequest.setSiteId(siteId);

	        		PatientRequest patientRequest = WS_CLIENT_FACTORY
	        				.createPatientRequest();
	        		patientRequest.setPatientId(Integer.toString(drugHistoryResult.getPatientId()));
	        		/*patientRequest.setPatientId(Integer.toString(10));*/

	        		PatientInformationRequester informationRequester = WS_CLIENT_FACTORY
	        				.createPatientInformationRequester();
	        		informationRequester.setUserId(userId);
	        		informationRequester.setUserType(userType);

	        		// Formulary Composite Request

	        		FormularyComposite formularyCompositeRequest = WS_CLIENT_FACTORY
	        				.createFormularyComposite();
	        		formularyCompositeRequest.setAccountRequest(accountRequest);
	        		formularyCompositeRequest.setCredentials(credentials);
	        		
	        	   formularyCompositeRequest.setDrugConcept(drugHistoryResult.getDrugId());
	        		/*formularyCompositeRequest.setDrugConcept(Integer.toString(166544));*/ // USEd For Testing 
	        		formularyCompositeRequest.setDrugConceptType(drugConceptType);
	        		formularyCompositeRequest.setEligibilityIndex(eligibilityIndex);
	        		formularyCompositeRequest.setHealthplanID(healthPlanId);
	        		formularyCompositeRequest.setHealthplanTypeID("R");
	        		formularyCompositeRequest.setIncludeSchema("N");
	        		formularyCompositeRequest
	        				.setPatientInformationRequester(informationRequester);
	        		formularyCompositeRequest.setPatientRequest(patientRequest);

	        		WebServiceClientImpl<FormularyComposite> wsClientForFormularyComposite = new WebServiceClientImpl<FormularyComposite>();
	        		FormularyCompositeResponse formularyCompositeResponse = (FormularyCompositeResponse) wsClientForFormularyComposite
	        				.sendRequestAndReceiveResponse(formularyCompositeRequest,
	        						FormularyServiceDetail);
	        		
	        		if (formularyCompositeResponse.getFormularyCompositeResult()
	        				.getStatus().value().equals("OK")) {
	        			
	        			PatientFormularyCompositeDrugDetailInfo xmlResponseDrugDetailInfo=new PatientFormularyCompositeDrugDetailInfo();
	        			Result result = formularyCompositeResponse
	        					.getFormularyCompositeResult();
	        			if(!(result.getXmlResponse().equals(""))){
	        				xmlResponse = result.getXmlResponse();
	        				xmlResponseDrugDetailInfo.setXmlResponse(xmlResponse);
	        				xmlResponseDrugDetailInfo.setPatientId(drugHistoryResult.getPatientId());
	        				xmlResponseDrugDetailInfo.setOrignalDrugId(drugHistoryResult.getDrugId());
	        				formularyCompositeXmlResponseList.add(xmlResponseDrugDetailInfo);
	        			}
	        			
	        		} else if (formularyCompositeResponse.getFormularyCompositeResult()
	        				.getStatus().value().equals("OK")) {
	        			getAuditInfo().setComment(
	        					formularyCompositeResponse.getFormularyCompositeResult()
	        							.getMessage());
	        			getAuditInfo().setUserId(new ContextUtil().getLoginId());
	        			getAuditInfo().setMachineName("FormularyCompositeDetail WSDL");
	        			getAuditInfo().setCreateTime(new Date());
	        			userService.saveWSFailStatus(auditInfo);
	        		}
	        	}
	        	System.out.println("xmlResponseList.size()  "+formularyCompositeXmlResponseList.size());
			

			
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
			return formularyCompositeXmlResponseList;
			
			
		}
	/**
	 * 
	 * @param providerLocation
	 * @param roleSecurity
	 * @param patientPBMDrugHistoryResultList
	 * @return 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	  public Map<String, ArrayList<? extends Object>> convertFormularyCompositeUsingDomParser(
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList) throws ParserConfigurationException, SAXException, IOException {
		
		  System.out.println("convertFormularyCompositeUsingDomParser :::::::::::::::::::");
		  ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeDrugDetailInfoList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
		  ArrayList<PatientFormularyCompositeCopayInfo> formularyCompositeCopayInfoList=new ArrayList<PatientFormularyCompositeCopayInfo>();
		  
		  Map<String, ArrayList<? extends Object>> formularyCompositeDetailMap=new HashMap<String, ArrayList<? extends Object>>();
		  
		  
		  ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeXmlList = decodeFormularyCompositeXmlResponse(providerLocation,roleSecurity,patientPBMDrugHistoryResultList);
		  System.out.println("formularyCompositeXmlList.size():::::::"+formularyCompositeXmlList.size());
		  try{
				for(PatientFormularyCompositeDrugDetailInfo formularyComposite: formularyCompositeXmlList ){
					
					System.out.println("formularyComposite.getXmlResponse()"+formularyComposite.getDecodedXmlResponse());
					
					if(!(formularyComposite.getDecodedXmlResponse().equals(""))){
						InputSource inputSource = new InputSource(new StringReader(formularyComposite.getDecodedXmlResponse()));
								
						DocumentBuilderFactory builderFactory = DocumentBuilderFactory
								.newInstance();
						DocumentBuilder documentBuilder = builderFactory
								.newDocumentBuilder();
						org.w3c.dom.Document document = documentBuilder
								.parse(inputSource);

						Element docElement = document.getDocumentElement();
						NodeList node = docElement.getChildNodes();
						System.out.println(" Total nodes in xml File "
								+ node.getLength());
						
						if (node != null && !(node.getLength()==0)) {
							for (int i = 0; i < node.getLength(); i++) {
								FormularyDetail formularyDetail = new FormularyDetail();
		                       
								//Table3 Attribute 
								String formularyCode="";
								String formularyText="";
								String drugInfoPlus="";
								String drugId="";
								String drugName="";
								String strength="";
								String strengthUOM="";
								String route="";
								String dosageForm="";
								String genericName="";
								String deaClassCode="";
								String deaGenericNamedCode="";
								String deaLegendCode="";
								String therapeuticCategory="";
								String drugNameID="";
								String status="";
								String ndc="";
								
								// Table4 Attribute 
								String  pharmacyTypeDesc="";
								String copayDesc="";
								String copaySummary="";
								String copayTier="";
								String maximumCopayTier="";
								String drugIdForCopayDrug="";
								String copayListType="";
								
								
								if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
									Element e1 = (Element) node.item(i);
									
									
									
								
									if (e1.getNodeName().equals("Table3")) {
									     PatientFormularyCompositeDrugDetailInfo formularyCompositeDrugDetailInfo=new PatientFormularyCompositeDrugDetailInfo();
									    try {
									    	if(!(e1.getElementsByTagName("FormularyCode").item(0).getFirstChild()==null)){
									    		formularyCode = e1.getElementsByTagName("FormularyCode").item(0).getFirstChild().getNodeValue();
									    		formularyCompositeDrugDetailInfo.setFormularyCode(formularyCode);
									    	}
									    	if(!(e1.getElementsByTagName("FormularyText").item(0).getFirstChild()==null)){
									    		formularyText = e1.getElementsByTagName("FormularyText").item(0).getFirstChild().getNodeValue();
									    		 formularyCompositeDrugDetailInfo.setFormularyText(formularyText);
									    	}
											if(!(e1.getElementsByTagName("DrugInfoPlus").item(0).getFirstChild()==null)){
												drugInfoPlus = e1.getElementsByTagName("DrugInfoPlus").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDrugInfoPlus(drugInfoPlus);
											}
											if(!(e1.getElementsByTagName("drugId").item(0).getFirstChild()==null)){
												drugId=e1.getElementsByTagName("drugId").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDrugId(drugId);
											}
										    if(!(e1.getElementsByTagName("DrugName").item(0).getFirstChild()==null)){
										    	drugName=e1.getElementsByTagName("DrugName").item(0).getFirstChild().getNodeValue();
										    	formularyCompositeDrugDetailInfo.setDrugName(drugName);
										    }
											if(!(e1.getElementsByTagName("Strength").item(0).getFirstChild()==null)){
												strength=e1.getElementsByTagName("Strength").item(0).getFirstChild().getNodeValue();
												  formularyCompositeDrugDetailInfo.setStrength(strength);
											}
											if(!(e1.getElementsByTagName("StrengthUOM").item(0).getFirstChild()==null)){
												strengthUOM=e1.getElementsByTagName("StrengthUOM").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setStrengthUOM(strengthUOM);
											}
											if(!(e1.getElementsByTagName("Route").item(0).getFirstChild()==null)){
												route=e1.getElementsByTagName("Route").item(0).getFirstChild().getNodeValue();
												 formularyCompositeDrugDetailInfo.setRoute(route);
											}
											if(!(e1.getElementsByTagName("DosageForm").item(0).getFirstChild()==null)){
												dosageForm=e1.getElementsByTagName("DosageForm").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDosageForm(dosageForm);
											}
											
											if(!(e1.getElementsByTagName("GenericName").item(0)==null)){
												if(!(e1.getElementsByTagName("GenericName").item(0).getFirstChild()==null)){
													genericName=e1.getElementsByTagName("GenericName").item(0).getFirstChild().getNodeValue();
												     formularyCompositeDrugDetailInfo.setGenericName(genericName);
												}
												
											}
											if(!(e1.getElementsByTagName("DeaClassCode").item(0).getFirstChild()==null)){
												deaClassCode=e1.getElementsByTagName("DeaClassCode").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDeaClassCode(deaClassCode);
											}
											if(!(e1.getElementsByTagName("DeaGenericNamedCode").item(0).getFirstChild()==null)){
												deaGenericNamedCode=e1.getElementsByTagName("DeaGenericNamedCode").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDeaGenericNamedCode(deaGenericNamedCode);
											}
										   if((e1.getElementsByTagName("DeaLegendCode").item(0).getFirstChild()==null)){
											   deaLegendCode=e1.getElementsByTagName("DeaLegendCode").item(0).getFirstChild().getNodeValue();
											   formularyCompositeDrugDetailInfo.setDeaLegendCode(deaLegendCode);
										   }
										   if(!(e1.getElementsByTagName("TherapeuticCategory").item(0).getFirstChild()==null)){
											   therapeuticCategory=e1.getElementsByTagName("TherapeuticCategory").item(0).getFirstChild().getNodeValue(); 
											   formularyCompositeDrugDetailInfo.setTherapeuticCategory(therapeuticCategory);
										   }
										   if(!(e1.getElementsByTagName("DrugNameID").item(0).getFirstChild()==null)){
											   drugNameID=e1.getElementsByTagName("DrugNameID").item(0).getFirstChild().getNodeValue();
												formularyCompositeDrugDetailInfo.setDrugNameID(drugNameID);
										   }
										   if(!(e1.getElementsByTagName("Status").item(0).getFirstChild()==null)){
												status=e1.getElementsByTagName("Status").item(0).getFirstChild().getNodeValue();
												  formularyCompositeDrugDetailInfo.setStatus(status);
										   }
										   if(!(e1.getElementsByTagName("NDC").item(0).getFirstChild()==null)){
											   ndc=e1.getElementsByTagName("NDC").item(0).getFirstChild().getNodeValue();
											   formularyCompositeDrugDetailInfo.setNdc(ndc);
										   }
											
											
											//Set Table3 Formulary Composite value in Java Class PatientFormularyCompositeDrugDetailInfo 
											
									       
									   
									        
									        formularyCompositeDrugDetailInfo.setPatientId(formularyComposite.getPatientId());
									        formularyCompositeDrugDetailInfo.setOrignalDrugId(formularyComposite.getOrignalDrugId());
									        
									        formularyCompositeDrugDetailInfoList.add(formularyCompositeDrugDetailInfo);
										} catch (Exception e) {
											// TODO: handle exception
											e.printStackTrace();
										}
										
										
				                      
									}
									// Formulary Composite Table 4 Element 
									if(e1.getNodeName().equals("Table4")){
										PatientFormularyCompositeCopayInfo compositeCopayInfo=new PatientFormularyCompositeCopayInfo();
										if(!(e1.getElementsByTagName("PharmacyTypeDesc").item(0).getFirstChild()==null)){
											pharmacyTypeDesc=e1.getElementsByTagName("PharmacyTypeDesc").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setPharmacyTypeDesc(pharmacyTypeDesc);
										}
										if(!(e1.getElementsByTagName("CopayDesc").item(0).getFirstChild()==null)){
											copayDesc=e1.getElementsByTagName("CopayDesc").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setCopayDesc(copayDesc);
										}
										if(!(e1.getElementsByTagName("drugId").item(0).getFirstChild()==null)){
											drugIdForCopayDrug=e1.getElementsByTagName("drugId").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setDrugId(drugIdForCopayDrug);
										}
									
										if(!(e1.getElementsByTagName("CopaySummary").item(0)==null)){
											if(!(e1.getElementsByTagName("CopaySummary").item(0).getFirstChild()==null)){
												 copaySummary=e1.getElementsByTagName("CopaySummary").item(0).getFirstChild().getNodeValue();
												 compositeCopayInfo.setCopaySummary(copaySummary);
											}
											
										}
										if(!(e1.getElementsByTagName("CopayTier").item(0).getFirstChild()==null)){
											copayTier=e1.getElementsByTagName("CopayTier").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setCopayTier(copayTier);
										}
										if(!(e1.getElementsByTagName("MaximumCopayTier").item(0).getFirstChild()==null)){
											maximumCopayTier=e1.getElementsByTagName("MaximumCopayTier").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setMaximumCopayTier(maximumCopayTier);
										}
										if(!(e1.getElementsByTagName("copayListType").item(0).getFirstChild()==null)){
											copayListType=e1.getElementsByTagName("copayListType").item(0).getFirstChild().getNodeValue();
											compositeCopayInfo.setCopayListType(copayListType);
										}
										
										
										
									
										
										
										
									
										compositeCopayInfo.setPatientId(formularyComposite.getPatientId());
										
										compositeCopayInfo.setOriginalDrugId(formularyComposite.getOrignalDrugId());
										
										formularyCompositeCopayInfoList.add(compositeCopayInfo);
										
									}
									// Table 5 Attribute 
									/*if(e1.getNodeName().equals("Table5")){
										String formularyCodeForTab5=e1.getElementsByTagName("FormularyCode").item(0).getFirstChild().getNodeValue();
										String formularyTextForTab5=e1.getElementsByTagName("FormularyText").item(0).getFirstChild().getNodeValue();
										String drugInfoPlusForTab5=e1.getElementsByTagName("DrugInfoPlus").item(0).getFirstChild().getNodeValue();
										String drugIdForTab5=e1.getElementsByTagName("drugId").item(0).getFirstChild().getNodeValue();
										System.out.println("formularyCodeForTab5"+formularyCodeForTab5+"formularyTextForTab5   "+formularyTextForTab5 +" drugInfoPlusForTab5"+drugInfoPlusForTab5 );
										
									}*/
								
									
								}
							}
							
						}
							
						}
					formularyCompositeDetailMap.put("Table3",  formularyCompositeDrugDetailInfoList);
					formularyCompositeDetailMap.put("Table4", formularyCompositeCopayInfoList);
				}
		  }
		  catch(NullPointerException ne){
			  ne.printStackTrace();
		  }

			return formularyCompositeDetailMap;
		  
			
		  }
			

		

	/**
	 * 
	 * @param providerLocation
	 * @param roleSecurity
	 * @param patientPBMDrugHistoryResultList
	 * @return
	 */
	private ArrayList<PatientFormularyCompositeDrugDetailInfo> decodeFormularyCompositeXmlResponse(
			ProviderLocation providerLocation, RoleSecurity roleSecurity,
			List<PatientPBMDrugHistoryResult> patientPBMDrugHistoryResultList) {
		
		
		ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositerDecodedXmlResponseList=new ArrayList<PatientFormularyCompositeDrugDetailInfo>();
		
		ArrayList<PatientFormularyCompositeDrugDetailInfo> formularyCompositeXmlResponseList = callFormularyCompositeDetail(providerLocation,roleSecurity,patientPBMDrugHistoryResultList);
		
		

		
		for(PatientFormularyCompositeDrugDetailInfo xmlResponse:formularyCompositeXmlResponseList){
			
			PatientFormularyCompositeDrugDetailInfo formularyCompositeDecodeXmlResponse=new PatientFormularyCompositeDrugDetailInfo();
			if(!(xmlResponse.equals(""))){
				
				Base64 decoder = new Base64();
				byte[] decodedByte = (byte[]) decoder.decode(xmlResponse.getXmlResponse().getBytes());
				String decodedResult = new String(decodedByte);
				System.out.println("decodedResult"+decodedResult);
				formularyCompositeDecodeXmlResponse.setDecodedXmlResponse(decodedResult);
				formularyCompositeDecodeXmlResponse.setPatientId(xmlResponse.getPatientId());
				formularyCompositeDecodeXmlResponse.setOrignalDrugId(xmlResponse.getOrignalDrugId());
				
				formularyCompositerDecodedXmlResponseList.add(formularyCompositeDecodeXmlResponse);
				}
			
			System.out.println("decodeResultList.size()++++++++++  "+formularyCompositeXmlResponseList.size());
		}
		
		
		return formularyCompositerDecodedXmlResponseList;
	}



	

}
