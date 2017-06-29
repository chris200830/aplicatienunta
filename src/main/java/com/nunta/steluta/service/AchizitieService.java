package com.nunta.steluta.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Achizitie;

@Service
public interface AchizitieService {
	public List<Achizitie> findAll();
	
	public Page<Achizitie> findByNuntaIdEquals(int nuntaId, int pageId, int total);

	public List<Achizitie> findByNuntaIdEquals(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuDesc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorDesc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiDesc(int nuntaId);
	
	public Achizitie findOne(int id);

	public float calculateCostTotal(List<Achizitie> achizitii);

	public void delete(Achizitie achizitie);

	public void delete(List<Achizitie> achizitii);

	public void save(Achizitie achizitie);
	
	public Achizitie transfer(Achizitie toAchizitie, Achizitie fromAchizitie);
}
