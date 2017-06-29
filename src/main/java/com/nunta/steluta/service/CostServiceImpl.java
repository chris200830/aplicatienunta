package com.nunta.steluta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nunta.steluta.entity.Cost;
import com.nunta.steluta.repository.CostRepository;

@Component
public class CostServiceImpl implements CostService{
	@Autowired
	private CostRepository costRepository;
	
	@Override
	public List<Cost> findAll() {
		List<Cost> costuri = new ArrayList<Cost>();
		for (Cost cost : costRepository.findAll())
			costuri.add(cost);

		return costuri;
	}
	
	@Override
	public Cost findOne(int id) {
		return costRepository.findOne(id);
	}
	
	@Override
	public void delete(Cost cost) {
		costRepository.delete(cost);
	}
	
	@Override
	public void save(Cost cost) {
		costRepository.save(cost);
	}
	
	@Override
	public Cost transfer(Cost toCost, Cost fromCost) {
		toCost.setAvans(fromCost.getAvans());
		toCost.setRest(fromCost.getRest());
		toCost.setTotal(toCost.getAvans() + toCost.getRest());
		
		return toCost;
	}
}
