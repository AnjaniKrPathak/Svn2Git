
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="renewalDetail" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalDetailResult", propOrder = {
    "result",
    "renewalDetail"
})
public class RenewalDetailResult {

    protected Result result;
    protected RenewalDetail renewalDetail;

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
     * Gets the value of the renewalDetail property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalDetail }
     *     
     */
    public RenewalDetail getRenewalDetail() {
        return renewalDetail;
    }

    /**
     * Sets the value of the renewalDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalDetail }
     *     
     */
    public void setRenewalDetail(RenewalDetail value) {
        this.renewalDetail = value;
    }

}
