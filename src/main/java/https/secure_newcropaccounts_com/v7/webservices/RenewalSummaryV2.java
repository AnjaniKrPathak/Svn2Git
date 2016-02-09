
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalSummaryV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalSummaryV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RenewalRequestGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceivedTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyFullInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyStoreName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfRefills" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalLocationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalDoctorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPrescriptionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NcpdpId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "RenewalSummaryV2", propOrder = {
    "renewalRequestGuid",
    "receivedTimestamp",
    "locationName",
    "doctorFullName",
    "pharmacyInfo",
    "pharmacyFullInfo",
    "pharmacyStoreName",
    "patientFirstName",
    "patientMiddleName",
    "patientLastName",
    "patientDOB",
    "patientGender",
    "drugInfo",
    "numberOfRefills",
    "externalLocationId",
    "externalDoctorId",
    "externalPatientId",
    "externalPrescriptionId",
    "quantity",
    "sig",
    "ncpdpId",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5"
})
public class RenewalSummaryV2 {

    @XmlElement(name = "RenewalRequestGuid")
    protected String renewalRequestGuid;
    @XmlElement(name = "ReceivedTimestamp")
    protected String receivedTimestamp;
    @XmlElement(name = "LocationName")
    protected String locationName;
    @XmlElement(name = "DoctorFullName")
    protected String doctorFullName;
    @XmlElement(name = "PharmacyInfo")
    protected String pharmacyInfo;
    @XmlElement(name = "PharmacyFullInfo")
    protected String pharmacyFullInfo;
    @XmlElement(name = "PharmacyStoreName")
    protected String pharmacyStoreName;
    @XmlElement(name = "PatientFirstName")
    protected String patientFirstName;
    @XmlElement(name = "PatientMiddleName")
    protected String patientMiddleName;
    @XmlElement(name = "PatientLastName")
    protected String patientLastName;
    @XmlElement(name = "PatientDOB")
    protected String patientDOB;
    @XmlElement(name = "PatientGender")
    protected String patientGender;
    @XmlElement(name = "DrugInfo")
    protected String drugInfo;
    @XmlElement(name = "NumberOfRefills")
    protected String numberOfRefills;
    @XmlElement(name = "ExternalLocationId")
    protected String externalLocationId;
    @XmlElement(name = "ExternalDoctorId")
    protected String externalDoctorId;
    @XmlElement(name = "ExternalPatientId")
    protected String externalPatientId;
    @XmlElement(name = "ExternalPrescriptionId")
    protected String externalPrescriptionId;
    @XmlElement(name = "Quantity")
    protected String quantity;
    @XmlElement(name = "Sig")
    protected String sig;
    @XmlElement(name = "NcpdpId")
    protected String ncpdpId;
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
     * Gets the value of the renewalRequestGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRenewalRequestGuid() {
        return renewalRequestGuid;
    }

    /**
     * Sets the value of the renewalRequestGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRenewalRequestGuid(String value) {
        this.renewalRequestGuid = value;
    }

    /**
     * Gets the value of the receivedTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceivedTimestamp() {
        return receivedTimestamp;
    }

    /**
     * Sets the value of the receivedTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceivedTimestamp(String value) {
        this.receivedTimestamp = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the doctorFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorFullName() {
        return doctorFullName;
    }

    /**
     * Sets the value of the doctorFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorFullName(String value) {
        this.doctorFullName = value;
    }

    /**
     * Gets the value of the pharmacyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyInfo() {
        return pharmacyInfo;
    }

    /**
     * Sets the value of the pharmacyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyInfo(String value) {
        this.pharmacyInfo = value;
    }

    /**
     * Gets the value of the pharmacyFullInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyFullInfo() {
        return pharmacyFullInfo;
    }

    /**
     * Sets the value of the pharmacyFullInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyFullInfo(String value) {
        this.pharmacyFullInfo = value;
    }

    /**
     * Gets the value of the pharmacyStoreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyStoreName() {
        return pharmacyStoreName;
    }

    /**
     * Sets the value of the pharmacyStoreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyStoreName(String value) {
        this.pharmacyStoreName = value;
    }

    /**
     * Gets the value of the patientFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientFirstName() {
        return patientFirstName;
    }

    /**
     * Sets the value of the patientFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientFirstName(String value) {
        this.patientFirstName = value;
    }

    /**
     * Gets the value of the patientMiddleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    /**
     * Sets the value of the patientMiddleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientMiddleName(String value) {
        this.patientMiddleName = value;
    }

    /**
     * Gets the value of the patientLastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientLastName() {
        return patientLastName;
    }

    /**
     * Sets the value of the patientLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientLastName(String value) {
        this.patientLastName = value;
    }

    /**
     * Gets the value of the patientDOB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientDOB() {
        return patientDOB;
    }

    /**
     * Sets the value of the patientDOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientDOB(String value) {
        this.patientDOB = value;
    }

    /**
     * Gets the value of the patientGender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientGender() {
        return patientGender;
    }

    /**
     * Sets the value of the patientGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientGender(String value) {
        this.patientGender = value;
    }

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
     * Gets the value of the numberOfRefills property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumberOfRefills() {
        return numberOfRefills;
    }

    /**
     * Sets the value of the numberOfRefills property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumberOfRefills(String value) {
        this.numberOfRefills = value;
    }

    /**
     * Gets the value of the externalLocationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalLocationId() {
        return externalLocationId;
    }

    /**
     * Sets the value of the externalLocationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalLocationId(String value) {
        this.externalLocationId = value;
    }

    /**
     * Gets the value of the externalDoctorId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalDoctorId() {
        return externalDoctorId;
    }

    /**
     * Sets the value of the externalDoctorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalDoctorId(String value) {
        this.externalDoctorId = value;
    }

    /**
     * Gets the value of the externalPatientId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPatientId() {
        return externalPatientId;
    }

    /**
     * Sets the value of the externalPatientId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPatientId(String value) {
        this.externalPatientId = value;
    }

    /**
     * Gets the value of the externalPrescriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPrescriptionId() {
        return externalPrescriptionId;
    }

    /**
     * Sets the value of the externalPrescriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPrescriptionId(String value) {
        this.externalPrescriptionId = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the sig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSig() {
        return sig;
    }

    /**
     * Sets the value of the sig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSig(String value) {
        this.sig = value;
    }

    /**
     * Gets the value of the ncpdpId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNcpdpId() {
        return ncpdpId;
    }

    /**
     * Sets the value of the ncpdpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNcpdpId(String value) {
        this.ncpdpId = value;
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
