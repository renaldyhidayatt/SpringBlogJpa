package com.sanedge.simpleblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanedge.simpleblog.dto.request.ArticleRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.models.User;

public interface ArticleService {
    Page<MessageResponse> findByCategory(String category, Pageable pageable);

    Page<MessageResponse> findByCategorySlug(String slug, Pageable pageable);

    Page<MessageResponse> findByTagSlug(String slug, Pageable pageable);

    MessageResponse findByUser(User user);

    MessageResponse save(ArticleRequest article);

    MessageResponse updateById(long id, ArticleRequest article);

    MessageResponse findById(Long id);

    MessageResponse delete(long id);
}
