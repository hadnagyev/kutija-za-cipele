package com.kutija.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kutija.model.Fotka;

@Repository
public interface FotkaRepository extends JpaRepository<Fotka, Long> {
	List<Fotka> findAll();

	Fotka findOne(Long id);
	
	Fotka findFirstByIdGreaterThan(Long id);
	
	List<Fotka> findLastByIdLessThan(Long id);
}
