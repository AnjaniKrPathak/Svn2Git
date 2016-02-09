
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRenewalDetailV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRenewalDetailV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RenewalDetailV4" type="{https://secure.newcropaccounts.com/V7/webservices}RenewalDetailV4" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRenewalDetailV4", propOrder = {
    "renewalDetailV4"
})
public class ArrayOfRenewalDetailV4 {

    @XmlElement(name = "RenewalDetailV4", nillable = true)
    protected List<RenewalDetailV4> renewalDetailV4;

    /**
     * Gets the value of the renewalDetailV4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the renewalDetailV4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRenewalDetailV4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RenewalDetailV4 }
     * 
     * 
     */
    public List<RenewalDetailV4> getRenewalDetailV4() {
        if (renewalDetailV4 == null) {
            renewalDetailV4 = new ArrayList<RenewalDetailV4>();
        }
        return this.renewalDetailV4;
    }

}
