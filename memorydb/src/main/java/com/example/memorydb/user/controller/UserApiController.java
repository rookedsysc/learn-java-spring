package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {
    // RequiredArgsConstructor 이걸 추가해줌으로써
    // Spring으로부터 UserService Bean을 주입받는다.
    private final UserService userService;

    @PutMapping
    public UserEntity create(
            @RequestBody UserEntity userEntity
    ) {
        return userService.create(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/del/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        userService.delete(id);
    }

    @GetMapping("/score")
    public List<UserEntity> scoreBetween(
            @RequestParam Integer score
    ) {
        return userService.findAllScoreGreaterThen(score);
    }

    @GetMapping("/min-max")
    public List<UserEntity> scoreBetween(
            @RequestParam Integer low,
            @RequestParam Integer high
    ) {
        return userService.findAllByLowLessThanEqualAndHighGreaterThanEqual(high, low);
    }

    @GetMapping("/min-max2")
    public List<UserEntity> scoreBetween2 (
            @RequestParam Integer low,
            @RequestParam Integer high
    ) {
        return userService.scoreBetween(high, low);
    }



    @GetMapping("/find/{id}")
    public UserEntity findById(
            @PathVariable Long id
    ) {
        Optional<UserEntity> userEntity = userService.findById(id);
        return userEntity.get();
    }
}
