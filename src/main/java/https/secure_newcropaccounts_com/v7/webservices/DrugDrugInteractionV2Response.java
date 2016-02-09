
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
 *         &lt;element name="DrugDrugInteractionV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}DrugInteractionResult" minOccurs="0"/>
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
    "drugDrugInteractionV2Result"
})
@XmlRootElement(name = "DrugDrugInteractionV2Response")
public class DrugDrugInteractionV2Response {

    @XmlElement(name = "DrugDrugInteractionV2Result")
    protected DrugInteractionResult drugDrugInteractionV2Result;

    /**
     * Gets the value of the drugDrugInteractionV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link DrugInteractionResult }
     *     
     */
    public DrugInteractionResult getDrugDrugInteractionV2Result() {
        return drugDrugInteractionV2Result;
    }

    /**
     * Sets the value of the drugDrugInteractionV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugInteractionResult }
     *     
     */
    public void setDrugDrugInteractionV2Result(DrugInteractionResult value) {
        this.drugDrugInteractionV2Result = value;
    }

}
