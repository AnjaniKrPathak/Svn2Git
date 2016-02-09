
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
 *         &lt;element name="GetPBMDrugHistoryV2Result" type="{https://secure.newcropaccounts.com/V7/webservices}DrugHistoryDetailResult" minOccurs="0"/>
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
    "getPBMDrugHistoryV2Result"
})
@XmlRootElement(name = "GetPBMDrugHistoryV2Response")
public class GetPBMDrugHistoryV2Response {

    @XmlElement(name = "GetPBMDrugHistoryV2Result")
    protected DrugHistoryDetailResult getPBMDrugHistoryV2Result;

    /**
     * Gets the value of the getPBMDrugHistoryV2Result property.
     * 
     * @return
     *     possible object is
     *     {@link DrugHistoryDetailResult }
     *     
     */
    public DrugHistoryDetailResult getGetPBMDrugHistoryV2Result() {
        return getPBMDrugHistoryV2Result;
    }

    /**
     * Sets the value of the getPBMDrugHistoryV2Result property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugHistoryDetailResult }
     *     
     */
    public void setGetPBMDrugHistoryV2Result(DrugHistoryDetailResult value) {
        this.getPBMDrugHistoryV2Result = value;
    }

}
