package com.flyhub.ideamanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flyhub.ideamanagementsystem.entities.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

}
