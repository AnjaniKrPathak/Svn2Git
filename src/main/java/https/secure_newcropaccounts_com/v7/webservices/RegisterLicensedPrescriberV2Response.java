
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
 *         &lt;element name="RegisterLicensedPrescriberV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}RegisterPrescriberDetailV2Result" minOccurs="0"/>
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
    "registerLicensedPrescriberV2Result"
})
@XmlRootElement(name = "RegisterLicensedPrescriberV2Response")
public class RegisterLicensedPrescriberV2Response {

    @XmlElement(name = "RegisterLicensedPrescriberV2Result")
    protected RegisterPrescriberDetailV2Result registerLicensedPrescriberV2Result;

    /**
     * Gets the value of the registerLicensedPrescriberV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterPrescriberDetailV2Result }
     *     
     */
    public RegisterPrescriberDetailV2Result getRegisterLicensedPrescriberV2Result() {
        return registerLicensedPrescriberV2Result;
    }

    /**
     * Sets the value of the registerLicensedPrescriberV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterPrescriberDetailV2Result }
     *     
     */
    public void setRegisterLicensedPrescriberV2Result(RegisterPrescriberDetailV2Result value) {
        this.registerLicensedPrescriberV2Result = value;
    }

}
