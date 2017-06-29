package com.nunta.steluta.util.currency;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

public class CurrencyConvertor {
	private static CurrencyConvertor instance;

	public static CurrencyConvertor getInstance() {
		if (instance == null)
			instance = new CurrencyConvertor();
		return instance;
	}
	
	public float convertCurrency(String valuta, float suma) {
	    MonetaryAmount moneda = Monetary.getDefaultAmountFactory().setCurrency(valuta)
	    	      .setNumber(suma).create();
	    	 
	    	    CurrencyConversion toRON = MonetaryConversions.getConversion("RON");
	    	 
	    	    MonetaryAmount convertedAmountToRON = moneda.with(toRON);
	    	    String delims = " ";
	    	    String[] tokens = convertedAmountToRON.toString().split(delims);
	    	    
	    	    return Float.parseFloat(String.format("%.2f", Float.parseFloat(tokens[1])));
	}
}
