
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CounselingMessageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CounselingMessageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="displayOrder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="professionalMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="patientMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CounselingMessageDetail", propOrder = {
    "displayOrder",
    "professionalMessage",
    "patientMessage"
})
public class CounselingMessageDetail {

    protected String displayOrder;
    protected String professionalMessage;
    protected String patientMessage;

    /**
     * Gets the value of the displayOrder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayOrder() {
        return displayOrder;
    }

    /**
     * Sets the value of the displayOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayOrder(String value) {
        this.displayOrder = value;
    }

    /**
     * Gets the value of the professionalMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionalMessage() {
        return professionalMessage;
    }

    /**
     * Sets the value of the professionalMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionalMessage(String value) {
        this.professionalMessage = value;
    }

    /**
     * Gets the value of the patientMessage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatientMessage() {
        return patientMessage;
    }

    /**
     * Sets the value of the patientMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatientMessage(String value) {
        this.patientMessage = value;
    }

}
