package com.news.dto;


import lombok.Data;

import java.util.List;

@Data
public class NewsRequest {
    private String category;
   private List<ArticlesRequest> articles;

}
