package com.devcavalcant.artigu.repositories;

import com.devcavalcant.artigu.domain.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID> {
}
