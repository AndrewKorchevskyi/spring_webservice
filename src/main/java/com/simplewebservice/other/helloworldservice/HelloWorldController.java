package com.simplewebservice.other.helloworldservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("HelloWorldBean");
    }

    @GetMapping("/hello-world-bean/{name}")
    public HelloWorldBean helloWorldBeanPathVar(@PathVariable String name) {
        return new HelloWorldBean(String.format("HelloWorldBean, %s", name));
    }

    @GetMapping("/hello-world-int")
    public String helloWorldInt(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("hello.world.message", null, locale);
    }
}
