package com.company.springBootTemplate.domain;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "template_message")
public class TemplateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private TemplateAuthor author;
    @Column(name = "author_id", insertable = false, updatable = false)
    private Long authorId;
    @Column(name="message_text")
    private String text;


    public TemplateMessage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TemplateAuthor getAuthor() {
        return author;
    }

    public void setAuthor(TemplateAuthor author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

