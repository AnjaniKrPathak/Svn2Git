
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
 *         &lt;element name="FormularyCoverageV3Result" type="{https://secure.newcropaccounts.com/V7/webservices}FormularyCoverageDetailResultV3" minOccurs="0"/>
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
    "formularyCoverageV3Result"
})
@XmlRootElement(name = "FormularyCoverageV3Response")
public class FormularyCoverageV3Response {

    @XmlElement(name = "FormularyCoverageV3Result")
    protected FormularyCoverageDetailResultV3 formularyCoverageV3Result;

    /**
     * Gets the value of the formularyCoverageV3Result property.
     * 
     * @return
     *     possible object is
     *     {@link FormularyCoverageDetailResultV3 }
     *     
     */
    public FormularyCoverageDetailResultV3 getFormularyCoverageV3Result() {
        return formularyCoverageV3Result;
    }

    /**
     * Sets the value of the formularyCoverageV3Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormularyCoverageDetailResultV3 }
     *     
     */
    public void setFormularyCoverageV3Result(FormularyCoverageDetailResultV3 value) {
        this.formularyCoverageV3Result = value;
    }

}
