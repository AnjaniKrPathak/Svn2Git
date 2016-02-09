
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
 *         &lt;element name="GetPBMDrugHistoryResult" type="{https://secure.newcropaccounts.com/V7/webservices}DrugHistoryDetailResult" minOccurs="0"/>
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
    "getPBMDrugHistoryResult"
})
@XmlRootElement(name = "GetPBMDrugHistoryResponse")
public class GetPBMDrugHistoryResponse {

    @XmlElement(name = "GetPBMDrugHistoryResult")
    protected DrugHistoryDetailResult getPBMDrugHistoryResult;

    /**
     * Gets the value of the getPBMDrugHistoryResult property.
     * 
     * @return
     *     possible object is
     *     {@link DrugHistoryDetailResult }
     *     
     */
    public DrugHistoryDetailResult getGetPBMDrugHistoryResult() {
        return getPBMDrugHistoryResult;
    }

    /**
     * Sets the value of the getPBMDrugHistoryResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugHistoryDetailResult }
     *     
     */
    public void setGetPBMDrugHistoryResult(DrugHistoryDetailResult value) {
        this.getPBMDrugHistoryResult = value;
    }

}
