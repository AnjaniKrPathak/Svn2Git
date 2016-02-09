
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugAllergyDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugAllergyDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugAllergyDetailV2" type="{https://secure.newcropaccounts.com/V7/webservices}DrugAllergyDetailV2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugAllergyDetailV2", propOrder = {
    "drugAllergyDetailV2"
})
public class ArrayOfDrugAllergyDetailV2 {

    @XmlElement(name = "DrugAllergyDetailV2", nillable = true)
    protected List<DrugAllergyDetailV2> drugAllergyDetailV2;

    /**
     * Gets the value of the drugAllergyDetailV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugAllergyDetailV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugAllergyDetailV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugAllergyDetailV2 }
     * 
     * 
     */
    public List<DrugAllergyDetailV2> getDrugAllergyDetailV2() {
        if (drugAllergyDetailV2 == null) {
            drugAllergyDetailV2 = new ArrayList<DrugAllergyDetailV2>();
        }
        return this.drugAllergyDetailV2;
    }

}
