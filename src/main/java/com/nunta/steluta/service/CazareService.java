package com.nunta.steluta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Cazare;

@Service
public interface CazareService {
	public List<Cazare> findAll();
	
	public void delete(Cazare cazare);
	
	public void save(Cazare cazare);
	
	public Cazare transfer(Cazare toCazare, Cazare fromCazare);
}
