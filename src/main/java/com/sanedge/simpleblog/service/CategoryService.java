package com.sanedge.simpleblog.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.sanedge.simpleblog.models.Category;

public interface CategoryService {
    Page<Category> getAllCategoriesPaged(Pageable req);

    Category getByName(String name);

    List<Category> getArticlesCountCategorizedAs(String categoryName);

    Collection<Category[]> fetchNameAndSlug();

    Set<Category> fetchCategoriesFromArticleId(Long id);

    void create(Category category);

    void update(Category category);

    void delete(Category category);

    void deleteById(Long id);
}
