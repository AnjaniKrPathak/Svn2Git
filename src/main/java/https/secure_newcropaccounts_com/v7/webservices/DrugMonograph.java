
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
 *         &lt;element name="patientInformationRequester" type="{https://secure.newcropaccounts.com/V7/webservices}PatientInformationRequester" minOccurs="0"/>
 *         &lt;element name="drugConcept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugStandardType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monographFormat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "patientInformationRequester",
    "drugConcept",
    "drugStandardType",
    "monographFormat"
})
@XmlRootElement(name = "DrugMonograph")
public class DrugMonograph {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientRequest patientRequest;
    protected PatientInformationRequester patientInformationRequester;
    protected String drugConcept;
    protected String drugStandardType;
    protected String monographFormat;

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
     * Gets the value of the drugConcept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugConcept() {
        return drugConcept;
    }

    /**
     * Sets the value of the drugConcept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugConcept(String value) {
        this.drugConcept = value;
    }

    /**
     * Gets the value of the drugStandardType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugStandardType() {
        return drugStandardType;
    }

    /**
     * Sets the value of the drugStandardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugStandardType(String value) {
        this.drugStandardType = value;
    }

    /**
     * Gets the value of the monographFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonographFormat() {
        return monographFormat;
    }

    /**
     * Sets the value of the monographFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonographFormat(String value) {
        this.monographFormat = value;
    }

}
