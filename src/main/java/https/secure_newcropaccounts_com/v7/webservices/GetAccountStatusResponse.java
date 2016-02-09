
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
 *         &lt;element name="GetAccountStatusResult" type="{https://secure.newcropaccounts.com/V7/webservices}AccountStatusDetailResult" minOccurs="0"/>
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
    "getAccountStatusResult"
})
@XmlRootElement(name = "GetAccountStatusResponse")
public class GetAccountStatusResponse {

    @XmlElement(name = "GetAccountStatusResult")
    protected AccountStatusDetailResult getAccountStatusResult;

    /**
     * Gets the value of the getAccountStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatusDetailResult }
     *     
     */
    public AccountStatusDetailResult getGetAccountStatusResult() {
        return getAccountStatusResult;
    }

    /**
     * Sets the value of the getAccountStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatusDetailResult }
     *     
     */
    public void setGetAccountStatusResult(AccountStatusDetailResult value) {
        this.getAccountStatusResult = value;
    }

}
