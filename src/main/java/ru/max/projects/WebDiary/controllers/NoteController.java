package ru.max.projects.WebDiary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.max.projects.WebDiary.Entities.Note;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.dao.interfaces.NoteDao;

import java.util.Date;

@Controller
public class NoteController {
    NoteDao noteDao;

    public NoteController(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    @PostMapping("/note")
    public String addNote(@ModelAttribute User user) {
        Note note = new Note();
        note.setLastUpdate(new Date());
        note.setUserId(user.getId());
        noteDao.addNote(note);
        return "redirect:/profile";
    }

    @PatchMapping("/note")
    public String updateNote(@ModelAttribute Note note) {
        if (note.getCategory().getId() == -1) note.setCategory(null);
        note.setLastUpdate(new Date());
        noteDao.updateNote(note);
        return "redirect:/profile";
    }

    @DeleteMapping("/note")
    public String deleteNote(@ModelAttribute Note note) {
        noteDao.deleteNote(note.getId());
        return "redirect:/profile";
    }
}
