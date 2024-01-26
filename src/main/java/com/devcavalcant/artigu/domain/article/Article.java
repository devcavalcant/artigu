package com.devcavalcant.artigu.domain.article;

import com.devcavalcant.artigu.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "article")
@Table(name = "article")
@AllArgsConstructor()
@NoArgsConstructor()
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String text;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public Article (RequestArticleDTO data){
        this.title = data.title();
        this.text = data.text();
    }
}
