
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugInteraction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugInteraction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MechanismOfAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Discussion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ClinicalEffects" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SeverityLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientManagement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PredisposingFactors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="References" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonographTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug1ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug1Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug2ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug2Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "DrugInteraction", propOrder = {
    "mechanismOfAction",
    "discussion",
    "clinicalEffects",
    "severityLevel",
    "patientManagement",
    "predisposingFactors",
    "references",
    "monographTitle",
    "drug1",
    "drug1ID",
    "drug1Type",
    "drug2",
    "drug2ID",
    "drug2Type",
    "performance"
})
public class DrugInteraction {

    @XmlElement(name = "MechanismOfAction")
    protected String mechanismOfAction;
    @XmlElement(name = "Discussion")
    protected String discussion;
    @XmlElement(name = "ClinicalEffects")
    protected String clinicalEffects;
    @XmlElement(name = "SeverityLevel")
    protected String severityLevel;
    @XmlElement(name = "PatientManagement")
    protected String patientManagement;
    @XmlElement(name = "PredisposingFactors")
    protected String predisposingFactors;
    @XmlElement(name = "References")
    protected String references;
    @XmlElement(name = "MonographTitle")
    protected String monographTitle;
    @XmlElement(name = "Drug1")
    protected String drug1;
    @XmlElement(name = "Drug1ID")
    protected String drug1ID;
    @XmlElement(name = "Drug1Type")
    protected String drug1Type;
    @XmlElement(name = "Drug2")
    protected String drug2;
    @XmlElement(name = "Drug2ID")
    protected String drug2ID;
    @XmlElement(name = "Drug2Type")
    protected String drug2Type;
    @XmlElement(name = "Performance")
    protected String performance;

    /**
     * Gets the value of the mechanismOfAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMechanismOfAction() {
        return mechanismOfAction;
    }

    /**
     * Sets the value of the mechanismOfAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMechanismOfAction(String value) {
        this.mechanismOfAction = value;
    }

    /**
     * Gets the value of the discussion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscussion() {
        return discussion;
    }

    /**
     * Sets the value of the discussion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscussion(String value) {
        this.discussion = value;
    }

    /**
     * Gets the value of the clinicalEffects property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClinicalEffects() {
        return clinicalEffects;
    }

    /**
     * Sets the value of the clinicalEffects property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClinicalEffects(String value) {
        this.clinicalEffects = value;
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
     * Gets the value of the patientManagement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientManagement() {
        return patientManagement;
    }

    /**
     * Sets the value of the patientManagement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientManagement(String value) {
        this.patientManagement = value;
    }

    /**
     * Gets the value of the predisposingFactors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPredisposingFactors() {
        return predisposingFactors;
    }

    /**
     * Sets the value of the predisposingFactors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPredisposingFactors(String value) {
        this.predisposingFactors = value;
    }

    /**
     * Gets the value of the references property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferences() {
        return references;
    }

    /**
     * Sets the value of the references property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferences(String value) {
        this.references = value;
    }

    /**
     * Gets the value of the monographTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonographTitle() {
        return monographTitle;
    }

    /**
     * Sets the value of the monographTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonographTitle(String value) {
        this.monographTitle = value;
    }

    /**
     * Gets the value of the drug1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug1() {
        return drug1;
    }

    /**
     * Sets the value of the drug1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug1(String value) {
        this.drug1 = value;
    }

    /**
     * Gets the value of the drug1ID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug1ID() {
        return drug1ID;
    }

    /**
     * Sets the value of the drug1ID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug1ID(String value) {
        this.drug1ID = value;
    }

    /**
     * Gets the value of the drug1Type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug1Type() {
        return drug1Type;
    }

    /**
     * Sets the value of the drug1Type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug1Type(String value) {
        this.drug1Type = value;
    }

    /**
     * Gets the value of the drug2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug2() {
        return drug2;
    }

    /**
     * Sets the value of the drug2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug2(String value) {
        this.drug2 = value;
    }

    /**
     * Gets the value of the drug2ID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug2ID() {
        return drug2ID;
    }

    /**
     * Sets the value of the drug2ID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug2ID(String value) {
        this.drug2ID = value;
    }

    /**
     * Gets the value of the drug2Type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrug2Type() {
        return drug2Type;
    }

    /**
     * Sets the value of the drug2Type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrug2Type(String value) {
        this.drug2Type = value;
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
