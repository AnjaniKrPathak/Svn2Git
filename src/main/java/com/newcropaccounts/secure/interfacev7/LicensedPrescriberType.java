
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.PrescriberNetwork;
import com.newcropaccounts.secure.interfacev7.ncstandard.PrescriberStatus;


/**
 * <p>Java class for LicensedPrescriberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LicensedPrescriberType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LicensedPrescriberName" type="{http://secure.newcropaccounts.com/interfaceV7}PersonNameType" minOccurs="0"/>
 *         &lt;element name="dea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prescriberStatus" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}PrescriberStatus" minOccurs="0"/>
 *         &lt;element name="upin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenseState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licenseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prescriberNetwork" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}PrescriberNetwork" minOccurs="0"/>
 *         &lt;element name="prescriberStartDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prescriberStopDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="npi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeformCredentials" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "LicensedPrescriberType", propOrder = {
    "licensedPrescriberName",
    "dea",
    "prescriberStatus",
    "upin",
    "licenseState",
    "licenseNumber",
    "prescriberNetwork",
    "prescriberStartDateTime",
    "prescriberStopDateTime",
    "npi",
    "freeformCredentials"
})
public class LicensedPrescriberType {

    @XmlElement(name = "LicensedPrescriberName")
    protected PersonNameType licensedPrescriberName;
    protected String dea;
    protected PrescriberStatus prescriberStatus;
    protected String upin;
    protected String licenseState;
    protected String licenseNumber;
    protected PrescriberNetwork prescriberNetwork;
    protected String prescriberStartDateTime;
    protected String prescriberStopDateTime;
    protected String npi;
    protected String freeformCredentials;
    @XmlAttribute(name = "ID")
    protected String id;

    /**
     * Gets the value of the licensedPrescriberName property.
     * 
     * @return
     *     possible object is
     *     {@link PersonNameType }
     *     
     */
    public PersonNameType getLicensedPrescriberName() {
        return licensedPrescriberName;
    }

    /**
     * Sets the value of the licensedPrescriberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNameType }
     *     
     */
    public void setLicensedPrescriberName(PersonNameType value) {
        this.licensedPrescriberName = value;
    }

    /**
     * Gets the value of the dea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDea() {
        return dea;
    }

    /**
     * Sets the value of the dea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDea(String value) {
        this.dea = value;
    }

    /**
     * Gets the value of the prescriberStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriberStatus }
     *     
     */
    public PrescriberStatus getPrescriberStatus() {
        return prescriberStatus;
    }

    /**
     * Sets the value of the prescriberStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriberStatus }
     *     
     */
    public void setPrescriberStatus(PrescriberStatus value) {
        this.prescriberStatus = value;
    }

    /**
     * Gets the value of the upin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpin() {
        return upin;
    }

    /**
     * Sets the value of the upin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpin(String value) {
        this.upin = value;
    }

    /**
     * Gets the value of the licenseState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseState() {
        return licenseState;
    }

    /**
     * Sets the value of the licenseState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseState(String value) {
        this.licenseState = value;
    }

    /**
     * Gets the value of the licenseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the value of the licenseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseNumber(String value) {
        this.licenseNumber = value;
    }

    /**
     * Gets the value of the prescriberNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link PrescriberNetwork }
     *     
     */
    public PrescriberNetwork getPrescriberNetwork() {
        return prescriberNetwork;
    }

    /**
     * Sets the value of the prescriberNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrescriberNetwork }
     *     
     */
    public void setPrescriberNetwork(PrescriberNetwork value) {
        this.prescriberNetwork = value;
    }

    /**
     * Gets the value of the prescriberStartDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberStartDateTime() {
        return prescriberStartDateTime;
    }

    /**
     * Sets the value of the prescriberStartDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberStartDateTime(String value) {
        this.prescriberStartDateTime = value;
    }

    /**
     * Gets the value of the prescriberStopDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberStopDateTime() {
        return prescriberStopDateTime;
    }

    /**
     * Sets the value of the prescriberStopDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberStopDateTime(String value) {
        this.prescriberStopDateTime = value;
    }

    /**
     * Gets the value of the npi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpi() {
        return npi;
    }

    /**
     * Sets the value of the npi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpi(String value) {
        this.npi = value;
    }

    /**
     * Gets the value of the freeformCredentials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFreeformCredentials() {
        return freeformCredentials;
    }

    /**
     * Sets the value of the freeformCredentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFreeformCredentials(String value) {
        this.freeformCredentials = value;
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
