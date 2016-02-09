
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugDiseaseDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugDiseaseDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="drugDiseaseDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfDrugDiseaseDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugDiseaseDetailResult", propOrder = {
    "result",
    "drugDiseaseDetailArray"
})
public class DrugDiseaseDetailResult {

    protected Result result;
    protected ArrayOfDrugDiseaseDetail drugDiseaseDetailArray;

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
     * Gets the value of the drugDiseaseDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDrugDiseaseDetail }
     *     
     */
    public ArrayOfDrugDiseaseDetail getDrugDiseaseDetailArray() {
        return drugDiseaseDetailArray;
    }

    /**
     * Sets the value of the drugDiseaseDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDrugDiseaseDetail }
     *     
     */
    public void setDrugDiseaseDetailArray(ArrayOfDrugDiseaseDetail value) {
        this.drugDiseaseDetailArray = value;
    }

}
