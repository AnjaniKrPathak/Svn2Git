package com.clinakos.common.util;

import org.drools.compiler.lang.dsl.DSLMapParser.statement_return;

/*
 * for using constant value...
 * @author:Gopal Krishna JHA
 */
public  class ClinakosConstant {
	public static final int NUMBER_OF_ROW_IN_MED_HISTORY=5;
	public static final double Goal_INR_range_value=3.5;
	public static final String YES="yes";
	public static final String NO="no";
	public static final String ANTICOAG="Anticoag";
	public static final String ANTI_PLATELET="Anti Platelet";
	
	public static final String DISCONTINUE_MEDS ="Discontinued Med";
	public static final String CHANGE_MEDS="Changed Med (Not Completed)";
	public static final String DOSE_CHANGE="Changed Dose";
	public static final String REGIMEN_CHANGE="Regimen Changed";
	public static final String MODIFY_MEDS="Changed Med (Not Completed)";
	
	public static final String OTHER="Other";
	public static final String SEND_MESSAGE="send message";
	public static final String KIDNEYFUNCTION="Kidney";
	public static final String LIVERFUNCTION="Liver";
	public static final String UNDERWEIGHT="Under Weight";
	public static final String NORMAL_WEIGHT="Normal Weight";
	public static final String OVER_WEIGHT="Over Weight";
	public static final String OBESE="Obese";
	public static final String VERY_OBESE="Very Obese";
	public static final String MORBIDLY_OBESE="Morbidly Obese";
	public static final String IMAGE_EEXTENSION=".jpg";
	public static final String ASIAN="asian";
	public static final String BLACK="black";
	public static final String AFRICAN_AMERICAN="african american";
	public static final String WHITE="white";
	public static final String CAUCASIAN="caucasian";
	public static final String A="A";
	public static final String B="B";
	public static final String C="C";
	public static final String U="U";
	public static final String Y="Y";
	public static final String DATE_PATTERN="yyyyMMdd";
	public static final String MM_DD_YYYY_DATE_PATTERN="MM/dd/yyyy";
	
	public static final String CHANGES_QUANTITY ="Dispense Changed";
	public static final String CHANGES_DIRECTIONS ="Regimen Changed";
	public static final String CHANGES_DOSE ="Changed Dose";
	public static final String TRANSMITTED_MED="Transmitted ";
	public static final String NEW_ADDED_MEDS ="Newly Added Meds (Not Completed)";
	public static final String RECONCILE_MEDS ="Reconciled Med (Not Completed)";
	public static final String DB ="DB";
	public static final String CHRONIC="Chronic";
	public static final String DAYS ="Days";
	public static final String BASElINE ="Baseline";
	public static final String WEEKS ="Weeks";
	public static final String MONTHS ="Months";
	public static final String MONTH ="Month";
	public static final String YEARS ="Years";
	
	public static final String WARFARIN ="Warfarin";
	public static final String NOT_RECOMMENDED= "Not recommended";
	public static final String WEEK="Week";
	public static final String PRESCRIPITION_STATUS_PENDING="P";
	public static final String PRESCRIPITION_STATUS_CURRENT="C";
	public static final String HIGH_FILTER="severe";
	public static final String MEDIUM_FILTER="moderate";
	public static final String LOW_FILTER="empty";//as low is not in drug drug interaction webservice
	public static final String HIGH_FILTER_CONTRAINDICATED="contraindicated";
	public static final String HIGH_VALUE="High";
	public static final String MEDIUM_VALUE="Medium";
	public static final String LOW_VALUE="Low";
	//public static final String NOT_RECOMMENDED="Not recommended";
	public static final String PROP_VAL_DELIM = "|";
	public static final String MONITORING_PARAMETERS="monitoring_parameters";
	public static final String CLINAKOS_MESSAGES_LOCATION="/com/clinakos/properties/ClinakosMessages.properties";
	public static final String ALERT_SYMBOLS="alert_symbols_range";
	public static final String FREQUENCY_LIST_DATA="frequency_data";
	public static final String FREQUENCY_LIST_NO="frequency_number";
	
	public static final String 	INITIATION_STAGE="Initiation";
	public static final String 	MAINTAINENCE_STAGE="Maintenance";
	// add for patient Issues
	public static final String DRUG_DRUG_INTERECTION="Drug Drug Interaction";
    public static final String DRUG_DISEASE_INTERACTION="Drug Disease Interaction";
   public static final String DRUG_ALLERGY_INTERACTION="Drug Allergy Interaction";
   public static final  String HIGH_SEVERITY_LEVEL="High";
   public static final  String MEDIUM_SEVERITY_LEVEL="Medium";
   public static final String LOW_SEVERITY_LEVEL="Low";
   public static final String DRUG_SEVERITY_LEVEL_SEARCH_STR="severe";
   public static final String DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRAINDICATED="contraindicated";
	public static final String DRUG_SEVERITY_LEVEL_SEARCH_STR_MODERATE="moderate";
   public static final String DRUG_SEVERITY_TOOLTIP="Severe";
   public static final String DRUG_SEVERITY_TOOLTIP_CONTRAINDICATED="Contraindicated";
  public static	final String DRUG_SEVERITY_TOOLTIP_MODERATE="Moderate";
  public static	final String DRUG_SEVERITY_LEVEL_SEARCH_STR_DISEASE="Absolute contraindication";
  public static final String DRUG_SEVERITY_LEVEL_SEARCH_STR_RELATIVE="Relative contraindications";
  public static	final String DRUG_SEVERITY_LEVEL_SEARCH_STR_CONTRADICATION="Contraindication warning";
  public static	final String MILD_LEVEL="Mild";
  public static	final String PHARMACOGENOMIC="Pharmacogenomics";
  public static	final String HIGH_SEVERITY_LEVEL_RATING="High";
 public static	final String MEDIUM_SEVERITY_LEVEL_RATING="Medium";
 public static	final String LOW_SEVERITY_LEVEL_RATING="Low";
 public static final String MED_ACTION_PLAN="Med Action Plan";
 public static final String LOW_SLIDER_DATA="Low";
 public static final String MEDIUM_SLIDER_DATA="Medium";
 public static final String HIGH_SLIDER_DATA="High";
 public static final String DRUG_FILTERS="drug_filters";
 public static final String FORMULARY_FILTERS="formulary_filters";
 public static final String MED_CLASSIFICATION="med_classification_filters";
 public static final String PSYCHOPHARM_ACTION_CONFIRMATION_DAYS="lithium_action_confirm_days";
 public static final String DRUG_SCREENING_OPTIONS="drug_screening_options";
 
 	//for formulary #696 
 	public static final String TIER_4_LABEL="Tier 4 and above";
 	public static final String TIER_4_PREFERRED_LEVEL="Preferred Level 4";

 	public static final String DRUG_CLASSIFICATION="med_classfication";
 	public static final String OVW_MSG_ID="msgVarId:growlMsgId";
 	public static final String RED="Red";
 	public static final String AMBER="Amber";
 	public static final String GREEN="Green";
 	public static final String OPTIONS_VALUE="select_dropdown_decision_classification";
 	
 	//PsychoPharm QuickPrescribe View
 	public static final String PSYCHOPHARM_LITHIUM_QUICK_PRESCRIBE_VIEW="/page/Doctor/quickPrecribePsychopharm?faces-redirect=true";
 	
 	public static final String PSYCHOPHARM_DOSING_GUIDELINE_WIZARD="/page/Doctor/lithiumDosingGuideline.xhtml?faces-redirect=true";
 	public static final String LITHIUM_MED_NAME="lithium";
 	public static final String PSYCHOPHARM_DOSING_GUIDELINE_JAVASCRIPT_FUNC="doHiddenClickToDosingGuideLine()";
 	
 	//Keys for request to DRL ==>lithium clinic
 	public static final String PSYCHOPHARM_CONTRAINDICATED_MEDS="contraindicated_meds";
 	public static final String PSYCHOPHARM_CONTRAINDICATED_DIAGNOSIS="contraindicated_diagnosis";
 	public static final String MONITORING_PARAMETERS_LITHIUM="lithium_monitoring_parameters";
 	
 	public static final String HEPATITIS_C_VIEW="/page/Doctor/hepatitis.xhtml";
 	public static final String HEPATITIS_MED_NAME="sovaldi";
 	public static final String PSYCHOPHARM_CLINIC_NAME="psychopharm";
 	public static final String HEPATITIS_C_CLINIC_NAME="hepatitisC";
 	public static final String GENERAL_MAP_VIEW="general_map";
 	public static final String PSYCHOPHARM_MAP_VIEW="psychopharm_map";
 	
 	//Added By Nagaraj on 28/Jan/2015 for Formulary
 	public static final String NON_FORMULARY="Non Formulary";
 	public static final String NOT_REIMBURSED="Not Reimbursed";
 	public static final String PRIOR_AUTHORIZATION ="Prior Authorization";
 	public static final String ON_FORMULARY="On Formulary";
 	public static final String UNLISTED_DRUG="generic";
 	public static final String PREFERRED_LEVEL_1="Preferred Level 1";
 	public static final String PREFERRED_LEVEL_2="Preferred Level 2";
 	public static final String PREFERRED_LEVEL_3="Preferred Level 3";
 	public static final String OTHER_LEVELS="Preferred Level and above";
 	
 	public static final String FORMULARY_MAP_TO_OLD_TEXT="formulary_map";
 	public static final String FORMULARY_ANALYTIC_MAP_TO_OLD_TEXT="formulary_map_analytic_chart";
 	public static final String FORMULARY_ANALYTIC_MAP_TO_OLD_TEXT_REVERSE="formulary_map_analytic_chart_reverse";
 	
 	public static final Integer GERIATRIC_MINIMUM_AGE=65;
 	public static final Integer PAEDIATRIC_MAXIMUM_AGE=18;
 	
 	public static final String GERIATRIC_PRECAUTIONS="Geriatric Precaution";
 	public static final String PAEDIATRIC_PRECAUTIONS="Paediatric Precaution";
 	
 	public static final String GERIATRIC_HIGH_SEVERITY="Contraindication";
 	public static final String GERIATRIC_MEDIUM_SEVERITY="Precaution";
 	
 	
 	//----------added by saurabh for recover password
 	public static final String ALLOWED_RANDOM_ALPHA_NUMERIC_VALUE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";//@$^&*()_-+=}{][,/.;:!";
 	
 	//----------added by saurabh for decrypting data
 	public static final String DecryptionKey="akos";
 	
 	//--------Added By Anjani for Split Size of Selected Patient Array 
 	public static final int SELECTED_PATIENT_SPLIT_SIZE=500;
 	
 	//------Added by saurabh
 	public static final String ExternalDoctorDiagnosis="ExternalDoctor";
 	
 	public static final String DEVICE_APPS_HTML="/page/Doctor/deviceApps?faces-redirect=true";
 	
 	public static final int VALIDIC_TAB_1=1;
 	public static final int VALIDIC_TAB_2=2;
 	public static final int VALIDIC_TAB_3=3;
 	public static final int VALIDIC_TAB_4=4;
 	public static final int VALIDIC_TAB_5=5;
 	public static final int VALIDIC_TAB_6=6;
 	public static final int VALIDIC_TAB_7=7;
 	
 	public static final String VALIDIC_FITNESS_LABEL ="Fitness";
 	public static final String VALIDIC_NUTRITION_LABEL ="Nutrition";
 	public static final String VALIDIC_WEIGHT_LABEL ="Weight";
 	public static final String VALIDIC_SLEEP_LABEL ="Sleep";
 	public static final String VALIDIC_BIOMETRIC_LABEL ="Biometric";
 	public static final String VALIDIC_DIABETES_LABEL ="Diabetes";
 	public static final String VALIDIC_TOBACCO_LABEL ="Tobacco";
 	
 	
}

