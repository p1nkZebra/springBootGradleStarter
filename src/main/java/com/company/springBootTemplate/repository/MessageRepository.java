package com.company.springBootTemplate.repository;

import com.company.springBootTemplate.domain.TemplateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<TemplateMessage, Long> {

    @Query(value = "select * from template_message tm where tm.author_id = :authorId", nativeQuery = true)
    List<TemplateMessage> findByAuthorId(@Param("authorId") Long id);

    @Query(value = "select tm from TemplateMessage tm where tm.author.name = :name")
    List<TemplateMessage> findByAuthorName(@Param("name") String authorName);
}

