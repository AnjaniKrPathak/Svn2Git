
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
 *         &lt;element name="locationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="licensedPrescriberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportDateCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeHL7Result" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeHtmlResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "locationId",
    "licensedPrescriberId",
    "reportDateCCYYMMDD",
    "includeHL7Result",
    "includeHtmlResult"
})
@XmlRootElement(name = "GetLabResultsPrototype")
public class GetLabResultsPrototype {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected String locationId;
    protected String licensedPrescriberId;
    protected String reportDateCCYYMMDD;
    protected String includeHL7Result;
    protected String includeHtmlResult;

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
     * Gets the value of the locationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * Sets the value of the locationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationId(String value) {
        this.locationId = value;
    }

    /**
     * Gets the value of the licensedPrescriberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicensedPrescriberId() {
        return licensedPrescriberId;
    }

    /**
     * Sets the value of the licensedPrescriberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicensedPrescriberId(String value) {
        this.licensedPrescriberId = value;
    }

    /**
     * Gets the value of the reportDateCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportDateCCYYMMDD() {
        return reportDateCCYYMMDD;
    }

    /**
     * Sets the value of the reportDateCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportDateCCYYMMDD(String value) {
        this.reportDateCCYYMMDD = value;
    }

    /**
     * Gets the value of the includeHL7Result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeHL7Result() {
        return includeHL7Result;
    }

    /**
     * Sets the value of the includeHL7Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeHL7Result(String value) {
        this.includeHL7Result = value;
    }

    /**
     * Gets the value of the includeHtmlResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeHtmlResult() {
        return includeHtmlResult;
    }

    /**
     * Sets the value of the includeHtmlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeHtmlResult(String value) {
        this.includeHtmlResult = value;
    }

}
