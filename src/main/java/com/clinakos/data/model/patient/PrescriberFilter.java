package com.clinakos.data.model.patient;

public class PrescriberFilter {
	
	private String prescriberName;
	private int precriberId;
	private double drugId;
	private String drugName;
	
	public String getPrescriberName() {
		return prescriberName;
	}
	public void setPrescriberName(String prescriberName) {
		this.prescriberName = prescriberName;
	}
	public int getPrecriberId() {
		return precriberId;
	}
	public void setPrecriberId(int precriberId) {
		this.precriberId = precriberId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((prescriberName == null) ? 0 : prescriberName.hashCode());
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
		PrescriberFilter other = (PrescriberFilter) obj;
		if (prescriberName == null) {
			if (other.prescriberName != null)
				return false;
		} else if (!prescriberName.equals(other.prescriberName))
			return false;
		return true;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	

}
