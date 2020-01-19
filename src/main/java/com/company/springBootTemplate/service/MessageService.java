package com.company.springBootTemplate.service;


import com.company.springBootTemplate.domain.TemplateMessage;
import com.company.springBootTemplate.infrastructure.exception.NotFoundException;
import com.company.springBootTemplate.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MessageService {
    private final static Logger LOG = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    public List<TemplateMessage> getAllMessages() {
        return messageRepository.findAll();
    }

    public List<TemplateMessage> getMessagesForAuthor(String authorName) {
        return messageRepository.findByAuthorName(authorName);
    }

    public TemplateMessage getMessage(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Message with id: %s not found", id)));
    }


    public TemplateMessage saveNewMessage(TemplateMessage message) {
        return messageRepository.save(message);
    }


    public void updateMessage(Long id,
                              TemplateMessage message) {
        TemplateMessage updatedMessage = messageRepository.findById(id)
                .map(item -> {
                    item.setAuthor(message.getAuthor());
                    item.setText(message.getText());
                    return item;
                })
                .orElseThrow(() -> new NotFoundException(String.format("Message with id: %s not found", id)));

        messageRepository.save(updatedMessage);
    }

    @Transactional
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}

