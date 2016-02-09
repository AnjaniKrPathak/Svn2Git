
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
 *         &lt;element name="FormularyCoverageResult" type="{https://secure.newcropaccounts.com/V7/webservices}FormularyCoverageDetailResult" minOccurs="0"/>
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
    "formularyCoverageResult"
})
@XmlRootElement(name = "FormularyCoverageResponse")
public class FormularyCoverageResponse {

    @XmlElement(name = "FormularyCoverageResult")
    protected FormularyCoverageDetailResult formularyCoverageResult;

    /**
     * Gets the value of the formularyCoverageResult property.
     * 
     * @return
     *     possible object is
     *     {@link FormularyCoverageDetailResult }
     *     
     */
    public FormularyCoverageDetailResult getFormularyCoverageResult() {
        return formularyCoverageResult;
    }

    /**
     * Sets the value of the formularyCoverageResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormularyCoverageDetailResult }
     *     
     */
    public void setFormularyCoverageResult(FormularyCoverageDetailResult value) {
        this.formularyCoverageResult = value;
    }

}
