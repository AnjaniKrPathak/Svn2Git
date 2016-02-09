
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
 *         &lt;element name="GetPBMEligibilityV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}EligibilityDetailResult" minOccurs="0"/>
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
    "getPBMEligibilityV2Result"
})
@XmlRootElement(name = "GetPBMEligibilityV2Response")
public class GetPBMEligibilityV2Response {

    @XmlElement(name = "GetPBMEligibilityV2Result")
    protected EligibilityDetailResult getPBMEligibilityV2Result;

    /**
     * Gets the value of the getPBMEligibilityV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link EligibilityDetailResult }
     *     
     */
    public EligibilityDetailResult getGetPBMEligibilityV2Result() {
        return getPBMEligibilityV2Result;
    }

    /**
     * Sets the value of the getPBMEligibilityV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link EligibilityDetailResult }
     *     
     */
    public void setGetPBMEligibilityV2Result(EligibilityDetailResult value) {
        this.getPBMEligibilityV2Result = value;
    }

}
