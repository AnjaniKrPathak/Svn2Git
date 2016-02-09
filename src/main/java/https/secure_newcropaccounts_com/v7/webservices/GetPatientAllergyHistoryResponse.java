
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
 *         &lt;element name="GetPatientAllergyHistoryResult" type="{https://secure.newcropaccounts.com/V7/webservices}PatientAllergyDetailResult" minOccurs="0"/>
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
    "getPatientAllergyHistoryResult"
})
@XmlRootElement(name = "GetPatientAllergyHistoryResponse")
public class GetPatientAllergyHistoryResponse {

    @XmlElement(name = "GetPatientAllergyHistoryResult")
    protected PatientAllergyDetailResult getPatientAllergyHistoryResult;

    /**
     * Gets the value of the getPatientAllergyHistoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link PatientAllergyDetailResult }
     *     
     */
    public PatientAllergyDetailResult getGetPatientAllergyHistoryResult() {
        return getPatientAllergyHistoryResult;
    }

    /**
     * Sets the value of the getPatientAllergyHistoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientAllergyDetailResult }
     *     
     */
    public void setGetPatientAllergyHistoryResult(PatientAllergyDetailResult value) {
        this.getPatientAllergyHistoryResult = value;
    }

}
