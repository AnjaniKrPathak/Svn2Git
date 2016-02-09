
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugFoodDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugFoodDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugFoodDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFoodDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugFoodDetail", propOrder = {
    "drugFoodDetail"
})
public class ArrayOfDrugFoodDetail {

    @XmlElement(name = "DrugFoodDetail", nillable = true)
    protected List<DrugFoodDetail> drugFoodDetail;

    /**
     * Gets the value of the drugFoodDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugFoodDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugFoodDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugFoodDetail }
     * 
     * 
     */
    public List<DrugFoodDetail> getDrugFoodDetail() {
        if (drugFoodDetail == null) {
            drugFoodDetail = new ArrayList<DrugFoodDetail>();
        }
        return this.drugFoodDetail;
    }

}
