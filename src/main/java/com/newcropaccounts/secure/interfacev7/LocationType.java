
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LocationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LocationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationAddress" type="{http://secure.newcropaccounts.com/interfaceV7}AddressType" minOccurs="0"/>
 *         &lt;element name="primaryPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryFaxNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pharmacyContactNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "LocationType", propOrder = {
    "locationName",
    "locationShortName",
    "locationAddress",
    "primaryPhoneNumber",
    "primaryFaxNumber",
    "pharmacyContactNumber"
})
public class LocationType {

    protected String locationName;
    protected String locationShortName;
    @XmlElement(name = "LocationAddress")
    protected AddressType locationAddress;
    protected String primaryPhoneNumber;
    protected String primaryFaxNumber;
    protected String pharmacyContactNumber;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the locationShortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationShortName() {
        return locationShortName;
    }

    /**
     * Sets the value of the locationShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationShortName(String value) {
        this.locationShortName = value;
    }

    /**
     * Gets the value of the locationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getLocationAddress() {
        return locationAddress;
    }

    /**
     * Sets the value of the locationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setLocationAddress(AddressType value) {
        this.locationAddress = value;
    }

    /**
     * Gets the value of the primaryPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * Sets the value of the primaryPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPhoneNumber(String value) {
        this.primaryPhoneNumber = value;
    }

    /**
     * Gets the value of the primaryFaxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryFaxNumber() {
        return primaryFaxNumber;
    }

    /**
     * Sets the value of the primaryFaxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryFaxNumber(String value) {
        this.primaryFaxNumber = value;
    }

    /**
     * Gets the value of the pharmacyContactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyContactNumber() {
        return pharmacyContactNumber;
    }

    /**
     * Sets the value of the pharmacyContactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyContactNumber(String value) {
        this.pharmacyContactNumber = value;
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
