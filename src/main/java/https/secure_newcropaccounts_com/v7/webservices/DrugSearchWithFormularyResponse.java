
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
 *         &lt;element name="DrugSearchWithFormularyResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugFormularyDetailResult" minOccurs="0"/>
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
    "drugSearchWithFormularyResult"
})
@XmlRootElement(name = "DrugSearchWithFormularyResponse")
public class DrugSearchWithFormularyResponse {

    @XmlElement(name = "DrugSearchWithFormularyResult")
    protected DrugFormularyDetailResult drugSearchWithFormularyResult;

    /**
     * Gets the value of the drugSearchWithFormularyResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public DrugFormularyDetailResult getDrugSearchWithFormularyResult() {
        return drugSearchWithFormularyResult;
    }

    /**
     * Sets the value of the drugSearchWithFormularyResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugFormularyDetailResult }
     *     
     */
    public void setDrugSearchWithFormularyResult(DrugFormularyDetailResult value) {
        this.drugSearchWithFormularyResult = value;
    }

}
