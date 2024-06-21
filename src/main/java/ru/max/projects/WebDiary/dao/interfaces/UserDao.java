package ru.max.projects.WebDiary.dao.interfaces;

import ru.max.projects.WebDiary.Entities.User;

public interface UserDao {
    public User getUser(int id);
    public User getUser(String login);
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(String login);
}
