
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
 *         &lt;element name="GetSubmittedMessageTransactionStatusResult" type="{https://secure.newcropaccounts.com/V7/webservices}MessageTransactionStatusResult" minOccurs="0"/>
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
    "getSubmittedMessageTransactionStatusResult"
})
@XmlRootElement(name = "GetSubmittedMessageTransactionStatusResponse")
public class GetSubmittedMessageTransactionStatusResponse {

    @XmlElement(name = "GetSubmittedMessageTransactionStatusResult")
    protected MessageTransactionStatusResult getSubmittedMessageTransactionStatusResult;

    /**
     * Gets the value of the getSubmittedMessageTransactionStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link MessageTransactionStatusResult }
     *     
     */
    public MessageTransactionStatusResult getGetSubmittedMessageTransactionStatusResult() {
        return getSubmittedMessageTransactionStatusResult;
    }

    /**
     * Sets the value of the getSubmittedMessageTransactionStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link MessageTransactionStatusResult }
     *     
     */
    public void setGetSubmittedMessageTransactionStatusResult(MessageTransactionStatusResult value) {
        this.getSubmittedMessageTransactionStatusResult = value;
    }

}
