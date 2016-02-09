
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPatientDrugDetail5 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPatientDrugDetail5">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PatientDrugDetail5" type="{https://secure.newcropaccounts.com/V7/webservices}PatientDrugDetail5" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPatientDrugDetail5", propOrder = {
    "patientDrugDetail5"
})
public class ArrayOfPatientDrugDetail5 {

    @XmlElement(name = "PatientDrugDetail5", nillable = true)
    protected List<PatientDrugDetail5> patientDrugDetail5=new ArrayList<PatientDrugDetail5>();

    /**
     * Gets the value of the patientDrugDetail5 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the patientDrugDetail5 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPatientDrugDetail5().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PatientDrugDetail5 }
     * 
     * 
     */
    public List<PatientDrugDetail5> getPatientDrugDetail5() {
        if (patientDrugDetail5 == null) {
            patientDrugDetail5 = new ArrayList<PatientDrugDetail5>();
        }
        return this.patientDrugDetail5;
    }

}
