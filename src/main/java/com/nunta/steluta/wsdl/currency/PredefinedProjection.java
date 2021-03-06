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
 * <p>Java class for PredefinedProjection.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PredefinedProjection">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Orthogonal"/>
 *     &lt;enumeration value="OrthogonalElevated"/>
 *     &lt;enumeration value="OrthogonalHorizontalLeft"/>
 *     &lt;enumeration value="OrthogonalHorizontalRight"/>
 *     &lt;enumeration value="OrthogonalHalf"/>
 *     &lt;enumeration value="OrthogonalHalfHorizontalLeft"/>
 *     &lt;enumeration value="OrthogonalHalfHorizontalRight"/>
 *     &lt;enumeration value="OrthogonalHalfRotated"/>
 *     &lt;enumeration value="OrthogonalHalfElevated"/>
 *     &lt;enumeration value="Perspective"/>
 *     &lt;enumeration value="PerspectiveHorizontalLeft"/>
 *     &lt;enumeration value="PerspectiveHorizontalRight"/>
 *     &lt;enumeration value="PerspectiveRotated"/>
 *     &lt;enumeration value="PerspectiveElevated"/>
 *     &lt;enumeration value="PerspectiveTilted"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PredefinedProjection")
@XmlEnum
public enum PredefinedProjection {

    @XmlEnumValue("Orthogonal")
    ORTHOGONAL("Orthogonal"),
    @XmlEnumValue("OrthogonalElevated")
    ORTHOGONAL_ELEVATED("OrthogonalElevated"),
    @XmlEnumValue("OrthogonalHorizontalLeft")
    ORTHOGONAL_HORIZONTAL_LEFT("OrthogonalHorizontalLeft"),
    @XmlEnumValue("OrthogonalHorizontalRight")
    ORTHOGONAL_HORIZONTAL_RIGHT("OrthogonalHorizontalRight"),
    @XmlEnumValue("OrthogonalHalf")
    ORTHOGONAL_HALF("OrthogonalHalf"),
    @XmlEnumValue("OrthogonalHalfHorizontalLeft")
    ORTHOGONAL_HALF_HORIZONTAL_LEFT("OrthogonalHalfHorizontalLeft"),
    @XmlEnumValue("OrthogonalHalfHorizontalRight")
    ORTHOGONAL_HALF_HORIZONTAL_RIGHT("OrthogonalHalfHorizontalRight"),
    @XmlEnumValue("OrthogonalHalfRotated")
    ORTHOGONAL_HALF_ROTATED("OrthogonalHalfRotated"),
    @XmlEnumValue("OrthogonalHalfElevated")
    ORTHOGONAL_HALF_ELEVATED("OrthogonalHalfElevated"),
    @XmlEnumValue("Perspective")
    PERSPECTIVE("Perspective"),
    @XmlEnumValue("PerspectiveHorizontalLeft")
    PERSPECTIVE_HORIZONTAL_LEFT("PerspectiveHorizontalLeft"),
    @XmlEnumValue("PerspectiveHorizontalRight")
    PERSPECTIVE_HORIZONTAL_RIGHT("PerspectiveHorizontalRight"),
    @XmlEnumValue("PerspectiveRotated")
    PERSPECTIVE_ROTATED("PerspectiveRotated"),
    @XmlEnumValue("PerspectiveElevated")
    PERSPECTIVE_ELEVATED("PerspectiveElevated"),
    @XmlEnumValue("PerspectiveTilted")
    PERSPECTIVE_TILTED("PerspectiveTilted");
    private final String value;

    PredefinedProjection(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PredefinedProjection fromValue(String v) {
        for (PredefinedProjection c: PredefinedProjection.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
