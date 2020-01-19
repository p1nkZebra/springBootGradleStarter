package com.company.springBootTemplate.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security")
public class BasicSecurityController {
    private final static Logger LOG = LoggerFactory.getLogger(BasicSecurityController.class);

    @GetMapping("/admin")
    public String adminPage() {
        LOG.info("Called URL: /security/admin");
        return "ADMIN Page for Spring Security Testing!";
    }

    @GetMapping("/any")
    public String anyRolePage() {
        LOG.info("Called URL: /security/any");
        return "ANY Role Page for Spring Security Testing!";
    }

}
