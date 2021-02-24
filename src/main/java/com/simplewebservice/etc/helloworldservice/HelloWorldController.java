package com.simplewebservice.etc.helloworldservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

@ApiIgnore
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
    public String helloWorldInt() {
        return messageSource.getMessage("hello.world.message", null, LocaleContextHolder.getLocale());
    }

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);
        return localeResolver;
    }
}
