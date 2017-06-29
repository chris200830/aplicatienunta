package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Invitat;
import com.nunta.steluta.entity.Nunta;
import com.nunta.steluta.repository.AchizitieRepository;
import com.nunta.steluta.repository.InvitatRepository;
import com.nunta.steluta.repository.NuntaRepository;
import com.nunta.steluta.util.currency.CurrencyConvertor;

@Component
public class NuntaServiceImpl implements NuntaService {
	@Autowired
	private NuntaRepository nuntaRepository;

	@Autowired
	private AchizitieRepository achizitieRepository;

	@Autowired
	private InvitatRepository invitatRepository;

	@Override
	public List<Nunta> findAll() {
		List<Nunta> nunti = new ArrayList<Nunta>();
		for (Nunta nunta : nuntaRepository.findAll())
			nunti.add(nunta);

		return nunti;
	}

	@Override
	public List<Nunta> findAllByOrderByMireasaAsc() {
		return nuntaRepository.findAllByOrderByMireasaAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByMireasaDesc() {
		return nuntaRepository.findAllByOrderByMireasaDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByMireAsc() {
		return nuntaRepository.findAllByOrderByMireAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByMireDesc() {
		return nuntaRepository.findAllByOrderByMireDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByNumeLocalAsc() {
		return nuntaRepository.findAllByOrderByNumeLocalAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByNumeLocalDesc() {
		return nuntaRepository.findAllByOrderByNumeLocalDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByJudetAsc() {
		return nuntaRepository.findAllByOrderByJudetAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByJudetDesc() {
		return nuntaRepository.findAllByOrderByJudetDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByOrasAsc() {
		return nuntaRepository.findAllByOrderByOrasAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByOrasDesc() {
		return nuntaRepository.findAllByOrderByOrasDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByNrInvitatiAsc() {
		return nuntaRepository.findAllByOrderByNrInvitatiAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByNrInvitatiDesc() {
		return nuntaRepository.findAllByOrderByNrInvitatiDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByNrPersoanePrezenteAsc() {
		return nuntaRepository.findAllByOrderByNrPersoanePrezenteAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByNrPersoanePrezenteDesc() {
		return nuntaRepository.findAllByOrderByNrPersoanePrezenteDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByCostTotalAsc() {
		return nuntaRepository.findAllByOrderByCostTotalAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByCostTotalDesc() {
		return nuntaRepository.findAllByOrderByCostTotalDesc();
	}

	@Override
	public List<Nunta> findAllByOrderByDataNuntiiAsc() {
		return nuntaRepository.findAllByOrderByDataNuntiiAsc();
	}

	@Override
	public List<Nunta> findAllByOrderByDataNuntiiDesc() {
		return nuntaRepository.findAllByOrderByDataNuntiiDesc();
	}

	@Override
	public Nunta findOne(int id) {
		return nuntaRepository.findOne(id);
	}

	@Override
	public void save(Nunta nunta) {
		nuntaRepository.save(nunta);
	}

	@Override
	public void delete(Nunta nunta) {
		nuntaRepository.delete(nunta);
	}

	@Override
	public List<Nunta> getSortingResult() {
		return findAll();
	}

	@Override
	public void addCost(int nuntaId, float cost) {
		Nunta n = findOne(nuntaId);
		float costTotal = n.getCostTotal() + cost;
		n.setCostTotal(costTotal);
		save(n);
	}

	@Override
	public void subtractCost(int nuntaId, float cost) {
		Nunta n = findOne(nuntaId);
		float costTotal = n.getCostTotal() - cost;
		n.setCostTotal(costTotal);
		save(n);
	}

	@Override
	public void recalculateCostForNunta(int nuntaId) {
		Nunta n = findOne(nuntaId);
		float costTotal = 0;
		for (Float cost : achizitieRepository.findCostTotalByNuntaIdEquals(nuntaId))
			costTotal += cost;

		n.setCostTotal(costTotal);
		save(n);
	}

	@Override
	public void addDar(int nuntaId, float dar) {
		Nunta n = findOne(nuntaId);
		float darTotal = n.getBaniDaruri() + dar;
		n.setBaniDaruri(darTotal);
		save(n);
	}

	@Override
	public void subtractDar(int nuntaId, float dar) {
		Nunta n = findOne(nuntaId);
		float darTotal = n.getBaniDaruri() - dar;
		n.setBaniDaruri(darTotal);
		save(n);
	}

	@Override
	public void recalculateDaruriForNunta(int nuntaId) {
		float total = 0;
		Nunta n = findOne(nuntaId);
		for (Invitat i : invitatRepository.findAllByNuntaIdEquals(nuntaId))
			total += CurrencyConvertor.getInstance().convertCurrency(i.getTipValuta(), i.getBani());
		n.setBaniDaruri(total);
		save(n);
	}

	@Override
	public void updateNrInvitatiForNunta(int nuntaId) {
		Nunta n = findOne(nuntaId);
		n.setNrInvitati(invitatRepository.findByNuntaIdEquals(nuntaId).size());
		save(n);
	}

	@Override
	public void updateNrPersoanePrezenteForNunta(int nuntaId) {
		Nunta n = findOne(nuntaId);
		n.setNrPersoanePrezente(
				invitatRepository.findByConfirmarePrezentaEqualsAndNuntaIdEquals(true, nuntaId).size());
		save(n);
	}
}
