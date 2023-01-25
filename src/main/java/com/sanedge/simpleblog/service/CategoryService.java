package com.sanedge.simpleblog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sanedge.simpleblog.dto.request.CategoryRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;

public interface CategoryService {
    Page<MessageResponse> findAll(Pageable pageable);

    MessageResponse findByName(String name);

    MessageResponse create(CategoryRequest category);

    MessageResponse update(Long id, CategoryRequest category);

    MessageResponse delete(Long id);

    MessageResponse findById(Long id);
}
