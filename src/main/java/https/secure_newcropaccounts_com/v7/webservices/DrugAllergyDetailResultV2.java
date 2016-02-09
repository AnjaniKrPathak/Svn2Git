
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugAllergyDetailResultV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugAllergyDetailResultV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="drugAllergyDetailArrayV2" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfDrugAllergyDetailV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugAllergyDetailResultV2", propOrder = {
    "result",
    "drugAllergyDetailArrayV2"
})
public class DrugAllergyDetailResultV2 {

    protected Result result;
    protected ArrayOfDrugAllergyDetailV2 drugAllergyDetailArrayV2;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the drugAllergyDetailArrayV2 property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDrugAllergyDetailV2 }
     *     
     */
    public ArrayOfDrugAllergyDetailV2 getDrugAllergyDetailArrayV2() {
        return drugAllergyDetailArrayV2;
    }

    /**
     * Sets the value of the drugAllergyDetailArrayV2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDrugAllergyDetailV2 }
     *     
     */
    public void setDrugAllergyDetailArrayV2(ArrayOfDrugAllergyDetailV2 value) {
        this.drugAllergyDetailArrayV2 = value;
    }

}
