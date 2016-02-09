
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
 *         &lt;element name="DrugPackageDetailsResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugPackageDetailResult" minOccurs="0"/>
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
    "drugPackageDetailsResult"
})
@XmlRootElement(name = "DrugPackageDetailsResponse")
public class DrugPackageDetailsResponse {

    @XmlElement(name = "DrugPackageDetailsResult")
    protected DrugPackageDetailResult drugPackageDetailsResult;

    /**
     * Gets the value of the drugPackageDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugPackageDetailResult }
     *     
     */
    public DrugPackageDetailResult getDrugPackageDetailsResult() {
        return drugPackageDetailsResult;
    }

    /**
     * Sets the value of the drugPackageDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugPackageDetailResult }
     *     
     */
    public void setDrugPackageDetailsResult(DrugPackageDetailResult value) {
        this.drugPackageDetailsResult = value;
    }

}
