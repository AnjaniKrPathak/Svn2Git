
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
 *         &lt;element name="GetPatientFullMedicationHistory4Result" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetailResult4" minOccurs="0"/>
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
    "getPatientFullMedicationHistory4Result"
})
@XmlRootElement(name = "GetPatientFullMedicationHistory4Response")
public class GetPatientFullMedicationHistory4Response {

    @XmlElement(name = "GetPatientFullMedicationHistory4Result")
    protected PatientDrugDetailResult4 getPatientFullMedicationHistory4Result;

    /**
     * Gets the value of the getPatientFullMedicationHistory4Result property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDrugDetailResult4 }
     *     
     */
    public PatientDrugDetailResult4 getGetPatientFullMedicationHistory4Result() {
        return getPatientFullMedicationHistory4Result;
    }

    /**
     * Sets the value of the getPatientFullMedicationHistory4Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDrugDetailResult4 }
     *     
     */
    public void setGetPatientFullMedicationHistory4Result(PatientDrugDetailResult4 value) {
        this.getPatientFullMedicationHistory4Result = value;
    }

}
