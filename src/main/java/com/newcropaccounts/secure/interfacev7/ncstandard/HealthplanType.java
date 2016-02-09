
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HealthplanType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HealthplanType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Summary"/>
 *     &lt;enumeration value="Detail"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HealthplanType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum HealthplanType {

    @XmlEnumValue("Summary")
    SUMMARY("Summary"),
    @XmlEnumValue("Detail")
    DETAIL("Detail");
    private final String value;

    HealthplanType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HealthplanType fromValue(String v) {
        for (HealthplanType c: HealthplanType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
