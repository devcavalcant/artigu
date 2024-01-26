package com.devcavalcant.artigu.controllers;

import com.devcavalcant.artigu.domain.user.RequestUserDTO;
import com.devcavalcant.artigu.domain.user.UpdateUserDTO;
import com.devcavalcant.artigu.domain.user.User;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    private ResponseEntity<List<User>> getAll(){
        var users = userRepository.findAll();

        return ResponseEntity.ok().body(users);
    }

    @GetMapping("{id}")
    private ResponseEntity<User> getOne(@PathVariable(value = "id") UUID id){
        System.out.println(id);
        var user = this.userRepository.findById(id);

        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    private ResponseEntity create(@RequestBody @Validated RequestUserDTO data){
        var user = new User(data);

        var userExist = this.userRepository.findByEmail(data.email());

        if(userExist.isEmpty()){
            var newUser = this.userRepository.save(user);
            return ResponseEntity.created(URI.create("localhost:8080")).body(newUser);
        }else{
            return  ResponseEntity.badRequest().body("User already exist");
        }
    }

    @PutMapping("{id}")
    private ResponseEntity update(@PathVariable(value = "id") UUID id, @RequestBody @Validated UpdateUserDTO data){
        var user = this.userRepository.findById(id);

        if(user.isPresent()){
            var updateUser = user.get();
            updateUser.setNickname(data.nickname());
            updateUser.setEmail(data.email());
            updateUser.setPassword(data.password());

            this.userRepository.save(updateUser);

            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    private ResponseEntity delete(@PathVariable(value = "id") UUID id){
        var user = this.userRepository.findById(id);

        if(user.isPresent()){
            if(user.get().getArticles().isEmpty()) return ResponseEntity.badRequest().body("User has registered articles");

            this.userRepository.delete(user.get());
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
