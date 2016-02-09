
package https.secure_newcropaccounts_com.v7.webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRegisterPrescriberDetailV2 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRegisterPrescriberDetailV2">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RegisterPrescriberDetailV2" type="{https://secure.newcropaccounts.com/V7/webservices}RegisterPrescriberDetailV2" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRegisterPrescriberDetailV2", propOrder = {
    "registerPrescriberDetailV2"
})
public class ArrayOfRegisterPrescriberDetailV2 {

    @XmlElement(name = "RegisterPrescriberDetailV2", nillable = true)
    protected List<RegisterPrescriberDetailV2> registerPrescriberDetailV2;

    /**
     * Gets the value of the registerPrescriberDetailV2 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the registerPrescriberDetailV2 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRegisterPrescriberDetailV2().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RegisterPrescriberDetailV2 }
     * 
     * 
     */
    public List<RegisterPrescriberDetailV2> getRegisterPrescriberDetailV2() {
        if (registerPrescriberDetailV2 == null) {
            registerPrescriberDetailV2 = new ArrayList<RegisterPrescriberDetailV2>();
        }
        return this.registerPrescriberDetailV2;
    }

}
