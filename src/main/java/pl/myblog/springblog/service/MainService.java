package pl.myblog.springblog.service;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.model.PostCategory;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.respository.PostRepository;
import pl.myblog.springblog.respository.UserReposirory;

import java.util.List;

@Service
public class MainService {
    // Dependency injection
    UserReposirory userReposirory;
    PostRepository postRepository;

    @Autowired
    public MainService(UserReposirory userReposirory, PostRepository postRepository) {
        this.userReposirory = userReposirory;
        this.postRepository = postRepository;
    }

    //    @Autowired   // wstrzykniecie zaleznosci przez konstruktor
//    public MainService(UserReposirory userReposirory) {
//        this.userReposirory = userReposirory;
//    }

    public List<User> getAllUsers() {
        return userReposirory.findAll();  // Selct * from user
    }

    // EndPoint  zwracajacy uzytkownika o zadanym adresie email
    public User getUserByEmail(String email) {
        return userReposirory.findByEmail(email);              // select * From user Where email =?
    }

    //End Pointzwracajacy liczbe uzytkownikow
    public Long countAllUsers(){
        return  userReposirory.count();
    }

    //End Point zwracajacy aktywnosc uzytkownika
    public void updateUserActivityById(Long id){
        User user = userReposirory.getOne(id);
        user.setActive(!user.getActive());
        userReposirory.save(user);
    }

    //End Point zwracajacy wynik logowania
    public User logIn(String email, String password){
        return userReposirory.findByEmailAndPassword(email,password);
    }

    //End Point usuwanjacy uzytkownika po ID
    public void deleteUserById(Long id){
        userReposirory.deleteById(id);
    }

    //End Point utworzenie nowego posta
    public Post addPost(Long id, String title,  String content){
        // szukamy usera po ID
        User user = userReposirory.getOne(id);
        Post post = new Post(title,content, PostCategory.PROGRAMOWANIE,user);
        // dodaj posta do zbioru postow obiektu user
        user.addPost(post);
        return postRepository.save(post);
    }
}
