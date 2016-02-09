
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientAllergyExtendedDetailV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientAllergyExtendedDetailV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientAllergyExtendedDetailV4" type="{https://secure.newcropaccounts.com/V7/webservices}PatientAllergyExtendedDetailV4" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientAllergyExtendedDetailV4", propOrder = {
    "patientAllergyExtendedDetailV4"
})
public class ArrayOfPatientAllergyExtendedDetailV4 {

    @XmlElement(name = "PatientAllergyExtendedDetailV4", nillable = true)
    protected List<PatientAllergyExtendedDetailV4> patientAllergyExtendedDetailV4;

    /**
     * Gets the value of the patientAllergyExtendedDetailV4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientAllergyExtendedDetailV4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientAllergyExtendedDetailV4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientAllergyExtendedDetailV4 }
     * 
     * 
     */
    public List<PatientAllergyExtendedDetailV4> getPatientAllergyExtendedDetailV4() {
        if (patientAllergyExtendedDetailV4 == null) {
            patientAllergyExtendedDetailV4 = new ArrayList<PatientAllergyExtendedDetailV4>();
        }
        return this.patientAllergyExtendedDetailV4;
    }

}
