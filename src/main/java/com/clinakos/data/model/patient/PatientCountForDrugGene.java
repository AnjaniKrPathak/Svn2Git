package com.clinakos.data.model.patient;

public class PatientCountForDrugGene {
	
	private int id;
	private String medicineName;
	private String geneSymbol;
	private int patientCount;
	private int providerId;
	private String attentionRating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getGeneSymbol() {
		return geneSymbol;
	}
	public void setGeneSymbol(String geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	public int getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((geneSymbol == null) ? 0 : geneSymbol.hashCode());
		result = prime * result
				+ ((medicineName == null) ? 0 : medicineName.hashCode());
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
		PatientCountForDrugGene other = (PatientCountForDrugGene) obj;
		if (geneSymbol == null) {
			if (other.geneSymbol != null)
				return false;
		} else if (!geneSymbol.equals(other.geneSymbol))
			return false;
		if (medicineName == null) {
			if (other.medicineName != null)
				return false;
		} else if (!medicineName.equalsIgnoreCase(other.medicineName))
			return false;
		return true;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public String getAttentionRating() {
		return attentionRating;
	}
	public void setAttentionRating(String attentionRating) {
		this.attentionRating = attentionRating;
	}
	
	

}
