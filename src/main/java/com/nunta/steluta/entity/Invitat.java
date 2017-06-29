package com.nunta.steluta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "invitat")
public class Invitat {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String nume;

	private String prenume;
	
	@ManyToOne
	private Masa masa;

	@ManyToOne
	private Cazare cazare;
	
	private String dar;
	
	@Column(name = "bani", columnDefinition = "Decimal(10,2)")
	private float bani;
	
	private String tipValuta;

	@Lob
	@Column
	private String detalii;

	private boolean confirmarePrezenta;
	
	@ManyToOne
	private Nunta nunta;
	
	private String judet;

	private String oras;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public Masa getMasa() {
		return masa;
	}

	public void setMasa(Masa masa) {
		this.masa = masa;
	}

	public Cazare getCazare() {
		return cazare;
	}

	public void setCazare(Cazare cazare) {
		this.cazare = cazare;
	}

	public String getDar() {
		return dar;
	}

	public void setDar(String dar) {
		this.dar = dar;
	}

	public float getBani() {
		return bani;
	}

	public void setBani(float bani) {
		this.bani = bani;
	}

	public String getTipValuta() {
		return tipValuta;
	}

	public void setTipValuta(String tipValuta) {
		this.tipValuta = tipValuta;
	}

	public String getDetalii() {
		return detalii;
	}

	public void setDetalii(String detalii) {
		this.detalii = detalii;
	}

	public boolean isConfirmarePrezenta() {
		return confirmarePrezenta;
	}

	public void setConfirmarePrezenta(boolean confirmarePrezenta) {
		this.confirmarePrezenta = confirmarePrezenta;
	}

	public Nunta getNunta() {
		return nunta;
	}

	public void setNunta(Nunta nunta) {
		this.nunta = nunta;
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
