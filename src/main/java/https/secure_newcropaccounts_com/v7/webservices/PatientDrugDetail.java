
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientDrugDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientDrugDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalAccountID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SiteID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPatientID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Strength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StrengthUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DosageNumberDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DosageForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Route" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DosageFrequencyDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dispense" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TakeAsNeeded" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispenseAsWritten" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Refills" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OrderGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPhysicianID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PhysicianName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateMovedToPreviousMedications" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDrugDetail", propOrder = {
    "accountName",
    "externalAccountID",
    "siteID",
    "fullName",
    "externalPatientID",
    "prescriptionDate",
    "drugID",
    "drugTypeID",
    "drugName",
    "strength",
    "strengthUOM",
    "dosageNumberDescription",
    "dosageForm",
    "route",
    "dosageFrequencyDescription",
    "dispense",
    "takeAsNeeded",
    "dispenseAsWritten",
    "refills",
    "status",
    "prescriptionGuid",
    "orderGuid",
    "prescriptionNotes",
    "externalPhysicianID",
    "physicianName",
    "dateMovedToPreviousMedications"
})
public class PatientDrugDetail {

    @XmlElement(name = "AccountName")
    protected String accountName;
    @XmlElement(name = "ExternalAccountID")
    protected String externalAccountID;
    @XmlElement(name = "SiteID")
    protected String siteID;
    @XmlElement(name = "FullName")
    protected String fullName;
    @XmlElement(name = "ExternalPatientID")
    protected String externalPatientID;
    @XmlElement(name = "PrescriptionDate")
    protected String prescriptionDate;
    @XmlElement(name = "DrugID")
    protected String drugID;
    @XmlElement(name = "DrugTypeID")
    protected String drugTypeID;
    @XmlElement(name = "DrugName")
    protected String drugName;
    @XmlElement(name = "Strength")
    protected String strength;
    @XmlElement(name = "StrengthUOM")
    protected String strengthUOM;
    @XmlElement(name = "DosageNumberDescription")
    protected String dosageNumberDescription;
    @XmlElement(name = "DosageForm")
    protected String dosageForm;
    @XmlElement(name = "Route")
    protected String route;
    @XmlElement(name = "DosageFrequencyDescription")
    protected String dosageFrequencyDescription;
    @XmlElement(name = "Dispense")
    protected String dispense;
    @XmlElement(name = "TakeAsNeeded")
    protected String takeAsNeeded;
    @XmlElement(name = "DispenseAsWritten")
    protected String dispenseAsWritten;
    @XmlElement(name = "Refills")
    protected String refills;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "PrescriptionGuid")
    protected String prescriptionGuid;
    @XmlElement(name = "OrderGuid")
    protected String orderGuid;
    @XmlElement(name = "PrescriptionNotes")
    protected String prescriptionNotes;
    @XmlElement(name = "ExternalPhysicianID")
    protected String externalPhysicianID;
    @XmlElement(name = "PhysicianName")
    protected String physicianName;
    @XmlElement(name = "DateMovedToPreviousMedications")
    protected String dateMovedToPreviousMedications;

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the externalAccountID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalAccountID() {
        return externalAccountID;
    }

    /**
     * Sets the value of the externalAccountID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalAccountID(String value) {
        this.externalAccountID = value;
    }

    /**
     * Gets the value of the siteID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteID() {
        return siteID;
    }

    /**
     * Sets the value of the siteID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteID(String value) {
        this.siteID = value;
    }

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the externalPatientID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPatientID() {
        return externalPatientID;
    }

    /**
     * Sets the value of the externalPatientID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPatientID(String value) {
        this.externalPatientID = value;
    }

    /**
     * Gets the value of the prescriptionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    /**
     * Sets the value of the prescriptionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionDate(String value) {
        this.prescriptionDate = value;
    }

    /**
     * Gets the value of the drugID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugID() {
        return drugID;
    }

    /**
     * Sets the value of the drugID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugID(String value) {
        this.drugID = value;
    }

    /**
     * Gets the value of the drugTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugTypeID() {
        return drugTypeID;
    }

    /**
     * Sets the value of the drugTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugTypeID(String value) {
        this.drugTypeID = value;
    }

    /**
     * Gets the value of the drugName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * Sets the value of the drugName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugName(String value) {
        this.drugName = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrength(String value) {
        this.strength = value;
    }

    /**
     * Gets the value of the strengthUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrengthUOM() {
        return strengthUOM;
    }

    /**
     * Sets the value of the strengthUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrengthUOM(String value) {
        this.strengthUOM = value;
    }

    /**
     * Gets the value of the dosageNumberDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosageNumberDescription() {
        return dosageNumberDescription;
    }

    /**
     * Sets the value of the dosageNumberDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosageNumberDescription(String value) {
        this.dosageNumberDescription = value;
    }

    /**
     * Gets the value of the dosageForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosageForm() {
        return dosageForm;
    }

    /**
     * Sets the value of the dosageForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosageForm(String value) {
        this.dosageForm = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
    }

    /**
     * Gets the value of the dosageFrequencyDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosageFrequencyDescription() {
        return dosageFrequencyDescription;
    }

    /**
     * Sets the value of the dosageFrequencyDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosageFrequencyDescription(String value) {
        this.dosageFrequencyDescription = value;
    }

    /**
     * Gets the value of the dispense property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispense() {
        return dispense;
    }

    /**
     * Sets the value of the dispense property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispense(String value) {
        this.dispense = value;
    }

    /**
     * Gets the value of the takeAsNeeded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTakeAsNeeded() {
        return takeAsNeeded;
    }

    /**
     * Sets the value of the takeAsNeeded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTakeAsNeeded(String value) {
        this.takeAsNeeded = value;
    }

    /**
     * Gets the value of the dispenseAsWritten property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispenseAsWritten() {
        return dispenseAsWritten;
    }

    /**
     * Sets the value of the dispenseAsWritten property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispenseAsWritten(String value) {
        this.dispenseAsWritten = value;
    }

    /**
     * Gets the value of the refills property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefills() {
        return refills;
    }

    /**
     * Sets the value of the refills property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefills(String value) {
        this.refills = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the prescriptionGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionGuid() {
        return prescriptionGuid;
    }

    /**
     * Sets the value of the prescriptionGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionGuid(String value) {
        this.prescriptionGuid = value;
    }

    /**
     * Gets the value of the orderGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderGuid() {
        return orderGuid;
    }

    /**
     * Sets the value of the orderGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderGuid(String value) {
        this.orderGuid = value;
    }

    /**
     * Gets the value of the prescriptionNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionNotes() {
        return prescriptionNotes;
    }

    /**
     * Sets the value of the prescriptionNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionNotes(String value) {
        this.prescriptionNotes = value;
    }

    /**
     * Gets the value of the externalPhysicianID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPhysicianID() {
        return externalPhysicianID;
    }

    /**
     * Sets the value of the externalPhysicianID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPhysicianID(String value) {
        this.externalPhysicianID = value;
    }

    /**
     * Gets the value of the physicianName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicianName() {
        return physicianName;
    }

    /**
     * Sets the value of the physicianName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicianName(String value) {
        this.physicianName = value;
    }

    /**
     * Gets the value of the dateMovedToPreviousMedications property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateMovedToPreviousMedications() {
        return dateMovedToPreviousMedications;
    }

    /**
     * Sets the value of the dateMovedToPreviousMedications property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateMovedToPreviousMedications(String value) {
        this.dateMovedToPreviousMedications = value;
    }

}
