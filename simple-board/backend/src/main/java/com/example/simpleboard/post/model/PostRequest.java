package com.example.simpleboard.post.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostRequest {
    @NotBlank
    private String userName;
    @NotBlank
    @Size(max = 4, min = 4)
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
