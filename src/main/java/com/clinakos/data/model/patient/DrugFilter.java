package com.clinakos.data.model.patient;

public class DrugFilter {

	private String drugName;
	private double drugId;
	private String prescriberName;
	
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((drugName == null) ? 0 : drugName.hashCode());
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
		DrugFilter other = (DrugFilter) obj;
		if (drugName == null) {
			if (other.drugName != null)
				return false;
		} else if (!drugName.equalsIgnoreCase(other.drugName))
			return false;
		return true;
	}
	public String getPrescriberName() {
		return prescriberName;
	}
	public void setPrescriberName(String prescriberName) {
		this.prescriberName = prescriberName;
	}
	
	
}
