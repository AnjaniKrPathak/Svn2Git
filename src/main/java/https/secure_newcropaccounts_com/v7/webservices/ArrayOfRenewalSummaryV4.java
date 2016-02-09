
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRenewalSummaryV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRenewalSummaryV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RenewalSummaryV4" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalSummaryV4" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRenewalSummaryV4", propOrder = {
    "renewalSummaryV4"
})
public class ArrayOfRenewalSummaryV4 {

    @XmlElement(name = "RenewalSummaryV4", nillable = true)
    protected List<RenewalSummaryV4> renewalSummaryV4;

    /**
     * Gets the value of the renewalSummaryV4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the renewalSummaryV4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRenewalSummaryV4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RenewalSummaryV4 }
     * 
     * 
     */
    public List<RenewalSummaryV4> getRenewalSummaryV4() {
        if (renewalSummaryV4 == null) {
            renewalSummaryV4 = new ArrayList<RenewalSummaryV4>();
        }
        return this.renewalSummaryV4;
    }

}
