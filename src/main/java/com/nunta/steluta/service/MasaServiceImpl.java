package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Masa;
import com.nunta.steluta.repository.MasaRepository;

@Component
public class MasaServiceImpl implements MasaService{
	@Autowired
	private MasaRepository masaRepository;
	
	@Override
	public List<Masa> findAll() {
		List<Masa> mese = new ArrayList<Masa>();
		for (Masa masa : masaRepository.findAll())
			mese.add(masa);

		return mese;
	}
	
	@Override
	public Masa findByNrMasaEquals(String nrMasa) {
		return masaRepository.findByNrMasaEquals(nrMasa);
	}
	
	@Override
	public void delete(Masa masa) {
		masaRepository.delete(masa);
	}
	
	@Override
	public void save(Masa masa) {
		masaRepository.save(masa);
	}
}
