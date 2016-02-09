
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ReportDownloadDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReportDownloadDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReportGenerationStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ReportGenerationStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportContents" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportMIMEType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportFormatType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="StartTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="SubmissionTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Base64EncodedSizeInBytes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CompressedSizeInBytes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="CompressionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="RawSizeInBytes" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Spare1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare5" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Spare6" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Spare7" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Spare8" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportDownloadDetail", propOrder = {
    "reportGenerationStatus",
    "reportGenerationStatusMessage",
    "reportContents",
    "reportMIMEType",
    "reportFormatType",
    "startTimestamp",
    "endTimestamp",
    "submissionTimestamp",
    "base64EncodedSizeInBytes",
    "compressedSizeInBytes",
    "compressionType",
    "rawSizeInBytes",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5",
    "spare6",
    "spare7",
    "spare8"
})
public class ReportDownloadDetail {

    @XmlElement(name = "ReportGenerationStatus")
    protected int reportGenerationStatus;
    @XmlElement(name = "ReportGenerationStatusMessage")
    protected String reportGenerationStatusMessage;
    @XmlElement(name = "ReportContents")
    protected String reportContents;
    @XmlElement(name = "ReportMIMEType")
    protected String reportMIMEType;
    @XmlElement(name = "ReportFormatType")
    protected int reportFormatType;
    @XmlElement(name = "StartTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTimestamp;
    @XmlElement(name = "EndTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTimestamp;
    @XmlElement(name = "SubmissionTimestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submissionTimestamp;
    @XmlElement(name = "Base64EncodedSizeInBytes")
    protected int base64EncodedSizeInBytes;
    @XmlElement(name = "CompressedSizeInBytes")
    protected int compressedSizeInBytes;
    @XmlElement(name = "CompressionType")
    protected int compressionType;
    @XmlElement(name = "RawSizeInBytes")
    protected int rawSizeInBytes;
    @XmlElement(name = "Spare1")
    protected String spare1;
    @XmlElement(name = "Spare2")
    protected String spare2;
    @XmlElement(name = "Spare3")
    protected String spare3;
    @XmlElement(name = "Spare4")
    protected String spare4;
    @XmlElement(name = "Spare5")
    protected int spare5;
    @XmlElement(name = "Spare6")
    protected int spare6;
    @XmlElement(name = "Spare7")
    protected int spare7;
    @XmlElement(name = "Spare8")
    protected int spare8;

    /**
     * Gets the value of the reportGenerationStatus property.
     * 
     */
    public int getReportGenerationStatus() {
        return reportGenerationStatus;
    }

    /**
     * Sets the value of the reportGenerationStatus property.
     * 
     */
    public void setReportGenerationStatus(int value) {
        this.reportGenerationStatus = value;
    }

    /**
     * Gets the value of the reportGenerationStatusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportGenerationStatusMessage() {
        return reportGenerationStatusMessage;
    }

    /**
     * Sets the value of the reportGenerationStatusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportGenerationStatusMessage(String value) {
        this.reportGenerationStatusMessage = value;
    }

    /**
     * Gets the value of the reportContents property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportContents() {
        return reportContents;
    }

    /**
     * Sets the value of the reportContents property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportContents(String value) {
        this.reportContents = value;
    }

    /**
     * Gets the value of the reportMIMEType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportMIMEType() {
        return reportMIMEType;
    }

    /**
     * Sets the value of the reportMIMEType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportMIMEType(String value) {
        this.reportMIMEType = value;
    }

    /**
     * Gets the value of the reportFormatType property.
     * 
     */
    public int getReportFormatType() {
        return reportFormatType;
    }

    /**
     * Sets the value of the reportFormatType property.
     * 
     */
    public void setReportFormatType(int value) {
        this.reportFormatType = value;
    }

    /**
     * Gets the value of the startTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Sets the value of the startTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTimestamp(XMLGregorianCalendar value) {
        this.startTimestamp = value;
    }

    /**
     * Gets the value of the endTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTimestamp() {
        return endTimestamp;
    }

    /**
     * Sets the value of the endTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTimestamp(XMLGregorianCalendar value) {
        this.endTimestamp = value;
    }

    /**
     * Gets the value of the submissionTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmissionTimestamp() {
        return submissionTimestamp;
    }

    /**
     * Sets the value of the submissionTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmissionTimestamp(XMLGregorianCalendar value) {
        this.submissionTimestamp = value;
    }

    /**
     * Gets the value of the base64EncodedSizeInBytes property.
     * 
     */
    public int getBase64EncodedSizeInBytes() {
        return base64EncodedSizeInBytes;
    }

    /**
     * Sets the value of the base64EncodedSizeInBytes property.
     * 
     */
    public void setBase64EncodedSizeInBytes(int value) {
        this.base64EncodedSizeInBytes = value;
    }

    /**
     * Gets the value of the compressedSizeInBytes property.
     * 
     */
    public int getCompressedSizeInBytes() {
        return compressedSizeInBytes;
    }

    /**
     * Sets the value of the compressedSizeInBytes property.
     * 
     */
    public void setCompressedSizeInBytes(int value) {
        this.compressedSizeInBytes = value;
    }

    /**
     * Gets the value of the compressionType property.
     * 
     */
    public int getCompressionType() {
        return compressionType;
    }

    /**
     * Sets the value of the compressionType property.
     * 
     */
    public void setCompressionType(int value) {
        this.compressionType = value;
    }

    /**
     * Gets the value of the rawSizeInBytes property.
     * 
     */
    public int getRawSizeInBytes() {
        return rawSizeInBytes;
    }

    /**
     * Sets the value of the rawSizeInBytes property.
     * 
     */
    public void setRawSizeInBytes(int value) {
        this.rawSizeInBytes = value;
    }

    /**
     * Gets the value of the spare1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare1() {
        return spare1;
    }

    /**
     * Sets the value of the spare1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare1(String value) {
        this.spare1 = value;
    }

    /**
     * Gets the value of the spare2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare2() {
        return spare2;
    }

    /**
     * Sets the value of the spare2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare2(String value) {
        this.spare2 = value;
    }

    /**
     * Gets the value of the spare3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare3() {
        return spare3;
    }

    /**
     * Sets the value of the spare3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare3(String value) {
        this.spare3 = value;
    }

    /**
     * Gets the value of the spare4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare4() {
        return spare4;
    }

    /**
     * Sets the value of the spare4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare4(String value) {
        this.spare4 = value;
    }

    /**
     * Gets the value of the spare5 property.
     * 
     */
    public int getSpare5() {
        return spare5;
    }

    /**
     * Sets the value of the spare5 property.
     * 
     */
    public void setSpare5(int value) {
        this.spare5 = value;
    }

    /**
     * Gets the value of the spare6 property.
     * 
     */
    public int getSpare6() {
        return spare6;
    }

    /**
     * Sets the value of the spare6 property.
     * 
     */
    public void setSpare6(int value) {
        this.spare6 = value;
    }

    /**
     * Gets the value of the spare7 property.
     * 
     */
    public int getSpare7() {
        return spare7;
    }

    /**
     * Sets the value of the spare7 property.
     * 
     */
    public void setSpare7(int value) {
        this.spare7 = value;
    }

    /**
     * Gets the value of the spare8 property.
     * 
     */
    public int getSpare8() {
        return spare8;
    }

    /**
     * Sets the value of the spare8 property.
     * 
     */
    public void setSpare8(int value) {
        this.spare8 = value;
    }

}
