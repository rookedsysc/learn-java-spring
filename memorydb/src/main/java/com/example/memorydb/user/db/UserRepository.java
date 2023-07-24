package com.example.memorydb.user.db;

import com.example.memorydb.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // select * from user where score > [??]
    // SELECT * FROM book_store.`user` WHERE score > 95;
    public List<UserEntity> findAllByScoreGreaterThan(Integer sc);
    // GreaterThan : >
    // Equal : =
    // 쿼리메서드By[변수]조건문 : JPA에서는 메서드의 명에 따라서 Query를 자동으로 생성해준다.
    // 이 때, 인자로 받는 변수는 쿼리메서드 이후에 나오는 필드 부분에 들어가고 CamelCase 기준으로 나눈다.
    // By 뒤로 들어가는 부분은 Where 절이다.
    // ex)findAll(쿼리메서드)[필드명]GreaterThanEqual(조건문)

    // select * from book_store.user where low <= [??] and high >= [??];
    public List<UserEntity> findAllByScoreLessThanEqualAndScoreGreaterThanEqual(Integer high, Integer low);
//    public List<UserEntity> findAllByScoreGreaterThanEqual(Integer score);


    @Query(
            // form user u : user는 Entity를 뜻하고 u는 user의 별칭
            // u.score : user entity 안에 있는 score 필드(변수)
            // ?1, ?2 : 들어오는 첫 번째 인자, 두 번째 인자
            // "select * from user u where u.score <= ?1 and u.score >= ?2"
            // named parameter 매칭 방식 : @Param으로 등록해준 이름으로 :[이름]을 사용해서 매칭한다.
            value = "select * from user u where u.score <= :high and u.score >= :low",
            nativeQuery = true
    )
    public List<UserEntity> scoreBetween(
            @Param(value = "high") Integer high,
            @Param(value = "low") Integer low);
}
