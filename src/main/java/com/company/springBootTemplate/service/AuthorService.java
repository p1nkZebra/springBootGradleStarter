package com.company.springBootTemplate.service;


import com.company.springBootTemplate.domain.TemplateAuthor;
import com.company.springBootTemplate.domain.TemplateMessage;
import com.company.springBootTemplate.infrastructure.exception.NotFoundException;
import com.company.springBootTemplate.repository.AuthorRepository;
import com.company.springBootTemplate.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorService {
    private final static Logger LOG = LoggerFactory.getLogger(AuthorService.class);

    private final AuthorRepository authorRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository,
                         MessageRepository messageRepository) {
        this.authorRepository = authorRepository;
        this.messageRepository = messageRepository;
    }


    public List<TemplateAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    public TemplateAuthor getAuthorInfo(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %s not found", id)));
    }

    public List<TemplateAuthor> getAuthorsByName(String name) {
        return authorRepository.findByName(name);
    }

    @Transactional
    public TemplateAuthor saveNewAuthor(TemplateAuthor author) {
        return authorRepository.save(author);
    }


    public void updateAuthorInfo(Long id,
                                 TemplateAuthor author) {
        TemplateAuthor updatedAuthor = authorRepository.findById(id)
                .map(item -> {
                    if(!StringUtils.isEmpty(author.getName())) {
                        item.setName(author.getName());
                    }
                    if(!StringUtils.isEmpty(author.getAge())) {
                        item.setAge(author.getAge());
                    }
                    return item;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Author with id: %s not found", id)));

        authorRepository.save(updatedAuthor);
    }


    public void deleteAuthor(Long id) {

        List<TemplateMessage> messageList = messageRepository.findByAuthorId(id);
        if (!messageList.isEmpty()) {
            messageRepository.deleteAll(messageList);
        }
        authorRepository.deleteById(id);
    }
}

