
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisterPrescriberDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterPrescriberDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DoctorDeaNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorNpi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegistrationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FailureCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegistrationStatusMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetworkName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NetworkMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StopDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceLevelMask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrimaryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecondaryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TertiaryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "RegisterPrescriberDetailV2", propOrder = {
    "doctorDeaNumber",
    "doctorNpi",
    "doctorLastName",
    "doctorFirstName",
    "doctorID",
    "locationName",
    "locationID",
    "registrationStatus",
    "failureCode",
    "registrationStatusMessage",
    "networkName",
    "networkMask",
    "startDateTime",
    "stopDateTime",
    "serviceLevelMask",
    "primaryId",
    "secondaryId",
    "tertiaryId",
    "spare1",
    "spare2",
    "spare3",
    "spare4",
    "spare5"
})
public class RegisterPrescriberDetailV2 {

    @XmlElement(name = "DoctorDeaNumber")
    protected String doctorDeaNumber;
    @XmlElement(name = "DoctorNpi")
    protected String doctorNpi;
    @XmlElement(name = "DoctorLastName")
    protected String doctorLastName;
    @XmlElement(name = "DoctorFirstName")
    protected String doctorFirstName;
    @XmlElement(name = "DoctorID")
    protected String doctorID;
    @XmlElement(name = "LocationName")
    protected String locationName;
    @XmlElement(name = "LocationID")
    protected String locationID;
    @XmlElement(name = "RegistrationStatus")
    protected String registrationStatus;
    @XmlElement(name = "FailureCode")
    protected String failureCode;
    @XmlElement(name = "RegistrationStatusMessage")
    protected String registrationStatusMessage;
    @XmlElement(name = "NetworkName")
    protected String networkName;
    @XmlElement(name = "NetworkMask")
    protected String networkMask;
    @XmlElement(name = "StartDateTime")
    protected String startDateTime;
    @XmlElement(name = "StopDateTime")
    protected String stopDateTime;
    @XmlElement(name = "ServiceLevelMask")
    protected String serviceLevelMask;
    @XmlElement(name = "PrimaryId")
    protected String primaryId;
    @XmlElement(name = "SecondaryId")
    protected String secondaryId;
    @XmlElement(name = "TertiaryId")
    protected String tertiaryId;
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
     * Gets the value of the doctorDeaNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorDeaNumber() {
        return doctorDeaNumber;
    }

    /**
     * Sets the value of the doctorDeaNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorDeaNumber(String value) {
        this.doctorDeaNumber = value;
    }

    /**
     * Gets the value of the doctorNpi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorNpi() {
        return doctorNpi;
    }

    /**
     * Sets the value of the doctorNpi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorNpi(String value) {
        this.doctorNpi = value;
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
     * Gets the value of the doctorID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoctorID() {
        return doctorID;
    }

    /**
     * Sets the value of the doctorID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoctorID(String value) {
        this.doctorID = value;
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
     * Gets the value of the locationID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationID() {
        return locationID;
    }

    /**
     * Sets the value of the locationID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationID(String value) {
        this.locationID = value;
    }

    /**
     * Gets the value of the registrationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationStatus() {
        return registrationStatus;
    }

    /**
     * Sets the value of the registrationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationStatus(String value) {
        this.registrationStatus = value;
    }

    /**
     * Gets the value of the failureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailureCode() {
        return failureCode;
    }

    /**
     * Sets the value of the failureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailureCode(String value) {
        this.failureCode = value;
    }

    /**
     * Gets the value of the registrationStatusMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationStatusMessage() {
        return registrationStatusMessage;
    }

    /**
     * Sets the value of the registrationStatusMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationStatusMessage(String value) {
        this.registrationStatusMessage = value;
    }

    /**
     * Gets the value of the networkName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkName() {
        return networkName;
    }

    /**
     * Sets the value of the networkName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkName(String value) {
        this.networkName = value;
    }

    /**
     * Gets the value of the networkMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkMask() {
        return networkMask;
    }

    /**
     * Sets the value of the networkMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkMask(String value) {
        this.networkMask = value;
    }

    /**
     * Gets the value of the startDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDateTime() {
        return startDateTime;
    }

    /**
     * Sets the value of the startDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDateTime(String value) {
        this.startDateTime = value;
    }

    /**
     * Gets the value of the stopDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStopDateTime() {
        return stopDateTime;
    }

    /**
     * Sets the value of the stopDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStopDateTime(String value) {
        this.stopDateTime = value;
    }

    /**
     * Gets the value of the serviceLevelMask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceLevelMask() {
        return serviceLevelMask;
    }

    /**
     * Sets the value of the serviceLevelMask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceLevelMask(String value) {
        this.serviceLevelMask = value;
    }

    /**
     * Gets the value of the primaryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryId() {
        return primaryId;
    }

    /**
     * Sets the value of the primaryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryId(String value) {
        this.primaryId = value;
    }

    /**
     * Gets the value of the secondaryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryId() {
        return secondaryId;
    }

    /**
     * Sets the value of the secondaryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryId(String value) {
        this.secondaryId = value;
    }

    /**
     * Gets the value of the tertiaryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTertiaryId() {
        return tertiaryId;
    }

    /**
     * Sets the value of the tertiaryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTertiaryId(String value) {
        this.tertiaryId = value;
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
