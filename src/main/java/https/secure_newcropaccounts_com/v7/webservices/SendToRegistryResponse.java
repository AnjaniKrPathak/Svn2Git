
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
 *         &lt;element name="SendToRegistryResult" type="{https://secure.newcropaccounts.com/V7/webservices}RegistryDetailResult" minOccurs="0"/>
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
    "sendToRegistryResult"
})
@XmlRootElement(name = "SendToRegistryResponse")
public class SendToRegistryResponse {

    @XmlElement(name = "SendToRegistryResult")
    protected RegistryDetailResult sendToRegistryResult;

    /**
     * Gets the value of the sendToRegistryResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegistryDetailResult }
     *     
     */
    public RegistryDetailResult getSendToRegistryResult() {
        return sendToRegistryResult;
    }

    /**
     * Sets the value of the sendToRegistryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistryDetailResult }
     *     
     */
    public void setSendToRegistryResult(RegistryDetailResult value) {
        this.sendToRegistryResult = value;
    }

}
