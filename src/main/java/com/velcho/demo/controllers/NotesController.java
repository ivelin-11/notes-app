package com.velcho.demo.controllers;


import com.velcho.demo.models.CurrentUser;
import com.velcho.demo.models.Note;
import com.velcho.demo.models.dto.UserLoginDTO;
import com.velcho.demo.repositories.NoteRepository;
import com.velcho.demo.repositories.UserRepository;
import com.velcho.demo.services.NotesService;
import com.velcho.demo.services.UserService;
import com.velcho.demo.utill.FormObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;


@Controller
public class NotesController {


    private final NotesService notesService;
    private final UserService userService;

    private boolean isRedirecting =true;
    @Autowired
    public NotesController(NotesService notesService, UserService userService, UserRepository userRepository, NoteRepository noteRepository) {
        this.notesService = notesService;
        this.userService = userService;
    }


    @GetMapping("notes")
    public ModelAndView getNotes() {

        if (CurrentUser.getUser() == null) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT, new UserLoginDTO());
        }
        ModelAndView modelAndView = new ModelAndView("user/notes");
        modelAndView.addObject(FormObjects.NOTES_FORM_OBJECT, this.notesService.getNotes());
        modelAndView.addObject("loggedData", CurrentUser.getUser().getLoggedData());

        return modelAndView;
    }

    @GetMapping("addNote")
    public ModelAndView addNote() {
        if (CurrentUser.getUser() == null) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT, new UserLoginDTO());
        }

        ModelAndView modelAndView = new ModelAndView("notes/add-note");
        Note newNote = new Note();
        modelAndView.addObject("note", newNote);

        return modelAndView;
    }

    @PostMapping("addNote")
    public ModelAndView addNoteSuccessOrNot(@ModelAttribute("note") Note note) {
        if (CurrentUser.getUser() == null) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT, new UserLoginDTO());
        }

        note.setCreatedOn(LocalDate.now());
        this.notesService.saveNote(note);

        ModelAndView modelAndView = new ModelAndView("user/notes");
        modelAndView.addObject(FormObjects.NOTES_FORM_OBJECT, this.notesService.getNotes());
        modelAndView.addObject("loggedData", CurrentUser.getUser().getLoggedData());

        return modelAndView;
    }

    @GetMapping("updateNote")
    public ModelAndView updateNode(@RequestParam Long noteId) {
        if (CurrentUser.getUser() == null) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT, new UserLoginDTO());
        }

        ModelAndView modelAndView = new ModelAndView("notes/add-note");
        Note note = this.notesService.findById(noteId).get();
        modelAndView.addObject("note", note);

        return modelAndView;
    }



    @GetMapping({"deleteNote/{id}"})
    public ModelAndView deleteNote(@PathVariable(name = "id") Long id) {
        if (CurrentUser.getUser() == null) {
            return new ModelAndView("user/login").addObject(FormObjects.LOGIN_FORM_OBJECT, new UserLoginDTO());
        }



        Set<Note> notes = CurrentUser.getUser().getNotes();
        Iterator<Note> iterator = notes.iterator();

        Note n =null;
        while (iterator.hasNext()) {
            n=iterator.next();
            if (n.getId() == id) {
                iterator.remove();
                break;
            }
        }
        CurrentUser.getUser().setNotes(notes);
        this.userService.save(CurrentUser.getUser());

        this.notesService.deleteById(id);



        return getNotes();
    }
}
