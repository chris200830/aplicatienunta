package com.nunta.steluta.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nunta.steluta.entity.Achizitie;

public interface AchizitieRepository extends CrudRepository<Achizitie, Integer> {
	public Page<Achizitie> findByNuntaIdEquals(int nuntaId, Pageable page);
	
	public List<Achizitie> findByNuntaIdEquals(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDenumireServiciuDesc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByNumeFurnizorDesc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiAsc(int nuntaId);

	public List<Achizitie> findAllByNuntaIdEqualsOrderByDataAchizitieiDesc(int nuntaId);
	
	@Query("select c.total from Cost c, Achizitie a where a.cost.id=c.id and a.nunta.id=?1")
	public List<Float> findCostTotalByNuntaIdEquals(int nuntaId);
}
