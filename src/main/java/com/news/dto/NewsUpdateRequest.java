package com.news.dto;

import lombok.Data;

import java.util.Date;

@Data

public class NewsUpdateRequest {
    private String category;
    private String title;
    private String description;
    private String titles;
    private Date date;
    private int id;



}
