
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataProvider" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Drug" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugSubID1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugNameID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GenericName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Dosage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DosageForm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Route" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TherapeuticClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaGenericNamedCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaGenericNamedDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaLegendCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaLegendDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Touchdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugDetail", propOrder = {
    "dataProvider",
    "drug",
    "drugID",
    "drugSubID1",
    "drugName",
    "drugNameID",
    "genericName",
    "deaClassCode",
    "dosage",
    "dosageForm",
    "route",
    "status",
    "therapeuticClass",
    "deaGenericNamedCode",
    "deaGenericNamedDescription",
    "deaLegendCode",
    "deaLegendDescription",
    "touchdate"
})
public class DrugDetail {

    @XmlElement(name = "DataProvider")
    protected String dataProvider;
    @XmlElement(name = "Drug")
    protected String drug;
    @XmlElement(name = "DrugID")
    protected String drugID;
    @XmlElement(name = "DrugSubID1")
    protected String drugSubID1;
    @XmlElement(name = "DrugName")
    protected String drugName;
    @XmlElement(name = "DrugNameID")
    protected String drugNameID;
    @XmlElement(name = "GenericName")
    protected String genericName;
    @XmlElement(name = "DeaClassCode")
    protected String deaClassCode;
    @XmlElement(name = "Dosage")
    protected String dosage;
    @XmlElement(name = "DosageForm")
    protected String dosageForm;
    @XmlElement(name = "Route")
    protected String route;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "TherapeuticClass")
    protected String therapeuticClass;
    @XmlElement(name = "DeaGenericNamedCode")
    protected String deaGenericNamedCode;
    @XmlElement(name = "DeaGenericNamedDescription")
    protected String deaGenericNamedDescription;
    @XmlElement(name = "DeaLegendCode")
    protected String deaLegendCode;
    @XmlElement(name = "DeaLegendDescription")
    protected String deaLegendDescription;
    @XmlElement(name = "Touchdate")
    protected String touchdate;

    /**
     * Gets the value of the dataProvider property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataProvider() {
        return dataProvider;
    }

    /**
     * Sets the value of the dataProvider property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataProvider(String value) {
        this.dataProvider = value;
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
     * Gets the value of the drugSubID1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugSubID1() {
        return drugSubID1;
    }

    /**
     * Sets the value of the drugSubID1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugSubID1(String value) {
        this.drugSubID1 = value;
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

    /**
     * Gets the value of the drugNameID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugNameID() {
        return drugNameID;
    }

    /**
     * Sets the value of the drugNameID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugNameID(String value) {
        this.drugNameID = value;
    }

    /**
     * Gets the value of the genericName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGenericName() {
        return genericName;
    }

    /**
     * Sets the value of the genericName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGenericName(String value) {
        this.genericName = value;
    }

    /**
     * Gets the value of the deaClassCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaClassCode() {
        return deaClassCode;
    }

    /**
     * Sets the value of the deaClassCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaClassCode(String value) {
        this.deaClassCode = value;
    }

    /**
     * Gets the value of the dosage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * Sets the value of the dosage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosage(String value) {
        this.dosage = value;
    }

    /**
     * Gets the value of the dosageForm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDosageForm() {
        return dosageForm;
    }

    /**
     * Sets the value of the dosageForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDosageForm(String value) {
        this.dosageForm = value;
    }

    /**
     * Gets the value of the route property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoute() {
        return route;
    }

    /**
     * Sets the value of the route property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoute(String value) {
        this.route = value;
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
     * Gets the value of the therapeuticClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTherapeuticClass() {
        return therapeuticClass;
    }

    /**
     * Sets the value of the therapeuticClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTherapeuticClass(String value) {
        this.therapeuticClass = value;
    }

    /**
     * Gets the value of the deaGenericNamedCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaGenericNamedCode() {
        return deaGenericNamedCode;
    }

    /**
     * Sets the value of the deaGenericNamedCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaGenericNamedCode(String value) {
        this.deaGenericNamedCode = value;
    }

    /**
     * Gets the value of the deaGenericNamedDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaGenericNamedDescription() {
        return deaGenericNamedDescription;
    }

    /**
     * Sets the value of the deaGenericNamedDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaGenericNamedDescription(String value) {
        this.deaGenericNamedDescription = value;
    }

    /**
     * Gets the value of the deaLegendCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaLegendCode() {
        return deaLegendCode;
    }

    /**
     * Sets the value of the deaLegendCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaLegendCode(String value) {
        this.deaLegendCode = value;
    }

    /**
     * Gets the value of the deaLegendDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaLegendDescription() {
        return deaLegendDescription;
    }

    /**
     * Sets the value of the deaLegendDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaLegendDescription(String value) {
        this.deaLegendDescription = value;
    }

    /**
     * Gets the value of the touchdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTouchdate() {
        return touchdate;
    }

    /**
     * Sets the value of the touchdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTouchdate(String value) {
        this.touchdate = value;
    }

}
