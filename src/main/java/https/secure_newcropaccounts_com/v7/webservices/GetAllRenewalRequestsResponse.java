
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
 *         &lt;element name="GetAllRenewalRequestsResult" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalSummaryResult" minOccurs="0"/>
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
    "getAllRenewalRequestsResult"
})
@XmlRootElement(name = "GetAllRenewalRequestsResponse")
public class GetAllRenewalRequestsResponse {

    @XmlElement(name = "GetAllRenewalRequestsResult")
    protected RenewalSummaryResult getAllRenewalRequestsResult;

    /**
     * Gets the value of the getAllRenewalRequestsResult property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalSummaryResult }
     *     
     */
    public RenewalSummaryResult getGetAllRenewalRequestsResult() {
        return getAllRenewalRequestsResult;
    }

    /**
     * Sets the value of the getAllRenewalRequestsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalSummaryResult }
     *     
     */
    public void setGetAllRenewalRequestsResult(RenewalSummaryResult value) {
        this.getAllRenewalRequestsResult = value;
    }

}
