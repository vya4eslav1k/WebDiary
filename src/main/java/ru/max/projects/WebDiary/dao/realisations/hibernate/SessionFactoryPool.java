package ru.max.projects.WebDiary.dao.realisations.hibernate;

import jakarta.annotation.PostConstruct;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;


@Component
public class SessionFactoryPool {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @PostConstruct
    public void init() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
}
