
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
 *         &lt;element name="patientInformationRequester" type="{https://secure.newcropaccounts.com/V7/webservices}PatientInformationRequester" minOccurs="0"/>
 *         &lt;element name="xmlIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "patientInformationRequester",
    "xmlIn",
    "includeSchema"
})
@XmlRootElement(name = "GetPBMEligibilityV3")
public class GetPBMEligibilityV3 {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientInformationRequester patientInformationRequester;
    protected String xmlIn;
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
     * Gets the value of the patientInformationRequester property.
     * 
     * @return
     *     possible object is
     *     {@link PatientInformationRequester }
     *     
     */
    public PatientInformationRequester getPatientInformationRequester() {
        return patientInformationRequester;
    }

    /**
     * Sets the value of the patientInformationRequester property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientInformationRequester }
     *     
     */
    public void setPatientInformationRequester(PatientInformationRequester value) {
        this.patientInformationRequester = value;
    }

    /**
     * Gets the value of the xmlIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlIn() {
        return xmlIn;
    }

    /**
     * Sets the value of the xmlIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlIn(String value) {
        this.xmlIn = value;
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
