
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientAllergyExtendedDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyExtendedDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompositeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityTypeID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyExtendedDetail", propOrder = {
    "name",
    "compositeID",
    "conceptID",
    "source",
    "conceptTypeID",
    "status",
    "severityTypeID",
    "severityName",
    "notes"
})
public class PatientAllergyExtendedDetail {

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "CompositeID")
    protected String compositeID;
    @XmlElement(name = "ConceptID")
    protected String conceptID;
    @XmlElement(name = "Source")
    protected String source;
    @XmlElement(name = "ConceptTypeID")
    protected String conceptTypeID;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "SeverityTypeID")
    protected String severityTypeID;
    @XmlElement(name = "SeverityName")
    protected String severityName;
    @XmlElement(name = "Notes")
    protected String notes;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the compositeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompositeID() {
        return compositeID;
    }

    /**
     * Sets the value of the compositeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompositeID(String value) {
        this.compositeID = value;
    }

    /**
     * Gets the value of the conceptID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptID() {
        return conceptID;
    }

    /**
     * Sets the value of the conceptID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptID(String value) {
        this.conceptID = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the conceptTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptTypeID() {
        return conceptTypeID;
    }

    /**
     * Sets the value of the conceptTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptTypeID(String value) {
        this.conceptTypeID = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the severityTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityTypeID() {
        return severityTypeID;
    }

    /**
     * Sets the value of the severityTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityTypeID(String value) {
        this.severityTypeID = value;
    }

    /**
     * Gets the value of the severityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityName() {
        return severityName;
    }

    /**
     * Sets the value of the severityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityName(String value) {
        this.severityName = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

}
