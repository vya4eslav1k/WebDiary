package ru.max.projects.WebDiary.dao.realisations.hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.dao.interfaces.UserDao;

@Component
public class UserDaoImpl implements UserDao {
    SessionFactoryPool sessionFactoryPool;

    @Autowired
    public UserDaoImpl(SessionFactoryPool sessionFactoryPool) {
        this.sessionFactoryPool = sessionFactoryPool;
    }

    @Override
    public User getUser(int id) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public User getUser(String login) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.createQuery("from User where login = :login", User.class).setParameter("login", login).getSingleResult();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(String login) {
        return false;
    }
}
