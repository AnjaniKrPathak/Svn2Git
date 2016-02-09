
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExternalDrugOverride complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExternalDrugOverride">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="externalDrugConcept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugStrength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugStrengthUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugStrengthWithUOM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugDosageForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugRoute" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugIdentifierType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalDrugOverTheCounter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExternalDrugOverride", propOrder = {
    "externalDrugConcept",
    "externalDrugName",
    "externalDrugStrength",
    "externalDrugStrengthUOM",
    "externalDrugStrengthWithUOM",
    "externalDrugDosageForm",
    "externalDrugRoute",
    "externalDrugIdentifier",
    "externalDrugIdentifierType",
    "externalDrugSchedule",
    "externalDrugOverTheCounter"
})
public class ExternalDrugOverride {

    protected String externalDrugConcept;
    protected String externalDrugName;
    protected String externalDrugStrength;
    protected String externalDrugStrengthUOM;
    protected String externalDrugStrengthWithUOM;
    protected String externalDrugDosageForm;
    protected String externalDrugRoute;
    protected String externalDrugIdentifier;
    protected String externalDrugIdentifierType;
    protected String externalDrugSchedule;
    protected String externalDrugOverTheCounter;

    /**
     * Gets the value of the externalDrugConcept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugConcept() {
        return externalDrugConcept;
    }

    /**
     * Sets the value of the externalDrugConcept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugConcept(String value) {
        this.externalDrugConcept = value;
    }

    /**
     * Gets the value of the externalDrugName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugName() {
        return externalDrugName;
    }

    /**
     * Sets the value of the externalDrugName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugName(String value) {
        this.externalDrugName = value;
    }

    /**
     * Gets the value of the externalDrugStrength property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugStrength() {
        return externalDrugStrength;
    }

    /**
     * Sets the value of the externalDrugStrength property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugStrength(String value) {
        this.externalDrugStrength = value;
    }

    /**
     * Gets the value of the externalDrugStrengthUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugStrengthUOM() {
        return externalDrugStrengthUOM;
    }

    /**
     * Sets the value of the externalDrugStrengthUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugStrengthUOM(String value) {
        this.externalDrugStrengthUOM = value;
    }

    /**
     * Gets the value of the externalDrugStrengthWithUOM property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugStrengthWithUOM() {
        return externalDrugStrengthWithUOM;
    }

    /**
     * Sets the value of the externalDrugStrengthWithUOM property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugStrengthWithUOM(String value) {
        this.externalDrugStrengthWithUOM = value;
    }

    /**
     * Gets the value of the externalDrugDosageForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugDosageForm() {
        return externalDrugDosageForm;
    }

    /**
     * Sets the value of the externalDrugDosageForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugDosageForm(String value) {
        this.externalDrugDosageForm = value;
    }

    /**
     * Gets the value of the externalDrugRoute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugRoute() {
        return externalDrugRoute;
    }

    /**
     * Sets the value of the externalDrugRoute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugRoute(String value) {
        this.externalDrugRoute = value;
    }

    /**
     * Gets the value of the externalDrugIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugIdentifier() {
        return externalDrugIdentifier;
    }

    /**
     * Sets the value of the externalDrugIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugIdentifier(String value) {
        this.externalDrugIdentifier = value;
    }

    /**
     * Gets the value of the externalDrugIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugIdentifierType() {
        return externalDrugIdentifierType;
    }

    /**
     * Sets the value of the externalDrugIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugIdentifierType(String value) {
        this.externalDrugIdentifierType = value;
    }

    /**
     * Gets the value of the externalDrugSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugSchedule() {
        return externalDrugSchedule;
    }

    /**
     * Sets the value of the externalDrugSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugSchedule(String value) {
        this.externalDrugSchedule = value;
    }

    /**
     * Gets the value of the externalDrugOverTheCounter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDrugOverTheCounter() {
        return externalDrugOverTheCounter;
    }

    /**
     * Sets the value of the externalDrugOverTheCounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDrugOverTheCounter(String value) {
        this.externalDrugOverTheCounter = value;
    }

}
