package com.company.springBootTemplate.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags={"Hello controller"})
@RestController
@RequestMapping("template-app-api")
public class HelloController {

    @ApiOperation("Return mock Page with text \"Hello page for Spring Boot Template App\"")
    @GetMapping("/hello")
    public String helloPage() {
        return "Hello page for Spring Boot Template App";
    }

}
