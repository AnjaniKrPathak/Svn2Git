
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriptionTransmissionQueryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrescriptionTransmissionQueryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PrescriptionIDs"/>
 *     &lt;enumeration value="PrescriptionGuid"/>
 *     &lt;enumeration value="TransactionGuid"/>
 *     &lt;enumeration value="TransactionDetailGuid"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrescriptionTransmissionQueryType")
@XmlEnum
public enum PrescriptionTransmissionQueryType {

    @XmlEnumValue("PrescriptionIDs")
    PRESCRIPTION_I_DS("PrescriptionIDs"),
    @XmlEnumValue("PrescriptionGuid")
    PRESCRIPTION_GUID("PrescriptionGuid"),
    @XmlEnumValue("TransactionGuid")
    TRANSACTION_GUID("TransactionGuid"),
    @XmlEnumValue("TransactionDetailGuid")
    TRANSACTION_DETAIL_GUID("TransactionDetailGuid");
    private final String value;

    PrescriptionTransmissionQueryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrescriptionTransmissionQueryType fromValue(String v) {
        for (PrescriptionTransmissionQueryType c: PrescriptionTransmissionQueryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
