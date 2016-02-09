
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPharmacyDetailV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPharmacyDetailV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PharmacyDetailV4" type="{https://secure.newcropaccounts.com/V7/webservices}PharmacyDetailV4" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPharmacyDetailV4", propOrder = {
    "pharmacyDetailV4"
})
public class ArrayOfPharmacyDetailV4 {

    @XmlElement(name = "PharmacyDetailV4", nillable = true)
    protected List<PharmacyDetailV4> pharmacyDetailV4;

    /**
     * Gets the value of the pharmacyDetailV4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pharmacyDetailV4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPharmacyDetailV4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PharmacyDetailV4 }
     * 
     * 
     */
    public List<PharmacyDetailV4> getPharmacyDetailV4() {
        if (pharmacyDetailV4 == null) {
            pharmacyDetailV4 = new ArrayList<PharmacyDetailV4>();
        }
        return this.pharmacyDetailV4;
    }

}
