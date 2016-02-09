
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormularyCoverageDetailV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormularyCoverageDetailV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drugConcept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formularyStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="formularyStatusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="therapeuticClassDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="therapeuticSubClassDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormularyCoverageDetailV3", propOrder = {
    "drugConcept",
    "formularyStatus",
    "formularyStatusDesc",
    "therapeuticClassDesc",
    "therapeuticSubClassDesc"
})
public class FormularyCoverageDetailV3 {

    protected String drugConcept;
    protected String formularyStatus;
    protected String formularyStatusDesc;
    protected String therapeuticClassDesc;
    protected String therapeuticSubClassDesc;

    /**
     * Gets the value of the drugConcept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugConcept() {
        return drugConcept;
    }

    /**
     * Sets the value of the drugConcept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugConcept(String value) {
        this.drugConcept = value;
    }

    /**
     * Gets the value of the formularyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormularyStatus() {
        return formularyStatus;
    }

    /**
     * Sets the value of the formularyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormularyStatus(String value) {
        this.formularyStatus = value;
    }

    /**
     * Gets the value of the formularyStatusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormularyStatusDesc() {
        return formularyStatusDesc;
    }

    /**
     * Sets the value of the formularyStatusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormularyStatusDesc(String value) {
        this.formularyStatusDesc = value;
    }

    /**
     * Gets the value of the therapeuticClassDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTherapeuticClassDesc() {
        return therapeuticClassDesc;
    }

    /**
     * Sets the value of the therapeuticClassDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTherapeuticClassDesc(String value) {
        this.therapeuticClassDesc = value;
    }

    /**
     * Gets the value of the therapeuticSubClassDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTherapeuticSubClassDesc() {
        return therapeuticSubClassDesc;
    }

    /**
     * Sets the value of the therapeuticSubClassDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTherapeuticSubClassDesc(String value) {
        this.therapeuticSubClassDesc = value;
    }

}
