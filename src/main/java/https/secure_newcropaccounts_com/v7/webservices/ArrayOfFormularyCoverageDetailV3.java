
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfFormularyCoverageDetailV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFormularyCoverageDetailV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormularyCoverageDetailV3" type="{https://secure.newcropaccounts.com/V7/webservices}FormularyCoverageDetailV3" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFormularyCoverageDetailV3", propOrder = {
    "formularyCoverageDetailV3"
})
public class ArrayOfFormularyCoverageDetailV3 {

    @XmlElement(name = "FormularyCoverageDetailV3", nillable = true)
    protected List<FormularyCoverageDetailV3> formularyCoverageDetailV3;

    /**
     * Gets the value of the formularyCoverageDetailV3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formularyCoverageDetailV3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormularyCoverageDetailV3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FormularyCoverageDetailV3 }
     * 
     * 
     */
    public List<FormularyCoverageDetailV3> getFormularyCoverageDetailV3() {
        if (formularyCoverageDetailV3 == null) {
            formularyCoverageDetailV3 = new ArrayList<FormularyCoverageDetailV3>();
        }
        return this.formularyCoverageDetailV3;
    }

}
