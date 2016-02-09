
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
 *         &lt;element name="GetPatientFullMedicationHistory3Result" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetailResult3" minOccurs="0"/>
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
    "getPatientFullMedicationHistory3Result"
})
@XmlRootElement(name = "GetPatientFullMedicationHistory3Response")
public class GetPatientFullMedicationHistory3Response {

    @XmlElement(name = "GetPatientFullMedicationHistory3Result")
    protected PatientDrugDetailResult3 getPatientFullMedicationHistory3Result;

    /**
     * Gets the value of the getPatientFullMedicationHistory3Result property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDrugDetailResult3 }
     *     
     */
    public PatientDrugDetailResult3 getGetPatientFullMedicationHistory3Result() {
        return getPatientFullMedicationHistory3Result;
    }

    /**
     * Sets the value of the getPatientFullMedicationHistory3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDrugDetailResult3 }
     *     
     */
    public void setGetPatientFullMedicationHistory3Result(PatientDrugDetailResult3 value) {
        this.getPatientFullMedicationHistory3Result = value;
    }

}
