
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
 *         &lt;element name="reportDateCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="endHour" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transmissionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSchema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reportDateCCYYMMDD",
    "startHour",
    "endHour",
    "status",
    "transmissionType",
    "includeSchema"
})
@XmlRootElement(name = "GetDailyScriptReportV2")
public class GetDailyScriptReportV2 {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected String reportDateCCYYMMDD;
    protected int startHour;
    protected int endHour;
    protected String status;
    protected String transmissionType;
    protected String includeSchema;

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
     * Gets the value of the startHour property.
     * 
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Sets the value of the startHour property.
     * 
     */
    public void setStartHour(int value) {
        this.startHour = value;
    }

    /**
     * Gets the value of the endHour property.
     * 
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * Sets the value of the endHour property.
     * 
     */
    public void setEndHour(int value) {
        this.endHour = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the transmissionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmissionType() {
        return transmissionType;
    }

    /**
     * Sets the value of the transmissionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmissionType(String value) {
        this.transmissionType = value;
    }

    /**
     * Gets the value of the includeSchema property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncludeSchema() {
        return includeSchema;
    }

    /**
     * Sets the value of the includeSchema property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncludeSchema(String value) {
        this.includeSchema = value;
    }

}
