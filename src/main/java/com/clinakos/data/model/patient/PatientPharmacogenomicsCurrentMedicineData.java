/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class PatientPharmacogenomicsCurrentMedicineData {
	
	private int id;
	private int patientId;
	private String drug;
	private String impact;
	private String implications;
	private String recommendation;
	private String strengths;
	private String directions;
	private String geneSymbol;
	private String attentionRating;
	private String implicationOvaleMessage;
	private boolean status;
	
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
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public String getImpact() {
		return impact;
	}
	public void setImpact(String impact) {
		this.impact = impact;
	}
	public String getImplications() {
		return implications;
	}
	public void setImplications(String implications) {
		this.implications = implications;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getStrengths() {
		return strengths;
	}
	public void setStrengths(String strengths) {
		this.strengths = strengths;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getGeneSymbol() {
		return geneSymbol;
	}
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	public String getAttentionRating() {
		return attentionRating;
	}
	public void setAttentionRating(String attentionRating) {
		this.attentionRating = attentionRating;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getImplicationOvaleMessage() {
		return implicationOvaleMessage;
	}
	public void setImplicationOvaleMessage(String implicationOvaleMessage) {
		this.implicationOvaleMessage = implicationOvaleMessage;
	}
	@Override
	public int hashCode() {
		 int hash = 3;
		 	String patientID=String.valueOf(this.patientId);
		    hash = 7 * hash +patientID.hashCode();
		    hash = 7 * hash + this.drug.hashCode();
		    hash = 7 * hash + this.geneSymbol.hashCode();
		    return hash;
	}
	@Override
	public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        else{
        PatientPharmacogenomicsCurrentMedicineData guest = (PatientPharmacogenomicsCurrentMedicineData) obj;
        if(patientId==guest.getPatientId() && drug.equals(guest.getDrug()) && geneSymbol.equals(guest.getGeneSymbol())){
        	return true;
        }else{
        	return false;
        }
        }
	}
	
}
