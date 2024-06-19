package ru.max.projects.WebDiary.dao.realisations.hibernate;

import org.hibernate.Session;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.Category;
import ru.max.projects.WebDiary.dao.interfaces.GroupDao;

import java.util.List;

@Component
public class GroupDaoImpl implements GroupDao {
    private SessionFactoryPool sessionFactoryPool;
    @Override
    public List<Category> getAllCategoriesForUser(int userId) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.createQuery("from Category where userId=:userId", Category.class).setParameter("userId", userId).list();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addCategory(Category category) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(category);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            Category category = session.get(Category.class, id);
            session.remove(category);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
