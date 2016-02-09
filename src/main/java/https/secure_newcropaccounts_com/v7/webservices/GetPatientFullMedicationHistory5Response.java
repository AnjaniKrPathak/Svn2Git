
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
 *         &lt;element name="GetPatientFullMedicationHistory5Result" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetailResult5" minOccurs="0"/>
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
    "getPatientFullMedicationHistory5Result"
})
@XmlRootElement(name = "GetPatientFullMedicationHistory5Response")
public class GetPatientFullMedicationHistory5Response {

    @XmlElement(name = "GetPatientFullMedicationHistory5Result")
    protected PatientDrugDetailResult5 getPatientFullMedicationHistory5Result=new PatientDrugDetailResult5();

    /**
     * Gets the value of the getPatientFullMedicationHistory5Result property.
     * 
     * @return
     *     possible object is
     *     {@link PatientDrugDetailResult5 }
     *     
     */
    public PatientDrugDetailResult5 getGetPatientFullMedicationHistory5Result() {
        return getPatientFullMedicationHistory5Result;
    }

    /**
     * Sets the value of the getPatientFullMedicationHistory5Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatientDrugDetailResult5 }
     *     
     */
    public void setGetPatientFullMedicationHistory5Result(PatientDrugDetailResult5 value) {
        this.getPatientFullMedicationHistory5Result = value;
    }

}
