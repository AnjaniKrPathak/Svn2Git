
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransmissionSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransmissionSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExternalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionGuid" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="PrescriptionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionSubStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionArchive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SummaryMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SummaryXmlResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransmissionDetailCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="drugDetail" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetail5" minOccurs="0"/>
 *         &lt;element name="transmissionDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfTransmissionDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransmissionSummary", propOrder = {
    "externalId",
    "prescriptionGuid",
    "prescriptionStatus",
    "prescriptionSubStatus",
    "prescriptionArchive",
    "summaryMessage",
    "summaryXmlResponse",
    "transmissionDetailCount",
    "drugDetail",
    "transmissionDetailArray"
})
public class TransmissionSummary {

    @XmlElement(name = "ExternalId")
    protected String externalId;
    @XmlElement(name = "PrescriptionGuid", required = true)
    protected String prescriptionGuid;
    @XmlElement(name = "PrescriptionStatus")
    protected String prescriptionStatus;
    @XmlElement(name = "PrescriptionSubStatus")
    protected String prescriptionSubStatus;
    @XmlElement(name = "PrescriptionArchive")
    protected String prescriptionArchive;
    @XmlElement(name = "SummaryMessage")
    protected String summaryMessage;
    @XmlElement(name = "SummaryXmlResponse")
    protected String summaryXmlResponse;
    @XmlElement(name = "TransmissionDetailCount")
    protected int transmissionDetailCount;
    protected PatientDrugDetail5 drugDetail;
    protected ArrayOfTransmissionDetail transmissionDetailArray;

    /**
     * Gets the value of the externalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalId() {
        return externalId;
    }

    /**
     * Sets the value of the externalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalId(String value) {
        this.externalId = value;
    }

    /**
     * Gets the value of the prescriptionGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionGuid() {
        return prescriptionGuid;
    }

    /**
     * Sets the value of the prescriptionGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionGuid(String value) {
        this.prescriptionGuid = value;
    }

    /**
     * Gets the value of the prescriptionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    /**
     * Sets the value of the prescriptionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionStatus(String value) {
        this.prescriptionStatus = value;
    }

    /**
     * Gets the value of the prescriptionSubStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionSubStatus() {
        return prescriptionSubStatus;
    }

    /**
     * Sets the value of the prescriptionSubStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionSubStatus(String value) {
        this.prescriptionSubStatus = value;
    }

    /**
     * Gets the value of the prescriptionArchive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionArchive() {
        return prescriptionArchive;
    }

    /**
     * Sets the value of the prescriptionArchive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionArchive(String value) {
        this.prescriptionArchive = value;
    }

    /**
     * Gets the value of the summaryMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryMessage() {
        return summaryMessage;
    }

    /**
     * Sets the value of the summaryMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryMessage(String value) {
        this.summaryMessage = value;
    }

    /**
     * Gets the value of the summaryXmlResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummaryXmlResponse() {
        return summaryXmlResponse;
    }

    /**
     * Sets the value of the summaryXmlResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummaryXmlResponse(String value) {
        this.summaryXmlResponse = value;
    }

    /**
     * Gets the value of the transmissionDetailCount property.
     * 
     */
    public int getTransmissionDetailCount() {
        return transmissionDetailCount;
    }

    /**
     * Sets the value of the transmissionDetailCount property.
     * 
     */
    public void setTransmissionDetailCount(int value) {
        this.transmissionDetailCount = value;
    }

    /**
     * Gets the value of the drugDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDrugDetail5 }
     *     
     */
    public PatientDrugDetail5 getDrugDetail() {
        return drugDetail;
    }

    /**
     * Sets the value of the drugDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDrugDetail5 }
     *     
     */
    public void setDrugDetail(PatientDrugDetail5 value) {
        this.drugDetail = value;
    }

    /**
     * Gets the value of the transmissionDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfTransmissionDetail }
     *     
     */
    public ArrayOfTransmissionDetail getTransmissionDetailArray() {
        return transmissionDetailArray;
    }

    /**
     * Sets the value of the transmissionDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfTransmissionDetail }
     *     
     */
    public void setTransmissionDetailArray(ArrayOfTransmissionDetail value) {
        this.transmissionDetailArray = value;
    }

}
