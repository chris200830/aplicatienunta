//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.06.24 at 05:23:06 PM EEST 
//


package com.nunta.steluta.wsdl.currency;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetChartDesignResult" type="{http://www.xignite.com/services/}ChartDesign" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getChartDesignResult"
})
@XmlRootElement(name = "GetChartDesignResponse")
public class GetChartDesignResponse {

    @XmlElement(name = "GetChartDesignResult")
    protected ChartDesign getChartDesignResult;

    /**
     * Gets the value of the getChartDesignResult property.
     * 
     * @return
     *     possible object is
     *     {@link ChartDesign }
     *     
     */
    public ChartDesign getGetChartDesignResult() {
        return getChartDesignResult;
    }

    /**
     * Sets the value of the getChartDesignResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChartDesign }
     *     
     */
    public void setGetChartDesignResult(ChartDesign value) {
        this.getChartDesignResult = value;
    }

}