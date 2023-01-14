package com.sanedge.simpleblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.simpleblog.models.Article;

@Repository
public interface ArticleRepo extends JpaRepository<Article, Long> {

}
