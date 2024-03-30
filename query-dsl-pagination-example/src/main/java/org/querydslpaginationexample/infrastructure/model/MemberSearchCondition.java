package org.querydslpaginationexample.infrastructure.model;

import lombok.Data;

@Data
public class MemberSearchCondition {
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
