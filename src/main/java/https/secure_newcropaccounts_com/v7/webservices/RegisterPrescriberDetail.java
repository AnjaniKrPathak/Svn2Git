
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisterPrescriberDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterPrescriberDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DoctorDeaNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DoctorID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocationID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Network" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StopDateTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterPrescriberDetail", propOrder = {
    "doctorDeaNumber",
    "doctorLastName",
    "doctorFirstName",
    "doctorID",
    "locationName",
    "locationID",
    "status",
    "network",
    "startDateTime",
    "stopDateTime"
})
public class RegisterPrescriberDetail {

    @XmlElement(name = "DoctorDeaNumber")
    protected String doctorDeaNumber;
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
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Network")
    protected String network;
    @XmlElement(name = "StartDateTime")
    protected String startDateTime;
    @XmlElement(name = "StopDateTime")
    protected String stopDateTime;

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
     * Gets the value of the network property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetwork() {
        return network;
    }

    /**
     * Sets the value of the network property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetwork(String value) {
        this.network = value;
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

}
