package pl.myblog.springblog.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


}
