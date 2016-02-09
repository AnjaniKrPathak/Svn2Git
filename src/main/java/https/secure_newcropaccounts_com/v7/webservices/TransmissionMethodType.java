
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TransmissionMethodType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TransmissionMethodType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Print"/>
 *     &lt;enumeration value="Fax"/>
 *     &lt;enumeration value="Electronic"/>
 *     &lt;enumeration value="HandWritten"/>
 *     &lt;enumeration value="Other"/>
 *     &lt;enumeration value="Indeterminate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TransmissionMethodType")
@XmlEnum
public enum TransmissionMethodType {

    @XmlEnumValue("Print")
    PRINT("Print"),
    @XmlEnumValue("Fax")
    FAX("Fax"),
    @XmlEnumValue("Electronic")
    ELECTRONIC("Electronic"),
    @XmlEnumValue("HandWritten")
    HAND_WRITTEN("HandWritten"),
    @XmlEnumValue("Other")
    OTHER("Other"),
    @XmlEnumValue("Indeterminate")
    INDETERMINATE("Indeterminate");
    private final String value;

    TransmissionMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransmissionMethodType fromValue(String v) {
        for (TransmissionMethodType c: TransmissionMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
