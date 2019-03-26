package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.respository.PostRepository;

import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    //metoda zwracajaca wszystkie posty
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        return postRepository.getOne(id);
    }


}
