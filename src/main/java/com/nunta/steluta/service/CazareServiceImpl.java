package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Cazare;
import com.nunta.steluta.repository.CazareRepository;


@Component
public class CazareServiceImpl implements CazareService{
	@Autowired
	private CazareRepository cazareRepository;
	
	@Override
	public List<Cazare> findAll() {
		List<Cazare> cazari = new ArrayList<Cazare>();
		for (Cazare cazare : cazareRepository.findAll())
			cazari.add(cazare);

		return cazari;
	}
	
	@Override
	public void delete(Cazare cazare) {
		cazareRepository.delete(cazare);
	}
	
	@Override
	public void save(Cazare cazare) {
		cazareRepository.save(cazare);
	}
	
	@Override
	public Cazare transfer(Cazare toCazare, Cazare fromCazare) {
		toCazare.setDataInitiala(fromCazare.getDataInitiala());
		toCazare.setDataFinala(fromCazare.getDataFinala());
		toCazare.setNumeHotel(fromCazare.getNumeHotel());
		toCazare.setTipCamera(fromCazare.getTipCamera());		
		
		return toCazare;
	}
}
