
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientDrugNameDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientDrugNameDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDrugNameDetail", propOrder = {
    "drugID",
    "drugTypeID",
    "drugName"
})
public class PatientDrugNameDetail {

    @XmlElement(name = "DrugID")
    protected String drugID;
    @XmlElement(name = "DrugTypeID")
    protected String drugTypeID;
    @XmlElement(name = "DrugName")
    protected String drugName;

    /**
     * Gets the value of the drugID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugID() {
        return drugID;
    }

    /**
     * Sets the value of the drugID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugID(String value) {
        this.drugID = value;
    }

    /**
     * Gets the value of the drugTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugTypeID() {
        return drugTypeID;
    }

    /**
     * Sets the value of the drugTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugTypeID(String value) {
        this.drugTypeID = value;
    }

    /**
     * Gets the value of the drugName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * Sets the value of the drugName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugName(String value) {
        this.drugName = value;
    }

}
