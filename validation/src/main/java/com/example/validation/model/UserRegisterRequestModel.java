package com.example.validation.model;

import java.time.LocalDateTime;
import java.util.Objects;


import com.example.validation.annotation.PhoneNumberAnnotation;
import com.example.validation.annotation.YearMonthAnnotation;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequestModel {
    private String name;
    private String nickName;
    @NotNull
    @Min(1)
    @Max(100)
    private Integer age;
    @Size(min = 8, max = 20)
    @NotBlank
    private String password;
    @Email
    private String email;
    @PhoneNumberAnnotation
    private String phoneNumber;
    @FutureOrPresent
    private LocalDateTime registerAt;
    @YearMonthAnnotation
    private String birthday;

    // 반드시 이름은 is 또는 get
    @AssertTrue(message = "name or nickName is required")
    public boolean isNameCheck() {
        if(Objects.nonNull(nickName) && !nickName.isBlank()) {
            return true;
        }
        if(Objects.nonNull(name) && !name.isBlank()) {
            return true;
        }

        return false;
    }
}
