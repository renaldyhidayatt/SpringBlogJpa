package com.sanedge.simpleblog.repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sanedge.simpleblog.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    @Query("SELECT t.name, count(a) FROM Article a INNER JOIN a.tags t WHERE t.name = :name GROUP BY t.id")
    public List<Tag> getArticlesCountTaggedWith(@Param("name") String tagName);

    @Query("SELECT t.name, count(t.name) FROM Tag t INNER JOIN t.articles a GROUP BY t.name")
    public List<Tag> getAll();

    @Query("SELECT t FROM Tag t INNER JOIN t.articles a WHERE a.id in :ids")
    List<Tag> fetchTagFromArticles(List<Long> ids);

    @Query("SELECT t FROM Tag t")
    Collection<Tag> fetchNameAndSlug();

    @Query("select t from Tag t inner join t.articles a where a.id = :id")
    Set<Tag> fetchTagsFromArticleId(Long id);
}
