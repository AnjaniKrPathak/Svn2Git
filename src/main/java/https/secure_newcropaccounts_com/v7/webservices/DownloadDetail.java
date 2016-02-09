
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DownloadDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DownloadDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LatestDownloadMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestDownloadDay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestDownloadYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestDownloadSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LatestDownloadUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CchitDrugDatabaseDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DownloadDetail", propOrder = {
    "latestDownloadMonth",
    "latestDownloadDay",
    "latestDownloadYear",
    "latestDownloadSize",
    "latestDownloadUrl",
    "cchitDrugDatabaseDate",
    "comments"
})
public class DownloadDetail {

    @XmlElement(name = "LatestDownloadMonth")
    protected String latestDownloadMonth;
    @XmlElement(name = "LatestDownloadDay")
    protected String latestDownloadDay;
    @XmlElement(name = "LatestDownloadYear")
    protected String latestDownloadYear;
    @XmlElement(name = "LatestDownloadSize")
    protected String latestDownloadSize;
    @XmlElement(name = "LatestDownloadUrl")
    protected String latestDownloadUrl;
    @XmlElement(name = "CchitDrugDatabaseDate")
    protected String cchitDrugDatabaseDate;
    @XmlElement(name = "Comments")
    protected String comments;

    /**
     * Gets the value of the latestDownloadMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestDownloadMonth() {
        return latestDownloadMonth;
    }

    /**
     * Sets the value of the latestDownloadMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestDownloadMonth(String value) {
        this.latestDownloadMonth = value;
    }

    /**
     * Gets the value of the latestDownloadDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestDownloadDay() {
        return latestDownloadDay;
    }

    /**
     * Sets the value of the latestDownloadDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestDownloadDay(String value) {
        this.latestDownloadDay = value;
    }

    /**
     * Gets the value of the latestDownloadYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestDownloadYear() {
        return latestDownloadYear;
    }

    /**
     * Sets the value of the latestDownloadYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestDownloadYear(String value) {
        this.latestDownloadYear = value;
    }

    /**
     * Gets the value of the latestDownloadSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestDownloadSize() {
        return latestDownloadSize;
    }

    /**
     * Sets the value of the latestDownloadSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestDownloadSize(String value) {
        this.latestDownloadSize = value;
    }

    /**
     * Gets the value of the latestDownloadUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestDownloadUrl() {
        return latestDownloadUrl;
    }

    /**
     * Sets the value of the latestDownloadUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestDownloadUrl(String value) {
        this.latestDownloadUrl = value;
    }

    /**
     * Gets the value of the cchitDrugDatabaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCchitDrugDatabaseDate() {
        return cchitDrugDatabaseDate;
    }

    /**
     * Sets the value of the cchitDrugDatabaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCchitDrugDatabaseDate(String value) {
        this.cchitDrugDatabaseDate = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

}
