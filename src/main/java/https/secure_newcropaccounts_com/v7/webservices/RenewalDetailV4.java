
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RenewalDetailV4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RenewalDetailV4">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FormatType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormatVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CompressionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RenewalRequestGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReceivedTimestamp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalLocationId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalDoctorId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPatientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExternalPrescriptionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NcpdpId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriberOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RxReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriberFullName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriberPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrescriberContactNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyFullInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyStoreName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientMiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientSuffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddressCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddressState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientAddressZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PatientContactNumbers" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NDC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrugInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NumberOfRefills" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RefillQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="QuantityQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PotencyUnitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Sig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacistMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubstitutionAllowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WrittenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastFillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DiagnosisCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PriorAuthorizationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PriorAuthorizationQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DaysSupply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DeaSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StateDeaSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedNDC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedDrugInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedNumberOfRefills" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedRefillQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedQuantityQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedPotencyUnitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedSig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedPharmacistMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedSubstitutionAllowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedWrittenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedLastFillDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedDiagnosisCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedPriorAuthorizationNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedPriorAuthorizationQualifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedDaysSupply" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedDeaSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DispensedStateDeaSchedule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AssignedToDoctorExternalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AssignedByStaffExternalId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Spare6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RenewalDetailV4", propOrder = {
    "formatType",
    "formatVersion",
    "compressionType",
    "renewalRequestGuid",
    "receivedTimestamp",
    "externalLocationId",
    "externalDoctorId",
    "externalPatientId",
    "externalPrescriptionId",
    "ncpdpId",
    "prescriberOrderNumber",
    "rxReferenceNumber",
    "locationName",
    "prescriberFullName",
    "prescriberPhoneNumber",
    "prescriberContactNumbers",
    "pharmacyInfo",
    "pharmacyFullInfo",
    "pharmacyStoreName",
    "patientFirstName",
    "patientMiddleName",
    "patientLastName",
    "patientSuffix",
    "patientDOB",
    "patientGender",
    "patientAddressLine1",
    "patientAddressLine2",
    "patientAddressCity",
    "patientAddressState",
    "patientAddressZipCode",
    "patientPhoneNumber",
    "patientContactNumbers",
    "ndc",
    "drugInfo",
    "numberOfRefills",
    "refillQualifier",
    "quantity",
    "quantityQualifier",
    "potencyUnitCode",
    "sig",
    "pharmacistMessage",
    "substitutionAllowed",
    "writtenDate",
    "lastFillDate",
    "diagnosisCodes",
    "priorAuthorizationNumber",
    "priorAuthorizationQualifier",
    "daysSupply",
    "deaSchedule",
    "stateDeaSchedule",
    "dispensedNDC",
    "dispensedDrugInfo",
    "dispensedNumberOfRefills",
    "dispensedRefillQualifier",
    "dispensedQuantity",
    "dispensedQuantityQualifier",
    "dispensedPotencyUnitCode",
    "dispensedSig",
    "dispensedPharmacistMessage",
    "dispensedSubstitutionAllowed",
    "dispensedWrittenDate",
    "dispensedLastFillDate",
    "dispensedDiagnosisCodes",
    "dispensedPriorAuthorizationNumber",
    "dispensedPriorAuthorizationQualifier",
    "dispensedDaysSupply",
    "dispensedDeaSchedule",
    "dispensedStateDeaSchedule",
    "assignedToDoctorExternalId",
    "assignedByStaffExternalId",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5",
    "spare6"
})
public class RenewalDetailV4 {

    @XmlElement(name = "FormatType")
    protected String formatType;
    @XmlElement(name = "FormatVersion")
    protected String formatVersion;
    @XmlElement(name = "CompressionType")
    protected String compressionType;
    @XmlElement(name = "RenewalRequestGuid")
    protected String renewalRequestGuid;
    @XmlElement(name = "ReceivedTimestamp")
    protected String receivedTimestamp;
    @XmlElement(name = "ExternalLocationId")
    protected String externalLocationId;
    @XmlElement(name = "ExternalDoctorId")
    protected String externalDoctorId;
    @XmlElement(name = "ExternalPatientId")
    protected String externalPatientId;
    @XmlElement(name = "ExternalPrescriptionId")
    protected String externalPrescriptionId;
    @XmlElement(name = "NcpdpId")
    protected String ncpdpId;
    @XmlElement(name = "PrescriberOrderNumber")
    protected String prescriberOrderNumber;
    @XmlElement(name = "RxReferenceNumber")
    protected String rxReferenceNumber;
    @XmlElement(name = "LocationName")
    protected String locationName;
    @XmlElement(name = "PrescriberFullName")
    protected String prescriberFullName;
    @XmlElement(name = "PrescriberPhoneNumber")
    protected String prescriberPhoneNumber;
    @XmlElement(name = "PrescriberContactNumbers")
    protected String prescriberContactNumbers;
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
    @XmlElement(name = "PatientSuffix")
    protected String patientSuffix;
    @XmlElement(name = "PatientDOB")
    protected String patientDOB;
    @XmlElement(name = "PatientGender")
    protected String patientGender;
    @XmlElement(name = "PatientAddressLine1")
    protected String patientAddressLine1;
    @XmlElement(name = "PatientAddressLine2")
    protected String patientAddressLine2;
    @XmlElement(name = "PatientAddressCity")
    protected String patientAddressCity;
    @XmlElement(name = "PatientAddressState")
    protected String patientAddressState;
    @XmlElement(name = "PatientAddressZipCode")
    protected String patientAddressZipCode;
    @XmlElement(name = "PatientPhoneNumber")
    protected String patientPhoneNumber;
    @XmlElement(name = "PatientContactNumbers")
    protected String patientContactNumbers;
    @XmlElement(name = "NDC")
    protected String ndc;
    @XmlElement(name = "DrugInfo")
    protected String drugInfo;
    @XmlElement(name = "NumberOfRefills")
    protected String numberOfRefills;
    @XmlElement(name = "RefillQualifier")
    protected String refillQualifier;
    @XmlElement(name = "Quantity")
    protected String quantity;
    @XmlElement(name = "QuantityQualifier")
    protected String quantityQualifier;
    @XmlElement(name = "PotencyUnitCode")
    protected String potencyUnitCode;
    @XmlElement(name = "Sig")
    protected String sig;
    @XmlElement(name = "PharmacistMessage")
    protected String pharmacistMessage;
    @XmlElement(name = "SubstitutionAllowed")
    protected String substitutionAllowed;
    @XmlElement(name = "WrittenDate")
    protected String writtenDate;
    @XmlElement(name = "LastFillDate")
    protected String lastFillDate;
    @XmlElement(name = "DiagnosisCodes")
    protected String diagnosisCodes;
    @XmlElement(name = "PriorAuthorizationNumber")
    protected String priorAuthorizationNumber;
    @XmlElement(name = "PriorAuthorizationQualifier")
    protected String priorAuthorizationQualifier;
    @XmlElement(name = "DaysSupply")
    protected String daysSupply;
    @XmlElement(name = "DeaSchedule")
    protected String deaSchedule;
    @XmlElement(name = "StateDeaSchedule")
    protected String stateDeaSchedule;
    @XmlElement(name = "DispensedNDC")
    protected String dispensedNDC;
    @XmlElement(name = "DispensedDrugInfo")
    protected String dispensedDrugInfo;
    @XmlElement(name = "DispensedNumberOfRefills")
    protected String dispensedNumberOfRefills;
    @XmlElement(name = "DispensedRefillQualifier")
    protected String dispensedRefillQualifier;
    @XmlElement(name = "DispensedQuantity")
    protected String dispensedQuantity;
    @XmlElement(name = "DispensedQuantityQualifier")
    protected String dispensedQuantityQualifier;
    @XmlElement(name = "DispensedPotencyUnitCode")
    protected String dispensedPotencyUnitCode;
    @XmlElement(name = "DispensedSig")
    protected String dispensedSig;
    @XmlElement(name = "DispensedPharmacistMessage")
    protected String dispensedPharmacistMessage;
    @XmlElement(name = "DispensedSubstitutionAllowed")
    protected String dispensedSubstitutionAllowed;
    @XmlElement(name = "DispensedWrittenDate")
    protected String dispensedWrittenDate;
    @XmlElement(name = "DispensedLastFillDate")
    protected String dispensedLastFillDate;
    @XmlElement(name = "DispensedDiagnosisCodes")
    protected String dispensedDiagnosisCodes;
    @XmlElement(name = "DispensedPriorAuthorizationNumber")
    protected String dispensedPriorAuthorizationNumber;
    @XmlElement(name = "DispensedPriorAuthorizationQualifier")
    protected String dispensedPriorAuthorizationQualifier;
    @XmlElement(name = "DispensedDaysSupply")
    protected String dispensedDaysSupply;
    @XmlElement(name = "DispensedDeaSchedule")
    protected String dispensedDeaSchedule;
    @XmlElement(name = "DispensedStateDeaSchedule")
    protected String dispensedStateDeaSchedule;
    @XmlElement(name = "AssignedToDoctorExternalId")
    protected String assignedToDoctorExternalId;
    @XmlElement(name = "AssignedByStaffExternalId")
    protected String assignedByStaffExternalId;
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
    @XmlElement(name = "Spare6")
    protected String spare6;

    /**
     * Gets the value of the formatType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatType() {
        return formatType;
    }

    /**
     * Sets the value of the formatType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatType(String value) {
        this.formatType = value;
    }

    /**
     * Gets the value of the formatVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormatVersion() {
        return formatVersion;
    }

    /**
     * Sets the value of the formatVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormatVersion(String value) {
        this.formatVersion = value;
    }

    /**
     * Gets the value of the compressionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompressionType() {
        return compressionType;
    }

    /**
     * Sets the value of the compressionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompressionType(String value) {
        this.compressionType = value;
    }

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
     * Gets the value of the prescriberOrderNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberOrderNumber() {
        return prescriberOrderNumber;
    }

    /**
     * Sets the value of the prescriberOrderNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberOrderNumber(String value) {
        this.prescriberOrderNumber = value;
    }

    /**
     * Gets the value of the rxReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRxReferenceNumber() {
        return rxReferenceNumber;
    }

    /**
     * Sets the value of the rxReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRxReferenceNumber(String value) {
        this.rxReferenceNumber = value;
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
     * Gets the value of the prescriberFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberFullName() {
        return prescriberFullName;
    }

    /**
     * Sets the value of the prescriberFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberFullName(String value) {
        this.prescriberFullName = value;
    }

    /**
     * Gets the value of the prescriberPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberPhoneNumber() {
        return prescriberPhoneNumber;
    }

    /**
     * Sets the value of the prescriberPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberPhoneNumber(String value) {
        this.prescriberPhoneNumber = value;
    }

    /**
     * Gets the value of the prescriberContactNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrescriberContactNumbers() {
        return prescriberContactNumbers;
    }

    /**
     * Sets the value of the prescriberContactNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrescriberContactNumbers(String value) {
        this.prescriberContactNumbers = value;
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
     * Gets the value of the patientSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientSuffix() {
        return patientSuffix;
    }

    /**
     * Sets the value of the patientSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientSuffix(String value) {
        this.patientSuffix = value;
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
     * Gets the value of the patientAddressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAddressLine1() {
        return patientAddressLine1;
    }

    /**
     * Sets the value of the patientAddressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAddressLine1(String value) {
        this.patientAddressLine1 = value;
    }

    /**
     * Gets the value of the patientAddressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAddressLine2() {
        return patientAddressLine2;
    }

    /**
     * Sets the value of the patientAddressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAddressLine2(String value) {
        this.patientAddressLine2 = value;
    }

    /**
     * Gets the value of the patientAddressCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAddressCity() {
        return patientAddressCity;
    }

    /**
     * Sets the value of the patientAddressCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAddressCity(String value) {
        this.patientAddressCity = value;
    }

    /**
     * Gets the value of the patientAddressState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAddressState() {
        return patientAddressState;
    }

    /**
     * Sets the value of the patientAddressState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAddressState(String value) {
        this.patientAddressState = value;
    }

    /**
     * Gets the value of the patientAddressZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientAddressZipCode() {
        return patientAddressZipCode;
    }

    /**
     * Sets the value of the patientAddressZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientAddressZipCode(String value) {
        this.patientAddressZipCode = value;
    }

    /**
     * Gets the value of the patientPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    /**
     * Sets the value of the patientPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientPhoneNumber(String value) {
        this.patientPhoneNumber = value;
    }

    /**
     * Gets the value of the patientContactNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientContactNumbers() {
        return patientContactNumbers;
    }

    /**
     * Sets the value of the patientContactNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientContactNumbers(String value) {
        this.patientContactNumbers = value;
    }

    /**
     * Gets the value of the ndc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNDC() {
        return ndc;
    }

    /**
     * Sets the value of the ndc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNDC(String value) {
        this.ndc = value;
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
     * Gets the value of the refillQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefillQualifier() {
        return refillQualifier;
    }

    /**
     * Sets the value of the refillQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefillQualifier(String value) {
        this.refillQualifier = value;
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
     * Gets the value of the quantityQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantityQualifier() {
        return quantityQualifier;
    }

    /**
     * Sets the value of the quantityQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantityQualifier(String value) {
        this.quantityQualifier = value;
    }

    /**
     * Gets the value of the potencyUnitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPotencyUnitCode() {
        return potencyUnitCode;
    }

    /**
     * Sets the value of the potencyUnitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPotencyUnitCode(String value) {
        this.potencyUnitCode = value;
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
     * Gets the value of the pharmacistMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacistMessage() {
        return pharmacistMessage;
    }

    /**
     * Sets the value of the pharmacistMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacistMessage(String value) {
        this.pharmacistMessage = value;
    }

    /**
     * Gets the value of the substitutionAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstitutionAllowed() {
        return substitutionAllowed;
    }

    /**
     * Sets the value of the substitutionAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstitutionAllowed(String value) {
        this.substitutionAllowed = value;
    }

    /**
     * Gets the value of the writtenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrittenDate() {
        return writtenDate;
    }

    /**
     * Sets the value of the writtenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrittenDate(String value) {
        this.writtenDate = value;
    }

    /**
     * Gets the value of the lastFillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastFillDate() {
        return lastFillDate;
    }

    /**
     * Sets the value of the lastFillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastFillDate(String value) {
        this.lastFillDate = value;
    }

    /**
     * Gets the value of the diagnosisCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosisCodes() {
        return diagnosisCodes;
    }

    /**
     * Sets the value of the diagnosisCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosisCodes(String value) {
        this.diagnosisCodes = value;
    }

    /**
     * Gets the value of the priorAuthorizationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorAuthorizationNumber() {
        return priorAuthorizationNumber;
    }

    /**
     * Sets the value of the priorAuthorizationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriorAuthorizationNumber(String value) {
        this.priorAuthorizationNumber = value;
    }

    /**
     * Gets the value of the priorAuthorizationQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriorAuthorizationQualifier() {
        return priorAuthorizationQualifier;
    }

    /**
     * Sets the value of the priorAuthorizationQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriorAuthorizationQualifier(String value) {
        this.priorAuthorizationQualifier = value;
    }

    /**
     * Gets the value of the daysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaysSupply() {
        return daysSupply;
    }

    /**
     * Sets the value of the daysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysSupply(String value) {
        this.daysSupply = value;
    }

    /**
     * Gets the value of the deaSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeaSchedule() {
        return deaSchedule;
    }

    /**
     * Sets the value of the deaSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeaSchedule(String value) {
        this.deaSchedule = value;
    }

    /**
     * Gets the value of the stateDeaSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStateDeaSchedule() {
        return stateDeaSchedule;
    }

    /**
     * Sets the value of the stateDeaSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStateDeaSchedule(String value) {
        this.stateDeaSchedule = value;
    }

    /**
     * Gets the value of the dispensedNDC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedNDC() {
        return dispensedNDC;
    }

    /**
     * Sets the value of the dispensedNDC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedNDC(String value) {
        this.dispensedNDC = value;
    }

    /**
     * Gets the value of the dispensedDrugInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedDrugInfo() {
        return dispensedDrugInfo;
    }

    /**
     * Sets the value of the dispensedDrugInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedDrugInfo(String value) {
        this.dispensedDrugInfo = value;
    }

    /**
     * Gets the value of the dispensedNumberOfRefills property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedNumberOfRefills() {
        return dispensedNumberOfRefills;
    }

    /**
     * Sets the value of the dispensedNumberOfRefills property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedNumberOfRefills(String value) {
        this.dispensedNumberOfRefills = value;
    }

    /**
     * Gets the value of the dispensedRefillQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedRefillQualifier() {
        return dispensedRefillQualifier;
    }

    /**
     * Sets the value of the dispensedRefillQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedRefillQualifier(String value) {
        this.dispensedRefillQualifier = value;
    }

    /**
     * Gets the value of the dispensedQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedQuantity() {
        return dispensedQuantity;
    }

    /**
     * Sets the value of the dispensedQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedQuantity(String value) {
        this.dispensedQuantity = value;
    }

    /**
     * Gets the value of the dispensedQuantityQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedQuantityQualifier() {
        return dispensedQuantityQualifier;
    }

    /**
     * Sets the value of the dispensedQuantityQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedQuantityQualifier(String value) {
        this.dispensedQuantityQualifier = value;
    }

    /**
     * Gets the value of the dispensedPotencyUnitCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedPotencyUnitCode() {
        return dispensedPotencyUnitCode;
    }

    /**
     * Sets the value of the dispensedPotencyUnitCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedPotencyUnitCode(String value) {
        this.dispensedPotencyUnitCode = value;
    }

    /**
     * Gets the value of the dispensedSig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedSig() {
        return dispensedSig;
    }

    /**
     * Sets the value of the dispensedSig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedSig(String value) {
        this.dispensedSig = value;
    }

    /**
     * Gets the value of the dispensedPharmacistMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedPharmacistMessage() {
        return dispensedPharmacistMessage;
    }

    /**
     * Sets the value of the dispensedPharmacistMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedPharmacistMessage(String value) {
        this.dispensedPharmacistMessage = value;
    }

    /**
     * Gets the value of the dispensedSubstitutionAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedSubstitutionAllowed() {
        return dispensedSubstitutionAllowed;
    }

    /**
     * Sets the value of the dispensedSubstitutionAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedSubstitutionAllowed(String value) {
        this.dispensedSubstitutionAllowed = value;
    }

    /**
     * Gets the value of the dispensedWrittenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedWrittenDate() {
        return dispensedWrittenDate;
    }

    /**
     * Sets the value of the dispensedWrittenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedWrittenDate(String value) {
        this.dispensedWrittenDate = value;
    }

    /**
     * Gets the value of the dispensedLastFillDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedLastFillDate() {
        return dispensedLastFillDate;
    }

    /**
     * Sets the value of the dispensedLastFillDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedLastFillDate(String value) {
        this.dispensedLastFillDate = value;
    }

    /**
     * Gets the value of the dispensedDiagnosisCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedDiagnosisCodes() {
        return dispensedDiagnosisCodes;
    }

    /**
     * Sets the value of the dispensedDiagnosisCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedDiagnosisCodes(String value) {
        this.dispensedDiagnosisCodes = value;
    }

    /**
     * Gets the value of the dispensedPriorAuthorizationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedPriorAuthorizationNumber() {
        return dispensedPriorAuthorizationNumber;
    }

    /**
     * Sets the value of the dispensedPriorAuthorizationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedPriorAuthorizationNumber(String value) {
        this.dispensedPriorAuthorizationNumber = value;
    }

    /**
     * Gets the value of the dispensedPriorAuthorizationQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedPriorAuthorizationQualifier() {
        return dispensedPriorAuthorizationQualifier;
    }

    /**
     * Sets the value of the dispensedPriorAuthorizationQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedPriorAuthorizationQualifier(String value) {
        this.dispensedPriorAuthorizationQualifier = value;
    }

    /**
     * Gets the value of the dispensedDaysSupply property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedDaysSupply() {
        return dispensedDaysSupply;
    }

    /**
     * Sets the value of the dispensedDaysSupply property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedDaysSupply(String value) {
        this.dispensedDaysSupply = value;
    }

    /**
     * Gets the value of the dispensedDeaSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedDeaSchedule() {
        return dispensedDeaSchedule;
    }

    /**
     * Sets the value of the dispensedDeaSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedDeaSchedule(String value) {
        this.dispensedDeaSchedule = value;
    }

    /**
     * Gets the value of the dispensedStateDeaSchedule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDispensedStateDeaSchedule() {
        return dispensedStateDeaSchedule;
    }

    /**
     * Sets the value of the dispensedStateDeaSchedule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDispensedStateDeaSchedule(String value) {
        this.dispensedStateDeaSchedule = value;
    }

    /**
     * Gets the value of the assignedToDoctorExternalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedToDoctorExternalId() {
        return assignedToDoctorExternalId;
    }

    /**
     * Sets the value of the assignedToDoctorExternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedToDoctorExternalId(String value) {
        this.assignedToDoctorExternalId = value;
    }

    /**
     * Gets the value of the assignedByStaffExternalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedByStaffExternalId() {
        return assignedByStaffExternalId;
    }

    /**
     * Sets the value of the assignedByStaffExternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedByStaffExternalId(String value) {
        this.assignedByStaffExternalId = value;
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

    /**
     * Gets the value of the spare6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpare6() {
        return spare6;
    }

    /**
     * Sets the value of the spare6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpare6(String value) {
        this.spare6 = value;
    }

}
