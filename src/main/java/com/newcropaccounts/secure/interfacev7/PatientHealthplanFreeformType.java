
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientHealthplanFreeformType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientHealthplanFreeformType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="healthplanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientHealthplanFreeformType", propOrder = {
    "healthplanName"
})
public class PatientHealthplanFreeformType {

    protected String healthplanName;

    /**
     * Gets the value of the healthplanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanName() {
        return healthplanName;
    }

    /**
     * Sets the value of the healthplanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanName(String value) {
        this.healthplanName = value;
    }

}
