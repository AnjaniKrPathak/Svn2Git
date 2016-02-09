
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.AllergySeverityType;


/**
 * <p>Java class for PatientAllergyFreeformType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyFreeformType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allergyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allergySeverityTypeID" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}AllergySeverityType" minOccurs="0"/>
 *         &lt;element name="allergyComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "PatientAllergyFreeformType", propOrder = {
    "allergyName",
    "allergySeverityTypeID",
    "allergyComment"
})
public class PatientAllergyFreeformType {

    protected String allergyName;
    protected AllergySeverityType allergySeverityTypeID;
    protected String allergyComment;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the allergyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyName() {
        return allergyName;
    }

    /**
     * Sets the value of the allergyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyName(String value) {
        this.allergyName = value;
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
