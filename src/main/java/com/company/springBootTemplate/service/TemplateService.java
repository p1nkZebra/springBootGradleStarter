package com.company.springBootTemplate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {

    @Autowired
    private TemplateSubService templateSubService;


    ///---///


    public String sayHelloMethod() {
        return "Hello from TemplateService!";
    }

    public String sayHelloFromSubService() {
        return sayHelloMethod() + " and " + templateSubService.sayHelloMethod();
    }


}
