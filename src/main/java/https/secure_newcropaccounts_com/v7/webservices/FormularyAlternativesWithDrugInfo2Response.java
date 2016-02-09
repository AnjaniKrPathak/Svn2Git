
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
 *         &lt;element name="FormularyAlternativesWithDrugInfo2Result" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyDetailResult" minOccurs="0"/>
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
    "formularyAlternativesWithDrugInfo2Result"
})
@XmlRootElement(name = "FormularyAlternativesWithDrugInfo2Response")
public class FormularyAlternativesWithDrugInfo2Response {

    @XmlElement(name = "FormularyAlternativesWithDrugInfo2Result")
    protected DrugFormularyDetailResult formularyAlternativesWithDrugInfo2Result=new DrugFormularyDetailResult();

    /**
     * Gets the value of the formularyAlternativesWithDrugInfo2Result property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public DrugFormularyDetailResult getFormularyAlternativesWithDrugInfo2Result() {
    	//System.out.println(":::getFormularyAlternativesWithDrugInfo2Result getter:");
        return formularyAlternativesWithDrugInfo2Result;
    }

    /**
     * Sets the value of the formularyAlternativesWithDrugInfo2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public void setFormularyAlternativesWithDrugInfo2Result(DrugFormularyDetailResult value) {
    	//System.out.println(":::getFormularyAlternativesWithDrugInfo2Result setter:");
        this.formularyAlternativesWithDrugInfo2Result = value;
    }

}
