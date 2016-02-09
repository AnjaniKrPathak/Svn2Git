
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccountStatusDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountStatusDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PendingRxCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AlertCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FaxCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmComCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountStatusDetail", propOrder = {
    "pendingRxCount",
    "alertCount",
    "faxCount",
    "pharmComCount"
})
public class AccountStatusDetail {

    @XmlElement(name = "PendingRxCount")
    protected String pendingRxCount;
    @XmlElement(name = "AlertCount")
    protected String alertCount;
    @XmlElement(name = "FaxCount")
    protected String faxCount;
    @XmlElement(name = "PharmComCount")
    protected String pharmComCount;

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
     * Gets the value of the alertCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertCount() {
        return alertCount;
    }

    /**
     * Sets the value of the alertCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertCount(String value) {
        this.alertCount = value;
    }

    /**
     * Gets the value of the faxCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaxCount() {
        return faxCount;
    }

    /**
     * Sets the value of the faxCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaxCount(String value) {
        this.faxCount = value;
    }

    /**
     * Gets the value of the pharmComCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmComCount() {
        return pharmComCount;
    }

    /**
     * Sets the value of the pharmComCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmComCount(String value) {
        this.pharmComCount = value;
    }

}
