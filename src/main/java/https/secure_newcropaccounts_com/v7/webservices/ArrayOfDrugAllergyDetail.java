
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugAllergyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugAllergyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugAllergyDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugAllergyDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugAllergyDetail", propOrder = {
    "drugAllergyDetail"
})
public class ArrayOfDrugAllergyDetail {

    @XmlElement(name = "DrugAllergyDetail", nillable = true)
    protected List<DrugAllergyDetail> drugAllergyDetail;

    /**
     * Gets the value of the drugAllergyDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugAllergyDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugAllergyDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugAllergyDetail }
     * 
     * 
     */
    public List<DrugAllergyDetail> getDrugAllergyDetail() {
        if (drugAllergyDetail == null) {
            drugAllergyDetail = new ArrayList<DrugAllergyDetail>();
        }
        return this.drugAllergyDetail;
    }

}
