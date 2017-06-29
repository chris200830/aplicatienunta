package com.nunta.steluta.repository;

import org.springframework.data.repository.CrudRepository;

import com.nunta.steluta.entity.Masa;

public interface MasaRepository extends CrudRepository<Masa, Integer>{
	public Masa findByNrMasaEquals(String nrMasa);
}
