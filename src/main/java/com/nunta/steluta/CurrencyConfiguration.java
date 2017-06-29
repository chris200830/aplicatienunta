package com.nunta.steluta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.nunta.steluta.wsdl.client.CurrencyClient;

@Configuration
public class CurrencyConfiguration {
	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		// this package must match the package in the <generatePackage> specified in
		// pom.xml
		marshaller.setContextPath("com.nunta.steluta.wsdl.currency");
		return marshaller;
	}

	@Bean
	public CurrencyClient currencyClient(Jaxb2Marshaller marshaller) {
		CurrencyClient client = new CurrencyClient();
		client.setDefaultUri("http://www.webservicex.net/CurrencyConvertor.asmx?WSDL");
		client.setMarshaller(marshaller);
		client.setUnmarshaller(marshaller);
		return client;
	}
}
