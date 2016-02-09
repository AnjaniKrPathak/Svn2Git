
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
 *         &lt;element name="reportType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reportEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prescriptionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prescriptionCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "reportType",
    "reportStartDate",
    "reportEndDate",
    "prescriptionType",
    "prescriptionCount"
})
@XmlRootElement(name = "ReportPrescribingCount")
public class ReportPrescribingCount {

    protected Credentials credentials;
    protected AccountRequest accountRequest;
    protected PatientInformationRequester patientInformationRequester;
    protected String reportType;
    protected String reportStartDate;
    protected String reportEndDate;
    protected String prescriptionType;
    protected String prescriptionCount;

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
     * Gets the value of the reportType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportType() {
        return reportType;
    }

    /**
     * Sets the value of the reportType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportType(String value) {
        this.reportType = value;
    }

    /**
     * Gets the value of the reportStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportStartDate() {
        return reportStartDate;
    }

    /**
     * Sets the value of the reportStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportStartDate(String value) {
        this.reportStartDate = value;
    }

    /**
     * Gets the value of the reportEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportEndDate() {
        return reportEndDate;
    }

    /**
     * Sets the value of the reportEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportEndDate(String value) {
        this.reportEndDate = value;
    }

    /**
     * Gets the value of the prescriptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * Sets the value of the prescriptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionType(String value) {
        this.prescriptionType = value;
    }

    /**
     * Gets the value of the prescriptionCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionCount() {
        return prescriptionCount;
    }

    /**
     * Sets the value of the prescriptionCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionCount(String value) {
        this.prescriptionCount = value;
    }

}
