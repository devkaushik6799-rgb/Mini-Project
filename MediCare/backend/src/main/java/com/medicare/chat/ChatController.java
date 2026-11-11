package com.medicare.chat;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService service;
    public ChatController(ChatService service) { this.service = service; }

    public record ChatRequest(@NotBlank(message = "Message cannot be empty") String message) {}
    public record ChatResponse(String reply) {}

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        return new ChatResponse(service.reply(request.message().trim()));
    }
}
