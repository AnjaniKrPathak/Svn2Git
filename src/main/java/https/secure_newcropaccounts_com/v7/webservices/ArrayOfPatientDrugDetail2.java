
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientDrugDetail2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientDrugDetail2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientDrugDetail2" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetail2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientDrugDetail2", propOrder = {
    "patientDrugDetail2"
})
public class ArrayOfPatientDrugDetail2 {

    @XmlElement(name = "PatientDrugDetail2", nillable = true)
    protected List<PatientDrugDetail2> patientDrugDetail2;

    /**
     * Gets the value of the patientDrugDetail2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientDrugDetail2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientDrugDetail2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientDrugDetail2 }
     * 
     * 
     */
    public List<PatientDrugDetail2> getPatientDrugDetail2() {
        if (patientDrugDetail2 == null) {
            patientDrugDetail2 = new ArrayList<PatientDrugDetail2>();
        }
        return this.patientDrugDetail2;
    }

}
