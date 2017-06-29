package com.nunta.steluta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Masa;

@Service
public interface MasaService {
	public List<Masa> findAll();
	public Masa findByNrMasaEquals(String nrMasa);
	
	public void delete(Masa masa);
	
	public void save(Masa masa);
}
