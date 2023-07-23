package com.example.jpadata.user.db;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "user") // 테이블 이름
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserEntity {
    @Id
    // 해당 데이터는 MySQL에 의해서 Auto Increment가 된다는 것을 의미한다.
    // strategy의 종류는 DB에 따라서 달라진다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String email;

}
