
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
 *         &lt;element name="FormularyAlternativesWithDrugInfoResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyDetailResult" minOccurs="0"/>
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
    "formularyAlternativesWithDrugInfoResult"
})
@XmlRootElement(name = "FormularyAlternativesWithDrugInfoResponse")
public class FormularyAlternativesWithDrugInfoResponse {

    @XmlElement(name = "FormularyAlternativesWithDrugInfoResult")
    protected DrugFormularyDetailResult formularyAlternativesWithDrugInfoResult;

    /**
     * Gets the value of the formularyAlternativesWithDrugInfoResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public DrugFormularyDetailResult getFormularyAlternativesWithDrugInfoResult() {
        return formularyAlternativesWithDrugInfoResult;
    }

    /**
     * Sets the value of the formularyAlternativesWithDrugInfoResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public void setFormularyAlternativesWithDrugInfoResult(DrugFormularyDetailResult value) {
        this.formularyAlternativesWithDrugInfoResult = value;
    }

}
