
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PatientAllergyExtendedDetailV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientAllergyExtendedDetailV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AllergyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompositeAllergyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergySourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyConceptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergySeverityTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergySeverityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllergyNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConceptTypeId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Rxcui" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OnsetDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecordedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SnomedConceptId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientAllergyExtendedDetailV4", propOrder = {
    "allergyType",
    "compositeAllergyId",
    "allergySourceId",
    "allergyId",
    "allergyConceptId",
    "conceptType",
    "allergyName",
    "status",
    "allergySeverityTypeId",
    "allergySeverityName",
    "allergyNotes",
    "conceptId",
    "conceptTypeId",
    "rxcui",
    "onsetDate",
    "recordedDate",
    "endDate",
    "snomedConceptId",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5"
})
public class PatientAllergyExtendedDetailV4 {

    @XmlElement(name = "AllergyType")
    protected String allergyType;
    @XmlElement(name = "CompositeAllergyId")
    protected String compositeAllergyId;
    @XmlElement(name = "AllergySourceId")
    protected String allergySourceId;
    @XmlElement(name = "AllergyId")
    protected String allergyId;
    @XmlElement(name = "AllergyConceptId")
    protected String allergyConceptId;
    @XmlElement(name = "ConceptType")
    protected String conceptType;
    @XmlElement(name = "AllergyName")
    protected String allergyName;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "AllergySeverityTypeId")
    protected String allergySeverityTypeId;
    @XmlElement(name = "AllergySeverityName")
    protected String allergySeverityName;
    @XmlElement(name = "AllergyNotes")
    protected String allergyNotes;
    @XmlElement(name = "ConceptId")
    protected String conceptId;
    @XmlElement(name = "ConceptTypeId")
    protected String conceptTypeId;
    @XmlElement(name = "Rxcui")
    protected String rxcui;
    @XmlElement(name = "OnsetDate")
    protected String onsetDate;
    @XmlElement(name = "RecordedDate")
    protected String recordedDate;
    @XmlElement(name = "EndDate")
    protected String endDate;
    @XmlElement(name = "SnomedConceptId")
    protected String snomedConceptId;
    @XmlElement(name = "Spare1")
    protected String spare1;
    @XmlElement(name = "Spare2")
    protected String spare2;
    @XmlElement(name = "Spare3")
    protected String spare3;
    @XmlElement(name = "Spare4")
    protected String spare4;
    @XmlElement(name = "Spare5")
    protected String spare5;

    /**
     * Gets the value of the allergyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyType() {
        return allergyType;
    }

    /**
     * Sets the value of the allergyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyType(String value) {
        this.allergyType = value;
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
     * Gets the value of the allergySourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergySourceId() {
        return allergySourceId;
    }

    /**
     * Sets the value of the allergySourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergySourceId(String value) {
        this.allergySourceId = value;
    }

    /**
     * Gets the value of the allergyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyId() {
        return allergyId;
    }

    /**
     * Sets the value of the allergyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyId(String value) {
        this.allergyId = value;
    }

    /**
     * Gets the value of the allergyConceptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyConceptId() {
        return allergyConceptId;
    }

    /**
     * Sets the value of the allergyConceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyConceptId(String value) {
        this.allergyConceptId = value;
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
     * Gets the value of the allergyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyName() {
        return allergyName;
    }

    /**
     * Sets the value of the allergyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyName(String value) {
        this.allergyName = value;
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
     * Gets the value of the allergySeverityTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergySeverityTypeId() {
        return allergySeverityTypeId;
    }

    /**
     * Sets the value of the allergySeverityTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergySeverityTypeId(String value) {
        this.allergySeverityTypeId = value;
    }

    /**
     * Gets the value of the allergySeverityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergySeverityName() {
        return allergySeverityName;
    }

    /**
     * Sets the value of the allergySeverityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergySeverityName(String value) {
        this.allergySeverityName = value;
    }

    /**
     * Gets the value of the allergyNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllergyNotes() {
        return allergyNotes;
    }

    /**
     * Sets the value of the allergyNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllergyNotes(String value) {
        this.allergyNotes = value;
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
     * Gets the value of the conceptTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConceptTypeId() {
        return conceptTypeId;
    }

    /**
     * Sets the value of the conceptTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConceptTypeId(String value) {
        this.conceptTypeId = value;
    }

    /**
     * Gets the value of the rxcui property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRxcui() {
        return rxcui;
    }

    /**
     * Sets the value of the rxcui property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRxcui(String value) {
        this.rxcui = value;
    }

    /**
     * Gets the value of the onsetDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnsetDate() {
        return onsetDate;
    }

    /**
     * Sets the value of the onsetDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnsetDate(String value) {
        this.onsetDate = value;
    }

    /**
     * Gets the value of the recordedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordedDate() {
        return recordedDate;
    }

    /**
     * Sets the value of the recordedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordedDate(String value) {
        this.recordedDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDate(String value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the snomedConceptId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnomedConceptId() {
        return snomedConceptId;
    }

    /**
     * Sets the value of the snomedConceptId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnomedConceptId(String value) {
        this.snomedConceptId = value;
    }

    /**
     * Gets the value of the spare1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare1() {
        return spare1;
    }

    /**
     * Sets the value of the spare1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare1(String value) {
        this.spare1 = value;
    }

    /**
     * Gets the value of the spare2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare2() {
        return spare2;
    }

    /**
     * Sets the value of the spare2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare2(String value) {
        this.spare2 = value;
    }

    /**
     * Gets the value of the spare3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare3() {
        return spare3;
    }

    /**
     * Sets the value of the spare3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare3(String value) {
        this.spare3 = value;
    }

    /**
     * Gets the value of the spare4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare4() {
        return spare4;
    }

    /**
     * Sets the value of the spare4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare4(String value) {
        this.spare4 = value;
    }

    /**
     * Gets the value of the spare5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare5() {
        return spare5;
    }

    /**
     * Sets the value of the spare5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare5(String value) {
        this.spare5 = value;
    }

}
