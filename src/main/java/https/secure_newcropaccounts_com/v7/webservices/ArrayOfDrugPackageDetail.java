
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfDrugPackageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfDrugPackageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugPackageDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugPackageDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfDrugPackageDetail", propOrder = {
    "drugPackageDetail"
})
public class ArrayOfDrugPackageDetail {

    @XmlElement(name = "DrugPackageDetail", nillable = true)
    protected List<DrugPackageDetail> drugPackageDetail;

    /**
     * Gets the value of the drugPackageDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the drugPackageDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDrugPackageDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DrugPackageDetail }
     * 
     * 
     */
    public List<DrugPackageDetail> getDrugPackageDetail() {
        if (drugPackageDetail == null) {
            drugPackageDetail = new ArrayList<DrugPackageDetail>();
        }
        return this.drugPackageDetail;
    }

}
