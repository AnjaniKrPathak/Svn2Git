/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class PharmacogenomicsRecomendations {

	private int id;
	private PharmacogenomicsClassification pharmacogenomicsId;
	private String drugName;
	private String recommendation;
	private String implications;
	private String impact;
	private String attentionRating;
	private String ovaleMessage;
	private String gene1;
	private String phenotype;
	private String genericDrugName;
	
	
		
	//--------View Member
	
	private String dose;
	private String regimen;
	private String drugClass;
	private String drugId;
	private int patientMedicationDataId;
	private String impactingGene;
	private String drugForCompareWithFutureImpactedMedicine;
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the pharmacogenomicsId
	 */
	public PharmacogenomicsClassification getPharmacogenomicsId() {
		if(pharmacogenomicsId==null){
			pharmacogenomicsId=new PharmacogenomicsClassification();
		}
		return pharmacogenomicsId;
	}
	/**
	 * @param pharmacogenomicsId the pharmacogenomicsId to set
	 */
	public void setPharmacogenomicsId(
			PharmacogenomicsClassification pharmacogenomicsId) {
		this.pharmacogenomicsId = pharmacogenomicsId;
	}
	
	/**
	 * @return the recommendation
	 */
	public String getRecommendation() {
		return recommendation;
	}
	/**
	 * @param recommendation the recommendation to set
	 */
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	/**
	 * @return the implications
	 */
	public String getImplications() {
		return implications;
	}
	/**
	 * @param implications the implications to set
	 */
	public void setImplications(String implications) {
		this.implications = implications;
	}
	/**
	 * @return the impact
	 */
	public String getImpact() {
		return impact;
	}
	/**
	 * @param impact the impact to set
	 */
	public void setImpact(String impact) {
		this.impact = impact;
	}
	/**
	 * @return the dose
	 */
	public String getDose() {
		return dose;
	}
	/**
	 * @param dose the dose to set
	 */
	public void setDose(String dose) {
		this.dose = dose;
	}
	/**
	 * @return the regimen
	 */
	public String getRegimen() {
		return regimen;
	}
	/**
	 * @param regimen the regimen to set
	 */
	public void setRegimen(String regimen) {
		this.regimen = regimen;
	}
	/**
	 * @return the drugName
	 */
	public String getDrugName() {
		return drugName;
	}
	/**
	 * @param drugName the drugName to set
	 */
	public void setDrugName(String drugName) {
		this.drugName = drugName;
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
	/**
	 * @return the drugClass
	 */
	public String getDrugClass() {
		return drugClass;
	}
	/**
	 * @param drugClass the drugClass to set
	 */
	public void setDrugClass(String drugClass) {
		this.drugClass = drugClass;
	}
	/**
	 * @return the ovaleMessage
	 */
	public String getOvaleMessage() {
		return ovaleMessage;
	}
	/**
	 * @param ovaleMessage the ovaleMessage to set
	 */
	public void setOvaleMessage(String ovaleMessage) {
		this.ovaleMessage = ovaleMessage;
	}
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public int getPatientMedicationDataId() {
		return patientMedicationDataId;
	}
	public void setPatientMedicationDataId(int patientMedicationDataId) {
		this.patientMedicationDataId = patientMedicationDataId;
	}
	public String getGene1() {
		return gene1;
	}
	public void setGene1(String gene1) {
		this.gene1 = gene1;
	}
	public String getGenericDrugName() {
		return genericDrugName;
	}
	public void setGenericDrugName(String genericDrugName) {
		this.genericDrugName = genericDrugName;
	}
	public String getPhenotype() {
		return phenotype;
	}
	public void setPhenotype(String phenotype) {
		this.phenotype = phenotype;
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
	public String getDrugForCompareWithFutureImpactedMedicine() {
		return drugForCompareWithFutureImpactedMedicine;
	}
	public void setDrugForCompareWithFutureImpactedMedicine(
			String drugForCompareWithFutureImpactedMedicine) {
		this.drugForCompareWithFutureImpactedMedicine = drugForCompareWithFutureImpactedMedicine;
	}
		
	
}
