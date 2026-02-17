package com.chinmayshivratriwar.dev_playground.ai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ollama")

public class OllamaController {

    //private final OllamaChatModel ollamaChatModel;
    private final ChatClient chatClient;

    public OllamaController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/message")
    public ResponseEntity<String> askOllama(@RequestParam(value = "message", defaultValue = "Who are you") String message)
    {
        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok(response);
    }
}
