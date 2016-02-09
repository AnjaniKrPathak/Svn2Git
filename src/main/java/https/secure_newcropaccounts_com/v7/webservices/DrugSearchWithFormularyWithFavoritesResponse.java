
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
 *         &lt;element name="DrugSearchWithFormularyWithFavoritesResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyFavoriteDetailResult" minOccurs="0"/>
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
    "drugSearchWithFormularyWithFavoritesResult"
})
@XmlRootElement(name = "DrugSearchWithFormularyWithFavoritesResponse")
public class DrugSearchWithFormularyWithFavoritesResponse {

    @XmlElement(name = "DrugSearchWithFormularyWithFavoritesResult")
    protected DrugFormularyFavoriteDetailResult drugSearchWithFormularyWithFavoritesResult;

    /**
     * Gets the value of the drugSearchWithFormularyWithFavoritesResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFormularyFavoriteDetailResult }
     *     
     */
    public DrugFormularyFavoriteDetailResult getDrugSearchWithFormularyWithFavoritesResult() {
        return drugSearchWithFormularyWithFavoritesResult;
    }

    /**
     * Sets the value of the drugSearchWithFormularyWithFavoritesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFormularyFavoriteDetailResult }
     *     
     */
    public void setDrugSearchWithFormularyWithFavoritesResult(DrugFormularyFavoriteDetailResult value) {
        this.drugSearchWithFormularyWithFavoritesResult = value;
    }

}
