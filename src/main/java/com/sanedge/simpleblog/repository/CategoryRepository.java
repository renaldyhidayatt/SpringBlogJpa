package com.sanedge.simpleblog.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sanedge.simpleblog.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAll(Pageable req);

    Category findByName(String name);

    @Query("select c.name, count(a) from Article a join a.categories c where c.name=:name group by c.name")
    public List<Category> getArticlesCountCategorisedAs(@Param("name") String categoryName);

    @Query("select c.id, c.name, c.slug from Category c")
    Collection<Category[]> fetchNameAndSlug();

    @Query("select c from Category c inner join c.articles a where a.id = :id")
    Set<Category> fetchCategoriesFromArticleId(Long id);

}
