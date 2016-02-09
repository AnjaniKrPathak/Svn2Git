package com.clinakos.data.model.patient;

import java.util.Date;



public class UserInsuranceDetails {
	private static final long serialVersionUID = -7783038885323203850L;
	
	private int id;
	private int userId;
	private int insuranceId;
	private String insuranceName;
	private String primaryInsurance;
	private String source;
	private String pbm;
	private String pbmId;
	private String insurancePlanName;
	private String insurancePlanIdNewCrop;
	private String coverageListId;
	private String cardHolderName;
	private String cardholderId;
	private String personCode;
	private String groupNumber;
	private String groupNumberId;
	private String formularyId;
	private String alternateFormularyId;
	private String bin;
	private String binInfo;
	private String copayId;
	private String pharmacyBenefit;
	private String mailOrderBenefit;
	private String specialtyPharmacyBenefit;
	private String ltcBenefit;
	private String pbmPatientId;
	private String subscriberDate;
	private String pharmacyBenefitDescription;
	private String mailOrderBenefitDescription;
	private String specialtyPharmacyBenefitDescription;
	private String ltcBenefitDescription;
	private String cacheExpiryTimestamp;
	private String benefitLoop;
	private String companyName;
	
	private String secondaryInsurance;
	private String tertiaryInsurance;
	
	private boolean checkInsuranceDelete;
	
	private String payorAndInsuredID;
	private String payorName;
	private String payorAddress1;
	private String payorAddress2;
	private String payorCity;
	private String payorState;
	private String payorZip;
	private String payorZip4;
	private String payorCountry;
	private String insuranceServiceProvider;
	private String payorGroupNumber;
	private String patientRelationship;
	private String priorityCode;
	private String policyNumber;
	private String insuredFirstName;
	private String insuredMiddleName;
	private String insuredLastName;
	private String insuredAddress1;
	private String insuredAddress2;
	private String insuredCity;
	private String insuredState;
	private String insuredZip;
	private String insuredZip4;
	private String insuredCountry;
	private Date insuredDOB;
	private String insuredGender;
	private String insuredHomeTelephone;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInsuranceId() {
		return insuranceId;
	}
	public void setInsuranceId(int insuranceId) {
		this.insuranceId = insuranceId;
	}
	
	public String getPrimaryInsurance() {
		return primaryInsurance;
	}
	public void setPrimaryInsurance(String primaryInsurance) {
		this.primaryInsurance = primaryInsurance;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	/**
	 * @return the pbmId
	 */
	public String getPbmId() {
		return pbmId;
	}
	/**
	 * @param pbmId the pbmId to set
	 */
	public void setPbmId(String pbmId) {
		this.pbmId = pbmId;
	}
	/**
	 * @return the insurancePlanName
	 */
	public String getInsurancePlanName() {
		return insurancePlanName;
	}
	/**
	 * @param insurancePlanName the insurancePlanName to set
	 */
	public void setInsurancePlanName(String insurancePlanName) {
		this.insurancePlanName = insurancePlanName;
	}
	/**
	 * @return the coverageListId
	 */
	public String getCoverageListId() {
		return coverageListId;
	}
	/**
	 * @param coverageListId the coverageListId to set
	 */
	public void setCoverageListId(String coverageListId) {
		this.coverageListId = coverageListId;
	}
	/**
	 * @return the cardHolderName
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}
	/**
	 * @param cardHolderName the cardHolderName to set
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	/**
	 * @return the cardholderId
	 */
	public String getCardholderId() {
		return cardholderId;
	}
	/**
	 * @param cardholderId the cardholderId to set
	 */
	public void setCardholderId(String cardholderId) {
		this.cardholderId = cardholderId;
	}
	/**
	 * @return the personCode
	 */
	public String getPersonCode() {
		return personCode;
	}
	/**
	 * @param personCode the personCode to set
	 */
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	/**
	 * @return the groupNumber
	 */
	public String getGroupNumber() {
		return groupNumber;
	}
	/**
	 * @param groupNumber the groupNumber to set
	 */
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	/**
	 * @return the groupNumberId
	 */
	public String getGroupNumberId() {
		return groupNumberId;
	}
	/**
	 * @param groupNumberId the groupNumberId to set
	 */
	public void setGroupNumberId(String groupNumberId) {
		this.groupNumberId = groupNumberId;
	}
	/**
	 * @return the formularyId
	 */
	public String getFormularyId() {
		return formularyId;
	}
	/**
	 * @param formularyId the formularyId to set
	 */
	public void setFormularyId(String formularyId) {
		this.formularyId = formularyId;
	}
	/**
	 * @return the alternateFormularyId
	 */
	public String getAlternateFormularyId() {
		return alternateFormularyId;
	}
	/**
	 * @param alternateFormularyId the alternateFormularyId to set
	 */
	public void setAlternateFormularyId(String alternateFormularyId) {
		this.alternateFormularyId = alternateFormularyId;
	}
	/**
	 * @return the bin
	 */
	public String getBin() {
		return bin;
	}
	/**
	 * @param bin the bin to set
	 */
	public void setBin(String bin) {
		this.bin = bin;
	}
	/**
	 * @return the binInfo
	 */
	public String getBinInfo() {
		return binInfo;
	}
	/**
	 * @param binInfo the binInfo to set
	 */
	public void setBinInfo(String binInfo) {
		this.binInfo = binInfo;
	}
	/**
	 * @return the copayId
	 */
	public String getCopayId() {
		return copayId;
	}
	/**
	 * @param copayId the copayId to set
	 */
	public void setCopayId(String copayId) {
		this.copayId = copayId;
	}
	/**
	 * @return the pharmacyBenefit
	 */
	public String getPharmacyBenefit() {
		return pharmacyBenefit;
	}
	/**
	 * @param pharmacyBenefit the pharmacyBenefit to set
	 */
	public void setPharmacyBenefit(String pharmacyBenefit) {
		this.pharmacyBenefit = pharmacyBenefit;
	}
	/**
	 * @return the mailOrderBenefit
	 */
	public String getMailOrderBenefit() {
		return mailOrderBenefit;
	}
	/**
	 * @param mailOrderBenefit the mailOrderBenefit to set
	 */
	public void setMailOrderBenefit(String mailOrderBenefit) {
		this.mailOrderBenefit = mailOrderBenefit;
	}
	/**
	 * @return the specialtyPharmacyBenefit
	 */
	public String getSpecialtyPharmacyBenefit() {
		return specialtyPharmacyBenefit;
	}
	/**
	 * @param specialtyPharmacyBenefit the specialtyPharmacyBenefit to set
	 */
	public void setSpecialtyPharmacyBenefit(String specialtyPharmacyBenefit) {
		this.specialtyPharmacyBenefit = specialtyPharmacyBenefit;
	}
	/**
	 * @return the ltcBenefit
	 */
	public String getLtcBenefit() {
		return ltcBenefit;
	}
	/**
	 * @param ltcBenefit the ltcBenefit to set
	 */
	public void setLtcBenefit(String ltcBenefit) {
		this.ltcBenefit = ltcBenefit;
	}
	/**
	 * @return the pbmPatientId
	 */
	public String getPbmPatientId() {
		return pbmPatientId;
	}
	/**
	 * @param pbmPatientId the pbmPatientId to set
	 */
	public void setPbmPatientId(String pbmPatientId) {
		this.pbmPatientId = pbmPatientId;
	}
	/**
	 * @return the subscriberDate
	 */
	public String getSubscriberDate() {
		return subscriberDate;
	}
	/**
	 * @param subscriberDate the subscriberDate to set
	 */
	public void setSubscriberDate(String subscriberDate) {
		this.subscriberDate = subscriberDate;
	}
	/**
	 * @return the pharmacyBenefitDescription
	 */
	public String getPharmacyBenefitDescription() {
		return pharmacyBenefitDescription;
	}
	/**
	 * @param pharmacyBenefitDescription the pharmacyBenefitDescription to set
	 */
	public void setPharmacyBenefitDescription(String pharmacyBenefitDescription) {
		this.pharmacyBenefitDescription = pharmacyBenefitDescription;
	}
	/**
	 * @return the mailOrderBenefitDescription
	 */
	public String getMailOrderBenefitDescription() {
		return mailOrderBenefitDescription;
	}
	/**
	 * @param mailOrderBenefitDescription the mailOrderBenefitDescription to set
	 */
	public void setMailOrderBenefitDescription(String mailOrderBenefitDescription) {
		this.mailOrderBenefitDescription = mailOrderBenefitDescription;
	}
	/**
	 * @return the specialtyPharmacyBenefitDescription
	 */
	public String getSpecialtyPharmacyBenefitDescription() {
		return specialtyPharmacyBenefitDescription;
	}
	/**
	 * @param specialtyPharmacyBenefitDescription the specialtyPharmacyBenefitDescription to set
	 */
	public void setSpecialtyPharmacyBenefitDescription(
			String specialtyPharmacyBenefitDescription) {
		this.specialtyPharmacyBenefitDescription = specialtyPharmacyBenefitDescription;
	}
	/**
	 * @return the ltcBenefitDescription
	 */
	public String getLtcBenefitDescription() {
		return ltcBenefitDescription;
	}
	/**
	 * @param ltcBenefitDescription the ltcBenefitDescription to set
	 */
	public void setLtcBenefitDescription(String ltcBenefitDescription) {
		this.ltcBenefitDescription = ltcBenefitDescription;
	}
	/**
	 * @return the cacheExpiryTimestamp
	 */
	public String getCacheExpiryTimestamp() {
		return cacheExpiryTimestamp;
	}
	/**
	 * @param cacheExpiryTimestamp the cacheExpiryTimestamp to set
	 */
	public void setCacheExpiryTimestamp(String cacheExpiryTimestamp) {
		this.cacheExpiryTimestamp = cacheExpiryTimestamp;
	}
	/**
	 * @return the benefitLoop
	 */
	public String getBenefitLoop() {
		return benefitLoop;
	}
	/**
	 * @param benefitLoop the benefitLoop to set
	 */
	public void setBenefitLoop(String benefitLoop) {
		this.benefitLoop = benefitLoop;
	}
	/**
	 * @return the pbm
	 */
	public String getPbm() {
		return pbm;
	}
	/**
	 * @param pbm the pbm to set
	 */
	public void setPbm(String pbm) {
		this.pbm = pbm;
	}
	/**
	 * @return the insurancePlanIdNewCrop
	 */
	public String getInsurancePlanIdNewCrop() {
		return insurancePlanIdNewCrop;
	}
	/**
	 * @param insurancePlanIdNewCrop the insurancePlanIdNewCrop to set
	 */
	public void setInsurancePlanIdNewCrop(String insurancePlanIdNewCrop) {
		this.insurancePlanIdNewCrop = insurancePlanIdNewCrop;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSecondaryInsurance() {
		return secondaryInsurance;
	}
	public void setSecondaryInsurance(String secondaryInsurance) {
		this.secondaryInsurance = secondaryInsurance;
	}
	public String getTertiaryInsurance() {
		return tertiaryInsurance;
	}
	public void setTertiaryInsurance(String tertiaryInsurance) {
		this.tertiaryInsurance = tertiaryInsurance;
	}
	public boolean isCheckInsuranceDelete() {
		return checkInsuranceDelete;
	}
	public void setCheckInsuranceDelete(boolean checkInsuranceDelete) {
		this.checkInsuranceDelete = checkInsuranceDelete;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + insuranceId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserInsuranceDetails other = (UserInsuranceDetails) obj;
		if (insuranceId != other.insuranceId){
			return false;
		}
		else{
		return true;
		}
	}
	
	
	
	
	
	public String getPayorAndInsuredID() {
		return payorAndInsuredID;
	}
	public void setPayorAndInsuredID(String payorAndInsuredID) {
		this.payorAndInsuredID = payorAndInsuredID;
	}
	public String getPayorName() {
		return payorName;
	}
	public void setPayorName(String payorName) {
		this.payorName = payorName;
	}
	public String getPayorAddress1() {
		return payorAddress1;
	}
	public void setPayorAddress1(String payorAddress1) {
		this.payorAddress1 = payorAddress1;
	}
	public String getPayorAddress2() {
		return payorAddress2;
	}
	public void setPayorAddress2(String payorAddress2) {
		this.payorAddress2 = payorAddress2;
	}
	public String getPayorCity() {
		return payorCity;
	}
	public void setPayorCity(String payorCity) {
		this.payorCity = payorCity;
	}
	public String getPayorState() {
		return payorState;
	}
	public void setPayorState(String payorState) {
		this.payorState = payorState;
	}
	public String getPayorZip() {
		return payorZip;
	}
	public void setPayorZip(String payorZip) {
		this.payorZip = payorZip;
	}
	public String getPayorZip4() {
		return payorZip4;
	}
	public void setPayorZip4(String payorZip4) {
		this.payorZip4 = payorZip4;
	}
	public String getPayorCountry() {
		return payorCountry;
	}
	public void setPayorCountry(String payorCountry) {
		this.payorCountry = payorCountry;
	}
	public String getInsuranceServiceProvider() {
		return insuranceServiceProvider;
	}
	public void setInsuranceServiceProvider(String insuranceServiceProvider) {
		this.insuranceServiceProvider = insuranceServiceProvider;
	}
	
	public String getPayorGroupNumber() {
		return payorGroupNumber;
	}
	public void setPayorGroupNumber(String payorGroupNumber) {
		this.payorGroupNumber = payorGroupNumber;
	}
	public String getPatientRelationship() {
		return patientRelationship;
	}
	public void setPatientRelationship(String patientRelationship) {
		this.patientRelationship = patientRelationship;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getPolicyNumber() {
		return policyNumber;
	}
	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}
	public String getInsuredFirstName() {
		return insuredFirstName;
	}
	public void setInsuredFirstName(String insuredFirstName) {
		this.insuredFirstName = insuredFirstName;
	}
	public String getInsuredMiddleName() {
		return insuredMiddleName;
	}
	public void setInsuredMiddleName(String insuredMiddleName) {
		this.insuredMiddleName = insuredMiddleName;
	}
	public String getInsuredLastName() {
		return insuredLastName;
	}
	public void setInsuredLastName(String insuredLastName) {
		this.insuredLastName = insuredLastName;
	}
	public String getInsuredAddress1() {
		return insuredAddress1;
	}
	public void setInsuredAddress1(String insuredAddress1) {
		this.insuredAddress1 = insuredAddress1;
	}
	public String getInsuredAddress2() {
		return insuredAddress2;
	}
	public void setInsuredAddress2(String insuredAddress2) {
		this.insuredAddress2 = insuredAddress2;
	}
	public String getInsuredCity() {
		return insuredCity;
	}
	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}
	public String getInsuredState() {
		return insuredState;
	}
	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}
	public String getInsuredZip() {
		return insuredZip;
	}
	public void setInsuredZip(String insuredZip) {
		this.insuredZip = insuredZip;
	}
	public String getInsuredZip4() {
		return insuredZip4;
	}
	public void setInsuredZip4(String insuredZip4) {
		this.insuredZip4 = insuredZip4;
	}
	public String getInsuredCountry() {
		return insuredCountry;
	}
	public void setInsuredCountry(String insuredCountry) {
		this.insuredCountry = insuredCountry;
	}
	public Date getInsuredDOB() {
		return insuredDOB;
	}
	public void setInsuredDOB(Date insuredDOB) {
		this.insuredDOB = insuredDOB;
	}
	public String getInsuredGender() {
		return insuredGender;
	}
	public void setInsuredGender(String insuredGender) {
		this.insuredGender = insuredGender;
	}
	public String getInsuredHomeTelephone() {
		return insuredHomeTelephone;
	}
	public void setInsuredHomeTelephone(String insuredHomeTelephone) {
		this.insuredHomeTelephone = insuredHomeTelephone;
	}
	/**
	 * @return the insuranceName
	 */
	public String getInsuranceName() {
		return insuranceName;
	}
	/**
	 * @param insuranceName the insuranceName to set
	 */
	public void setInsuranceName(String insuranceName) {
		this.insuranceName = insuranceName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  pbm ;
	}
	
	
	
	
	
	
	
	

}
