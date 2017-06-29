package com.nunta.steluta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cost")
public class Cost {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "avans", columnDefinition = "Decimal(10,2)")
	private float avans;

	@Column(name = "rest", columnDefinition = "Decimal(10,2)")
	private float rest;

	@Column(name = "total", columnDefinition = "Decimal(10,2)")
	private float total;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAvans() {
		return avans;
	}

	public void setAvans(float avans) {
		this.avans = avans;
	}

	public float getRest() {
		return rest;
	}

	public void setRest(float rest) {
		this.rest = rest;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
