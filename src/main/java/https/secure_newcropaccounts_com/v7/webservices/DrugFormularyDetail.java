
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugFormularyDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugFormularyDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="drugDetail" type="{https://secure.newcropaccounts.com/V7/webservices}DrugDetail" minOccurs="0"/>
 *         &lt;element name="formularyCoverage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugFormularyDetail", propOrder = {
    "drugDetail",
    "formularyCoverage"
})
public class DrugFormularyDetail {

    protected DrugDetail drugDetail;
    protected String formularyCoverage;

    /**
     * Gets the value of the drugDetail property.
     * 
     * @return
     *     possible object is
     *     {@link DrugDetail }
     *     
     */
    public DrugDetail getDrugDetail() {
        return drugDetail;
    }

    /**
     * Sets the value of the drugDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugDetail }
     *     
     */
    public void setDrugDetail(DrugDetail value) {
        this.drugDetail = value;
    }

    /**
     * Gets the value of the formularyCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormularyCoverage() {
        return formularyCoverage;
    }

    /**
     * Sets the value of the formularyCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormularyCoverage(String value) {
        this.formularyCoverage = value;
    }

}
