
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RegisterPrescriberDetailV2Result complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RegisterPrescriberDetailV2Result">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="registerPrescriberDetailV2Array" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfRegisterPrescriberDetailV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterPrescriberDetailV2Result", propOrder = {
    "result",
    "registerPrescriberDetailV2Array"
})
public class RegisterPrescriberDetailV2Result {

    protected Result result;
    protected ArrayOfRegisterPrescriberDetailV2 registerPrescriberDetailV2Array;

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
     * Gets the value of the registerPrescriberDetailV2Array property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRegisterPrescriberDetailV2 }
     *     
     */
    public ArrayOfRegisterPrescriberDetailV2 getRegisterPrescriberDetailV2Array() {
        return registerPrescriberDetailV2Array;
    }

    /**
     * Sets the value of the registerPrescriberDetailV2Array property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRegisterPrescriberDetailV2 }
     *     
     */
    public void setRegisterPrescriberDetailV2Array(ArrayOfRegisterPrescriberDetailV2 value) {
        this.registerPrescriberDetailV2Array = value;
    }

}
