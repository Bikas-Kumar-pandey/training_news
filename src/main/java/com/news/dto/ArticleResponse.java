package com.news.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleResponse {
    private String title;
    private String description;
    private Date date;
    private String category;
    private int articleId;

}
