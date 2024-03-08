package com.example.springaidemo.controller;

import com.example.springaidemo.service.AIImageService;
import com.example.springaidemo.service.AIService;
import org.springframework.ai.image.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AIController {

    private final AIService aiService;

    private final AIImageService imageService;

    public AIController(AIService aiService, AIImageService imageService) {
        this.aiService = aiService;
        this.imageService = imageService;
    }


    @GetMapping("/joke")
    public String getJoke(@RequestParam String topic) {
        return aiService.getJoke(topic);
    }

    @GetMapping("/books")
    public String getBook(@RequestParam String category, @RequestParam String year) {
        return aiService.getBooks(category, year);
    }

    @GetMapping("/image")
    public Image getImage() {
        return imageService.getImage();
    }

}
