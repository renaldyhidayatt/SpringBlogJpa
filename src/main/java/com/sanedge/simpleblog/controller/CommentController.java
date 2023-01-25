package com.sanedge.simpleblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanedge.simpleblog.dto.request.CommentRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.service.impl.CommentServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class CommentController {
    private final CommentServiceImpl commentServiceImpl;

    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @GetMapping
    public MessageResponse findAll() {
        return this.commentServiceImpl.findAll();
    }

    @PostMapping
    public MessageResponse findById(@PathVariable Long id) {
        return this.commentServiceImpl.findById(id);
    }

    @PostMapping
    public MessageResponse create(@RequestBody CommentRequest commentRequest) {
        return commentServiceImpl.create(commentRequest);
    }

    @PutMapping("/{id}")
    public MessageResponse update(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        return commentServiceImpl.update(id, commentRequest);
    }

    @DeleteMapping("/{id}")
    public MessageResponse delete(@PathVariable Long id) {
        return commentServiceImpl.deleteById(id);
    }

}
