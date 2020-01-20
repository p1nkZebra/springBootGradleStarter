package com.company.springBootTemplate.controller.swagger;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@Api(tags={"Hello controller"})
@RestController
@RequestMapping("app-hello")
public class HelloController {
    private final static Logger LOG = LoggerFactory.getLogger(HelloController.class);

    @ApiOperation("Return mock Page with text \"Hello page for Spring Boot Template App\"")
    @GetMapping("/hello")
    public String helloPage() {
        LOG.info("Called URL: /app-hello/hello");
        return "Hello page for Spring Boot Template App!";
    }

}

