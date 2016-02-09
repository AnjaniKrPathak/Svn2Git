/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class DrugAllergyDetailData {
	
		private int id;
		private String interactionText;  
		private String drug;
		private String drugID;
		private String drugType;
		private String compositeAllergyId;
		private String source;
		private String conceptId;
		private String conceptType;
		private String description;
		private String performance;
		private boolean checkBox;
		private boolean status;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getInteractionText() {
			return interactionText;
		}
		public void setInteractionText(String interactionText) {
			this.interactionText = interactionText;
		}
		public String getDrug() {
			return drug;
		}
		public void setDrug(String drug) {
			this.drug = drug;
		}
		public String getDrugID() {
			return drugID;
		}
		public void setDrugID(String drugID) {
			this.drugID = drugID;
		}
		public String getDrugType() {
			return drugType;
		}
		public void setDrugType(String drugType) {
			this.drugType = drugType;
		}
		public String getCompositeAllergyId() {
			return compositeAllergyId;
		}
		public void setCompositeAllergyId(String compositeAllergyId) {
			this.compositeAllergyId = compositeAllergyId;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getConceptId() {
			return conceptId;
		}
		public void setConceptId(String conceptId) {
			this.conceptId = conceptId;
		}
		public String getConceptType() {
			return conceptType;
		}
		public void setConceptType(String conceptType) {
			this.conceptType = conceptType;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getPerformance() {
			return performance;
		}
		public void setPerformance(String performance) {
			this.performance = performance;
		}
		public boolean isCheckBox() {
			return checkBox;
		}
		public void setCheckBox(boolean checkBox) {
			this.checkBox = checkBox;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		
		
}
