package pl.myblog.springblog.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @Email
    @NotNull
    private String email;
    @Length(min = 6)
    @Pattern(regexp = "([A-Z]+.*[0-9]+|[0-9]+.*[A-Z])")
    private String password;

    private boolean active = true;
    private LocalDateTime registered_date = LocalDateTime.now();

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),        // tego chyba moglo by nie byc to wtedy defaultowo
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Post> posts = new HashSet<>();

}
