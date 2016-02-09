
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EligibilityDetailResultV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityDetailResultV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="eligibilityGuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eligibilityDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfEligibilityDetailV3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EligibilityDetailResultV3", propOrder = {
    "result",
    "eligibilityGuid",
    "eligibilityDetailArray"
})
public class EligibilityDetailResultV3 {

    protected Result result;
    protected String eligibilityGuid;
    protected ArrayOfEligibilityDetailV3 eligibilityDetailArray;

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
     * Gets the value of the eligibilityGuid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibilityGuid() {
        return eligibilityGuid;
    }

    /**
     * Sets the value of the eligibilityGuid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibilityGuid(String value) {
        this.eligibilityGuid = value;
    }

    /**
     * Gets the value of the eligibilityDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfEligibilityDetailV3 }
     *     
     */
    public ArrayOfEligibilityDetailV3 getEligibilityDetailArray() {
        return eligibilityDetailArray;
    }

    /**
     * Sets the value of the eligibilityDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfEligibilityDetailV3 }
     *     
     */
    public void setEligibilityDetailArray(ArrayOfEligibilityDetailV3 value) {
        this.eligibilityDetailArray = value;
    }

}
