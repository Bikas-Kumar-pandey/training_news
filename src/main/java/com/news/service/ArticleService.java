package com.news.service;

import com.news.dto.ArticleResponse;
import com.news.dto.ArticlesRequest;
import com.news.model.Articles;
import com.news.model.Category;
import com.news.repository.ArticleRepository;
import com.news.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final CategoryRepository categoryRepository;
    private final ArticleRepository articleRepository;

    public ArticleService(CategoryRepository categoryRepository, ArticleRepository articleRepository) {
        this.categoryRepository = categoryRepository;
        this.articleRepository = articleRepository;
    }

    public Category setArticles(int id, List<ArticlesRequest> request) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Category doesn't present");
        }
        Category category = byId.get();

        List<Articles> articlesList = new ArrayList<>();

        for (ArticlesRequest request1 : request) {
            Articles articles = new Articles();
            articles.setTitles(request1.getTitles());
            articles.setDescription(request1.getDescription());
            articlesList.add(articles);
        }
        category.setArticles(articlesList);
        Category save = categoryRepository.save(category);
        return save;
    }

    public List<ArticleResponse> getArticle() {
        Category category = new Category();
        List<ArticleResponse> responseList = new ArrayList<>();
        List<Category> all = categoryRepository.findAll();
        for (Category articles1 : all) {
            List<Articles> articles = articles1.getArticles();
            for (Articles articles2 : articles) {
                ArticleResponse response = new ArticleResponse();
                response.setCategory(articles1.getCategories());
                response.setTitle(articles2.getTitles());
                response.setTitle(articles2.getTitles());
                response.setDescription(articles2.getDescription());
                response.setDate(articles2.getDate());
                response.setArticleId(articles2.getId());
                responseList.add(response);
            }
        }
        return responseList;
    }

    public List<ArticleResponse> getArticlesBycategory(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isEmpty()){
            throw new RuntimeException("Category doesn't present by given id");
        }
        Category category = byId.get();
        List<Articles> articles = category.getArticles();
        List<ArticleResponse> articleResponseList = new ArrayList<>();
        for (Articles articles1:articles){
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setDescription(articles1.getDescription());
            articleResponse.setTitle(articles1.getTitles());
            articleResponse.setDate(articles1.getDate());
            articleResponse.setCategory(category.getCategories());
            articleResponseList.add(articleResponse);
        }
        return articleResponseList;
    }

    public String deleteArticleById(int id) throws Exception {
        Optional<Articles> byId = articleRepository.findById(id);
        if (byId.isEmpty()){
            throw new Exception("Article doesnot present by given id: "+id);
        }
        articleRepository.deleteById(id);
        return "Article deleted";
    }

    public String updateArticle(int id, ArticlesRequest request) throws Exception {
        Optional<Articles> byId = articleRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("Aricle does not present by given id :"+id);
        }
        Articles articles = byId.get();

        articles.setTitles(request.getTitles());
        articles.setDescription(request.getDescription());

        Articles save = articleRepository.save(articles);


        return "Updated articles" +save;
    }
}
