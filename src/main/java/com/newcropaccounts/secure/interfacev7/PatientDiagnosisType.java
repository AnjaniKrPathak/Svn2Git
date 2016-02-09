
package com.newcropaccounts.secure.interfacev7;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.newcropaccounts.secure.interfacev7.ncstandard.DiagnosisType;


/**
 * <p>Java class for PatientDiagnosisType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PatientDiagnosisType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="diagnosisID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diagnosisType" type="{http://secure.newcropaccounts.com/interfaceV7:NCStandard}DiagnosisType"/>
 *         &lt;element name="onsetDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diagnosisName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientDiagnosisType", propOrder = {
    "diagnosisID",
    "diagnosisType",
    "onsetDate",
    "diagnosisName",
    "recordedDate"
})
public class PatientDiagnosisType {

    protected String diagnosisID;
    @XmlElement(required = true)
    protected DiagnosisType diagnosisType;
    protected String onsetDate;
    protected String diagnosisName;
    protected String recordedDate;

    /**
     * Gets the value of the diagnosisID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosisID() {
        return diagnosisID;
    }

    /**
     * Sets the value of the diagnosisID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosisID(String value) {
        this.diagnosisID = value;
    }

    /**
     * Gets the value of the diagnosisType property.
     * 
     * @return
     *     possible object is
     *     {@link DiagnosisType }
     *     
     */
    public DiagnosisType getDiagnosisType() {
        return diagnosisType;
    }

    /**
     * Sets the value of the diagnosisType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiagnosisType }
     *     
     */
    public void setDiagnosisType(DiagnosisType value) {
        this.diagnosisType = value;
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
     * Gets the value of the diagnosisName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosisName() {
        return diagnosisName;
    }

    /**
     * Sets the value of the diagnosisName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosisName(String value) {
        this.diagnosisName = value;
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

}
