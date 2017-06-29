package com.nunta.steluta.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cazare")
public class Cazare {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String numeHotel;

	private String tipCamera;

	private int nrPersoane;

	@Temporal(TemporalType.DATE)
	private Calendar dataInitiala;

	@Temporal(TemporalType.DATE)
	private Calendar dataFinala;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeHotel() {
		return numeHotel;
	}

	public void setNumeHotel(String numeHotel) {
		this.numeHotel = numeHotel;
	}

	public String getTipCamera() {
		return tipCamera;
	}

	public void setTipCamera(String tipCamera) {
		this.tipCamera = tipCamera;
	}

	public int getNrPersoane() {
		return nrPersoane;
	}

	public void setNrPersoane(int nrPersoane) {
		this.nrPersoane = nrPersoane;
	}

	public Calendar getDataInitiala() {
		return dataInitiala;
	}

	public void setDataInitiala(Calendar dataInitiala) {
		this.dataInitiala = dataInitiala;
	}

	public Calendar getDataFinala() {
		return dataFinala;
	}

	public void setDataFinala(Calendar dataFinala) {
		this.dataFinala = dataFinala;
	}
}
