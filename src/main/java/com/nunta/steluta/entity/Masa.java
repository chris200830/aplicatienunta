package com.nunta.steluta.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "masa")
public class Masa {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nrMasa;

	private int nrPersoane;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNrMasa() {
		return nrMasa;
	}

	public void setNrMasa(String nrMasa) {
		this.nrMasa = nrMasa;
	}

	public int getNrPersoane() {
		return nrPersoane;
	}

	public void setNrPersoane(int nrPersoane) {
		this.nrPersoane = nrPersoane;
	}
}
