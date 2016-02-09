
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
 *         &lt;element name="GetCounselingMessagesResult" type="{https://secure.newcropaccounts.com/V7/webservices}CounselingMessageDetailResult" minOccurs="0"/>
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
    "getCounselingMessagesResult"
})
@XmlRootElement(name = "GetCounselingMessagesResponse")
public class GetCounselingMessagesResponse {

    @XmlElement(name = "GetCounselingMessagesResult")
    protected CounselingMessageDetailResult getCounselingMessagesResult;

    /**
     * Gets the value of the getCounselingMessagesResult property.
     * 
     * @return
     *     possible object is
     *     {@link CounselingMessageDetailResult }
     *     
     */
    public CounselingMessageDetailResult getGetCounselingMessagesResult() {
        return getCounselingMessagesResult;
    }

    /**
     * Sets the value of the getCounselingMessagesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CounselingMessageDetailResult }
     *     
     */
    public void setGetCounselingMessagesResult(CounselingMessageDetailResult value) {
        this.getCounselingMessagesResult = value;
    }

}
