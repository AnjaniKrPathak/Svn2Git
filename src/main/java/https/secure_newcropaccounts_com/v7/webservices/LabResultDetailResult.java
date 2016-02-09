
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabResultDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabResultDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="labResultDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfLabResultDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabResultDetailResult", propOrder = {
    "result",
    "labResultDetailArray"
})
public class LabResultDetailResult {

    protected Result result;
    protected ArrayOfLabResultDetail labResultDetailArray;

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
     * Gets the value of the labResultDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLabResultDetail }
     *     
     */
    public ArrayOfLabResultDetail getLabResultDetailArray() {
        return labResultDetailArray;
    }

    /**
     * Sets the value of the labResultDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLabResultDetail }
     *     
     */
    public void setLabResultDetailArray(ArrayOfLabResultDetail value) {
        this.labResultDetailArray = value;
    }

}
