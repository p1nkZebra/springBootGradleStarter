package com.company.springBootTemplate.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    private final static Logger LOG = LoggerFactory.getLogger(TemplateService.class);

    @Autowired
    private TemplateSubService templateSubService;


    ///---///


    public String sayHelloMethod() {
        LOG.info("Called sayHelloMethod() in TemplateService");

        return "Hello from TemplateService!";
    }

    public String sayHelloFromSubService() {
        LOG.info("Called sayHelloFromSubService() in TemplateService");

        return sayHelloMethod() + " and " + templateSubService.sayHelloMethod();
    }


}
