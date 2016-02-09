
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
 *         &lt;element name="DrugFoodInteractionResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFoodDetailResult" minOccurs="0"/>
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
    "drugFoodInteractionResult"
})
@XmlRootElement(name = "DrugFoodInteractionResponse")
public class DrugFoodInteractionResponse {

    @XmlElement(name = "DrugFoodInteractionResult")
    protected DrugFoodDetailResult drugFoodInteractionResult;

    /**
     * Gets the value of the drugFoodInteractionResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFoodDetailResult }
     *     
     */
    public DrugFoodDetailResult getDrugFoodInteractionResult() {
        return drugFoodInteractionResult;
    }

    /**
     * Sets the value of the drugFoodInteractionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFoodDetailResult }
     *     
     */
    public void setDrugFoodInteractionResult(DrugFoodDetailResult value) {
        this.drugFoodInteractionResult = value;
    }

}
