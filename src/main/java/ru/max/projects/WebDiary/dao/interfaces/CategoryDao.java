package ru.max.projects.WebDiary.dao.interfaces;

import ru.max.projects.WebDiary.Entities.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategoriesForUser(int userId);
    public boolean addCategory(Category category);
    public boolean deleteCategory(int id);
    public Category getCategoryById(int id);
    public boolean updateCategory(Category category);
}
