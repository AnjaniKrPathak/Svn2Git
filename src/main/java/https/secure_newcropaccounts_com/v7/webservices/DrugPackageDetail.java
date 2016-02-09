
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugPackageDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugPackageDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PackageInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PackageSize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugPackageDetail", propOrder = {
    "packageInfo",
    "packageType",
    "packageSize"
})
public class DrugPackageDetail {

    @XmlElement(name = "PackageInfo")
    protected String packageInfo;
    @XmlElement(name = "PackageType")
    protected String packageType;
    @XmlElement(name = "PackageSize")
    protected String packageSize;

    /**
     * Gets the value of the packageInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageInfo() {
        return packageInfo;
    }

    /**
     * Sets the value of the packageInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageInfo(String value) {
        this.packageInfo = value;
    }

    /**
     * Gets the value of the packageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageType() {
        return packageType;
    }

    /**
     * Sets the value of the packageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageType(String value) {
        this.packageType = value;
    }

    /**
     * Gets the value of the packageSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackageSize() {
        return packageSize;
    }

    /**
     * Sets the value of the packageSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackageSize(String value) {
        this.packageSize = value;
    }

}
