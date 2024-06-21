package ru.max.projects.WebDiary.dao.realisations.hibernate;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.max.projects.WebDiary.Entities.Category;
import ru.max.projects.WebDiary.dao.interfaces.CategoryDao;

import java.util.List;

@Component
public class CategoryDaoImpl implements CategoryDao {
    private SessionFactoryPool sessionFactoryPool;

    @Autowired
    public CategoryDaoImpl(SessionFactoryPool sessionFactoryPool) {
        this.sessionFactoryPool = sessionFactoryPool;
    }

    @Override
    public boolean updateCategory(Category category) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(category);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    @Override
    public Category getCategoryById(int id) {
        try (Session session = sessionFactoryPool.getSessionFactory().openSession()) {
            return session.get(Category.class, id);
        }
        catch (Exception e) {
            return null;
        }
    }

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
            e.printStackTrace();
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
