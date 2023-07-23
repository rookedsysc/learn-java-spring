package com.example.jpadata.user.controller;


import com.example.jpadata.user.db.UserEntity;
import com.example.jpadata.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/find-all")
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/add/{name}")
    public void autoSave(
            @PathVariable String name
    ) {
        /*
        INSERT INTO user.user (name) VALUES (value)
        위의 Query와 동일한 코드이다.
         */
        var user = UserEntity.builder().name(name).build();
        userRepository.save(user);
    }
}
