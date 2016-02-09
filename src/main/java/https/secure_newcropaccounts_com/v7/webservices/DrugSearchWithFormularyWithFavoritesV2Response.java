
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
 *         &lt;element name="DrugSearchWithFormularyWithFavoritesV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyFavoriteDetailResult" minOccurs="0"/>
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
    "drugSearchWithFormularyWithFavoritesV2Result"
})
@XmlRootElement(name = "DrugSearchWithFormularyWithFavoritesV2Response")
public class DrugSearchWithFormularyWithFavoritesV2Response {

    @XmlElement(name = "DrugSearchWithFormularyWithFavoritesV2Result")
    protected DrugFormularyFavoriteDetailResult drugSearchWithFormularyWithFavoritesV2Result;

    /**
     * Gets the value of the drugSearchWithFormularyWithFavoritesV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFormularyFavoriteDetailResult }
     *     
     */
    public DrugFormularyFavoriteDetailResult getDrugSearchWithFormularyWithFavoritesV2Result() {
        return drugSearchWithFormularyWithFavoritesV2Result;
    }

    /**
     * Sets the value of the drugSearchWithFormularyWithFavoritesV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFormularyFavoriteDetailResult }
     *     
     */
    public void setDrugSearchWithFormularyWithFavoritesV2Result(DrugFormularyFavoriteDetailResult value) {
        this.drugSearchWithFormularyWithFavoritesV2Result = value;
    }

}
