package com.flyhub.ideamanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Note;
import com.flyhub.ideamanagementsystem.entities.User;
import com.flyhub.ideamanagementsystem.repositories.NoteRepository;
import com.flyhub.ideamanagementsystem.repositories.UserRepository;

@Service
public class NoteService {

	@Autowired
	private NoteRepository noteRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	  //save the idea for first time
    public Object save(Note note) {    	
    	return noteRepo.save(note);
    }
}