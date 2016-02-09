
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
 *         &lt;element name="GetAllRenewalRequestsV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalSummaryResultV2" minOccurs="0"/>
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
    "getAllRenewalRequestsV2Result"
})
@XmlRootElement(name = "GetAllRenewalRequestsV2Response")
public class GetAllRenewalRequestsV2Response {

    @XmlElement(name = "GetAllRenewalRequestsV2Result")
    protected RenewalSummaryResultV2 getAllRenewalRequestsV2Result;

    /**
     * Gets the value of the getAllRenewalRequestsV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalSummaryResultV2 }
     *     
     */
    public RenewalSummaryResultV2 getGetAllRenewalRequestsV2Result() {
        return getAllRenewalRequestsV2Result;
    }

    /**
     * Sets the value of the getAllRenewalRequestsV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalSummaryResultV2 }
     *     
     */
    public void setGetAllRenewalRequestsV2Result(RenewalSummaryResultV2 value) {
        this.getAllRenewalRequestsV2Result = value;
    }

}
