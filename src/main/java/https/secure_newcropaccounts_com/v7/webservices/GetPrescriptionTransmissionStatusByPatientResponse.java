
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
 *         &lt;element name="GetPrescriptionTransmissionStatusByPatientResult" type="{https://secure.newcropaccounts.com/V7/webservices}TransmissionSummaryResult" minOccurs="0"/>
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
    "getPrescriptionTransmissionStatusByPatientResult"
})
@XmlRootElement(name = "GetPrescriptionTransmissionStatusByPatientResponse")
public class GetPrescriptionTransmissionStatusByPatientResponse {

    @XmlElement(name = "GetPrescriptionTransmissionStatusByPatientResult")
    protected TransmissionSummaryResult getPrescriptionTransmissionStatusByPatientResult;

    /**
     * Gets the value of the getPrescriptionTransmissionStatusByPatientResult property.
     * 
     * @return
     *     possible object is
     *     {@link TransmissionSummaryResult }
     *     
     */
    public TransmissionSummaryResult getGetPrescriptionTransmissionStatusByPatientResult() {
        return getPrescriptionTransmissionStatusByPatientResult;
    }

    /**
     * Sets the value of the getPrescriptionTransmissionStatusByPatientResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransmissionSummaryResult }
     *     
     */
    public void setGetPrescriptionTransmissionStatusByPatientResult(TransmissionSummaryResult value) {
        this.getPrescriptionTransmissionStatusByPatientResult = value;
    }

}
