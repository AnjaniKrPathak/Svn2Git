
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
 *         &lt;element name="DrugDiseaseInteractionResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugDiseaseDetailResult" minOccurs="0"/>
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
    "drugDiseaseInteractionResult"
})
@XmlRootElement(name = "DrugDiseaseInteractionResponse")
public class DrugDiseaseInteractionResponse {

    @XmlElement(name = "DrugDiseaseInteractionResult")
    protected DrugDiseaseDetailResult drugDiseaseInteractionResult;

    /**
     * Gets the value of the drugDiseaseInteractionResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugDiseaseDetailResult }
     *     
     */
    public DrugDiseaseDetailResult getDrugDiseaseInteractionResult() {
        return drugDiseaseInteractionResult;
    }

    /**
     * Sets the value of the drugDiseaseInteractionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugDiseaseDetailResult }
     *     
     */
    public void setDrugDiseaseInteractionResult(DrugDiseaseDetailResult value) {
        this.drugDiseaseInteractionResult = value;
    }

}
