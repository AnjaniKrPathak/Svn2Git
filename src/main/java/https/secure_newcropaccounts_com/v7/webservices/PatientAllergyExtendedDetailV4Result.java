
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientAllergyExtendedDetailV4Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyExtendedDetailV4Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="patientAllergyExtendedDetailV4" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfPatientAllergyExtendedDetailV4" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyExtendedDetailV4Result", propOrder = {
    "result",
    "patientAllergyExtendedDetailV4"
})
public class PatientAllergyExtendedDetailV4Result {

    protected Result result;
    protected ArrayOfPatientAllergyExtendedDetailV4 patientAllergyExtendedDetailV4;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the patientAllergyExtendedDetailV4 property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPatientAllergyExtendedDetailV4 }
     *     
     */
    public ArrayOfPatientAllergyExtendedDetailV4 getPatientAllergyExtendedDetailV4() {
        return patientAllergyExtendedDetailV4;
    }

    /**
     * Sets the value of the patientAllergyExtendedDetailV4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPatientAllergyExtendedDetailV4 }
     *     
     */
    public void setPatientAllergyExtendedDetailV4(ArrayOfPatientAllergyExtendedDetailV4 value) {
        this.patientAllergyExtendedDetailV4 = value;
    }

}
