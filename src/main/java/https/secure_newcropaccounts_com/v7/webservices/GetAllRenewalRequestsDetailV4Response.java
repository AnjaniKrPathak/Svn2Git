
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
 *         &lt;element name="GetAllRenewalRequestsDetailV4Result" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalListDetailResultV4" minOccurs="0"/>
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
    "getAllRenewalRequestsDetailV4Result"
})
@XmlRootElement(name = "GetAllRenewalRequestsDetailV4Response")
public class GetAllRenewalRequestsDetailV4Response {

    @XmlElement(name = "GetAllRenewalRequestsDetailV4Result")
    protected RenewalListDetailResultV4 getAllRenewalRequestsDetailV4Result;

    /**
     * Gets the value of the getAllRenewalRequestsDetailV4Result property.
     * 
     * @return
     *     possible object is
     *     {@link RenewalListDetailResultV4 }
     *     
     */
    public RenewalListDetailResultV4 getGetAllRenewalRequestsDetailV4Result() {
        return getAllRenewalRequestsDetailV4Result;
    }

    /**
     * Sets the value of the getAllRenewalRequestsDetailV4Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link RenewalListDetailResultV4 }
     *     
     */
    public void setGetAllRenewalRequestsDetailV4Result(RenewalListDetailResultV4 value) {
        this.getAllRenewalRequestsDetailV4Result = value;
    }

}
