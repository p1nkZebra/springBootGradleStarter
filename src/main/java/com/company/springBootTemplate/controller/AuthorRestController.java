package com.company.springBootTemplate.controller;


import com.company.springBootTemplate.domain.TemplateAuthor;
import com.company.springBootTemplate.service.AuthorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"Author REST controller"})
@RestController
@RequestMapping("rest-api")
public class AuthorRestController {
    private final static Logger LOG = LoggerFactory.getLogger(AuthorRestController.class);

    private final AuthorService authorService;

    @Autowired
    public AuthorRestController(AuthorService authorService) {
        this.authorService = authorService;
    }


    @ApiOperation("Return Author's Info from database in JSON format")
    @GetMapping("/get-all-authors")
    public List<TemplateAuthor> getAuthors() {
        LOG.info("Called URL: /rest-api/get-all-authors");
        return authorService.getAllAuthors();
    }

    @ApiOperation("Return Author by ID number in JSON format")
    @GetMapping("/get-author-by-id/{id}")
    public TemplateAuthor getAuthorById(@PathVariable Long id) {
        LOG.info("Called URL: /rest-api/get-author-by-id/" + id);
        return authorService.getAuthorInfo(id);
    }

    @ApiOperation("Return Authors by Name in JSON format")
    @GetMapping("/get-author-by-name/{name}")
    public List<TemplateAuthor> getAuthorByName(@PathVariable String name) {
        LOG.info("Called URL: /rest-api/get-author-by-name/" + name);
        return authorService.getAuthorsByName(name);
    }

    @ApiOperation("Return Author's Info with new added Author in JSON format")
    @PostMapping("/add-author")
    public List<TemplateAuthor> addAuthor(@RequestBody TemplateAuthor author) {
        LOG.info("Called URL: /rest-api/add-author with requestBody: {}", author);
        authorService.saveNewAuthor(author);
        return authorService.getAllAuthors();
    }

    @ApiOperation("Return Author's Info with updated Author by ID in JSON format")
    @PutMapping("/update-author/{id}")
    public List<TemplateAuthor> updateAuthor(@PathVariable Long id,
                                             @RequestBody TemplateAuthor author) {
        LOG.info("Called URL: /rest-api/update-author/" + id);
        authorService.updateAuthorInfo(id, author);
        return authorService.getAllAuthors();
    }

    @ApiOperation("Return Author's Info with deleted Author by ID in JSON format")
    @DeleteMapping("/delete-author/{id}")
    public List<TemplateAuthor> deleteAuthor(@PathVariable Long id) {
        LOG.info("Called URL: /rest-api/delete-author/" + id);
        authorService.deleteAuthor(id);
        return authorService.getAllAuthors();
    }

}

