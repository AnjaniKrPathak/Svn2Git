/**
 * 
 */
package com.clinakos.data.model.core;



/**
 * @author IDC-0004
 *
 */
public class BatchDiseaseInteraction {
		private int id;
		private double drugId;
		private double drugNameId;
	    private String drugName;
	    private String icd9;
		private String interactionText;
		private String severityLevel;
		private int interactionType;
		
		private String  directCondition;
		private String  relatedCondition;
		private String severityLevelText;
		private String severityLevelShortText;
		private String diseaseRelation;
		private String diseaseRelationText;
		
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
		public String getDrugName() {
			return drugName;
		}
		public void setDrugName(String drugName) {
			this.drugName = drugName;
		}
		public String getIcd9() {
			return icd9;
		}
		public void setIcd9(String icd9) {
			this.icd9 = icd9;
		}
		public String getInteractionText() {
			return interactionText;
		}
		public void setInteractionText(String interactionText) {
			this.interactionText = interactionText;
		}
		public String getSeverityLevel() {
			return severityLevel;
		}
		public void setSeverityLevel(String severityLevel) {
			this.severityLevel = severityLevel;
		}
		public int getInteractionType() {
			return interactionType;
		}
		public void setInteractionType(int interactionType) {
			this.interactionType = interactionType;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDirectCondition() {
			return directCondition;
		}
		public void setDirectCondition(String directCondition) {
			this.directCondition = directCondition;
		}
		public String getRelatedCondition() {
			return relatedCondition;
		}
		public void setRelatedCondition(String relatedCondition) {
			this.relatedCondition = relatedCondition;
		}
		public String getSeverityLevelText() {
			return severityLevelText;
		}
		public void setSeverityLevelText(String severityLevelText) {
			this.severityLevelText = severityLevelText;
		}
		public String getSeverityLevelShortText() {
			return severityLevelShortText;
		}
		public void setSeverityLevelShortText(String severityLevelShortText) {
			this.severityLevelShortText = severityLevelShortText;
		}
		public String getDiseaseRelation() {
			return diseaseRelation;
		}
		public void setDiseaseRelation(String diseaseRelation) {
			this.diseaseRelation = diseaseRelation;
		}
		public String getDiseaseRelationText() {
			return diseaseRelationText;
		}
		public void setDiseaseRelationText(String diseaseRelationText) {
			this.diseaseRelationText = diseaseRelationText;
		}
	   
	  
}
