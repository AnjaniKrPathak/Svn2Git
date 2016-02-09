
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="licensedPrescriberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startDateCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endDateCCYYMMDD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quarterNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="quarterYearCCYY" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="timePeriodQueryType" type="{https://secure.newcropaccounts.com/V7/webservices}TimePeriodQueryType"/>
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
    "licensedPrescriberId",
    "startDateCCYYMMDD",
    "endDateCCYYMMDD",
    "quarterNumber",
    "quarterYearCCYY",
    "timePeriodQueryType",
    "includeSchema"
})
@XmlRootElement(name = "GenerateMeaningfulUseUtilizationReport")
public class GenerateMeaningfulUseUtilizationReport {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientInformationRequester patientInformationRequester;
    protected String licensedPrescriberId;
    protected String startDateCCYYMMDD;
    protected String endDateCCYYMMDD;
    protected int quarterNumber;
    protected String quarterYearCCYY;
    @XmlElement(required = true)
    protected TimePeriodQueryType timePeriodQueryType;
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
     * Gets the value of the startDateCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDateCCYYMMDD() {
        return startDateCCYYMMDD;
    }

    /**
     * Sets the value of the startDateCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDateCCYYMMDD(String value) {
        this.startDateCCYYMMDD = value;
    }

    /**
     * Gets the value of the endDateCCYYMMDD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDateCCYYMMDD() {
        return endDateCCYYMMDD;
    }

    /**
     * Sets the value of the endDateCCYYMMDD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDateCCYYMMDD(String value) {
        this.endDateCCYYMMDD = value;
    }

    /**
     * Gets the value of the quarterNumber property.
     * 
     */
    public int getQuarterNumber() {
        return quarterNumber;
    }

    /**
     * Sets the value of the quarterNumber property.
     * 
     */
    public void setQuarterNumber(int value) {
        this.quarterNumber = value;
    }

    /**
     * Gets the value of the quarterYearCCYY property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuarterYearCCYY() {
        return quarterYearCCYY;
    }

    /**
     * Sets the value of the quarterYearCCYY property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuarterYearCCYY(String value) {
        this.quarterYearCCYY = value;
    }

    /**
     * Gets the value of the timePeriodQueryType property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodQueryType }
     *     
     */
    public TimePeriodQueryType getTimePeriodQueryType() {
        return timePeriodQueryType;
    }

    /**
     * Sets the value of the timePeriodQueryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodQueryType }
     *     
     */
    public void setTimePeriodQueryType(TimePeriodQueryType value) {
        this.timePeriodQueryType = value;
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
