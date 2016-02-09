
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugHistoryDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugHistoryDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugNdc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorContactNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyContactNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HealthplanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugHistoryDetail", propOrder = {
    "drugInfo",
    "drugNdc",
    "doctorLastName",
    "doctorFirstName",
    "doctorContactNumber",
    "pharmacyName",
    "pharmacyContactNumber",
    "fillDate",
    "source",
    "healthplanName",
    "drugId",
    "drugQuantity"
})
public class DrugHistoryDetail {

    @XmlElement(name = "DrugInfo")
    protected String drugInfo;
    @XmlElement(name = "DrugNdc")
    protected String drugNdc;
    @XmlElement(name = "DoctorLastName")
    protected String doctorLastName;
    @XmlElement(name = "DoctorFirstName")
    protected String doctorFirstName;
    @XmlElement(name = "DoctorContactNumber")
    protected String doctorContactNumber;
    @XmlElement(name = "PharmacyName")
    protected String pharmacyName;
    @XmlElement(name = "PharmacyContactNumber")
    protected String pharmacyContactNumber;
    @XmlElement(name = "FillDate")
    protected String fillDate;
    @XmlElement(name = "Source")
    protected String source;
    @XmlElement(name = "HealthplanName")
    protected String healthplanName;
    @XmlElement(name = "DrugId")
    protected String drugId;
    @XmlElement(name = "DrugQuantity")
    protected String drugQuantity;

    /**
     * Gets the value of the drugInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugInfo() {
        return drugInfo;
    }

    /**
     * Sets the value of the drugInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugInfo(String value) {
        this.drugInfo = value;
    }

    /**
     * Gets the value of the drugNdc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugNdc() {
        return drugNdc;
    }

    /**
     * Sets the value of the drugNdc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugNdc(String value) {
        this.drugNdc = value;
    }

    /**
     * Gets the value of the doctorLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorLastName() {
        return doctorLastName;
    }

    /**
     * Sets the value of the doctorLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorLastName(String value) {
        this.doctorLastName = value;
    }

    /**
     * Gets the value of the doctorFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    /**
     * Sets the value of the doctorFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorFirstName(String value) {
        this.doctorFirstName = value;
    }

    /**
     * Gets the value of the doctorContactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorContactNumber() {
        return doctorContactNumber;
    }

    /**
     * Sets the value of the doctorContactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorContactNumber(String value) {
        this.doctorContactNumber = value;
    }

    /**
     * Gets the value of the pharmacyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyName() {
        return pharmacyName;
    }

    /**
     * Sets the value of the pharmacyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyName(String value) {
        this.pharmacyName = value;
    }

    /**
     * Gets the value of the pharmacyContactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyContactNumber() {
        return pharmacyContactNumber;
    }

    /**
     * Sets the value of the pharmacyContactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyContactNumber(String value) {
        this.pharmacyContactNumber = value;
    }

    /**
     * Gets the value of the fillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFillDate() {
        return fillDate;
    }

    /**
     * Sets the value of the fillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFillDate(String value) {
        this.fillDate = value;
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
     * Gets the value of the healthplanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthplanName() {
        return healthplanName;
    }

    /**
     * Sets the value of the healthplanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthplanName(String value) {
        this.healthplanName = value;
    }

    /**
     * Gets the value of the drugId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugId() {
        return drugId;
    }

    /**
     * Sets the value of the drugId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugId(String value) {
        this.drugId = value;
    }

    /**
     * Gets the value of the drugQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugQuantity() {
        return drugQuantity;
    }

    /**
     * Sets the value of the drugQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugQuantity(String value) {
        this.drugQuantity = value;
    }

}
