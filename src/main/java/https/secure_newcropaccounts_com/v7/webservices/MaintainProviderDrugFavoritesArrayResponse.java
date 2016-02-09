
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
 *         &lt;element name="MaintainProviderDrugFavoritesArrayResult" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
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
    "maintainProviderDrugFavoritesArrayResult"
})
@XmlRootElement(name = "MaintainProviderDrugFavoritesArrayResponse")
public class MaintainProviderDrugFavoritesArrayResponse {

    @XmlElement(name = "MaintainProviderDrugFavoritesArrayResult")
    protected Result maintainProviderDrugFavoritesArrayResult;

    /**
     * Gets the value of the maintainProviderDrugFavoritesArrayResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getMaintainProviderDrugFavoritesArrayResult() {
        return maintainProviderDrugFavoritesArrayResult;
    }

    /**
     * Sets the value of the maintainProviderDrugFavoritesArrayResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setMaintainProviderDrugFavoritesArrayResult(Result value) {
        this.maintainProviderDrugFavoritesArrayResult = value;
    }

}
