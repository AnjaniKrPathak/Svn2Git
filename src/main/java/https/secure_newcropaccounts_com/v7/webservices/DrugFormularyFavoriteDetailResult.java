
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugFormularyFavoriteDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugFormularyFavoriteDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="drugFormularyFavoriteDetail" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfDrugFormularyFavoriteDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugFormularyFavoriteDetailResult", propOrder = {
    "result",
    "drugFormularyFavoriteDetail"
})
public class DrugFormularyFavoriteDetailResult {

    protected Result result;
    protected ArrayOfDrugFormularyFavoriteDetail drugFormularyFavoriteDetail;

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
     * Gets the value of the drugFormularyFavoriteDetail property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDrugFormularyFavoriteDetail }
     *     
     */
    public ArrayOfDrugFormularyFavoriteDetail getDrugFormularyFavoriteDetail() {
        return drugFormularyFavoriteDetail;
    }

    /**
     * Sets the value of the drugFormularyFavoriteDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDrugFormularyFavoriteDetail }
     *     
     */
    public void setDrugFormularyFavoriteDetail(ArrayOfDrugFormularyFavoriteDetail value) {
        this.drugFormularyFavoriteDetail = value;
    }

}
