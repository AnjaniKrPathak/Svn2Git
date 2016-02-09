
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRenewalSummaryV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRenewalSummaryV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RenewalSummaryV2" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalSummaryV2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRenewalSummaryV2", propOrder = {
    "renewalSummaryV2"
})
public class ArrayOfRenewalSummaryV2 {

    @XmlElement(name = "RenewalSummaryV2", nillable = true)
    protected List<RenewalSummaryV2> renewalSummaryV2;

    /**
     * Gets the value of the renewalSummaryV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the renewalSummaryV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRenewalSummaryV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RenewalSummaryV2 }
     * 
     * 
     */
    public List<RenewalSummaryV2> getRenewalSummaryV2() {
        if (renewalSummaryV2 == null) {
            renewalSummaryV2 = new ArrayList<RenewalSummaryV2>();
        }
        return this.renewalSummaryV2;
    }

}
