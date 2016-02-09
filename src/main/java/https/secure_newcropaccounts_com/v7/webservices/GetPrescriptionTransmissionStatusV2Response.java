
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
 *         &lt;element name="GetPrescriptionTransmissionStatusV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}TransmissionSummaryResult" minOccurs="0"/>
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
    "getPrescriptionTransmissionStatusV2Result"
})
@XmlRootElement(name = "GetPrescriptionTransmissionStatusV2Response")
public class GetPrescriptionTransmissionStatusV2Response {

    @XmlElement(name = "GetPrescriptionTransmissionStatusV2Result")
    protected TransmissionSummaryResult getPrescriptionTransmissionStatusV2Result;

    /**
     * Gets the value of the getPrescriptionTransmissionStatusV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link TransmissionSummaryResult }
     *     
     */
    public TransmissionSummaryResult getGetPrescriptionTransmissionStatusV2Result() {
        return getPrescriptionTransmissionStatusV2Result;
    }

    /**
     * Sets the value of the getPrescriptionTransmissionStatusV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransmissionSummaryResult }
     *     
     */
    public void setGetPrescriptionTransmissionStatusV2Result(TransmissionSummaryResult value) {
        this.getPrescriptionTransmissionStatusV2Result = value;
    }

}
