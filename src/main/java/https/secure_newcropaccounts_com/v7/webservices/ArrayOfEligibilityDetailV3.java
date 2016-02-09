
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfEligibilityDetailV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfEligibilityDetailV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EligibilityDetailV3" type="{https://secure.newcropaccounts.com/V7/webservices}EligibilityDetailV3" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfEligibilityDetailV3", propOrder = {
    "eligibilityDetailV3"
})
public class ArrayOfEligibilityDetailV3 {

    @XmlElement(name = "EligibilityDetailV3", nillable = true)
    protected List<EligibilityDetailV3> eligibilityDetailV3;

    /**
     * Gets the value of the eligibilityDetailV3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibilityDetailV3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibilityDetailV3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EligibilityDetailV3 }
     * 
     * 
     */
    public List<EligibilityDetailV3> getEligibilityDetailV3() {
        if (eligibilityDetailV3 == null) {
            eligibilityDetailV3 = new ArrayList<EligibilityDetailV3>();
        }
        return this.eligibilityDetailV3;
    }

}
