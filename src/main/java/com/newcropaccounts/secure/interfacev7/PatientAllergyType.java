
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.AllergySeverityType;
import com.newcropaccounts.secure.interfacev7.ncstandard.DrugDatabaseType;


/**
 * <p>Java class for PatientAllergyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allergyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergyTypeID" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}DrugDatabaseType"/>
 *         &lt;element name="allergySeverityTypeID" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}AllergySeverityType" minOccurs="0"/>
 *         &lt;element name="allergyComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conceptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="conceptType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyType", propOrder = {
    "allergyID",
    "allergyTypeID",
    "allergySeverityTypeID",
    "allergyComment",
    "conceptID",
    "conceptType"
})
public class PatientAllergyType {

    protected String allergyID;
    @XmlElement(required = true)
    protected DrugDatabaseType allergyTypeID;
    protected AllergySeverityType allergySeverityTypeID;
    protected String allergyComment;
    protected String conceptID;
    protected String conceptType;

    /**
     * Gets the value of the allergyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyID() {
        return allergyID;
    }

    /**
     * Sets the value of the allergyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyID(String value) {
        this.allergyID = value;
    }

    /**
     * Gets the value of the allergyTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link DrugDatabaseType }
     *     
     */
    public DrugDatabaseType getAllergyTypeID() {
        return allergyTypeID;
    }

    /**
     * Sets the value of the allergyTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugDatabaseType }
     *     
     */
    public void setAllergyTypeID(DrugDatabaseType value) {
        this.allergyTypeID = value;
    }

    /**
     * Gets the value of the allergySeverityTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link AllergySeverityType }
     *     
     */
    public AllergySeverityType getAllergySeverityTypeID() {
        return allergySeverityTypeID;
    }

    /**
     * Sets the value of the allergySeverityTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link AllergySeverityType }
     *     
     */
    public void setAllergySeverityTypeID(AllergySeverityType value) {
        this.allergySeverityTypeID = value;
    }

    /**
     * Gets the value of the allergyComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyComment() {
        return allergyComment;
    }

    /**
     * Sets the value of the allergyComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyComment(String value) {
        this.allergyComment = value;
    }

    /**
     * Gets the value of the conceptID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptID() {
        return conceptID;
    }

    /**
     * Sets the value of the conceptID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptID(String value) {
        this.conceptID = value;
    }

    /**
     * Gets the value of the conceptType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptType() {
        return conceptType;
    }

    /**
     * Sets the value of the conceptType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptType(String value) {
        this.conceptType = value;
    }

}
