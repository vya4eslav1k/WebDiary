package ru.max.projects.WebDiary.dao.interfaces;

import ru.max.projects.WebDiary.Entities.Note;

import java.util.List;

public interface NoteDao {
    public Note getNote(int id);
    public List<Note> getAllNotesForUser(int userId);
    public List<Note> getAllNotesForUser(String login);
    public boolean addNote(Note note);
    public boolean updateNote(Note note);
    public boolean deleteNote(int id);
}
