package com.devcavalcant.artigu.domain.user;

import com.devcavalcant.artigu.domain.article.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "users")
@AllArgsConstructor()
@NoArgsConstructor()
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nickname;

    private String email;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Article> articles;

    public UserRole role;

    public User (RequestUserDTO data){
        this.nickname = data.nickname();
        this.email = data.email();
        this.password = data.password();
    }
}
