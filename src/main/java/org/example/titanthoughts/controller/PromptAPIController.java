package org.example.titanthoughts.controller;

import lombok.RequiredArgsConstructor;
import org.example.titanthoughts.dto.PromptForm;
import org.example.titanthoughts.entity.Prompt;
import org.example.titanthoughts.service.GeminiService;
import org.example.titanthoughts.service.PromptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/api/prompt")
@RequiredArgsConstructor
@CrossOrigin
public class PromptAPIController {
    private final PromptService promptService;
    private final GeminiService geminiService;

    @PostMapping
    public ResponseEntity<Prompt> savePrompt(
            @RequestBody PromptForm promptForm
    ) {
        String result = geminiService.generate(promptForm.text());
        Prompt data = promptService.savePrompt(
                promptForm.text(),
                result
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prompt> getPrompt(@PathVariable("id") String id) {
        return ResponseEntity.ok(promptService.getPromptById(id));
    }
}