
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContactType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContactType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="homeTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cellularTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pagerTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backOfficeTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="backOfficeFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContactType", propOrder = {
    "homeTelephone",
    "workTelephone",
    "cellularTelephone",
    "pagerTelephone",
    "fax",
    "email",
    "backOfficeTelephone",
    "backOfficeFax"
})
public class ContactType {

    protected String homeTelephone;
    protected String workTelephone;
    protected String cellularTelephone;
    protected String pagerTelephone;
    protected String fax;
    protected String email;
    protected String backOfficeTelephone;
    protected String backOfficeFax;

    /**
     * Gets the value of the homeTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeTelephone() {
        return homeTelephone;
    }

    /**
     * Sets the value of the homeTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeTelephone(String value) {
        this.homeTelephone = value;
    }

    /**
     * Gets the value of the workTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkTelephone() {
        return workTelephone;
    }

    /**
     * Sets the value of the workTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkTelephone(String value) {
        this.workTelephone = value;
    }

    /**
     * Gets the value of the cellularTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCellularTelephone() {
        return cellularTelephone;
    }

    /**
     * Sets the value of the cellularTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCellularTelephone(String value) {
        this.cellularTelephone = value;
    }

    /**
     * Gets the value of the pagerTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagerTelephone() {
        return pagerTelephone;
    }

    /**
     * Sets the value of the pagerTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagerTelephone(String value) {
        this.pagerTelephone = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the backOfficeTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackOfficeTelephone() {
        return backOfficeTelephone;
    }

    /**
     * Sets the value of the backOfficeTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackOfficeTelephone(String value) {
        this.backOfficeTelephone = value;
    }

    /**
     * Gets the value of the backOfficeFax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackOfficeFax() {
        return backOfficeFax;
    }

    /**
     * Sets the value of the backOfficeFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackOfficeFax(String value) {
        this.backOfficeFax = value;
    }

}
