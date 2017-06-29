package com.nunta.steluta.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.nunta.steluta.entity.Nunta;

public interface NuntaRepository extends CrudRepository<Nunta, Integer> {
	public Nunta findOne(int id);

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
}
