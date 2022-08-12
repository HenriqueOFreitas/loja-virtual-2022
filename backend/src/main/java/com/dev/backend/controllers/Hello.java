package com.dev.backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Hello {
    
    @GetMapping("/")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Olá mundo spring!");
    }
}
