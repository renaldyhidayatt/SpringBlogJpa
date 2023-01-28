package com.sanedge.simpleblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.CategoryRequest;
import com.sanedge.simpleblog.dto.response.CategoryResponse;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.exception.NotFoundException;
import com.sanedge.simpleblog.models.Category;
import com.sanedge.simpleblog.repository.CategoryRepository;
import com.sanedge.simpleblog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

        private CategoryRepository categoryRepository;

        @Autowired
        public CategoryServiceImpl(CategoryRepository categoryRepository) {
                this.categoryRepository = categoryRepository;
        }

        public Page<MessageResponse> findAll(Pageable pageable) {
                Page<Category> categories = this.categoryRepository.findAll(pageable);
                List<CategoryResponse> categoryResponses = new ArrayList<>();
                for (Category category : categories) {
                        CategoryResponse dto = new CategoryResponse(category.getId(), category.getName(),
                                        category.getSlug(),
                                        category.getDescription(), category.getArticles(), category.getCreatedAt(),
                                        category.getUpdatedAt());
                        categoryResponses.add(dto);
                }
                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data")
                                .data(categoryResponses)
                                .statusCode(200).build();
                List<MessageResponse> messageList = new ArrayList<>();
                messageList.add(message);
                return new PageImpl<>(messageList, pageable, categories.getTotalElements());
        }

        public MessageResponse findByName(String name) {
                Category category = this.categoryRepository.findByName(name);

                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data").data(category)
                                .statusCode(200).build();

                return message;
        }

        @Override
        public MessageResponse create(CategoryRequest categoryRequest) {
                Category category = new Category();

                category.setName(categoryRequest.getName());
                category.setSlug(categoryRequest.getSlug());
                category.setDescription(categoryRequest.getDescription());

                this.categoryRepository.save(category);

                CategoryResponse categoryResponse = CategoryResponse.builder().id(category.getId())
                                .name(category.getName())
                                .slug(category.getSlug()).description(category.getDescription())
                                .createdAt(category.getCreatedAt())
                                .updatedAt(category.getUpdatedAt()).build();
                return MessageResponse.builder().message("Berhasil mendapatkan membuat data").data(categoryResponse)
                                .statusCode(200).build();
        }

        @Override
        public MessageResponse update(Long id, CategoryRequest categoryRequest) {
                Category category = this.categoryRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("No found category id"));

                category.setName(categoryRequest.getName());
                category.setSlug(categoryRequest.getSlug());
                category.setDescription(categoryRequest.getDescription());

                this.categoryRepository.save(category);

                CategoryResponse categoryResponse = CategoryResponse.builder().id(category.getId())
                                .name(category.getName())
                                .slug(category.getSlug()).description(category.getDescription())
                                .createdAt(category.getCreatedAt())
                                .updatedAt(category.getUpdatedAt()).build();
                return MessageResponse.builder().message("Berhasil mendapatkan update data").data(categoryResponse)
                                .statusCode(200).build();

        }

        @Override
        public MessageResponse delete(Long id) {
                Category category = this.categoryRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("No found category id"));

                this.categoryRepository.delete(category);

                return MessageResponse.builder().message("Berhasil menghapus data").data(null).statusCode(200).build();
        }

        @Override
        public MessageResponse findById(Long id) {
                Category category = this.categoryRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("No found category id"));
                CategoryResponse categoryResponse = CategoryResponse.builder().id(category.getId())
                                .name(category.getName())
                                .slug(category.getSlug()).description(category.getDescription())
                                .createdAt(category.getCreatedAt())
                                .updatedAt(category.getUpdatedAt()).build();
                return MessageResponse.builder().message("Berhasil mendapatkan  data").data(categoryResponse)
                                .statusCode(200).build();

        }

}
