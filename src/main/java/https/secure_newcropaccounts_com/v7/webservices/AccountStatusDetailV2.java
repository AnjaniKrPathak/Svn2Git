
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountStatusDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountStatusDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PendingRxCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnreviewedFailedFaxCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnansweredRenewalRequestCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnreadClinicalMessageCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnreviewedLabResultCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountStatusDetailV2", propOrder = {
    "pendingRxCount",
    "unreviewedFailedFaxCount",
    "unansweredRenewalRequestCount",
    "unreadClinicalMessageCount",
    "unreviewedLabResultCount",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5"
})
public class AccountStatusDetailV2 {

    @XmlElement(name = "PendingRxCount")
    protected String pendingRxCount;
    @XmlElement(name = "UnreviewedFailedFaxCount")
    protected String unreviewedFailedFaxCount;
    @XmlElement(name = "UnansweredRenewalRequestCount")
    protected String unansweredRenewalRequestCount;
    @XmlElement(name = "UnreadClinicalMessageCount")
    protected String unreadClinicalMessageCount;
    @XmlElement(name = "UnreviewedLabResultCount")
    protected String unreviewedLabResultCount;
    @XmlElement(name = "Spare1")
    protected String spare1;
    @XmlElement(name = "Spare2")
    protected String spare2;
    @XmlElement(name = "Spare3")
    protected String spare3;
    @XmlElement(name = "Spare4")
    protected String spare4;
    @XmlElement(name = "Spare5")
    protected String spare5;

    /**
     * Gets the value of the pendingRxCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPendingRxCount() {
        return pendingRxCount;
    }

    /**
     * Sets the value of the pendingRxCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPendingRxCount(String value) {
        this.pendingRxCount = value;
    }

    /**
     * Gets the value of the unreviewedFailedFaxCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnreviewedFailedFaxCount() {
        return unreviewedFailedFaxCount;
    }

    /**
     * Sets the value of the unreviewedFailedFaxCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnreviewedFailedFaxCount(String value) {
        this.unreviewedFailedFaxCount = value;
    }

    /**
     * Gets the value of the unansweredRenewalRequestCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnansweredRenewalRequestCount() {
        return unansweredRenewalRequestCount;
    }

    /**
     * Sets the value of the unansweredRenewalRequestCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnansweredRenewalRequestCount(String value) {
        this.unansweredRenewalRequestCount = value;
    }

    /**
     * Gets the value of the unreadClinicalMessageCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnreadClinicalMessageCount() {
        return unreadClinicalMessageCount;
    }

    /**
     * Sets the value of the unreadClinicalMessageCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnreadClinicalMessageCount(String value) {
        this.unreadClinicalMessageCount = value;
    }

    /**
     * Gets the value of the unreviewedLabResultCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnreviewedLabResultCount() {
        return unreviewedLabResultCount;
    }

    /**
     * Sets the value of the unreviewedLabResultCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnreviewedLabResultCount(String value) {
        this.unreviewedLabResultCount = value;
    }

    /**
     * Gets the value of the spare1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare1() {
        return spare1;
    }

    /**
     * Sets the value of the spare1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare1(String value) {
        this.spare1 = value;
    }

    /**
     * Gets the value of the spare2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare2() {
        return spare2;
    }

    /**
     * Sets the value of the spare2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare2(String value) {
        this.spare2 = value;
    }

    /**
     * Gets the value of the spare3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare3() {
        return spare3;
    }

    /**
     * Sets the value of the spare3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare3(String value) {
        this.spare3 = value;
    }

    /**
     * Gets the value of the spare4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare4() {
        return spare4;
    }

    /**
     * Sets the value of the spare4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare4(String value) {
        this.spare4 = value;
    }

    /**
     * Gets the value of the spare5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare5() {
        return spare5;
    }

    /**
     * Sets the value of the spare5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare5(String value) {
        this.spare5 = value;
    }

}
