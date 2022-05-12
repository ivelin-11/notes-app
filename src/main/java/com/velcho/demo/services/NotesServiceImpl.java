package com.velcho.demo.services;

import com.velcho.demo.models.CurrentUser;
import com.velcho.demo.models.Note;
import com.velcho.demo.models.User;
import com.velcho.demo.repositories.NoteRepository;
import com.velcho.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class NotesServiceImpl implements NotesService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Autowired
    public NotesServiceImpl(UserRepository userRepository, NoteRepository noteRepository) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public Set<Note> getNotes(){
        Optional<User> optionalUser = this.userRepository.findByEmail(CurrentUser.getUser().getEmail());

        return optionalUser.get().getNotes();
    }

    public void saveNote(Note note){
        Optional<User> user = this.userRepository.findByEmail(CurrentUser.getUser().getEmail());

        this.noteRepository.save(note);
        user.get().getNotes().add(note);
        this.userRepository.save(user.get());
    }

    @Override
    public Optional<Note> findById(Long noteId) {

        Optional<Note> byId = this.noteRepository.findById(noteId);

        return byId;
    }

    @Override
    public void deleteById(Long id) {
        this.noteRepository.deleteById(id);
    }
}
