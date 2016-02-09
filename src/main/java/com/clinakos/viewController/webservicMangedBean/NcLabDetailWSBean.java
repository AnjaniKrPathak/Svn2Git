package com.clinakos.viewController.webservicMangedBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Node;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;







import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.llp.HL7Reader;
import ca.uhn.hl7v2.llp.LLPException;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.Primitive;
import ca.uhn.hl7v2.model.v23.segment.MSH;
import ca.uhn.hl7v2.model.v26.message.ACK;
import ca.uhn.hl7v2.parser.DefaultXMLParser;
import ca.uhn.hl7v2.parser.Parser;
import ca.uhn.hl7v2.parser.PipeParser;
import ca.uhn.hl7v2.parser.XMLParser;
import ca.uhn.hl7v2.util.Terser;
import ca.uhn.hl7v2.validation.EncodingRule;
import ca.uhn.hl7v2.validation.MessageRule;
import ca.uhn.hl7v2.validation.MessageValidator;
import ca.uhn.hl7v2.validation.PrimitiveTypeRule;
import ca.uhn.hl7v2.validation.ValidationContext;
import ca.uhn.hl7v2.validation.ValidationException;
import ca.uhn.hl7v2.validation.impl.NoValidation;

import com.clinakos.common.util.ContextUtil;
import com.clinakos.data.model.core.EmdeonLabResult;
import com.clinakos.data.model.core.AuditInfo;
import com.clinakos.data.model.core.InsuranceCompanies;
import com.clinakos.data.model.core.ProviderLocation;
import com.clinakos.data.model.patient.EmdeonLabOrders;
import com.clinakos.data.model.patient.LabOrderCommonSegment;
import com.clinakos.data.model.patient.LabOrderObservationDetail;
import com.clinakos.data.model.patient.LabOrderObservationRequestDetail;
import com.clinakos.data.model.patient.LabResultDetailClinakos;
import com.clinakos.data.model.patient.ORUPatientLabResult;
import com.clinakos.data.model.patient.PatientDiagnosesDetails;
import com.clinakos.data.model.patient.PatientGuarantor;
import com.clinakos.data.model.patient.ProcedureResultHistory;
import com.clinakos.data.model.patient.ProcedureType;
import com.clinakos.data.model.patient.UserInsuranceDetails;
import com.clinakos.service.IUserService;
import com.clinakos.service.serviceImpl.WebServiceClientImpl;
import com.sun.org.apache.xml.internal.serializer.SerializerFactory;

import https.secure_newcropaccounts_com.v7.webservices.AccountRequest;
import https.secure_newcropaccounts_com.v7.webservices.Credentials;
import https.secure_newcropaccounts_com.v7.webservices.GetLabOrders;
import https.secure_newcropaccounts_com.v7.webservices.GetLabOrdersResponse;
import https.secure_newcropaccounts_com.v7.webservices.GetLabResults;
import https.secure_newcropaccounts_com.v7.webservices.GetLabResultsPrototype;
import https.secure_newcropaccounts_com.v7.webservices.GetLabResultsPrototypeResponse;
import https.secure_newcropaccounts_com.v7.webservices.GetLabResultsResponse;
import https.secure_newcropaccounts_com.v7.webservices.LabResultDetail;
import https.secure_newcropaccounts_com.v7.webservices.ObjectFactory;


public class NcLabDetailWSBean {
	 public static final Logger logger = Logger.getLogger("NcLabDetailWSBean.class");
	 static https.secure_newcropaccounts_com.v7.webservices.ObjectFactory WS_CLIENT_FACTORY = new ObjectFactory();
	 @Autowired
	 private WebServiceTemplate labServicesDetail;
	 private List<LabResultDetail> labResultDetailList;
     private AuditInfo auditInfo;
	 @Autowired
     IUserService userService;
	 Properties properties=null;
	 
	 
	 /**
	  * Plain constructor.
	  */
	 public NcLabDetailWSBean(){
		 
	 }
	public NcLabDetailWSBean(WebServiceTemplate webServiceTemplate) {
		
		this.labServicesDetail = webServiceTemplate;
	}
	 
	
	
	/**
	 * Call GetLabResultPrototype
	 * @throws Exception 
	 */
	
	public LabResultDetail callGetLabResultPrototype() throws Exception{
		String accId = "demo";  
		String siteId = "demo";
		String partName="Clinakos";
		String name="demo";
		String password="demo";
		String licPrescId="DEMOLP1";
		String locId="LOC11";
		
		List<LabResultDetail> labResuDetailList=new ArrayList<LabResultDetail>();
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		WebServiceClientImpl<GetLabResultsPrototype> wsClientForLabResultProptotype=new WebServiceClientImpl<GetLabResultsPrototype>();
		GetLabResultsPrototype labResultsPrototype=WS_CLIENT_FACTORY.createGetLabResultsPrototype();
		labResultsPrototype.setAccountRequest(accountRequest);
		labResultsPrototype.setCredentials(credentials);
		labResultsPrototype.setIncludeHL7Result("Y");
		labResultsPrototype.setIncludeHtmlResult("Y");
		labResultsPrototype.setLicensedPrescriberId(licPrescId);
		labResultsPrototype.setLocationId(locId);
		labResultsPrototype.setReportDateCCYYMMDD("20140221");
		
		GetLabResultsPrototypeResponse labResultsPrototypeResponse=(GetLabResultsPrototypeResponse) wsClientForLabResultProptotype.sendRequestAndReceiveResponse(labResultsPrototype,labServicesDetail );
		if(labResultsPrototypeResponse.getGetLabResultsPrototypeResult().getResult().getStatus().value().equals("OK")){
			if(!(labResultsPrototypeResponse.getGetLabResultsPrototypeResult().getLabResultDetailArray()==null)){
			labResuDetailList=labResultsPrototypeResponse.getGetLabResultsPrototypeResult().getLabResultDetailArray().getLabResultDetail();
			}
		}
		else if (!(labResultsPrototypeResponse.getGetLabResultsPrototypeResult().getResult().getStatus().value().equals("OK"))) {
			    getAuditInfo().setComment(labResultsPrototypeResponse.getGetLabResultsPrototypeResult().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("GetLabResultPrototype WSDL");
				getAuditInfo().setCreateTime(new Date());
				userService.saveWSFailStatus(auditInfo);
		}
		
		
		LabResultDetail labResult=new LabResultDetail();
		for(LabResultDetail labResultDetail:labResuDetailList)
		{
			
			labResult.setHL7Message(labResultDetail.getHL7Message());
			labResult.setHtmlMessage(labResultDetail.getHtmlMessage());
			System.out.println("HL7 Message In for loop "+labResult.getHL7Message());
		}
		
	System.out.println("HL7 Message out side of for loop"+labResult.getHL7Message());
		return labResult;
		
		
	}
	
	/**
	 * Call Get Lab Result Soap File
	 * @param providerLocation 
	 * @param reportDate 
	 * @throws Exception 
	 * 
	 */
	public List<LabResultDetail> callGetLabResult(ProviderLocation providerLocation, String reportDate) throws Exception{
		/*String accId = providerLocation.getAccountId();  
		String siteId = providerLocation.getSiteId();*/
		String accId="demo";
		String siteId="demo";
		String partName=getProperties().getProperty("partnerName");
		String name=getProperties().getProperty("name");
		String password=getProperties().getProperty("password");
		String licPrescId="DEMOLP1";
		String locId="LOC11";
		List<LabResultDetail> labResultDetailList=new ArrayList<LabResultDetail>();
		AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
		accountRequest.setAccountId(accId);
		accountRequest.setSiteId(siteId);
		
		Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
		credentials.setName(name);
		credentials.setPartnerName(partName);
		credentials.setPassword(password);
		
		WebServiceClientImpl<GetLabResults> wsClientForGetLabResult=new WebServiceClientImpl<GetLabResults>();
		
		GetLabResults labResults=WS_CLIENT_FACTORY.createGetLabResults();
		labResults.setAccountRequest(accountRequest);
		labResults.setCredentials(credentials);
		labResults.setIncludeHL7Result("Y");
		labResults.setIncludeHtmlResult("Y");
		labResults.setLicensedPrescriberId(licPrescId);
		labResults.setLocationId(locId);
		labResults.setReportDateCCYYMMDD(reportDate);
		
		GetLabResultsResponse labResultResponse=(GetLabResultsResponse) wsClientForGetLabResult.sendRequestAndReceiveResponse(labResults, labServicesDetail);
		if(labResultResponse.getGetLabResultsResult().getResult().getStatus().value().equals("OK")){
			if(!(labResultResponse.getGetLabResultsResult().getLabResultDetailArray()==null)){
				 labResultDetailList=labResultResponse.getGetLabResultsResult().getLabResultDetailArray().getLabResultDetail();
				for(LabResultDetail labResult:labResultDetailList){
					System.out.println("labResult.getHL7Message()"+labResult.getHL7Message() +" labResult.getHtmlMessage()"+labResult.getHtmlMessage());
				}
				
			}
		}
		else if (!(labResultResponse.getGetLabResultsResult().getResult().getStatus().value().equals("OK"))) {
			    getAuditInfo().setComment(labResultResponse.getGetLabResultsResult().getResult().getMessage());
				getAuditInfo().setUserId(new ContextUtil().getLoginId());
				getAuditInfo().setMachineName("GetLabResult WSDL");
				getAuditInfo().setCreateTime(new Date());
				labResultDetailList=new ArrayList<LabResultDetail>();
				userService.saveWSFailStatus(auditInfo);
		}
		
		return labResultDetailList;
		
		
	}
	
	/**
	 * Decode HL7 Message
	 * @param providerLocation 
	 * @throws Exception 
	 */
	public List<ORUPatientLabResult> decodeHL7Message(ProviderLocation providerLocation,String reportDate) throws Exception{
		List<LabResultDetail> laDetails=callGetLabResult(providerLocation,reportDate);
	/*	List<ORUPatientLabResult> oruPatientLabDetailList=new ArrayList<ORUPatientLabResult>();*/
		String hl7Message="";
		String htmlMessage="";
		List<ORUPatientLabResult> patientLabResultDecodedHL7List=new ArrayList<ORUPatientLabResult>();
		for(LabResultDetail labResultDetail:laDetails){
			ORUPatientLabResult patientLabResult=new ORUPatientLabResult();
			hl7Message=labResultDetail.getHL7Message();
			htmlMessage=labResultDetail.getHtmlMessage();
			System.out.println("Before decoding hl7Message"+hl7Message);
			/*hl7Message="TVNIfF5+XCZ8UVVFU1R8V0RMXl5MLUNMfEVNUnw4MDgwODEzNXwyMDEzMDYwNTE1MDkyMXx8T1JVXlIwMXwzMDA1OTUyMDAwfFB8Mi41LjF8fHxTVQ1QSUR8MXwyMDE1fDIwMTV8MjUyMTh8VGVzdF5MYWJwYXRpZW50fHwxOTM0MDMxMnxNfHx8MTIzIFJFU0lERU5DRSBMQU5FXl5TQVJBU09UQV5GTF4zNDIzMnx8NTU1MTIzNDU2N15QUk5eXl5eNTU1XjEyMzQ1Njd8fHx8fDAwNjc5OTZ8MzQ1ODg5OTk5DU9SQ3xSRXx8fHx8fHx8MjAxMzA2MDUxNTA5MjF8fHw0NTczMjExMjMwXk1heWZpZWxkXldpbGxpYW1eSl5eXl5eXl5eXk5QSX5BQkMxMjNeTWF5ZmllbGReV2lsbGlhbV5KXl5eXl5eXl5eVVBJTn40NTczMjExMjMwXk1heWZpZWxkXldpbGxpYW1eSl5eXl5eXl5eXlUNT0JSfDF8MjUyMTh8VE0xMzI4MjFUfDQ1N15GRVJSSVRJTl5XREx8fHwyMDEzMDYwMzA5MjgwMHx8fHxMfHx8MjAxMzA2MDUxNTA0MDB8fDQ1NzMyMTEyMzBeTWF5ZmllbGReV2lsbGlhbV5KXl5eXl5eXl5eTlBJfkFCQzEyM15NYXlmaWVsZF5XaWxsaWFtXkpeXl5eXl5eXl5VUElOfjQ1NzMyMTEyMzBeTWF5ZmllbGReV2lsbGlhbV5KXl5eXl5eXl5eVXx8fHx8fDIwMTMwNjA1MTUwNzAwfHx8Rg1PQlh8MXxOTXw1NTA1OTMwMF5GRVJSSVRJTl5XRExeMjI3Ni00XkZlcnJpdGluIFNlclBsLW1DbmNeTE58fDQ1fG5nL21MXm5nL21MfDEwLTEwNXxOfHx8Rnx8fDIwMTMwNjA1MTUwNzAwfFRQXlFVRVNUIERJQUdOT1NUSUNTLVRBTVBBfHx8fHx8fHxRVUVTVCBESUFHTk9TVElDUy1UQU1QQXw0MjI1IEUuIEZvd2xlciBBdmVeXlRhbXBhXkZMXjMzNjE3fF5MVUlTIEEgRElBWi1ST1NBUklPLE1EDQ==";*/
			patientLabResult.setPatFirstName(labResultDetail.getPatientFirstName());
			
			patientLabResult.setPatLastName(labResultDetail.getPatientLastName());
			patientLabResult.setPatMidName(labResultDetail.getPatientMiddleName());
			Base64 decoder=new Base64();
			byte[] decodedByte=(byte[]) decoder.decode(hl7Message.getBytes());
			String decodeHL7Message=new String(decodedByte);
			
			patientLabResult.setHl7Message(decodeHL7Message);
			/*patientLabResult.setHl7Message(hl7Message);*/
			
			byte[] decodedHTMLByte=(byte[]) decoder.decode(htmlMessage.getBytes());
			String decodeHtmlMessage=new String(decodedHTMLByte);
			patientLabResult.setHtmlMessage(decodeHtmlMessage);
			System.out.println("decodeHL7Message :::::::::::::::::"+decodeHL7Message);
			patientLabResultDecodedHL7List.add(patientLabResult);
		}
		
		
		return patientLabResultDecodedHL7List;
	}
	/**
	 * Decode HL7 Message
	 * @param providerLocation 
	 * @hl7Message
	 * @throws Exception 
	 */
	public String decodeHL7Message(ProviderLocation providerLocation,String hl7Message,String reportDate) throws Exception{
		
		Base64 decoder=new Base64();
		byte[] decodedByte=(byte[]) decoder.decode(hl7Message.getBytes());
		String decodeHL7Message=new String(decodedByte);
		System.out.println("decodeHL7Message :::::::::::::::::"+decodeHL7Message);
		return new String(decodedByte);
	}
	
	/**
	 * Decode HTML Message
	 * @param providerLocation 
	 * @throws Exception
	 */
	
	public String decodeHTMLMessage(ProviderLocation providerLocation,String reportDate) throws Exception{
		System.out.println("decode html msg ");
		List<LabResultDetail> laDetailsResultDetailList=callGetLabResult(providerLocation,reportDate);
		String htmlMessage="";
		for(LabResultDetail labDetail:laDetailsResultDetailList){
			htmlMessage=labDetail.getHtmlMessage();
		}
		
		Base64 decoder=new Base64();
		byte[] decodedByte=(byte[]) decoder.decode(htmlMessage.getBytes());
		System.out.println("decoded Byte "+new String(decodedByte)+"\n");
		return new String(decodedByte);
	}
	/**
	 * Decode HTML Message
	 * @param providerLocation 
	 * @param htmlMessage
	 * @throws Exception
	 */
	
	public String decodeHTMLMessage(ProviderLocation providerLocation,String htmlMessage,String reportDate) throws Exception{
		System.out.println("decode html msg ");
		
		
		Base64 decoder=new Base64();
		byte[] decodedByte=(byte[]) decoder.decode(htmlMessage.getBytes());
		System.out.println("decoded Byte "+new String(decodedByte)+"\n");
		return new String(decodedByte);
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
	 * Convert HL7 Message Format to Xml Format
	 * @throws Exception 
	 */
	public List<ORUPatientLabResult> convertHL7MessageToXMLFormat(ProviderLocation providerLocation,String reportDate) throws Exception{
		
	  List<ORUPatientLabResult> patientLabResultHL7List=decodeHL7Message(providerLocation,reportDate);
	  List<ORUPatientLabResult> patientLabResultHL7XmlList=new ArrayList<ORUPatientLabResult>();
	  
	   String hl7InXml="";
	   for(ORUPatientLabResult patientLabResult: patientLabResultHL7List){
		   if(!(patientLabResult.getHl7Message()==null)) {
			   ORUPatientLabResult patientLabResultHL7XML=new ORUPatientLabResult();
			   PipeParser pipeParser=new PipeParser();
			   Message message=pipeParser.parse(patientLabResult.getHl7Message());
			   XMLParser xmlParser=new DefaultXMLParser();
			   hl7InXml=xmlParser.encode(message);
			  patientLabResultHL7XML.setHl7Message(hl7InXml);
			  patientLabResultHL7XML.setPatientId(patientLabResult.getPatientId());
			  patientLabResultHL7XML.setPatFirstName(patientLabResult.getPatFirstName());
			  patientLabResultHL7XML.setPatLastName(patientLabResult.getPatLastName());
			  patientLabResultHL7XML.setHtmlMessage(patientLabResult.getHtmlMessage());
			  
			  patientLabResultHL7XmlList.add(patientLabResultHL7XML);
			   System.out.println("HL7 message in xml format "+hl7InXml+"Size Of patientLabResultHL7XmlList List"+patientLabResultHL7XmlList.size());
	   }
	  
	  }
	  
	   return patientLabResultHL7XmlList;
	    
	}
	
	/**
	 * Get value from HL7 XML format 
	 * @throws Exception 
	 * 
	 */
	public List<ORUPatientLabResult> getValueFromHL7XmlFormat(ProviderLocation providerLocation,String reportDate) throws Exception{
		List<ORUPatientLabResult> patientLabResultHL7XmlList=convertHL7MessageToXMLFormat(providerLocation,reportDate);
		/*logger.info("hl7XmlData in lab detail ws bean "+patientLabResult.getHl7Message());*/
		List<ORUPatientLabResult> oruPatientLabResultList=new ArrayList<ORUPatientLabResult>();
		
		InputSource inputSource=null;
		
		// ORUPatientLabResult Object Creation 
		/*ORUPatientLabResult patientLabResult=new ORUPatientLabResult();*/
		for(ORUPatientLabResult patientLabResult:patientLabResultHL7XmlList){
			if( !(patientLabResult.getHl7Message()==null)){
				inputSource=new InputSource(new StringReader(patientLabResult.getHl7Message()));
				DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
				org.w3c.dom.Document document = documentBuilder.parse(inputSource);

				Element docElement = document.getDocumentElement();
				NodeList node = docElement.getChildNodes();
				
				// Object for ORUPatientLabResult
				
				
				String patientComment="";
				String procedureTypeComment="";
				
				if(node !=null && node.getLength() >0 ){
					for(int i=0;i<node.getLength();i++){
						if(node.item(i).getNodeType()== Node.ELEMENT_NODE ){
							Element e1=(Element) node.item(i);
							System.out.println("Element node name "+e1.getNodeName());
							
							
							//MSH Element Node Value
							if(e1.getNodeName().contains("MSH")){
								String msh1=e1.getElementsByTagName("MSH.1").item(0).getFirstChild().getNodeValue();
								String msh2=e1.getElementsByTagName("MSH.2").item(0).getFirstChild().getNodeValue();
								String msh15=e1.getElementsByTagName("MSH.15").item(0).getFirstChild().getNodeValue();
							}
							
							// Split MSH in child nodes 
							if(e1.getNodeName().contentEquals("MSH")){
						      NodeList mshNode=  e1.getChildNodes();
						      
						      for(int j=0;j<mshNode.getLength();j++){
						    	  if(mshNode.item(j).getNodeType()==Node.ELEMENT_NODE){
						    		  Element me1=(Element) mshNode.item(j);
						    		  System.out.println(me1.getNodeName());
						    		  
						    		  //MSH.3 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.3")){
						    			  String msh3Hd1=me1.getElementsByTagName("HD.1").item(0).getFirstChild().getNodeValue();
						    			  
						    		  }
						    		  
						    		  //MSH.4 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.4")){
						    			  String msh4Hd1=me1.getElementsByTagName("HD.1").item(0).getFirstChild().getNodeValue();
						    			  String msh4Hd3=me1.getElementsByTagName("HD.3").item(0).getFirstChild().getNodeValue();
						    			  
						    		  }
						    		  
						    		  //MSH.5 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.5")){
						    			  String msh5Hd1=me1.getElementsByTagName("HD.1").item(0).getFirstChild().getNodeValue();
						    			  
						    		  }
						    		  
						    		  //MSH.6 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.6")){
						    			  String msh6Hd1=me1.getElementsByTagName("HD.1").item(0).getFirstChild().getNodeValue();
						    			  
						    		  }
						    		  
						    		  //MSH.7 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.7")){
						    			  String msh7TS1=me1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    		  }
						    		  
						    		  //MSH.9 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.9")){
						    			  String msh9Msg1=me1.getElementsByTagName("MSG.1").item(0).getFirstChild().getNodeValue();
						    			  String msh9Msg2=me1.getElementsByTagName("MSG.2").item(0).getFirstChild().getNodeValue();
						    		  }
						    		  
						    		  //MSH.11 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.11")){
						    			  String msh11Pt1=me1.getElementsByTagName("PT.1").item(0).getFirstChild().getNodeValue();
						    		  }
						    		  
						    		  //MSH.12 Element Node Value 
						    		  if(me1.getNodeName().contains("MSH.12")){
						    			  String msh12Vid1=me1.getElementsByTagName("VID.1").item(0).getFirstChild().getNodeValue();
						    			  
						    		  }
						    		  
						    		  
						    		  
						    		  
						    	  }
						      }
							}
						     // Split ORU_R01.PATIENT_RESULT in child nodes 
						    if(e1.getNodeName().contentEquals("ORU_R01.PATIENT_RESULT")){
						    	NodeList prNode=e1.getChildNodes();
						    	String patientId="";
						    	int z=0;
						    	for(int k=0;k<prNode.getLength();k++){
						    		if(prNode.item(k).getNodeType()==Node.ELEMENT_NODE){
						    			Element ptReE1=(Element) prNode.item(k);
						    			System.out.println("PATIENT_RESULT "+ptReE1);
						    			
						    			// Split ORU_R01.PATIENT in child nodes 
						    			if(ptReE1.getNodeName().contentEquals("ORU_R01.PATIENT")){
						    				NodeList ptNode=ptReE1.getChildNodes();
						    				for(int l=0;l<ptNode.getLength();l++){
						    					if(ptNode.item(l).getNodeType()==Node.ELEMENT_NODE){
						    						Element ptE1=(Element) ptNode.item(l);
						    						System.out.println("patient Element"+ptE1);
						    						
						    						// PID Element value 
						    						if(ptE1.getNodeName().equals("PID")){
						    							
						    						String pid1=ptE1.getElementsByTagName("PID.1").item(0).getFirstChild().getNodeValue();
						    						
						    						
						    						if(!(ptE1.getElementsByTagName("PID.2").item(0)==null)){
						    							patientId=ptE1.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
						    							patientLabResult.setPatientId(patientId);
						    						}
						    						
						    						
						    						String pid8=ptE1.getElementsByTagName("PID.8").item(0).getFirstChild().getNodeValue();
						    						String pid19=ptE1.getElementsByTagName("PID.8").item(0).getFirstChild().getNodeValue();
						    						System.out
															.println(" pid value 1.1 "+pid1 +"pid 8 value "+pid8);
						    						}
						    						
						    						// NTE element value 
						    						if(ptE1.getNodeName().contains("NTE")){
						    							
						    							String pNte1=ptE1.getElementsByTagName("NTE.1").item(0).getFirstChild().getNodeValue();
						    							String pNte2=ptE1.getElementsByTagName("NTE.2").item(0).getFirstChild().getNodeValue();
						    							String pNte3="";
						    							if(!(ptE1.getElementsByTagName("NTE.3").item(0)==null)){
						    								pNte3=ptE1.getElementsByTagName("NTE.3").item(0).getFirstChild().getNodeValue();
							    							
						    							}
						    							patientComment+=pNte3;
						    							System.out.println("patComment  "+patientComment);
						    							patientLabResult.setComment(patientComment);
						    							
						    						}
						    						
						    						// Split PID in child nodes 
						    						if(ptE1.getNodeName().contentEquals("PID")){
						    							NodeList pidNode=ptE1.getChildNodes();
						    							for(int a=0;a<pidNode.getLength();a++){
						    								if(pidNode.item(a).getNodeType()==Node.ELEMENT_NODE){
						    									Element pidE1=(Element) pidNode.item(a);
							    								System.out.println("PID Element  "+pidE1);
							    								
							    								// PID.2  Element Value 
							    								if(pidE1.getNodeName().contains("PID.2")){
							    									if(!(pidE1.getElementsByTagName("CX.1").item(0)==null)){
							    										String pid2Cx1=pidE1.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
								    									System.out.println("pid2Cx1   "+pid2Cx1);
							    									}
							    									
																			
							    								}
							    								
							    								//PID.3  Element Value
							    								if(pidE1.getNodeName().contains("PID.3")){
							    									String pid3Cx1=pidE1.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
							    									
							    								}
							    								
							    								// PID.4 Element Value 
							    								if(pidE1.getNodeName().contains("PID.4")){
							    									String pid4Cx1=pidE1.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
							    									System.out.println(" pid4Cx1 "+pid4Cx1);
																			
							    								}
							    								
							    								// PID.5 Element Value 
							    								if(pidE1.getNodeName().contains("PID.5")){
							    									String pid5Xpn2=pidE1.getElementsByTagName("XPN.2").item(0).getFirstChild().getNodeValue();
							    									System.out.println("pid5Xpn2  :::::::"+pid5Xpn2);
																			
							    								}
							    								// PID.7  element Value 
							    								if(pidE1.getNodeName().contains("PID.7")){
							    								String pid7Ts=pidE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
							    								System.out.println("pid 7 TS value "+pid7Ts);
							    								logger.info("pid7Ts");
							    								}
																	
							    								// PID.11 Element Value 
							    								if(pidE1.getNodeName().contains("PID.11")){
							    									String pid11XadSad1=pidE1.getElementsByTagName("SAD.1").item(0).getFirstChild().getNodeValue();
							    									String pid11Xad3=pidE1.getElementsByTagName("XAD.3").item(0).getFirstChild().getNodeValue();
							    									String pid11Xad4=pidE1.getElementsByTagName("XAD.4").item(0).getFirstChild().getNodeValue();
							    									String pid11Xad5=pidE1.getElementsByTagName("XAD.4").item(0).getFirstChild().getNodeValue();
							    								}
							    								
							    								// PID.13 Element Value 
							    								if(pidE1.getNodeName().contains("PID.13")){
							    									String pid13Xtn1=pidE1.getElementsByTagName("XTN.1").item(0).getFirstChild().getNodeValue();
							    									String pid13Xtn2=pidE1.getElementsByTagName("XTN.2").item(0).getFirstChild().getNodeValue();
							    									String pid13Xtn6=pidE1.getElementsByTagName("XTN.6").item(0).getFirstChild().getNodeValue();
							    									String pid13Xtn7=pidE1.getElementsByTagName("XTN.7").item(0).getFirstChild().getNodeValue();
							    								}
							    								
							    								// PID.18 Element Value 
							    								if(pidE1.getNodeName().contains("PID.18")){
							    									String pid18Cx1=pidE1.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
							    								}
							    								
							    								//Split PID_PID.5 in child nodes 
							    								if(pidE1.getNodeName().contentEquals("PID.5")){
							    									NodeList pidPid5=pidE1.getChildNodes();
							    									for(int c=0;c<pidPid5.getLength();c++){
							    										if(pidPid5.item(c).getNodeType()==Node.ELEMENT_NODE){
							    											Element pidPid5E1=(Element) pidPid5.item(c);
							    											System.out.println(" PID.5 Child nodes "+pidPid5E1);
							    											
							    											//PID.5 XPN.1 Element Value
							    											if(pidPid5E1.getNodeName().contains("XPN.1")){
							    												String pid5Xpn1Fn1=pidPid5E1.getElementsByTagName("FN.1").item(0).getFirstChild().getNodeValue();
							    												System.out.println("pid5Xpn1Fn1 ::::::::"+pid5Xpn1Fn1);
																						
							    											}
																					
							    											
							    										}
							    									}
							    									
							    									
							    								}
							    								
						    								}
						    							}
						    						}
						    						
						    						// Split NTE in child nodes 
						    						if(ptE1.getNodeName().contentEquals("NTE")){
						    							NodeList ptNteNode=ptE1.getChildNodes();
						    							for(int b=0;b<ptNteNode.getLength();b++){
						    								if(ptNteNode.item(b).getNodeType()==Node.ELEMENT_NODE){
						    									Element ptNteE1=(Element) ptNteNode.item(b);
							    								System.out.println("Pt Nte child node "+ptNteE1);
						    								}
						    							}
						    						}
															
						    						
						    					}
						    				}
						    			}
						    			
						    			// Split ORU_R01.ORDER_OBSERVATION in child nodes 
						    			if(ptReE1.getNodeName().contentEquals("ORU_R01.ORDER_OBSERVATION")){
						    				NodeList orObNode=ptReE1.getChildNodes();
						    				ProcedureType orderObservationLab=new ProcedureType();
						    				for(int m=0;m<orObNode.getLength();m++){
						    					if(orObNode.item(m).getNodeType()==Node.ELEMENT_NODE){
						    						Element orObE1=(Element) orObNode.item(m);
						    						System.out.println("Order Observation "+orObE1);
						    						
						    						
						    						// ORC Element Node Value 
						    						if(orObE1.getNodeName().contains("ORC")){
						    							String orc1=orObE1.getElementsByTagName("ORC.1").item(0).getFirstChild().getNodeValue();
						    						}
						    						
						    						// OBR Element Node Value 
						    						if(orObE1.getNodeName().contains("OBR")){
						    							String obr1=orObE1.getElementsByTagName("OBR.1").item(0).getFirstChild().getNodeValue();
						    							String obr25=orObE1.getElementsByTagName("OBR.25").item(0).getFirstChild().getNodeValue();
						    						}
						    						
						    						// Split ORC in child nodes 
						    						if(orObE1.getNodeName().contentEquals("ORC")){
						    							NodeList orcNode=orObE1.getChildNodes();
						    							for(int n=0;n<orcNode.getLength();n++){
						    								if(orcNode.item(n).getNodeType()==Node.ELEMENT_NODE){
						    									Element orcE1=(Element) orcNode.item(n);
						    									System.out.println("Orc node name "+orcE1.getNodeName());
						    									
						    									//ORC.9 Element Value 
						    									if(orcE1.getNodeName().contains("ORC.9")){
						    										String orc9Ts1=orcE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    										
						    									}
						    									
						    									// ORC.12 Element Value 
						    									if(orcE1.getNodeName().contains("ORC.12")){
						    										ArrayList<String> orc12List=new ArrayList<String>();
						    										if(!(orcE1.getElementsByTagName("XCN.1").item(0).getFirstChild()==null)){
						    											String orc12Xcn1=orcE1.getElementsByTagName("XCN.1").item(0).getFirstChild().getNodeValue();
							    										orc12List.add(orc12Xcn1);
						    										}
						    										
						    										if(!(orcE1.getElementsByTagName("FN.1").item(0).getFirstChild()==null)){
						    											String orc12Xcn2Fn1=orcE1.getElementsByTagName("FN.1").item(0).getFirstChild().getNodeValue();
							    										orc12List.add(orc12Xcn2Fn1);
						    										}
						    										
						    										if(!(orcE1.getElementsByTagName("XCN.3").item(0).getFirstChild()==null)){
						    											String orc12Xcn3=orcE1.getElementsByTagName("XCN.3").item(0).getFirstChild().getNodeValue();
							    										orc12List.add(orc12Xcn3);
						    										}
						    										if(!(orcE1.getElementsByTagName("XCN.4").item(0)==null)){
						    											String orc12Xcn4=orcE1.getElementsByTagName("XCN.4").item(0).getFirstChild().getNodeValue();
							    										orc12List.add(orc12Xcn4);
						    										}
						    										if(!(orcE1.getElementsByTagName("XCN.13").item(0)==null)){
						    											String orc12Xcn13=orcE1.getElementsByTagName("XCN.13").item(0).getFirstChild().getNodeValue();
							    										orc12List.add(orc12Xcn13);	
						    										}
						    										
						    										System.out.println("Orc 12 List Value "+orc12List);
																			
						    									}
																		
						    								}
						    							}
						    						}
						    						
						    						// Split OBR in child nodes 
						    						if(orObE1.getNodeName().contentEquals("OBR")){
						    						NodeList obrNode=orObE1.getChildNodes();
						    						for(int o=0;o<obrNode.getLength();o++){
						    							if(obrNode.item(o).getNodeType()==Node.ELEMENT_NODE){
						    								Element obrE1=(Element) obrNode.item(o);
						    								System.out.println("OBR   Node "+obrE1.getNodeName());
						    								
						    								// OBR.2 Node Element Value 
						    								if(obrE1.getNodeName().equals("OBR.2")){
						    									String obr2Ei1=obrE1.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
						    									patientLabResult.setRequisitionNo(obr2Ei1);
						    								}
						    								
						    								//OBR.3 Node Element Value 
						    								if(obrE1.getNodeName().contains("OBR.3")){
						    									String obr3Ei1=obrE1.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
						    									patientLabResult.setAccessionNo(obr3Ei1);
						    									
						    								}
						    								
						    								//OBR.4 Node Element Value 
						    								if(obrE1.getNodeName().contains("OBR.4")){
						    									if(!(obrE1.getElementsByTagName("CE.1").item(0)==null)){
						    										String obr4Ce1=obrE1.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
						    									}
						    									if(!(obrE1.getElementsByTagName("CE.2").item(0)==null)){
						    										String obr4Ce2=obrE1.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
							    									
							    									//Set Value of Lab TestName 
							    									orderObservationLab.setLabType(obr4Ce2);
						    									}
						    									
						    									if(!(obrE1.getElementsByTagName("CE.3").item(0)==null)){
						    										String obr4Ce3=obrE1.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
						    									}
						    									
						    									
						    								}
						    								
						    								//OBR.7 Node Element Value 
						    								if(obrE1.getNodeName().contains("OBR.7")){
						    									String obr7Ts1=obrE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    									DateFormat format=new SimpleDateFormat("yyyyMMddhhmmss");
						    									Date collectionDateTime=format.parse(obr7Ts1);
						    									patientLabResult.setCollectionDateNTime(collectionDateTime);
						    								}
						    								
						    								//OBR.14 Node Element Value 
						    								if(obrE1.getNodeName().contains("OBR.14")){
						    									String obr14Ts1=obrE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    								}
						    								
						    								//OBR.16 Node Element Value 
						    								if(obrE1.getNodeName().contains("OBR.16")){
						    									ArrayList<String> obr16List=new ArrayList<String>();
						    									String obr16Xcn1=obrE1.getElementsByTagName("XCN.1").item(0).getFirstChild().getNodeValue();
						    									obr16List.add(obr16Xcn1);
						    									String obr16Xcn2Fn1=obrE1.getElementsByTagName("FN.1").item(0).getFirstChild().getNodeValue();
						    									obr16List.add(obr16Xcn2Fn1);
						    									String obr16Xcn3=obrE1.getElementsByTagName("XCN.3").item(0).getFirstChild().getNodeValue();
						    									obr16List.add(obr16Xcn3);
						    									if(!(obrE1.getElementsByTagName("XCN.4").item(0)==null)){
						    										String obr16Xcn4=obrE1.getElementsByTagName("XCN.4").item(0).getFirstChild().getNodeValue();
							    									obr16List.add(obr16Xcn4);
						    									}
						    									if(obrE1.getElementsByTagName("XCN.13").item(0)==null){
						    										String obr16Xcn13=obrE1.getElementsByTagName("XCN.13").item(0).getFirstChild().getNodeValue();
							    									obr16List.add(obr16Xcn13);
							    									System.out.println("Obr 16 List"+obr16List);
						    									}
						    									
																		
						    								}
						    								
						    								//OBR.22 Node Element Value 
						    								if(obrE1.getNodeName().equals("OBR.22")){
						    									String obr22Ts1=obrE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    									SimpleDateFormat formateDate=new SimpleDateFormat("yyyyMMddhhmmss");
						    									Date reportDateTime=formateDate.parse(obr22Ts1);
						    									patientLabResult.setReportDateNTime(reportDateTime);
						    								}
						    								
						    							}
						    						 }
						    					  }
						    						
						    						// Split ORU_R01.OBSERVATION in child nodes 
						    						if(orObE1.getNodeName().contentEquals("ORU_R01.OBSERVATION")){
						    							/*ProcedureResultHistory observation=new ProcedureResultHistory();*/
						    							
						    							EmdeonLabResult observation=new EmdeonLabResult();
						    							/*int z=0;*/
						    							
						    							NodeList obsrNode=orObE1.getChildNodes();
						    							for(int p=0;p<obsrNode.getLength();p++){
						    								if(obsrNode.item(p).getNodeType()==Node.ELEMENT_NODE){
						    									Element obsrE1=(Element) obsrNode.item(p);
						    									System.out.println("ORU_R01.OBSERVATION  Node "+obsrE1.getNodeName());
						    									
						    									//OBX Element Node Value 
						    									if(obsrE1.getNodeName().contains("OBX")){
						    										String obx1=obsrE1.getElementsByTagName("OBX.1").item(0).getFirstChild().getNodeValue();
						    										String obx2=obsrE1.getElementsByTagName("OBX.2").item(0).getFirstChild().getNodeValue();
						    										String obx5=obsrE1.getElementsByTagName("OBX.5").item(0).getFirstChild().getNodeValue();
						    										
						    										//Set Value of In Range in Procedure Type 
						    										observation.setLabResultValue(obx5);
						    										
						    										//observation.setResult(Double.valueOf(obx5));
						    										
						    										System.out.println("In Range Procedure Type "+obx5 + "    ");
																			
						    										if(!(obsrE1.getElementsByTagName("OBX.7").item(0)==null)){
						    											String obx7=obsrE1.getElementsByTagName("OBX.7").item(0).getFirstChild().getNodeValue();
						    											
						    											//Set Value of Reference Range in Procedure Type Class 
						    											
						    											 observation.setRefrenceRange(obx7);
						    											
						    											
						    											System.out.println("Reference Range "+obx7);
																				
						    										}
						    										if(!(obsrE1.getElementsByTagName("OBX.8").item(0)==null)){
						    											String obx8=obsrE1.getElementsByTagName("OBX.8").item(0).getFirstChild().getNodeValue();
						    											observation.setLabResultStatus(obx8);
						    										}
						    										if(!(obsrE1.getElementsByTagName("OBX.11").item(0)==null)){
						    											String obx11=obsrE1.getElementsByTagName("OBX.11").item(0).getFirstChild().getNodeValue();
						    										}
						    										
						    									}
						    									
						    									//NTE Element Node Value
						    									if(obsrE1.getNodeName().contains("NTE")){
						    										ArrayList<String> obsrNteList=new ArrayList<String>();
						    										
						    										String obsrNteNte1=obsrE1.getElementsByTagName("NTE.1").item(0).getFirstChild().getNodeValue();
						    										String obsrNteNte2=obsrE1.getElementsByTagName("NTE.2").item(0).getFirstChild().getNodeValue();
						    										String obsrNteNte3="";
						    										if(!(obsrE1.getElementsByTagName("NTE.3").item(0)==null)){
						    											obsrNteNte3=obsrE1.getElementsByTagName("NTE.3").item(0).getFirstChild().getNodeValue();
						    											
						    										}
						    										obsrNteList.add(obsrNteNte1);
						    										obsrNteList.add(obsrNteNte2);
						    										obsrNteList.add(obsrNteNte3);
						    										procedureTypeComment+=obsrNteNte3;
						    										
						    										//Set Comment Value of observation Comment(procedure type comment)
						    										
						    										observation.setComments(procedureTypeComment);
						    									
						    										System.out.println("OBSR NTE  " +obsrNteList +" procedureTypeComment "+procedureTypeComment);
						    										System.out.println();
																			
																			
						    										
						    										
						    									}
						    									
						    									// Split OBX in child nodes 
						    									if(obsrE1.getNodeName().contentEquals("OBX")){
						    										NodeList obxNode=obsrE1.getChildNodes();
						    											for(int q=0;q<obxNode.getLength();q++){
						    												if(obxNode.item(q).getNodeType()==Node.ELEMENT_NODE){
						    													Element obxE1=(Element) obxNode.item(q);
						    													System.out.println("OBX Child Node  "+obxE1.getNodeName());
						    													
						    													//OBX.3 Element Node value 
						    													if(obxE1.getNodeName().contains("OBX.3")){
						    														String obx3Ce1=obxE1.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
						    														//Set labResult Code 
						    														observation.setLabResultCode(obx3Ce1);
						    														String obx3Ce2=obxE1.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
						    														
						    														//Set Value of ProcedureType Name in Patient lab Result Class 
						    														
						    														observation.setLabName(obx3Ce2);
						    														observation.setPatientId(patientId);
						    														String obx3Ce3=obxE1.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
						    														if(!(obxE1.getElementsByTagName("CE.4").item(0)==null)){
						    															String obx3Ce4=obxE1.getElementsByTagName("CE.4").item(0).getFirstChild().getNodeValue();
						    															// Set Loinc Code 
						    															observation.setLoincCode(obx3Ce4);
						    														}
						    														
						    														if(!(obxE1.getElementsByTagName("CE.5").item(0)==null)){
						    															String obx3Ce5=obxE1.getElementsByTagName("CE.5").item(0).getFirstChild().getNodeValue();
						    														}
						    														if(!(obxE1.getElementsByTagName("CE.6").item(0)==null)){
						    															String obx3Ce6=obxE1.getElementsByTagName("CE.6").item(0).getFirstChild().getNodeValue();
						    														}
						    														
						    													}
						    													
						    													//OBX.6 Element Node value 
						    													if(obxE1.getNodeName().contains("OBX.6")){
						    														String obx6Ce1=obxE1.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
						    														
						    														//Set Value of Range in ProcedureType Class 
						    														observation.setLabUnit(obx6Ce1);
						    														String obx6Ce2=obxE1.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
						    													}
						    													
						    													// OBX.14 Element Node Value 
						    													if(obxE1.getNodeName().contains("OBX.14")){
						    														String obx14Ts1=obxE1.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
						    													}
						    													
						    													//OBX.15 Element Node Value 
						    													if(obxE1.getNodeName().contains("OBX.15")){
						    														String obx15Ce1=obxE1.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
						    														String obx15Ce2=obxE1.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
						    													}
						    													
						    													//OBX.23 Element Node Value 
						    													if(obxE1.getNodeName().contains("OBX.23")){
						    														String obx23Xon1=obxE1.getElementsByTagName("XON.1").item(0).getFirstChild().getNodeValue();
						    													}
						    													
						    													//OBX.24 Element Node Value 
						    													if(obxE1.getNodeName().contains("OBX.24")){
						    														String obx24Xad1Sad1=obxE1.getElementsByTagName("SAD.1").item(0).getFirstChild().getNodeValue();
						    														String obx24Xad3=obxE1.getElementsByTagName("XAD.3").item(0).getFirstChild().getNodeValue();
						    														String obx24Xad4=obxE1.getElementsByTagName("XAD.4").item(0).getFirstChild().getNodeValue();
						    														String obx24Xad5=obxE1.getElementsByTagName("XAD.5").item(0).getFirstChild().getNodeValue();
						    														System.out.println("OBX.24  obx24Xad1Sad1"+obx24Xad1Sad1  +" obx24Xad5  "+obx24Xad5);
						    														
						    													}
						    													// OBX.25 Element Node value 
						    													if(obxE1.getNodeName().contains("OBX.25")){
						    														String obx25Xcn2Fn1=obxE1.getElementsByTagName("FN.1").item(0).getFirstChild().getNodeValue();
						    														System.out.println(" OBX.25 obx25Xcn2Fn1 "+ obx25Xcn2Fn1);
																							
						    														
						    													}
																						
						    												}
						    											}
						    									 }
						    									
						    									// Split NTE in child nodes 
						    									if(obsrE1.getNodeName().contentEquals("NTE")){
						    										NodeList nteNode=obsrE1.getChildNodes();
						    										for(int r=0;r<nteNode.getLength();r++){
						    											if(nteNode.item(r).getNodeType()==Node.ELEMENT_NODE){
						    												Element nteE1=(Element) nteNode.item(r);
						    												System.out.println("NTE Child node "+nteE1.getNodeName());
																					
						    											}
						    										}
						    									}
						    									
						    									
						    									
																		
																		
						    								}
						    							}
						    							
						    							/*patientLabResult.getOrderObservationList().add(orderObservationLab);*/
						    							
						    							
						    							
						    							/*patientLabResult.getOrderObservationList().get(patientLabResult.getOrderObservationList().indexOf(orderObservationLab)).getObservationLabList().add(observation);*/ // Commented By Anjani
						    							SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
						    							Date requestReportDate=dateFormat.parse(reportDate);
						    							
						    							patientLabResult.setRequestReportDate(requestReportDate);
						    							observation.setPatientId(patientId);
						    							patientLabResult.getEmdeonLabResultDetailList().add(observation);	
						    							
				    									
																
						    							
						    						}
						    						
															
						    					}
						    				}
						    				
				    							 
				    							
						    				
						    			/*	patientLabResult.getOrderObservationList().add(orderObservationLab);*/
						    			    
						    				      
						    					 /*  patientLabResult.getOrderObservationList().get(patientLabResult.getOrderObservationList().lastIndexOf(orderObservationLab)).setObservationLabList(patientLabResult.getOrderObservation().getObservationLabList());*/
						    				
						    				
						    				
						    					
								    			
								    		
						    				
						    				
						    				
						    				
						    				
						    				// For testing proc result history 
						    				for(ProcedureType procResult:patientLabResult.getOrderObservationList()){
						    					System.out.println("Proce Lab Name "+procResult.getLabType()+"::::::"+procResult.getLoincCode()+"::::::::"+procResult.getProcedureDescription());
						    				}
						    				System.out.println("HTML Message :::::::::::::::::::"+patientLabResult.getHtmlMessage());
						    				patientLabResult.setHtmlMessage(patientLabResult.getHtmlMessage());
						    				oruPatientLabResultList.add(patientLabResult);
						    				
						    				
						    				
						    			}
						    		}
						    		
						    		
						    		
						    		
							    	
						    	}
						    	
						    }
						    
						    
						    
								
							}
						}
					}
				}
			
			
		}
		
		System.out.println("oruPatientLabResultList.size()"+oruPatientLabResultList.size());
		
		    return oruPatientLabResultList;
		}

	
	
	/**
	 * Get all decode data of lab Result detail and send to it for save 
	 * @param providerLocation 
	 * @param reportDate 
	 * @throws Exception 
	 */
	public List<LabResultDetailClinakos> getAllLabResultDetailData(ProviderLocation providerLocation, String reportDate) throws Exception{
		List<LabResultDetail> labResultList=callGetLabResult(providerLocation,reportDate);
		System.out.println("labResultList.size() in nc lab bean "+labResultList.size());
		List<LabResultDetailClinakos> labResultDetailClinakosList=new ArrayList<LabResultDetailClinakos>();
		for(LabResultDetail labResultDetail:labResultList){
			LabResultDetailClinakos resultDetailClinakos=new LabResultDetailClinakos();
			resultDetailClinakos.setDoctorFirstName(labResultDetail.getDoctorFirstName());
			resultDetailClinakos.setDoctorLastName(labResultDetail.getDoctorLastName());
			String hl7Message=decodeHL7Message(providerLocation,labResultDetail.getHL7Message(),reportDate);
			resultDetailClinakos.setHl7Message(hl7Message);
			String htmlMessage=decodeHTMLMessage(providerLocation,labResultDetail.getHtmlMessage(),reportDate);
			resultDetailClinakos.setHtmlMessage(htmlMessage);
			resultDetailClinakos.setPatientDOB(labResultDetail.getPatientDOB());
			resultDetailClinakos.setPatientFirstName(labResultDetail.getPatientFirstName());
			resultDetailClinakos.setPatientLastName(labResultDetail.getPatientLastName());
			resultDetailClinakos.setPatientMiddleName(labResultDetail.getPatientMiddleName());
			XMLGregorianCalendar xmlResultDate=labResultDetail.getResultDateTime();
			GregorianCalendar grCalResultDate=xmlResultDate.toGregorianCalendar();
			SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String formatedResultDate=format.format(grCalResultDate.getTime());
			Date resultDateTime=format.parse(formatedResultDate);
			
			resultDetailClinakos.setResultDateTime(resultDateTime);
			
			
			resultDetailClinakos.setResultGuid(labResultDetail.getResultGuid());
			labResultDetailClinakosList.add(resultDetailClinakos);
		}
		System.out.println("Lab Detail Clinakos List"+labResultDetailClinakosList.size());
		return labResultDetailClinakosList;
	}
	/**
	 * @return the properties
	 * @throws IOException 
	 */
	public Properties getProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
		}
		properties.load(NcLabDetailWSBean.class.getClassLoader()
				.getResourceAsStream(
						"/com/clinakos/properties/webservices.properties"));
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
	/**
	 * 
	 * 
	 */
	public String callLabOrdersAPI(ProviderLocation providerLocation,String strReportDate){
		logger.info("Lab Orders Data in LabDetailWsBean");
		
		String hl7Message="";
	
		try {
			
			
			
			
			String accId="demo";
			String siteId="demo";
			String partName=getProperties().getProperty("partnerName");
			String name=getProperties().getProperty("name");
			String password=getProperties().getProperty("password");
			String licPrescId="DEMOLP1";
			String locId="LOC11";
			List<LabResultDetail> labResultDetailList=new ArrayList<LabResultDetail>();
			AccountRequest accountRequest=WS_CLIENT_FACTORY.createAccountRequest();
			accountRequest.setAccountId(accId);
			accountRequest.setSiteId(siteId);
			
			Credentials credentials=WS_CLIENT_FACTORY.createCredentials();
			credentials.setName(name);
			credentials.setPartnerName(partName);
			credentials.setPassword(password);
			
			GetLabOrders labOrders =WS_CLIENT_FACTORY.createGetLabOrders();
			labOrders.setAccountRequest(accountRequest);
			labOrders.setCredentials(credentials);
			labOrders.setIncludeHL7Result("Y");
			labOrders.setIncludeHtmlResult("Y");
			labOrders.setLicensedPrescriberId(licPrescId);
			labOrders.setLocationId(locId);
			labOrders.setReportDateCCYYMMDD(strReportDate);
			
			
			WebServiceClientImpl<GetLabOrders> wsClientForGetLabOrder=new WebServiceClientImpl<GetLabOrders>();
			
			GetLabOrdersResponse labOrdersResponse=(GetLabOrdersResponse) wsClientForGetLabOrder.sendRequestAndReceiveResponse(labOrders, labServicesDetail);
			if(!(labOrdersResponse.getGetLabOrdersResult().getLabOrderDetailArray()==null)){
				 hl7Message=labOrdersResponse.getGetLabOrdersResult().getLabOrderDetailArray().getLabOrderDetail().get(0).getHL7Message();
			}
			logger.info("Lab Orders API Status  "+labOrdersResponse.getGetLabOrdersResult().getResult().getStatus());
			System.out.println("Lab Order Response HL7"+labOrdersResponse.getGetLabOrdersResult().getLabOrderDetailArray().getLabOrderDetail().get(0).getHL7Message());
			
			
		} catch (Exception e) {
			
		}
		return hl7Message;
			
		
	}
	
	/**
	 * Convert GetLabOrders API 
	 * String HL7 message to Base64 format 
	 * 
	 */
	public String decodeLabOrdersHl7MessageInBase64Format(ProviderLocation providerLocation,String strReportDate){
		
	/*	String hl7MessageInUtf8=callLabOrdersAPI(providerLocation, strReportDate);*/
		String hl7MessageInUtf8="MSH|^~"+"\\"+"&|H_DX|22133736|QUEST|WDL|201507151523|24577^COS_CERT_1^STANDARD|ORM^O01|3004529053|P|2.3"+
				"PID|1|831|831^^^^PAN^NEWCROP LLC TEST SITE||ABRAHAM^ABE^A||19300827|M|||101 MAIN ST^^LEBANON^IN^46052||7654827964^PRN^PH^^^765^4827964"+
				"IN1|1||DEFAULT^^^^^WDL|MEDICARE|P O BOX 1418^^LITTLE ROCK^AR^46052|||00000||||20150119|||OTHER|ABRAHAM^ABE^A|18|19300827|101 MAIN ST^^LEBANON^IN^46052|||1||||||||||||||110111110D|||||||M"+
				"IN2|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||7654827964^PRN^PH^^^765^4827964"+
				"IN1|2||DEFAULT^^^^^WDL|MEDICAID|P O BOX 8034^^LITTLE ROCK^PA^46052|||00000||||20150116|||OTHER|ABRAHAM^ABE^A|18|19300827|101 MAIN ST^^LEBANON^IN^46052|||2||||||||||||||0010010001|||||||M"+
				"IN2|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||7654827964^PRN^PH^^^765^4827964"+
				"GT1|1||ABRAHAM^ABE^A||101 MAIN ST^^LEBANON^IN^46052|7654827964^PRN^PH^^^765^4827964||19300827|M||18"+
				"ORC|NW|37396^H_DX|||||||201507151523|P_NEWCROP1||ABC123^MAYFIELD^WILLIAM^J^^^^^^^^^UPIN~4573211230^MAYFIELD^WILLIAM^J^^^^^^^^^NPI||||T|||||NEWCROP LLC TEST SITE|1800 BERING DRIVE^SUITE 600^HOUSTON^TX^77057^USA"+
				"OBR|1|37396||6399^CBC (INCLUDES DIFF/PLT)^WDL|||201507151622||||||||^^BLOOD|ABC123^MAYFIELD^WILLIAM^J^^^^^^^^^UPIN~4573211230^MAYFIELD^WILLIAM^J^^^^^^^^^NPI"+
				"DG1|1||365.11^PRIMARY OPEN-ANGLE GLAUCOMA^I9C";
		
		Base64 decoder=new Base64();
		byte[] decodedByte=(byte[]) decoder.decode(hl7MessageInUtf8.getBytes());
		String decodeHL7Message=new String(decodedByte);
	    logger.info("decodeHL7Message :::::::::::::::::"+decodeHL7Message);
		return decodeHL7Message;
	    
	}
	
	
	public String convertGetLabOrdersHl7MessageToXmlFormat(ProviderLocation providerLocation,String strReportDate){
		String hl7MessageInBase64=decodeLabOrdersHl7MessageInBase64Format(providerLocation,strReportDate);
		
		
		String hl7InXml="";
		try {
			if(StringUtils.isNotEmpty(hl7MessageInBase64)){
			HapiContext context=new DefaultHapiContext();
			
			context.setValidationContext(new NoValidation());
			 Message message ;
			 
			 Parser parser=context.getGenericParser();
			 
				 message=parser.parse(hl7MessageInBase64); 
			 
			 
			 /*PipeParser pipeParser=new PipeParser();
			 
			 String version= pipeParser.getVersion(hl7MessageInBase64);
			 logger.info("Version"+version+"ACK Id"+pipeParser.getAckID(hl7MessageInBase64)+"Default Encoding "+pipeParser.getDefaultEncoding());
			 System.out.println("Message Structure :':;"+pipeParser.getMessageStructure(hl7MessageInBase64));
			message = pipeParser.parse(hl7MessageInBase64);*/
			 
			
			 
			 XMLParser xmlParser=new DefaultXMLParser();
			 hl7InXml=xmlParser.encode(message);
			 
			 logger.info("hl7 in XML: "+ hl7InXml);
			 }
			
		
		  
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hl7InXml;
		
		  
		
	     
		   
	}
	
	/**
	 * Get All Data of GetLabOrders API using HL7 XML Format  
	 * @param providerLocation
	 * @param reportDate
	 * @return 
	 */
	public ArrayList<EmdeonLabOrders> getDataFromGetLabOrderHL7XmlResult(ProviderLocation providerLocation, String reportDate ){
		String hl7MessageInXmlFormat = convertGetLabOrdersHl7MessageToXmlFormat(providerLocation,reportDate);
		ArrayList<EmdeonLabOrders> emdeonLabOrdersList=new ArrayList<EmdeonLabOrders>();
		InputSource inputSource=null; 
		try {
			if(StringUtils.isNotEmpty(hl7MessageInXmlFormat)){
				inputSource=new InputSource(new StringReader(hl7MessageInXmlFormat));
				DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder;
				
					documentBuilder = documentBuilderFactory.newDocumentBuilder();
				
				org.w3c.dom.Document document = documentBuilder.parse(inputSource);
				String patientId="";
				Element docElement = document.getDocumentElement();
				NodeList labOrderNode = docElement.getChildNodes();
				if(labOrderNode !=null && labOrderNode.getLength() >0 ){
					
					for(int a=0;a<labOrderNode.getLength();a++){
						EmdeonLabOrders labOrders=new EmdeonLabOrders();
						if(labOrderNode.item(a).getNodeType() == Node.ELEMENT_NODE){
							Element labOrderElement=(Element) labOrderNode.item(a);
							logger.info("Element Node Name "+labOrderElement.getNodeName());
							
							
							if(labOrderElement.getNodeName().contains("MSH")){
								String fieldSperator=labOrderElement.getElementsByTagName("MSH.1").item(0).getFirstChild().getNodeValue();
								String encodingCharacter=labOrderElement.getElementsByTagName("MSH.2").item(0).getFirstChild().getNodeValue();
								String versionId=labOrderElement.getElementsByTagName("MSH.12").item(0).getFirstChild().getNodeValue();
								logger.info("fieldSperator"+fieldSperator+"versionId "+versionId+" encodingCharacter "+encodingCharacter);
								
							}
							
							// ORM_O01.PATIENT Patient Nodes 
							// Split Patient Data  Node in child nodes 
							if(labOrderElement.getNodeName().contentEquals("ORM_O01.PATIENT")){
								NodeList patientDataNode=labOrderElement.getChildNodes();
								for(int b=0;b<patientDataNode.getLength();b++){
									if(patientDataNode.item(b).getNodeType() == Node.ELEMENT_NODE){
										Element patientDataNodeElement=(Element) patientDataNode.item(b);
										logger.info("Patient Data Node "+patientDataNodeElement);
										
										
										
										
										//PID Patient Detail 
										// Split Patient detail Data in child Nodes 
										if(patientDataNodeElement.getNodeName().contentEquals("PID")){
											NodeList patientDetailDataNode=patientDataNodeElement.getChildNodes();
											for(int j=0;j<patientDetailDataNode.getLength();j++){
												if(patientDetailDataNode.item(j).getNodeType() == Node.ELEMENT_NODE){
													Element patientDetailDataNodeElement= (Element) patientDetailDataNode.item(j);
													logger.info("patientDetailDataNodeElement"+patientDetailDataNodeElement);
												    patientId=patientDataNodeElement.getElementsByTagName("PID.1").item(0).getFirstChild().getNodeValue();
													logger.info("Patient Id "+patientId);
													
													
												}
											}
											
										}
										
										
										
										
										
										//ORM_O01.INSURANCE Patient Insurance 
										//Split Insurance Data Node in child nodes 
										
										if(patientDataNodeElement.getNodeName().contentEquals("ORM_O01.INSURANCE")){
											NodeList patientInsuranceDataNode=patientDataNodeElement.getChildNodes();
											ArrayList<UserInsuranceDetails> userInsuranceDetailList=new ArrayList<UserInsuranceDetails>();
											for(int h=0;h<patientInsuranceDataNode.getLength();h++){
												if(patientInsuranceDataNode.item(h).getNodeType() == Node.ELEMENT_NODE){
													Element patientInsuranceDataNodeElement=(Element) patientInsuranceDataNode.item(h);
													logger.info("patientInsuranceDataNodeElement"+patientInsuranceDataNodeElement);
													UserInsuranceDetails userInsurace=new UserInsuranceDetails();
													if(patientInsuranceDataNodeElement.getNodeName().contentEquals("IN1.2")){
														String insurancePlanId=patientInsuranceDataNodeElement.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
														userInsurace.setInsuranceId(Integer.parseInt(insurancePlanId));
													}
													if(patientInsuranceDataNodeElement.getNodeName().contentEquals("IN1.4")){
														String insuranceCompanyName=patientInsuranceDataNodeElement.getElementsByTagName("XON.1").item(0).getFirstChild().getNodeValue();
														userInsurace.setCompanyName(insuranceCompanyName);
													}
													if(patientInsuranceDataNodeElement.getNodeName().contentEquals("IN1.5")){
														String insuranceCompanyStreetAddress=patientInsuranceDataNodeElement.getElementsByTagName("XON.1").item(0).getFirstChild().getNodeValue();
														String insuranceCompanyOtherAddress=patientInsuranceDataNodeElement.getElementsByTagName("XON.2").item(0).getFirstChild().getNodeValue();
														String insuranceCompanyCity=patientInsuranceDataNodeElement.getElementsByTagName("XON.3").item(0).getFirstChild().getNodeValue();
														String insuranceCompanyState=patientInsuranceDataNodeElement.getElementsByTagName("XON.4").item(0).getFirstChild().getNodeValue();
														String insuranceCompanyZip=patientInsuranceDataNodeElement.getElementsByTagName("XON.5").item(0).getFirstChild().getNodeValue();
														String insuranceCompanyCountry=patientInsuranceDataNodeElement.getElementsByTagName("XON.6").item(0).getFirstChild().getNodeValue();
														
														
													}
													
													
													if(patientInsuranceDataNodeElement.getNodeName().contentEquals("IN1.7")){
														String insuranceCompanyTelephoneNo=patientInsuranceDataNodeElement.getElementsByTagName("XTN.1").item(0).getFirstChild().getNodeValue();
														
													}
													
												}
												
											}
										}
										
										// GT1 Patient Guarantor   Detail
										// Split Patient Guarantor Detail in Child Nodes 
										if(patientDataNodeElement.getNodeName().contentEquals("GT1")){
											NodeList patientGuarantorDataNode=patientDataNodeElement.getChildNodes();
											ArrayList<PatientGuarantor> patientGurantorDetailList=new ArrayList<PatientGuarantor>();
											for(int i=0;i<patientGuarantorDataNode.getLength();i++){
												
												if(patientGuarantorDataNode.item(i).getNodeType() == Node.ELEMENT_NODE){
													Element patientGuarantorDataNodeElement= (Element) patientGuarantorDataNode.item(i);
													PatientGuarantor patGuarantor=new PatientGuarantor();
													logger.info("patientGuarantorDataNodeElement :::"+patientGuarantorDataNodeElement);
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.2")){
														String gurantorNumberId=patientGuarantorDataNodeElement.getElementsByTagName("CX.1").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorID(Integer.parseInt(gurantorNumberId));
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.3")){
														String gurantorFamilyName=patientGuarantorDataNodeElement.getElementsByTagName("XPN.1").item(0).getFirstChild().getNodeValue();
														String gurantorGivenName=patientGuarantorDataNodeElement.getElementsByTagName("XPN.2").item(0).getFirstChild().getNodeValue();
														String gurantorMiddleName=patientGuarantorDataNodeElement.getElementsByTagName("XPN.3").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorFirstName(gurantorGivenName);
														patGuarantor.setGuarantorMiddleName(gurantorMiddleName);
														patGuarantor.setGuarantorLastName(gurantorFamilyName);
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.5")){
														String guarantorStreetAddress=patientGuarantorDataNodeElement.getElementsByTagName("XAD.1").item(0).getFirstChild().getNodeValue();
														String guarantorOtherAddress=patientGuarantorDataNodeElement.getElementsByTagName("XAD.2").item(0).getFirstChild().getNodeValue();
												        String guarantorCity=patientGuarantorDataNodeElement.getElementsByTagName("XAD.3").item(0).getFirstChild().getNodeValue();
												        String guarantorState=patientGuarantorDataNodeElement.getElementsByTagName("XAD.4").item(0).getFirstChild().getNodeValue();
												        String guarantroCountry=patientGuarantorDataNodeElement.getElementsByTagName("XAD.6").item(0).getFirstChild().getNodeValue();
												        String guarantorZip=patientGuarantorDataNodeElement.getElementsByTagName("XAD.5").item(0).getFirstChild().getNodeValue();
												        
												        
												        patGuarantor.setGuarantorAddress1(guarantorStreetAddress);
												        patGuarantor.setGuarantorAddress2(guarantorOtherAddress);
												        patGuarantor.setGuarantorCity(guarantorCity);
												        patGuarantor.setGuarantorState(guarantorState);
												        patGuarantor.setGuarantorCountry(guarantroCountry);
												        patGuarantor.setGuarantorZip(guarantorZip);
													
													}
													
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.6")){
														
														String guarantorHomeTelphoneNo=patientGuarantorDataNodeElement.getElementsByTagName("XTN.1").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorHomeTelephone(guarantorHomeTelphoneNo);
														
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.7")){
														String gurantorWorkTelephone=patientGuarantorDataNodeElement.getElementsByTagName("XTN.1").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorWorkTelephone(gurantorWorkTelephone);
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.8")){
														String guarantorDOB=patientGuarantorDataNodeElement.getElementsByTagName("XTN.1").item(0).getFirstChild().getNodeValue();
														SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
														Date gurantorDateOfBirth=format.parse(guarantorDOB);
														patGuarantor.setGuarantorDOB(gurantorDateOfBirth);
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.9")){
														String guarantorSex=patientGuarantorDataNodeElement.getElementsByTagName("GT1.9").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorGender(guarantorSex);
													}
													if(patientGuarantorDataNodeElement.getNodeName().contentEquals("GT1.11")){
														String guarantorRelationship=patientGuarantorDataNodeElement.getElementsByTagName("GT1.11").item(0).getFirstChild().getNodeValue();
														patGuarantor.setGuarantorRelationship(guarantorRelationship);
													}
													patGuarantor.setUserID(Integer.parseInt(patientId));
													patientGurantorDetailList.add(patGuarantor);
													
												}
												
												
												
											}
											labOrders.setPatientGurantorList(patientGurantorDetailList);
										}
										
										
										
										
										
									}
																		
								}
								
							}
							
							// ORM_O01.ORDER Order Node 
							// Split Lab Order Data Nodes in child Nodes 
							if(labOrderElement.getNodeName().contentEquals("ORM_O01.ORDER")){
								NodeList orderDataNode=labOrderElement.getChildNodes();
								for(int c=0;c<orderDataNode.getLength();c++){
									if(orderDataNode.item(c).getNodeType() == Node.ELEMENT_NODE){
										Element orderNodeElement=(Element) orderDataNode.item(c);
										logger.info("orderNodeElement :::::"+orderNodeElement);
										
										//ORC Common Order Segment 
										// Split Common Order Segment in Child Nodes 
										if(orderNodeElement.getNodeName().contentEquals("ORC")){
											NodeList orderDataCommonOrderSegmentNode=orderNodeElement.getChildNodes();
											ArrayList<LabOrderCommonSegment> orderCommonSegmentList=new ArrayList<LabOrderCommonSegment>();
											for(int d=0;d<orderDataCommonOrderSegmentNode.getLength();d++){
												if(orderDataCommonOrderSegmentNode.item(d).getNodeType() == Node.ELEMENT_NODE){
													Element orderDataCommonOrderSegmentNodeElement=(Element) orderDataCommonOrderSegmentNode.item(d);
													LabOrderCommonSegment orderCommonSegment=new LabOrderCommonSegment();
													logger.info("orderDataCommonOrderSegmentNode"+orderDataCommonOrderSegmentNodeElement);
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.1")){
														String orderControlId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setOrderControlId(orderControlId);
													}
													
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.2")){
														
														String placerOrderNoIdentityIdentifier=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
														String placerOrderNamespaceId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.2").item(0).getFirstChild().getNodeValue();
														logger.info("placerOrderNoIdentityIdentifier"+placerOrderNoIdentityIdentifier+" "+placerOrderNamespaceId);
														orderCommonSegment.setPlacerOrderEntityIdentifier(placerOrderNoIdentityIdentifier);
														orderCommonSegment.setPlaceroOrderNamespaceId(placerOrderNamespaceId);
													}
													
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.3")){
														String fillerOrderNoEntityIdentifier=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
														String fillerOrderNoNameSpaceId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.2").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setFillerOrderEntityIdentifier(fillerOrderNoEntityIdentifier);
														orderCommonSegment.setFillerOrderNamespaceId(fillerOrderNoNameSpaceId);
														
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.4")){
														String placerGroupEntityIdentifier=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
														String placerGroupNameSpaceId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("EI.2").item(0).getFirstChild().getNodeValue();
														String placerGroupNo=placerGroupEntityIdentifier+" "+placerGroupNameSpaceId;
														orderCommonSegment.setPlacerGroupNumber(placerGroupNo);
														
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.5")){
														String orderStatus=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.5").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setOrderStatus(orderStatus);
														
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.6")){
														String responseFlag=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.6").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setResponseFlag(responseFlag);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.7")){
														String quantity=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.7.1.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setQuantity(quantity);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.8")){
														String parentPlacerOrderNumber=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.8.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setParentPlacerOrderNumber(parentPlacerOrderNumber);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.9")){
														String timeOfTansaction=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.9.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setDateTimeOfTransaction(timeOfTansaction);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.10")){
														String enteredPersonIdNo=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.10.1").item(0).getFirstChild().getNodeValue();
														String enteredPersonFamilyName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.10.2").item(0).getFirstChild().getNodeValue();
														String enteredPersonGivenName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.10.3").item(0).getFirstChild().getNodeValue();
														String enteredPersonMiddleName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.10.4").item(0).getFirstChild().getNodeValue();
														String enteredBy=enteredPersonIdNo+" "+enteredPersonFamilyName+" "+enteredPersonGivenName+" "+enteredPersonMiddleName;
														orderCommonSegment.setEnteredBy(enteredBy);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.11")){
														String verifiedByPersonId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.11.1").item(0).getFirstChild().getNodeValue();
														String verifiedByPersonFamilyNames=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.11.2").item(0).getFirstChild().getNodeValue();
														String verifiedByPersonGivenNames=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.11.3").item(0).getFirstChild().getNodeValue();
														String verifiedByPersonMiddleNames=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.11.4").item(0).getFirstChild().getNodeValue();
														String verifiedBy=verifiedByPersonId+" "+verifiedByPersonFamilyNames+" "+verifiedByPersonGivenNames+" "+verifiedByPersonMiddleNames;
														orderCommonSegment.setVerifiedBy(verifiedBy);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.12")){
														String orderingProviderPersonId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.12.1").item(0).getFirstChild().getNodeValue();
														String orderingProviderPersonFamilyName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.12.2").item(0).getFirstChild().getNodeValue();
														String orderingProviderPersonGivenName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.12.3").item(0).getFirstChild().getNodeValue();
														String orderingProviderPersonMiddleName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.12.4").item(0).getFirstChild().getNodeValue();
														String orderingProviderPerson=orderingProviderPersonId+" "+orderingProviderPersonFamilyName+" "+orderingProviderPersonGivenName+" "+orderingProviderPersonMiddleName;
														orderCommonSegment.setOrderingProvider(orderingProviderPerson);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.13")){
														String entererLocationPointOfCareId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.13.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setEntererLocation(entererLocationPointOfCareId);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.14")){
														String callBackPhoneNo=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.14.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setCallBackPhoneNumber(callBackPhoneNo);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.15")){
														String orderEffectiveDate=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.15.1").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setOrderEffectiveDate(orderEffectiveDate);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.17")){
														String enteringOrganizationIdentifierId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.17.1").item(0).getFirstChild().getNodeValue();
														String enteringOrganizationText=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.17.2").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setEnteringOrganization(enteringOrganizationIdentifierId+" "+enteringOrganizationText);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.18")){
														String enteringDeviceId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.18.1").item(0).getFirstChild().getNodeValue();
														String enteringDeviceText=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.18.2").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setEnteringDevice(enteringDeviceId+" "+enteringDeviceText);
													}
													if(orderDataCommonOrderSegmentNodeElement.getNodeName().contentEquals("ORC.19")){
														String actionByPersonId=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.19.1").item(0).getFirstChild().getNodeValue();
														String actionbyPersonFamilyName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.19.2").item(0).getFirstChild().getNodeValue();
														String actionbyPersonGivenName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.19.3").item(0).getFirstChild().getNodeValue();
														String actionbyPersonMiddleName=orderDataCommonOrderSegmentNodeElement.getElementsByTagName("ORC.19.4").item(0).getFirstChild().getNodeValue();
														orderCommonSegment.setActionBy(actionByPersonId+" "+actionbyPersonFamilyName+" "+actionbyPersonGivenName+" "+actionbyPersonMiddleName);
													}
													orderCommonSegment.setPatientId(patientId);
													orderCommonSegmentList.add(orderCommonSegment);
													
													labOrders.setLabOrderCommonSegmentList(orderCommonSegmentList);
													
													
												}
											}
											
										}
										
										// ORM_O01.ORDER_DETAIL Order Detail 
										// Split Order Detail Nodes in Child Nodes
										
										if(orderNodeElement.getNodeName().contentEquals("ORM_O01.ORDER_DETAIL")){
											NodeList orderDataOrderDetailNode=orderNodeElement.getChildNodes();
											for(int e=0;e<orderDataOrderDetailNode.getLength();e++){
												if(orderDataOrderDetailNode.item(e).getNodeType() == Node.ELEMENT_NODE){
													Element orderDataOrderDetailNodeElement=(Element) orderDataOrderDetailNode.item(e);
													logger.info("orderDataOrderDetailNodeElement"+orderDataOrderDetailNodeElement);
													
													//OBR Observation Request Segment 
													// Split Observation Request Segment in Child Nodes 
													if(orderDataOrderDetailNodeElement.getNodeName().contentEquals("OBR")){
														NodeList orderDataOrderDetailOrderObservationRequestNode=orderDataOrderDetailNodeElement.getChildNodes();
														ArrayList<LabOrderObservationRequestDetail> orderObservationRequestDetailList=new ArrayList<LabOrderObservationRequestDetail>();
														for(int f=0;f<orderDataOrderDetailOrderObservationRequestNode.getLength();f++){
															if(orderDataOrderDetailOrderObservationRequestNode.item(f).getNodeType() == Node.ELEMENT_NODE){
																LabOrderObservationRequestDetail orderObservationRequestDetail=new LabOrderObservationRequestDetail();
																Element  orderDataOrderDetailOrderObservationNodeElement=(Element) orderDataOrderDetailOrderObservationRequestNode.item(f);
																logger.info("orderDataOrderDetailOrderObservationNodeElement"+orderDataOrderDetailOrderObservationNodeElement);
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.1")){
																	String observationRequestId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("OBR.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setObservationRequestId(observationRequestId);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.2")){
																	String placerOrderEntityIdentifier=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
																	String placerOrderNameSpaceId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("EI.2").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setPlacerOrderNumber(placerOrderEntityIdentifier+" "+placerOrderNameSpaceId);
																	
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.3")){
																	String fillerOrderEntityIdentifier=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("EI.1").item(0).getFirstChild().getNodeValue();
																	String fillerOrderNamespaceId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("EI.2").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setFillerOrderNumber(fillerOrderEntityIdentifier+" "+fillerOrderNamespaceId);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.4")){
																	String universalServiceIdentifierId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String universalServiceText=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String universalServiceCodingSystem=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setUniversalServiceIdentifierId(universalServiceIdentifierId);
																	orderObservationRequestDetail.setUniversalServiceText(universalServiceCodingSystem);
																	orderObservationRequestDetail.setUniversalServiceCodingSystem(universalServiceCodingSystem);
																	
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.5")){
																	String priority=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("OBR.5.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setPriority(priority);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.6")){
																	String requestedDateTime=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setRequestedDateTime(requestedDateTime);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.7")){
																	String observationDateTime=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setObservationDateTime(observationDateTime);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.8")){
																	String observationEndDateTime=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setObservationEndDateTime(observationEndDateTime);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.9")){
																	String collectionVolumeQuantity=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CQ.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setCollectionVolumeQuantity(collectionVolumeQuantity);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.10")){
																	String collectionIdentifierPersonId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.1").item(0).getFirstChild().getNodeValue();
																	String collectionIdentifierPersonFamilyName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.2").item(0).getFirstChild().getNodeValue();
																	String collectionIdentifierPersonGivenName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.3").item(0).getFirstChild().getNodeValue();
																	String collectionIdentifierPersonMiddleName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.4").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setCollectionIdentifier(collectionIdentifierPersonId+" "+collectionIdentifierPersonFamilyName+" "+collectionIdentifierPersonGivenName+" "+collectionIdentifierPersonMiddleName);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.11")){
																	String specimenActionCode=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("OBR.11.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setSpeciemenActionCode(specimenActionCode);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.13")){
																	String releventClinicalInformation=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("OBR.13.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setReleventClinicalInformation(releventClinicalInformation);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.14")){
																	String specimenRecivedDateTime=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setSpeciemenRecivedDateTime(specimenRecivedDateTime);
																}
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.15")){
																	String specimenSourceCode=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CM_SPS.1").item(0).getFirstChild().getNodeValue();
																	String specimenSourceAddatives=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CM_SPS.2").item(0).getFirstChild().getNodeValue();
																	String specimenSourceText=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("CM_SPS.3").item(0).getFirstChild().getNodeValue();
																	
																	orderObservationRequestDetail.setSpeciemenSourceCode(specimenSourceCode);
																	orderObservationRequestDetail.setSpecimenSourceAdditives(specimenSourceAddatives);
																	orderObservationRequestDetail.setSpeciemenSourceText(specimenSourceText);
																	
																}
																
																if(orderDataOrderDetailOrderObservationNodeElement.getNodeName().contentEquals("OBR.16")){
																	String orderingProviderPersonId=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.1").item(0).getFirstChild().getNodeValue();
																	String orderingProviderPersonFamilyName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.2").item(0).getFirstChild().getNodeValue();
																	String orderingProviderPersonGivenyName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.3").item(0).getFirstChild().getNodeValue();
																	String orderingProviderPersonMiddleName=orderDataOrderDetailOrderObservationNodeElement.getElementsByTagName("XCN.4").item(0).getFirstChild().getNodeValue();
																	orderObservationRequestDetail.setOrderingProvider(orderingProviderPersonId+" "+orderingProviderPersonFamilyName+" "+orderingProviderPersonGivenyName+" "+orderingProviderPersonMiddleName);
																}
																
																orderObservationRequestDetailList.add(orderObservationRequestDetail);
																
															}
															
															labOrders.setLabOrderObservationRequestDetailList(orderObservationRequestDetailList);
															
															
															
														}
													}
													
													//OBX Observation Segment 
													// Split Observation Segment in child Nodes 
													if(orderDataOrderDetailNodeElement.getNodeName().contentEquals("OBX")){
														NodeList orderDataOrderDetailOrderObservationNode =orderDataOrderDetailNodeElement.getChildNodes();
														ArrayList<LabOrderObservationDetail> orderObservationDetailList=new ArrayList<LabOrderObservationDetail>();
														for(int p=0;p<orderDataOrderDetailOrderObservationNode.getLength();p++){
															if(orderDataOrderDetailOrderObservationNode.item(p).getNodeType() == Node.ELEMENT_NODE){
																LabOrderObservationDetail orderObservationDetail=new LabOrderObservationDetail();
																Element orderDataOrderDetailOrderObservationNodeElemenmt=(Element) orderDataOrderDetailOrderObservationNode.item(p);
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.1")){
																	String orderObservationId=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.1.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setOrderObservationId(orderObservationId);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.2")){
																	String observationValueType = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.2.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setObservationValueType(observationValueType);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.3")){
																	String observationIdentifierId = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String observationText= orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String observationCodingSystemName=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setObservationIdentifier(observationIdentifierId);
																	orderObservationDetail.setObservationText(observationText);
																	orderObservationDetail.setObservationCodingSystemName(observationCodingSystemName);
																	
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.4")){
																	String observationSubId=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.4.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setObservationSubId(observationSubId);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.5")){
																	String observationValues = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.5.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setObservationValues(observationValues);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.6")){
																	String unitId=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String unitText=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String unitCodingSystem=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setUnits(unitId+" "+unitText+" "+unitCodingSystem);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.7")){
																	String refrenceRange=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setReferanceRange(refrenceRange);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.8")){
																	String abnormalFlag=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.8.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setAbnormalFlag(abnormalFlag);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.9")){
																	String probablity=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.9.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setProbablity(probablity);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.10")){
																	String natureOfAbnormalTest=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.10.1").item(0).getFirstChild().getNodeValue();
																    orderObservationDetail.setNatureOfAbnormalTest(natureOfAbnormalTest);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.11")){
																	String observResultStatus=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.11.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setObservResultStatus(observResultStatus);
																	
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.13")){
																	String userDefinedAccessChecks=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.13.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setUserDefienedAccessChecks(userDefinedAccessChecks);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.12")){
																	String dateLastObsNormalValues= orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("OBX.12.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setDateLastObsNormalValues(dateLastObsNormalValues);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.15")){
																	String producersId = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String producersText = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String producersCodingSystem = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setProducersId(producersId);
																	orderObservationDetail.setProducersText(producersText);
																	orderObservationDetail.setProducersCodingSystem(producersCodingSystem);
																}
																
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.14")){
																	String dateTimeOfObservation = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("TS.1").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setDateTimeOfObservation(dateTimeOfObservation);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.16")){
																	String responsibleObserverPersonId=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("XCN.1").item(0).getFirstChild().getNodeValue();
																	String responsibleObserverPersonFamilyName = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("XCN.2").item(0).getFirstChild().getNodeValue();
																	String responsibleObserverPersonGivenName = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("XCN.3").item(0).getFirstChild().getNodeValue();
																	String responsibleObserverPersonMiddleName = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("XCN.4").item(0).getFirstChild().getNodeValue();
																	orderObservationDetail.setResponsibleObserverName(responsibleObserverPersonId+" "+responsibleObserverPersonFamilyName+" "+responsibleObserverPersonGivenName+" "+responsibleObserverPersonMiddleName);
																}
																if(orderDataOrderDetailOrderObservationNodeElemenmt.getNodeName().contentEquals("OBX.17")){
																	
																	String observationMethodId=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String observationMethodText=orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String observationMethodNameOfCodingSystem = orderDataOrderDetailOrderObservationNodeElemenmt.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	
																	orderObservationDetail.setObservationMethodId(observationMethodId);
																	orderObservationDetail.setObservationMethodText(observationMethodText);
																	orderObservationDetail.setObservationCodingSystemName(observationMethodNameOfCodingSystem);
																	
																	
																}
																
																orderObservationDetail.setPatientId(patientId);
																orderObservationDetailList.add(orderObservationDetail);
																
																
																
															}
															
														}
														
														labOrders.setLabOrderObservationDetailList(orderObservationDetailList);
													}
													
													// DG1 Diagnosis 
													// Split Diagnosis in Child Nodes 
													if(orderDataOrderDetailNodeElement.getNodeName().contentEquals("DG1")){
														NodeList orderDataOrderDetailDiagnosisNode=orderDataOrderDetailNodeElement.getChildNodes();
													    ArrayList<PatientDiagnosesDetails> diagnosesDetailList=new ArrayList<PatientDiagnosesDetails>();
														for(int g=0;g<orderDataOrderDetailDiagnosisNode.getLength();g++){
															if(orderDataOrderDetailDiagnosisNode.item(g).getNodeType() == Node.ELEMENT_NODE){
																PatientDiagnosesDetails diagnosesDetails=new PatientDiagnosesDetails();
																Element orderDataOrderDetailDiagnosisNodeElement=(Element) orderDataOrderDetailDiagnosisNode.item(g);
																logger.info("orderDataOrderDetailDiagnosisNodeElement"+orderDataOrderDetailDiagnosisNodeElement);
																
																if(!(orderDataOrderDetailDiagnosisNodeElement.getElementsByTagName("DG1.1").item(0) == null )){
																	String setIdDiagnosis=orderDataOrderDetailDiagnosisNodeElement.getElementsByTagName("DG1.1").item(0).getFirstChild().getNodeValue();
																	logger.info("setIdDiagnosis"+setIdDiagnosis);
																	
																}
																
																// Get Value from Diagnosis Code Node 
																if(orderDataOrderDetailDiagnosisNodeElement.getNodeName().contentEquals("DG1.3")){
																	String diagnosisCodeIdentifier=orderDataOrderDetailDiagnosisNodeElement.getElementsByTagName("CE.1").item(0).getFirstChild().getNodeValue();
																	String diagnosisText=orderDataOrderDetailDiagnosisNodeElement.getElementsByTagName("CE.2").item(0).getFirstChild().getNodeValue();
																	String diagnosisCodingSystem=orderDataOrderDetailDiagnosisNodeElement.getElementsByTagName("CE.3").item(0).getFirstChild().getNodeValue();
																	diagnosesDetails.setIcdId(diagnosisCodeIdentifier);
																	diagnosesDetails.setCode(diagnosisText);
																	diagnosesDetails.setIcdCodeType(diagnosisCodingSystem);
																}
																
																diagnosesDetails.setPatientId(Integer.parseInt(patientId));
																
																diagnosesDetailList.add(diagnosesDetails);
																
															}
															
															
														}
														
														labOrders.setDiagnosesDetailList(diagnosesDetailList);
													}
													
													
												}
											}
										}
										
									}
								}
								
							}
						
							
							
							
						}
						emdeonLabOrdersList.add(labOrders);
					}
					
					
				}
			}
		
		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException ne) {
			// TODO: handle exception
			ne.printStackTrace();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return emdeonLabOrdersList;
	}
	
	
	
	
	 
}
