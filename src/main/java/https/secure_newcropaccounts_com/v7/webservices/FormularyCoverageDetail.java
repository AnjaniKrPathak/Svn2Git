
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormularyCoverageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FormularyCoverageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugConcept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormularyStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormularyCoverageDetail", propOrder = {
    "drugConcept",
    "formularyStatus"
})
public class FormularyCoverageDetail {

    @XmlElement(name = "DrugConcept")
    protected String drugConcept;
    @XmlElement(name = "FormularyStatus")
    protected String formularyStatus;

    /**
     * Gets the value of the drugConcept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugConcept() {
        return drugConcept;
    }

    /**
     * Sets the value of the drugConcept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugConcept(String value) {
        this.drugConcept = value;
    }

    /**
     * Gets the value of the formularyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFormularyStatus() {
        return formularyStatus;
    }

    /**
     * Sets the value of the formularyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFormularyStatus(String value) {
        this.formularyStatus = value;
    }

}
