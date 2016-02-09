
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
 *         &lt;element name="MaintainProviderDrugFavoritesResult" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
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
    "maintainProviderDrugFavoritesResult"
})
@XmlRootElement(name = "MaintainProviderDrugFavoritesResponse")
public class MaintainProviderDrugFavoritesResponse {

    @XmlElement(name = "MaintainProviderDrugFavoritesResult")
    protected Result maintainProviderDrugFavoritesResult;

    /**
     * Gets the value of the maintainProviderDrugFavoritesResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getMaintainProviderDrugFavoritesResult() {
        return maintainProviderDrugFavoritesResult;
    }

    /**
     * Sets the value of the maintainProviderDrugFavoritesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setMaintainProviderDrugFavoritesResult(Result value) {
        this.maintainProviderDrugFavoritesResult = value;
    }

}
