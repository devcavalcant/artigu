package com.devcavalcant.artigu.controllers;


import com.devcavalcant.artigu.domain.article.Article;
import com.devcavalcant.artigu.domain.article.RequestArticleDTO;
import com.devcavalcant.artigu.repositories.ArticleRepository;
import com.devcavalcant.artigu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    private ResponseEntity<List<Article>> getAll(){
        var articles = this.articleRepository.findAll();
        return ResponseEntity.ok(articles);
    }

    @GetMapping("{id}")
    private ResponseEntity<Article> getOne(@PathVariable(value = "id") UUID id){
        var article = this.articleRepository.findById(id);

        if (article.isPresent()) return ResponseEntity.ok(article.get());
        else return ResponseEntity.notFound().build();
    }

    @PostMapping
    private ResponseEntity create(@RequestBody @Validated  RequestArticleDTO data){
        var article = new Article(data);

        var writer = this.userRepository.findById(data.user_id());

        if(writer.isPresent()){
            article.setUser(writer.get());
            var newArticle = this.articleRepository.save(article);
            return  ResponseEntity.created(URI.create("localhost:8080")).body(newArticle);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    private ResponseEntity update(@PathVariable(value = "id") UUID id, @RequestBody @Validated RequestArticleDTO data){
        var article = this.articleRepository.findById(id);

        if(article.isPresent()){
            var updateArticle = article.get();
            updateArticle.setText(data.text());
            updateArticle.setTitle(data.title());

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity remove(@PathVariable(value = "id") UUID id){
        var article = this.articleRepository.findById(id);

        if(article.isPresent()){
            this.articleRepository.delete(article.get());
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
