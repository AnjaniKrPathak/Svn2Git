
package com.newcropaccounts.secure.interfacev7;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientName" type="{http://secure.newcropaccounts.com/interfaceV7}PersonNameType" minOccurs="0"/>
 *         &lt;element name="medicalRecordNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="socialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddress" type="{http://secure.newcropaccounts.com/interfaceV7}AddressOptionalType" minOccurs="0"/>
 *         &lt;element name="PatientContact" type="{http://secure.newcropaccounts.com/interfaceV7}ContactType" minOccurs="0"/>
 *         &lt;element name="PatientCharacteristics" type="{http://secure.newcropaccounts.com/interfaceV7}PatientCharacteristicsType" minOccurs="0"/>
 *         &lt;element name="PatientAllergies" type="{http://secure.newcropaccounts.com/interfaceV7}PatientAllergyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PatientHealthplans" type="{http://secure.newcropaccounts.com/interfaceV7}PatientHealthplanType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PatientDiagnosis" type="{http://secure.newcropaccounts.com/interfaceV7}PatientDiagnosisType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PatientDiagnosisSearch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientIdentifier" type="{http://secure.newcropaccounts.com/interfaceV7}PatientIdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PatientFreeformHealthplans" type="{http://secure.newcropaccounts.com/interfaceV7}PatientHealthplanFreeformType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EpisodeIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EncounterIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientFreeformAllergy" type="{http://secure.newcropaccounts.com/interfaceV7}PatientAllergyFreeformType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PatientFormulary" type="{http://secure.newcropaccounts.com/interfaceV7}PatientFormularyType" minOccurs="0"/>
 *         &lt;element name="PatientPharmacies" type="{http://secure.newcropaccounts.com/interfaceV7}PatientPharmacyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DrugSetID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientType", propOrder = {
    "patientName",
    "medicalRecordNumber",
    "socialSecurityNumber",
    "memo",
    "patientAddress",
    "patientContact",
    "patientCharacteristics",
    "patientAllergies",
    "patientHealthplans",
    "patientDiagnosis",
    "patientDiagnosisSearch",
    "patientIdentifier",
    "patientFreeformHealthplans",
    "episodeIdentifier",
    "encounterIdentifier",
    "patientFreeformAllergy",
    "patientFormulary",
    "patientPharmacies",
    "drugSetID"
})
public class PatientType {

    @XmlElement(name = "PatientName")
    protected PersonNameType patientName;
    protected String medicalRecordNumber;
    protected String socialSecurityNumber;
    protected String memo;
    @XmlElement(name = "PatientAddress")
    protected AddressOptionalType patientAddress;
    @XmlElement(name = "PatientContact")
    protected ContactType patientContact;
    @XmlElement(name = "PatientCharacteristics")
    protected PatientCharacteristicsType patientCharacteristics;
    @XmlElement(name = "PatientAllergies")
    protected List<PatientAllergyType> patientAllergies;
    @XmlElement(name = "PatientHealthplans")
    protected List<PatientHealthplanType> patientHealthplans;
    @XmlElement(name = "PatientDiagnosis")
    protected List<PatientDiagnosisType> patientDiagnosis;
    @XmlElement(name = "PatientDiagnosisSearch")
    protected String patientDiagnosisSearch;
    @XmlElement(name = "PatientIdentifier")
    protected List<PatientIdentifierType> patientIdentifier;
    @XmlElement(name = "PatientFreeformHealthplans")
    protected List<PatientHealthplanFreeformType> patientFreeformHealthplans;
    @XmlElement(name = "EpisodeIdentifier")
    protected String episodeIdentifier;
    @XmlElement(name = "EncounterIdentifier")
    protected String encounterIdentifier;
    @XmlElement(name = "PatientFreeformAllergy")
    protected List<PatientAllergyFreeformType> patientFreeformAllergy;
    @XmlElement(name = "PatientFormulary")
    protected PatientFormularyType patientFormulary;
    @XmlElement(name = "PatientPharmacies")
    protected List<PatientPharmacyType> patientPharmacies;
    @XmlElement(name = "DrugSetID")
    protected String drugSetID;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the patientName property.
     * 
     * @return
     *     possible object is
     *     {@link PersonNameType }
     *     
     */
    public PersonNameType getPatientName() {
        return patientName;
    }

    /**
     * Sets the value of the patientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNameType }
     *     
     */
    public void setPatientName(PersonNameType value) {
        this.patientName = value;
    }

    /**
     * Gets the value of the medicalRecordNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }

    /**
     * Sets the value of the medicalRecordNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedicalRecordNumber(String value) {
        this.medicalRecordNumber = value;
    }

    /**
     * Gets the value of the socialSecurityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the value of the socialSecurityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNumber(String value) {
        this.socialSecurityNumber = value;
    }

    /**
     * Gets the value of the memo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemo() {
        return memo;
    }

    /**
     * Sets the value of the memo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemo(String value) {
        this.memo = value;
    }

    /**
     * Gets the value of the patientAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressOptionalType }
     *     
     */
    public AddressOptionalType getPatientAddress() {
        return patientAddress;
    }

    /**
     * Sets the value of the patientAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressOptionalType }
     *     
     */
    public void setPatientAddress(AddressOptionalType value) {
        this.patientAddress = value;
    }

    /**
     * Gets the value of the patientContact property.
     * 
     * @return
     *     possible object is
     *     {@link ContactType }
     *     
     */
    public ContactType getPatientContact() {
        return patientContact;
    }

    /**
     * Sets the value of the patientContact property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactType }
     *     
     */
    public void setPatientContact(ContactType value) {
        this.patientContact = value;
    }

    /**
     * Gets the value of the patientCharacteristics property.
     * 
     * @return
     *     possible object is
     *     {@link PatientCharacteristicsType }
     *     
     */
    public PatientCharacteristicsType getPatientCharacteristics() {
        return patientCharacteristics;
    }

    /**
     * Sets the value of the patientCharacteristics property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientCharacteristicsType }
     *     
     */
    public void setPatientCharacteristics(PatientCharacteristicsType value) {
        this.patientCharacteristics = value;
    }

    /**
     * Gets the value of the patientAllergies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientAllergies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientAllergies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientAllergyType }
     * 
     * 
     */
    public List<PatientAllergyType> getPatientAllergies() {
        if (patientAllergies == null) {
            patientAllergies = new ArrayList<PatientAllergyType>();
        }
        return this.patientAllergies;
    }

    /**
     * Gets the value of the patientHealthplans property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientHealthplans property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientHealthplans().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientHealthplanType }
     * 
     * 
     */
    public List<PatientHealthplanType> getPatientHealthplans() {
        if (patientHealthplans == null) {
            patientHealthplans = new ArrayList<PatientHealthplanType>();
        }
        return this.patientHealthplans;
    }

    /**
     * Gets the value of the patientDiagnosis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientDiagnosis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientDiagnosis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientDiagnosisType }
     * 
     * 
     */
    public List<PatientDiagnosisType> getPatientDiagnosis() {
        if (patientDiagnosis == null) {
            patientDiagnosis = new ArrayList<PatientDiagnosisType>();
        }
        return this.patientDiagnosis;
    }

    /**
     * Gets the value of the patientDiagnosisSearch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientDiagnosisSearch() {
        return patientDiagnosisSearch;
    }

    /**
     * Sets the value of the patientDiagnosisSearch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientDiagnosisSearch(String value) {
        this.patientDiagnosisSearch = value;
    }

    /**
     * Gets the value of the patientIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientIdentifierType }
     * 
     * 
     */
    public List<PatientIdentifierType> getPatientIdentifier() {
        if (patientIdentifier == null) {
            patientIdentifier = new ArrayList<PatientIdentifierType>();
        }
        return this.patientIdentifier;
    }

    /**
     * Gets the value of the patientFreeformHealthplans property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientFreeformHealthplans property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientFreeformHealthplans().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientHealthplanFreeformType }
     * 
     * 
     */
    public List<PatientHealthplanFreeformType> getPatientFreeformHealthplans() {
        if (patientFreeformHealthplans == null) {
            patientFreeformHealthplans = new ArrayList<PatientHealthplanFreeformType>();
        }
        return this.patientFreeformHealthplans;
    }

    /**
     * Gets the value of the episodeIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEpisodeIdentifier() {
        return episodeIdentifier;
    }

    /**
     * Sets the value of the episodeIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEpisodeIdentifier(String value) {
        this.episodeIdentifier = value;
    }

    /**
     * Gets the value of the encounterIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncounterIdentifier() {
        return encounterIdentifier;
    }

    /**
     * Sets the value of the encounterIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncounterIdentifier(String value) {
        this.encounterIdentifier = value;
    }

    /**
     * Gets the value of the patientFreeformAllergy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientFreeformAllergy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientFreeformAllergy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientAllergyFreeformType }
     * 
     * 
     */
    public List<PatientAllergyFreeformType> getPatientFreeformAllergy() {
        if (patientFreeformAllergy == null) {
            patientFreeformAllergy = new ArrayList<PatientAllergyFreeformType>();
        }
        return this.patientFreeformAllergy;
    }

    /**
     * Gets the value of the patientFormulary property.
     * 
     * @return
     *     possible object is
     *     {@link PatientFormularyType }
     *     
     */
    public PatientFormularyType getPatientFormulary() {
        return patientFormulary;
    }

    /**
     * Sets the value of the patientFormulary property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientFormularyType }
     *     
     */
    public void setPatientFormulary(PatientFormularyType value) {
        this.patientFormulary = value;
    }

    /**
     * Gets the value of the patientPharmacies property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientPharmacies property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientPharmacies().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientPharmacyType }
     * 
     * 
     */
    public List<PatientPharmacyType> getPatientPharmacies() {
        if (patientPharmacies == null) {
            patientPharmacies = new ArrayList<PatientPharmacyType>();
        }
        return this.patientPharmacies;
    }

    /**
     * Gets the value of the drugSetID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugSetID() {
        return drugSetID;
    }

    /**
     * Sets the value of the drugSetID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugSetID(String value) {
        this.drugSetID = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

}
