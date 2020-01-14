package com.company.springBootTemplate.controller;

import com.company.springBootTemplate.domain.TemplateMessage;
import com.company.springBootTemplate.infrastructure.exception.PageNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Api(tags={"Hello controller"})
@RestController
@RequestMapping("template-app-api")
public class HelloController {
    private final static Logger LOG = LoggerFactory.getLogger(HelloController.class);

    private List<TemplateMessage> testTemplateMessageList = new ArrayList<TemplateMessage>() {{
        add(new TemplateMessage(1L,"One"));
        add(new TemplateMessage(2L,"Two"));
        add(new TemplateMessage(3L,"Three"));
    }};


    ///---///


//    @ApiOperation("Return mock Page with text \"Hello page for Spring Boot Template App\"")
//    @GetMapping("/page-not-found")
//    public String notFoundPage() {
//        LOG.info("Called URL: /template-app-api/page-not-found");
//        throw new PageNotFoundException();
//    }

    @ApiOperation("Return mock Page with text \"Hello page for Spring Boot Template App\"")
    @GetMapping("/hello")
    public String helloPage() {
        LOG.info("Called URL: /template-app-api/hello");
        return "Hello page for Spring Boot Template App";
    }

    @ApiOperation("Return TemplateMessageList in JSON format")
    @GetMapping("/get-all-messages")
    public List<TemplateMessage> getTemplateMessageList() {
        LOG.info("Called URL: /template-app-api/get-all-messages");
        return testTemplateMessageList;
    }

    @ApiOperation("Return TemplateMessage by ID number in JSON format")
    @GetMapping("/get-message/{id}")
    public TemplateMessage getTemplateMessage(@PathVariable Long id) {
        LOG.info("Called URL: /template-app-api/get-messages");
        return findTemplateMessageById(id);
    }

    @ApiOperation("Return TemplateMessageList with new added operation in JSON format")
    @PostMapping("/add-message")
    public List<TemplateMessage> addTemplateMessage(@RequestBody TemplateMessage message) {
        LOG.info("Called URL: /template-app-api/add-message");
        testTemplateMessageList.add(message);
        return testTemplateMessageList;
    }

    @ApiOperation("Return TemplateMessageList with update operation by ID in JSON format")
    @PutMapping("/update-message/{id}")
    public List<TemplateMessage> addTemplateMessage(@PathVariable Long id,
                                                    @RequestBody TemplateMessage message) {
        LOG.info("Called URL: /template-app-api/add-message");

        TemplateMessage msg = findTemplateMessageById(id);
        msg.setId(message.getId());
        msg.setText(message.getText());

        return testTemplateMessageList;
    }

    @ApiOperation("Return TemplateMessageList with delete operation by ID in JSON format")
    @DeleteMapping("/delete-message/{id}")
    public List<TemplateMessage> addTemplateMessage(@PathVariable Long id) {
        LOG.info("Called URL: /template-app-api/add-message");

        testTemplateMessageList = testTemplateMessageList.stream()
                .filter(item -> !item.getId().equals(id))
                .collect(Collectors.toList());

        return testTemplateMessageList;
    }

    private TemplateMessage findTemplateMessageById(Long id) {
        return testTemplateMessageList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .orElseThrow(PageNotFoundException::new);
    }

}
