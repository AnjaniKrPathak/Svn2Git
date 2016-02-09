
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LabResultDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabResultDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResultGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HL7Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HtmlMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResultDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PatientLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientMRN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabResultDetail", propOrder = {
    "resultGuid",
    "hl7Message",
    "htmlMessage",
    "doctorLastName",
    "doctorFirstName",
    "resultDateTime",
    "patientLastName",
    "patientFirstName",
    "patientMiddleName",
    "patientDOB",
    "patientMRN"
})
public class LabResultDetail {

    @XmlElement(name = "ResultGuid")
    protected String resultGuid;
    @XmlElement(name = "HL7Message")
    protected String hl7Message;
    @XmlElement(name = "HtmlMessage")
    protected String htmlMessage;
    @XmlElement(name = "DoctorLastName")
    protected String doctorLastName;
    @XmlElement(name = "DoctorFirstName")
    protected String doctorFirstName;
    @XmlElement(name = "ResultDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resultDateTime;
    @XmlElement(name = "PatientLastName")
    protected String patientLastName;
    @XmlElement(name = "PatientFirstName")
    protected String patientFirstName;
    @XmlElement(name = "PatientMiddleName")
    protected String patientMiddleName;
    @XmlElement(name = "PatientDOB")
    protected String patientDOB;
    @XmlElement(name = "PatientMRN")
    protected String patientMRN;

    /**
     * Gets the value of the resultGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResultGuid() {
        return resultGuid;
    }

    /**
     * Sets the value of the resultGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResultGuid(String value) {
        this.resultGuid = value;
    }

    /**
     * Gets the value of the hl7Message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHL7Message() {
        return hl7Message;
    }

    /**
     * Sets the value of the hl7Message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHL7Message(String value) {
        this.hl7Message = value;
    }

    /**
     * Gets the value of the htmlMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtmlMessage() {
        return htmlMessage;
    }

    /**
     * Sets the value of the htmlMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtmlMessage(String value) {
        this.htmlMessage = value;
    }

    /**
     * Gets the value of the doctorLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorLastName() {
        return doctorLastName;
    }

    /**
     * Sets the value of the doctorLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorLastName(String value) {
        this.doctorLastName = value;
    }

    /**
     * Gets the value of the doctorFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    /**
     * Sets the value of the doctorFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorFirstName(String value) {
        this.doctorFirstName = value;
    }

    /**
     * Gets the value of the resultDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResultDateTime() {
        return resultDateTime;
    }

    /**
     * Sets the value of the resultDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResultDateTime(XMLGregorianCalendar value) {
        this.resultDateTime = value;
    }

    /**
     * Gets the value of the patientLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientLastName() {
        return patientLastName;
    }

    /**
     * Sets the value of the patientLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientLastName(String value) {
        this.patientLastName = value;
    }

    /**
     * Gets the value of the patientFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientFirstName() {
        return patientFirstName;
    }

    /**
     * Sets the value of the patientFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientFirstName(String value) {
        this.patientFirstName = value;
    }

    /**
     * Gets the value of the patientMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    /**
     * Sets the value of the patientMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientMiddleName(String value) {
        this.patientMiddleName = value;
    }

    /**
     * Gets the value of the patientDOB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientDOB() {
        return patientDOB;
    }

    /**
     * Sets the value of the patientDOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientDOB(String value) {
        this.patientDOB = value;
    }

    /**
     * Gets the value of the patientMRN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientMRN() {
        return patientMRN;
    }

    /**
     * Sets the value of the patientMRN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientMRN(String value) {
        this.patientMRN = value;
    }

}
