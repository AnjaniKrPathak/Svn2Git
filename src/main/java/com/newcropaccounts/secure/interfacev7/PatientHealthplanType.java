
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.HealthplanType;


/**
 * <p>Java class for PatientHealthplanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientHealthplanType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="healthplanID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanTypeID" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}HealthplanType"/>
 *         &lt;element name="group" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientHealthplanType", propOrder = {
    "healthplanID",
    "healthplanTypeID",
    "group"
})
public class PatientHealthplanType {

    protected String healthplanID;
    @XmlElement(required = true)
    protected HealthplanType healthplanTypeID;
    protected String group;

    /**
     * Gets the value of the healthplanID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanID() {
        return healthplanID;
    }

    /**
     * Sets the value of the healthplanID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanID(String value) {
        this.healthplanID = value;
    }

    /**
     * Gets the value of the healthplanTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link HealthplanType }
     *     
     */
    public HealthplanType getHealthplanTypeID() {
        return healthplanTypeID;
    }

    /**
     * Sets the value of the healthplanTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link HealthplanType }
     *     
     */
    public void setHealthplanTypeID(HealthplanType value) {
        this.healthplanTypeID = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

}
