package com.company.springBootTemplate.repository;


import com.company.springBootTemplate.domain.TemplateAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<TemplateAuthor, Long> {

    @Query(value = "select a from TemplateAuthor as a where a.name = :name")
    List<TemplateAuthor> findByName(@Param("name") String authorName);
}

