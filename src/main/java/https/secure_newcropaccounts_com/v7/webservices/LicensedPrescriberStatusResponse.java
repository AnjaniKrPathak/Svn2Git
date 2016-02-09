
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
 *         &lt;element name="LicensedPrescriberStatusResult" type="{https://secure.newcropaccounts.com/V7/webservices}RegisterPrescriberDetailResult" minOccurs="0"/>
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
    "licensedPrescriberStatusResult"
})
@XmlRootElement(name = "LicensedPrescriberStatusResponse")
public class LicensedPrescriberStatusResponse {

    @XmlElement(name = "LicensedPrescriberStatusResult")
    protected RegisterPrescriberDetailResult licensedPrescriberStatusResult;

    /**
     * Gets the value of the licensedPrescriberStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link RegisterPrescriberDetailResult }
     *     
     */
    public RegisterPrescriberDetailResult getLicensedPrescriberStatusResult() {
        return licensedPrescriberStatusResult;
    }

    /**
     * Sets the value of the licensedPrescriberStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegisterPrescriberDetailResult }
     *     
     */
    public void setLicensedPrescriberStatusResult(RegisterPrescriberDetailResult value) {
        this.licensedPrescriberStatusResult = value;
    }

}
