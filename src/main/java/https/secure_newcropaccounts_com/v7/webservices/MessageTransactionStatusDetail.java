
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageTransactionStatusDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageTransactionStatusDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageTransactionSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageTransactionSubSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageTransactionState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MessageTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalUserIdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageTransactionStatusDetail", propOrder = {
    "messageId",
    "messageTransactionSource",
    "messageTransactionSubSource",
    "messageTransactionState",
    "messageTimestamp",
    "externalPatientId",
    "externalUserId",
    "externalUserIdType"
})
public class MessageTransactionStatusDetail {

    @XmlElement(name = "MessageId")
    protected String messageId;
    @XmlElement(name = "MessageTransactionSource")
    protected String messageTransactionSource;
    @XmlElement(name = "MessageTransactionSubSource")
    protected String messageTransactionSubSource;
    @XmlElement(name = "MessageTransactionState")
    protected String messageTransactionState;
    @XmlElement(name = "MessageTimestamp")
    protected String messageTimestamp;
    @XmlElement(name = "ExternalPatientId")
    protected String externalPatientId;
    @XmlElement(name = "ExternalUserId")
    protected String externalUserId;
    @XmlElement(name = "ExternalUserIdType")
    protected String externalUserIdType;

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

    /**
     * Gets the value of the messageTransactionSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTransactionSource() {
        return messageTransactionSource;
    }

    /**
     * Sets the value of the messageTransactionSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTransactionSource(String value) {
        this.messageTransactionSource = value;
    }

    /**
     * Gets the value of the messageTransactionSubSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTransactionSubSource() {
        return messageTransactionSubSource;
    }

    /**
     * Sets the value of the messageTransactionSubSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTransactionSubSource(String value) {
        this.messageTransactionSubSource = value;
    }

    /**
     * Gets the value of the messageTransactionState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTransactionState() {
        return messageTransactionState;
    }

    /**
     * Sets the value of the messageTransactionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTransactionState(String value) {
        this.messageTransactionState = value;
    }

    /**
     * Gets the value of the messageTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTimestamp() {
        return messageTimestamp;
    }

    /**
     * Sets the value of the messageTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTimestamp(String value) {
        this.messageTimestamp = value;
    }

    /**
     * Gets the value of the externalPatientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPatientId() {
        return externalPatientId;
    }

    /**
     * Sets the value of the externalPatientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPatientId(String value) {
        this.externalPatientId = value;
    }

    /**
     * Gets the value of the externalUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalUserId() {
        return externalUserId;
    }

    /**
     * Sets the value of the externalUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalUserId(String value) {
        this.externalUserId = value;
    }

    /**
     * Gets the value of the externalUserIdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalUserIdType() {
        return externalUserIdType;
    }

    /**
     * Sets the value of the externalUserIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalUserIdType(String value) {
        this.externalUserIdType = value;
    }

}
