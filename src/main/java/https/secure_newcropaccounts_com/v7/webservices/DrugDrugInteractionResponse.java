
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
 *         &lt;element name="DrugDrugInteractionResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugInteractionResult" minOccurs="0"/>
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
    "drugDrugInteractionResult"
})
@XmlRootElement(name = "DrugDrugInteractionResponse")
public class DrugDrugInteractionResponse {

    @XmlElement(name = "DrugDrugInteractionResult")
    protected DrugInteractionResult drugDrugInteractionResult;

    /**
     * Gets the value of the drugDrugInteractionResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugInteractionResult }
     *     
     */
    public DrugInteractionResult getDrugDrugInteractionResult() {
        return drugDrugInteractionResult;
    }

    /**
     * Sets the value of the drugDrugInteractionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugInteractionResult }
     *     
     */
    public void setDrugDrugInteractionResult(DrugInteractionResult value) {
        this.drugDrugInteractionResult = value;
    }

}
