
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
 *         &lt;element name="patientRequest" type="{https://secure.newcropaccounts.com/V7/webservices}PatientRequest" minOccurs="0"/>
 *         &lt;element name="maxCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="patientInformationRequester" type="{https://secure.newcropaccounts.com/V7/webservices}PatientInformationRequester" minOccurs="0"/>
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
    "patientRequest",
    "maxCount",
    "patientInformationRequester"
})
@XmlRootElement(name = "GetPatientPharmacyHistory")
public class GetPatientPharmacyHistory {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientRequest patientRequest;
    protected int maxCount;
    protected PatientInformationRequester patientInformationRequester;

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
     * Gets the value of the patientRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PatientRequest }
     *     
     */
    public PatientRequest getPatientRequest() {
        return patientRequest;
    }

    /**
     * Sets the value of the patientRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientRequest }
     *     
     */
    public void setPatientRequest(PatientRequest value) {
        this.patientRequest = value;
    }

    /**
     * Gets the value of the maxCount property.
     * 
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * Sets the value of the maxCount property.
     * 
     */
    public void setMaxCount(int value) {
        this.maxCount = value;
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

}
