
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountStatusDetailV2Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountStatusDetailV2Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="accountStatusDetailV2" type="{https://secure.newcropaccounts.com/V7/webservices}AccountStatusDetailV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountStatusDetailV2Result", propOrder = {
    "result",
    "accountStatusDetailV2"
})
public class AccountStatusDetailV2Result {

    protected Result result;
    protected AccountStatusDetailV2 accountStatusDetailV2;

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
     * Gets the value of the accountStatusDetailV2 property.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatusDetailV2 }
     *     
     */
    public AccountStatusDetailV2 getAccountStatusDetailV2() {
        return accountStatusDetailV2;
    }

    /**
     * Sets the value of the accountStatusDetailV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatusDetailV2 }
     *     
     */
    public void setAccountStatusDetailV2(AccountStatusDetailV2 value) {
        this.accountStatusDetailV2 = value;
    }

}
