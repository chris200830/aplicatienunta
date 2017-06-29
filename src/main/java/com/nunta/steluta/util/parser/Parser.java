package com.nunta.steluta.util.parser;

import java.util.Calendar;

import org.springframework.web.multipart.MultipartFile;

import com.nunta.steluta.entity.Achizitie;
import com.nunta.steluta.entity.Cazare;
import com.nunta.steluta.entity.Cost;
import com.nunta.steluta.entity.Invitat;
import com.nunta.steluta.service.CostService;
import com.nunta.steluta.service.MasaService;

public class Parser {
	private static Parser instance;

	public static Parser getInstance() {
		if (instance == null)
			return new Parser();

		return instance;
	}

	public boolean checkExtension(String extension, MultipartFile multipart) {
		String filename = multipart.getOriginalFilename();
		String fileExtension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

		if (!fileExtension.equals(extension))
			return false;

		return true;
	}

	public Invitat parseInvitat(String line, MasaService masaService) {

		Invitat i = new Invitat();

		String[] tokens = line.split(",");
		i.setNume(tokens[0]);
		i.setPrenume(tokens[1]);
		if (tokens[2].equals("DA"))
			i.setConfirmarePrezenta(true);
		else
			i.setConfirmarePrezenta(false);

		i.setDar(tokens[3]);
		if (tokens[4].isEmpty()) {
			i.setBani(0);
			i.setTipValuta("RON");
		} else {
			i.setBani(Float.parseFloat(tokens[4]));
			i.setTipValuta(tokens[5]);
		}
		i.setJudet(tokens[6]);
		i.setOras(tokens[7]);
		i.setDetalii(tokens[8]);
		i.setMasa(masaService.findByNrMasaEquals(tokens[9]));
		if (tokens.length > 10) {
			Cazare c = new Cazare();
			c.setNumeHotel(tokens[10]);
			c.setTipCamera(tokens[11]);
			String[] ini = tokens[12].split("\\.");
			String[] fin = tokens[13].split("\\.");
			Calendar dataInitiala = Calendar.getInstance();
			dataInitiala.set(Calendar.DAY_OF_MONTH, Integer.parseInt(ini[0]));
			dataInitiala.set(Calendar.MONTH, Integer.parseInt(ini[1]) - 1);
			dataInitiala.set(Calendar.YEAR, Integer.parseInt(ini[2]));

			Calendar dataFinala = Calendar.getInstance();
			dataFinala.set(Calendar.DAY_OF_MONTH, Integer.parseInt(fin[0]));
			dataFinala.set(Calendar.MONTH, Integer.parseInt(fin[1]) - 1);
			dataFinala.set(Calendar.YEAR, Integer.parseInt(fin[2]));
			c.setDataInitiala(dataInitiala);
			c.setDataFinala(dataFinala);
			i.setCazare(c);
		}
		return i;
	}

	public Achizitie parseAchizitie(String line, CostService costService) {
		Achizitie a = new Achizitie();
		String[] tokens = line.split(",");

		a.setDenumireServiciu(tokens[0]);
		a.setNumeFurnizor(tokens[1]);

		if (!tokens[2].isEmpty()) {
			String[] s = tokens[2].split("\\.");
			Calendar dataAchizitiei = Calendar.getInstance();
			dataAchizitiei.set(Calendar.DAY_OF_MONTH, Integer.parseInt(s[0]));
			dataAchizitiei.set(Calendar.MONTH, Integer.parseInt(s[1]) - 1);
			dataAchizitiei.set(Calendar.YEAR, Integer.parseInt(s[2]));
			a.setDataAchizitiei(dataAchizitiei);
		}

		Cost c = new Cost();
		if (tokens.length > 3) {
			if (tokens[3].isEmpty())
				c.setAvans(0);
			else
				c.setAvans(Float.parseFloat(tokens[3]));

			if (tokens.length > 4)
				c.setRest(Float.parseFloat(tokens[4]));
			else
				c.setRest(0);
		} else {
			c.setAvans(0);
			c.setRest(0);
			c.setTotal(0);
		}
		c.setTotal(c.getAvans() + c.getRest());
		a.setCost(c);
		return a;
	}
}
