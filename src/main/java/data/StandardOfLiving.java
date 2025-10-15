package data;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum StandardOfLiving {
    @XmlEnumValue("Очень высокий")
    VERY_HIGH,
    @XmlEnumValue("Высокий")
    HIGH,
    @XmlEnumValue("Средний")
    MEDIUM,
    @XmlEnumValue("Очень низкий")
    ULTRA_LOW;
}
