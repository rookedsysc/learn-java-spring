package com.example.memorydb.entity;



import lombok.*;

@Data
public abstract class Entity implements PrimaryKey {
    @Getter
    @Setter
    private Long id;
}
