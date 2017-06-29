package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Achizitie;
import com.nunta.steluta.repository.AchizitieRepository;
import com.nunta.steluta.repository.CostRepository;

@Component
public class AchizitieServiceImpl implements AchizitieService {
	@Autowired
	private AchizitieRepository achizitieRepository;

	@Autowired
	private CostRepository costRepository;

	@Override
	public List<Achizitie> findAll() {
		List<Achizitie> achizitii = new ArrayList<Achizitie>();
		for (Achizitie achizitie : achizitieRepository.findAll())
			achizitii.add(achizitie);

		return achizitii;
	}
	
	@Override
	public  Page<Achizitie> findByNuntaIdEquals(int nuntaId, int pageId, int total) {
		Pageable page = new PageRequest(pageId, total);

		return achizitieRepository.findByNuntaIdEquals(nuntaId, page);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuAsc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByDenumireServiciuAsc(nuntaId);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuDesc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByDenumireServiciuDesc(nuntaId);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorAsc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByNumeFurnizorAsc(nuntaId);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorDesc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByNumeFurnizorDesc(nuntaId);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiAsc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByDataAchizitieiAsc(nuntaId);
	}

	@Override
	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiDesc(int nuntaId) {
		return achizitieRepository.findAllByNuntaIdEqualsOrderByDataAchizitieiDesc(nuntaId);
	}

	@Override
	public List<Achizitie> findByNuntaIdEquals(int nuntaId) {
		return achizitieRepository.findByNuntaIdEquals(nuntaId);
	}

	@Override
	public Achizitie findOne(int id) {
		return achizitieRepository.findOne(id);
	}

	@Override
	public float calculateCostTotal(List<Achizitie> achizitii) {
		float total = 0;

		for (Achizitie a : achizitii)
			total += a.getCost().getTotal();

		return total;
	}

	@Override
	public void delete(List<Achizitie> achizitii) {
		if (achizitii.isEmpty())
			return;

		for (Achizitie achizitie : achizitii) {
			achizitieRepository.delete(achizitie);
			costRepository.delete(achizitie.getCost());
		}
	}

	@Override
	public void save(Achizitie achizitie) {
		achizitieRepository.save(achizitie);
	}

	@Override
	public void delete(Achizitie achizitie) {
		achizitieRepository.delete(achizitie);
	}
	
	@Override
	public Achizitie transfer(Achizitie toAchizitie, Achizitie fromAchizitie) {
		toAchizitie.setDenumireServiciu(fromAchizitie.getDenumireServiciu());
		toAchizitie.setNumeFurnizor(fromAchizitie.getNumeFurnizor());
		toAchizitie.setDataAchizitiei(fromAchizitie.getDataAchizitiei());
		toAchizitie.setCost(fromAchizitie.getCost());
		
		return toAchizitie;
	}
}
