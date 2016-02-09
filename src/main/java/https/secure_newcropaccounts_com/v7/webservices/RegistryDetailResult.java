
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegistryDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegistryDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="registryDetail" type="{https://secure.newcropaccounts.com/V7/webservices}RegistryDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistryDetailResult", propOrder = {
    "result",
    "registryDetail"
})
public class RegistryDetailResult {

    protected Result result;
    protected RegistryDetail registryDetail;

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
     * Gets the value of the registryDetail property.
     * 
     * @return
     *     possible object is
     *     {@link RegistryDetail }
     *     
     */
    public RegistryDetail getRegistryDetail() {
        return registryDetail;
    }

    /**
     * Sets the value of the registryDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistryDetail }
     *     
     */
    public void setRegistryDetail(RegistryDetail value) {
        this.registryDetail = value;
    }

}
