package com.news.controller;

import com.news.dto.NewsByDateResponse;
import com.news.dto.NewsRequest;
import com.news.dto.NewsResponse;
import com.news.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) { this.newsService = newsService;}


    @PostMapping("/news")
    public List<NewsResponse> setNews(@RequestBody NewsRequest newsRequest){
        return newsService.setNews(newsRequest);
    }

    @GetMapping("/news/{id}")
    public List<NewsResponse> getNews(@PathVariable("id")int id){
        return newsService.getNews(id);
    }

//

    @PutMapping("/news/{id}")
    public List<NewsResponse> updateNews(@PathVariable("id")int id,@RequestBody NewsRequest request){
        return newsService.updateNews(id,request);
    }

    @DeleteMapping("/news/{id}")
    public String deleteNews(@PathVariable("id") int id){
       return newsService.deleteNews(id);
    }

    @GetMapping("/news-by-date")// @DateTimeFormat(pattern = "yyyy-MM-dd")
    public List <NewsByDateResponse> newsByDate(@RequestParam String date) throws Exception {
            return newsService.newsByDate(date);
    }


}
