
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
 *         &lt;element name="GetPBMEligibilityV3Result" type="{https://secure.newcropaccounts.com/V7/webservices}EligibilityDetailResultV3" minOccurs="0"/>
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
    "getPBMEligibilityV3Result"
})
@XmlRootElement(name = "GetPBMEligibilityV3Response")
public class GetPBMEligibilityV3Response {

    @XmlElement(name = "GetPBMEligibilityV3Result")
    protected EligibilityDetailResultV3 getPBMEligibilityV3Result;

    /**
     * Gets the value of the getPBMEligibilityV3Result property.
     * 
     * @return
     *     possible object is
     *     {@link EligibilityDetailResultV3 }
     *     
     */
    public EligibilityDetailResultV3 getGetPBMEligibilityV3Result() {
        return getPBMEligibilityV3Result;
    }

    /**
     * Sets the value of the getPBMEligibilityV3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link EligibilityDetailResultV3 }
     *     
     */
    public void setGetPBMEligibilityV3Result(EligibilityDetailResultV3 value) {
        this.getPBMEligibilityV3Result = value;
    }

}
