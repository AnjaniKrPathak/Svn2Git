
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
 *         &lt;element name="HealthplanSearchV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}HealthplanDetailResult" minOccurs="0"/>
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
    "healthplanSearchV2Result"
})
@XmlRootElement(name = "HealthplanSearchV2Response")
public class HealthplanSearchV2Response {

    @XmlElement(name = "HealthplanSearchV2Result")
    protected HealthplanDetailResult healthplanSearchV2Result;

    /**
     * Gets the value of the healthplanSearchV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link HealthplanDetailResult }
     *     
     */
    public HealthplanDetailResult getHealthplanSearchV2Result() {
        return healthplanSearchV2Result;
    }

    /**
     * Sets the value of the healthplanSearchV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link HealthplanDetailResult }
     *     
     */
    public void setHealthplanSearchV2Result(HealthplanDetailResult value) {
        this.healthplanSearchV2Result = value;
    }

}
