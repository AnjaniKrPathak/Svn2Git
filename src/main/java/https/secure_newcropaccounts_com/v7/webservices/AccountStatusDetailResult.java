
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountStatusDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountStatusDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="accountStatusDetail" type="{https://secure.newcropaccounts.com/V7/webservices}AccountStatusDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountStatusDetailResult", propOrder = {
    "result",
    "accountStatusDetail"
})
public class AccountStatusDetailResult {

    protected Result result;
    protected AccountStatusDetail accountStatusDetail;

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
     * Gets the value of the accountStatusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatusDetail }
     *     
     */
    public AccountStatusDetail getAccountStatusDetail() {
        return accountStatusDetail;
    }

    /**
     * Sets the value of the accountStatusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatusDetail }
     *     
     */
    public void setAccountStatusDetail(AccountStatusDetail value) {
        this.accountStatusDetail = value;
    }

}
