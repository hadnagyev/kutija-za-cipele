package com.kutija.controller;

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

import com.kutija.model.Fotka;
import com.kutija.service.FotkaService;

@RestController
@RequestMapping(value = "api/fotke")
public class ApiFotkeController {
	@Autowired
	private FotkaService fotkaService;

	// GET FOTKA
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Fotka> getFotka(@RequestParam(value = "currentFotka", required = true) Long id,
			@RequestParam(value = "previousFotka", required = true) int previous) {
		Fotka fotka = null;
		int hasNextFotka = -1;
		int hasPreviousFotka = -1;

		if (previous == 1) {
			id--;
		} else {
			id++;

		}
		fotka = fotkaService.findOne(id);

		while (fotka == null) {
			if (previous == 1) {
				fotka = fotkaService.findPreviousFotka(id);
			} else {
				fotka = fotkaService.findNextFotka(id);
			}
		}

		List<Fotka> fotke = new ArrayList<Fotka>();
		fotke = fotkaService.findAll();
		// ako je id trenutne fotke isti kao id poslednje onda saljem hasnext =
		// 0 pa je dugme sledeca fotka disabled
		if (fotke.get((fotke.size()) - 1).getId().equals(fotka.getId())) {
			hasNextFotka = 0;
		}
		// ako je id trenutne fotke isti kao id prve onda hasprevious=0, dugme
		// prethodna fotka disabled
		if (fotka.getId().equals(fotke.get(0).getId())) {
			hasPreviousFotka = 0;
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("has-next-fotka", "" + hasNextFotka);
		httpHeaders.add("has-previous-fotka", "" + hasPreviousFotka);
		return new ResponseEntity<>(fotka, httpHeaders, HttpStatus.OK);
	}

	// GET FOTKA BY ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Fotka> getFotkaId(@PathVariable Long id) {
		Fotka fotka = fotkaService.findOne(id);
		// System.out.println(fotka.toString());
		return new ResponseEntity<>(fotka, HttpStatus.OK);
	}

	// save uploaded photo to folder
	@RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
	public ResponseEntity<Fotka> saveFotka(@RequestBody MultipartFile file) {

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
	public ResponseEntity<Fotka> saveFotkaData(@RequestBody Fotka fotka) {
		fotka.setFotografijaFajl("fotke/" + fotka.getFotografijaFajl());
		fotkaService.save(fotka);

		return new ResponseEntity<Fotka>(fotka, HttpStatus.OK);
	}

	// get all photos
	@RequestMapping(value = "/sve", method = RequestMethod.GET)
	public ResponseEntity<List<Fotka>> getSveFotke() {
		List<Fotka> sveFotke = null;
		sveFotke = new ArrayList<Fotka>();
		sveFotke = fotkaService.findAll();

		return new ResponseEntity<List<Fotka>>(sveFotke, HttpStatus.OK);
	}

	// delete photo
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResponseEntity<Fotka> deleteFotka(@PathVariable Long id) {
		fotkaService.delete(id);

		return null;
	}

}
