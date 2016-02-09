
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PrescriptionHistoryRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrescriptionHistoryRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartHistory" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndHistory" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="PrescriptionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionSubStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriptionArchiveStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrescriptionHistoryRequest", propOrder = {
    "startHistory",
    "endHistory",
    "prescriptionStatus",
    "prescriptionSubStatus",
    "prescriptionArchiveStatus"
})
public class PrescriptionHistoryRequest {

    @XmlElement(name = "StartHistory", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startHistory;
    @XmlElement(name = "EndHistory", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endHistory;
    @XmlElement(name = "PrescriptionStatus")
    protected String prescriptionStatus;
    @XmlElement(name = "PrescriptionSubStatus")
    protected String prescriptionSubStatus;
    @XmlElement(name = "PrescriptionArchiveStatus")
    protected String prescriptionArchiveStatus;

    /**
     * Gets the value of the startHistory property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartHistory() {
        return startHistory;
    }

    /**
     * Sets the value of the startHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartHistory(XMLGregorianCalendar value) {
        this.startHistory = value;
    }

    /**
     * Gets the value of the endHistory property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndHistory() {
        return endHistory;
    }

    /**
     * Sets the value of the endHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndHistory(XMLGregorianCalendar value) {
        this.endHistory = value;
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
     * Gets the value of the prescriptionArchiveStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriptionArchiveStatus() {
        return prescriptionArchiveStatus;
    }

    /**
     * Sets the value of the prescriptionArchiveStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriptionArchiveStatus(String value) {
        this.prescriptionArchiveStatus = value;
    }

}
