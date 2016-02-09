
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PharmacyDetailResultV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PharmacyDetailResultV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{https://secure.newcropaccounts.com/V7/webservices}Result" minOccurs="0"/>
 *         &lt;element name="pharmacyDetailArray" type="{https://secure.newcropaccounts.com/V7/webservices}ArrayOfPharmacyDetailV2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PharmacyDetailResultV2", propOrder = {
    "result",
    "pharmacyDetailArray"
})
public class PharmacyDetailResultV2 {

    protected Result result;
    protected ArrayOfPharmacyDetailV2 pharmacyDetailArray;

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
     * Gets the value of the pharmacyDetailArray property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPharmacyDetailV2 }
     *     
     */
    public ArrayOfPharmacyDetailV2 getPharmacyDetailArray() {
        return pharmacyDetailArray;
    }

    /**
     * Sets the value of the pharmacyDetailArray property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPharmacyDetailV2 }
     *     
     */
    public void setPharmacyDetailArray(ArrayOfPharmacyDetailV2 value) {
        this.pharmacyDetailArray = value;
    }

}
