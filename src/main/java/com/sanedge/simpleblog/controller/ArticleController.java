package com.sanedge.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.simpleblog.dto.request.ArticleRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.models.User;
import com.sanedge.simpleblog.service.impl.ArticleServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    ArticleServiceImpl articleServiceImpl;

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<MessageResponse>> findByCategory(@PathVariable String category,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageResponse> articles = articleServiceImpl.findByCategory(category, pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/category/{slug}")
    public ResponseEntity<Page<MessageResponse>> findByCategorySlug(@PathVariable String slug,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageResponse> articles = articleServiceImpl.findByCategorySlug(slug, pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/tag/{slug}")
    public ResponseEntity<Page<MessageResponse>> findByTagSlug(@PathVariable String slug,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageResponse> articles = articleServiceImpl.findByTagSlug(slug, pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<MessageResponse> findByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        MessageResponse result = articleServiceImpl.findByUser(user);
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> save(@RequestBody ArticleRequest request) {
        MessageResponse result = articleServiceImpl.save(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updateById(@PathVariable Long id, @RequestBody ArticleRequest request) {
        MessageResponse result = articleServiceImpl.updateById(id, request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        MessageResponse result = articleServiceImpl.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable Long id) {
        MessageResponse result = articleServiceImpl.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
