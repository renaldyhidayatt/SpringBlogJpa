package com.sanedge.simpleblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sanedge.simpleblog.models.Article;
import com.sanedge.simpleblog.models.User;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a INNER JOIN a.categories c WHERE c.name=:category")
    Page<Article> findByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT a FROM Article a inner join a.categories c WHERE c.slug = :slug")
    Page<Article> findByCategorySlug(String slug, Pageable pageable);

    @Query("SELECT a FROM Article a inner join a.tags t WHERE t.slug = :slug")
    Page<Article> findByTagSlug(@Param("slug") String slug, Pageable pageable);

    Article findByUser(User user);

}
