package com.shoebox.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblPhoto")
public class Photo {
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name="userKojiJePostavioFotografiju")
	private String userUploaded;
	
	@Column(name="datumSlanjaFotografije")
	private Date dateOfUpload;
	
	@Column(name="imeFotografa")
	private String nameOfPhotographer;
	
	@Column(name="vlasnikFotografije")
	private String photoOwner;
	
	@Column(name="datumNastankaFotografije")
	private Date dateOfMakingPhoto;
	
	@Column(name="mestoFotografisanja")
	private String placeOfMakingPhoto;
	
	@Column(name="blizeMestoFotografisanja")
	private String detailedPlaceOfMakingPhoto;
	
	@Column(name="brojKuceFotografisanja")
	private String houseNumberOfMakingPhoto;
	
	@Column(name="dogadjajFotografisanja")
	private String eventOfPhoto;
	
	@Column(name="fotografijaFajl")
	private String fileOfPhoto;

	public String userUploaded() {
		return userUploaded;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatumSlanjaFotografije() {
		return dateOfUpload;
	}

	public void setdateOfUpload(Date dateOfUpload) {
		this.dateOfUpload = dateOfUpload;
	}

	public String getnameOfPhotographer() {
		return nameOfPhotographer;
	}

	public void setnameOfPhotographer(String nameOfPhotographer) {
		this.nameOfPhotographer = nameOfPhotographer;
	}

	public Date getDateOfMakingPhoto() {
		return dateOfMakingPhoto;
	}

	public void setDateOfMakingPhoto(Date dateOfMakingPhoto) {
		this.dateOfMakingPhoto = dateOfMakingPhoto;
	}

	public String getPlaceOfMakingPhoto() {
		return placeOfMakingPhoto;
	}

	public void setPlaceOfMakingPhoto(String placeOfMakingPhoto) {
		this.placeOfMakingPhoto = placeOfMakingPhoto;
	}

	public String getEventOfPhoto() {
		return eventOfPhoto;
	}

	public void setEventOfPhoto(String eventOfPhoto) {
		this.eventOfPhoto = eventOfPhoto;
	}

	public void setuserUploaded(String userUploaded) {
		this.userUploaded = userUploaded;
	}
	

	public String getFileOfPhoto() {
		return fileOfPhoto;
	}

	public void setFileOfPhoto(String fileOfPhoto) {
		this.fileOfPhoto = fileOfPhoto;
	}

	public String getPhotoOwner() {
		return photoOwner;
	}

	public void setPhotoOwner(String photoOwner) {
		this.photoOwner = photoOwner;
	}

	public String getDetailedPlaceOfMakingPhoto() {
		return detailedPlaceOfMakingPhoto;
	}

	public void setDetailedPlaceOfMakingPhoto(String detailedPlaceOfMakingPhoto) {
		this.detailedPlaceOfMakingPhoto = detailedPlaceOfMakingPhoto;
	}

	public String getHouseNumberOfMakingPhoto() {
		return houseNumberOfMakingPhoto;
	}

	public void setHouseNumberOfMakingPhoto(String houseNumberOfMakingPhoto) {
		this.houseNumberOfMakingPhoto = houseNumberOfMakingPhoto;
	}



}
