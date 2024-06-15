package ru.max.projects.WebDiary.dao.realisations.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.Note;
import ru.max.projects.WebDiary.dao.interfaces.NoteDao;

import java.util.List;

@Component
public class NoteDaoImpl implements NoteDao {
    SessionFactoryPool sessionFactoryPool;

    @Autowired
    public NoteDaoImpl(SessionFactoryPool sessionFactoryPool) {
        this.sessionFactoryPool = sessionFactoryPool;
    }
    @Override
    public Note getNote(int id) {
        return null;
    }

    @Override
    public List<Note> getAllNotesForUser(int userId) {
        return List.of();
    }

    @Override
    public List<Note> getAllNotesForUser(String login) {
        return List.of();
    }

    @Override
    public boolean addNote(Note note) {
        return false;
    }

    @Override
    public boolean updateNote(Note note) {
        return false;
    }

    @Override
    public boolean deleteNote(int id) {
        return false;
    }
}
