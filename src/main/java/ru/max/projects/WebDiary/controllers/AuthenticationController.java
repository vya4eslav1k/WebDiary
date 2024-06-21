package ru.max.projects.WebDiary.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.configuration.CustomUserDetails;
import ru.max.projects.WebDiary.configuration.CustomUserDetailsService;
import ru.max.projects.WebDiary.dao.interfaces.UserDao;

@Controller
public class AuthenticationController {
    UserDao userDao;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    CustomUserDetailsService userDetailsService;
    private SecurityContextRepository securityContextRepository;

    @Autowired
    public AuthenticationController(SecurityContextRepository securityContextRepository, CustomUserDetailsService userDetailsService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserDao userDao) {
        this.securityContextRepository = securityContextRepository;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (user.getLogin().length() < 3 || user.getLogin().length() > 30)
            bindingResult.addError(new FieldError("user", "login",  "Login length must be between 3 and 30 characters"));
        if (user.getPassword().length() < 5 || user.getPassword().length() > 32)
            bindingResult.addError(new FieldError("user", "password",  "Password length must be between 5 and 32 characters"));
        if (!user.getPassword().equals(user.getConfirmPassword()))
            bindingResult.addError(new FieldError("user", "confirmPassword", "Passwords do not match"));
        if (bindingResult.hasErrors()) return "register";

        if (userDao.getUser(user.getLogin()) != null) {
            bindingResult.addError(new FieldError("user", "login",  "Login is already in use"));

            return "register";
        }
        user.setRole("ROLE_USER");
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(user.getLogin());
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(customUserDetails, password, customUserDetails.getAuthorities());
        password = null;
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        securityContextRepository.saveContext(securityContext, request, response);
        return "redirect:/profile";
    }
}
