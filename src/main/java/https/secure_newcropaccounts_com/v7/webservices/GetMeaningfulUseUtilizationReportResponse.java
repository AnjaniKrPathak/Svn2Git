
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
 *         &lt;element name="GetMeaningfulUseUtilizationReportResult" type="{https://secure.newcropaccounts.com/V7/webservices}ReportDownloadResult" minOccurs="0"/>
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
    "getMeaningfulUseUtilizationReportResult"
})
@XmlRootElement(name = "GetMeaningfulUseUtilizationReportResponse")
public class GetMeaningfulUseUtilizationReportResponse {

    @XmlElement(name = "GetMeaningfulUseUtilizationReportResult")
    protected ReportDownloadResult getMeaningfulUseUtilizationReportResult;

    /**
     * Gets the value of the getMeaningfulUseUtilizationReportResult property.
     * 
     * @return
     *     possible object is
     *     {@link ReportDownloadResult }
     *     
     */
    public ReportDownloadResult getGetMeaningfulUseUtilizationReportResult() {
        return getMeaningfulUseUtilizationReportResult;
    }

    /**
     * Sets the value of the getMeaningfulUseUtilizationReportResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportDownloadResult }
     *     
     */
    public void setGetMeaningfulUseUtilizationReportResult(ReportDownloadResult value) {
        this.getMeaningfulUseUtilizationReportResult = value;
    }

}
