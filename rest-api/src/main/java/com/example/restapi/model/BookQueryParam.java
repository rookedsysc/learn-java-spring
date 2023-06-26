package com.example.restapi.model;

import lombok.Data;

@Data
public class BookQueryParam {
    private  String category;
    private String issuedYear;
    private String issuedMonth;
    private String issuedDay;
}
