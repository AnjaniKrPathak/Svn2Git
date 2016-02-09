
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NCRenewal complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NCRenewal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Credentials" type="{http://secure.newcropaccounts.com/interfaceV7}CredentialsType" minOccurs="0"/>
 *         &lt;element name="Account" type="{http://secure.newcropaccounts.com/interfaceV7}AccountType" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://secure.newcropaccounts.com/interfaceV7}LocationType" minOccurs="0"/>
 *         &lt;element name="LicensedPrescriber" type="{http://secure.newcropaccounts.com/interfaceV7}LicensedPrescriberType" minOccurs="0"/>
 *         &lt;element name="Staff" type="{http://secure.newcropaccounts.com/interfaceV7}StaffType" minOccurs="0"/>
 *         &lt;element name="Patient" type="{http://secure.newcropaccounts.com/interfaceV7}PatientType" minOccurs="0"/>
 *         &lt;element name="PrescriptionRenewalRequest" type="{http://secure.newcropaccounts.com/interfaceV7}PrescriptionRenewalRequestType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NCRenewal", propOrder = {
    "credentials",
    "account",
    "location",
    "licensedPrescriber",
    "staff",
    "patient",
    "prescriptionRenewalRequest"
})
public class NCRenewal {

    @XmlElement(name = "Credentials")
    protected CredentialsType credentials;
    @XmlElement(name = "Account")
    protected AccountType account;
    @XmlElement(name = "Location")
    protected LocationType location;
    @XmlElement(name = "LicensedPrescriber")
    protected LicensedPrescriberType licensedPrescriber;
    @XmlElement(name = "Staff")
    protected StaffType staff;
    @XmlElement(name = "Patient")
    protected PatientType patient;
    @XmlElement(name = "PrescriptionRenewalRequest")
    protected PrescriptionRenewalRequestType prescriptionRenewalRequest;

    /**
     * Gets the value of the credentials property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialsType }
     *     
     */
    public CredentialsType getCredentials() {
        return credentials;
    }

    /**
     * Sets the value of the credentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialsType }
     *     
     */
    public void setCredentials(CredentialsType value) {
        this.credentials = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link AccountType }
     *     
     */
    public AccountType getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountType }
     *     
     */
    public void setAccount(AccountType value) {
        this.account = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link LocationType }
     *     
     */
    public LocationType getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocationType }
     *     
     */
    public void setLocation(LocationType value) {
        this.location = value;
    }

    /**
     * Gets the value of the licensedPrescriber property.
     * 
     * @return
     *     possible object is
     *     {@link LicensedPrescriberType }
     *     
     */
    public LicensedPrescriberType getLicensedPrescriber() {
        return licensedPrescriber;
    }

    /**
     * Sets the value of the licensedPrescriber property.
     * 
     * @param value
     *     allowed object is
     *     {@link LicensedPrescriberType }
     *     
     */
    public void setLicensedPrescriber(LicensedPrescriberType value) {
        this.licensedPrescriber = value;
    }

    /**
     * Gets the value of the staff property.
     * 
     * @return
     *     possible object is
     *     {@link StaffType }
     *     
     */
    public StaffType getStaff() {
        return staff;
    }

    /**
     * Sets the value of the staff property.
     * 
     * @param value
     *     allowed object is
     *     {@link StaffType }
     *     
     */
    public void setStaff(StaffType value) {
        this.staff = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     * @return
     *     possible object is
     *     {@link PatientType }
     *     
     */
    public PatientType getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientType }
     *     
     */
    public void setPatient(PatientType value) {
        this.patient = value;
    }

    /**
     * Gets the value of the prescriptionRenewalRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriptionRenewalRequestType }
     *     
     */
    public PrescriptionRenewalRequestType getPrescriptionRenewalRequest() {
        return prescriptionRenewalRequest;
    }

    /**
     * Sets the value of the prescriptionRenewalRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriptionRenewalRequestType }
     *     
     */
    public void setPrescriptionRenewalRequest(PrescriptionRenewalRequestType value) {
        this.prescriptionRenewalRequest = value;
    }

}
