
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LabOrderDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LabOrderDetailResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="labOrderDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfLabOrderDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LabOrderDetailResult", propOrder = {
    "result",
    "labOrderDetailArray"
})
public class LabOrderDetailResult {

    protected Result result;
    protected ArrayOfLabOrderDetail labOrderDetailArray;

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
     * Gets the value of the labOrderDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLabOrderDetail }
     *     
     */
    public ArrayOfLabOrderDetail getLabOrderDetailArray() {
        return labOrderDetailArray;
    }

    /**
     * Sets the value of the labOrderDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLabOrderDetail }
     *     
     */
    public void setLabOrderDetailArray(ArrayOfLabOrderDetail value) {
        this.labOrderDetailArray = value;
    }

}
