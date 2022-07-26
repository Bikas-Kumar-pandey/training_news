package com.news.service;

import com.news.dto.*;
import com.news.model.Articles;
import com.news.model.Category;
import com.news.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    public final CategoryRepository categoryRepository;

    public NewsService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<NewsResponse> setNews(NewsRequest newsRequest) {
        Category category = new Category();
        List<Articles> articlesList = new ArrayList<>();
        List<ArticlesRequest> articles = newsRequest.getArticles();
        for (ArticlesRequest articlesRequest : articles) {
            Articles articles1 = new Articles();
            articles1.setTitles(articlesRequest.getTitles());
            articles1.setDescription(articlesRequest.getDescription());
            articlesList.add(articles1);
        }
        category.setArticles(articlesList);
        category.setCategories(newsRequest.getCategory());
        Category save = categoryRepository.save(category);
        List<NewsResponse> newsResponseList = new ArrayList<>();
        List<Articles> articles1 = save.getArticles();
        for (Articles articles2 : articles1) {
            NewsResponse response = new NewsResponse();
            response.setCategory(save.getCategories());
            response.setTitles(articles2.getTitles());
            response.setDescription(articles2.getDescription());
            response.setDate(articles2.getDate());
            response.setId(category.getId());
            newsResponseList.add(response);
        }
        return newsResponseList;
    }

    public List<NewsResponse> getNews(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("News with given id doesn't Exists !");
        }
        Category category = byId.get();
        List<NewsResponse> newsResponseList = new ArrayList<>();
        List<Articles> articles = category.getArticles();
        for (Articles articles1 : articles) {
            NewsResponse response = new NewsResponse();
            response.setId(articles1.getId());
            response.setCategory(category.getCategories());
            response.setTitles(articles1.getTitles());
            response.setDescription(articles1.getDescription());
            response.setDate(articles1.getDate());
            newsResponseList.add(response);
        }
        return newsResponseList;
    }







//    public Category updateNews(int id, NewsRequest request) {
//        Optional<Category> byId = categoryRepository.findById(id);
//        if(!byId.isPresent()){
//            throw new RuntimeException("News with given id doesn't Exists !");
//        }
//        Category category = byId.get();
//        if(!category.getCategories().equals(request.getCategory())){
//            throw new RuntimeException("Category miss match");
//        }
//        List<Articles> articlesList = new ArrayList<>();
////        category.getArticles().retainAll(articlesList);
//
////        List<Articles> articles1 = category.getArticles();
//
//        List<ArticlesRequest> articles = request.getArticles();
//         for(ArticlesRequest articlesRequest :articles){
//                Articles articleEntity = new Articles();
//                articleEntity.setTitles(articlesRequest.getTitles());
//                articleEntity.setDescription(articlesRequest.getDescription());
//                articlesList.add(articleEntity);
//        }
//        category.setArticles(articlesList);
//        category.setCategories(request.getCategory());
//        return categoryRepository.save(category);
//    }
//    public String deleteNews(int id) {
//       categoryRepository.deleteById(id);
//       return "News deleted by given id "+id;
//    }



    public List<NewsResponse> updateNews(int id, NewsRequest request) {
        Optional<Category> byId = categoryRepository.findById(id);
        if(!byId.isPresent()){
            throw new RuntimeException("News with given id doesn't Exists !");
        }
        Category category = byId.get();
        if(!category.getCategories().equals(request.getCategory())){
            throw new RuntimeException("Category miss match");
        }
        List<Articles> articlesList = new ArrayList<>();
        List<Articles> articles1 = category.getArticles();
        List<ArticlesRequest> articles = request.getArticles();


        for (int i = 0; i < articles1.size(); i ++){
            String title = articles.get(i).getTitles();
            String description = articles.get(i).getDescription();
            articles1.get(i).setTitles(title);
            articles1.get(i).setDescription(description);
        }


        Category save = categoryRepository.save(category);

        List<Articles> articles2 = save.getArticles();
        List<NewsResponse> responseList = new ArrayList<>();
        for (Articles values :articles2){
            NewsResponse response = new NewsResponse();
            response.setCategory(save.getCategories());
            response.setTitles(values.getTitles());
            response.setDescription(values.getDescription());
            response.setDate(save.getDate());
            response.setId(response.getId());
            responseList.add(response);
        }
        return responseList;
    }


    public String deleteNews(int id) {
        categoryRepository.deleteById(id);
        return "News deleted by given id "+id;
    }



    public List <NewsByDateResponse> newsByDate( String date ) throws Exception {
        List<Category> byDate = categoryRepository.findAllByDate(date);
        if(byDate==null){
            throw new Exception("News for this date not present");
        }
        NewsByDateResponse dateResponse = new NewsByDateResponse();
        List <NewsByDateResponse> newsList = new ArrayList<>() ;
        for (Category bydates:byDate) {
            dateResponse.setDate(bydates.getDate());
            dateResponse.setCategories(bydates.getCategories());
            newsList.add(dateResponse);
        }
        return  newsList;
    }


//    public List<NewsResponse> getAllNews() {
//        List<Category> all = categoryRepository.findAll();
//        List<NewsResponse> responseList = new ArrayList<>();
//
//        for (Category values :all){
//            List<Articles> articles = values.getArticles();
//            NewsResponse response = new NewsResponse();
//            response.setCategory(values.getCategories());
//            response.setDate(values.getDate());
//            response.setId(values.getId());
//
//           for (Articles valuess:articles){
//              response.setTitles(valuess.getTitles());
//              response.setDescription(valuess.getDescription());
//
//            }
//            responseList.add(response);
//
//        }
//        return responseList;
//    }



}//END
