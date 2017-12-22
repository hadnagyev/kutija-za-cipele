package com.shoebox.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shoebox.model.Photo;
import com.shoebox.repository.PhotoRepository;
import com.shoebox.service.PhotoService;

@Transactional
@Service
public class JpaPhotoService implements PhotoService{
	@Autowired
	private PhotoRepository photoRepository;
	
	@Override
	public List<Photo> findAll(){
		return photoRepository.findAll();
	}

	@Override
	public Photo findOne(Long id) {
		return photoRepository.findOne(id);

	}

	@Override
	public Photo save(Photo fotka) {

		return photoRepository.save(fotka);
	}

	@Override
	public Photo findNextPhoto(Long id) {

		return photoRepository.findFirstByIdGreaterThan(id);
	}

	@Override
	public Photo findPreviousPhoto(Long id) {
		List<Photo> photos = photoRepository.findLastByIdLessThan(id);
		Photo photo = photos.get(photos.size()-1);

		return photo;
	}

	@Override
	public Photo delete(Long id) {
		photoRepository.delete(id);
		return null;
	}




















}
