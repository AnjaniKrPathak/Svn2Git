
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
 *         &lt;element name="GetPatientAllergyHistoryV4Result" type="{https://secure.newcropaccounts.com/V7/webservices}PatientAllergyExtendedDetailV4Result" minOccurs="0"/>
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
    "getPatientAllergyHistoryV4Result"
})
@XmlRootElement(name = "GetPatientAllergyHistoryV4Response")
public class GetPatientAllergyHistoryV4Response {

    @XmlElement(name = "GetPatientAllergyHistoryV4Result")
    protected PatientAllergyExtendedDetailV4Result getPatientAllergyHistoryV4Result;

    /**
     * Gets the value of the getPatientAllergyHistoryV4Result property.
     * 
     * @return
     *     possible object is
     *     {@link PatientAllergyExtendedDetailV4Result }
     *     
     */
    public PatientAllergyExtendedDetailV4Result getGetPatientAllergyHistoryV4Result() {
        return getPatientAllergyHistoryV4Result;
    }

    /**
     * Sets the value of the getPatientAllergyHistoryV4Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientAllergyExtendedDetailV4Result }
     *     
     */
    public void setGetPatientAllergyHistoryV4Result(PatientAllergyExtendedDetailV4Result value) {
        this.getPatientAllergyHistoryV4Result = value;
    }

}
