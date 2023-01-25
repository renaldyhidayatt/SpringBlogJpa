package com.sanedge.simpleblog.service;

import com.sanedge.simpleblog.dto.request.CommentRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;

public interface CommentService {
    public MessageResponse findAll();

    public MessageResponse findById(Long id);

    public MessageResponse create(CommentRequest commentRequest);

    public MessageResponse update(Long id, CommentRequest commentRequest);

    public MessageResponse deleteById(Long id);
}
