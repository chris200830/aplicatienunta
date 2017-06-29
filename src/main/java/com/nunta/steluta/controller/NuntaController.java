package com.nunta.steluta.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nunta.steluta.entity.Nunta;
import com.nunta.steluta.service.AchizitieService;
import com.nunta.steluta.service.InvitatService;
import com.nunta.steluta.service.NuntaService;
import com.nunta.steluta.util.date.Data;

@Controller
public class NuntaController {
	@Autowired
	private NuntaService nuntaService;

	@Autowired
	private InvitatService invitatService;

	@Autowired
	private AchizitieService achizitieService;

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("nunti", nuntaService.findAll());
		model.addAttribute("nrNunti", nuntaService.findAll().size());

		return "index";
	}

	@GetMapping("/adauga-nunta")
	public String adaugaNunta(Model model) {
		model.addAttribute("nuntaFormular", new Nunta());
		model.addAttribute("dati", Data.getInstance());

		return "adauga-nunta";
	}

	@PostMapping("/adauga-nunta")
	public String adaugaNunta(@ModelAttribute("nuntaFormular") Nunta nuntaFormular, Model model,
			HttpServletRequest request) {
		Integer ziNunta = Integer.parseInt(request.getParameter("ziNunta"));
		Integer lunaNunta = Integer.parseInt(request.getParameter("lunaNunta"));
		Integer anNunta = Integer.parseInt(request.getParameter("anNunta"));

		nuntaFormular.setDataNuntii(Data.getInstance().getCalendarData(ziNunta, lunaNunta, anNunta));

		nuntaFormular.setCostTotal(0);
		nuntaFormular.setNrInvitati(0);
		nuntaFormular.setNrPersoanePrezente(0);

		nuntaService.save(nuntaFormular);

		return "redirect:/";
	}

	@RequestMapping(value = "/nunta/{id}", method = RequestMethod.GET)
	public String nunta(@PathVariable("id") int id, Model model) {
		model.addAttribute("nunta", nuntaService.findOne(id));

		return "nunta";
	}

	@RequestMapping(value = "/sterge-nunta{idNunta}", method = RequestMethod.GET)
	public String stergeNnunta(@RequestParam("idNunta") int idNunta) {
		invitatService.delete(invitatService.findByNuntaIdEquals(idNunta));
		achizitieService.delete(achizitieService.findByNuntaIdEquals(idNunta));
		nuntaService.delete(nuntaService.findOne(idNunta));
		return "redirect:/";
	}

	@RequestMapping(value = "/modifica-nunta{idNunta}", method = RequestMethod.GET)
	public String modificaNunta(@RequestParam("idNunta") int idNunta, Model model) {
		Calendar c = nuntaService.findOne(idNunta).getDataNuntii();
		
		Map<String, Integer> data= new HashMap<>();
		data.put("ziAleasa", c.get(Calendar.DAY_OF_MONTH));
		data.put("lunaAleasa",c.get(Calendar.MONTH) + 1);
		data.put("anAles", c.get(Calendar.YEAR));
		
		model.addAllAttributes(data);
		model.addAttribute("nunta", nuntaService.findOne(idNunta));
		model.addAttribute("dati", Data.getInstance());
		System.out.println(idNunta);
		return "adauga-nunta";
	}

	@RequestMapping(value = "/modifica-nunta{idNunta}", method = RequestMethod.POST)
	public String modificaNunta(@RequestParam("idNunta") int idNunta, @ModelAttribute("nunta") Nunta nunta,
			HttpServletRequest request) {
		// Data Nuntii
		Integer ziNunta = Integer.parseInt(request.getParameter("ziNunta"));
		Integer lunaNunta = Integer.parseInt(request.getParameter("lunaNunta"));
		Integer anNunta = Integer.parseInt(request.getParameter("anNunta"));

		// System.out.println(idNunta);
		Nunta nuntaModif = nuntaService.findOne(idNunta);
		nuntaModif.setDataNuntii(Data.getInstance().getCalendarData(ziNunta, lunaNunta, anNunta));

		nuntaModif.setMire(nunta.getMire());
		nuntaModif.setMireasa(nunta.getMireasa());
		nuntaModif.setJudet(nunta.getJudet());
		nuntaModif.setOras(nunta.getOras());
		nuntaModif.setNumeLocal(nunta.getNumeLocal());

		nuntaService.save(nuntaModif);
		return "redirect:/";
	}

	@RequestMapping(value = "/orderBy{value}", method = RequestMethod.GET)
	public String orderBy(@RequestParam("value") String value, Model model) {
		switch (value) {
		case "MireasaASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByMireasaAsc());
			break;
		case "MireasaDesc":
			model.addAttribute("nunti", nuntaService.findAllByOrderByMireasaDesc());
			break;
		case "MireASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByMireAsc());
			break;
		case "MireDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByMireDesc());
			break;
		case "LocalASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNumeLocalAsc());
			break;
		case "LocalDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNumeLocalDesc());
			break;
		case "JudetASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByJudetAsc());
			break;
		case "JudetDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByJudetDesc());
			break;
		case "OrasASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByOrasAsc());
			break;
		case "OrasDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByOrasDesc());
			break;
		case "NrInvitatiASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNrInvitatiAsc());
			break;
		case "NrInvitatiDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNrInvitatiDesc());
			break;
		case "NrPersoanePrezenteASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNrPersoanePrezenteAsc());
			break;
		case "NrPersoanePrezenteDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByNrPersoanePrezenteDesc());
			break;
		case "CostTotalASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByCostTotalAsc());
			break;
		case "CostTotalDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByCostTotalDesc());
			break;
		case "DataNuntiiASC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByDataNuntiiAsc());
			break;
		case "DataNuntiiDESC":
			model.addAttribute("nunti", nuntaService.findAllByOrderByDataNuntiiDesc());
			break;
		default:
			model.addAttribute("nunti", nuntaService.findAll());
		}
		model.addAttribute("nrNunti", nuntaService.findAll().size());
		return "index";
	}
}
