
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
 *         &lt;element name="GetRenewalRequestDetailResult" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalDetailResult" minOccurs="0"/>
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
    "getRenewalRequestDetailResult"
})
@XmlRootElement(name = "GetRenewalRequestDetailResponse")
public class GetRenewalRequestDetailResponse {

    @XmlElement(name = "GetRenewalRequestDetailResult")
    protected RenewalDetailResult getRenewalRequestDetailResult;

    /**
     * Gets the value of the getRenewalRequestDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalDetailResult }
     *     
     */
    public RenewalDetailResult getGetRenewalRequestDetailResult() {
        return getRenewalRequestDetailResult;
    }

    /**
     * Sets the value of the getRenewalRequestDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalDetailResult }
     *     
     */
    public void setGetRenewalRequestDetailResult(RenewalDetailResult value) {
        this.getRenewalRequestDetailResult = value;
    }

}
