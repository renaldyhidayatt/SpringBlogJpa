package com.sanedge.simpleblog.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.TagRequest;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.dto.response.TagResponse;
import com.sanedge.simpleblog.exception.NotFoundException;
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
    public MessageResponse save(TagRequest tag) {
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

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(tagResponse).statusCode(200).build();

    }

    @Override
    public MessageResponse findById(Long id) {
        Tag tag = this.tagRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(tag).statusCode(200).build();
    }

    @Override
    public MessageResponse deleteById(Long id) {
        Tag tag = this.tagRepository.findById(id).orElseThrow(() -> new NotFoundException("not found"));

        return MessageResponse.builder().message("Berhasil mendapatkan data").data(tag).statusCode(200).build();
    }

    @Override
    public List<MessageResponse> getArticlesCountTaggedWith(String tagName) {
        List<Tag> tags = this.tagRepository.getArticlesCountTaggedWith(tagName);
        MessageResponse messageResponse = MessageResponse.builder().message("Berhasil mendapatkan data").data(tags)
                .statusCode(200).build();

        return Collections.singletonList(messageResponse);
    }

    @Override
    public List<MessageResponse> findAll() {
        List<Tag> tags = this.tagRepository.findAll();
        MessageResponse messageResponse = MessageResponse.builder().message("Berhasil mendapatkan data").data(tags)
                .statusCode(200).build();

        return Collections.singletonList(messageResponse);
    }

    @Override
    public List<MessageResponse> fetchTagFromArticles(List<Long> ids) {
        List<Tag> tags = this.tagRepository.fetchTagFromArticles(ids);
        MessageResponse messageResponse = MessageResponse.builder().message("Berhasil mendapatkan data").data(tags)
                .statusCode(200)
                .build();

        return Collections.singletonList(messageResponse);
    }

    @Override
    public Collection<MessageResponse> fetchNameAndSlug() {
        Collection<Tag> tags = this.tagRepository.fetchNameAndSlug();
        MessageResponse messageResponse = MessageResponse.builder().message("Berhasil mendapatkan data").data(tags)
                .statusCode(200).build();

        return Collections.singletonList(messageResponse);
    }

    @Override
    public List<MessageResponse> fetchTagsFromArticleId(Long id) {
        Set<Tag> tags = this.tagRepository.fetchTagsFromArticleId(id);
        MessageResponse messageResponse = MessageResponse.builder().message("Berhasil mendapatkan data").data(tags)
                .statusCode(200).build();

        return Collections.singletonList(messageResponse);
    }

}
