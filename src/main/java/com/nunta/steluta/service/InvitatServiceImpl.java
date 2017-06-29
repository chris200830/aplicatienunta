package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Invitat;
import com.nunta.steluta.repository.CazareRepository;
import com.nunta.steluta.repository.InvitatRepository;

@Component
public class InvitatServiceImpl implements InvitatService {
	@Autowired
	private InvitatRepository invitatRepository;
	
	@Autowired
	private CazareRepository cazareRepository;

	@Override
	public List<Invitat> findAll() {
		List<Invitat> invitati = new ArrayList<Invitat>();
		for (Invitat invitat : invitatRepository.findAll())
			invitati.add(invitat);

		return invitati;
	}
	
	@Override
	public Page<Invitat> findByNuntaIdEquals(int nuntaId, int pageId, int total) {
		Pageable page = new PageRequest(pageId, total);
		return invitatRepository.findByNuntaIdEquals(nuntaId, page);
	}

	@Override
	public void save(Invitat invitat) {
		invitatRepository.save(invitat);
	}

	@Override
	public Invitat findOne(int id) {
		return invitatRepository.findOne(id);
	}

	@Override
	public List<Invitat> findByNuntaIdEquals(int nuntaId) {
		return invitatRepository.findByNuntaIdEquals(nuntaId);
	}
	
	public Page<Invitat> findByNuntaIdEqualsOrderByNumeAsc(int nuntaId, Pageable page){
		return invitatRepository.findByNuntaIdEqualsOrderByNumeAsc(nuntaId, page);
	}
	public Page<Invitat> findByNuntaIdEqualsOrderByNumeDesc(int nuntaId, Pageable page){
		return invitatRepository.findByNuntaIdEqualsOrderByNumeDesc(nuntaId, page);
	}
	
	@Override
	public List<Invitat> findByConfirmarePrezentaEqualsAndNuntaIdEquals(boolean ePrezent, int nuntaId) {
		return invitatRepository.findByConfirmarePrezentaEqualsAndNuntaIdEquals(ePrezent, nuntaId);
	}

	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByNumeAsc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByNumeAsc(nuntaId);
	}

	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByNumeDesc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByNumeDesc(nuntaId);
	}

	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByPrenumeAsc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByPrenumeAsc(nuntaId);
	}

	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByPrenumeDesc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByPrenumeDesc(nuntaId);
	}

	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByConfirmarePrezentaAsc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByConfirmarePrezentaAsc(nuntaId);
	}
	
	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByConfirmarePrezentaDesc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByConfirmarePrezentaDesc(nuntaId);
	}
	
	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByJudetAsc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByJudetAsc(nuntaId);
	}
	
	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByJudetDesc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByJudetDesc(nuntaId);
	}
	
	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByOrasAsc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByOrasAsc(nuntaId);
	}
	
	@Override
	public List<Invitat> findAllByNuntaIdEqualsOrderByOrasDesc(int nuntaId) {
		return invitatRepository.findAllByNuntaIdEqualsOrderByOrasDesc(nuntaId);
	}

	@Override
	public void delete(Invitat invitat) {
		if (invitat == null)
			return;

		invitatRepository.delete(invitat);
	}

	@Override
	public void delete(List<Invitat> invitati) {
		if (invitati.isEmpty())
			return;

		for (Invitat invitat : invitati) {
			invitatRepository.delete(invitat);
			cazareRepository.delete(invitat.getCazare());	
		}
	}
	
	@Override
	public Invitat transfer(Invitat toInvitat, Invitat fromInvitat) {
		toInvitat.setConfirmarePrezenta(fromInvitat.isConfirmarePrezenta());
		toInvitat.setDetalii(fromInvitat.getDetalii());
		toInvitat.setDar(fromInvitat.getDar());
		toInvitat.setBani(fromInvitat.getBani());
		toInvitat.setTipValuta(fromInvitat.getTipValuta());
		toInvitat.setJudet(fromInvitat.getJudet());
		toInvitat.setOras(fromInvitat.getOras());
		toInvitat.setNume(fromInvitat.getNume());
		toInvitat.setPrenume(fromInvitat.getPrenume());
		toInvitat.setMasa(fromInvitat.getMasa());
		
		return toInvitat;
	}
}
