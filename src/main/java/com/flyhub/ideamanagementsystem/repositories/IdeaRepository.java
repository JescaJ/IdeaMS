package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flyhub.ideamanagementsystem.entities.Idea;

public interface IdeaRepository extends JpaRepository<Idea, Integer> {
	//for the search feature
	@Query("SELECT i FROM Idea i WHERE LOWER(i.idea_title) LIKE LOWER(CONCAT('%',?1,'%'))" 
	+ "OR LOWER(i.idea_description) LIKE LOWER(CONCAT('%',?1,'%'))")
	public Page<Idea> findAll(Pageable pageable, String keyword);
	
}