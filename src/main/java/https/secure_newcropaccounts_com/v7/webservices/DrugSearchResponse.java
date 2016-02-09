
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
 *         &lt;element name="DrugSearchResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugDetailResult" minOccurs="0"/>
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
    "drugSearchResult"
})
@XmlRootElement(name = "DrugSearchResponse")
public class DrugSearchResponse {

    @XmlElement(name = "DrugSearchResult")
    protected DrugDetailResult drugSearchResult;

    /**
     * Gets the value of the drugSearchResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugDetailResult }
     *     
     */
    public DrugDetailResult getDrugSearchResult() {
        return drugSearchResult;
    }

    /**
     * Sets the value of the drugSearchResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugDetailResult }
     *     
     */
    public void setDrugSearchResult(DrugDetailResult value) {
        this.drugSearchResult = value;
    }

}
