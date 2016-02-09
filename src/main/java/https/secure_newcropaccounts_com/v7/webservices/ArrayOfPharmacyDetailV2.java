
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPharmacyDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPharmacyDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PharmacyDetailV2" type="{https://secure.newcropaccounts.com/V7/webservices}PharmacyDetailV2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPharmacyDetailV2", propOrder = {
    "pharmacyDetailV2"
})
public class ArrayOfPharmacyDetailV2 {

    @XmlElement(name = "PharmacyDetailV2", nillable = true)
    protected List<PharmacyDetailV2> pharmacyDetailV2;

    /**
     * Gets the value of the pharmacyDetailV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pharmacyDetailV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPharmacyDetailV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PharmacyDetailV2 }
     * 
     * 
     */
    public List<PharmacyDetailV2> getPharmacyDetailV2() {
        if (pharmacyDetailV2 == null) {
            pharmacyDetailV2 = new ArrayList<PharmacyDetailV2>();
        }
        return this.pharmacyDetailV2;
    }

}
