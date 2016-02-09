
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.DrugSubstitutionType;


/**
 * <p>Java class for PrescriptionRenewalRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrescriptionRenewalRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pharmacyIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugNDC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drug" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dispenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dosage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastFillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="writtenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="daysSupply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="substitution" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}DrugSubstitutionType"/>
 *         &lt;element name="refills" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pharmacistMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "PrescriptionRenewalRequestType", propOrder = {
    "pharmacyIdentifier",
    "drugNDC",
    "drug",
    "dispenseNumber",
    "dosage",
    "lastFillDate",
    "writtenDate",
    "daysSupply",
    "substitution",
    "refills",
    "pharmacistMessage"
})
public class PrescriptionRenewalRequestType {

    protected String pharmacyIdentifier;
    protected String drugNDC;
    protected String drug;
    protected String dispenseNumber;
    protected String dosage;
    protected String lastFillDate;
    protected String writtenDate;
    protected String daysSupply;
    @XmlElement(required = true)
    protected DrugSubstitutionType substitution;
    protected String refills;
    protected String pharmacistMessage;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the pharmacyIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyIdentifier() {
        return pharmacyIdentifier;
    }

    /**
     * Sets the value of the pharmacyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyIdentifier(String value) {
        this.pharmacyIdentifier = value;
    }

    /**
     * Gets the value of the drugNDC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugNDC() {
        return drugNDC;
    }

    /**
     * Sets the value of the drugNDC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugNDC(String value) {
        this.drugNDC = value;
    }

    /**
     * Gets the value of the drug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug() {
        return drug;
    }

    /**
     * Sets the value of the drug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug(String value) {
        this.drug = value;
    }

    /**
     * Gets the value of the dispenseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispenseNumber() {
        return dispenseNumber;
    }

    /**
     * Sets the value of the dispenseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispenseNumber(String value) {
        this.dispenseNumber = value;
    }

    /**
     * Gets the value of the dosage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the value of the dosage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosage(String value) {
        this.dosage = value;
    }

    /**
     * Gets the value of the lastFillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFillDate() {
        return lastFillDate;
    }

    /**
     * Sets the value of the lastFillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFillDate(String value) {
        this.lastFillDate = value;
    }

    /**
     * Gets the value of the writtenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrittenDate() {
        return writtenDate;
    }

    /**
     * Sets the value of the writtenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrittenDate(String value) {
        this.writtenDate = value;
    }

    /**
     * Gets the value of the daysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaysSupply() {
        return daysSupply;
    }

    /**
     * Sets the value of the daysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysSupply(String value) {
        this.daysSupply = value;
    }

    /**
     * Gets the value of the substitution property.
     * 
     * @return
     *     possible object is
     *     {@link DrugSubstitutionType }
     *     
     */
    public DrugSubstitutionType getSubstitution() {
        return substitution;
    }

    /**
     * Sets the value of the substitution property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugSubstitutionType }
     *     
     */
    public void setSubstitution(DrugSubstitutionType value) {
        this.substitution = value;
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
     * Gets the value of the pharmacistMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacistMessage() {
        return pharmacistMessage;
    }

    /**
     * Sets the value of the pharmacistMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacistMessage(String value) {
        this.pharmacistMessage = value;
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
