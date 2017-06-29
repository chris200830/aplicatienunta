package com.nunta.steluta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nunta.steluta.entity.Cost;

@Service
public interface CostService {
	public List<Cost> findAll();
	public Cost findOne(int id);
	public void delete(Cost cost);
	
	public void save(Cost cost);
	
	public Cost transfer(Cost toCost, Cost fromCost);
}
