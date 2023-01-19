package com.sanedge.simpleblog.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.sanedge.simpleblog.dto.request.TagRequest;
import com.sanedge.simpleblog.dto.response.TagResponse;

public interface TagService {
    TagResponse save(TagRequest tag);

    TagResponse findById(Long id);

    void deleteById(Long id);

    List<TagResponse> getArticlesCountTaggedWith(String tagName);

    List<TagResponse> findAll();

    List<TagResponse> fetchTagFromArticles(List<Long> ids);

    Collection<TagResponse> fetchNameAndSlug();

    Set<TagResponse> fetchTagsFromArticleId(Long id);
}
