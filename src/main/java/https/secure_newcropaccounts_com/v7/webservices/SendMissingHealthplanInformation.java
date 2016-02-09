
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
 *         &lt;element name="healthplanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanAddress1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanAddress2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanStateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanZip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanZip4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="healthplanPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "healthplanName",
    "healthplanId",
    "healthplanAddress1",
    "healthplanAddress2",
    "healthplanCity",
    "healthplanStateCode",
    "healthplanZip",
    "healthplanZip4",
    "healthplanPhoneNumber"
})
@XmlRootElement(name = "SendMissingHealthplanInformation")
public class SendMissingHealthplanInformation {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected String healthplanName;
    protected String healthplanId;
    protected String healthplanAddress1;
    protected String healthplanAddress2;
    protected String healthplanCity;
    protected String healthplanStateCode;
    protected String healthplanZip;
    protected String healthplanZip4;
    protected String healthplanPhoneNumber;

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

    /**
     * Gets the value of the healthplanId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanId() {
        return healthplanId;
    }

    /**
     * Sets the value of the healthplanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanId(String value) {
        this.healthplanId = value;
    }

    /**
     * Gets the value of the healthplanAddress1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanAddress1() {
        return healthplanAddress1;
    }

    /**
     * Sets the value of the healthplanAddress1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanAddress1(String value) {
        this.healthplanAddress1 = value;
    }

    /**
     * Gets the value of the healthplanAddress2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanAddress2() {
        return healthplanAddress2;
    }

    /**
     * Sets the value of the healthplanAddress2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanAddress2(String value) {
        this.healthplanAddress2 = value;
    }

    /**
     * Gets the value of the healthplanCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanCity() {
        return healthplanCity;
    }

    /**
     * Sets the value of the healthplanCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanCity(String value) {
        this.healthplanCity = value;
    }

    /**
     * Gets the value of the healthplanStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanStateCode() {
        return healthplanStateCode;
    }

    /**
     * Sets the value of the healthplanStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanStateCode(String value) {
        this.healthplanStateCode = value;
    }

    /**
     * Gets the value of the healthplanZip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanZip() {
        return healthplanZip;
    }

    /**
     * Sets the value of the healthplanZip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanZip(String value) {
        this.healthplanZip = value;
    }

    /**
     * Gets the value of the healthplanZip4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanZip4() {
        return healthplanZip4;
    }

    /**
     * Sets the value of the healthplanZip4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanZip4(String value) {
        this.healthplanZip4 = value;
    }

    /**
     * Gets the value of the healthplanPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanPhoneNumber() {
        return healthplanPhoneNumber;
    }

    /**
     * Sets the value of the healthplanPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanPhoneNumber(String value) {
        this.healthplanPhoneNumber = value;
    }

}
