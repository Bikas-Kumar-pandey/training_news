package com.news.repository;

import com.news.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

@Query(value = "SELECT * from categories where date =:date", nativeQuery = true)
    List<Category> findAllByDate(String date);

//    List<Category> getAllNews();

//    Category getAllNews(int id);
}
