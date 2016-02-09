
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugSubstitutionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DrugSubstitutionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DispenseAsWritten"/>
 *     &lt;enumeration value="SubstitutionAllowed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DrugSubstitutionType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum DrugSubstitutionType {

    @XmlEnumValue("DispenseAsWritten")
    DISPENSE_AS_WRITTEN("DispenseAsWritten"),
    @XmlEnumValue("SubstitutionAllowed")
    SUBSTITUTION_ALLOWED("SubstitutionAllowed");
    private final String value;

    DrugSubstitutionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DrugSubstitutionType fromValue(String v) {
        for (DrugSubstitutionType c: DrugSubstitutionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
