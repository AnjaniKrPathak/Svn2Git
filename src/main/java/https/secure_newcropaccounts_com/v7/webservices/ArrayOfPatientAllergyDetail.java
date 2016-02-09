
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientAllergyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientAllergyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientAllergyDetail" type="{https://secure.newcropaccounts.com/V7/webservices}PatientAllergyDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientAllergyDetail", propOrder = {
    "patientAllergyDetail"
})
public class ArrayOfPatientAllergyDetail {

    @XmlElement(name = "PatientAllergyDetail", nillable = true)
    protected List<PatientAllergyDetail> patientAllergyDetail;

    /**
     * Gets the value of the patientAllergyDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientAllergyDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientAllergyDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientAllergyDetail }
     * 
     * 
     */
    public List<PatientAllergyDetail> getPatientAllergyDetail() {
        if (patientAllergyDetail == null) {
            patientAllergyDetail = new ArrayList<PatientAllergyDetail>();
        }
        return this.patientAllergyDetail;
    }

}
