package com.exzray.spring6security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    @GetMapping
    public ResponseEntity<?> hello() {
        Map<String, Object> data = new HashMap<>();
        data.put("name", "Exzray");

        return ResponseEntity.ok(data);
    }
}
