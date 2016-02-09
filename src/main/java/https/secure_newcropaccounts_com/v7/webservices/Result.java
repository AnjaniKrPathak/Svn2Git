
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{https://secure.newcropaccounts.com/V7/webservices}StatusType"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XmlResponse" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RowCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Timing" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Result", propOrder = {
    "status",
    "message",
    "xmlResponse",
    "rowCount",
    "timing"
})
public class Result {

    @XmlElement(name = "Status", required = true)
    protected StatusType status;
    @XmlElement(name = "Message")
    protected String message;
    @XmlElement(name = "XmlResponse")
    protected String xmlResponse;
    @XmlElement(name = "RowCount")
    protected int rowCount;
    @XmlElement(name = "Timing")
    protected int timing;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the xmlResponse property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlResponse() {
        return xmlResponse;
    }

    /**
     * Sets the value of the xmlResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlResponse(String value) {
        this.xmlResponse = value;
    }

    /**
     * Gets the value of the rowCount property.
     * 
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Sets the value of the rowCount property.
     * 
     */
    public void setRowCount(int value) {
        this.rowCount = value;
    }

    /**
     * Gets the value of the timing property.
     * 
     */
    public int getTiming() {
        return timing;
    }

    /**
     * Sets the value of the timing property.
     * 
     */
    public void setTiming(int value) {
        this.timing = value;
    }

}
