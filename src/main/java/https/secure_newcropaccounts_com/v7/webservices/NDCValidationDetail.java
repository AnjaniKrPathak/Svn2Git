
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NDCValidationDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NDCValidationDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ndc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ndcStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NDCValidationDetail", propOrder = {
    "ndc",
    "ndcStatus"
})
public class NDCValidationDetail {

    protected String ndc;
    protected String ndcStatus;

    /**
     * Gets the value of the ndc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNdc() {
        return ndc;
    }

    /**
     * Sets the value of the ndc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNdc(String value) {
        this.ndc = value;
    }

    /**
     * Gets the value of the ndcStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNdcStatus() {
        return ndcStatus;
    }

    /**
     * Sets the value of the ndcStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNdcStatus(String value) {
        this.ndcStatus = value;
    }

}
