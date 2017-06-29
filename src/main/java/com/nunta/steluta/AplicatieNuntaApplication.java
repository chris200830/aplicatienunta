package com.nunta.steluta;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;

//import com.nunta.steluta.wsdl.client.CurrencyClient;


@SpringBootApplication
public class AplicatieNuntaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AplicatieNuntaApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner lookup(CurrencyClient currencyClient) {
		return args -> {

			Double response = currencyClient.getConversionRate(Currency.GBP, Currency.USD);
			System.err.println(response);
		};
	} */
}
