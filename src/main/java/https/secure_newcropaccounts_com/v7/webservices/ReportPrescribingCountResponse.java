
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ReportPrescribingCountResult" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reportPrescribingCountResult"
})
@XmlRootElement(name = "ReportPrescribingCountResponse")
public class ReportPrescribingCountResponse {

    @XmlElement(name = "ReportPrescribingCountResult")
    protected Result reportPrescribingCountResult;

    /**
     * Gets the value of the reportPrescribingCountResult property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getReportPrescribingCountResult() {
        return reportPrescribingCountResult;
    }

    /**
     * Sets the value of the reportPrescribingCountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setReportPrescribingCountResult(Result value) {
        this.reportPrescribingCountResult = value;
    }

}
