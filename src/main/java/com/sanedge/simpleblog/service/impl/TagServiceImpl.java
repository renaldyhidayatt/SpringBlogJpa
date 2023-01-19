package com.sanedge.simpleblog.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.TagRequest;
import com.sanedge.simpleblog.dto.response.TagResponse;
import com.sanedge.simpleblog.models.Tag;
import com.sanedge.simpleblog.repository.TagRepository;
import com.sanedge.simpleblog.service.TagService;

@Service
public class TagServiceImpl implements TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public TagResponse save(TagRequest tag) {
        Tag tagModel = new Tag();
        tagModel.setName(tag.getName());
        tagModel.setSlug(tag.getSlug());
        tagModel.setDescription(tag.getDescription());

        tagModel = this.tagRepository.save(tagModel);

        TagResponse tagResponse = new TagResponse();

        tagResponse.setName(tagModel.getName());
        tagResponse.setSlug(tagModel.getSlug());
        tagResponse.setDescription(tagModel.getDescription());
        tagResponse.setCreatedAt(tagModel.getCreatedAt());
        tagResponse.setUpdatedAt(tagModel.getUpdatedAt());

        return tagResponse;

    }

    @Override
    public TagResponse findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<TagResponse> getArticlesCountTaggedWith(String tagName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TagResponse> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<TagResponse> fetchTagFromArticles(List<Long> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<TagResponse> fetchNameAndSlug() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<TagResponse> fetchTagsFromArticleId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
