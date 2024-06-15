package ru.max.projects.WebDiary.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.max.projects.WebDiary.Entities.User;
import ru.max.projects.WebDiary.dao.interfaces.UserDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUser(username);
        if (user == null) throw new UsernameNotFoundException(username);
        else return new CustomUserDetails(user);
    }
}
