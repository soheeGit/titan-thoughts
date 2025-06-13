package org.example.titanthoughts.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptForm {
    private String character;
    private String text;

    public PromptForm() {}

    public PromptForm(String character, String text) {
        this.character = character;
        this.text = text;
    }
}