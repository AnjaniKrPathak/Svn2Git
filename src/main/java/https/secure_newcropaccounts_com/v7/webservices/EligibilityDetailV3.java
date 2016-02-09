
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EligibilityDetailV3 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EligibilityDetailV3">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PBM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Plan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardHolder" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CardHolderID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PharmacyBenefit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MailOrderBenefit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SpecialtyPharmacyBenefit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LTCBenefit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EligibilityDetailV3", propOrder = {
    "source",
    "pbm",
    "plan",
    "cardHolder",
    "cardHolderID",
    "pharmacyBenefit",
    "mailOrderBenefit",
    "specialtyPharmacyBenefit",
    "ltcBenefit"
})
public class EligibilityDetailV3 {

    @XmlElement(name = "Source")
    protected String source;
    @XmlElement(name = "PBM")
    protected String pbm;
    @XmlElement(name = "Plan")
    protected String plan;
    @XmlElement(name = "CardHolder")
    protected String cardHolder;
    @XmlElement(name = "CardHolderID")
    protected String cardHolderID;
    @XmlElement(name = "PharmacyBenefit")
    protected String pharmacyBenefit;
    @XmlElement(name = "MailOrderBenefit")
    protected String mailOrderBenefit;
    @XmlElement(name = "SpecialtyPharmacyBenefit")
    protected String specialtyPharmacyBenefit;
    @XmlElement(name = "LTCBenefit")
    protected String ltcBenefit;

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the pbm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPBM() {
        return pbm;
    }

    /**
     * Sets the value of the pbm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPBM(String value) {
        this.pbm = value;
    }

    /**
     * Gets the value of the plan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlan() {
        return plan;
    }

    /**
     * Sets the value of the plan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlan(String value) {
        this.plan = value;
    }

    /**
     * Gets the value of the cardHolder property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolder() {
        return cardHolder;
    }

    /**
     * Sets the value of the cardHolder property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolder(String value) {
        this.cardHolder = value;
    }

    /**
     * Gets the value of the cardHolderID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderID() {
        return cardHolderID;
    }

    /**
     * Sets the value of the cardHolderID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderID(String value) {
        this.cardHolderID = value;
    }

    /**
     * Gets the value of the pharmacyBenefit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPharmacyBenefit() {
        return pharmacyBenefit;
    }

    /**
     * Sets the value of the pharmacyBenefit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPharmacyBenefit(String value) {
        this.pharmacyBenefit = value;
    }

    /**
     * Gets the value of the mailOrderBenefit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailOrderBenefit() {
        return mailOrderBenefit;
    }

    /**
     * Sets the value of the mailOrderBenefit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailOrderBenefit(String value) {
        this.mailOrderBenefit = value;
    }

    /**
     * Gets the value of the specialtyPharmacyBenefit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialtyPharmacyBenefit() {
        return specialtyPharmacyBenefit;
    }

    /**
     * Sets the value of the specialtyPharmacyBenefit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialtyPharmacyBenefit(String value) {
        this.specialtyPharmacyBenefit = value;
    }

    /**
     * Gets the value of the ltcBenefit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLTCBenefit() {
        return ltcBenefit;
    }

    /**
     * Sets the value of the ltcBenefit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLTCBenefit(String value) {
        this.ltcBenefit = value;
    }

}
