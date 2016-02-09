
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
 *         &lt;element name="GetPatientFullMedicationHistory2Result" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetailResult2" minOccurs="0"/>
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
    "getPatientFullMedicationHistory2Result"
})
@XmlRootElement(name = "GetPatientFullMedicationHistory2Response")
public class GetPatientFullMedicationHistory2Response {

    @XmlElement(name = "GetPatientFullMedicationHistory2Result")
    protected PatientDrugDetailResult2 getPatientFullMedicationHistory2Result;

    /**
     * Gets the value of the getPatientFullMedicationHistory2Result property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDrugDetailResult2 }
     *     
     */
    public PatientDrugDetailResult2 getGetPatientFullMedicationHistory2Result() {
        return getPatientFullMedicationHistory2Result;
    }

    /**
     * Sets the value of the getPatientFullMedicationHistory2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDrugDetailResult2 }
     *     
     */
    public void setGetPatientFullMedicationHistory2Result(PatientDrugDetailResult2 value) {
        this.getPatientFullMedicationHistory2Result = value;
    }

}
