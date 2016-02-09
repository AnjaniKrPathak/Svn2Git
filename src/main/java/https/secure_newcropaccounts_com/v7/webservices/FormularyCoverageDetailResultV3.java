
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormularyCoverageDetailResultV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormularyCoverageDetailResultV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="formularyCoverageDetailV3Array" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfFormularyCoverageDetailV3" minOccurs="0"/>
 *         &lt;element name="formularyCoverageAlternativesDetailV3Array" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfFormularyCoverageDetailV3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormularyCoverageDetailResultV3", propOrder = {
    "result",
    "formularyCoverageDetailV3Array",
    "formularyCoverageAlternativesDetailV3Array"
})
public class FormularyCoverageDetailResultV3 {

    protected Result result;
    protected ArrayOfFormularyCoverageDetailV3 formularyCoverageDetailV3Array;
    protected ArrayOfFormularyCoverageDetailV3 formularyCoverageAlternativesDetailV3Array;

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
     * Gets the value of the formularyCoverageDetailV3Array property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFormularyCoverageDetailV3 }
     *     
     */
    public ArrayOfFormularyCoverageDetailV3 getFormularyCoverageDetailV3Array() {
        return formularyCoverageDetailV3Array;
    }

    /**
     * Sets the value of the formularyCoverageDetailV3Array property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFormularyCoverageDetailV3 }
     *     
     */
    public void setFormularyCoverageDetailV3Array(ArrayOfFormularyCoverageDetailV3 value) {
        this.formularyCoverageDetailV3Array = value;
    }

    /**
     * Gets the value of the formularyCoverageAlternativesDetailV3Array property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfFormularyCoverageDetailV3 }
     *     
     */
    public ArrayOfFormularyCoverageDetailV3 getFormularyCoverageAlternativesDetailV3Array() {
        return formularyCoverageAlternativesDetailV3Array;
    }

    /**
     * Sets the value of the formularyCoverageAlternativesDetailV3Array property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfFormularyCoverageDetailV3 }
     *     
     */
    public void setFormularyCoverageAlternativesDetailV3Array(ArrayOfFormularyCoverageDetailV3 value) {
        this.formularyCoverageAlternativesDetailV3Array = value;
    }

}
