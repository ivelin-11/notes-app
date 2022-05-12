package com.velcho.demo.services;

import com.velcho.demo.models.Note;

import java.util.Optional;
import java.util.Set;


public interface NotesService {

    public Set<Note> getNotes();

    void saveNote(Note note);

    Optional<Note> findById(Long noteId);

    void deleteById(Long id);

}
