package com.company.springBootTemplate.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TemplateSubService {
    private final static Logger LOG = LoggerFactory.getLogger(TemplateSubService.class);

    public String sayHelloMethod() {
        LOG.info("Called sayHelloFromSubService() in TemplateSubService");

        return "Hello from TemplateSubService!";
    }


}
