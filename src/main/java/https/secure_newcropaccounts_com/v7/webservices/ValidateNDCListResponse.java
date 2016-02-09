
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
 *         &lt;element name="ValidateNDCListResult" type="{https://secure.newcropaccounts.com/V7/webservices}NDCValidationDetailResult" minOccurs="0"/>
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
    "validateNDCListResult"
})
@XmlRootElement(name = "ValidateNDCListResponse")
public class ValidateNDCListResponse {

    @XmlElement(name = "ValidateNDCListResult")
    protected NDCValidationDetailResult validateNDCListResult;

    /**
     * Gets the value of the validateNDCListResult property.
     * 
     * @return
     *     possible object is
     *     {@link NDCValidationDetailResult }
     *     
     */
    public NDCValidationDetailResult getValidateNDCListResult() {
        return validateNDCListResult;
    }

    /**
     * Sets the value of the validateNDCListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link NDCValidationDetailResult }
     *     
     */
    public void setValidateNDCListResult(NDCValidationDetailResult value) {
        this.validateNDCListResult = value;
    }

}
