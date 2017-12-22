package com.shoebox.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shoebox.model.Photo;
import com.shoebox.service.PhotoService;

@RestController
@RequestMapping(value = "api/fotke")
public class ApiPhotoController {
	@Autowired
	private PhotoService photoService;

	// GET photo
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Photo> getPhoto(@RequestParam(value = "currentFotka", required = true) Long id,
			@RequestParam(value = "previousFotka", required = false) Integer previous) {
		Photo photo = null;
		int hasNextPhoto = -1;
		int hasPreviousPhoto = -1;

		if (previous != -1) {
			if (previous == 1) {
				id--;
			} else {
				id++;

			}
		}
		photo = photoService.findOne(id);

		while (photo == null) {
			if (previous == 1) {
				photo = photoService.findPreviousPhoto(id);
			} else {
				photo = photoService.findNextPhoto(id);
			}
		}

		List<Photo> photos = new ArrayList<Photo>();
		photos = photoService.findAll();
		// if the id of current photo is same as id of the last one sending hasnext=
		// 0 so next photo is disabled
		if (photos.get((photos.size()) - 1).getId().equals(photo.getId())) {
			hasNextPhoto = 0;
		}
		// if id of current is same as id of previous, button hasprevious=0
		// previous photo disabled
		if (photo.getId().equals(photos.get(0).getId())) {
			hasPreviousPhoto = 0;
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("has-next-fotka", "" + hasNextPhoto);
		httpHeaders.add("has-previous-fotka", "" + hasPreviousPhoto);
		return new ResponseEntity<>(photo, httpHeaders, HttpStatus.OK);
	}

	// GET Photo BY ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Photo> getFotkaId(@PathVariable Long id) {
		Photo fotka = photoService.findOne(id);

		return new ResponseEntity<>(fotka, HttpStatus.OK);
	}

	// save uploaded photo to folder
	@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<Photo> savePhoto(@RequestBody MultipartFile file) {

		if (file != null) {
			String name = file.getOriginalFilename();
			System.out.println(name);
			try {
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("src/main/webapp/fotke/" + name)));
				FileCopyUtils.copy(file.getInputStream(), stream);
				stream.close();

			} catch (Exception e) {

			}
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// save uploaded photo data to sql database
	@RequestMapping(value = "/data", method = RequestMethod.POST, consumes = "application/JSON")
	public ResponseEntity<Photo> savePhotoData(@RequestBody Photo photo) {
		photo.setFileOfPhoto("fotke/" + photo.getFileOfPhoto());
		photoService.save(photo);

		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

	// get all photos
	@RequestMapping(value = "/sve", method = RequestMethod.GET)
	public ResponseEntity<List<Photo>> getAllPhotos() {
		List<Photo> allPhotos = null;
		allPhotos = new ArrayList<Photo>();
		allPhotos = photoService.findAll();

		return new ResponseEntity<List<Photo>>(allPhotos, HttpStatus.OK);
	}

	// delete photo
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<Photo> deletePhoto(@PathVariable Long id) {
		photoService.delete(id);

		return null;
	}

}
