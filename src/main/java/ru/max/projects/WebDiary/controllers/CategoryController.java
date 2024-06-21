package ru.max.projects.WebDiary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.max.projects.WebDiary.Entities.Category;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.dao.interfaces.CategoryDao;
import ru.max.projects.WebDiary.dao.interfaces.UserDao;

@Controller
public class CategoryController {
    CategoryDao categoryDao;
    UserDao userDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao, UserDao userDao) {
        this.categoryDao = categoryDao;
        this.userDao = userDao;
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        String login = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userDao.getUser(login);
        model.addAttribute("user", user);
        model.addAttribute("categoryList", categoryDao.getAllCategoriesForUser(user.getId()));
        return "categories";
    }

    @PatchMapping("/category")
    public String updateCategory(@ModelAttribute Category category) {
        categoryDao.updateCategory(category);
        return "redirect:/categories";
    }

    @DeleteMapping("/category")
    public String deleteCategory(@ModelAttribute Category category) {
        categoryDao.deleteCategory(category.getId());
        return "redirect:/categories";
    }

    @PostMapping("/category")
    public String addCategory(@ModelAttribute User user) {
        Category category = new Category();
        category.setUserId(user.getId());
        category.setName("New category");
        categoryDao.addCategory(category);
        return "redirect:/categories";
    }
}
