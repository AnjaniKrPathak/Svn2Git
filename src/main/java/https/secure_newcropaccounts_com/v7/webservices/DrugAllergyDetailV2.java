
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugAllergyDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugAllergyDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InteractionText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompositeAllergyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Performance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugAllergyDetailV2", propOrder = {
    "interactionText",
    "drug",
    "drugID",
    "drugType",
    "compositeAllergyId",
    "source",
    "conceptId",
    "conceptType",
    "description",
    "performance"
})
public class DrugAllergyDetailV2 {

    @XmlElement(name = "InteractionText")
    protected String interactionText;
    @XmlElement(name = "Drug")
    protected String drug;
    @XmlElement(name = "DrugID")
    protected String drugID;
    @XmlElement(name = "DrugType")
    protected String drugType;
    @XmlElement(name = "CompositeAllergyId")
    protected String compositeAllergyId;
    @XmlElement(name = "Source")
    protected String source;
    @XmlElement(name = "ConceptId")
    protected String conceptId;
    @XmlElement(name = "ConceptType")
    protected String conceptType;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Performance")
    protected String performance;

    /**
     * Gets the value of the interactionText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInteractionText() {
        return interactionText;
    }

    /**
     * Sets the value of the interactionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInteractionText(String value) {
        this.interactionText = value;
    }

    /**
     * Gets the value of the drug property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug() {
        return drug;
    }

    /**
     * Sets the value of the drug property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug(String value) {
        this.drug = value;
    }

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
     * Gets the value of the drugType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugType() {
        return drugType;
    }

    /**
     * Sets the value of the drugType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugType(String value) {
        this.drugType = value;
    }

    /**
     * Gets the value of the compositeAllergyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompositeAllergyId() {
        return compositeAllergyId;
    }

    /**
     * Sets the value of the compositeAllergyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompositeAllergyId(String value) {
        this.compositeAllergyId = value;
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
     * Gets the value of the conceptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptId() {
        return conceptId;
    }

    /**
     * Sets the value of the conceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptId(String value) {
        this.conceptId = value;
    }

    /**
     * Gets the value of the conceptType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptType() {
        return conceptType;
    }

    /**
     * Sets the value of the conceptType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptType(String value) {
        this.conceptType = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the performance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerformance() {
        return performance;
    }

    /**
     * Sets the value of the performance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerformance(String value) {
        this.performance = value;
    }

}
