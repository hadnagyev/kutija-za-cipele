package com.kutija.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kutija.model.Fotka;
import com.kutija.repository.FotkaRepository;
import com.kutija.service.FotkaService;

@Transactional
@Service
public class JpaFotkaService implements FotkaService{
	@Autowired
	private FotkaRepository fotkaRepository;
	
	@Override
	public List<Fotka> findAll(){
		return fotkaRepository.findAll();
	}

	@Override
	public Fotka findOne(Long id) {
		return fotkaRepository.findOne(id);

	}

	@Override
	public Fotka save(Fotka fotka) {

		return fotkaRepository.save(fotka);
	}

	@Override
	public Fotka findNextFotka(Long id) {

		return fotkaRepository.findFirstByIdGreaterThan(id);
	}

	@Override
	public Fotka findPreviousFotka(Long id) {
		List<Fotka> fotke = fotkaRepository.findLastByIdLessThan(id);
		Fotka fotka = fotke.get(fotke.size()-1);

		return fotka;
	}

	@Override
	public Fotka delete(Long id) {
		fotkaRepository.delete(id);
		return null;
	}




















}
