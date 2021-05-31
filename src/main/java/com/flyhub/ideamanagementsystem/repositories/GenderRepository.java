package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {

}
