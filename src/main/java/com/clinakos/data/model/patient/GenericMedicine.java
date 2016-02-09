/**
 * 
 */
package com.clinakos.data.model.patient;

/**
 * @author li0008
 *
 */
public class GenericMedicine {
	
	private int id;
	private String genericMedicine;
	private String medicineImageName;
	
	
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
	 * @return the genericMedicine
	 */
	public String getGenericMedicine() {
		return genericMedicine;
	}
	/**
	 * @param genericMedicine the genericMedicine to set
	 */
	public void setGenericMedicine(String genericMedicine) {
		this.genericMedicine = genericMedicine;
	}
	/**
	 * @return the medicineImageName
	 */
	public String getMedicineImageName() {
		return medicineImageName;
	}
	/**
	 * @param medicineImageName the medicineImageName to set
	 */
	public void setMedicineImageName(String medicineImageName) {
		this.medicineImageName = medicineImageName;
	}
	
	
	
}
