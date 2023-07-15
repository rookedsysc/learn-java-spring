package com.example.validation.model;

import java.time.LocalDateTime;


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
    @NotBlank
    private String name;
    @NotNull
    @Min(1)
    @Max(100)
    private Integer age;
    @Size(min = 8, max = 20)
    @NotBlank
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$")
    private String phoneNumber;
    // ISO 8601로 들어가야 해서 2023-07-12T10:15:30 이런식으로 들어가야 함
    @FutureOrPresent
    private LocalDateTime registerAt;
}
