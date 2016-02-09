
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
 *         &lt;element name="GetPBMEligibilityResult" type="{https://secure.newcropaccounts.com/V7/webservices}EligibilityDetailResult" minOccurs="0"/>
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
    "getPBMEligibilityResult"
})
@XmlRootElement(name = "GetPBMEligibilityResponse")
public class GetPBMEligibilityResponse {

    @XmlElement(name = "GetPBMEligibilityResult")
    protected EligibilityDetailResult getPBMEligibilityResult;

    /**
     * Gets the value of the getPBMEligibilityResult property.
     * 
     * @return
     *     possible object is
     *     {@link EligibilityDetailResult }
     *     
     */
    public EligibilityDetailResult getGetPBMEligibilityResult() {
        return getPBMEligibilityResult;
    }

    /**
     * Sets the value of the getPBMEligibilityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link EligibilityDetailResult }
     *     
     */
    public void setGetPBMEligibilityResult(EligibilityDetailResult value) {
        this.getPBMEligibilityResult = value;
    }

}
