package com.sanedge.simpleblog.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.simpleblog.dto.request.TagRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.service.impl.TagServiceImpl;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImpl tagServiceImpl;

    @Autowired
    public TagController(TagServiceImpl tagServiceImpl) {
        this.tagServiceImpl = tagServiceImpl;
    }

    @PostMapping("/save")
    public ResponseEntity<MessageResponse> save(@RequestBody TagRequest tag) {
        MessageResponse response = tagServiceImpl.save(tag);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<MessageResponse> findById(@PathVariable Long id) {
        MessageResponse response = tagServiceImpl.findById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MessageResponse> deleteById(@PathVariable Long id) {
        MessageResponse response = tagServiceImpl.deleteById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/articlesCountTaggedWith/{tagName}")
    public ResponseEntity<List<MessageResponse>> getArticlesCountTaggedWith(@PathVariable String tagName) {
        List<MessageResponse> response = tagServiceImpl.getArticlesCountTaggedWith(tagName);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.get(0).getStatusCode()));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<MessageResponse>> findAll() {
        List<MessageResponse> response = tagServiceImpl.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.get(0).getStatusCode()));
    }

    @PostMapping("/fetchTagFromArticles")
    public ResponseEntity<List<MessageResponse>> fetchTagFromArticles(@RequestBody List<Long> ids) {
        List<MessageResponse> response = tagServiceImpl.fetchTagFromArticles(ids);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.get(0).getStatusCode()));
    }

    @GetMapping("/fetchNameAndSlug")
    public ResponseEntity<Collection<MessageResponse>> fetchNameAndSlug() {
        Collection<MessageResponse> response = tagServiceImpl.fetchNameAndSlug();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.iterator().next().getStatusCode()));
    }

    @GetMapping("/fetchTagsFromArticleId/{id}")
    public ResponseEntity<List<MessageResponse>> fetchTagsFromArticleId(@PathVariable Long id) {
        List<MessageResponse> response = tagServiceImpl.fetchTagsFromArticleId(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.get(0).getStatusCode()));
    }

}
