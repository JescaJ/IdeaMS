package com.flyhub.ideamanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flyhub.ideamanagementsystem.entities.Idea;
import com.flyhub.ideamanagementsystem.entities.IdeaNotes;
import com.flyhub.ideamanagementsystem.entities.Note;
import com.flyhub.ideamanagementsystem.repositories.IdeaRepository;
import com.flyhub.ideamanagementsystem.repositories.NoteRepository;

@Service
public class IdeaNotesService implements IdeaNotes {

	@Autowired
    private IdeaRepository ideaRepo;
	
    @Autowired
    private NoteRepository noteRepo;

    @Override
    public void save(Idea idea, Note note) {
        if (idea.getIdea_id() == note.getIdea_id()) {
        	Idea currentIdea = ideaRepo.getOne(idea.getIdea_id());
        	
        	currentIdea.setIdea_title(idea.getIdea_title());
        	currentIdea.setIdea_description(idea.getIdea_description());
        	
        	ideaRepo.save(currentIdea);
        	
//        	ideaService.saveUpdatedIdea(idea);
        	noteRepo.save(note);
        } else throw new IllegalArgumentException("The ids of the entities do not match.");
    }


}
