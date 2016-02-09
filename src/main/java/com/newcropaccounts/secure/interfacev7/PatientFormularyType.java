
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.DrugDatabaseType;


/**
 * <p>Java class for PatientFormularyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientFormularyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eligibilityGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eligibilityIndex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugIdentifierType" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}DrugDatabaseType" minOccurs="0"/>
 *         &lt;element name="statusDisplayed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientFormularyType", propOrder = {
    "eligibilityGuid",
    "eligibilityIndex",
    "drugIdentifier",
    "drugIdentifierType",
    "statusDisplayed"
})
public class PatientFormularyType {

    protected String eligibilityGuid;
    protected String eligibilityIndex;
    protected String drugIdentifier;
    protected DrugDatabaseType drugIdentifierType;
    protected String statusDisplayed;

    /**
     * Gets the value of the eligibilityGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibilityGuid() {
        return eligibilityGuid;
    }

    /**
     * Sets the value of the eligibilityGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibilityGuid(String value) {
        this.eligibilityGuid = value;
    }

    /**
     * Gets the value of the eligibilityIndex property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibilityIndex() {
        return eligibilityIndex;
    }

    /**
     * Sets the value of the eligibilityIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibilityIndex(String value) {
        this.eligibilityIndex = value;
    }

    /**
     * Gets the value of the drugIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugIdentifier() {
        return drugIdentifier;
    }

    /**
     * Sets the value of the drugIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugIdentifier(String value) {
        this.drugIdentifier = value;
    }

    /**
     * Gets the value of the drugIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link DrugDatabaseType }
     *     
     */
    public DrugDatabaseType getDrugIdentifierType() {
        return drugIdentifierType;
    }

    /**
     * Sets the value of the drugIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugDatabaseType }
     *     
     */
    public void setDrugIdentifierType(DrugDatabaseType value) {
        this.drugIdentifierType = value;
    }

    /**
     * Gets the value of the statusDisplayed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDisplayed() {
        return statusDisplayed;
    }

    /**
     * Sets the value of the statusDisplayed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDisplayed(String value) {
        this.statusDisplayed = value;
    }

}
