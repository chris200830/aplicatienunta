//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.24 at 05:23:06 PM EEST 
//


package com.nunta.steluta.wsdl.currency;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HistoricalPeriodTypes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="HistoricalPeriodTypes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Daily"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "HistoricalPeriodTypes")
@XmlEnum
public enum HistoricalPeriodTypes {

    @XmlEnumValue("Daily")
    DAILY("Daily");
    private final String value;

    HistoricalPeriodTypes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static HistoricalPeriodTypes fromValue(String v) {
        for (HistoricalPeriodTypes c: HistoricalPeriodTypes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
