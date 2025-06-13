package org.example.titanthoughts.controller;

import lombok.RequiredArgsConstructor;
import org.example.titanthoughts.dto.PromptForm;
import org.example.titanthoughts.entity.Prompt;
import org.example.titanthoughts.service.GeminiService;
import org.example.titanthoughts.service.PromptService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PromptController {
    private final PromptService promptService;
    private final GeminiService geminiService;
    @Value("${server.baseurl}")
    private String baseUrl;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("promptForm", new PromptForm("", ""));
        return "index";
    }

    @PostMapping
    public String submit(PromptForm promptForm, Model model) {
        String character = promptForm.getCharacter();
        String result = geminiService.generate(character, promptForm.getText());

        // eren, mikasa, armin, levi, zeke

        model.addAttribute("promptText", result);

        Prompt data = promptService.savePrompt(
                character,                 // 캐릭터 이름
                promptForm.getText(),      // 입력된 고민
                result                     // LLM 응답
        );

        model.addAttribute("promptData", data);
        model.addAttribute("baseUrl", baseUrl);
        return "index";
    }

//    @GetMapping("/history/{id}")
//    public String history(@PathVariable("id") String id, Model model) {
//        model.addAttribute("promptData", promptService.getPromptById(id));
//        model.addAttribute("baseUrl", baseUrl);
//        return "history";
//    }
}