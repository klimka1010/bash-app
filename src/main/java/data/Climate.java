package data;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Climate {
    @XmlEnumValue("Влажность суб тропики")
    HUMIDSUBTROPICAL,
    @XmlEnumValue("Влажность континента")
    HUMIDCONTINENTTAL,
    @XmlEnumValue("Океаник")
    OCEANIC,
    @XmlEnumValue("Полярный кэп")
    POLAR_ICECAP;
}
