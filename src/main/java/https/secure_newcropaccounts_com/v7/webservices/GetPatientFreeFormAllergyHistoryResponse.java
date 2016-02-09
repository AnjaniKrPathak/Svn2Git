
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
 *         &lt;element name="GetPatientFreeFormAllergyHistoryResult" type="{https://secure.newcropaccounts.com/V7/webservices}PatientFreeFormAllergyExtendedDetailResult" minOccurs="0"/>
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
    "getPatientFreeFormAllergyHistoryResult"
})
@XmlRootElement(name = "GetPatientFreeFormAllergyHistoryResponse")
public class GetPatientFreeFormAllergyHistoryResponse {

    @XmlElement(name = "GetPatientFreeFormAllergyHistoryResult")
    protected PatientFreeFormAllergyExtendedDetailResult getPatientFreeFormAllergyHistoryResult;

    /**
     * Gets the value of the getPatientFreeFormAllergyHistoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link PatientFreeFormAllergyExtendedDetailResult }
     *     
     */
    public PatientFreeFormAllergyExtendedDetailResult getGetPatientFreeFormAllergyHistoryResult() {
        return getPatientFreeFormAllergyHistoryResult;
    }

    /**
     * Sets the value of the getPatientFreeFormAllergyHistoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientFreeFormAllergyExtendedDetailResult }
     *     
     */
    public void setGetPatientFreeFormAllergyHistoryResult(PatientFreeFormAllergyExtendedDetailResult value) {
        this.getPatientFreeFormAllergyHistoryResult = value;
    }

}
