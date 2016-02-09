
package com.newcropaccounts.secure.interfacev7.ncstandard;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LanguageType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LanguageType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Arabic"/>
 *     &lt;enumeration value="Chinese"/>
 *     &lt;enumeration value="Chinese (Simplified)"/>
 *     &lt;enumeration value="Creole"/>
 *     &lt;enumeration value="English"/>
 *     &lt;enumeration value="French"/>
 *     &lt;enumeration value="German"/>
 *     &lt;enumeration value="Greek"/>
 *     &lt;enumeration value="Italian"/>
 *     &lt;enumeration value="Japanese"/>
 *     &lt;enumeration value="Korean"/>
 *     &lt;enumeration value="Polish"/>
 *     &lt;enumeration value="Portuguese"/>
 *     &lt;enumeration value="Russian"/>
 *     &lt;enumeration value="Spanish"/>
 *     &lt;enumeration value="Tagalog"/>
 *     &lt;enumeration value="Turkish"/>
 *     &lt;enumeration value="Vietnamese"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LanguageType", namespace = "http://secure.newcropaccounts.com/interfaceV7:NCStandard")
@XmlEnum
public enum LanguageType {

    @XmlEnumValue("Arabic")
    ARABIC("Arabic"),
    @XmlEnumValue("Chinese")
    CHINESE("Chinese"),
    @XmlEnumValue("Chinese (Simplified)")
    CHINESE_SIMPLIFIED("Chinese (Simplified)"),
    @XmlEnumValue("Creole")
    CREOLE("Creole"),
    @XmlEnumValue("English")
    ENGLISH("English"),
    @XmlEnumValue("French")
    FRENCH("French"),
    @XmlEnumValue("German")
    GERMAN("German"),
    @XmlEnumValue("Greek")
    GREEK("Greek"),
    @XmlEnumValue("Italian")
    ITALIAN("Italian"),
    @XmlEnumValue("Japanese")
    JAPANESE("Japanese"),
    @XmlEnumValue("Korean")
    KOREAN("Korean"),
    @XmlEnumValue("Polish")
    POLISH("Polish"),
    @XmlEnumValue("Portuguese")
    PORTUGUESE("Portuguese"),
    @XmlEnumValue("Russian")
    RUSSIAN("Russian"),
    @XmlEnumValue("Spanish")
    SPANISH("Spanish"),
    @XmlEnumValue("Tagalog")
    TAGALOG("Tagalog"),
    @XmlEnumValue("Turkish")
    TURKISH("Turkish"),
    @XmlEnumValue("Vietnamese")
    VIETNAMESE("Vietnamese");
    private final String value;

    LanguageType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static LanguageType fromValue(String v) {
        for (LanguageType c: LanguageType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
