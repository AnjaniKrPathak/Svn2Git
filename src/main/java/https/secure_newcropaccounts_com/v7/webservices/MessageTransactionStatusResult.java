
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MessageTransactionStatusResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MessageTransactionStatusResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="messageTransactionStatusDetail" type="{https://secure.newcropaccounts.com/V7/webservices}MessageTransactionStatusDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageTransactionStatusResult", propOrder = {
    "result",
    "messageTransactionStatusDetail"
})
public class MessageTransactionStatusResult {

    protected Result result;
    protected MessageTransactionStatusDetail messageTransactionStatusDetail;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the messageTransactionStatusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link MessageTransactionStatusDetail }
     *     
     */
    public MessageTransactionStatusDetail getMessageTransactionStatusDetail() {
        return messageTransactionStatusDetail;
    }

    /**
     * Sets the value of the messageTransactionStatusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageTransactionStatusDetail }
     *     
     */
    public void setMessageTransactionStatusDetail(MessageTransactionStatusDetail value) {
        this.messageTransactionStatusDetail = value;
    }

}
