
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
 *         &lt;element name="FormularyAlternativesResult" type="{https://secure.newcropaccounts.com/V7/webservices}FormularyCoverageDetailResult" minOccurs="0"/>
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
    "formularyAlternativesResult"
})
@XmlRootElement(name = "FormularyAlternativesResponse")
public class FormularyAlternativesResponse {

    @XmlElement(name = "FormularyAlternativesResult")
    protected FormularyCoverageDetailResult formularyAlternativesResult;

    /**
     * Gets the value of the formularyAlternativesResult property.
     * 
     * @return
     *     possible object is
     *     {@link FormularyCoverageDetailResult }
     *     
     */
    public FormularyCoverageDetailResult getFormularyAlternativesResult() {
        return formularyAlternativesResult;
    }

    /**
     * Sets the value of the formularyAlternativesResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link FormularyCoverageDetailResult }
     *     
     */
    public void setFormularyAlternativesResult(FormularyCoverageDetailResult value) {
        this.formularyAlternativesResult = value;
    }

}
