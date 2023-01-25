package com.sanedge.simpleblog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanedge.simpleblog.dto.request.CommentRequest;
import com.sanedge.simpleblog.dto.response.ArticleResponse;
import com.sanedge.simpleblog.dto.response.CommentResponse;
import com.sanedge.simpleblog.dto.response.MessageResponse;
import com.sanedge.simpleblog.dto.response.UserResponse;
import com.sanedge.simpleblog.exception.NotFoundException;
import com.sanedge.simpleblog.models.Article;
import com.sanedge.simpleblog.models.Comment;
import com.sanedge.simpleblog.models.User;
import com.sanedge.simpleblog.repository.ArticleRepository;
import com.sanedge.simpleblog.repository.CommentRepository;
import com.sanedge.simpleblog.repository.UserRepository;

@Service
public class CommentServiceImpl {
    private CommentRepository commentRepository;

    private ArticleRepository articleRepository;
    private UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository,
            UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public MessageResponse findAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setContent(comment.getContent());
            ArticleResponse articleResponse = new ArticleResponse();
            articleResponse.setId(comment.getArticle().getId());
            articleResponse.setTitle(comment.getArticle().getTitle());
            articleResponse.setSlug(comment.getArticle().getSlug());
            articleResponse.setDescription(comment.getArticle().getDescription());
            articleResponse.setBody(comment.getArticle().getBody());
            articleResponse.setCreatedAt(comment.getArticle().getCreatedAt());
            articleResponse.setUpdatedAt(comment.getArticle().getUpdatedAt());
            commentResponse.setArticle(articleResponse);

            UserResponse userResponse = new UserResponse();

            userResponse.setUsername(comment.getUsers().getUsername());
            userResponse.setEmail(comment.getUsers().getEmail());
            userResponse.setBio(comment.getUsers().getEmail());
            userResponse.setImage(comment.getUsers().getImage());

            commentResponse.setUser(userResponse);
            commentResponses.add(commentResponse);
        }
        return MessageResponse.builder().message("Berhasil mendapatkan data").data(commentResponses).statusCode(200)
                .build();
    }

    public MessageResponse findById(Long id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment by id"));

        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        ArticleResponse articleResponse = new ArticleResponse();
        articleResponse.setId(comment.getArticle().getId());
        articleResponse.setTitle(comment.getArticle().getTitle());
        articleResponse.setSlug(comment.getArticle().getSlug());
        articleResponse.setDescription(comment.getArticle().getDescription());
        articleResponse.setBody(comment.getArticle().getBody());
        articleResponse.setCreatedAt(comment.getArticle().getCreatedAt());
        articleResponse.setUpdatedAt(comment.getArticle().getUpdatedAt());

        commentResponse.setArticle(articleResponse);

        UserResponse userResponse = new UserResponse();

        userResponse.setUsername(comment.getUsers().getUsername());
        userResponse.setEmail(comment.getUsers().getEmail());
        userResponse.setBio(comment.getUsers().getEmail());
        userResponse.setImage(comment.getUsers().getImage());

        commentResponse.setUser(userResponse);
        return MessageResponse.builder().message("Berhasil mendapatkan data").data(commentResponse).statusCode(200)
                .build();

    }

    public MessageResponse create(CommentRequest commentRequest) {
        Comment comment = new Comment();
        User user = this.userRepository.findById(commentRequest.getUser_id())
                .orElseThrow(() -> new NotFoundException("Not found user by id"));
        Article article = this.articleRepository.findById(commentRequest.getArticle_id())
                .orElseThrow(() -> new NotFoundException("Not found article by id"));

        comment.setContent(commentRequest.getContent());
        comment.setUsers(user);
        comment.setArticle(article);

        this.commentRepository.save(comment);

        return MessageResponse.builder().message("Berhasil membuat comment").data(comment).statusCode(200).build();
    }

    public MessageResponse update(Long id, CommentRequest commentRequest) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment by id"));

        User user = this.userRepository.findById(commentRequest.getUser_id())
                .orElseThrow(() -> new NotFoundException("Not found user by id"));
        Article article = this.articleRepository.findById(commentRequest.getArticle_id())
                .orElseThrow(() -> new NotFoundException("Not found article by id"));

        comment.setContent(commentRequest.getContent());
        comment.setUsers(user);
        comment.setArticle(article);

        this.commentRepository.save(comment);

        return MessageResponse.builder().message("Berhasil update comment").data(comment).statusCode(200).build();
    }

    public MessageResponse deleteById(Long id) {
        Comment comment = this.commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found comment by id"));
        this.commentRepository.delete(comment);

        return MessageResponse.builder().message("Berhasil update comment").data(null).statusCode(200).build();

    }
}
