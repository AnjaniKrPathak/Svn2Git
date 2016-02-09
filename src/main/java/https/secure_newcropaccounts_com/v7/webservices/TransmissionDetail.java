
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TransmissionDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransmissionDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TransmissionMethod" type="{https://secure.newcropaccounts.com/V7/webservices}TransmissionMethodType"/>
 *         &lt;element name="TransmissionNetwork" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TransactionGuid" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="TransactionDetailGuid" type="{http://microsoft.com/wsdl/types/}guid"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="UserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DetailMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DetailXmlResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pharmacyDetail" type="{https://secure.newcropaccounts.com/V7/webservices}PharmacyDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransmissionDetail", propOrder = {
    "transmissionMethod",
    "transmissionNetwork",
    "transactionGuid",
    "transactionDetailGuid",
    "timestamp",
    "userId",
    "statusMessage",
    "detailMessage",
    "detailXmlResponse",
    "pharmacyDetail"
})
public class TransmissionDetail {

    @XmlElement(name = "TransmissionMethod", required = true)
    protected TransmissionMethodType transmissionMethod;
    @XmlElement(name = "TransmissionNetwork")
    protected String transmissionNetwork;
    @XmlElement(name = "TransactionGuid", required = true)
    protected String transactionGuid;
    @XmlElement(name = "TransactionDetailGuid", required = true)
    protected String transactionDetailGuid;
    @XmlElement(name = "Timestamp", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "UserId")
    protected String userId;
    @XmlElement(name = "StatusMessage")
    protected String statusMessage;
    @XmlElement(name = "DetailMessage")
    protected String detailMessage;
    @XmlElement(name = "DetailXmlResponse")
    protected String detailXmlResponse;
    protected PharmacyDetail pharmacyDetail;

    /**
     * Gets the value of the transmissionMethod property.
     * 
     * @return
     *     possible object is
     *     {@link TransmissionMethodType }
     *     
     */
    public TransmissionMethodType getTransmissionMethod() {
        return transmissionMethod;
    }

    /**
     * Sets the value of the transmissionMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransmissionMethodType }
     *     
     */
    public void setTransmissionMethod(TransmissionMethodType value) {
        this.transmissionMethod = value;
    }

    /**
     * Gets the value of the transmissionNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmissionNetwork() {
        return transmissionNetwork;
    }

    /**
     * Sets the value of the transmissionNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmissionNetwork(String value) {
        this.transmissionNetwork = value;
    }

    /**
     * Gets the value of the transactionGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionGuid() {
        return transactionGuid;
    }

    /**
     * Sets the value of the transactionGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionGuid(String value) {
        this.transactionGuid = value;
    }

    /**
     * Gets the value of the transactionDetailGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionDetailGuid() {
        return transactionDetailGuid;
    }

    /**
     * Sets the value of the transactionDetailGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionDetailGuid(String value) {
        this.transactionDetailGuid = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

    /**
     * Gets the value of the statusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * Sets the value of the statusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusMessage(String value) {
        this.statusMessage = value;
    }

    /**
     * Gets the value of the detailMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailMessage() {
        return detailMessage;
    }

    /**
     * Sets the value of the detailMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailMessage(String value) {
        this.detailMessage = value;
    }

    /**
     * Gets the value of the detailXmlResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailXmlResponse() {
        return detailXmlResponse;
    }

    /**
     * Sets the value of the detailXmlResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailXmlResponse(String value) {
        this.detailXmlResponse = value;
    }

    /**
     * Gets the value of the pharmacyDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyDetail }
     *     
     */
    public PharmacyDetail getPharmacyDetail() {
        return pharmacyDetail;
    }

    /**
     * Sets the value of the pharmacyDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyDetail }
     *     
     */
    public void setPharmacyDetail(PharmacyDetail value) {
        this.pharmacyDetail = value;
    }

}
