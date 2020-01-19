package com.company.springBootTemplate.repository;

import com.company.springBootTemplate.JpaDataConfigIT;
import com.company.springBootTemplate.domain.TemplateAuthor;
import com.company.springBootTemplate.domain.TemplateMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MessageRepositoryTest extends JpaDataConfigIT {

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

        TemplateMessage firstMessage = new TemplateMessage();
        firstMessage.setAuthor(firstAuthor);
        firstMessage.setText("First test message from Alex");

        TemplateMessage secondMessage = new TemplateMessage();
        secondMessage.setAuthor(secondAuthor);
        secondMessage.setText("First test message from Alice");

        TemplateMessage thirdMessage = new TemplateMessage();
        thirdMessage.setAuthor(secondAuthor);
        thirdMessage.setText("Second test message from Alice");


        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(status -> {
            authorRepository.saveAll(Arrays.asList(
                    firstAuthor,
                    secondAuthor)
            );
            messageRepository.saveAll(Arrays.asList(
                    firstMessage,
                    secondMessage,
                    thirdMessage)
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
    public void whenGetMessagesByExistingAuthorName_thanReturnAllMessagesForAuthorsWithThatName() {
        List<TemplateMessage> result = messageRepository.findByAuthorName("Alex");


        assertEquals(1, result.size());
    }

    @Test
    public void whenGetMessagesByNoExistingAuthorName_thanReturnEmptyMessagesList() {
        List<TemplateMessage> result = messageRepository.findByAuthorName("no name");


        assertEquals(0, result.size());
    }

    @Test
    public void whenGetMessagesByNoExistingAuthorId_thanReturnAllMessagesForAuthorWithThatId() {
        List<TemplateAuthor> all = authorRepository.findByName("Alice");
        Long aliceId = all.get(0).getId();


        List<TemplateMessage> result = messageRepository.findByAuthorId(aliceId);


        assertEquals(2, result.size());
    }

    @Test
    public void whenGetMessagesByNoExistingAuthorId_thanReturnEmptyMessagesList() {
        List<TemplateMessage> result = messageRepository.findByAuthorId(5L);


        assertEquals(0, result.size());
    }


}