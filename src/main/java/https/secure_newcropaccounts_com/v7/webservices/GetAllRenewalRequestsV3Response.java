
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
 *         &lt;element name="GetAllRenewalRequestsV3Result" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalSummaryResultV2" minOccurs="0"/>
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
    "getAllRenewalRequestsV3Result"
})
@XmlRootElement(name = "GetAllRenewalRequestsV3Response")
public class GetAllRenewalRequestsV3Response {

    @XmlElement(name = "GetAllRenewalRequestsV3Result")
    protected RenewalSummaryResultV2 getAllRenewalRequestsV3Result;

    /**
     * Gets the value of the getAllRenewalRequestsV3Result property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalSummaryResultV2 }
     *     
     */
    public RenewalSummaryResultV2 getGetAllRenewalRequestsV3Result() {
        return getAllRenewalRequestsV3Result;
    }

    /**
     * Sets the value of the getAllRenewalRequestsV3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalSummaryResultV2 }
     *     
     */
    public void setGetAllRenewalRequestsV3Result(RenewalSummaryResultV2 value) {
        this.getAllRenewalRequestsV3Result = value;
    }

}
