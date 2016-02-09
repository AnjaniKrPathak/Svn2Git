package com.clinakos.data.model.patient;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.primefaces.model.SelectableDataModel;

import com.clinakos.data.model.core.FormularyDetail;



public class PatientMedicationData implements SelectableDataModel<FormularyDetail>{
	//private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int patientId;
	private int providerId;
	private String drugs="";
	//private String formula;
	private String signature;
	private String quantity;
	private double quantityforsorting;
	private String notes;
	private int reffils;
	private Date startDate;
	private Date lastFillDate;
	private String pharmacyName;
	private String complianceSymbol;
	private String directions;
	private String strengths;
	private String recommendedDose;
	private String comments;
	private String formularySymbol;
	private int compliancePercentage;
	private int doctorId;
	private String alterNateDrugs;
	private String prescriberName;
	private String prescriberNamefornewcropreturn;
	private String drugsNotes;
	private String medicineStatus="";
	private String causeOfAddMeds;
	private String causeOfTransmittedMeds;
	private int changeMedId;
	private Date modifyDate;
	private String today;
	private String clinicName;
	private String attentionRating;
	private String imageName;
	private boolean checkForMaintenance;
	
//-----------------VIEW DATA Member
	
	private String drugName;
	private Date drugPurchaseDate;
	private int daysInOneReffil;
	private Date dateOfNextReffil;
	private String medicineReminder;
	private List<String> genericMedicine;
	
	
	
	//for virtual pill box
	private String morning;
	private String afternoon;
	private String evening;
	private String night;
	private String otherTime;
	
	private boolean selectedImageForPatient;
	private boolean checkMedsForEprescribe =true;
	//private boolean selectedActionIconForPatient;
	//private boolean selectedSuggestionIconForPatient;
	private boolean iconForNewMedAdd;
	//for eprescribe
	private double drugId;
	private String dataProvider;
	private double drugNameId;
	
	//for meds..
	private String unit;
	private String dosageForm;
	private String byRoute;
	private boolean prn;
	private String additionalSig;
	private String daySupply;
	private boolean allowSubstitution;
	private String drugInstruction;
	private String genericName;
	private String additionalRecommendation;
	private String unitDetail;
	private String doctorComments;
	private String prescriptionGuid;
	private String prescriptionStatus;
	private String prescriptionSubStatus;
	private double calculatedDose;
	private String recomendation;
	private boolean flagForVisit;
	private String externalPrescriptionId;
	private String pharmacyType;
	private String pharmacyDetailsType;
	private String finalDestinationType;
	private String finalStatusType;

	private boolean daw_dns;
	private boolean one_Time;
	private boolean secondrx_90days;
	private boolean savetodoctorlist;
	private String sigcodemeaning;
	
	


	private boolean flagForMedActionPlan;
	
//--------------for comparing timestamp of clinakos and WS 
	private String dateWithTimeZoneForCompare;
	
	// for Pharmecogenomics 
	private String ovaleMessage;
	private String reason;
	private String phenoType;
	private String impactingGene;
	
	// for patient issues 
//	private int totalCount;
	private String totalCount="";
	private String issuesLevel;
	
	private String medNameForPopUp;
	
	//for Med action Plans indicator rendering
	private boolean medPlanExists;
	private String medPlanStrFilter;
	private String drugInteractionStr;
	private String diseaseInteractionStr;
	private String allergyInteractionStr;
	private String geriatricPrecautionStr;
	private String paediatricPrecautionStr;
	
	private boolean checkReconcileMedToCurrent;
	private boolean checkReconcileMedToCurrentForPharma;
	
	private String therapeuticCategory;
	

	private String medicineImageId;
	

	private String originalCount="";
	private String originalIssueLevel;
	
	private String sundayDose;
	private String mondayDose;
	private String tuesDay;
	private String wednesdayDose;
	private String thursdayDose;
	private String fridayDose;
	private String saturdayDose;
	private String averagedose;

	private String formularyText;//for UI purpose
	private int maxSeverity;

	public String getSundayDose() {
		return sundayDose;
	}
	public void setSundayDose(String sundayDose) {
		this.sundayDose = sundayDose;
	}
	public String getMondayDose() {
		return mondayDose;
	}
	public void setMondayDose(String mondayDose) {
		this.mondayDose = mondayDose;
	}
	public String getTuesDay() {
		return tuesDay;
	}
	public void setTuesDay(String tuesDay) {
		this.tuesDay = tuesDay;
	}
	public String getWednesdayDose() {
		return wednesdayDose;
	}
	public void setWednesdayDose(String wednesdayDose) {
		this.wednesdayDose = wednesdayDose;
	}
	public String getThursdayDose() {
		return thursdayDose;
	}
	public void setThursdayDose(String thursdayDose) {
		this.thursdayDose = thursdayDose;
	}
	public String getFridayDose() {
		return fridayDose;
	}
	public void setFridayDose(String fridayDose) {
		this.fridayDose = fridayDose;
	}
	public String getSaturdayDose() {
		return saturdayDose;
	}
	public void setSaturdayDose(String saturdayDose) {
		this.saturdayDose = saturdayDose;
	}


	private String drugCategory;
	private boolean specialtyDrugExist;
	private boolean showMedActionPlanCreateOrNot;
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	/*public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}*/
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getReffils() {
		return reffils;
	}
	public void setReffils(int reffils) {
		this.reffils = reffils;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getLastFillDate() {
		return lastFillDate;
	}
	public void setLastFillDate(Date lastFillDate) {
		this.lastFillDate = lastFillDate;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getComplianceSymbol() {
		return complianceSymbol;
	}
	public void setComplianceSymbol(String complianceSymbol) {
		this.complianceSymbol = complianceSymbol;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getStrengths() {
		return strengths;
	}
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	public String getFormularySymbol() {
		return formularySymbol;
	}
	public void setFormularySymbol(String formularySymbol) {
		this.formularySymbol = formularySymbol;
	}
	
	public String getDrugName() {
		return drugName;
	}
	
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public int getCompliancePercentage() {
		return compliancePercentage;
	}
	public void setCompliancePercentage(int compliancePercentage) {
		this.compliancePercentage = compliancePercentage;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getPrescriberName() {
		return prescriberName;
	}
	public void setPrescriberName(String prescriberName) {
		this.prescriberName = prescriberName;
	}
	public String getAlterNateDrugs() {
		return alterNateDrugs;
	}
	public void setAlterNateDrugs(String alterNateDrugs) {
		this.alterNateDrugs = alterNateDrugs;
	}
	
	public FormularyDetail getRowData(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public Object getRowKey(FormularyDetail arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public String getDrugsNotes() {
		return drugsNotes;
	}
	public void setDrugsNotes(String drugsNotes) {
		this.drugsNotes = drugsNotes;
	}
	public String getMedicineStatus() {
		return medicineStatus;
	}
	public void setMedicineStatus(String medicineStatus) {
		this.medicineStatus = medicineStatus;
	}
	public int getChangeMedId() {
		return changeMedId;
	}
	public void setChangeMedId(int changeMedId) {
		this.changeMedId = changeMedId;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	/**
	 * @return the attentionRating
	 */
	public String getAttentionRating() {
		return attentionRating;
	}
	/**
	 * @param attentionRating the attentionRating to set
	 */
	public void setAttentionRating(String attentionRating) {
		this.attentionRating = attentionRating;
	}
	public Date getDrugPurchaseDate() {
		return drugPurchaseDate;
	}
	public void setDrugPurchaseDate(Date drugPurchaseDate) {
		this.drugPurchaseDate = drugPurchaseDate;
	}
	public int getDaysInOneReffil() {
		return daysInOneReffil;
	}
	public void setDaysInOneReffil(int daysInOneReffil) {
		this.daysInOneReffil = daysInOneReffil;
	}
	public Date getDateOfNextReffil() {
		return dateOfNextReffil;
	}
	public void setDateOfNextReffil(Date dateOfNextReffil) {
		this.dateOfNextReffil = dateOfNextReffil;
	}
	public String getMedicineReminder() {
		return medicineReminder;
	}
	public void setMedicineReminder(String medicineReminder) {
		this.medicineReminder = medicineReminder;
	}
	public String getMorning() {
		return morning;
	}
	public void setMorning(String morning) {
		this.morning = morning;
	}
	public String getAfternoon() {
		return afternoon;
	}
	public void setAfternoon(String afternoon) {
		this.afternoon = afternoon;
	}
	public String getEvening() {
		return evening;
	}
	public void setEvening(String evening) {
		this.evening = evening;
	}
	public String getNight() {
		return night;
	}
	public void setNight(String night) {
		this.night = night;
	}
	public String getOtherTime() {
		return otherTime;
	}
	public void setOtherTime(String otherTime) {
		this.otherTime = otherTime;
	}
	/**
	 * @return the genericMedicine
	 */
	public List<String> getGenericMedicine() {
		return genericMedicine;
	}
	/**
	 * @param genericMedicine the genericMedicine to set
	 */
	public void setGenericMedicine(List<String> genericMedicine) {
		this.genericMedicine = genericMedicine;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public boolean isSelectedImageForPatient() {
		return selectedImageForPatient;
	}
	public void setSelectedImageForPatient(boolean selectedImageForPatient) {
		this.selectedImageForPatient = selectedImageForPatient;
	}
	public boolean isCheckForMaintenance() {
		return checkForMaintenance;
	}
	public void setCheckForMaintenance(boolean checkForMaintenance) {
		this.checkForMaintenance = checkForMaintenance;
	}
	public boolean isCheckMedsForEprescribe() {
		return checkMedsForEprescribe;
	}
	public void setCheckMedsForEprescribe(boolean checkMedsForEprescribe) {
		this.checkMedsForEprescribe = checkMedsForEprescribe;
	}
	public String getCauseOfAddMeds() {
		return causeOfAddMeds;
	}
	public void setCauseOfAddMeds(String causeOfAddMeds) {
		this.causeOfAddMeds = causeOfAddMeds;
	}
	public String getRecommendedDose() {
		return recommendedDose;
	}
	public void setRecommendedDose(String recommendedDose) {
		this.recommendedDose = recommendedDose;
	}
	public String getDataProvider() {
		return dataProvider;
	}
	public void setDataProvider(String dataProvider) {
		this.dataProvider = dataProvider;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public double getDrugNameId() {
		return drugNameId;
	}
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDosageForm() {
		return dosageForm;
	}
	public void setDosageForm(String dosageForm) {
		this.dosageForm = dosageForm;
	}
	public String getByRoute() {
		return byRoute;
	}
	public void setByRoute(String byRoute) {
		this.byRoute = byRoute;
	}
	public boolean isPrn() {
		return prn;
	}
	public void setPrn(boolean prn) {
		this.prn = prn;
	}
	
	public boolean isAllowSubstitution() {
		return allowSubstitution;
	}
	public void setAllowSubstitution(boolean allowSubstitution) {
		this.allowSubstitution = allowSubstitution;
	}
	public String getDrugInstruction() {
		return drugInstruction;
	}
	public void setDrugInstruction(String drugInstruction) {
		this.drugInstruction = drugInstruction;
	}
	public String getGenericName() {
		return genericName;
	}
	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}
	public String getAdditionalRecommendation() {
		return additionalRecommendation;
	}
	public void setAdditionalRecommendation(String additionalRecommendation) {
		this.additionalRecommendation = additionalRecommendation;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUnitDetail() {
		return unitDetail;
	}
	public void setUnitDetail(String unitDetail) {
		this.unitDetail = unitDetail;
	}

	public String getDoctorComments() {
		return doctorComments;
	}
	public void setDoctorComments(String doctorComments) {
		this.doctorComments = doctorComments;
	}
	public String getDaySupply() {
		return daySupply;
	}
	public void setDaySupply(String daySupply) {
		this.daySupply = daySupply;
	}
	public String getPrescriptionGuid() {
		return prescriptionGuid;
	}
	public void setPrescriptionGuid(String prescriptionGuid) {
		this.prescriptionGuid = prescriptionGuid;
	}
	public String getPrescriptionStatus() {
		return prescriptionStatus;
	}
	public void setPrescriptionStatus(String prescriptionStatus) {
		this.prescriptionStatus = prescriptionStatus;
	}
	public String getPrescriptionSubStatus() {
		return prescriptionSubStatus;
	}
	public void setPrescriptionSubStatus(String prescriptionSubStatus) {
		this.prescriptionSubStatus = prescriptionSubStatus;
	}
	public double getCalculatedDose() {
		return calculatedDose;
	}
	public void setCalculatedDose(double calculatedDose) {
		this.calculatedDose = calculatedDose;
	}
	/**
	 * @return the recomendation
	 */
	public String getRecomendation() {
		return recomendation;
	}
	/**
	 * @param recomendation the recomendation to set
	 */
	public void setRecomendation(String recomendation) {
		this.recomendation = recomendation;
	}

	/*public boolean isSelectedActionIconForPatient() {
		return selectedActionIconForPatient;
	}
	public void setSelectedActionIconForPatient(boolean selectedActionIconForPatient) {
		this.selectedActionIconForPatient = selectedActionIconForPatient;
	}
	public boolean isSelectedSuggestionIconForPatient() {
		return selectedSuggestionIconForPatient;
	}
	public void setSelectedSuggestionIconForPatient(
			boolean selectedSuggestionIconForPatient) {
		this.selectedSuggestionIconForPatient = selectedSuggestionIconForPatient;
	}*/
	public boolean isFlagForVisit() {
		return flagForVisit;
	}
	public void setFlagForVisit(boolean flagForVisit) {
		this.flagForVisit = flagForVisit;
	}
	public String getExternalPrescriptionId() {
		return externalPrescriptionId;
	}
	public void setExternalPrescriptionId(String externalPrescriptionId) {
		this.externalPrescriptionId = externalPrescriptionId;
	}
	/**
	 * @return the dateWithTimeZoneForCompare
	 */
	public String getDateWithTimeZoneForCompare() {
		return dateWithTimeZoneForCompare;
	}
	/**
	 * @param dateWithTimeZoneForCompare the dateWithTimeZoneForCompare to set
	 */
	public void setDateWithTimeZoneForCompare(String dateWithTimeZoneForCompare) {
		this.dateWithTimeZoneForCompare = dateWithTimeZoneForCompare;
	}
	/**
	 * @return the ovaleMessage
	 */
	public String getOvaleMessage() {
		return ovaleMessage;
	}
	
	public boolean isFlagForMedActionPlan() {
		return flagForMedActionPlan;
	}
	public void setFlagForMedActionPlan(boolean flagForMedActionPlan) {
		this.flagForMedActionPlan = flagForMedActionPlan;
	}
	
	/**
	 * @param ovaleMessage the ovaleMessage to set
	 */
	public void setOvaleMessage(String ovaleMessage) {
		this.ovaleMessage = ovaleMessage;
	}
	public String getAdditionalSig() {
		return additionalSig;
	}
	public void setAdditionalSig(String additionalSig) {
		this.additionalSig = additionalSig;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	public boolean isIconForNewMedAdd() {
		return iconForNewMedAdd;
	}
	public void setIconForNewMedAdd(boolean iconForNewMedAdd) {
		this.iconForNewMedAdd = iconForNewMedAdd;
	}
	/*public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}*/
	public void setIssuesLevel(String issuesLevel) {
		this.issuesLevel = issuesLevel;
	}
	public String getIssuesLevel() {
		return issuesLevel;
	}
	public String getMedNameForPopUp() {
		return medNameForPopUp;
	}

	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	public void setMedNameForPopUp(String medNameForPopUp) {
		this.medNameForPopUp = medNameForPopUp;
	}
	public boolean isMedPlanExists() {
		return medPlanExists;
	}
	public void setMedPlanExists(boolean medPlanExists) {
		this.medPlanExists = medPlanExists;
	}
	public boolean isCheckReconcileMedToCurrent() {
		return checkReconcileMedToCurrent;
	}
	public void setCheckReconcileMedToCurrent(boolean checkReconcileMedToCurrent) {
		this.checkReconcileMedToCurrent = checkReconcileMedToCurrent;
	}
	
	  public boolean equals(Object object)
	    {
	        boolean sameSame = false;

	        if (object != null && object instanceof PatientMedicationData)
	        {
	            sameSame = this.getDrugId() == ((PatientMedicationData) object).getDrugId();
	        }

	        return sameSame;
	    }
	  
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(drugId);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((drugs == null) ? 0 : drugs.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		return result;
	}
	
/*	public boolean equals(Object obj) {
		//SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		if (this == obj)
			return true;
		if (obj == null) 
			return false;
		if (getClass() != obj.getClass())
			return false;
		PatientMedicationData other = (PatientMedicationData) obj;
		if (Double.doubleToLongBits(drugId) == Double
				.doubleToLongBits(other.drugId) && drugs.equals(other.drugs)){
			return true;
		}
	
		else{
		return false;
		}
	}*/

	
	 /**
	 * @return the phenoType
	 */
	public String getPhenoType() {
		return phenoType;
	}
	/**
	 * @param phenoType the phenoType to set
	 */
	public void setPhenoType(String phenoType) {
		this.phenoType = phenoType;
	}


	/**
	 * @return the impactingGene
	 */
	public String getImpactingGene() {
		return impactingGene;
	}
	/**
	 * @param impactingGene the impactingGene to set
	 */
	public void setImpactingGene(String impactingGene) {
		this.impactingGene = impactingGene;
	}


	public String getMedPlanStrFilter() {
		return medPlanStrFilter;
	}
	public void setMedPlanStrFilter(String medPlanStrFilter) {
		this.medPlanStrFilter = medPlanStrFilter;
	}


	public String getDrugInteractionStr() {
		return drugInteractionStr;
	}
	public void setDrugInteractionStr(String drugInteractionStr) {
		this.drugInteractionStr = drugInteractionStr;
	}
	public String getDiseaseInteractionStr() {
		return diseaseInteractionStr;
	}
	public void setDiseaseInteractionStr(String diseaseInteractionStr) {
		this.diseaseInteractionStr = diseaseInteractionStr;
	}
	public String getAllergyInteractionStr() {
		return allergyInteractionStr;
	}
	public void setAllergyInteractionStr(String allergyInteractionStr) {
		this.allergyInteractionStr = allergyInteractionStr;
	}


	public static Comparator<PatientMedicationData> dateComparator 
    = new Comparator<PatientMedicationData>(){

	
		public int compare(PatientMedicationData o1, PatientMedicationData o2) {
			// TODO Auto-generated method stub
			return o2.getStartDate().compareTo(o1.getStartDate());
		}
		
	};

	public String getTherapeuticCategory() {
		return therapeuticCategory;
	}
	public void setTherapeuticCategory(String therapeuticCategory) {
		this.therapeuticCategory = therapeuticCategory;
	}

	/**
	 * @return the medicineImageId
	 */
	public String getMedicineImageId() {
		return medicineImageId;
	}
	/**
	 * @param medicineImageId the medicineImageId to set
	 */
	public void setMedicineImageId(String medicineImageId) {
		this.medicineImageId = medicineImageId;
	}

	public String getOriginalCount() {
		return originalCount;
	}
	public void setOriginalCount(String originalCount) {
		this.originalCount = originalCount;
	}
	public String getOriginalIssueLevel() {
		return originalIssueLevel;
	}
	public void setOriginalIssueLevel(String originalIssueLevel) {
		this.originalIssueLevel = originalIssueLevel;
	}

	public boolean isCheckReconcileMedToCurrentForPharma() {
		return checkReconcileMedToCurrentForPharma;
	}
	public void setCheckReconcileMedToCurrentForPharma(
			boolean checkReconcileMedToCurrentForPharma) {
		this.checkReconcileMedToCurrentForPharma = checkReconcileMedToCurrentForPharma;
	}



	public String getDrugCategory() {
		return drugCategory;
	}
	public void setDrugCategory(String drugCategory) {
		this.drugCategory = drugCategory;
	}
	public boolean isSpecialtyDrugExist() {
		return specialtyDrugExist;
	}
	public void setSpecialtyDrugExist(boolean specialtyDrugExist) {
		this.specialtyDrugExist = specialtyDrugExist;
	}
	public boolean isShowMedActionPlanCreateOrNot() {
		return showMedActionPlanCreateOrNot;
	}
	public void setShowMedActionPlanCreateOrNot(boolean showMedActionPlanCreateOrNot) {
		this.showMedActionPlanCreateOrNot = showMedActionPlanCreateOrNot;
	}
	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public double getQuantityforsorting() {
		return quantityforsorting;
	}
	public void setQuantityforsorting(double quantityforsorting) {
		this.quantityforsorting = quantityforsorting;
	}
	public String getAveragedose() {
		return averagedose;
	}
	public void setAveragedose(String averagedose) {
		this.averagedose = averagedose;
	}
	public String getCauseOfTransmittedMeds() {
		return causeOfTransmittedMeds;
	}
	public void setCauseOfTransmittedMeds(String causeOfTransmittedMeds) {
		this.causeOfTransmittedMeds = causeOfTransmittedMeds;
	}
	

	
	public String getPharmacyType() {
		return pharmacyType;
	}
	public void setPharmacyType(String pharmacyType) {
		this.pharmacyType = pharmacyType;
	}
	public String getPharmacyDetailsType() {
		return pharmacyDetailsType;
	}
	public void setPharmacyDetailsType(String pharmacyDetailsType) {
		this.pharmacyDetailsType = pharmacyDetailsType;
	}
	public String getFinalDestinationType() {
		return finalDestinationType;
	}
	public void setFinalDestinationType(String finalDestinationType) {
		this.finalDestinationType = finalDestinationType;
	}
	public String getFinalStatusType() {
		return finalStatusType;
	}
	public void setFinalStatusType(String finalStatusType) {
		this.finalStatusType = finalStatusType;
	}
	public String getPrescriberNamefornewcropreturn() {
		return prescriberNamefornewcropreturn;
	}
	public void setPrescriberNamefornewcropreturn(
			String prescriberNamefornewcropreturn) {
		this.prescriberNamefornewcropreturn = prescriberNamefornewcropreturn;
	}
	public String getSigcodemeaning() {
		return sigcodemeaning;
	}
	public void setSigcodemeaning(String sigcodemeaning) {
		this.sigcodemeaning = sigcodemeaning;
	}
	public boolean isDaw_dns() {
		return daw_dns;
	}
	public void setDaw_dns(boolean daw_dns) {
		this.daw_dns = daw_dns;
	}
	public boolean isSecondrx_90days() {
		return secondrx_90days;
	}
	public void setSecondrx_90days(boolean secondrx_90days) {
		this.secondrx_90days = secondrx_90days;
	}
	public boolean isOne_Time() {
		return one_Time;
	}
	public void setOne_Time(boolean one_Time) {
		this.one_Time = one_Time;
	}
	public boolean isSavetodoctorlist() {
		return savetodoctorlist;
	}
	public void setSavetodoctorlist(boolean savetodoctorlist) {
		this.savetodoctorlist = savetodoctorlist;
	}
	public String getFormularyText() {
		return formularyText;
	}
	public void setFormularyText(String formularyText) {
		this.formularyText = formularyText;
	}
	public String getGeriatricPrecautionStr() {
		return geriatricPrecautionStr;
	}
	public void setGeriatricPrecautionStr(String geriatricPrecautionStr) {
		this.geriatricPrecautionStr = geriatricPrecautionStr;
	}
	public String getPaediatricPrecautionStr() {
		return paediatricPrecautionStr;
	}
	public void setPaediatricPrecautionStr(String paediatricPrecautionStr) {
		this.paediatricPrecautionStr = paediatricPrecautionStr;
	}
	/**
	 * @return the maxSeverity
	 */
	public int getMaxSeverity() {
		return maxSeverity;
	}
	/**
	 * @param maxSeverity the maxSeverity to set
	 */
	public void setMaxSeverity(int maxSeverity) {
		this.maxSeverity = maxSeverity;
	}

	
	
}
