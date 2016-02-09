package com.clinakos.data.model.patient;

import java.util.Comparator;
import java.util.Date;

public class PharmacyDetail {
	
	private int id;
	//private int patientMedicationId;
	private int patientId;
	//private int doctorId;
	private String drugInfo;
	private int quantity;
	private Date dateOfPurchase;
	private double  ndcCode;
	private double drugId;
	private double drugNameId;
	
	private String pharmacyName;
	private String pharmacyContactNumber;
	//private String drugHistoryTransactionGuid;
	
	private String source;
	private String healthplanName;
	//private String daysSupply;
	
	private String doctorName;
	private String doctorContactNumber;
	private String dosage;
	private Date downloaddate;
	
	private String daysOfSupply;
	//private String HealthplanName
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
	public String getDrugInfo() {
		return drugInfo;
	}
	public void setDrugInfo(String drugInfo) {
		this.drugInfo = drugInfo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public double getNdcCode() {
		return ndcCode;
	}
	public void setNdcCode(double ndcCode) {
		this.ndcCode = ndcCode;
	}
	public double getDrugId() {
		return drugId;
	}
	public void setDrugId(double drugId) {
		this.drugId = drugId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	public String getPharmacyContactNumber() {
		return pharmacyContactNumber;
	}
	public void setPharmacyContactNumber(String pharmacyContactNumber) {
		this.pharmacyContactNumber = pharmacyContactNumber;
	}
	/*public String getDrugHistoryTransactionGuid() {
		return drugHistoryTransactionGuid;
	}
	public void setDrugHistoryTransactionGuid(String drugHistoryTransactionGuid) {
		this.drugHistoryTransactionGuid = drugHistoryTransactionGuid;
	}*/
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getHealthplanName() {
		return healthplanName;
	}
	public void setHealthplanName(String healthplanName) {
		this.healthplanName = healthplanName;
	}
	/*public String getDaysSupply() {
		return daysSupply;
	}
	public void setDaysSupply(String daysSupply) {
		this.daysSupply = daysSupply;
	}*/
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorContactNumber() {
		return doctorContactNumber;
	}
	public void setDoctorContactNumber(String doctorContactNumber) {
		this.doctorContactNumber = doctorContactNumber;
	}
	/**
	 * @return the downloaddate
	 */
	public Date getDownloaddate() {
		return downloaddate;
	}
	/**
	 * @param downloaddate the downloaddate to set
	 */
	public void setDownloaddate(Date downloaddate) {
		this.downloaddate = downloaddate;
	}
	/**
	 * @return the dosage
	 */
	public String getDosage() {
		return dosage;
	}
	/**
	 * @param dosage the dosage to set
	 */
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	/**
	 * @return the drugNameId
	 */
	public double getDrugNameId() {
		return drugNameId;
	}
	/**
	 * @param drugNameId the drugNameId to set
	 */
	public void setDrugNameId(double drugNameId) {
		this.drugNameId = drugNameId;
	}
	public String getDaysOfSupply() {
		return daysOfSupply;
	}
	public void setDaysOfSupply(String daysOfSupply) {
		this.daysOfSupply = daysOfSupply;
	}
	
	/**
	 * To sort the Pharamacy detail Objects in ascending order based on date of purchase
	 * 
	 * @author Nagaraj
	 * 
	 */
	public static Comparator<PharmacyDetail>oldDatePurchasePharamacyComparator=new Comparator<PharmacyDetail>() {

		@Override
		public int compare(PharmacyDetail p1, PharmacyDetail p2) {
			
			return p1.getDateOfPurchase().compareTo(p2.getDateOfPurchase());
		}
	};
	
	
	
	

}
