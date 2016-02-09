
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugDiseaseDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugDiseaseDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ICD9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DirectCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RelatedCondition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityLevelText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityLevelShortText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiseaseRelation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiseaseRelationText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugDiseaseDetail", propOrder = {
    "drugName",
    "icd9",
    "directCondition",
    "relatedCondition",
    "severityLevel",
    "severityLevelText",
    "severityLevelShortText",
    "diseaseRelation",
    "diseaseRelationText"
})
public class DrugDiseaseDetail {

    @XmlElement(name = "DrugName")
    protected String drugName;
    @XmlElement(name = "ICD9")
    protected String icd9;
    @XmlElement(name = "DirectCondition")
    protected String directCondition;
    @XmlElement(name = "RelatedCondition")
    protected String relatedCondition;
    @XmlElement(name = "SeverityLevel")
    protected String severityLevel;
    @XmlElement(name = "SeverityLevelText")
    protected String severityLevelText;
    @XmlElement(name = "SeverityLevelShortText")
    protected String severityLevelShortText;
    @XmlElement(name = "DiseaseRelation")
    protected String diseaseRelation;
    @XmlElement(name = "DiseaseRelationText")
    protected String diseaseRelationText;

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

    /**
     * Gets the value of the icd9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getICD9() {
        return icd9;
    }

    /**
     * Sets the value of the icd9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setICD9(String value) {
        this.icd9 = value;
    }

    /**
     * Gets the value of the directCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectCondition() {
        return directCondition;
    }

    /**
     * Sets the value of the directCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectCondition(String value) {
        this.directCondition = value;
    }

    /**
     * Gets the value of the relatedCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedCondition() {
        return relatedCondition;
    }

    /**
     * Sets the value of the relatedCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedCondition(String value) {
        this.relatedCondition = value;
    }

    /**
     * Gets the value of the severityLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityLevel() {
        return severityLevel;
    }

    /**
     * Sets the value of the severityLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityLevel(String value) {
        this.severityLevel = value;
    }

    /**
     * Gets the value of the severityLevelText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityLevelText() {
        return severityLevelText;
    }

    /**
     * Sets the value of the severityLevelText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityLevelText(String value) {
        this.severityLevelText = value;
    }

    /**
     * Gets the value of the severityLevelShortText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverityLevelShortText() {
        return severityLevelShortText;
    }

    /**
     * Sets the value of the severityLevelShortText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverityLevelShortText(String value) {
        this.severityLevelShortText = value;
    }

    /**
     * Gets the value of the diseaseRelation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiseaseRelation() {
        return diseaseRelation;
    }

    /**
     * Sets the value of the diseaseRelation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiseaseRelation(String value) {
        this.diseaseRelation = value;
    }

    /**
     * Gets the value of the diseaseRelationText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiseaseRelationText() {
        return diseaseRelationText;
    }

    /**
     * Sets the value of the diseaseRelationText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiseaseRelationText(String value) {
        this.diseaseRelationText = value;
    }

}
