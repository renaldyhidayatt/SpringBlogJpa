package com.sanedge.simpleblog.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.ArticleRequest;
import com.sanedge.simpleblog.dto.response.ArticleResponse;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.exception.NotFoundException;
import com.sanedge.simpleblog.models.Article;
import com.sanedge.simpleblog.models.Category;
import com.sanedge.simpleblog.models.Tag;
import com.sanedge.simpleblog.models.User;
import com.sanedge.simpleblog.repository.ArticleRepository;
import com.sanedge.simpleblog.repository.CategoryRepository;
import com.sanedge.simpleblog.repository.TagRepository;
import com.sanedge.simpleblog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

        private ArticleRepository articleRepository;
        private TagRepository tagRepository;
        private CategoryRepository categoryRepository;

        @Autowired
        public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository,
                        CategoryRepository categoryRepository) {
                this.articleRepository = articleRepository;
                this.tagRepository = tagRepository;
                this.categoryRepository = categoryRepository;
        }

        @Override
        public Page<MessageResponse> findByCategory(String category, Pageable pageable) {
                Page<Article> articles = articleRepository.findByCategory(category, pageable);
                List<ArticleResponse> articleDtos = new ArrayList<>();
                for (Article article : articles) {
                        ArticleResponse dto = new ArticleResponse(article.getId(), article.getTitle(),
                                        article.getSlug(),
                                        article.getDescription(), article.getBody(), article.getCategories(),
                                        article.getTags(),
                                        article.getCreatedAt(), article.getUpdatedAt());
                        articleDtos.add(dto);
                }
                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data")
                                .data(articleDtos)
                                .statusCode(200).build();
                List<MessageResponse> messageList = new ArrayList<>();
                messageList.add(message);
                return new PageImpl<>(messageList, pageable, articles.getTotalElements());
        }

        @Override
        public Page<MessageResponse> findByCategorySlug(String slug, Pageable pageable) {
                Page<Article> articles = articleRepository.findByCategorySlug(slug, pageable);
                List<ArticleResponse> articleDtos = new ArrayList<>();
                for (Article article : articles) {
                        ArticleResponse dto = new ArticleResponse(article.getId(), article.getTitle(),
                                        article.getSlug(),
                                        article.getDescription(), article.getBody(), article.getCategories(),
                                        article.getTags(),
                                        article.getCreatedAt(), article.getUpdatedAt());
                        articleDtos.add(dto);
                }
                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data")
                                .data(articleDtos)
                                .statusCode(200).build();
                List<MessageResponse> messageList = new ArrayList<>();
                messageList.add(message);
                return new PageImpl<>(messageList, pageable, articles.getTotalElements());
        }

        @Override
        public Page<MessageResponse> findByTagSlug(String slug, Pageable pageable) {
                Page<Article> articles = articleRepository.findByTagSlug(slug, pageable);
                List<ArticleResponse> articleDtos = new ArrayList<>();
                for (Article article : articles) {
                        ArticleResponse dto = new ArticleResponse(article.getId(), article.getTitle(),
                                        article.getSlug(),
                                        article.getDescription(), article.getBody(), article.getCategories(),
                                        article.getTags(),
                                        article.getCreatedAt(), article.getUpdatedAt());
                        articleDtos.add(dto);
                }
                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data").data(articles)
                                .statusCode(200).build();
                List<MessageResponse> messageList = new ArrayList<>();
                messageList.add(message);
                return new PageImpl<>(messageList, pageable, articles.getTotalElements());
        }

        @Override
        public MessageResponse findByUser(User user) {
                Article article = articleRepository.findByUser(user);
                if (article == null)
                        return null;
                ArticleResponse dto = new ArticleResponse(article.getId(), article.getTitle(), article.getSlug(),
                                article.getDescription(), article.getBody(), article.getCategories(), article.getTags(),
                                article.getCreatedAt(), article.getUpdatedAt());

                return MessageResponse.builder().message("Berhasil mendapatkan data").data(dto).statusCode(200).build();
        }

        @Override
        public MessageResponse save(ArticleRequest request) {
                Article article = new Article();

                Tag tag = this.tagRepository.findById(request.getTagId())
                                .orElseThrow(() -> new NotFoundException("not found " + request.getTagId()));
                Category category = this.categoryRepository.findById(request.getCategoryId())
                                .orElseThrow(() -> new NotFoundException("not found " + request.getCategoryId()));

                article.setTitle(request.getTitle());
                article.setSlug(request.getSlug());
                article.setDescription(request.getDescription());
                article.setBody(request.getBody());

                article.setTags(Arrays.asList(tag));
                article.setCategories(Arrays.asList(category));

                this.articleRepository.save(article);

                return MessageResponse.builder().message("Berhasil mendapakan data").data(article).statusCode(201)
                                .build();
        }

        @Override
        public MessageResponse updateById(long id, ArticleRequest request) {
                Article article = this.articleRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("not found " + id));

                Tag tag = this.tagRepository.findById(request.getTagId())
                                .orElseThrow(() -> new NotFoundException("not found " + request.getTagId()));
                Category category = this.categoryRepository.findById(request.getCategoryId())
                                .orElseThrow(() -> new NotFoundException("not found " + request.getCategoryId()));

                article.setTitle(request.getTitle());
                article.setSlug(request.getSlug());
                article.setDescription(request.getDescription());
                article.setBody(request.getBody());

                article.setTags(Arrays.asList(tag));
                article.setCategories(Arrays.asList(category));

                this.articleRepository.save(article);

                return MessageResponse.builder().message("Berhasil mendapakan data").data(article).statusCode(201)
                                .build();

        }

        @Override
        public MessageResponse findById(Long id) {
                Article article = this.articleRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("not found " + id));

                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan data").data(article)
                                .statusCode(200).build();
                return message;
        }

        @Override
        public MessageResponse delete(long id) {
                Article article = this.articleRepository.findById(id)
                                .orElseThrow(() -> new NotFoundException("not found " + id));

                this.articleRepository.delete(article);
                MessageResponse message = MessageResponse.builder().message("Berhasil mendapatkan dekete").data(null)
                                .statusCode(200).build();
                return message;

        }

}
