package com.company.springBootTemplate.service;

import com.company.springBootTemplate.domain.TemplateAuthor;
import com.company.springBootTemplate.infrastructure.exception.NotFoundException;
import com.company.springBootTemplate.repository.AuthorRepository;
import com.company.springBootTemplate.repository.MessageRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private MessageRepository messageRepository;

    List<TemplateAuthor> authors = createAuthorList();
    List<TemplateAuthor> authorsWithSameName = createAuthorListWithSameName();


    @Test
    public void whenGetAllAuthors_thanReturnAllTemplateAuthors() {
        when(authorRepository.findAll()).thenReturn(authors);


        List<TemplateAuthor> allAuthors = authorService.getAllAuthors();

        assertEquals(2, allAuthors.size());
    }

    @Test
    public void whenGetExistingAuthorInfoById_thanReturnTemplateAuthor() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authors.get(0)));


        TemplateAuthor authorInfo = authorService.getAuthorInfo(1L);


        assertEquals("Alex", authorInfo.getName());
        assertEquals("12", authorInfo.getAge().toString());
    }

    @Test(expected = NotFoundException.class)
    public void whenAuthorIdNotFound_thanThrowException() {
        when(authorRepository.findById(1L)).thenThrow(new NotFoundException("Author not found"));


        authorService.getAuthorInfo(1L);
    }

    @Test
    public void whenGetAuthorByExistingName_thanReturnTemplateAuthorList() {
        when(authorRepository.findByName("Alex")).thenReturn(authorsWithSameName);


        List<TemplateAuthor> allAuthors = authorService.getAuthorsByName("Alex");

        assertEquals(3, allAuthors.size());
    }

    private List<TemplateAuthor> createAuthorList() {
        List<TemplateAuthor> authors = new ArrayList<>();
        TemplateAuthor firstAuthor = new TemplateAuthor();
        firstAuthor.setId(1L);
        firstAuthor.setName("Alex");
        firstAuthor.setAge(12L);
        TemplateAuthor secondAuthor = new TemplateAuthor();
        secondAuthor.setId(2L);
        secondAuthor.setName("Mike");
        secondAuthor.setAge(null);

        authors.add(firstAuthor);
        authors.add(secondAuthor);

        return authors;
    }

    private List<TemplateAuthor> createAuthorListWithSameName() {
        List<TemplateAuthor> authors = new ArrayList<>();
        TemplateAuthor firstAuthor = new TemplateAuthor();
        firstAuthor.setId(1L);
        firstAuthor.setName("Alex");
        firstAuthor.setAge(24L);
        TemplateAuthor secondAuthor = new TemplateAuthor();
        secondAuthor.setId(2L);
        secondAuthor.setName("Alex");
        secondAuthor.setAge(48L);
        TemplateAuthor thirdAuthor = new TemplateAuthor();
        thirdAuthor.setId(2L);
        thirdAuthor.setName("Alex");
        thirdAuthor.setAge(39L);

        authors.add(firstAuthor);
        authors.add(secondAuthor);
        authors.add(thirdAuthor);

        return authors;
    }
}