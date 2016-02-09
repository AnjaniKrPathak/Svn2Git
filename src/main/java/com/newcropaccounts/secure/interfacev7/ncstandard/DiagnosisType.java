
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiagnosisType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DiagnosisType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ICD9"/>
 *     &lt;enumeration value="ICD9CM"/>
 *     &lt;enumeration value="ICD10"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DiagnosisType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum DiagnosisType {

    @XmlEnumValue("ICD9")
    ICD_9("ICD9"),
    @XmlEnumValue("ICD9CM")
    ICD_9_CM("ICD9CM"),
    @XmlEnumValue("ICD10")
    ICD_10("ICD10");
    private final String value;

    DiagnosisType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DiagnosisType fromValue(String v) {
        for (DiagnosisType c: DiagnosisType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
