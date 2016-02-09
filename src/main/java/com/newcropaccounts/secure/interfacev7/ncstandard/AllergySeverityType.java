
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AllergySeverityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AllergySeverityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unspecified"/>
 *     &lt;enumeration value="Mild"/>
 *     &lt;enumeration value="Moderate"/>
 *     &lt;enumeration value="Severe"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AllergySeverityType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum AllergySeverityType {

    @XmlEnumValue("Unspecified")
    UNSPECIFIED("Unspecified"),
    @XmlEnumValue("Mild")
    MILD("Mild"),
    @XmlEnumValue("Moderate")
    MODERATE("Moderate"),
    @XmlEnumValue("Severe")
    SEVERE("Severe");
    private final String value;

    AllergySeverityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AllergySeverityType fromValue(String v) {
        for (AllergySeverityType c: AllergySeverityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
