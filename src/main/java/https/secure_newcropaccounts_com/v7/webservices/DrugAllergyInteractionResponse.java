
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
 *         &lt;element name="DrugAllergyInteractionResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugAllergyDetailResult" minOccurs="0"/>
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
    "drugAllergyInteractionResult"
})
@XmlRootElement(name = "DrugAllergyInteractionResponse")
public class DrugAllergyInteractionResponse {

    @XmlElement(name = "DrugAllergyInteractionResult")
    protected DrugAllergyDetailResult drugAllergyInteractionResult;

    /**
     * Gets the value of the drugAllergyInteractionResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugAllergyDetailResult }
     *     
     */
    public DrugAllergyDetailResult getDrugAllergyInteractionResult() {
        return drugAllergyInteractionResult;
    }

    /**
     * Sets the value of the drugAllergyInteractionResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugAllergyDetailResult }
     *     
     */
    public void setDrugAllergyInteractionResult(DrugAllergyDetailResult value) {
        this.drugAllergyInteractionResult = value;
    }

}
