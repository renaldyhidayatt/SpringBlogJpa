package com.sanedge.simpleblog.seed;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sanedge.simpleblog.models.Article;
import com.sanedge.simpleblog.models.Category;
import com.sanedge.simpleblog.models.Comment;
import com.sanedge.simpleblog.models.Tag;
import com.sanedge.simpleblog.models.User;
import com.sanedge.simpleblog.repository.ArticleRepository;
import com.sanedge.simpleblog.repository.CategoryRepository;
import com.sanedge.simpleblog.repository.CommentRepository;
import com.sanedge.simpleblog.repository.TagRepository;
import com.sanedge.simpleblog.repository.UserRepository;

@Profile("seeds")
@Component
public class MySeed implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public MySeed(CategoryRepository categoryRepository, TagRepository tagRepository,
            CommentRepository commentRepository, ArticleRepository articleRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedUsers();
        this.seedCategory();
        this.seedTag();
        this.seedArticles();
        this.seedComments();
    }

    private void seedUsers() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("Dota " + i);
            user.setEmail("email@gmail" + i + ".com");
            user.setPassword(this.passwordEncoder.encode("Password " + i));
        }
    }

    private void seedCategory() {
        for (int i = 0; i < 10; i++) {
            Category categoryRequest = new Category();

            categoryRequest.setName("Fruits " + i);
            categoryRequest.setSlug("fruits " + i);
            categoryRequest.setDescription("A category for all types of fruits" + i);

            this.categoryRepository.save(categoryRequest);

        }
    }

    private void seedTag() {

        for (int i = 0; i < 10; i++) {
            Tag tag = new Tag();
            tag.setName("Fruits" + i);
            tag.setSlug("SLug" + i);
            tag.setDescription("Description " + i);

            this.tagRepository.save(tag);

        }
    }

    public void seedArticles() {
        List<Tag> tags = this.tagRepository.findAll();
        List<Category> categories = this.categoryRepository.findAll();
        for (int i = 0; i < 10; i++) {
            Article article = new Article();
            article.setTitle("Sample Article " + i);
            article.setSlug("sample-article-" + i);
            article.setDescription("This is a sample article " + i);
            article.setBody(
                    "Lorem Ipsum is simply dummy text "
                            + i);
            article.setTags(Arrays.asList(tags.get(i % tags.size())));
            article.setCategories(Arrays.asList(categories.get(i % categories.size())));
            this.articleRepository.save(article);
        }
    }

    public void seedComments() {
        List<User> users = this.userRepository.findAll();
        List<Article> articles = this.articleRepository.findAll();

        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();

            comment.setContent("This is a sample comment " + i);
            comment.setUsers((User) Arrays.asList(users.get(i % users.size())));
            comment.setArticle((Article) Arrays.asList(articles.get(i % articles.size())));

            this.commentRepository.save(comment);
        }
    }

}
