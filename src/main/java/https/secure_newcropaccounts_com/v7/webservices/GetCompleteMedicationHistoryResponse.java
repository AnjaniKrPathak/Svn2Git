
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
 *         &lt;element name="GetCompleteMedicationHistoryResult" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
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
    "getCompleteMedicationHistoryResult"
})
@XmlRootElement(name = "GetCompleteMedicationHistoryResponse")
public class GetCompleteMedicationHistoryResponse {

    @XmlElement(name = "GetCompleteMedicationHistoryResult")
    protected Result getCompleteMedicationHistoryResult;

    /**
     * Gets the value of the getCompleteMedicationHistoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getGetCompleteMedicationHistoryResult() {
        return getCompleteMedicationHistoryResult;
    }

    /**
     * Sets the value of the getCompleteMedicationHistoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setGetCompleteMedicationHistoryResult(Result value) {
        this.getCompleteMedicationHistoryResult = value;
    }

}
