package org.test.canema.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.test.canema.service.impl.GeminiService;

@RestController
@RequestMapping("/api/v1/ai")
@RequiredArgsConstructor
public class GeminiController {

    private final GeminiService geminiService;

    @PostMapping("/ask")
    public ResponseEntity<String> ask(@RequestBody String prompt) {
        String response = geminiService.askGemini(prompt);
        return ResponseEntity.ok(response);
    }
}