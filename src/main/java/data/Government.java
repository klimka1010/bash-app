package data;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum(String.class)
public enum Government {
    @XmlEnumValue("Коммунизм")
    COMMYNISM,

    @XmlEnumValue("Меритократия")
    MERITOCRACY,

    @XmlEnumValue("Теллюрократия")
    TELLUROCRACY,

    @XmlEnumValue("Тимократия")
    TIMOCRACY;
}
