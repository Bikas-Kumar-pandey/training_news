package com.news.repository;

import com.news.model.Articles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Articles,Integer> {
}
