package ru.max.projects.WebDiary.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.max.projects.WebDiary.Entities.Category;
import ru.max.projects.WebDiary.Entities.Note;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.Models.NoteFilterForm;
import ru.max.projects.WebDiary.dao.interfaces.CategoryDao;
import ru.max.projects.WebDiary.dao.interfaces.NoteDao;
import ru.max.projects.WebDiary.dao.interfaces.UserDao;

import java.util.List;

@Controller
public class ProfileController {
    NoteDao noteDao;
    UserDao userDao;
    CategoryDao categoryDao;

    public ProfileController(NoteDao noteDao, UserDao userDao, CategoryDao categoryDao) {
        this.noteDao = noteDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @GetMapping("/profile")
    public String profile(@ModelAttribute NoteFilterForm noteFilterForm, Model model) {
        String login = ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userDao.getUser(login);
        List<Note> notes = noteDao.getAllNotesForUserByCriteria(user.getId(), noteFilterForm.getStartDate(),
                noteFilterForm.getEndDate(), noteFilterForm.getCategoryId(), noteFilterForm.getSortOrder());
        List<Category> categories = categoryDao.getAllCategoriesForUser(user.getId());
        model.addAttribute("categoryList", categories);
        model.addAttribute("noteList", notes);
        model.addAttribute("user", user);
        return "profile";
    }
}
