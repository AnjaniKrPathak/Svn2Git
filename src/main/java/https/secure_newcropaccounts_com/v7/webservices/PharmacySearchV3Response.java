
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
 *         &lt;element name="PharmacySearchV3Result" type="{https://secure.newcropaccounts.com/V7/webservices}PharmacyDetailResultV2" minOccurs="0"/>
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
    "pharmacySearchV3Result"
})
@XmlRootElement(name = "PharmacySearchV3Response")
public class PharmacySearchV3Response {

    @XmlElement(name = "PharmacySearchV3Result")
    protected PharmacyDetailResultV2 pharmacySearchV3Result;

    /**
     * Gets the value of the pharmacySearchV3Result property.
     * 
     * @return
     *     possible object is
     *     {@link PharmacyDetailResultV2 }
     *     
     */
    public PharmacyDetailResultV2 getPharmacySearchV3Result() {
        return pharmacySearchV3Result;
    }

    /**
     * Sets the value of the pharmacySearchV3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link PharmacyDetailResultV2 }
     *     
     */
    public void setPharmacySearchV3Result(PharmacyDetailResultV2 value) {
        this.pharmacySearchV3Result = value;
    }

}
