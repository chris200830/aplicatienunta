package com.nunta.steluta.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Invitat;

@Service
public interface InvitatService {
	public List<Invitat> findAll();

	public void save(Invitat invitat);

	public Invitat findOne(int id);
	
	public Page<Invitat> findByNuntaIdEquals(int nuntaId, int pageId, int total);
	public Page<Invitat> findByNuntaIdEqualsOrderByNumeAsc(int nuntaId, Pageable page);
	public Page<Invitat> findByNuntaIdEqualsOrderByNumeDesc(int nuntaId, Pageable page);
	
	public List<Invitat> findByNuntaIdEquals(int nuntaId);

	public List<Invitat> findByConfirmarePrezentaEqualsAndNuntaIdEquals(boolean ePrezent, int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByNumeAsc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByNumeDesc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByPrenumeAsc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByPrenumeDesc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByConfirmarePrezentaAsc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByConfirmarePrezentaDesc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByJudetAsc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByJudetDesc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByOrasAsc(int nuntaId);

	public List<Invitat> findAllByNuntaIdEqualsOrderByOrasDesc(int nuntaId);

	public void delete(Invitat invitat);

	public void delete(List<Invitat> invitati);
	
	public Invitat transfer(Invitat toInvitat, Invitat fromInvitat);
}
