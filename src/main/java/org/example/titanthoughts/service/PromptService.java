package org.example.titanthoughts.service;

import lombok.RequiredArgsConstructor;
import org.example.titanthoughts.entity.Prompt;
import org.example.titanthoughts.repository.PromptRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromptService {
    final private PromptRepository promptRepository;

    public Prompt getPromptById(String id) {
        return promptRepository.findById(id).orElseThrow();
    }

    public Prompt savePrompt(String characterNames, String question, String answer) {
        Prompt prompt = new Prompt();
        prompt.setCharacterNames(characterNames);
        prompt.setQuestion(question);
        prompt.setAnswer(answer);
        prompt.setCreatedAt(ZonedDateTime.now(ZoneOffset.UTC));
        return promptRepository.save(prompt);
    }
}