
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CounselingMessageDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CounselingMessageDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="counselingMessageDetail" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfCounselingMessageDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CounselingMessageDetailResult", propOrder = {
    "result",
    "counselingMessageDetail"
})
public class CounselingMessageDetailResult {

    protected Result result;
    protected ArrayOfCounselingMessageDetail counselingMessageDetail;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

    /**
     * Gets the value of the counselingMessageDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCounselingMessageDetail }
     *     
     */
    public ArrayOfCounselingMessageDetail getCounselingMessageDetail() {
        return counselingMessageDetail;
    }

    /**
     * Sets the value of the counselingMessageDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCounselingMessageDetail }
     *     
     */
    public void setCounselingMessageDetail(ArrayOfCounselingMessageDetail value) {
        this.counselingMessageDetail = value;
    }

}
