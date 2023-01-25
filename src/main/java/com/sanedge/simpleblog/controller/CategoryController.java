package com.sanedge.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.simpleblog.dto.request.CategoryRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<MessageResponse> findAll(Pageable pageable) {
        return categoryService.findAll(pageable);
    }

    @GetMapping("/name/{name}")
    public MessageResponse findByName(@PathVariable String name) {
        return categoryService.findByName(name);
    }

    @PostMapping
    public MessageResponse create(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.create(categoryRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse update(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.update(id, categoryRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }

    @GetMapping("/{id}")
    public MessageResponse findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}
