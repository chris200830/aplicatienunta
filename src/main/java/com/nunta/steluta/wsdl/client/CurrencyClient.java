package com.nunta.steluta.wsdl.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CurrencyClient extends WebServiceGatewaySupport {
/*
	public Double getConversionRate(Currency fromCurrency, Currency toCurrency) {
		ConversionRate conversionRate = new ObjectFactory().createConversionRate();
		conversionRate.setFromCurrency(fromCurrency);
		conversionRate.setToCurrency(toCurrency);

		ConversionRateResponse response = (ConversionRateResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://www.webservicex.net/CurrencyConvertor.asmx?WSDL", conversionRate, 
						new SoapActionCallback("http://www.webserviceX.NET/ConversionRate"));

		return response.getConversionRateResult();
	} */
}
