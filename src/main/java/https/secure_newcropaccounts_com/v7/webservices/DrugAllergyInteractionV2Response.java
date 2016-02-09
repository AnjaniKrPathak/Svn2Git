
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
 *         &lt;element name="DrugAllergyInteractionV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}DrugAllergyDetailResultV2" minOccurs="0"/>
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
    "drugAllergyInteractionV2Result"
})
@XmlRootElement(name = "DrugAllergyInteractionV2Response")
public class DrugAllergyInteractionV2Response {

    @XmlElement(name = "DrugAllergyInteractionV2Result")
    protected DrugAllergyDetailResultV2 drugAllergyInteractionV2Result;

    /**
     * Gets the value of the drugAllergyInteractionV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link DrugAllergyDetailResultV2 }
     *     
     */
    public DrugAllergyDetailResultV2 getDrugAllergyInteractionV2Result() {
        return drugAllergyInteractionV2Result;
    }

    /**
     * Sets the value of the drugAllergyInteractionV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugAllergyDetailResultV2 }
     *     
     */
    public void setDrugAllergyInteractionV2Result(DrugAllergyDetailResultV2 value) {
        this.drugAllergyInteractionV2Result = value;
    }

}
