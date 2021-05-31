package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
