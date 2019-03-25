package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.respository.UserReposirory;

import java.util.List;

@Service
public class MainService {
    // Dependency injection
    UserReposirory userReposirory;

    @Autowired   // wstrzykniecie zaleznosci przez konstruktor
    public MainService(UserReposirory userReposirory) {
        this.userReposirory = userReposirory;
    }

    public List<User> getAllUsers() {
        return userReposirory.findAll();  // Selct * from user
    }

    // EndPoint  zwracajacy uzytkownika o zadanym adresie email
    public User getUserByEmail(String email) {
        return userReposirory.findByEmail(email);              // select * From user Where email =?
    }
}
