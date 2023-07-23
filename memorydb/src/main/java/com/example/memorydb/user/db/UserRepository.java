package com.example.memorydb.user.db;

import com.example.memorydb.db.SimpleDataRepository;
import com.example.memorydb.user.model.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserRepository extends SimpleDataRepository<UserEntity, Long> {
    public List<UserEntity> findAllScoreGreaterThen(Integer score) {
        List<UserEntity> dataList = findAll();

        return dataList.stream().filter(
                val -> val.getScore() >= score
        ).toList();
    }

}
