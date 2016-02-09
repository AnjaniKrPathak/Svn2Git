
package https.secure_newcropaccounts_com.v7.webservices;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TimePeriodQueryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TimePeriodQueryType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DateRange"/>
 *     &lt;enumeration value="Quarterly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TimePeriodQueryType")
@XmlEnum
public enum TimePeriodQueryType {

    @XmlEnumValue("DateRange")
    DATE_RANGE("DateRange"),
    @XmlEnumValue("Quarterly")
    QUARTERLY("Quarterly");
    private final String value;

    TimePeriodQueryType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TimePeriodQueryType fromValue(String v) {
        for (TimePeriodQueryType c: TimePeriodQueryType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
