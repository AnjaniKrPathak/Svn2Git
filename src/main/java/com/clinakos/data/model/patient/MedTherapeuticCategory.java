/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author LI-M-0004
 *
 */
public class MedTherapeuticCategory {

	private double drugId;
	private String therapeuticCategoryLabel;
	private String therapeuticCategoryValue;
	
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public String getTherapeuticCategoryLabel() {
		return therapeuticCategoryLabel;
	}
	public void setTherapeuticCategoryLabel(String therapeuticCategoryLabel) {
		this.therapeuticCategoryLabel = therapeuticCategoryLabel;
	}
	public String getTherapeuticCategoryValue() {
		return therapeuticCategoryValue;
	}
	public void setTherapeuticCategoryValue(String therapeuticCategoryValue) {
		this.therapeuticCategoryValue = therapeuticCategoryValue;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(drugId);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime
				* result
				+ ((therapeuticCategoryValue == null) ? 0
						: therapeuticCategoryValue.hashCode());
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
		MedTherapeuticCategory other = (MedTherapeuticCategory) obj;
		if (therapeuticCategoryValue.equalsIgnoreCase(other.therapeuticCategoryValue))
		{
			return true;
		}else{
			return false;
		}
	
	}
	
	
	
}
