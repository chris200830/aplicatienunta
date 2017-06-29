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

import com.nunta.steluta.entity.Achizitie;
import com.nunta.steluta.entity.Cost;
import com.nunta.steluta.entity.Nunta;
import com.nunta.steluta.service.AchizitieService;
import com.nunta.steluta.service.CostService;
import com.nunta.steluta.service.NuntaService;
import com.nunta.steluta.util.date.Data;
import com.nunta.steluta.util.parser.Parser;

@Controller
public class AchizitieController {
	@Autowired
	private AchizitieService achizitieService;
	@Autowired
	private CostService costService;
	@Autowired
	private NuntaService nuntaService;

	@GetMapping("/nunta/{id}/achizitii{pageId}")
	public String achizitii(@PathVariable("id") int nuntaId, Model model, @RequestParam("pageId") int pageId) {
		// model.addAttribute("achizitii",
		// achizitieService.findByNuntaIdEquals(idNunta));
		Page<Achizitie> page = achizitieService.findByNuntaIdEquals(nuntaId, pageId - 1, 2);
		// System.out.println(page.getTotalPages());
		model.addAttribute("pages", page.getTotalPages());
		model.addAttribute("achizitii", page.getContent());

		return "achizitii";
	}

	@GetMapping("/nunta/{id}/adauga-achizitie")
	public String adaugaAchizitie(@PathVariable("id") int nuntaId, Model model) {
		model.addAttribute("achizitieFormular", new Achizitie());
		model.addAttribute("costFormular", new Cost());
		model.addAttribute("dati", Data.getInstance());

		return "adauga-achizitie";
	}

	@PostMapping("/nunta/{id}/adauga-achizitie")
	public String adaugaAchizitie(@PathVariable("id") int nuntaId,
			@ModelAttribute("achizitieFormular") Achizitie achizitieFormular,
			@ModelAttribute("costFormular") Cost costFormular, HttpServletRequest request, Model model) {
		Integer zi = Integer.parseInt(request.getParameter("zi"));
		Integer luna = Integer.parseInt(request.getParameter("luna"));
		Integer an = Integer.parseInt(request.getParameter("an"));

		// Cost
		Cost cost = costService.transfer(new Cost(), costFormular);
		costService.save(cost);

		// Achizitie
		achizitieFormular.setDataAchizitiei(Data.getInstance().getCalendarData(zi, luna, an));
		achizitieFormular.setCost(cost);
		Achizitie achizitie = achizitieService.transfer(new Achizitie(), achizitieFormular);
		Nunta nunta = nuntaService.findOne(nuntaId);
		achizitie.setNunta(nunta);

		// Adauga cost total
		nuntaService.addCost(nuntaId, cost.getTotal());
		achizitieService.save(achizitie);

		return ("redirect:/nunta/" + nuntaId + "/achizitii?pageId=1");
	}

	@RequestMapping(value = "/nunta/{id}/sterge-achizitie{idAchizitie}", method = RequestMethod.GET)
	public String stergeAchizitie(@PathVariable("id") int nuntaId, @RequestParam("idAchizitie") int idAchizitie,
			@RequestParam("pageId") int pageId, Model model) {
		Achizitie achizitie = achizitieService.findOne(idAchizitie);
		float total = achizitie.getCost().getTotal();
		achizitieService.delete(achizitie);
		costService.delete(achizitie.getCost());

		nuntaService.subtractCost(nuntaId, total);
		return ("redirect:/nunta/" + nuntaId + "/achizitii?pageId=" + pageId);
	}

	@RequestMapping(value = "/nunta/{id}/modifica-achizitie{idAchizitie}", method = RequestMethod.GET)
	public String modificaAchizitie(@PathVariable("id") int nuntaId, @RequestParam("idAchizitie") int idAchizitie,
			Model model, @RequestParam("pageId") int pageId) {
		Achizitie a = achizitieService.findOne(idAchizitie);

		if (a.getDataAchizitiei() != null) {
			Calendar c = a.getDataAchizitiei();

			Map<String, Integer> data = new HashMap<>();
			data.put("ziAleasa", c.get(Calendar.DAY_OF_MONTH));
			data.put("lunaAleasa", c.get(Calendar.MONTH) + 1);
			data.put("anAles", c.get(Calendar.YEAR));

			model.addAllAttributes(data);
		}

		model.addAttribute("dati", Data.getInstance());
		model.addAttribute("achizitie", a);
		model.addAttribute("cost", costService.findOne(a.getCost().getId()));
		model.addAttribute("pageId", pageId);
		return "adauga-achizitie";
	}

	@RequestMapping(value = "/nunta/{id}/modifica-achizitie{idAchizitie}", method = RequestMethod.POST)
	public String modificaAchizitie(@PathVariable("id") int nuntaId, @RequestParam("idAchizitie") int idAchizitie,
			@ModelAttribute("cost") Cost cost, @ModelAttribute("achizitie") Achizitie achizitie,
			HttpServletRequest request, @RequestParam("pageId") int pageId) {
		Integer zi = Integer.parseInt(request.getParameter("zi"));
		Integer luna = Integer.parseInt(request.getParameter("luna"));
		Integer an = Integer.parseInt(request.getParameter("an"));

		achizitie.setDataAchizitiei(Data.getInstance().getCalendarData(zi, luna, an));
		Achizitie achizitieModificata = achizitieService.findOne(idAchizitie);

		Cost costModificat = costService.findOne(achizitieModificata.getCost().getId());
		costModificat = costService.transfer(costModificat, cost);
		costService.save(costModificat);
		achizitie.setCost(costModificat);

		achizitieService.save(achizitieService.transfer(achizitieModificata, achizitie));

		nuntaService.recalculateCostForNunta(nuntaId);
		return "redirect:/nunta/" + nuntaId + "/achizitii?pageId=" + pageId;
	}

	@RequestMapping(value = "/nunta/{id}/incarca-achizitii{pageId}", method = RequestMethod.POST)
	public String incarcaAchizitii(@PathVariable("id") int nuntaId,
			@RequestParam("achizitiiFile") MultipartFile multipart, @RequestParam("pageId") int pageId) {

		try {
			String content = new String(multipart.getBytes());
			Scanner reader = new Scanner(content);
			reader.nextLine();
			while (reader.hasNextLine()) {
				Achizitie a = Parser.getInstance().parseAchizitie(reader.nextLine(), costService);
				a.setNunta(nuntaService.findOne(nuntaId));
				costService.save(a.getCost());
				achizitieService.save(a);
				nuntaService.addCost(nuntaId, a.getCost().getTotal());
			}
			reader.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return ("redirect:/nunta/" + nuntaId + "/achizitii?pageId=" + pageId);
	}

	@RequestMapping(value = "/nunta/{id}/achizitii-orderBy{value}", method = RequestMethod.GET)
	public String orderBy(@PathVariable("id") int nuntaId, @RequestParam("value") String value, Model model) {
		switch (value) {
		case "DenumireServiciuASC":
			model.addAttribute("achizitii", achizitieService.findAllByNuntaIdEqualsOrderByDenumireServiciuAsc(nuntaId));
			break;
		case "DenumireServiciuDESC":
			model.addAttribute("achizitii",
					achizitieService.findAllByNuntaIdEqualsOrderByDenumireServiciuDesc(nuntaId));
			break;
		case "NumeFurnizorASC":
			model.addAttribute("achizitii", achizitieService.findAllByNuntaIdEqualsOrderByNumeFurnizorAsc(nuntaId));
			break;
		case "NumeFurnizorDESC":
			model.addAttribute("achizitii", achizitieService.findAllByNuntaIdEqualsOrderByNumeFurnizorDesc(nuntaId));
			break;
		case "DataAchizitieiASC":
			model.addAttribute("achizitii", achizitieService.findAllByNuntaIdEqualsOrderByDataAchizitieiAsc(nuntaId));
			break;
		case "DataAchizitieiDESC":
			model.addAttribute("achizitii", achizitieService.findAllByNuntaIdEqualsOrderByDataAchizitieiDesc(nuntaId));
			break;
		default:
			model.addAttribute("achizitii", achizitieService.findByNuntaIdEquals(nuntaId));
		}

		return "achizitii";
	}
}
