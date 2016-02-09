
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientDrugDetail4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientDrugDetail4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientDrugDetail4" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetail4" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientDrugDetail4", propOrder = {
    "patientDrugDetail4"
})
public class ArrayOfPatientDrugDetail4 {

    @XmlElement(name = "PatientDrugDetail4", nillable = true)
    protected List<PatientDrugDetail4> patientDrugDetail4;

    /**
     * Gets the value of the patientDrugDetail4 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientDrugDetail4 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientDrugDetail4().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientDrugDetail4 }
     * 
     * 
     */
    public List<PatientDrugDetail4> getPatientDrugDetail4() {
        if (patientDrugDetail4 == null) {
            patientDrugDetail4 = new ArrayList<PatientDrugDetail4>();
        }
        return this.patientDrugDetail4;
    }

}
