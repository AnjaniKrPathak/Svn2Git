
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientAllergyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Allergy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyConceptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergySourceID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyDetail", propOrder = {
    "allergy",
    "allergyID",
    "allergyConceptID",
    "allergySourceID"
})
public class PatientAllergyDetail {

    @XmlElement(name = "Allergy")
    protected String allergy;
    @XmlElement(name = "AllergyID")
    protected String allergyID;
    @XmlElement(name = "AllergyConceptID")
    protected String allergyConceptID;
    @XmlElement(name = "AllergySourceID")
    protected String allergySourceID;

    /**
     * Gets the value of the allergy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergy() {
        return allergy;
    }

    /**
     * Sets the value of the allergy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergy(String value) {
        this.allergy = value;
    }

    /**
     * Gets the value of the allergyID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyID() {
        return allergyID;
    }

    /**
     * Sets the value of the allergyID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyID(String value) {
        this.allergyID = value;
    }

    /**
     * Gets the value of the allergyConceptID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyConceptID() {
        return allergyConceptID;
    }

    /**
     * Sets the value of the allergyConceptID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyConceptID(String value) {
        this.allergyConceptID = value;
    }

    /**
     * Gets the value of the allergySourceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergySourceID() {
        return allergySourceID;
    }

    /**
     * Sets the value of the allergySourceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergySourceID(String value) {
        this.allergySourceID = value;
    }

}
