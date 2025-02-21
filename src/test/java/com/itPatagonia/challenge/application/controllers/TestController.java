package com.itPatagonia.challenge.application.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/illegal-argument")
    public String throwIllegalArgumentException() {
        throw new IllegalArgumentException("Argumento inválido");
    }

    @GetMapping("/generic-exception")
    public String throwGenericException() {
        throw new RuntimeException("Error genérico");
    }
}