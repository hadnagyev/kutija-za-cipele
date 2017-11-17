package com.kutija.service;

import java.util.List;
import com.kutija.model.Fotka;

public interface FotkaService {
	List<Fotka> findAll();

	Fotka findOne(Long id);
	
	Fotka save(Fotka fotka);
	
	Fotka findNextFotka(Long id);
	
	Fotka findPreviousFotka(Long id);
	
	Fotka delete(Long id);
	



}
