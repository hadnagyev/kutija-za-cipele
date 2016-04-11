package com.kutija.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblFotka")
public class Fotka {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name="userKojiJePostavioFotografiju")
	private String userKojiJePostavioFotografiju;
	
	@Column(name="datumSlanjaFotografije")
	private Date datumSlanjaFotografije;
	
	@Column(name="imeFotografa")
	private String imeFotografa;
	
	@Column(name="vlasnikFotografije")
	private String vlasnikFotografije;
	
	@Column(name="datumNastankaFotografije")
	private Date datumNastankaFotografije;
	
	@Column(name="mestoFotografisanja")
	private String mestoFotografisanja;
	
	@Column(name="dogadjajFotografisanja")
	private String dogadjajFotografisanja;
	
	@Column(name="fotografijaFajl")
	private String fotografijaFajl;

	public String getUserKojiJePostavioFotografiju() {
		return userKojiJePostavioFotografiju;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumSlanjaFotografije() {
		return datumSlanjaFotografije;
	}

	public void setDatumSlanjaFotografije(Date datumSlanjaFotografije) {
		this.datumSlanjaFotografije = datumSlanjaFotografije;
	}

	public String getImeFotografa() {
		return imeFotografa;
	}

	public void setImeFotografa(String imeFotografa) {
		this.imeFotografa = imeFotografa;
	}

	public Date getDatumNastankaFotografije() {
		return datumNastankaFotografije;
	}

	public void setDatumNastankaFotografije(Date datumNastankaFotografije) {
		this.datumNastankaFotografije = datumNastankaFotografije;
	}

	public String getMestoFotografisanja() {
		return mestoFotografisanja;
	}

	public void setMestoFotografisanja(String mestoFotografisanja) {
		this.mestoFotografisanja = mestoFotografisanja;
	}

	public String getDogadjajFotografisanja() {
		return dogadjajFotografisanja;
	}

	public void setDogadjajFotografisanja(String dogadjajFotografisanja) {
		this.dogadjajFotografisanja = dogadjajFotografisanja;
	}

	public void setUserKojiJePostavioFotografiju(String userKojiJePostavioFotografiju) {
		this.userKojiJePostavioFotografiju = userKojiJePostavioFotografiju;
	}
	

	public String getFotografijaFajl() {
		return fotografijaFajl;
	}

	public void setFotografijaFajl(String fotografijaFajl) {
		this.fotografijaFajl = fotografijaFajl;
	}



}
