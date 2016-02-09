
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
 *         &lt;element name="reportDateStartCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportDateEndCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includeSchema" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sortOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reportDateStartCCYYMMDD",
    "reportDateEndCCYYMMDD",
    "includeSchema",
    "sortOrder"
})
@XmlRootElement(name = "GetCompleteMedicationHistory")
public class GetCompleteMedicationHistory {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected String reportDateStartCCYYMMDD;
    protected String reportDateEndCCYYMMDD;
    protected String includeSchema;
    protected String sortOrder;

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
     * Gets the value of the reportDateStartCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportDateStartCCYYMMDD() {
        return reportDateStartCCYYMMDD;
    }

    /**
     * Sets the value of the reportDateStartCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportDateStartCCYYMMDD(String value) {
        this.reportDateStartCCYYMMDD = value;
    }

    /**
     * Gets the value of the reportDateEndCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportDateEndCCYYMMDD() {
        return reportDateEndCCYYMMDD;
    }

    /**
     * Sets the value of the reportDateEndCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportDateEndCCYYMMDD(String value) {
        this.reportDateEndCCYYMMDD = value;
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

    /**
     * Gets the value of the sortOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Sets the value of the sortOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortOrder(String value) {
        this.sortOrder = value;
    }

}
