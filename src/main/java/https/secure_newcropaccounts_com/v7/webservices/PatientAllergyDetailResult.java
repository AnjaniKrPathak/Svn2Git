
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientAllergyDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="patientAllergyDetail" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfPatientAllergyDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyDetailResult", propOrder = {
    "result",
    "patientAllergyDetail"
})
public class PatientAllergyDetailResult {

    protected Result result;
    protected ArrayOfPatientAllergyDetail patientAllergyDetail;

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
     * Gets the value of the patientAllergyDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPatientAllergyDetail }
     *     
     */
    public ArrayOfPatientAllergyDetail getPatientAllergyDetail() {
        return patientAllergyDetail;
    }

    /**
     * Sets the value of the patientAllergyDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPatientAllergyDetail }
     *     
     */
    public void setPatientAllergyDetail(ArrayOfPatientAllergyDetail value) {
        this.patientAllergyDetail = value;
    }

}
