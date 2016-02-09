
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalListDetailResultV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalListDetailResultV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="renewalListDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfRenewalDetailV4" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalListDetailResultV4", propOrder = {
    "result",
    "renewalListDetailArray"
})
public class RenewalListDetailResultV4 {

    protected Result result;
    protected ArrayOfRenewalDetailV4 renewalListDetailArray;

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
     * Gets the value of the renewalListDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRenewalDetailV4 }
     *     
     */
    public ArrayOfRenewalDetailV4 getRenewalListDetailArray() {
        return renewalListDetailArray;
    }

    /**
     * Sets the value of the renewalListDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRenewalDetailV4 }
     *     
     */
    public void setRenewalListDetailArray(ArrayOfRenewalDetailV4 value) {
        this.renewalListDetailArray = value;
    }

}
