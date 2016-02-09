
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugMonographResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugMonographResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="drugMonograph" type="{https://secure.newcropaccounts.com/V7/webservices}DrugMonograph" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugMonographResult", propOrder = {
    "result",
    "drugMonograph"
})
public class DrugMonographResult {

    protected Result result;
    protected DrugMonograph2 drugMonograph;

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
     * Gets the value of the drugMonograph property.
     * 
     * @return
     *     possible object is
     *     {@link DrugMonograph2 }
     *     
     */
    public DrugMonograph2 getDrugMonograph() {
        return drugMonograph;
    }

    /**
     * Sets the value of the drugMonograph property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugMonograph2 }
     *     
     */
    public void setDrugMonograph(DrugMonograph2 value) {
        this.drugMonograph = value;
    }

}
