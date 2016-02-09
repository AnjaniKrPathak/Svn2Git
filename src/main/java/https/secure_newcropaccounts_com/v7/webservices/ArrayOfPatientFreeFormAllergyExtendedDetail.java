
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientFreeFormAllergyExtendedDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientFreeFormAllergyExtendedDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientFreeFormAllergyExtendedDetail" type="{https://secure.newcropaccounts.com/V7/webservices}PatientFreeFormAllergyExtendedDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientFreeFormAllergyExtendedDetail", propOrder = {
    "patientFreeFormAllergyExtendedDetail"
})
public class ArrayOfPatientFreeFormAllergyExtendedDetail {

    @XmlElement(name = "PatientFreeFormAllergyExtendedDetail", nillable = true)
    protected List<PatientFreeFormAllergyExtendedDetail> patientFreeFormAllergyExtendedDetail;

    /**
     * Gets the value of the patientFreeFormAllergyExtendedDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientFreeFormAllergyExtendedDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientFreeFormAllergyExtendedDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientFreeFormAllergyExtendedDetail }
     * 
     * 
     */
    public List<PatientFreeFormAllergyExtendedDetail> getPatientFreeFormAllergyExtendedDetail() {
        if (patientFreeFormAllergyExtendedDetail == null) {
            patientFreeFormAllergyExtendedDetail = new ArrayList<PatientFreeFormAllergyExtendedDetail>();
        }
        return this.patientFreeFormAllergyExtendedDetail;
    }

}
