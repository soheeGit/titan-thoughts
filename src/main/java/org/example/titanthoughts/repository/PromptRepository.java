package org.example.titanthoughts.repository;

import org.example.titanthoughts.entity.Prompt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromptRepository extends JpaRepository<Prompt, String> {
}