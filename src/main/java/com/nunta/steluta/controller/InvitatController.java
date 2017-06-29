package com.nunta.steluta.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.nunta.steluta.entity.Cazare;
import com.nunta.steluta.entity.Invitat;
import com.nunta.steluta.entity.Nunta;
import com.nunta.steluta.service.CazareService;
import com.nunta.steluta.service.InvitatService;
import com.nunta.steluta.service.MasaService;
import com.nunta.steluta.service.NuntaService;
import com.nunta.steluta.util.currency.CurrencyConvertor;
import com.nunta.steluta.util.date.Data;
import com.nunta.steluta.util.parser.Parser;

@Controller
public class InvitatController {
	@Autowired
	private InvitatService invitatService;

	@Autowired
	private CazareService cazareService;

	@Autowired
	private MasaService masaService;

	@Autowired
	private NuntaService nuntaService;

	@RequestMapping(value = "/nunta/{id}/invitati{pageId}", method = RequestMethod.GET)
	public String invitati(@PathVariable("id") int nuntaId, Model model, @RequestParam("pageId") int pageId) {
		// model.addAttribute("invitati",
		// invitatService.findByNuntaIdEquals(idNunta));
		Page<Invitat> page = invitatService.findByNuntaIdEquals(nuntaId, pageId - 1, 2);
		// System.out.println(page.getTotalPages());
		model.addAttribute("pages", page.getTotalPages());
		model.addAttribute("invitati", page.getContent());
		return "invitati";
	}

	@GetMapping("/nunta/{id}/adauga-invitat")
	public String adauga(@PathVariable("id") int nuntaId, Model model) {
		model.addAttribute("invitatFormular", new Invitat());
		model.addAttribute("mese", masaService.findAll());
		model.addAttribute("cazareFormular", new Cazare());
		model.addAttribute("dati", Data.getInstance());

		return "adauga-invitat";
	}

	@PostMapping("/nunta/{id}/adauga-invitat")
	public String adauga(@PathVariable("id") int nuntaId, Invitat invitatFormular, Cazare cazareFormular,
			HttpServletRequest request) {
		Integer ziInitiala = Integer.parseInt(request.getParameter("ziInitiala"));
		Integer lunaInitiala = Integer.parseInt(request.getParameter("lunaInitiala"));
		Integer anInitial = Integer.parseInt(request.getParameter("anInitial"));

		Integer ziFinala = Integer.parseInt(request.getParameter("ziFinala"));
		Integer lunaFinala = Integer.parseInt(request.getParameter("lunaFinala"));
		Integer anFinal = Integer.parseInt(request.getParameter("anFinal"));

		Nunta nunta = nuntaService.findOne(nuntaId);
		invitatFormular.setMasa(masaService.findByNrMasaEquals(request.getParameter("nrMasa")));

		// Invitat
		Invitat invitat = invitatService.transfer(new Invitat(), invitatFormular);
		invitat.setNunta(nunta);

		// Cazare
		if (!cazareFormular.getNumeHotel().isEmpty()) {
			cazareFormular.setDataInitiala(Data.getInstance().getCalendarData(ziInitiala, lunaInitiala, anInitial));
			cazareFormular.setDataFinala(Data.getInstance().getCalendarData(ziFinala, lunaFinala, anFinal));
			Cazare cazare = cazareService.transfer(new Cazare(), cazareFormular);
			cazareService.save(cazare);
			invitat.setCazare(cazare);
		} else
			invitat.setCazare(null);

		invitatService.save(invitat);
		nuntaService.addDar(nuntaId,
				CurrencyConvertor.getInstance().convertCurrency(invitat.getTipValuta(), invitat.getBani()));
		nuntaService.updateNrInvitatiForNunta(nuntaId);
		nuntaService.updateNrPersoanePrezenteForNunta(nuntaId);
		return ("redirect:/nunta/" + nuntaId + "/invitati?pageId=1");
	}

	@RequestMapping(value = "/nunta/{id}/sterge-invitat{idInvitat}", method = RequestMethod.GET)
	public String stergeInvitat(@PathVariable("id") int nuntaId, @RequestParam("idInvitat") int idInvitat, Model model,
			@RequestParam("pageId") int pageId) {
		Invitat invitat = invitatService.findOne(idInvitat);
		float bani = CurrencyConvertor.getInstance().convertCurrency(invitat.getTipValuta(), invitat.getBani());
		invitatService.delete(invitat);
		nuntaService.subtractDar(nuntaId, bani);
		nuntaService.updateNrInvitatiForNunta(nuntaId);
		nuntaService.updateNrPersoanePrezenteForNunta(nuntaId);
		if (invitat.getCazare() != null)
			cazareService.delete(invitat.getCazare());

		return ("redirect:/nunta/" + nuntaId + "/invitati?pageId=" + pageId);
	}

	@RequestMapping(value = "/nunta/{id}/modifica-invitat{idInvitat}", method = RequestMethod.GET)
	public String modificaInvitat(@PathVariable("id") int nuntaId, @RequestParam("idInvitat") int idInvitat,
			Model model, @RequestParam("pageId") int pageId) {
		Invitat i = invitatService.findOne(idInvitat);
		if (i.getCazare() != null) {
			Cazare cazare = i.getCazare();
			Calendar dI = cazare.getDataInitiala();
			Calendar dF = cazare.getDataFinala();

			Map<String, Integer> data = new HashMap<>();
			data.put("zIAleasa", dI.get(Calendar.DAY_OF_MONTH));
			data.put("lIAleasa", dI.get(Calendar.MONTH) + 1);
			data.put("aIAles", dI.get(Calendar.YEAR));
			data.put("zFAleasa", dF.get(Calendar.DAY_OF_MONTH));
			data.put("lFAleasa", dF.get(Calendar.MONTH) + 1);
			data.put("aFAles", dF.get(Calendar.YEAR));

			model.addAllAttributes(data);
			model.addAttribute("cazare", i.getCazare());
		} else
			model.addAttribute("cazare", new Cazare());
		model.addAttribute("invitat", i);
		model.addAttribute("masaAleasa", i.getMasa().getNrMasa());
		model.addAttribute("mese", masaService.findAll());

		model.addAttribute("dati", Data.getInstance());
		model.addAttribute("pageId", pageId);
		return "adauga-invitat";
	}

	@RequestMapping(value = "/nunta/{id}/modifica-invitat{idInvitat}", method = RequestMethod.POST)
	public String modificaInvitat(@PathVariable("id") int nuntaId, @RequestParam("idInvitat") int idInvitat,
			@ModelAttribute("invitat") Invitat invitat, @ModelAttribute("cazare") Cazare cazare,
			HttpServletRequest request, @RequestParam("pageId") int pageId) {
		Integer ziInitiala = Integer.parseInt(request.getParameter("ziInitiala"));
		Integer lunaInitiala = Integer.parseInt(request.getParameter("lunaInitiala"));
		Integer anInitial = Integer.parseInt(request.getParameter("anInitial"));

		Integer ziFinala = Integer.parseInt(request.getParameter("ziFinala"));
		Integer lunaFinala = Integer.parseInt(request.getParameter("lunaFinala"));
		Integer anFinal = Integer.parseInt(request.getParameter("anFinal"));

		invitat.setMasa(masaService.findByNrMasaEquals(request.getParameter("nrMasa")));

		Invitat invitatModificat = invitatService.transfer(invitatService.findOne(idInvitat), invitat);
		// Cazare

		if (cazare.getNumeHotel().isEmpty() && invitatModificat.getCazare() != null) {

			Cazare c = invitatModificat.getCazare();
			invitatModificat.setCazare(null);
			invitatService.save(invitatModificat);
			cazareService.delete(c);

		} else if (cazare.getNumeHotel().isEmpty())
			invitatModificat.setCazare(null);
		else {
			Cazare cazareModificata = new Cazare();
			cazare.setDataInitiala(Data.getInstance().getCalendarData(ziInitiala, lunaInitiala, anInitial));
			cazare.setDataFinala(Data.getInstance().getCalendarData(ziFinala, lunaFinala, anFinal));

			if (invitatModificat.getCazare() != null)
				cazareModificata = invitatModificat.getCazare();

			cazareService.save(cazareService.transfer(cazareModificata, cazare));
			invitatModificat.setCazare(cazareModificata);
		}

		invitatService.save(invitatModificat);
		
		nuntaService.recalculateDaruriForNunta(nuntaId);
		nuntaService.updateNrPersoanePrezenteForNunta(nuntaId);
		return ("redirect:/nunta/" + nuntaId + "/invitati?pageId=" + pageId);
	}

	@RequestMapping(value = "/nunta/{id}/invitat{idInvitat}", method = RequestMethod.GET)
	public String invitat(@PathVariable("id") int nuntaId, @RequestParam("idInvitat") int idInvitat, Model model) {
		model.addAttribute("invitat", invitatService.findOne(idInvitat));
		return ("invitat");
	}

	@RequestMapping(value = "/nunta/{id}/incarca-invitati{pageId}", method = RequestMethod.POST)
	public String incarcaInvitati(@PathVariable("id") int nuntaId,
			@RequestParam("invitatiFile") MultipartFile multipart, @RequestParam("pageId") int pageId) {

		try {
			String content = new String(multipart.getBytes());
			Scanner reader = new Scanner(content);
			reader.nextLine();
			while (reader.hasNextLine()) {
				Invitat i = Parser.getInstance().parseInvitat(reader.nextLine(), masaService);
				i.setNunta(nuntaService.findOne(nuntaId));
				if (i.getCazare() != null)
					cazareService.save(i.getCazare());
				invitatService.save(i);
				nuntaService.addDar(nuntaId,
						CurrencyConvertor.getInstance().convertCurrency(i.getTipValuta(), i.getBani()));
			}
			nuntaService.updateNrInvitatiForNunta(nuntaId);
			nuntaService.updateNrPersoanePrezenteForNunta(nuntaId);
			
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ("redirect:/nunta/" + nuntaId + "/invitati?pageId=" + pageId);
	}

	@RequestMapping(value = "/nunta/{id}/invitati-orderBy{value}", method = RequestMethod.GET)
	public String orderBy(@PathVariable("id") int nuntaId, @RequestParam("value") String value, Model model) {
		switch (value) {
		case "NumeASC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByNumeAsc(nuntaId));
			break;
		case "NumeDESC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByNumeDesc(nuntaId));
			break;
		case "PrenumeASC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByPrenumeAsc(nuntaId));
			break;
		case "PrenumeDESC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByPrenumeDesc(nuntaId));
			break;
		case "Absenti":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByConfirmarePrezentaAsc(nuntaId));
			break;
		case "Prezenti":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByConfirmarePrezentaDesc(nuntaId));
			break;
		case "JudetASC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByJudetAsc(nuntaId));
			break;
		case "JudetDESC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByJudetDesc(nuntaId));
			break;
		case "OrasASC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByOrasAsc(nuntaId));
			break;
		case "OrasDESC":
			model.addAttribute("invitati", invitatService.findAllByNuntaIdEqualsOrderByOrasDesc(nuntaId));
			break;
		default:
			model.addAttribute("invitati", invitatService.findAll());
		}
		return "invitati";
	}
}
