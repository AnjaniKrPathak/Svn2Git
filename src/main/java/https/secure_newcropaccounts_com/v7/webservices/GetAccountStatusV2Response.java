
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
 *         &lt;element name="GetAccountStatusV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}AccountStatusDetailV2Result" minOccurs="0"/>
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
    "getAccountStatusV2Result"
})
@XmlRootElement(name = "GetAccountStatusV2Response")
public class GetAccountStatusV2Response {

    @XmlElement(name = "GetAccountStatusV2Result")
    protected AccountStatusDetailV2Result getAccountStatusV2Result;

    /**
     * Gets the value of the getAccountStatusV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link AccountStatusDetailV2Result }
     *     
     */
    public AccountStatusDetailV2Result getGetAccountStatusV2Result() {
        return getAccountStatusV2Result;
    }

    /**
     * Sets the value of the getAccountStatusV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountStatusDetailV2Result }
     *     
     */
    public void setGetAccountStatusV2Result(AccountStatusDetailV2Result value) {
        this.getAccountStatusV2Result = value;
    }

}
