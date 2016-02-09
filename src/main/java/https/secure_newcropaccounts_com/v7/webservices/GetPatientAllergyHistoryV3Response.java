
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
 *         &lt;element name="GetPatientAllergyHistoryV3Result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
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
    "getPatientAllergyHistoryV3Result"
})
@XmlRootElement(name = "GetPatientAllergyHistoryV3Response")
public class GetPatientAllergyHistoryV3Response {

    @XmlElement(name = "GetPatientAllergyHistoryV3Result")
    protected Result getPatientAllergyHistoryV3Result;

    /**
     * Gets the value of the getPatientAllergyHistoryV3Result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetPatientAllergyHistoryV3Result() {
        return getPatientAllergyHistoryV3Result;
    }

    /**
     * Sets the value of the getPatientAllergyHistoryV3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetPatientAllergyHistoryV3Result(Result value) {
        this.getPatientAllergyHistoryV3Result = value;
    }

}
