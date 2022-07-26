package com.news.dto;


import lombok.Data;

import java.util.Date;

@Data
public class CategoryResponse {
    private String category;
    private Date date;
}
