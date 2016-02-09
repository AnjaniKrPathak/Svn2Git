
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
 *         &lt;element name="GetLabOrdersResult" type="{https://secure.newcropaccounts.com/V7/webservices}LabOrderDetailResult" minOccurs="0"/>
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
    "getLabOrdersResult"
})
@XmlRootElement(name = "GetLabOrdersResponse")
public class GetLabOrdersResponse {

    @XmlElement(name = "GetLabOrdersResult")
    protected LabOrderDetailResult getLabOrdersResult;

    /**
     * Gets the value of the getLabOrdersResult property.
     * 
     * @return
     *     possible object is
     *     {@link LabOrderDetailResult }
     *     
     */
    public LabOrderDetailResult getGetLabOrdersResult() {
        return getLabOrdersResult;
    }

    /**
     * Sets the value of the getLabOrdersResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link LabOrderDetailResult }
     *     
     */
    public void setGetLabOrdersResult(LabOrderDetailResult value) {
        this.getLabOrdersResult = value;
    }

}
