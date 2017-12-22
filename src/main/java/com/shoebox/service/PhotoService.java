package com.shoebox.service;

import java.util.List;

import com.shoebox.model.Photo;

public interface PhotoService {
	List<Photo> findAll();

	Photo findOne(Long id);
	
	Photo save(Photo photo);
	
	Photo findNextPhoto(Long id);
	
	Photo findPreviousPhoto(Long id);
	
	Photo delete(Long id);
	



}
