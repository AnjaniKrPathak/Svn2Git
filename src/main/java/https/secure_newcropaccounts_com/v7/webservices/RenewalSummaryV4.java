
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalSummaryV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalSummaryV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RenewalRequestGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceivedTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalSummaryV4", propOrder = {
    "renewalRequestGuid",
    "receivedTimestamp"
})
public class RenewalSummaryV4 {

    @XmlElement(name = "RenewalRequestGuid")
    protected String renewalRequestGuid;
    @XmlElement(name = "ReceivedTimestamp")
    protected String receivedTimestamp;

    /**
     * Gets the value of the renewalRequestGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalRequestGuid() {
        return renewalRequestGuid;
    }

    /**
     * Sets the value of the renewalRequestGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalRequestGuid(String value) {
        this.renewalRequestGuid = value;
    }

    /**
     * Gets the value of the receivedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedTimestamp() {
        return receivedTimestamp;
    }

    /**
     * Sets the value of the receivedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedTimestamp(String value) {
        this.receivedTimestamp = value;
    }

}
