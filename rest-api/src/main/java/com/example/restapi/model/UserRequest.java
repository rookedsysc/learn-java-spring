package com.example.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    private String userName;
    private String userEmail;
    // 이와 같이 JsonProperty를 사용하여 Json Mapping을 직접 t정할 수 있다.
    // 이건 위에서 선언한 JsonNaming을 무시한다.
    @JsonProperty("userAge")
    private int age;


    // Json Mapping 과정에서 예상치 못한 결과가 나올 수 있기 때문에
    // JsonIgnore를 사용하여 무시하도록 한다.
    @JsonIgnore
    public String getUserName() {
        return this.userName + " 입니다.";
    }
}
