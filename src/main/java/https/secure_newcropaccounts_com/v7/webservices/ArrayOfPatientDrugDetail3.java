
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientDrugDetail3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientDrugDetail3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientDrugDetail3" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetail3" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientDrugDetail3", propOrder = {
    "patientDrugDetail3"
})
public class ArrayOfPatientDrugDetail3 {

    @XmlElement(name = "PatientDrugDetail3", nillable = true)
    protected List<PatientDrugDetail3> patientDrugDetail3;

    /**
     * Gets the value of the patientDrugDetail3 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientDrugDetail3 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientDrugDetail3().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientDrugDetail3 }
     * 
     * 
     */
    public List<PatientDrugDetail3> getPatientDrugDetail3() {
        if (patientDrugDetail3 == null) {
            patientDrugDetail3 = new ArrayList<PatientDrugDetail3>();
        }
        return this.patientDrugDetail3;
    }

}
