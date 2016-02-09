
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
 *         &lt;element name="AllLicensedPrescribersStatusResult" type="{https://secure.newcropaccounts.com/V7/webservices}RegisterPrescriberDetailResult" minOccurs="0"/>
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
    "allLicensedPrescribersStatusResult"
})
@XmlRootElement(name = "AllLicensedPrescribersStatusResponse")
public class AllLicensedPrescribersStatusResponse {

    @XmlElement(name = "AllLicensedPrescribersStatusResult")
    protected RegisterPrescriberDetailResult allLicensedPrescribersStatusResult;

    /**
     * Gets the value of the allLicensedPrescribersStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterPrescriberDetailResult }
     *     
     */
    public RegisterPrescriberDetailResult getAllLicensedPrescribersStatusResult() {
        return allLicensedPrescribersStatusResult;
    }

    /**
     * Sets the value of the allLicensedPrescribersStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterPrescriberDetailResult }
     *     
     */
    public void setAllLicensedPrescribersStatusResult(RegisterPrescriberDetailResult value) {
        this.allLicensedPrescribersStatusResult = value;
    }

}
