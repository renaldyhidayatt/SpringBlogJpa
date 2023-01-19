package com.sanedge.simpleblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.response.ArticleResponse;
import com.sanedge.simpleblog.dto.response.CategoryResponse;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.models.Category;
import com.sanedge.simpleblog.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<MessageResponse> findAll(Pageable pageable) {
        Page<Category> categories = this.categoryRepository.findAll(pageable);
        List<MessageResponse> messageResponses = new ArrayList<>();
        for (Category category : categories) {
            CategoryResponse dto = new CategoryResponse();
            dto.setName(category.getName());
            dto.setSlug(category.getSlug());
            dto.setDescription(category.getDescription());
            dto.setArticles(category.getArticles());

            MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data").data(dto)
                    .statusCode(200).build();
            messageResponses.add(message);
        }
        return new PageImpl<>(messageResponses, pageable, categories.getTotalElements());
    }

}
