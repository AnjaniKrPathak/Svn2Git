
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugFormularyFavoriteDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugFormularyFavoriteDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugFormularyFavoriteDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyFavoriteDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugFormularyFavoriteDetail", propOrder = {
    "drugFormularyFavoriteDetail"
})
public class ArrayOfDrugFormularyFavoriteDetail {

    @XmlElement(name = "DrugFormularyFavoriteDetail", nillable = true)
    protected List<DrugFormularyFavoriteDetail> drugFormularyFavoriteDetail;

    /**
     * Gets the value of the drugFormularyFavoriteDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugFormularyFavoriteDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugFormularyFavoriteDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugFormularyFavoriteDetail }
     * 
     * 
     */
    public List<DrugFormularyFavoriteDetail> getDrugFormularyFavoriteDetail() {
        if (drugFormularyFavoriteDetail == null) {
            drugFormularyFavoriteDetail = new ArrayList<DrugFormularyFavoriteDetail>();
        }
        return this.drugFormularyFavoriteDetail;
    }

}
