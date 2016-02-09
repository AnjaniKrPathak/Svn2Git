/**
 * 
 */
package com.clinakos.data.model.patient;





/**
 * @author li0008
 *
 */
public class MasterDrugDrugInteraction {
	
	private int id;
	private String drug1ID;
	private String drug2ID;
	private String drug1Name;
	private String drug2Name;
	private String clinicalEffects;
	private String severityLevels;
	private String mechanismOfAction;  
	private String discussion;   
	private String patientManagement; 
	private String predisposingFactors;   
	private String references;   
	private String monographTitle;   
	private String drug1Type;   
	private String drug2Type; 
	private String performance;
	
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
	 * @return the drug1ID
	 */
	public String getDrug1ID() {
		return drug1ID;
	}
	/**
	 * @param drug1id the drug1ID to set
	 */
	public void setDrug1ID(String drug1id) {
		drug1ID = drug1id;
	}
	/**
	 * @return the drug2ID
	 */
	public String getDrug2ID() {
		return drug2ID;
	}
	/**
	 * @param drug2id the drug2ID to set
	 */
	public void setDrug2ID(String drug2id) {
		drug2ID = drug2id;
	}
	/**
	 * @return the drug1Name
	 */
	public String getDrug1Name() {
		return drug1Name;
	}
	/**
	 * @param drug1Name the drug1Name to set
	 */
	public void setDrug1Name(String drug1Name) {
		this.drug1Name = drug1Name;
	}
	/**
	 * @return the drug2Name
	 */
	public String getDrug2Name() {
		return drug2Name;
	}
	/**
	 * @param drug2Name the drug2Name to set
	 */
	public void setDrug2Name(String drug2Name) {
		this.drug2Name = drug2Name;
	}
	/**
	 * @return the clinicalEffects
	 */
	public String getClinicalEffects() {
		return clinicalEffects;
	}
	/**
	 * @param clinicalEffects the clinicalEffects to set
	 */
	public void setClinicalEffects(String clinicalEffects) {
		this.clinicalEffects = clinicalEffects;
	}
	/**
	 * @return the severityLevels
	 */
	public String getSeverityLevels() {
		return severityLevels;
	}
	/**
	 * @param severityLevels the severityLevels to set
	 */
	public void setSeverityLevels(String severityLevels) {
		this.severityLevels = severityLevels;
	}

	public String getMechanismOfAction() {
		return mechanismOfAction;
	}
	public void setMechanismOfAction(String mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}
	public String getDiscussion() {
		return discussion;
	}
	public void setDiscussion(String discussion) {
		this.discussion = discussion;
	}
	public String getPatientManagement() {
		return patientManagement;
	}
	public void setPatientManagement(String patientManagement) {
		this.patientManagement = patientManagement;
	}
	public String getPredisposingFactors() {
		return predisposingFactors;
	}
	public void setPredisposingFactors(String predisposingFactors) {
		this.predisposingFactors = predisposingFactors;
	}
	public String getReferences() {
		return references;
	}
	public void setReferences(String references) {
		this.references = references;
	}
	public String getMonographTitle() {
		return monographTitle;
	}
	public void setMonographTitle(String monographTitle) {
		this.monographTitle = monographTitle;
	}
	public String getDrug1Type() {
		return drug1Type;
	}
	public void setDrug1Type(String drug1Type) {
		this.drug1Type = drug1Type;
	}
	public String getDrug2Type() {
		return drug2Type;
	}
	public void setDrug2Type(String drug2Type) {
		this.drug2Type = drug2Type;
	}
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	 @Override
		public boolean equals(Object obj) {
			
			if (obj == this) {
	            return true;
	        }
	        if (obj == null || obj.getClass() != this.getClass()) {
	            return false;
	        }

	        MasterDrugDrugInteraction guest = (MasterDrugDrugInteraction) obj;
	        if((drug1ID.equals(guest.getDrug1ID())||drug1ID.equals(guest.getDrug2ID())&&
	        		(drug2ID.equals(guest.getDrug2ID())||drug2ID.equals(guest.getDrug1ID())))){
	        	return true;
	        }else{
	        	return false;
	        }


		}
	

}
