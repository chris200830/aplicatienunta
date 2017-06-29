package com.nunta.steluta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Nunta;

@Service
public interface NuntaService {
	public List<Nunta> findAll();

	public List<Nunta> findAllByOrderByMireasaAsc();

	public List<Nunta> findAllByOrderByMireasaDesc();

	public List<Nunta> findAllByOrderByMireAsc();

	public List<Nunta> findAllByOrderByMireDesc();

	public List<Nunta> findAllByOrderByNumeLocalAsc();

	public List<Nunta> findAllByOrderByNumeLocalDesc();

	public List<Nunta> findAllByOrderByJudetAsc();

	public List<Nunta> findAllByOrderByJudetDesc();

	public List<Nunta> findAllByOrderByOrasAsc();

	public List<Nunta> findAllByOrderByOrasDesc();

	public List<Nunta> findAllByOrderByNrInvitatiAsc();

	public List<Nunta> findAllByOrderByNrInvitatiDesc();

	public List<Nunta> findAllByOrderByNrPersoanePrezenteAsc();

	public List<Nunta> findAllByOrderByNrPersoanePrezenteDesc();

	public List<Nunta> findAllByOrderByCostTotalAsc();

	public List<Nunta> findAllByOrderByCostTotalDesc();

	public List<Nunta> findAllByOrderByDataNuntiiAsc();

	public List<Nunta> findAllByOrderByDataNuntiiDesc();

	public Nunta findOne(int id);

	public void save(Nunta nunta);

	public void delete(Nunta nunta);
	
	public List<Nunta> getSortingResult();
	
	public void addCost(int nuntaId, float cost);
	public void subtractCost(int nuntaId, float cost);
	public void recalculateCostForNunta(int nuntaId);
	
	public void addDar(int nuntaId, float dar);
	public void subtractDar(int nuntaId, float dar);
	public void recalculateDaruriForNunta(int nuntaId);
	
	public void updateNrInvitatiForNunta(int nuntaId);
	public void updateNrPersoanePrezenteForNunta(int nuntaId);
}
