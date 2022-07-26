package com.news.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryRequest {
    private String category;
    Date date=new Date();
}
