
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.NCRenewal;


/**
 * <p>Java class for RenewalDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExternalLocationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalDoctorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="renewal" type="{http://secure.newcropaccounts.com/interfaceV7}NCRenewal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalDetail", propOrder = {
    "externalLocationId",
    "externalDoctorId",
    "renewal"
})
public class RenewalDetail {

    @XmlElement(name = "ExternalLocationId")
    protected String externalLocationId;
    @XmlElement(name = "ExternalDoctorId")
    protected String externalDoctorId;
    protected NCRenewal renewal;

    /**
     * Gets the value of the externalLocationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalLocationId() {
        return externalLocationId;
    }

    /**
     * Sets the value of the externalLocationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalLocationId(String value) {
        this.externalLocationId = value;
    }

    /**
     * Gets the value of the externalDoctorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDoctorId() {
        return externalDoctorId;
    }

    /**
     * Sets the value of the externalDoctorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDoctorId(String value) {
        this.externalDoctorId = value;
    }

    /**
     * Gets the value of the renewal property.
     * 
     * @return
     *     possible object is
     *     {@link NCRenewal }
     *     
     */
    public NCRenewal getRenewal() {
        return renewal;
    }

    /**
     * Sets the value of the renewal property.
     * 
     * @param value
     *     allowed object is
     *     {@link NCRenewal }
     *     
     */
    public void setRenewal(NCRenewal value) {
        this.renewal = value;
    }

}
