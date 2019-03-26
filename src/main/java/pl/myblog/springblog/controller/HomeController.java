package pl.myblog.springblog.controller;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")            // mapowany adres
    public String home(Model model) {        //  nazwa metody wywolanej dla URL
        List<Post> posts = postService.getAllPost();
        // sortowanie po dacie

        List<Post> sortedPost = posts
                .stream()
                .sorted((p1, p2) -> p2.getDate_added().compareTo(p1.getDate_added()))
                .collect(Collectors.toList());
        //przekazanie obiektu posts do html i w Html tez bedzie nazywal sie post
        model.addAttribute("posts", sortedPost);
        return "index";          // nazwa zwracanego widokuHTML
    }

    @GetMapping("/allposts/{id}")
    public String getOnePost(@PathVariable("id") Long id,
                             Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}
