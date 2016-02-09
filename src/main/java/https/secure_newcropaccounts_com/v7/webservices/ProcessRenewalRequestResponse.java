
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
 *         &lt;element name="ProcessRenewalRequestResult" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
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
    "processRenewalRequestResult"
})
@XmlRootElement(name = "ProcessRenewalRequestResponse")
public class ProcessRenewalRequestResponse {

    @XmlElement(name = "ProcessRenewalRequestResult")
    protected Result processRenewalRequestResult;

    /**
     * Gets the value of the processRenewalRequestResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getProcessRenewalRequestResult() {
        return processRenewalRequestResult;
    }

    /**
     * Sets the value of the processRenewalRequestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setProcessRenewalRequestResult(Result value) {
        this.processRenewalRequestResult = value;
    }

}
