package com.sanedge.simpleblog.service;

import java.util.Collection;
import java.util.List;

import com.sanedge.simpleblog.dto.request.TagRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;

public interface TagService {
    MessageResponse save(TagRequest tag);

    MessageResponse findById(Long id);

    MessageResponse deleteById(Long id);

    List<MessageResponse> getArticlesCountTaggedWith(String tagName);

    List<MessageResponse> findAll();

    List<MessageResponse> fetchTagFromArticles(List<Long> ids);

    Collection<MessageResponse> fetchNameAndSlug();

    List<MessageResponse> fetchTagsFromArticleId(Long id);
}
