
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugFormularyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugFormularyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugFormularyDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugFormularyDetail", propOrder = {
    "drugFormularyDetail"
})
public class ArrayOfDrugFormularyDetail {

    @XmlElement(name = "DrugFormularyDetail", nillable = true)
    protected List<DrugFormularyDetail> drugFormularyDetail;

    /**
     * Gets the value of the drugFormularyDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugFormularyDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugFormularyDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugFormularyDetail }
     * 
     * 
     */
    public List<DrugFormularyDetail> getDrugFormularyDetail() {
        if (drugFormularyDetail == null) {
            drugFormularyDetail = new ArrayList<DrugFormularyDetail>();
        }
        return this.drugFormularyDetail;
    }

}
