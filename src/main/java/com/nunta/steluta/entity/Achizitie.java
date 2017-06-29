package com.nunta.steluta.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "achizitie")
public class Achizitie {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String denumireServiciu;

	@ManyToOne(fetch=FetchType.LAZY)
	private Cost cost;
	
	private String numeFurnizor;

	@Temporal(TemporalType.DATE)
	private Calendar dataAchizitiei;
	
	@ManyToOne
	private Nunta nunta;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getDenumireServiciu() {
		return denumireServiciu;
	}

	public void setDenumireServiciu(String denumireServiciu) {
		this.denumireServiciu = denumireServiciu;
	}

	public Cost getCost() {
		return cost;
	}

	public void setCost(Cost cost) {
		this.cost = cost;
	}

	public String getNumeFurnizor() {
		return numeFurnizor;
	}

	public void setNumeFurnizor(String numeFurnizor) {
		this.numeFurnizor = numeFurnizor;
	}

	public Calendar getDataAchizitiei() {
		return dataAchizitiei;
	}

	public void setDataAchizitiei(Calendar dataAchizitiei) {
		this.dataAchizitiei = dataAchizitiei;
	}

	public Nunta getNunta() {
		return nunta;
	}

	public void setNunta(Nunta nunta) {
		this.nunta = nunta;
	}
}
