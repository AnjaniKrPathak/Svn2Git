
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugDatabaseType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DrugDatabaseType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FDA"/>
 *     &lt;enumeration value="FDB"/>
 *     &lt;enumeration value="RXNORM"/>
 *     &lt;enumeration value="Z"/>
 *     &lt;enumeration value="Y"/>
 *     &lt;enumeration value="MULTUM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DrugDatabaseType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum DrugDatabaseType {

    FDA,
    FDB,
    RXNORM,
    Z,
    Y,
    MULTUM;

    public String value() {
        return name();
    }

    public static DrugDatabaseType fromValue(String v) {
        return valueOf(v);
    }

}
