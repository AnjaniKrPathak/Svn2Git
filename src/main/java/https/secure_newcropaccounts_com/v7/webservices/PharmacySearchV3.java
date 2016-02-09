
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="credentials" type="{https://secure.newcropaccounts.com/V7/webservices}Credentials" minOccurs="0"/>
 *         &lt;element name="accountRequest" type="{https://secure.newcropaccounts.com/V7/webservices}AccountRequest" minOccurs="0"/>
 *         &lt;element name="postalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pharmacyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ncpdpID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eligibilityIndex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "credentials",
    "accountRequest",
    "postalCode",
    "phoneNumber",
    "streetName",
    "pharmacyName",
    "city",
    "state",
    "ncpdpID",
    "healthplanID",
    "healthplanTypeID",
    "eligibilityIndex"
})
@XmlRootElement(name = "PharmacySearchV3")
public class PharmacySearchV3 {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected String postalCode;
    protected String phoneNumber;
    protected String streetName;
    protected String pharmacyName;
    protected String city;
    protected String state;
    protected String ncpdpID;
    protected String healthplanID;
    protected String healthplanTypeID;
    protected String eligibilityIndex;

    /**
     * Gets the value of the credentials property.
     * 
     * @return
     *     possible object is
     *     {@link Credentials }
     *     
     */
    public Credentials getCredentials() {
        return credentials;
    }

    /**
     * Sets the value of the credentials property.
     * 
     * @param value
     *     allowed object is
     *     {@link Credentials }
     *     
     */
    public void setCredentials(Credentials value) {
        this.credentials = value;
    }

    /**
     * Gets the value of the accountRequest property.
     * 
     * @return
     *     possible object is
     *     {@link AccountRequest }
     *     
     */
    public AccountRequest getAccountRequest() {
        return accountRequest;
    }

    /**
     * Sets the value of the accountRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountRequest }
     *     
     */
    public void setAccountRequest(AccountRequest value) {
        this.accountRequest = value;
    }

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the pharmacyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyName() {
        return pharmacyName;
    }

    /**
     * Sets the value of the pharmacyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyName(String value) {
        this.pharmacyName = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the ncpdpID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcpdpID() {
        return ncpdpID;
    }

    /**
     * Sets the value of the ncpdpID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcpdpID(String value) {
        this.ncpdpID = value;
    }

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
     *     {@link String }
     *     
     */
    public String getHealthplanTypeID() {
        return healthplanTypeID;
    }

    /**
     * Sets the value of the healthplanTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanTypeID(String value) {
        this.healthplanTypeID = value;
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

}
