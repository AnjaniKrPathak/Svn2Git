
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
 *         &lt;element name="drugId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drugTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="birthDateCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diagnosis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doseType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doseUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="weightInKg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "patientRequest",
    "patientInformationRequester",
    "drugId",
    "drugTypeId",
    "birthDateCCYYMMDD",
    "gender",
    "diagnosis",
    "doseType",
    "dose",
    "doseUOM",
    "weightInKg",
    "includeSchema"
})
@XmlRootElement(name = "DoseCheck")
public class DoseCheck {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientRequest patientRequest;
    protected PatientInformationRequester patientInformationRequester;
    protected String drugId;
    protected String drugTypeId;
    protected String birthDateCCYYMMDD;
    protected String gender;
    protected String diagnosis;
    protected String doseType;
    protected String dose;
    protected String doseUOM;
    protected String weightInKg;
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
     * Gets the value of the drugId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugId() {
        return drugId;
    }

    /**
     * Sets the value of the drugId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugId(String value) {
        this.drugId = value;
    }

    /**
     * Gets the value of the drugTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugTypeId() {
        return drugTypeId;
    }

    /**
     * Sets the value of the drugTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugTypeId(String value) {
        this.drugTypeId = value;
    }

    /**
     * Gets the value of the birthDateCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDateCCYYMMDD() {
        return birthDateCCYYMMDD;
    }

    /**
     * Sets the value of the birthDateCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDateCCYYMMDD(String value) {
        this.birthDateCCYYMMDD = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the diagnosis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the value of the diagnosis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosis(String value) {
        this.diagnosis = value;
    }

    /**
     * Gets the value of the doseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoseType() {
        return doseType;
    }

    /**
     * Sets the value of the doseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoseType(String value) {
        this.doseType = value;
    }

    /**
     * Gets the value of the dose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDose() {
        return dose;
    }

    /**
     * Sets the value of the dose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDose(String value) {
        this.dose = value;
    }

    /**
     * Gets the value of the doseUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoseUOM() {
        return doseUOM;
    }

    /**
     * Sets the value of the doseUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoseUOM(String value) {
        this.doseUOM = value;
    }

    /**
     * Gets the value of the weightInKg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeightInKg() {
        return weightInKg;
    }

    /**
     * Sets the value of the weightInKg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeightInKg(String value) {
        this.weightInKg = value;
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
