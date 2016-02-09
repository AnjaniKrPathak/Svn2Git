
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PrescriberNetwork.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PrescriberNetwork">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SureScripts"/>
 *     &lt;enumeration value="RxHub"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PrescriberNetwork", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum PrescriberNetwork {

    @XmlEnumValue("SureScripts")
    SURE_SCRIPTS("SureScripts"),
    @XmlEnumValue("RxHub")
    RX_HUB("RxHub");
    private final String value;

    PrescriberNetwork(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PrescriberNetwork fromValue(String v) {
        for (PrescriberNetwork c: PrescriberNetwork.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
