package com.company.springBootTemplate.repository;

import com.company.springBootTemplate.JpaDataConfigIT;
import com.company.springBootTemplate.domain.TemplateAuthor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AuthorRepositoryTest extends JpaDataConfigIT {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Before
    public void setUp() throws Exception {
        TemplateAuthor firstAuthor = new TemplateAuthor();
        firstAuthor.setName("Alex");
        firstAuthor.setAge(15L);

        TemplateAuthor secondAuthor = new TemplateAuthor();
        secondAuthor.setName("Alice");
        secondAuthor.setAge(28L);

        TemplateAuthor thirdAuthor = new TemplateAuthor();
        thirdAuthor.setName("Alex");
        thirdAuthor.setAge(36L);

        TemplateAuthor fourthAuthor = new TemplateAuthor();
        fourthAuthor.setName("Mike");
        fourthAuthor.setAge(51L);

        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(status -> {
            authorRepository.saveAll(Arrays.asList(
                    firstAuthor,
                    secondAuthor,
                    thirdAuthor,
                    fourthAuthor)
            );
            status.flush();
            return status;
        });
    }

    @After
    public void tearDown() throws Exception {
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(status -> {
            messageRepository.deleteAll();
            authorRepository.deleteAll();
            status.flush();

            return status;
        });
    }

    @Test
    public void whenGetAuthorsByExistingName_thanReturnAllAuthorsWithThatName() {
        List<TemplateAuthor> result = authorRepository.findByName("Alex");


        assertEquals(2, result.size());
    }


    @Test
    public void whenGetAuthorsByNoExistingName_thanReturnEmptyAuthorList() {
        List<TemplateAuthor> result = authorRepository.findByName("no name");


        assertEquals(0, result.size());
    }
}