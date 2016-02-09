
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
 *         &lt;element name="PharmacySearchV4Result" type="{https://secure.newcropaccounts.com/V7/webservices}PharmacyDetailResultV4" minOccurs="0"/>
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
    "pharmacySearchV4Result"
})
@XmlRootElement(name = "PharmacySearchV4Response")
public class PharmacySearchV4Response {

    @XmlElement(name = "PharmacySearchV4Result")
    protected PharmacyDetailResultV4 pharmacySearchV4Result;

    /**
     * Gets the value of the pharmacySearchV4Result property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyDetailResultV4 }
     *     
     */
    public PharmacyDetailResultV4 getPharmacySearchV4Result() {
        return pharmacySearchV4Result;
    }

    /**
     * Sets the value of the pharmacySearchV4Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyDetailResultV4 }
     *     
     */
    public void setPharmacySearchV4Result(PharmacyDetailResultV4 value) {
        this.pharmacySearchV4Result = value;
    }

}
