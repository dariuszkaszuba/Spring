package pl.myblog.springblog.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.User;

@Repository
public interface UserReposirory extends JpaRepository<User, Long> {
    // select * From user Where email =?
    User findByEmail(String email);
}
