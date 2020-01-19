package com.company.springBootTemplate.controller;


import com.company.springBootTemplate.domain.TemplateMessage;
import com.company.springBootTemplate.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"Message REST controller"})
@RestController
@RequestMapping("rest-api")
public class MessageRestController {
    private final static Logger LOG = LoggerFactory.getLogger(MessageRestController.class);

    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }


    @ApiOperation("Return All Messages from in JSON format")
    @GetMapping("/get-all-messages")
    public List<TemplateMessage> getMessageList() {
        LOG.info("Called URL: /rest-api/get-all-messages");
        return messageService.getAllMessages();
    }

    @ApiOperation("Return Message by ID number in JSON format")
    @GetMapping("/get-message/{id}")
    public TemplateMessage getMessage(@PathVariable Long id) {
        LOG.info("Called URL: /rest-api/get-message/" + id);
        return messageService.getMessage(id);
    }

    @ApiOperation("Return Messages by Author name in JSON format")
    @GetMapping("/get-messages-by-author/{authorName}")
    public List<TemplateMessage> getMessageForAuthor(@PathVariable String authorName) {
        LOG.info("Called URL: /rest-api/get-messages-by-author/" + authorName);
        return messageService.getMessagesForAuthor(authorName);
    }

    @ApiOperation("Return All Messages with new added operation in JSON format")
    @PostMapping("/add-message")
    public List<TemplateMessage> addTemplateMessage(@RequestBody TemplateMessage message) {
        LOG.info("Called URL: /rest-api/add-message with requestBody: {}", message);
        messageService.saveNewMessage(message);
        return messageService.getAllMessages();
    }

    @ApiOperation("Return All Messages with update operation by ID in JSON format")
    @PutMapping("/update-message/{id}")
    public List<TemplateMessage> updateTemplateMessage(@PathVariable Long id,
                                                       @RequestBody TemplateMessage message) {
        LOG.info("Called URL: /rest-api/update-message/" + id);
        messageService.updateMessage(id, message);
        return messageService.getAllMessages();
    }

    @ApiOperation("Return All Messages with delete operation by ID in JSON format")
    @DeleteMapping("/delete-message/{id}")
    public List<TemplateMessage> addTemplateMessage(@PathVariable Long id) {
        LOG.info("Called URL: /rest-api/delete-message/" + id);
        messageService.deleteMessage(id);
        return messageService.getAllMessages();
    }

}

