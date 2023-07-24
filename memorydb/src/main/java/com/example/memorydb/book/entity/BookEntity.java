package com.example.memorydb.book.entity;

import com.example.memorydb.entity.Entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@javax.persistence.Entity(name = "book")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookEntity extends Entity {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private BigDecimal price;
}
