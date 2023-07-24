package com.example.memorydb.user.service;

import com.example.memorydb.user.db.UserRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service 로직이 들어가는 Bean의 로직이다. 라고 Spring에 알려준다.
@Service
public class UserService {

    @Autowired // Autowired를 사용할 경우 final 키워드를 사용할 수 없음
    private UserRepository userRepository;

    public UserEntity create(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long id){
//        userRepository.delete(user);
    }

    public List<UserEntity> findAllScoreGreaterThen(Integer score) {
        return userRepository.findAllByScoreGreaterThan(score);
    }

    public List<UserEntity> findAllByLowLessThanEqualAndHighGreaterThanEqual(Integer high, Integer low) {
        return userRepository.findAllByScoreLessThanEqualAndScoreGreaterThanEqual(high, low);
    }

    public List<UserEntity> scoreBetween(Integer high, Integer low) {
        return userRepository.scoreBetween(high, low);
    }



    public Optional<UserEntity> findById(Long id) {
        return null;
//        return userRepository.findByID(id);
    }
}
