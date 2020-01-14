package com.company.springBootTemplate.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TemplateServiceTest {

    @InjectMocks
    private TemplateService templateService;
    @Mock
    private TemplateSubService templateSubService;


    ///---///


    @Test
    public void say_hello_test() {


        String result = templateService.sayHelloMethod();


        assertEquals("Hello from TemplateService!", result);
    }

    @Test
    public void say_hello_from_sub_service_test() {
        when(templateSubService.sayHelloMethod()).thenReturn("Hello from TemplateSubService!");


        String result = templateService.sayHelloFromSubService();


        assertEquals("Hello from TemplateService! and Hello from TemplateSubService!", result);
    }

}