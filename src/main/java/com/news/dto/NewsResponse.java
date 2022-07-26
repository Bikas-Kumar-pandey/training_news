package com.news.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NewsResponse {
    private String category;
    private String titles;
    private String description;
    private Date date;
    private int id;
}
