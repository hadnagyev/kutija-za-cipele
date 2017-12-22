package com.shoebox.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoebox.model.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	List<Photo> findAll();

	Photo findOne(Long id);
	
	Photo findFirstByIdGreaterThan(Long id);
	
	List<Photo> findLastByIdLessThan(Long id);
}
