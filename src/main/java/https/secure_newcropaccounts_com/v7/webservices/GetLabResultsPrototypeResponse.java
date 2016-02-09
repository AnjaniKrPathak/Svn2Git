
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
 *         &lt;element name="GetLabResultsPrototypeResult" type="{https://secure.newcropaccounts.com/V7/webservices}LabResultDetailResult" minOccurs="0"/>
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
    "getLabResultsPrototypeResult"
})
@XmlRootElement(name = "GetLabResultsPrototypeResponse")
public class GetLabResultsPrototypeResponse {

    @XmlElement(name = "GetLabResultsPrototypeResult")
    protected LabResultDetailResult getLabResultsPrototypeResult;

    /**
     * Gets the value of the getLabResultsPrototypeResult property.
     * 
     * @return
     *     possible object is
     *     {@link LabResultDetailResult }
     *     
     */
    public LabResultDetailResult getGetLabResultsPrototypeResult() {
        return getLabResultsPrototypeResult;
    }

    /**
     * Sets the value of the getLabResultsPrototypeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabResultDetailResult }
     *     
     */
    public void setGetLabResultsPrototypeResult(LabResultDetailResult value) {
        this.getLabResultsPrototypeResult = value;
    }

}
