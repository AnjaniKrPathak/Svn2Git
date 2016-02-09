
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugHistoryDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugHistoryDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugHistoryDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugHistoryDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugHistoryDetail", propOrder = {
    "drugHistoryDetail"
})
public class ArrayOfDrugHistoryDetail {

    @XmlElement(name = "DrugHistoryDetail", nillable = true)
    protected List<DrugHistoryDetail> drugHistoryDetail;

    /**
     * Gets the value of the drugHistoryDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugHistoryDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugHistoryDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugHistoryDetail }
     * 
     * 
     */
    public List<DrugHistoryDetail> getDrugHistoryDetail() {
        if (drugHistoryDetail == null) {
            drugHistoryDetail = new ArrayList<DrugHistoryDetail>();
        }
        return this.drugHistoryDetail;
    }

}
