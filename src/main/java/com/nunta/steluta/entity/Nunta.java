package com.nunta.steluta.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "nunta")
public class Nunta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String mireasa;

	private String mire;

	private String judet;

	private String oras;

	private String numeLocal;

	private int nrInvitati;

	private int nrPersoanePrezente;

	@Column(name = "costTotal", columnDefinition = "Decimal(10,2)")
	private float costTotal;

	@Column(name = "baniDaruri", columnDefinition = "Decimal(10,2)")
	private float baniDaruri;

	@Temporal(TemporalType.DATE)
	private Calendar dataNuntii;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMireasa() {
		return mireasa;
	}

	public void setMireasa(String mireasa) {
		this.mireasa = mireasa;
	}

	public String getMire() {
		return mire;
	}

	public void setMire(String mire) {
		this.mire = mire;
	}

	public String getNumeLocal() {
		return numeLocal;
	}

	public void setNumeLocal(String numeLocal) {
		this.numeLocal = numeLocal;
	}

	public int getNrInvitati() {
		return nrInvitati;
	}

	public void setNrInvitati(int nrInvitati) {
		this.nrInvitati = nrInvitati;
	}

	public int getNrPersoanePrezente() {
		return nrPersoanePrezente;
	}

	public void setNrPersoanePrezente(int nrPersoanePrezente) {
		this.nrPersoanePrezente = nrPersoanePrezente;
	}

	public float getCostTotal() {
		return costTotal;
	}

	public void setCostTotal(float costTotal) {
		this.costTotal = costTotal;
	}

	public float getBaniDaruri() {
		return baniDaruri;
	}

	public void setBaniDaruri(float baniDaruri) {
		this.baniDaruri = baniDaruri;
	}

	public Calendar getDataNuntii() {
		return dataNuntii;
	}

	public void setDataNuntii(Calendar dataNuntii) {
		this.dataNuntii = dataNuntii;
	}

	public String getJudet() {
		return judet;
	}

	public void setJudet(String judet) {
		this.judet = judet;
	}

	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}
}
