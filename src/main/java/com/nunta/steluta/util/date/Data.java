package com.nunta.steluta.util.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Data {	
	private static Data instance;
	private List<Integer> days;
	private List<Integer> months;
	private List<Integer> years;

	private Data() {
		days = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++)
			days.add(i);
		
		months = new ArrayList<Integer>();
		for(int i = 1; i <= 12; i++)
			months.add(i);
		
		years = new ArrayList<Integer>();
		for (int i = 2010; i <= 2050; i++)
			years.add(i);
	}
	
	public static Data getInstance() {
		if (instance == null)
			instance = new Data();
		return instance;
	}
	
	public List<Integer> getDays() {
		return days;
	}

	public List<Integer> getMonths() {
		return months;
	}

	public List<Integer> getYears() {
		return years;
	}
	
	public Calendar getCalendarData(int zi, int luna, int an) {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_MONTH, zi);
		data.set(Calendar.MONTH, luna - 1);
		data.set(Calendar.YEAR, an);
		return data;
	}
}
