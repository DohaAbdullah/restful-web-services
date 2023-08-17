package com.rest.webservices.restfulwebservices.controller;


import com.rest.webservices.restfulwebservices.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private MessageSource messageSource;
    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    @RequestMapping(path = "/hello-world")
    public String HelloWorld() {
        return "Hello World";
    }

    @RequestMapping(path = "hello-world-bean")
    public HelloWorldBean HelloWorldBean() {
        return new HelloWorldBean("hello world bean");
    }

    @RequestMapping(path = "hello-world/path-variable/{name}")
    public HelloWorldBean HelloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello world %s", name));
    }

    @GetMapping(path = "hello-world-internationalized")
    public String HelloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }


}
