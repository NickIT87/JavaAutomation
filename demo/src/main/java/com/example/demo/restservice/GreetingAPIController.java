package com.example.demo.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingAPIController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greetingapi")
    public GreetingAPI greetingAPI(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new GreetingAPI(counter.incrementAndGet(), String.format(template, name));
    }
}