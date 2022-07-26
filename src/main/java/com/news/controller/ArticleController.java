package com.news.controller;

import com.news.dto.ArticleResponse;
import com.news.dto.ArticlesRequest;
import com.news.dto.CategoryResponse;
import com.news.model.Category;
import com.news.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {this.articleService = articleService; }



@PostMapping("/article/{id}")
public Category setArticles(@PathVariable int id, @RequestBody List<ArticlesRequest> request){
    return articleService.setArticles(id,request);
}

@GetMapping("/article")
public List<ArticleResponse> getArticle(){
      return   articleService.getArticle();
}
    @GetMapping("/all-articlesBy-category/{id}")
    public List<ArticleResponse>  getCategories(@PathVariable int id) throws Exception {
        return articleService.getArticlesBycategory(id);
    }

    @PutMapping("/article/{id}")
    public String updateArticle(@PathVariable int id,@RequestBody ArticlesRequest request) throws Exception {
        return articleService.updateArticle(id,request);
    }


@DeleteMapping("article/{id}")
    public String deleteArticleById(@PathVariable int id) throws Exception {
       return articleService.deleteArticleById(id);
}


}
