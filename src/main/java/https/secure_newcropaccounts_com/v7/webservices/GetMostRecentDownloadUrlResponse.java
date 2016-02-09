
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
 *         &lt;element name="GetMostRecentDownloadUrlResult" type="{https://secure.newcropaccounts.com/V7/webservices}DownloadDetailResult" minOccurs="0"/>
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
    "getMostRecentDownloadUrlResult"
})
@XmlRootElement(name = "GetMostRecentDownloadUrlResponse")
public class GetMostRecentDownloadUrlResponse {

    @XmlElement(name = "GetMostRecentDownloadUrlResult")
    protected DownloadDetailResult getMostRecentDownloadUrlResult;

    /**
     * Gets the value of the getMostRecentDownloadUrlResult property.
     * 
     * @return
     *     possible object is
     *     {@link DownloadDetailResult }
     *     
     */
    public DownloadDetailResult getGetMostRecentDownloadUrlResult() {
        return getMostRecentDownloadUrlResult;
    }

    /**
     * Sets the value of the getMostRecentDownloadUrlResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DownloadDetailResult }
     *     
     */
    public void setGetMostRecentDownloadUrlResult(DownloadDetailResult value) {
        this.getMostRecentDownloadUrlResult = value;
    }

}
