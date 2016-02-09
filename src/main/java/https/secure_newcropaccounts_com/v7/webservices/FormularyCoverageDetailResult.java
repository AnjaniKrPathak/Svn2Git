
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormularyCoverageDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormularyCoverageDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="formularyCoverageDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfFormularyCoverageDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormularyCoverageDetailResult", propOrder = {
    "result",
    "formularyCoverageDetailArray"
})
public class FormularyCoverageDetailResult {

    protected Result result;
    protected ArrayOfFormularyCoverageDetail formularyCoverageDetailArray;

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
     * Gets the value of the formularyCoverageDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFormularyCoverageDetail }
     *     
     */
    public ArrayOfFormularyCoverageDetail getFormularyCoverageDetailArray() {
        return formularyCoverageDetailArray;
    }

    /**
     * Sets the value of the formularyCoverageDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFormularyCoverageDetail }
     *     
     */
    public void setFormularyCoverageDetailArray(ArrayOfFormularyCoverageDetail value) {
        this.formularyCoverageDetailArray = value;
    }

}
