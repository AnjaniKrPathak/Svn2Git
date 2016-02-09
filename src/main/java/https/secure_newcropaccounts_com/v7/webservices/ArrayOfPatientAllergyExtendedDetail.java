
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientAllergyExtendedDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientAllergyExtendedDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientAllergyExtendedDetail" type="{https://secure.newcropaccounts.com/V7/webservices}PatientAllergyExtendedDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientAllergyExtendedDetail", propOrder = {
    "patientAllergyExtendedDetail"
})
public class ArrayOfPatientAllergyExtendedDetail {

    @XmlElement(name = "PatientAllergyExtendedDetail", nillable = true)
    protected List<PatientAllergyExtendedDetail> patientAllergyExtendedDetail;

    /**
     * Gets the value of the patientAllergyExtendedDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientAllergyExtendedDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientAllergyExtendedDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientAllergyExtendedDetail }
     * 
     * 
     */
    public List<PatientAllergyExtendedDetail> getPatientAllergyExtendedDetail() {
        if (patientAllergyExtendedDetail == null) {
            patientAllergyExtendedDetail = new ArrayList<PatientAllergyExtendedDetail>();
        }
        return this.patientAllergyExtendedDetail;
    }

}
