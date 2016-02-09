
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientDrugDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientDrugDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="patientDrugDetail" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfPatientDrugDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDrugDetailResult", propOrder = {
    "result",
    "patientDrugDetail"
})
public class PatientDrugDetailResult {

    protected Result result;
    protected ArrayOfPatientDrugDetail patientDrugDetail;

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
     * Gets the value of the patientDrugDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPatientDrugDetail }
     *     
     */
    public ArrayOfPatientDrugDetail getPatientDrugDetail() {
        return patientDrugDetail;
    }

    /**
     * Sets the value of the patientDrugDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPatientDrugDetail }
     *     
     */
    public void setPatientDrugDetail(ArrayOfPatientDrugDetail value) {
        this.patientDrugDetail = value;
    }

}
