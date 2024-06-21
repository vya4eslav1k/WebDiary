package ru.max.projects.WebDiary.dao.realisations.hibernate;

import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.Category;
import ru.max.projects.WebDiary.Entities.Note;
import ru.max.projects.WebDiary.dao.interfaces.NoteDao;

import java.util.ArrayList;
import java.util.Date;
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
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.get(Note.class, id);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Note> getAllNotesForUser(int userId) {// todo add filters
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.createQuery("from Note where userId = :userId", Note.class).setParameter("userId", userId).list();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Note> getAllNotesForUserByCriteria(int userId, Date startDate, Date endDate, Integer categoryId, String sortOrder) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Note> cq = cb.createQuery(Note.class);
            Root<Note> root = cq.from(Note.class);
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userId"), userId));
            if (categoryId != null && categoryId != -1) {
                Join<Note, Category> categoryJoin = root.join("category");
                predicates.add(cb.equal(categoryJoin.get("id"), categoryId));
                System.out.println("add filtration by categoryId: " + categoryId);
            }
            if (startDate != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("lastUpdate"), startDate));
            }
            if (endDate != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("lastUpdate"), endDate));
            }
            cq.where(predicates.toArray(new Predicate[0]));
            if (sortOrder != null && sortOrder.equals("asc")) {
                cq.orderBy(cb.asc(root.get("lastUpdate")));
            } else {
                cq.orderBy(cb.desc(root.get("lastUpdate")));
            }
            return session.createQuery(cq).getResultList();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<Note> getAllNotesForUser(String login) {
//        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
//            int userId = session.get(User.class, login).getId();
//            System.out.println("userId = " + userId);
//            return session.createQuery("from Note where userId = :userId", Note.class).setParameter("userId", userId).list();
//        }
//        catch (Exception e) {
//            return null;
//        }
        return null; //todo
    }

    @Override
    public boolean addNote(Note note) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(note);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateNote(Note note) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            if (note.getCategory() != null && note.getCategory().getId() != null) {
                Category categoryReference = session.get(Category.class, note.getCategory().getId());
                note.setCategory(categoryReference);
            }
            session.merge(note);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteNote(int id) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            Note note = session.get(Note.class, id);
            session.remove(note);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
