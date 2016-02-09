
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalSummaryResultV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalSummaryResultV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="renewalSummaryArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfRenewalSummaryV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalSummaryResultV2", propOrder = {
    "result",
    "renewalSummaryArray"
})
public class RenewalSummaryResultV2 {

    protected Result result;
    protected ArrayOfRenewalSummaryV2 renewalSummaryArray;

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
     * Gets the value of the renewalSummaryArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRenewalSummaryV2 }
     *     
     */
    public ArrayOfRenewalSummaryV2 getRenewalSummaryArray() {
        return renewalSummaryArray;
    }

    /**
     * Sets the value of the renewalSummaryArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRenewalSummaryV2 }
     *     
     */
    public void setRenewalSummaryArray(ArrayOfRenewalSummaryV2 value) {
        this.renewalSummaryArray = value;
    }

}
